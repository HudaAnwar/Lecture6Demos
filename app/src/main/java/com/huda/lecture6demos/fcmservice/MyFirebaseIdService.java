package com.huda.lecture6demos.fcmservice;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.graphics.Color;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.Build;

import androidx.annotation.NonNull;
import androidx.core.app.NotificationCompat;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;


public class MyFirebaseIdService extends FirebaseMessagingService {

    private final String NOTIFICATION_CHANNEL_ID = "com.nursy.app.test";
    private String icon;
    private String title;
    private String body;

    @Override
    public void onMessageReceived(@NonNull RemoteMessage remoteMessage) {
        super.onMessageReceived(remoteMessage);
        String receiver = remoteMessage.getData().get("receiver");
        String uid = FirebaseAuth.getInstance().getCurrentUser().getUid();
        if (uid != null) {
            if (receiver.equals(uid)) {
                prepareData(remoteMessage);
            }
        }
    }

    private void prepareData(RemoteMessage remoteMessage) {
        icon = remoteMessage.getData().get("icon");
        title = remoteMessage.getData().get("title");
        body = remoteMessage.getData().get("body");
        sendNotificationAndNothing();
    }

    private void notificationSettings(
            String icon,
            PendingIntent pendingIntent,
            String title,
            String body
    ) {
        Uri defaultSound = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
        NotificationManager notificationManager = (NotificationManager)
                getSystemService(Context.NOTIFICATION_SERVICE);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            NotificationChannel notificationChannel = new NotificationChannel(
                    NOTIFICATION_CHANNEL_ID,
                    "notification",
                    NotificationManager.IMPORTANCE_HIGH
            );
            notificationChannel.setDescription("Nursy channel");
            notificationChannel.enableLights(true);
            notificationChannel.setLightColor(Color.BLUE);
            notificationManager.createNotificationChannel(notificationChannel);
        }
        NotificationCompat.Builder builder = new NotificationCompat
                .Builder(this, NOTIFICATION_CHANNEL_ID)
                .setDefaults(Notification.DEFAULT_ALL)
                .setWhen(System.currentTimeMillis())
                .setSmallIcon(Integer.parseInt(icon))
                .setOngoing(false)
                .setAutoCancel(true)
                .setContentTitle(title)
                .setContentText(body)
                .setSound(defaultSound)
                .setContentIntent(pendingIntent);

        notificationManager.notify(0, builder.build());
    }

    @Override
    public void onNewToken(@NonNull String s) {
        super.onNewToken(s);
        sendRegistrationToServer(s);
    }

    private void sendNotificationAndNothing() {
        notificationSettings(icon, null, title, body);
    }

    private void sendRegistrationToServer(String token) {
        FirebaseUser firebaseUser = FirebaseAuth.getInstance().getCurrentUser();
        if (firebaseUser != null) {
            DatabaseReference reference = FirebaseDatabase
                    .getInstance().getReference("token");
            String uid = firebaseUser.getUid();
            reference.child(uid).setValue(token);

        }
    }

}
