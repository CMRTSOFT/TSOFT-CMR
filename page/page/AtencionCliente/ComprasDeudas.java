package page.AtencionCliente;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import util.FunctionGeneric;

public class ComprasDeudas {

	public static String validarTextoCompra(WebDriver driver) {

		String msg = "OK";

		try {

			driver.switchTo().frame((new WebDriverWait(driver, 30)).until(
					ExpectedConditions.presenceOfElementLocated(By.xpath("//IFRAME[@src='paginas/esperaAux.jsp']"))));

			msg = FunctionGeneric.validarTexto("Tipo Doc.", "Desplegar Pantalla Compra Deudas", driver);

		} catch (Exception e) {
			msg = "Erro en el despliegue de pantalla Compras Deudas";
		}

		return msg;
	}

}
