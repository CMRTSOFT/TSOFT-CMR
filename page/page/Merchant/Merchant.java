package page.Merchant;

import java.awt.Robot;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.Point;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import util.FunctionGeneric;
import util.KeyboardClass;

public class Merchant {
	FunctionGeneric func = new FunctionGeneric();
	static KeyboardClass key;

	public static String formBusquedaPAN(String numComercio, String pan, WebDriver driver) {
		String msg = "OK";
		try {
			Robot robot = new Robot();
			// CODCOMF
			WebElement txtComercio = (new WebDriverWait(driver, 60))
					.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//INPUT[@name='CODCOMF']")));
			// INPUT NAME PANF
			WebElement txtPan = (new WebDriverWait(driver, 60))
					.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//INPUT[@name='PANF']")));
			// Consultar
			WebElement btnConsultar = (new WebDriverWait(driver, 60))
					.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//INPUT[@value='Consultar']")));
			if (txtComercio.isDisplayed()) {
				txtComercio.sendKeys(numComercio);
				robot.keyPress(KeyEvent.VK_ENTER);

			} else {
				if (msg.equals("OK"))
					msg = "";

				msg = msg + "No se encontró el Campo comercio  \n";
				return msg;
			}

			if (txtPan.isDisplayed()) {
				txtPan.sendKeys(numComercio);
			} else {
				if (msg.equals("OK"))
					msg = "";

				msg = msg + "No se encontró el Campo PAN  \n";
				return msg;
			}

			if (btnConsultar.isDisplayed()) {
				btnConsultar.click();
			} else {
				if (msg.equals("OK"))
					msg = "";

				msg = msg + "No se encontró el botón Consultar \n";
				return msg;
			}

			WebElement lnkDatosAdicionales = (new WebDriverWait(driver, 25))
					.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//A[@name='LinkSolapaSOLAPA2']")));
			if (lnkDatosAdicionales.isDisplayed()) {
				lnkDatosAdicionales.click();
			} else {
				if (msg.equals("OK"))
					msg = "";

				msg = msg + "No se encontró el Link datos Adicionales \n";
				return msg;
			}

		} catch (Exception e) {
			System.out.println("ERROR EN LA BUSQUEDA DE RECHAZO MERCHANT " + e.getMessage());
			msg = "ERROR EN LA BUSQUEDA DE RECHAZO MERCHANT " + e.getMessage();
		}
		return msg;
	}

