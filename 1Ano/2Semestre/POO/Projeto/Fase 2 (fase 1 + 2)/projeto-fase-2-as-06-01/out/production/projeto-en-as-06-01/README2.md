+# Programação Orientada por Objetos

Centro de Distribuição - Segunda fase

**Época normal**

# 1.  Introdução

O objetivo deste projeto é o desenvolvimento de uma simulação do funcionamento de um centro de distribuição de produtos utilizando a linguagem Java e a Programação Orientada por Objetos (POO).

Neste centro de distribuição, os produtos são recebidos num local especifico do armazém, são embalados e depois são transportados para os locais onde ficam armazenados. O transporte é efetuado por veículos guiados automaticamente (Automated Guided Vehicle - AGV). A distribuição dos produtos para os locais de venda segue um processo idêntico onde os produtos armazenados são retirados dos locais de armazenamento e levados para um local de recolha dentro armazém.

O projeto será desenvolvido em duas fases. A primeira fase consistiu na modelação e implementação de um conjunto de classes que permitam representar a lógica da aplicação e que permitam a sua utilização em modo **consola**. A segunda fase será dedicada a completar as funcionalidades definidas na primeira fase e à criação da **interface gráfica** com o utilizador.

Tenha particular atenção ao uso do paradigma de POO na modelação das classes, i.e., ao correto uso dos conceitos de encapsulamento, herança, classes abstratas, polimorfismo, interfaces, maximização da coesão (responsabilidade única), minimização do acoplamento, desenho orientado por responsabilidades, etc. Uma modelação bem pensada facilitará todo o processo de desenvolvimento e manutenção.

**O presente documento refere-se apenas à segunda-fase do projeto.**

# 2.  Objetivos

Na segunda fase do projeto o objetivo passa por completar as funcionalidades e desenvolver a interface gráfica, em **JavaFx**, que permita ao utilizador interagir com o sistema concebido na primeira fase.

A interface gráfica **deve incluir** **apenas**  os requisitos indicados no ponto 3. Contudo deverá ser reaproveitado o código necessário da primeira fase.

# 3.  Funcionalidades e requisitos de implementação

## 3.1. Completar as funcionalidades definidas na primeira fase

Nesta segunda fase será necessário completar as funcionalidades que ficaram como opcionais na primeira fase, nomeadamente:

- Implementar os vários tipos de sensores com as características definidas na primeira fase.
- Implementar o movimento **pré-definido** para os vários veículos. Neste caso, deve ser definido uma sequência de pontos que cada veículo deve seguir para o transporte da carga entre os vários locais. Não é necessário o cálculo dinâmico da trajetória dos veículos.

## 3.2. Interface gráfica

Deverá ser implementada uma interface gráfica, em JavaFX, **que permita visualizar o centro de distribuição com todos os elementos que o compõem**. Na simulação é necessária a visualização do movimento dos veículos. Um possibilidade será aproveitar o tipo de movimentação apresentado no exemplo da companhia de táxis para reproduzir esta movimentação no projeto.

Também será necessário a visualização do número de produtos armazenados e o peso total dos produtos armazenados (com atualização dinâmica destes valores).

## 3.3. Requisitos da Implementação

Para a implementação da aplicação deverão obrigatoriamente ser utilizadas:

3.2.1. Coleções observáveis

3.2.2. Propriedades (*Properties*), sempre que seja pertinente

3.2.3. Eventos para executar as alterações no ecrã

3.2.4. Efeitos ou animações (por exemplo na “apresentação de gráficos”)

## 3.3. Funcionamento da aplicação e novos requisitos funcionais

3.3.1. A visualização dos passos da simulação deixa de utilizar a consola, passando a ter uma interface gráfica.

3.3.2. Deverá ser possível distinguir e identificar os diferentes locais e elementos do armazém, em particular:

3.3.2.1.       **Paredes do armazém** – As paredes do armazém devem ser identificadas por uma linha de posições na vertical e na horizontal.

3.3.2.2.       **Locais de entrega e recolha de produtos** Os locais de entrega e recolha de produtos devem estar assinalados. Neste caso, correspondem a uma interrupção das paredes do armazém nessas posições. Pode optar por usar cores diferentes para as posições ocupados por estes pontos.

3.3.2.3.       **Prateleiras** As prateleiras devem ser facilmente identificadas e devem mostrar em cada momento o número de produtos que contêm. A zona de carregamento dos produtos em frente às prateleiras também deve estar assinalada. Por exemplo, com uma cor diferente. O armazém deverá ter pelo menos duas prateleiras de dimensão mínima de dois blocos.

