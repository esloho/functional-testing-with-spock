import org.openqa.selenium.firefox.FirefoxDriver

//driver = {
//    System.setProperty('webdriver.chrome.driver', '/Users/elozano/Downloads/chromedriver')
//    new ChromeDriver()
//}

driver = { new FirefoxDriver() }

baseUrl = "http://localhost:8080"
reportsDir = "target/geb-reports"