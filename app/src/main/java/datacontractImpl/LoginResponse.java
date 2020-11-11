package datacontractImpl;

import datacontractDomain.SOAResponse;

/**
 * Clase creada para la respuesta de la API/login extiende de SOAResponse
 */
public class LoginResponse extends SOAResponse {

    public LoginResponse() {
    }

    @Override
    public String toString() {
        return "LoginResponse{" +
                "success='" + this.getSuccess() + '\'' +
                ", env='" + this.getEnv() + '\'' +
                ", token='" + this.getToken() + '\'' +
                ", error='" + this.getMsg() + '\'' +
                '}';
    }
}
