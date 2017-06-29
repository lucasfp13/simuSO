package memoria;

// tem que fazer os testes pra isso
public interface Memoria { 
	public Memoria getpagina(int pIndice);
	public void setValor();
	public void getValor();
	public void setIndice(int pIndice);
	public void alocar();
	public int getTamanho();
}
