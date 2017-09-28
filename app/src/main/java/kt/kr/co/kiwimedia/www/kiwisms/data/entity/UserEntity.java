package kt.kr.co.kiwimedia.www.kiwisms.data.entity;

import com.google.gson.annotations.SerializedName;

/**
 * Created by andy on 2017. 8. 11..
 * User Entity used in the data layer.
 */

public class UserEntity {

   @SerializedName("id")
   String id;

   @SerializedName("password")
    String password;

   @SerializedName("username")
    String username;

    @SerializedName("company")
    String company;


    @SerializedName("token")
    String token;

    @SerializedName("result")  //sucess : 0 , fail: -1
    String result;

    public UserEntity() {

    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }
}
