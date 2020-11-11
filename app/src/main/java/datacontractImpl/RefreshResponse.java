package datacontractImpl;

import datacontractDomain.SOAResponse;


/**
 * Clase creada para la respuesta de la API/refresh extiende de SOAResponse
 */
public class RefreshResponse extends SOAResponse {


    public RefreshResponse() {
    }

    @Override
    public String toString() {
        return "RefreshResponse{" +
                "success='" + this.getSuccess() + '\'' +
                ", env='" + this.getEnv() + '\'' +
                ", token='" + this.getToken() + '\'' +
                ", msg='" + this.getMsg() + '\'' +
                '}';
    }
}