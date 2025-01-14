# ☁️ Weather API

API REST desenvolvida em Spring Boot para gerenciamento de cidades favoritas e obtenção de dados meteorológicos via OpenWeatherMap API.

## 🚀 Tecnologias Utilizadas

![Java](https://img.shields.io/badge/Java_17-ED8B00?style=for-the-badge&logo=openjdk&logoColor=white)
![Spring](https://img.shields.io/badge/Spring_Boot-6DB33F?style=for-the-badge&logo=spring&logoColor=white)
![PostgreSQL](https://img.shields.io/badge/PostgreSQL-316192?style=for-the-badge&logo=postgresql&logoColor=white)
![Maven](https://img.shields.io/badge/Maven-C71A36?style=for-the-badge&logo=apache-maven&logoColor=white)
![JUnit5](https://img.shields.io/badge/JUnit5-25A162?style=for-the-badge&logo=junit5&logoColor=white)
![Lombok](https://img.shields.io/badge/Lombok-BC4521?style=for-the-badge&logo=lombok&logoColor=white)

## 📋 Descrição

API responsável por gerenciar cidades favoritas dos usuários e fornecer informações meteorológicas em tempo real através da integração com OpenWeatherMap API. Inclui recursos de cache, validação e tratamento de erros.

## 🛠️ Funcionalidades

- ✅ Gerenciamento completo de cidades favoritas (CRUD)
- ✅ Dados meteorológicos em tempo real
- ✅ Listas personalizadas por usuário
- ✅ Cache de dados para performance
- ✅ Validação robusta de entrada
- ✅ Operações transacionais seguras
- ✅ Tratamento abrangente de erros

## 🔒 Segurança

- Validação de entrada
- Tratamento de exceções personalizado
- Operações transacionais
- Configurações sensíveis via variáveis de ambiente

## 📡 Endpoints

### Cidades Favoritas
```http
POST /api/favorites
GET /api/favorites?userId={userId}
GET /api/favorites/{id}
PUT /api/favorites/{id}
DELETE /api/favorites/{id}
```

### Dados Meteorológicos
```http
GET /api/weather/cities/{cityName}/users/{userId}
GET /api/weather/cities/{cityName}/users/{userId}/history
```

## 🏗️ Arquitetura

A API segue uma arquitetura em camadas e modelo MVC + Clean Architeture.

- config: Configurações do Spring Boot e beans
- controller: Endpoints REST e handlers de requisições
- dto: Objetos de transferência de dados
- exception: Classes de exceção customizadas e handlers
- external.openweathermap: 
  - data: DTOs e modelos para integração com OpenWeatherMap
- model: Entidades do domínio
- repository: Interfaces de acesso a dados com Spring Data JPA
- service: Lógica de negócios e orquestração


## ⚙️ Configuração Local

1. Clone o repositório
```bash
git clone https://github.com/guicarneiro11/weather-api.git
```

2. Crie o banco de dados
```sql
CREATE DATABASE weather_app;
```

3. Configure as variáveis de ambiente
```bash
cp src/main/resources/application.properties.example src/main/resources/application.properties
# Adicione suas credenciais
```

4. Execute o projeto
```bash
mvn spring-boot:run
```

## 📦 Dependências Principais

- Spring Boot 3.x
- Spring Data JPA
- PostgreSQL Driver
- Lombok
- JUnit 5
- OpenWeatherMap Client

## 🔍 Monitoramento

- Logs estruturados
- Métricas Spring Actuator
- Rastreamento de exceções

## 🤝 Integração

Esta API é parte do ecossistema Weather App:
- Aplicativo Android Weather
- OpenWeatherMap API
- PostgreSQL Database

## 📈 Próximos Passos

- [ ] Implementação de cache
- [ ] Melhorias na documentação
- [ ] Adição de mais testes
- [ ] Integração com CI/CD
- [ ] Implementação de métricas
- [ ] Dockerização da aplicação
- [ ] Integração com app android

## 👨‍💻 Autor

[Guilherme Carneiro](https://github.com/guicarneiro11) - [@guizaokt](https://twitter.com/seu_twitter)

---
