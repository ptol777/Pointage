package mg.misa.model.duree;

import mg.misa.model.pointage.Pointage;

import java.time.Duration;

public class Supplementaire extends Duree {

    Supplementaire(){
        setType("supplementaire");
    }

    static Supplementaire calculateSupplementaire(Pointage pointage){
        Supplementaire supplementaire = null;
        if(!pointage.isIN() && pointage.getTime().isAfter(pointage.getNormalEnd())){
            supplementaire = new Supplementaire();
            supplementaire.setIdEmploye(pointage.getIdEmploye());
            supplementaire.setDate(pointage.getDateOnly());
            supplementaire.setTotal(Duration.between(pointage.getNormalEnd(),pointage.getTime()));
        }
        return supplementaire;
    }
}
