package kt.kr.co.kiwimedia.www.kiwisms.presentation.view.smslist;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.Collection;

import javax.inject.Inject;

import dagger.android.support.AndroidSupportInjection;
import kt.kr.co.kiwimedia.www.kiwisms.R;
import kt.kr.co.kiwimedia.www.kiwisms.databinding.FragmentSmsListBinding;
import kt.kr.co.kiwimedia.www.kiwisms.presentation.model.SMSModel;
import kt.kr.co.kiwimedia.www.kiwisms.presentation.view.BaseFragment;
import kt.kr.co.kiwimedia.www.kiwisms.presentation.view.smslist.adapter.SMSListAdapter;
import kt.kr.co.kiwimedia.www.kiwisms.util.CustomLog;

/**
 * Created by h02 on 2017. 8. 31..
 */

public class SMSListFragment extends BaseFragment implements SMSListFragmentContract.View{

    /**
     * Interface for listening sms list event
     */
    public interface SMSListListner{
        void onSMSClicked(SMSModel smsModel);
    }

    private SMSListListner mSMSListListner;

    @Inject
    SMSListFragmentContract.Presenter mSMSListFragmentPresenter;

    @Inject
    SMSListAdapter mSMSListAdapter;

    private FragmentSmsListBinding binding;

//    private RecyclerView mRecyclerView;
//    private ProgressBar mProgressBar;
//    private Button mRetryButton;
//
//    private LinearLayout mLinearLayout;

    public static SMSListFragment newInstance() {
        Bundle args = new Bundle();
        SMSListFragment fragment = new SMSListFragment();
        fragment.setArguments(args);
        return fragment;
    }

    public SMSListFragment() {
    }


    @Override
    public void onAttach(Context context) {
        AndroidSupportInjection.inject(this); //injection
        super.onAttach(context);

        //Activity로 부터 데이터를 받아오기 위해서 Framgment에 Interface를 정의하고
        //Activity에서 Implement함.
        if (getActivity() !=null && getActivity() instanceof SMSListListner){
            mSMSListListner = (SMSListListner) getActivity();
        }

    }


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //Activity의 상태변화(수직,수평 등) 시
        // true -> onAttach , onCreateView 수행됨
        // NA -> onAttach -> onCreat -> onCreateView 수행
        setRetainInstance(true);
        //액티비티와 함께 소멸되지 않고, 보존되었다가 새로운 액티비티 인스턴스에 전달된다

    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        //View fragmentView = inflater.inflate(R.layout.fragment_sms_list,container,false);

        binding = DataBindingUtil.inflate(inflater,R.layout.fragment_sms_list,container,false);
        View view = binding.getRoot();

        //mRecyclerView = (RecyclerView) fragmentView.findViewById(R.id.rv_lists);

        binding.rvLists.setLayoutManager(new LinearLayoutManager(getActivity()));

        //mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        //mProgressBar = (ProgressBar) fragmentView.findViewById(R.id.pb_smslistprogressbar);
        //mRetryButton = (Button) fragmentView.findViewById(R.id.bt_retry);

        //mLinearLayout = (LinearLayout) fragmentView.findViewById(R.id.sms_linearLaoyout);

        mSMSListAdapter.setOnSMSItemClickListner(onSMSItemClickListener);
        //mRecyclerView.setAdapter(mSMSListAdapter);

        binding.rvLists.setAdapter(mSMSListAdapter);

        CustomLog.d("injectioninjection","SMSListFragment onCreateView");

        loadSMSList();

        //return fragmentView;
        return view;
    }


    @Override
    public void onDetach() {
        super.onDetach();
        mSMSListListner = null;
    }


    @Override
    public void onDestroy() {
        super.onDestroy();
        mSMSListFragmentPresenter.destroy();

    }

    private void loadSMSList(){
        mSMSListFragmentPresenter.initialize();
    }

    @Override
    public void renderSMSList(Collection<SMSModel> smsModelCollection) {
        if (smsModelCollection != null){
            mSMSListAdapter.setSMSCollection(smsModelCollection);
        }

    }

    @Override
    public void viewSMS(SMSModel smsModel) {
        if (mSMSListListner != null){
            mSMSListListner.onSMSClicked(smsModel);
        }

    }

    @Override
    public void showLoading() {
        binding.pbSmslistprogressbar.setVisibility(View.VISIBLE);
        //mProgressBar.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideLoading() {
        binding.pbSmslistprogressbar.setVisibility(View.GONE);
        //mProgressBar.setVisibility(View.GONE);
    }

    @Override
    public void showRetry() {
        binding.btRetry.setVisibility(View.VISIBLE);
        //mRetryButton.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideRetry() {
        binding.btRetry.setVisibility(View.GONE);
        //mRetryButton.setVisibility(View.GONE);
    }

    @Override
    public void showError(String errorMessage) {
        showMessage(errorMessage);
    }

    @Override
    public Context context() {
        return this.getActivity().getApplicationContext();
    }

    private void showMessage(String message) {
        showSnackbarMessage(binding.smsLinearLaoyout,message);
        //Snackbar.make(mLinearLayout, message, Snackbar.LENGTH_LONG).show();
    }

    private SMSListAdapter.OnSMSItemClickListener onSMSItemClickListener =
            new SMSListAdapter.OnSMSItemClickListener() {

                @Override
                public void onSMSItemClicked(SMSModel smsModel) {
                    if (mSMSListFragmentPresenter != null & smsModel !=null) {
                        mSMSListFragmentPresenter.onSMSClicked(smsModel);
                    }
                }
            };

}
