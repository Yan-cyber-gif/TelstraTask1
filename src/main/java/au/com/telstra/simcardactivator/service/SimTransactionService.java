package au.com.telstra.simcardactivator.service;

import au.com.telstra.simcardactivator.model.ActivationRequest;
import au.com.telstra.simcardactivator.model.ActuatorResponse;
import au.com.telstra.simcardactivator.domain.SimTransaction;
import au.com.telstra.simcardactivator.repository.SimTransactionRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class SimTransactionService {

    private static final String ACTUATOR_URL = "http://localhost:8444/actuate";

    private final SimTransactionRepository repo;
    private final RestTemplate rest = new RestTemplate();

    public SimTransactionService(SimTransactionRepository repo) {
        this.repo = repo;
    }

    public SimTransaction create(String iccid, String customerEmail) {

        /* ---- 1. Call the external actuator with correct JSON ---- */
        ActivationRequest payload = new ActivationRequest(iccid);
        ResponseEntity<ActuatorResponse> response =
                rest.postForEntity(ACTUATOR_URL, payload, ActuatorResponse.class);

        boolean activated = response.getStatusCode().is2xxSuccessful()
                           && Boolean.TRUE.equals(response.getBody().getSuccess());

        /* ---- 2. Persist the outcome ---- */
        SimTransaction saved =
                repo.save(new SimTransaction(iccid, customerEmail, activated));

        return saved;
    }

    public SimTransaction findById(long id) {
        return repo.findById(id)
                   .orElseThrow(() -> new IllegalArgumentException("No record with id " + id));
    }
}
