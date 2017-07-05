package simuSO;

public class Clock implements Runnable {

	private int tempo;
	private boolean rodando;
	private static int TIMESTEMP = 100;

	public Clock() {
		this.tempo = 0;
		this.rodando = false;
	}

	public void run() {
		while (this.rodando) {
			try {
				this.tempo++;
				Thread.sleep(TIMESTEMP);
			} catch (InterruptedException e) {
				System.out.println(e);
			}
		}
	}

	public void iniciar() {
		this.rodando = true;
	}

	public void parar() {
		this.rodando = false;
	}

	public int getTempo() {
		return this.tempo;
	}

}