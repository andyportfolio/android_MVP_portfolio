package kt.kr.co.kiwimedia.www.kiwisms.presentation.model;

/**
 * Created by andy on 2017. 8. 11..
 */

public class UserModel {

   String id;
   String password;
   String username;
   String company;
   String token;


    public UserModel(String id, String password) {
        this.id = id;
        this.password = password;
    }


    public UserModel(String id, String username, String password,String token,String company) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.token = token;
        this.company = company;
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

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }
}
