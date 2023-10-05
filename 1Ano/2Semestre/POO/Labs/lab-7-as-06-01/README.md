# Programação  Orientada por Objetos 2022/2023

## Ficha de Laboratório <mark>#7</mark>

## Objetivos

> - Introdução à utilização de classes abstratas e métodos abstratos
> - Introdução à utilização de interfaces

## Programa

Pretende-se desenvolver um protótipo para facilitar a oferta de diversos _tours_. São disponibilizados: Tours com guia `GuidedTour` e Tours áudio `AudioTour`, que especializam `Tour`.

Também vai ser necessário implementar uma classe representativa de concertos a realizar no Largo José Afonso `JoseAfonsoSquareConcert`.
O programa deverá implementar duas interfaces: Listável `Listable` e Pesquisável `Searchable`.
A interface `Listable` será aplicada aos tours e aos concertos, que apesar de pertenceram a hierarquias diferentes, partilham o interesse de divulgação.
A interface `Searchable` será aplicada às classes que agrupam tours e/ou concertos.

Deverá ser possível:

- Obter diversas informações relativas aos tours e também aos concertos;
- Pesquisar os tours e os concertos por diversas informações.

## Regras de implementação

- Aceitar o assignment do GitHub Classroom [**aqui**](https://classroom.github.com/a/lRVBMX52)
- Clonar o repositório gerado pelo GitHub Classroom
- Utilizar o IDE BlueJ.
- Implementar o código necessário e testar no fim de cada nível.
- **Atualizar a versão do programa no repositório no mínimo no fim de cada nível** (é aconselhado no final de cada funcionalidade implementada e testada com sucesso). Não é necessário incluir na versão os ficheiros compilados (extensão .class).
- Use as convenções de codificação adotadas para a linguagem Java (ver **Notas**).

## Implementação

### Nível 1:

1. Crie uma interface `Listable`, com as seguintes constantes:
   
   - `STANDARD_HOW_LONG` - tem como valor o número inteiro 60 e representa a duração standard em minutos.
   - `STANDARD_HOW_MUCH` - tem como valor o número decimal 20.0 e representa o preço standard em euros.

2. Defina na interface `Listable`, os seguintes métodos:
   
   - `getWhat()`, que retorna uma String com informação do que é.
   - `getWhere()`, que retorna uma String com o local em que decorre.
   - `getWhen()`, que retorna uma String com informação de quando ocorre.
   - `getHowLong()`, que retorna um número inteiro com a duração em minutos.
   - `getHowMuch()`, que retorna um número decimal com o preço em euros.
   - `isFree()`, que retorna um boolean (true indica que é gratuito).

   
   

### Nível 2

1. Defina uma classe abstrata `Tour`, com o seguinte atributo:

   - `ID` - uma constante pública de tipo String.

2. Implemente um construtor que recebe por parâmetro o valor de `ID`.

3. Declare ainda na classe `Tour` o seguinte método abstrato:

   - `getTag()`, que retorna uma String que representa a caraterística principal do `Tour`.

4. Implemente na classe `Tour` os seguintes métodos da interface `Listable`:

   - `getHowLong()`, que retorna a constante `STANDARD_HOW_LONG`.
   - `getHowMuch()`, que retorna a constante `STANDARD_HOW_MUCH`.
   - `isFree()`, que retorna o valor `false`.

### Nível 3

- Aplicando a herança derive da classe `Tour` classes para `GuidedTour` e `AudioTour`.

- A subclasse `GuidedTour` recebe por parâmetros do respetivo construtor valores a guardar nos seguintes atributos:
  - `tag` (`String`) - a caraterística principal do `GuidedTour`.
  - `what` (`String`) - uma descrição do `GuidedTour`.
  - `where` (`String`) - o local em que decorre o `GuidedTour`.
  - `schedule` (`LocalDateTime`) - a data e hora do `GuidedTour`.

**Nota:** o ID, a fornecer à superclasse, é constituído pela string "Guided Tour " concatenada com o número de ordem de instanciação de `GuidedTour`, número gerado automaticamente com valor inicial 100. A primeira instância de `GuidedTour` fica com o ID: **Guided Tour 100**.

- Após a implementação do construtor acrescente os métodos seletores e modificadores que julgue serem necessários.

- Implemente todos os métodos abstratos herdados da classe `Tour`.

- Crie o método `toString()` na classe `GuidedTour` que retorne toda a informação.

- A subclasse `AudioTour` recebe por parâmetros do respetivo construtor valores a guardar nos seguintes atributos:
  - `tag` (`String`) - a caraterística principal do `AudioTour`.
  - `what` (`String`) - uma descrição do `AudioTour`.

**Nota:** o ID, a fornecer à superclasse, é constituído pela string "Audio Tour " concatenada com o número de ordem de instanciação de `AudioTour`, número gerado automaticamente com valor inicial 100. A primeira instância de `AudioTour` fica com o ID: **Audio Tour 100**.

- Após a implementação do construtor acrescente os métodos seletores e modificadores que julgue serem necessários.

- Implemente todos os métodos abstratos herdados.
  **Nota:** Todos os `AudioTour` são localizados em Setúbal e podem ser realizados em qualquer dia entre as 8:00 e as 20:00. Adicione duas constantes de classe `WHERE` e `WHEN` para guardar os respetivos valores.

- Crie o método `toString()` na classe `AudioTour` que retorne toda a informação.

Neste ponto devem ser unidos os branches do desenvolvimento. Após esta fase estarão disponíveis no projeto principal os dois desenvolvimentos.

### Nível 4

1. Implemente a classe `Listings` que herda de `ArrayList` de tipo `Listable`.
2. Implemente nesta classe o método `getNumberOfListings()` que retorna o número de objetos na coleção.
3. Crie o método `toString()` na classe `Listings` que retorna a informação de todos os listings existentes na coleção, separados por uma linha em branco. 
4. Implemente também o método `show()` que apresenta no ecran a string retornada pelo método `toString()` da propria classe.
5. No método main, instancie `Listings`, adicione dois objetos `GuidedTour` e dois `AudioTour`. Teste todos os métodos.
6. Crie uma interface `Searchable`, com os seguintes métodos:

- `search(String searchText)`, que retorna uma lista de `Listable`, com todos os objetos que contêm a string a pesquisar na informação disponibilizada.
- `searchWhere(String where)`, que retorna uma lista de `Listable`, com todos os objetos que decorrem no local pesquisado.
- `searchFree()`, que retorna uma lista de `Listable`, com todos os objetos que satisfazem a condição de serem gratuitos.

7. Implemente a interface `Searchable` na classe `Listings`.

8. Teste todos os métodos.

### Nível 5

1. Implemente a classe `JoseAfonsoSquareConcert`, com os seguintes atributos:
   
   - `who` (`String`) - indica quem vai atuar no concerto.
   - `schedule` (`LocalDateTime`) - a data e hora do concerto.

2. Acrescente à classe criada o respectivo construtor que vai receber por parametros todos os atributos.

3. Acrescente os métodos seletores e modificadores que julgue serem necessários.

4. Implemente a interface `Listable` na classe `JoseAfonsoSquareConcert`.
   
   **Nota:** Todos os concertos `JoseAfonsoSquareConcert` decorrem em Setúbal, têm a duração standard e são gratuitos. A descrição do que é (what) pode ser sempre, por exemplo,  **Music at José Afonso Square**.

5. Crie o método `toString()` na classe `JoseAfonsoSquareConcert` que retorna toda a informação.

6. No método main, adicione dois objetos `JoseAfonsoSquareConcert` à instância anterior de `Listings` (já com objetos `GuidedTour` e `AudioTour`). Teste todos os métodos da classe `JoseAfonsoSquareConcert` e teste novamente os métodos da classe `Listings`.

**Notas**:

Para os identificadores siga as convenções adotadas normalmente, em particular:

1. A notação **camelCase** para o nome das variáveis locais e identificadores de atributos e métodos.
2. A notação **PascalCase** para os nomes das classes.
3. Não utilize o símbolo ‘_’, nem abreviaturas nos identificadores.