3.3.2.4.       **Veículos** Deve ser possível distinguir entre os diferentes tipos de veículos. Para cada veículo, deve ser perceptível se transporta carga ou se está vazio.

# 4.  Fases de desenvolvimento e entrega

O projeto está dividido em 2 fases, com a cotação distribuída da seguinte forma:

·     Fase I – 70% da avaliação final

·     Fase II – 30% da avaliação final

Conforme referido na ficha da disciplina, poderá alternativamente entregar o projeto numa só data, na época de recurso (sem possibilidade de incorporar componente de avaliação contínua), contemplando as duas fases do projeto.

# 5.  Implementação e codificação

O programa deve ser desenvolvido utilizando a linguagem Java, colocando em prática os conceitos fundamentais do paradigma de Programação Orientada por Objetos.

Em relação às regras de codificação, siga as convenções adotadas normalmente para a linguagem Java:

·     A notação *camelCase* para o nome das variáveis locais e identificadores de atributos e métodos;

·     A notação *PascalCase* para os nomes das classes e interfaces;

·     Utilização de maiúsculas para os nomes das constantes e dos valores enumerados;

·     Não utilize o símbolo ‘_’ nos identificadores (exceto nas constantes), nem abreviaturas.

É necessário que o projeto cumpra o que é pedido no seu enunciado, sendo deixado ao critério do programador qualquer pormenor de implementação que não seja referido, o qual deverá ser devidamente documentado.

# 6.  Constituição de grupos

Cada projeto deverá ser elaborado em grupos de dois alunos, podendo eventualmente ser elaborado individualmente. Não serão permitidos, em nenhum caso, grupos com mais do que dois alunos.

Os grupos dos alunos já se encontram determinados através da metodologia de *pair programming* que está a ser utilizada nos laboratórios.

# 7.  Entrega do projeto

O projeto será entregue em duas fases:

·     Uma primeira fase (**até às 08:00:00 do dia 30 de maio de 2023**) com a implementação da lógica da aplicação (descrita no presente documento);

·     A segunda fase (**até às 22:00:00 do dia 1 de julho de 2023**) com a parte gráfica.

O projeto deverá ser entregue até à data limite especificada **por via exclusivamente eletrónica utilizando os grupos criados no Github**.

**Não serão aceites quaisquer projetos entregues fora do prazo!**

Todos os materiais do projeto devem ser devidamente identificados com nome, número e endereço de correio eletrónico dos alunos.

Os materiais do projeto deverão incluir:

·     Um Manual Técnico onde conste uma breve descrição do programa, incluindo a explicação das classes/interfaces implementadas, principais atributos e métodos e suas relações.

·     A documentação do programa em **JavaDoc** (não converta o documento gerado automaticamente em HTML para DOC!).

·     O código fonte do programa na forma de projeto em BlueJ, *NetBeans* ou *IntelliJ*, com um *main* de testes a funcionar e com todas as funcionalidades implementadas.

·     Todos os ficheiros que compõem o projeto deverão estar guardados num único ficheiro compactado em formato ZIP cujo nome deverá ter a seguinte nomenclatura: *<curso>_<numAluno1>_<numAluno2>*.zip.

# 8.  Regras e Critérios de Avaliação do Projeto

## 8.1. Regras de Avaliação

A avaliação do projeto está sujeita às seguintes regras:

·     Caso o aluno falte ao momento de supervisão, terá essa componente avaliada com zero valores.

·     **Não serão aceites quaisquer projetos entregues fora do prazo!**

·     A classificação do programa terá em conta a qualidade da programação (fatores de qualidade do software), a estrutura do código criado segundo os princípios da Programação Orientada por Objetos, tendo em conta conceitos como a coesão de classes e métodos, o grau de acoplamento entre classes e o desenho de classes orientado pela responsabilidade, e a utilização/conhecimento da linguagem Java.

·     Serão premiadas a facilidade de utilização, a apresentação, a imaginação e a criatividade.

·     O projeto terá uma componente de avaliação oral obrigatória com classificação individual dos elementos do grupo.

·     Os alunos que não comparecerem à discussão serão classificados com zero na fase respetiva. Nesta discussão será apurada a capacidade do aluno de produzir o código apresentado. Nos casos em que essa capacidade não for demonstrada, a nota atribuída será zero.

