package simuSO;

import memoria.*;

import java.util.Random;
import clock.*;

public class MMU implements Memoria, IClockListener {
    private int tempoAtual = 0;
	private MemoriaVirtual memVirtual = null;
    private MemoriaFisica memFisica = null;
    private MemoriaHD memoriaHD = null;
    
    public MMU(int pTamanhoMemoriaFisica, int pTamanhoMemoriaVirtual, String pLocalMemoriaHD){
    	this.memFisica = new MemoriaFisica(pTamanhoMemoriaFisica);
    	this.memVirtual = new MemoriaVirtual(pTamanhoMemoriaVirtual);
    	this.memoriaHD = new MemoriaHD(pLocalMemoriaHD, pTamanhoMemoriaVirtual);
    }

    @Override
	public void escrever(int pIndiceVirtual, int idProcesso) { // Aplicar o algoritmo WS nesse método pra saber quem vai sair ou ser substituido
    	
		boolean testePresenca = this.memVirtual.getPagina(pIndiceVirtual).presente();
		
		if (testePresenca == false) {
			PaginaVirtual pagina = memVirtual.getPagina(pIndiceVirtual);
			Random r = new Random();
			Integer valor = r.nextInt(100);
            Integer indiceMemoriaFisica = this.getIndiceMemFisica(this.memFisica.getMemoria()); 
			
			if(indiceMemoriaFisica == null){
				System.out.println("NAO TEM MAIS ESPAÇO NA MEM FISICA");
				System.out.println("CHAMA O ALGORITMO WS");
				
			} else {
				pagina.setMolduraPagina(pIndiceVirtual, indiceMemoriaFisica);
	            pagina.modificar(true);
	            pagina.referenciar(true);
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
	public void ler(int pIndice, int idProcesso) {
    	try {
	    	if(this.memVirtual.getPagina(pIndice) == null) {
	    		System.out.println("Pagina nula");
	    		return;
			} else {
				boolean t = this.memVirtual.getPagina(pIndice).presente();
				int valorPaginaFisica = this.memFisica.getValor(pIndice);
				if (t) {
					System.out.println("Processo " + idProcesso + " leu o valor ----> " + valorPaginaFisica);
				}
				else {
					System.out.println("NÃO TÁ NA MEMÓRIA FÍSICA");
				}
			}	
    	}catch (Exception e) {
			// TODO: handle exception
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
    	for(int i = 0; i < memVirtual.getTamanho(); i++){
    		memVirtual.getPagina(i).referenciar(false);
    	}
    	
    	this.tempoAtual = tempoClock;
    }    
}