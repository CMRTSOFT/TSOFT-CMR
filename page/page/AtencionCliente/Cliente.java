package page.AtencionCliente;

import org.openqa.selenium.WebDriver;
import util.FunctionGeneric;

public class Cliente {

	public static String formModificaCliente(String calidadVivienda, String celular, WebDriver driver) {

		String msg = "OK";

		try {

			msg = FunctionGeneric.selecCBO("Combobox Dia de Pago", "name", "CALIDAD", calidadVivienda, false, driver);
			if (!msg.equals("OK"))
				return msg;

			msg = FunctionGeneric.setTextObjectByXpath("OSERVACION 1", "INPUT", "name", "OBSERVACION1", celular,
					"click", driver);
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
			msg = "Error al modificar el cliente";
		}

		return msg;
	}
}
