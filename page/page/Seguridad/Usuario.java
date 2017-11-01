package page.Seguridad;

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

public class Usuario {

	public static String FormBusquedaUsuario(String usuario, WebDriver driver) {

		String msg = "OK";

		try {

			driver.switchTo().frame((new WebDriverWait(driver, 30)).until(
					ExpectedConditions.presenceOfElementLocated(By.xpath("//IFRAME[@src='paginas/index.jsp']"))));

			Actions action = new Actions(driver);
			if (driver.findElements(By.xpath("//INPUT[@name='usuarios']")).size() == 0) {
				msg = "No encontró Radio Button de Usuario ";
				return msg;
			}

			WebElement rdoUsuario = (new WebDriverWait(driver, 60))
					.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//INPUT[@value='PorUsuario']")));
			// bBuscar
			WebElement btnBuscar = (new WebDriverWait(driver, 60))
					.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//INPUT[@name='bBuscar']")));

			if (rdoUsuario.isDisplayed()) {
				// rdoUsuario.submit();
				action.doubleClick(rdoUsuario).perform();
			} else {
				if (msg.equals("OK"))
					msg = "";

				msg = msg + "No se encontró el Radio Button Usuario \n";
				return msg;
			}
			// USUARIOF
			WebElement txtUsuario = (new WebDriverWait(driver, 60))
					.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//INPUT[@name='USUARIOF']")));
			if (txtUsuario.isDisplayed()) {
				txtUsuario.sendKeys(usuario.toUpperCase().trim());
			} else {
				if (msg.equals("OK"))
					msg = "";

				msg = msg + "No se encontró el Campo Usuario \n";
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
			System.out.println("ERROR BUSCANDO USUARIOS EN FORMULARIO " + e.getMessage());
			msg = "ERROR BUSCANDO USUARIOS EN FORMULARIO " + e.getMessage();
		}
		return msg;
	}

	public static String updateDataUser(String nomPerfil, WebDriver driver) {
		String msg = "OK";
		int index = 0;
		try {
			WebElement btnModificar = (new WebDriverWait(driver, 60))
					.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//IMG[@title='Modificar (F8)']")));

			// SELECT name = CODPERFIL
			WebElement cboPerfil = (new WebDriverWait(driver, 60))
					.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//SELECT[@name='CODPERFIL']")));
			Select cboTipoPerfil = new Select(cboPerfil);

			// = driver.findElement(By.xpath("//SELECT[@name='CODPERFIL']"));
			// BtnAceptar img title=Confirmar (F10)
			WebElement btnAceptar = (new WebDriverWait(driver, 60))
					.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//IMG[@title='Confirmar (F10)']")));
			if (btnModificar.isDisplayed()) {
				btnModificar.click();
			} else {
				if (msg.equals("OK"))
					msg = "";

				msg = msg + "No se encontró el Botón Modificar \n";
				return msg;
			}
			if (cboPerfil.isDisplayed())
				index = FunctionGeneric.returnPositionDataCBO(cboPerfil, nomPerfil);
			if (index >= 0) {
				cboTipoPerfil.selectByIndex(index);
			} else {
				msg = "No se encontró el valor en Combobox de PERFIL \n";
				return msg;
			}

			if (btnAceptar.isDisplayed()) {
				btnAceptar.click();
			} else {
				if (msg.equals("OK"))
					msg = "";

				msg = msg + "No se encontró el Botón Aceptar \n";
				return msg;
			}

			if (FunctionGeneric.validaAlert(driver)) {
				if (driver.switchTo().alert().getText().contains("¿Desea modificar")) {
					driver.switchTo().alert().accept();
				} else {
					msg = msg + "Se produjo error al modificar Usuario \n";
					return msg;
				}
			}
			Thread.sleep(2000);
			driver.switchTo().alert().accept();

		} catch (Exception e) {
			System.out.println("ERROR AL MODIFICAR DATOS DE USUARIO " + e.getMessage());
			msg = "ERROR AL MODIFICAR DATOS DE USUARIO " + e.getMessage();
		}
		return msg;
	}

	public static String formInsertDataUser(String doc, String perfil, String perfilFidelizacion, String password,
			String usuario, String numDoc, WebDriver driver) {
		String msg = "OK";
		int index = 0;
		try {
			Robot robot = new Robot();
			Set<String> winSet = driver.getWindowHandles();
			List<String> winList = new ArrayList<String>(winSet);
			WebElement FrameInterContrato = (new WebDriverWait(driver, 60))
					.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//IFRAME[@src='paginas/index.jsp']")));
			driver.switchTo().frame(FrameInterContrato);
			WebElement btnInsertar = (new WebDriverWait(driver, 60)).until(ExpectedConditions
					.presenceOfElementLocated(By.xpath("//IMG[@id='imgnuevomantMantenimientoFormUSUARIOS']")));

			if (btnInsertar.isDisplayed()) {
				btnInsertar.click();
			} else {
				if (msg.equals("OK"))
					msg = "";

				msg = msg + "No se encontró el Botón Insertar \n";
				return msg;
			}
			WebElement cboDoc = (new WebDriverWait(driver, 60))
					.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//SELECT[@name='TIPDOCAUX1']")));
			Select cboTipoDoc = new Select(cboDoc);
			WebElement cboPerfil = (new WebDriverWait(driver, 60))
					.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//SELECT[@name='CODPERFIL']")));
			Select cboTipoPerfil = new Select(driver.findElement(By.xpath("//SELECT[@name='CODPERFIL']")));

			// USUARIOCMR
			WebElement txtUserCMR = (new WebDriverWait(driver, 60))
					.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//INPUT[@name='USUARIOCMR']")));

			// NUMDOC
			WebElement txtNumDoc = (new WebDriverWait(driver, 60))
					.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//INPUT[@name='NUMDOC']")));

			WebElement btnCarpetaCone = (new WebDriverWait(driver, 60))
					.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//IMG[@name='PAGLIS']")));

			if (cboDoc.isDisplayed())
				index = FunctionGeneric.returnPositionDataCBO(cboDoc, doc);
			if (index >= 0) {
				cboTipoDoc.selectByIndex(index);
			} else {
				msg = "No se encontró el valor en Combobox de TIPO DOCUMENTO \n";
				return msg;
			}

			if (txtUserCMR.isDisplayed()) {
				txtUserCMR.sendKeys(usuario.toUpperCase());
			} else {
				if (msg.equals("OK"))
					msg = "";

				msg = msg + "No se encontró el Campo Usuario CMR \n";
				return msg;
			}

			if (txtNumDoc.isDisplayed()) {
				txtNumDoc.sendKeys(numDoc);
			} else {
				if (msg.equals("OK"))
					msg = "";

				msg = msg + "No se encontró el Campo Número Documento CMR \n";
				return msg;
			}

			if (cboPerfil.isDisplayed())
				index = FunctionGeneric.returnPositionDataCBO(cboPerfil, perfil);
			if (index >= 0) {
				cboTipoPerfil.selectByIndex(index);
			} else {
				msg = "No se encontró el valor en Combobox de PERFIL \n";
				return msg;
			}

			if (btnCarpetaCone.isDisplayed()) {
				btnCarpetaCone.click();
			} else {
				if (msg.equals("OK"))
					msg = "";

				msg = msg + "No se encontró el Botón carpeta \n";
				return msg;
			}

			if (!formWindowPerfil(perfil, driver)) {
				msg = msg + "No se seleccionó perfil en Ventana \n";
				return msg;
			}

			if (!FunctionGeneric.waitWindows(1, winList, driver)) {
				msg = msg + "No se encontró Ventana Principal \n";
				return msg;
			}
			Thread.sleep(3000);
			winSet = driver.getWindowHandles();
			winList = new ArrayList<String>(winSet);
			driver.switchTo().window(winList.get(winList.size() - 1));

			FrameInterContrato = (new WebDriverWait(driver, 60))
					.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//IFRAME[@src='paginas/index.jsp']")));
			driver.switchTo().frame(FrameInterContrato);

			List<WebElement> lnkCarpeta = (new WebDriverWait(driver, 60))
					.until(ExpectedConditions.presenceOfAllElementsLocatedBy((By.xpath("//a[@id='CARPETA_CLIENTE']"))));

			if (lnkCarpeta.size() != 0) {
				lnkCarpeta.get(0).click();
				Thread.sleep(1000);
				robot.keyPress(KeyEvent.VK_ENTER);
			}

			if (!formWindowOficina(driver)) {
				msg = msg + "No se seleccionó Oficina en Ventana \n";
				return msg;
			}
			if (!FunctionGeneric.waitWindows(1, winList, driver)) {
				msg = msg + "No se encontró Ventana Principal \n";
				return msg;
			}
			Thread.sleep(3000);
			winSet = driver.getWindowHandles();
			winList = new ArrayList<String>(winSet);
			driver.switchTo().window(winList.get(winList.size() - 1));

			FrameInterContrato = (new WebDriverWait(driver, 60))
					.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//IFRAME[@src='paginas/index.jsp']")));
			driver.switchTo().frame(FrameInterContrato);
			lnkCarpeta = (new WebDriverWait(driver, 60))
					.until(ExpectedConditions.presenceOfAllElementsLocatedBy((By.xpath("//a[@id='CARPETA_CLIENTE']"))));

			if (lnkCarpeta.size() != 0) {
				lnkCarpeta.get(1).click();

				Thread.sleep(1000);
				robot.keyPress(KeyEvent.VK_ENTER);
			}
			if (!formWindowOficina(driver)) {
				msg = msg + "No se seleccionó Oficina en Ventana \n";
				return msg;
			}

			if (!FunctionGeneric.waitWindows(1, winList, driver)) {
				msg = msg + "No se encontró Ventana Principal \n";
				return msg;
			}
			Thread.sleep(3000);
			winSet = driver.getWindowHandles();
			winList = new ArrayList<String>(winSet);
			driver.switchTo().window(winList.get(winList.size() - 1));

			FrameInterContrato = (new WebDriverWait(driver, 60))
					.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//IFRAME[@src='paginas/index.jsp']")));
			driver.switchTo().frame(FrameInterContrato);

			WebElement cboEstado = (new WebDriverWait(driver, 60))
					.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//SELECT[@name='ESTADOUSU']")));
			Select cboTipoEst = new Select(driver.findElement(By.xpath("//SELECT[@name='ESTADOUSU']")));
			if (cboEstado.isDisplayed())
				index = FunctionGeneric.returnPositionDataCBO(cboEstado, "ACTIVO");
			if (index >= 0) {
				cboTipoEst.selectByIndex(index);
			} else {
				msg = "No se encontró el valor en Combobox de TIPO DOCUMENTO \n";
				return msg;
			}

			WebElement cboPerFidel = (new WebDriverWait(driver, 60))
					.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//SELECT[@name='CODPERF_FIDE']")));
			Select cboTipoPerFidel = new Select(driver.findElement(By.xpath("//SELECT[@name='CODPERF_FIDE']")));
			if (cboPerFidel.isDisplayed())
				index = FunctionGeneric.returnPositionDataCBO(cboPerFidel, perfilFidelizacion);
			if (index >= 0) {
				cboTipoPerFidel.selectByIndex(index);
			} else {
				msg = "No se encontró el valor en Combobox de TIPO DOCUMENTO \n";
				return msg;
			}
			WebElement txtPassWord = (new WebDriverWait(driver, 60))
					.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//INPUT[@name='MI_PASSWORD']")));
			if (txtPassWord.isDisplayed()) {
				txtPassWord.sendKeys(password.toUpperCase());
			} else {
				if (msg.equals("OK"))
					msg = "";

				msg = msg + "No se encontró el Campo PAssword \n";
				return msg;
			}

			txtPassWord = (new WebDriverWait(driver, 25))
					.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//INPUT[@name='MI_CONFIRMACION']")));
			if (txtPassWord.isDisplayed()) {
				txtPassWord.sendKeys(password.toUpperCase());
			} else {
				if (msg.equals("OK"))
					msg = "";

				msg = msg + "No se encontró el Campo Confirmación PAssword \n";
				return msg;
			}

			// Confirmar (F10)
			WebElement btnAceptar = (new WebDriverWait(driver, 60))
					.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//IMG[@title='Confirmar (F10)']")));
			if (btnAceptar.isDisplayed()) {
				btnAceptar.click();
			} else {
				if (msg.equals("OK"))
					msg = "";

				msg = msg + "No se encontró el Botón Aceptar \n";
				return msg;
			}

			driver.switchTo().alert().accept();
			Thread.sleep(5000);
			driver.switchTo().alert().accept();

		} catch (Exception e) {
			System.out.println("ERROR AL INSERTAR USUARIO " + e.getMessage());
			msg = "ERROR AL INSERTAR USUARIO " + e.getMessage();
		}
		return msg;
	}

	private static boolean formWindowPerfil(String perfil, WebDriver driver) {
		boolean flag = true;
		try {
			Thread.sleep(2000);
			Set<String> winSet = driver.getWindowHandles();
			List<String> winList = new ArrayList<String>(winSet);
			if (!FunctionGeneric.waitWindows(2, winList, driver)) {
				// msg = msg + "No se encontró el ventana de menú \n";
				flag = false;
				return flag;
			}
			winSet = driver.getWindowHandles();
			winList = new ArrayList<String>(winSet);
			driver.switchTo().window(winList.get(winList.size() - 1));

			List<WebElement> lnkPerfil = (new WebDriverWait(driver, 60)).until(ExpectedConditions
					.presenceOfAllElementsLocatedBy((By.xpath("//A[contains(text(), '" + perfil + "')]"))));

			if (lnkPerfil.size() > 0) {
				lnkPerfil.get(0).click();
			} else {
				flag = false;
				return flag;
			}

		} catch (Exception e) {
			System.out.println("ERROR EN EL FORMULARIO VENTANA PERFIL " + e.getMessage());
			// msg = "ERROR EN EL FORMULARIO VENTANA PERFIL "+e.toString();
			flag = false;
		}
		return flag;
	}

	private static boolean formWindowOficina(WebDriver driver) {
		boolean flag = true;
		try {

			Thread.sleep(3000);
			Set<String> winSet = driver.getWindowHandles();
			List<String> winList = new ArrayList<String>(winSet);
			if (!FunctionGeneric.waitWindows(2, winList, driver)) {
				flag = false;
				return flag;
			}
			winSet = driver.getWindowHandles();
			winList = new ArrayList<String>(winSet);
			driver.switchTo().window(winList.get(winList.size() - 1));

			List<WebElement> lnkOficina = (new WebDriverWait(driver, 60)).until(
					ExpectedConditions.presenceOfAllElementsLocatedBy((By.xpath("//A[contains(text(), 'AHUMADA')]"))));

			if (lnkOficina.size() > 0) {
				// System.out.println("Cantidad Link Oficina " + lnkOficina.size());
				lnkOficina.get(0).click();
			} else {
				flag = false;
				return flag;
			}

		} catch (Exception e) {
			System.out.println("ERROR EN EL FORMULARIO VENTANA OFICINA " + e.getMessage());
		}
		return flag;
	}

	public static String activeUserNew(WebDriver driver) {
		String msg = "OK";
		try {
			// input name= ACTIVAR
			WebElement btnActivar = (new WebDriverWait(driver, 60))
					.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//INPUT[@name='ACTIVAR']")));

			if (btnActivar.isDisplayed()) {
				btnActivar.click();
			} else {
				if (msg.equals("OK"))
					msg = "";

				msg = msg + "No se encontró el Botón Activar \n";
				return msg;
			}

			Thread.sleep(3000);
			Set<String> winSet = driver.getWindowHandles();
			List<String> winList = new ArrayList<String>(winSet);
			if (!FunctionGeneric.waitWindows(2, winList, driver)) {
				return msg;
			}
			winSet = driver.getWindowHandles();
			winList = new ArrayList<String>(winSet);
			driver.switchTo().window(winList.get(winList.size() - 1));
			// INPUT value Aceptar
			WebElement btnAceptar = (new WebDriverWait(driver, 60))
					.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//INPUT[@value='Aceptar']")));
			if (btnAceptar.isDisplayed()) {
				btnAceptar.click();
			} else {
				if (msg.equals("OK"))
					msg = "";

				msg = msg + "No se encontró el Botón Aceptar \n";
				return msg;
			}
		} catch (Exception e) {
			System.out.println("ERROR AL ACTIVAR USUARIO NUEVO " + e.getMessage());
			msg = "ERROR AL ACTIVAR USUARIO NUEVO " + e.getMessage();
		}
		return msg;
	}

	public static String FormSearchUser(String usuario, WebDriver driver) {
		String msg = "OK";
		try {
			Actions action = new Actions(driver);
			if (driver.findElements(By.xpath("//INPUT[@name='usuarios']")).size() == 0) {
				msg = "No encontró Radio Button de Usuario ";
				return msg;
			}

			WebElement rdoUsuario = (new WebDriverWait(driver, 60))
					.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//INPUT[@value='PorUsuario']")));
			// bBuscar
			WebElement btnBuscar = (new WebDriverWait(driver, 60))
					.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//INPUT[@name='bBuscar']")));

			if (rdoUsuario.isDisplayed()) {
				action.doubleClick(rdoUsuario).perform();
			} else {
				if (msg.equals("OK"))
					msg = "";

				msg = msg + "No se encontró el Radio Button Usuario \n";
				return msg;
			}
			// USUARIOF
			WebElement txtUsuario = (new WebDriverWait(driver, 60))
					.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//INPUT[@name='USUARIOF']")));
			if (txtUsuario.isDisplayed()) {
				txtUsuario.sendKeys(usuario.toUpperCase().trim());
			} else {
				if (msg.equals("OK"))
					msg = "";

				msg = msg + "No se encontró el Campo Usuario \n";
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
			System.out.println("ERROR BUSCANDO USUARIOS EN FORMULARIO " + e.getMessage());
			msg = "ERROR BUSCANDO USUARIOS EN FORMULARIO " + e.getMessage();
		}
		return msg;

	}

	public static String deleteUser(WebDriver driver) {
		String msg = "OK";
		try {
			WebElement btnEliminar = (new WebDriverWait(driver, 60))
					.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//IMG[@title='Eliminar (F9)']")));
			if (btnEliminar.isDisplayed()) {
				btnEliminar.click();
			} else {
				if (msg.equals("OK"))
					msg = "";

				msg = msg + "No se encontró el Botón Eliminar \n";
				return msg;
			}

			// imgconfirmarmantMantenimientoFormUSUARIOS
			WebElement btnAceptar = (new WebDriverWait(driver, 60)).until(ExpectedConditions
					.presenceOfElementLocated(By.xpath("//IMG[@id='imgconfirmarmantMantenimientoFormUSUARIOS']")));
			if (btnAceptar.isDisplayed()) {
				btnAceptar.click();
			} else {
				if (msg.equals("OK"))
					msg = "";

				msg = msg + "No se encontró el Botón Aceptar \n";
				return msg;
			}
		} catch (Exception e) {
			System.out.println("ERROR AL ELIMINAR UN USUARIO " + e.getMessage());
			msg = "ERROR AL ELIMINAR UN USUARIO " + e.getMessage();
		}
		return msg;
	}

	public static String validarEliminarUser(WebDriver driver) {

		String msg = "OK";

		try {

			Thread.sleep(3000);
			if (FunctionGeneric.validaAlert(driver)) {
				driver.switchTo().alert().accept();
			}

		} catch (Exception e) {
			msg = "ERROR AL VALIDAR MENSAJE DE ELIMINACIÓN USUARIO ";
		}

		return msg;
	}

}
