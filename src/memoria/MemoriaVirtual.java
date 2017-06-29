package memoria;

import java.util.ArrayList;

public class MemoriaVirtual {
	private int tamanho;
	private ArrayList <PaginaVirtual> listaMemoriaVirtual;
	
	public MemoriaVirtual(int pTamanho) {
		this.listaMemoriaVirtual = new ArrayList<>();
		this.tamanho = pTamanho;
	}
	
	public void alocar() {
		for (int i = 0; i < this.tamanho; i++) {
			this.listaMemoriaVirtual.add(new PaginaVirtual());
		}
	}
	
	public PaginaVirtual getPagina(int pIndice) {
		return this.listaMemoriaVirtual.get(pIndice);
	}
	
	public int getTamanho() {
		return this.tamanho;
	}
	
}
