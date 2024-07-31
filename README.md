# Trabalho pratico de grafos 2024-1

<details>
  <summary>Tabela de conteúdos</summary>
  <ol>
    <li>
      <a href="#decisões-tomadas">Decisões do projeto</a>
    </li>
    <li>
      <a href="#como-rodar">Como rodar</a>
      <ul>
        <li><a href="#pré-requisitos">Pré-requisitos</a></li>
        <li><a href="#especificações-do-arquivo-de-input-do-grafo">Especificações do arquivo de input do grafo</a></li>
        <li><a href="#rodando-o-projeto">Rodando o projeto</a></li>
      </ul>
    </li>
  </ol>
</details>

## Decisões tomadas

Os arquivo `.txt` é lido em `LeituraEscrita.java` e os vértices e arestas são armazenados em `Grafo.java`, assim como a adjacência dos vértices, na propriedade `listaAdjacencia`.

## Dúvidas

O padrão de leitura do grafo informa a quantidade de arestas e vértices, no entanto, os vértices são lidos somente a quantidade de arestas, visto a forma `id_aresta u v peso` informada. Existe alguma forma de receber input de vértices sem as arestas? Se sim, como?

## Como rodar

### Pré-requisitos

- JDK 21^
- Para utilizar com VSCode: [Extension pack for java](https://marketplace.visualstudio.com/items?itemName=vscjava.vscode-java-pack)

### Especificações do arquivo de input do grafo

O grafo a ser analizado estará em um arquivo .txt, e deve ser representado como no exemplo a baixo, seguindo as regras de chaves ‘{‘ ‘}’ para representar o conjunto, e finalizar com ponto e virgula ‘;’. Exemplo:

![exemplo de input em formato .txt](.github/exemplo-input-txt.png)

O arquivo a ser lido (com um exemplo pré-definido) é o [`grafo.txt`](./grafo.txt) na raiz do projeto

### Rodando o projeto

1. Configure o [arquivo `.txt`](#especificações-do-arquivo-de-input-do-grafo) conforme necessário
2. Com VSCode e as extensões para Java instaladas, abra o arquivo principal [Main.java](./src/Main.java)
3. Logo acima do método `main`, clique em `Run`
  