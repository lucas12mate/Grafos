import java.util.ArrayList;
import java.util.List;

public class Vertice{
	public String cor;
	public int dist;
	public int pre;
	public int numVertice;
	public List<Integer> adj;

	public void setCor(String cor){
		this.cor = cor;
	}
	public void setDist(int dist){
		this.dist = dist;
	}
	public void setPre(int pre){
		this.pre = pre;
	}
	public void setNumVertice(int numVertice){
		this.numVertice = numVertice;
	}
	public void setAdj(List<Integer> adj){
		this.adj = adj;
	}

	public String getCor(){
		return this.cor;
	}
	public int getDist(){
		return this.dist;
	}
	public int getPre(){
		return this.pre;
	}
	public int getNumVertice(){
		return this.numVertice;
	}
	public List<Integer> getAdj(){
		return this.adj;
	}
	
}