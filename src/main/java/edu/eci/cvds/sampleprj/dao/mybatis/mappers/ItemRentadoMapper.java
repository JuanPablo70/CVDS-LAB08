package edu.eci.cvds.sampleprj.dao.mybatis.mappers;

import edu.eci.cvds.samples.entities.Item;
import edu.eci.cvds.samples.entities.ItemRentado;
import org.apache.ibatis.annotations.Param;

import java.sql.Date;
import java.util.List;

public interface ItemRentadoMapper {
    public List<ItemRentado> consultarItemsRentados();
    public List<ItemRentado> consultarItemRentadosCli(@Param("idcli") int idcli);
    public ItemRentado consultarItemRentado(@Param("idIr") int id);
    public long consultarMultaRentado(@Param("idIt")int idIt, @Param("fDev") Date fechaDevolucion);
    public void registrarAlquiler(@Param("date") Date date, @Param("docu") long docu, @Param("item") Item item, @Param("numdias") int numdias);
}
