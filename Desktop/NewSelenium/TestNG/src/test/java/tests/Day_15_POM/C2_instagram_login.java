package tests.Day_15_POM;

import org.openqa.selenium.Keys;
import org.testng.annotations.Test;
import pages.InstagramWeb;
import utilities.Driver;
import utilities.ReuseableMethod;

public class C2_instagram_login {

    @Test
    public void instagramlogin() {
        Driver.getDriver().get("https://instagram.com");
        InstagramWeb instagramWeb = new InstagramWeb();
        instagramWeb.cookiesAllow.click();

        instagramWeb.username
                .sendKeys("enter your username" + Keys.TAB);
        instagramWeb.password
                .sendKeys("enter your password" + Keys.TAB);
        instagramWeb.loginButton
                .click();
        ReuseableMethod.wait(20);

        Driver.closeDriver();
    }
}
