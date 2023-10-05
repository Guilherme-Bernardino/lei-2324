# Programação Orientada por Objetos 2022/2023

## Ficha de Laboratório <mark>#6</mark>

### Objetivo

> - Polimorfismo
> - Princípio da Substituição

## Programa

Pretende-se implementar um Media Player com simulação de funções de Live Radio e de leitura de Stream.

## Regras de implementação

- Aceitar o assignment do GitHub Classroom [**aqui**](https://classroom.github.com/a/tUUrv__P)
- Clonar o repositório gerado pelo GitHub Classroom
- Utilizar o IDE BlueJ.
- Implementar o código necessário e testar no fim de cada nível.
- **Atualizar a versão do programa no repositório no mínimo no fim de cada nível** (é aconselhado no final de cada funcionalidade implementada e testada com sucesso). Não é necessário incluir na versão os ficheiros compilados (extensão .class).
- Use as convenções de codificação adotadas para a linguagem Java (ver **Notas**).

## Implementação

### Nível 1:

1. Implemente a classe `MediaPlayer` com os seguintes atributos: 
  - `volume` ( `int` ) - Valor que gere o volume do media player. Os valores aceites são entre 0 e 30.
  - `muted` ( `boolean` ) - booleano que indica se o volume está mute (igual a 0);

2. Crie um construtor que não recebe parâmetros. Deverá inicializar os atributos com os seguintes valores:
   - `volume` - 5
   - `muted` - false

  - Adicione ainda os habituais métodos seletores e modificadores que achar necessários.

3. Crie o método `increaseVolume()` para incrementar o volume em 1 valor.
   
4. Crie o método `decreaseVolume()` para decrementar o volume em 1 valor.

5. Crie o método `whatsPlaying()`, que deverá devolver uma string "Nothing to play."

6. Implemente o método `toString()` de modo a devolver a informação no seguinte formato:

```console
Volume: 5
Nothing to play.
```

7. Na classe `AppStart` adicione um objeto do tipo `MediaPlayer` para testar a classe.


### Nível 2:

1. Verifique que já tem no projeto a classe `Podcast` com os seguintes atributos:
   - `title` (`String`) com o título do podcast

2. Implemente a classe `StreamPlayer`, que deve estender a classe `MediaPlayer`, com o seguinte atributo:
   - `podcast` (`Podcast`) - Objeto do tipo `Podcast`

3. Crie um construtor sem argumentos, onde deve iniciar o construtor da classe `MediaPlayer` e o atributo `podcast`. Implemente os métodos seletores e modificadores necessários.

4. Re-escreva o método `whatsPlaying()` para retornar a seguinte string:
```console
Stream Player: O Homem que Mordeu o Cão
``` 

### Nível 3:

1. Verifique que já tem no projeto a classe `Station` com os seguintes atributos:
    - `frequency` (`double`) - valor com a frequência da estação
    - `name` (`string`) - valor com o nome da estação

2. Implemente a classe `LiveRadioPlayer`, que deve estender a classe `MediaPlayer`, com os seguintes atributos:
   - `stationList` - Lista de estações
   - `currentStation` - Informação relativa à estação que está atualmente selecionada

3. Crie um construtor sem parâmetros, onde deve iniciar o construtor da classe `MediaPlayer` e o atributo `stationList`. Implemente os métodos seletores e modificadores necessários.

4. Re-escreva o método `whatsPlaying()` para retornar a seguinte string, referente à estação atual:
```console
Live Radio Player: 103.4: Renascença
``` 

1. Implemente os métodos `nextStation()` e `previousStation()` que servirão para passar para a estação seguinte ou anterior da lista de estações. Se estiver na última estação da lista, deverá passar para a primeira estação, e vice-versa.


### Nível 4:

1. Na classe `AppStart`, no método `main`, efetue as seguintes operações:
   - Recorrendo ao princípio da substituição, crie um objeto do tipo `StreamPlayer`. Tenha em atenção ao tipo estático e tipo dinâmico.
   - Selecione um podcast dentro da lista seguinte:
    ```java
    List<Podcast> podcasts = new ArrayList<>();
    podcasts.add(new Podcast("O Homem que Mordeu o Cão"));
    podcasts.add(new Podcast("O CEO é o limite"));
    podcasts.add(new Podcast("Portugalex"));
    ```

2. Imprima para o ecrã através do método `whatsPlaying()`o resultado do objeto `streamPlayer`
3. Recorrendo novamente ao princípio da substituição, crie um objeto do tipo `LiveRadioPlayer`
4. Adicione ao objeto que criou a lista de emissoras (exemplo):

   ```java
   List<Station> stations = new ArrayList<>();
   stations.add(new Station(88.3, "KFM"));
   stations.add(new Station(93.2, "RFM"));
   stations.add(new Station(95.7, "Antena 1"));
   stations.add(new Station(97.4, "Rádio Comercial"));
   stations.add(new Station(101.9, "Orbital"));
   ```

5. Aumente o volume do objeto do tipo `LiveRadioPlayer` três vezes
6. No objeto do tipo `LiveRadioPlayer` avance dois canais e - cada vez que efetua a operação - imprima no ecrã o resultado do método `whatsPlaying()`

### Nível 5:

1. Na classe `AppStart` adicione uma lista que receberá objetos do tipo `MediaPlayer`
2. Adicione à lista criada os objetos criados no nível 1,2 e 3
3. Recorrendo a programação funcional, imprima no ecrã o resultado do método `whatsPlaying()` de todos os objetos existentes na coleção.



<mark>**Notas:**</mark>

Para os identificadores siga as convenções adotadas normalmente, em particular:

1. A notação **camelCase** para o nome das variáveis locais e identificadores de atributos e métodos.

2. A notação **PascalCase** para os nomes das classes.

3. Não utilize o símbolo ‘_’, nem abreviaturas nos identificadores.
