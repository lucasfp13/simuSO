package memoria;

public class PaginaFisica {
	private int valor;
	private int indice;

	PaginaFisica(int pIndice) {
		setValor(0);
		setIndice(pIndice);
	}
	
    public int getValor() {
        return this.valor;
    }

    public void setValor(int pValor) {
        this.valor = pValor;
    }
    
    public void setIndice(int pIndice) {
    	this.indice = pIndice;
    }
    
    public int getIndice() {
    	return this.indice;
    }
    
}