·     A avaliação oral é realizada pelo respetivo professor de laboratório e irá ser feita uma marcação prévia para cada grupo de trabalho.

·     Todos os projetos serão submetidos a um sistema automático de deteção de cópias. Os projetos que forem identificados como possíveis cópias, e verificando-se serem realmente cópias, serão anulados.

·     As avaliações da segunda fase do projeto serão realizadas na última semana de junho de 2023.

## 8.2. Critérios de Avaliação

A segunda fase do projeto será avaliada segundo os seguintes critérios:

| **Funcionalidades**                          | **45%** |
| -------------------------------------------- | ------- |
| **Implementação**                            | **35%** |
| Estrutura de classes                         | 20%     |
| Conhecimento e boa utilização  da linguagem  | 5%      |
| Bom estilo (nomes, comentários,  indentação) | 5%      |
| Definição de testes unitários                | 5%      |
| **Documentação** | **10%** |
| JavaDOC          | 5%      |
| Manual técnico   | 5%      |
| **Avaliação  qualitativa** | **10%** |


# 9.  Resumo das Datas Importantes

## 9.1. Entrega da 2ª fase

A entrega da 2ª fase do projeto será até **às 22:00:00 do dia 1 de julho de 2023**.

## 9.2. Avaliações da 2ª fase

As avaliações da segunda fase do projeto serão realizadas, se possível, entre os dias **03 e 04 de julho de 2023**.

---

# Object-Oriented Programming

Distribution Centre - Phase Two

**Normal season**

# 1. Introduction

The objective of this project is to develop a simulation of the operation of a product distribution centre using Java language and Object-Oriented Programming (OOP).

In this distribution centre, products are received at a specific location in the warehouse, are packaged and then transported to the locations where they are stored. The transport is carried out by Automated Guided Vehicles (AGV). The distribution of the products to the points of sale follows an identical process where the stored products are removed from the storage locations and taken to a collection point within the warehouse.

The project will be developed in two phases. The first phase will consist of modelling and implementing a set of classes that will represent the logic of the application and allow it to be used in **consolation** mode. The second phase will be dedicated to completing the functionalities defined in the first phase and to the creation of the **graphical user interface**.

Pay particular attention to the use of the OOP paradigm in the modelling of classes, i.e., the correct use of the concepts of encapsulation, inheritance, abstract classes, polymorphism, interfaces, cohesion maximisation (single responsibility), coupling minimisation, responsibility-oriented design, etc. A well thought-out modelling will facilitate the whole development and maintenance process.

**This document refers only to the second phase of the project.**

# 2. objectives

In the second phase of the project the goal is to complete the functionalities and develop the graphical interface, in **JavaFx**, that allows the user to interact with the system designed in the first phase.

The graphical interface **should** include **only** the requirements indicated in paragraph 3. However the necessary code from the first phase should be reused.

## 3. Functionalities and implementation requirements

## 3.1. Completing the functionalities defined in the first phase

In this second phase it will be necessary to complete the functionalities that were left as optional in the first phase, namely:

- Implement the various types of sensors with the features defined in the first phase.
- Implement the **pre-defined** movement for the various vehicles. In this case, it must be defined a sequence of points that each vehicle must follow to transport the load between the various locations. Dynamic calculation of the trajectory of the vehicles is not necessary.

## 3.2. Graphical interface

A graphical interface in JavaFX ** must be implemented in order to visualise the distribution centre with all its elements**. In the simulation, it is necessary to visualise the movement of the vehicles. One possibility is to use the type of movement shown in the taxi company example to reproduce this movement in the project.

It will also be necessary to visualize the number of products stored and the total weight of stored products (with dynamic updating of these values).

## 3.3. Implementation Requirements

For the implementation of the application, the following must be used

3.2.1. observable collections

3.2.2. properties (*Properties*), whenever pertinent

3.2.3. events to execute screen changes

3.2.4. effects or animations (e.g. in "presentation graphics")

## 3.3. Application operation and new functional requirements

3.3.1 The visualisation of the simulation steps no longer uses the console, but a graphical interface.

3.3.2 It should be possible to distinguish and identify the different warehouse locations and elements, in particular

3.3.2.1.       **The walls of the warehouse** - The walls of the warehouse must be identified by a line of vertical and horizontal positions.

3.3.2.2.       **Product delivery and collection points** Product delivery and collection points should be marked. In this case, they correspond to a break in the warehouse walls at those positions. You may choose to use different colours for the positions occupied by these points.

