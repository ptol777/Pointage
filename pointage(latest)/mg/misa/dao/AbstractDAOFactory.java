package mg.misa.dao;

import mg.misa.dao.concrete.MysqlDAOFactory;
import mg.misa.dao.interfaces.PointageDAO;
import mg.misa.dao.interfaces.JourneeDAO;
import mg.misa.dao.interfaces.DureeDAO;
import mg.misa.dao.interfaces.EmployeDAO;
import mg.misa.dao.interfaces.AutorisationDAO;

public abstract class AbstractDAOFactory {

    public static final int MYSQL = 0;
    public static final int ORACLE = 1;
    public static final int POSTGRESQL = 2;

    public static AbstractDAOFactory createDAOFactory(int type){
        switch (type){
            case MYSQL:
                return new MysqlDAOFactory();
            /*case ORACLE:
                return new OracleDAOFActory();
            case POSTGRESQL:
                return new PostgresqlDAOFActory();*/
            default:
                return null;
        }
    }

    public abstract EmployeDAO createEmployeDAO();
    public abstract PointageDAO createPointageDAO();
    public abstract JourneeDAO createJourneeDAO();
    public abstract DureeDAO createDureeDAO();
    public abstract AutorisationDAO createAutorisationDAO();
}
