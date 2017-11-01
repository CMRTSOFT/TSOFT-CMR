package page.NegocioEmisor;

import java.awt.Robot;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import util.FunctionGeneric;

public class NegocioEmisor {

	public static String formBusquedaPAN(String pan, WebDriver driver) {
		String msg = "OK";
		Actions action = new Actions(driver);
		try {
			// paginas/index.jsp
			WebElement FrameInterContrato = (new WebDriverWait(driver, 60))
					.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//IFRAME[@src='paginas/index.jsp']")));
			driver.switchTo().frame(FrameInterContrato);
			// name = BUSCAR
			WebElement rdoPAN = (new WebDriverWait(driver, 60))
					.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//INPUT[@value='1']")));
			WebElement btnBuscar = (new WebDriverWait(driver, 60))
					.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//INPUT[@name='BUSCAR']")));
			WebElement txtPAN;
			if (rdoPAN.isDisplayed()) {
				action.doubleClick(rdoPAN).perform();
			} else {
				if (msg.equals("OK"))
					msg = "";

				msg = msg + "No se encontró el Radio Button PAN \n";
				return msg;
			}
			txtPAN = (new WebDriverWait(driver, 60))
					.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//INPUT[@name='PAN1']")));
			if (txtPAN.isDisplayed()) {
				txtPAN.sendKeys(pan);

			} else {
				if (msg.equals("OK"))
					msg = "";

				msg = msg + "No se encontró el Campo PAN \n";
				return msg;
			}
			if (btnBuscar.isDisplayed()) {
				btnBuscar.click();
			} else {
				if (msg.equals("OK"))
					msg = "";

				msg = msg + "No se encontró el Botón Buscar \n";
				return msg;
			}

		} catch (Exception e) {
			System.out.println("ERROR EN EL FORMULARIO BUSQUEDA DE PAN " + e.getMessage());
			msg = "ERROR EN EL FORMULARIO BUSQUEDA DE PAN " + e.getMessage();
		}
		return msg;
	}

	public static String presionLinkPlastico(WebDriver driver) {
		String msg = "OK";
		try {
			WebElement lnkPlastico = (new WebDriverWait(driver, 60))
					.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//a[@name='LinkSolapaPLASTICO']")));
			if (lnkPlastico.isDisplayed()) {
				lnkPlastico.click();
			} else {
				if (msg.equals("OK"))
					msg = "";

				msg = msg + "No se encontró el Radio Button PAN \n";
				return msg;
			}

			// Ult.renovación
			if (!FunctionGeneric.validarTexto("Ult.renovación", "Desplegar Pantalla Pestaña Plástico", driver)
					.equals("OK")) {
				msg = "No se encontró el texto a buscar \n";
				return msg;
			}
		} catch (Exception e) {
			System.out.println("ERROR AL PRESIONAR LINK PLÁSTICO " + e.getMessage());
			msg = "ERROR AL PRESIONAR LINK PLÁSTICO " + e.getMessage();
		}
		return msg;
	}