3.3.2.3.       **Shelves** Shelves must be easily identified and must show at all times the number of products they contain. The loading area of the products in front of the shelves must also be marked. For example, with a different colour. The warehouse should have at least two shelves with a minimum size of two blocks.

3.3.2.4.       **Vehicles** It should be possible to distinguish between different types of vehicles. For each vehicle, it should be perceptible whether it is carrying a load or if it is empty.

# 4. development and delivery phases

The project is divided into 2 phases, with the quotation distributed as follows:

- Phase I - 70% of the final assessment

- Phase II - 30% of the final mark

As stated in the discipline sheet, you can alternatively deliver the project in a single date, in the appeal period (without possibility of incorporating continuous assessment component), contemplating the two phases of the project.

# 5. implementation and coding

The program should be developed using the Java language, putting into practice the fundamental concepts of the Object-Oriented Programming paradigm.

Regarding the coding rules, follow the conventions normally adopted for the Java language:

- The *camelCase* notation for the name of local variables and identifiers of attributes and methods;

- The *PascalCase* notation for class and interface names;

- Use of uppercase for the names of constants and enumerated values;

- Do not use the symbol '_' in identifiers (except in constants), nor abbreviations.

It is necessary that the project complies with what is requested in its statement, being left to the programmer's discretion any implementation detail that is not mentioned, which should be properly documented.

# 6. formation of groups

Each project must be prepared in groups of two students, and may eventually be prepared individually. Under no circumstances will groups with more than two students be allowed.

The student groups are already determined by the *pair programming* methodology being used in the laboratories.

# 7 Project delivery

The project will be delivered in two phases:

- A first phase (**by 08:00:00 on 30 May 2023**) with the implementation of the application logic (described in this document);

- The second phase (**until 22:00:00 on July 1st 2023**) with the graphic part.

The project must be delivered until the specified deadline **only electronically using the groups created on Github**.

**No projects submitted after the deadline will be accepted!**

All project materials must be properly identified with the students' name, number and email address.

Project materials should include:

- A Technical Manual containing a brief description of the program, including an explanation of the implemented classes/interfaces, main attributes and methods and their relationships.

- The program documentation in **JavaDoc** (do not convert the automatically generated HTML document to DOC!).

- The source code of the program in project form in BlueJ, *NetBeans* or *IntelliJ*, with a working test *main* and with all functionalities implemented.

- All the files that make up the project must be saved in a single compressed file in ZIP format whose name must have the following nomenclature: *<course>_<numStudent1>_<numStudent2>*.zip.

# 8. Project Evaluation Rules and Criteria

## 8.1. Evaluation Rules

Project evaluation is subject to the following rules:

- If the student misses the supervision moment, he/she will have that component assessed with zero values.

- **No projects delivered after the deadline will be accepted! **

- The program classification will take into account the quality of the programming (software quality factors), the structure of the code created according to the principles of Object Oriented Programming, taking into account concepts such as cohesion of classes and methods, the degree of coupling between classes and responsibility-oriented class design, and the use/knowledge of the Java language.

- User-friendliness, presentation, imagination and creativity will be rewarded.

- The project will have a compulsory oral evaluation component with individual grading of the group elements.

- Students who do not attend the discussion will be classified with zero in the respective phase. In this discussion, the student's ability to produce the code presented will be assessed. In cases where this ability is not demonstrated, the grade will be zero.

- The oral evaluation is performed by the respective laboratory teacher and a prior appointment will be made for each work group.

- All projects will be submitted to an automatic copy detection system. The projects that are identified as possible copies, and if they are indeed copies, they will be cancelled.

- The evaluations of the second phase of the project will take place in the last week of June 2023.


## 8.2. Evaluation Criteria

The second phase of the project will be evaluated according to the following criteria:

| **Functionalities** | **45%** |
| -------------------------------------------- | ------- |
| **Implementation** | **35%** |
| Class structure | 20%|
| Language knowledge and usage |5%.|
|Good style (names, comments, indentation) | 5% |
|Definition of unit tests | 5% |
| **Documentation** | **10%** |
| JavaDOC documentation|5% |
| Technical manuals |5%.|
|Qualitative evaluation | **10%** | 

# 9. Summary of Important Dates

## 9.1. Delivery of Phase 2

Delivery of the 2nd phase of the project will be by **at 22:00:00 on July 1, 2023**.

## 9.2. Evaluations of the 2nd phase

The evaluations of the second phase of the project will be carried out, if possible, between **03 and 04 July 2023**.