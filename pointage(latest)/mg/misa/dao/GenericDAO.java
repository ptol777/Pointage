package mg.misa.dao;

import java.sql.Connection;

public class GenericDAO {

    protected Connection connection;

    protected GenericDAO(Connection connection){
        this.connection = connection;
    }
}
