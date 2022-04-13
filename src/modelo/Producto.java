package modelo;

public class Producto {

    private String nombre;
    private String marca;
    private String descripcion;
    private int piezasExistentes;
    private double precioCompra;
    private double precioVenta;

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public int getPiezasExistentes() {
        return piezasExistentes;
    }

    public void setPiezasExistentes(int piezasExistentes) {
        this.piezasExistentes = piezasExistentes;
    }

    public double getPrecioCompra() {
        return precioCompra;
    }

    public void setPrecioCompra(double precioCompra) {
        this.precioCompra = precioCompra;
    }

    public double getPrecioVenta() {
        return precioVenta;
    }

    public void setPrecioVenta(double precioVenta) {
        this.precioVenta = precioVenta;
    }
    

}
