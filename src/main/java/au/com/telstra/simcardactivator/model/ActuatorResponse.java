package au.com.telstra.simcardactivator.model;

public class ActuatorResponse {

    private Boolean success;

    public ActuatorResponse() { }

    public Boolean getSuccess() {
        return success;
    }

    /* add this ↓ */
    public Boolean isSuccess() {
        return success;
    }

    public void setSuccess(Boolean success) {
        this.success = success;
    }
}
