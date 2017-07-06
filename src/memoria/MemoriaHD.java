package memoria;

import java.io.File;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class MemoriaHD {
	private File arquivo = new File("/hd.txt");
	public MemoriaHD() {
		try {
			if (!arquivo.exists()) {
				this.arquivo.createNewFile();
			}
		}catch (Exception e) {
			// TODO: handle exception
		}
	}
	public void setText(String texto) {
		try {
			FileWriter fw = new FileWriter(arquivo, true);
			BufferedWriter bw = new BufferedWriter(fw); 
			bw.write(texto);
			bw.newLine();
			bw.close();
			fw.close();
		}catch (Exception e) {
			// TODO: handle exception
		}
	}
}
