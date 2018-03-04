package uk.co.bbc.mybbc.entities;

import javax.validation.constraints.NotEmpty;

public class LinkNotification extends NotificationBase {

    @NotEmpty(message = "Parameter 'url' is missing.")
    private String url;

    @NotEmpty(message = "Parameter 'title' missing.")
    private String title;

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

}
