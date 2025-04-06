package User;

import java.util.HashMap;

public class UserService {
    // Banco de dados temporário
    private HashMap<String, user> users = new HashMap<>();

    public boolean registerUser(String name, String email, String password) {
        if (users.containsKey(email)) {
            System.out.println("Erro: Este e-mail já está registrado.");
            return false;
        }

        if (password.length() <= 8) {
            System.out.println("Erro: A senha deve ter mais que 8 caracteres.");
            return false;
        }

        User newUser = new User(name, email, password);
        users.put(email, newUser);
        System.out.println("Conta criada com sucesso para: " + name);
        return true;
    }

    public boolean login(String email, String password) {
        if (!users.containsKey(email)) {
            System.out.println("Erro: E-mail não encontrado.");
            return false;
        }

        User user = users.get(email);
        if (!user.getPassword().equals(password)) {
            System.out.println("Erro: Senha incorreta.");
            return false;
        }

        System.out.println("Login realizado com sucesso! Bem-vindo, " + user.getName());
        return true;
    }
}
