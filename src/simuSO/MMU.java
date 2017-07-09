package simuSO;

import memoria.*;

import java.util.Random;
import clock.*;

public class MMU implements Memoria, IClockListener {
    private int tempoAtual = 0;
	private MemoriaVirtual memVirtual = null;
    private MemoriaFisica memFisica = null;
    private MemoriaHD memoriaHD = null;
    
    public MMU(int pTamanhoMemoriaFisica, int pTamanhoMemoriaVirtual, MemoriaVirtual pMemoriaVirtual, MemoriaFisica pMemoriaFisica, MemoriaHD pMemoriaHD){
    	this.memFisica = pMemoriaFisica;
    	this.memVirtual = pMemoriaVirtual;
    	this.memoriaHD = pMemoriaHD;
    }

    @Override
	public void escrever(int pIndiceVirtual, int idProcesso) {
    	System.out.println("PROCESSO " + idProcesso + " ESCREVENDO NO INDICE " + pIndiceVirtual);
		boolean testePresenca = this.memVirtual.getPagina(pIndiceVirtual).presente();
		
		if (testePresenca == false) {
			System.out.println("PAGE FAULT ESCRITA");
			PaginaVirtual pagina = this.memVirtual.getPagina(pIndiceVirtual);
			Random r = new Random();
			Integer novoValor = r.nextInt(100);
            Integer indiceLivre = this.memFisica.getIndiceLivre(this.memFisica.getMemoria()); 
			
			if(indiceLivre == null){
				System.out.println("NAO TEM MAIS ESPAÇO NA MEM FISICA");
				System.out.println("CHAMA O ALGORITMO WS");
				
				if(this.memVirtual.getPagina(pIndiceVirtual).modificada() == true){
					Integer moldPagina = this.memVirtual.getPagina(pIndiceVirtual).getMolduraPagina();
					Integer valorModificado = this.memFisica.getValor(moldPagina);
					this.memoriaHD.swap(valorModificado, pIndiceVirtual);
				} else {
					this.WS();
				}	
				
			} else {
				pagina.setIndice(pIndiceVirtual);
				pagina.setMolduraPagina(indiceLivre);
	            pagina.modificar(false);
	            pagina.referenciar(true);
	            pagina.presenca(true);
	            this.memFisica.setValor(indiceLivre, novoValor);
			}			
			
		} else {
			System.out.println("Substituição de valores");
			
			Random r = new Random();		
			Integer novoValor = r.nextInt(100);			// Gerando um valor aleatório, já que não importa para o nosso caso
			Integer molduraPagina = this.memVirtual.getPagina(pIndiceVirtual).getMolduraPagina();
			this.memVirtual.getPagina(pIndiceVirtual).setValor(novoValor);
			this.memVirtual.getPagina(pIndiceVirtual).modificar(true);
			this.memFisica.setValor(molduraPagina, novoValor);
		}
	}

    @Override
	public void ler(int pIndiceVirtual, int IDProcesso) {
    	System.out.println("PROCESSO " + IDProcesso + " LENDO NO INDICE " + pIndiceVirtual);
    	try {
	    	if(this.memVirtual.getPagina(pIndiceVirtual).getValor() == null) {
	    		System.out.println("Falta de pagina!");
	    		PaginaVirtual paginaNova = new PaginaVirtual();
	 
	    		Integer indiceMF = this.memFisica.getIndiceLivre(this.memFisica.getMemoria());
	    		// Se a moldura estiver livre
	    		if(indiceMF != null){
	    			System.out.println("#partiu buscar no HD...");
	    			paginaNova.referenciar(true);
	    			paginaNova.modificar(false);
	    			paginaNova.presenca(true);
	    			paginaNova.setIndice(pIndiceVirtual);
	    			paginaNova.setMolduraPagina(indiceMF);
	    			// Pega valor gravado no HD e salva no valor da pagina virtual
	    			Integer valorDoHD = this.memoriaHD.getValorHD(pIndiceVirtual);
	    			paginaNova.setValor(valorDoHD);
	    			
	    			// Escreve pagina na memoria virtual
	    			this.memVirtual.setPagina(paginaNova, pIndiceVirtual);
	    			
	    			// Escreve valor da pagina na memoria fisica
	    			this.memFisica.setValor(indiceMF, valorDoHD);
	    			
	    			//System.out.println("Pagina agora referenciada na memoria fisica!");
	    			System.out.println("Valor na memoria virtual: " + this.memVirtual.getPagina(pIndiceVirtual).getValor());
	    			System.out.println("Valor na memoria fisica: " + this.memFisica.getValor(indiceMF));	
	    			return;
	    		}
	    		
			} else {
				boolean t = this.memVirtual.getPagina(pIndiceVirtual).presente();
				if (t) {
					Integer valorMoldPagina = this.memVirtual.getPagina(pIndiceVirtual).getMolduraPagina();
					System.out.println("Processo " + IDProcesso + " leu o valor ----> " + this.memFisica.getValor(valorMoldPagina));
				} else {
					System.out.println("AUSENCIA DE PAGINA NA MEMORIA FISICA!");
					
					if(this.memVirtual.getPagina(pIndiceVirtual).modificada() == true){
						Integer moldPagina = this.memVirtual.getPagina(pIndiceVirtual).getMolduraPagina();
						Integer valorModificado = this.memFisica.getValor(moldPagina);
						this.memoriaHD.swap(valorModificado, pIndiceVirtual);
					} else {
						this.WS();
					}		
				}
			}	
    	} catch (Exception e) {
			// TODO: handle exception
		}
	}   
    
    private void WS() {
    	int t = 500;
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
    			this.memVirtual.getPagina(countPaginas).descartarPagina();
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
    			this.memVirtual.getPagina(countPaginas).descartarPagina();
    		}
    	}
    }    
    // Para zerar os bits de referenciado das páginas virtuais
    // Tick do clock!
    public void eventoZerarRecebido(int tempoClock){
    	System.out.println("ANTES DE ZERAR " + this.tempoAtual);
    	for(int i = 0; i < this.memVirtual.getTamanho(); i++){
    		this.memVirtual.getPagina(i).referenciar(false);
    	}
    	this.tempoAtual = tempoClock;
    	
    }    
}