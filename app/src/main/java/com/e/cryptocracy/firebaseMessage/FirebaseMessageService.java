package com.e.cryptocracy.firebaseMessage;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.media.MediaPlayer;
import android.media.Ringtone;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.core.app.NotificationCompat;
import androidx.navigation.NavDeepLinkBuilder;

import com.e.cryptocracy.R;
import com.e.cryptocracy.utility.App;
import com.e.cryptocracy.utility.AppConstant;
import com.e.cryptocracy.utility.AppUtils;
import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;

import java.util.Date;

public class FirebaseMessageService extends FirebaseMessagingService {

    private static final String TAG = "FirebaseMessageService";
    MediaPlayer mp;

    final String CHANNEL_ID = "com.mytuition";
    Bitmap bitmap;
    Context context;
    int destinationId;
    private NotificationManager mManager;

    @Override
    public void onMessageReceived(@NonNull RemoteMessage remoteMessage) {
        super.onMessageReceived(remoteMessage);

        mp = MediaPlayer.create(getApplicationContext(), R.raw.notificationsound);
        mp.start();


        try {
            showNotification(remoteMessage);
            //Log.d(TAG, "onMessageReceived: " + remoteMessage.getNotification());
        } catch (Exception e) {
            e.printStackTrace();
            Log.d(TAG, "onMessageReceivedError: " + e.getLocalizedMessage());
        }

    }

    private void showNotification(RemoteMessage remoteMessage) {


        String msg = remoteMessage.getNotification().getBody();
        String title = remoteMessage.getNotification().getTitle();
        String click_action = remoteMessage.getNotification().getClickAction();
        String notificationId = remoteMessage.getData().get("notification_id");

        Log.d(TAG, "click_action: " + click_action);
        Log.d(TAG, "notificationId: " + notificationId);


        Bundle bundle = new Bundle();
        bundle.putString(AppConstant.COIN_ID, "bitcoin");
        bundle.putString(AppConstant.NAME, "Bitcoin");
        bundle.putString(AppConstant.SYMBOL, "BTC");


        PendingIntent pendingIntent = new NavDeepLinkBuilder(App.context)
                .setGraph(R.navigation.app_home_nav)
                .setDestination(R.id.coinListFragment)
                //.setArguments(bundle)
                .createPendingIntent();

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            NotificationChannel androidChannel = new NotificationChannel(CHANNEL_ID,
                    title, NotificationManager.IMPORTANCE_HIGH);
            androidChannel.enableLights(true);
            androidChannel.enableVibration(true);
            androidChannel.setLightColor(Color.GREEN);

            androidChannel.setLockscreenVisibility(Notification.VISIBILITY_PUBLIC);
            getManager().createNotificationChannel(androidChannel);

            NotificationCompat.Builder notification = new NotificationCompat.Builder(getApplicationContext(), CHANNEL_ID)
                    .setSmallIcon(R.mipmap.ic_launcher)
                    .setContentTitle(title)
                    .setContentText(msg)
                    .setStyle(new NotificationCompat.BigTextStyle().bigText(msg))
                    .setAutoCancel(true)
                    .setContentIntent(pendingIntent);

            if (bitmap != null) {
                notification.setLargeIcon(bitmap);
            }

            int timestamp = (int) ((new Date().getTime() / 1000L) % Integer.MAX_VALUE);

            getManager().notify(timestamp, notification.build());

            //playNotificationSound();

        } else {

            //playNotificationSound();
            NotificationCompat.Builder notificationBuilder = new NotificationCompat.Builder(context)
                    .setSmallIcon(R.mipmap.ic_launcher)
                    .setContentTitle(title)
                    .setContentText(msg)
                    .setAutoCancel(true)
                    .setPriority(NotificationCompat.PRIORITY_HIGH)
                    .setStyle(new NotificationCompat.BigTextStyle().bigText(msg)).setContentIntent(pendingIntent)
                    .setDefaults(Notification.DEFAULT_ALL)
                    .setLights(0xFF760193, 300, 1000)
                    .setVibrate(new long[]{200, 400});

            if (bitmap != null) {
                notificationBuilder.setLargeIcon(bitmap);
            }
            int timestamp = (int) ((new Date().getTime() / 1000L) % Integer.MAX_VALUE);
            NotificationManager notificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
            notificationManager.notify(timestamp, notificationBuilder.build());

        }


    }

    @Override
    public void onNewToken(@NonNull String s) {
        super.onNewToken(s);
        AppUtils.updateToServer(s);
    }

    public void playNotificationSound() {
        try {
            Uri notificationSoundUri = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
            Ringtone r = RingtoneManager.getRingtone(this, notificationSoundUri);
            r.play();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private NotificationManager getManager() {
        if (mManager == null) {
            mManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        }
        return mManager;
    }
}
