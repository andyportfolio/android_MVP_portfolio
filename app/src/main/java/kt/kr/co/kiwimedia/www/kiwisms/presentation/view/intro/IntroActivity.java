package kt.kr.co.kiwimedia.www.kiwisms.presentation.view.intro;

import android.Manifest;
import android.content.ActivityNotFoundException;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.provider.Settings;
import android.support.annotation.NonNull;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.view.Window;
import android.widget.LinearLayout;

import kt.kr.co.kiwimedia.www.kiwisms.R;
import kt.kr.co.kiwimedia.www.kiwisms.presentation.view.BaseActivity;
import kt.kr.co.kiwimedia.www.kiwisms.presentation.view.login.LoginActivity;

public class IntroActivity extends BaseActivity {
    private final static String TAG = "IntroActivity";
    private Handler h;//핸들러 선언

    private static final int MY_PERMISSIONS_REQUEST_SEND_SMS = 0;
    private static final int MY_REQUEST_CODE = 1;

    private static final Uri URI = Uri.parse("content://com.google.android.gsf.gservices");
    private static final String ID_KEY = "android_id";

    private LinearLayout mLinearLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        requestWindowFeature(Window.FEATURE_NO_TITLE); //인트로화면이므로 타이틀바를 없앤다
        setContentView(R.layout.activity_intro);

        mLinearLayout = (LinearLayout) findViewById(R.id.ll_intro);

        //Permission Check
        //http://gun0912.tistory.com/55
        populateAutoComplete();

        // TODO: 2016. 11. 24.
        // version check 기능 추가 필요



    }


    Runnable mrun = new Runnable(){
        @Override
        public void run(){

            Intent intent = LoginActivity.getCallingIntent(IntroActivity.this);
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP
                    | intent.FLAG_ACTIVITY_SINGLE_TOP); //
            startActivity(intent);
            finish();

            overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
            //overridePendingTransition 이란 함수를 이용하여 fade in,out 효과를줌. 순서가 중요
        }
    };


    @Override
    public void onBackPressed() {
        super.onBackPressed();
        h.removeCallbacks(mrun); //handler를 cancel함
    }

    private void populateAutoComplete() {
        if (mayRequestContacts()) {

            h= new Handler(); //딜래이를 주기 위해 핸들러 생성
            h.postDelayed(mrun, 1000); // 딜레이 ( 런어블 객체는 mrun, 시간 1초)

        }else{
            return;
        }
    }

    private boolean mayRequestContacts() {
        //23 버전보다 아래라면 앱이 설치되면 모든 권한이 허용되어있는 상태에서 시작합니다.
        //25 < 23 ==> 마시멜로우 이상에서는 실행시점에서 권한을 요청
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.M) {
            return true;
        }

        //권한 있음
        if (checkSelfPermission(Manifest.permission.SEND_SMS) == PackageManager.PERMISSION_GRANTED) {
            Log.d(TAG,"checkSelfPermission(Manifest.permission.SEND_SMS) == PackageManager.PERMISSION_GRANTED");
            return true;
        }else{
            //권한요청
            requestPermissions(new String[]{Manifest.permission.SEND_SMS}, MY_PERMISSIONS_REQUEST_SEND_SMS);
        }
        return false;

    }

    /**
     * Callback received when a permissions request has been completed.
     * 퍼미션 요청후 요청후 결과 가져오기 함수
     */
    @Override
    public void onRequestPermissionsResult(int requestCode,
                                           @NonNull String[] permissions,
                                           @NonNull int[] grantResults) {

        switch (requestCode){
            case MY_PERMISSIONS_REQUEST_SEND_SMS:
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED){
                    //권한허가 됨
                    populateAutoComplete();
                }else{
                    //권한거부 됨
                    showRequestAgainDialog();
                }
        }

    }

    private void showRequestAgainDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage(getString(R.string.alert_title));

        builder.setPositiveButton(getString(R.string.alert_positive_button), new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                try{
                    Intent intent = new Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS)
                            .setData(Uri.parse("package:" + getPackageName()));
                    startActivityForResult(intent,MY_REQUEST_CODE);
                }catch(ActivityNotFoundException e){
                    e.printStackTrace();
                    Intent intent = new Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS);
                    startActivityForResult(intent,MY_REQUEST_CODE);

                }
            }
        });

        builder.create().show();

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        Log.d(TAG, "resultCode: " + resultCode);

        switch (requestCode) {
            case MY_REQUEST_CODE:
                if (resultCode == 0){
                    //다시 권한 체크 요청
                    populateAutoComplete();
                }

        }

    }
}

//권한 체크시 하기의 메소드는 return값이 이상하여 사용하지 않음
//해당권한이 필요한 이유를 설명해야 하는지 판단하여(true면 설명필요) 설명하고 권한허가 요청
//만약 [다시묻지않기]를 체크했으면 항상 false를 리턴 , 처음에 권한을 요청할때 false return
//즉 사용자가 [다시문지않기] 를 체크하지 않고 1번이상 권한요청에 대해 거부한 경우에만 ture 리턴
//        if (shouldShowRequestPermissionRationale(Manifest.permission.SEND_SMS)) {
//
//            Log.d(TAG,"shouldShowRequestPermissionRationale(Manifest.permission.SEND_SMS)");
//
//            Snackbar.make(mLinearLayout, R.string.permission_rationale, Snackbar.LENGTH_INDEFINITE)
//                    .setAction(android.R.string.ok, new View.OnClickListener() {
//                        @Override
//                        @TargetApi(Build.VERSION_CODES.M)
//                        public void onClick(View v) {
//                            requestPermissions(new String[]{Manifest.permission.SEND_SMS}, MY_PERMISSIONS_REQUEST_SEND_SMS);
//                        }
//                    });
//        } else {
//            Log.d(TAG,"Not houldShowRequestPermissionRationale(Manifest.permission.SEND_SMS)");
//            //퍼미션을 요청
//            requestPermissions(new String[]{Manifest.permission.SEND_SMS}, MY_PERMISSIONS_REQUEST_SEND_SMS);
//        }
//        return false;