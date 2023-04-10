package p4Hash;

/**
 * Clase ClosedHashTable
 * @author MariaTeresaFernandezCoro - UO263728
 */
public class ClosedHashTable<T> extends AbstractHash<T> {
	
	protected HashNode<T> associativeArray[]; // array de nodos hash

	protected int hashSize;	// tamaño de la tabla, debe ser un numero primo
	protected int numElems;	// numero de elementos en la tabla en cada momento
	
	// Tipo de exploracion en caso de colision, por defecto sera LINEAL
	public static final int LINEAL = 0;
	public static final int CUADRATICA = 1;
	public static final int DOBLEHASH = 2;

	protected int exploracion; // exploracion que se realizara en caso de colision
	
	private double fcUP;	// Factor de carga maximo
	private double fcDOWN;	// Factor de carga minimo
	
	/**
	 * Constructor
	 * @param tam, tamaño de la tabla
	 * @param expl, tipo de exploracion
	 */
	@SuppressWarnings("unchecked")
	public ClosedHashTable(int tam, int expl) {
		hashSize = nextPrimeNumber(tam); // Establece un tamaño valido si tam no es primo
		associativeArray = (HashNode<T>[]) new HashNode[hashSize]; // Crea el array de HashNode's
		for (int i = 0; i < hashSize; i++)
			associativeArray[i] = new HashNode<T>();
		exploracion = tipoExploracion(expl);
		this.fcUP = 0.5;
		this.fcDOWN = 0.16;
	}

	/**
	 * Constructor
	 * @param tam, tamaño de la table Hash, si no es un numero primo lo ajusta al primo superior
	 * @param fcUP, el factor de carga limite, por encima del cual hay que redispersar (directa)
	 * @param fcDOWN, el factor de carga limite, por debajo del cual hay que redispersar (inversa)
	 * @param expl, el tipo de exploracion(LINEAL=0, CUADRATICA=1, ...), si invalido LINEAL
	 */
	@SuppressWarnings("unchecked")
	public ClosedHashTable(int tam, double fcUP, double fcDOWN, int expl) {
		hashSize = nextPrimeNumber(tam); // Establece un tamaño valido si tam no es primo
		associativeArray = (HashNode<T>[]) new HashNode[hashSize]; // Crea el array de HashNode's
		for (int i = 0; i < hashSize; i++)
			associativeArray[i] = new HashNode<T>();
		exploracion = tipoExploracion(expl);
		this.fcUP = fcUP;
		this.fcDOWN = fcDOWN;
	}
	
	/**
	 * Establece el tipo de exploracion con la que se trabaja
	 * @param expl, int
	 * @return el tipo de exploracion, LINEAL por defecto
	 */
	private int tipoExploracion(int expl) {
		if (expl == CUADRATICA)
			return CUADRATICA;
		else if (expl == DOBLEHASH)
			return DOBLEHASH;
		else
			return LINEAL;
	}

	/**
	 * Obtiene el numero de elementos que contiene la tabla Hash
	 * @return numElems, int
	 */
	@Override
	public int getNumOfElems() {
		return numElems;
	}

	/**
	 * Obtiene el tamaño de la tabla Hash
	 * @return la longitud del array, int
	 */
	@Override
	public int getSize() {
		return associativeArray.length;
	}
	
	/**
	 * Calcula la posicion en la que añadir la clave
	 * segun el tipo de exploracion
	 * @param elem de tipo generico a añadir
	 * @param i, int
	 * @return el resultado de la funcion de exploracion, int
	 */
	private int funcionExploracion(T elem, int i) {
		int f;
		if (exploracion == LINEAL) {
			f = (fHash(elem)+i) % getSize();
			return f < 0 ? f+getSize() : f;
		}
		else if (exploracion == CUADRATICA) {
			f = (fHash(elem) + i*i) % getSize();
			return f < 0 ? f+getSize() : f;
		}
		else {
			int r = previousPrimeNumber(getSize());
			f = (fHash(elem) + i*(r - fHash(elem)%r)) % getSize();
			return f < 0 ? f+getSize() : f;
		}
	}

