package datacontractDomain;

/**
 * Clase creada para la respuesta para el método Register
 */
public class SOAResponse {

    private String success;
    private String env;
    private String token;
    private String msg;

    public String getSuccess() {
        return success;
    }

    public void setSuccess(String success) {
        this.success = success;
    }

    public String getEnv() {
        return env;
    }

    public void setEnv(String env) {
        this.env = env;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    @Override
    public String toString() {
        return "SOAResponse{" +
                "success='" + success + '\'' +
                ", env='" + env + '\'' +
                ", token='" + token + '\'' +
                ", error='" + msg + '\'' +
                '}';
    }
}
