package simuSO;

import entrada.*;

public class SO {
	public static void main(String[] args) {
		StringBuffer sb = new StringBuffer();
		int TAMANHO_MEMORIA_FISICA = 32;
		int TAMANHO_MEMORIA_VIRTUAL = 64;
		
		FabricaDeEntradas fab = new FabricaDeEntradas(TAMANHO_MEMORIA_VIRTUAL);
		String input = fab.getNewEntrada(); 
		System.out.println(input);
		
		MMU mmu = new MMU(TAMANHO_MEMORIA_FISICA, TAMANHO_MEMORIA_VIRTUAL);
		//Processo processo = new Processo();
		
		//Thread p1 = new Thread(processo);
		
		//for(int i = 0; i < sb.substring(0, ); ++i){
			
			
			if(mmu.executarInstrucao('W', 0)) {
				System.out.println("valor escrito!");
			}
			else {
				System.out.println("valor excedeu o tamanho da memória virtual!");
			}
			
			mmu.executarInstrucao('R', 0);
	//	}
		
		//p1.start();
	}
}