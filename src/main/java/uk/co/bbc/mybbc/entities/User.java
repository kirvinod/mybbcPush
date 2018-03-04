package uk.co.bbc.mybbc.entities;


import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.time.LocalDateTime;

@Entity
public class User {

    @Id
    @GeneratedValue
    @JsonIgnore
    private Long id;

    @Column(unique=true)
    @NotEmpty(message = "Parameter 'username' is required.")
    @Size(min = 4, max = 30, message = "Parameter 'username' must be between 4 and 30 characters.")
    @Pattern(regexp = "^[A-Za-z0-9]*$", message = "Parameter 'username' must contain alpha-numeric characters.")
    private String username;

    @NotEmpty(message = "Parameter 'accessToken' is required.")
    @Size(min = 5, max = 50, message = "Parameter 'accessToken' must be between 5 and 50 characters.")
    private String accessToken;

    private int numOfNotificationsPushed;

    private LocalDateTime creationTime;

    public User(){
        this.creationTime = LocalDateTime.now();
    }

    @JsonIgnore
    @JsonProperty(value = "id")
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username.toLowerCase ();
    }

    public void setUsername(String username) {
        this.username = username.toLowerCase ();
    }

    public int getNumOfNotificationsPushed() {
        return numOfNotificationsPushed;
    }

    public void setNumOfNotificationsPushed(int numOfNotificationsPushed) {
        this.numOfNotificationsPushed = numOfNotificationsPushed;
    }

    public LocalDateTime getCreationTime() {
        return creationTime;
    }

    public void setCreationTime(LocalDateTime creationTime) {
        this.creationTime = creationTime;
    }

    public String getAccessToken() {
        return accessToken;
    }

    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
    }
}
