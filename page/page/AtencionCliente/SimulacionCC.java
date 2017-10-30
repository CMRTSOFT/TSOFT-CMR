package page.AtencionCliente;

import java.awt.Robot;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.apache.http.impl.io.SocketOutputBuffer;
import org.apache.poi.hslf.record.Sound;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import util.FunctionGeneric;
import util.KeyboardClass;

public class SimulacionCC {
	private KeyboardClass keyBoa;
	private FunctionGeneric func = new FunctionGeneric();

	public String formularioSimulacionCC(String monto, String numCuota, String mesDiferido, String valCbo,
			WebDriver driver) {
		String msg = "OK";

		int index = 0;
		try {
			keyBoa = new KeyboardClass();
			Robot robot = new Robot();
			Set<String> winSet = driver.getWindowHandles();
			List<String> winList = new ArrayList<String>(winSet);

			WebElement FrameInterContrato = (new WebDriverWait(driver, 60)).until(
					ExpectedConditions.presenceOfElementLocated(By.xpath("//IFRAME[@src='paginas/esperaAux.jsp']")));
			driver.switchTo().frame(FrameInterContrato);
			WebElement txtMonto = (new WebDriverWait(driver, 60))
					.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//INPUT[@name='IMPFAC']")));
			WebElement txtNumCuotas = (new WebDriverWait(driver, 60))
					.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//INPUT[@name='TOTCUOTAS']")));
			WebElement txtMesDife = (new WebDriverWait(driver, 60))
					.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//INPUT[@name='MESCARCUO']")));
			WebElement cboCuota = (new WebDriverWait(driver, 60))
					.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//SELECT[@name='CODTIPC']")));
			Select cboTipoCuota = new Select(cboCuota);

			WebElement btnSimular = driver.findElement(By.xpath("//INPUT[@value='Simular']"));

			if (cboCuota.isDisplayed())
				index = FunctionGeneric.returnPositionDataCBO(cboCuota, valCbo);

			if (txtMonto.isDisplayed()) {
				txtMonto.sendKeys(monto.toString().trim());
			} else {
				if (msg.equals("OK")) {
					msg = "";
				}
				msg = msg + "No se encontró el campo Monto a Financiar \n";
				System.exit(0);
			}
			if (txtNumCuotas.isDisplayed()) {
				txtNumCuotas.sendKeys(numCuota);
			} else {
				if (msg.equals("OK")) {
					msg = "";
				}
				msg = msg + "No se encontró el campo N° Cuotas \n";
				System.exit(0);
			}
			if (txtMesDife.isDisplayed()) {
				txtMesDife.sendKeys(mesDiferido);
			} else {
				if (msg.equals("OK")) {
					msg = "";
				}
				msg = msg + "No se encontró el campo Meses Diferido \n";
				System.exit(0);
			}
			if (index >= 0) {
				cboTipoCuota.selectByIndex(index);
			} else {
				msg = "No se encontró el valor en Combobox de TipoCuota \n";
				System.exit(0);
			}
			// Simular
			if (btnSimular.isDisplayed()) {
				btnSimular.click();
			} else {
				if (msg.equals("OK")) {
					msg = "";
				}
				msg = msg + "No se encontró el botón Simular \n";
				System.exit(0);
			}

			Thread.sleep(3000);
			// System.out.println(driver.switchTo().alert().getText());

			if (func.validaAlert(driver)) {
					driver.switchTo().alert().accept();
					Thread.sleep(3000);
					WebElement btnImprimir = (new WebDriverWait(driver, 60))
							.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//INPUT[@value='Imprimir']")));
					if (btnImprimir.isDisplayed()) {
						btnImprimir.click();
					} else {
						if (msg.equals("OK")) {
							msg = "";
						}
						msg = msg + "No se encontró el botón Imprimir \n";
					}
				
			}
			/*Thread.sleep(3000);
			if (func.validaAlert(driver))
				driver.switchTo().alert().accept();
*/
			Thread.sleep(4000);
			keyBoa.KeyPressTecl("ESC");
			Thread.sleep(4000);

			System.out.println("Cantidad Ventana antes de cerrar "+driver.getWindowHandles().size());
			if (driver.getWindowHandles().size() == 3) {
				func.closeWindows(driver, 2);
			}else {
				keyBoa.KeyPressTecl("ALT"); 
				keyBoa.KeyPressTecl("F4");
				 robot.keyRelease(KeyEvent.VK_ALT); robot.keyRelease(KeyEvent.VK_F4);
			}
			
			System.out.println("Cantidad Ventana despues de cerrar "+driver.getWindowHandles().size());
			Thread.sleep(4000);
			driver = func.waitWindows(2, driver);
			if (func.validaAlert(driver))
				driver.switchTo().alert().accept();
			
		
			driver.switchTo().frame((new WebDriverWait(driver, 60))
					.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//IFRAME[@src='paginas/esperaAux.jsp']"))));
			
			
		} catch (Exception e) {
			System.out.println("Error Formulario Simulación " + e.toString());
			msg = "Error Formulario Simulación " + e.toString();
		}
		return msg;
	}

	public String transferirCuenta(String banco, String tipoCuenta, String numCuenta, String email, WebDriver driver) {
		String msg = "OK";
		int index = 0;
		try {

			//msg = func.clickObjectByXpath("Botón Transferir", "INPUT", "name","TRANSFERIR", "click", driver);
			
			msg = func.clickObject("Botón Transferir", "name", "TRANSFERIR", "click", driver);
			System.out.println("msg "+msg);
			Thread.sleep(4000);
			//System.out.println(driver.getPageSource());

			List<WebElement> lstAccount = (new WebDriverWait(driver, 60))
					.until(ExpectedConditions.presenceOfAllElementsLocatedBy((By.tagName("a"))));

			if (lstAccount.size() != 0) {
				lstAccount.get(0).click();
			} else {
				// SELECT name TIPOBCO
				WebElement cboBanco = (new WebDriverWait(driver, 60))
						.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//SELECT[@name='TIPOBCO']")));
				Select cboTipoBanco = new Select(cboBanco);
				// SELECT name=TIPOCTA
				WebElement cboCuenta = (new WebDriverWait(driver, 60))
						.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//SELECT[@name='TIPOCTA']")));
				Select cboTipoCuenta = new Select(cboCuenta);
				// INPUT name=MP675_NROCUENTA
				WebElement txtNumCuenta = (new WebDriverWait(driver, 60)).until(
						ExpectedConditions.presenceOfElementLocated(By.xpath("//INPUT[@name='MP675_NROCUENTA']")));
				// INPUT name=MP675_MAILCLIENTE
				WebElement txtEmail = (new WebDriverWait(driver, 60)).until(
						ExpectedConditions.presenceOfElementLocated(By.xpath("//INPUT[@name='MP675_MAILCLIENTE']")));
				// INPUT name=Agregar
				WebElement btnAgregar = (new WebDriverWait(driver, 60)).until(
						ExpectedConditions.presenceOfElementLocated(By.xpath("//INPUT[@name='Agregar']")));
				if (cboBanco.isDisplayed())
					index = FunctionGeneric.returnPositionDataCBO(cboBanco, banco);
				if (index >= 0) {
					cboTipoBanco.selectByIndex(index);
				} else {
					msg = "No se encontró el valor en Combobox Banco \n";
					System.exit(0);
				}

				if (cboCuenta.isDisplayed())
					index = FunctionGeneric.returnPositionDataCBO(cboCuenta, tipoCuenta);
				if (index >= 0) {
					cboTipoCuenta.selectByIndex(index);
				} else {
					msg = "No se encontró el valor en Combobox Tipo Cuenta \n";
					System.exit(0);
				}

				if (txtNumCuenta.isDisplayed()) {
					txtNumCuenta.sendKeys(numCuenta);
				} else {
					if (msg.equals("OK"))
						msg = "";

					msg = msg + "No se encontró el Número de Cuenta \n";
					System.exit(0);
				}
				if (txtEmail.isDisplayed()) {
					txtEmail.sendKeys(email);
				} else {
					if (msg.equals("OK"))
						msg = "";

					msg = msg + "No se encontró el Email Cliente \n";
					System.exit(0);
				}
				if (btnAgregar.isDisplayed()) {
					btnAgregar.click();
				} else {
					if (msg.equals("OK"))
						msg = "";

					msg = msg + "No se encontró el Botón Agregar \n";
					System.exit(0);
				}
				
			}
			
			WebElement btnConfirmar = (new WebDriverWait(driver, 60)).until(
					ExpectedConditions.presenceOfElementLocated(By.xpath("//INPUT[@value='Confirmar Transferencia']")));
			if (btnConfirmar.isDisplayed()) {
				btnConfirmar.click();
			} else {
				if (msg.equals("OK"))
					msg = "";

				msg = msg + "No se encontró el Botón Confirmar Transferencia \n";
				System.exit(0);
			}
			
			func.waitTOC("Favor Realizar Proceso TOC o AUTENTIA, luego presionar Botón 'ACEPTAR'");
			
			if (func.validaAlert(driver)) 
				driver.switchTo().alert().accept();

		} catch (Exception e) {
			System.out.println("ERROR EN EL FORMULARIO DE TRANSFERIR CUENTA " + e.toString());
			msg = "ERROR EN EL FORMULARIO DE TRANSFERIR CUENTA " + e.toString();
		}
		return msg;
	}
	
	public String validaPantallaImprimir(WebDriver driver ) {
		String msg = "";
		try {
			Thread.sleep(60000);
			System.out.println("HTML "+driver.getPageSource());
			msg = func.clickObject("Botón Imprimir", "name", "IMPRIMIR", "click", driver);
			Thread.sleep(60000);
			keyBoa.KeyPressTecl("ESC");
			Thread.sleep(4000);
			func.addScreenEvi("Comprobante de Transferencia", "Pass");
			
		} catch (Exception e) {
			System.out.println("ERROR AL VALIDAR PANTALLA IMPRIMIR");
			
		}
		return msg;
	}
}
