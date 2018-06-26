package mg.misa.model.pointage;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;
import java.time.format.DateTimeParseException;
import java.time.temporal.ChronoField;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DateUtil {

    public static LocalTime parseTime(String timeString) throws DateTimeParseException {
        LocalTime time = null;
        ///Prepare string
        timeString = timeString.trim();
        timeString = timeString.toLowerCase();
        timeString = timeString.replaceAll("\\D","h");
        timeString = timeString.replaceAll("h+","h");

        DateTimeFormatterBuilder builder = new DateTimeFormatterBuilder();
        DateTimeFormatterBuilder builder2 = new DateTimeFormatterBuilder();
        DateTimeFormatter formatter1 =builder.parseCaseInsensitive()
                .appendValue(ChronoField.HOUR_OF_DAY)
                .appendLiteral("h")
                .appendText(ChronoField.MINUTE_OF_HOUR)
                .toFormatter();

        DateTimeFormatter formatter2 =builder2.parseCaseInsensitive()
                .appendValue(ChronoField.HOUR_OF_DAY)
                .appendLiteral("h")
                .toFormatter();

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("H");
        try{
            time = LocalTime.parse(timeString,formatter);
        }catch(DateTimeParseException e){
            try{
                time = LocalTime.parse(timeString,formatter1);
            }catch(DateTimeParseException ex){
                try{
                    time = LocalTime.parse(timeString,formatter2);
                }catch(DateTimeParseException exe){
                    throw exe;
                }
            }
        }
        return time;
    }

    public static LocalDate parseDate(String dateString) throws DateTimeParseException {
        LocalDate date = null;
        ///Prepare string
        dateString = dateString.trim();
        dateString = dateString.replaceAll("\\W",",");
        dateString = dateString.replaceAll(",+",",");

        ///Build the formatters
        DateTimeFormatterBuilder builder = new DateTimeFormatterBuilder();
        DateTimeFormatterBuilder builder1 = new DateTimeFormatterBuilder();

        Map<Long, String> map = new HashMap<>();
        map.put(1L, "janvier");
        map.put(2L, "fevrier");
        map.put(3L, "mars");
        map.put(4L, "avril");
        map.put(5L, "mai");
        map.put(6L, "juin");
        map.put(7L, "juillet");
        map.put(8L, "aout");
        map.put(9L, "septembre");
        map.put(10L, "octobre");
        map.put(11L, "novembre");
        map.put(12L, "decembre");

        Map<Long, String> mapMalagasy = new HashMap<>();
        mapMalagasy.put(1L, "janoary");
        mapMalagasy.put(2L, "febroary");
        mapMalagasy.put(3L, "martsa");
        mapMalagasy.put(4L, "aprily");
        mapMalagasy.put(5L, "may");
        mapMalagasy.put(6L, "jona");
        mapMalagasy.put(7L, "jolay");
        mapMalagasy.put(8L, "aogositra");
        mapMalagasy.put(9L, "septambra");
        mapMalagasy.put(10L, "oktobra");
        mapMalagasy.put(11L, "novambra");
        mapMalagasy.put(12L, "desambra");

        DateTimeFormatter formatterFrenchMonth =builder.parseCaseInsensitive()
                .appendValue(ChronoField.DAY_OF_MONTH)
                .appendLiteral(",")
                .appendText(ChronoField.MONTH_OF_YEAR,map)
                .appendLiteral(",")
                .appendValue(ChronoField.YEAR)
                .toFormatter();

        DateTimeFormatter formatterMalagasyMonth =builder1.parseCaseInsensitive()
                .appendValue(ChronoField.DAY_OF_MONTH)
                .appendLiteral(",")
                .appendText(ChronoField.MONTH_OF_YEAR,mapMalagasy)
                .appendLiteral(",")
                .appendValue(ChronoField.YEAR)
                .toFormatter();

        DateTimeFormatter formatter1 = DateTimeFormatter.ofPattern("y,d,M");
        DateTimeFormatter formatter2 = DateTimeFormatter.ofPattern("d,M,y");

        try{
            date = LocalDate.parse(dateString,formatterMalagasyMonth);
        }catch (DateTimeParseException e){
            try{
                date = LocalDate.parse(dateString,formatterFrenchMonth);
            }catch (DateTimeParseException e1){
                try{
                    date = LocalDate.parse(dateString,formatter1);
                }catch (DateTimeParseException e2){
                    try{
                        date = LocalDate.parse(dateString,formatter2);
                    }catch (DateTimeParseException e3){
                        try{
                            date = LocalDate.parse(dateString+","+LocalDate.now().getYear(),formatterMalagasyMonth);
                        }catch (DateTimeParseException e4){
                            try{
                                date = LocalDate.parse(dateString+","+LocalDate.now().getYear(),formatterFrenchMonth);
                            }catch (DateTimeParseException e5){
                                try{
                                    date = LocalDate.parse(dateString+","+LocalDate.now().getYear(),formatter1);
                                }catch (DateTimeParseException e6){
                                    date = LocalDate.parse(dateString+","+LocalDate.now().getYear(),formatter2);
                                }
                            }
                        }
                    }
                }
            }
        }
        return date;
    }

    public static String format(LocalDate date){
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        return date.format(dateTimeFormatter);
    }

    public static String format(LocalTime time){
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("HH:mm");
        return time.format(dateTimeFormatter);
    }
}
