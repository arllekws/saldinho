package Program.User;

import Entities.User;
import Program.dao.UserDAO;

public class UserService {

    private final UserDAO userDAO = new UserDAO();

    public void registerUser(String name, String email, String password) {
        if (userDAO.emailExiste(email)) {
            System.out.println("Erro: Este e-mail já está registrado.");
            return;
        }

        if (password.length() <= 3) {
            System.out.println("Erro: A senha deve ter mais que 3 caracteres.");
            return;
        }

        // criação e persistência do usuário
        User newUser = new User(name, email, password);
        boolean sucesso = userDAO.salvarUsuario(newUser);

        if (sucesso) {
            System.out.println("Conta criada com sucesso para: " + name);
        } else {
            System.out.println("Erro ao salvar usuário no banco.");
        }
    }

    public User login(String email, String password) {
        // busca o usuário no banco de dados
        User user = userDAO.buscarPorEmail(email);

        if (user == null) {
            System.out.println("Erro: E-mail não encontrado.");
            return null;
        }

        if (!user.getPassword().equals(password)) {
            System.out.println("Erro: Senha incorreta.");
            return null;
        }

        System.out.println("Login realizado com sucesso! Bem-vindo, " + user.getName());
        return user;
    }
}
