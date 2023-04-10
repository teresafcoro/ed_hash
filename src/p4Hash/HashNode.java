package p4Hash;

/**
 * Clase HashNode
 * @author MariaTeresaFernandezCoro - UO263728
 */
public class HashNode<T> {
	
	private T info;		// info del nodo
	private int status;	// estado del nodo
	
	// valores posibles del estado del nodo
	public static final int BORRADO = -1;
	public static final int VACIO = 0;
	public static final int LLENO = 1;

	/**
	 * Constructor
	 */
	public HashNode() {
		info = null;
		status = VACIO;
	}
	
	/**
	 * Obtiene la informacion del nodo
	 * @return info de tipo generico
	 */
	public T getInfo() {
		return info;
	}
	
	/**
	 * Elimina el nodo
	 * Modificando su estado a borrado
	 */
	public void remove() {
		status = BORRADO;
	}
	
	/**
	 * Establece la informacion del nodo a partir del parametro pasado
	 * Modifica su estado a lleno
	 * @param elem de tipo generico
	 */
	public void setInfo(T elem) {
		info = elem;
		status = LLENO;
	}
	
	/**
	 * Obtiene el estado del nodo
	 * @return status, int
	 */
	public int getStatus() {
		return status;
	}

	/**
	 * Crea el formato string del hodo hash
	 * @return el formato string del nodo
	 */
	public String toString() {
		StringBuilder cadena = new StringBuilder("{");
		switch (getStatus()) {
		case LLENO:
			cadena.append(info);
			break;
		case VACIO:
			cadena.append("_E_");
			break;
		case BORRADO:
			cadena.append("_D_");
		}
		cadena.append("}");
		return cadena.toString();
	}

}