	public static String formBusquedaPANContrato(String pan, WebDriver driver) {
		String msg = "OK";
		Actions action = new Actions(driver);
		try {
			WebElement FrameInterContrato = (new WebDriverWait(driver, 60))
					.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//IFRAME[@src='paginas/index.jsp']")));
			driver.switchTo().frame(FrameInterContrato);

			WebElement rdoPAN = (new WebDriverWait(driver, 60))
					.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//INPUT[@value='2']")));
			WebElement txtPAN;
			// input name BUSQUEDA
			WebElement btnBuscar = (new WebDriverWait(driver, 60))
					.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//INPUT[@name='BUSQUEDA']")));

			if (rdoPAN.isDisplayed()) {
				action.doubleClick(rdoPAN).perform();
			} else {
				if (msg.equals("OK"))
					msg = "";

				msg = msg + "No se encontró el Radio Button PAN \n";
				return msg;
			}
			// BUSCADOR_PAN
			txtPAN = (new WebDriverWait(driver, 60))
					.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//INPUT[@name='BUSCADOR_PAN']")));
			if (txtPAN.isDisplayed()) {
				txtPAN.sendKeys(pan);
			} else {
				if (msg.equals("OK"))
					msg = "";

				msg = msg + "No se encontró el Campo Texto PAN \n";
				return msg;
			}
			if (btnBuscar.isDisplayed()) {
				btnBuscar.click();
			} else {
				if (msg.equals("OK"))
					msg = "";

				msg = msg + "No se encontró el Botón Buscar \n";
				return msg;
			}

		} catch (Exception e) {
			System.out.println("ERROR EN EL FORMULARIO BUSQUEDA CONTRATO " + e.getMessage());
			msg = "ERROR EN EL FORMULARIO BUSQUEDA CONTRATO " + e.getMessage();
		}
		return msg;
	}

	public static String selectOptionContract(WebDriver driver) {
		String msg = "OK";
		try {
			// table id = BotonBarraMenumOptions
			WebElement menuOpcionContra = (new WebDriverWait(driver, 60)).until(
					ExpectedConditions.presenceOfElementLocated(By.xpath("//TABLE[@id='BotonBarraMenumOptions']")));

			if (menuOpcionContra.isDisplayed()) {
				menuOpcionContra.click();
			} else {
				if (msg.equals("OK"))
					msg = "";

				msg = msg + "No se encontró el Menú Opciones de CONTRATO \n";
				return msg;
			}
			// td ID = MenuOptionmOptionsConsulta de extracto
			WebElement subConsExtracto = (new WebDriverWait(driver, 60)).until(ExpectedConditions
					.presenceOfElementLocated(By.xpath("//TD[@id='MenuOptionmOptionsConsulta de extracto']")));
			if (subConsExtracto.isDisplayed()) {
				subConsExtracto.click();
			} else {
				if (msg.equals("OK"))
					msg = "";

				msg = msg + "No se encontró el Menú Consulta de Extracto \n";
				return msg;
			}
		} catch (Exception e) {
			System.out.println("ERROR AL SELECCIONAR OPCIONES DE CONTRATO " + e.getMessage());
			msg = "ERROR AL SELECCIONAR OPCIONES DE CONTRATO " + e.getMessage();
		}
		return msg;
	}

	public static String selectAccountPending(WebDriver driver) {
		String msg = "OK";
		try {
			List<WebElement> lnkAccountPending = (new WebDriverWait(driver, 60))
					.until(ExpectedConditions.presenceOfAllElementsLocatedBy((By.linkText("PENDIENTE"))));

			if (lnkAccountPending.size() > 0) {
				lnkAccountPending.get(0).click();
			} else {
				if (msg.equals("OK"))
					msg = "";

				msg = msg + "No se encontraron Cuentas Pendientes \n";
				return msg;
			}

			Thread.sleep(3000);
			// input name = INCLUSION
			WebElement btnIncMOV = (new WebDriverWait(driver, 60))
					.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//INPUT[@name='INCLUSION']")));
			if (btnIncMOV.isDisplayed()) {
				btnIncMOV.click();
			} else {
				if (msg.equals("OK"))
					msg = "";

				msg = msg + "No se encontró el Botón Inc. Mov. Extractos \n";
				return msg;
			}

		} catch (Exception e) {
			System.out.println("ERROR AL SELECCIONAR UNA CUENTA EN ESTADO PENDIENTE " + e.getMessage());
			msg = "ERROR AL SELECCIONAR UNA CUENTA EN ESTADO PENDIENTE " + e.getMessage();
		}
		return msg;
	}

