package mg.misa.dao.concrete;

import mg.misa.model.Autorisation;
import mg.misa.model.pointage.DateUtil;
import mg.misa.dao.GenericDAO;
import mg.misa.dao.interfaces.AutorisationDAO;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class AutorisationSqlDAO extends GenericDAO implements AutorisationDAO {

    AutorisationSqlDAO(Connection connection){
        super(connection);
    }

    private static final String INSERT_QUERY = "INSERT INTO autorisation (idEmploye,date, start, end, motif) VALUES (?,?,?,?,?)";
    private static final String UPDATE_QUERY = "UPDATE autorisation SET date=? ,start=?, end=?, motif=? WHERE id=?";
    private static final String DELETE_QUERY = "DELETE FROM autorisation WHERE idEmploye=?";
    private static final String FIND_BY_ID_QUERY = "SELECT * FROM autorisation WHERE idEmploye=?";
    private static final String FIND_BY_ID_DATE_QUERY = "SELECT * FROM autorisation WHERE idEmploye=? AND date= ?";
    private static final String FIND_ALL_QUERY = "SELECT * FROM autorisation";


    @Override
    public boolean add(Autorisation autorisation)throws SQLException {
        boolean result = false;
        PreparedStatement preparedStatement = null;
        try{
            preparedStatement = connection.prepareStatement(INSERT_QUERY);
            preparedStatement.setInt( 1, autorisation.getIdEmploye() );
            preparedStatement.setString( 2, autorisation.getDateInString() );
            preparedStatement.setString( 3, autorisation.getStartInString());
            preparedStatement.setString( 4, autorisation.getEndInString() );
            preparedStatement.setString( 5, autorisation.getMotif());
            preparedStatement.executeUpdate();
            result = true;
        }catch(SQLException e){
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
    public boolean update(Autorisation autorisation) {
        boolean result = false;
        PreparedStatement preparedStatement = null;
        try{
            preparedStatement = connection.prepareStatement(UPDATE_QUERY);
            preparedStatement.setString( 1, autorisation.getDateInString() );
            preparedStatement.setString( 2, autorisation.getStartInString());
            preparedStatement.setString( 3, autorisation.getEndInString() );
            preparedStatement.setString( 4, autorisation.getMotif());
            preparedStatement.setInt( 5, autorisation.getId() );

            preparedStatement.executeUpdate();
            result = true;
        }catch(SQLException e){
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
    public boolean delete(Autorisation autorisation) {
        boolean result = false;
        PreparedStatement preparedStatement = null;
        try {
            preparedStatement = connection.prepareStatement(DELETE_QUERY);
            preparedStatement.setInt(1, autorisation.getIdEmploye());
            preparedStatement.executeUpdate();
            preparedStatement.close();
            result = true;
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
        return result;
    }

    @Override
    public List<Autorisation> findByEmployeId(int id) {
        List<Autorisation> autorisations = new ArrayList<Autorisation>();
        PreparedStatement preparedStatement = null;
        try {
            preparedStatement = connection.prepareStatement(FIND_BY_ID_QUERY);
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            while( resultSet.next() ) {
                Autorisation p = new Autorisation();
                p.setIdEmploye( resultSet.getInt( "idEmploye" ) );
                p.setDateProperly(resultSet.getString("date"));
                p.setStart( resultSet.getString( "start" ));
                p.setEnd( resultSet.getString( "end" ));
                p.setMotif( resultSet.getString( "motif" ));
                p.setId(resultSet.getInt("id"));
                autorisations.add(p);
            }
            resultSet.close();
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
        return autorisations;
    }

    @Override
    public List<Autorisation> findByEmployeIdDate(int id,LocalDate date) {
        List<Autorisation> autorisations = new ArrayList<Autorisation>();
        PreparedStatement preparedStatement = null;

        try {
            preparedStatement = connection.prepareStatement(FIND_BY_ID_DATE_QUERY);
            preparedStatement.setInt(1, id);
            preparedStatement.setString(2, DateUtil.format(date));
            ResultSet resultSet = preparedStatement.executeQuery();
            while( resultSet.next() ) {
                Autorisation p = new Autorisation();
                p.setIdEmploye( resultSet.getInt( "idEmploye" ) );
                p.setDateProperly(resultSet.getString("date"));
                p.setStart( resultSet.getString( "start" ));
                p.setEnd( resultSet.getString( "end" ));
                p.setMotif( resultSet.getString( "motif" ));
                p.setId(resultSet.getInt("id"));
                autorisations.add(p);
            }
            resultSet.close();
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
        return autorisations;
    }

        @Override
    public List<Autorisation> findAll() {
            List<Autorisation> Autorisations = new ArrayList<Autorisation>();
            Statement statement = null;
            try {
                statement = connection.createStatement();
                ResultSet resultSet = statement.executeQuery(FIND_ALL_QUERY);
                while( resultSet.next() ) {
                    Autorisation p = new Autorisation();
                    p.setIdEmploye( resultSet.getInt( "idEmploye" ) );
                    p.setDateProperly(resultSet.getString("date"));
                    p.setStart( resultSet.getString( "start" ));
                    p.setEnd( resultSet.getString( "end" ));
                    p.setMotif( resultSet.getString( "motif" ));
                    p.setId(resultSet.getInt("id"));
                    Autorisations.add(p);
                }
                resultSet.close();
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
            return Autorisations;
    }
}
