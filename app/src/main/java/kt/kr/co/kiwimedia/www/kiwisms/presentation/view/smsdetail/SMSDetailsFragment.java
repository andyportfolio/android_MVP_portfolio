package kt.kr.co.kiwimedia.www.kiwisms.presentation.view.smsdetail;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import javax.inject.Inject;

import dagger.android.support.AndroidSupportInjection;
import kt.kr.co.kiwimedia.www.kiwisms.R;
import kt.kr.co.kiwimedia.www.kiwisms.databinding.FragmentSmsDetailsBinding;
import kt.kr.co.kiwimedia.www.kiwisms.presentation.model.SMSModel;
import kt.kr.co.kiwimedia.www.kiwisms.presentation.view.BaseFragment;
import kt.kr.co.kiwimedia.www.kiwisms.util.CustomLog;

/**
 * Created by andy on 2017. 9. 12..
 */

public class SMSDetailsFragment extends BaseFragment implements SMSDetailsFragmentContract.View{
    public static final String CONST_SMSMODEL = "smsmodel";

    @Inject
    SMSDetailsFragmentContract.Presenter mSMSDetailsPresenter;

    private FragmentSmsDetailsBinding binding;


//    private TextView sms_num;
//    private TextView receiver_number;
//    private TextView send_datetime;
//    private TextView send_message;
//    private TextView sender_number;

    private SMSModel mSMSModel;

    public static SMSDetailsFragment newInstance(SMSModel smsModel){
        Bundle args = new Bundle();
        args.putParcelable(CONST_SMSMODEL,smsModel);
        SMSDetailsFragment fragment = new SMSDetailsFragment();
        fragment.setArguments(args);

        return fragment;
    }

    @Override
    public void onAttach(Context context) {
        AndroidSupportInjection.inject(this); //injection
        super.onAttach(context);
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //Activity의 상태변화(수직,수평 등) 시
        // true -> onAttach , onCreateView 수행됨
        // NA -> onAttach -> onCreat -> onCreateView 수행
        setRetainInstance(true);
        //액티비티와 함께 소멸되지 않고, 보존되었다가 새로운 액티비티 인스턴스에 전달된다

        Bundle bundle = getArguments();
        mSMSModel = bundle.getParcelable(CONST_SMSMODEL);

    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View fragmentview = inflater.inflate(R.layout.fragment_sms_details,container,false);

        binding = DataBindingUtil.inflate(inflater,R.layout.fragment_sms_details,container,false);
        View view = binding.getRoot();

//        sms_num          = fragmentview.findViewById(R.id.sms_num);
//        receiver_number = fragmentview.findViewById(R.id.receiver_number);
//        send_datetime   = fragmentview.findViewById(R.id.send_datetime);
//        send_message    = fragmentview.findViewById(R.id.send_message);
//        sender_number   = fragmentview.findViewById(R.id.sender_number);

        CustomLog.d("injectioninjection","SMSDetailsFragment onCreateView");

        loadSMSDetails();

        //return fragmentview;
        return view;
    }

    private void loadSMSDetails() {
        mSMSDetailsPresenter.loadSMSDetails();
    }

    @Override
    public void showData() {
        binding.smsNum.setText(mSMSModel.getNum());
        binding.receiverNumber.setText(mSMSModel.getReceiver_number());
        binding.sendDatetime.setText(mSMSModel.getSend_datetime());
        binding.sendMessage.setText(mSMSModel.getSend_message());
        binding.senderNumber.setText(mSMSModel.getSender_number());
    }
}
