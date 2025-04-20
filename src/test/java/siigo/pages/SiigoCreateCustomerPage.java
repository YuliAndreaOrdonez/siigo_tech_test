package siigo.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import siigo.utils.BasePage;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SiigoCreateCustomerPage extends BasePage {

    public static final By CONTAINER = By.xpath("//app-third-party-detail");
    public static final By TYPE_DROPDOWN = By.xpath("(//siigo-dropdownlist-web)[2]");
    public static final By IDENTIFICATION_TYPE_DROPDOWN = By.xpath("(//siigo-dropdownlist-web)[3]");
    public static final String DIV_ELEMENT = "div";
    public static final By IDENTIFICATION_INPUT = By.xpath("//siigo-identification-input-web");
    public static final String INPUT_ELEMENT = "input";
    public static final By BRANCH_CODE_INPUT = By.xpath("(//siigo-textfield-web)[1]");
    public static final By NAME_INPUT = By.xpath("(//siigo-textfield-web)[2]");
    public static final By CONTACT_NAME_INPUT = By.xpath("//siigo-textfield-web[@name='contactname']");
    public static final By LAST_NAME_INPUT = By.xpath("(//siigo-textfield-web)[3]");
    public static final By CONTACT_LAST_NAME_INPUT = By.xpath("//siigo-textfield-web[@name='contactlastname']");
    public static final By COMPANY_NAME_INPUT = By.xpath("(//siigo-textfield-web)[5]");
    public static final By CITY_INPUT = By.cssSelector("[key-id='city']");
    public static final String CITIES_FIRST_OPTION = "table[id=\"tableAutocompletecity\"] tbody tr:first-of-type";
    public static final By ADDRESS_INPUT = By.xpath("(//siigo-textfield-web)[6]");
    public static final By PHONE_FORM = By.xpath("//siigo-phone-web");
    public static final String INDICATIVE_QUERY_INPUT = "input[aria-labelledby*=\"inputIndicative\"]:last-of-type";
    public static final String PHONE_QUERY_INPUT = "input[aria-labelledby*=\"inputNumber\"]:last-of-type";
    public static final String EXTENSION_QUERY_INPUT = "input[aria-labelledby*=\"inputExtension\"]:last-of-type";
    public static final By NAME_OF_CONTACT_INPUT = By.xpath("//siigo-textfield-web[@name='FirstName']");
    public static final By SAVE_BUTTON = By.xpath("//div[@id='sticky']//button[contains(@class, 'green')]");
    public static final By CONTACTS_SECTION_HEADER = By.xpath("//app-third-party-contact//h3[@class='clickable']");

    public static Map<String, By> customerTypes;
    public static Map<String, String> types;
    public static Map<String, String> identificationTypes;

    static {
        customerTypes = new HashMap<>();
        customerTypes.put("Clientes", By.name("isClient"));
        customerTypes.put("Proveedores", By.name("isSupplier"));
        customerTypes.put("Otros", By.name("isOthers"));
    }

    static {
        types = new HashMap<>();
        types.put("Persona", "[data-value*=\"Es persona\"]");
        types.put("Empresa", "[data-value*=\"Empresa\"]");
    }

    static {
        identificationTypes = new HashMap<>();
        identificationTypes.put("Registro Civil", "[data-value*=\"Registro civil\"]");
        identificationTypes.put("Tarjeta de Identidad", "[data-value*=\"Tarjeta de identidad\"]");
        identificationTypes.put("Cedula de Ciudadania", "[data-value*=\"Cédula de ciudadanía\"]");
        identificationTypes.put("Tarjeta de Extranjeria", "[data-value*=\"Tarjeta de extranjería\"]");
        identificationTypes.put("Cedula de Extranjeria", "[data-value*=\"Cédula de extranjería\"]");
        identificationTypes.put("NIT", "[data-value*=\"NIT\"]");
        identificationTypes.put("Pasaporte", "[data-value*=\"Pasaporte\"]");
    }

    public void waitUntilPageLoad() {
        waitForRenderedElements(CONTAINER);
    }

    public void selectCustomerTypes(List<String> types) {
        for (By customerType : customerTypes.values()) {
            WebElement element = find(customerType);
            if (element.isSelected()) {
                element.click();
            }
        }
        for (String type : types) {
            find(customerTypes.get(type)).click();
        }
    }

    public void selectType(String type) {
        selectValueInDropdown(TYPE_DROPDOWN, DIV_ELEMENT, types.get(type));
    }

    public void selectIdentificationType(String identificationType) {
        selectValueInDropdown(IDENTIFICATION_TYPE_DROPDOWN, DIV_ELEMENT, identificationTypes.get(identificationType));
    }

    public void enterIdentification(Long identification) {
        sendKeysToElement(IDENTIFICATION_INPUT, INPUT_ELEMENT, String.valueOf(identification));
    }

    public void enterBranchCode(String branchCode) {
        sendKeysToElement(BRANCH_CODE_INPUT, INPUT_ELEMENT, branchCode);
    }

    public void enterName(String name) {
        sendKeysToElement(NAME_INPUT, INPUT_ELEMENT, name);
    }

    public void enterLastName(String lastName) {
        sendKeysToElement(LAST_NAME_INPUT, INPUT_ELEMENT, lastName);
    }

    public String getContactName() {
        return find(CONTACT_NAME_INPUT).getValue();
    }

    public String getContactLastName() {
        return find(CONTACT_LAST_NAME_INPUT).getValue();
    }

    public void enterCompanyName(String companyName) {
        sendKeysToElement(COMPANY_NAME_INPUT, INPUT_ELEMENT, companyName);
    }

    public void enterCity(String city) {
        find(CITY_INPUT).click();
        sendKeysToElement(CITY_INPUT, INPUT_ELEMENT, city);
        waitAndClickElement(CITY_INPUT, CITIES_FIRST_OPTION);
    }

    public void enterAddress(String address) {
        sendKeysToElement(ADDRESS_INPUT, INPUT_ELEMENT, address);
    }

    public void enterIndicative(String indicative) {
        sendKeysToElement(PHONE_FORM, INDICATIVE_QUERY_INPUT, indicative);
    }

    public void enterPhoneNumber(String phoneNumber) {
        sendKeysToElement(PHONE_FORM, PHONE_QUERY_INPUT, phoneNumber);
    }

    public void enterExtension(String extension) {
        sendKeysToElement(PHONE_FORM, EXTENSION_QUERY_INPUT, extension);
    }

    public void enterContacName(String contactName) {
        sendKeysToElement(NAME_OF_CONTACT_INPUT, INPUT_ELEMENT, contactName);
    }

    public void clickOnSaveButton() {
        find(SAVE_BUTTON).click();
    }

    public void openContactsSection() {
        WebElement element = find(CONTACTS_SECTION_HEADER);
        scrollToElement(element);
        element.click();
    }
}
