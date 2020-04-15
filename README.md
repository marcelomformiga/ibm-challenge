# IBM ATM Challenge

Projeto Java 8 criado com Maven. Foi desenvolvido utilizando os frameworks Spring e a arquitetura REST.

Banco de dados utilizado é o MySQL. Para criação do mesmo, foi cofigurado o Liquibase e uma imagem Docker. Para persistência e modelagem, optamos pelo Spring JPA e o Hibernate.

Aplicação disponibilizada na <b>URL: localhost:8080 </b> com auxílio do Spring Boot.

### Operações

## Saque

Foi criada rotina para entregar o menor número de cédulas possível. Pode-se sacar notas de 5 a 100 reais, e para isso, verifica-se se o valor solicitado é divisível por 5.
Também verifica-se se o Cliente possui saldo suficiente. Caso OK, se atualiza o saldo da conta.

## Depósito

Além de atualizar o saldo da Conta, se grava na base de dados também a operação de depósito.

## Transferência

Valida as contas de origem e destino. Caso estejam OK, atualiza o saldo de ambas.