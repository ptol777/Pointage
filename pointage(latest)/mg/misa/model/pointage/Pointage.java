package mg.misa.model.pointage;

import mg.misa.dao.AbstractDAOFactory;
import mg.misa.dao.interfaces.EmployeDAO;
import mg.misa.dao.interfaces.PointageDAO;
import mg.misa.model.employe.Employe;
import mg.misa.model.exceptions.MyDateTimeException;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeParseException;

public class Pointage {

    private Employe employe;
    private Pointage lastPointage;

    private int idEmploye;
    private LocalDateTime date;
    private boolean IN_OUT;
    private LocalTime normalStart;
    private LocalTime normalEnd;
    private String remarque;
    private String antenne;

    public Pointage(int idEmploye, LocalDateTime date, boolean IN_OUT, LocalTime normalStart, LocalTime normalEnd) {
        this.idEmploye = idEmploye;
        this.date = date;
        this.IN_OUT = IN_OUT;
        this.normalStart = normalStart;
        this.normalEnd = normalEnd;
    }

    public Pointage(int idEmploye, LocalDateTime date, boolean IN_OUT, LocalTime normalStart, LocalTime normalEnd, String remarque, String antenne) {
        this.idEmploye = idEmploye;
        this.date = date;
        this.IN_OUT = IN_OUT;
        this.normalStart = normalStart;
        this.normalEnd = normalEnd;
        this.remarque = remarque;
        this.antenne = antenne;
    }

    public Pointage(int idEmploye, LocalDateTime date, boolean IN_OUT) {
        this.idEmploye = idEmploye;
        this.date = date;
        this.IN_OUT = IN_OUT;
    }

    public Pointage(int idEmploye, String date, String time,String antenne,String remarque) throws DateTimeParseException,NullPointerException,MyDateTimeException{
        setEmploye(idEmploye);
        setIdEmploye(employe.getId());
        setDate(date,time);
        calculateNormalStartNormalEnd();
        calculateLastPointage();
        calculateIN_OUT();
        setAntenne(antenne);
        setRemarque(remarque);
    }

    public Pointage(int idEmploye, LocalDateTime date) throws NullPointerException{
        setEmploye(idEmploye);
        setIdEmploye(employe.getId());
        setDate(date);
        calculateNormalStartNormalEnd();
        calculateLastPointage();
        calculateIN_OUT();
        setAntenne(antenne);
        setRemarque(remarque);
    }

    public Pointage(){}

    public void setEmploye(int id){
        AbstractDAOFactory mysqlDAOFactory = AbstractDAOFactory.createDAOFactory(AbstractDAOFactory.MYSQL);
        EmployeDAO employeDAO = mysqlDAOFactory.createEmployeDAO();
        employe = employeDAO.findById(id);
    }

    public int getIdEmploye() {
        return idEmploye;
    }

    public void setIdEmploye(int idEmploye){
        this.idEmploye = idEmploye;
    }

    public LocalDateTime getDate() {
        return date;
    }

    public String getDateInString() {
        return date.toString();
    }

    public LocalDate getDateOnly(){
        return date.toLocalDate();
    }

    public String getDateOnlyInString(){
        return DateUtil.format(date.toLocalDate());
    }

    public LocalTime getTime(){
        return this.date.toLocalTime();
    }

    public String getTimeInString(){
        return DateUtil.format(date.toLocalTime());
    }

    public void setDate(LocalDateTime date) {
        this.date = date;
    }

    public void setDate(String dateString) {
        this.date = LocalDateTime.parse(dateString);
    }

    public void setDate(String dateString, String timeString) throws MyDateTimeException,DateTimeParseException {
        LocalTime time = DateUtil.parseTime(timeString);
        LocalDate date = DateUtil.parseDate(dateString);
        LocalDateTime dateTime = date.atTime(time);
        dateTime = dateTime.withYear(LocalDate.now().getYear());

        if(dateTime.isAfter(LocalDateTime.now())){
            //throw new MyDateTimeException("Pointage futur non autorisé");
        }
        try{
            if(dateTime.isBefore(lastPointage.getDate())){
                throw new MyDateTimeException("Tentative de pointage avant le dernier pointage");
            }
        }catch (NullPointerException e){
            //ignore no lastpointage
        }finally {
            this.date = dateTime;
        }
    }

    public boolean isIN() {
        return IN_OUT;
    }

    public String getIN_OUT() {
        String io;
        if(this.isIN()){
            io = "IN";
        }else {
            io ="OUT";
        }
        return io;
    }

    public void setIN_OUT(boolean IN_OUT) {
        this.IN_OUT = IN_OUT;
    }

    public void setIN_OUT(String IN_OUT) {
        if(IN_OUT.equals("IN")){
            this.IN_OUT = true;
        }else if (IN_OUT.equals("OUT")){
            this.IN_OUT = false;
        }
    }

    public void calculateIN_OUT() {
        if(lastPointage!=null){
            this.IN_OUT = !lastPointage.isIN();
        }else{
            this.IN_OUT = true;
        }
    }

    public LocalTime getNormalStart() {
        return normalStart;
    }

    public String getNormalStartInString(){
        return DateUtil.format(normalStart);
    }

    private void calculateNormalStartNormalEnd() {
        if(date.toLocalTime().isBefore(employe.getHour(Employe.START_AFTERNOON))){
            normalStart = employe.getHour(Employe.START_MORNING);
            normalEnd = employe.getHour(Employe.END_MORNING);
        }
        else{
            normalStart = employe.getHour(Employe.START_AFTERNOON);
            normalEnd = employe.getHour(Employe.END_AFTERNOON);
        }
    }

    public LocalTime getNormalEnd() {
        return normalEnd;
    }

    public String getNormalEndInString() {
        return DateUtil.format(normalEnd);
    }

    public void setNormalStart(String normalStart) {
        this.normalStart = LocalTime.parse(normalStart);
    }

    public void setNormalEnd(String normalEnd) {
        this.normalEnd = LocalTime.parse(normalEnd);
    }

    public void calculateLastPointage(){
        AbstractDAOFactory mysqlDAOFactory = AbstractDAOFactory.createDAOFactory(AbstractDAOFactory.MYSQL);
        PointageDAO pointageDAO = mysqlDAOFactory.createPointageDAO();
        lastPointage = pointageDAO.findLastByEmployeId(idEmploye);
    }

    public Pointage getLastPointage(){
        return lastPointage;
    }

    public String getRemarque() {
        return remarque;
    }

    public void setRemarque(String remarque) {
        this.remarque = remarque;
    }

    public String getAntenne() {
        return antenne;
    }

    public void setAntenne(String antenne) {
        this.antenne = antenne;
    }

    public Employe getEmploye() {
        return employe;
    }

    public void setEmploye(Employe employe) {
        this.employe = employe;
    }

    public void setLastPointage(Pointage lastPointage) {
        this.lastPointage = lastPointage;
    }

    @Override
    public String toString() {
        return "Pointage{" +
                "idEmploye=" + getIdEmploye() +
                "\nDate=" + getDate() +
                "\nIN_OUT=" + getIN_OUT() +
                "\nNormal start=" +getNormalStart()+
                "\nNormal end=" +getNormalEnd()+
                //"\nLast pointage=" +getLastPointage().getDate()+
                "\nRemarque="+getRemarque()+
                "\nAntenne="+getAntenne()+
                '}';
    }
}
