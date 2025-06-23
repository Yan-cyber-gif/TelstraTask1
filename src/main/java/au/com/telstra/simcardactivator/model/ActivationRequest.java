package au.com.telstra.simcardactivator.model;

public class ActivationRequest {

    private String iccid;

    public ActivationRequest() { }

    public ActivationRequest(String iccid) {
        this.iccid = iccid;
    }

    public String getIccid() {
        return iccid;
    }

    public void setIccid(String iccid) {
        this.iccid = iccid;
    }
}
