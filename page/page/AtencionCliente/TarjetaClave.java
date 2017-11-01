package page.AtencionCliente;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import util.FunctionGeneric;

public class TarjetaClave {

	static FunctionGeneric funge = new FunctionGeneric();

	public static String generarPIN(WebDriver driver) {
		String msg = "OK";
		try {
			WebElement FrameInterContrato = (new WebDriverWait(driver, 60)).until(
					ExpectedConditions.presenceOfElementLocated(By.xpath("//IFRAME[@src='paginas/esperaAux.jsp']")));
			driver.switchTo().frame(FrameInterContrato);

			List<WebElement> lstRadio = (new WebDriverWait(driver, 25)).until(ExpectedConditions
					.presenceOfAllElementsLocatedBy((By.xpath("//INPUT[@name='RadioButtonTarjetasVigentes']"))));
			if (lstRadio.size() != 0) {
				lstRadio.get(0).click();
			} else {
				if (msg.equals("OK"))
					msg = "";

				msg = msg + "No se encontraron Tarjetas Vigentes \n";
				System.exit(0);
			}
			// IMG name GENERARPIN
			WebElement btnGenerarPin = (new WebDriverWait(driver, 60))
					.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//IMG[@name='GENERARPIN']")));
			if (btnGenerarPin.isDisplayed()) {
				btnGenerarPin.click();
			} else {
				if (msg.equals("OK"))
					msg = "";

				msg = msg + "No se encontró el botón Generar PIN \n";
				System.exit(0);
			}

			if (FunctionGeneric.validaAlert(driver)) {
				driver.switchTo().alert().accept();
			}

			FunctionGeneric
					.waitTOC("Favor Realizar Proceso Manual de TOC o Autentia, Luego Presionar el Botón Aceptar");
			// System.out.println(driver.getPageSource());

			System.out.println("tERMINO DESPUES DE PRESIONAR ACEPTAR");
		} catch (Exception e) {
			System.out.println("ERROR AL GENERAR TARJETA CLAVE " + e.getMessage());
			msg = "ERROR AL GENERAR TARJETA CLAVE " + e.getMessage();
		}
		return msg;
	}

	public static String bloquearPIN(WebDriver driver) {
		String msg = "OK";
		try {
			WebElement FrameInterContrato = (new WebDriverWait(driver, 60)).until(
					ExpectedConditions.presenceOfElementLocated(By.xpath("//IFRAME[@src='paginas/esperaAux.jsp']")));
			driver.switchTo().frame(FrameInterContrato);

			List<WebElement> lstRadio = (new WebDriverWait(driver, 25)).until(ExpectedConditions
					.presenceOfAllElementsLocatedBy((By.xpath("//INPUT[@name='RadioButtonTarjetasVigentes']"))));
			if (lstRadio.size() != 0) {
				lstRadio.get(0).click();
			} else {
				if (msg.equals("OK"))
					msg = "";

				msg = msg + "No se encontraron Tarjetas Vigentes \n";
				System.exit(0);
			}
			// IMG name BLOQUEARPIN
			WebElement btnBloquearPin = (new WebDriverWait(driver, 60))
					.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//IMG[@name='BLOQUEARPIN']")));
			if (btnBloquearPin.isDisplayed()) {
				btnBloquearPin.click();
			} else {
				if (msg.equals("OK"))
					msg = "";

				msg = msg + "No se encontró el botón Bloquear PIN \n";
				System.exit(0);
			}

			Thread.sleep(2000);
			if (FunctionGeneric.validaAlert(driver)) {
				driver.switchTo().alert().accept();
			}

		} catch (Exception e) {
			System.out.println("ERROR AL MOMENTO DE BLOQUEAR PIN " + e.getMessage());
			msg = "ERROR AL MOMENTO DE BLOQUEAR PIN " + e.getMessage();
		}
		return msg;
	}

	public static String validaActivaPIN(WebDriver driver) {
		String msg = "OK";
		try {

			Thread.sleep(3000);
			List<WebElement> td_collectionLink = driver
					.findElements(By.xpath("id('Listado_ListTarjetasVigentes')/TBODY/TR/TD/A"));

			if (!td_collectionLink.get(10).getText().equals("ACT")) {
				msg = "NO SÉ MODIFICÓ EL ESTADO A ACTIVO PIN";
				System.out.println("NO SÉ MODIFICÓ EL ESTADO A ACTIVO PIN");
				return msg;
			} else {
				System.out.println("SÉ MODIFICÓ EL ESTADO A ACTIVO PIN");
			}

		} catch (Exception e) {
			System.out.println("ERROR AL VALIDAR ACTIVACIÓN PIN " + e.getMessage());
			msg = "ERROR AL VALIDAR ACTIVACIÓN PIN " + e.getMessage();
		}
		return msg;
	}

