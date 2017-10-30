package page.AtencionCliente;

import org.openqa.selenium.WebDriver;

import util.FunctionGeneric;

public class Contrato {
	FunctionGeneric funge = new FunctionGeneric();
	public String formModificaContrato(String diaPago, WebDriver driver) {
		String msg = "OK";
		try {
			msg = FunctionGeneric.selecCBO("Combobox Dia de Pago", "name", "MPMF_DIAVCTO", diaPago, false, driver);
			if (!msg.equals("OK"))
				return msg;
			// msg = FunctionGeneric.setTextObjectByXpath("OSERVACION 1", "INPUT", "name",
			// "OBSERVACION1", "click", driver);
			msg = FunctionGeneric.setTextObjectByXpath("OSERVACION 1", "INPUT", "name", "OBSERVACION1",
					"PRUEBA AUTOMATIZADA TSOFT", "click", driver);
			if (!msg.equals("OK"))
				return msg;
			FunctionGeneric.addScreenEvi("Formulario Modificar Contrato", "Pass");
			// //IMG[@id="imgconfirmarmantMantenimientoFormMANTENIMIENTO"]
			msg = FunctionGeneric.clickObjectByXpath("Botón Aceptar", "IMG", "id",
					"imgconfirmarmantMantenimientoFormMANTENIMIENTO", "click", driver);
			if (!msg.equals("OK"))
				return msg;
			FunctionGeneric.addScreenEvi("Modificación de contrato", "Pass");
			
			msg = funge.validaMensajeAlert("la operación se ha efectuado correctamente", driver);
			if (!msg.equals("OK"))
				return msg;

		} catch (Exception e) {
			System.out.println("ERROR EN FORMULARIO MODIFICA CONTRATO " + e.getMessage());
			msg = "ERROR EN FORMULARIO MODIFICA CONTRATO ";
		}
		return msg;
	}

}
