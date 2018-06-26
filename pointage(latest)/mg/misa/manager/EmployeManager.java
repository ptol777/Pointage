package mg.misa.manager;

import mg.misa.dao.interfaces.EmployeDAO;
import mg.misa.model.employe.Employe;
import mg.misa.model.exceptions.MyInvalidFormatException;

import java.sql.SQLException;
import java.util.List;
import java.util.regex.Pattern;

public class EmployeManager extends Manager{

    private Employe employe;
    private EmployeDAO employeDAO;

    public EmployeManager(){
        super();
        employeDAO = mysqlDAOFactory.createEmployeDAO();
    }

    public Employe findEmploye(String identity){
        Employe employe = null;
        identity = identity.trim();
        identity = identity.replaceAll("[\\W&&[^-\\s]]","");
        System.out.println(identity);
        if(Pattern.matches("\\d{3}-\\d{3}-\\d{3}-\\d{3}",identity)){
            employe = employeDAO.findByCIN(identity);
        }else if(Pattern.matches("\\d*",identity)){
            employe = employeDAO.findById(Integer.parseInt(identity));
        }else if(Pattern.matches("[a-zA-Z]*",identity)){
            //String[] s = identity.split("\\s");

        }
        return employe;
    }

    public void createEmploye(String id,String nom,String prenom,String sexe,String CIN)throws MyInvalidFormatException,SQLException{
        employe = new Employe(id,nom,prenom,sexe,CIN);
        employeDAO.add(employe);
    }

    public void updateEmploye(String id,String nom,String prenom,String sexe,String CIN)throws MyInvalidFormatException,SQLException{
        employe = new Employe(id,nom,prenom,sexe,CIN);
        employeDAO.update(employe);
    }

    public List<Employe> getAll(){
        return employeDAO.findAll();
    }
}
