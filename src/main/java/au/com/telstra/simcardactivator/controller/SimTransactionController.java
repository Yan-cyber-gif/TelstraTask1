package au.com.telstra.simcardactivator.controller;

import au.com.telstra.simcardactivator.domain.SimTransaction;
import au.com.telstra.simcardactivator.service.SimTransactionService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/transactions")
public class SimTransactionController {

    private final SimTransactionService service;

    public SimTransactionController(SimTransactionService service) {
        this.service = service;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public SimTransaction create(@RequestBody CreateRequest request) {
        return service.create(request.getIccid(), request.getCustomerEmail());
    }


    /* GET /transactions?simCardId=123 */
    @GetMapping
    public QueryResponse query(@RequestParam long simCardId) {
        SimTransaction tx = service.findById(simCardId);
        return new QueryResponse(tx.getIccid(), tx.getCustomerEmail(), tx.getActive());
    }

    static class CreateRequest {
        private String iccid;
        private String customerEmail;
        public String getIccid() { return iccid; }
        public String getCustomerEmail() { return customerEmail; }
    }
    
    static class QueryResponse {
        private String iccid;
        private String customerEmail;
        private Boolean active;
        public QueryResponse(String iccid, String customerEmail, Boolean active) {
            this.iccid = iccid;
            this.customerEmail = customerEmail;
            this.active = active;
        }
        public String getIccid() { return iccid; }
        public String getCustomerEmail() { return customerEmail; }
        public Boolean getActive() { return active; }
    }
    
}
