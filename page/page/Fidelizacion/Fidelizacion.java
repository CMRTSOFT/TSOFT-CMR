package page.Fidelizacion;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import util.FunctionGeneric;

public class Fidelizacion {

	private FunctionGeneric func = new FunctionGeneric();

	public String formBusqueda(String rut, WebDriver driver) {
		String msg = "OK";
		try {
			WebElement FrameInterContrato = (new WebDriverWait(driver, 60)).until(
					ExpectedConditions.presenceOfElementLocated(By.xpath("//IFRAME[@src='paginas/index1.jsp']")));
			driver.switchTo().frame(FrameInterContrato);
			
			WebElement txtRut = (new WebDriverWait(driver, 60))
					.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//INPUT[@name='SIDCLIENT']")));
			// INPUT value=Entrar
			WebElement btnEntrar = (new WebDriverWait(driver, 60))
					.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//INPUT[@value='Entrar']")));
			if (txtRut.isDisplayed()) {
				txtRut.sendKeys(rut);
			} else {
				if (msg.equals("OK"))
					msg = "";

				msg = msg + "No se encontró el Campo RUT \n";
				return msg;
			}
			if (btnEntrar.isDisplayed()) {
				btnEntrar.click();
			} else {
				if (msg.equals("OK"))
					msg = "";

				msg = msg + "No se encontró el Botón \n";
				return msg;
			}

		} catch (Exception e) {
			System.out.println("ERROR EN EL FORMULARIO BUSQUEDA CUENTA GLOBAL " + e.getMessage());
			msg = "ERROR EN EL FORMULARIO BUSQUEDA CUENTA GLOBAL " + e.getMessage();
		}
		return msg;
	}

	public String selectAccountmovements(WebDriver driver) {
		String msg = "OK";
		try {
			// paginas/index1.jsp
			List<WebElement> lnkMovements = (new WebDriverWait(driver, 60))
					.until(ExpectedConditions.presenceOfAllElementsLocatedBy((By.tagName("a"))));

			WebElement btnBuscar = (new WebDriverWait(driver, 60))
					.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//INPUT[@value='Buscar']")));

			if (lnkMovements.size() > 0) {
				lnkMovements.get(0).click();
			} else {
				if (msg.equals("OK"))
					msg = "";

				msg = msg + "No se Encontraron movimientos \n";
				return msg;
			}

			if (btnBuscar.isDisplayed()) {
				btnBuscar.click();
			} else {
				if (msg.equals("OK"))
					msg = "";

				msg = msg + "No se encontró botón Buscar \n";
				return msg;
			}
		} catch (Exception e) {
			System.out.println("ERROR AL MOMENTO DE SELECCIONAR UNA CUENTA PARA BUSCAR MOVIMIENTOS " + e.getMessage());
		}
		return msg;
	}

	public String validarMovimientos(WebDriver driver) {
		String msg = "OK";

		try {
			// NO SE HAN ENCONTRADO DATOS PARA ESTA CONSULTA
			if (func.validarTexto("NO SE HAN ENCONTRADO DATOS PARA ESTA CONSULTA","Desplegar pantalla movimientos", driver).equals("OK")) {
				msg = "No se encontraron datos de movimientos";
				return msg;
			}

		} catch (Exception e) {
			System.out.println("ERROR AL MOMENTO VALIDAR LA CONSULTA DE PUNTOS " + e.getMessage());
			msg = "ERROR AL MOMENTO VALIDAR LA CONSULTA DE PUNTOS " + e.getMessage();
		}
		return msg;
	}

	/** Estado Cuenta ***/

	public String formEstadoCuenta(WebDriver driver) {
		String msg = "OK";
		try {
			// IMG src /imagenes/buscar.gif

			WebElement FrameInterContrato = (new WebDriverWait(driver, 60)).until(
					ExpectedConditions.presenceOfElementLocated(By.xpath("//IFRAME[@src='paginas/index1.jsp']")));
			driver.switchTo().frame(FrameInterContrato);

			List<WebElement> lnkDirectory = (new WebDriverWait(driver, 60)).until(ExpectedConditions
					.presenceOfAllElementsLocatedBy((By.xpath("//IMG[@src='/imagenes/buscar.gif']"))));

			if (lnkDirectory.size() > 0) {
				lnkDirectory.get(4).click();
			} else {
				if (msg.equals("OK"))
					msg = "";

				msg = msg + "No se encontró botón Para abrir Ventana \n";
				return msg;
			}

		} catch (Exception e) {
			System.out.println("ERROR EN EL FORMULARIO DE ESTADO CUENTA " + e.getMessage());
			msg = "ERROR EN EL FORMULARIO DE ESTADO CUENTA " + e.getMessage();
		}
		return msg;
	}

