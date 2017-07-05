package clock;

import simuSO.*;

public class Clock implements Runnable {
	private int tempo;
	private boolean rodando;
	private static int TIMESTEMP = 100;
	private Dispatcher dispatcher;
	
	public Clock() {
		this.tempo = 0;
		this.rodando = false;
	}

	public void run() {
		while(this.rodando) {
			try {
				this.tempo++;
				Thread.sleep(TIMESTEMP);
				
				this.tempo += TIMESTEMP;
				dispatcher.notificaListener(tempo);
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

	public void adicionaListener(IClockListener icl){
		this.dispatcher.adicionaListener(icl);
	}

}