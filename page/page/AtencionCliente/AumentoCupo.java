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
	FunctionGeneric func;

	public String formAumentoCupo(String cupo, WebDriver driver) {
		String msg = "OK";

		try {
			Thread.sleep(4000);
			func = new FunctionGeneric();

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
				msg = msg + "No se encontr� el Link Autorizaci�n \n";
				
				return msg;
			}
			

			WebElement btnAceptar = driver.findElement(By.name("ACEPTAR"));
			btnAceptar.click();

			/*
			 * WebDriverWait wait = new WebDriverWait(driver, 15); Alert myAlert =
			 * wait.until(ExpectedConditions.alertIsPresent()); myAlert.accept();
			 */
			Thread.sleep(4000);
			if (func.validaAlert(driver)) {
				
				driver.switchTo().alert().accept();
			}
			winSet = driver.getWindowHandles();
			winList = new ArrayList<String>(winSet);

			if (!func.waitWindows(3, winList, driver)) {
				msg = "No encuentra ventana 3 ";
				return msg;
			}
			winSet = driver.getWindowHandles();
			winList = new ArrayList<String>(winSet);
			driver.switchTo().window(winList.get(winList.size() - 1));
			
			//System.out.println(driver.getPageSource());

			// INPUT name OBSERV
			/*WebElement txtObserv = (new WebDriverWait(driver, 25)).until(ExpectedConditions
					.presenceOfElementLocated(By.xpath("//INPUT[@name='OBSERV']")));
			if (txtObserv.isDisplayed()) {
				txtObserv.sendKeys();;
			} else {
				if (msg.equals("OK"))
					msg = "";

				msg = msg + "No se encontr� Bot�n Crear \n";
				System.exit(0);
			}*/
			WebElement btnCrear = (new WebDriverWait(driver, 60)).until(ExpectedConditions
					.presenceOfElementLocated(By.xpath("//IMG[@name='Enviar']")));
			
			if (btnCrear.isDisplayed()) {
				btnCrear.click();
			} else {
				if (msg.equals("OK"))
					msg = "";

				msg = msg + "No se encontr� Bot�n Crear \n";
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
			func = new FunctionGeneric();

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
			if (func.validaAlert(driver)) {
				
				driver.switchTo().alert().accept();
			}
			winSet = driver.getWindowHandles();
			winList = new ArrayList<String>(winSet);

			if (!func.waitWindows(3, winList, driver)) {
				msg = "No encuentra ventana 3 ";
				System.exit(0);
			}
			winSet = driver.getWindowHandles();
			winList = new ArrayList<String>(winSet);
			driver.switchTo().window(winList.get(winList.size() - 1));
			
			//System.out.println(driver.getPageSource());

			// INPUT name OBSERV
			/*WebElement txtObserv = (new WebDriverWait(driver, 25)).until(ExpectedConditions
					.presenceOfElementLocated(By.xpath("//INPUT[@name='OBSERV']")));
			if (txtObserv.isDisplayed()) {
				txtObserv.sendKeys();;
			} else {
				if (msg.equals("OK"))
					msg = "";

				msg = msg + "No se encontr� Bot�n Crear \n";
				System.exit(0);
			}*/
			WebElement btnCrear = (new WebDriverWait(driver, 25)).until(ExpectedConditions
					.presenceOfElementLocated(By.xpath("//IMG[@name='Enviar']")));
			
			if (btnCrear.isDisplayed()) {
				btnCrear.click();
			} else {
				if (msg.equals("OK"))
					msg = "";

				msg = msg + "No se encontr� Bot�n Crear \n";
				System.exit(0);
			}
			//WebElement btnCrear = driver.findElement(By.xpath("//IMG[@name='Enviar']"));
			//btnCrear.click();
			
		} catch (Exception e) {
			System.out.println("ERROR FORMULARIO AUMENTO CUPO " + e.getMessage());
			msg = "ERROR FORMULARIO AUMENTO CUPO \n" + e.getMessage();
		}
		return msg;
	}
}
