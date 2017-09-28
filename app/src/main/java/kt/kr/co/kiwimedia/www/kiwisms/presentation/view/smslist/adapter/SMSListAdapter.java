package kt.kr.co.kiwimedia.www.kiwisms.presentation.view.smslist.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.Collection;
import java.util.List;

import javax.inject.Inject;

import kt.kr.co.kiwimedia.www.kiwisms.R;
import kt.kr.co.kiwimedia.www.kiwisms.presentation.model.SMSModel;

/**
 * Created by h02 on 2017. 9. 1..
 */

public class SMSListAdapter extends RecyclerView.Adapter<SMSListAdapter.SMSListViewHolder>{
    public interface OnSMSItemClickListener{
        void onSMSItemClicked(SMSModel smsModel);
    }

    private List<SMSModel> mSMSCollection;
    private LayoutInflater mLayoutInflater;

    private OnSMSItemClickListener mOnSMSItemClickListener;


    @Inject
    public SMSListAdapter(Context context) {
        this.mLayoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        //this.mSMSCollection = Collections.emptyList();

//        this.mSMSCollection = new ArrayList<SMSModel>();

//        for (int i= 0 ; i < 10 ; i++){
//            SMSModel s = new SMSModel(String.valueOf(i),"number","date,","message","sender");
//            mSMSCollection.add(s);
//        }
    }

    @Override
    public SMSListViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        //다른 방법 -- adapter가 fragment안에서 구현될 경우
        //mLayoutInflater = (LayoutInflater) LayoutInflater.from(getActivity()); 로 view를 생성할수 있다

        View view = this.mLayoutInflater.inflate(R.layout.row_list,parent,false);
        return new SMSListViewHolder(view);
    }

    @Override
    public void onBindViewHolder(SMSListViewHolder holder, int position) {
        SMSModel smsModel = this.mSMSCollection.get(position);

        holder.bindView(smsModel);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (SMSListAdapter.this.mOnSMSItemClickListener != null){
                    SMSListAdapter.this.mOnSMSItemClickListener.onSMSItemClicked(smsModel);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return (this.mSMSCollection != null) ? this.mSMSCollection.size() : 0;
    }

    public void setSMSCollection(Collection<SMSModel> SMSCollection) {

        if (SMSCollection == null){
            throw new IllegalArgumentException("SMS List cannot be null");
        }
        this.mSMSCollection = (List<SMSModel>) SMSCollection;
        this.notifyDataSetChanged();

    }

    public void setOnSMSItemClickListner(OnSMSItemClickListener onSMSItemClickListner){
        this.mOnSMSItemClickListener = onSMSItemClickListner;
    }


    static class SMSListViewHolder extends RecyclerView.ViewHolder{
        private SMSModel mSMSModel;

        TextView sms_num;
        TextView receiver_number;
        TextView send_datetime;
        TextView send_message;
        TextView sender_number;


        public SMSListViewHolder(View itemView) {
            super(itemView);

            sms_num = itemView.findViewById(R.id.sms_num);
            receiver_number = itemView.findViewById(R.id.receiver_number);
            send_datetime = itemView.findViewById(R.id.send_datetime);
            send_message = itemView.findViewById(R.id.send_message);
            //sender_number = itemView.findViewById(R.id.sender_number);


        }


        public void bindView(SMSModel smsModel){
            mSMSModel = smsModel;

            sms_num.setText(mSMSModel.getNum());
            receiver_number.setText(mSMSModel.getReceiver_number());
            send_datetime.setText(mSMSModel.getSend_datetime());
            send_message.setText(mSMSModel.getSend_message());
            //sender_number.setText(mSMSModel.getSender_number());
        }

//        @Override
//        public void onClick(View view) {
//            //Toast.makeText(view.getContext(), "Click: " + sms_id, Toast.LENGTH_SHORT).show();
//            mSMSModel
//            Intent intent = SMSDetailsActivity.newIntent(getActivity(),smsMdel);
//            startActivity(intent);
//        }

//        @Override
//        public void onSMSItemClicked(SMSModel smsModel) {
//           if (SMSListAdapter.mOnItemClickListner != null){
//
//           }
//        }
    }

//    public void setOnItemClickListner(OnItemClickListner onItemClickListner){
//        this.mOnItemClickListner = onItemClickListner;
//    }
}

