package simuSO;

public class Main {
	public static void main(String[] args) {
		MMU mmu = new MMU(16);
		mmu.referenciarMemoriaVirtual(0);
		mmu.executarInstrucao('r', 0);
	}
}
