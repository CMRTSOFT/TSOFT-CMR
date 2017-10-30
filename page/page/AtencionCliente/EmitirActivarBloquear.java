package page.AtencionCliente;

import java.awt.Robot;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import util.FunctionGeneric;

public class EmitirActivarBloquear {
	FunctionGeneric func;

	public String bloquearTarjeta(WebDriver driver) {
		String msg = "OK";
		try {
			Robot robot = new Robot();
			Thread.sleep(3000);
			func = new FunctionGeneric();
			Set<String> winSet = driver.getWindowHandles();
			List<String> winList = new ArrayList<String>(winSet);
			WebElement FrameInterContrato = (new WebDriverWait(driver, 30)).until(
					ExpectedConditions.presenceOfElementLocated(By.xpath("//IFRAME[@src='paginas/esperaAux.jsp']")));
			driver.switchTo().frame(FrameInterContrato);
			// System.out.println(driver.getPageSource());
			// IMG name=EMITIR; IMG name=ACTIVAR; IMG name=BLOQUEAR
			List<WebElement> lstRadio = (new WebDriverWait(driver, 30)).until(ExpectedConditions
					.presenceOfAllElementsLocatedBy((By.xpath("//INPUT[@name='RadioButtonTarjetasVigentes']"))));
			List<WebElement> lstLink;

			WebElement btnBloquear = (new WebDriverWait(driver, 30))
					.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//IMG[@name='BLOQUEAR']")));
			if (lstRadio.size() != 0) {
				lstRadio.get(0).click();
			} else {
				if (msg.equals("OK"))
					msg = "";

				msg = msg + "No se encontraron Tarjetas Vigentes \n";
				return msg;
			}
			if (btnBloquear.isDisplayed()) {
				btnBloquear.click();
			} else {
				if (msg.equals("OK"))
					msg = "";

				msg = msg + "No se encontró Botón Bloquear \n";
				return msg;
			}
			// System.out.println(driver.getPageSource());
			lstLink = (new WebDriverWait(driver, 30))
					.until(ExpectedConditions.presenceOfAllElementsLocatedBy((By.linkText("EXTRAVIO"))));
			if (lstLink.size() != 0) {
				lstLink.get(0).click();
			} else {
				if (msg.equals("OK"))
					msg = "";

				msg = msg + "No se encontraron Motivo a Bloquear \n";
				return msg;
			}

			if (func.validaAlert(driver)) {
				driver.switchTo().alert().accept();
				robot.keyPress(KeyEvent.VK_ESCAPE);
				robot.keyRelease(KeyEvent.VK_ESCAPE);

				Thread.sleep(2000);
				func = new FunctionGeneric();
				winSet = driver.getWindowHandles();
				winList = new ArrayList<String>(winSet);
				if (!func.waitWindows(3, winList, driver)) {
					msg = msg + "No se encontró el ventana de menú \n";
					return msg;
				}
				winSet = driver.getWindowHandles();
				winList = new ArrayList<String>(winSet);
				driver.switchTo().window(winList.get(winList.size() - 1));

				driver.close();
			}

			// driver.switchTo().alert().;
			/*
			 * Thread.sleep(3000); robot.keyPress(KeyEvent.VK_ESCAPE);
			 * robot.keyRelease(KeyEvent.VK_ESCAPE);
			 * 
			 * // robot.keyRelease(KeyEvent.VK_F4); Thread.sleep(2000); func = new
			 * FunctionGeneric(); Set<String> winSet = driver.getWindowHandles();
			 * List<String> winList = new ArrayList<String>(winSet); if
			 * (!func.waitWindows(3, winList, driver)) { msg = msg +
			 * "No se encontró el ventana de menú \n"; return msg; } winSet =
			 * driver.getWindowHandles(); winList = new ArrayList<String>(winSet);
			 * driver.switchTo().window(winList.get(winList.size() - 1));
			 * 
			 * driver.close();
			 * 
			 * //System.out.println(driver.getPageSource());
			 */
		} catch (Exception e) {
			System.out.println("ERROR AL BLOQUEAR TARJETA " + e.getMessage());
			msg = "ERROR AL BLOQUEAR TARJETA ";
		}
		return msg;
	}

