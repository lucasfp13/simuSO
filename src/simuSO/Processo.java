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
		System.out.println("Executando Processo " + this.id);
		String parts[] = input.split(",");
		
		for (int i = 0; i < parts.length; i++) {
			String parts2[] = parts[i].split("-");
			String w = "W";
			
			if(parts2[1].equals(w)){
				//System.out.println("Processo " + this.id + " escrevendo na memória");
				mmu.escrever(Integer.parseInt(parts2[0]), this.id);		// WRITE
			} else {
				//System.out.println("Processo " + this.id + " lendo indice " + Integer.parseInt(parts2[0]) + " da memória virtual");
				mmu.ler(Integer.parseInt(parts2[0]), this.id);		// READ
			}
			
			try {
				Thread.sleep(100);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
	}

}
