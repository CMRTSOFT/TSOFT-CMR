package page.AtencionCliente;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import util.FunctionGeneric;

public class ComprasDeudas {
	FunctionGeneric func;

	public String validarTextoCompra(WebDriver driver) {
		String msg = "OK";
		try {
			func = new FunctionGeneric();

			WebElement FrameInterContrato = (new WebDriverWait(driver, 30)).until(
					ExpectedConditions.presenceOfElementLocated(By.xpath("//IFRAME[@src='paginas/esperaAux.jsp']")));
			driver.switchTo().frame(FrameInterContrato);

			if (!func.validarTexto("Tipo Doc.","Desplegar Pantalla Compra Deudas", driver).equals("OK")) {
				msg = "No se encontró valor Tipo Doc. en HTML";
				System.exit(0);
			}
		} catch (Exception e) {
			System.out.println("ERROR AL VALIDAR TEXTO EN COMPRA DEUDAS " + e.getMessage());
			msg = "ERROR AL VALIDAR TEXTO EN COMPRA DEUDAS " + e.getMessage();
		}
		return msg;
	}

}
