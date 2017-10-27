package page.Admision;

import java.util.concurrent.TimeUnit;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import util.FunctionGeneric;

public class Evaluador {

	public String evaluarCliente(String rut, WebDriver driver) {

		String msg = "OK";

		try {

			driver.switchTo().frame(driver.findElement(By.xpath("//IFRAME[@src='paginas/index.jsp']")));

			msg = FunctionGeneric.setTextObject("Número de Documento", "name", "MPMF_NUMDOC", rut, "set", false, driver);
			if (!msg.equals("OK"))
				return msg;
			msg = FunctionGeneric.setTextObject("Número de Documento", "name", "MPMF_NUMDOC", rut, "enter", false, driver);
			if (!msg.equals("OK"))
				return msg;

			driver.manage().timeouts().pageLoadTimeout(20, TimeUnit.SECONDS);

			msg = FunctionGeneric.clickObjectByXpath("Botón Siguiente", "input", "title", "Siguiente", "click", driver);

		} catch (Exception e) {
			msg = "Error en el Evaluador de Cliente";
		}

		return msg;
	}

	public String validaEstado(String resolucion, String rechazo, WebDriver driver) {

		String msg = "OK";

		WebElement cboResolucion = (new WebDriverWait(driver, 30))
				.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//SELECT[@name='MPAP_RESOLUCION']")));

		int index = FunctionGeneric.returnPositionDataCBO(cboResolucion, resolucion);

		if (index < 0) {
			msg = msg + "La solicitud no posee el rechazo: " + rechazo;
			return msg;
		}

		WebElement txtRechazo = (new WebDriverWait(driver, 30))
				.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//INPUT[@id='MPAP_RECHAZOSDS']")));
		if (!txtRechazo.getAttribute("value").equals(rechazo)) {
			msg = msg + "La solicitud no posee el rechazo: " + rechazo;
			return msg;
		}

		return msg;
	}

	public String crearCuenta(WebDriver driver) {

		String msg = "OK";

		try {

			msg = FunctionGeneric.clickObjectByXpath("Botón Seguros y PAT", "input", "value", "SEGUROS Y PAT", "click",
					driver);
			
			driver = FunctionGeneric.waitWindows(2, driver);

			msg = FunctionGeneric.clickObject("Seguro 1", "name", "INDENVCENTPIN1", "click", driver);
			if (!msg.equals("OK"))
				return msg;
			FunctionGeneric.clickObject("Seguro 1", "name", "INDENVCENTPIN1", "click", driver);

			msg = FunctionGeneric.clickObject("Seguro 2", "name", "INDENVCENTPIN2", "click", driver);
			if (!msg.equals("OK"))
				return msg;
			FunctionGeneric.clickObject("Seguro 2", "name", "INDENVCENTPIN2", "click", driver);

			msg = FunctionGeneric.clickObject("Seguro 3", "name", "INDENVCENTPIN3", "click", driver);
			if (!msg.equals("OK"))
				return msg;
			FunctionGeneric.clickObject("Seguro 3", "name", "INDENVCENTPIN3", "click", driver);

			FunctionGeneric.addScreenEvi("Crear Cuenta", "Pass");
			
			driver.close();

			driver = FunctionGeneric.waitWindows(1, driver);

			driver.switchTo().frame(driver.findElement(By.xpath("//IFRAME[@src='paginas/index.jsp']")));

			msg = FunctionGeneric.selecCBO("Emisión de Tarjeta", "name", "MPAP_EMISION_TJT", "2 - NO", false, driver);
			if (!msg.equals("OK"))
				return msg;

			msg = FunctionGeneric.selecCBO("Resolución", "name", "MPAP_RESOLUCUSU", "1 - CREADA", false, driver);
			if (!msg.equals("OK"))
				return msg;

			FunctionGeneric.addScreenEvi("Crear Cuenta", "Pass");

			msg = FunctionGeneric.clickObjectByXpath("Botón Crear Cuenta", "input", "value", "Crear Cuenta", "click",
					driver);

		} catch (Exception e) {
			msg = "Error al Crear Cuenta";
		}

		return msg;
	}
}
