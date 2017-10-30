package page.Admision;

import java.awt.Robot;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import util.FunctionGeneric;

public class CambioSucursal {
	FunctionGeneric func;

	public String cambioOficina(String sucursal, WebDriver driver) {
		String msg = "OK";
		int index = 0;
		func = new FunctionGeneric();
		try {
			Thread.sleep(2000);
			Robot robot = new Robot();
			Set<String> winSet = driver.getWindowHandles();
			List<String> winList = new ArrayList<String>(winSet);
			List<WebElement> imgCarpet = (new WebDriverWait(driver, 25))
					.until(ExpectedConditions.presenceOfAllElementsLocatedBy((By.xpath("//img[@id='CARPET']"))));
			WebElement cboOficina;
			Select cboTipoOficina;// = new Select(driver.findElement(By.xpath("//SELECT[@name='TIPDOCAUX1']")));
			WebElement btnAceptar;

			
			if (imgCarpet.size() > 0) {
				imgCarpet.get(0).click();
				robot.keyPress(KeyEvent.VK_ENTER);
			} else {
				if (msg.equals("OK"))
					msg = "";

				msg = msg + "No se encontró la Imagen de la carpeta \n";
				return msg;
			}
			
			if (!func.waitWindows(2, winList, driver)) {
				msg = "No se encontró ventana";
				return msg;
			}
			winSet = driver.getWindowHandles();
			winList = new ArrayList<String>(winSet);
			driver.switchTo().window(winList.get(winList.size() - 1));

			cboOficina = (new WebDriverWait(driver, 25))
					.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//SELECT[@name='CODOFICINA']")));
			cboTipoOficina = new Select(driver.findElement(By.xpath("//SELECT[@name='CODOFICINA']")));
			
			if (cboOficina.isDisplayed())
				index = FunctionGeneric.returnPositionDataCBO(cboOficina, sucursal);
			if (index >= 0) {
				cboTipoOficina.selectByIndex(index);
			} else {
				msg = "No se encontró el valor en Combobox de TIPO DOCUMENTO \n";
				return msg;
			}
			
			btnAceptar = (new WebDriverWait(driver, 25))
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
			System.out.println("ERROR AL MODIFICAR EL CAMBIO DE SUCURSAL " + e.getMessage());
			msg = "ERROR AL MODIFICAR EL CAMBIO DE SUCURSAL " + e.getMessage();
		}

		return msg;
	}
}
