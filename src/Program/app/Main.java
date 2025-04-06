package Program.app;

import Program.User.userService;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        userService userService = new userService();

        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("\nEscolha uma opção:");
            System.out.println("1 - Registrar");
            System.out.println("2 - Login");
            System.out.println("0 - Sair");
            System.out.print("Opção: ");
            int option = scanner.nextInt();
            scanner.nextLine();

            if (option == 1) {
                System.out.print("Nome: ");
                String name = scanner.nextLine();
                System.out.print("E-mail: ");
                String email = scanner.nextLine();
                System.out.print("Senha: ");
                String password = scanner.nextLine();
                userService.registerUser(name, email, password);
            } else if (option == 2) {
                System.out.print("E-mail: ");
                String email = scanner.nextLine();
                System.out.print("Senha: ");
                String password = scanner.nextLine();
                userService.login(email, password);
            } else if (option == 0) {
                System.out.println("Saindo...");
                break;
            } else {
                System.out.println("Opção inválida.");
            }
        }

        scanner.close();
    }
}

