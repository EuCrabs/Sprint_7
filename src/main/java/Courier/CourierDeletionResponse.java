package Courier;

public class CourierDeletionResponse {
    private Boolean ok;
    private Integer code;
    private String message;

    CourierDeletionResponse() {}

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

    public void setCode(Integer code) {
        this.code = code;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
