package page.AtencionCliente;

import org.openqa.selenium.WebDriver;
import util.FunctionGeneric;

public class Contrato {

	public static String formModificaContrato(String diaPago, WebDriver driver) {

		String msg = "OK";

		try {

			msg = FunctionGeneric.selecCBO("Combobox Dia de Pago", "name", "MPMF_DIAVCTO", diaPago, false, driver);
			if (!msg.equals("OK"))
				return msg;

			msg = FunctionGeneric.setTextObjectByXpath("OSERVACION 1", "INPUT", "name", "OBSERVACION1",
					"PRUEBA AUTOMATIZADA TSOFT", "click", driver);
			if (!msg.equals("OK"))
				return msg;
			FunctionGeneric.addScreenEvi("Formulario Modificar Contrato", "Pass");

			msg = FunctionGeneric.clickObjectByXpath("Botón Aceptar", "IMG", "id",
					"imgconfirmarmantMantenimientoFormMANTENIMIENTO", "click", driver);
			if (!msg.equals("OK"))
				return msg;
			FunctionGeneric.addScreenEvi("Modificación de contrato", "Pass");

			msg = FunctionGeneric.validaMensajeAlert("la operación se ha efectuado correctamente", driver);

		} catch (Exception e) {
			msg = "Error al modificar el contrato";
		}

		return msg;
	}

}
