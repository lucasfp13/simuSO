#include "memoria_fisica.hpp"

MemoriaFisica::MemoriaFisica(int pTamanho) {
	this->memoria_fisica = (int *) malloc(sizeof(int) * pTamanho);
}

int *MemoriaFisica::getMemoriaFisica() {
	return this->memoria_fisica;
}

int MemoriaFisica::getValorMemoriaFisica(int pIndice) {
	return this->memoria_fisica[pIndice];
}

void MemoriaFisica::setValorMemoriaFisica(int pValor, int pIndice) {
	this->memoria_fisica[pIndice] = pValor;
}