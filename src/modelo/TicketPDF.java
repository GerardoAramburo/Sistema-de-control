package modelo;

import java.util.HashMap;
import javax.swing.WindowConstants;
import net.sf.jasperreports.engine.JREmptyDataSource;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.design.JasperDesign;
import net.sf.jasperreports.engine.xml.JRXmlLoader;
import net.sf.jasperreports.view.JasperViewer;

/**
 *
 * @author Daniel
 */
public class TicketPDF {

    Venta data;

    public TicketPDF(Venta venta) {
        this.data = venta;
    }

    public void createPDF() throws JRException {
        HashMap params = new HashMap<String, String>();
        params.put("nombre", data.getComprador());
        System.out.println(params.get("nombre"));
        params.put("fecha", data.getFecha() + " " + data.getHora());
        params.put("metodoPago", data.getTipoPago());
        params.put("nombreProducto", data.getProducto());
        params.put("cantidadProducto", "" + data.getCantidad());
        params.put("precioProducto", "$" + data.getPrecio());
        params.put("totalProducto", "$" + (data.getCantidad() * data.getPrecio()));

        JasperDesign jd = JRXmlLoader.load(Thread.currentThread().getContextClassLoader().getResourceAsStream("modelo/ticketXML/ticket.jrxml"));
        JasperReport report = JasperCompileManager.compileReport(jd);

        JasperPrint jprint = JasperFillManager.fillReport(report, params, new JREmptyDataSource());

        JasperViewer view = new JasperViewer(jprint, false);
        view.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        view.setVisible(true);
    }
}
