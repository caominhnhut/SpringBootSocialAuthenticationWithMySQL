# Spring Boot MySQL Example

A simple Spring Boot application demonstrating integration with MySQL database and Google OAuth2 login.

## Prerequisites

- Java 17
- Maven
- Docker and Docker Compose

## Setup

1. Start MySQL using Docker Compose:
```bash
docker compose up -d
```

2. Build the project:
```bash
mvn clean install
```

3. Run the application with desired profile:
```bash
mvn spring-boot:run -Dspring-boot.run.profiles=dev
```

## Configuration

### Google OAuth2 Setup

1. Go to [Google Cloud Console](https://console.cloud.google.com/)
2. Create a new project or select existing one
3. Navigate to APIs & Services > Credentials
4. Click "Create Credentials" > "OAuth client ID"
5. Choose "Web application"
6. Add authorized redirect URI: `http://localhost:8080/grantcode`
7. Note down your Client ID and Client Secret

Update these credentials in `src/main/resources/application-dev.yaml`:

```yaml
google:
  client:
    id: YOUR_CLIENT_ID
    secret: YOUR_CLIENT_SECRET
```

Also update `loginpage/index.html` with your client ID in the OAuth URL.

### Database Configuration

The application uses Spring profiles for different environments:
- `dev` - Development environment
- `prod` - Production environment
- `test` - Testing environment

Each profile has its own configuration in:
- `application-dev.properties`
- `application-prod.properties`
- `application-test.properties`

Choose the appropriate profile when running the application.

Update `src/main/resources/application.properties`:

```properties
spring.datasource.url=jdbc:mysql://localhost:3306/your_database
spring.datasource.username=your_username
spring.datasource.password=your_password
```

## API Endpoints

### User Endpoints
- GET `/api/users` - Get all users
- GET `/api/users/{id}` - Get user by ID
- POST `/api/users` - Create new user
- PUT `/api/users/{id}` - Update user
- DELETE `/api/users/{id}` - Delete user

### Role Endpoints
- GET `/api/roles` - Get all roles
- GET `/api/roles/{id}` - Get role by ID
- POST `/api/roles` - Create new role
- PUT `/api/roles/{id}` - Update role
- DELETE `/api/roles/{id}` - Delete role

### User-Role Endpoints
- GET `/api/user-roles` - Get all user roles
- GET `/api/user-roles/{id}` - Get user role by ID
- POST `/api/user-roles` - Create new user role
- PUT `/api/user-roles/{id}` - Update user role
- DELETE `/api/user-roles/{id}` - Delete user role

## Demo OAuth2 Login

1. Simply open the file in your browser:
```bash
cd loginpage
# Double click index.html or open it directly in your browser
```

2. Click "Sign in with Google" button
   - This will redirect to Google's login page
   - After authentication, Google will redirect back with the user info
   - You will be redirected to a success page

## Technologies

- Spring Boot
- Spring Data JPA
- Spring Security
- Spring Audit
- MySQL
- Flyway (Database Migration)
- Maven
- Lombok
- OAuth2 Client
- Google Sign-In
