package simuSO;

import memoria.MemoriaFisica;
import memoria.MemoriaHD;
import memoria.MemoriaVirtual;

/*
 * Aqui é só um backend pra um front que tô pensando fazer
 */

public class Log { // Quando criar o front eu coloco a Runnable
	private MemoriaVirtual memVirtual = null;
    private MemoriaFisica memFisica = null;
    private MemoriaHD memoriaHD = null;

    public Log(MemoriaVirtual pMemoriaVirtual, MemoriaFisica pMemoriaFisica, MemoriaHD pMemoriaHD) {
		this.memFisica = pMemoriaFisica;
		this.memVirtual = pMemoriaVirtual;
		this.memoriaHD = pMemoriaHD;
	}
    
    public void mostrarMemorias() { // No lugar desses prints colocar um return pra passar ao front os dados
    	System.out.println("Valores memória virtual: ");
    	for (int i = 0; i < memVirtual.getTamanho(); i++) {
    		System.out.print(" - " + memVirtual.getPagina(i).getValor());
    	}
    	
    	System.out.println("Valores memória física: ");
    	for (int i = 0; i < memFisica.getTamanho(); i++) {
    		System.out.print(" - " + memFisica.getValor(i));
    	}
    	
    	System.out.println("Valores memória HD: ");
    	for (int i = 0; i < memoriaHD.getTamanho(); i++) {
    		System.out.print(" - " + memoriaHD.getValorHD(i));
    	}
    	
    }
	
}
