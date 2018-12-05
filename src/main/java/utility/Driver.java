package utility;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;

public class Driver {

	public static WebDriver instance = null;
	private static String browser;

	public static WebDriver getInstance() {
		if (instance == null) {
			initialize();
		}
		return instance;
	}

	
	public static synchronized void initialize() {
			System.out.println("Initializing Driver...");
			
			String BROWSER_NAME = "browser";
			String browserName = System.getProperty(BROWSER_NAME);
			browser = browserName;
			if (browserName == null) {
				browser = "ch";
			}
			if (browser.equals("ff")) {
				instance = new FirefoxDriver();
			} else if (browser.equals("ch")) {
				System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir") + "//testdata//chromedriver.exe");
				instance = new ChromeDriver();
			} else if (browser.equals("ie")) {
				System.setProperty("webdriver.ie.driver", System.getProperty("user.dir") + "//testdata//IEDriverServer.exe");
				instance = new InternetExplorerDriver();
			}
			if (instance == null) {
				throw new RuntimeException("Driver init fail");
			}
	}
	
	public static void quit() {
		System.out.println("Closing browser...");
		instance.quit();
		instance = null;
	}
}
