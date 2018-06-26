package mg.misa.model.journee;

import mg.misa.model.pointage.DateUtil;
import mg.misa.model.exceptions.MyDateTimeException;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Conge extends Journee {
    public Conge(int idEmploye, LocalDate date, TypeJournee type){
        setIdEmploye(idEmploye);
        setDate(date);
        setType(type);
        setNature("conge");
    }

    public Conge(int idEmploye, String date, String type){
        setIdEmploye(idEmploye);
        setDate(date);
        setType(type);
        setNature("conge");
    }

    public Conge(){
    }

    public static List<Journee> createCongesById(int idEmploye, LocalDate dateDebut, LocalDate dateFin, TypeJournee type)throws MyDateTimeException{
        if(dateDebut.isAfter(dateFin)){
            throw new MyDateTimeException("Date début avant date fin");
        }
        if(dateDebut.isBefore(LocalDate.now())){
            throw new MyDateTimeException("Congé à une date passée non autorisée");
        }
        List<Journee> conges = new ArrayList<Journee>();
        conges.add(new Conge(idEmploye,dateDebut,type));
        while (!(dateDebut.equals(dateFin))) {
            if (!Journee.checkDayOff(dateFin)) {
                conges.add(new Conge(idEmploye, dateFin,type));
            }
            dateFin = dateFin.minusDays(1);
        }
        return conges;
    }

    public static List<Journee> createCongesById(int idEmploye, String dateDebut, String dateFin, String type)throws MyDateTimeException{

        LocalDate start = DateUtil.parseDate(dateDebut);
        LocalDate end = DateUtil.parseDate(dateFin);
        TypeJournee typeJournee = TypeJournee.valueOf(type);
        return createCongesById(idEmploye,start,end,typeJournee);
    }
}
