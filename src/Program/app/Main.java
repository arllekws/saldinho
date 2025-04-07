package Program.app;

import Entities.User;
import Program.User.UserService;
import Program.Expense.ExpenseService;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        UserService userService = new UserService();

        while (true) {
            System.out.println("\n--- MENU PRINCIPAL ---");
            System.out.println("1. Registrar");
            System.out.println("2. Login");
            System.out.println("0. Sair");
            System.out.print("Escolha uma opção: ");
            String opcao = scanner.nextLine();

            switch (opcao) {
                case "1":
                    System.out.print("Nome: ");
                    String nome = scanner.nextLine();
                    System.out.print("E-mail: ");
                    String email = scanner.nextLine().trim();
                    System.out.print("Senha: ");
                    String senha = scanner.nextLine().trim();
                    userService.registerUser(nome, email, senha);
                    break;
                case "2":
                    System.out.print("E-mail: ");
                    String loginEmail = scanner.nextLine().trim();
                    System.out.print("Senha: ");
                    String loginSenha = scanner.nextLine().trim();
                    User usuarioLogado = userService.login(loginEmail, loginSenha);

                    if (usuarioLogado != null) {
                        ExpenseService expenseService = new ExpenseService();
                        boolean logado = true;

                        while (logado) {
                            System.out.println("\nOlá, " + usuarioLogado.getName());
                            System.out.println("1. Registrar Despesa");
                            System.out.println("2. Ver Despesas");
                            System.out.println("3. Apagar Todas as Despesas");
                            System.out.println("0. Sair da Conta");
                            System.out.print("Escolha uma opção: ");
                            String escolha = scanner.nextLine();

                            switch (escolha) {
                                case "1":
                                    System.out.print("Descrição da despesa: ");
                                    String desc = scanner.nextLine();
                                    System.out.print("Valor: ");
                                    double valor = Double.parseDouble(scanner.nextLine());
                                    expenseService.addExpense(desc, valor);
                                    break;
                                case "2":
                                    expenseService.listExpenses();
                                    break;
                                case "3":
                                    expenseService.clearExpenses();
                                    break;
                                case "0":
                                    logado = false;
                                    System.out.println("Logout feito com sucesso.");
                                    break;
                                default:
                                    System.out.println("Opção inválida.");
                            }
                        }
                    }
                    break;
                case "0":
                    System.out.println("Saindo do sistema...");
                    return;
                default:
                    System.out.println("Opção inválida.");
            }
        }
    }
}

