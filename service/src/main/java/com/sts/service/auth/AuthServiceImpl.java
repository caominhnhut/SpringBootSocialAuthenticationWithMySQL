package com.sts.service.auth;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.sts.entity.UserEntity;
import com.sts.entity.UserStatus;
import com.sts.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.web.client.RestTemplate;

import java.util.UUID;

@Service
@Slf4j
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {

    private final UserRepository userRepository;

    @Value("${google.client.id}")
    private String clientId;

    @Value("${google.client.secret}")
    private String clientSecret;

    @Override
    public void processGrantCode(String code) {
        var accessToken = getOauthAccessTokenGoogle(code);

        var googleUser = getProfileDetailsGoogle(accessToken);
        userRepository.findByEmail(googleUser.getEmail()).orElseGet(() -> userRepository.save(googleUser));
    }

    private UserEntity getProfileDetailsGoogle(String accessToken) {
        var restTemplate = new RestTemplate();
        var httpHeaders = new HttpHeaders();
        httpHeaders.setBearerAuth(accessToken);

        var requestEntity = new HttpEntity<>(httpHeaders);

        var url = "https://www.googleapis.com/oauth2/v2/userinfo";
        var response = restTemplate.exchange(url, HttpMethod.GET, requestEntity, String.class);
        var jsonObject = new Gson().fromJson(response.getBody(), JsonObject.class);
        log.info("jsonObject: {}", jsonObject);

        return UserEntity.builder().email(jsonObject.get("email").toString().replace("\"", ""))
                .name(jsonObject.get("name").toString().replace("\"", "") + " -- " + jsonObject.get("given_name").toString().replace("\"", ""))
                .password(UUID.randomUUID().toString())
                .status(UserStatus.ACTIVE).build();
    }

    private String getOauthAccessTokenGoogle(String code) {
        var restTemplate = new RestTemplate();
        var httpHeaders = new HttpHeaders();
        httpHeaders.setContentType(MediaType.APPLICATION_FORM_URLENCODED);

        var params = new LinkedMultiValueMap<String, String>();
        params.add("code", code);
        params.add("redirect_uri", "http://localhost:8080/grantcode");
        params.add("client_id", clientId);
        params.add("client_secret", clientSecret);
        params.add("scope", "https%3A%2F%2Fwww.googleapis.com%2Fauth%2Fuserinfo.profile");
        params.add("scope", "https%3A%2F%2Fwww.googleapis.com%2Fauth%2Fuserinfo.email");
        params.add("scope", "openid");
        params.add("grant_type", "authorization_code");

        var requestEntity = new HttpEntity<>(params, httpHeaders);

        var url = "https://oauth2.googleapis.com/token";
        var response = restTemplate.postForObject(url, requestEntity, String.class);
        var jsonObject = new Gson().fromJson(response, JsonObject.class);

        return jsonObject.get("access_token").toString().replace("\"", "");
    }

}
