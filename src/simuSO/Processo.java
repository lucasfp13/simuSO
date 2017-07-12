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
				synchronized(this.mmu){
					try {
						System.out.println("Processo " + this.id + " escrevendo indice " + Integer.parseInt(parts2[0]) + " da memória virtual");
						mmu.escrever(Integer.parseInt(parts2[0]), this.id);		// WRITE
					} catch (Exception e) {
						// TODO: handle exception
					}
				}
				
			}
			
			if(!parts2[1].equals(w)){
				synchronized(this.mmu){
					try {
						System.out.println("Processo " + this.id + " lendo indice " + Integer.parseInt(parts2[0]) + " da memória virtual");
						System.out.println("Valor lido: " + mmu.ler(Integer.parseInt(parts2[0]), this.id));	// READ
					} catch (Exception e) {
						// TODO: handle exception
					}
				}
				
			}
						
			
			try {
				Thread.sleep(SO.INTERVALO_PROCESSO);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
		
		System.out.println("Processo " + this.id + " terminou.");
	}

}
