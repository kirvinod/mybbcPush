package uk.co.bbc.mybbc.entities;


import javax.validation.constraints.NotEmpty;

public class NotificationBase {

    @NotEmpty(message = "Parameter 'type' is required.")
    private String type;

    @NotEmpty(message = "Parameter 'body' is required.")
    private String body;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }
}
