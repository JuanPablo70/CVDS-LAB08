package edu.eci.cvds.view;

import com.google.inject.Inject;
import edu.eci.cvds.samples.entities.Cliente;
import edu.eci.cvds.samples.entities.Item;
import edu.eci.cvds.samples.entities.ItemRentado;
import edu.eci.cvds.samples.services.ExcepcionServiciosAlquiler;
import edu.eci.cvds.samples.services.ServiciosAlquiler;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import java.util.List;


@ManagedBean(name = "AlquilerItemsBean")
@SessionScoped
public class AlquilerItemsBean extends BasePageBean{
    @Inject
    private ServiciosAlquiler serviciosAlquiler;
    private Cliente clienteSeleccionado;

    public void registrarCliente(String nombre, long documento, String telefono, String direccion, String email) {
        Cliente cliente = new Cliente(nombre, documento, telefono, direccion, email);
        try {
            serviciosAlquiler.registrarCliente(cliente);
        } catch (ExcepcionServiciosAlquiler ex) {
            System.out.println(ex.getMessage());
        }
    }

    public List<Cliente> getClientes() {
        try {
            return serviciosAlquiler.consultarClientes();
        } catch (ExcepcionServiciosAlquiler ex) {
            System.out.println(ex.getMessage());
            return null;
        }
    }

    public List<ItemRentado> getItemsRentados(long idcli) {
        try {
            return serviciosAlquiler.consultarItemsCliente(idcli);
        } catch (ExcepcionServiciosAlquiler ex) {
            System.out.println(ex.getMessage());
            return null;
        }
    }

    public long getMultaItemRentado(ItemRentado itemRentado) {
        try {
            long millis=System.currentTimeMillis();
            java.sql.Date date = new java.sql.Date(millis);
            return serviciosAlquiler.consultarMultaAlquiler(itemRentado.getId(), date);
        } catch (ExcepcionServiciosAlquiler ex) {
            System.out.println(ex.getMessage());
            return -1;
        }
    }

    public void registrarAlquiler(int iditem, int numdias) {
        try {
            long millis=System.currentTimeMillis();
            java.sql.Date date = new java.sql.Date(millis);
            Item item = serviciosAlquiler.consultarItem(iditem);
            serviciosAlquiler.registrarAlquilerCliente(date, clienteSeleccionado.getDocumento(), item, numdias);
        } catch (ExcepcionServiciosAlquiler ex) {
            System.out.println(ex.getMessage());
        }
    }

    public long getValorAlquiler(int iditem, int numdias) {
        try {
            return serviciosAlquiler.consultarCostoAlquiler(iditem, numdias);
        } catch (ExcepcionServiciosAlquiler ex) {
            System.out.println(ex.getMessage());
            return -1;
        }
    }

    public Item getItem(int iditem) {
        try {
            return serviciosAlquiler.consultarItem(iditem);
        } catch (ExcepcionServiciosAlquiler ex) {
            System.out.println(ex.getMessage());
            return null;
        }
    }

    public Cliente getClienteSeleccionado() {
        return clienteSeleccionado;
    }

    public void setClienteSeleccionado(Cliente clienteSeleccionado) {
        this.clienteSeleccionado = clienteSeleccionado;
    }
}
