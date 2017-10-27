package page.Menu;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import util.FunctionGeneric;

public class Menu {

	public String menuAperturaInmediata(WebDriver driver) {

		String msg = "OK";

		try {

			msg = FunctionGeneric.clickObjectByXpath("Menú Admisión", "table", "id", "BotonBarraMenuBarraMenuAdmisión",
					"click", driver);
			if (!msg.equals("OK"))
				return msg;

			msg = FunctionGeneric.clickObjectByXpath("Menú Apertura Inmediata", "td", "id",
					"MenuOptionBarraMenuAdmisiónApertura Inmediata", "click", driver);
			if (!msg.equals("OK"))
				return msg;

			msg = FunctionGeneric.validarTexto("Monto Solicitud", "Acceder al Menú Apertura Inmediata", driver);

		} catch (Exception e) {
			msg = "Error en el Menú Apertura Inmediata";
		}

		return msg;
	}

	public String menuBusquedaContrato(WebDriver driver) {

		String msg = "OK";

		try {

			msg = FunctionGeneric.clickObjectByXpath("Menú Atención al Clientes", "table", "id",
					"BotonBarraMenuBarraMenuAtención_al_cliente", "click", driver);
			if (!msg.equals("OK"))
				return msg;
			msg = FunctionGeneric.clickObjectByXpath("Menú Búsqueda de Contrato", "td", "id",
					"MenuOptionBarraMenuAtención_al_clienteBúsqueda de contrato", "click", driver);
			if (!msg.equals("OK"))
				return msg;

			msg = FunctionGeneric.validarTexto("Número de Contrato",
					"Acceder el Menú Atención al Cliente Busqueda de Contrato", driver);

		} catch (Exception e) {
			msg = "Error en Menú Busqueda Contrato";
		}

		return msg;
	}

	public String subMenuModificacionCupo(WebDriver driver) {

		String msg = "OK";

		try {

			driver = FunctionGeneric.waitWindows(2, driver);

			WebElement menuModificaciones = (new WebDriverWait(driver, 25))
					.until(ExpectedConditions.presenceOfElementLocated(By.id("BotonBarraMenuBarraMenuModificaciones")));

			menuModificaciones.click();

			Thread.sleep(1000);

			WebElement menuModificarCupo = driver
					.findElement(By.xpath("//td[@id='MenuOptionBarraMenuModificacionesModificar Cupo']"));
			menuModificarCupo.click();

			Thread.sleep(4000);

			WebElement FrameInterContrato = (new WebDriverWait(driver, 25))
					.until(ExpectedConditions.presenceOfElementLocated(By.name("InterfaceContrato")));

			driver.switchTo().frame(FrameInterContrato);

			WebElement txtCupo = driver.findElement(By.xpath("//input[@name='CUPOSOL']"));
			txtCupo.sendKeys("300000");

			WebElement btnAceptar = driver.findElement(By.name("ACEPTAR"));
			btnAceptar.click();

			WebDriverWait wait = new WebDriverWait(driver, 15);
			Alert myAlert = wait.until(ExpectedConditions.alertIsPresent());
			myAlert.accept();

			driver = FunctionGeneric.waitWindows(3, driver);

			WebElement btnCrear = driver.findElement(By.xpath("//img[@name='Enviar']"));
			btnCrear.click();

		} catch (Exception e) {
			System.out.println("Error MenÃº Busqueda Contrato  " + e.toString());
			msg = "Error MenÃº Busqueda Contrato " + e.toString();
		}
		return msg;
	}

	public String menuAdmisionEvaluador(WebDriver driver) {

		String msg = "OK";

		try {

			driver = FunctionGeneric.waitWindows(0, driver);

			msg = FunctionGeneric.clickObject("Menu Admisión", "id", "BotonBarraMenuBarraMenuAdmisión", "click",
					driver);
			if (!msg.equals("OK"))
				return msg;
			msg = FunctionGeneric.clickObject("Menu Evaluador", "id",
					"BotonBarraMenuAdmisiónBarraMenuAdmisiónEvaluador", "click", driver);
			if (!msg.equals("OK"))
				return msg;
			msg = FunctionGeneric.clickObject("Menu Evaluador de Solicitudes", "id",
					"MenuOptionBarraMenuAdmisiónEvaluadorEvaluador de Solicitudes", "click", driver);

		} catch (Exception e) {
			msg = "Error en Menú Admisión Evaluador de Solicitudes";
		}

		return msg;
	}

	public String subMenuDetalle(WebDriver driver) {

		String msg = "OK";

		try {

			driver = FunctionGeneric.waitWindows(2, driver);

			msg = FunctionGeneric.clickObjectByXpath("Menú Detalle", "td", "id", "MenuOptionBarraMenuDetalle", "enter",
					driver);
			FunctionGeneric.clickObjectByXpath("Menú Detalle", "td", "id", "MenuOptionBarraMenuDetalle", "click",
					driver);

			Thread.sleep(4000);

		} catch (Exception e) {
			msg = "Error en Submenú Detalle";
		}

		return msg;
	}

}
