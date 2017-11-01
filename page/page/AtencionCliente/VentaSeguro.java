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

public class VentaSeguro {

	public static String iniciarContratacionSeguro(String celular, String rutCaptador, WebDriver driver) {
		String msg = "OK";
		try {
			WebElement FrameInterContrato = (new WebDriverWait(driver, 60)).until(
					ExpectedConditions.presenceOfElementLocated(By.xpath("//IFRAME[@src='paginas/esperaAux.jsp']")));
			driver.switchTo().frame(FrameInterContrato);
			Thread.sleep(3000);
			// INPUT id=CELULAR
			WebElement txtCelular = (new WebDriverWait(driver, 60))
					.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//INPUT[@id='CELULAR']")));
			// INPUT name=SEGCESANT
			WebElement chkCesantia = (new WebDriverWait(driver, 60))
					.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//INPUT[@name='SEGCESANT']")));
			// INPUT id=RRCC_SEGCESANT
			WebElement txtRutCaptador = (new WebDriverWait(driver, 60))
					.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//INPUT[@id='RRCC_SEGCESANT']")));

			// INPUT id=SIGUIENTE
			WebElement btnIniciarContrato = (new WebDriverWait(driver, 60))
					.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//INPUT[@id='SIGUIENTE']")));
			if (txtCelular.isDisplayed()) {
				txtCelular.sendKeys(celular);
			} else {
				if (msg.equals("OK"))
					msg = "";

				msg = msg + "No se encontró el Campo Celular \n";
				return msg;
			}
			if (chkCesantia.isDisplayed()) {
				chkCesantia.click();
			} else {
				if (msg.equals("OK"))
					msg = "";

				msg = msg + "No se encontró el Checkbox Seguro Cesantía \n";
				return msg;
			}
			if (txtRutCaptador.isDisplayed()) {
				txtRutCaptador.sendKeys(rutCaptador);
			} else {
				if (msg.equals("OK"))
					msg = "";

				msg = msg + "No se encontró el Campo Rut Captador \n";
				return msg;
			}
			if (btnIniciarContrato.isDisplayed()) {
				btnIniciarContrato.click();
			} else {
				if (msg.equals("OK"))
					msg = "";

				msg = msg + "No se encontró el Botón Iniciar Contrato \n";
				return msg;
			}

		} catch (Exception e) {
			System.out.println("ERROR AL INICIAR CONTRATACIÓN DE SEGUROS " + e.getMessage());
			msg = "ERROR AL INICIAR CONTRATACIÓN DE SEGUROS " + e.getMessage();
		}
		return msg;
	}

	public static String ventanaSeguro(WebDriver driver) {
		String msg = "OK";
		try {
			Set<String> winSet = driver.getWindowHandles();
			List<String> winList = new ArrayList<String>(winSet);
			if (!FunctionGeneric.waitWindows(3, winList, driver)) {
				msg = "No se encontró Ventana de Seguros";
				return msg;
			}
			winSet = driver.getWindowHandles();
			winList = new ArrayList<String>(winSet);
			driver.switchTo().window(winList.get(winList.size() - 1));
			// System.out.println(driver.getPageSource());
			// INPUT value=Siguiente
			WebElement btnSiguiente = (new WebDriverWait(driver, 60))
					.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//INPUT[@value='Siguiente']")));
			if (btnSiguiente.isDisplayed()) {
				btnSiguiente.click();
			} else {
				if (msg.equals("OK"))
					msg = "";

				msg = msg + "No se encontró el Botón Siguiente \n";
				return msg;
			}
		} catch (Exception e) {
			System.out.println("ERROR EN LA VENTANA DE SEGUROS " + e.getMessage());
			msg = "ERROR EN LA VENTANA DE SEGUROS " + e.getMessage();
		}
		return msg;
	}
}
