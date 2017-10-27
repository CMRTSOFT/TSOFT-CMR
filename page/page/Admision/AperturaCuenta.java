package page.Admision;

import java.awt.AWTException;
import java.util.List;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import util.FunctionGeneric;

public class AperturaCuenta {

	public String solicitudApertura(WebDriver driver, String rut, String numSerie) {

		String msg = "OK";

		try {

			driver.switchTo().frame((new WebDriverWait(driver, 30)).until(
					ExpectedConditions.presenceOfElementLocated(By.xpath("//IFRAME[@src='paginas/index.jsp']"))));

			msg = FunctionGeneric.setTextObject("Número de Documento", "name", "MPMF_NUMDOC", rut, "set", false, driver);
			if (!msg.equals("OK"))
				return msg;

			msg = FunctionGeneric.setTextObject("Número de Serie", "name", "MPMF_SERIE", numSerie, "set", false, driver);
			if (!msg.equals("OK"))
				return msg;

			FunctionGeneric.addScreenEvi("Solicitud de Apertura", "Pass");
			msg = FunctionGeneric.clickObject("Botón Aceptar", "name", "ACEPTAR", "click", driver);

			Thread.sleep(2000);

		} catch (Exception e) {
			msg = "Error en la Solicitud de Apertura";
		}

		return msg;
	}

	public String validarDatosCliente(String producto, String fchPago, String cadena, String cargo, String sueldo,
			WebDriver driver) {

		String msg = "OK";

		try {

			driver.switchTo().frame((new WebDriverWait(driver, 30)).until(
					ExpectedConditions.presenceOfElementLocated(By.xpath("//IFRAME[@src='paginas/index.jsp']"))));

			List<WebElement> txtDireccion = (new WebDriverWait(driver, 30)).until(
					ExpectedConditions.presenceOfAllElementsLocatedBy((By.xpath("//INPUT[@name='MPMF_AUXDIREC']"))));

			if (txtDireccion.get(0).getAttribute("value").equals("")) {

				msg = FunctionGeneric.clickObject("Carpeta Dirección", "name", "IMGDIR", "click", driver);
				if (!msg.equals("OK"))
					return msg;

				driver = FunctionGeneric.waitWindows(2, driver);
				
				msg = FunctionGeneric.setTextObject("Comuna", "name", "COMUNA", "SANTIAGO", "set", false, driver);
				if (!msg.equals("OK"))
					return msg;
				FunctionGeneric.setTextObject("Comuna", "name", "COMUNA", "", "enter", false, driver);

				Thread.sleep(1000);

				msg = FunctionGeneric.setTextObject("Calle", "name", "CALLE", "CARMEN", "set", false, driver);
				if (!msg.equals("OK"))
					return msg;
				FunctionGeneric.setTextObject("Calle", "name", "CALLE", "", "enter", false, driver);

				Thread.sleep(1000);

				msg = FunctionGeneric.setTextObject("Número", "name", "NUMERO", "237", "set", false, driver);
				if (!msg.equals("OK"))
					return msg;
				FunctionGeneric.setTextObject("Número", "name", "NUMERO", "", "enter", false, driver);

				Thread.sleep(1000);

				msg = FunctionGeneric.clickObject("Botón Aceptar", "name", "ACEPTAR", "click", driver);
				if (!msg.equals("OK"))
					return msg;
			}

			driver = FunctionGeneric.waitWindows(1, driver);

			driver.switchTo().frame((new WebDriverWait(driver, 30)).until(
					ExpectedConditions.presenceOfElementLocated(By.xpath("//IFRAME[@src='paginas/index.jsp']"))));

			msg = FunctionGeneric.setTextObject("Telefono", "name", "MPMF_FONO", "123456789", "set", true, driver);
			if (!msg.equals("OK"))
				return msg;

			msg = FunctionGeneric.setTextObject("Celular", "name", "MPMF_CELULAR", "987654321", "set", true, driver);
			if (!msg.equals("OK"))
				return msg;

			msg = FunctionGeneric.selecCBO("Producto", "name", "MPMF_PRODUCTO", producto, true, driver);
			if (!msg.equals("OK"))
				return msg;

			msg = FunctionGeneric.selecCBO("Día de Vencimiento", "name", "MPMF_DIAVCTO", fchPago, true, driver);
			if (!msg.equals("OK"))
				return msg;

			msg = FunctionGeneric.selecCBO("Cadena", "name", "MPMF_CADENA", fchPago, true, driver);
			if (!msg.equals("OK"))
				return msg;

			msg = FunctionGeneric.selecCBO("Cargo", "name", "MPMF_CARGOAD", fchPago, true, driver);
			if (!msg.equals("OK"))
				return msg;

			msg = FunctionGeneric.setTextObject("Sueldo", "name", "MPMF_SUELDO", sueldo, "set", true, driver);
			if (!msg.equals("OK"))
				return msg;

			msg = FunctionGeneric.selecCBO("Confirmación Sueldo", "name", "MPMF_CONFSUELDO", "SI", true, driver);
			if (!msg.equals("OK"))
				return msg;

			FunctionGeneric.addScreenEvi("Validar Datos del Cliente", "Pass");

			msg = FunctionGeneric.clickObjectByXpath("Botón Aceptar", "input", "value", "Aceptar", "click", driver);

		} catch (Exception e) {
			System.out.println("Error al Validar Datos del Cliente");
		}

		return msg;
	}