	/** MANTENIMIENTO MERCHANT ***/
	public static String formInsertMantMerchant(String oficina, String codComercio, String nomComercio, String rut,
			String retribucion, String numImport, String glosa, String ctaCargo, String tipoProveedor, String regimen,
			String moneda, WebDriver driver) {
		String msg = "OK";
		int index = 0;
		try {
			Robot robot = new Robot();
			key = new KeyboardClass();
			Actions action = new Actions(driver);

			WebElement FrameInterContrato = (new WebDriverWait(driver, 60))
					.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//IFRAME[@src='paginas/index.jsp']")));
			driver.switchTo().frame(FrameInterContrato);

			WebElement btnInsertar = (new WebDriverWait(driver, 60)).until(ExpectedConditions
					.presenceOfElementLocated(By.xpath("//IMG[@id='imgnuevomantMantenimientoFormMANTENIMIENTO']")));
			if (btnInsertar.isDisplayed()) {
				btnInsertar.click();
			} else {
				if (msg.equals("OK"))
					msg = "";

				msg = msg + "No se encontró el botón Insertar \n";
				return msg;
			}

			WebElement cboComer = (new WebDriverWait(driver, 60))
					.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//SELECT[@name='INDCONT']")));
			Select cboTipoComer = new Select(cboComer);

			if (cboComer.isDisplayed())
				index = FunctionGeneric.returnPositionDataCBO(cboComer, "CAPTADO");
			if (index >= 0) {
				cboTipoComer.selectByIndex(index);
			} else {
				msg = "No se encontró el valor en Combobox de COMERCIO \n";
				return msg;
			}

			if (FunctionGeneric.validaAlert(driver)) {
				driver.switchTo().alert().accept();
			}
			// INPUT NAME CODCOM
			WebElement txtCodComer = (new WebDriverWait(driver, 60))
					.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//INPUT[@name='CODCOM']")));

			// INPUT name NOMCOMRED
			WebElement txtNomComer = (new WebDriverWait(driver, 60))
					.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//INPUT[@name='NOMCOMRED']")));
			// INPUT name CENTALTA
			WebElement txtOficina = (new WebDriverWait(driver, 60))
					.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//INPUT[@name='CENTALTA']")));

			if (txtCodComer.isDisplayed()) {
				txtCodComer.sendKeys(codComercio);
			} else {
				if (msg.equals("OK"))
					msg = "";

				msg = msg + "No se encontró el Campo Codigo Comercio \n";
				return msg;
			}

			if (txtNomComer.isDisplayed()) {
				txtNomComer.sendKeys(nomComercio);
			} else {
				if (msg.equals("OK"))
					msg = "";

				msg = msg + "No se encontró el Campo Nombre Comercio \n";
				return msg;
			}
			if (txtOficina.isDisplayed()) {
				txtOficina.sendKeys(oficina);
			} else {
				if (msg.equals("OK"))
					msg = "";

				msg = msg + "No se encontró el Campo Oficina \n";
				return msg;
			}

			// a href javascript:SeleccionarPersona(FormMANTENIMIENTO,"S")
			WebElement btnCarpeta = (new WebDriverWait(driver, 60)).until(ExpectedConditions.presenceOfElementLocated(
					By.xpath("//A[@href='javascript:SeleccionarPersona(FormMANTENIMIENTO,\"S\")']")));
			// btnCarpeta.click();
			if (btnCarpeta.isDisplayed()) {
				btnCarpeta.click();
				key.KeyPressTecl("ENTER");
				robot.keyRelease(KeyEvent.VK_ENTER);
			} else {
				if (msg.equals("OK"))
					msg = "";

				msg = msg + "No se encontró la Imagen Carpeta Identidad Cliente \n";
				return msg;
			}

			if (!ventanaIdentidadMerchant(rut, driver)) {
				msg = "Hubo un erro en la ventana de identificación cliente \n";
				return msg;
			}
			// System.out.println(msg);

			/******* Datos Comercios ******/
			// SELECT name TIPCOMERCIO
			WebElement cboRetribucion = (new WebDriverWait(driver, 60))
					.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//SELECT[@name='TIPCOMERCIO']")));
			Select cboTipoRetribucion = new Select(cboRetribucion);

			if (cboRetribucion.isDisplayed())
				index = FunctionGeneric.returnPositionDataCBO(cboRetribucion, retribucion);
			if (index >= 0) {
				cboTipoRetribucion.selectByIndex(index);
			} else {
				msg = "No se encontró el valor en Combobox de TIPO RETRIBUCIÓN \n";
				return msg;
			}

			// javascript:BuscarProductoSubproducto(FormMANTENIMIENTO)

			WebElement btnCarpetaPro = (new WebDriverWait(driver, 60))
					.until(ExpectedConditions.presenceOfElementLocated(
							By.xpath("//A[@href='javascript:BuscarProductoSubproducto(FormMANTENIMIENTO)']")));
			if (btnCarpetaPro.isDisplayed()) {
				btnCarpetaPro.click();
				key.KeyPressTecl("ENTER");
				robot.keyRelease(KeyEvent.VK_ENTER);
			} else {
				if (msg.equals("OK"))
					msg = "";

				msg = msg + "No se encontró la Imagen Carpeta Producto \n";
				return msg;
			}

			if (!ventanaProductoMerchant(driver)) {
				msg = "Hubo un error en la ventana de producto-subproducto \n";
				return msg;
			}

			// javascript:BuscarCondicionEco(FormMANTENIMIENTO)
			WebElement btnCarpetaCondi = (new WebDriverWait(driver, 60))
					.until(ExpectedConditions.presenceOfElementLocated(
							By.xpath("//A[@href='javascript:BuscarCondicionEco(FormMANTENIMIENTO)']")));
			if (btnCarpetaCondi.isDisplayed()) {
				btnCarpetaCondi.click();
				key.KeyPressTecl("ENTER");
				robot.keyRelease(KeyEvent.VK_ENTER);
			} else {
				if (msg.equals("OK"))
					msg = "";

				msg = msg + "No se encontró la Imagen Carpeta Condición Economica \n";
				return msg;
			}
			if (!ventanaCondicionEconomica(driver)) {
				msg = "Hubo un error en la ventana de Condición Economica \n";
				return msg;
			}

			// INPUT name INDENVEXT
			WebElement chkImpresionDiaria = (new WebDriverWait(driver, 60))
					.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//INPUT[@name='INDENVEXT']")));
			if (chkImpresionDiaria.isDisplayed()) {
				if (!chkImpresionDiaria.isSelected()) {
					chkImpresionDiaria.click();
				}
			} else {
				if (msg.equals("OK"))
					msg = "";

				msg = msg + "No se encontró el Checkbox Impresión Diaria \n";
				return msg;
			}

			// INPUT name INDENVRCHEXT
			WebElement chkImprOpeRecha = (new WebDriverWait(driver, 60))
					.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//INPUT[@name='INDENVRCHEXT']")));
			if (chkImprOpeRecha.isDisplayed()) {
				if (!chkImprOpeRecha.isSelected()) {
					chkImprOpeRecha.click();
				}
			} else {
				if (msg.equals("OK"))
					msg = "";

				msg = msg + "No se encontró el Checkbox Operaciones Diarias \n";
				return msg;
			}

			// INPUT name INDINCRECH Incorporación a rechazos
			WebElement chkIncRechazo = (new WebDriverWait(driver, 60))
					.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//INPUT[@name='INDINCRECH']")));
			if (chkIncRechazo.isDisplayed()) {
				if (!chkIncRechazo.isSelected()) {
					chkIncRechazo.click();
				}
			} else {
				if (msg.equals("OK"))
					msg = "";

				msg = msg + "No se encontró el Checkbox Incorporación Rechazos \n";
				return msg;
			}

			// SELECT name NUMIMP
			WebElement cboNumImportancia = (new WebDriverWait(driver, 60))
					.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//SELECT[@name='NUMIMP']")));
			Select cboTipoNumImpor = new Select(cboNumImportancia);
			if (cboNumImportancia.isDisplayed())
				index = FunctionGeneric.returnPositionDataCBO(cboNumImportancia, numImport);
			if (index >= 0) {
				cboTipoNumImpor.selectByIndex(index);
			} else {
				msg = "No se encontró el valor en Combobox de Número de Importancia \n";
				return msg;
			}
			// INPUT name INDFID
			WebElement chkComercioFideli = (new WebDriverWait(driver, 60))
					.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//INPUT[@name='INDFID']")));
			if (chkComercioFideli.isDisplayed()) {
				if (!chkComercioFideli.isSelected()) {
					chkComercioFideli.click();
				}
			} else {
				if (msg.equals("OK"))
					msg = "";

				msg = msg + "No se encontró el Checkbox Comercio Fidelizable \n";
				return msg;
			}

			// INPUT name GLOSAEECC
			WebElement txtGlosaEECC = (new WebDriverWait(driver, 60))
					.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//INPUT[@name='GLOSAEECC']")));
			if (txtGlosaEECC.isDisplayed()) {
				txtGlosaEECC.click();
				key.type(glosa);
				// txtGlosaEECC.sendKeys(glosa);
			} else {
				if (msg.equals("OK"))
					msg = "";

				msg = msg + "No se encontró el Campo Glosa EECC \n";
				return msg;
			}

			/******* Datos Proveedor *******/
			// SELECT name TIPOPROV
			WebElement cboProveedor = (new WebDriverWait(driver, 60))
					.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//SELECT[@name='TIPOPROV']")));
			Select cboTipoProveedor = new Select(cboProveedor);
			if (cboProveedor.isDisplayed())
				index = FunctionGeneric.returnPositionDataCBO(cboProveedor, tipoProveedor);
			if (index >= 0) {
				cboTipoProveedor.selectByIndex(index);
			} else {
				msg = "No se encontró el valor en Combobox Tipo Proveedor \n";
				return msg;
			}
			// a href javascript:BuscarActividad(FormMANTENIMIENTO)
			WebElement btnCarpetaAct = (new WebDriverWait(driver, 60)).until(ExpectedConditions
					.presenceOfElementLocated(By.xpath("//A[@href='javascript:BuscarActividad(FormMANTENIMIENTO)']")));
			if (btnCarpetaAct.isDisplayed()) {
				btnCarpetaAct.click();
				key.KeyPressTecl("ENTER");
				robot.keyRelease(KeyEvent.VK_ENTER);
			} else {
				if (msg.equals("OK"))
					msg = "";

				msg = msg + "No se encontró la Imagen Carpeta Actividad \n";
				return msg;
			}

			if (!ventanaActividad(driver)) {
				msg = "Hubo un error en la ventana de Actividad \n";
				return msg;
			}

			// SELECT name CODREGIMEN
			WebElement cboRegimen = (new WebDriverWait(driver, 60))
					.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//SELECT[@name='CODREGIMEN']")));
			Select cboTipoRegimen = new Select(cboRegimen);
			if (cboRegimen.isDisplayed())
				index = FunctionGeneric.returnPositionDataCBO(cboRegimen, regimen);

			// System.out.println("index : " + index);
			if (index >= 0) {
				cboTipoRegimen.selectByIndex(index);
			} else {
				msg = "No se encontró el valor en Combobox Régimen \n";
				return msg;
			}
			// INPUT name CTACARGO
			WebElement txtCtaCargo = (new WebDriverWait(driver, 60))
					.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//INPUT[@name='CTACARGO']")));
			if (txtCtaCargo.isDisplayed()) {
				txtCtaCargo.click();
				key.type(ctaCargo);
				// txtCtaCargo.sendKeys(ctaCargo);
			} else {
				if (msg.equals("OK"))
					msg = "";

				msg = msg + "No se encontró el Campo Cuenta Cargo \n";
				return msg;
			}

			// INPUT name INDDATAUT
			WebElement chkIndAuto = (new WebDriverWait(driver, 60))
					.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//INPUT[@name='INDDATAUT']")));
			if (chkIndAuto.isDisplayed()) {
				if (!chkIndAuto.isSelected()) {
					chkIndAuto.click();
				}
			} else {
				if (msg.equals("OK"))
					msg = "";

				msg = msg + "No se encontró el Checkbox Datos Autorización \n";
				return msg;
			}
			// SELECT name CLAMON
			WebElement cboMoneda = (new WebDriverWait(driver, 60))
					.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//SELECT[@name='CLAMON']")));
			Select cboTipoMoneda = new Select(cboMoneda);
			if (cboMoneda.isDisplayed())
				index = FunctionGeneric.returnPositionDataCBO(cboMoneda, moneda);
			if (index >= 0) {
				cboTipoMoneda.selectByIndex(index);
			} else {
				msg = "No se encontró el valor en Combobox Moneda \n";
				return msg;
			}
			// SELECT name INDAJENA
			WebElement chkIndAjena = (new WebDriverWait(driver, 60))
					.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//INPUT[@name='INDAJENA']")));
			if (chkIndAjena.isDisplayed()) {
				if (!chkIndAjena.isSelected()) {
					chkIndAjena.click();
				}
			} else {
				if (msg.equals("OK"))
					msg = "";

				msg = msg + "No se encontró el Checkbox AJENA \n";
				return msg;
			}
			// a href javascript:BuscarEmisorProcesador(FormMANTENIMIENTO)
			WebElement btnCarpEmisor = (new WebDriverWait(driver, 60))
					.until(ExpectedConditions.presenceOfElementLocated(
							By.xpath("//A[@href='javascript:BuscarEmisorProcesador(FormMANTENIMIENTO)']")));
			if (btnCarpEmisor.isDisplayed()) {
				btnCarpEmisor.click();
				key.KeyPressTecl("ENTER");
				robot.keyRelease(KeyEvent.VK_ENTER);
			} else {
				if (msg.equals("OK"))
					msg = "";

				msg = msg + "No se encontró la Imagen Carpeta Emisor Procesador \n";
				return msg;
			}
			// ventana emisor Procesador
			if (!ventanaEmisor(driver)) {
				msg = "Hubo un error en la ventana de Emisor Procesador \n";
				return msg;
			}

		} catch (Exception e) {
			System.out.println("ERROR EN EL FORMULARIO DE MANTENIMIENTO MERCHANT " + e.getMessage());
			msg = "ERROR EN EL FORMULARIO DE MANTENIMIENTO MERCHANT " + e.getMessage();
		}
		return msg;
	}

