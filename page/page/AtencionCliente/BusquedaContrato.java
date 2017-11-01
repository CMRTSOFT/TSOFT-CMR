package page.AtencionCliente;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import util.FunctionGeneric;

public class BusquedaContrato {

	public static String formularioContrato(String rut, WebDriver driver) {

		String msg = "OK";

		try {

			msg = FunctionGeneric.setTextObject("Número de Documento", "name", "NUMDOC", rut, "set", false, driver);
			if (!msg.equals("OK"))
				return msg;
			msg = FunctionGeneric.clickObject("Botón Aceptar", "name", "ELIMINAR", "click", driver);

		} catch (Exception e) {
			msg = "Error en Ingreso de Formulario de Contrato";
		}

		return msg;
	}

	public static boolean validarCuentas(WebDriver driver) {
		boolean exists = false;
		try {
			int cant = driver.findElements(By.xpath("//IMG[@src='imagenes/refrescar.gif']")).size();
			if (cant > 0) {
				if (driver.getPageSource().contains("NO SE HAN ENCONTRADO DATOS PARA ESTA CONSULTA")) {
					exists = false;
				} else {
					exists = true;
				}
			}
		} catch (Exception ex) {
			// TODO: handle exception
			System.out.println("Error en la validación de la Cuentas Busqueda Contrato " + ex.toString());
		}
		return exists;
	}

	public static String seleccionarProducto(String producto, WebDriver driver) {

		String msg = "OK";

		try {

			msg = FunctionGeneric.clickObject("Link Producto", "link", producto, "click", driver);

		} catch (Exception e) {
			msg = "Error en la Selección de Producto";
		}

		return msg;
	}

	public static String formContratoPAN(String tipoTarjeta, String numTarjeta, WebDriver driver) {
		String msg = "OK";
		int index = 0;
		try {
			WebElement FrameInterContrato = (new WebDriverWait(driver, 25))
					.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//IFRAME[@src='paginas/index.jsp']")));
			driver.switchTo().frame(FrameInterContrato);
			WebElement txtNumTarjeta = driver.findElement(By.name("PAN2"));
			Select cboTipoTarjeta = new Select(driver.findElement(By.xpath("//SELECT[@name='MPMF_BINENTTIP']")));
			WebElement cboTarjeta = driver.findElement(By.xpath("//SELECT[@name='MPMF_BINENTTIP']"));
			WebElement btnAceptar = driver.findElement(By.name("ELIMINAR"));

			if (cboTarjeta.isDisplayed()) {
				index = FunctionGeneric.returnPositionDataCBO(cboTarjeta, tipoTarjeta);
			} else {
				msg = "No se encontró en combobox de N° Tarjeta";

			}

			if (index >= 0) {
				cboTipoTarjeta.selectByIndex(index);
			} else {
				msg = "No se encontró Valor en combobox N° Tarjeta";

			}

			if (txtNumTarjeta.isDisplayed()) {
				txtNumTarjeta.sendKeys(numTarjeta);
			} else {
				if (msg.equals("OK"))
					msg = "";

				msg = msg + "No se encontró el campo N° Tarjeta \n";

			}
			if (btnAceptar.isDisplayed()) {
				btnAceptar.click();
			} else {
				if (msg.equals("OK"))
					msg = "";

				msg = msg + "No se encontró el botón Aceptar \n";

			}

		} catch (Exception e) {
			System.out.println("Error con el Formulario de Contrato PAN " + e.toString());
			msg = "Error con el Formulario de Contrato PAN " + e.toString();
		}
		return msg;
	}

