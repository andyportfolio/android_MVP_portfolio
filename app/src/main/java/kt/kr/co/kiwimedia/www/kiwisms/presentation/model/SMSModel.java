package kt.kr.co.kiwimedia.www.kiwisms.presentation.model;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by h02 on 2017. 8. 31..
 */

public class SMSModel implements Parcelable {
    String num;
    String receiver_number;
    String send_datetime;
    String send_message;
    String sender_number;

    //결과
    String from;


    public SMSModel(String num, String receiver_number, String send_datetime, String send_message,
                    String sender_number,String from) {
        this.num = num;
        this.receiver_number = receiver_number;
        this.send_datetime = send_datetime;
        this.send_message = send_message;
        this.sender_number = sender_number;
        this.from = from;
    }


    //creator 가 사용하는 생성자 임
    protected SMSModel(Parcel in) {
        num = in.readString();
        receiver_number = in.readString();
        send_datetime = in.readString();
        send_message = in.readString();
        sender_number = in.readString();
        from = in.readString();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(num);
        dest.writeString(receiver_number);
        dest.writeString(send_datetime);
        dest.writeString(send_message);
        dest.writeString(sender_number);
        dest.writeString(from);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    //creator 생성
    public static final Creator<SMSModel> CREATOR = new Creator<SMSModel>() {
        @Override
        public SMSModel createFromParcel(Parcel in) {
            return new SMSModel(in);
        }

        @Override
        public SMSModel[] newArray(int size) {
            return new SMSModel[size];
        }
    };

    public String getNum() {
        return num;
    }

    public void setNum(String num) {
        this.num = num;
    }

    public String getReceiver_number() {
        return receiver_number;
    }

    public void setReceiver_number(String receiver_number) {
        this.receiver_number = receiver_number;
    }

    public String getSend_datetime() {
        return send_datetime;
    }

    public void setSend_datetime(String send_datetime) {
        this.send_datetime = send_datetime;
    }

    public String getSend_message() {
        return send_message;
    }

    public void setSend_messge(String send_messge) {
        this.send_message = send_messge;
    }

    public String getSender_number() {
        return sender_number;
    }

    public void setSender_number(String sender_number) {
        this.sender_number = sender_number;
    }

    public void setSend_message(String send_message) {
        this.send_message = send_message;
    }

    public String getFrom() {
        return from;
    }

    public void setFrom(String from) {
        this.from = from;
    }


}
