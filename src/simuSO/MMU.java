package simuSO;

import memoria.*;

import java.util.Random;
import clock.*;

public class MMU implements Memoria, IClockListener {
    private int tempoAtual = 0;
	private MemoriaVirtual memVirtual = null;
    private MemoriaFisica memFisica = null;
    private MemoriaHD memoriaHD = null;
    //private static final int TEMPO_PAGINA = 100;
    
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
				this.WS();
				
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
					this.WS();
					//int valor = this.memoriaHD.getValorHD(pIndiceVirtual);		
					//System.out.println(valor);
					
				}
			}	
    	}catch (Exception e) {
			// TODO: handle exception
		}
	}   
    
    private void WS() {
    	int t = 50;
    	boolean marcada = false;	// marca pagina candidata a ser retirada da memFisica
    	int idadePaginaAtual = 0;
    	int valorPagina = 0;
    	// Variaveis temporarias para comparação de pagina mais antiga escolhida a sair
    	int idadePaginaTemp = 0;
    	int iTemp = 0;
    	
    	for(int countPaginas = 0; countPaginas < this.memVirtual.getTamanho(); countPaginas++) {
    		if(this.memVirtual.getPagina(countPaginas).presente() == false){
    			continue;
    		}
    		
    		if(this.memVirtual.getPagina(countPaginas).referenciada() == true){
    			this.memVirtual.getPagina(countPaginas).setTempoVirtualAtual(this.tempoAtual);
    			idadePaginaAtual = (this.tempoAtual - this.memVirtual.getPagina(countPaginas).getTempoVirtualAtual());
    			idadePaginaTemp = (this.tempoAtual - this.memVirtual.getPagina(iTemp).getTempoVirtualAtual());
    			
    			if(idadePaginaAtual >= idadePaginaTemp){
    				iTemp = countPaginas;
    				marcada = true;
    				continue;
    			}
    		}
    		
    		if(this.memVirtual.getPagina(countPaginas).referenciada() == false && (this.tempoAtual - this.memVirtual.getPagina(countPaginas).getTempoVirtualAtual()) > t){
    			Integer moldura2Swap = this.memVirtual.getPagina(countPaginas).getMolduraPagina();
    			Integer valor = this.memFisica.getValor(moldura2Swap);
    			this.memoriaHD.swap(valor, countPaginas);
    			
    			// Limpar pagina removida da memoria fisica
    			this.memVirtual.getPagina(countPaginas).setMolduraPagina(null);
    			this.memVirtual.getPagina(countPaginas).setTempoVirtualAtual(0);
    			this.memVirtual.getPagina(countPaginas).setValor(null);
    			this.memVirtual.getPagina(countPaginas).presenca(false);
    			this.memVirtual.getPagina(countPaginas).referenciar(false);
    			this.memVirtual.getPagina(countPaginas).modificar(false);
    			break;
    		}
    		
    		if(this.memVirtual.getPagina(countPaginas).referenciada() == false && (this.tempoAtual - this.memVirtual.getPagina(countPaginas).getTempoVirtualAtual()) <= t){
    			idadePaginaAtual = (this.tempoAtual - this.memVirtual.getPagina(countPaginas).getTempoVirtualAtual());
    			idadePaginaTemp = (this.tempoAtual - this.memVirtual.getPagina(iTemp).getTempoVirtualAtual());
    			
    			if(idadePaginaAtual >= idadePaginaTemp){
    				iTemp = countPaginas;
    				marcada = true;
    				continue;
    			}
    		}
    		
    		if(marcada == true){
    			Integer moldura2Swap = this.memVirtual.getPagina(countPaginas).getMolduraPagina();
    			Integer valor = this.memFisica.getValor(moldura2Swap);
    			this.memoriaHD.swap(valor, countPaginas);
    			
    			// Limpar pagina removida da memoria fisica
    			this.memVirtual.getPagina(countPaginas).setMolduraPagina(null);
    			this.memVirtual.getPagina(countPaginas).setTempoVirtualAtual(0);
    			this.memVirtual.getPagina(countPaginas).setValor(null);
    			this.memVirtual.getPagina(countPaginas).presenca(false);
    			this.memVirtual.getPagina(countPaginas).referenciar(false);
    			this.memVirtual.getPagina(countPaginas).modificar(false);
    		}
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
    // Só é chamado no tick do Clock
    public void eventoZerarRecebido(int tempoClock){
    	for(int i = 0; i < this.memVirtual.getTamanho(); i++){
    		this.memVirtual.getPagina(i).referenciar(false);
    	}
    	this.tempoAtual = tempoClock;
    }    
}