	public static String popupAceptarMerchant(WebDriver driver) {
		String msg = "OK";
		try {
			// IMG id imgconfirmarmantMantenimientoFormMANTENIMIENTO}
			Thread.sleep(4000);
			WebElement btnAceptar = (new WebDriverWait(driver, 60)).until(ExpectedConditions
					.presenceOfElementLocated(By.xpath("//IMG[@id='imgconfirmarmantMantenimientoFormMANTENIMIENTO']")));
			if (btnAceptar.isDisplayed()) {
				btnAceptar.click();
			} else {
				if (msg.equals("OK"))
					msg = "";

				msg = msg + "No se encontró el botón Aceptar \n";
				return msg;
			}

			if (FunctionGeneric.validaAlert(driver)) {
				driver.switchTo().alert().accept();
				if (FunctionGeneric.validaAlert(driver)) {
					driver.switchTo().alert().accept();
				}
			}

		} catch (Exception e) {
			System.out.println("ERROR AL ACEPTAR ALERTAR DE MERCHANT " + e.getMessage());
			msg = "ERROR AL ACEPTAR ALERTAR DE MERCHANT " + e.getMessage();
		}
		return msg;
	}

	// Producto-Subproducto
	public static String validaMerchant(WebDriver driver) {
		String msg = "OK";
		try {
			if (!FunctionGeneric.validarTexto("Producto-Subproducto","Desplegar Pantalla Merchant", driver).equals("OK")) {
				msg = "No sé encontró el valor en la pantalla Merchant";
			}

		} catch (Exception e) {
			System.out.println("ERROR AL VALIDAR LA PANTALLA MERCHANT " + e.getMessage());
			msg = "ERROR AL VALIDAR LA PANTALLA MERCHANT " + e.getMessage();
		}
		return msg;
	}

