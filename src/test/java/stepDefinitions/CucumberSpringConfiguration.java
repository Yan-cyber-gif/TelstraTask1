package stepDefinitions;

import io.cucumber.spring.CucumberContextConfiguration;
import org.springframework.boot.test.context.SpringBootTest;
import au.com.telstra.simcardactivator.SimCardActivator;

@CucumberContextConfiguration
@SpringBootTest(
        classes = SimCardActivator.class,                       // load the app
        webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT   // start it on port 8080
)
public class CucumberSpringConfiguration { }
