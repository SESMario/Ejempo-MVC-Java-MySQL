package modelo;

import com.mysql.jdbc.ResultSetMetaData;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import vista.VistaProductos;

public class ConsultasProductos extends ConexioBD {

    ConexioBD con = new ConexioBD();
    PreparedStatement ps;
    ResultSet rs = null;
    VistaProductos vp;

    public ArrayList<Producto> listarProductos() {

        ArrayList listaProductos = new ArrayList();
        Producto prod;

        try {
            Connection cn = con.getConexioBD();
            String sql = "SELECT * FROM producto";
            ps = cn.prepareStatement(sql);
            rs = ps.executeQuery();

            while (rs.next()) {
                
                prod = new Producto();
                prod.setIdProducto(rs.getInt(1));
                prod.setNombre(rs.getString(2));
                prod.setMarca(rs.getString(3));
                prod.setDescripcion(rs.getString(4));
                prod.setPiezasExistentes(rs.getInt(5));
                prod.setPrecioCompra(rs.getDouble(6));
                prod.setPrecioVenta(rs.getDouble(7));
                listaProductos.add(prod);
                
            }

        } catch (SQLException e) {
            System.out.println("Ocurrio un error al listar los productos" + e);
        } 
        return listaProductos;
    }

    public boolean registrar(Producto pro) {

        Connection cn = con.getConexioBD();
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

    public boolean modificar(Producto pro) {
        Connection cn = con.getConexioBD();
        String sql = "UPDATE producto SET nombre=?, marca=?, descripcion=?, numeropz=?, pcompra=?, pventa=? WHERE pkproducto=?";
        try {
            ps = cn.prepareStatement(sql);
            ps.setString(1, pro.getNombre());
            ps.setString(2, pro.getMarca());
            ps.setString(3, pro.getDescripcion());
            ps.setInt(4, pro.getPiezasExistentes());
            ps.setDouble(5, pro.getPrecioCompra());
            ps.setDouble(6, pro.getPrecioVenta());
            ps.setInt(7, pro.getIdProducto());
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

    public boolean eliminar(Producto pro) {
        Connection cn = con.getConexioBD();
        String sql = "DELETE FROM  producto WHERE pkproducto=?";
        try {
            ps = cn.prepareStatement(sql);
            ps.setInt(1, pro.getIdProducto());
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

    public boolean buscar(Producto pro) {
        Connection cn = con.getConexioBD();
        String sql = "SELECT * FROM producto WHERE pkproducto = ?";
        try {
            ps = cn.prepareStatement(sql);
            ps.setInt(1, pro.getIdProducto());
            rs = ps.executeQuery();

            if (rs.next()) {
                pro.setIdProducto(rs.getInt("pkproducto"));
                pro.setNombre(rs.getString("nombre"));
                pro.setMarca(rs.getString("marca"));
                pro.setDescripcion(rs.getString("descripcion"));
                pro.setPiezasExistentes(rs.getInt("numeropz"));
                pro.setPrecioCompra(Double.parseDouble(rs.getString("pcompra")));
                pro.setPrecioVenta(Double.parseDouble(rs.getString("pventa")));
                return true;
            }

            return false;
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
