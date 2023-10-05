# Programação Orientada por Objetos 2022/2023

## Ficha de Laboratório <mark>#4</mark>

### Objetivo

> - Introdução ao desenho de aplicações. Pretende-se dar início à etapa de desenho de uma aplicação, trabalhando apenas um subconjunto do Projeto I.

## Trabalho a entregar

- Ficheiro de texto com:
  
  - a análise substantivo/verbo;
  
  - as cartas CRC

- O esboço das classes envolvidas nas cartas CRC

## Regras de implementação

- Aceitar o assignment do GitHub Classroom [**aqui**](https://classroom.github.com/a/LFsnMEWk)
- Clonar o repositório gerado pelo GitHub Classroom
- Criar os ficheiros de texto com a análise e as cartas CRC dentro da pasta do projeto BlueJ
- Utilizar o IDE BlueJ para o esboço das classes.
- **Atualizar a versão do trabalho de laboratório no repositório no mínimo no fim de cada nível**. 
- Use as convenções de codificação adotadas para a linguagem Java para o código que for criado (ver **Notas**).

## Descrição do problema

O objeto do projeto é o desenvolvimento de uma simulação de um centro de distribuição de produtos. Neste centro de distribuição, os produtos são recebidos no armazém num local de entrega e depois são transportados para os locais onde ficam armazenados. O transporte é efetuado por veículos guiados automaticamente (Automated Guided Vehicle - AGV). A distribuição dos produtos para os locais de venda segue um processo idêntico onde os produtos armazenados são retirados dos seus locais de armazenamento e levados para um local de recolha dentro do armazém.

### Centro de Distribuição

O armazém do centro de distribuição é um espaço retangular que vai conter todos os elementos da simulação. Este espaço está dividido em posições, onde cada uma delas representa uma área quadrada de 2x2m.  Os elementos existentes podem ser locais de armazenamento com prateleiras/estantes para guardar os produtos, veículos que circulam no armazem ou as paredes que limitam o armazém. Além disso, existem espaços próprios para a carga e descargas dos produtos que o armazém contém e zonas em frentes aos locais de armazenamento internos para depositar e recolher os produtos.

### Produtos

Para esta simulação, os produtos são muito simples caracterizando-se apenas por um nome, um identificador único, o seu peso e o seu tipo. 

### Veículos Guiados Automaticamente (AGV)

Os veículos AGV são usados no interior do armazém para tranportar os produtos. Estes veículos seguem caminhos pré-determinados entre o local de recolha e o local de destino do produto ou definem automaticamente o seu percurso entre esses locais.  Existem diferentes tipos de veículos com funcionalidades distintas:

- **Transportador de Carga Unitária** (Unit Load Carrier - ULC) - É um AGV usado para transportar um único produto. Não tem limites de peso para a carga transportada;
- **Carrinho de Transporte** - Não é um AGV. Destina-se a transportar vários produtos até um limite de peso de 200 Kg e é puxado por um veículo rebocador.
- **Carrinho Guiado Automaticamente** (Automatic Guided Card - AGC) – É um AGV usado para transportar produtos leves; Tem um limite de peso total de 100 Kg.
- **Veículo Rebocador** – É um AGV que não transporta diretamente os produtos. Neste caso, é usado rebocar um carrinho de transporte.

O movimento dos veículos dentro do armazém faz-se exclusivamente na direção horizontal ou na direção vertical. Qualquer deles se movimenta uma posição por cada passo da simulação, podendo virar ou parar. Todos os veículas ocupam uma posição do armazém, ou seja uma área de 2x2 metros.

### Simulação

Para esta simulação deve ser definido um armazém. Podem ser depois adicionados veículos e locais de armazenamento ao armazém criado. Durante a simulação, serão entregues produtos que devem ser armazenados. Deve ser feita uma seleção dos veiculos para transportar os produtos até ao seu local de armazenamento. Da mesma forma, podem ser solicitados vários produtos entre os existentes no armazém. Neste caso, os mesmos veículos podem ser usados para recolher os produtos e deixá-los nos pontos de entrega. 

Para a simulação será necessário definir o armazém com os locais de armazenamento, de entrega e de recolha de produtos e os veículos de transporte. Durante a simulação são entregues produtos para armazenamento e pedidos de recolha de produtos do armazém que devem ser satisfeitos pelos veículos existentes.

### Nível 1:

- Copie o conteúdo do tópico "Descrição do Problema" para um processador de texto à sua escolha.

- No texto copiado, pinte de verde todos os substantivos (nomes) para começar a ter uma ideia das entidades envolvidas no problema.

- Pinte de vermelho todos os verbos para começar a ter uma ideia das ações envolvidas no projeto.

### Nível 2:

- Copie todos os substantivos para uma lista e elimine os duplicados.

- Copie todos os verbos e respetivos substantivos associados para outra lista e elimine as ações repetidas.

### Nível 3:

- Com base nos substantivos extraídos, faça uma 3ª lista em que identifica o que lhe pareça poder vir a constituir classes de objetos.

- Para cada uma delas faça a respetiva carta CRC, tendo em conta apenas a informação disponível na descrição da Simulação. Deve ter presente o princípio da
  responsabilidade única.

## Nível 4:

- Com base nas cartas CRC das classes identificadas no Nível 3 defina uma primeira
  aproximação da interface das classes envolvidas (métodos públicos). Crie um esboço da implementação da classe, com a sua estrutura, os cabeçalhos dos métodos públicos, ainda sem o código interno, e a respetiva documentação destes métodos.

### Nível 5:

- Crie um esboço da implementação das funcionalidades principais identificadas na classe do AGC, com a estrutura da classe, os cabeçalhos do métodos públicos, ainda sem o código interno, e a respetiva documentação destes métodos.

**Notas:**

Para os identificadores siga as convenções adotadas normalmente, em particular:

1. A notação **camelCase** para o nome das variáveis locais e identificadores de atributos e métodos.

2. A notação **PascalCase** para os nomes das classes.

3. Não utilize o símbolo ‘_’, nem abreviaturas nos identificadores.
