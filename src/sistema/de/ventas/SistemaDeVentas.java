package sistema.de.ventas;

import controlador.CtrlProductos;
import modelo.ConsultasProductos;
import modelo.Producto;
import vista.VistaProductos;

public class SistemaDeVentas {

    public static void main(String[] args) {
        Producto prod =new Producto();
        ConsultasProductos consProd = new ConsultasProductos();
        VistaProductos vipro = new VistaProductos();
        
        CtrlProductos ctrlProductos =new CtrlProductos(prod, consProd, vipro);
        ctrlProductos.iniciar();
        vipro.setVisible(true);
    }
    
}
