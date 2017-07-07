package simuSO;

import memoria.*;

import java.util.Random;
import clock.*;

public class MMU implements Memoria, IClockListener {
    private int tempoAtual = 0;
	private MemoriaVirtual memVirtual = null;
    private MemoriaFisica memFisica = null;
    private MemoriaHD memoriaHD = null;
    private static final int TEMPO_PAGINA = 1000;
    
    public MMU(int pTamanhoMemoriaFisica, int pTamanhoMemoriaVirtual, String pLocalMemoriaHD){
    	this.memFisica = new MemoriaFisica(pTamanhoMemoriaFisica);
    	this.memVirtual = new MemoriaVirtual(pTamanhoMemoriaVirtual);
    	this.memoriaHD = new MemoriaHD(pLocalMemoriaHD, pTamanhoMemoriaVirtual);
    }

    @Override
	public void escrever(int pIndiceVirtual, int idProcesso) { // Aplicar o algoritmo WS nesse método pra saber quem vai sair ou ser substituido
    	
		boolean testePresenca = this.memVirtual.getPagina(pIndiceVirtual).presente();
		
		if (testePresenca == false) {
			PaginaVirtual pagina = this.memVirtual.getPagina(pIndiceVirtual);
			Random r = new Random();
			Integer valor = r.nextInt(100);
            Integer indiceMemoriaFisica = this.getIndiceMemFisica(this.memFisica.getMemoria()); 
			
			if(indiceMemoriaFisica == null){
				System.out.println("NAO TEM MAIS ESPAÇO NA MEM FISICA");
				System.out.println("CHAMA O ALGORITMO WS");
				this.WS(this.memVirtual.getPagina(pIndiceVirtual));
				
			} else {
				pagina.setIndice(pIndiceVirtual);
				pagina.setMolduraPagina(indiceMemoriaFisica);
	            pagina.modificar(true);
	            pagina.referenciar(true);
	            pagina.presenca(true);
	            this.memFisica.setValor(indiceMemoriaFisica, valor);
	            System.out.println("Processo " + idProcesso + " escreveu valor " + valor + " no indice " + indiceMemoriaFisica + " da memoria fisica e"
	            		+ " escreveu " + indiceMemoriaFisica + " no indice " + pagina.getIndice() + " da memoria virtual!");
			}			
			
		} else {
			System.out.println("JA TEM PAGINA ENDEREÇADA NESSE INDICE DA MEMORIA VIRTUAL");
			return;
			//Integer indiceLivre = memFisica.getIndice();
            //System.out.println("Página livre : " + indiceLivre);
		}
	}

    @Override
	public void ler(int pIndiceVirtual, int IDProcesso) {
    	
    	try {
	    	if(this.memVirtual.getPagina(pIndiceVirtual).getValor() == null) {
	    		System.out.println("Falta de pagina!");
	    		System.out.println("#partiu buscar no HD...");
	    		
	    		Integer valorDoHD = 0;
	    		PaginaVirtual paginaNova = new PaginaVirtual();
	    		
	    		// Percorrer na memória física atrás de moldura de página livre
	    		for(int molduraPag = 0; molduraPag < this.memFisica.getMemoria().length; molduraPag++){
	    			
	    			// Se a moldura estiver livre
	    			if(this.memFisica.getMemoria()[molduraPag] == null){
	    				paginaNova.referenciar(true);
	    				paginaNova.modificar(false);
	    				paginaNova.presenca(true);
	    				paginaNova.setIndice(pIndiceVirtual);
	    				paginaNova.setMolduraPagina(molduraPag);
	    				// Pega valor gravado no HD e salva no valor da pagina virtual
	    				valorDoHD = this.memoriaHD.getValorHD(pIndiceVirtual);
	    				paginaNova.setValor(valorDoHD);
	    				
	    				// Escreve pagina na memoria virtual
	    				this.memVirtual.setPagina(paginaNova, pIndiceVirtual);
	    				
	    				// Escreve valor da pagina na memoria fisica
	    				this.memFisica.setValor(molduraPag, valorDoHD);
	    				
	    				
	    				//System.out.println("Pagina agora referenciada na memoria fisica!");
	    				System.out.println("Valor na memoria virtual: " + this.memVirtual.getPagina(pIndiceVirtual).getValor());
	    				System.out.println("Valor na memoria fisica: " + this.memFisica.getValor(molduraPag));	
	    				return;
	    			}
	    		}
	    		
	    		
			} else {
				boolean t = this.memVirtual.getPagina(pIndiceVirtual).presente();
				if (t) {
					Integer valorMoldPagina = this.memVirtual.getPagina(pIndiceVirtual).getMolduraPagina();
					System.out.println("Processo " + IDProcesso + " leu o valor ----> " + this.memFisica.getValor(valorMoldPagina));
				} else {
					System.out.println("AUSENCIA DE PAGINA NA MEMORIA FISICA!");
					System.out.println("-- CHAMOU WS --");
					this.WS(this.memVirtual.getPagina(pIndiceVirtual));
					//int valor = this.memoriaHD.getValorHD(pIndiceVirtual);		
					//System.out.println(valor);
					
				}
			}	
    	}catch (Exception e) {
			// TODO: handle exception
		}
	}   
    
    private void WS(PaginaVirtual pPaginaVirtual) {
    	int tempoAtualDaPagina = pPaginaVirtual.getTempoVirtualAtual(); // gravando o tempo atual da página verificada
    	for(int countPaginas = 0; countPaginas < this.memVirtual.getTamanho(); countPaginas++) {
    		int tempoPaginaLoop = this.memVirtual.getPagina(countPaginas).getTempoVirtualAtual();
    		System.out.println("tempo pagina atual -> " + tempoAtualDaPagina + "\ntempo pagina loop -> " + tempoPaginaLoop);
    	}
    }
    
    public Integer getIndiceMemFisica(Integer[] memFisica) {
		for(int i = 0; i < memFisica.length; i++){
			if(memFisica[i] == null){
				return i;
			}
		}
		return null;
	}
    
    // Para zerar os bits de referenciado das páginas virtuais
    public void eventoZerarRecebido(int tempoClock){
    	for(int i = 0; i < this.memVirtual.getTamanho(); i++){
    		this.memVirtual.getPagina(i).referenciar(false);
    	}
    	this.tempoAtual = tempoClock;
    }    
}