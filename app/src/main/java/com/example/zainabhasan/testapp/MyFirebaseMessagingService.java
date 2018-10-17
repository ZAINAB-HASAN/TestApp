package com.example.zainabhasan.testapp;

import android.content.Context;
import android.content.Intent;
import android.util.Log;

import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;

/**
 * Created by admin on 2/5/2018.
 */

public class MyFirebaseMessagingService extends FirebaseMessagingService {

    private static final String TAG = "FirebaseMessageService";
    String message;

    @Override
    public void onMessageReceived(RemoteMessage remoteMessage) {
        Log.d(TAG, "From: " + remoteMessage.getNotification());

        Intent resultIntent = new Intent(getApplicationContext(),MainActivity.class);
        resultIntent.putExtra("message", message);


        if (remoteMessage.getData().size() > 0) {
            Log.d(TAG, "FriendlyMessage data payload: " + remoteMessage.getData());
            message  =  remoteMessage.getData().get("message");
            showNotificationMessageWithBigImage(getApplicationContext(), message, message, String.valueOf( System.currentTimeMillis() ), resultIntent);
        }else {
            Log.d(TAG, "FriendlyMessage Notification Body: " + remoteMessage.getNotification().getBody());
            message  =  remoteMessage.getNotification().getBody();
            showNotificationMessageWithBigImage(getApplicationContext(), message, message, String.valueOf( System.currentTimeMillis() ), resultIntent);

        }



       // String imageUri = remoteMessage.getData().get("image");
        //sendNotification(message, bitmap);



    }

    private void showNotificationMessageWithBigImage(Context context, String title, String message, String timeStamp, Intent intent) {
        Log.d(TAG, "From: " + message);
        NotificationUtils notificationUtils = new NotificationUtils(getApplicationContext());
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        notificationUtils.showNotificationMessage(title, message, timeStamp, intent);
    }


}
//can't finish github sharing process