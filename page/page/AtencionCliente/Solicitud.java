package page.AtencionCliente;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class Solicitud {
	public String formularioSolicitud(WebDriver driver) {
		String msg = "OK";
		try {
			WebElement FrameInterContrato = (new WebDriverWait(driver, 60)).until(
					ExpectedConditions.presenceOfElementLocated(By.xpath("//IFRAME[@src='paginas/esperaAux.jsp']")));
			driver.switchTo().frame(FrameInterContrato);

			WebElement btnSiguiente = (new WebDriverWait(driver, 60))
					.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//INPUT[@id='SIGUIENTE']")));

			if (btnSiguiente.isDisplayed()) {
				btnSiguiente.click();
			} else {
				if (msg.equals("OK"))
					msg = "";

				msg = msg + "No se encontró el Botón Siguiente \n";
				return msg;
			}
			Thread.sleep(3000);
			WebElement btnAnterior = (new WebDriverWait(driver, 25))
					.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//INPUT[@value='Anterior']")));
			if (btnAnterior.isDisplayed()) {
				btnAnterior.click();
			} else {
				if (msg.equals("OK"))
					msg = "";

				msg = msg + "No se encontró el Botón Anterior \n";
				return msg;
			}
			System.out.println(driver.getPageSource());
		} catch (Exception e) {
			System.out.println("ERROR EN EL FORMULARIO SOLICITUD " + e.getMessage());
			msg = "ERROR EN EL FORMULARIO SOLICITUD " + e.getMessage();
		}
		return msg;
	}

}
