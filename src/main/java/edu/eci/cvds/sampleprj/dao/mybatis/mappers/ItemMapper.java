package edu.eci.cvds.sampleprj.dao.mybatis.mappers;


import java.util.Date;
import java.util.List;
import org.apache.ibatis.annotations.Param;

import edu.eci.cvds.samples.entities.Item;

/**
 *
 * @author 2106913
 */
public interface ItemMapper {
    
    
    public List<Item> consultarItems();        
    
    public Item consultarItem(@Param("idit") int id);
    
    public void insertarItem(@Param("item") Item it);

    public void actualizarItem(@Param("idit") int id, @Param("tarxdia") long tarxdia);
    public long costoAlquilerItem(@Param("idit") int iditem, @Param("dias") int numdias);
    public int consultarAlquilerxDia(@Param("idit") int id);

        
}
