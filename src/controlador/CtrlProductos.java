package controlador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
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
        this.vistaProductos.btnModificar.addActionListener(this);
        this.vistaProductos.btnEliminar.addActionListener(this);
        this.vistaProductos.btnBuscar.addActionListener(this);
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
            producto.setPiezasExistentes((Integer) vistaProductos.jspPiezasExistentes.getValue());
            System.out.println("valor spinner: " + vistaProductos.jspPiezasExistentes.getValue());
            producto.setPrecioCompra(Double.parseDouble(vistaProductos.txtPrecioCompra.getText()));
            producto.setPrecioVenta(Double.parseDouble(vistaProductos.txtPrecioVenta.getText()));
            if (consP.registrar(producto)) {
                JOptionPane.showMessageDialog(null, "Registro guardado");
                llenarTabla(vistaProductos.jtbListaProductos);
                limpiar();
            } else {
                JOptionPane.showMessageDialog(null, "Error al guardar");
                limpiar();
            }

        } else if (e.getSource() == vistaProductos.btnModificar) {

            producto.setIdProducto(Integer.parseInt(vistaProductos.txtid.getText()));
            producto.setNombre(vistaProductos.txtNombre.getText());
            producto.setMarca(vistaProductos.txtMarca.getText());
            producto.setDescripcion(vistaProductos.txtDescripcion.getText());
            producto.setPiezasExistentes((Integer) vistaProductos.jspPiezasExistentes.getValue());
            System.out.println("valor spinner: " + vistaProductos.jspPiezasExistentes.getValue());
            producto.setPrecioCompra(Double.parseDouble(vistaProductos.txtPrecioCompra.getText()));
            producto.setPrecioVenta(Double.parseDouble(vistaProductos.txtPrecioVenta.getText()));

            if (consP.modificar(producto)) {
                JOptionPane.showMessageDialog(null, "Registro modificado");
                llenarTabla(vistaProductos.jtbListaProductos);
                limpiar();
            } else {
                JOptionPane.showMessageDialog(null, "Error al modificar");
                limpiar();
            }

        } else if (e.getSource() == vistaProductos.btnEliminar) {

            producto.setIdProducto(Integer.parseInt(vistaProductos.txtid.getText()));

            if (consP.eliminar(producto)) {
                JOptionPane.showMessageDialog(null, "Registro Eliminado");
                llenarTabla(vistaProductos.jtbListaProductos);
                limpiar();
            } else {
                JOptionPane.showMessageDialog(null, "Error al Eliminar");
                limpiar();
            }

        } else if (e.getSource() == vistaProductos.btnBuscar) {

            limpiar();
            producto.setIdProducto(Integer.parseInt(vistaProductos.txtid.getText()));

            if (consP.buscar(producto)) {
                vistaProductos.txtNombre.setText(producto.getNombre());
                vistaProductos.txtMarca.setText(producto.getMarca());
                vistaProductos.txtDescripcion.setText(producto.getDescripcion());
                vistaProductos.jspPiezasExistentes.setValue(producto.getPiezasExistentes());
                vistaProductos.txtPrecioCompra.setText(String.valueOf(producto.getPrecioCompra()));
                vistaProductos.txtPrecioVenta.setText(String.valueOf(producto.getPrecioVenta()));
                //JOptionPane.showMessageDialog(null, "Registro Eliminado");
            } else {
                JOptionPane.showMessageDialog(null, "Ocurrio un error al buscar");
                limpiar();
            }

        }
    }

    public void llenarTabla(JTable tablaProductos) {

        DefaultTableModel modeloT = new DefaultTableModel();
        tablaProductos.setModel(modeloT);

        modeloT.addColumn("ID");
        modeloT.addColumn("Nombre");
        modeloT.addColumn("Marca");
        modeloT.addColumn("Desc");
        modeloT.addColumn("No.Pzs");
        modeloT.addColumn("P Compra");
        modeloT.addColumn("P Venta");

        Object[] columna = new Object[7];

        int numeroRegistros = consP.listarProductos().size();

        for (int i = 0; i < numeroRegistros; i++) {
            columna[0] = consP.listarProductos().get(i).getIdProducto();
            columna[1] = consP.listarProductos().get(i).getNombre();
            columna[2] = consP.listarProductos().get(i).getMarca();
            columna[3] = consP.listarProductos().get(i).getDescripcion();
            columna[4] = consP.listarProductos().get(i).getPiezasExistentes();
            columna[5] = consP.listarProductos().get(i).getPrecioCompra();
            columna[6] = consP.listarProductos().get(i).getPrecioVenta();
            modeloT.addRow(columna);
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
