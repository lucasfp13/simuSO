package memoria;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.Random;

public class MemoriaHD {
	private String local;
	//private int countNumeroLinhas = 0; // contador para saber se o indice não estrapolou o tamanho maximo da memoria do HD
	private int tamanho = 0;
	
	public MemoriaHD(String local, int pTamanho) {
		this.local = local;
		this.tamanho = pTamanho;
		try {
			FileReader file = new FileReader(this.local);
			if(file.ready()){
				return;
			} else {
				BufferedWriter buffWrite = new BufferedWriter(new FileWriter(this.local));
				Random r = new Random();
			
				for(int i = 0; i < pTamanho; i++){
					Integer seed = r.nextInt(100);
					this.setValorHD(seed);
				}
				
				buffWrite.close();
			}
		} catch (Exception e) {
			System.out.println(e);
		}
	}
	
	public void setValorHD(Integer pValor) {
		try {
			BufferedWriter buffWrite = new BufferedWriter(new FileWriter(this.local, true));
	        buffWrite.write(Integer.toString(pValor));
	        buffWrite.newLine();
	        	
	        
	        buffWrite.close();
	        //this.countNumeroLinhas++;
		}catch (Exception e) {
			System.out.println(e);
		}
	}
	
	public Integer getValorHD(int indice) {
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
	
	public void swap(Integer pValor, Integer pIndice) {
		try {
			Integer valor2Swap = new Integer(pValor);
			
			// Variáveis para escrita
			BufferedWriter buffWrite = new BufferedWriter(new FileWriter(this.local, true));	  
	        
	        // Variáveis para leitura
			FileReader arq = new FileReader(this.local);
		    BufferedReader lerArq = new BufferedReader(arq);
		    String linha = lerArq.readLine();
	        
		    // Swap
		    String[] arqLinha = linha.split(" ");
		    
	        arqLinha[pIndice] = valor2Swap.toString();
	        for(int x = 0; pIndice < arqLinha.length; pIndice++){
	        	buffWrite.write(arqLinha[pIndice]);
	        }
	        
	        buffWrite.close();
	        lerArq.close();
		} catch (Exception e) {
			System.out.println(e);
		}
	}
	
	public int getTamanho() {
		return this.tamanho;
	}
}