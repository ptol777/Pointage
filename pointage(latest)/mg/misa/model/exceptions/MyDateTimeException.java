package mg.misa.model.exceptions;

public class MyDateTimeException extends Exception{
    private String message;

    public MyDateTimeException(String message){
        this.message = message;
    }

    public String getMessage(){
        return this.message;
    }
}
