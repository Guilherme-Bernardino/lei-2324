# Programação Orientada por Objetos 2022/2023

## Ficha de Laboratório #2

### Objetivo

> - Introdução à utilização de testes unitários.

## Programa

Protótipo de uma aplicação para facilitar a gestão de uma imobiliária. A aplicação deve permitir registar: os clientes, os vendedores, os imóveis comercializados e as vendas realizadas pela imobiliária (os vários registos não podem aceitar duplicados).

## Regras de implementação

- Aceitar o assignent do GitHub Classroom [**aqui**](https://classroom.github.com/a/Fc7185QA)
- Clonar o repositório gerado pelo GitHub Classroom
- Utilizar o IDE BlueJ.
- Implementar o código necessário e testar no fim de cada nível.
- **Atualizar a versão do programa no repositório no mínimo no fim de cada nível** (é aconselhado no final de cada funcionalidade implementada e testada com sucesso). Não é necessário incluir na versão os ficheiros compilados (extensão .class).
- Use as convenções de codificação adotadas para a linguagem Java (ver **Notas**).

## Implementação

### Nível 1:

As classes fornecidas correspondem à estrutura das classes da aplicação, com as assinaturas dos métodos públicos, sem a implementação do código interno. Vamos preparar o programa para que possa compilar e vamos criar alguns testes básicos que serão executados repetidamente ao longo da implementação do código interno da aplicação.

1. Inclua todas as classes fornecidas com este enunciado `User`, `Property`, `Sell` e `Company` num projeto BlueJ deste laboratório, no repositório local criado com o GitHub Desktop.
2. Altere os métodos de forma a retornar valores fixos de modo a possibilitar a compilação das classes. Por exemplo, os métodos que retornam referências de objetos retornam o valor `null`, os métodos que retornam inteiros retornam o valor -1, etc.

A classe `Property` representa um imóvel. Crie a classe de teste `PropertyTest`, para a classe `Property`.

1. Crie o método de teste `testConstructor` que testa o construtor. Este método de teste deve criar o objeto property1 (ver dados na **Tabela 1**) como uma Fixture e verificar que o objeto é criado com os valores fornecidos, recorrendo aos seletores do preço e do modelo para isso.
2. Implemente o código interno do construtor e também de todos os seletores da classe (necessários para verificar que o construtor inicializou o objeto com os valores corretos).
3. Execute o teste criado (quando completar a implementação do código interno de todos os métodos utilizados no teste, a execução do teste deve ter sucesso).
   1. Quando o teste passar com sucesso, crie uma nova versão da aplicação

| Identificador | Modelo         | Preço   |
|:-------------:|:--------------:|:-------:|
| **property1**  | T3 Monte Belo | 150000.0 |

<center><strong>Tabela 1</strong> - Imóvel a criar para o teste do construtor</center>

### Nível 2:

O método `toString` da classe `Property` deve retornar uma string no formato apresentado na listagem 1. 

1. Crie o método de teste `testToString`.
2. Implemente o código interno do método `toString`.
3. Execute todos o teste criado.

```shell
Propriedade:
Modelo        : T3 Monte Belo
Preço         : 150000.0 Euros
```

<center><strong>Listagem 1</strong> - Exemplo de String a devolver pelo método toString para o imóvel da tabela 1</center>

### Nível 3:

A classe `Company` representa uma imobiliária. 

| Identificador | Nome             | Telefone  | E-mail                    |
|:-------------:|:----------------:|:---------:|:-------------------------:|
| **client1**   | José Manuel          | 911111111 | zemanel@yahoo.com     |
| **client2**   | António Francisco    | 922222222 | tochico@hotmail.com   |
| **seller1**   | Fernando Fernandes   | 966777101 | fefe@remax.pt         |
| **seller2**   | Rodrigo Rodrigues    | 966777152 | roro@remax.pt         |
<center><strong>Tabela 2</strong> - Objetos da fixtures para a classe CompanyTest</center><br/>

1. Crie a classe de teste `CompanyTest`, para a classe `Company`.
2. Defina na classe `CompanyTest` uma Fixture com um objeto `Company` e todos os objetos da tabela 2. Utilize o método existente `setUp()` para este efeito.
3. Crie, nesta classe, o método de teste `testConstructor` que testa o construtor. Este método de teste cria verifica que o construtor instancia as coleções da imobiliária configurada no método `setUp()`, ou seja, que após a criação os atributos não são null.
4. Implemente o código interno do construtor e dos seletores da classe.
5. Execute todos os testes criados.

### Nível 4:

1. Crie o teste unitário `testRegisterClient` que regista um cliente e o método de teste `testRegisterClients` que regista dois clientes.

2. Crie o teste unitário `testRegisterClientDuplicate`, que valida que não é possível registar o mesmo cliente duas vezes.

3. Crie o teste unitário `testRegisterClientNull`, que tenta registar um cliente null.

4. Em seguida, implemente o código interno do método `registerClient`.

5. Execute todos os testes criados.

6. Aplique a mesma abordagem do que nas alíneas 1 a 5 para testar o registo de um vendedor e o registo de um imóvel.

### Nível 5:

1. Defina uma Fixture que cria o objeto sell1 (ver dados na **Tabela 3**).

| Identificador | Cliente | Vendedor | Imóvel  |
|:-------------:|:-------:|:--------:|:--------:|
| **sell1**     | client1 | seller1  | property1 |
| **sell2**     | client2 | seller2  | property2 |

<center><strong>Tabela 3</strong> - Vendas a criar</center>

2. Crie na classe de teste **PropertyTest** o método de teste `testCreateSell`.

3. Em seguida, implemente o código interno do método `createSell`.

4. Execute todos os testes criados.

5. Crie o método de teste `testCalculateSellsOfTheYear`. Em seguida, implemente o código interno do método `calculateSellsOfTheYear`.

6. Execute todos os testes criados.

7. Crie o método de teste `testFindSellerOfTheYear`. Em seguida, implemente o código interno do método `findSellerOfTheYear`.

8. Execute todos os testes criados.

## **Notas:**

Para os identificadores siga as convenções adotadas normalmente, em particular:

1. A notação **camelCase** para o nome das variáveis locais e identificadores de atributos e métodos.

2. A notação **PascalCase** para os nomes das classes.

3. Não utilize o símbolo ‘_’, nem abreviaturas nos identificadores.
