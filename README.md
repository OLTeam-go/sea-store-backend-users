# sea-store-backend-users

All about users microservice  

- Users API  
https://app.swaggerhub.com/apis-docs/olteam-go/Users/1.0.0
- Auth API  
https://app.swaggerhub.com/apis-docs/olteam-go/Auth/1.0.0
- Wallets API  
https://app.swaggerhub.com/apis-docs/olteam-go/Wallets/1.0.0
- Transactions API  
https://app.swaggerhub.com/apis-docs/olteam-go/Transactions/1.0.0
- Transactions Histories API  
https://app.swaggerhub.com/apis-docs/olteam-go/TransactionsHistories/1.0.0

## USER API DOCUMENTATION
Base URL: sea-store-backend-users.herokuapp.com/api/v1/users  

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
Base URL: sea-store-backend-users.herokuapp.com/api/v1/auth

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

## WALLET API DOCUMENTATION
Base URL: sea-store-backend-users.herokuapp.com/api/v1/wallets

### GET /wallets
Obtain information about list of wallets  
Success response(200): Successfully returned list of wallets  
Example Value | Model:
```
[
  {
    "id": "string",
    "user_id": "string",
    "balance": "string",
    "created_at": "string",
    "updated_at": "string"
  }
]
```

### POST /wallets
Post a new wallet
credentials, body(Example Value | Model):
```
{
  "id": "string",
  "user_id": "string",
  "balance": "string",
  "created_at": "string",
  "updated_at": "string"
}
```
Success response(200): Successfully posted a wallet  

### GET /wallets/{id}
Obtain information about a wallet from unique ID  
Parameters: id("string")  
Success response(200): Successfully returned a wallet  
Example Value | Model:
```
[
  {
    "id": "string",
    "user_id": "string",
    "balance": "string",
    "created_at": "string",
    "updated_at": "string"
  }
]
```
Error response(404): Wallet not found  

### PUT /wallets/{id}
Put a wallet  
Parameters: id("string")  
user, body(Example Value | Model):
```
{
  "id": "string",
  "user_id": "string",
  "balance": "string",
  "created_at": "string",
  "updated_at": "string"
}
```
Success response(200): Successfully updated a wallet  
Error response(404): Wallet not found  

### DELETE /wallets/{id}
Delete a wallet  
Parameters: id("string", Parameters)  
Success response(200): Successfully deleted a wallet  
Error response(404): Wallet not found  

## TRANSACTIONS API DOCUMENTATION
Base URL: sea-store-backend-users.herokuapp.com/api/v1/transactions

### PUT /{id}/credit
Credit amount of balance to a wallet by wallet ID  
Parameters: id("string")  
wallet, body(Example Value | Model):
```
{
  "amount": 0
}
```
Success response(200): Successfully credit amount of balance to a wallet  
Error response(404): Wallet not found    

### PUT /{id}/debit
Debit amount of balance to a wallet by wallet ID  
Parameters: id("string")  
wallet, body(Example Value | Model):
```
{
  "amount": 0
}
```
Success response(200): Successfully debit amount of balance to a wallet  
Error response(403): Wallet amount exceed the balance to transact  
Error response(404): Wallet not found  

### PUT /users/{user_id}/credit
Credit amount of balance to a wallet by user ID  
Parameters: user_id("string")  
walet, body(Example Value | Model):
```
{
  "amount": 0
}
```
Success response(200): Successfully credit amount of balance to a wallet  
Error response(404): Wallet not found  

### PUT /users/{user_id}/debit
Debit amount of balance to a wallet by user ID  
Parameters: user_id("string")  
walet, body(Example Value | Model):
```
{
  "amount": 0
}
```
Success response(200): Successfully debit amount of balance to a wallet  
Error response(403): Wallet amount exceed the balance to transact  
Error response(404): Wallet not found  

### PUT /usernames/{username}/credit
Credit amount of balance to a wallet by user username  
Parameters: username("string")  
wallet, body(Example Value | Model):
```
{
  "amount": 0
}
```
Success response(200): Successfully credit amount of balance to a wallet  
Error response(404): Wallet not found  

### PUT /users/{user_id}/debit
Debit amount of balance to a wallet by user username  
Parameters: username("string")  
wallet, body(Example Value | Model):
```
{
  "amount": 0
}
```
Success response(200): Successfully debit amount of balance to a wallet  
Error response(403): Wallet amount exceed the balance to transact  
Error response(404): Wallet not found  

## TRANSACTIONS HISTORIES API DOCUMENTATION
Base URL: sea-store-backend-users.herokuapp.com/api/v1/transactions/histories

### GET /histories
Obtain information about list of transactions history  
Success response(200): Successfully returned list of transactions history  
Example Value | Model:
```
[
  {
    "id": "string",
    "user_id": "string",
    "wallet_id": "string",
    "amount": "string",
    "created_at": "string",
    "updated_at": "string"
  }
]
```

### POST /histories
Post a new transaction history  
wallet, body(Example Value | Model):
```
{
  "id": "string",
  "user_id": "string",
  "wallet_id": "string",
  "amount": "string",
  "created_at": "string",
  "updated_at": "string"
}
```
Success response(200): Successfully posted a transaction history  

### GET /histories/{id}
Obtain information about a transaction history from unique ID  
Parameters: id("string")  
Success response(200): Successfully returned a transaction history  
Example Value | Model:
```
{
  "id": "string",
  "user_id": "string",
  "wallet_id": "string",
  "amount": "string",
  "created_at": "string",
  "updated_at": "string"
}
```
Error response(404): Transaction history not found  

### PUT /histories/{id}
Put a transaction history  
Parameters: id("string")  
user, body(Example Value | Model):
```
{
  "id": "string",
  "user_id": "string",
  "wallet_id": "string",
  "amount": "string",
  "created_at": "string",
  "updated_at": "string"
}
```
Success response(200): Successfully updated a transaction history  
Error response(404): Transaction history not found  

### DELETE /wallets/{id}
Delete a transaction history  
Parameters: id("string", Parameters)  
Success response(200): Successfully deleted a transaction history  
Error response(404): Transaction history not found  