	public static boolean ventanaIdentidadMerchant(String rut, WebDriver driver) {
		boolean flag = true;
		try {
			Set<String> winSet = driver.getWindowHandles();
			List<String> winList = new ArrayList<String>(winSet);
			if (!FunctionGeneric.waitWindows(2, winList, driver)) {
				flag = false;
				return flag;
			}
			Thread.sleep(3000);
			winSet = driver.getWindowHandles();
			winList = new ArrayList<String>(winSet);
			driver.switchTo().window(winList.get(winList.size() - 1));

			// INPUT name NUMDOC
			WebElement txtNumDoc = (new WebDriverWait(driver, 60))
					.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//INPUT[@name='NUMDOC']")));
			if (txtNumDoc.isDisplayed()) {
				txtNumDoc.sendKeys(rut);
			} else {
				flag = false;
				return flag;
			}
			// INPUT ID ACEPTAR
			WebElement btnAceptar = (new WebDriverWait(driver, 60))
					.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//INPUT[@id='ACEPTAR']")));
			if (btnAceptar.isDisplayed()) {
				btnAceptar.click();
			} else {
				flag = false;
				return flag;
			}
			// INPUT ID CargarDatos
			WebElement btnCargaDato = (new WebDriverWait(driver, 60))
					.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//INPUT[@id='CargarDatos']")));
			if (btnCargaDato.isDisplayed()) {
				btnCargaDato.click();
			} else {
				flag = false;
				return flag;
			}

			Thread.sleep(3000);
			winSet = driver.getWindowHandles();
			winList = new ArrayList<String>(winSet);
			if (!FunctionGeneric.waitWindows(1, winList, driver)) {
				flag = false;
				return flag;
			}
			Thread.sleep(3000);
			winSet = driver.getWindowHandles();
			winList = new ArrayList<String>(winSet);
			driver.switchTo().window(winList.get(winList.size() - 1));
			WebElement FrameInterContrato = (new WebDriverWait(driver, 60))
					.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//IFRAME[@src='paginas/index.jsp']")));
			driver.switchTo().frame(FrameInterContrato);

		} catch (Exception e) {
			System.out.println("ERROR EN LA VENTANA IDENTIFICACIÓN CLIENTE " + e.getMessage());
			flag = false;
		}
		return flag;
	}

	public static boolean ventanaProductoMerchant(WebDriver driver) {
		boolean flag = true;
		try {
			Set<String> winSet = driver.getWindowHandles();
			List<String> winList = new ArrayList<String>(winSet);
			if (!FunctionGeneric.waitWindows(2, winList, driver)) {
				flag = false;
				return flag;
			}
			Thread.sleep(3000);
			winSet = driver.getWindowHandles();
			winList = new ArrayList<String>(winSet);
			driver.switchTo().window(winList.get(winList.size() - 1));

			// System.out.println(driver.getPageSource());
			WebElement lnkComercioPropio = (new WebDriverWait(driver, 60))
					.until(ExpectedConditions.presenceOfElementLocated(By.linkText("COMERCIO PROPIO")));
			if (lnkComercioPropio.isDisplayed()) {
				lnkComercioPropio.click();
			} else {
				flag = false;
				return flag;
			}

			Thread.sleep(3000);
			winSet = driver.getWindowHandles();
			winList = new ArrayList<String>(winSet);
			if (!FunctionGeneric.waitWindows(1, winList, driver)) {
				flag = false;
				return flag;
			}
			winSet = driver.getWindowHandles();
			winList = new ArrayList<String>(winSet);
			driver.switchTo().window(winList.get(winList.size() - 1));
			WebElement FrameInterContrato = (new WebDriverWait(driver, 60))
					.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//IFRAME[@src='paginas/index.jsp']")));
			driver.switchTo().frame(FrameInterContrato);
		} catch (Exception e) {
			System.out.println("ERROR EN LA VENTANA PRODUCTO SUBPRODUCTO " + e.getMessage());
			flag = false;
		}
		return flag;
	}

