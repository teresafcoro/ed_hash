package p4Hash;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

/**
 * Clase ClosedHashTableTest
 * Pruebas con JUnit de la clase ClosedHashTable
 * @author MariaTeresaFernandezCoro - UO263728
 */
class ClosedHashTableTest {
	
	/*
	 * Numeros primos del 0 al 100:
	 * 2, 3, 5, 7, 11, 13, 17, 19, 23, 29, 31, 37, 41, 43, 47, 53, 59, 61, 67, 71, 73, 79, 83, 89 y 97
	 */

	/**
	 * Test de una tabla hash cerrada con exploracion lineal
	 */
	@Test
	void testLineal() {
		ClosedHashTable<Integer> h = new ClosedHashTable<Integer>(13, 0);
		assertEquals("{_E_};{_E_};{_E_};{_E_};{_E_};{_E_};{_E_};{_E_};{_E_};{_E_};{_E_};{_E_};{_E_};[Size: 13 Num.Elems.: 0]", h.toString());
		assertEquals(0, h.add(5));
		assertEquals("{_E_};{_E_};{_E_};{_E_};{_E_};{5};{_E_};{_E_};{_E_};{_E_};{_E_};{_E_};{_E_};[Size: 13 Num.Elems.: 1]", h.toString());
		assertEquals(-2, h.add(null));
		assertEquals(0, h.add(8));
		assertEquals("{_E_};{_E_};{_E_};{_E_};{_E_};{5};{_E_};{_E_};{8};{_E_};{_E_};{_E_};{_E_};[Size: 13 Num.Elems.: 2]", h.toString());
		assertEquals(0, h.add(11));
		assertEquals("{_E_};{_E_};{_E_};{_E_};{_E_};{5};{_E_};{_E_};{8};{_E_};{_E_};{11};{_E_};[Size: 13 Num.Elems.: 3]", h.toString());
		assertEquals(0, h.add(9));
		assertEquals("{_E_};{_E_};{_E_};{_E_};{_E_};{5};{_E_};{_E_};{8};{9};{_E_};{11};{_E_};[Size: 13 Num.Elems.: 4]", h.toString());
		assertEquals(5, h.find(5));
		assertEquals(0, h.add(5));
		assertEquals(5, h.find(5));
		assertEquals("{_E_};{_E_};{_E_};{_E_};{_E_};{5};{5};{_E_};{8};{9};{_E_};{11};{_E_};[Size: 13 Num.Elems.: 5]", h.toString());
		assertEquals(null, h.find(null));
		assertEquals(null, h.find(10));
		assertEquals(0, h.add(7));
		assertEquals("{_E_};{_E_};{_E_};{_E_};{_E_};{5};{5};{7};{8};{9};{_E_};{11};{_E_};[Size: 13 Num.Elems.: 6]", h.toString());
		assertEquals(0, h.add(8));	// necesario realizar redispersion
		assertEquals("{_E_};{_E_};{_E_};{_E_};{_E_};{5};{5};{7};{8};{9};{8};{11};{_E_};{_E_};{_E_};{_E_};{_E_};{_E_};{_E_};{_E_};{_E_};{_E_};{_E_};{_E_};{_E_};{_E_};{_E_};{_E_};{_E_};[Size: 29 Num.Elems.: 7]", h.toString());
		assertEquals(-1, h.remove(50));
		assertEquals(0, h.add(6));
		assertEquals("{_E_};{_E_};{_E_};{_E_};{_E_};{5};{5};{7};{8};{9};{8};{11};{6};{_E_};{_E_};{_E_};{_E_};{_E_};{_E_};{_E_};{_E_};{_E_};{_E_};{_E_};{_E_};{_E_};{_E_};{_E_};{_E_};[Size: 29 Num.Elems.: 8]", h.toString());
		assertEquals(-2, h.remove(null));
		assertEquals(0, h.add(14));
		assertEquals("{_E_};{_E_};{_E_};{_E_};{_E_};{5};{5};{7};{8};{9};{8};{11};{6};{_E_};{14};{_E_};{_E_};{_E_};{_E_};{_E_};{_E_};{_E_};{_E_};{_E_};{_E_};{_E_};{_E_};{_E_};{_E_};[Size: 29 Num.Elems.: 9]", h.toString());
		assertEquals(0, h.remove(5));
		assertEquals("{_E_};{_E_};{_E_};{_E_};{_E_};{_D_};{5};{7};{8};{9};{8};{11};{6};{_E_};{14};{_E_};{_E_};{_E_};{_E_};{_E_};{_E_};{_E_};{_E_};{_E_};{_E_};{_E_};{_E_};{_E_};{_E_};[Size: 29 Num.Elems.: 8]", h.toString());
		assertEquals(0, h.remove(11));
		assertEquals("{_E_};{_E_};{_E_};{_E_};{_E_};{_D_};{5};{7};{8};{9};{8};{_D_};{6};{_E_};{14};{_E_};{_E_};{_E_};{_E_};{_E_};{_E_};{_E_};{_E_};{_E_};{_E_};{_E_};{_E_};{_E_};{_E_};[Size: 29 Num.Elems.: 7]", h.toString());;
		assertEquals(0, h.remove(9));
		assertEquals("{_E_};{_E_};{_E_};{_E_};{_E_};{_D_};{5};{7};{8};{_D_};{8};{_D_};{6};{_E_};{14};{_E_};{_E_};{_E_};{_E_};{_E_};{_E_};{_E_};{_E_};{_E_};{_E_};{_E_};{_E_};{_E_};{_E_};[Size: 29 Num.Elems.: 6]", h.toString());
		assertEquals(null, h.find(9));
		assertEquals(0, h.remove(8));
		assertEquals("{_E_};{_E_};{_E_};{_E_};{_E_};{_D_};{5};{7};{_D_};{_D_};{8};{_D_};{6};{_E_};{14};{_E_};{_E_};{_E_};{_E_};{_E_};{_E_};{_E_};{_E_};{_E_};{_E_};{_E_};{_E_};{_E_};{_E_};[Size: 29 Num.Elems.: 5]", h.toString());
		assertEquals(0, h.remove(6));	// necesario realizar redispersion inversa
		assertEquals("{_E_};{14};{_E_};{_E_};{_E_};{5};{_E_};{7};{8};{_E_};{_E_};{_E_};{_E_};[Size: 13 Num.Elems.: 4]", h.toString());
	}

