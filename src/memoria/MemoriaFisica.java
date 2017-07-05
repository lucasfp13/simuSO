package memoria;

public class MemoriaFisica {	
	private int indice;
	private int[] memoriaFisica;
	
	public MemoriaFisica(int pTamanho){
		memoriaFisica = new int[pTamanho];
	}
	
	public int getValor(int pIndice){
		return memoriaFisica[pIndice];
	}
	
	public void setValor(int pIndice, int pValor){
		this.memoriaFisica[pIndice] = pValor;
	}
	
	public void setIndice(int pIndice){
		this.indice = pIndice;
	}
	
	public int getIndice(){
		return this.indice;
	}
}