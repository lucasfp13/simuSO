package memoria;

public class PaginaVirtual {
	
	private boolean referencia;
	private boolean modificada;
	private boolean presente;
	private int moldura_pagina;
	private int indice;
	
	public PaginaVirtual() {
		
	}
	
	PaginaVirtual(boolean pBitR, boolean pBitM, boolean pBitP, int pMolduraPagina) {
		this.referencia = pBitR;
		this.modificada = pBitM;
		this.presente = pBitP;
		this.moldura_pagina = pMolduraPagina;
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
	
	public void setMolduraPagina(int pMolduraPagina) {
		this.moldura_pagina = pMolduraPagina;
	}
	
	public int getMolduraPagina() {
		return this.moldura_pagina;
	}
	
	public void setIndice(int pIndice) {
	    this.indice = pIndice;
	}
	
	public int getIndice() {
		return this.indice;
	}
	
}
