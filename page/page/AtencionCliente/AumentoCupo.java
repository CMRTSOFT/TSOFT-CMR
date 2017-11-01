package page.AtencionCliente;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import util.FunctionGeneric;

public class AumentoCupo {

	public String formAumentoCupo(String cupo, WebDriver driver) {

		String msg = "OK";

		try {

			Thread.sleep(4000);

			Set<String> winSet;
			List<String> winList;
			WebElement FrameInterContrato = (new WebDriverWait(driver, 60))
					.until(ExpectedConditions.presenceOfElementLocated(By.name("InterfaceContrato")));

			driver.switchTo().frame(FrameInterContrato);

			WebElement txtCupo = (new WebDriverWait(driver, 60))
					.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//input[@name='CUPOSOL']")));

			if (txtCupo.isDisplayed()) {
				txtCupo.sendKeys(cupo);
			} else {
				if (msg.equals("OK")) {
					msg = "";
				}
				msg = msg + "No se encontró el Link Autorización \n";

				return msg;
			}

			WebElement btnAceptar = driver.findElement(By.name("ACEPTAR"));
			btnAceptar.click();

			/*
			 * WebDriverWait wait = new WebDriverWait(driver, 15); Alert myAlert =
			 * wait.until(ExpectedConditions.alertIsPresent()); myAlert.accept();
			 */
			Thread.sleep(4000);
			if (FunctionGeneric.validaAlert(driver)) {

				driver.switchTo().alert().accept();
			}
			winSet = driver.getWindowHandles();
			winList = new ArrayList<String>(winSet);

			if (!FunctionGeneric.waitWindows(3, winList, driver)) {
				msg = "No encuentra ventana 3 ";
				return msg;
			}
			winSet = driver.getWindowHandles();
			winList = new ArrayList<String>(winSet);
			driver.switchTo().window(winList.get(winList.size() - 1));

			// System.out.println(driver.getPageSource());

			// INPUT name OBSERV
			/*
			 * WebElement txtObserv = (new WebDriverWait(driver,
			 * 25)).until(ExpectedConditions
			 * .presenceOfElementLocated(By.xpath("//INPUT[@name='OBSERV']"))); if
			 * (txtObserv.isDisplayed()) { txtObserv.sendKeys();; } else { if
			 * (msg.equals("OK")) msg = "";
			 * 
			 * msg = msg + "No se encontró Botón Crear \n"; System.exit(0); }
			 */
			WebElement btnCrear = (new WebDriverWait(driver, 60))
					.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//IMG[@name='Enviar']")));

			if (btnCrear.isDisplayed()) {
				btnCrear.click();
			} else {
				if (msg.equals("OK"))
					msg = "";

				msg = msg + "No se encontró Botón Crear \n";
				return msg;
			}

		} catch (Exception e) {
			System.out.println("ERROR FORMULARIO AUMENTO CUPO " + e.getMessage());
			msg = "ERROR FORMULARIO AUMENTO CUPO \n" + e.getMessage();
		}
		return msg;
	}

	public String formAumentoCupo(WebDriver driver) {
		String msg = "OK";

		try {
			Thread.sleep(4000);
			Set<String> winSet;
			List<String> winList;
			WebElement FrameInterContrato = (new WebDriverWait(driver, 60))
					.until(ExpectedConditions.presenceOfElementLocated(By.name("InterfaceContrato")));

			driver.switchTo().frame(FrameInterContrato);

			WebElement txtCupo = driver.findElement(By.xpath("//input[@name='CUPOSOL']"));
			txtCupo.sendKeys("300000");

			WebElement btnAceptar = driver.findElement(By.name("ACEPTAR"));
			btnAceptar.click();

			/*
			 * WebDriverWait wait = new WebDriverWait(driver, 15); Alert myAlert =
			 * wait.until(ExpectedConditions.alertIsPresent()); myAlert.accept();
			 */
			Thread.sleep(4000);
			if (FunctionGeneric.validaAlert(driver)) {

				driver.switchTo().alert().accept();
			}
			winSet = driver.getWindowHandles();
			winList = new ArrayList<String>(winSet);

			if (!FunctionGeneric.waitWindows(3, winList, driver)) {
				msg = "No encuentra ventana 3 ";
				System.exit(0);
			}
			winSet = driver.getWindowHandles();
			winList = new ArrayList<String>(winSet);
			driver.switchTo().window(winList.get(winList.size() - 1));

			// System.out.println(driver.getPageSource());

			// INPUT name OBSERV
			/*
			 * WebElement txtObserv = (new WebDriverWait(driver,
			 * 25)).until(ExpectedConditions
			 * .presenceOfElementLocated(By.xpath("//INPUT[@name='OBSERV']"))); if
			 * (txtObserv.isDisplayed()) { txtObserv.sendKeys();; } else { if
			 * (msg.equals("OK")) msg = "";
			 * 
			 * msg = msg + "No se encontró Botón Crear \n"; System.exit(0); }
			 */
			WebElement btnCrear = (new WebDriverWait(driver, 25))
					.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//IMG[@name='Enviar']")));

			if (btnCrear.isDisplayed()) {
				btnCrear.click();
			} else {
				if (msg.equals("OK"))
					msg = "";

				msg = msg + "No se encontró Botón Crear \n";
				System.exit(0);
			}
			// WebElement btnCrear = driver.findElement(By.xpath("//IMG[@name='Enviar']"));
			// btnCrear.click();

		} catch (Exception e) {
			System.out.println("ERROR FORMULARIO AUMENTO CUPO " + e.getMessage());
			msg = "ERROR FORMULARIO AUMENTO CUPO \n" + e.getMessage();
		}
		return msg;
	}

