# GridenAuth - JWT Authorization Microservice 🔐

## 📋 Описание
GridenAuth — это микросервис для авторизации с поддержкой JWT, refresh-токенов, шифрованием паролей и полной документацией Swagger.

## 🚀 Технологии
- Java 17
- Spring Boot 3.x
- Spring Security
- JWT (jjwt)
- Maven
- Lombok
- Swagger (springdoc-openapi)

## 🛠️ Возможности
- Регистрация пользователя
- Авторизация (логин)
- Обновление Access токена через Refresh
- Шифрование паролей с BCrypt
- Stateless аутентификация

## 🛠️ Схема БД
https://drawsql.app/teams/neomodeon/diagrams/griden

## 🔧 Установка
```bash
git clone https://github.com/yourname/gridenauth.git
cd gridenauth
./mvnw spring-boot:run