	public static boolean ventanaCondicionEconomica(WebDriver driver) {
		boolean flag = true;
		try {
			Set<String> winSet = driver.getWindowHandles();
			List<String> winList = new ArrayList<String>(winSet);
			if (!FunctionGeneric.waitWindows(2, winList, driver)) {
				flag = false;
				return flag;
			}
			Thread.sleep(3000);
			winSet = driver.getWindowHandles();
			winList = new ArrayList<String>(winSet);
			driver.switchTo().window(winList.get(winList.size() - 1));

			// System.out.println(driver.getPageSource());
			WebElement lnkComercioPropio = (new WebDriverWait(driver, 60))
					.until(ExpectedConditions.presenceOfElementLocated(By.linkText("COMERCIO PROPIO SIN COMISION")));
			if (lnkComercioPropio.isDisplayed()) {
				lnkComercioPropio.click();
			} else {
				flag = false;
				return flag;
			}

			Thread.sleep(3000);
			winSet = driver.getWindowHandles();
			winList = new ArrayList<String>(winSet);
			if (!FunctionGeneric.waitWindows(1, winList, driver)) {
				flag = false;
				return flag;
			}
			winSet = driver.getWindowHandles();
			winList = new ArrayList<String>(winSet);
			driver.switchTo().window(winList.get(winList.size() - 1));
			WebElement FrameInterContrato = (new WebDriverWait(driver, 60))
					.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//IFRAME[@src='paginas/index.jsp']")));
			driver.switchTo().frame(FrameInterContrato);
		} catch (Exception e) {
			System.out.println("ERROR EN LA VENTANA CONDICIÓN ECONOMICA " + e.getMessage());
			flag = false;
		}
		return flag;
	}

	public static boolean ventanaActividad(WebDriver driver) {
		boolean flag = true;
		try {
			Set<String> winSet = driver.getWindowHandles();
			List<String> winList = new ArrayList<String>(winSet);
			if (!FunctionGeneric.waitWindows(2, winList, driver)) {
				flag = false;
				return flag;
			}
			Thread.sleep(3000);
			winSet = driver.getWindowHandles();
			winList = new ArrayList<String>(winSet);
			driver.switchTo().window(winList.get(winList.size() - 1));

			// System.out.println("Actividad "+driver.getPageSource());
			WebElement imgNext = (new WebDriverWait(driver, 60)).until(
					ExpectedConditions.presenceOfElementLocated(By.xpath("//IMG[@src='imagenes/siguiente.gif']")));
			if (imgNext.isDisplayed()) {
				imgNext.click();
			}

			for (int i = 0; i < 9; i++) {
				Thread.sleep(3000);
				imgNext = (new WebDriverWait(driver, 60)).until(
						ExpectedConditions.presenceOfElementLocated(By.xpath("//IMG[@src='imagenes/siguiente.gif']")));
				imgNext.click();
			}
			// FINANCIAL INST/MANUAL CASH
			WebElement lnkActividad = (new WebDriverWait(driver, 60))
					.until(ExpectedConditions.presenceOfElementLocated(By.linkText("FINANCIAL INST/MANUAL CASH")));
			if (lnkActividad.isDisplayed()) {
				lnkActividad.click();
			} else {
				flag = false;
				return flag;
			}
			Thread.sleep(3000);
			winSet = driver.getWindowHandles();
			winList = new ArrayList<String>(winSet);
			if (!FunctionGeneric.waitWindows(1, winList, driver)) {
				flag = false;
				return flag;
			}
			winSet = driver.getWindowHandles();
			winList = new ArrayList<String>(winSet);
			driver.switchTo().window(winList.get(winList.size() - 1));
			WebElement FrameInterContrato = (new WebDriverWait(driver, 60))
					.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//IFRAME[@src='paginas/index.jsp']")));
			driver.switchTo().frame(FrameInterContrato);
		} catch (Exception e) {
			System.out.println("ERROR EN LA VENTANA ACTIVIDAD " + e.getMessage());
		}
		return flag;
	}

	private static boolean ventanaEmisor(WebDriver driver) {
		boolean flag = true;
		try {
			Set<String> winSet = driver.getWindowHandles();
			List<String> winList = new ArrayList<String>(winSet);
			if (!FunctionGeneric.waitWindows(2, winList, driver)) {
				flag = false;
				return flag;
			}
			Thread.sleep(3000);
			winSet = driver.getWindowHandles();
			winList = new ArrayList<String>(winSet);
			driver.switchTo().window(winList.get(winList.size() - 1));

			// System.out.println("Emisor "+driver.getPageSource());
			WebElement lnkComercioPropio = (new WebDriverWait(driver, 60))
					.until(ExpectedConditions.presenceOfElementLocated(By.linkText("COMERCIOS PROPIOS")));
			if (lnkComercioPropio.isDisplayed()) {
				lnkComercioPropio.click();
			} else {
				flag = false;
				return flag;
			}

			Thread.sleep(3000);
			winSet = driver.getWindowHandles();
			winList = new ArrayList<String>(winSet);
			if (!FunctionGeneric.waitWindows(1, winList, driver)) {
				flag = false;
				return flag;
			}
			winSet = driver.getWindowHandles();
			winList = new ArrayList<String>(winSet);
			driver.switchTo().window(winList.get(winList.size() - 1));
			WebElement FrameInterContrato = (new WebDriverWait(driver, 60))
					.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//IFRAME[@src='paginas/index.jsp']")));
			driver.switchTo().frame(FrameInterContrato);
		} catch (Exception e) {
			System.out.println("ERROR EN LA VENTANA EMISOR PROCESADOR " + e.getMessage());
			flag = false;
		}
		return flag;
	}

