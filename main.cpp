#include <iostream>
#include <stdio.h>
#include "memoria_fisica.hpp"
#include "pagina_virtual.hpp"

using namespace std;

int main(int argc, char **argv){
	static int TAMANHO_MEMORIA = 16;
	static int TAMANHO_MEMORIA_VIRTUAL = 32;
	static int TAMANHO_HD = 32;

	//	Testes temporários

	MemoriaFisica mem_fisica(TAMANHO_MEMORIA);
//	MemoriaVirtual mem_virtual(TAMANHO_MEMORIA_VIRTUAL);
	PaginaVirtual pagina;

	return 0;
}