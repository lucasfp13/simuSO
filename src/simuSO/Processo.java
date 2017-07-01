package simuSO;

import memoria.PaginaVirtual;

public class Processo implements Runnable {
	
	private PaginaVirtual memoria;
	private int indice;
	private int valor;
	
	public Processo(PaginaVirtual pMemoria) {
		this.memoria = pMemoria;
		this.indice = pMemoria.getIndice();
	}
	
	@Override
	public void run() {
		while(true) {
			System.out.println("processo consumindo a posição " + this.indice + " da memoria");
		}
	}
	
	public void setValor(int pvalor) {
		this.valor = pvalor;
	}

}
