package algoritmo;

import simuSO.*;
import memoria.*;

public class WS {	
	Processo processo;
	PaginaVirtual[] conjTrabalho;
	int tempoAtual;
	
	WS(Processo pProcesso, PaginaVirtual[] pPaginas){
		this.processo = pProcesso;
		this.conjTrabalho = pPaginas;
	}
	
	public void verificarPagina(){}
}
