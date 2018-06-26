package mg.misa.dao.concrete;

import mg.misa.dao.AbstractDAOFactory;
import mg.misa.dao.interfaces.PointageDAO;
import mg.misa.dao.interfaces.JourneeDAO;
import mg.misa.dao.interfaces.DureeDAO;
import mg.misa.dao.interfaces.EmployeDAO;
import mg.misa.dao.interfaces.AutorisationDAO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;


public class MysqlDAOFactory extends AbstractDAOFactory {

    private static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
    private static final String DB_URL = "jdbc:mysql://localhost/pointage";
    private static final String USER = "root";
    private static final String PASS = "123456";

    private Connection connection;

    private Connection getConnection(){
        if(connection!=null){
            return connection;
        }
        try {
            Class.forName(JDBC_DRIVER);
            connection = DriverManager.getConnection(DB_URL, USER, PASS);
        }catch (Exception e){
            e.printStackTrace();
        }
        return connection;
    }

    public void openTransaction() {
        try {
            connection.setAutoCommit(false);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void endTransaction() {
        try {
            connection.commit();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    public void rollback() {
        try {
            connection.rollback();
        } catch (SQLException exc) {
            exc.printStackTrace();
        }
    }

    public void closeConnection() {
        try {
            connection.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    @Override
    public EmployeDAO createEmployeDAO(){
        return new EmployeSqlDAO(getConnection());
    }

    @Override
    public AutorisationDAO createAutorisationDAO() {
        return new AutorisationSqlDAO(getConnection());
    }

    @Override
    public DureeDAO createDureeDAO() {
        return new DureeSqlDAO(getConnection());
    }

    @Override
    public PointageDAO createPointageDAO() {
        return new PointageSqlDAO(getConnection());
    }

    @Override
    public JourneeDAO createJourneeDAO() {
        return new JourneeSqlDAO(getConnection());
    }


}
