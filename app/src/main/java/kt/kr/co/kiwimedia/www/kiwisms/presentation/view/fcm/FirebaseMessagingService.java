package kt.kr.co.kiwimedia.www.kiwisms.presentation.view.fcm;

import android.app.NotificationManager;
import android.content.Context;
import android.media.RingtoneManager;
import android.support.v4.app.NotificationCompat;
import android.telephony.SmsManager;

import com.google.firebase.messaging.RemoteMessage;

import java.util.Map;

import javax.inject.Inject;

import dagger.android.AndroidInjection;
import kt.kr.co.kiwimedia.www.kiwisms.R;
import kt.kr.co.kiwimedia.www.kiwisms.domain.SMS;
import kt.kr.co.kiwimedia.www.kiwisms.domain.interactor.DefaultObserver;
import kt.kr.co.kiwimedia.www.kiwisms.domain.interactor.UpdateSMSSendResult;
import kt.kr.co.kiwimedia.www.kiwisms.util.CustomLog;

import static kt.kr.co.kiwimedia.www.kiwisms.util.OtherUtil.getDateTime;

/**
 * Created by andy on 2017. 9. 6..
 */

public class FirebaseMessagingService extends com.google.firebase.messaging.FirebaseMessagingService {
//implements HasServiceInjector {
    private static final String TAG = "FirebaseMsgService";

    private String from;  //message unique id
    private String msg;   //message 내용
    private String receiver_number; //보낼곳 전화번호
    private String num;  //num
    private String datetime;

    //여기서는 구현할 필요가 없다
//    //service에서 injecting하기 위해서 이것을 생성한다
//    @Inject
//    DispatchingAndroidInjector<Service> serviceAndroidInjector;


    @Inject
    UpdateSMSSendResult mUpdateSMSSendResultUsecase;

    @Override
    public void onCreate() {
        AndroidInjection.inject(this); //Inject
        super.onCreate();
    }
    /**
     * Called when message is received.
     *
     * @param remoteMessage Object representing the message received from Firebase Cloud Messaging.
     */
    // [START receive_message]
    @Override
    public void onMessageReceived(RemoteMessage remoteMessage) {
        // [START_EXCLUDE]
        // There are two types of messages data messages and notification messages. Data messages are handled
        // here in onMessageReceived whether the app is in the foreground or background. Data messages are the type
        // traditionally used with GCM. Notification messages are only received here in onMessageReceived when the app
        // is in the foreground. When the app is in the background an automatically generated notification is displayed.
        // When the user taps on the notification they are returned to the app. Messages containing both notification
        // and data payloads are treated as notification messages. The Firebase console always sends notification
        // messages. For more see: https://firebase.google.com/docs/cloud-messaging/concept-options
        // [END_EXCLUDE]

        // Not getting messages here? See why this may be: https://goo.gl/39bRNJ
        CustomLog.d(TAG, "From: " + remoteMessage.getFrom());

        // Check if message contains a data payload.
        if (remoteMessage.getData().size() > 0) {
            CustomLog.d(TAG, "Message data payload: " + remoteMessage.getData());
        }

        // Check if message contains a notification payload.
        if (remoteMessage.getNotification() != null) {
            CustomLog.d(TAG, "Message Notification Body: " + remoteMessage.getNotification().getBody());
            msg = remoteMessage.getNotification().getBody();

        }else{
            msg = "No Message Notification Body";
        }

        // TODO(developer): Handle FCM messages here.

        Map<String,String> map =  remoteMessage.getData();
        from = remoteMessage.getFrom();

        receiver_number = (String)map.get("receiver_number");
        num              = (String)map.get("num");

        if (num == null || num.isEmpty()) num = "NA";

        datetime = getDateTime();

        CustomLog.d(TAG, "from: " + from + " receiver_number: " + receiver_number + " num: " + num + " datetime: " + datetime);


        this.mUpdateSMSSendResultUsecase.execute(new UpdateSMSSendResultObserver(),
                UpdateSMSSendResult.Params.forUpdateSMSSendResult(num, datetime,remoteMessage.getFrom()));

    }

//    @Override
//    public AndroidInjector<Service> serviceInjector() {
//        return serviceAndroidInjector;
//    }


    private final class UpdateSMSSendResultObserver extends DefaultObserver<SMS> {
        @Override
        public void onNext(SMS sms) {
            if (sms.getResult().equals("0")){
                sendNotification();
            }

        }

        @Override
        public void onError(Throwable e) {
            //
        }

        @Override
        public void onComplete() {
            super.onComplete();
        }
    }

    private void sendNotification()
    {

        // Also if you intend on generating your own notifications as a result of a received FCM
        // message, here is where that should be initiated. See sendNotification method below.

        // [END receive_message]

        //// TODO: 2017. 9. 6.
        //1. SMS메세지를 보내고 ,
        //2. 업주에게 SMS를 보낸것을 Notification을 통해서 알려줌
        //3. 서버에 보낸내용을 Update함
        //http://itmir.tistory.com/458

        if (receiver_number == null || receiver_number.isEmpty()) receiver_number = "01065193651";
        if (msg.isEmpty()) msg = "empty message";
        if (from.isEmpty()) from = "empty:001";

        SmsManager smsManager = SmsManager.getDefault();
        smsManager.sendTextMessage(receiver_number, null, msg, null, null);

        String title2 = getApplication().getResources().getString(R.string.msg_title);

        String title = receiver_number + " " + title2;

        NotificationCompat.Builder mBuilder
                = new NotificationCompat.Builder(this).setSmallIcon(R.mipmap.ic_launcher)
                .setContentTitle(title)
                .setContentText(msg)
                .setWhen(System.currentTimeMillis())
                .setAutoCancel(true)
                .setSound(RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION))
                .setVibrate(new long[]{1, 1000});

        NotificationManager notificationManager =
                (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);

        //notificationManager.notify(0 /* ID of notification */, mBuilder.build());
        notificationManager.notify((int) (System.currentTimeMillis() / 1000), mBuilder.build());


        //Notification을 click 했을때 Open되는 activity를 설정하기 위해서 PendingIntent를 설정하는 방법이
        //있지만 여기서는 사용하지 않음.

//        Intent intent = new Intent(this, SMSListActivity.class);
//        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
//
//        PendingIntent contentIntent = PendingIntent.getActivity(this, 0,
//                new Intent(this, SMSListActivity.class), 0);
//
//        mBuilder.setContentIntent(contentIntent);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        CustomLog.d(TAG,"onDestroy() => mUpdateSMSSendResultUsecase.dispose();");
       // mUpdateSMSSendResultUsecase.dispose();
    }
}


