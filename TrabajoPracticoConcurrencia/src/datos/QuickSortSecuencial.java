package datos;

/*
 * Clase que define un algoritmo de ordenamiento QuickSort.
 */

public class QuickSortSecuencial {
	
	
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

        swap(arr, i + 1, high);// Coloca el pivote en su posición correcta

        return i + 1;// Devuelve la posición del pivote después de la partición
    }
    
    /* Ordena un subarray utilizando el algoritmo QuickSort.
     * 
     * @param arr: El array que se ordenará.
     * @param low: Índice más bajo del subarray.
     * @param high: Índice más alto del subarray.
     */
    public static void quicksort(int[] arr, int low, int high) {
        if (low < high) {
            int pivot = partition(arr, low, high); // Encuentra el pivote correcto
            quicksort(arr, low, pivot - 1);// Ordena recursivamente los elementos a la izquierda del pivote
            quicksort(arr, pivot + 1, high);// Ordena recursivamente los elementos a la derecha del pivote
        }
    }

}










