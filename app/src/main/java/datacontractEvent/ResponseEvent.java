package datacontractEvent;

/**
 * Clase creada para respuesta de la API de event, es el array que nos responde en formato json
 */
public class ResponseEvent {
    private String type_events;
    private Integer dni;
    private String description;
    private Integer id;

    public ResponseEvent() {
    }

    public String getType_events() {
        return type_events;
    }

    public void setType_events(String type_events) {
        this.type_events = type_events;
    }

    public Integer getDni() {
        return dni;
    }

    public void setDni(Integer dni) {
        this.dni = dni;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

}
