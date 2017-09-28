package kt.kr.co.kiwimedia.www.kiwisms.presentation.view;

import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.widget.LinearLayout;
import android.widget.Toast;

/**
 * Created by h02 on 2017. 8. 30..
 */

public class BaseFragment extends Fragment {

    protected void showSnackbarMessage(LinearLayout linearLayout, String message) {
        Snackbar.make(linearLayout, message, Snackbar.LENGTH_LONG).show();
    }



    protected void showToastMessage(String message) {
        Toast.makeText(getActivity(), message, Toast.LENGTH_SHORT).show();
    }
}
