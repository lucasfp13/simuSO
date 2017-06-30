package simuSO;
import memoria.*;

public class MMU {
    private MemoriaFisica memoriaFisica;
    private MemoriaVirtual memoriaVirtual;
    private MemoriaHd memoriaHd;
    private static String localHd = ".\\memoria\\memoriaHd.txt";
    private int indiceInstrucao;
    
    public MMU(int pTamanhoMemoria) {
    	int tamanhoMemoriaFisica = pTamanhoMemoria;
    	int tamanhoMemoriaVirtual = tamanhoMemoriaFisica * 2; // Memoria virtual é o dobro da fisica
    	this.memoriaFisica = new MemoriaFisica(tamanhoMemoriaFisica);
        this.memoriaVirtual = new MemoriaVirtual(tamanhoMemoriaVirtual);
        this.memoriaHd = new MemoriaHd(localHd, pTamanhoMemoria);
    }
    
    public PaginaVirtual getPagina(int pIndice) {
    	return this.memoriaVirtual.getPagina(pIndice);
    }
    
    public boolean executarInstrucao(char pTipo, int pIndice) {

        if (pIndice > this.memoriaVirtual.getTamanho()) { // retorna false pois o indice excedeu o tamanho da memória virtual
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

    public void referenciarMemoriaVirtual(int pIndice){
        this.memoriaVirtual.getPagina(pIndice).setIndice(pIndice);
    }

	private void escrever(int pIndice) { // Aplicar o algoritmo WS nesse método pra saber quem vai sair ou ser substituido
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

	private void ler(int pIndice) {
		boolean t = this.memoriaVirtual.getPagina(pIndice).presente();
		
		if (t) {
			System.out.println("tá na memoria fisica");
		}
		else {
			System.out.println("não tá na memoria fisica");
		}
		
	}
	
	public void setValorHd(PaginaVirtual pPagina, int pIndice){

    }
	
	public void setValor(PaginaVirtual pPagina) { // vai ser usando quando fazer a troca de página
        int indiceMemoriaFisica = pPagina.getIndice();
        
        pPagina.presenca(false);
        this.memoriaVirtual.getPagina(this.indiceInstrucao).setIndice(indiceMemoriaFisica);
        this.memoriaVirtual.getPagina(this.indiceInstrucao).referenciar(true);
        this.memoriaVirtual.getPagina(this.indiceInstrucao).presenca(true);

        int valorHd = memoriaHd.getValorBloco(this.indiceInstrucao).getValor();
        
        this.memoriaFisica.setValor(this.indiceInstrucao, valorHd);
    }
    
}
