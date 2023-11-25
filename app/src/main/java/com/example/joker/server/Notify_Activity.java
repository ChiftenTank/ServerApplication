package com.example.joker.server;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.NotificationCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.LinearLayout;

public class Notify_Activity extends AppCompatActivity {

    LinearLayout btn1,btn2,btn3,btn4,btn5,btn6,btn7,btn8;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notify);

        btn1= (LinearLayout) findViewById(R.id.btn1);
        btn2= (LinearLayout) findViewById(R.id.btn2);
        btn3= (LinearLayout) findViewById(R.id.btn3);
        btn4= (LinearLayout) findViewById(R.id.btn4);
        btn5= (LinearLayout) findViewById(R.id.btn5);
        btn6= (LinearLayout) findViewById(R.id.btn6);
        btn7= (LinearLayout) findViewById(R.id.btn7);
        btn8= (LinearLayout) findViewById(R.id.btn8);
    }

    public void Notify(View v){
        int id=v.getId();

        if (id==R.id.btn1)
            Sample_Notify_1();
        else if (id==R.id.btn2)
            Sample_Notify_2();
        else if (id==R.id.btn3)
            Two_Option_Notify();
        else if (id==R.id.btn4)
            Big_Text_Notify();
        else if (id==R.id.btn5)
            Big_Picture_Notify();
        else if (id==R.id.btn6)
            Inbox_Style_Notify();
        else if (id==R.id.btn7)
            Big_Picture_Two_Option_Notify();
        else if (id==R.id.btn8)
            Large_Icon_Notify();
    }

    public static String create_notification_channel(Context context){
       if (Build.VERSION.SDK_INT>= Build.VERSION_CODES.O){
           String Channel_ID="Channel_id";
           CharSequence Channel_Name="Channel_Name";
           String Channel_Description="Channel-Description";

           int Channel_Importance= NotificationManager.IMPORTANCE_DEFAULT;
           boolean Channel_Enable_Vibrate=true;
           boolean Channel_Enabled_Light=true;

           NotificationChannel notificationChannel=new NotificationChannel(Channel_ID,Channel_Name,Channel_Importance);
           notificationChannel.setDescription(Channel_Description);
           notificationChannel.enableVibration(Channel_Enable_Vibrate);
           notificationChannel.enableLights(Channel_Enabled_Light);

           NotificationManager manager= (NotificationManager) context.getSystemService(context.NOTIFICATION_SERVICE);
           assert manager !=null ;
           manager.createNotificationChannel(notificationChannel);
           return Channel_ID;
       }else
        return null;
    }

    private void Sample_Notify_1(){
        String Channel_id=create_notification_channel(Notify_Activity.this);
        NotificationCompat.Builder builder=new NotificationCompat.Builder(Notify_Activity.this);
        builder.setContentTitle("Simple Notify 1");
        builder.setContentText("This is a First Simple Notify");
        builder.setSmallIcon(R.drawable.ico_1);
        builder.setAutoCancel(true);
        builder.setPriority(Notification.PRIORITY_MAX);
        builder.setColor(4334567);

        Notification notification= builder.build();
        NotificationManager manager= (NotificationManager) this.getSystemService(NOTIFICATION_SERVICE);
        manager.notify(Channel_id,123,notification);
    }
    public void Sample_Notify_2(){
        String Channel_id=create_notification_channel(Notify_Activity.this);
        NotificationCompat.Builder builder=new NotificationCompat.Builder(Notify_Activity.this);
        builder.setContentTitle("Simple Notify 2");
        builder.setContentText("This Is a Second Simple Notify");
        builder.setSmallIcon(R.drawable.ico_2);
        builder.setAutoCancel(true);
        builder.setPriority(Notification.PRIORITY_MAX);
        builder.setColor(644767);

        Intent intent=new Intent(Notify_Activity.this,Noti_Activity.class);
        PendingIntent pendingIntent=PendingIntent.getActivity(Notify_Activity.this,0,intent,0);
        builder.setContentIntent(pendingIntent);
        Notification notification= builder.build();
        NotificationManager manager= (NotificationManager) this.getSystemService(NOTIFICATION_SERVICE);
        manager.notify(Channel_id,233,notification);
    }
    public void Two_Option_Notify(){
        String Channel_id=create_notification_channel(Notify_Activity.this);
        NotificationCompat.Builder builder=new NotificationCompat.Builder(Notify_Activity.this);
        builder.setContentTitle("Two Big Option");
        builder.setContentText("This is a Two_Option_Notify");
        builder.setSmallIcon(R.drawable.ico_3);
        builder.setAutoCancel(true);
        builder.setPriority(Notification.PRIORITY_MAX);
        builder.setColor(846567);

        Intent intent=new Intent(Notify_Activity.this,Noti_Activity.class);
        PendingIntent pendingIntent=PendingIntent.getActivity(Notify_Activity.this,0,intent,0);

        Intent i1=new Intent(Notify_Activity.this,Noti1_Activity.class);
        PendingIntent peni1=PendingIntent.getActivity(Notify_Activity.this,0,i1,0);

        Intent i2=new Intent(Notify_Activity.this,Noti2_Activity.class);
        PendingIntent peni2=PendingIntent.getActivity(Notify_Activity.this,0,i2,0);

        builder.addAction(android.R.drawable.ic_dialog_dialer,"Delete",peni1);
        builder.addAction(android.R.drawable.ic_dialog_email,"Email",peni2);
        builder.setContentIntent(pendingIntent);

        Notification notification= builder.build();
        NotificationManager manager= (NotificationManager) this.getSystemService(NOTIFICATION_SERVICE);
        manager.notify(Channel_id,233,notification);
    }
    public void Big_Text_Notify(){
        String Channel_id=create_notification_channel(Notify_Activity.this);

        NotificationCompat.BigTextStyle style=new NotificationCompat.BigTextStyle();
        style.setBigContentTitle("Big_Text_Notify");
        style.bigText("This is a Big_Text_NotifyBig_Text_NotifyBig_Text_NotifyBig_Text_NotifyBig_Text_NotifyBig_Text_Notify");

        NotificationCompat.Builder builder=new NotificationCompat.Builder(Notify_Activity.this);
        builder.setContentTitle("Two Big Option");
        builder.setContentText("This is a Big_Text_Notify");
        builder.setSmallIcon(R.drawable.ico_4);
        builder.setAutoCancel(true);
        builder.setPriority(Notification.PRIORITY_MAX);
        builder.setColor(345567);
        builder.setStyle(style);

        Intent intent=new Intent(Notify_Activity.this,Noti_Activity.class);
        PendingIntent pendingIntent=PendingIntent.getActivity(Notify_Activity.this,0,intent,0);

        Intent i1=new Intent(Notify_Activity.this,Noti1_Activity.class);
        PendingIntent peni1=PendingIntent.getActivity(Notify_Activity.this,0,i1,0);

        Intent i2=new Intent(Notify_Activity.this,Noti2_Activity.class);
        PendingIntent peni2=PendingIntent.getActivity(Notify_Activity.this,0,i2,0);

        builder.addAction(android.R.drawable.ic_dialog_dialer,"Delete",peni1);
        builder.addAction(android.R.drawable.ic_dialog_email,"Email",peni2);
        builder.setContentIntent(pendingIntent);

        Notification notification= builder.build();
        NotificationManager manager= (NotificationManager) this.getSystemService(NOTIFICATION_SERVICE);
        manager.notify(Channel_id,233,notification);


    }
    public void Big_Picture_Notify(){
        String Channel_id=create_notification_channel(Notify_Activity.this);

        Bitmap bmp= BitmapFactory.decodeResource(this.getResources(),R.drawable.big);
        Notification.BigPictureStyle pictureStyle=new Notification.BigPictureStyle();
        pictureStyle.setBigContentTitle("Big_Picture_Notify");
        pictureStyle.bigPicture(bmp);

        NotificationCompat.Builder builder= new NotificationCompat.Builder(Notify_Activity.this);
        builder.setContentTitle("Big_Picture_Notify");
        builder.setContentText("This is a Big_Picture_Notify");
        builder.setSmallIcon(R.drawable.ico_4);
        builder.setAutoCancel(true);
        builder.setPriority(Notification.PRIORITY_MAX);
        builder.setColor(654567);

        Notification notification= builder.build();
        NotificationManager manager= (NotificationManager) this.getSystemService(NOTIFICATION_SERVICE);
        manager.notify(Channel_id,433,notification);


    }

    public void Inbox_Style_Notify(){
        NotificationCompat.InboxStyle inboxStyle=new NotificationCompat.InboxStyle();
        inboxStyle.setBigContentTitle("Inbox_Style_Notify");
        inboxStyle.addLine("Telegram");
        inboxStyle.addLine("WhatsApp");
        inboxStyle.addLine("Instagram");
        inboxStyle.addLine("Message");
        inboxStyle.addLine("Call");

        String Channel_id=create_notification_channel(Notify_Activity.this);

        NotificationCompat.Builder builder= new NotificationCompat.Builder(Notify_Activity.this);
        builder.setContentTitle("Two Big Option");
        builder.setContentText("This is a Inbox_Style_Notify");
        builder.setSmallIcon(R.drawable.ico_6);
        builder.setAutoCancel(true);
        builder.setPriority(Notification.PRIORITY_MAX);
        builder.setColor(974567);
        builder.setStyle(inboxStyle);

        Notification notification= builder.build();
        NotificationManager manager= (NotificationManager) this.getSystemService(NOTIFICATION_SERVICE);
        manager.notify(Channel_id,633,notification);

    }
    public void Big_Picture_Two_Option_Notify(){
        Bitmap bmp= BitmapFactory.decodeResource(this.getResources(),R.drawable.big);
        Notification.BigPictureStyle pictureStyle=new Notification.BigPictureStyle();
        pictureStyle.setBigContentTitle("Big_Picture_Two_Option_Notify");
        pictureStyle.bigPicture(bmp);

        String Channel_id=create_notification_channel(Notify_Activity.this);

        NotificationCompat.Builder builder= new NotificationCompat.Builder(Notify_Activity.this);
        builder.setContentTitle("Two Big Option");
        builder.setContentText("This is a Big_Picture_Two_Option_Notify");
        builder.setSmallIcon(R.drawable.ico_7);
        builder.setAutoCancel(true);
        builder.setPriority(Notification.PRIORITY_MAX);
        builder.setColor(974567);
        builder.setLargeIcon(bmp);

        Intent intent=new Intent(Notify_Activity.this,Noti_Activity.class);
        PendingIntent pendingIntent=PendingIntent.getActivity(Notify_Activity.this,0,intent,0);

        Intent i1=new Intent(Notify_Activity.this,Noti1_Activity.class);
        PendingIntent peni1=PendingIntent.getActivity(Notify_Activity.this,0,i1,0);

        Intent i2=new Intent(Notify_Activity.this,Noti2_Activity.class);
        PendingIntent peni2=PendingIntent.getActivity(Notify_Activity.this,0,i2,0);

        builder.addAction(android.R.drawable.ic_dialog_dialer,"Delete",peni1);
        builder.addAction(android.R.drawable.ic_dialog_email,"Email",peni2);
        builder.setContentIntent(pendingIntent);

        Notification notification= builder.build();
        NotificationManager manager= (NotificationManager) this.getSystemService(NOTIFICATION_SERVICE);
        manager.notify(Channel_id,733,notification);

    }
    public void Large_Icon_Notify(){
        Bitmap bmp= BitmapFactory.decodeResource(this.getResources(),R.drawable.big);

        String Channel_id=create_notification_channel(Notify_Activity.this);

        NotificationCompat.Builder builder= new NotificationCompat.Builder(Notify_Activity.this);

        builder.setContentTitle("Large_Icon_Notify");
        builder.setContentText("This is Large_Icon_Notify");
        builder.setSmallIcon(R.drawable.ico_8);
        builder.setAutoCancel(true);
        builder.setPriority(Notification.PRIORITY_MAX);
        builder.setColor(224967);
        builder.setLargeIcon(bmp);
        builder.setVibrate(new long[]{100,100,100,100,100,100});

        Notification notification= builder.build();
        NotificationManager manager= (NotificationManager) this.getSystemService(NOTIFICATION_SERVICE);
        manager.notify(Channel_id,833,notification);
    }

}
