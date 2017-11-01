package page.Seguridad;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import util.FunctionGeneric;

public class PerfilUsuario {

	public static String seleccionarPerfil(String perfil, WebDriver driver) {

		String msg = "OK";

		try {

			driver.switchTo().frame((new WebDriverWait(driver, 30)).until(
					ExpectedConditions.presenceOfElementLocated(By.xpath("//IFRAME[@src='paginas/index.jsp']"))));

			if (driver.findElements(By.xpath("//td[@title='" + perfil.toUpperCase().trim() + "']")).size() == 0)
				msg = "No encontr� el perfil: " + perfil + " en la Tabla";
			if (!msg.equals("OK"))
				return msg;

			msg = FunctionGeneric.clickObject("Link Perfil", "link", perfil.toUpperCase().trim(), "click", driver);
			if (!msg.equals("OK"))
				return msg;

			msg = FunctionGeneric.clickObjectByXpath("Men� Perfil", "INPUT", "value", "Men�s por Perfil", "click",
					driver);

		} catch (Exception e) {
			msg = "Error al Seleccionar Perfil";
		}

		return msg;
	}

	public static String selectTreeInicio(WebDriver driver) {

		String msg = "OK";

		try {

			msg = FunctionGeneric.clickObjectByXpath("Bot�n + Inicio", "IMG", "name", "ImagenInicio", "click", driver);

		} catch (Exception e) {
			msg = "Error al expandir el �rbol de perfiles";
		}

		return msg;
	}

	public static String permisoAdmisionConsulta(WebDriver driver) {

		String msg = "OK";

		try {

			msg = FunctionGeneric.clickObjectByXpath("Checkbox Admisi�n Consulta", "INPUT", "name",
					"checkboxInicioAdmisi�nConsulta", "click", driver);
			if (!msg.equals("OK"))
				return msg;

			msg = FunctionGeneric.clickObjectByXpath("Bot�n Guardar Cambios", "INPUT", "name", "ACTUALIZAR", "click",
					driver);
			if (!msg.equals("OK"))
				return msg;

			msg = FunctionGeneric.validaMensajeAlert("se actualizaron correctamente", driver);

		} catch (Exception e) {
			msg = "Error al presionar Checkbox Admisi�n Consulta";
		}

		return msg;
	}
}
