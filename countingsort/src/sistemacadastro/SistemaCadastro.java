/*
Exercícios:
1. Construir e exbir uma lista com 5 funcionários 
2. Pesquisar um funcionário pelo nome. Apresentar os dados do funcionario.
3. Ordenar pelo nome a lista de funcionários a partir do exercício 1.
*/


package sistemacadastro;

import java.util.Scanner;

public class SistemaCadastro {

    public static void main(String[] args) {
        Scanner s = new Scanner(System.in);

        int nf = 5; // número de funcionários

        // declaração do vetor de funcionários
        Funcionario[] f;
        f = new Funcionario[nf];

        // laço para cadastrar os funcionários
        for (int i = 0; i < nf; i++) {

            //inserir os valores dos nomes e ids
            System.out.println("Digite nome do " + (i + 1) + "o funcionario: ");
            String n = s.nextLine();
            System.out.println("Digite o id do " + (i + 1) + "o funcionario: ");
            int id = s.nextInt();

            //guardar os valores correspondentes com seu tipo tlg
            f[i] = new Funcionario();
            f[i].nome = n;
            f[i].id = id;
            s.nextLine();
        }

        //exibir sem ordenação
        System.out.println("Lista de funcionários antes da ordenação: ");
        exibeFuncionario(f);

        System.out.println("\n");

        //exibir com ordenação
        System.out.println("Lista de funcionários ordenada por id: ");
        f = contingsort(f);
        exibeFuncionario(f);

    }

    public static void exibeFuncionario(Funcionario[] f) {
        for (int i = 0; i < f.length; i++) {
            System.out.print("Nome: " + f[i].nome);
            System.out.println(" id: " + f[i].id);
        }
    }

    public static Funcionario[] contingsort(Funcionario[] vet) {

        //pegar numero maior do vetor
        int max = getMax(vet);

        //criar outro vetor \o/
        Funcionario[] output = new Funcionario[vet.length];
        int[] count = new int[max + 1];

        // Conta a ocorrência de cada elemento no array
        for (int i = 0; i < vet.length; i++) {
            count[vet[i].id]++;
        }

        // Atualiza o array de contagem para armazenar as posições corretas
        for (int i = 1; i <= max; i++) {
            count[i] += count[i - 1];
        }

        // Constrói o array de saída
        for (int i = vet.length - 1; i >= 0; i--) {
            output[count[vet[i].id] - 1] = vet[i];
            count[vet[i].id]--;
        }

        // Copia o array ordenado de volta para o array original
        System.arraycopy(output, 0, vet, 0, vet.length);

        return vet;
    }

    //função para pegar o maior id
    private static int getMax(Funcionario[] vet) {
        int max = vet[0].id;
        for (int i = 1; i < vet.length; i++) {
            if (vet[i].id > max) {
                max = vet[i].id;
            }
        }
        return max;
    }
}