	public static String validaBloqueoPIN(WebDriver driver) {
		String msg = "OK";
		try {

			Thread.sleep(3000);
			List<WebElement> td_collectionLink = driver
					.findElements(By.xpath("id('Listado_ListTarjetasVigentes')/TBODY/TR/TD/A"));

			if (!td_collectionLink.get(10).getText().equals("BLO")) {
				msg = "NO SÉ MODIFICÓ EL ESTADO A BLOQUEADO PIN";
				System.out.println("NO SÉ MODIFICÓ EL ESTADO A BLOQUEADO PIN");
				return msg;
			} else {
				System.out.println("SÉ MODIFICÓ EL ESTADO A BLOQUEADO PIN");
			}

			/*
			 * for (int i = 0; i < td_collectionLink.size(); i++) {
			 * System.out.println("i = "+i+" "+td_collectionLink.get(i).getText());
			 * 
			 * }
			 */
			/*
			 * WebElement table_element =
			 * driver.findElement(By.id("Listado_ListTarjetasVigentes")); List<WebElement>
			 * tr_collection = table_element
			 * .findElements(By.xpath("id('Listado_ListTarjetasVigentes')/tbody/tr"));
			 * 
			 * System.out.println("NUMBER OF ROWS IN THIS TABLE = " + tr_collection.size());
			 * int row_num, col_num; row_num = 1; for (WebElement trElement : tr_collection)
			 * { List<WebElement> td_collection = trElement.findElements(By.xpath("td"));
			 * //System.out.println("NUMBER OF COLUMNS=" + td_collection.size()); col_num =
			 * 0; for (WebElement tdElement : td_collection) { if (row_num == 1 && col_num
			 * ==12 ) { if (tdElement.getText().equals("BLOQ")) }
			 * 
			 * System.out.println("row # " + row_num + ", col # " + col_num + "text=" +
			 * tdElement.getText());
			 * 
			 * col_num++; } row_num++; }
			 */
		} catch (Exception e) {
			System.out.println("ERROR AL VALIDAR BLOQUEO TARJETA " + e.getMessage());
			msg = "ERROR AL VALIDAR BLOQUEO TARJETA " + e.getMessage();
		}
		return msg;
	}

	public static String usosPIN(WebDriver driver) {
		String msg = "OK";
		try {

			WebElement FrameInterContrato = (new WebDriverWait(driver, 60)).until(
					ExpectedConditions.presenceOfElementLocated(By.xpath("//IFRAME[@src='paginas/esperaAux.jsp']")));
			driver.switchTo().frame(FrameInterContrato);

			List<WebElement> lstRadio = (new WebDriverWait(driver, 25)).until(ExpectedConditions
					.presenceOfAllElementsLocatedBy((By.xpath("//INPUT[@name='RadioButtonTarjetasVigentes']"))));
			if (lstRadio.size() != 0) {
				lstRadio.get(0).click();
			} else {
				if (msg.equals("OK"))
					msg = "";

				msg = msg + "No se encontraron Tarjetas Vigentes \n";
				System.exit(0);
			}
			// IMG name USOSPIN
			WebElement btnUsoPin = (new WebDriverWait(driver, 60))
					.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//IMG[@name='USOSPIN']")));
			if (btnUsoPin.isDisplayed()) {
				btnUsoPin.click();
			} else {
				if (msg.equals("OK"))
					msg = "";

				msg = msg + "No se encontró el botón Usos de PIN \n";
				System.exit(0);
			}

		} catch (Exception e) {
			System.out.println("ERROR AL MOMENTO USOS DE CLAVE " + e.getMessage());
			msg = "ERROR AL MOMENTO USOS DE CLAVE " + e.getMessage();
		}

		return msg;
	}

	public static String ventaUsosPin(WebDriver driver) {
		String msg = "OK";
		try {
			Thread.sleep(6000);
			Set<String> winSet;
			List<String> winList;
			winSet = driver.getWindowHandles();
			winList = new ArrayList<String>(winSet);

			if (!FunctionGeneric.waitWindows(3, winList, driver)) {
				msg = "No encuentra ventana 3 ";
				System.exit(0);
			}
			winSet = driver.getWindowHandles();
			winList = new ArrayList<String>(winSet);
			driver.switchTo().window(winList.get(winList.size() - 1));

			// System.out.println("HTML 3 uso pin " + driver.getPageSource());
			if (!FunctionGeneric.validarTexto("Usos de Pin", "Validar Ventana Uso PIN", driver).equals("OK")) {
				msg = "No ha encontrado texto en la Ventana Uso Pin";
				System.exit(0);
			}
		} catch (Exception e) {
			System.out.println("ERROR EN LA VENTANA USU DE PIN " + e.getMessage());
			msg = "ERROR EN LA VENTANA USU DE PIN " + e.getMessage();
		}
		return msg;
	}

