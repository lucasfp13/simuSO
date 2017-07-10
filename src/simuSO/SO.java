package simuSO;

import clock.Clock;
import log.Log;
import entrada.*;
import memoria.MemoriaFisica;
import memoria.MemoriaHD;
import memoria.MemoriaVirtual;

public class SO {
	
	public static final Clock CLOCK = new Clock(1000);
	public static final int TAMANHO_MEMORIA_FISICA = 2;
	public static final int TAMANHO_MEMORIA_VIRTUAL = 4;
	public static final String LOCAL_HD = "MEMORIA_HD.txt";
	public static MemoriaFisica memFisica = new MemoriaFisica(TAMANHO_MEMORIA_FISICA);
	public static MemoriaVirtual memVirtual = new MemoriaVirtual(TAMANHO_MEMORIA_VIRTUAL);
	public static MemoriaHD memoriaHD = new MemoriaHD(LOCAL_HD, TAMANHO_MEMORIA_VIRTUAL);
	public static final MMU mmu = new MMU(TAMANHO_MEMORIA_FISICA, TAMANHO_MEMORIA_VIRTUAL, memVirtual, memFisica, memoriaHD);
	public static final Log LOG = new Log(memVirtual, memFisica, memoriaHD);
	//public static final FabricaDeEntradas fab1 = new FabricaDeEntradas(TAMANHO_MEMORIA_VIRTUAL);
	//public static final FabricaDeEntradas fab2 = new FabricaDeEntradas(TAMANHO_MEMORIA_VIRTUAL);
	
	public static void main(String[] args) {
		//String inputProcesso1 = fab1.getNewEntrada();
		//System.out.println("fab1 = " + inputProcesso1);
	//	String inputProcesso2 = fab2.getNewEntrada(); 
	//	System.out.println("fab2 = " + inputProcesso2);
		
		String input1 = "2-R,3-R,0-W,1-W";
		
		CLOCK.adicionaListener(mmu);
		
		Processo processo1 = new Processo(mmu, input1, 1);
	//	Processo processo2 = new Processo(mmu, inputProcesso2, 2);
		
		Thread clock = new Thread(CLOCK);
		clock.start();
		
		Thread p1 = new Thread(processo1);
	//	Thread p2 = new Thread(processo2);
				
		p1.start();
		
		Thread log = new Thread(LOG);
		log.start();
	//	p2.start();
	}
}