# Projeto de gerencimento de bolsista e seus pagamentos.

- mvn clean install

- mvn test

http://localhost:8080/api/bolsistas/findAll - GET

http://localhost:8080/api/bolsistas/alterar-bolsista - POST

http://localhost:8080/api/bolsistas/insert-bolsista - PUT

http://localhost:8080/api/bolsistas/desativar-bolsista/2 - PUT

http://localhost:8080/api/pagamentos/findAll - GET

http://localhost:8080/api/pagamentos/insert-pagamento - POST

http://localhost:8080/api/pagamentos/alterar-pagamento - PUT

http://localhost:8080/api/pagamentos/remover-pagamento/1 - DELETE

http://localhost:8080/api/pagamentos/find?idPagamento=5&idBolsista=3 - GET

JSON - BOLSISTA

{
    "id": 4,
    "nomeCompleto": "Willyan Fernando 2",
    "tipoIdentificador": 1,
    "numeroIdentificador": "12345678",
    "dataCadastro": "2024-04-15",
    "codigoBanco": "005",
    "numeroAgencia": "1415",
    "numeroConta": "24680-1",
    "status": null
}

JSON - PAGAMENTO

{
    "id": 7,
    "bolsistaId": 1,
    "dataPagamento": "2024-04-15",
    "banco": "Banco ADB",
    "agencia": "4321",
    "conta": "8765",
    "valor": 1500.00,
    "status": "SOLICITADO"
}