	public String formWindowUser(String rut, WebDriver driver) {
		String msg = "OK";
		try {
			Set<String> winSet = driver.getWindowHandles();
			List<String> winList = new ArrayList<String>(winSet);
			if (!func.waitWindows(2, winList, driver)) {
				msg = msg + "No se encontró Ventana 2 \n";
				return msg;
			}
			Thread.sleep(3000);
			winSet = driver.getWindowHandles();
			winList = new ArrayList<String>(winSet);
			driver.switchTo().window(winList.get(winList.size() - 1));
			WebElement txtRut = (new WebDriverWait(driver, 60))
					.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//INPUT[@id='SIDCLIENT']")));
			WebElement btnEntrar = (new WebDriverWait(driver, 60))
					.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//INPUT[@value='Entrar']")));
			if (txtRut.isDisplayed()) {
				txtRut.sendKeys(rut);
			} else {
				if (msg.equals("OK"))
					msg = "";

				msg = msg + "No se encontró Campo rut \n";
				return msg;
			}
			if (btnEntrar.isDisplayed()) {
				btnEntrar.click();
			} else {
				if (msg.equals("OK"))
					msg = "";

				msg = msg + "No se encontró Campo rut \n";
				return msg;
			}

			List<WebElement> lnkMovements = (new WebDriverWait(driver, 60))
					.until(ExpectedConditions.presenceOfAllElementsLocatedBy((By.tagName("a"))));
			if (lnkMovements.size() > 0) {
				lnkMovements.get(0).click();
			} else {
				if (msg.equals("OK"))
					msg = "";

				msg = msg + "No se encontraron Lista de Movimientos \n";
				return msg;
			}
		} catch (Exception e) {
			System.out.println("ERROR EN EL FORMULARIO DE LA VENTANA " + e.getMessage());
			msg = "ERROR EN EL FORMULARIO DE LA VENTANA " + e.getMessage();
		}
		return msg;
	}

	public String formEstateAccount(WebDriver driver) {
		String msg = "OK";
		try {
			Set<String> winSet = driver.getWindowHandles();
			List<String> winList = new ArrayList<String>(winSet);
			if (!func.waitWindows(1, winList, driver)) {
				msg = msg + "No se encontró Ventana principal \n";
				return msg;
			}
			Thread.sleep(3000);
			winSet = driver.getWindowHandles();
			winList = new ArrayList<String>(winSet);
			driver.switchTo().window(winList.get(winList.size() - 1));

			WebElement FrameInterContrato = (new WebDriverWait(driver, 60)).until(
					ExpectedConditions.presenceOfElementLocated(By.xpath("//IFRAME[@src='paginas/index1.jsp']")));
			driver.switchTo().frame(FrameInterContrato);

			// INPUT Id= TdBtnBuscar
			WebElement btnBuscar = (new WebDriverWait(driver, 60))
					.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//INPUT[@id='TdBtnBuscar']")));
			if (btnBuscar.isDisplayed()) {
				btnBuscar.click();
			} else {
				if (msg.equals("OK"))
					msg = "";

				msg = msg + "No se encontró Botón Buscar \n";
				return msg;
			}

			if (!func.validarTexto("Puntos a Caducar","Desplegar pantalla Puntos", driver).equals("OK")) {
				msg = "No se encontraron datos en la Busqueda";
				return msg;
			}
		} catch (Exception e) {
			System.out.println("ERROR EN EL BUSQUEDA DE ESTADO CUENTA " + e.getMessage());
			msg = "ERROR EN EL BUSQUEDA DE ESTADO CUENTA " + e.getMessage();
		}
		return msg;
	}
	
	
}
