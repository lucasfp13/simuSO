package memoria;

public class MemoriaFisica {	
	private int indice;
	private Integer[] memoriaFisica;
	
	public MemoriaFisica(int pTamanho) {
		memoriaFisica = new Integer[pTamanho];
	}
	
	public int getValor(int pIndice) {
		return memoriaFisica[pIndice].intValue();
	}
	
	public void setValor(int pIndice, int pValor) {
		this.memoriaFisica[pIndice] = new Integer(pValor);
	}
	
	public void setIndice(int pIndice) {
		this.indice = pIndice;
	}
	
	public int getIndice(){
		return this.indice;
	}
	
	public Integer[] getMemoria() {
		return this.memoriaFisica;
	}
}