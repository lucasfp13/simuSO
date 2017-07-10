package log;

import java.awt.BorderLayout;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.border.EmptyBorder;

import memoria.MemoriaFisica;
import memoria.MemoriaHD;
import memoria.MemoriaVirtual;
import javax.swing.JLabel;
import java.awt.FlowLayout;
import javax.swing.border.SoftBevelBorder;
import javax.swing.border.BevelBorder;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JList;

public class TelaLog extends JFrame {
	JPanel contentPane = new JPanel();
	/**
	 * Create the frame.
	 */
	public TelaLog(MemoriaVirtual pMemoriaVirtual, MemoriaFisica pMemoriaFisica, MemoriaHD pMemoriaHD) {
		this.setResizable(false);
		try {
			UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InstantiationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (UnsupportedLookAndFeelException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		setTitle("Log - simuSO");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 624, 437);
		
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		
		JPanel panel = new JPanel();
		contentPane.add(panel, BorderLayout.CENTER);
		panel.setLayout(null);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBorder(new SoftBevelBorder(BevelBorder.LOWERED, null, null, null, null));
		panel_1.setBounds(0, 0, 589, 349);
		panel.add(panel_1);
		panel_1.setLayout(null);
		
		JList<Boolean> list = new JList<Boolean>();
		list.setBorder(new SoftBevelBorder(BevelBorder.LOWERED, null, null, null, null));
		list.setBounds(12, 40, 180, 296);
		panel_1.add(list);
		
		JLabel lblMemriaVirtual = new JLabel("Mem\u00F3ria Virtual:");
		lblMemriaVirtual.setBounds(12, 13, 109, 16);
		panel_1.add(lblMemriaVirtual);
		
		JList<Integer> list_1 = new JList<Integer>();
		list_1.setBorder(new SoftBevelBorder(BevelBorder.LOWERED, null, null, null, null));
		list_1.setBounds(204, 40, 180, 293);
		panel_1.add(list_1);
		
		JLabel lblMemriaFsica = new JLabel("Mem\u00F3ria F\u00EDsica:");
		lblMemriaFsica.setBounds(204, 13, 109, 16);
		panel_1.add(lblMemriaFsica);
		
		JList<Integer> list_2 = new JList<Integer>();
		list_2.setBorder(new SoftBevelBorder(BevelBorder.LOWERED, null, null, null, null));
		list_2.setBounds(396, 40, 180, 293);
		panel_1.add(list_2);
		
		JLabel lblMemriaHd = new JLabel("Mem\u00F3ria HD:");
		lblMemriaHd.setBounds(396, 13, 89, 16);
		panel_1.add(lblMemriaHd);
		
		JButton btnAtualizarInformaes = new JButton("Atualizar Informa\u00E7\u00F5es");
		btnAtualizarInformaes.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent arg0) {
				
				DefaultListModel <Boolean> modelListMemoriaVirtual = new DefaultListModel <Boolean> ();
				DefaultListModel <Integer> modelListMemoriaFisica = new DefaultListModel <Integer> ();
				DefaultListModel <Integer> modelListMemoriaHD = new DefaultListModel <Integer> ();
				
				list.setModel(modelListMemoriaVirtual);
				list_1.setModel(modelListMemoriaFisica);
				list_2.setModel(modelListMemoriaHD);
				
				for (int i = 0; i < pMemoriaVirtual.getTamanho(); i++) {
					modelListMemoriaVirtual.addElement(pMemoriaVirtual.getPagina(i).presente());
    	    	}
    	    	
    	    	for (int i = 0; i < pMemoriaFisica.getTamanho(); i++) {
    	    		 modelListMemoriaFisica.addElement(pMemoriaFisica.getValor(i));
    	    	}
    	    	
    	    	for (int i = 0; i < pMemoriaHD.getTamanho(); i++) {
    	    		modelListMemoriaHD.addElement(pMemoriaHD.getValorHD(i));
    	    	}
				
			}
		});
		btnAtualizarInformaes.setBounds(12, 359, 172, 25);
		panel.add(btnAtualizarInformaes);
		
	}
}