	public static String generaClave(WebDriver driver) {
		String msg = "OK";
		try {
			// name=GENERARCLAVE
			WebElement FrameInterContrato = (new WebDriverWait(driver, 30)).until(
					ExpectedConditions.presenceOfElementLocated(By.xpath("//IFRAME[@src='paginas/esperaAux.jsp']")));
			driver.switchTo().frame(FrameInterContrato);

			List<WebElement> lstRadio = (new WebDriverWait(driver, 30)).until(ExpectedConditions
					.presenceOfAllElementsLocatedBy((By.xpath("//INPUT[@name='RadioButtonTarjetasVigentes']"))));
			if (lstRadio.size() != 0) {
				lstRadio.get(0).click();
			} else {
				if (msg.equals("OK"))
					msg = "";

				msg = msg + "No se encontraron Tarjetas Vigentes \n";
				System.exit(0);
			}
			// IMG name USOSPIN
			WebElement btnGeneraClave = (new WebDriverWait(driver, 30))
					.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//IMG[@name='GENERARCLAVE']")));
			if (btnGeneraClave.isDisplayed()) {
				btnGeneraClave.click();
			} else {
				if (msg.equals("OK"))
					msg = "";

				msg = msg + "No se encontró el botón Generar Clave \n";
				System.exit(0);
			}

			FunctionGeneric
					.waitTOC("Favor Realizar Proceso Manual de TOC o Autentia, Luego Presionar el Botón Aceptar");
		} catch (Exception e) {
			System.out.println("ERROR AL MOMENTO DE GENERAR CLAVE " + e.getMessage());
			msg = "ERROR AL MOMENTO DE GENERAR CLAVE " + e.getMessage();
		}
		return msg;
	}

	public static String bloquearClave(WebDriver driver) {
		String msg = "OK";
		try {

			WebElement FrameInterContrato = (new WebDriverWait(driver, 60)).until(
					ExpectedConditions.presenceOfElementLocated(By.xpath("//IFRAME[@src='paginas/esperaAux.jsp']")));
			driver.switchTo().frame(FrameInterContrato);

			List<WebElement> lstRadio = (new WebDriverWait(driver, 25)).until(ExpectedConditions
					.presenceOfAllElementsLocatedBy((By.xpath("//INPUT[@name='RadioButtonTarjetasVigentes']"))));
			if (lstRadio.size() != 0) {
				lstRadio.get(0).click();
			} else {
				if (msg.equals("OK"))
					msg = "";

				msg = msg + "No se encontraron Tarjetas Vigentes \n";
				System.exit(0);
			}
			// img name BLOQUEARCLAVE
			WebElement btnBloquearClave = (new WebDriverWait(driver, 60))
					.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//IMG[@name='BLOQUEARCLAVE']")));
			if (btnBloquearClave.isDisplayed()) {
				btnBloquearClave.click();
			} else {
				if (msg.equals("OK"))
					msg = "";

				msg = msg + "No se encontró el botón Bloquear Clave \n";
				System.exit(0);
			}

		} catch (Exception e) {
			System.out.println("ERROR AL BLOQUEAR LA CLAVE " + e.getMessage());
			msg = "ERROR AL BLOQUEAR LA CLAVE " + e.getMessage();
		}
		return msg;
	}

	public static String validarActivaClave(WebDriver driver) {
		String msg = "OK";
		try {
			Thread.sleep(4000);
			List<WebElement> td_collectionLink = driver
					.findElements(By.xpath("id('Listado_ListTarjetasVigentes')/TBODY/TR/TD/A"));

			if (!td_collectionLink.get(11).getText().equals("ACT")) {
				msg = "CLAVE NO SÉ MODIFICÓ EL ESTADO A ACTIVA";
				System.out.println("CLAVE NO SÉ MODIFICÓ EL ESTADO A ACTIVA ");
				return msg;
			} else {
				System.out.println("CLAVE SÉ MODIFICÓ EL ESTADO A ACTIVA");
			}

		} catch (Exception e) {
			System.out.println("ERROR AL VALIDAR EL ESTADO DE LA CLAVE " + e.getMessage());
			msg = "ERROR AL VALIDAR EL ESTADO DE LA CLAVE " + e.getMessage();
		}
		return msg;
	}

