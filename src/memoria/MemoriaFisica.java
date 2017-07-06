package memoria;

public class MemoriaFisica {	
	private Integer indice;
	private Integer[] memoriaFisica;
	
	public MemoriaFisica(int pTamanho) {
		memoriaFisica = new Integer[pTamanho];
	}
	
	public int getValor(int pIndice) {
		return memoriaFisica[pIndice];
	}
	
	public void setValor(int pIndice, int pValor) {
		this.memoriaFisica[pIndice] = pValor;
	}
	
	public void setIndice(int pIndice) {
		this.indice = pIndice;
	}
	
	public Integer getIndice(){
		return this.indice;
	}
	
	public Integer[] getMemoria() {
		return this.memoriaFisica;
	}
}