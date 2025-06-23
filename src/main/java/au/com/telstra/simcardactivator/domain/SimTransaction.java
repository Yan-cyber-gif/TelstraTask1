package au.com.telstra.simcardactivator.domain;

import javax.persistence.*;

@Entity
@Table(name = "sim_transactions")
public class SimTransaction {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;                      // managed by DB

    private String iccid;
    private String customerEmail;
    private Boolean active;

    /* ===== Constructors ===== */
    public SimTransaction() {}            // JPA needs this

    public SimTransaction(String iccid, String customerEmail, Boolean active) {
        this.iccid = iccid;
        this.customerEmail = customerEmail;
        this.active = active;
    }

    /* ===== Getters / Setters ===== */
    public Long getId() { return id; }

    public String getIccid() { return iccid; }
    public void setIccid(String iccid) { this.iccid = iccid; }

    public String getCustomerEmail() { return customerEmail; }
    public void setCustomerEmail(String customerEmail) { this.customerEmail = customerEmail; }

    public Boolean getActive() { return active; }
    public void setActive(Boolean active) { this.active = active; }
}
