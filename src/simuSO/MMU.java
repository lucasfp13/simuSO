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
    	
    	if (this.memVirtual.getPagina(pIndiceVirtual).presente() == false) {
    		Integer indiceLivre1 = this.memFisica.getIndiceLivre(this.memFisica.getMemoria());
			if(indiceLivre1 == null){				
				if(this.memVirtual.getPagina(pIndiceVirtual).modificada() == true){
					Integer moldPagina = this.memVirtual.getPagina(pIndiceVirtual).getMolduraPagina();
					Integer valorModificado = this.memFisica.getValor(moldPagina);
					this.memoriaHD.swap(valorModificado, moldPagina);
					this.memVirtual.getPagina(pIndiceVirtual).descartarPagina();
					this.WS();
				} else {
					this.WS();
					
					Integer indiceLivre2 = this.memFisica.getIndiceLivre(this.memFisica.getMemoria());
					this.memVirtual.getPagina(pIndiceVirtual).presenca(true);
	    			this.memVirtual.getPagina(pIndiceVirtual).referenciar(true);
	    			this.memVirtual.getPagina(pIndiceVirtual).modificar(false);
	    			this.memVirtual.getPagina(pIndiceVirtual).setMolduraPagina(indiceLivre2);
	    			this.memVirtual.getPagina(pIndiceVirtual).setTempoVirtualAtual(this.tempoAtual);
	    			Integer valorDoHD = this.memoriaHD.getValorHD(pIndiceVirtual);
	    			this.memFisica.setValor(indiceLivre2, valorDoHD);
	    			
				}	
			} else {
				this.memVirtual.getPagina(pIndiceVirtual).referenciar(true);
    			this.memVirtual.getPagina(pIndiceVirtual).modificar(false);
    			this.memVirtual.getPagina(pIndiceVirtual).presenca(true);
    			this.memVirtual.getPagina(pIndiceVirtual).setIndice(pIndiceVirtual);
    			this.memVirtual.getPagina(pIndiceVirtual).setMolduraPagina(indiceLivre1);
    			this.memVirtual.getPagina(pIndiceVirtual).setTempoVirtualAtual(tempoAtual);
    			Integer valorDoHD = this.memoriaHD.getValorHD(pIndiceVirtual);
    			this.memFisica.setValor(indiceLivre1, valorDoHD);	
    			return;
			}			
			
		} else {			
			Random r = new Random();		
			Integer novoValor = r.nextInt(100);			// Gerando um valor aleatório, já que não importa para o nosso caso
			Integer molduraPagina = this.memVirtual.getPagina(pIndiceVirtual).getMolduraPagina();
			//this.memVirtual.getPagina(pIndiceVirtual).setValor(novoValor);
			this.memVirtual.getPagina(pIndiceVirtual).referenciar(true);
			this.memVirtual.getPagina(pIndiceVirtual).modificar(true);
			this.memFisica.setValor(molduraPagina, novoValor);
			this.memVirtual.getPagina(pIndiceVirtual).setTempoVirtualAtual(this.tempoAtual);
		}
	}

    @Override
	public void ler(int pIndiceVirtual, int IDProcesso) {
    	//System.out.println("PROCESSO " + IDProcesso + " LENDO NO INDICE " + pIndiceVirtual);
    	try {
    		if(this.memVirtual.getPagina(pIndiceVirtual).presente() == false){   		
	    		Integer indiceMF = this.memFisica.getIndiceLivre(this.memFisica.getMemoria());
	    		// Se a moldura estiver livre
	    		if(indiceMF != null){
	    			this.memVirtual.getPagina(pIndiceVirtual).presenca(true);
	    			this.memVirtual.getPagina(pIndiceVirtual).referenciar(true);
	    			this.memVirtual.getPagina(pIndiceVirtual).modificar(false);
	    			this.memVirtual.getPagina(pIndiceVirtual).setMolduraPagina(indiceMF);
	    			this.memVirtual.getPagina(pIndiceVirtual).setTempoVirtualAtual(this.tempoAtual);
	    			Integer valorDoHD = this.memoriaHD.getValorHD(pIndiceVirtual);
	    			this.memFisica.setValor(indiceMF, valorDoHD);
	    		} else {
	    		
	    			if(this.memVirtual.getPagina(pIndiceVirtual).modificada() == true){
						Integer moldPagina = this.memVirtual.getPagina(pIndiceVirtual).getMolduraPagina();
						Integer valorModificado = this.memFisica.getValor(moldPagina);
						this.memoriaHD.swap(valorModificado, moldPagina);
						//this.memVirtual.getPagina(pIndiceVirtual).modificar(false);
						this.WS();
					} else {
						this.WS();
						this.memVirtual.getPagina(pIndiceVirtual).presenca(true);
		    			this.memVirtual.getPagina(pIndiceVirtual).referenciar(true);
		    			this.memVirtual.getPagina(pIndiceVirtual).modificar(false);
		    			this.memVirtual.getPagina(pIndiceVirtual).setMolduraPagina(indiceMF);
		    			this.memVirtual.getPagina(pIndiceVirtual).setTempoVirtualAtual(this.tempoAtual);
		    			Integer valorDoHD = this.memoriaHD.getValorHD(pIndiceVirtual);
		    			this.memFisica.setValor(indiceMF, valorDoHD);
					}
	    		}
	    		
			} else {
				if(this.memVirtual.getPagina(pIndiceVirtual).modificada() == true){
					Integer moldPagina = this.memVirtual.getPagina(pIndiceVirtual).getMolduraPagina();
					Integer valorModificado = this.memFisica.getValor(moldPagina);
					this.memoriaHD.swap(valorModificado, moldPagina);
					//this.memVirtual.getPagina(pIndiceVirtual).modificar(false);
					this.WS();
				}
				
			}	
    	} catch (Exception e) {
			// TODO: handle exception
		}
	}   
    
    private void WS() {
    	int t = 10;
    	boolean marcada = false;	// marca pagina candidata a ser retirada da memFisica
    	int idadePaginaAtual = 0;
    	// Variaveis temporarias para comparação de pagina mais antiga escolhida a sair
    	int idadePaginaTemp = 0;
    	int iTemp = 0;
    	
    	for(int countPaginas = 0; countPaginas < this.memVirtual.getTamanho(); countPaginas++) {
    		if(this.memVirtual.getPagina(countPaginas).presente() == false){
    			System.out.println("AAAAAAAAA");
    			continue;
    		}
    		
    		if(this.memVirtual.getPagina(countPaginas).referenciada() == true){
    			System.out.println("BBBBBBBB");
    			this.memVirtual.getPagina(countPaginas).setTempoVirtualAtual(this.tempoAtual);
    			idadePaginaAtual = (this.tempoAtual - this.memVirtual.getPagina(countPaginas).getTempoVirtualAtual());
    			idadePaginaTemp = (this.tempoAtual - this.memVirtual.getPagina(iTemp).getTempoVirtualAtual());
    			
    			System.out.println(this.tempoAtual);
    			//System.out.println(i);
    			if(idadePaginaAtual >= idadePaginaTemp){
    				iTemp = countPaginas;
    				marcada = true;
    				continue;
    			}
    		}
    		
    		if(this.memVirtual.getPagina(countPaginas).referenciada() == false && (this.tempoAtual - this.memVirtual.getPagina(countPaginas).getTempoVirtualAtual()) > t){
    			System.out.println("CCCCCC");
    			Integer moldura2Swap = this.memVirtual.getPagina(countPaginas).getMolduraPagina();
    			System.out.println(moldura2Swap);
    			Integer valor = this.memFisica.getValor(moldura2Swap);
   
    			this.memoriaHD.swap(valor, countPaginas);
      			// Limpar pagina removida da memoria fisica
    			this.memFisica.setValor(moldura2Swap, null);
    			marcada = true;
    			break;
    		}
    		
    		if(this.memVirtual.getPagina(countPaginas).referenciada() == false && (this.tempoAtual - this.memVirtual.getPagina(countPaginas).getTempoVirtualAtual()) <= t){
    			System.out.println("DDDDDDDDD");
    			idadePaginaAtual = (this.tempoAtual - this.memVirtual.getPagina(countPaginas).getTempoVirtualAtual());
    			idadePaginaTemp = (this.tempoAtual - this.memVirtual.getPagina(iTemp).getTempoVirtualAtual());
    			
    			if(idadePaginaAtual >= idadePaginaTemp){
    				iTemp = countPaginas;
    				marcada = true;
    				continue;
    			}
    		}
    		
    		if(marcada == true){
    			System.out.println("UKULELE");
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
    	this.tempoAtual = tempoClock;
    	for(int i = 0; i < this.memVirtual.getTamanho(); i++){
    		this.memVirtual.getPagina(i).referenciar(false);
    	}
    }    
}