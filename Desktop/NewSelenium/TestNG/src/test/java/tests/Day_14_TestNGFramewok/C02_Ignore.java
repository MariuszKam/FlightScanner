package tests.Day_14_TestNGFramewok;

import org.testng.annotations.Ignore;
import org.testng.annotations.Test;

public class C02_Ignore {

    @Test(priority = 5)
    public void youtubeTest(){
        System.out.println("youtube");
    }
    @Test(priority = -3) @Ignore
    public void wiseTest(){
        System.out.println("wise");
    }
    @Test(priority = 5)
    public void amazonTest(){
        System.out.println("amazon");
    }

}