	public String ingresoFormularioCliente(WebDriver driver) throws AWTException {

		String msg = "OK";
		String actividad = "04 - ADMINISTRATIVO";

		try {

			driver.switchTo().window(driver.getWindowHandle());

			driver.switchTo().frame((new WebDriverWait(driver, 30))
					.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//IFRAME[@src='paginas/index.jsp']"))));

			msg = FunctionGeneric.selecCBO("Nacionalidad", "name", "MPMF_NACIONALIDAD", "152 - CHILENA", true, driver);
			if (!msg.equals("OK"))
				return msg;

			List<WebElement> txtDireccion = (new WebDriverWait(driver, 60)).until(
					ExpectedConditions.presenceOfAllElementsLocatedBy((By.xpath("//INPUT[@name='MPMF_AUXDIREC']"))));

			if (txtDireccion.get(0).getAttribute("value").equals("")) {

				msg = FunctionGeneric.clickObject("Carpeta Dirección", "name", "IMGDIR", "click", driver);
				if (!msg.equals("OK"))
					return msg;
				
				driver = FunctionGeneric.waitWindows(2, driver);

				msg = FunctionGeneric.setTextObject("Comuna", "name", "COMUNA", "SANTIAGO", "set", false, driver);
				if (!msg.equals("OK"))
					return msg;
				FunctionGeneric.setTextObject("Comuna", "name", "COMUNA", "", "enter", false, driver);

				Thread.sleep(1000);

				msg = FunctionGeneric.setTextObject("Calle", "name", "CALLE", "CARMEN", "set", false, driver);
				if (!msg.equals("OK"))
					return msg;
				FunctionGeneric.setTextObject("Calle", "name", "CALLE", "", "enter", false, driver);

				Thread.sleep(1000);

				msg = FunctionGeneric.setTextObject("Número", "name", "NUMERO", "237", "set", false, driver);
				if (!msg.equals("OK"))
					return msg;
				FunctionGeneric.setTextObject("Número", "name", "NUMERO", "", "enter", false, driver);

				Thread.sleep(1000);

				msg = FunctionGeneric.clickObject("Botón Aceptar", "name", "ACEPTAR", "click", driver);
				if (!msg.equals("OK"))
					return msg;

			}
			
			msg = FunctionGeneric.setTextObject("Celular", "name", "MPMF_CELULAR", "987654321", "set", true, driver);
			if (!msg.equals("OK"))
				return msg;
			
			msg = FunctionGeneric.setTextObject("Email", "name", "MPMF_EMAIL", "a@a.cl", "set", true, driver);
			if (!msg.equals("OK"))
				return msg;

			msg = FunctionGeneric.selecCBO("Tipo Vivienda", "name", "MPMF_TIPVIVIEN", "0 - PROPIA", true, driver);
			if (!msg.equals("OK"))
				return msg;
			
			msg = FunctionGeneric.setTextObject("Email", "name", "MPMF_ANTIGUEDAD", "1", "set", true, driver);
			if (!msg.equals("OK"))
				return msg;
			
			WebElement cboActividad = driver.findElement(By.xpath("//SELECT[@name='MPMF_CARGOAD']"));
			if (FunctionGeneric.returnItemActual(cboActividad).equals("99 - NO INFORMADO")) {
				cboActividad.sendKeys(actividad);

				if (actividad.equals("01 - EMPRESARIO") || actividad.equals("03 - P.EDUC.UNIVER(INDEP.)")
						|| actividad.equals("08 - TRABAJADOR INDEPENDIENTE")) {

					WebElement cboSubActividad = driver.findElement(By.xpath("//SELECT[@name='MPMF_SUBACTIVIDAD']"));
					cboSubActividad.sendKeys("sub actividad");
				}

				if (actividad.equals("01 - EMPRESARIO") || actividad.equals("02 - P.EDUC.UNIVER(EMPLE.)")
						|| actividad.equals("03 - P.EDUC.UNIVER(INDEP.)") || actividad.equals("04 - ADMINISTRATIVO")
						|| actividad.equals("05 - COMISIONISTA") || actividad.equals("06 - PERSONAL DE SEGURIDAD")
						|| actividad.equals("07 - OBREROS, OPERARIOS, AUXILIARES")
						|| actividad.equals("08 - TRABAJADOR INDEPENDIENTE")) {

					WebElement txtEmpresa = driver.findElement(By.xpath("//INPUT[@name='MPMF_EMPRESA']"));
					txtEmpresa.sendKeys("EMPRESA PRUEBA");

					WebElement cboGiro = driver.findElement(By.xpath("//SELECT[@name='MPMF_GIROAD']"));
					cboGiro.sendKeys("8 - OTROS");

					WebElement cboTamano = driver.findElement(By.xpath("//SELECT[@name='MPMF_TAMANOAD']"));
					cboTamano.sendKeys("1 - PEQUEÑA");
				}

				if (!actividad.equals("11 - JUBILADOS O MONTEPIADOS") || !actividad.equals("12 - DUEÑA DE CASA")
						|| !actividad.equals("13 - ESTUDIANTE UNIVERSITARIO")) {

					WebElement txtFechaIngreso = driver.findElement(By.xpath("//INPUT[@name='MPMF_FECINGRESO2']"));
					txtFechaIngreso.sendKeys("082015");

					WebElement cboConf = driver.findElement(By.xpath("//SELECT[@name='MPMF_CONF']"));
					cboConf.sendKeys("SI");

					WebElement txtSueldoBruto = driver.findElement(By.xpath("//INPUT[@name='MPMF_SUELDO']"));
					txtSueldoBruto.sendKeys("900000");
				}

			}

			WebElement cboEstudios = driver.findElement(By.xpath("//SELECT[@name='MPMF_ESTUDIOS']"));
			if (FunctionGeneric.returnItemActual(cboEstudios).equals("")) {
				cboEstudios.sendKeys("4 - UNIVERSITARIO");
			}

			WebElement cboCtaCte = driver.findElement(By.xpath("//SELECT[@name='MPMF_CUENTA']"));
			if (FunctionGeneric.returnItemActual(cboCtaCte).equals("")) {
				cboCtaCte.sendKeys("1 - NO TIENE");
			}
			WebElement cboTarjetaCredito = driver.findElement(By.xpath("//SELECT[@name='MPMF_TARJETAC']"));
			if (FunctionGeneric.returnItemActual(cboTarjetaCredito).equals("")) {
				cboTarjetaCredito.sendKeys("1 - NO TIENE");
			}

			WebElement cboTarjetaComercial1 = driver.findElement(By.xpath("//SELECT[@name='MPMF_TAJCOM1']"));
			if (FunctionGeneric.returnItemActual(cboTarjetaComercial1).equals("")) {
				cboTarjetaComercial1.sendKeys("1 - NO TIENE");
			}

			WebElement cboTarjetaComercial2 = driver.findElement(By.xpath("//SELECT[@name='MPMF_TAJCOM2']"));
			if (FunctionGeneric.returnItemActual(cboTarjetaComercial2).equals("")) {
				cboTarjetaComercial2.sendKeys("1 - NO TIENE");
			}

			WebElement cboSistema = driver.findElement(By.xpath("//SELECT[@name='MPMF_SISTEMA']"));
			cboSistema.sendKeys("02 - REVOLVING");

			WebElement cboVencimiento = driver.findElement(By.xpath("//SELECT[@name='MPMF_DIAVCTO']"));
			cboVencimiento.sendKeys("DIA 5");

			WebElement cboCadena = driver.findElement(By.xpath("//SELECT[@name='MPMF_CADENA']"));
			cboCadena.sendKeys("1 - CMR");

			FunctionGeneric.addScreenEvi("Completar Formulario de Apertura", "Pass");
			
			WebElement btnFin = driver.findElement(By.xpath("//INPUT[@name='FIN']"));
			btnFin.click();

		} catch (Exception e) {
			System.out.println("Error al Completar Formulario del Cliente: " + e.getMessage());
			msg = "Error al Completar Formulario del Cliente: " + e.getMessage();
		}

		return msg;
	}
}
