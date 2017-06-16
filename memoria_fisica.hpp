#ifndef _MEMORIA_FISICA_HPP_
#define _MEMORIA_FISICA_HPP_

//#include <>

class MemoriaFisica {
public:
	MemoriaFisica(int);
	void setMemoriaFisica(int, int);
	int getMemoriaFisica(int);

private:
	int memoria_fisica[];
};

#endif