	/**
	 * Test de una tabla hash cerrada con exploracion cuadratica
	 */
	@Test
	void testCuadratica() {
		ClosedHashTable<Integer> h = new ClosedHashTable<Integer>(13, 1);
		assertEquals("{_E_};{_E_};{_E_};{_E_};{_E_};{_E_};{_E_};{_E_};{_E_};{_E_};{_E_};{_E_};{_E_};[Size: 13 Num.Elems.: 0]", h.toString());
		assertEquals(0, h.add(5));
		assertEquals("{_E_};{_E_};{_E_};{_E_};{_E_};{5};{_E_};{_E_};{_E_};{_E_};{_E_};{_E_};{_E_};[Size: 13 Num.Elems.: 1]", h.toString());
		assertEquals(-2, h.add(null));
		assertEquals(0, h.add(8));
		assertEquals("{_E_};{_E_};{_E_};{_E_};{_E_};{5};{_E_};{_E_};{8};{_E_};{_E_};{_E_};{_E_};[Size: 13 Num.Elems.: 2]", h.toString());
		assertEquals(0, h.add(10));
		assertEquals("{_E_};{_E_};{_E_};{_E_};{_E_};{5};{_E_};{_E_};{8};{_E_};{10};{_E_};{_E_};[Size: 13 Num.Elems.: 3]", h.toString());
		assertEquals(0, h.add(8));
		assertEquals("{_E_};{_E_};{_E_};{_E_};{_E_};{5};{_E_};{_E_};{8};{8};{10};{_E_};{_E_};[Size: 13 Num.Elems.: 4]", h.toString());
		assertEquals(5, h.find(5));
		assertEquals(0, h.add(5));
		assertEquals(5, h.find(5));
		assertEquals("{_E_};{_E_};{_E_};{_E_};{_E_};{5};{5};{_E_};{8};{8};{10};{_E_};{_E_};[Size: 13 Num.Elems.: 5]", h.toString());
		assertEquals(null, h.find(null));
		assertEquals(null, h.find(11));
		assertEquals(0, h.add(11));
		assertEquals(11, h.find(11));
		assertEquals("{_E_};{_E_};{_E_};{_E_};{_E_};{5};{5};{_E_};{8};{8};{10};{11};{_E_};[Size: 13 Num.Elems.: 6]", h.toString());
		assertEquals(0, h.add(6));		// necesario realizar redispersion
		assertEquals("{_E_};{_E_};{_E_};{_E_};{_E_};{5};{5};{6};{8};{8};{10};{11};{_E_};{_E_};{_E_};{_E_};{_E_};{_E_};{_E_};{_E_};{_E_};{_E_};{_E_};{_E_};{_E_};{_E_};{_E_};{_E_};{_E_};[Size: 29 Num.Elems.: 7]", h.toString());
		assertEquals(-1, h.remove(50));
		assertEquals(0, h.add(7));
		assertEquals("{_E_};{_E_};{_E_};{_E_};{_E_};{5};{5};{6};{8};{8};{10};{11};{_E_};{_E_};{_E_};{_E_};{7};{_E_};{_E_};{_E_};{_E_};{_E_};{_E_};{_E_};{_E_};{_E_};{_E_};{_E_};{_E_};[Size: 29 Num.Elems.: 8]", h.toString());
		assertEquals(-2, h.remove(null));
		assertEquals(0, h.add(14));
		assertEquals("{_E_};{_E_};{_E_};{_E_};{_E_};{5};{5};{6};{8};{8};{10};{11};{_E_};{_E_};{14};{_E_};{7};{_E_};{_E_};{_E_};{_E_};{_E_};{_E_};{_E_};{_E_};{_E_};{_E_};{_E_};{_E_};[Size: 29 Num.Elems.: 9]", h.toString());
		assertEquals(0, h.remove(5));
		assertEquals("{_E_};{_E_};{_E_};{_E_};{_E_};{_D_};{5};{6};{8};{8};{10};{11};{_E_};{_E_};{14};{_E_};{7};{_E_};{_E_};{_E_};{_E_};{_E_};{_E_};{_E_};{_E_};{_E_};{_E_};{_E_};{_E_};[Size: 29 Num.Elems.: 8]", h.toString());
		assertEquals(0, h.remove(11));
		assertEquals("{_E_};{_E_};{_E_};{_E_};{_E_};{_D_};{5};{6};{8};{8};{10};{_D_};{_E_};{_E_};{14};{_E_};{7};{_E_};{_E_};{_E_};{_E_};{_E_};{_E_};{_E_};{_E_};{_E_};{_E_};{_E_};{_E_};[Size: 29 Num.Elems.: 7]", h.toString());
		assertEquals(0, h.remove(8));
		assertEquals("{_E_};{_E_};{_E_};{_E_};{_E_};{_D_};{5};{6};{_D_};{8};{10};{_D_};{_E_};{_E_};{14};{_E_};{7};{_E_};{_E_};{_E_};{_E_};{_E_};{_E_};{_E_};{_E_};{_E_};{_E_};{_E_};{_E_};[Size: 29 Num.Elems.: 6]", h.toString());
		assertEquals(8, h.find(8));
		assertEquals(0, h.remove(8));
		assertEquals(null, h.find(8));
		assertEquals("{_E_};{_E_};{_E_};{_E_};{_E_};{_D_};{5};{6};{_D_};{_D_};{10};{_D_};{_E_};{_E_};{14};{_E_};{7};{_E_};{_E_};{_E_};{_E_};{_E_};{_E_};{_E_};{_E_};{_E_};{_E_};{_E_};{_E_};[Size: 29 Num.Elems.: 5]", h.toString());
		assertEquals(0, h.remove(6));	// necesario realizar redispersion inversa
		assertEquals("{_E_};{14};{_E_};{_E_};{_E_};{5};{_E_};{7};{_E_};{_E_};{10};{_E_};{_E_};[Size: 13 Num.Elems.: 4]", h.toString());
	}
	
