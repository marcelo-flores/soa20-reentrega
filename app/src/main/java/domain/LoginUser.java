package domain;

import java.io.Serializable;

/**
 * Clase creada para enviar como body en el método login
 */
public class LoginUser implements Serializable {

    private String email;
    private String password;


    public LoginUser() {
    }

    public LoginUser( String email, String password) {

        this.email = email;
        this.password = password;

    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

}
