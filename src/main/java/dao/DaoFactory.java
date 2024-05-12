package dao;

import dao.custom.impl.CustomerDaoImpl;
import dao.custom.impl.ItemDaoImpl;
import dao.util.DaoType;
import entity.Customer;
import javafx.scene.image.PixelBuffer;

public class DaoFactory {

    private static  DaoFactory instance = null;
    private DaoFactory(){}

    public static  DaoFactory getInstance(){

        return  instance!=null ? instance : (instance = new DaoFactory());
    }

    public <T extends SuperDao>T getDao(DaoType daoType){

        switch (daoType){
            case CUSTOMER: return (T)(new CustomerDaoImpl());
            case ITEM: return (T)(new ItemDaoImpl());
        }
        return null;
    }
}
