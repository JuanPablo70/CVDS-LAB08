package edu.eci.cvds.sampleprj.dao.mybatis;

import com.google.inject.Inject;
import edu.eci.cvds.sampleprj.dao.ItemRentadoDAO;
import edu.eci.cvds.sampleprj.dao.mybatis.mappers.ItemRentadoMapper;
import edu.eci.cvds.samples.entities.Item;
import edu.eci.cvds.samples.entities.ItemRentado;
import org.apache.ibatis.exceptions.PersistenceException;

import java.sql.Date;
import java.util.List;

public class MyBATISItemRentadoDAO implements ItemRentadoDAO {
    @Inject
    private ItemRentadoMapper itemRentadoMapper;

    @Override
    public List<ItemRentado> loadAll() throws PersistenceException {
        try {
            return itemRentadoMapper.consultarItemsRentados();
        } catch (org.apache.ibatis.exceptions.PersistenceException e) {
            throw new PersistenceException("Error al consultar items rentados", e);
        }
    }

    @Override
    public List<ItemRentado> loadAlquileres(long idcli) {
        try {
            return itemRentadoMapper.consultarItemRentadosCli((int)idcli);
        } catch (org.apache.ibatis.exceptions.PersistenceException e) {
            System.out.println(e.getMessage());
            throw new PersistenceException("Error al consultar el items rentados del cliente " + idcli, e);
        }
    }

    @Override
    public ItemRentado load(int id) throws PersistenceException {
        try {
            return itemRentadoMapper.consultarItemRentado(id);
        } catch (org.apache.ibatis.exceptions.PersistenceException e) {
            throw new PersistenceException("Error al consultar el item rentado " + id, e);
        }
    }

    @Override
    public long loadMulta(int id, Date fechaDevolucion) throws PersistenceException {
        try {
            return itemRentadoMapper.consultarMultaRentado(id, fechaDevolucion);
        } catch (org.apache.ibatis.exceptions.PersistenceException e) {
            throw new PersistenceException("Error al consultar multa item rentado " + id, e);
        }
    }

    @Override
    public void saveAlquiler(Date date, long docu, Item item, int numdias) throws PersistenceException {
        try {
            itemRentadoMapper.registrarAlquiler(date, docu, item, numdias);
        } catch (org.apache.ibatis.exceptions.PersistenceException e) {
            System.out.println(e.getMessage());
            throw new PersistenceException("Error al registrar item rentado del cliente " + docu, e);
        }
    }
}
