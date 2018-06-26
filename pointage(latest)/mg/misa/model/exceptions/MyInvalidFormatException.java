package mg.misa.model.exceptions;

public class MyInvalidFormatException extends Exception {
    String message;

    public MyInvalidFormatException(String message){
        this.message = message;
    }

    @Override
    public String getMessage(){
        return this.message;
    }
}
