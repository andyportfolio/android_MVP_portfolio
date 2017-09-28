package kt.kr.co.kiwimedia.www.kiwisms.domain;

/**
 * Created by h02 on 2017. 8. 29..
 */

public class User {
    String id;
    String password;
    String username;
    String token;
    String company;
    String result;

    public User(String id, String username,String token,String company,String result)
    {
        this(id,username,"No Return",token,company,result);
    }

    public User(String id, String username,String password,String token,String company,String result) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.token = token;
        this.company = company;
        this.result = result;
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
