package Courier;

public class CourierLoginRequest {
    private String login;
    private String password;

    public CourierLoginRequest() {}

    public CourierLoginRequest(String login, String password) {
        this.login = login;
        this.password = password;
    }

    public CourierLoginRequest(String login) {
        this.login = login;
    }

    public String getLogin() {
        return login;
    }

    public String getPassword() {
        return password;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
