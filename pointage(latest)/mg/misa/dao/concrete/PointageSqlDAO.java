package mg.misa.dao.concrete;

import mg.misa.model.pointage.Pointage;
import mg.misa.dao.GenericDAO;
import mg.misa.dao.interfaces.PointageDAO;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PointageSqlDAO extends GenericDAO implements PointageDAO {

    PointageSqlDAO(Connection connection){
        super(connection);
    }

    private static final String INSERT_QUERY = "INSERT INTO pointage (idEmploye,date,IN_OUT,normalStart,normalEnd,antenne,remarque) VALUES (?,?,?,?,?,?,?)";
    private static final String UPDATE_QUERY = "UPDATE pointage SET date=?,IN_OUT=?,normalStart=?,normalEnd=?,antenne=?,remarque=? WHERE idEmploye=?";
    private static final String DELETE_QUERY = "DELETE FROM pointage WHERE idEmploye=?";
    private static final String FIND_BY_EMPLOYE_ID_QUERY = "SELECT * FROM pointage WHERE idEmploye=?";
    private static final String FIND_ALL_QUERY = "SELECT * FROM pointage";
    private static final String FIND_LAST_POINTAGE_QUERY = "SELECT * FROM pointage WHERE idEmploye=? ORDER BY id DESC LIMIT 1;";


    @Override
    public boolean add(Pointage pointage) throws SQLException{
        boolean result = false;
        PreparedStatement preparedStatement = null;
        try{
            preparedStatement = connection.prepareStatement(INSERT_QUERY);

            preparedStatement.setInt(1,pointage.getIdEmploye());
            preparedStatement.setString(2,pointage.getDateInString());
            preparedStatement.setString(3,pointage.getIN_OUT());
            preparedStatement.setString(4,pointage.getNormalStartInString());
            preparedStatement.setString(5,pointage.getNormalEndInString());
            preparedStatement.setString(6,pointage.getAntenne());
            preparedStatement.setString(7,pointage.getRemarque());
            preparedStatement.executeUpdate();
            result = true;
        }catch (SQLException e){
            e.printStackTrace();
            throw e;
        }finally{
            if(preparedStatement!=null){
                try{
                    preparedStatement.close();
                }catch (SQLException exc){
                    exc.printStackTrace();
                }
            }
        }
        return result;
    }

    @Override
    public boolean update(Pointage pointage) {
        boolean result = false;
        PreparedStatement preparedStatement = null;
        try{
            preparedStatement = connection.prepareStatement(UPDATE_QUERY);

            preparedStatement.setString(1,pointage.getDateInString());
            preparedStatement.setString(2,pointage.getIN_OUT());
            preparedStatement.setString(3,pointage.getNormalStartInString());
            preparedStatement.setString(4,pointage.getNormalEndInString());
            preparedStatement.setString(5,pointage.getAntenne());
            preparedStatement.setString(6,pointage.getRemarque());
            preparedStatement.setInt(7,pointage.getIdEmploye());
            preparedStatement.executeUpdate();
            result = true;
        }catch (SQLException e){
            e.printStackTrace();
        }finally{
            if(preparedStatement!=null){
                try{
                    preparedStatement.close();
                }catch (SQLException exc){
                    exc.printStackTrace();
                }
            }
        }
        return result;
    }

    @Override
    public boolean delete(Pointage pointage) {
        boolean result = false;
        PreparedStatement preparedStatement = null;
        try{
            preparedStatement = connection.prepareStatement(DELETE_QUERY);

            preparedStatement.setInt(1,pointage.getIdEmploye());
            preparedStatement.executeUpdate();
            result = true;
        }catch (SQLException e){
            e.printStackTrace();
        }finally{
            if(preparedStatement!=null){
                try{
                    preparedStatement.close();
                }catch (SQLException exc){
                    exc.printStackTrace();
                }
            }
        }
        return result;
    }

    @Override
    public List<Pointage> findByEmployeId(int id) {
        List<Pointage> pointages = new ArrayList<Pointage>();
        PreparedStatement preparedStatement = null;
        try {
            preparedStatement = connection.prepareStatement(FIND_BY_EMPLOYE_ID_QUERY);
            preparedStatement.setInt(1, id);
            ResultSet rs = preparedStatement.executeQuery();

            while(rs.next()) {
                Pointage pointage = new Pointage();
                pointage.setIdEmploye(rs.getInt("idEmploye"));
                pointage.setDate(rs.getString("date"));
                pointage.setIN_OUT(rs.getString("IN_OUT"));
                pointage.setNormalStart(rs.getString("normalStart"));
                pointage.setNormalEnd(rs.getString("normalEnd"));
                pointage.setRemarque(rs.getString("remarque"));
                pointage.setAntenne(rs.getString("antenne"));
                pointages.add(pointage);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }finally{
            if(preparedStatement!=null){
                try{
                    preparedStatement.close();
                }catch (SQLException exc){
                    exc.printStackTrace();
                }
            }
        }
        return pointages;
    }

    @Override
    public List<Pointage> findAll() {
        List<Pointage> pointages = new ArrayList<Pointage>();
        Statement statement = null;
        try {
            statement = connection.createStatement();
            ResultSet rs = statement.executeQuery(FIND_ALL_QUERY);

            while(rs.next()) {
                Pointage pointage = new Pointage();
                pointage.setIdEmploye(rs.getInt("idEmploye"));
                pointage.setDate(rs.getString("date"));
                pointage.setIN_OUT(rs.getString("IN_OUT"));
                pointage.setNormalStart(rs.getString("normalStart"));
                pointage.setNormalEnd(rs.getString("normalEnd"));
                pointage.setRemarque(rs.getString("remarque"));
                pointage.setAntenne(rs.getString("antenne"));
                pointages.add(pointage);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }finally{
            if(statement!=null){
                try{
                    statement.close();
                }catch (SQLException exc){
                    exc.printStackTrace();
                }
            }
        }
        return pointages;
    }

    @Override
    public Pointage findLastByEmployeId(int id){
        PreparedStatement preparedStatement = null;
        Pointage p = null;
        try {
            preparedStatement = connection.prepareStatement(FIND_LAST_POINTAGE_QUERY);
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            while( resultSet.next() ) {
                p = new Pointage();
                p.setIdEmploye(( resultSet.getInt( "idEmploye" ) ));
                p.setDate( resultSet.getString( "date" ) );
                p.setIN_OUT( resultSet.getString( "IN_OUT" ) );
                p.setNormalStart(resultSet.getString("normalStart"));
                p.setNormalEnd(resultSet.getString("normalEnd"));
            }
            resultSet.close();
            preparedStatement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }finally{
            if(preparedStatement!=null){
                try{
                    preparedStatement.close();
                }catch (SQLException exc){
                    exc.printStackTrace();
                }
            }
        }
        return p;
    }
}