	public static String validarBloqueoTarjeta(WebDriver driver) {
		String msg = "OK";
		try {
			Thread.sleep(4000);
			List<WebElement> td_collectionLink = driver
					.findElements(By.xpath("id('Listado_ListTarjetasVigentes')/TBODY/TR/TD/A"));

			if (!td_collectionLink.get(11).getText().equals("BLO")) {
				msg = "NO SÉ MODIFICÓ EL ESTADO A BLOQUEADO ";
				System.out.println("NO SÉ MODIFICÓ EL ESTADO A BLOQUEADO ");
				return msg;
			} else {
				System.out.println("SÉ MODIFICÓ EL ESTADO A BLOQUEADO ");
			}

			/*
			 * for (int i = 0; i < td_collectionLink.size(); i++) {
			 * System.out.println("i = "+i+" "+td_collectionLink.get(i).getText());
			 * 
			 * }
			 */
			/*
			 * WebElement table_element =
			 * driver.findElement(By.id("Listado_ListTarjetasVigentes")); List<WebElement>
			 * tr_collection = table_element
			 * .findElements(By.xpath("id('Listado_ListTarjetasVigentes')/tbody/tr"));
			 * 
			 * System.out.println("NUMBER OF ROWS IN THIS TABLE = " + tr_collection.size());
			 * int row_num, col_num; row_num = 1; for (WebElement trElement : tr_collection)
			 * { List<WebElement> td_collection = trElement.findElements(By.xpath("td"));
			 * //System.out.println("NUMBER OF COLUMNS=" + td_collection.size()); col_num =
			 * 0; for (WebElement tdElement : td_collection) { if (row_num == 1 && col_num
			 * ==12 ) { if (tdElement.getText().equals("BLOQ")) }
			 * 
			 * System.out.println("row # " + row_num + ", col # " + col_num + "text=" +
			 * tdElement.getText());
			 * 
			 * col_num++; } row_num++; }
			 */
		} catch (Exception e) {
			System.out.println("ERROR AL VALIDAR BLOQUEO TARJETA " + e.getMessage());
			msg = "ERROR AL VALIDAR BLOQUEO TARJETA " + e.getMessage();
		}
		return msg;
	}

	public static String usoClave(WebDriver driver) {
		String msg = "OK";
		try {
			// name=USOSCLAVE
			WebElement FrameInterContrato = (new WebDriverWait(driver, 60)).until(
					ExpectedConditions.presenceOfElementLocated(By.xpath("//IFRAME[@src='paginas/esperaAux.jsp']")));
			driver.switchTo().frame(FrameInterContrato);

			List<WebElement> lstRadio = (new WebDriverWait(driver, 25)).until(ExpectedConditions
					.presenceOfAllElementsLocatedBy((By.xpath("//INPUT[@name='RadioButtonTarjetasVigentes']"))));
			if (lstRadio.size() != 0) {
				lstRadio.get(0).click();
			} else {
				if (msg.equals("OK"))
					msg = "";

				msg = msg + "No se encontraron Tarjetas Vigentes \n";
				System.exit(0);
			}
			// img name BLOQUEARCLAVE
			WebElement btnUsoClave = (new WebDriverWait(driver, 60))
					.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//IMG[@name='USOSCLAVE']")));
			if (btnUsoClave.isDisplayed()) {
				btnUsoClave.click();
			} else {
				if (msg.equals("OK"))
					msg = "";

				msg = msg + "No se encontró el botón Uso Clave \n";
				System.exit(0);
			}
		} catch (Exception e) {
			System.out.println("ERRO AL PRESIONAR USO DE CLAVE " + e.getMessage());
			msg = "ERRO AL PRESIONAR USO DE CLAVE " + e.getMessage();
		}
		return msg;
	}

	public static String ventanaUsoClave(WebDriver driver) {
		String msg = "OK";
		try {
			Thread.sleep(6000);
			Set<String> winSet;
			List<String> winList;
			winSet = driver.getWindowHandles();
			winList = new ArrayList<String>(winSet);

			if (!FunctionGeneric.waitWindows(3, winList, driver)) {
				msg = "No encuentra ventana 3 ";
				System.exit(0);
			}
			winSet = driver.getWindowHandles();
			winList = new ArrayList<String>(winSet);
			// System.out.println("Cantidad Ventanas "+winList.size());
			driver.switchTo().window(winList.get(winList.size() - 1));

			Thread.sleep(2000);
			// System.out.println("HTML 3 " + driver.getPageSource());
			if (!FunctionGeneric.validarTexto("Usos de Clave", "Validar Ventana Uso Clave", driver).equals("OK")) {
				msg = "No ha encontrado texto en la Ventana Uso Pin";
				System.exit(0);
			}
		} catch (Exception e) {
			System.out.println();
		}
		return msg;

	}

}
