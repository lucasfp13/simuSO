#include "pagina_virtual.hpp"

PaginaVirtual::PaginaVirtual(bool pBitR, bool pBitM, bool pBitP, double pMolduraPagina ){
	this->refer = pBitR;
	this->modif = pBitM;
	this->pres = pBitP;
	this->moldura_pagina = pMolduraPagina;
}

void PaginaVirtual::referenciar(bool bit){
	this->refer = bit;
}

bool PaginaVirtual::referenciada(){
	return refer;
}

void PaginaVirtual::modificar(bool bit){
	this->modif = bit;
}

bool PaginaVirtual::modificada(){
	return modif;
}

void PaginaVirtual::presenca(bool bit){
	this->pres = bit;
}

bool PaginaVirtual::presente(){
	return pres;
}

void PaginaVirtual::setMolduraPagina(double pMolduraPagina){
	this->moldura_pagina = pMolduraPagina;
}

double PaginaVirtual::getMolduraPagina(){
	return moldura_pagina;
}