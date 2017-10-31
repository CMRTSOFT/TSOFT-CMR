package page.AtencionCliente;

import org.openqa.selenium.WebDriver;

import util.FunctionGeneric;

public class Cliente {
	FunctionGeneric funge = new FunctionGeneric();
	
	public String formModificaCliente(String calidadVivienda, String celular, WebDriver driver) {
		String msg = "OK";
		try {
			// name=CALIDAD calidad
			msg = FunctionGeneric.selecCBO("Combobox Dia de Pago", "name", "CALIDAD", calidadVivienda, false, driver);
			if (!msg.equals("OK"))
				return msg;
			// msg = FunctionGeneric.setTextObjectByXpath("OSERVACION 1", "INPUT", "name",
			// "OBSERVACION1", "click", driver);
			msg = FunctionGeneric.setTextObjectByXpath("OSERVACION 1", "INPUT", "name", "OBSERVACION1",
					celular, "click", driver);
			if (!msg.equals("OK"))
				return msg;
			FunctionGeneric.addScreenEvi("Formulario Modificar Contrato", "Pass");
			// //IMG[@id="imgconfirmarmantMantenimientoFormMANTENIMIENTO"]
			msg = FunctionGeneric.clickObjectByXpath("Bot�n Aceptar", "IMG", "id",
					"imgconfirmarmantMantenimientoFormMANTENIMIENTO", "click", driver);
			if (!msg.equals("OK"))
				return msg;
			FunctionGeneric.addScreenEvi("Modificaci�n de contrato", "Pass");
			
			msg = funge.validaMensajeAlert("la operaci�n se ha efectuado correctamente", driver);
			if (!msg.equals("OK"))
				return msg;

		} catch (Exception e) {
			System.out.println("ERROR EN FORMULARIO MODIFICA CONTRATO " + e.getMessage());
			msg = "ERROR EN FORMULARIO MODIFICA CONTRATO ";
		}
		return msg;
	}
}
