package stepDefinitions;

import io.cucumber.java.en.*;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;
import java.util.Map;
import static org.junit.jupiter.api.Assertions.*;

public class SimCardActivatorStepDefinitions {

    private final RestTemplate rest = new RestTemplate();
    private ResponseEntity<Map> lastPost;
    private long lastId;

    @Given("the SIM card actuator is running")
    public void actuatorRunning() { }

    @When("I activate a SIM card with iccid {string} and email {string}")
    public void activateSim(String iccid, String email) {
        Map<String, String> body = Map.of("iccid", iccid, "customerEmail", email);
        lastPost = rest.postForEntity("http://localhost:8080/transactions", body, Map.class);
        lastId = ((Number) lastPost.getBody().get("id")).longValue();
    }

    @Then("the activation should be recorded with id {int} and active {word}")
    public void verifyActivation(int expectedId, String expectedActive) {
        Map response = rest.getForObject("http://localhost:8080/transactions?simCardId=" + expectedId, Map.class);
        assertEquals(Boolean.parseBoolean(expectedActive), response.get("active"));
        assertEquals(expectedId, lastId);
    }
}
