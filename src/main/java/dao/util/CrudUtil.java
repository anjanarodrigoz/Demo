package dao.util;
import db.Database;

import java.sql.PreparedStatement;
import java.sql.SQLException;

public class CrudUtil {


    private static CrudUtil instance = null;
    private CrudUtil(){}

    public static CrudUtil getInstance(){
        return  instance!=null ? instance : (instance = new CrudUtil() );
    }

    public static <T>T execute(String sql,Object...args) throws SQLException {
        PreparedStatement pstm = Database.getInstance().getConnection().prepareStatement(sql);
        if(sql.startsWith("SELECT")||sql.startsWith("select")){
            return (T)pstm.executeQuery();
        }

        for(int i = 0;i<args.length;i++){
            pstm.setObject(i+1,args[i]);
        }

        return (T)(Boolean)(pstm.executeUpdate()>0);
    }
}
