package mg.misa.dao.concrete;

import mg.misa.model.employe.Employe;
import mg.misa.dao.GenericDAO;
import mg.misa.dao.interfaces.EmployeDAO;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class EmployeSqlDAO extends GenericDAO implements EmployeDAO {

    private static final String INSERT_QUERY = "INSERT INTO employe (id,nom,prenom,sexe,CIN) VALUES (?,?,?,?,?)";
    private static final String UPDATE_QUERY = "UPDATE employe SET nom=?,prenom=?,sexe=?,CIN=?  WHERE id=?";
    private static final String DELETE_QUERY = "DELETE FROM employe WHERE id=?";
    private static final String FIND_BY_EMPLOYE_ID_QUERY = "SELECT * FROM employe WHERE id=?";
    private static final String FIND_ALL_QUERY = "SELECT * FROM employe";
    private static final String FIND_BY_CIN_QUERY = "SELECT * FROM employe WHERE CIN=?";
    private static final String FIND_BY_NOM_QUERY = "SELECT * FROM employe WHERE nom=? AND prenom=?";

    private static final String GET_HOURS_QUERY = "SELECT startMorning,endMorning,startAfternoon,endAfternoon FROM employe " +
            "INNER JOIN heureSpecifique ON employe.id=heureSpecifique.idEmploye WHERE employe.id=? UNION SELECT " +
            "startMorning,endMorning,startAfternoon,endAfternoon FROM heureNormal";
    private static final String INSERT_HOURS_QUERY = "INSERT INTO heureSpecifique (idEmploye,startMorning,endMorning,startAfternoon,endAfternoon) " +
            "VALUEs (?,?,?,?,?)";
    private static final String UPDATE_HOURS_QUERY = "UPDATE heureSpecifique SET startMorning=?,endMorning=?,startAfternoon=?,endAfternoon=? WHERE idEmploye=?";

    EmployeSqlDAO(Connection connection){
        super(connection);
    }

    @Override
    public boolean add(Employe employe) throws SQLException{
        boolean result = false;
        PreparedStatement preparedStatement = null;
        try{
            preparedStatement = connection.prepareStatement(INSERT_QUERY);

            preparedStatement.setInt(1,employe.getId());
            preparedStatement.setString(2,employe.getNom());
            preparedStatement.setString(3,employe.getPrenom());
            preparedStatement.setString(4,employe.getSexeInString());
            preparedStatement.setString(5,employe.getCIN());
            preparedStatement.executeUpdate();

            result = true;
        }catch (SQLException e){
            //e.printStackTrace();
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
    public boolean update(Employe employe) {
        boolean result = false;
        PreparedStatement preparedStatement = null;
        try{
            preparedStatement = connection.prepareStatement(UPDATE_QUERY);

            preparedStatement.setString(1,employe.getNom());
            preparedStatement.setString(2,employe.getPrenom());
            preparedStatement.setString(3,employe.getSexeInString());
            preparedStatement.setString(4,employe.getCIN());
            preparedStatement.setInt(5,employe.getId());
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
    public boolean delete(Employe employe) {
        boolean result = false;
        PreparedStatement preparedStatement = null;
        try{
            preparedStatement = connection.prepareStatement(DELETE_QUERY);

            preparedStatement.setInt(1,employe.getId());
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
    public List<Employe> findByEmployeId(int id) {
        List<Employe> employes = new ArrayList<Employe>();
        PreparedStatement preparedStatement = null;
        try {
            preparedStatement = connection.prepareStatement(FIND_BY_EMPLOYE_ID_QUERY);
            preparedStatement.setInt(1, id);
            ResultSet rs = preparedStatement.executeQuery();

            while(rs.next()) {
                Employe employe = new Employe();
                employe.setId(rs.getInt("id"));
                employe.setNom(rs.getString("nom"));
                employe.setPrenom(rs.getString("prenom"));
                employe.setSexe(rs.getString("sexe"));
                employe.setCINProperly(rs.getString("CIN"));
                getHours(employe);
                employes.add(employe);
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
        return employes;
    }

    @Override
    public Employe findById(int id) {
        Employe employe = new Employe();
        PreparedStatement preparedStatement = null;
        try {
            preparedStatement = connection.prepareStatement(FIND_BY_EMPLOYE_ID_QUERY);
            preparedStatement.setInt(1, id);
            ResultSet rs = preparedStatement.executeQuery();

            if(rs.first()) {
                employe.setId(rs.getInt("id"));
                employe.setNom(rs.getString("nom"));
                employe.setPrenom(rs.getString("prenom"));
                employe.setSexe(rs.getString("sexe"));
                employe.setCINProperly(rs.getString("CIN"));
                getHours(employe);
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
        return employe;
    }

    @Override
    public Employe findByCIN(String CIN) {
        Employe employe = new Employe();
        PreparedStatement preparedStatement = null;
        try {
            preparedStatement = connection.prepareStatement(FIND_BY_CIN_QUERY);
            preparedStatement.setString(1, CIN);
            ResultSet rs = preparedStatement.executeQuery();

            while(rs.next()) {
                employe.setId(rs.getInt("id"));
                employe.setNom(rs.getString("nom"));
                employe.setPrenom(rs.getString("prenom"));
                employe.setSexe(rs.getString("sexe"));
                employe.setCINProperly(rs.getString("CIN"));
                getHours(employe);
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
        return employe;
    }

    @Override
    public Employe findByNom(String nom,String prenom) {
        Employe employe = new Employe();
        PreparedStatement preparedStatement = null;
        try {
            preparedStatement = connection.prepareStatement(FIND_BY_NOM_QUERY);
            preparedStatement.setString(1, nom);
            preparedStatement.setString(2, prenom);
            ResultSet rs = preparedStatement.executeQuery();

            while(rs.next()) {
                employe.setId(rs.getInt("id"));
                employe.setNom(rs.getString("nom"));
                employe.setPrenom(rs.getString("prenom"));
                employe.setSexe(rs.getString("sexe"));
                employe.setCINProperly(rs.getString("CIN"));
                getHours(employe);
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
        return employe;
    }

    @Override
    public List<Employe> findAll() {
        List<Employe> employes = new ArrayList<Employe>();
        Statement statement = null;
        try {
            statement = connection.createStatement();
            ResultSet rs = statement.executeQuery(FIND_ALL_QUERY);

            while(rs.next()) {
                Employe employe = new Employe();
                employe.setId(rs.getInt("id"));
                employe.setNom(rs.getString("nom"));
                employe.setPrenom(rs.getString("prenom"));
                employe.setSexe(rs.getString("sexe"));
                employe.setCINProperly(rs.getString("CIN"));
                getHours(employe);
                employes.add(employe);
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
        return employes;
    }

    @Override
    public boolean getHours(Employe employe) {
        boolean result = false;
        PreparedStatement preparedStatement = null;
        try{
            preparedStatement = connection.prepareStatement(GET_HOURS_QUERY);
            preparedStatement.setInt(1,employe.getId());
            ResultSet rs = preparedStatement.executeQuery();

            if(rs.first()) {
                employe.setHour(Employe.START_MORNING, rs.getString("startMorning"));
                employe.setHour(Employe.END_MORNING, rs.getString("endMorning"));
                employe.setHour(Employe.START_AFTERNOON, rs.getString("startAfternoon"));
                employe.setHour(Employe.END_AFTERNOON, rs.getString("endAfternoon"));
            }
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
    public boolean updateHours(Employe employe) {
        boolean result = false;
        PreparedStatement preparedStatement = null;
        try{
            preparedStatement = connection.prepareStatement(UPDATE_HOURS_QUERY);

            preparedStatement.setString(1,employe.getHourInSTring(Employe.START_MORNING));
            preparedStatement.setString(2,employe.getHourInSTring(Employe.END_MORNING));
            preparedStatement.setString(3,employe.getHourInSTring(Employe.START_AFTERNOON));
            preparedStatement.setString(4,employe.getHourInSTring(Employe.END_AFTERNOON));
            preparedStatement.setInt(5,employe.getId());
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
    public boolean addHours(Employe employe) {
        boolean result = false;
        PreparedStatement preparedStatement = null;
        try{
            preparedStatement = connection.prepareStatement(INSERT_HOURS_QUERY);

            preparedStatement.setInt(1,employe.getId());
            preparedStatement.setString(2,employe.getHourInSTring(Employe.START_MORNING));
            preparedStatement.setString(3,employe.getHourInSTring(Employe.END_MORNING));
            preparedStatement.setString(4,employe.getHourInSTring(Employe.START_AFTERNOON));
            preparedStatement.setString(5,employe.getHourInSTring(Employe.END_AFTERNOON));
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
}
