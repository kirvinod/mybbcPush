package uk.co.bbc.mybbc.services;


import uk.co.bbc.mybbc.entities.NotificationResponse;

import java.util.Map;

public interface NotificationService {

    NotificationResponse sendNotification(Map<String, String> jsonRequest);

}
