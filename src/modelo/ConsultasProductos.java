package modelo;

import java.sql.Connection;
import modelo.ConexioBD;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import vista.VistaProductos;

public class ConsultasProductos extends ConexioBD {

    ConexioBD con = new ConexioBD();
    Connection cn = con.getConexioBD();
    PreparedStatement ps;
    ResultSet rs;
    VistaProductos vp;

    public boolean registrar(Producto pro) {
        String sql = "INSERT INTO producto (nombre,marca,descripcion,numeropz,pcompra,pventa) values(?,?,?,?,?,?)";
        try {
            ps = cn.prepareStatement(sql);
            ps.setString(1, pro.getNombre());
            ps.setString(2, pro.getMarca());
            ps.setString(3, pro.getDescripcion());
            ps.setInt(4, pro.getPiezasExistentes());
            ps.setDouble(5, pro.getPrecioCompra());
            ps.setDouble(6, pro.getPrecioVenta());
            ps.execute();
            return true;
        } catch (SQLException e) {
            System.out.println("Ocurrio un error" + e);
            return false;
        } finally {
            try {
                cn.close();
            } catch (SQLException sqle) {
                System.out.println("No se cerro la conexion: " + sqle);
            }
        }
    }

}
