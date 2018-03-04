package uk.co.bbc.mybbc.entities;

import javax.validation.constraints.NotEmpty;

public class FileNotification extends NotificationBase {


    @NotEmpty(message = "Parameter 'file_name' is required.")
    private String file_name;

    @NotEmpty(message = "Parameter 'file_type' is required.")
    private String file_type;

    @NotEmpty(message = "Parameter 'file_url' is required.")
    private String file_url;

    public String getFile_name() {
        return file_name;
    }

    public void setFile_name(String file_name) {
        this.file_name = file_name;
    }

    public String getFile_type() {
        return file_type;
    }

    public void setFile_type(String file_type) {
        this.file_type = file_type;
    }

    public String getFile_url() {
        return file_url;
    }

    public void setFile_url(String file_url) {
        this.file_url = file_url;
    }

}
