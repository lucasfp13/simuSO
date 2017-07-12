package memoria;

public class MemoriaFisica {	
	private Integer[] memoriaFisica;
	private int tamanho = 0;
	
	public MemoriaFisica(int pTamanho) {
		this.tamanho = pTamanho;
		memoriaFisica = new Integer[pTamanho];
	}
	
	public Integer getValor(int pIndice) {
		return memoriaFisica[pIndice];
	}
	
	public void setValor(int pIndice, Integer pValor) {
		this.memoriaFisica[pIndice] = pValor;
	}
	
	public int getTamanho() {
		return this.tamanho;
	}
	
	public Integer[] getMemoria() {
		return this.memoriaFisica;
	}
	
	public Integer getIndiceLivre(Integer[] memFisica) {
		for(int i = 0; i < memFisica.length; i++){
			if(memFisica[i] == null){
				return i;
			}
		}
		return null;
	}
}