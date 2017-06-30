package memoria;

public class PaginaVirtual {
	
	private boolean referencia;
	private boolean modificada;
	private boolean presente;
	private int indice;
	private int contador;
	
	public PaginaVirtual() {
		referenciar(false);
		modificar(false);
		presenca(false);
		setContador(0);
		setIndice(-1);
	}
	
	public void atualizar(boolean pReferenciada, boolean pModificada, boolean pPresente, int pIndice) {
		referenciar(pReferenciada);
		modificar(pModificada);
		presenca(pPresente);
		setContador(0);
		setIndice(pIndice);
	}
	
	public void referenciar(boolean bit) {
		this.referencia = bit;
	}
	
	public boolean referenciada() {
		return this.referencia;
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
	
	public void setContador(int pContador) {
		this.contador = pContador;
	}
	
	public int getContador() {
		return this.contador;
	}
	
}
