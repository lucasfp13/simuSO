package simuSO;

import memoria.*;

public class MMU implements Memoria {
    private MemoriaVirtual memVirtual;
    private MemoriaFisica memFisica;
    
    public MMU(int pTamanhoMemoriaFisica, int pTamanhoMemoriaVirtual){
    	this.memFisica = new MemoriaFisica(pTamanhoMemoriaFisica);
    	this.memVirtual = new MemoriaVirtual(pTamanhoMemoriaVirtual);
    }
    
    public boolean executarInstrucao(char pTipo, int pIndice) {

        if (pIndice > this.memVirtual.getTamanho()) { // retorna false pois o indice excedeu o tamanho da memória virtual
        	return false;
        }
        
        if (pTipo == 'R') {
        	this.ler(pIndice);
        }
        
        if (pTipo == 'W') {
        	this.escrever(pIndice);
        }
        
        return true;
      
    }

    @Override
	public void escrever(int pIndice) { // Aplicar o algoritmo WS nesse método pra saber quem vai sair ou ser substituido
		boolean testePresenca = this.memVirtual.getPagina(pIndice).presente();
		
		if (testePresenca == true) {
			PaginaVirtual pagina = memVirtual.getPagina(pIndice);
            int valor = 123456;
            memFisica.setValor(pIndice, valor);
            pagina.modificar(true);
            pagina.referenciar(true);
            System.out.println("Escrevendo valor " + valor + " no indice " + pagina.getIndice() + " da memoria fisica!");
		} else {
			System.out.println("não tá na memoria fisica");
			int indiceLivre = memFisica.getIndice();
            System.out.println("Página livre : " + indiceLivre);
		}
	}

    @Override
	public void ler(int pIndice) {
		boolean t = this.memVirtual.getPagina(pIndice).presente();
		int pgFisica = this.memFisica.getValor(pIndice);
		
		if (t) {
			System.out.println("tá na memoria fisica\nvalor -> " + pgFisica);
		}
		else {
			System.out.println("não tá na memoria fisica");
		}
		
	}   
}