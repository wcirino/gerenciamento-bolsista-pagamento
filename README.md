# Projeto de Gerenciamento de Bolsistas e Pagamentos

Este projeto é um sistema de gerenciamento de bolsistas e seus pagamentos.

## Comandos Maven

Para compilar o projeto, execute o seguinte comando:

- mvn clean install

- mvn test


## Endpoints da API

- **GET /api/bolsistas/findAll**: Retorna todos os bolsistas cadastrados.
- **POST /api/bolsistas/alterar-bolsista**: Altera os dados de um bolsista.
- **PUT /api/bolsistas/insert-bolsista**: Insere um novo bolsista.
- **PUT /api/bolsistas/desativar-bolsista/{id}**: Desativa um bolsista pelo ID.

- **GET /api/pagamentos/findAll**: Retorna todos os pagamentos cadastrados.
- **POST /api/pagamentos/insert-pagamento**: Insere um novo pagamento.
- **PUT /api/pagamentos/alterar-pagamento**: Altera os dados de um pagamento.
- **DELETE /api/pagamentos/remover-pagamento/{id}**: Remove um pagamento pelo ID.
- **GET /api/pagamentos/find?idPagamento={idPagamento}&idBolsista={idBolsista}**: Retorna um pagamento pelo ID.

## Exemplo de JSON para Bolsista / Pagamento

```json
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

IMG test
![image](https://github.com/wcirino/gerenciamento-bolsista-pagamento/assets/73764307/f1da5e32-9d76-4836-902d-2748d2a16879)




