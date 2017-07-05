package simuSO;

public class Processo implements Runnable {
	private MMU mmu;
	private String input;
	private int id;
	
	public Processo(MMU pMmu, String pFabricaEntradas, int pId) {
		this.mmu = pMmu;
		this.input = pFabricaEntradas;
		this.id = pId;
	}
	
	@Override
	public void run() {
		System.out.println("Executando Processo " + hashCode());
		String parts[] = input.split(",");
		
		for (int i = 0; i < parts.length; i++) {
			String parts2[] = parts[i].split("-");
			if(parts2[1] == "W"){
				mmu.escrever(Integer.parseInt(parts2[0]), parts2[1]);		// WRITE
				System.out.println("Processo " + this.id + " escrevendo na memória");
			} else {
				mmu.ler(Integer.parseInt(parts2[0]), parts2[1]);		// READ
				System.out.println("Processo " + this.id + " lendo da memória ");
			}
		}
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
