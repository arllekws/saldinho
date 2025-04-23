package Program.app;

import Entities.User;
import Program.User.UserService;
import Program.Expense.ExpenseService;
import Program.Goal.GoalService;
import Entities.FinancialGoal;
import java.time.LocalDate;


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
                        clearScreen();
                        ExpenseService expenseService = new ExpenseService();
                        GoalService goalService = new GoalService();
                        boolean logado = true;

                        while (logado) {
                            System.out.println("\nOlá, " + usuarioLogado.getName());
                            System.out.println("1. Registrar Despesa");
                            System.out.println("2. Ver Despesas");
                            System.out.println("3. Apagar Todas as Despesas");
                            System.out.println("4. Metas Financeiras");
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
                                case "4":
                                    boolean gerenciarMetas = true;
                                    while (gerenciarMetas) {
                                        System.out.println("\n---GERENCIAR METAS---");
                                        System.out.println("1. Criar Nova Meta");
                                        System.out.println("2. Ver Todas as Metas");
                                        System.out.println("3. Adicionar Economia");
                                        System.out.println("0. Voltar");
                                        System.out.print("Escolha uma opção: ");
                                        String opcaoMeta = scanner.nextLine();

                                        switch (opcaoMeta) {
                                            case "1":
                                                System.out.print("Nome da meta: ");
                                                String nomeMeta = scanner.nextLine();
                                                System.out.print("Valor da meta (R$): ");
                                                double valorMeta = Double.parseDouble(scanner.nextLine());
                                                System.out.print("Prazo em meses: ");
                                                int prazo = Integer.parseInt(scanner.nextLine());
                                                LocalDate fim = LocalDate.now().plusMonths(prazo);
                                                FinancialGoal meta = new FinancialGoal(nomeMeta, valorMeta, fim);
                                                goalService.createGoal(meta);
                                                System.out.println("Meta criada com sucesso!");
                                                System.out.printf("Você precisa economizar R$%.2f por mês.\n", meta.getMonthlySaving());
                                                break;
                                            case "2":
                                                System.out.println("\n--- METAS CADASTRADAS ---");
                                                for (FinancialGoal g : goalService.getAllGoals()) {
                                                    System.out.println(g);
                                                    System.out.printf("Progresso: %.2f%%\n", g.getProgressPercentage());
                                                    System.out.printf("Meta mensal: R$%.2f\n", g.getMonthlySaving());
                                                }
                                                break;
                                            case "3":
                                                System.out.println("\n--- ADICIONAR ECONOMIA ---");
                                                int i = 1;
                                                for (FinancialGoal g : goalService.getAllGoals()) {
                                                    System.out.println(i + ". " + g);
                                                    i++;
                                                }
                                                if (i == 1) {
                                                    System.out.println("Nenhuma meta criada.");
                                                    break;
                                                }
                                                System.out.print("Escolha a meta (número): ");
                                                int escolhaMeta = Integer.parseInt(scanner.nextLine()) - 1;
                                                System.out.print("Valor economizado (R$): ");
                                                double valorEco = Double.parseDouble(scanner.nextLine());
                                                goalService.getAllGoals().get(escolhaMeta).addSavings(valorEco);
                                                System.out.println("Valor adicionado com sucesso!");
                                                break;
                                            case "0":
                                                gerenciarMetas = false;
                                                break;
                                            default:
                                                System.out.println("Opção inválida.");
                                        }
                                    }
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

    public static void clearScreen() {
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }
}


