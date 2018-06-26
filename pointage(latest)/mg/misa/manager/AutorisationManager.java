package mg.misa.manager;

import mg.misa.dao.interfaces.AutorisationDAO;
import mg.misa.model.Autorisation;
import mg.misa.model.employe.Employe;
import mg.misa.model.exceptions.MyDateTimeException;

import java.sql.SQLException;
import java.util.List;

public class AutorisationManager extends Manager {

    private Autorisation autorisation;
    private AutorisationDAO autorisationDAO;
    private Employe employe;

    public AutorisationManager(){
        super();
        autorisationDAO = mysqlDAOFactory.createAutorisationDAO();
    }

    public void createAutorisation(String identity,String date,String start,String end,String motif)throws MyDateTimeException, NullPointerException,SQLException{
        EmployeManager employeManager = new EmployeManager();
        employe = employeManager.findEmploye(identity);
        autorisation = new Autorisation(employe.getId(),date,start,end,motif);
        autorisationDAO.add(autorisation);

    }

    public void updateAutorisation(int id,String date,String start,String end,String motif)throws SQLException,MyDateTimeException, NullPointerException{
        autorisation = new Autorisation();
        autorisation.setDate(date);
        autorisation.setStart(start);
        autorisation.setEnd(end);
        autorisation.setMotif(motif);
        autorisation.setId(id);
        autorisationDAO.update(autorisation);
    }

    public List<Autorisation> getAll(){
        return autorisationDAO.findAll();
    }
}
