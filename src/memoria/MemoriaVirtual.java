package memoria;

public class MemoriaVirtual {	
	public PaginaVirtual[] memoriaVirtual;
	
	public MemoriaVirtual(int pTamanho){
		this.memoriaVirtual = new PaginaVirtual[pTamanho];
		for (int i = 0; i < pTamanho; i++) {
			this.memoriaVirtual[i] = new PaginaVirtual();
		}
	}
	
	public PaginaVirtual getPagina(int pIndice){
		return memoriaVirtual[pIndice];
	}
	
	public int getTamanho(){
		return memoriaVirtual.length;
	}
	
	public void setMolduraPagina(int pIndice, int pMolduraPagina){

	}
}