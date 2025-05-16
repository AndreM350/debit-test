### Projeto de Cancelamento de Débitos
1. Gerar token para fazer requests:

curl --location 'https://7qoqarhfp4.execute-api.us-east-1.amazonaws.com/auth/token' \
--header 'Content-Type: application/json' \
--data '{
  "username": "user-teste",
  "password": "teste123456"
}
'

2. Solicitar Cancelamento de Débito:

curl --location 'https://7qoqarhfp4.execute-api.us-east-1.amazonaws.com/debit-cancel' \
--header 'Content-Type: application/json' \
--header 'Authorization: Bearer seuToken' \
--data '{    
    "id":"55991548",
    "reason":"sample-reason"
}'
