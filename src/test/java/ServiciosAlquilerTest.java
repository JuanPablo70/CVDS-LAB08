import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import com.google.inject.Inject;
import edu.eci.cvds.samples.entities.Cliente;
import edu.eci.cvds.samples.entities.Item;
import edu.eci.cvds.samples.entities.ItemRentado;
import edu.eci.cvds.samples.entities.TipoItem;
import edu.eci.cvds.samples.services.ExcepcionServiciosAlquiler;
import edu.eci.cvds.samples.services.ServiciosAlquiler;
import edu.eci.cvds.samples.services.ServiciosAlquilerFactory;
import org.apache.ibatis.session.SqlSession;
import org.junit.Before;
import org.junit.Test;
import org.junit.Assert;

public class ServiciosAlquilerTest {
    @Inject
    private SqlSession sqlSession;
    ServiciosAlquiler serviciosAlquiler;

    public ServiciosAlquilerTest() {
        serviciosAlquiler = ServiciosAlquilerFactory.getInstance().getServiciosAlquilerTesting();
    }

    @Test
    public void deberiaInsertarCliente() {
        Cliente cliente = new Cliente("pupito", 1236969, "1236969", "cra 123", "pupito@gmail.com");
        try {
            serviciosAlquiler.registrarCliente(cliente);
            Assert.assertEquals(serviciosAlquiler.consultarCliente(1236969).getNombre(), "pupito");
        } catch (ExcepcionServiciosAlquiler e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    public void deberiaIConsultarCliente() {
        try {
            Assert.assertEquals(serviciosAlquiler.consultarCliente(1236969).getDocumento(), 1236969);
        } catch (ExcepcionServiciosAlquiler e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    public void deberiaVetarCliente() {
        Cliente cliente = new Cliente("pupito", 1236969, "1236969", "cra 123", "pupito@gmail.com");
        try {
            serviciosAlquiler.registrarCliente(cliente);
            serviciosAlquiler.vetarCliente(1236969, true);
            Assert.assertTrue(serviciosAlquiler.consultarCliente(1236969).isVetado());
        } catch (ExcepcionServiciosAlquiler e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    public void noDeberiaConsultarCliente() {
        try {
            Assert.assertNull(serviciosAlquiler.consultarCliente(123));
        } catch (ExcepcionServiciosAlquiler e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    public void deberiaInsertarItem() {
        TipoItem tipo = new TipoItem(2, "Accion");
        Item item = new Item(tipo, 639, "Pupito", "Pupito", Date.valueOf("2022-10-02"), 639639, "Pupito", "Pupito");
        try {
            serviciosAlquiler.registrarItem(item);
            Assert.assertEquals(serviciosAlquiler.consultarItem(639).getNombre(), "Pupito");
        } catch (ExcepcionServiciosAlquiler e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    public void deberiaIConsultarItem() {
        try {
            Assert.assertEquals(serviciosAlquiler.consultarItem(639).getId(), 639);
        } catch (ExcepcionServiciosAlquiler e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    public void deberiaActualizarTarifa() {
        try {
            serviciosAlquiler.actualizarTarifaItem(639, 500);
            Assert.assertEquals(serviciosAlquiler.consultarItem(639).getTarifaxDia(), 500);
        } catch (ExcepcionServiciosAlquiler e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    public void noDeberiaIConsultarItem() {
        try {
            Assert.assertNull(serviciosAlquiler.consultarItem(123));
        } catch (ExcepcionServiciosAlquiler e) {
            throw new RuntimeException(e);
        }
    }


}