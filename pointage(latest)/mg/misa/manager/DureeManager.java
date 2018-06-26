package mg.misa.manager;

import mg.misa.dao.interfaces.DureeDAO;
import mg.misa.model.duree.Duree;

import java.util.List;

public class DureeManager extends Manager {

    private DureeDAO dureeDAO;

    public DureeManager(){
        super();
        dureeDAO = mysqlDAOFactory.createDureeDAO();
    }

    public List<Duree> getAll(String type){
        return dureeDAO.findAll(type);
    }
}
