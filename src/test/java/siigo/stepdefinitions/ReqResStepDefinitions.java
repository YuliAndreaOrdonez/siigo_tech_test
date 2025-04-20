package siigo.stepdefinitions;

import io.cucumber.java.Before;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import net.serenitybdd.annotations.Steps;
import net.serenitybdd.model.environment.ConfiguredEnvironment;
import net.thucydides.model.util.EnvironmentVariables;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import siigo.apis.ReqResAPI;


public class ReqResStepDefinitions {

    EnvironmentVariables variables = ConfiguredEnvironment.getEnvironmentVariables();
    @Steps
    ReqResAPI reqResAPI;

    @Before
    public void setUp(){
        reqResAPI.setBaseURL(variables.getProperty("restapi.baseurl"));
    }

    @Given("User get all users")
    public void actorOpensSiigoApplication() {
        reqResAPI.getAllUsers();
    }

    @Given("User get details of the first user in the list")
    public void actorOpensSiigoDetails() {
        reqResAPI.getUserDetails();
    }

    @When("User change the user name for {string}")
    public void actorOpensSiigoChangeUserName(String userName) {
        reqResAPI.changeUserName(userName);
    }

    @Then("User deletes the modified user")
    public void actorOpensSiigoDeleteUser() {
        reqResAPI.deleteUser();
    }


}
