package uk.co.bbc.mybbc.entities;


import javax.validation.constraints.NotEmpty;

public class NoteNotification extends NotificationBase {

    @NotEmpty(message = "Parameter 'title' is required.")
    private String title;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
