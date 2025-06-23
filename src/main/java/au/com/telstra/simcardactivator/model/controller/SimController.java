package au.com.telstra.simcardactivator.controller;

import au.com.telstra.simcardactivator.model.ActivationRequest;
import au.com.telstra.simcardactivator.model.ActuatorResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

@RestController
@RequestMapping("/sims")
public class SimController {

    private final RestTemplate restTemplate = new RestTemplate();
    private static final String ACTUATOR_URL = "http://localhost:8444/actuate";

    @PostMapping("/activate")
    public ResponseEntity<String> activateSim(@RequestBody ActivationRequest req) {

        // forward only the ICCID to the actuator
        ActuatorResponse actuatorReply = restTemplate.postForObject(
                ACTUATOR_URL,
                java.util.Map.of("iccid", req.getIccid()),
                ActuatorResponse.class);

        boolean ok = actuatorReply != null && actuatorReply.isSuccess();
        String msg = ok ? "Activation successful" : "Activation failed";
        System.out.printf("ICCID %s ➜ %s%n", req.getIccid(), msg);

        return ResponseEntity.ok(msg);
    }
}
