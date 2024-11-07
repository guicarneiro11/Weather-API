# Weather API

Uma API REST em Spring Boot para gerenciar cidades favoritas e obter informações meteorológicas usando a API OpenWeatherMap.

## Tecnologias

- Java 17
- Spring Boot 3.x
- PostgreSQL
- Maven
- OpenWeatherMap API
- JUnit 5
- Lombok
- Spring Data JPA

## Funcionalidades

- Gerenciamento de cidades favoritas (operações CRUD)
- Busca de dados meteorológicos em tempo real
- Listas de cidades específicas por usuário
- Atualizações automáticas do clima
- Cache de dados
- Tratamento de erros
- Validação de entrada
- Operações transacionais

## Pré-requisitos

- Java 17+
- Maven 3.8+
- PostgreSQL 12+
- Chave da API OpenWeatherMap

## Configuração

1. Clone o repositório
```bash
git clone https://github.com/guicarneiro11/weather-api.git
```

2. Crie o banco de dados
```sql
CREATE DATABASE weather_app;
```

3. Configure o application.properties
```bash
cp src/main/resources/application.properties.example src/main/resources/application.properties
```
Em seguida, edite o arquivo com suas credenciais do banco de dados e da API.

4. Compile o projeto
```bash
mvn clean install
```

5. Execute a aplicação
```bash
mvn spring-boot:run
```

## Endpoints da API

### Cidades Favoritas
- `POST /api/favorites` - Adicionar uma nova cidade favorita
- `GET /api/favorites?userId={userId}` - Buscar cidades favoritas do usuário
- `GET /api/favorites/{id}` - Buscar uma cidade favorita específica
- `PUT /api/favorites/{id}` - Atualizar uma cidade favorita
- `DELETE /api/favorites/{id}` - Deletar uma cidade favorita

### Dados Meteorológicos
- `GET /api/weather/cities/{cityName}/users/{userId}` - Buscar clima de uma cidade
- `GET /api/weather/cities/{cityName}/users/{userId}/history` - Buscar histórico do clima

## Testes

Execute os testes usando:
```bash
mvn test
```

## Como Contribuir

1. Faça um Fork do repositório
2. Crie sua branch de feature (`git checkout -b feature/nova-funcionalidade`)
3. Faça commit das suas alterações (`git commit -m 'Adiciona nova funcionalidade'`)
4. Faça push para a branch (`git push origin feature/nova-funcionalidade`)
5. Abra um Pull Request

## Contato

Guilherme Carneiro - [@guizaokt](https://twitter.com/seu_twitter) - guicarneiro.dev@gmail.com

Link do Projeto: [https://github.com/guicarneiro11/weather-api](https://github.com/guicarneiro11/weather-api)

## Status do Projeto

🚧 Em desenvolvimento 

## Próximos Passos

- [ ] Implementação de cache
- [ ] Melhorias na documentação
- [ ] Adição de mais testes
- [ ] Integração com CI/CD
- [ ] Implementação de métricas
- [ ] Dockerização da aplicação
- [ ] Integração com app android

---

⭐ Se este projeto te ajudou, considere dar uma estrela!

Desenvolvido com ❤️ por [Guizão](https://github.com/guicarneiro11)
