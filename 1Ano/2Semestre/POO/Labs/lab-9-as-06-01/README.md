# Programação Orientada por Objetos 2022/2023

## Ficha de Laboratório #9

## Objetivos

- Introdução ao uso de exceções, IO e serialização.

## Programa

O objetivo principal da solução passa pelo desenvolvimento de um sistema de suporte a uma loja online de venda de cafés. Nesta fase, o sistema permite o registo de contas e gestão de vendas de diversos tipos de cafés. O objetivo, passa por minimizar as vulnerabilidades a falhas provocadas por erros na introdução de dados, automatizar alguns processos e ter a possibilidade de efetuar cópias de segurança dos dados.

## Regras de implementação

- Aceitar o assignment do GitHub Classroom [**aqui**](https://classroom.github.com/a/MEjA392c)
- Clonar o repositório gerado pelo GitHub Classroom
- Utilizar o IDE BlueJ.
- Implementar o código necessário e testar no fim de cada nível.
- **Atualizar a versão do programa no repositório no mínimo no fim de cada nível** (é aconselhado no final de cada funcionalidade implementada e testada com sucesso). Não é necessário incluir na versão os ficheiros compilados (extensão .class).
- Use as convenções de codificação adotadas para a linguagem Java (ver **Notas**).

## Implementação

### Nível 1:

1. Crie um tipo enumerado `ErrorCode` com os dados da Tabela. Implemente os textos explicativos fornecidos, no método `toString()` deste enumerado:
  
  | Identificador                    | Descrição                                                            |
  |:--------------------------------:|:--------------------------------------------------------------------:|
  | MEMBER_DOES_NOT_EXISTS           | O utilizador não se encontra registado                               |
  | MEMBER_ALREADY_EXISTS            | O nome de login já se encontra em utilização                         |
  | LOGIN_NAME_CANT_BE_NULL          | O nome do utilizador tem de ser fornecido                            |
  | LOGIN_NAME_MUST_BE_BIGGER        | O nome do utilizador tem de ser composto por pelo menos 5 caracteres |
  | MEMBER_NIF_CANT_BE_NULL          | O NIF do utilizador tem de ser fornecido                             |
  | MEMBER_NIF_IS_INCORRECT          | O NIF do utilizador está incorreto                                   |
  | SHOPPING_CART_IS_EMPTY           | O carrinho de compras está vazio                                     |
  | NUMBER_OF_BOXES_MUST_BE_POSITIVE | O numero de caixas têm de ser positivo                               |
  | FILE_CANT_BE_NULL_OR_EMPTY       | O ficheiro para imprimir não pode ser vazio ou nulo                  |

2. Implemente a classe `CoffeeShopIllegalArgumentException`, que herda de `IllegalArgumentException` com um atributo do tipo `ErrorCode` e um método para obter o código de erro da exceção. Crie ainda o construtor que tem um único parâmetro do tipo `ErrorCode`.

### Nível 2

1. Na classe `CoffeeShop` implemente o método auxiliar `searchMember()` que recebe uma `String` com o nome de login na coleção de membros. Caso o encontre devolve o Membro com este nome de utilizador, caso contrário devolve o valor *null*.

2. Implemente o método `createMemberAccount()`, que recebe os dados necessários para a instanciação de um novo  membro (`Member`). Primeiro deve procurar se já existe um novo membro na respetiva coleção e se sim,  lançar a exceção do tipo `CoffeeShopIllegalArgumentException`, específica para o efeito.

3. Implemente o método `getMember()`, que recebe o nome de login. Procura e retorna o membro existente na respetiva coleção. Caso não exista, deve lançar a exceção do tipo `CoffeeShopIllegalArgumentException`, específica para o efeito.

4. Implemente também o método `addToCart()`, que recebe:
  
  - Nome de login - `string` com o nome de utilizador;
  - Número de caixas - Quantidade de caixas a adicionar ao carrinho de compras;
  - Tipo de café - Tipo de café a adicionar ao carrinho de compras;
  
  Este método deverá adicionar ao carrinho daquele membro a quantidade de caixas de café, do tipo selecionado.

5. Implemente ainda o método `finishPurchase()`, que recebe o nome de login e retorna uma compra `Purchase`. Essa compra deve ser composta por todos os items existentes no carrinho de compras daquele membro. Essa compra deve ser também adicionada à lista de compras da loja. Caso o carrinho de compras do membro em questão se encontre vazio, deve lançar a exceção do tipo `CoffeeShopIllegalArgumentException`, específica para o efeito.

### Nível 3

1. Redefina agora algumas classes fornecidas, de forma a conter validações, de forma a que seja lançada uma `CoffeeShopIllegalArgumentException` com o código de erro apropriado:
  
  - Na classe `Member`:
    
    - Crie o método auxiliar  `String validateName(String name)`, que deve ser chamado no construtor da classe e verifica se  o nome não é nulo ou vazio, e se, o nome de utilizador é composto por pelo menos 5 caracteres.
    - Crie o método auxiliar  `String validateNif(String nif)`, que deve ser chamado no construtor da classe e verifica se  o NIF não é nulo ou vazio, e se é composto por 9 caracteres.
    - Na classe `Purchase`:
      - No construtor verifique se o carrinho de compras do membro recebido não se encontra vazio.
  
  - Na classe `ShoppingCart`:
    
    - No método `addCoffeToCard()` existente na classe, adicione uma verificação que verifica se está a ser adicionada pelo menos uma caixa ao carrinho de compras.  

2. Para testar as classes, no método `main` da classe `AppStart` uma loja e adicione membros, por forma a testar, o lançamento do máximo de exceções implementadas no código. Utilize também um destes membros para adicionar caixas de café ao seu carrinho de compras e finalizar uma compra, de forma a testar todo o código desenvolvido. Capture cada cada uma das exceções.

### Nível 4

1. Crie o método `getBalance()`, na classe `CoffeeShop`, que retorna a informação relativa ao momento em que a informação é gerada e, o valor do total de vendas realizadas até ao momento. Sempre que possível, opte pela programação funcional. 

2. Crie a classe abstrata `CoffeeShopFileHandler`.

3. Implemente um método estático `printToFile` que recebe como parâmetros:
  
  - `fileName` - String com o nome do ficheiro;
  - `textToSave` - String com o texto a guardar no ficheiro de texto;
  
  O conteúdo (textToSave) não pode ser nulo nem vazio. Se for, lança a exceção do tipo `CoffeeShopIllegalArgumentException`, específica para o efeito.

4. No método **main** teste o método para gravar em ficheiro o balanço da loja. O ficheiro deve ser armazenado com o  nome "*balançoDeCompras.txt*".
  
  Teste, ainda, a gravação da informação da última compra (o nome do ficheiro é constituído pelo prefixo "*Fatura_*" e número da compra, por exemplo "*Fatura_1.txt*").

### Nível 5

1. Implemente na classe abstrata `CoffeeShopFileHandler` um método estático `saveShop()` que recebe como parâmetros uma `String` com o nome do ficheiro e a instância de `CoffeeShop` a guardar num ficheiro usando a serialização de dados.
  
  Nota: implemente a interface **Serializable** em todas as classes que considerar necessário.

2. Implemente também um método estático `loadShop` que recebe o nome do ficheiro de onde deve ler uma instância de `CoffeeShop`.

3. No método `main` guarde uma loja com pelo menos uma compra efetuada, e respetivos membros, no ficheiro "**shop.backup**", leia do ficheiro e imprima na consola o balanço da loja que leu do ficheiro, de forma a obter a informação armazenada: 

**Notas**:

Para os identificadores siga as convenções adotadas normalmente, em particular:

1. A notação **camelCase** para o nome das variáveis locais e identificadores de atributos e métodos.
2. A notação **PascalCase** para os nomes das classes.
3. Não utilize o símbolo ‘_’, nem abreviaturas nos identificadores.