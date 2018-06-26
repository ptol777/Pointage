package mg.misa.dao.concrete;

import mg.misa.model.duree.Duree;
import mg.misa.dao.GenericDAO;
import mg.misa.dao.interfaces.DureeDAO;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DureeSqlDAO extends GenericDAO implements DureeDAO {

    DureeSqlDAO(Connection connection){
        super(connection);
    }

    private static final String INSERT_QUERY = "INSERT INTO duree (idEmploye,date,total,type) VALUES (?,?,?,?)";
    private static final String UPDATE_QUERY = "UPDATE duree SET date=?,total=?,type=? WHERE idEmploye=?";
    private static final String DELETE_QUERY = "DELETE FROM duree WHERE idEmploye=?";
    private static final String FIND_BY_EMPLOYE_ID_QUERY = "SELECT * FROM duree WHERE idEmploye=? AND type=?";
    private static final String FIND_ALL_QUERY = "SELECT * FROM duree WHERE type=?";

    @Override
    public boolean add(Duree duree)throws SQLException {
        boolean result = false;
        PreparedStatement preparedStatement = null;
        try{
            preparedStatement = connection.prepareStatement(INSERT_QUERY);

            preparedStatement.setInt(1,duree.getIdEmploye());
            preparedStatement.setString(2,duree.getDateInString());
            preparedStatement.setLong(3,duree.getTotalInMinutes());
            preparedStatement.setString(4,duree.getType());
            preparedStatement.executeUpdate();
            result = true;
        }catch (SQLException e){
            e.printStackTrace();
            throw e;
        }catch (NullPointerException exc){
            //ignore
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
    public boolean update(Duree duree) {
        boolean result = false;
        PreparedStatement preparedStatement = null;
        try{
            preparedStatement = connection.prepareStatement(UPDATE_QUERY);

            preparedStatement.setInt(1,duree.getIdEmploye());
            preparedStatement.setString(2,duree.getDateInString());
            preparedStatement.setLong(3,duree.getTotalInMinutes());
            preparedStatement.setString(4,duree.getType());
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
    public boolean delete(Duree duree) {
        boolean result = false;
        PreparedStatement preparedStatement = null;
        try{
            preparedStatement = connection.prepareStatement(DELETE_QUERY);

            preparedStatement.setInt(1,duree.getIdEmploye());
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
    public List<Duree> findByEmployeId(int id) {
        return null;
    }

    @Override
    public List<Duree> findAll() {
        return null;
    }

    @Override
    public List<Duree> findByEmployeId(String type, int id) {
        List<Duree> Durees = new ArrayList<Duree>();
        PreparedStatement preparedStatement = null;

        try {
            preparedStatement = connection.prepareStatement(FIND_BY_EMPLOYE_ID_QUERY);
            preparedStatement.setInt(1, id);
            preparedStatement.setString(2, type);
            ResultSet resultSet = preparedStatement.executeQuery();
            while( resultSet.next() ) {
                Duree Duree = new Duree();
                Duree.setIdEmploye(( resultSet.getInt( "idEmploye" ) ));
                Duree.setDate(resultSet.getString( "date" ));
                Duree.setTotal(resultSet.getInt( "total" ));
                Duree.setType(resultSet.getString("type"));
                Durees.add(Duree);
            }
            resultSet.close();
        } catch (SQLException|NullPointerException e) {
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
        return Durees;
    }

    @Override
    public List<Duree> findAll(String type) {
        List<Duree> Durees = new ArrayList<Duree>();
        PreparedStatement preparedStatement = null;

        try {
            preparedStatement = connection.prepareStatement(FIND_ALL_QUERY);
            preparedStatement.setString(1, type);
            ResultSet resultSet = preparedStatement.executeQuery();
            while( resultSet.next() ) {
                Duree Duree = new Duree();
                Duree.setIdEmploye(( resultSet.getInt( "idEmploye" ) ));
                Duree.setDate(resultSet.getString( "date" ));
                Duree.setTotal(resultSet.getInt( "total" ));
                Duree.setType(resultSet.getString("type"));
                Durees.add(Duree);
            }
            resultSet.close();
        } catch (SQLException|NullPointerException e) {
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
        return Durees;
    }
}
