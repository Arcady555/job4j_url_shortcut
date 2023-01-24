# **RESTfull приложение - Сервис по обработке закодированных ссылок**

## Welcome!

_Благодаря этой программе, вы обеспечите безопасность пользователей Вашего сайта, заменив ссылки в нём ссылками 
на этот сервис_
       
## Используемые технологии:

* Java 18

* Spring Boot (v2.7.3)

* Apache Tomcat/9.0.65

* Maven 4.0.0

* JDBC

* Hibernate ORM core version 5.6.11.Final

* Liquibase 

* PostgresSQL

* Lombok

## Требования к окружению

Maven 3.8.1

OpenJDK 18.0.1

Postgresql 14 


## Запуск приложения

### 1a. Используя Ваши данные:

```
mvn spring-boot:run -Dspring-boot.run.arguments=--db=your_database,
--user=your_user,--password=your_password,--port=your_port
```

### 1b. Или используя данные по умолчанию(db=shortcut, user=postgres, password=password, port=8080).

```
mvn spring-boot:run
```

### 2. Примеры запросов в -curl.
 #### Регистрация Вашего сайта:
```
curl --location --request POST 'http://localhost:8080/registration' \
--header 'Content-Type: application/json' \
--data-raw '{
    "site": "your_site.com"
}'
```
#### Получение токена:
```
curl --location --request POST 'http://localhost:8080/login' \
--header 'Accept: application/json' \
--header 'Content-Type: application/json' \
--data-raw '{
    "login": "your_login",
    "password": "your_password"
}'
```
#### Получение кода Вашей ссылки:
```
curl --location --request POST 'http://localhost:8080/convert' \
--header 'Authorization: Bearer your_token\
--header 'Content-Type: application/json' \
--data-raw '{
    "url": "http://your_site/et cetera"
}'
```
#### Переадресация кода на ссылку:
```
curl --location --request GET 'http://localhost:8080/redirect/this_code' \
```
#### Получение статистики:
```
curl --location --request GET 'http://localhost:8080/statistic' \
--header 'Authorization: Bearer your_token\
```
## Have a good job!

Ваши пожелания -- parfenov7233@gmail.com