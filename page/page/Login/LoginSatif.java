package page.Login;

import java.util.concurrent.TimeUnit;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.PageFactory;
import util.FunctionGeneric;

public class LoginSatif {

	WebDriver driver;
	static String curDir = System.getProperty("user.dir");

	public LoginSatif() {

	}

	public WebDriver openUrlSatif(String ambiente) {
		try {

			FunctionGeneric.executeCMD("taskkill -f -im cmd.exe");
			FunctionGeneric.executeCMD("taskkill -f -im IEDriverServer.exe");
			System.setProperty("webdriver.ie.driver", "C:\\desarrollos\\Drivers\\IEDriverServer.exe");
			// System.setProperty("webdriver.ie.driver", curDir +
			// "\\drivers\\IEDriverServer.exe");
			DesiredCapabilities capabilities = DesiredCapabilities.internetExplorer();
			capabilities.setCapability(InternetExplorerDriver.INTRODUCE_FLAKINESS_BY_IGNORING_SECURITY_DOMAINS, true);
			capabilities.setCapability("requireWindowFocus", true);
			capabilities.setCapability("ignoreZoomSetting", true);
			capabilities.setCapability("nativeEvents", false);

			driver = new InternetExplorerDriver(capabilities);
			
			if (ambiente.replace(" ", "").toUpperCase().equals("QA1")) {
				//driver.navigate().to("http://108.20.1.41:10061/sat/index.jsp");
				driver.navigate().to("http://www.google.cl");
			} else if (ambiente.replace(" ", "").toUpperCase().equals("QA4")) {
				driver.navigate().to("http://desacloud960.falabella.cl:8580/sat/index.jsp");
			}

			driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
			driver.manage().window().maximize();

			PageFactory.initElements(driver, this);

		} catch (Exception e) {
			System.out.println("Error al abrir SATIF: " + e.getMessage());
		}
		return driver;
	}

	public String ingresoLogin(String usuario, String password, WebDriver driver) {

		String msg = "OK";

		try {

			driver.switchTo().frame("ifr");

			msg = FunctionGeneric.setTextObject("Usuario", "name", "USUARIOCMR", usuario, "set", false, driver);
			if (!msg.equals("OK"))
				return msg;

			msg = FunctionGeneric.setTextObject("Usuario", "name", "PASSWORD", password, "set", false, driver);
			if (!msg.equals("OK"))
				return msg;
			FunctionGeneric.addScreenEvi("Login", "Pass");

			msg = FunctionGeneric.clickObjectByXpath("Botón Aceptar", "INPUT", "value", "Aceptar", "click", driver);
			if (!msg.equals("OK"))
				return msg;

			if (FunctionGeneric.validatePopUp(driver)) {
				msg = "No se ha logrado realizar Login correctamente";
				FunctionGeneric.addScreenEvi("Login", "Fail");
				driver.switchTo().alert().accept();
			}

		} catch (Exception e) {
			msg = "Error al realizar login en SATIF";
		}
		return msg;
	}

	public String validarURL(String title) {
		String msg = "OK";
		if (!title.equals(driver.getTitle())) {

			msg = "No se ha logrado acceder a la URL " + title;
		}
		return msg;
	}
}
