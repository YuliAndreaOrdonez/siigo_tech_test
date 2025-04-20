package siigo.pages;

import net.serenitybdd.annotations.DefaultUrl;
import org.openqa.selenium.By;
import siigo.utils.BasePage;

@DefaultUrl("https://qastaging.siigo.com/#/login")
public class SiigoLoginPage extends BasePage {

    public static final By USER_NAME_INPUT =  By.id("username");
    public static final String QUERY_SELECTOR_INPUT = "input";
    public static final By PASSWORD_INPUT =  By.id("current-password");
    public static final By SUBMIT_BUTTON =  By.id("login-submit");

    public void loginIntoApplication(String userName, String password) {
        synchronized(getDriver()) {
            try {
                getDriver().wait(6000); // Waits for 5 seconds
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
        waitForRenderedElementsToBePresent(USER_NAME_INPUT);
        sendKeysToElement(USER_NAME_INPUT, QUERY_SELECTOR_INPUT, userName);
        sendKeysToElement(PASSWORD_INPUT, QUERY_SELECTOR_INPUT, password);
        find(SUBMIT_BUTTON).click();
    }
}
