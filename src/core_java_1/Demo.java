package core_java_1;

public class Demo {
    public static void main(String[] args) {
        System.out.println("Hello, World!");

        Selenium cheat sheet

Driver Initialization:-
WebDriver driver = new FirefoxDriver(); // Firefox
WebDriver driver = new ChromeDriver(); // Chrome
WebDriver driver = new InternetExplorerDriver(); // Internet Explorer
WebDriver driver = new SafariDriver(); // Safari

Locators:-
driver.findElement(By.id("q")).sendKeys("selenium 3"); // By ID
driver.findElement(By.name("q")).sendKeys("Selenium 3"); // By Name
driver.findElement(By.xpath("//input[@id='q']")).sendKeys("Selenium 3"); // By XPath
driver.findElement(By.linkText("edit this page")).click(); // By Link Text
driver.findElement(By.className("profileheader")).click(); // By Class Name
driver.findElement(By.tagName("select")).click(); // By Tag Name
driver.findElement(By.linkText("Next")).click(); // By Link Text
driver.findElement(By.partialLinkText("Next")).click(); // By Partial Link Text

Selenium Navigation:-
driver.navigate().to("http://newexample.com"); // Navigate to URL
driver.navigate().refresh(); // Refresh page
driver.navigate().forward(); // Forward in browser history
driver.navigate().back(); // Backwards in browser history

Window Handling:-
string handle = driver.getWindowHandle(); // Get Window Handle

Set<String> handles = driver.getWindowHandles(); // Switch to newly created window
for (String handle : handles) {
    driver.switchTo().window(handle);
}

Frames:-
driver.switchTo().frame(1); // By Index
driver.switchTo().frame("name"); // By Name
driver.switchTo().frame(element); // By Element
driver.switchTo().defaultContent(); // Return to main document

Alerts:-
driver.switchTo().alert().getText(); // Get text
driver.switchTo().alert().dismiss(); // Dismiss
driver.switchTo().alert().accept(); // Accept
driver.switchTo().alert().sendKeys("Text"); // Send Keys

Operations:-
driver.get("http://www.webdriverinselenium.com"); // Launch Webpage
driver.findElement(By.id("submit")).click(); // Click Button
driver.switchTo().alert().dismiss(); // Handle Alert
driver.getElementById("field").setAttribute("disabled", "true"); // Disable field (requires JS execution)
driver.getElementById("field").removeAttribute("disabled"); // Enable field (requires JS execution)

Waits:-
driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS); // Implicit Wait

WebDriverWait wait = new WebDriverWait(driver, 10); // Explicit Wait
wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("element")));

Thread.sleep(10); // Sleep

TestNG Annotations:-
@BeforeSuite
@AfterSuite
@BeforeClass
@AfterClass
@BeforeTest
@AfterTest
@BeforeMethod
@AfterMethod
@Test

JUnit Annotations:-
@Before
@After
@Test

Selenium Grid:-
 // Start Hub
 java -jar selenium-server-standalone-x.x.x.jar -role hub

 // Start Node
 java -jar selenium-server-standalone-x.x.x.jar -role node -hub http://localhost:4444/grid/register
    }
}
