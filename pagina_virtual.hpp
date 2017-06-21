#ifndef PAGINA_VIRTUAL_HPP
#define PAGINA_VIRTUAL_HPP

class PaginaVirtual {
public:
	PaginaVirtual(bool, bool, bool, double);
	void referenciar(bool);
	bool referenciada();
	void modificar(bool);
	bool modificada();
	void presenca(bool);
	bool presente();
	void setMolduraPagina(double);
	double getMolduraPagina();

private:
	bool refer;
	bool modif;
	bool pres;
	unsigned int moldura_pagina;	
};

#endif