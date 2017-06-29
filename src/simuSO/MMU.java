package simuSO;
import memoria.*;

public class MMU {
    private MemoriaFisica memoriaFisica;
    private MemoriaVirtual memoriaVirtual;
    
    public MMU(int pTamanhoMemoriaFisica){
    	this.memoriaFisica = new MemoriaFisica(pTamanhoMemoriaFisica);
        this.memoriaVirtual = new MemoriaVirtual(pTamanhoMemoriaFisica * 2);
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
		boolean t = this.memoriaVirtual.getPagina(pIndice).presente();
		
		if (t) {
			System.out.println("tá na memoria fisica");
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
