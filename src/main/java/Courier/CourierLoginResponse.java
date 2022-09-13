package Courier;

public class CourierLoginResponse {
    private Integer id;
    private Integer code;
    private String message;

    public CourierLoginResponse() {}

    public Integer getId() {
        return id;
    }

    public Integer getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
