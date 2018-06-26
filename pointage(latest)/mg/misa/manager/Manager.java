package mg.misa.manager;

import mg.misa.dao.AbstractDAOFactory;
import mg.misa.dao.GenericDAO;
import mg.misa.dao.concrete.MysqlDAOFactory;

public abstract class Manager {

    protected MysqlDAOFactory mysqlDAOFactory;

    GenericDAO dao;

    protected Manager(){
        mysqlDAOFactory = (MysqlDAOFactory)AbstractDAOFactory.createDAOFactory(AbstractDAOFactory.MYSQL);
    }
}
