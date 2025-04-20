package siigo.pages;

import org.openqa.selenium.By;
import siigo.utils.BasePage;

public class SiigoCustomerViewPage extends BasePage {
    public static final By CUSTOMER_IDENTIFICATION = By.id("Identification");

    public void validateIdentificationIsPresent() {
        waitForRenderedElementsToBePresent(CUSTOMER_IDENTIFICATION);
        find(CUSTOMER_IDENTIFICATION).isDisplayed();
    }
}
