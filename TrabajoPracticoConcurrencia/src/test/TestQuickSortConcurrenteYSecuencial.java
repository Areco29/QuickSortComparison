package test;
import java.util.Random;

import datos.QuickSortConcurrente;
import datos.QuickSortSecuencial;

/**
 * Clase de prueba para comparar los algoritmos QuickSort Concurrente y Secuencial.
 */
public class TestQuickSortConcurrenteYSecuencial {

    public static void main(String[] args) {
        probarConArray(10);
        probarConArray(1000);
        probarConArray(100000);
        probarConArray(1000000);
    }

    private static void probarConArray(int tamano) {
        // Generar un array aleatorio de tamaño 'tamano'
        int[] array = new Random().ints(tamano, 0, 1000).toArray();

        // Probar QuickSort Concurrente
        long startTime = System.nanoTime();
        QuickSortConcurrente.concurrentQuicksort(array);
        long endTime = System.nanoTime();
        long tiempoConcurrente = endTime - startTime;

        // Probar QuickSort Secuencial
        array = new Random().ints(tamano, 0, 1000).toArray();
        startTime = System.nanoTime();
        QuickSortSecuencial.quicksort(array,0,array.length-1); 
        endTime = System.nanoTime();
        long tiempoSecuencial = endTime - startTime;

        // Imprimir resultados
        System.out.println("\n=========================");
        System.out.println("\nProbando con Array de " + tamano + " elementos:");
        System.out.println("Tiempo Concurrente: " + tiempoConcurrente + " nanosegundos");
        System.out.println("Tiempo Secuencial: " + tiempoSecuencial + " nanosegundos");
    }
}