	public static String insertarDatosPAN(String pan, String transaccion, String monto, String comercio, String pais,
			String factura, String moneda, String actividad, String nombre, WebDriver driver) {
		String msg = "OK";
		int index = 0;
		try {
			// img id = imgnuevomantMantenimientoFormEXTRACTOS
			WebElement btnInsert = (new WebDriverWait(driver, 60)).until(ExpectedConditions
					.presenceOfElementLocated(By.xpath("//IMG[@id='imgnuevomantMantenimientoFormEXTRACTOS']")));

			if (btnInsert.isDisplayed()) {
				btnInsert.click();
			} else {
				if (msg.equals("OK"))
					msg = "";

				msg = msg + "No se encontró el Botón Insertar \n";
				return msg;
			}

			/*** Ingresar en Formulario ***/
			// PAN_I
			WebElement txtPAN = (new WebDriverWait(driver, 60))
					.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//INPUT[@name='PAN_I']")));

			WebElement cboTransac = (new WebDriverWait(driver, 60))
					.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//SELECT[@name='INDNORCOR']")));

			Select cboTipoTransac = new Select(cboTransac);
			// Input name = IMPFAC
			WebElement txtMonto = (new WebDriverWait(driver, 60))
					.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//INPUT[@name='IMPFAC']")));

			// input name = CODCOM
			WebElement txtComercio = (new WebDriverWait(driver, 60))
					.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//INPUT[@name='CODCOM']")));
			// CODPAIS
			WebElement cboPais = (new WebDriverWait(driver, 25))
					.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//SELECT[@name='CODPAIS']")));
			Select cboTipoPais = new Select(cboPais);
			// FECFAC
			WebElement txtFecFact = (new WebDriverWait(driver, 60))
					.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//INPUT[@name='FECFAC']")));
			// CLAMON_2
			WebElement cboMoneda = (new WebDriverWait(driver, 60))
					.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//SELECT[@name='CLAMON_2']")));
			Select cboTipoMoneda = new Select(cboMoneda);
			// NOMCOMRED
			WebElement txtNombre = (new WebDriverWait(driver, 60))
					.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//INPUT[@name='NOMCOMRED']")));
			// CODACT
			WebElement cboActividad = (new WebDriverWait(driver, 25))
					.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//SELECT[@name='CODACT']")));
			Select cboTipoActividad = new Select(cboActividad);

			// IMG name= IMGFAC
			WebElement imgFactura = (new WebDriverWait(driver, 60))
					.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//IMG[@name='IMGFAC']")));

			if (txtPAN.isDisplayed()) {
				txtPAN.sendKeys(pan);
			} else {
				if (msg.equals("OK"))
					msg = "";

				msg = msg + "No se encontró el Campo Referencia \n";
				return msg;
			}

			if (cboTransac.isDisplayed())
				index = FunctionGeneric.returnPositionDataCBO(cboTransac, transaccion);
			if (index >= 0) {
				cboTipoTransac.selectByIndex(index);
			} else {
				msg = "No se encontró el valor en Combobox de Transacción \n";
				return msg;
			}

			if (txtMonto.isDisplayed()) {
				txtMonto.sendKeys(monto);
			} else {
				if (msg.equals("OK"))
					msg = "";

				msg = msg + "No se encontró el Campo Monto \n";
				return msg;
			}

			if (txtComercio.isDisplayed()) {
				txtComercio.sendKeys(comercio);
			} else {
				if (msg.equals("OK"))
					msg = "";

				msg = msg + "No se encontró el Campo Comercio \n";
				return msg;
			}

			if (cboPais.isDisplayed())
				index = FunctionGeneric.returnPositionDataCBO(cboPais, pais);
			if (index >= 0) {
				cboTipoPais.selectByIndex(index);
			} else {
				msg = "No se encontró el valor en Combobox de PAIS \n";
				return msg;
			}
			// txtFecFact
			if (txtFecFact.isDisplayed()) {
				txtFecFact.sendKeys(factura);
			} else {
				if (msg.equals("OK"))
					msg = "";

				msg = msg + "No se encontró el Campo Factura \n";
				return msg;
			}
			if (cboMoneda.isDisplayed())
				index = FunctionGeneric.returnPositionDataCBO(cboMoneda, moneda);
			if (index >= 0) {
				cboTipoMoneda.selectByIndex(index);
			} else {
				msg = "No se encontró el valor en Combobox de Moneda \n";
				return msg;
			}
			//
			if (cboActividad.isDisplayed())
				index = FunctionGeneric.returnPositionDataCBO(cboActividad, actividad);
			if (index >= 0) {
				cboTipoActividad.selectByIndex(index);
			} else {
				msg = "No se encontró el valor en Combobox de PAIS \n";
				return msg;
			}

			if (txtNombre.isDisplayed()) {
				txtNombre.sendKeys(nombre);
			} else {
				if (msg.equals("OK"))
					msg = "";

				msg = msg + "No se encontró el Campo Nombre \n";
				return msg;
			}

			// IMG name= IMGFAC
			if (imgFactura.isDisplayed()) {
				imgFactura.click();
			} else {
				if (msg.equals("OK"))
					msg = "";

				msg = msg + "No se encontró la Imagen de Factura \n";
				return msg;
			}

		} catch (Exception e) {
			System.out.println("ERROR AL INSERTAR DATOS " + e.getMessage());
			msg = "ERROR AL INSERTAR DATOS " + e.getMessage();
		}
		return msg;
	}

