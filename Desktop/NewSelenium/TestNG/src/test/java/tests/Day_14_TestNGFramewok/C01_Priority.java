package tests.Day_14_TestNGFramewok;

import org.testng.annotations.Test;

public class C01_Priority {

    @Test(priority = 5)
    public void youtubeTest(){
        System.out.println("youtube");
    }
    @Test(priority = -3)
    public void wiseTest(){
        System.out.println("wise");
    }
    @Test(priority = 5)
    public void amazonTest(){
        System.out.println("amazon");
    }

}
