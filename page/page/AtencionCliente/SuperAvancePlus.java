package page.AtencionCliente;

import org.openqa.selenium.WebDriver;

import util.FunctionGeneric;

public class SuperAvancePlus {

	public String linkSuperAvance(WebDriver driver) {
		String msg = "OK";
		try {
			// <A class=SolapaInactiva href="javascript:void(0)" name=LinkSolapaSOLAPA3
			// target=InterfaceContrato>Súper Avance</A>
			msg = FunctionGeneric.clickObjectByXpath("Link Super Avance", "A", "name", "LinkSolapaSOLAPA3", "click",
					driver);
			if (!msg.equals("OK"))
				return msg;

		} catch (Exception e) {
			System.out.println("ERROR AL PRESIONAR PESTAÑA SUPER AVANCE" + e.getMessage());
			msg = "ERROR AL PRESIONAR PESTAÑA SUPER AVANCE";
		}
		return msg;
	}

}
