package Courier;

public class CourierCreationResponse {
    private Boolean ok;
    private Integer code;
    private String message;

    public CourierCreationResponse() {

    }

    public Boolean getOk() {
        return ok;
    }

    public Integer getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }

    public void setOk(Boolean ok) {
        this.ok = ok;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
