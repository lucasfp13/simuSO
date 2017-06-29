package simuSO;
import memoria.*;

public class MMU {
    private MemoriaFisica memoriaFisica;
    private MemoriaVirtual memoriaVirtual;
    
    public MMU(int pTamanhoMemoria) {
    	int tamanhoMemoriaFisica = pTamanhoMemoria;
    	int tamanhoMemoriaVirtual = tamanhoMemoriaFisica * 2;
    	this.memoriaFisica = new MemoriaFisica(tamanhoMemoriaFisica);
        this.memoriaVirtual = new MemoriaVirtual(tamanhoMemoriaVirtual);
    }
    
    public PaginaVirtual getPagina(int pIndice) {
    	return this.memoriaVirtual.getPagina(pIndice);
    }
    
    public boolean executarInstrucao(char pTipo, int pIndice) {

        if (pIndice > this.memoriaVirtual.getTamanho()) {
                return false;
        }
        
        if (pTipo == 'R') {
        	this.leia(pIndice);
        }
        
        if (pTipo == 'W') {
        	this.escreva(pIndice);
        }
        
        return true;
      
    }

    public void referenciarMemoriaVirtual(int pIndice){
        this.memoriaVirtual.getPagina(pIndice).setIndice(pIndice);
    }

	private void escreva(int pIndice) {
		boolean testePresenca = this.memoriaVirtual.getPagina(pIndice).presente();
		if (testePresenca) {
			PaginaVirtual pagina = memoriaVirtual.getPagina(pIndice);
            int valor = 123456;
            memoriaFisica.setValor(pIndice, valor);
            pagina.modificar(true);
            pagina.referenciar(true);
            System.out.println("Escrevendo valor " + valor + " no indice " + pagina.getIndice() + " da memoria fisica!");
		}
		else {
			System.out.println("não tá na memoria fisica");
		}
		
	}

	private void leia(int pIndice) {
		boolean t = this.memoriaVirtual.getPagina(pIndice).presente();
		
		if (t) {
			System.out.println("tá na memoria fisica");
		}
		else {
			System.out.println("não tá na memoria fisica");
		}
		
	}
	
	private void trocaPagona() {
		
	}

    
}
