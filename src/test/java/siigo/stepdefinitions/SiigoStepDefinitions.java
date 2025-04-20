package siigo.stepdefinitions;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import net.serenitybdd.model.environment.ConfiguredEnvironment;
import net.serenitybdd.rest.SerenityRest;
import net.thucydides.model.util.EnvironmentVariables;
import siigo.pages.SiigoCreateCustomerPage;
import siigo.pages.SiigoCustomerViewPage;
import siigo.pages.SiigoHomePage;
import siigo.pages.SiigoLoginPage;

import java.util.Date;
import java.util.List;
import static org.junit.Assert.*;


public class SiigoStepDefinitions {

    EnvironmentVariables variables = ConfiguredEnvironment.getEnvironmentVariables();

    SiigoLoginPage siigoLoginPage;
    SiigoHomePage siigoHomePage;
    SiigoCreateCustomerPage siigoCreateCustomerPage;
    SiigoCustomerViewPage siigoCustomerViewPage;

    @Given("User opens Siigo application")
    public void actorOpensSiigoApplication() {
        siigoLoginPage.open();
    }

    @Given("User logins into the application")
    public void actorLoginsIntoSiigoApplication() {
        siigoLoginPage.loginIntoApplication(variables.getProperty("siigo.username"), variables.getProperty("siigo.password"));
    }

    @When("User clicks the create customer option")
    public void userClicksTheCreateCustomerOption() {
        siigoHomePage.selectCreateCustomerOption();
    }

    @When("User select the following customer types:")
    public void userSelectTheFollowingCustomerTypes(List<String> types) {
        siigoCreateCustomerPage.waitUntilPageLoad();
        siigoCreateCustomerPage.selectCustomerTypes(types);
    }

    @When("User select {string} as type")
    public void userSelectASType(String type) {
        siigoCreateCustomerPage.selectType(type);
    }

    @When("User select {string} as identification type and enter the value")
    public void userSelectASIdentificationType(String identificationType) {
        siigoCreateCustomerPage.selectIdentificationType(identificationType);
        siigoCreateCustomerPage.enterIdentification(new Date().getTime());
    }

    @When("User enter the value {int} as branch code")
    public void userEnterTheValueAsBranchCode(int branchCode) {
        siigoCreateCustomerPage.enterBranchCode(String.valueOf(branchCode));
    }

    @When("User enter the value {string} as name")
    public void userEnterTheValueAsName(String name) {
        siigoCreateCustomerPage.enterName(name);
        String contactName = siigoCreateCustomerPage.getContactName();
        assertEquals(name, contactName);
    }

    @When("User enter the value {string} as last name")
    public void userEnterTheValueAsLastName(String lastName) {
        siigoCreateCustomerPage.enterLastName(lastName);
        String contactLastName = siigoCreateCustomerPage.getContactLastName();
        assertEquals(lastName,contactLastName);
    }

    @When("User enter the value {string} as company name")
    public void userEnterTheValueAsCompanyName(String companyName) {
        siigoCreateCustomerPage.enterCompanyName(companyName);

    }

    @When("User enter the value {string} as city")
    public void userEnterTheValueAsCity(String city) {
        siigoCreateCustomerPage.enterCity(city);
    }

    @When("User enter the value {string} as addres")
    public void userEnterTheValueAsAddress(String address) {
        siigoCreateCustomerPage.enterAddress(address);
    }

    @When("User enter the value {int} {int} {int} as phone number")
    public void userEnterTheValuesAsPhoneNumber(int indicative, int phoneNumber, int extension) {
        siigoCreateCustomerPage.enterIndicative(String.valueOf(indicative));
        siigoCreateCustomerPage.enterPhoneNumber(String.valueOf(phoneNumber));
        siigoCreateCustomerPage.enterExtension(String.valueOf(extension));
    }

    @When("User enter the value {string} as contact name")
    public void userEnterTheValueAsContactName(String contactName) {
        siigoCreateCustomerPage.openContactsSection();
        siigoCreateCustomerPage.enterContacName(contactName);
    }

    @When("User clicks on Save Button")
    public void userClicksOnSaveButton() {
        siigoCreateCustomerPage.clickOnSaveButton();
    }

    @Then("The Customer Identification is present in the Customer View")
    public void theCustomerIdentificationIsPresentInTheCustomerView() {
        siigoCustomerViewPage.validateIdentificationIsPresent();
    }

    @Given("User requests data from {string}")
    public void userRequestsDataFrom(String path) {
        SerenityRest.given().get(path).then().statusCode(200);
    }
}