	public static String formContratoContrato(String numContrato, WebDriver driver) {
		String msg = "OK";
		try {
			if (driver.findElements(By.xpath("//INPUT[@name='ALTACUENTA']")).size() == 0) {
				msg = "No se encontró el campo Contrato \\n";
				System.exit(0);
			}
			if (driver.findElements(By.xpath("//INPUT[@name='ELIMINAR']")).size() == 0) {
				msg = "No se encontró el botón Aceptar \\n";
				System.exit(0);
			}
			WebElement txtContrato = driver.findElement(By.xpath("//INPUT[@name='ALTACUENTA']"));
			WebElement btnAceptar = driver.findElement(By.name("ELIMINAR"));
			if (txtContrato.isDisplayed()) {
				txtContrato.sendKeys(numContrato);
			} else {
				if (msg.equals("OK"))
					msg = "";

				msg = msg + "No se encontró el campo Contrato \n";
				System.exit(0);
			}

			if (btnAceptar.isDisplayed()) {
				btnAceptar.click();
			} else {
				if (msg.equals("OK"))
					msg = "";

				msg = msg + "No se encontró el botón Aceptar \n";
				System.exit(0);
			}

		} catch (Exception e) {
			System.out.println("Error con el Formulario de Contrato CONTRATO " + e.toString());
			msg = "Error con el Formulario de Contrato CONTRATO " + e.toString();
		}
		return msg;
	}

	public static String formContratoNombre(String nombre, WebDriver driver) {

		String msg = "OK";

		try {

			msg = FunctionGeneric.setTextObjectByXpath("Nombre", "INPUT", "name", "NOMBRES", nombre, "set", driver);
			if (!msg.equals("OK"))
				return msg;

			msg = FunctionGeneric.clickObjectByXpath("Botón Aceptar", "INPUT", "name", "ELIMINAR", "click", driver);
			if (!msg.equals("OK"))
				return msg;

			List<WebElement> listUser = (new WebDriverWait(driver, 30))
					.until(ExpectedConditions.presenceOfAllElementsLocatedBy((By.tagName("A"))));

			if (listUser.size() < 0) {
				msg = "No se encontraron usuarios con el nombre " + nombre;
				return msg;
			}

			listUser.get(0).click();
			FunctionGeneric.waitWindows(2, driver);
			driver.switchTo().frame((new WebDriverWait(driver, 30)).until(
					ExpectedConditions.presenceOfElementLocated(By.xpath("//IFRAME[@src='paginas/esperaAux.jsp']"))));

		} catch (Exception e) {
			msg = "Error en el Formulario de Contrato";
		}

		return msg;
	}

	public static String formContratoApellido(String apellido, WebDriver driver) {

		String msg = "OK";

		try {

			msg = FunctionGeneric.setTextObject("Nombre", "name", "APELLI1", apellido, "set", false, driver);
			if (!msg.equals("OK"))
				return msg;

			msg = FunctionGeneric.clickObject("Botón Aceptar", "name", "ELIMINAR", "click", driver);
			if (!msg.equals("OK"))
				return msg;

			List<WebElement> listUser = (new WebDriverWait(driver, 30))
					.until(ExpectedConditions.presenceOfAllElementsLocatedBy((By.tagName("A"))));

			if (listUser.size() > 1) {
				listUser.get(1).click();
			} else {
				msg = "No se encontraron usuarios con ese Apellido";
				return msg;
			}

			FunctionGeneric.addScreenEvi("Formulario contrato Apellido", "Pass");

			listUser = (new WebDriverWait(driver, 30))
					.until(ExpectedConditions.presenceOfAllElementsLocatedBy((By.tagName("A"))));
			if (listUser.size() > 1) {
				listUser.get(1).click();
			} else {
				msg = "No se encontraron usuarios con ese Apellido";
				return msg;
			}

			Thread.sleep(7000);
			FunctionGeneric.waitWindows(2, driver);

			driver.switchTo().frame((new WebDriverWait(driver, 30)).until(
					ExpectedConditions.presenceOfElementLocated(By.xpath("//IFRAME[@src='paginas/esperaAux.jsp']"))));

		} catch (Exception e) {
			msg = "Error en el Formulario de Contrato";
		}

		return msg;
	}
}
