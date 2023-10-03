# Iterator_Template

Packages base para realização dos exercícios da aula sobre o padrão Iterator

## ADT STACK
1. Reveja a implementação do Padrão Iterator para a classe``StackArray``

2. Implemente o método iterator na classe ``StackLinked``, de forma a esta passar a ser Iterável.



## ADT Tree
1. Torne a classe ``TreeLiked`` Iterável, implementando o iterador de forma a disponibilizar o elementos da árvore na sequência pre-order.

## Exercicios Adicionais
1. Implemente para o TAD Stack um iterador que percorra os elementos da base para o topo.
  - Defina na ``interface Stack`` o método ``Iterator<E> iteratorInverse()``
  - Implemente o método para o ``StackArray`` e ``StackLinked``
  - Teste este novo iterador no main, usando os métodos ``hasNext`` e ``next``.
 ```java
 Iterator<Integer> it= stack.iteratorInverse();
  while(it.hasNext())
     System.out.print(it.next() + " ");
 ```
