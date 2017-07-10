package log;

import java.awt.BorderLayout;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import memoria.MemoriaFisica;
import memoria.MemoriaHD;
import memoria.MemoriaVirtual;
import javax.swing.JLabel;
import java.awt.FlowLayout;
import javax.swing.border.SoftBevelBorder;
import javax.swing.border.BevelBorder;
import javax.swing.JButton;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class TelaLog extends JFrame {
	JPanel contentPane = new JPanel();
	/**
	 * Create the frame.
	 */
	public TelaLog(MemoriaVirtual pMemoriaVirtual, MemoriaFisica pMemoriaFisica, MemoriaHD pMemoriaHD) {
		setTitle("Log - simuSO");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 744, 463);
		
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		
		JPanel panel = new JPanel();
		contentPane.add(panel, BorderLayout.CENTER);
		panel.setLayout(null);
		
		JLabel lblMemriaVirtual = new JLabel("Mem\u00F3ria Virtual:");
		lblMemriaVirtual.setBounds(12, 13, 104, 16);
		panel.add(lblMemriaVirtual);
		
		JLabel lblMemriaFsica = new JLabel("Mem\u00F3ria F\u00EDsica:");
		lblMemriaFsica.setBounds(12, 51, 104, 16);
		panel.add(lblMemriaFsica);
		
		JLabel lblMemriaHd = new JLabel("Mem\u00F3ria HD:");
		lblMemriaHd.setBounds(12, 92, 104, 16);
		panel.add(lblMemriaHd);
		
		JLabel label = new JLabel("0");
		label.setBounds(128, 13, 561, 16);
		panel.add(label);
		
		JLabel label_1 = new JLabel("0");
		label_1.setBounds(128, 51, 561, 16);
		panel.add(label_1);
		
		JLabel label_2 = new JLabel("0");
		label_2.setBounds(128, 92, 561, 16);
		panel.add(label_2);
		
		JButton btnAtualizarInformaes = new JButton("Atualizar Informa\u00E7\u00F5es");
		btnAtualizarInformaes.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent arg0) {
				
				String memVirtual = "";
				String memFisica = "";
				String memHD = "";
				
				for (int i = 0; i < pMemoriaVirtual.getTamanho(); i++) {
    	    		memVirtual += pMemoriaVirtual.getPagina(i).presente() + " presente - ";
    	    	}
    	    	
    	    	for (int i = 0; i < pMemoriaFisica.getTamanho(); i++) {
    	    		 memFisica += pMemoriaFisica.getValor(i) + " - ";
    	    	}
    	    	
    	    	for (int i = 0; i < pMemoriaHD.getTamanho(); i++) {
    	    		 memHD += pMemoriaHD.getValorHD(i) + " - ";
    	    	}
    	    	
    	    	label.setText(memVirtual);
    	    	label_1.setText(memFisica);
    	    	label_2.setText(memHD);
				
			}
		});
		btnAtualizarInformaes.setBounds(12, 368, 172, 25);
		panel.add(btnAtualizarInformaes);
	}
}
