package bo.custom.impl;

import bo.custom.ItemBo;
import dao.custom.ItemDao;
import dao.custom.impl.ItemDaoImpl;
import dto.ItemDto;
import entity.Item;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ItemBoImpl implements ItemBo<ItemDto> {

    ItemDao itemDao = new ItemDaoImpl();
    @Override
    public boolean saveItem(ItemDto dto) throws SQLException {
        return itemDao.save(new Item(
                dto.getCode(),
                dto.getName(),
                dto.getQty(),
                dto.getPrice()
        ));

    }

    @Override
    public boolean updateItem(ItemDto dto) throws SQLException {
        return itemDao.update(new Item(
                dto.getCode(),
                dto.getName(),
                dto.getQty(),
                dto.getPrice()
        ));
    }

    @Override
    public boolean deleteItem(String itemCode) throws SQLException {

        return itemDao.delete(itemCode);
    }

    @Override
    public List<ItemDto> getAllItems() throws SQLException {

        List<Item> itemEntityList =    itemDao.getAll();
        List<ItemDto> itemDtoList = new ArrayList<>();

        for(Item item : itemEntityList){

            itemDtoList.add(
                    new ItemDto(
                            item.getId(),
                            item.getName(),
                            item.getQty(),
                            item.getPrice()
                    )
            );
        }

        return itemDtoList;

    }
}
