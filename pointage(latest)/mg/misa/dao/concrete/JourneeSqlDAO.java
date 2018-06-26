package mg.misa.dao.concrete;

import mg.misa.model.journee.Journee;
import mg.misa.dao.GenericDAO;
import mg.misa.dao.interfaces.JourneeDAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class JourneeSqlDAO extends GenericDAO implements JourneeDAO {

    JourneeSqlDAO(Connection connection){
        super(connection);
    }

    private static final String INSERT_QUERY = "INSERT INTO journee (idEmploye,date,type,nature) VALUES (?,?,?,?)";
    private static final String UPDATE_QUERY = "UPDATE journee SET date=?,type=?,nature=? WHERE id=?";
    private static final String DELETE_QUERY = "DELETE FROM journee WHERE idEmploye=?";
    private static final String FIND_BY_EMPLOYE_ID_QUERY = "SELECT * FROM journee WHERE idEmploye=? AND nature=?";
    private static final String FIND_ALL_QUERY = "SELECT * FROM journee WHERE nature=?";


    @Override
    public boolean add(Journee journee) throws SQLException{
        boolean result = false;
        PreparedStatement preparedStatement = null;
        try{
            preparedStatement = connection.prepareStatement(INSERT_QUERY);

            preparedStatement.setInt(1,journee.getIdEmploye());
            preparedStatement.setString(2,journee.getDateInString());
            preparedStatement.setString(3,journee.getTypeInString());
            preparedStatement.setString(4,journee.getNature());
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
    public boolean update(Journee journee) throws SQLException{
        boolean result = false;
        PreparedStatement preparedStatement = null;
        try{
            preparedStatement = connection.prepareStatement(UPDATE_QUERY);

            preparedStatement.setString(1,journee.getDateInString());
            preparedStatement.setString(2,journee.getTypeInString());
            preparedStatement.setString(3,journee.getNature());
            preparedStatement.setInt(4,journee.getId());
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
    public boolean delete(Journee journee) {
        boolean result = false;
        PreparedStatement preparedStatement = null;
        try{
            preparedStatement = connection.prepareStatement(DELETE_QUERY);

            preparedStatement.setInt(1,journee.getIdEmploye());
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
    public List<Journee> findByEmployeId(int id) {
        return null;
    }

    @Override
    public List<Journee> findAll() {
        return null;
    }

    @Override
    public List<Journee> findByEmployeId(String nature, int id) {
        List<Journee> journees = new ArrayList<Journee>();
        PreparedStatement preparedStatement = null;

        try {
            preparedStatement = connection.prepareStatement(FIND_BY_EMPLOYE_ID_QUERY);
            preparedStatement.setInt(1, id);
            preparedStatement.setString(2, nature);
            ResultSet resultSet = preparedStatement.executeQuery();
            while( resultSet.next() ) {
                Journee journee = new Journee();
                journee.setIdEmploye(( resultSet.getInt( "idEmploye" ) ));
                journee.setDate(resultSet.getString( "date" ));
                journee.setType(resultSet.getString("type"));
                journee.setNature(resultSet.getString("nature"));
                journee.setId(resultSet.getInt("id"));
                journees.add(journee);
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
        return journees;
    }

    @Override
    public List<Journee> findAll(String nature) {
        List<Journee> journees = new ArrayList<Journee>();
        PreparedStatement preparedStatement = null;

        try {
            preparedStatement = connection.prepareStatement(FIND_ALL_QUERY);
            preparedStatement.setString(1, nature);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                Journee journee = new Journee();
                journee.setIdEmploye(( resultSet.getInt( "idEmploye" ) ));
                journee.setDate(resultSet.getString( "date" ));
                journee.setType(resultSet.getString("type"));
                journee.setNature(resultSet.getString("nature"));
                journee.setId(resultSet.getInt("id"));
                journees.add(journee);
            }
            resultSet.close();
        } catch (SQLException | NullPointerException e) {
            e.printStackTrace();
        } finally {
            if (preparedStatement != null) {
                try {
                    preparedStatement.close();
                } catch (SQLException exc) {
                    exc.printStackTrace();
                }
            }
        }
        return journees;
    }

    @Override
    public void addList(List<Journee> journees) throws SQLException{ //Handle if list vide
        if(journees!=null){
            for (Journee a : journees) {
                add(a);
            }
        }
    }
}
