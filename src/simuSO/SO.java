package simuSO;

import entrada.*;

public class SO {
	
	public static final Clock CLOCK = new Clock();
	public static final int TAMANHO_MEMORIA_FISICA = 8;
	public static final int TAMANHO_MEMORIA_VIRTUAL = 16;
	public static final MMU mmu = new MMU(TAMANHO_MEMORIA_FISICA, TAMANHO_MEMORIA_VIRTUAL);
	public static final FabricaDeEntradas fab1 = new FabricaDeEntradas(TAMANHO_MEMORIA_VIRTUAL);
	public static final FabricaDeEntradas fab2 = new FabricaDeEntradas(TAMANHO_MEMORIA_VIRTUAL);
	
	public static void main(String[] args) {
		
		String inputProcesso1 = fab1.getNewEntrada(); 
		System.out.println("fab1 = " + inputProcesso1);
		String inputProcesso2 = fab2.getNewEntrada(); 
		System.out.println("fab2 = " + inputProcesso2);
		
		Processo processo1 = new Processo(mmu, inputProcesso1, 1);
		Processo processo2 = new Processo(mmu, inputProcesso2, 2);
		
		Thread p1 = new Thread(processo1);
		Thread p2 = new Thread(processo2);
				
		p1.start();
		p2.start();
	}
}