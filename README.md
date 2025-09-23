# 🏦 API Банкомата

## 📋 Функционал
- **Авторизация пользователя** по номеру карты и PIN-коду
- **Просмотр баланса** в трех валютах (BYN, USD, EUR) с автоматической конвертацией
- **Снятие денег** в любой из поддерживаемых валют
- **Пополнение счета** в любой из поддерживаемых валют
- **Перевод денег** на карту другого пользователя
- **Оплата услуг** по коду услуги (электроэнергия, вода, газ, мобильная связь, интернет)
- **Swagger UI** для интерактивного тестирования API
- **Полная локализация** - все сообщения об ошибках на русском языке

## 🚀 Запуск приложения
```bash
mvn spring-boot:run
```

## 📚 Документация API
- **Swagger UI**: http://localhost:8080/swagger-ui.html
- **OpenAPI спецификация**: http://localhost:8080/v3/api-docs

## 🔧 Технологии
- **Spring Boot 3.4.2**
- **PostgreSQL** - база данных
- **JPA/Hibernate** - ORM
- **Lombok** - уменьшение boilerplate кода
- **Swagger/OpenAPI** - документация API
- **Jakarta Validation** - валидация входных данных
- **BigDecimal** - точные финансовые вычисления




## 💰 Курсы валют
- **1 USD = 3.0 BYN**
- **1 EUR = 3.5 BYN**
- **BYN** - базовая валюта

## 📝 Примеры запросов

### 1. 🔐 Аутентификация пользователя
```http
POST http://localhost:8080/api/v1/auth
Content-Type: application/json

{
    "cardNumber": "1111111111111111",
    "password": "1234"
}
```

**Ответ:**
```json
{
    "userName": "Vassily Petrov",
    "balanceByn": 57174.00,
    "balanceUsd": 19058.00,
    "balanceEur": 16335.43
}
```

### 2. 💳 Получение баланса
```http
GET http://localhost:8080/api/v1/balance/1111111111111111
```

**Ответ:**
```json
{
    "message": "Balance user Vassily Petrov",
    "balanceByn": 57174.00,
    "balanceUsd": 19058.00,
    "balanceEur": 16335.43
}
```

### 3. 💸 Снятие денег в BYN
```http
POST http://localhost:8080/api/v1/withdraw
Content-Type: application/json

{
    "cardNumber": "1111111111111111",
    "amount": 1000.00,
    "currency": "byn"
}
```

**Ответ:**
```json
{
    "success": true,
    "message": "Снятие выполнено успешно",
    "balanceByn": 56174.00,
    "balanceUsd": 18724.67,
    "balanceEur": 16049.71
}
```

### 4. 💸 Снятие денег в USD (конвертируется в BYN)
```http
POST http://localhost:8080/api/v1/withdraw
Content-Type: application/json

{
    "cardNumber": "1111111111111111",
    "amount": 100.00,
    "currency": "usd"
}
```

**Ответ:**
```json
{
    "success": true,
    "message": "Снятие выполнено успешно",
    "balanceByn": 55874.00,
    "balanceUsd": 18624.67,
    "balanceEur": 15964.00
}
```

### 5. 💰 Пополнение счета в BYN
```http
POST http://localhost:8080/api/v1/deposit
Content-Type: application/json

{
    "cardNumber": "1111111111111111",
    "amount": 5000.00,
    "currency": "byn"
}
```

**Ответ:**
```json
{
    "success": true,
    "message": "Пополнение выполнено успешно",
    "remainingBalanceByn": 60874.00,
    "remainingBalanceUsd": 20291.33,
    "remainingBalanceEur": 17392.57
}
```

### 6. 💰 Пополнение счета в USD
```http
POST http://localhost:8080/api/v1/deposit
Content-Type: application/json

{
    "cardNumber": "1111111111111111",
    "amount": 1000.00,
    "currency": "usd"
}
```

**Ответ:**
```json
{
    "success": true,
    "message": "Пополнение выполнено успешно",
    "remainingBalanceByn": 63874.00,
    "remainingBalanceUsd": 21291.33,
    "remainingBalanceEur": 18249.71
}
```

