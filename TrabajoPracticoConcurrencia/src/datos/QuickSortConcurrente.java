package datos;

import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveAction;
import java.util.concurrent.TimeUnit;

/*
 * Clase que define un algoritmo de ordenamiento QuickSort concurrente.
 */
public class QuickSortConcurrente {

    /* Intercambia dos elementos en un array.
     * 
     * @param arr: El array en el que se realizará el intercambio.
     * @param i: Índice del primer elemento a intercambiar.
     * @param j: Índice del segundo elemento a intercambiar.
     */
    public static void swap(int[] arr, int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }

    /* Particiona el array en relación a un pivote.
     * 
     * @param arr: El array que se particionará.
     * @param low: Índice más bajo del array.
     * @param high: Índice más alto del array.
     * @return La posición del pivote después de la partición.
     */
    public static int partition(int[] arr, int low, int high) {
        int pivot = arr[high];  // Selecciona el pivote como el elemento en la posición 'high'
        int i = low - 1; // Inicializa el índice 'i' como el índice más bajo menos 1

        for (int j = low; j < high; j++) {
            if (arr[j] < pivot) {
                i++;  // Incrementa 'i' para mover el límite entre los elementos menores y mayores que el pivote
                swap(arr, i, j); // Intercambia el elemento actual con el elemento en la posición 'i'
            }
        }

        swap(arr, i + 1, high); // Coloca el pivote en su posición correcta

        return i + 1; // Devuelve la posición del pivote después de la partición
    }

    /* Ordena un subarray utilizando el algoritmo QuickSort concurrente.
     * 
     * @param arr: El array que se ordenará.
     */
    public static void concurrentQuicksort(int[] arr) {
        try (ForkJoinPool pool = new ForkJoinPool()) {
			try {
			    pool.invoke(new QuicksortTask(arr, 0, arr.length - 1));
			} finally {
			    pool.shutdown();
			    try {
			        if (!pool.awaitTermination(1, TimeUnit.MINUTES)) {
			            pool.shutdownNow();
			        }
			    } catch (InterruptedException e) {
			        pool.shutdownNow();
			        Thread.currentThread().interrupt();
			    }
			}
		}
    }

    /* Clase que define una tarea de QuickSort para ser utilizada con ForkJoinPool.
     */
    private static class QuicksortTask extends RecursiveAction {
        private static final long serialVersionUID = 1L;
        private int[] arr;
        private int low;
        private int high;

        public QuicksortTask(int[] arr, int low, int high) {
            this.arr = arr;
            this.low = low;
            this.high = high;
        }

        @Override
        protected void compute() {
            if (low < high) {
                int pivot = partition(arr, low, high);
                QuicksortTask leftTask = new QuicksortTask(arr, low, pivot - 1);
                QuicksortTask rightTask = new QuicksortTask(arr, pivot + 1, high);
                invokeAll(leftTask, rightTask);
            }
        }
    }

}
