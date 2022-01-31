/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package appinformes;

import static appinformes.AppInformes.conexion;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextField;
import javax.swing.JOptionPane;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.util.JRLoader;
import net.sf.jasperreports.view.JasperViewer;

/**
 * FXML Controller class
 *
 * @author chris
 */
public class AppInformesController implements Initializable {

    @FXML
    private MenuItem listado_facturas;
    @FXML
    private MenuItem ventas;
    @FXML
    private MenuItem facturas_cliente;
    @FXML
    private MenuItem listado_facturas_sub;
    @FXML
    private TextField idCliente;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        
    }    

    @FXML
    private void generaFactura(ActionEvent event) {
        try {
            JasperReport jr = (JasperReport)JRLoader.loadObject(AppInformes.class.getResource("jasper/facturas.jasper"));
            JasperPrint jp = (JasperPrint) JasperFillManager.fillReport(jr,null, conexion);
            JasperViewer.viewReport(jp, false);
        } catch (JRException ex) {
            System.out.println("Error al recuperar el jasper");
            JOptionPane.showMessageDialog(null, ex);
        }
    }

    @FXML
    private void generaVentas(ActionEvent event) {
        try {
            JasperReport jr = (JasperReport)JRLoader.loadObject(AppInformes.class.getResource("jasper/Ventas Totales.jasper"));
            JasperPrint jp = (JasperPrint) JasperFillManager.fillReport(jr,null, conexion);
            JasperViewer.viewReport(jp, false);
        } catch (JRException ex) {
            System.out.println("Error al recuperar el jasper");
            JOptionPane.showMessageDialog(null, ex);
        }
    }

    @FXML
    private void generaFacturasCliente(ActionEvent event) {
        try {
            JasperReport jr = (JasperReport)JRLoader.loadObject(AppInformes.class.getResource("jasper/Facturas_por_Cliente.jasper"));
            Map parametros = new HashMap();
            int id = Integer.valueOf(idCliente.getText());
            parametros.put("COD_CLIENTE", id);
            JasperPrint jp = (JasperPrint) JasperFillManager.fillReport(jr,parametros, conexion);
            JasperViewer.viewReport(jp, false);
        } catch (JRException ex) {
            System.out.println("Error al recuperar el jasper");
            JOptionPane.showMessageDialog(null, ex);
        }
    }

    @FXML
    private void generaFacturaSub(ActionEvent event) {
        
        try {
            JasperReport jr = (JasperReport) JRLoader.loadObject(AppInformes.class.getResource("jasper/facturas_subinformes.jasper"));
            JasperReport jsr = (JasperReport) JRLoader.loadObject(AppInformes.class.getResource("jasper/facturas_subinforme2.jasper"));
            
            Map parametros = new HashMap();
            parametros.put("ID_CLIENTE", jsr);
            JasperPrint jp = (JasperPrint) JasperFillManager.fillReport(jr, parametros,conexion);
            JasperViewer.viewReport(jp, false);
        } catch (JRException ex) {
            Logger.getLogger(AppInformesController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}
