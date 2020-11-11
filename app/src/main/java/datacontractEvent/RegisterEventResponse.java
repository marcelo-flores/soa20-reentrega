package datacontractEvent;

/**
 * Clase creada para la respuesta de la API para el método event
 */
public class RegisterEventResponse {
    private String success;
    private String env;
    private ResponseEvent event;
    private String msg;

    public RegisterEventResponse() {
    }

    public RegisterEventResponse(String success, String env, ResponseEvent event, String msg) {
        this.success = success;
        this.env = env;
        this.event = event;
        this.msg = msg;
    }

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

    public ResponseEvent getEvent() {
        return event;
    }

    public void setEvent(ResponseEvent event) {
        this.event = event;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

}
