#include <iostream>
#include "memoria_fisica.hpp"

using namespace std;

int main(int argc, char **argv){
	static int TAMANHO_MEMORIA = 16;

	MemoriaFisica mem_fisica(TAMANHO_MEMORIA);

	return 0;
}