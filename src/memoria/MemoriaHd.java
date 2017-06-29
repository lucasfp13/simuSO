package memoria;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class MemoriaHd {
	
	private static String local;
	private int tamanho;
	private PaginaHd paginaHd;
	
	public MemoriaHd(String pLocal, int pTamanho) {
		local = pLocal;
		this.tamanho = pTamanho;
		this.paginaHd = new PaginaHd(pTamanho);
	}
	
	public int ler(int pIndice) throws IOException {
		BufferedReader arq = new BufferedReader(new FileReader(local));
        String linha = null;
        for (int i = 0; i < pIndice; i++) {
            linha = arq.readLine();
        }
        arq.close();
        return Integer.parseInt(linha);
    }
 
    public static void escrever(int valor) throws IOException { // verificar esse método
        BufferedWriter buffWrite = new BufferedWriter(new FileWriter(local));
        String linha = Integer.toString(valor);
        buffWrite.append(linha + "\n");
        buffWrite.close();
    }
    
    public PaginaHd getValorBloco(int pIndice){
    	try {
    	   this.paginaHd.setValor(ler(pIndice));
    	} catch (Exception e) {
    		System.out.println(e);
		}
        return this.paginaHd; 
    }

    public void setValorBloco(int endereco, int valor){
        paginaHd.setValor(valor);

    }
    
}
