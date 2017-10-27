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

			msg = FunctionGeneric.clickObjectByXpath("Men� Admisi�n", "table", "id", "BotonBarraMenuBarraMenuAdmisi�n",
					"click", driver);
			if (!msg.equals("OK"))
				return msg;

			msg = FunctionGeneric.clickObjectByXpath("Men� Apertura Inmediata", "td", "id",
					"MenuOptionBarraMenuAdmisi�nApertura Inmediata", "click", driver);
			if (!msg.equals("OK"))
				return msg;

			msg = FunctionGeneric.validarTexto("Monto Solicitud", "Acceder al Men� Apertura Inmediata", driver);

		} catch (Exception e) {
			msg = "Error en el Men� Apertura Inmediata";
		}

		return msg;
	}

	public String menuBusquedaContrato(WebDriver driver) {

		String msg = "OK";

		try {

			msg = FunctionGeneric.clickObjectByXpath("Men� Atenci�n al Clientes", "table", "id",
					"BotonBarraMenuBarraMenuAtenci�n_al_cliente", "click", driver);
			if (!msg.equals("OK"))
				return msg;
			msg = FunctionGeneric.clickObjectByXpath("Men� B�squeda de Contrato", "td", "id",
					"MenuOptionBarraMenuAtenci�n_al_clienteB�squeda de contrato", "click", driver);
			if (!msg.equals("OK"))
				return msg;

			msg = FunctionGeneric.validarTexto("N�mero de Contrato",
					"Acceder el Men� Atenci�n al Cliente Busqueda de Contrato", driver);

		} catch (Exception e) {
			msg = "Error en Men� Busqueda Contrato";
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
			System.out.println("Error Menú Busqueda Contrato  " + e.toString());
			msg = "Error Menú Busqueda Contrato " + e.toString();
		}
		return msg;
	}

	public String menuAdmisionEvaluador(WebDriver driver) {

		String msg = "OK";

		try {

			driver = FunctionGeneric.waitWindows(0, driver);

			msg = FunctionGeneric.clickObject("Menu Admisi�n", "id", "BotonBarraMenuBarraMenuAdmisi�n", "click",
					driver);
			if (!msg.equals("OK"))
				return msg;
			msg = FunctionGeneric.clickObject("Menu Evaluador", "id",
					"BotonBarraMenuAdmisi�nBarraMenuAdmisi�nEvaluador", "click", driver);
			if (!msg.equals("OK"))
				return msg;
			msg = FunctionGeneric.clickObject("Menu Evaluador de Solicitudes", "id",
					"MenuOptionBarraMenuAdmisi�nEvaluadorEvaluador de Solicitudes", "click", driver);

		} catch (Exception e) {
			msg = "Error en Men� Admisi�n Evaluador de Solicitudes";
		}

		return msg;
	}

	public String subMenuDetalle(WebDriver driver) {

		String msg = "OK";

		try {

			driver = FunctionGeneric.waitWindows(2, driver);

			msg = FunctionGeneric.clickObjectByXpath("Men� Detalle", "td", "id", "MenuOptionBarraMenuDetalle", "enter",
					driver);
			FunctionGeneric.clickObjectByXpath("Men� Detalle", "td", "id", "MenuOptionBarraMenuDetalle", "click",
					driver);

			Thread.sleep(4000);

		} catch (Exception e) {
			msg = "Error en Submen� Detalle";
		}

		return msg;
	}

}
