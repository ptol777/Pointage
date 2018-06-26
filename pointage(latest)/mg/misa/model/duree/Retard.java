package mg.misa.model.duree;

import mg.misa.model.Autorisation;
import mg.misa.model.pointage.Pointage;
import mg.misa.dao.AbstractDAOFactory;
import mg.misa.dao.interfaces.AutorisationDAO;

import java.time.Duration;
import java.util.List;

public class Retard extends Duree {

    Retard(){
        setType("retard");
    }

    static Retard calculateRetard(Pointage pointage){
        Retard retard = null;
        if(pointage.isIN() && pointage.getTime().isAfter(pointage.getNormalStart())){
            retard = new Retard();
            retard.setIdEmploye(pointage.getIdEmploye());
            retard.setDate(pointage.getDateOnly());
            retard.setTotal(Duration.between(pointage.getNormalStart(),pointage.getTime()));

            AbstractDAOFactory mysqlDAOFactory = AbstractDAOFactory.createDAOFactory(AbstractDAOFactory.MYSQL);
            AutorisationDAO autorisationDAO = mysqlDAOFactory.createAutorisationDAO();
            List<Autorisation> autorisations = autorisationDAO.findByEmployeIdDate(pointage.getIdEmploye(),pointage.getDateOnly());
            if(!autorisations.isEmpty()){
                removeRetardAutorise(retard, pointage, autorisations);
            }
        }
        return retard;
    }

    static void removeRetardAutorise(Retard retard, Pointage pointage, List<Autorisation> autorisations){
        for(Autorisation autorisation:autorisations){
            if(pointage.getTime().isAfter(autorisation.getEnd())){
                retard.total = retard.total.minus(Duration.between(autorisation.getStart(),autorisation.getEnd()));
            }else if(pointage.getTime().isAfter(autorisation.getStart()) && pointage.getTime().isBefore(autorisation.getEnd())){
                retard.total = retard.total.minus(Duration.between(autorisation.getStart(),pointage.getTime()));
            }
        }
    }
}
