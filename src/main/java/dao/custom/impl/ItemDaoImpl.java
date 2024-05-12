package dao.custom.impl;

import dao.util.CrudUtil;
import db.Database;
import dto.ItemDto;
import dao.custom.ItemDao;
import entity.Item;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ItemDaoImpl implements ItemDao {



    @Override
    public ItemDto getItem(String ItemCode) throws SQLException {

        String sql = "SELECT FROM item WHERE id =?";
        PreparedStatement pstm = Database.getInstance().getConnection().prepareStatement(sql);
        ResultSet resultSet = pstm.executeQuery();

        return new ItemDto(
                resultSet.getString(1),
                resultSet.getString(2),
                resultSet.getInt(3),
                resultSet.getDouble(4)
        );

    }



    @Override
    public boolean save(Item entity) throws SQLException {

        String sql = "INSERT INTO item(id,name,qty,price) VALUES (?,?,?,?)";


        return CrudUtil.execute(sql,
                entity.getId(),
                entity.getName(),
                entity.getQty(),
                entity.getPrice());

    }

    @Override
    public boolean update(Item entity) throws SQLException {
        String sql = "UPDATE item SET name = ? ,qty = ? , price = ? WHERE id = ?";



        return CrudUtil.execute(sql,
                entity.getName(),
                entity.getQty(),
                entity.getPrice(),
                entity.getId());

    }

    @Override
    public boolean delete(String value) throws SQLException {
        String sql = "DELETE FROM item WHERE id=?";

        return  CrudUtil.execute(sql,value);

    }

    @Override
    public List<Item> getAll() throws SQLException {

        List<Item> itmeEntityList = new ArrayList<>();

        String sql = "SELECT * FROM item";
        ResultSet resultSet = CrudUtil.execute(sql);

        while (resultSet.next()){

            itmeEntityList.add(
                    new Item(
                            resultSet.getString(1),
                            resultSet.getString(2),
                            resultSet.getInt(3),
                            resultSet.getDouble(4)
                    ));
        }

        return itmeEntityList;
    }
}
