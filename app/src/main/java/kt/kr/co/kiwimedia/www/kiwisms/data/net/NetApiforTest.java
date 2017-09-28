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

public interface NetApiforTest {

    //나중에 고쳐라 @Field("user_name") 으로....

    @FormUrlEncoded
    @POST("test/login.php")
    Observable<UserEntity> doLogin(@Field("user_id") String user_name,
                                   @Field("user_password") String user_password);

    @FormUrlEncoded
    @POST("test/getSMSList.php")
    Observable<SMSListEntity> getSMSList(@Field("id") String id);


    @FormUrlEncoded
    @POST("test/updateToken.php")
    Observable<UserEntity> updateToken(@Field("id") String id,
                                   @Field("token") String token);



    @FormUrlEncoded
    @POST("test/updateSMSSendResult.php")
    Observable<SMSEntity> updateSMSSendResult(@Field("id") String id,
                                              @Field("send_datetime") String send_datetime,
                                              @Field("from") String from);


}
