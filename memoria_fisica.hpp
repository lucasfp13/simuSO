#ifndef MEMORIA_FISICA_HPP
#define MEMORIA_FISICA_HPP

#include <stdlib.h>

class MemoriaFisica {
	public:
		MemoriaFisica(int);
		void setValorMemoriaFisica(int, int);
		int *getMemoriaFisica();
		int getValorMemoriaFisica(int);

	private:
		int *memoria_fisica;
};

#endif