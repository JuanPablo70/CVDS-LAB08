package edu.eci.cvds.view;

import com.google.inject.Inject;
import edu.eci.cvds.samples.entities.Cliente;
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

    public Cliente getClienteSeleccionado() {
        return clienteSeleccionado;
    }

    public void setClienteSeleccionado(Cliente clienteSeleccionado) {
        this.clienteSeleccionado = clienteSeleccionado;
    }
}
