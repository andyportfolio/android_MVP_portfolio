package kt.kr.co.kiwimedia.www.kiwisms.presentation.view;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.widget.LinearLayout;
import android.widget.Toast;

import javax.inject.Inject;

import kt.kr.co.kiwimedia.www.kiwisms.presentation.navigation.Navigator;

/**
 * Created by andy on 2017. 8. 28..
 */

public abstract class BaseActivity extends AppCompatActivity{
    private static final String TAG = "DependencyInjection";

    //[Field Injection]
    //Field Injection을 할때는 해당 class의 생성자에 @Inject가 있거나, module에서 @Provider로 제공을 해야한다
    //new Navigator() 디폴트 생성자가 있는것에 경우
    // 1. 해당 class에 하기와 같이 기술
    // @Singleton
    // public class Navigator {
    //  @Inject
    //  public Navigator() {

    //실제 Inject가 될때 Navigator객체가 생성되어 주입된다.
    //즉 변수에 주입 될때 객체가 생기거나
    //생성자에 파라메터로 주입될때 객체가 생성된다.
    @Inject
    public Navigator gNavigator; //Navigator Class 에 생성자가 @Inject로 존재해야 함

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {

        //여기에서 Navigator Inject을 안하는 이유는
        //Injection을 하게 되면 LoginActivityModule의 new LoginPresenter(view);를 한번 수행하고
        //LoginActivity에서 @Inject LoginPresenter를 통해서 다시한번더 수행하기 때문이다
        //두번 생성됨으로 여기서 하지 않고 해당 액티비티에서 한다. BaseActivity를 상속받기때문에
        //각 Activity에서 호출해도 singleton으로 reutnr된다

        //AndroidInjection.inject(this); //Inject

        super.onCreate(savedInstanceState);
    }


    /**
     * Adds a Fragment to this activity's layout
     * @param containerViewId container view to where add the fragment
     * @param fragment fragment to be added
     */
    protected void addFragment(int containerViewId, Fragment fragment){
        FragmentTransaction fragmentTransaction = this.getSupportFragmentManager().beginTransaction();
        fragmentTransaction.add(containerViewId,fragment).commit();
    }


    /**
     * context return : sharedpreference 사용을 위해서
     * @return
     */
    protected Context context() {
        return this.getApplicationContext();
    }

    /**
     * Shows message.
     *
     * @param message An string representing a message to be shown.
     */
    protected void showSnackbarMessage(LinearLayout linearLayout,String message) {
        Snackbar.make(linearLayout, message, Snackbar.LENGTH_LONG).show();
    }



    /**
     * Shows message.
     *
     * @param message An string representing a message to be shown.
     */
    protected void showToastMessage(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }
}
