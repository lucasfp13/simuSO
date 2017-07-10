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
import java.io.File;
import java.util.ArrayList;

import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.table.DefaultTableModel;

import javax.swing.JScrollPane;
import java.awt.Font;

public class TelaLog extends JFrame {
	JPanel contentPane = new JPanel();
	private JTable table;
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
		setBounds(100, 100, 666, 437);
		
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		
		JPanel panel = new JPanel();
		contentPane.add(panel, BorderLayout.CENTER);
		panel.setLayout(null);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBorder(new SoftBevelBorder(BevelBorder.LOWERED, null, null, null, null));
		panel_1.setBounds(0, 0, 638, 349);
		panel.add(panel_1);
		panel_1.setLayout(null);
		
		JLabel lblMemriaVirtual = new JLabel("Mem\u00F3ria Virtual:");
		lblMemriaVirtual.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblMemriaVirtual.setBounds(12, 13, 109, 16);
		panel_1.add(lblMemriaVirtual);
		
		JList<Integer> list_1 = new JList<Integer>();
		list_1.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		list_1.setVisibleRowCount(100);
		list_1.setBorder(new SoftBevelBorder(BevelBorder.LOWERED, null, null, null, null));
		list_1.setBounds(380, 42, 117, 293);
		panel_1.add(list_1);
		
		JLabel lblMemriaFsica = new JLabel("Mem\u00F3ria F\u00EDsica:");
		lblMemriaFsica.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblMemriaFsica.setBounds(380, 13, 109, 16);
		panel_1.add(lblMemriaFsica);
		
		JList<Integer> list_2 = new JList<Integer>();
		list_2.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		list_2.setVisibleRowCount(100);
		list_2.setBorder(new SoftBevelBorder(BevelBorder.LOWERED, null, null, null, null));
		list_2.setBounds(509, 42, 117, 293);
		panel_1.add(list_2);
		
		JLabel lblMemriaHd = new JLabel("Mem\u00F3ria HD:");
		lblMemriaHd.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblMemriaHd.setBounds(507, 13, 89, 16);
		panel_1.add(lblMemriaHd);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setViewportBorder(new SoftBevelBorder(BevelBorder.LOWERED, null, null, null, null));
		scrollPane.setBounds(12, 42, 356, 294);
		panel_1.add(scrollPane);
		
		table = new JTable();
		table.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"Referenciada", "Modificada", "Presente/Ausente", "IndiceRef"
			}
		));
		table.getColumnModel().getColumn(0).setPreferredWidth(91);
		table.getColumnModel().getColumn(2).setPreferredWidth(115);
		table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		scrollPane.setViewportView(table);
		
		JButton btnAtualizarInformaes = new JButton("Atualizar Informa\u00E7\u00F5es");
		btnAtualizarInformaes.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent arg0) {
				
				//DefaultListModel <Boolean> modelListMemoriaVirtual = new DefaultListModel <Boolean> ();
				DefaultListModel <Integer> modelListMemoriaFisica = new DefaultListModel <Integer> ();
				DefaultListModel <Integer> modelListMemoriaHD = new DefaultListModel <Integer> ();
				DefaultTableModel model = (DefaultTableModel) table.getModel();
				
				//list.setModel(modelListMemoriaVirtual);
				list_1.setModel(modelListMemoriaFisica);
				list_2.setModel(modelListMemoriaHD);
				
				int tableSize = model.getRowCount();
				
				// Gambi pra apagar tudo da tabela
				for(int a = 0; a < tableSize; a++) {
					model.removeRow(0);
				}
				
				for (int i = 0; i < pMemoriaVirtual.getTamanho(); i++) {
					Integer indice = pMemoriaVirtual.getPagina(i).getIndice();
					boolean modificada = pMemoriaVirtual.getPagina(i).modificada();
					boolean referenciada = pMemoriaVirtual.getPagina(i).referenciada();
					boolean presenteAusente = pMemoriaVirtual.getPagina(i).presente();
					model.addRow(new Object[] {referenciada, modificada, presenteAusente, indice});
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
