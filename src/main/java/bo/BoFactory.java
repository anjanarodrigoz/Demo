package bo;

import bo.custom.ItemBo;
import bo.custom.impl.CustomerBoImpl;
import bo.custom.impl.ItemBoImpl;
import dao.util.BoType;

public class BoFactory {

    private static BoFactory instance = null;
    private BoFactory(){}


    public static BoFactory  getInstance(){

       return instance!=null ?  instance : (instance =  new BoFactory());
    }

    public <T extends SuperBo>T getBo(BoType boType){

        switch (boType){
            case ITEM: return (T)(new ItemBoImpl());
            case CUSTOMER: return (T)(new CustomerBoImpl());

        }
        return null;
    }
}
