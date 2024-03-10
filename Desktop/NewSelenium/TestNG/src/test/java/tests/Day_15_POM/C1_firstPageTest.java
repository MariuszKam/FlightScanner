package tests.Day_15_POM;

import org.openqa.selenium.Keys;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.AmazonPage;
import utilities.Driver;

public class C1_firstPageTest {

    @Test
    public void amazonTest(){

        Driver.getDriver().get("https://amazon.com");
        AmazonPage amazonPage = new AmazonPage();
        amazonPage.searchBox.sendKeys("nutella"+ Keys.ENTER);

        String expectedText = "nutella";
        String actualText = amazonPage.result.getText();

        Assert.assertTrue(actualText.contains(expectedText));
        Driver.closeDriver();
    }
}
