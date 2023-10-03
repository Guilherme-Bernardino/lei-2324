# PT
***
# Laboratório 5 - Enunciado
Template para o Lab 5

## Objetivos:
* Compreensão da implementação do ADT Graph;
* Utilização do ADT Tree;
* Desenvolvimento de testes unitários;
* Utilizar a API SmartGraph para visualizar e interagir com um grafo.

## Introdução:

Comece por clonar o projeto existente no GitHub no seguinte endereço:
https://github.com/estsetubal-pa-geral/Lab5Template.git

O objetivo do laboratório é fazer a implementação da classe MapCities que utiliza o **ADT Graph<V, E>** para implementar um mapa de distâncias entre cidades (City).

NOTA: Todos os métodos seguintes deverão ser implementados invocando métodos já implementados na classe base utilizada (não é necessário fazer qualquer alteração na implementação da classe).



## Nível 1 – Implementação dos métodos que fornecem informação sobre a classe

Crie os seguintes métodos auxiliares privados na classe MapCities:
* **Vertex<City> findCity(String city)**  
  Devolve o vértice onde a cidade city se encontra ou null, c.c.

* **boolean existCity(String city)**  
  Verifica se a cidade city existe (como vértice).

* **Edge<Integer, City> findConnection(String sourceCity, String destinationCity)
  throws ConnectionInvalidOperation**  
  Devolve a aresta que liga os vértices sourceCity e destinationCity ou null. Caso alguma das cidades não exista é lançada uma exceção.

* **boolean existConnection(String sourceCity, String destinationCity)**  
  Verifica se existe a ligação (aresta) entre sourceCity e destinationCity.



## Nível 2 – Implementar os métodos para adicionar e remover elementos no grafo
Crie os seguintes métodos públicos:
* **void addCity(String city)
  throws CityInvalidOperation**  
  Insere uma nova cidade no mapa (um novo vértice). Se a cidade já existir é lançada uma exceção.

* **Vertex<City> removeCity(String city)
  throws CityInvalidOperation**  
  Remove e devolve a cidade enviada ao método. Se a cidade não existir é lançada a exceção.

* **void addConnection(String sourceCity, String destinationCity, int distance)
  throws ConnectionInvalidOperation**  
  Adiciona ao grafo a ligação entre duas cidades (aresta entre dois vértices). Se a ligação entre as cidades já existir é lançada a exceção.

* **void removeConnection(String sourceCity, String destinationCity)
  throws ConnectionInvalidOperation**  
  Remove do grafo a ligação entre duas cidades. Se a ligação não existir é lançada a exceção.

 

## Nível 3 – Visualização do grafo usando SmartGraph
* Altere o método **build_sample_map** e adicione as cidades e as distâncias indicadas como comentário no método.
* Altere a propriedade **edge.label** no ficheiro **smartgraph.properties** para ver no mapa as distâncias entre as cidades.



## Nível 4 – Implementar métodos adicionais e testes unitários relacionados
Crie os seguintes métodos públicos:
* **boolean isIsolated(String city) throws CityInvalidOperation**  
  Verifica se uma cidade está isolada, isto é, se existe no grafo, mas não tem ligações a qualquer outra cidade (nem a ela própria).

* **Collection<City> neighbors(String city) throws CityInvalidOperation**  
  Devolve a lista de cidades "vizinhas" de uma determinada cidade.



Comece por criar o método setup de modo a disponibilizar uma configuração inicial a cada um dos testes unitários.
De seguida implemente os seguintes testes unitários:
* **isIsolated_isCorrect_AfterInsertRemoveCitiesAndConnections**  
  Verifica se:
  * Um vértice depois de criado é considerado isolado;
  * Um vértice depois de ligado a outro não é considerado isolado;
  * Um vértice ligado a outro volta a ser considerado isolado se for removida a única ligação entre eles;
  * Um vértice com >=2 ligações a outros vértices não é isolado depois de remover uma das ligações.

* **addConnection_isCorrect_whenSourceIsEqualToDestination**  
  Verifica se se pode fazer uma ligação de uma cidade a ela própria, isto é, se não é lançada qualquer a exceção.

* **neighbors_isCorrect_afterInsertAndRemoveVerticesAndEdges**  
  Verifica se a lista de cidades vizinhas é gerada corretamente, isto é:
  * Se X for uma cidade sem ligações, então não tem vizinhos;
  * Se X->Y, então Y faz parte da lista de vizinhos de X
  * Se X->Y e se removermos a ligação entre X e Y, então Y não faz parte da lista de vizinhos de X;
  * Se X->Y, se removermos Y e voltarmos a adicionar a cidade Y (mas não a ligação), então Y não faz parte da lista de vizinhos de X.

Na implementação da UT utilize a função auxiliar exists.
```
    private boolean exists(Iterable<City> list, String city) {
        for(City item: list)
            if (item.element().equals(city))
                return true;
        return false;
    }
```



## 5 Implementação do método load
Complete a implementação do método load da classe que permite fazer o carregamento das cidades e respetivas ligações a partir de um ficheiro de dados (texto).
As cidades e as ligações devem ser criadas dinamicamente através dos métodos existentes na classe. A criação dos elementos deverá ser feita apenas se o elemento não existir, seja ele cidade ou ligação.
A estrutura do ficheiro é a seguinte:

