package memoria;

public interface Memoria { 
	public Integer ler(int pIndice, int idProcesso);
	public void escrever(int pIndice, int idProcesso);
}