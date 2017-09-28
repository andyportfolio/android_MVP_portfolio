package kt.kr.co.kiwimedia.www.kiwisms.domain;

/**
 * Created by h02 on 2017. 8. 31..
 */

public class SMS {
    String num;
    String receiver_number;
    String send_datetime;
    String send_message;
    String sender_number;

    //결과
    String from;
    String result;

    public SMS(String num, String receiver_number, String send_datetime, String send_message,
               String sender_number,String from,String result) {
        this.num = num;
        this.receiver_number = receiver_number;
        this.send_datetime = send_datetime;
        this.send_message = send_message;
        this.sender_number = sender_number;
        this.from = from;
        this.result = result;

    }

    public SMS(String num, String from, String result) {
        this.num = num;
        this.receiver_number = receiver_number;
        this.send_datetime = send_datetime;
        this.send_message = send_message;
        this.sender_number = sender_number;
        this.from = from;
    }

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

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }
}
