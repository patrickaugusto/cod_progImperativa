package org.example;

public class CountingSort {

  public static Funcionario[] ordenarPorMatricula(Funcionario[] vet) {
    int n = vet.length;
    int max = getMax(vet);

    Funcionario[] output = new Funcionario[n];
    int[] count = new int[max + 1];

    for (int i = 0; i < n; i++) {
      count[vet[i].matricula]++;
    }

    for (int i = 1; i <= max; i++) {
      count[i] += count[i - 1];
    }

    for (int i = n - 1; i >= 0; i--) {
      output[count[vet[i].matricula] - 1] = vet[i];
      count[vet[i].matricula]--;
    }

    return output;
  }

  public static Funcionario[] ordenarPorNome(Funcionario[] vet) {
    int n = vet.length;

    int maxLength = 0;
    for (Funcionario fun : vet) {
      maxLength = Math.max(maxLength, fun.nome.length());
    }

    Funcionario[] output = new Funcionario[n];

    for (int i = maxLength - 1; i >= 0; i--) {
      int[] count = new int[256];

      for (Funcionario fun : vet) {
        int index = (i < fun.nome.length()) ? fun.nome.charAt(i) : 0;
        count[index]++;
      }

      for (int j = 1; j < count.length; j++) {
        count[j] += count[j - 1];
      }

      for (int j = n - 1; j >= 0; j--) {
        int index = (i < vet[j].nome.length()) ? vet[j].nome.charAt(i) : 0;
        output[count[index] - 1] = vet[j];
        count[index]--;
      }

      System.arraycopy(output, 0, vet, 0, n);
    }

    return vet;
  }

  private static int getMax(Funcionario[] vet) {
    int max = vet[0].matricula;

    for (int i = 1; i < vet.length; i++) {
      max = Math.max(max, vet[i].matricula);
    }

    return max;
  }
}
