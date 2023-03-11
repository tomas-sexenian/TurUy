package excepciones;

public class PasswordsNoCoincidenException extends Exception{
    public PasswordsNoCoincidenException() {
        super("Las contraseñas ingresadas no coinciden");
    }
}
