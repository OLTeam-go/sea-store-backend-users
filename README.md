# sea-store-backend-users

All about users microservice  

- User API  
https://app.swaggerhub.com/apis-docs/olteam-go/Users/1.0.0
- Auth API  
https://app.swaggerhub.com/apis-docs/olteam-go/Auth/1.0.0
- Wallet API  
https://app.swaggerhub.com/apis-docs/olteam-go/Wallet/1.0.0
- Transaction API  
https://app.swaggerhub.com/apis-docs/olteam-go/Transactions/1.0.0
- Transaction History API  
https://app.swaggerhub.com/apis-docs/olteam-go/TransactionsHistory/1.0.0

## USER API DOCUMENTATION

### GET /users
Obtain information about list of users  
Success response(200): Successfully returned list of users  
Example Value | Model:
```
[
  {
    "ID": "string",
    "username": "string",
    "email": "string",
    "password": "string",
    "name": "string",
    "gender": "string",
    "type": "string",
    "active": true,
    "created_at": "string",
    "updated_at": "string"
  }
]
```

### POST /users
Post a new user into the database  
Success response(200): Successfully posted a user  
body(Example Value | Model):
```
{
  "ID": "string",
  "username": "string",
  "email": "string",
  "password": "string",
  "name": "string",
  "gender": "string",
  "type": "string",
  "active": true,
  "created_at": "string",
  "updated_at": "string"
}
```

### GET /users/{id}
Obtain information about a user from unique ID  
Parameters: id("string")  
Success response(200): Successfully returned a user  
Example Value | Model:
```
[
  {
    "ID": "string",
    "username": "string",
    "email": "string",
    "password": "string",
    "name": "string",
    "gender": "string",
    "type": "string",
    "active": true,
    "created_at": "string",
    "updated_at": "string"
  }
]
```

### PUT /users/{id}
Put a user  
Parameters: id("string")  
Success response(200): Successfully updated a user  
Error response(404): User not found  
body(Example Value | Model):
```
{
  "ID": "string",
  "username": "string",
  "email": "string",
  "password": "string",
  "name": "string",
  "gender": "string",
  "type": "string",
  "active": true
}
```

### PUT /users/{id}/setting
Put a user by username and password  
Parameters: id("string")  
Success response(200): Successfully updated a user  
Error response(404): User not found  
body(Example Value | Model):
```
{
  "login_credentials": {
    "username": "string",
    "password": "string"
  },
  "user_to_update": {
    "username": "string",
    "email": "string",
    "password": "string",
    "name": "string",
    "gender": "string",
    "type": "string",
    "active": true
  }
}
```

### DELETE /user/{id}
Delete a user  
Parameters: id("string", Parameters)  
Success response(200): Successfully deleted a user  
Error response(404): User not found  

## AUTH API DOCUMENTATION

### POST /login
Login a user
credentials, body(Example Value | Model):
```
{
  "username": "string",
  "password": "string"
}
```
Success response(200): Successfully login a user  
Response(Example Value | Model):
```
{
  "ID": "string",
  "username": "string",
  "email": "string",
  "password": "string",
  "name": "string",
  "gender": "string",
  "type": "string",
  "active": true,
  "created_at": "string",
  "updated_at": "string"
}
```
Error response(401): Unauthorized because wrong credentials given  

### POST /register/customer
Register a user as customer  
credentials, body(Example Value | Model):
```
{
  "username": "string",
  "email": "string",
  "password": "string",
  "name": "string",
  "gender": "string"
}
```
Success response(200): Successfully registered a user  

### POST /register/merchant
Register a user as merchant  
credentials, body(Example Value | Model):
```
{
  "username": "string",
  "email": "string",
  "password": "string",
  "name": "string",
  "gender": "string"
}
```
Success response(200): Successfully registered a merchant  

### POST /register/admin
Register a user as customer  
credentials, body(Example Value | Model):
```
{
  "username": "string",
  "email": "string",
  "password": "string",
  "name": "string",
  "gender": "string",
  "token": "string"
}
```
Success response(200): Successfully registered an admin  
Error response(401): Unauthorized because wrong token given  

### GET /register/merchant/pending
Obtain information about list of users  
Parameters: id("string")  
Success response(200): Successfully returned list of merchant  
Example Value | Model:
```
[
  {
    "ID": "string",
    "username": "string",
    "email": "string",
    "password": "string",
    "name": "string",
    "gender": "string",
    "type": "string",
    "active": true,
    "created_at": "string",
    "updated_at": "string"
  }
]
```

### PUT /register/merchant/pending/{userid}/accept
Put a user
Parameters: userid("string")  
Success response(200): Successfully accept a pending merchant  
Error response(404): Merchant not found  

### PUT /register/merchant/pending/{userid}/reject
Put a user
Parameters: userid("string")  
Success response(200): Successfully reject a pending merchant  
Error response(404): Merchant not found  