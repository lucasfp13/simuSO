package memoria;

public class MemoriaFisica {	
	//private int indice;
	private Integer[] memoriaFisica;
	
	public MemoriaFisica(int pTamanho) {
		memoriaFisica = new Integer[pTamanho];
	}
	
	public Integer getValor(int pIndice) {
		return memoriaFisica[pIndice];
	}
	
	public void setValor(int pIndice, int pValor) {
		this.memoriaFisica[pIndice] = new Integer(pValor);
	}
	
	/*public void setIndice(int pIndice) {
		this.indice = pIndice;
	}*/
	
	/*public int getIndice(){
		return this.indice;
	}*/
	
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