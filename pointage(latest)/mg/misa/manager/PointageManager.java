package mg.misa.manager;

import mg.misa.dao.interfaces.DureeDAO;
import mg.misa.dao.interfaces.JourneeDAO;
import mg.misa.dao.interfaces.PointageDAO;
import mg.misa.model.pointage.Pointage;
import mg.misa.model.duree.Duree;
import mg.misa.model.duree.DureeFactory;
import mg.misa.model.employe.Employe;
import mg.misa.model.exceptions.MyDateTimeException;
import mg.misa.model.journee.Absence;
import mg.misa.model.journee.Journee;

import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.List;

public class PointageManager extends Manager{

    private Pointage pointage;
    private Employe employe;
    private Duree retard;
    private Duree supp;
    private Duree depart;
    private List<Journee> absences;

    private PointageDAO pointageDAO;

    public PointageManager(){}

    public void pointer(String identity, String date, String time, String antenne, String remarque)throws MyDateTimeException,NullPointerException{
        EmployeManager employeManager = new EmployeManager();
        employe = employeManager.findEmploye(identity);
        pointage = new Pointage(employe.getId(),date,time,antenne,remarque);
        checkDependencies();
        save();
    }

    public void pointer(String identity)throws NullPointerException{
        EmployeManager employeManager = new EmployeManager();
        employe = employeManager.findEmploye(identity);
        pointage = new Pointage(employe.getId(), LocalDateTime.now());
        checkDependencies();
        save();
    }

    private void checkDependencies(){
        retard = DureeFactory.create(DureeFactory.RETARD,pointage);
        supp = DureeFactory.create(DureeFactory.DEPART,pointage);
        depart = DureeFactory.create(DureeFactory.SUPP,pointage);
        absences = Absence.createAbsencesById(pointage);
    }

    private void save(){
        DureeDAO dureeDAO = mysqlDAOFactory.createDureeDAO();
        JourneeDAO journeeDAO = mysqlDAOFactory.createJourneeDAO();
        pointageDAO = mysqlDAOFactory.createPointageDAO();

        try{
            mysqlDAOFactory.openTransaction();
            pointageDAO.add(pointage);
            dureeDAO.add(retard);
            dureeDAO.add(supp);
            dureeDAO.add(depart);
            journeeDAO.addList(absences);
            mysqlDAOFactory.endTransaction();
        }catch (SQLException e){
            mysqlDAOFactory.rollback();
        }
    }

    public List<Pointage> getAll(){
        return pointageDAO.findAll();
    }

}
