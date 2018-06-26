package mg.misa.model.employe;



import mg.misa.model.pointage.DateUtil;
import mg.misa.model.exceptions.MyDateTimeException;
import mg.misa.model.exceptions.MyInvalidFormatException;

import java.time.LocalTime;
import java.util.Arrays;
import java.util.regex.Pattern;

public class Employe {

    private int id;
    private String nom;
    private String prenom;
    private Sexe sexe;
    private String CIN;

    private LocalTime[] hours = new LocalTime[4];

    public static final int START_MORNING = 0;
    public static final int END_MORNING = 1;
    public static final int START_AFTERNOON = 2;
    public static final int END_AFTERNOON = 3;

    public Employe(int id, String nom, String prenom, Sexe sexe, String CIN) {
        this.id = id;
        this.nom = nom;
        this.prenom = prenom;
        this.sexe = sexe;
        this.CIN = CIN;
    }

    public Employe(int id, String nom, String prenom, String sexe, String CIN) throws MyInvalidFormatException{
        setId(id);
        setNom(nom);
        setPrenom(prenom);
        setSexe(sexe);
        setCIN(CIN);
    }

    public Employe(String id, String nom, String prenom, String sexe, String CIN) throws MyInvalidFormatException{
        setId(id);
        setNom(nom);
        setPrenom(prenom);
        setSexe(sexe);
        setCIN(CIN);
    }

    public Employe(){}

    public int getId() {
        return id;
    }

    public void setId(String id){
        id = id.trim();
        id = id.replaceAll("\\D","");
        this.id = Integer.parseInt(id);
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = clean(nom);
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = clean(prenom);
    }

    public Sexe getSexe() {
        return sexe;
    }

    public void setSexe(Sexe sexe) {
        this.sexe = sexe;
    }

    public String getSexeInString() {
        return sexe.toString();
    }

    public void setSexe(String sexe) {
        this.sexe = Sexe.valueOf(sexe);
    }

    public String getCIN() {
        return CIN;
    }

    public void setCIN(String CIN) throws MyInvalidFormatException{
        CIN = CIN.trim();
        if(Pattern.matches("\\d{3}-\\d{3}-\\d{3}-\\d{3}",CIN)){
            this.CIN = CIN;
        }else{
            throw new MyInvalidFormatException("Format CIN invalide");
        }
    }

    public void setCINProperly(String CIN){
        this.CIN = CIN;
    }

    public LocalTime[] getHours() {
        return hours;
    }

    public void setHours(LocalTime[] hours) throws MyDateTimeException {
        if(hours[START_MORNING].isAfter(hours[END_MORNING]) || hours[END_MORNING].isAfter(hours[START_AFTERNOON]) ||
                hours[START_AFTERNOON].isAfter(hours[END_AFTERNOON])){
            throw new MyDateTimeException("Incongruence dans les heures");
        }
        this.hours = hours;
    }

    public void setHours(String[] hours) throws MyDateTimeException {
        LocalTime[] newHours = new LocalTime[4];
        for(int i=0;i<this.hours.length;i++){
            newHours[i] = DateUtil.parseTime(hours[i]);
        }
        setHours(newHours);
    }

    public LocalTime getHour(int index){
        return this.hours[index];
    }

    public String getHourInSTring(int index){
        return DateUtil.format(this.hours[index]);
    }

    public void setHour(int index,LocalTime hour){
        this.hours[index] = hour;
    }

    public void setHour(int index,String hour){
        this.hours[index] = DateUtil.parseTime(hour);
    }

    private String clean(String s){
        s = s.trim();
        s = s.replaceAll("\\W","");
        return s;
    }

    @Override
    public String toString() {
        return "Employe{" +
                "id=" + id +
                ", nom='" + nom + '\'' +
                ", prenom='" + prenom + '\'' +
                ", sexe=" + sexe +
                ", CIN='" + CIN + '\'' +
                ", hours=" + Arrays.toString(hours) +
                '}';
    }
}
