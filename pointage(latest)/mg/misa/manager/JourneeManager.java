package mg.misa.manager;

import mg.misa.dao.interfaces.JourneeDAO;
import mg.misa.manager.Manager;
import mg.misa.model.employe.Employe;
import mg.misa.model.exceptions.MyDateTimeException;
import mg.misa.model.journee.Conge;
import mg.misa.model.journee.Journee;

import java.sql.SQLException;
import java.util.List;

public class JourneeManager extends Manager {

    private JourneeDAO journeeDAO;
    private List<Journee> journees;
    private Journee journee;
    private Employe employe;

    public JourneeManager(){
        super();
        journeeDAO = mysqlDAOFactory.createJourneeDAO();
    }

    public void createConge(String identity,String start,String end,String type)throws NullPointerException,SQLException,MyDateTimeException{
        EmployeManager employeManager = new EmployeManager();
        employe = employeManager.findEmploye(identity);
        journees = Conge.createCongesById(employe.getId(),start,end,type);
        try{
            journeeDAO.addList(journees);
        }catch (SQLException e){
            e.printStackTrace();
        }
    }

    public List<Journee> getAll(String nature){
        return journeeDAO.findAll(nature);
    }

    public void updateJournee(int id,String date,String type)throws SQLException,MyDateTimeException{
        journee = new Conge();
        journee.setDate(date);
        journee.setType(type);
        journee.setId(id);
        journee.setNature("conge");
        System.out.println(journee);
        System.out.println(journeeDAO);
        journeeDAO.update(journee);
    }
}
