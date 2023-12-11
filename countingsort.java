import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

class Funcionario {
    String matricula;
    String cargo;
    String nome;
    double salario;

    // Construtor, métodos getters e setters podem ser adicionados conforme necessário
}

public class main {
    private static final String NOME_ARQUIVO = "funcionarios.txt";

    public static void main(String[] args) {
        ArrayList<Funcionario> funcionarios = new ArrayList<>();
        lerArquivo(funcionarios);
        exibirMenu(funcionarios);
        salvarArquivo(funcionarios);
    }

    private static void lerArquivo(ArrayList<Funcionario> funcionarios) {
        try (Scanner scanner = new Scanner(new File(NOME_ARQUIVO))) {
            while (scanner.hasNextLine()) {
                String linha = scanner.nextLine();
                String[] dados = linha.split(",");
                Funcionario funcionario = new Funcionario();
                funcionario.matricula = dados[0];
                funcionario.cargo = converterCodigoParaCargo(dados[1]);
                funcionario.nome = dados[2].replace("-", " ");
                funcionario.salario = Double.parseDouble(dados[3]);
                funcionarios.add(funcionario);
            }
        } catch (FileNotFoundException e) {
            System.err.println("Arquivo não encontrado. Criando novo arquivo...");
        }
    }

    private static void exibirMenu(ArrayList<Funcionario> funcionarios) {
        Scanner scanner = new Scanner(System.in);

        int opcao;
        do {
            System.out.println("\nMenu:");
            System.out.println("1. Consultar funcionários");
            System.out.println("2. Alterar dados de um funcionário");
            System.out.println("3. Exibir todos os funcionários");
            System.out.println("4. Ordenar por matrícula (Counting Sort)");
            System.out.println("5. Sair");
            System.out.print("Escolha a opção: ");

            opcao = scanner.nextInt();

            switch (opcao) {
                case 1:
                    consultarFuncionarios(funcionarios);
                    break;
                case 2:
                    alterarFuncionario(funcionarios);
                    break;
                case 3:
                    exibirTodosFuncionarios(funcionarios);
                    break;
                case 4:
                    ordenarPorMatricula(funcionarios);
                    break;
                case 5:
                    System.out.println("Saindo do programa...");
                    break;
                default:
                    System.out.println("Opção inválida. Tente novamente.");
            }

        } while (opcao != 5);

        scanner.close();
    }

    private static void ordenarPorMatricula(ArrayList<Funcionario> funcionarios) {
        funcionarios = contingsort(funcionarios);
        System.out.println("Funcionários ordenados por matrícula:");
        exibirTodosFuncionarios(funcionarios);
    }

    private static void consultarFuncionarios(ArrayList<Funcionario> funcionarios) {
        // Implementar lógica de consulta conforme necessário
        System.out.println("\nConsulta de funcionários");
        // Exemplo: listar todos os funcionários
        exibirTodosFuncionarios(funcionarios);
    }

    private static void alterarFuncionario(ArrayList<Funcionario> funcionarios) {
        // Implementar lógica de alteração conforme necessário
        Scanner scanner = new Scanner(System.in);
        System.out.print("\nInforme a matrícula do funcionário a ser alterado: ");
        String matricula = scanner.nextLine();

        for (Funcionario funcionario : funcionarios) {
            if (funcionario.matricula.equals(matricula)) {
                System.out.print("Novo salário: ");
                funcionario.salario = scanner.nextDouble();
                System.out.println("Dados do funcionário atualizados.");
                return;
            }
        }

        System.out.println("Funcionário não encontrado com a matrícula fornecida.");
    }

    private static void exibirTodosFuncionarios(ArrayList<Funcionario> funcionarios) {
        System.out.println("\nLista de todos os funcionários:");
        for (Funcionario funcionario : funcionarios) {
            System.out.println("Matrícula: " + funcionario.matricula);
            System.out.println("Cargo: " + funcionario.cargo);
            System.out.println("Nome: " + funcionario.nome);
            System.out.println("Salário: " + funcionario.salario);
            System.out.println("-----");
        }
    }

    private static void salvarArquivo(ArrayList<Funcionario> funcionarios) {
        try (PrintWriter writer = new PrintWriter(new FileWriter(NOME_ARQUIVO))) {
            for (Funcionario funcionario : funcionarios) {
                String nomeFormatado = funcionario.nome.replace(" ", "-");
                writer.println(funcionario.matricula + "," + funcionario.cargo + "," + nomeFormatado + "," + funcionario.salario);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static String converterCodigoParaCargo(String codigo) {
        return "Cargo";
    }

    private static ArrayList<Funcionario> contingsort(ArrayList<Funcionario> vet) {
        int max = getMax(vet);

        //criação de outra array list
        ArrayList<Funcionario> output = new ArrayList<>(vet.size());
        int[] count = new int[max + 1];

        // Conta a ocorrência de cada elemento no array
        for (Funcionario funcionario : vet) {
            count[Integer.parseInt(funcionario.matricula)]++;
        }

        // Atualiza o array de contagem para armazenar as posições corretas
        for (int i = 1; i <= max; i++) {
            count[i] += count[i - 1];
        }

        // Constrói o array de saída
        for (int i = vet.size() - 1; i >= 0; i--) {
            output.add(count[Integer.parseInt(vet.get(i).matricula)] - 1, vet.get(i));
            count[Integer.parseInt(vet.get(i).matricula)]--;
        }

        return output;
    }

    //pegar o maior numero de matricula do objeto funcionario.
    private static int getMax(ArrayList<Funcionario> vet) {
        int max = Integer.parseInt(vet.get(0).matricula);
        for (int i = 1; i < vet.size(); i++) {
            if (Integer.parseInt(vet.get(i).matricula) > max) {
                max = Integer.parseInt(vet.get(i).matricula);
            }
        }
        return max;
    }
}
