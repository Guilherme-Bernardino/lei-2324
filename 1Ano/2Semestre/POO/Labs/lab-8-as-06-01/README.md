# Programação Orientada por Objetos 2022/2023

## Ficha de Laboratório <mark>#8</mark>

## Objetivos

- Utilização de coleções e genéricos
- Utilização de testes unitários

## Programa

Pretende-se desenvolver um protótipo para facilitar a organização, ordenação e listagem de tarefas. Vamos criar as classes `Task` e `ListOfElements` e também a interface `Orderable`.
Pretende-se que `ListOfElements` tenha uma implementação genérica, de modo a que possa ser utilizada para gerir outros objetos que implementem `Orderable`.

Por exemplo, se criar uma classe `Bill` que implemente `Orderable`, pode utilizar a classe genérica `ListOfElements` para gerir os vários pagamentos de contas.

## Regras de implementação

- Aceitar o assignment do GitHub Classroom [**aqui**](https://classroom.github.com/a/VkqN-qbc)
- Clonar o repositório gerado pelo GitHub Classroom
- Utilizar o IDE BlueJ.
- Implementar o código necessário e testar no fim de cada nível.
- **Atualizar a versão do programa no repositório no mínimo no fim de cada nível** (é aconselhado no final de cada funcionalidade implementada e testada com sucesso). Não é necessário incluir na versão os ficheiros compilados (extensão .class).
- Use as convenções de codificação adotadas para a linguagem Java (ver **Notas**).

## Implementação

### Nível 1:

1. Crie a interface `Orderable` e copie para esta interface a definição dos seguintes métodos:

```java
    int getPriority();
    LocalDate getDate();
```

2. Crie a classe `Task` que implementa a interface `Orderable`. Adicione à classe `Task` os seguintes atributos:
   
   - `description` - `String` para a descrição da tarefa.
   - `priority` - Número inteiro para a prioridade.
   - `date` - `LocalDate` para a data em que a tarefa tem de estar realizada.

3. Crie um construtor que recebe, por parâmetros, valores para todos os atributos.

4. Implemente os métodos modificadores e selectores que achar necessários.

5. Crie a classe de teste `TaskTest`, para a classe `Task`.

6. Crie o método de teste `testConstructor` que testa o construtor. Este método de teste deve criar um objeto tarefa como uma Fixture e verificar que o objeto é inicializado corretamente.

### Nível 2

1. Implemente, na classe `Task`, um método de classe `getHeader()` que retorna uma `String` com o seguinte conteúdo:

```java
  "\n |--------------------------------------------------------|"
+ "\n | Priority | Task name                      | Due date   |"
+ "\n |----------|--------------------------------|------------|"
```

2. Implemente o método `toString`. Utilize o método `format` da classe `String` para alinhar os valores dos atributos com o header fornecido.

3. Na classe `Task` implemente os métodos `equals` e `hashCode`. Duas tarefas são consideradas iguais se tiverem a mesma data e a mesma descrição.

4. Crie um método de teste `testEquals`. Este método deve criar um objeto da classe `Task`, com a mesma data e descrição da tarefa já instanciada na Fixture, para validar se o retorno do método equals está correto.

### Nível 3

Pretende-se efetuar a construção da classe principal da solução. O principal requisito da classe passa por aceitar apenas elementos que herdem da interface `Orderable`.

1. Implemente a classe `ListOfElements` que recebe um elemento génerico que implemente a interface `Orderable` e que extende a classe `ArrayList` do elemento génerico.

2. Adicione à classe a seguinte constante pública:
   
   - `HEADER` - `String` que representa o cabeçalho a incluir antes de listar os elementos da lista.

3. Adicione um construtor à classe que recebe, por parâmetro, o valor da constante `HEADER`.

4. Crie uma nova classe de teste `ListOfElementsTest` e defina uma Fixture com um objeto `ListOfElements` e as seguintes 4 tarefas:

```java
Task t1 = new Task("Study for POO MT3", 3, LocalDate.of(2023,6,20));
Task t2 = new Task("Do POO project phase 2", 2, LocalDate.of(2023,6,24));
Task t3 = new Task("Do POO project phase 1", 1, LocalDate.of(2023,5,29));
Task t4 = new Task("Study for POO test", 2, LocalDate.of(2023, 6, 4));
```

5. Adicione o método de teste `testAdd` à classe de teste. Este método deve adicionar as 4 tarefas da Fixture à lista e verificar que foram adicionadas.

### Nível 4

Pretende-se efetuar a ordenação da coleção pela prioridade.

1. Implemente na classe `Task` a interface `Comparable<Task>`.

2. Na classe de teste `TaskTest`, crie um método de teste `testCompareTo`. Este método deve verificar que a comparação está corretamente implementada.

3. Implemente na classe `ListOfElements` o método `sort` para ordenar de forma crescente por prioridade todos os elementos da coleção. DICA: utilize `Collections.sort`.

4. Implemente o método `toString` que retorna o cabeçalho seguido dos elementos na coleção, um elemento por linha.

5. Implemente o método `showSortedByNaturalOrder` que ordena a coleção, apresenta o cabeçalho e apresenta todos os elementos na coleção, um elemento por linha:

```java
 |--------------------------------------------------------|
 | Priority | Task name                      | Due date   |
 |----------|--------------------------------|------------|
 |    01    | Do POO project phase 1         | 2023-05-29 |
 |    02    | Do POO project phase 2         | 2023-06-24 |
 |    02    | Study for POO test             | 2023-06-04 |
 |    03    | Study for POO MT3              | 2023-06-20 |
```

### Nível 5

Pretende-se agora efetuar a ordenação da coleção pela prioridade e em caso de prioridades iguais, pela data.

1. Implemente a classe `OrderableComparator` que implementa a interface `Comparator<Orderable>`.

2. Implemente o método `compare` para ordenar de forma crescente por prioridade e em caso de prioridades iguais, por data.

3. Implemente na classe `ListOfElements` o método `sortByOrderableComparator` que utiliza o `OrderableComparator` para fazer a ordenação da coleção. DICA: utilize `Collections.sort`.

4. Implemente o método `showSortedByOrderableComparator` que ordena a coleção, apresenta o cabeçalho e apresenta todos os elementos na coleção, um elemento por linha:

```java
 |--------------------------------------------------------|
 | Priority | Task name                      | Due date   |
 |----------|--------------------------------|------------|
 |    01    | Do POO project phase 1         | 2023-05-29 |
 |    02    | Study for POO test             | 2023-06-04 |
 |    02    | Do POO project phase 2         | 2023-06-24 |
 |    03    | Study for POO MT3              | 2023-06-20 |
```

**Notas**:

Para os identificadores siga as convenções adotadas normalmente, em particular:

1. A notação **camelCase** para o nome das variáveis locais e identificadores de atributos e métodos.
2. A notação **PascalCase** para os nomes das classes.
3. Não utilize o símbolo ‘_’, nem abreviaturas nos identificadores.