package mg.misa.model.duree;

import mg.misa.model.Autorisation;
import mg.misa.model.pointage.Pointage;
import mg.misa.dao.AbstractDAOFactory;
import mg.misa.dao.interfaces.AutorisationDAO;

import java.time.Duration;
import java.util.List;

public class DepartPremature extends Duree {

    DepartPremature(){
        setType("depart");
    }
    
    static DepartPremature calculateDepart(Pointage pointage){
        DepartPremature depart = null;
        if(!pointage.isIN() && pointage.getTime().isBefore(pointage.getNormalEnd())){
            depart = new DepartPremature();
            depart.setIdEmploye(pointage.getIdEmploye());
            depart.setDate(pointage.getDateOnly());
            depart.setTotal(Duration.between(pointage.getTime(),pointage.getNormalEnd()));

            AbstractDAOFactory mysqlDAOFactory = AbstractDAOFactory.createDAOFactory(AbstractDAOFactory.MYSQL);
            AutorisationDAO autorisationDAO = mysqlDAOFactory.createAutorisationDAO();
            List<Autorisation> autorisations = autorisationDAO.findByEmployeIdDate(pointage.getIdEmploye(),pointage.getDateOnly());
            if(!autorisations.isEmpty()){
                removeDepartAutorise(depart, pointage, autorisations);
            }
        }
        return depart;
    }

    static void removeDepartAutorise(DepartPremature depart, Pointage pointage,List<Autorisation> autorisations){
        for(Autorisation autorisation:autorisations){
            if(pointage.getTime().isBefore(autorisation.getStart())){
                depart.total = depart.total.minus(Duration.between(autorisation.getStart(),autorisation.getEnd()));
            }else if(pointage.getTime().isAfter(autorisation.getStart()) && pointage.getTime().isBefore(autorisation.getEnd())){
                depart.total = depart.total.minus(Duration.between(pointage.getTime(),autorisation.getEnd()));
            }
        }
    }
}
