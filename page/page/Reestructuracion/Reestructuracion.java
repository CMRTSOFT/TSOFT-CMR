package page.Reestructuracion;

import java.awt.Robot;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import util.FunctionGeneric;
import util.KeyboardClass;

public class Reestructuracion {
	FunctionGeneric func = new FunctionGeneric();
	static KeyboardClass keyBoa;

	public static String formVencimientos(String tipoFinanciacion, String cuotas, WebDriver driver) {
		String msg = "OK";
		try {
			int index = 0;
			driver.switchTo().frame((new WebDriverWait(driver, 60)).until(
					ExpectedConditions.presenceOfElementLocated(By.xpath("//IFRAME[@src='paginas/esperaAux.jsp']"))));

			// A name=LinkSolapaSOLAPA3
			WebElement lnkSuperAvance = (new WebDriverWait(driver, 60))
					.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//A[@name='LinkSolapaSOLAPA3']")));
			if (lnkSuperAvance.isDisplayed()) {
				lnkSuperAvance.click();
			} else {
				if (msg.equals("OK"))
					msg = "";

				msg = msg + "No se encontró la pestaña Super Avance en vencimientos  \n";
				return msg;
			}
			// IMG name=REESTRUCTURAR
			WebElement btnReestructurar = (new WebDriverWait(driver, 60))
					.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//IMG[@name='REESTRUCTURAR']")));
			if (btnReestructurar.isDisplayed()) {
				btnReestructurar.click();
			} else {
				if (msg.equals("OK"))
					msg = "";

				msg = msg + "No se encontró el Botón Generar Renegociar Total  \n";
				return msg;
			}
			// name=CODTIPC
			WebElement cboFinanciacion = (new WebDriverWait(driver, 60))
					.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//SELECT[@name='CODTIPC']")));
			Select cboTipoFinanciacion = new Select(cboFinanciacion);

			// name=TOTCUOTAS
			WebElement txtCuotas = (new WebDriverWait(driver, 60))
					.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//INPUT[@name='TOTCUOTAS']")));
			// name Simular
			WebElement btnSimular = (new WebDriverWait(driver, 60))
					.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//INPUT[@name='Simular']")));

			if (cboFinanciacion.isDisplayed())
				index = FunctionGeneric.returnPositionDataCBO(cboFinanciacion, tipoFinanciacion);
			if (index >= 0) {
				cboTipoFinanciacion.selectByIndex(index);
			} else {
				msg = "No se encontró el valor en Combobox de TIPO FINANCIACIÓN \n";
				return msg;
			}
			if (txtCuotas.isDisplayed()) {
				txtCuotas.sendKeys(cuotas);
			} else {
				if (msg.equals("OK"))
					msg = "";

				msg = msg + "No se encontró el Campo CUOTAS  \n";
				return msg;
			}
			if (btnSimular.isDisplayed()) {
				btnSimular.click();
			} else {
				if (msg.equals("OK"))
					msg = "";

				msg = msg + "No se encontró el Botón Simular  \n";
				return msg;
			}

			if (FunctionGeneric.validaAlert(driver)) {
				driver.switchTo().alert().accept();
			}

		} catch (Exception e) {
			System.out.println("ERROR EN LA PANTALLA DE VENCIMIENTOS " + e.getMessage());
			msg = "ERROR EN LA PANTALLA DE VENCIMIENTOS " + e.getMessage();
		}
		return msg;
	}

	public static String formVencimientoImprimir(WebDriver driver) {
		String msg = "";
		try {
			keyBoa = new KeyboardClass();
			Robot robot = new Robot();
			Set<String> winSet;
			List<String> winList;
			// Falta VALOR
			WebElement btnImprimir = (new WebDriverWait(driver, 60))
					.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//INPUT[@name='Imprimir']")));
			if (btnImprimir.isDisplayed()) {
				btnImprimir.click();
			} else {
				if (msg.equals("OK"))
					msg = "";

				msg = msg + "No se encontró el Botón IMPRIMIR  \n";
				return msg;
			}
			Thread.sleep(2000);
			if (FunctionGeneric.validaAlert(driver)) {
				driver.switchTo().alert().accept();
			}
			Thread.sleep(6000);
			keyBoa.KeyPressTecl("ALT");
			keyBoa.KeyPressTecl("F4");
			robot.keyRelease(KeyEvent.VK_ALT);
			robot.keyRelease(KeyEvent.VK_F4);
			Thread.sleep(4000);

			winSet = driver.getWindowHandles();
			winList = new ArrayList<String>(winSet);
			if (!FunctionGeneric.waitWindows(3, winList, driver)) {
				msg = msg + "No se encontró el ventana 3 \n";
				return msg;
			}
			winSet = driver.getWindowHandles();
			winList = new ArrayList<String>(winSet);
			driver.switchTo().window(winList.get(winList.size() - 1));
			FunctionGeneric.closeWindows(driver, 2);
			Thread.sleep(2000);
			winSet = driver.getWindowHandles();
			winList = new ArrayList<String>(winSet);
			if (!FunctionGeneric.waitWindows(2, winList, driver)) {
				msg = msg + "No se encontró el ventana 2 \n";
				return msg;
			}
			winSet = driver.getWindowHandles();
			winList = new ArrayList<String>(winSet);
			driver.switchTo().window(winList.get(winList.size() - 1));
			Thread.sleep(3000);
			if (FunctionGeneric.validaAlert(driver)) {
				driver.switchTo().alert().accept();
			}
			Thread.sleep(3000);

			if (FunctionGeneric.validaAlert(driver)) {
				driver.switchTo().alert().accept();
			}
			Thread.sleep(3000);

			if (FunctionGeneric.validaAlert(driver)) {
				driver.switchTo().alert().accept();
			}

		} catch (Exception e) {
			System.out.println("ERROR EN EL FORMULARIO IMPRIMIR " + e.getMessage());
			msg = "ERROR EN EL FORMULARIO IMPRIMIR " + e.getMessage();
		}
		return msg;
	}

}
