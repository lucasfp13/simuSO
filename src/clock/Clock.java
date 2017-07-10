package clock;

import simuSO.*;

public class Clock implements Runnable {
	private int tempo = 0;
	private int timeStemp;
	private Dispatcher dispatcher = new Dispatcher();
	
	public Clock(int pTimeStemp) {
		this.timeStemp = pTimeStemp;
	}

	public void run() {
		while(true) {
			try {
				Thread.sleep(timeStemp);		
				this.tempo += timeStemp;
				this.dispatcher.notificaListener(this.tempo);
				//System.out.println("TEMPO CLOCK " + this.tempo);
			} catch (InterruptedException e) {
				System.out.println(e);
			}
		}
	}

	public void adicionaListener(IClockListener icl){
		this.dispatcher.adicionaListener(icl);
	}

}