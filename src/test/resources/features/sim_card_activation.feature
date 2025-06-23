Feature: SIM card activation

  Scenario: Successful SIM card activation
    Given the SIM card actuator is running
    When I activate a SIM card with iccid "1255789453849037777" and email "success@example.com"
    Then the activation should be recorded with id 1 and active true

  Scenario: Failed SIM card activation
    Given the SIM card actuator is running
    When I activate a SIM card with iccid "8944500102198304826" and email "fail@example.com"
    Then the activation should be recorded with id 2 and active false
