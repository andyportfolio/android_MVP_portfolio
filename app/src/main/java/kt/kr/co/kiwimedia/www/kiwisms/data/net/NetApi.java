package kt.kr.co.kiwimedia.www.kiwisms.data.net;

import io.reactivex.Observable;
import kt.kr.co.kiwimedia.www.kiwisms.data.entity.SMSEntity;
import kt.kr.co.kiwimedia.www.kiwisms.data.entity.SMSListEntity;
import kt.kr.co.kiwimedia.www.kiwisms.data.entity.UserEntity;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

/**
 * Created by andy on 2017. 8. 19..
 */

public interface NetApi {

    //Login
    @FormUrlEncoded
    @POST("json/loginCheck.php")
    Observable<UserEntity> doLogin(@Field("id") String user_name,
                                   @Field("password") String user_password);

    @FormUrlEncoded
    @POST("json/ktCallLog.php")
    //Observable<List<SMSEntity>> getSMSList();
    Observable<SMSListEntity> getSMSList(@Field("id") String id);



    @FormUrlEncoded
    @POST("json/memberTokenUdate.php")
    Observable<UserEntity> updateToken(@Field("id") String id,
                                       @Field("token") String token);

    @FormUrlEncoded
    @POST("/json/ktCallUdate.php")
    Observable<SMSEntity> updateSMSSendResult(@Field("num") String num,
                                              @Field("send_datetime") String send_datetime,
                                              @Field("from") String from);




}
