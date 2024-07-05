import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;
public class Menu {
    Grafo grafo;
    String Arquivo;
    Scanner scanner = new Scanner(System.in);
    public Menu(String arquivo) {
        try{
            this.grafo = LeituraEscrita.lerGrafo(arquivo);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public void iniciarMenu(){
        System.out.println("Escolha uma opção:\n1 - Verificar\n2 - Listar\n3 - Gerar\n4 - Sair");
        String opcao = scanner.nextLine();
        while(opcao != "4"){
            switch(opcao){
                case "1":
                    verificar();
                    break;
                case "2":
                    listar();
                    break;
                case "3":
                    gerar();
                    break;
                default:
                    System.out.println("Opção inválida");
                    break;
            }
            System.out.println("Escolha uma opção:\n1 - Verificar\n2 - Listar\n3 - Gerar\n4 - Sair");
            opcao = scanner.nextLine();
        }
        System.out.println("Saindo...");
    }
    private void verificar(){
        System.out.println(" a. Quantidade de vértices\r\n" + //
                        " b. Quantidade de arestas\r\n" + //
                        " c. Conexo\r\n" + //
                        " d. Bipartido\r\n" + //
                        " e. Euleriano\r\n" + //
                        " f. Hamiltoniano\r\n" + //
                        " g. Cíclico\r\n" + //
                        " h. Planar\r\n" + //
                        "");
        String opcao = scanner.nextLine();
        switch (opcao) {
            case "a":
                System.out.println(grafo.getVertices().size()+1);
                break;
            case "b":
                System.out.println(grafo.getArestas().size()+1);
                break;
            case "c":
                System.out.println(grafo.isConexo());
                break;
            case "d":
                System.out.println(grafo.isBipartido());
                break;
            case "e":
                System.out.println(grafo.isEuleriano());
                break;
            case "f":
                System.out.println(grafo.isHamiltoniano());
                break;
            case "g":
                System.out.println(grafo.isCiclico());
                break;
            case "h":  
                System.out.println(grafo.isPlanar());
                break;
            default:
                break;
        }
    }
    private void listar(){
        System.out.println(" a. Vértices\r\n" + //
                        " b. Arestas\r\n" + //
                        " c. Componentes conexas\r\n" + //
                        " d. Um caminho Euleriano\r\n" + //
                        " e. Um caminho Hamiltoniano\r\n" + //
                        " f. Vértices de articulação\r\n" + //
                        " g. Arestas ponte");
        String opcao = scanner.nextLine();
        switch (opcao) {
            case "a":
                HashMap<String,Vertice> vertices = grafo.getVertices();
                for(String v : vertices.keySet()){
                    System.out.print(v + " ");
                }
                break;
            case "b":
                List<Aresta> arestas = grafo.getArestas();
                for(Aresta a : arestas){
                    System.out.print(a + " ");
                }
                break;
            case "c":
                List<List<Vertice>> componentes = grafo.getComponentesConexas();
                for(List<Vertice> c : componentes){
                    for(Vertice v : c){
                        System.out.print(v + " ");
                    }
                    System.out.println();
                }
                break;
            case "d":
                List<Vertice> euler = grafo.encontrarCaminhoEuleriano();
                for(Vertice v : euler){
                    System.out.print(v + " -> ");
                }
                break;
            case "e":
                List<Vertice> hamilton = grafo.encontrarCaminhoHamiltoniano();
                for(Vertice v : hamilton){
                    System.out.print(v + " -> ");
                }
                break;
            case "f":
                Set<Vertice> articulacao = grafo.encontrarVerticesArticulacao();
                for(Vertice v : articulacao){
                    System.out.print(v + " ");
                }
                break;
            case "g":
                List<Aresta> pontes = grafo.encontrarArestasPonte();
                for(Aresta a : pontes){
                    System.out.print(a + " ");
                }
                break;
            default:
                break;
        }
    }
    private void gerar(){
        System.out.println(" a. Matriz de adjacência\r\n" + //
                        " b. Lista de adjacência\r\n" + //
                        " c. Árvore de profundidade\r\n" + //
                        " d. Árvore de largura\r\n" + //
                        " e. Árvore geradora mínima\r\n" + //
                        " f. Ordem topológia\r\n" + //
                        " g. Caminho mínimo entre dois vértices\r\n" + //
                        " h. Fluxo máximo\r\n" + //
                        " i. Fechamento transitivo\r\n");
        String opcao = scanner.nextLine();
        switch (opcao) {
            case "a":
                int matriz[][] = grafo.getMatrizAdjacencia();
                for(int i = 0; i < matriz.length; i++){
                    for(int j = 0; j < matriz.length; j++){
                        System.out.print(matriz[i][j] + " ");
                    }
                    System.out.println();
                }
                break;
            case "b":
                System.out.println(grafo.getListaAdjacencia());
                break;
            case "c":
                Grafo profundidade = grafo.gerarArvoreDeProfundidade(grafo.getVertices().get(0));
                System.out.println(profundidade.getListaAdjacencia());
                break;
            case "d":
                Grafo largura = grafo.gerarArvoreDeLargura(grafo.getVertices().get(0));
                System.out.println(largura.getListaAdjacencia());
                break;
            case "e":
                Grafo minimo = grafo.gerarArvoreGeradoraMinima();
                System.out.println(minimo.getListaAdjacencia());
                break;
            case "f":
                if(!grafo.isDirecionado()) break; // Não é possível gerar ordem topológica em grafos não direcionados
                List<Vertice> topo = grafo.gerarOrdemTopologica();
                for(Vertice v : topo){
                    System.out.print(v + " | ");
                }
                break;
            case "g":
                if(!grafo.isPonderado()) break; // Não é possível gerar caminho mínimo em grafos não ponderados
                System.out.println("Digite o vértice de partida e o vértice de chegada:");
                String partida = scanner.nextLine();
                String chegada = scanner.nextLine();
                ArrayList<Vertice> caminho = grafo.caminhoMinimo(grafo.getVertices().get(Integer.parseInt(partida)), grafo.getVertices().get(Integer.parseInt(chegada)));
                for(Vertice v : caminho){
                    System.out.print(v + " -> ");
                }
                break;
            case "h":
                if(!grafo.isPonderado()) break; // Não é possível gerar fluxo máximo em grafos não ponderados
                System.out.println("Digite o vértice de partida e o vértice de chegada:");
                String origem = scanner.nextLine();
                String fim = scanner.nextLine();
                System.out.println(grafo.fluxoMaximo(grafo.getVertices().get(Integer.parseInt(origem)), grafo.getVertices().get(Integer.parseInt(fim))));
                break;
            case "i":
                if(!grafo.isPonderado()) break; // Não é possível gerar fecho transitivo em grafos não ponderados
                Map<Vertice, Set<Vertice>> fecho = grafo.fechoTransitivo();
                for(Vertice v : fecho.keySet()){
                    System.out.print(v + " -> ");
                    for(Vertice u : fecho.get(v)){
                        System.out.print(u + " ");
                    }
                    System.out.println();
                }
                break;
            default:
                break;
        }
    }
}
