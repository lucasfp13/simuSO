package memoria;

public class PaginaVirtual {
	private boolean referenciada;
	private boolean modificada;
	private boolean presente;
	private Integer molduraPagina;
	private int indice;
	private int tempoVirtualAtual;
	
	public PaginaVirtual() {
		referenciar(false);
		modificar(false);
		presenca(false);
		this.molduraPagina = null;
		setTempoVirtualAtual(0);
		setIndice(-1);
	}
	
	public void atualizar(boolean pReferenciada, boolean pModificada, boolean pPresente, int pIndice) {
		referenciar(pReferenciada);
		modificar(pModificada);
		presenca(pPresente);
		setTempoVirtualAtual(0);
		setIndice(pIndice);
	}
	
	public void referenciar(boolean bit) {
		this.referenciada = bit;
	}
	
	public boolean referenciada() {
		return this.referenciada;
	}
	
	public void modificar(boolean bit) {
		this.modificada = bit;
	}
	
	public boolean modificada() {
		return this.modificada;
	}
	
	public void presenca(boolean bit) {
		this.presente = bit;
	}
	
	public boolean presente() {
		return this.presente;
	}
	
	public void setIndice(int pIndice) {
	    this.indice = pIndice;
	}
	
	public int getIndice() {
		return this.indice;
	}
	
	public void setTempoVirtualAtual(int pTempo) {
		this.tempoVirtualAtual = pTempo;
	}
	
	public void setMolduraPagina(int pIndice, Integer pMolduraPagina){
		this.indice = pIndice;
		this.molduraPagina = pMolduraPagina;
	}
	
	public int getTempoVirtualAtual() {
		return this.tempoVirtualAtual;
	}	
}