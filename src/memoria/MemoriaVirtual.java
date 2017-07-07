package memoria;

public class MemoriaVirtual {	
	private PaginaVirtual[] memoriaVirtual;
	
	public MemoriaVirtual(int pTamanho){
		this.memoriaVirtual = new PaginaVirtual[pTamanho];
		for (int i = 0; i < pTamanho; i++) {
			this.memoriaVirtual[i] = new PaginaVirtual();
		}
	}
	
	public void setPagina(PaginaVirtual pPagina, int pIndiceVirtual){
		this.memoriaVirtual[pIndiceVirtual] = pPagina;
	}
	
	public PaginaVirtual getPagina(int pIndice){
		return memoriaVirtual[pIndice];
	}
	
	public int getTamanho(){
		return memoriaVirtual.length;
	}
}