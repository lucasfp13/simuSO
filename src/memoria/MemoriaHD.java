package memoria;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.Random;

public class MemoriaHD {
	private String local;
	private int countNumeroLinhas = 0; // contador para saber se o indice não estrapolou o tamanho maximo da memoria do HD
	//private int tamanho = 0;
	
	public MemoriaHD(String local, int pTamanho) {
		this.local = local;
		//this.tamanho = pTamanho;
		try {
			
			if(this.local != null){
				return;
			} else {
				BufferedWriter buffWrite = new BufferedWriter(new FileWriter(this.local));
				Random r = new Random();
			
				for(int i = 0; i < pTamanho; i++){
					Integer seed = r.nextInt(100);
					this.setValor(seed);
				}
				
				buffWrite.close();
			}
		} catch (Exception e) {
			System.out.println(e);
		}
	}
	
	public void setValor(Integer pValor) {
		try {
			BufferedWriter buffWrite = new BufferedWriter(new FileWriter(this.local, true));
	        buffWrite.write(Integer.toString(pValor));
	        buffWrite.newLine();
	        buffWrite.close();
	        this.countNumeroLinhas++;
		}catch (Exception e) {
			System.out.println(e);
		}
	}
	
	public Integer getValor(int indice) {
		Integer valor = 0;
		
		try {
			FileReader arq = new FileReader(this.local);
		    BufferedReader lerArq = new BufferedReader(arq);
		    String linha = lerArq.readLine();
		    
		    for(int i = 0; i < indice; i++) {
		    	linha = lerArq.readLine();
		    }
		    valor = Integer.parseInt(linha);
		    arq.close();
		} catch (Exception e) {
			System.out.println(e);
		}
		return valor;
	}
}