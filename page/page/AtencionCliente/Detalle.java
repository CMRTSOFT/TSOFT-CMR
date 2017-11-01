package page.AtencionCliente;

import java.util.concurrent.TimeUnit;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import util.FunctionGeneric;

public class Detalle {

	public static String lnkAutorizaciones(WebDriver driver) {

		String msg = "OK";

		try {

			driver.switchTo().frame((new WebDriverWait(driver, 30)).until(
					ExpectedConditions.presenceOfElementLocated(By.xpath("//IFRAME[@src='paginas/esperaAux.jsp']"))));

			msg = FunctionGeneric.clickObjectByXpath("Link Autorizaciones", "a", "name", "LinkSolapaAUTORIZACIONES",
					"click", driver);

		} catch (Exception e) {
			msg = "Error al presionar Link Autorizaciones ";
		}

		return msg;
	}

	public static String validaPantallaDetalle(WebDriver driver) {

		String msg = "OK";

		try {

			driver.switchTo().frame(driver.findElement(By.xpath("//IFRAME[@src='paginas/esperaAux.jsp']")));

			if (!FunctionGeneric.findObject("name", "TIDOVE2", driver))
				msg = "La pantalla Detalle no se ha desplegado correctamente";

		} catch (Exception e) {
			msg = "La pantalla Detalle no se ha desplegado correctamente";
		}
		return msg;
	}

	public static String liberarAutorizacion(WebDriver driver) {

		String msg = "OK";

		try {

			msg = FunctionGeneric.clickObject("Checkbox Autorización", "name", "CheckAutorizaciones2", "click", driver);
			if (!msg.equals("OK"))
				return msg;
			FunctionGeneric.arrEvidencia
					.add("Liberación de Autorización" + "-" + FunctionGeneric.Screenshot() + "-" + "Pass");

			msg = FunctionGeneric.clickObject("Botón Liberar", "name", "LIBERAR2", "click", driver);
			driver.manage().timeouts().pageLoadTimeout(10, TimeUnit.SECONDS);

		} catch (Exception e) {

			msg = "Error al Liberar Autorizacion";
		}

		return msg;
	}

	public static String lnkCambioHistorico(WebDriver driver) {

		String msg = "OK";

		try {

			driver.switchTo().frame((new WebDriverWait(driver, 30)).until(
					ExpectedConditions.presenceOfElementLocated(By.xpath("//IFRAME[@src='paginas/esperaAux.jsp']"))));

			msg = FunctionGeneric.clickObject("Link Cambios", "name", "LinkSolapaCAMBIOS", "click", driver);

		} catch (Exception e) {

			msg = "Error al presionar Link Cambio Histórico";
		}

		return msg;
	}
}
