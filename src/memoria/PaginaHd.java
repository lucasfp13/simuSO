package memoria;

public class PaginaHd {
	private int valor;
	private int indice;

    public PaginaHd(int pValor) {
    	setValor(pValor);
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
    
}
