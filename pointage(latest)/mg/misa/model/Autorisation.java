package mg.misa.model;

import mg.misa.model.exceptions.MyDateTimeException;
import mg.misa.model.pointage.DateUtil;

import java.time.LocalDate;
import java.time.LocalTime;

public class Autorisation {
    private int id;
    private int idEmploye;
    private LocalDate date;
    private LocalTime start;
    private LocalTime end;
    private String motif;

    public Autorisation(){}

    public Autorisation(int idEmploye, LocalDate date, LocalTime start, LocalTime end, String motif) {
        this.idEmploye = idEmploye;
        this.date = date;
        this.start = start;
        this.end = end;
        this.motif = motif;
    }

    public Autorisation(int idEmploye, String date, String start, String end, String motif) throws MyDateTimeException{
        setIdEmploye(idEmploye);
        setDate(date);
        setStartEnd(start,end);
        setMotif(motif);
    }

    public Autorisation(String idEmploye, String date, String start, String end, String motif) throws MyDateTimeException{
        setIdEmploye(idEmploye);
        setDate(date);
        setStartEnd(start,end);
        setMotif(motif);
    }


    public int getIdEmploye() {
        return idEmploye;
    }

    public void setIdEmploye(int idEmploye) {
        this.idEmploye = idEmploye;
    }

    public void setIdEmploye(String idEmploye){
        idEmploye = idEmploye.trim();
        idEmploye = idEmploye.replaceAll("\\D","");
        this.idEmploye = Integer.parseInt(idEmploye);
    }

    public LocalDate getDate() {
        return date;
    }

    public String getDateInString() {
        return DateUtil.format(date);
    }

    public void setDate(String dateString) throws MyDateTimeException{
        setDate(DateUtil.parseDate(dateString));
    }

    public void setDate(LocalDate date) throws MyDateTimeException{
        if(date.isBefore(LocalDate.now())){
            throw new MyDateTimeException("Cette date est déjà passée");
        }
        this.date = date;
    }

    public void setDateProperly(String date){
        this.date = DateUtil.parseDate(date);
    }

    public void setStartEnd(String start,String end)throws MyDateTimeException{
        setStartEnd(DateUtil.parseTime(start),DateUtil.parseTime(end));
    }

    public void setStartEnd(LocalTime start, LocalTime end)throws MyDateTimeException{
        if(start.isAfter(end)){
            throw new MyDateTimeException("Incongruence dans les heures");
        }
        setStart(start);
        setEnd(end);
    }

    public LocalTime getStart() {
        return start;
    }

    public String getStartInString() {
        return DateUtil.format(start);
    }

    public void setStart(LocalTime start) {
        this.start = start;
    }

    public void setStart(String startString) {
        this.start = DateUtil.parseTime(startString);
    }

    public LocalTime getEnd() {
        return end;
    }

    public String getEndInString() {
        return DateUtil.format(end);
    }

    public void setEnd(LocalTime end) {
        this.end = end;
    }

    public void setEnd(String endString) {
        this.end = DateUtil.parseTime(endString);
    }

    public String getMotif() {
        return motif;
    }

    public void setMotif(String motif) {
        this.motif = motif;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "Autorisation{" +
                "idEmploye=" + idEmploye +
                ", date=" + date +
                ", start=" + start +
                ", end=" + end +
                ", motif='" + motif + '\'' +
                '}';
    }
}
