package test;

import java.util.Arrays;

import datos.QuickSortConcurrente;
/**
 * Clase de prueba para el algoritmo de ordenamiento QuickSort Concurrente.
 */
public class TestQuickSortConcurrente {

	public static void main(String[] args) {
		 System.out.println("-> QuickSort Concurrente:\n");

	        // Crear un array de prueba
	        int[] arr = {29, 10, 15, 12, 6, 3, 51, 3, 1, 21, 14, 37, 13, 4, 52, 5, 2, 22, 15, 38, 14};
	        System.out.println("Array original: " + Arrays.toString(arr));

	        // Registrar el tiempo inicial
	        long startTime = System.nanoTime();

	        // Ejecutar el algoritmo QuickSort concurrente
	        QuickSortConcurrente.concurrentQuicksort(arr);

	        // Registrar el tiempo final
	        long endTime = System.nanoTime();

	        // Calcular el tiempo de ejecución
	        long duration = endTime - startTime;

	        // Imprimir resultados
	        System.out.println("Array ordenado: " + Arrays.toString(arr));
	        System.out.println("Tiempo de ejecución: " + duration + " nanosegundos");
	}

}
