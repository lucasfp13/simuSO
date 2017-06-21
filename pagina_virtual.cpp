#include "pagina_virtual.hpp"

PaginaVirtual::PaginaVirtual(){
	this->refer = false;
	this->modif = false;
	this->pres = false;
	this->moldura_pagina = 0;
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