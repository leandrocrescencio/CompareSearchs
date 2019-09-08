# CompareSearchs
**Setup to handle tests for responsive websites.**

In this framework we have:
- Java 
- Gradle 
- Test NG 
- Selenium 
- Extent-report / Logback and et. al.

The solution to select the environment for each test case uses TestNG's description method to define what type of tests it is.

So, in this idea is possible to use the same Setup class (owns @Before and @After methods) for the desktop automation and also for the mobile.

For this Test example, I wrote 4 test cases in the same class that searches Google and also on Bing, 2 of them for desktop and 2 for mobile.