	public static String busquedaComercio(WebDriver driver) {
		String msg = "OK";
		try {
			WebElement btnUpdate = (new WebDriverWait(driver, 60)).until(ExpectedConditions
					.presenceOfElementLocated(By.xpath("//IMG[@id='imgeditarmantMantenimientoFormMANTENIMIENTO']")));
		} catch (Exception e) {
			System.out.println("ERROR EN LA BUSQUEDA DE COMERCIO MODIFICACIÓN MERCHANT " + e.getMessage());
			msg = "ERROR EN LA BUSQUEDA DE COMERCIO MODIFICACIÓN MERCHANT " + e.getMessage();
		}
		return msg;
	}

	public static String formUpdateMantMerchant(String numComercio, String nomComercio, WebDriver driver) {
		String msg = "OK";
		try {
			Robot robot = new Robot();
			Actions action = new Actions(driver);
			WebElement FrameInterContrato = (new WebDriverWait(driver, 60))
					.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//IFRAME[@src='paginas/index.jsp']")));

			driver.switchTo().frame(FrameInterContrato);
			// IMG id imgeditarmantMantenimientoFormMANTENIMIENTO
			WebElement rdoComercio = (new WebDriverWait(driver, 60))
					.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//INPUT[@value='Conmercio']")));
			if (rdoComercio.isDisplayed()) {
				action.doubleClick(rdoComercio).perform();
			} else {
				if (msg.equals("OK"))
					msg = "";

				msg = msg + "No se encontró el botón Modificar \n";
				return msg;
			}
			// CODCOM

			WebElement txtComercio = (new WebDriverWait(driver, 60))
					.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//INPUT[@name='CODCOM']")));

			WebElement btnConsultar = (new WebDriverWait(driver, 60))
					.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//INPUT[@value='Consultar']")));
			// IMG id = imgeditarmantMantenimientoFormMANTENIMIENTO

			if (txtComercio.isDisplayed()) {
				txtComercio.sendKeys(numComercio);
			} else {
				if (msg.equals("OK"))
					msg = "";

				msg = msg + "No se encontró el campo Comercio \n";
				return msg;
			}
			if (btnConsultar.isDisplayed()) {
				btnConsultar.click();
			} else {
				if (msg.equals("OK"))
					msg = "";

				msg = msg + "No se encontró el Botón Consultar \n";
				return msg;
			}

			WebElement btnModificar = (new WebDriverWait(driver, 60)).until(ExpectedConditions
					.presenceOfElementLocated(By.xpath("//IMG[@id='imgeditarmantMantenimientoFormMANTENIMIENTO']")));
			if (btnModificar.isDisplayed()) {
				btnModificar.click();
			} else {
				if (msg.equals("OK"))
					msg = "";

				msg = msg + "No se encontró el Botón Modificar \n";
				return msg;
			} //
				// INPUT name = NOMCOMRED

			WebElement txtNomComer = (new WebDriverWait(driver, 60))
					.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//INPUT[@name='NOMCOMRED']")));
			Thread.sleep(2000);
			if (txtNomComer.isDisplayed()) {
				FunctionGeneric.deleteAllText(txtNomComer);
				Thread.sleep(2000);
				txtNomComer.sendKeys(nomComercio);
			} else {
				if (msg.equals("OK"))
					msg = "";

				msg = msg + "No se encontró el Campo Nombre comercio \n";
				return msg;
			}

			// IMG id imgconfirmarmantMantenimientoFormMANTENIMIENTO
			WebElement btnAceptar = (new WebDriverWait(driver, 60)).until(ExpectedConditions
					.presenceOfElementLocated(By.xpath("//IMG[@id='imgconfirmarmantMantenimientoFormMANTENIMIENTO']")));
			if (btnAceptar.isDisplayed()) {
				btnAceptar.click();
			} else {
				if (msg.equals("OK"))
					msg = "";

				msg = msg + "No se encontró el Botón Aceptar \n";
				return msg;
			}

			// *** Intento de localizar X en TextBox ***//
			/*
			 * txtComercio.sendKeys(numComercio); robot.keyPress(KeyEvent.VK_TAB);
			 * robot.keyRelease(KeyEvent.VK_TAB); Point point = txtComercio.getLocation();
			 * int alto = txtComercio.getSize().getHeight(); int ancho =
			 * txtComercio.getSize().getWidth(); System.out.println("Alto : " + alto);
			 * System.out.println("Ancho : " + ancho); System.out.println("x : " +
			 * point.getX()); System.out.println("y : " + point.getY()); //
			 * action.click(txtComercio).moveByOffset(xOffset, yOffset) Thread.sleep(2000);
			 */
			// action.moveToElement(txtComercio).moveByOffset(point.getX() + 30,
			// point.getY() + 30).click().perform();
			// action.moveToElement(txtComercio).moveByOffset((ancho / 2) + 50,
			// 0).click().perform();
			/*
			 * WebElement body = (new WebDriverWait(driver, 60))
			 * .until(ExpectedConditions.presenceOfElementLocated(By.tagName("body")));
			 * 
			 * action.moveToElement(body).moveByOffset(point.getX(),
			 * point.getY()).click().build().perform();
			 */
			// Input value Consultar
			/*
			 * WebElement btnConsultar = (new WebDriverWait(driver, 60))
			 * .until(ExpectedConditions.presenceOfElementLocated(By.xpath(
			 * "//INPUT[@value='Consultar']"))); //IMG id =
			 * imgeditarmantMantenimientoFormMANTENIMIENTO WebElement btnModificar = (new
			 * WebDriverWait(driver, 60))
			 * .until(ExpectedConditions.presenceOfElementLocated(By.xpath(
			 * "//IMG[@id='imgeditarmantMantenimientoFormMANTENIMIENTO']")));
			 * 
			 * if (txtComercio.isDisplayed()) { txtComercio.sendKeys(numComercio); } else {
			 * if (msg.equals("OK")) msg = "";
			 * 
			 * msg = msg + "No se encontró el campo Comercio \n"; return msg; } if
			 * (btnConsultar.isDisplayed()) { btnConsultar.click(); } else { if
			 * (msg.equals("OK")) msg = "";
			 * 
			 * msg = msg + "No se encontró el Botón Consultar \n"; return msg; } if
			 * (btnModificar.isDisplayed()) { btnModificar.click(); } else { if
			 * (msg.equals("OK")) msg = "";
			 * 
			 * msg = msg + "No se encontró el Botón Modificar \n"; return msg; } //
			 * INPUT name = NOMCOMRED
			 * 
			 * WebElement txtNomComer = (new WebDriverWait(driver, 60))
			 * .until(ExpectedConditions.presenceOfElementLocated(By.xpath(
			 * "//INPUT[@name='NOMCOMRED']")));
			 * 
			 * if (txtNomComer.isDisplayed()) { txtNomComer.sendKeys(nomComercio); } else {
			 * if (msg.equals("OK")) msg = "";
			 * 
			 * msg = msg + "No se encontró el Campo Nombre comercio \n"; return msg; }
			 */

		} catch (Exception e) {
			System.out.println("ERROR EN LA FORMULARIO MODIFICACIÓN " + e.getMessage());
			msg = "ERROR EN LA FORMULARIO MODIFICACIÓN " + e.getMessage();
		}
		return msg;
	}