<table>
 <tr>
   <td>Linha de dados<br>(Exemplo)</td><td>Descrição</td>
 </tr>
 <tr>
   <td>Abrantes</td><td>Uma palavra apenas – Nome da cidade (vértice apenas)</td>
 </tr>
 <tr>
   <td>Faro 2</td><td>Duas palavras apenas - Uma cidade e a distância para si própria<br>
                      (A ligação a implementar será origem: Faro | destino: Faro | distância: 2)</td>
 </tr>
 <tr>
   <td>Viseu Coimbra 95</td><td>Três palavras – Cidade de origem, cidade de destino e a distância entre elas.<br>
                      (A ligação a implementar será origem: Viseu | destino: Coimbra | distância: 95)</td>
 </tr>
 <tr>
   <td colspan=2>**Nota:** Existe apenas um espaço entre cada elemento numa linha de dados</td>
 </tr>
 </table>

Aplique o método load ao ficheiro **data/demo-graph.txt** disponibilizado no projeto para fazer o carregamento automático de dados de um mapa e reescreva o carregamento dos dados a apresentar no _SmartGraph_ a partir desse ficheiro.

***

# EN
***
# Lab 5 - Statement
Template for Lab 5

## Objectives
* Understanding the implementation of ADT Graph;
* Use of ADT Tree;
* Developing unit tests
* Using the SmartGraph API to visualize and interact with a graph.

## Introduction:

Start by cloning the existing project on GitHub at the following address:
https://github.com/estsetubal-pa-geral/Lab5Template.git

The goal of the lab is to make an implementation of the MapCities class that uses the **ADT Graph<V, E>** to implement a map of distances between cities (City).

NOTE: All of the following methods should be implemented by invoking methods already implemented in the base class used (no changes need to be made to the class implementation).



## Level 1 - Implementation of methods that provide information about the class

Create the following private auxiliary methods in the MapCities class:
** **Vertex<City> findCity(String city)**  
Returns the vertex where the city city is located or null, c.c.

* **boolean existCity(String city)**  
  Checks if the city city exists (as a vertex).

* **Edge<Integer, City> findConnection(String sourceCity, String destinationCity)
  throws ConnectionInvalidOperation**  
  Returns the edge connecting the vertices sourceCity and destinationCity or null. If any of the cities do not exist an exception is thrown.

* **boolean existConnection(String sourceCity, String destinationCity)**  
  Checks if the connection (edge) exists between sourceCity and destinationCity.



## Level 2 - Implement the methods for adding and removing elements in the graph
Create the following public methods:
* **void addCity(String city)
  throws CityInvalidOperation**.  
  Inserts a new city on the map (a new vertex). If the city already exists an exception is thrown.

* Vertex<City> removeCity(String city)
  throws CityInvalidOperation**.  
  Removes and returns the city sent to the method. If the city does not exist an exception is thrown.

* **void addConnection(String sourceCity, String destinationCity, int distance)
  throws ConnectionInvalidOperation**  
  Adds the connection between two cities to the graph (edge between two vertices). If the connection between the cities already exists the exception is thrown.

* **void removeConnection(String sourceCity, String destinationCity)
  throws ConnectionInvalidOperation**.  
  Removes the connection between two cities from the graph. If the connection does not exist the exception is thrown.

 

## Level 3 - Visualizing the graph using SmartGraph
* Change the **build_sample_map** method and add the cities and distances indicated as comments in the method.
* Change the **edge.label** property in the **smartgraph.properties** file to see the distances between cities on the map.



## Level 4 - Implement additional methods and related unit tests
Create the following public methods:
* **boolean isIsolated(String city) throws CityInvalidOperation**  
  Checks if a city is isolated, that is, if it exists in the graph but has no connections to any other city (nor to itself).

* **Collection<City> neighbors(String city) throws CityInvalidOperation**  
  Returns the list of "neighboring" cities for a given city.



Start by creating the setup method to provide an initial setup for each of the unit tests.
Next implement the following unit tests:
* **isIsolated_isCorrect_AfterInsertRemoveCitiesAndConnections**  
  Checks that:
  * A vertex after creation is considered isolated;
  * A vertex after being connected to another vertex is not considered isolated;
  * A vertex that is connected to another vertex is considered isolated again if the only connection between them is removed
  * A vertex with >=2 connections to other vertices is not isolated after removing one of the connections.

* **addConnection_isCorrect_whenSourceIsEqualToDestination**  
  Checks that a connection from a city to itself can be made, that is, that no exception is thrown.

* **neighbors_isCorrect_afterInsertAndRemoveVerticesAndEdges**  
  Checks that the list of neighboring cities is generated correctly, i.e:
  * If X is an unconnected city, then it has no neighbors;
  * If X->Y, then Y is part of X's neighbor list
  * If X->Y and if we remove the link between X and Y, then Y is not part of X's neighbor list;
  * If X->Y, if we remove Y and add back the city Y (but not the link), then Y is not part of the list of neighbors of X.

In the UT implementation use the auxiliary function exists.
```
    private boolean exists(Iterable<City> list, String city) {
        for(City item: list)
            if (item.element().equals(city))
                return true;
        return false;
    }
```



## Level 5 - Implementing the load method
Complete the implementation of the class load method that allows you to load cities and their links from a data (text) file.
Cities and links must be created dynamically through the methods in the class. The creation of
