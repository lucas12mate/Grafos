import java.util.Scanner;
import java.util.ArrayList;
import java.util.List;
import java.util.Arrays;

public class Grafos {
	
	public static void main(String[] args) {

		Scanner scan = new Scanner(System.in);
		String linha;
		int tam = 17;
		String[] vetor; 
		int[][] matrizAdj = new int[tam][tam];

		for (int i = 0; i < tam; i++) {
			linha = scan.nextLine();
			vetor = linha.split(";");
			for (int j = 0; j < tam; j++) {
				matrizAdj[i][j] = Integer.parseInt(vetor[j]);
			}
		}	
		
		//calculaDiametro(calculaMenorDist(matrizAdj));
		//System.out.println(calculaGrauMedio(matrizAdj));
		//montaVertices(matrizAdj);
		//bfs(matrizAdj, 1, 24, tam);
		//System.out.println(calculaDensidade(matrizAdj, tam));
		//calculaMenorDist(matrizAdj, tam);
		//System.out.println(calculaGrauMedio(matrizAdj, tam));
		System.out.println(ccg(matrizAdj, tam));




	}

	public static List<Vertice> montaVertices(int[][] matrizAdj, int tam){
		List<Vertice> vertices = new ArrayList<Vertice>();
		Vertice v;
		for (int i = 0; i < tam; i++) {
			v = new Vertice();
			v.setCor("branco");
			v.setDist(Integer.MAX_VALUE);
			vertices.add(v);
		}
		int index = 1;
		for (Vertice vertice : vertices) {
			vertice.setNumVertice(index);
			index++;
		}

		List<Integer> adj;
		//System.out.println("Verices adjacentes:");
		for (int i = 0; i < tam; i++) {
			adj = new ArrayList<Integer>();
			for (int j = 0; j < tam; j++) {
				if (matrizAdj[i][j] == 1){
					adj.add(j+1);
				}
			}
			vertices.get(i).setAdj(adj);
			//System.out.println(vertices.get(i).getNumVertice()+": " + vertices.get(i).getAdj());
		}
		return vertices;
	}

	public static List<Integer> insere(List<Integer> filaDeEspera,int v){
		List<Integer> fila = filaDeEspera;
		filaDeEspera.add(v);
		return fila;
	}

	public static List<Integer> remove(List<Integer> filaDeEspera){
		List<Integer> fila = filaDeEspera;
		fila.remove(0);
		return fila;
	}

	public static int bfs(int[][] matrizAdj, int s, int v, int tam) {
		List<Integer> filaDeEspera = new ArrayList<Integer>();
		List<Vertice> vertices = montaVertices(matrizAdj, tam);
		List<Integer> jaVisitados = new ArrayList<Integer>();

		vertices.get(s-1).setCor("cinza");
		vertices.get(s-1).setDist(0);
		filaDeEspera = insere(filaDeEspera, s);
		//System.out.println(s+": (0, " + s +")");

		while (!filaDeEspera.isEmpty()) {
			Vertice b = vertices.get(filaDeEspera.get(0)-1);
			//System.out.println("vertice atual: " + b.getNumVertice());
			jaVisitados.add(filaDeEspera.get(0));
			filaDeEspera = remove(filaDeEspera);
			for (Integer adj : b.getAdj()) {
				if (!jaVisitados.contains(adj)){
					//System.out.println("inserindo " + adj + " na fila de espera");
					filaDeEspera = insere(filaDeEspera, adj);
				}
			}

			for (Integer busca : filaDeEspera) {
				if (vertices.get(busca-1).getCor().equals("branco")) {
					vertices.get(busca-1).setCor("cinza");
					vertices.get(busca-1).setDist(b.getDist()+1);
					//System.out.println(vertices.get(busca-1).getNumVertice()+": (" + vertices.get(busca-1).getDist() + ", " + s + ")");
					vertices.get(busca-1).setPre(b.getNumVertice());
				}
			}
			b.setCor("preto");
		}

		return vertices.get(v-1).getDist();
	}

	public static int[][] calculaMenorDist(int[][] matrizAdj, int tam) {
		int[][] menorDistancia = new int[tam][tam];
		int distMedia, distTotal = 0, numDist = 0;
		//System.out.println();		
		for (int i = 0; i < tam; i++) {
			for (int j = i; j < tam; j++) {
				if (i != j) {
					menorDistancia[i][j] = bfs(matrizAdj, i+1, j+1, tam);
					distTotal += menorDistancia[i][j];
					numDist++;
					//System.out.print(menorDistancia[i][j]+ " ");
				} else {
					menorDistancia[i][j] = 0;
				}
			}
			//System.out.print("\n");
		}
		distMedia = distTotal/numDist;
		//System.out.println(distMedia);
		return menorDistancia;
	}

	public static int calculaDiametro(int[][] distancias, int tam) {
		int maiorDist = Integer.MIN_VALUE;
		for (int i = 0; i < tam; i++) {
			for (int j = i; j < tam; j++) {
				if (distancias[i][j] > maiorDist) {
					maiorDist = distancias[i][j];
				}
			}
		}
		System.out.println(maiorDist);
		return maiorDist;
	}

	public static double calculaGrauMedio(int[][] matrizAdj, int tam) {
		double grauMedio, grauTotal = 0;
		List<Vertice> vertices = montaVertices(matrizAdj, tam);
		for (int i = 0; i < tam; i++) {
			for (Integer adjacentes : vertices.get(i).getAdj()) {
				if (adjacentes-1 > i) {
					grauTotal++;
				}
			}
		}
		//System.out.println(grauTotal);
		grauMedio = 2*grauTotal/tam;
		return grauMedio;
	}

	public static double calculaDensidade(int[][] matrizAdj, int tam) {
		double densidade, numArestas = 0;
		List<Vertice> vertices = montaVertices(matrizAdj, tam);
		for (int i = 0; i < tam; i++) {
			for (Integer adjacentes : vertices.get(i).getAdj()) {
				if (adjacentes-1 > i) {
					numArestas++;
				}
			}
		}

		densidade = (2*numArestas)/(tam*(tam-1));
		return densidade;
	}

	public static double ccl(int[][] matrizAdj, int tam, int vi) {
		double ccl, numArestasEfe = 0;
		List<Vertice> vertices = montaVertices(matrizAdj, tam);
		List<Integer> adj = vertices.get(vi).getAdj();
		int grauVi = adj.size();
		if (grauVi != 1) {
			for (int i = 0; i < adj.size()-1; i++) {
				for (int j = i+1; j < adj.size(); j++) {
					if (vertices.get(adj.get(j)-1).getAdj().contains(adj.get(i))){
						numArestasEfe++;
					}
				}
			}
			ccl = 2*numArestasEfe/(grauVi*(grauVi-1));
			System.out.println("CC vertice " + (vi+1) +": " + ccl);
		} else {
			ccl = 0;
			System.out.println("CC vertice " + (vi+1) +": " + ccl);
		}
		return ccl;
	}

	public static double ccg(int[][] matrizAdj, int tam) {
		double cct = 0, ccg = 0;
		for (int i = 0; i < tam; i++) {
			cct += ccl(matrizAdj, tam, i);
		}
		ccg = cct/tam;
		return ccg;
	}

}