	public static String acceptUpdateMerchant(WebDriver driver) {
		String msg = "OK";
		try {
			Robot robot = new Robot();
			if (FunctionGeneric.validaAlert(driver)) {
				driver.switchTo().alert().accept();
				Thread.sleep(2000);
				if (FunctionGeneric.validaAlert(driver)) {
					driver.switchTo().alert().accept();
				} else {
					robot.keyPress(KeyEvent.VK_ENTER);
					robot.keyRelease(KeyEvent.VK_ENTER);
				}

				if (!FunctionGeneric.validarTexto("Producto-Subproducto","Desplegar Pantalla Merchant" ,driver).equals("OK")) {
					msg = "No sé completo el flujo de manera correcta";
					return msg;
				}
			}
		} catch (Exception e) {
			System.out.println("ERROR AL ACEPTAR MODIFICACIÓN DE MERCHANT " + e.getMessage());
			msg = "ERROR AL ACEPTAR MODIFICACIÓN DE MERCHANT " + e.getMessage();
		}

		return msg;
	}

	/**** Eliminar Merchant ****/
	public static String formDeleteMerchant(String numComercio,String motivoBaja, WebDriver driver) {
		String msg = "OK";
		int index = 0;
		try {
			// IMG id imgborrarmantMantenimientoFormMANTENIMIENTO
			Robot robot = new Robot();
			Actions action = new Actions(driver);
			WebElement FrameInterContrato = (new WebDriverWait(driver, 25))
					.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//IFRAME[@src='paginas/index.jsp']")));

			driver.switchTo().frame(FrameInterContrato);
			// IMG id imgeditarmantMantenimientoFormMANTENIMIENTO
			WebElement rdoComercio = (new WebDriverWait(driver, 60))
					.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//INPUT[@value='Conmercio']")));
			if (rdoComercio.isDisplayed()) {
				action.doubleClick(rdoComercio).perform();
			} else {
				if (msg.equals("OK"))
					msg = "";

				msg = msg + "No se encontró el botón Modificar \n";
				return msg;
			}
			// CODCOM

			WebElement txtComercio = (new WebDriverWait(driver, 60))
					.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//INPUT[@name='CODCOM']")));

			WebElement btnConsultar = (new WebDriverWait(driver, 60))
					.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//INPUT[@value='Consultar']")));
			// IMG id = imgeditarmantMantenimientoFormMANTENIMIENTO

			if (txtComercio.isDisplayed()) {
				txtComercio.sendKeys(numComercio);
			} else {
				if (msg.equals("OK"))
					msg = "";

				msg = msg + "No se encontró el campo Comercio \n";
				return msg;
			}
			if (btnConsultar.isDisplayed()) {
				btnConsultar.click();
			} else {
				if (msg.equals("OK"))
					msg = "";

				msg = msg + "No se encontró el Botón Consultar \n";
				return msg;
			}

			WebElement btnEliminar = (new WebDriverWait(driver, 60)).until(ExpectedConditions
					.presenceOfElementLocated(By.xpath("//IMG[@id='imgborrarmantMantenimientoFormMANTENIMIENTO']")));
			if (btnEliminar.isDisplayed()) {
				btnEliminar.click();
			} else {
				if (msg.equals("OK"))
					msg = "";

				msg = msg + "No se encontró el Botón Eliminar \n";
				return msg;
			}
			
			//a name LinkSolapaSOLAPA2
			WebElement lnkOtrosDatos = (new WebDriverWait(driver, 60)).until(ExpectedConditions
					.presenceOfElementLocated(By.xpath("//A[@name='LinkSolapaSOLAPA2']")));
			if (lnkOtrosDatos.isDisplayed()) {
				lnkOtrosDatos.click();
			} else {
				if (msg.equals("OK"))
					msg = "";

				msg = msg + "No se encontró el Botón Eliminar \n";
				return msg;
			}
			
			// SELECT name MOTBAJA
			WebElement cboMotivoBaja = (new WebDriverWait(driver, 60))
					.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//SELECT[@name='MOTBAJA']")));
			Select cboTipoBaja = new Select(cboMotivoBaja);
			if (cboMotivoBaja.isDisplayed())
				index = FunctionGeneric.returnPositionDataCBO(cboMotivoBaja, motivoBaja);
			if (index >= 0) {
				cboTipoBaja.selectByIndex(index);
			} else {
				msg = "No se encontró el valor en Combobox Motivo Baja \n";
				return msg;
			}

		} catch (Exception e) {
			System.out.println("ERROR AL ELIMINAR UN COMERCIO MERCHANT " + e.getMessage());
			msg = "ERROR AL ELIMINAR UN COMERCIO MERCHANT " + e.getMessage();
		}
		return msg;
	}
	
