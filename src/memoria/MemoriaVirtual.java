package memoria;

import java.util.ArrayList;

public class MemoriaVirtual implements Memoria {
	private int indice;
	private int tamanho;
	private ArrayList <PaginaVirtual> listaMemoriaVirtual;
	
	public MemoriaVirtual(int pTamanho) {
		this.listaMemoriaVirtual = new ArrayList<>();
		this.tamanho = pTamanho;
		this.alocar();
	}
	
	public PaginaVirtual getPagina(int pIndice) {
		return this.listaMemoriaVirtual.get(pIndice);
	}
	
	public int getTamanho() {
		return this.tamanho;
	}
	
	public int getIndice() {
		return this.indice;
	}

	@Override
	public Memoria getpagina() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void setValor() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void getValor() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void setIndice(int pIndice) {
		this.indice = pIndice;
	}

	@Override
	public void alocar() {
		for (int i = 0; i < this.tamanho; i++) {
			this.listaMemoriaVirtual.add(new PaginaVirtual(i));
		}
	}
	
}
