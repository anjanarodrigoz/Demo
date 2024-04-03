package model.impl;

import db.Database;
import dto.ItemDto;
import model.Item;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ItemImpl implements Item {

    @Override
    public boolean saveItem(ItemDto dto) throws SQLException {

        String sql = "INSERT INTO item(id,name,qty,price) VALUES (?,?,?,?)";

        PreparedStatement pstm = Database.getInstance().getConnection().prepareStatement(sql);

        pstm.setString(1,dto.getCode());
        pstm.setString(2,dto.getName());
        pstm.setInt(3,dto.getQty());
        pstm.setDouble(4,dto.getPrice());

        return pstm.executeUpdate()>0;

    }

    @Override
    public boolean updateItem(ItemDto dto) throws SQLException {

        String sql = "UPDATE item SET name = ? ,qty = ? , price = ? WHERE id = ?";

        PreparedStatement pstm = Database.getInstance().getConnection().prepareStatement(sql);

        pstm.setString(1,dto.getName());
        pstm.setInt(2,dto.getQty());
        pstm.setDouble(3,dto.getPrice());
        pstm.setString(4,dto.getCode());

        return pstm.executeUpdate()>0;

    }

    @Override
    public boolean deleteItem(String itemCode) throws SQLException {

        String sql = "DELETE FROM item WHERE id=?";
        PreparedStatement pstm = Database.getInstance().getConnection().prepareStatement(sql);
        pstm.setString(1,itemCode);
        return pstm.executeUpdate() > 0;
    }

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
    public List<ItemDto> getAllItems() throws SQLException {

        List<ItemDto> itmeList = new ArrayList<ItemDto>();

        String sql = "SELECT * FROM item";
        PreparedStatement pstm = Database.getInstance().getConnection().prepareStatement(sql);
        ResultSet resultSet = pstm.executeQuery();

        while (resultSet.next()){

            itmeList.add(
                    new ItemDto(
                    resultSet.getString(1),
                    resultSet.getString(2),
                    resultSet.getInt(3),
                    resultSet.getDouble(4)
            ));
        }

      return itmeList;
    }
}