	public static String formVentana(WebDriver driver) {
		String msg = "OK";
		try {
			Set<String> winSet = driver.getWindowHandles();
			List<String> winList = new ArrayList<String>(winSet);
			if (!FunctionGeneric.waitWindows(2, winList, driver)) {
				msg = msg + "No se encontró Ventana Principal \n";
				return msg;
			}

			winSet = driver.getWindowHandles();
			winList = new ArrayList<String>(winSet);
			driver.switchTo().window(winList.get(winList.size() - 1));
			Thread.sleep(2000);
			WebElement imgNext = (new WebDriverWait(driver, 60)).until(
					ExpectedConditions.presenceOfElementLocated(By.xpath("//IMG[@src='imagenes/siguiente.gif']")));
			if (imgNext.isDisplayed()) {
				imgNext.click();
			} else {
				if (msg.equals("OK"))
					msg = "";

				msg = msg + "No se encontró la Imagen Siguiente \n";
				return msg;
			}
			imgNext = (new WebDriverWait(driver, 60)).until(
					ExpectedConditions.presenceOfElementLocated(By.xpath("//IMG[@src='imagenes/siguiente.gif']")));
			if (imgNext.isDisplayed()) {
				imgNext.click();
			} else {
				if (msg.equals("OK"))
					msg = "";

				msg = msg + "No se encontró la Imagen Siguiente \n";
				return msg;
			}
			imgNext = (new WebDriverWait(driver, 60)).until(
					ExpectedConditions.presenceOfElementLocated(By.xpath("//IMG[@src='imagenes/siguiente.gif']")));
			if (imgNext.isDisplayed()) {
				imgNext.click();
			} else {
				if (msg.equals("OK"))
					msg = "";

				msg = msg + "No se encontró la Imagen Siguiente \n";
				return msg;
			}
			List<WebElement> lnkAccountPending = (new WebDriverWait(driver, 60))
					.until(ExpectedConditions.presenceOfAllElementsLocatedBy((By.linkText("COMPRAS"))));

			if (lnkAccountPending.size() > 0) {
				lnkAccountPending.get(0).click();
			}

			Thread.sleep(3000);

		} catch (Exception e) {
			System.out.println("ERROR EN LA VENTANA " + e.getMessage());
			msg = "ERROR EN LA VENTANA " + e.getMessage();
		}
		return msg;
	}

