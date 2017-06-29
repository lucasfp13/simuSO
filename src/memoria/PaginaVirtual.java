package memoria;

public class PaginaVirtual {
	
	private boolean referencia_;
	private boolean modificada_;
	private boolean presente_;
	private int moldura_pagina;
	
	public PaginaVirtual() {
		
	}
	
	PaginaVirtual(boolean pBitR, boolean pBitM, boolean pBitP, int pMolduraPagina) {
		this.referencia_ = pBitR;
		this.modificada_ = pBitM;
		this.presente_ = pBitP;
		this.moldura_pagina = pMolduraPagina;
	}
	
	public void referenciar(boolean bit) {
		this.referencia_ = bit;
	}
	
	public boolean referenciada() {
		return this.referencia_;
	}
	
	public void modificar(boolean bit) {
		this.modificada_ = bit;
	}
	
	public boolean modificada() {
		return this.modificada_;
	}
	
	public void presenca(boolean bit) {
		this.presente_ = bit;
	}
	
	public boolean presente() {
		return this.presente_;
	}
	
	public void setMolduraPagina(int pMolduraPagina) {
		this.moldura_pagina = pMolduraPagina;
	}
	
	public int getMolduraPagina() {
		return this.moldura_pagina;
	}
}
