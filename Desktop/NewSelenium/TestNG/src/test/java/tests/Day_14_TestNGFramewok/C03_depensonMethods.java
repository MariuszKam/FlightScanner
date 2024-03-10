package tests.Day_14_TestNGFramewok;

import org.testng.Assert;
import org.testng.annotations.Test;

public class C03_depensonMethods {

    @Test
    public void youtubeTest(){
        System.out.println("youtube");
        Assert.assertTrue( 8 == 3);
    }
    @Test(priority = -3)
    public void wiseTest(){
        System.out.println("wise");
    }
    @Test(dependsOnMethods ="youtubeTest")
    public void amazonTest(){
        System.out.println("amazon");
    }


}
