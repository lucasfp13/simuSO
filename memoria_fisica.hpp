#ifndef MEMORIA_FISICA_HPP
#define MEMORIA_FISICA_HPP

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