	public String emitirTarjetas(WebDriver driver) {
		String msg = "OK";
		try {
			func = new FunctionGeneric();
			Robot robot = new Robot();
			Set<String> winSet = driver.getWindowHandles();
			List<String> winList = new ArrayList<String>(winSet);
			WebElement FrameInterContrato = (new WebDriverWait(driver, 30)).until(
					ExpectedConditions.presenceOfElementLocated(By.xpath("//IFRAME[@src='paginas/esperaAux.jsp']")));
			driver.switchTo().frame(FrameInterContrato);
			// System.out.println(driver.getPageSource());
			// IMG name=EMITIR; IMG name=ACTIVAR; IMG name=BLOQUEAR
			List<WebElement> lstRadio = (new WebDriverWait(driver, 30)).until(ExpectedConditions
					.presenceOfAllElementsLocatedBy((By.xpath("//INPUT[@name='RadioButtonTarjetasVigentes']"))));

			WebElement btnEmitir = (new WebDriverWait(driver, 30))
					.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//IMG[@name='EMITIR']")));
			if (lstRadio.size() != 0) {
				lstRadio.get(0).click();
			} else {
				if (msg.equals("OK"))
					msg = "";

				msg = msg + "No se encontraron Tarjetas Vigentes \n";
				return msg;
			}
			if (btnEmitir.isDisplayed()) {
				btnEmitir.click();
			} else {
				if (msg.equals("OK"))
					msg = "";

				msg = msg + "No se encontró Botón Emitir \n";
				return msg;
			}
			Thread.sleep(6000);
			if (func.validaAlert(driver)) {
				driver.switchTo().alert().accept();
				Thread.sleep(3000);
				robot.keyPress(KeyEvent.VK_ESCAPE);
				robot.keyRelease(KeyEvent.VK_ESCAPE);
				winSet = driver.getWindowHandles();
				winList = new ArrayList<String>(winSet);
				if (!func.waitWindows(3, winList, driver)) {
					msg = msg + "No se encontró el ventana de menú \n";
					return msg;
				}
				winSet = driver.getWindowHandles();
				winList = new ArrayList<String>(winSet);
				driver.switchTo().window(winList.get(winList.size() - 1));

				driver.close();
				
				winSet = driver.getWindowHandles();
				winList = new ArrayList<String>(winSet);
				if (!func.waitWindows(2, winList, driver)) {
					msg = msg + "No se encontró el ventana de menú \n";
					return msg;
				}
				winSet = driver.getWindowHandles();
				winList = new ArrayList<String>(winSet);
				driver.switchTo().window(winList.get(winList.size() - 1));
				
				FrameInterContrato = (new WebDriverWait(driver, 30)).until(
						ExpectedConditions.presenceOfElementLocated(By.xpath("//IFRAME[@src='paginas/esperaAux.jsp']")));
				driver.switchTo().frame(FrameInterContrato);
				if (!func.validarTexto("Aprobación","Desplegar Pantalla", driver).equals("OK")) {
					msg = "Valor no se encontró en HTML \n";
					return msg;
				}
			}

		} catch (Exception e) {
			System.out.println("ERROR EN LA EMISIÓN DE TARJETA " + e.getMessage());
			msg = "ERROR EN EMITIR TARJETA ";
		}
		return msg;
	}

	public String activarTarjeta(WebDriver driver) {
		String msg = "OK";
		try {
			func = new FunctionGeneric();
			//Robot robot = new Robot();
			WebElement FrameInterContrato = (new WebDriverWait(driver, 30)).until(
					ExpectedConditions.presenceOfElementLocated(By.xpath("//IFRAME[@src='paginas/esperaAux.jsp']")));
			driver.switchTo().frame(FrameInterContrato);
			// IMG name=EMITIR; IMG name=ACTIVAR; IMG name=BLOQUEAR
			List<WebElement> lstRadio = (new WebDriverWait(driver, 30)).until(ExpectedConditions
					.presenceOfAllElementsLocatedBy((By.xpath("//INPUT[@name='RadioButtonTarjetasVigentes']"))));

			WebElement btnActivar = (new WebDriverWait(driver, 30))
					.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//IMG[@name='ACTIVAR']")));
			if (lstRadio.size() != 0) {
				lstRadio.get(0).click();
			} else {
				if (msg.equals("OK"))
					msg = "";

				msg = msg + "No se encontraron Tarjetas Vigentes \n";
				return msg;
			}
			if (btnActivar.isDisplayed()) {
				btnActivar.click();
			} else {
				if (msg.equals("OK"))
					msg = "";

				msg = msg + "No se encontró Botón Activar \n";
				return msg;
			}

		} catch (Exception e) {
			System.out.println("ERROR EN ACTIVACIÓN DE TARJETA " + e.getMessage());
			msg = "ERROR EN ACTIVACIÓN DE TARJETA " ;
		}
		return msg;
	}
}
