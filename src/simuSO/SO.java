package simuSO;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;

import clock.Clock;
import log.TelaLog;
import entrada.*;
import memoria.MemoriaFisica;
import memoria.MemoriaHD;
import memoria.MemoriaVirtual;

public class SO extends JFrame {
	
	private JPanel contentPane;
	public static final int CLOCK = 10000;
	public static final int INTERVALO_PROCESSO = 5000;
	public static final int TAMANHO_MEMORIA_FISICA = 4;
	public static final int TAMANHO_MEMORIA_VIRTUAL = 8;
	public static final String LOCAL_HD = "MEMORIA_HD.txt";
	
	public static void main(String[] args) {
		//FabricaDeEntradas fab1 = new FabricaDeEntradas(TAMANHO_MEMORIA_VIRTUAL);
		//FabricaDeEntradas fab2 = new FabricaDeEntradas(TAMANHO_MEMORIA_VIRTUAL);
		//String inputProcesso1 = fab1.getNewEntrada();
		//System.out.println("fab1 = " + inputProcesso1);
		//String inputProcesso2 = fab2.getNewEntrada(); 
		//System.out.println("fab2 = " + inputProcesso2);
				
		String input1 = "6-R,7-R,7-R,3-W,2-W,4-R,7-W,2-W,0-R,1-W,0-W,5-R,6-R,7-W,1-R,2-W";
		String input2 = "5-R,0-R,3-R,3-W,0-W,7-R,7-W,1-W,4-R,5-W,1-W,5-R,0-R,5-W,2-R,3-W";
			
		MemoriaFisica memFisica = new MemoriaFisica(TAMANHO_MEMORIA_FISICA);
		MemoriaVirtual memVirtual = new MemoriaVirtual(TAMANHO_MEMORIA_VIRTUAL);
		MemoriaHD memHD = new MemoriaHD(LOCAL_HD, TAMANHO_MEMORIA_VIRTUAL);
		
		MMU mmu = new MMU(TAMANHO_MEMORIA_FISICA, TAMANHO_MEMORIA_VIRTUAL, memVirtual, memFisica, memHD);
	
		Clock clock = new Clock(CLOCK);
		clock.adicionaListener(mmu);
		Thread c = new Thread(clock);		
		c.start();
		
		
		Processo processo1 = new Processo(mmu, input1, 1);
		Processo processo2 = new Processo(mmu, input2, 2);
		
		Thread p1 = new Thread(processo1);
		Thread p2 = new Thread(processo2);
		
		p1.start();
		p2.start();
			
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					TelaLog frame = new TelaLog(memVirtual, memFisica, memHD);
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
}