	public static String formAumentoCupoAprobado(String cupo, WebDriver driver) {

		String msg = "OK";

		try {

			Thread.sleep(4000);

			driver.switchTo().frame((new WebDriverWait(driver, 30))
					.until(ExpectedConditions.presenceOfElementLocated(By.name("InterfaceContrato"))));

			msg = FunctionGeneric.setTextObject("Cupo", "name", "CUPOSOL", cupo, "set", false, driver);
			if (!msg.equals("OK"))
				return msg;

			msg = FunctionGeneric.clickObject("Botón Aceptar", "name", "ACEPTAR", "click", driver);
			if (!msg.equals("OK"))
				return msg;

			Thread.sleep(4000);

			FunctionGeneric.addScreenEvi("Resultado de Solicitud", "Pass");
			msg = FunctionGeneric.validaMensajeAlert("Aprobado", driver);

		} catch (Exception e) {
			msg = "Error al modificar cupo";
		}

		return msg;
	}

	public static String formAumentoCupoAlternativo(String cupo, WebDriver driver) {

		String msg = "OK";

		try {

			Thread.sleep(4000);

			Set<String> winSet;
			List<String> winList;

			driver.switchTo().frame((new WebDriverWait(driver, 30))
					.until(ExpectedConditions.presenceOfElementLocated(By.name("InterfaceContrato"))));

			msg = FunctionGeneric.setTextObject("Campo Cupo", "name", "CUPOSOL", cupo, "set", false, driver);
			if (!msg.equals("OK"))
				return msg;
			FunctionGeneric.addScreenEvi("Formulario Aumento Cupo", "Pass");

			msg = FunctionGeneric.clickObject("Botón Aceptar", "name", "ACEPTAR", "click", driver);
			if (!msg.equals("OK"))
				return msg;

			msg = FunctionGeneric.validaMensajeAlert("elevar solicitud", driver);
			if (!msg.equals("OK"))
				return msg;

			winSet = driver.getWindowHandles();
			winList = new ArrayList<String>(winSet);
			if (!FunctionGeneric.waitWindows(3, winList, driver)) {
				msg = "No encuentra ventana 3 ";
				return msg;
			}

			driver = FunctionGeneric.waitWindows(3, driver);
			FunctionGeneric.addScreenEvi("Creación de Solicitud", "Pass");

			msg = FunctionGeneric.clickObject("Botón Crear", "name", "Enviar", "click", driver);
			if (!msg.equals("OK"))
				return msg;

			msg = FunctionGeneric.validaMensajeAlert("Solicitud", driver);

		} catch (Exception e) {
			msg = "Error al crear solicitud Aumento de Cupo";
		}

		return msg;
	}

	public static String formAumentoCupoRechazo(String cupo, WebDriver driver) {

		String msg = "OK";

		try {

			Thread.sleep(4000);

			driver.switchTo().frame((new WebDriverWait(driver, 30))
					.until(ExpectedConditions.presenceOfElementLocated(By.name("InterfaceContrato"))));

			msg = FunctionGeneric.setTextObject("Cupo", "name", "CUPOSOL", cupo, "set", false, driver);
			if (!msg.equals("OK"))
				return msg;

			msg = FunctionGeneric.clickObject("Botón Aceptar Modificaciones", "name", "ACEPTAR", "click", driver);
			if (!msg.equals("OK"))
				return msg;

			msg = FunctionGeneric.validaMensajeAlert("Monto Rechazado", driver);

		} catch (Exception e) {
			msg = "Error al modificar Cupo";
		}

		return msg;
	}

	public static String formAumentoCupoSAAprobado(String cupo, WebDriver driver) {

		String msg = "OK";

		try {

			Thread.sleep(4000);

			driver.switchTo().frame((new WebDriverWait(driver, 30))
					.until(ExpectedConditions.presenceOfElementLocated(By.name("InterfaceContrato"))));

			msg = FunctionGeneric.setTextObject("Cupo", "name", "CUPOSOL", cupo, "set", false, driver);
			if (!msg.equals("OK"))
				return msg;

			msg = FunctionGeneric.clickObject("Botón Aceptar", "name", "ACEPTAR", "click", driver);
			if (!msg.equals("OK"))
				return msg;

			Thread.sleep(4000);

			FunctionGeneric.addScreenEvi("Resultado Solicitud", "Pass");
			msg = FunctionGeneric.validaMensajeAlert("Aprobado", driver);

		} catch (Exception e) {
			msg = "Error al aumentar cupo" + e.getMessage();
		}

		return msg;
	}

	public static String formAumentoCupoSARechazo(String cupo, WebDriver driver) {

		String msg = "OK";

		try {

			Thread.sleep(4000);

			driver.switchTo().frame((new WebDriverWait(driver, 30))
					.until(ExpectedConditions.presenceOfElementLocated(By.name("InterfaceContrato"))));

			msg = FunctionGeneric.setTextObject("Cupo", "name", "CUPOSOL", cupo, "set", false, driver);
			if (!msg.equals("OK"))
				return msg;

			msg = FunctionGeneric.clickObject("Botón Aceptar Modificaciones", "name", "ACEPTAR", "click", driver);
			if (!msg.equals("OK"))
				return msg;

			msg = FunctionGeneric.validaMensajeAlert("Monto Rechazado", driver);

		} catch (Exception e) {
			msg = "Error al modificar cupo";
		}
		return msg;
	}
}
