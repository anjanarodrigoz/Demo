package dao.custom;

import dao.CrudDao;
import dto.OrderDetailsDto;
import entity.OrderDetails;

import java.sql.SQLException;
import java.util.List;

public interface OrderDetailsDao extends CrudDao<List<OrderDetailsDto>>  {

}
