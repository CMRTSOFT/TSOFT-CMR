package page.Falanet;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class Falanet {

	WebDriver driver;
	
	public WebDriver openUrlFalanet(){
		this.driver = util.FunctionGeneric.initChromeDriver("http://testfalanet.falabella.com/admfats/PRT/logica/jsp/PRTFFInicio.jsp");
		PageFactory.initElements(driver, this);
		if (!"Falanet Intranet Corporativa".equals(driver.getTitle())) {
			throw new IllegalStateException(
					"La pantalla de login Falanet no se ha desplegado correctamente: " + driver.getTitle());
		}
		return driver;
	}
	public String ingresoLogin(String usuario, String password, WebDriver driver) {

		String msg = "OK";

		try {

			List<WebElement> txtUsuario = (new WebDriverWait(driver, 25)).until(ExpectedConditions
					.presenceOfAllElementsLocatedBy((By.name("rut_aux"))));
			
			if (txtUsuario.size() != 0) {

				if (txtUsuario.get(0).isDisplayed()) {
					txtUsuario.get(0).sendKeys(usuario);
				} else {
					if (msg.equals("OK")) {
						msg = "";
					}
					msg = msg + "No se encontró el campo Usuario \n";
					System.exit(0);
				}
			} else {
				if (msg.equals("OK")) {
					msg = "";
				}
				msg = msg + "No se encontró el campo Usuario \n";
				System.exit(0);
			}

			if (driver.findElements(By.name("clave_aux")).size() != 0) {
				WebElement txtPassword = driver.findElement(By.name("clave_aux"));
				if (txtPassword.isDisplayed()) {
					txtPassword.sendKeys(password);
				} else {
					if (msg.equals("OK")) {
						msg = "";
					}
					msg = msg + "No se encontró el campo Contraseña \n";
					System.exit(0);
				}
			} else {
				if (msg.equals("OK")) {
					msg = "";
				}
				msg = msg + "No se encontró el campo Contraseña \n";
				System.exit(0);
			}

			if (driver.findElements(By.xpath("//INPUT[@value='Aceptar']")).size() != 0) {
				WebElement btnAceptar = driver.findElement(By.xpath("//INPUT[@value='Aceptar']"));

				if (btnAceptar.isDisplayed()) {
					btnAceptar.click();
				} else {
					if (msg.equals("OK")) {
						msg = "";
					}
					msg = msg + "No se encontró el botón Aceptar \n";
					System.exit(0);
				}
			} else {
				if (msg.equals("OK")) {
					msg = "";
				}
				msg = msg + "No se encontró el botón Aceptar \n";
				System.exit(0);
			}

		} catch (Exception e) {
			msg = "Erro Login Satif: " + e.getMessage();
		}

		return msg;
	}

}
