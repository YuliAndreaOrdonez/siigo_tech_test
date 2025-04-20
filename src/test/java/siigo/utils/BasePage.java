package siigo.utils;

import net.thucydides.core.pages.PageObject;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class BasePage extends PageObject {
    public WebElement getShadowElement(By selector, String query) {

        WebElement shadowRootParent = getDriver().findElement(selector);
        scrollToElement(shadowRootParent);
        return getShadowElementFromElement(shadowRootParent, query);
    }

    public WebElement getShadowElementFromElement(WebElement shadowRootParent, String query) {
        JavascriptExecutor js = (JavascriptExecutor) getDriver();

        return (WebElement) js.executeScript(String.format("return arguments[0].shadowRoot.querySelector('%s')",query), shadowRootParent);
    }

    public void waitAndClickElement(By selector, String query) {
        WebElement shadowRootParent = getDriver().findElement(selector);

        JavascriptExecutor js = (JavascriptExecutor) getDriver();
        WebDriverWait wait = new WebDriverWait(getDriver(), Duration.ofSeconds(10));
        wait.until(driver -> ((JavascriptExecutor) driver).executeScript("return arguments[0].shadowRoot", shadowRootParent) != null);
        wait.until(driver -> ((JavascriptExecutor) driver).executeScript(String.format("return arguments[0].shadowRoot.querySelector('%s')",query), shadowRootParent) != null);
        WebElement shadowElement = (WebElement) js.executeScript(String.format("return arguments[0].shadowRoot.querySelector('%s')",query), shadowRootParent);

        wait.until(ExpectedConditions.visibilityOf(shadowElement));

        shadowElement.click();
    }

    public WebElement getElementFromElement(WebElement shadowRootParent, String query) {
        JavascriptExecutor js = (JavascriptExecutor) getDriver();

        return (WebElement) js.executeScript(String.format("return arguments[0].querySelector('%s')",query), shadowRootParent);
    }


    public void sendKeysToElement(By selector,String querySelector, String value) {
        getShadowElement(selector, querySelector).sendKeys(value);
    }

    public void selectValueInDropdown(By selector,String querySelector, String value) {
        getShadowElement(selector, querySelector).click();
        WebElement  element = getShadowElement(selector, value);
        scrollToOption(getShadowElement(selector, "div[class*=\"mdc-select__menu\"]"), element);
        element.click();
    }

    public void scrollToOption(WebElement container, WebElement targetElement) {
        JavascriptExecutor js = (JavascriptExecutor) getDriver();
        js.executeScript("arguments[0].scrollTo({ top: arguments[1].offsetTop - arguments[0].offsetTop, behavior: 'smooth' });", container, targetElement);
    }

    public void scrollToElement(WebElement element) {
        JavascriptExecutor js = (JavascriptExecutor) getDriver();
        js.executeScript("arguments[0].scrollIntoView({ behavior: 'smooth', block: 'center' });", element);
    }
}
