import java.util.Scanner;
import model.*;

public class CadastroPOO {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        PessoaFisicaRepo repoPF = new PessoaFisicaRepo();
        PessoaJuridicaRepo repoPJ = new PessoaJuridicaRepo();

        int opcao;

        do {
            System.out.println("====================================");
            System.out.println("1 – Incluir Pessoa");
            System.out.println("2 – Alterar Pessoa");
            System.out.println("3 – Excluir Pessoa");
            System.out.println("4 – Buscar pelo Id");
            System.out.println("5 – Exibir Todos");
            System.out.println("6 – Persistir Dados");
            System.out.println("7 – Recuperar Dados");
            System.out.println("0 – Finalizar Programa");
            System.out.println("====================================");

            opcao = sc.nextInt();
            sc.nextLine(); // limpeza

            switch(opcao) {

                case 1 -> { // INCLUIR
                    System.out.println("F - Pessoa Fisica | J - Pessoa Juridica");
                    char tipo = sc.nextLine().toUpperCase().charAt(0);

                    System.out.print("Digite o id: ");
                    int id = sc.nextInt();
                    sc.nextLine();

                    System.out.println("Insira os dados...");
                    System.out.print("Nome: ");
                    String nome = sc.nextLine();

                    if (tipo == 'F') {
                        System.out.print("CPF: ");
                        String cpf = sc.nextLine();
                        System.out.print("Idade: ");
                        int idade = sc.nextInt();
                        sc.nextLine();

                        repoPF.inserir(new PessoaFisica(id, nome, cpf, idade));
                    } 
                    else if (tipo == 'J') {
                        System.out.print("CNPJ: ");
                        String cnpj = sc.nextLine();

                        repoPJ.inserir(new PessoaJuridica(id, nome, cnpj));
                    }
                }

                case 2 -> { // ALTERAR
                    System.out.println("F - PF | J - PJ");
                    char tipo = sc.nextLine().toUpperCase().charAt(0);

                    System.out.print("Id: ");
                    int id = sc.nextInt();
                    sc.nextLine();

                    if (tipo == 'F') {
                        PessoaFisica p = repoPF.obter(id);
                        if (p != null) {
                            System.out.println("Nome atual: " + p.getNome());
                            System.out.print("Novo nome: ");
                            String nome = sc.nextLine();

                            System.out.print("Novo CPF: ");
                            String cpf = sc.nextLine();

                            System.out.print("Nova idade: ");
                            int idade = sc.nextInt();
                            sc.nextLine();

                            repoPF.alterar(new PessoaFisica(id, nome, cpf, idade));
                        }
                    } else {
                        PessoaJuridica p = repoPJ.obter(id);
                        if (p != null) {
                            System.out.println("Nome atual: " + p.getNome());
                            System.out.print("Novo nome: ");
                            String nome = sc.nextLine();
                            System.out.print("Novo CNPJ: ");
                            String cnpj = sc.nextLine();
                            repoPJ.alterar(new PessoaJuridica(id, nome, cnpj));
                        }
                    }
                }

                case 3 -> { // EXCLUIR
                    System.out.println("F - PF | J - PJ");
                    char tipo = sc.nextLine().toUpperCase().charAt(0);

                    System.out.print("Id: ");
                    int id = sc.nextInt();
                    sc.nextLine();

                    if (tipo == 'F') repoPF.excluir(id);
                    else repoPJ.excluir(id);
                }

                case 4 -> { // BUSCAR POR ID
                    System.out.println("F - PF | J - PJ");
                    char tipo = sc.nextLine().toUpperCase().charAt(0);

                    System.out.print("Id: ");
                    int id = sc.nextInt();
                    sc.nextLine();

                    if (tipo == 'F') {
                        PessoaFisica p = repoPF.obter(id);
                        if (p != null) p.exibir();
                    } else {
                        PessoaJuridica p = repoPJ.obter(id);
                        if (p != null) p.exibir();
                    }
                }

                case 5 -> { // EXIBIR TODOS
                    System.out.println("F - PF | J - PJ");
                    char tipo = sc.nextLine().toUpperCase().charAt(0);

                    if (tipo == 'F') {
                        for (PessoaFisica p : repoPF.obterTodos()) p.exibir();
                    } else {
                        for (PessoaJuridica p : repoPJ.obterTodos()) p.exibir();
                    }
                }

                case 6 -> { // PERSISTIR
                    System.out.print("Prefixo: ");
                    String prefixo = sc.nextLine();

                    try {
                        repoPF.persistir(prefixo + ".fisica.bin");
                        repoPJ.persistir(prefixo + ".juridica.bin");
                        System.out.println("Dados salvos.");
                    } catch (Exception e) {
                        System.out.println("Erro ao salvar: " + e.getMessage());
                    }
                }

                case 7 -> { // RECUPERAR
                    System.out.print("Prefixo: ");
                    String prefixo = sc.nextLine();

                    try {
                        repoPF.recuperar(prefixo + ".fisica.bin");
                        repoPJ.recuperar(prefixo + ".juridica.bin");
                        System.out.println("Dados recuperados.");
                    } catch (Exception e) {
                        System.out.println("Erro ao recuperar: " + e.getMessage());
                    }
                }

                case 0 -> System.out.println("Finalizando...");

            }

        } while (opcao != 0);

        sc.close();
    }
}