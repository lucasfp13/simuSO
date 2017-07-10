package log;

import memoria.*;

/*
 * Aqui é só um backend pra um front que tô pensando fazer
 */

public class Log implements Runnable { // Quando criar o front eu coloco a Runnable
	private MemoriaVirtual memVirtual = null;
    private MemoriaFisica memFisica = null;
    private MemoriaHD memoriaHD = null;

    public Log(MemoriaVirtual pMemoriaVirtual, MemoriaFisica pMemoriaFisica, MemoriaHD pMemoriaHD) {
		this.memFisica = pMemoriaFisica;
		this.memVirtual = pMemoriaVirtual;
		this.memoriaHD = pMemoriaHD;
	}
    
    public void run() { // No lugar desses prints colocar um return pra passar ao front os dados
    	while(true){
    		try {
    			Thread.sleep(6000);
    			System.out.println("**************************");
    			System.out.println("Valores memória virtual: ");
    	    	for (int i = 0; i < this.memVirtual.getTamanho(); i++) {
    	    		System.out.println(" - " + this.memVirtual.getPagina(i).getValor());
    	    	}
    	    	
    	    	System.out.println("**************************");
    	    	System.out.println("Valores memória física: ");
    	    	for (int i = 0; i < this.memFisica.getTamanho(); i++) {
    	    		System.out.println(" - " + this.memFisica.getValor(i));
    	    	}
    	    	
    	    	System.out.println("**************************");
    	    	System.out.println("Valores memória HD: ");
    	    	for (int i = 0; i < this.memoriaHD.getTamanho(); i++) {
    	    	System.out.println(" - " + this.memoriaHD.getValorHD(i));
    	    	}
    		} catch(InterruptedException e) {
    			System.out.println(e);
    		}
    	}
    }
}
