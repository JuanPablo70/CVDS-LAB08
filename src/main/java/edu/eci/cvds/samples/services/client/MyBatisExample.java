/*
 * Copyright (C) 2015 hcadavid
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package edu.eci.cvds.samples.services.client;



import java.io.IOException;
import java.io.InputStream;
import java.sql.Date;
import java.sql.SQLException;

import edu.eci.cvds.sampleprj.dao.mybatis.mappers.ClienteMapper;
import edu.eci.cvds.sampleprj.dao.mybatis.mappers.ItemMapper;
import edu.eci.cvds.sampleprj.dao.mybatis.mappers.TipoItemMapper;
import edu.eci.cvds.samples.entities.Cliente;
import edu.eci.cvds.samples.entities.Item;
import edu.eci.cvds.samples.entities.TipoItem;
import edu.eci.cvds.samples.services.ExcepcionServiciosAlquiler;
import edu.eci.cvds.samples.services.ServiciosAlquiler;
import edu.eci.cvds.samples.services.ServiciosAlquilerFactory;
import edu.eci.cvds.samples.services.impl.ServiciosAlquilerImpl;
import edu.eci.cvds.samples.services.impl.ServiciosAlquilerItemsStub;
import org.apache.ibatis.exceptions.PersistenceException;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

/**
 *
 * @author hcadavid
 */
public class MyBatisExample {

    /**
     * Método que construye una fábrica de sesiones de MyBatis a partir del
     * archivo de configuración ubicado en src/main/resources
     *
     * @return instancia de SQLSessionFactory
     */
    public static SqlSessionFactory getSqlSessionFactory() {
        SqlSessionFactory sqlSessionFactory = null;
        if (sqlSessionFactory == null) {
            InputStream inputStream;
            try {
                inputStream = Resources.getResourceAsStream("mybatis-config.xml");
                sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
            } catch (IOException e) {
                throw new RuntimeException(e.getCause());
            }
        }
        return sqlSessionFactory;
    }

    /**
     * Programa principal de ejempo de uso de MyBATIS
     * @param args
     * @throws SQLException 
     */
    public static void main(String args[]) throws SQLException, ExcepcionServiciosAlquiler {

        SqlSessionFactory sessionfact = getSqlSessionFactory();

        SqlSession sqlss = sessionfact.openSession();


        //Crear el mapper y usarlo: 
        ClienteMapper cm=sqlss.getMapper(ClienteMapper.class);
        Cliente cliente = new Cliente("pupito", 1236969, "1236969", "cra 123", "pupito@gmail.com");
        //System.out.println(cm.consultarClientes());
        //System.out.println(cm.consultarCliente(1));
        //cm.agregarItemRentadoACliente(3, 3, Date.valueOf("2022-10-02"), Date.valueOf("2022-10-05"));

        ItemMapper im = sqlss.getMapper(ItemMapper.class);
        TipoItem tipo = new TipoItem(2, "Accion");
        Item item = new Item(tipo, 639, "Pupito", "Pupito", Date.valueOf("2022-10-02"), 639639, "Pupito", "Pupito");
        //im.insertarItem(item);
        //System.out.println(im.consultarItems());
        //System.out.println(im.consultarItem(1));
        //System.out.println(im.costoAlquilerItem(2, 2));
        //im.actualizarItem(2, 6000);

        TipoItemMapper tim = sqlss.getMapper(TipoItemMapper.class);
        //System.out.println(tim.getTiposItems());
        //System.out.println(tim.getTipoItem(2));






        sqlss.commit();
        sqlss.close();

        //prueba stub
        /*ServiciosAlquiler serv = ServiciosAlquilerFactory.getInstance().getServiciosAlquilerStub();
        try {
            System.out.println(serv.consultarClientes());
        }
        catch (Exception ex) {
            System.out.println("se jodió");
        }*/


        // Prueba ServicioAlquilerImpl

        ServiciosAlquiler servicioAlq = ServiciosAlquilerFactory.getInstance().getServiciosAlquiler();
        try {
            // Cliente
            //System.out.println(servicioAlq.consultarCliente(3));
            //System.out.println(servicioAlq.consultarItemsCliente(3));
            //System.out.println(servicioAlq.consultarClientes());
            //servicioAlq.registrarCliente(cliente);
            //servicioAlq.vetarCliente(1236969, true);


            // Item
            //System.out.println(servicioAlq.consultarItemsDisponibles());
            //System.out.println(servicioAlq.consultarItem(2));
            //System.out.println(servicioAlq.consultarCostoAlquiler(2, 2));
            //servicioAlq.actualizarTarifaItem(2, 5000);
            //servicioAlq.registrarItem(item);

            // TipoItem
            //System.out.println(servicioAlq.consultarTiposItem());
            //System.out.println(servicioAlq.consultarTipoItem(1));

            // ItemRentado
            //System.out.println(servicioAlq.consultarMultaAlquiler(1, Date.valueOf("2022-10-18")));
            //servicioAlq.registrarAlquilerCliente(Date.valueOf("2022-10-18"), 1236969, item, 2);


        }
        catch (Exception ex) {
            System.out.println(ex.getMessage());
        }


    }


}
