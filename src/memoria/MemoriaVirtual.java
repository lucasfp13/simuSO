package memoria;

import java.util.ArrayList;

public class MemoriaVirtual {
	private int indice;
	private int tamanho;
	private ArrayList <PaginaVirtual> listaMemoriaVirtual;
	
	public MemoriaVirtual(int pTamanho) {
		this.listaMemoriaVirtual = new ArrayList<>();
		this.tamanho = pTamanho;
		this.alocar();
	}
	
	private void alocar() {
		for (int i = 0; i < this.tamanho; i++) {
			this.listaMemoriaVirtual.add(new PaginaVirtual(i));
		}
	}
	
	public PaginaVirtual getPagina(int pIndice) {
		return this.listaMemoriaVirtual.get(pIndice);
	}
	
	public int getTamanho() {
		return this.tamanho;
	}
	
	public void setIndice(int pIndice) {
		this.indice = pIndice;
	}
	
	public int getIndice() {
		return this.indice;
	}
	
}
