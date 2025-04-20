package siigo.stepdefinitions;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import siigo.pages.SiigoCreateCustomerPage;
import siigo.pages.SiigoCustomerViewPage;
import siigo.pages.SiigoHomePage;
import siigo.pages.SiigoLoginPage;

import java.util.List;
import static org.junit.Assert.*;


public class SiigoStepDefinitions {

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
        siigoLoginPage.loginIntoApplication("retoautomationsiigo@yopmail.com", "T4b4ck0ff1c3P455w0rd658*");
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

    @When("User select {string} as identification type and enter the value {int}")
    public void userSelectASIdentificationType(String identificationType, int identification) {
        siigoCreateCustomerPage.selectIdentificationType(identificationType);
        siigoCreateCustomerPage.enterIdentification(identification);
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

}
