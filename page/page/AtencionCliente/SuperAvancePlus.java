package page.AtencionCliente;

import org.openqa.selenium.WebDriver;
import util.FunctionGeneric;

public class SuperAvancePlus {

	public String linkSuperAvance(WebDriver driver) {

		String msg = "OK";

		try {

			msg = FunctionGeneric.clickObjectByXpath("Link Super Avance", "A", "name", "LinkSolapaSOLAPA3", "click",
					driver);

		} catch (Exception e) {
			msg = "Error al presionar el link Super Avance";
		}

		return msg;
	}

}
