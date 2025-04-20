package siigo.pages;

import org.openqa.selenium.By;
import siigo.utils.BasePage;

public class SiigoHomePage extends BasePage {

    public static final By HEADER =  By.xpath("//siigo-header-molecule");
    public static final String SHADOW_HEADER = "[data-id=\"header-create-button\"]";
    public static final String SHADOW_BUTTON = "button";
    public static final String SHADOW_DROPDOWN = "[data-id=\"header-create-button-dropdown\"]";
    public static final String SHADOW_DROPDOWN_CLIENT_OPTION = "a[data-value=\"Clientes\"]";

    public void selectCreateCustomerOption() {

        synchronized(getDriver()) {
            try {
                getDriver().wait(6000); // Waits for 5 seconds
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
        waitForRenderedElementsToBePresent(HEADER);
        getShadowElementFromElement(getShadowElement(HEADER, SHADOW_HEADER), SHADOW_BUTTON).click();
        getElementFromElement(getShadowElement(HEADER, SHADOW_DROPDOWN), SHADOW_DROPDOWN_CLIENT_OPTION).click();

    }
}
