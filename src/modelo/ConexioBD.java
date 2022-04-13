package modelo;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ConexioBD {

    private Connection con=null;
    private final String driver = "com.mysql.jdbc.Driver";
    private final String nameBase = "sistemaventas";
    private final String user = "root";
    private final String password = "";
    private final String url = "jdbc:mysql://localhost:3306/" + nameBase;

    public Connection getConexioBD() {
        try {
            Class.forName(driver);
            con = DriverManager.getConnection(this.url, this.user, this.password);
            if (con != null) {
                //System.out.println("Conexion establecida...");
            }
        } catch (ClassNotFoundException | SQLException e) {
            System.out.println("Error al conectar " + e);
        }
        return con;
    }

    //Con este metodo nos desconectamos de la bd
    public void desconectar() {
        con = null;
        if (con == null) {
            System.out.println("Conexion terminada...");
        }

    }

}
