package p4Hash;

/**
 * Clase abstracta AbstractHash
 * @author MariaTeresaFernandezCoro - UO263728
 */
public abstract class AbstractHash<T> {

	/**
	 * Obtiene el numero de elementos que contiene la tabla Hash
	 * @return numElems, int
	 */
	abstract public int getNumOfElems();

	/**
	 * Obtiene el tamaño de la tabla Hash
	 * @return la longitud del array, int
	 */
	abstract public int getSize();
	
	/**
	 * Inserta un nuevo elemento en la tabla hash
	 * @param elem de tipo generico, a insertar
	 * @return 0 si lo ha insertado 
	 * o negativo en caso contrario (-1 si no encuentra sitio y -2 si el elemento no es válido)
	 */
	abstract public int add(T elem);

	/**
	 * Busca y devuelve el elemento (igual al que se le pasa) encontrado en la tabla hash  
	 * @param elem de tipo generico a buscar
	 * @return elemento encontrado, de tipo generico
	 * o null si no lo encuentra
	 */
	abstract public T find(T elem);

	/**
	 * Borra un elemento (igual al que se le pasa) que se encuentra en la tabla hash 
	 * @param elem de tipo generico a borrar
	 * @return 0 si lo ha borrado
	 * o negativo en caso contrario (-1 si no existe, y –2 en otros casos) 
	 */
	abstract public int remove(T elem);

	/**
	 * Realiza una redispersion (aumentando el tamaño) al numero primo mayor
	 * que el doble del tamaño actual, recolocando los elementos 
	 * @return true si se realiza correctamente, sino false
	 */
	abstract protected boolean reDispersion();

	/**
	 * Realiza una redispersion inversa (disminuyendo el tamaño) al numero primo menor
	 * que la mitad del tamaño actual, recolocando los elementos
	 * @return true si se realiza correctamente, sino false
	 */
	abstract protected boolean inverseReDispersion();

	/**
	 * Convierte la tabla Hash en una cadena de String
	 * @return la cadena, String
	 */
	abstract public String toString();

	/**
	 * Calcula el resultado de aplicar la funcion hash basada en modulo, sobre el parametro  
	 * Utiliza hashCode() y convierte posibles negativos a positivos
	 * @param elem de tipo generico
	 * @return valor resultante de aplicar la funcion hash, int
	 */
	protected int fHash(T elem) { 
		int position = elem.hashCode()%getSize(); 
		return position<0 ? position+getSize() : position; 
	} 

	/**
	 * Calcula si un numero positivo es un numero primo, los negativos devuelve que no
	 * @param n, el numero que queremos comprobar, int
	 * @return true si es primo, false en caso contrario
	 */
	protected boolean isPositivePrime(int n) {
		if (n<2 || (n>2 && n%2==0))
			return false;
		if (n<=7)
			return true;
		for (int i=3; i*i<=n; i+=2) // impares
			if (n%i==0)
				return false; // no es primo
		return true;
	 }
	
	/**
	 * Comprueba si un numero es primo, y sino calcula el primo siguiente 
	 * @param n, el numero de partida, int
	 * @return n, si es primo
	 * sino, el primer primo encontrado MAYOR que n
	 */
	protected int nextPrimeNumber(int n) {
		if (n<=3) 
			return 3; // No permite primos menores de 3
		for (;!isPositivePrime(n);n++);
		return n;
	 }
	 
	/**
	 * Comprueba si un numero es primo, y sino calcula el primo anterior 
	 * @param n, el numero de partida, int
	 * @return n, si es primo
	 * sino, el primer primo encontrado MENOR que n
	 */
	protected int previousPrimeNumber(int n) {
		if (n<=3) // No permite primos menores de 3
			return 3;
		for(;!isPositivePrime(n);n--);
		return n;
	 }

}
