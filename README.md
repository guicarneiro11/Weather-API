# ☁️ Weather API
REST API developed in Spring Boot for managing favorite cities and obtaining weather data via OpenWeatherMap API.

## 🚀 Technologies Used
![Java](https://img.shields.io/badge/Java_17-ED8B00?style=for-the-badge&logo=openjdk&logoColor=white)
![Spring](https://img.shields.io/badge/Spring_Boot-6DB33F?style=for-the-badge&logo=spring&logoColor=white)
![PostgreSQL](https://img.shields.io/badge/PostgreSQL-316192?style=for-the-badge&logo=postgresql&logoColor=white)
![Maven](https://img.shields.io/badge/Maven-C71A36?style=for-the-badge&logo=apache-maven&logoColor=white)
![JUnit5](https://img.shields.io/badge/JUnit5-25A162?style=for-the-badge&logo=junit5&logoColor=white)
![Lombok](https://img.shields.io/badge/Lombok-BC4521?style=for-the-badge&logo=lombok&logoColor=white)

## 📋 Description
API responsible for managing users' favorite cities and providing real-time weather information through integration with OpenWeatherMap API. Includes caching, validation, and error handling features.

## 🛠️ Features
- ✅ Complete favorite cities management (CRUD)
- ✅ Real-time weather data
- ✅ Custom lists per user
- ✅ Data caching for performance
- ✅ Robust input validation
- ✅ Secure transactional operations
- ✅ Error handling

## 🔒 Security
- Input validation
- Custom exception handling
- Transactional operations
- Sensitive configurations via environment variables

## 📡 Endpoints
### Favorite Cities
```http
POST /api/favorites
GET /api/favorites?userId={userId}
GET /api/favorites/{id}
PUT /api/favorites/{id}
DELETE /api/favorites/{id}
```
### Weather Data
```http
GET /api/weather/cities/{cityName}/users/{userId}
GET /api/weather/cities/{cityName}/users/{userId}/history
```

## 🏗️ Architecture
The API follows a layered architecture and MVC + Clean Architecture model.
- config: Spring Boot configurations and beans
- controller: REST endpoints and request handlers
- dto: Data transfer objects
- exception: Custom exception classes and handlers
- external.openweathermap: 
  - data: DTOs and models for OpenWeatherMap integration
- model: Domain entities
- repository: Data access interfaces with Spring Data JPA
- service: Business logic and orchestration

## ⚙️ Local Setup
1. Clone the repository
```bash
git clone https://github.com/guicarneiro11/weather-api.git
```
2. Create the database
```sql
CREATE DATABASE weather_app;
```
3. Configure environment variables
```bash
cp src/main/resources/application.properties.example src/main/resources/application.properties
# Add your credentials
```
4. Run the project
```bash
mvn spring-boot:run
```

## 📦 Main Dependencies
- Spring Boot 3.x
- Spring Data JPA
- PostgreSQL Driver
- Lombok
- JUnit 5
- OpenWeatherMap Client

## 🤝 Integration
This API is part of the Weather App ecosystem:
- Weather Android Application
- OpenWeatherMap API
- PostgreSQL Database

## 📈 Next Steps
- [ ] Cache implementation
- [ ] Documentation improvements
- [ ] Addition of more tests
- [ ] CI/CD integration
- [ ] Application dockerization
- [x] Android app integration

## 👨‍💻 Author
[Guilherme Carneiro](https://github.com/guicarneiro11) - [@guizaokt](https://x.com/guizaokt)
---
