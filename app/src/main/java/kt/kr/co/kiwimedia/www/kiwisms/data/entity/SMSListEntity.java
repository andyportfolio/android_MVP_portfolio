package kt.kr.co.kiwimedia.www.kiwisms.data.entity;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by h02 on 2017. 8. 29..
 */

public class SMSListEntity {

    @SerializedName("total")
    public String total;

    @SerializedName("item")
    public List<SMSEntity> items;


}
