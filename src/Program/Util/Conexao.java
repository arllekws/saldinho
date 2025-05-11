package Program.Util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Conexao {
    public static Connection conectar() throws SQLException {
        try {
            Class.forName("org.postgresql.Driver");
        } catch (ClassNotFoundException e) {
            throw new SQLException("Driver PostgreSQL não encontrado", e);
        }

        String url = "jdbc:postgresql://localhost:5432/banco_saldinho";
        String user = "postgres"; // substitua pelo seu usuário
        String password = "kinho123"; // substitua pela sua senha

        return DriverManager.getConnection(url, user, password);
    }
}
