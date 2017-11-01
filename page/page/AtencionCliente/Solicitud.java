package page.AtencionCliente;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import util.FunctionGeneric;

public class Solicitud {

	public static String formularioSolicitud(WebDriver driver) {

		String msg = "OK";

		try {

			driver.switchTo().frame((new WebDriverWait(driver, 60)).until(
					ExpectedConditions.presenceOfElementLocated(By.xpath("//IFRAME[@src='paginas/esperaAux.jsp']"))));

			msg = FunctionGeneric.clickObject("Botón Siguiente", "id", "SIGUIENTE", "click", driver);
			if (!msg.equals("OK"))
				return msg;

			msg = FunctionGeneric.clickObjectByXpath("Botón Anterior", "input", "value", "Anterior", "click", driver);

		} catch (Exception e) {
			msg = "Error en el formulario de solicitud";
		}

		return msg;
	}

}