	/**
	 * Inserta un nuevo elemento en la tabla hash
	 * @param elem de tipo generico, a insertar
	 * @return 0 si lo ha insertado 
	 * o negativo en caso contrario (-1 si no encuentra sitio y -2 si el elemento no es válido)
	 */
	@Override
	public int add(T elem) {
		if (elem == null)
			return -2;
		int i = 0;
		int f = funcionExploracion(elem, i);
		do {
			if (associativeArray[f].getStatus() != 1) {
				associativeArray[f].setInfo(elem);
				numElems++;
				double FC = (double) getNumOfElems()/getSize();
				if (FC >= fcUP)
					reDispersion();
				return 0;
			}
			else {
				i++;
				f = funcionExploracion(elem, i);
			}
		} while (i < getSize());
		reDispersion();	// Cuando no hay posiciones libres realizo redisperion
		return -1;
	}

	/**
	 * Busca y devuelve el elemento (igual al que se le pasa) encontrado en la tabla hash
	 * Si el elemento esta repetido, retorna el primero que encuentre 
	 * @param elem de tipo generico a buscar
	 * @return elemento encontrado, de tipo generico
	 * o null si no lo encuentra
	 */
	@Override
	public T find(T elem) {
		if (elem == null)
			return null;
		int i = 0;
		int f = funcionExploracion(elem, i);
		do {
			if (associativeArray[f].getStatus() == 1 && associativeArray[f].getInfo() == elem)
				return associativeArray[f].getInfo();
			else {
				i++;
				f = funcionExploracion(elem, i);
			}
		} while (i < getSize());
		return null;
	}

	/**
	 * Borra un elemento (igual al que se le pasa) que se encuentra en la tabla hash 
	 * @param elem de tipo generico a borrar
	 * @return 0 si lo ha borrado
	 * o negativo en caso contrario (-1 si no existe en la tabla, y –2 en otros casos) 
	 */
	@Override
	public int remove(T elem) {
		if (elem == null)
			return -2;
		else if (find(elem) == null)
			return -1;
		int i = 0;
		int f = funcionExploracion(elem, i);
		do {
			if (associativeArray[f].getStatus() != 0) {
				if (associativeArray[f].getStatus() == 1 && associativeArray[f].getInfo() == elem) {
					associativeArray[f].remove();
					numElems--;
					double FC = (double) getNumOfElems()/getSize();
					if (FC <= fcDOWN)
						inverseReDispersion();
					return 0;
				}
				else {
					i++;
					f = funcionExploracion(elem, i);
				}
			}
		} while (i < getSize());
		return -2;
	}
	
	/**
	 * Realiza una redispersion (aumentando el tamaño) al numero primo mayor
	 * que el doble del tamaño actual, recolocando los elementos 
	 * @return true si se realiza correctamente, sino false
	 */
	@Override
	protected boolean reDispersion() {
		int new_tam = nextPrimeNumber(getSize()*2);
		return recolocar(new_tam);
	}

	/**
	 * Realiza una redispersion inversa (disminuyendo el tamaño) al numero primo menor
	 * que la mitad del tamaño actual, recolocando los elementos
	 * @return true si se realiza correctamente, sino false
	 */
	@Override
	protected boolean inverseReDispersion() {
		int new_tam = previousPrimeNumber(getSize()/2);
		return recolocar(new_tam);
	}
	
	/**
	 * Recoloca los elementos de la tabla segun su nuevo tamaño
	 * @param new_tam, integer
	 * @return true si los recoloca perfectamente, false en otro caso
	 */
	@SuppressWarnings("unchecked")
	private boolean recolocar(int new_tam) {
		HashNode<T> tabla_inicial[] = associativeArray;
		associativeArray = (HashNode<T>[]) new HashNode[new_tam];
		for (int i = 0; i < getSize(); i++)
			associativeArray[i] = new HashNode<T>();
		numElems = 0;
		for (int i = 0; i < tabla_inicial.length; i++) {
			if (tabla_inicial[i].getStatus() == 1)
				add((T) tabla_inicial[i].getInfo());
		}
		if (tabla_inicial.length == 0)
			return false;
		return true;
	}

	/**
	 * Convierte la tabla Hash en una cadena de String
	 * @return la cadena, String
	 */
	@Override
	public String toString() {
		StringBuilder cadena=new StringBuilder();
		for (int i =0; i < getSize(); i++){
			cadena.append(associativeArray[i]);
			cadena.append(";");
		}
		cadena.append("[Size: ");
		cadena.append(getSize());
		cadena.append(" Num.Elems.: ");
		cadena.append(getNumOfElems());
		cadena.append("]");
		return cadena.toString();
	}
	
}
