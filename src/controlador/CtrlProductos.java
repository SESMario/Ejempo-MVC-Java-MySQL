package controlador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JOptionPane;
import modelo.ConsultasProductos;
import modelo.Producto;
import vista.VistaProductos;

public class CtrlProductos implements ActionListener {

    Producto producto;
    ConsultasProductos consP;
    VistaProductos vistaProductos;

    public CtrlProductos(Producto pProducto, ConsultasProductos pConProd, VistaProductos pVistaProductos) {
        this.producto = pProducto;
        this.consP = pConProd;
        this.vistaProductos = pVistaProductos;

        this.vistaProductos.btnGuardar.addActionListener(this);
    }

    public void iniciar() {
        vistaProductos.setTitle("Productos");
        vistaProductos.setLocationRelativeTo(null);
        vistaProductos.setVisible(false);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == vistaProductos.btnGuardar) {
            producto.setNombre(vistaProductos.txtNombre.getText());
            producto.setMarca(vistaProductos.txtMarca.getText());
            producto.setDescripcion(vistaProductos.txtDescripcion.getText());
            producto.setPiezasExistentes((Integer)vistaProductos.jspPiezasExistentes.getValue());
            System.out.println("valor spinner: " + vistaProductos.jspPiezasExistentes.getValue());
            producto.setPrecioCompra(Double.parseDouble(vistaProductos.txtPrecioCompra.getText()));
            producto.setPrecioVenta(Double.parseDouble(vistaProductos.txtPrecioVenta.getText()));
            if (consP.registrar(producto)) {
                JOptionPane.showMessageDialog(null, "Registro guardado");
                limpiar();
            } else {
                JOptionPane.showMessageDialog(null, "Error al guardar");
                limpiar();
            }
        }
    }

    public void limpiar() {
        vistaProductos.txtNombre.setText(null);
        vistaProductos.txtMarca.setText(null);
        vistaProductos.txtDescripcion.setText(null);
        vistaProductos.jspPiezasExistentes.setValue(0);
        vistaProductos.txtPrecioCompra.setText(null);
        vistaProductos.txtPrecioVenta.setText(null);
    }
}
