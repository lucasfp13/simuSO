package memoria;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;

public class MemoriaHD {
	private String local;
	private int countNumeroLinhas = 0; // contador para saber se o indice não estrapolou o tamanho maximo da memoria do HD
	private int tamanho = 0;
	
	public MemoriaHD(String local, int pTamanho) {
		this.local = local;
		this.tamanho = pTamanho;
		try {
			BufferedWriter buffWrite = new BufferedWriter(new FileWriter(this.local));
			buffWrite.close();
		}catch (Exception e) {
			System.out.println(e);
		}
	}
	
	public void setValor(int pValor) {
		try {
			BufferedWriter buffWrite = new BufferedWriter(new FileWriter(this.local, true));
	        buffWrite.write(Integer.toString(pValor));
	        buffWrite.newLine();
	        buffWrite.close();
		}catch (Exception e) {
			System.out.println(e);
		}
		this.countNumeroLinhas+=1;
	}
	
	public void getValor(int indice) {
		try {
			FileReader arq = new FileReader(this.local);
		    BufferedReader lerArq = new BufferedReader(arq);
		    String linha = lerArq.readLine();
		      
		    for (int i = 0; i < indice; i++) {
		    	linha = lerArq.readLine();
		    }
		    System.out.println(linha);
		    arq.close();
		} catch (Exception e) {
			System.out.println(e);
		}
	}
}