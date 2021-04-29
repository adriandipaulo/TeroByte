package com.terobyte.appmedicamentos.alarmManager;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;

import androidx.core.app.NotificationCompat;

import com.terobyte.appmedicamentos.MainActivity;
import com.terobyte.appmedicamentos.R;

public class AlarmReceiver extends BroadcastReceiver {
    public static final String EXTRA_ID = "com.terobyte.appmedicamentos.id";
    public static final String EXTRA_NOM = "com.terobyte.appmedicamentos.nombre";
    public static final String EXTRA_HOR = "com.terobyte.appmedicamentos.hora";
    public static final String EXTRA_DOS = "com.terobyte.appmedicamentos.dosis";
    public static final String EXTRA_USU = "com.terobyte.appmedicamentos.usuario";
    public static final String EXTRA_FORMATOHORA = "com.terobyte.appmedicamentos.formatohora";
    public static String nombreMed;
    public static String usuarioMed;
    public static String presMed;
    public static Integer medID;


    @Override
    public void onReceive(Context context, Intent intent) {
        nombreMed=intent.getStringExtra(EXTRA_NOM);
        usuarioMed=intent.getStringExtra(EXTRA_USU);
        presMed=intent.getStringExtra(EXTRA_DOS);
        medID=intent.getIntExtra(EXTRA_ID,0);
        showNotification(context);

    }

    public void showNotification(Context context) {
        Intent i = new Intent(context,MainActivity.class);
        PendingIntent pi = PendingIntent.getActivity(context, medID, i, 0);
        NotificationCompat.Builder mBuilder = new NotificationCompat.Builder(context,"Canal001")
                .setSmallIcon(R.drawable.ic_baseline_medical_services_24)
                .setColor(Color.RED)
                .setContentTitle(medID +"-"+ usuarioMed)
                .setContentText("Tomar: " + presMed + " de: " + nombreMed);
        mBuilder.setContentIntent(pi);
        mBuilder.setDefaults(Notification.DEFAULT_SOUND);
        mBuilder.setAutoCancel(true);
        NotificationManager mNotificationManager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
        mNotificationManager.notify(medID, mBuilder.build());
    }}

