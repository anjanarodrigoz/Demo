package dao.custom.impl;

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

        PreparedStatement pstm = Database.getInstance().getConnection().prepareStatement(sql);

        pstm.setString(1,entity.getId());
        pstm.setString(2,entity.getName());
        pstm.setInt(3,entity.getQty());
        pstm.setDouble(4,entity.getPrice());

        return pstm.executeUpdate()>0;

    }

    @Override
    public boolean update(Item entity) throws SQLException {
        String sql = "UPDATE item SET name = ? ,qty = ? , price = ? WHERE id = ?";

        PreparedStatement pstm = Database.getInstance().getConnection().prepareStatement(sql);

        pstm.setString(1,entity.getName());
        pstm.setInt(2,entity.getQty());
        pstm.setDouble(3,entity.getPrice());
        pstm.setString(4,entity.getId());

        return pstm.executeUpdate()>0;

    }

    @Override
    public boolean delete(String value) throws SQLException {
        String sql = "DELETE FROM item WHERE id=?";
        PreparedStatement pstm = Database.getInstance().getConnection().prepareStatement(sql);
        pstm.setString(1,value);
        return pstm.executeUpdate() > 0;

    }

    @Override
    public List<Item> getAll() throws SQLException {

        List<Item> itmeEntityList = new ArrayList<>();

        String sql = "SELECT * FROM item";
        PreparedStatement pstm = Database.getInstance().getConnection().prepareStatement(sql);
        ResultSet resultSet = pstm.executeQuery();

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
