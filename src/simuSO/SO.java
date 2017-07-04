package simuSO;

public class SO {
	public static void main(String[] args) {
		MMU mmu = new MMU(16);
		mmu.referenciarMemoriaVirtual(0);
		Processo processo = new Processo(mmu.getPagina(0));
		
		Thread p1 = new Thread(processo);
		
		if (mmu.executarInstrucao('W', 0)) {
			System.out.println("valor escrito!");
		}
		else {
			System.out.println("valor excedeu o tamanho da memória virtual!");
		}
		
		mmu.executarInstrucao('R', 0);
		
		//p1.start();
		
	}
}