	public static String confirmDeleteMerchant(WebDriver driver) {
		String msg = "OK";
		try {
			//IMG id imgconfirmarmantMantenimientoFormMANTENIMIENTO
			WebElement btnAceptar = (new WebDriverWait(driver, 60)).until(ExpectedConditions
					.presenceOfElementLocated(By.xpath("//IMG[@id='imgconfirmarmantMantenimientoFormMANTENIMIENTO']")));
			if (btnAceptar.isDisplayed()) {
				btnAceptar.click();
			} else {
				if (msg.equals("OK"))
					msg = "";

				msg = msg + "No se encontró el Botón Aceptar \n";
				return msg;
			} 
			
			if (FunctionGeneric.validaAlert(driver)) {
				driver.switchTo().alert().accept();
				Thread.sleep(2000);
				if (FunctionGeneric.validaAlert(driver)) {
					driver.switchTo().alert().accept();
				}
			}
		} catch (Exception e) {
			System.out.println("ERROR AL VALIDAR ELIMINACIÓN MERCHANT "+e.getMessage());
			msg = "ERROR AL VALIDAR ELIMINACIÓN MERCHANT "+e.getMessage();
		}
		return msg ;
	}
	
	public static String rechazoMerchant(String numComercio, String pan,WebDriver driver) {
		String msg = "OK";
		try {
			WebElement FrameInterContrato = (new WebDriverWait(driver, 25))
					.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//IFRAME[@src='paginas/index.jsp']")));
			driver.switchTo().frame(FrameInterContrato);
			WebElement txtNumComercio = (new WebDriverWait(driver, 25))
					.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//INPUT[@name='CODCOMF']")));
			
			WebElement txtPan = (new WebDriverWait(driver, 25))
					.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//INPUT[@name='PANF']")));
			
			WebElement btnConsultar = (new WebDriverWait(driver, 25))
					.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//INPUT[@value='Consultar']")));
			// value Consultar
			
			if (txtNumComercio.isDisplayed()) {
				txtNumComercio.sendKeys(numComercio);
			} else {
				if (msg.equals("OK"))
					msg = "";

				msg = msg + "No se encontró el Campo Número comercio \n";
				return msg;
			}
			
			if (txtPan.isDisplayed()) {
				txtPan.sendKeys(pan);
			} else {
				if (msg.equals("OK"))
					msg = "";

				msg = msg + "No se encontró el Campo PAN \n";
				return msg;
			}
			if (btnConsultar.isDisplayed()) {
				btnConsultar.click();
			} else {
				if (msg.equals("OK"))
					msg = "";

				msg = msg + "No se encontró el Botón Consultar. \n";
				return msg;
			}
					
			WebElement table = (new WebDriverWait(driver, 25))
					.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//TABLE[@id='Listado_ListRechazosMerchant']")));
				
			List<WebElement> lnkRechazos =table.findElements(By.tagName("a")); 
			if (lnkRechazos.size() != 0) {
				lnkRechazos.get(0).click();
			}else {
				if (msg.equals("OK"))
					msg = "";

				msg = msg + "No se encontraron Rechazos en Merchant \n";
				return msg;
			}
			
			WebElement pestañaAdi = (new WebDriverWait(driver, 25))
					.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//a[@name='LinkSolapaSOLAPA2']")));
			if (pestañaAdi.isDisplayed()) {
				pestañaAdi.click();
			}else {
				if (msg.equals("OK"))
					msg = "";

				msg = msg + "No se encontró Pestaña \n";
				return msg;
			}
			//input name=SECOPE
			WebElement secuOperacion = (new WebDriverWait(driver, 25))
					.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//INPUT[@name='SECOPE']")));
			if (secuOperacion.isDisplayed()) {
				secuOperacion.getText();
			}else {
				if (msg.equals("OK"))
					msg = "";

				msg = msg + "No se encontró Campo Secu. Operación  \n";
				return msg;
			}
			
		} catch (Exception e) {
			System.out.println("ERROR EN RECHAZO MERCHANT " + e.getMessage());
			msg = "ERROR EN RECHAZO MERCHANT " + e.getMessage();
		}
		return msg;
	}
}