### 7. 🔄 Перевод денег в BYN
```http
POST http://localhost:8080/api/v1/transfer
Content-Type: application/json

{
    "fromCardNumber": "1111111111111111",
    "toCardNumber": "2222222222222222",
    "amount": 5000.00,
    "currency": "byn"
}
```

**Ответ:**
```json
{
    "success": true,
    "message": "Перевод выполнен успешно"
}
```

### 8. 🔄 Перевод денег в USD (конвертируется в BYN)
```http
POST http://localhost:8080/api/v1/transfer
Content-Type: application/json

{
    "fromCardNumber": "1111111111111111",
    "toCardNumber": "2222222222222222",
    "amount": 100.00,
    "currency": "usd"
}
```

**Ответ:**
```json
{
    "success": true,
    "message": "Перевод выполнен успешно"
}
```

### 9. 💡 Оплата электроэнергии
```http
POST http://localhost:8080/api/v1/payment
Content-Type: application/json

{
    "cardNumber": "1111111111111111",
    "serviceCode": "ELEC001",
    "currency": "byn"
}
```

**Ответ:**
```json
{
    "success": true,
    "message": "Оплата выполнена",
    "serviceName": "Электроэнергия",
    "amountPaid": 100.00,
    "remainingBalanceByn": 63774.00,
    "remainingBalanceUsd": 21258.00,
    "remainingBalanceEur": 18221.14
}
```

### 10. 💧 Оплата водоснабжения
```http
POST http://localhost:8080/api/v1/payment
Content-Type: application/json

{
    "cardNumber": "1111111111111111",
    "serviceCode": "WATER002",
    "currency": "byn"
}
```

### 11. 🔥 Оплата газа
```http
POST http://localhost:8080/api/v1/payment
Content-Type: application/json

{
    "cardNumber": "1111111111111111",
    "serviceCode": "GAS003",
    "currency": "byn"
}
```

### 12. 📱 Оплата мобильной связи
```http
POST http://localhost:8080/api/v1/payment
Content-Type: application/json

{
    "cardNumber": "1111111111111111",
    "serviceCode": "PHONE004",
    "currency": "byn"
}
```

### 13. 🌐 Оплата интернета
```http
POST http://localhost:8080/api/v1/payment
Content-Type: application/json

{
    "cardNumber": "1111111111111111",
    "serviceCode": "INTERNET005",
    "currency": "byn"
}
```

## ❌ Примеры ошибок

### Ошибка: Несуществующая услуга
```http
POST http://localhost:8080/api/v1/payment
Content-Type: application/json

{
    "cardNumber": "1111111111111111",
    "serviceCode": "INVALID001",
    "currency": "byn"
}
```

**Ответ:**
```json
{
    "message": "Услуга не найдена или неактивна",
    "errorCode": "INTERNAL_ERROR",
    "timestamp": "2025-09-23T15:45:00.000"
}
```

### Ошибка: Недостаточно средств
```http
POST http://localhost:8080/api/v1/payment
Content-Type: application/json

{
    "cardNumber": "2222222222222222",
    "serviceCode": "ELEC001",
    "currency": "byn"
}
```

**Ответ:**
```json
{
    "message": "Недостаточно средств",
    "errorCode": "INSUFFICIENT_FUNDS",
    "timestamp": "2025-09-23T15:45:00.000"
}
```

### Ошибка: Перевод самому себе
```http
POST http://localhost:8080/api/v1/transfer
Content-Type: application/json

{
    "fromCardNumber": "1111111111111111",
    "toCardNumber": "1111111111111111",
    "amount": 100.00,
    "currency": "byn"
}
```

**Ответ:**
```json
{
    "message": "Нельзя переводить деньги самому себе",
    "errorCode": "INTERNAL_ERROR",
    "timestamp": "2025-09-23T15:45:00.000"
}
```

## 📊 Коды услуг
- **ELEC001** - Электроэнергия (100 BYN)
- **WATER002** - Водоснабжение (50 BYN)
- **GAS003** - Газ (75 BYN)
- **PHONE004** - Мобильная связь (25 BYN)
- **INTERNET005** - Интернет (60 BYN)

## 🔍 Тестовые данные
- **Карта 1**: `1111111111111111` / PIN: `1234` (Vassily Petrov)
- **Карта 2**: `2222222222222222` / PIN: `4321` (Pjotr Vasechkin)
