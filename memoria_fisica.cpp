#include "memoria_fisica.hpp"

MemoriaFisica::MemoriaFisica(int pTamanho){
	this->memoria_fisica[pTamanho];
}

int MemoriaFisica::getMemoriaFisica(int pIndice){
	return this->memoria_fisica[pIndice];
}

void MemoriaFisica::setMemoriaFisica(int pValor, int pIndice){
	this->memoria_fisica[pIndice] = pValor;
}