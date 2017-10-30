package page.Seguridad;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import util.FunctionGeneric;

public class PerfilUsuario {
	private FunctionGeneric funge = new FunctionGeneric();

	public String seleccionarPerfil(String perfil, WebDriver driver) {
		String msg = "OK";
		try {

			driver.switchTo().frame((new WebDriverWait(driver, 60)).until(
					ExpectedConditions.presenceOfElementLocated(By.xpath("//IFRAME[@src='paginas/index.jsp']"))));
			if (driver.findElements(By.xpath("//td[@title='" + perfil.toUpperCase().trim() + "']")).size() == 0) {
				msg = "No encontr� dato en Tabla de Perfiles ";
				System.exit(0);
			}
			// By.linkText(perfil.toUpperCase().trim()))
			msg = FunctionGeneric.clickObject("Link Perfil", "link", perfil.toUpperCase().trim(), "click", driver);
			if (!msg.equals("OK"))
				return msg;
			
			//  "//INPUT[@value='Men�s por Perfil']"
			msg = FunctionGeneric.clickObjectByXpath("Men� Perfil", "INPUT", "value", "Men�s por Perfil", "click", driver);
			if (!msg.equals("OK"))
				return msg;

		} catch (Exception e) {
			System.out.println("ERROR Seleccionar Perfil en TABLA " + e.getMessage());
			msg = "ERROR Seleccionar Perfil en TABLA ";
		}
		return msg;
	}

	public String selectTreeInicio(WebDriver driver) {
		String msg = "OK";
		try {
			// ImagenInicio			
			/*WebElement btnExpand = (new WebDriverWait(driver, 60))
					.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//IMG[@name='ImagenInicio']")));
			*/
			msg = FunctionGeneric.clickObjectByXpath("Bot�n + Inicio", "IMG", "name", "ImagenInicio", "click", driver);
			if (!msg.equals("OK"))
				return msg;
			/*if (btnExpand.isDisplayed()) {
				btnExpand.click();
			} else {
				if (msg.equals("OK"))
					msg = "";

				msg = msg + "No se encontr� el Bot�n + Inicio \n";
				System.exit(0);
			}*/
		} catch (Exception e) {
			System.out.println("ERROR AL EXPANDER ARBOL " + e.toString());
			msg = "ERROR AL EXPANDER ARBOL ";
		}
		return msg;
	}

	public String permisoAdmisionConsulta(WebDriver driver) {
		String msg = "OK";
		try {

			/*WebElement chkAdmisionConsulta = (new WebDriverWait(driver, 60)).until(ExpectedConditions
					.presenceOfElementLocated(By.xpath("//INPUT[@name='checkboxInicioAdmisi�nConsulta']")));
			if (chkAdmisionConsulta.isDisplayed()) {
				chkAdmisionConsulta.click();
			} else {
				if (msg.equals("OK"))
					msg = "";

				msg = msg + "No se encontr� el CheckBox Admisi�n Consulta \n";
				System.exit(0);
			}*/
			
			msg = FunctionGeneric.clickObjectByXpath("Checkbox Admisi�n Consulta", "INPUT", "name", "checkboxInicioAdmisi�nConsulta", "click", driver);
			if (!msg.equals("OK"))
				return msg;
			msg = FunctionGeneric.clickObjectByXpath("Bot�n Guardar Cambios", "INPUT", "name", "ACTUALIZAR", "click", driver);
			if (!msg.equals("OK"))
				return msg;
			/*WebElement btnGuardar = (new WebDriverWait(driver, 60))
					.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//INPUT[@name='ACTUALIZAR']")));
			if (btnGuardar.isDisplayed()) {
				btnGuardar.click();
			} else {
				if (msg.equals("OK"))
					msg = "";

				msg = msg + "No se encontr� el Bot�n Guardar Cambios \n";
				System.exit(0);
			}*/
			
			msg = funge.validaMensajeAlert("se actualizaron correctamente",driver);
			if (!msg.equals("OK"))
				return msg;
			/*if (driver.switchTo().alert().getText().contains("se actualizaron correctamente")) {
				driver.switchTo().alert().accept();
			} else {
				driver.switchTo().alert().accept();
				msg = "Error al modificar cambios";
			}*/

		} catch (Exception e) {
			System.out.println("ERROR AL PRESIONAR CHECKBOX DE ADMISI�N CONSULTA " + e.toString());
			msg = "ERROR AL PRESIONAR CHECKBOX DE ADMISI�N CONSULTA ";
		}
		return msg;
	}
}
