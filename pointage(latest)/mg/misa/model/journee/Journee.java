package mg.misa.model.journee;

import mg.misa.model.pointage.DateUtil;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.MonthDay;
import java.util.ArrayList;
import java.util.List;

public class Journee {
    private int id;
    private int idEmploye;
    private LocalDate date;
    private TypeJournee type;
    private String nature;

    static List<MonthDay> customDayOffs = new ArrayList<>();
    static final MonthDay[] dayOffsMalagasy = new MonthDay[]{
            MonthDay.of(3,8), //JOURNEE_FEMME
            MonthDay.of(1,1), //JOUR_AN
            MonthDay.of(3,29), //REVOLTE
            toMonthDay(getLundiPaques(LocalDate.now().getYear())), //LUNDI_PAQUE
            MonthDay.of(5,1), //FETE_TRAVAIL
            toMonthDay(getAscension(LocalDate.now().getYear())), //ASCENSION
            toMonthDay(getLundiPentecote(LocalDate.now().getYear())), //LUNDI_PENTECOTE
            MonthDay.of(6,26), //INDEPENDANCE
            MonthDay.of(8,15), //ASSOMPTION
            MonthDay.of(11,1), //TOUSSAINT
            MonthDay.of(12,25) // NOEL
    };

    public int getIdEmploye() {
        return idEmploye;
    }

    public void setIdEmploye(int idEmploye) {
        this.idEmploye = idEmploye;
    }

    public LocalDate getDate() {
        return date;
    }

    public String getDateInString() {
        return DateUtil.format(date);
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public void setDate(String dateString) {
        this.date = DateUtil.parseDate(dateString);
    }

    public TypeJournee getType() {
        return type;
    }

    public String getTypeInString(){
        return type.toString();
    }

    public void setType(TypeJournee type) {
        this.type = type;
    }

    public void setType(String type) {
        this.type = TypeJournee.valueOf(type);
    }

    public String getNature() {
        return nature;
    }

    public void setNature(String nature) {
        this.nature = nature;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "Journee{" +
                "id=" + id +
                ", idEmploye=" + idEmploye +
                ", date=" + date +
                ", type=" + type +
                ", nature='" + nature + '\'' +
                '}';
    }

    private static LocalDate getLundiPaques(int year){
        int a = year % 19,
                b = year / 100,
                c = year % 100,
                d = b / 4,
                e = b % 4,
                g = (8 * b + 13) / 25,
                h = (19 * a + b - d - g + 15) % 30,
                j = c / 4,
                k = c % 4,
                m = (a + 11 * h) / 319,
                r = (2 * e + 2 * j - k - h + m + 32) % 7,
                n = (h - m + r + 90) / 25,
                p = (h - m + r + n + 19) % 32;
        LocalDate paques = LocalDate.of(year,n,p); //dimanche
        paques.plusDays(1);
        return paques;
    }

    private static MonthDay toMonthDay(LocalDate d){
        return MonthDay.of(d.getMonth(),d.getDayOfMonth());
    }

    private static LocalDate getLundiPentecote(int year){
        LocalDate pentecotes = getLundiPaques(year).plusDays(50);
        return pentecotes;
    }

    private static LocalDate getAscension(int year){
        LocalDate ascension = getLundiPaques(year).plusDays(39);
        return ascension;
    }

    protected static boolean checkDayOff(LocalDate day){
        boolean isdayOff = false;
        if(day.getDayOfWeek()== DayOfWeek.SATURDAY || day.getDayOfWeek()== DayOfWeek.SUNDAY){ //week-end
            isdayOff = true;
        }
        for(MonthDay dayOffMalagasy: dayOffsMalagasy){
            if(toMonthDay(day).equals(dayOffMalagasy)){
                isdayOff = true;
            }
        }
        for(MonthDay customDayOff: customDayOffs){
            if(toMonthDay(day).equals(customDayOff)){
                isdayOff = true;
            }
        }
        return isdayOff;
    }
}
