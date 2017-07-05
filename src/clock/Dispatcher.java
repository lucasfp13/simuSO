package clock;

import simuSO.*;
import java.util.ArrayList;

public class Dispatcher {
	private ArrayList<IClockListener> listeners = new ArrayList<IClockListener>();
	
	public void adicionaListener(IClockListener pAdd){
		listeners.add(pAdd);
	}
	
	public void notificaListener(int tempoClock){
		listeners.get(0).eventoZerarRecebido(tempoClock);
	}
}
