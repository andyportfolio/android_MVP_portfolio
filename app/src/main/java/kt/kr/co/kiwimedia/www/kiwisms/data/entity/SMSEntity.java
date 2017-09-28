package kt.kr.co.kiwimedia.www.kiwisms.data.entity;

import com.google.gson.annotations.SerializedName;

/**
 * Created by h02 on 2017. 8. 29..
 */

public class SMSEntity {

        @SerializedName("num")
        public String num;
        @SerializedName("receiver_number")
        public String receiver_number;
        @SerializedName("send_datetime")
        public String send_datetime;
        @SerializedName("send_messge")
        public String send_messge;
        @SerializedName("sender_number")
        public String sender_number;
        @SerializedName("from")  //sms unique key
        public String from;
        @SerializedName("result")  //sucess : 0 , fail: -1
        public String result;

        @Override
        public String toString() {
                return "SMSEntity{" +
                        "num='" + num + '\'' +
                        ", receiver_number='" + receiver_number + '\'' +
                        ", send_datetime='" + send_datetime + '\'' +
                        ", send_messge='" + send_messge + '\'' +
                        ", sender_number='" + sender_number + '\'' +
                        ", result='" + result + '\'' +
                        '}';
        }
}
