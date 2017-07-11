package simuSO;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;

import clock.Clock;
import log.Log;
import log.TelaLog;
import entrada.*;
import memoria.MemoriaFisica;
import memoria.MemoriaHD;
import memoria.MemoriaVirtual;

public class SO extends JFrame {
	
	private JPanel contentPane;
	public static final Clock CLOCK = new Clock(1000);
	public static final int TAMANHO_MEMORIA_FISICA = 4;
	public static final int TAMANHO_MEMORIA_VIRTUAL = 8;
	public static final String LOCAL_HD = "MEMORIA_HD.txt";
	public static MemoriaFisica memFisica = new MemoriaFisica(TAMANHO_MEMORIA_FISICA);
	public static MemoriaVirtual memVirtual = new MemoriaVirtual(TAMANHO_MEMORIA_VIRTUAL);
	public static MemoriaHD memoriaHD = new MemoriaHD(LOCAL_HD, TAMANHO_MEMORIA_VIRTUAL);
	public static final MMU mmu = new MMU(TAMANHO_MEMORIA_FISICA, TAMANHO_MEMORIA_VIRTUAL, memVirtual, memFisica, memoriaHD);
	public static final Log LOG = new Log(memVirtual, memFisica, memoriaHD);
	//public static final FabricaDeEntradas fab1 = new FabricaDeEntradas(TAMANHO_MEMORIA_VIRTUAL);
	//public static final FabricaDeEntradas fab2 = new FabricaDeEntradas(TAMANHO_MEMORIA_VIRTUAL);
	
	public static void main(String[] args) {
		//String inputProcesso1 = fab1.getNewEntrada();
		//System.out.println("fab1 = " + inputProcesso1);
		//String inputProcesso2 = fab2.getNewEntrada(); 
		//System.out.println("fab2 = " + inputProcesso2);
		
		String input1 = "8-R,7-R,7-R,3-W,2-W,4-R,7-W,2-W,0-R,1-W,0-W,5-R,6-R,8-W,1-R,2-W";
		
		CLOCK.adicionaListener(mmu);
		
		Processo processo1 = new Processo(mmu, input1, 1);
		//Processo processo2 = new Processo(mmu, inputProcesso2, 2);
		
		Thread clock = new Thread(CLOCK);
		clock.start();
		
		Thread p1 = new Thread(processo1);
		//Thread p2 = new Thread(processo2);
				
		p1.start();
		
		Thread log = new Thread(LOG);
		//log.start();
		//p2.start();
		
		// Evento que vai gerar o formulário
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					TelaLog frame = new TelaLog(memVirtual, memFisica, memoriaHD);
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
		
	}
}