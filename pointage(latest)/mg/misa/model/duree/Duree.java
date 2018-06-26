package mg.misa.model.duree;

import mg.misa.model.pointage.DateUtil;

import java.time.Duration;
import java.time.LocalDate;

public class Duree {
    protected int idEmploye;
    protected LocalDate date;
    protected Duration total;
    protected String type;

    public int getIdEmploye() {
        return idEmploye;
    }

    public void setIdEmploye(int idEmploye) {
        this.idEmploye = idEmploye;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public String getDateInString() {
        return DateUtil.format(date);
    }

    public void setDate(String dateString) {
        this.date = DateUtil.parseDate(dateString);
    }

    public Duration getTotal() {
        return total;
    }

    public void setTotal(Duration total) {
        this.total = total;
    }

    public long getTotalInMinutes(){
        return this.total.toMinutes();
    }

    public void setTotal(long total){
        this.total = Duration.ofMinutes(total);
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return "Duree{" +
                "idEmploye=" + idEmploye +
                ", date=" + date +
                ", total=" + total +
                ", type='" + type + '\'' +
                '}';
    }
}
