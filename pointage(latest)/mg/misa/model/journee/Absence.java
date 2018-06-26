package mg.misa.model.journee;

import mg.misa.model.employe.Employe;
import mg.misa.model.pointage.Pointage;
import mg.misa.dao.AbstractDAOFactory;
import mg.misa.dao.interfaces.JourneeDAO;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Absence extends Journee {

    Absence() {
        setType("absence");
    }

    public Absence(int idEmploye, LocalDate date, TypeJournee Type) {
        setIdEmploye(idEmploye);
        setDate(date);
        setType(Type);
        setNature("absence");
    }

    public static List<Journee> createAbsencesById(Pointage pointage) {
        List<Journee> absences = new ArrayList<Journee>();
        Pointage lastPointage = pointage.getLastPointage();
        if(pointage.isIN() && lastPointage != null) {
            LocalDate lastPointageDate = lastPointage.getDate().toLocalDate();

            if (!(lastPointage.getDateOnly().equals(pointage.getDateOnly()))) {
                if (lastPointage.getNormalEnd().equals(pointage.getEmploye().getHour(Employe.END_MORNING))) {
                    absences.add(new Absence(pointage.getIdEmploye(), lastPointageDate, TypeJournee.HALF_AFTERNOON));
                }
                if (pointage.getNormalStart().equals(pointage.getEmploye().getHour(Employe.START_AFTERNOON))) {
                    absences.add(new Absence(pointage.getIdEmploye(), pointage.getDateOnly(), TypeJournee.HALF_MORNING));
                }
                lastPointageDate = lastPointageDate.plusDays(1); //supposed to be the pointage day so ignore, ignore dateDebut koa stria dernier pointage
                while (!(pointage.getDateOnly().equals(lastPointageDate))) {
                    if (!Journee.checkDayOff(lastPointageDate)) {
                        absences.add(new Absence(pointage.getIdEmploye(), lastPointageDate, TypeJournee.FULL));
                    }
                    lastPointageDate = lastPointageDate.plusDays(1);
                }
            }
            AbstractDAOFactory mysqlDAOFactory = AbstractDAOFactory.createDAOFactory(AbstractDAOFactory.MYSQL);
            JourneeDAO journeeDAO = mysqlDAOFactory.createJourneeDAO();
            List<Journee> conges = journeeDAO.findByEmployeId("conge",pointage.getIdEmploye());
            if(conges != null) {
                List<Journee> absencesToBeRemoved = new ArrayList<Journee>();
                for (Journee conge : conges) {
                    if (conge.getDate().isAfter(lastPointage.getDateOnly()) || conge.getDate().equals(lastPointage.getDateOnly())) {
                        for (Journee absence : absences) {
                            if (absence.getDate().equals(conge.getDate()) && absence.getType().equals(conge.getType())) {
                                absencesToBeRemoved.add(absence);
                            }
                        }
                    }
                }
                absences.removeAll(absencesToBeRemoved);
            }
        }
        return absences;
    }
}
