package mg.misa.model.duree;

import mg.misa.model.pointage.Pointage;

public class DureeFactory {

    public static final int RETARD = 0;
    public static final int SUPP = 1;
    public static final int DEPART = 2;

    public static Duree create(int type, Pointage pointage){
        switch(type){
            case RETARD:
                return Retard.calculateRetard(pointage);
            case SUPP:
                return Supplementaire.calculateSupplementaire(pointage);
            case DEPART:
                return DepartPremature.calculateDepart(pointage);
            default:
                return null;
        }
    }
}
