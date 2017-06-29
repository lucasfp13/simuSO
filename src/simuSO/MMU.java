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
        	this.leia();
        }
        
        if (pTipo == 'W') {
        	this.escreva();
        }
        
        return true;
      
    }

	private void escreva() {
		// putaria
		
	}

	private void leia() {
		// putaria
		
	}

    
}
