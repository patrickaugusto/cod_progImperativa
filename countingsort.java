public class countingsort {


    public static void main(String[] args) {
        int[] array = {4, 2, 1, 3, 7, 5, 3};

        System.out.println("Array original:");
        printArray(array);

        countingSort(array);

        System.out.println("Array ordenado:");
        printArray(array);
    }


    public static void countingSort(int[] array) {
        int max = getMax(array);
        int[] count = new int[max + 1];
        int[] output = new int[array.length];

        // Conta a ocorrência de cada elemento no array
        for (int i = 0; i < array.length; i++) {
            count[array[i]]++;
        }

        // Atualiza o array de contagem para armazenar as posições corretas
        for (int i = 1; i <= max; i++) {
            count[i] += count[i - 1];
        }

        // Constrói o array de saída
        for (int i = array.length - 1; i >= 0; i--) {
            output[count[array[i]] - 1] = array[i];
            count[array[i]]--;
        }

        // Copia o array ordenado de volta para o array original
        System.arraycopy(output, 0, array, 0, array.length);
    }

    private static int getMax(int[] array) {
        int max = array[0];
        for (int i = 1; i < array.length; i++) {
            if (array[i] > max) {
                max = array[i];
            }
        }
        return max;
    }

    public static void printArray(int[] array) {
        for (int value : array) {
            System.out.print(value + " ");
        }
        System.out.println();
    }



}
