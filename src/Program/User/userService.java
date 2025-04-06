package Program.User;

import Entities.User;

import java.util.HashMap;

public class userService {
    private final HashMap<String, User> users = new HashMap<>();

    public void registerUser(String name, String email, String password) {
        if (users.containsKey(email)) {
            System.out.println("Erro: Este e-mail já está registrado.");
            return;
        }

        if (password.length() <= 8) {
            System.out.println("Erro: A senha deve ter mais que 8 caracteres.");
            return;
        }

        User newUser = new User(name, email, password);
        users.put(email, newUser);
        System.out.println("Conta criada com sucesso para: " + name);
    }

    public void login(String email, String password) {
        if (!users.containsKey(email)) {
            System.out.println("Erro: E-mail não encontrado.");
            return;
        }

        User user = users.get(email);
        if (!user.getPassword().equals(password)) {
            System.out.println("Erro: Senha incorreta.");
            return;
        }

        System.out.println("Login realizado com sucesso! Bem-vindo, " + user.getName());
    }
}
