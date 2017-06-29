package memoria;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class MemoriaHd {
	
	private static String local;
	
	public MemoriaHd(String pLocal) {
		local = pLocal;
	}
	
	public void ler() throws IOException {
		BufferedReader arq = new BufferedReader(new FileReader(local));
        String linha = null;
        while (true) {
            if (linha != null) {
                System.out.println(linha);
            } else {
            	break;
            }
            linha = arq.readLine();
        }
        arq.close();
    }
 
    public static void escrever(int valor) throws IOException {
        BufferedWriter buffWrite = new BufferedWriter(new FileWriter(local));
        String linha = Integer.toString(valor);
        buffWrite.append(linha + "\n");
        buffWrite.close();
    }
}