	public static boolean selectFactura(String factura, WebDriver driver) {
		boolean flag = false;
		try {

			WebElement imgNext = (new WebDriverWait(driver, 60)).until(
					ExpectedConditions.presenceOfElementLocated(By.xpath("//IMG[@src='imagenes/siguiente.gif']")));
			if (imgNext.isDisplayed()) {
				imgNext.click();
			}
			Thread.sleep(3000);
			List<WebElement> lnkFactura = (new WebDriverWait(driver, 60))
					.until(ExpectedConditions.presenceOfAllElementsLocatedBy((By.linkText(factura))));
			if (lnkFactura.size() > 0) {
				lnkFactura.get(0).click();
				flag = true;
			}
			if (!flag)
				selectFactura(factura, driver);

		} catch (Exception e) {
			flag = false;
		}
		return flag;
	}

	public static String confirmMoviExtractos(WebDriver driver) {
		String msg = "OK";
		try {
			Robot robot = new Robot();
			// IMG id=imgconfirmarmantMantenimientoFormEXTRACTOS
			Set<String> winSet = driver.getWindowHandles();
			List<String> winList = new ArrayList<String>(winSet);
			if (!FunctionGeneric.waitWindows(1, winList, driver)) {
				msg = msg + "No se encontró Ventana Principal \n";
				return msg;
			}
			winSet = driver.getWindowHandles();
			winList = new ArrayList<String>(winSet);
			driver.switchTo().window(winList.get(winList.size() - 1));

			WebElement FrameInterContrato = (new WebDriverWait(driver, 25))
					.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//IFRAME[@src='paginas/index.jsp']")));
			driver.switchTo().frame(FrameInterContrato);

			WebElement imgAceptar = (new WebDriverWait(driver, 60)).until(ExpectedConditions
					.presenceOfElementLocated(By.xpath("//IMG[@id='imgconfirmarmantMantenimientoFormEXTRACTOS']")));
			if (imgAceptar.isDisplayed()) {
				imgAceptar.click();
			} else {
				if (msg.equals("OK"))
					msg = "";

				msg = msg + "No se encontró el botón Aceptar \n";
				return msg;
			}

			if (FunctionGeneric.validaAlert(driver)) {
				driver.switchTo().alert().accept();
				Thread.sleep(3000);
				if (FunctionGeneric.validaAlert(driver)) {
					driver.switchTo().alert().accept();
				} else {
					robot.keyPress(KeyEvent.VK_ENTER);
				}
			}

			// img id imgsalirmantMantenimientoFormEXTRACTOS

			WebElement imgVolver = (new WebDriverWait(driver, 60)).until(ExpectedConditions
					.presenceOfElementLocated(By.xpath("//IMG[@id='imgsalirmantMantenimientoFormEXTRACTOS']")));
			if (imgVolver.isDisplayed()) {
				imgVolver.click();
			} else {
				if (msg.equals("OK"))
					msg = "";

				msg = msg + "No se encontró el botón Volver \n";
				return msg;
			}
		} catch (Exception e) {
			System.out.println("ERROR AL CONFIRMAR EL CAMBIO " + e.getMessage());
			msg = "ERROR AL CONFIRMAR EL CAMBIO " + e.getMessage();
		}
		return msg;
	}

	public static String validarMovimiento(WebDriver driver) {
		String msg = "OK";
		try {
			// INPUT name = MOVIMIENTOS
			WebElement btnMovimientos = (new WebDriverWait(driver, 60))
					.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//INPUT[@name='MOVIMIENTOS']")));
			if (btnMovimientos.isDisplayed()) {
				btnMovimientos.click();
			} else {
				if (msg.equals("OK"))
					msg = "";

				msg = msg + "No se encontró el botón Movimientos \n";
				return msg;
			}
		} catch (Exception e) {
			System.out.println("ERROR AL VALIDAR MOVIMIENTOS " + e.getMessage());
			msg = "ERROR AL VALIDAR MOVIMIENTOS " + e.getMessage();
		}
		return msg;
	}

}