	/**
	 * Test de una tabla hash cerrada con exploracion doble
	 */
	@Test
	void testDoble() {
		ClosedHashTable<Integer> h = new ClosedHashTable<Integer>(13, 2);
		assertEquals("{_E_};{_E_};{_E_};{_E_};{_E_};{_E_};{_E_};{_E_};{_E_};{_E_};{_E_};{_E_};{_E_};[Size: 13 Num.Elems.: 0]", h.toString());
		assertEquals(0, h.add(5));
		assertEquals("{_E_};{_E_};{_E_};{_E_};{_E_};{5};{_E_};{_E_};{_E_};{_E_};{_E_};{_E_};{_E_};[Size: 13 Num.Elems.: 1]", h.toString());
		assertEquals(-2, h.add(null));
		assertEquals(0, h.add(8));
		assertEquals("{_E_};{_E_};{_E_};{_E_};{_E_};{5};{_E_};{_E_};{8};{_E_};{_E_};{_E_};{_E_};[Size: 13 Num.Elems.: 2]", h.toString());
		assertEquals(0, h.add(10));
		assertEquals("{_E_};{_E_};{_E_};{_E_};{_E_};{5};{_E_};{_E_};{8};{_E_};{10};{_E_};{_E_};[Size: 13 Num.Elems.: 3]", h.toString());
		assertEquals(0, h.add(8));
		assertEquals("{8};{_E_};{_E_};{_E_};{_E_};{5};{_E_};{_E_};{8};{_E_};{10};{_E_};{_E_};[Size: 13 Num.Elems.: 4]", h.toString());
		assertEquals(5, h.find(5));
		assertEquals(0, h.add(5));
		assertEquals(5, h.find(5));
		assertEquals("{8};{_E_};{_E_};{5};{_E_};{5};{_E_};{_E_};{8};{_E_};{10};{_E_};{_E_};[Size: 13 Num.Elems.: 5]", h.toString());
		assertEquals(null, h.find(null));
		assertEquals(null, h.find(11));
		assertEquals(0, h.add(11));
		assertEquals(11, h.find(11));
		assertEquals("{8};{_E_};{_E_};{5};{_E_};{5};{_E_};{_E_};{8};{_E_};{10};{11};{_E_};[Size: 13 Num.Elems.: 6]", h.toString());
		assertEquals(0, h.add(6));		// necesario realizar redispersion
		assertEquals("{5};{_E_};{_E_};{_E_};{_E_};{5};{6};{_E_};{8};{_E_};{10};{11};{_E_};{_E_};{_E_};{_E_};{_E_};{_E_};{_E_};{_E_};{_E_};{8};{_E_};{_E_};{_E_};{_E_};{_E_};{_E_};{_E_};[Size: 29 Num.Elems.: 7]", h.toString());
		assertEquals(-1, h.remove(50));
		assertEquals(0, h.add(7));
		assertEquals("{5};{_E_};{_E_};{_E_};{_E_};{5};{6};{7};{8};{_E_};{10};{11};{_E_};{_E_};{_E_};{_E_};{_E_};{_E_};{_E_};{_E_};{_E_};{8};{_E_};{_E_};{_E_};{_E_};{_E_};{_E_};{_E_};[Size: 29 Num.Elems.: 8]", h.toString());
		assertEquals(-2, h.remove(null));
		assertEquals(0, h.add(6));
		assertEquals("{5};{_E_};{_E_};{_E_};{_E_};{5};{6};{7};{8};{_E_};{10};{11};{_E_};{_E_};{_E_};{_E_};{_E_};{_E_};{_E_};{_E_};{_E_};{8};{_E_};{6};{_E_};{_E_};{_E_};{_E_};{_E_};[Size: 29 Num.Elems.: 9]", h.toString());
		assertEquals(0, h.remove(5));
		assertEquals("{5};{_E_};{_E_};{_E_};{_E_};{_D_};{6};{7};{8};{_E_};{10};{11};{_E_};{_E_};{_E_};{_E_};{_E_};{_E_};{_E_};{_E_};{_E_};{8};{_E_};{6};{_E_};{_E_};{_E_};{_E_};{_E_};[Size: 29 Num.Elems.: 8]", h.toString());
		assertEquals(0, h.remove(11));
		assertEquals("{5};{_E_};{_E_};{_E_};{_E_};{_D_};{6};{7};{8};{_E_};{10};{_D_};{_E_};{_E_};{_E_};{_E_};{_E_};{_E_};{_E_};{_E_};{_E_};{8};{_E_};{6};{_E_};{_E_};{_E_};{_E_};{_E_};[Size: 29 Num.Elems.: 7]", h.toString());
		assertEquals(0, h.remove(8));
		assertEquals("{5};{_E_};{_E_};{_E_};{_E_};{_D_};{6};{7};{_D_};{_E_};{10};{_D_};{_E_};{_E_};{_E_};{_E_};{_E_};{_E_};{_E_};{_E_};{_E_};{8};{_E_};{6};{_E_};{_E_};{_E_};{_E_};{_E_};[Size: 29 Num.Elems.: 6]", h.toString());
		assertEquals(8, h.find(8));
		assertEquals(0, h.remove(8));
		assertEquals(null, h.find(8));
		assertEquals("{5};{_E_};{_E_};{_E_};{_E_};{_D_};{6};{7};{_D_};{_E_};{10};{_D_};{_E_};{_E_};{_E_};{_E_};{_E_};{_E_};{_E_};{_E_};{_E_};{_D_};{_E_};{6};{_E_};{_E_};{_E_};{_E_};{_E_};[Size: 29 Num.Elems.: 5]", h.toString());
		assertEquals(0, h.remove(6));	// necesario realizar redispersion inversa
		assertEquals("{_E_};{_E_};{_E_};{_E_};{_E_};{5};{6};{7};{_E_};{_E_};{10};{_E_};{_E_};[Size: 13 Num.Elems.: 4]", h.toString());
	}
	
}
