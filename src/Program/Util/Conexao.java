package Program.Util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import java.sql.Statement;

public class Conexao {
    private static final String URL = "jdbc:postgresql://localhost:5432/banco_saldinho";
    private static final String USUARIO = "postgres";
    private static final String SENHA = "kinho123";

    public static Connection conectar() throws SQLException {
        return DriverManager.getConnection(URL, USUARIO, SENHA);
    }
}
