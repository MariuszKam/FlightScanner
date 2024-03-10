package tests.Day_14_TestNGFramewok;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;
import utilities.Driver;
import utilities.ReuseableMethod;

public class C04_firstTest {


    @Test
    public void test01() {
        //go to amazon
        Driver.getDriver().get("https://www.amazon.com");

        //search nutella
        WebElement searchBox = Driver.getDriver().findElement(By.id("twotabsearchtextbox"));
        searchBox.sendKeys("nutella"+ Keys.ENTER);

        WebElement result = Driver.getDriver().findElement(By.xpath("(//div[@class='sg-col-inner'])[1]"));


        String expectedText= "nutella";
        String actualText = result.getText();

        Assert.assertTrue(actualText.contains(expectedText));

        //is disable nutella test
        ReuseableMethod.wait(8);
        Driver.closeDriver();
    }


}
