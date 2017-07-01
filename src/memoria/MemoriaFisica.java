package memoria;

import java.util.ArrayList;

public class MemoriaFisica {
	//private boolean checkMemoria;
	private int tamanho;
	private ArrayList <PaginaFisica> listaMemoriaFisica;
	
	public MemoriaFisica(int pTamanho) {
		this.tamanho = pTamanho;
		this.listaMemoriaFisica = new ArrayList<PaginaFisica>();
		this.alocar();
	}
	
	private void alocar() {
		for (int i = 0; i < this.tamanho; i++) {
			this.listaMemoriaFisica.add(new PaginaFisica(i));
		}
	}
	
	public int getPaginaLivre() {
        for (int i = 0; i < this.tamanho; i++) {
            if (listaMemoriaFisica.get(i).getIndice() != -1) {
                listaMemoriaFisica.get(i).getIndice();
            }
        }
        return -1;
    }
	
	public PaginaFisica getPagina(int pIndice){
        return listaMemoriaFisica.get(pIndice);
    }
	
	public int getValor(int pIndice) {
		if (listaMemoriaFisica.get(pIndice) != null) {
			return listaMemoriaFisica.get(pIndice).getValor();
		}
		return -1;
	}
	
	public void setValor(int pIndice, int pValor) {
		if (listaMemoriaFisica.get(pIndice) != null) {
			listaMemoriaFisica.get(pIndice).setValor(pValor);
		}
	}
	
	public int getTamanho() {
		return this.tamanho;
	}
	
}
