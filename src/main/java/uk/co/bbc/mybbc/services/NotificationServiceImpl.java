package uk.co.bbc.mybbc.services;


import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import uk.co.bbc.mybbc.entities.*;
import uk.co.bbc.mybbc.exceptions.CustomNotificationValidator;
import uk.co.bbc.mybbc.exceptions.InvalidInputException;
import java.util.Map;


@Service
public class NotificationServiceImpl implements NotificationService{

    @Autowired
    UserService userService;

    @Autowired
    CustomNotificationValidator customNotificationValidator;

    @Value("${pushbullet.pushUrl}")
    private String pushBulletPushUrl;

    private final String ACCESS_TOKEN = "Access-Token";



    public NotificationResponse sendNotification(Map<String, String> jsonRequest) {

        NotificationBase notification = createNotificationTypeObject(jsonRequest);

        //validate created object
        customNotificationValidator.validateFields(notification);

        User user = getUser (jsonRequest.get("username"));

        sendToPushBullet (notification, user.getAccessToken ());

        // increment notification push
        user.setNumOfNotificationsPushed(user.getNumOfNotificationsPushed() + 1);
        userService.save(user);

        return new NotificationResponse("OK", "Notification Sent Successfully!");

    }



    private void sendToPushBullet(NotificationBase notification, String accessToken){
        RestTemplate restTemplate = new RestTemplate();
        HttpEntity<?> entity = new HttpEntity<>(notification, getHttpHeaders (accessToken));
        restTemplate.postForObject(pushBulletPushUrl, entity, String.class);
        //could extend based on response
    }

    private User getUser(String username){

        if (username == null) throw new InvalidInputException("Parameter 'username' is required.");

        User user = userService.findUserByUsername(username.toLowerCase ());

        if(user == null) throw new InvalidInputException("Parameter 'username' is not valid.");

        return user;
    }


    private NotificationBase createNotificationTypeObject(Map<String, String> jsonRequest) {

        String notificationType = jsonRequest.get("type");

        if(notificationType == null) {
            throw new InvalidInputException("Missing or Invalid 'type' Parameter.");
        }

        ObjectMapper mapper = new ObjectMapper().configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);

        try {
            if(notificationType.equals("note")) {
                return mapper.convertValue(jsonRequest, NoteNotification.class);
            } else if(notificationType.equals("link")) {
                return mapper.convertValue(jsonRequest, LinkNotification.class);
            } else if(notificationType.equals("file")) {
                return mapper.convertValue(jsonRequest, FileNotification.class);
            } else {
                throw new InvalidInputException("Missing or Invalid 'type' Parameter.");
            }
        } catch(Exception e) {
            throw new InvalidInputException(e.getMessage ());
        }

    }

    private HttpHeaders getHttpHeaders(String accessToken) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.set(ACCESS_TOKEN, accessToken);
        return headers;
    }
}