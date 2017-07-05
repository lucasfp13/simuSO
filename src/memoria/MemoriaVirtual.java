package memoria;

public class MemoriaVirtual {	
	public static PaginaVirtual[] memoriaVirtual;
	
	public MemoriaVirtual(int pTamanho){
		this.memoriaVirtual = new PaginaVirtual[pTamanho];
	}
	
	public PaginaVirtual getPagina(int pIndice){
		return memoriaVirtual[pIndice];
	}
	
	public int getTamanho(){
		return memoriaVirtual.length;
	}
	
	public void setMolduraPagina(int pIndice, int pMolduraPagina){
		this.memoriaVirtual.getPagina(pIndice).setMolduraPagina(pMolduraPagina);
	}
}