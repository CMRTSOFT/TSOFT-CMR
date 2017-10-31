package page.Menu;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import util.FunctionGeneric;

public class Menu {

	public String menuAperturaInmediata(WebDriver driver) {

		String msg = "OK";

		try {

			msg = FunctionGeneric.clickObjectByXpath("Menú Admisión", "table", "id", "BotonBarraMenuBarraMenuAdmisión",
					"click", driver);
			if (!msg.equals("OK"))
				return msg;

			msg = FunctionGeneric.clickObjectByXpath("Menú Apertura Inmediata", "td", "id",
					"MenuOptionBarraMenuAdmisiónApertura Inmediata", "click", driver);
			if (!msg.equals("OK"))
				return msg;

			msg = FunctionGeneric.validarTexto("Monto Solicitud", "Acceder al Menú Apertura Inmediata", driver);

		} catch (Exception e) {
			msg = "Error en el Menú Apertura Inmediata";
		}

		return msg;
	}

	public String menuBusquedaContrato(WebDriver driver) {

		String msg = "OK";

		try {

			msg = FunctionGeneric.clickObjectByXpath("Menú Atención al Clientes", "table", "id",
					"BotonBarraMenuBarraMenuAtención_al_cliente", "click", driver);
			if (!msg.equals("OK"))
				return msg;

			msg = FunctionGeneric.clickObjectByXpath("Menú Búsqueda de Contrato", "td", "id",
					"MenuOptionBarraMenuAtención_al_clienteBúsqueda de contrato", "click", driver);
			if (!msg.equals("OK"))
				return msg;

			msg = FunctionGeneric.validarTexto("Número de Contrato",
					"Acceder el Menú Atención al Cliente Busqueda de Contrato", driver);

		} catch (Exception e) {
			msg = "Error en Menú Busqueda Contrato";
		}

		return msg;
	}

	public String subMenuModificacionCupo(WebDriver driver) {

		String msg = "OK";

		try {

			driver = FunctionGeneric.waitWindows(2, driver);

			msg = FunctionGeneric.clickObject("Menú Modificaciones", "id", "BotonBarraMenuBarraMenuModificaciones",
					"click", driver);
			if (!msg.equals("OK"))
				return msg;

			msg = FunctionGeneric.clickObject("Menú Modificar Cupo", "id",
					"MenuOptionBarraMenuModificacionesModificar Cupo", "click", driver);
			if (!msg.equals("OK"))
				return msg;

			driver.switchTo().frame((new WebDriverWait(driver, 30))
					.until(ExpectedConditions.presenceOfElementLocated(By.name("InterfaceContrato"))));

			msg = FunctionGeneric.setTextObject("Cupo", "name", "CUPOSOL", "300000", "set", false, driver);
			if (!msg.equals("OK"))
				return msg;

			msg = FunctionGeneric.clickObject("Botón Aceptar Modificaciones", "name", "ACEPTAR", "click", driver);
			if (!msg.equals("OK"))
				return msg;

			WebDriverWait wait = new WebDriverWait(driver, 15);
			Alert myAlert = wait.until(ExpectedConditions.alertIsPresent());
			myAlert.accept();

			driver = FunctionGeneric.waitWindows(3, driver);

			msg = FunctionGeneric.clickObject("Botón Crear", "name", "Enviar", "click", driver);

		} catch (Exception e) {
			msg = "Error al acceder al Menú Modificación de Cupo";
		}

		return msg;
	}

	public String menuAdmisionEvaluador(WebDriver driver) {

		String msg = "OK";

		try {

			driver = FunctionGeneric.waitWindows(0, driver);

			msg = FunctionGeneric.clickObject("Menu Admisión", "id", "BotonBarraMenuBarraMenuAdmisión", "click",
					driver);
			if (!msg.equals("OK"))
				return msg;

			msg = FunctionGeneric.clickObject("Menu Evaluador", "id",
					"BotonBarraMenuAdmisiónBarraMenuAdmisiónEvaluador", "click", driver);
			if (!msg.equals("OK"))
				return msg;

			msg = FunctionGeneric.clickObject("Menu Evaluador de Solicitudes", "id",
					"MenuOptionBarraMenuAdmisiónEvaluadorEvaluador de Solicitudes", "click", driver);

		} catch (Exception e) {
			msg = "Error en Menú Admisión Evaluador de Solicitudes";
		}

		return msg;
	}

	public String subMenuDetalle(WebDriver driver) {

		String msg = "OK";

		try {

			driver = FunctionGeneric.waitWindows(2, driver);

			msg = FunctionGeneric.clickObjectByXpath("Menú Detalle", "td", "id", "MenuOptionBarraMenuDetalle", "enter",
					driver);
			if (!msg.equals("OK"))
				return msg;

			FunctionGeneric.clickObjectByXpath("Menú Detalle", "td", "id", "MenuOptionBarraMenuDetalle", "click",
					driver);

			Thread.sleep(4000);

		} catch (Exception e) {
			msg = "Error en Submenú Detalle";
		}

		return msg;
	}

	public String subMenuSimulacionCC(WebDriver driver) {

		String msg = "OK";

		try {

			driver = FunctionGeneric.waitWindows(2, driver);
			msg = FunctionGeneric.clickObject("Menú Simulación CC", "id", "MenuOptionBarraMenuSimulación CC", "click",
					driver);

		} catch (Exception e) {
			System.out.println("Error Submenú Simulación CC" + e.toString());
			msg = "Error Submenú Simulación CC";
		}

		return msg;
	}

	public String menuSolicitud(WebDriver driver) {

		String msg = "OK";

		try {

			driver = FunctionGeneric.waitWindows(2, driver);
			msg = FunctionGeneric.clickObject("Menú Solicitud", "id", "MenuOptionBarraMenuSolicitud", "click", driver);

		} catch (Exception e) {
			msg = "Error en Menú Solicitud";
		}

		return msg;
	}

	public String menuComprasDeuda(WebDriver driver) {

		String msg = "OK";

		try {

			driver = FunctionGeneric.waitWindows(2, driver);
			msg = FunctionGeneric.clickObject("Menú Compras Deudas", "id", "MenuOptionBarraMenuCompras Deudas", "click",
					driver);

		} catch (Exception e) {
			msg = "Error en Menú Compras Deudas";
		}

		return msg;
	}

	public String menuFidelizacion(WebDriver driver) {

		String msg = "OK";

		try {

			msg = FunctionGeneric.clickObject("Menú Fidelización", "id", "MenuOptionBarraMenuFidelización", "click",
					driver);

		} catch (Exception e) {
			msg = "Error en Menú Fidelización";
		}

		return msg;
	}

	public String subMenuFideEstadoCuenta(WebDriver driver) {

		String msg = "OK";

		try {

			msg = FunctionGeneric.clickObject("Menú Titulares", "id", "MenuOptionBarraMenuFidelización", "click",
					driver);
			if (!msg.equals("OK"))
				return msg;

			msg = FunctionGeneric.clickObject("Menú Estado de Cuenta", "id", "MenuOptionBarraMenuFidelización", "click",
					driver);

		} catch (Exception e) {
			msg = "Error en Menú Fidelización Estado de Cuenta";
		}

		return msg;
	}

	public String subMenuFideCuentasGlobales(WebDriver driver) {

		String msg = "OK";

		try {

			msg = FunctionGeneric.clickObject("Menú Titulares", "id", "BotonBarraMenuBarraMenuTitulares_Cuentas",
					"click", driver);
			if (!msg.equals("OK"))
				return msg;

			msg = FunctionGeneric.clickObject("Menú Global Cuenta", "id",
					"MenuOptionBarraMenuTitulares_CuentasConsulta Global Cuenta", "click", driver);

		} catch (Exception e) {
			msg = "Error en el Menú Cuentas Globales";
		}

		return msg;
	}

	public String menuTarjetasClaves(WebDriver driver) {

		String msg = "OK";

		try {

			driver = FunctionGeneric.waitWindows(2, driver);

			msg = FunctionGeneric.clickObject("Menú Tarjetas Claves", "id", "MenuOptionBarraMenuTarjetas Clave",
					"click", driver);

		} catch (Exception e) {
			msg = "Error en Menú Tarjetas Claves";
		}

		return msg;
	}

	public String menuRechazosMerchant(WebDriver driver) {

		String msg = "OK";

		try {

			msg = FunctionGeneric.clickObject("Menú Adquirencia", "id", "BotonBarraMenuBarraMenuAdquirencia", "click",
					driver);
			if (!msg.equals("OK"))
				return msg;

			msg = FunctionGeneric.clickObject("Menú Rechazos Merchant", "id",
					"MenuOptionBarraMenuAdquirenciaRechazos Merchant", "click", driver);

		} catch (Exception e) {

			msg = "Error en Menú Adquirencia Rechazos Merchant";
		}

		return msg;
	}

	public String menuMantenimientoMerchant(WebDriver driver) {

		String msg = "OK";

		try {

			msg = FunctionGeneric.clickObject("Menú Adquirencia", "id", "BotonBarraMenuBarraMenuAdquirencia", "click",
					driver);
			if (!msg.equals("OK"))
				return msg;

			msg = FunctionGeneric.clickObject("Menú Merchant", "id",
					"BotonBarraMenuAdquirenciaBarraMenuAdquirenciaMerchant", "click", driver);
			if (!msg.equals("OK"))
				return msg;

			msg = FunctionGeneric.clickObject("Menú Mantenimiento Merchant", "id",
					"MenuOptionBarraMenuAdquirenciaMerchantMantenimiento Merchant", "click", driver);

		} catch (Exception e) {
			msg = "Error en el Menú Mantenimiento Merchant";
		}

		return msg;
	}

	public String menuNegocioEmisorContrato(WebDriver driver) {

		String msg = "OK";

		try {

			msg = FunctionGeneric.clickObject("Menú Negocio Emisor", "id", "BotonBarraMenuBarraMenuNegocio_Emisor",
					"click", driver);
			if (!msg.equals("OK"))
				return msg;

			msg = FunctionGeneric.clickObject("Menú Contrato", "id",
					"BotonBarraMenuNegocio_EmisorBarraMenuNegocio_EmisorContrato", "click", driver);
			if (!msg.equals("OK"))
				return msg;

			msg = FunctionGeneric.clickObject("Menú Mantenimiento de Contratos", "id",
					"MenuOptionBarraMenuNegocio_EmisorContratoMantenimiento de contratos", "click", driver);

		} catch (Exception e) {
			msg = "Error en el Menú Negocio Emisor Contrato";
		}

		return msg;
	}

	public String menuNegocioEmisor(WebDriver driver) {

		String msg = "OK";

		try {

			msg = FunctionGeneric.clickObject("Menú Negocio Emisor", "id", "BotonBarraMenuBarraMenuNegocio_Emisor",
					"click", driver);
			if (!msg.equals("OK"))
				return msg;

			msg = FunctionGeneric.clickObject("Menú Tarjeta", "id",
					"BotonBarraMenuNegocio_EmisorBarraMenuNegocio_EmisorTarjeta", "click", driver);
			if (!msg.equals("OK"))
				return msg;

			msg = FunctionGeneric.clickObject("Menú Mantenimiento de Tarjetas", "id",
					"MenuOptionBarraMenuNegocio_EmisorTarjetaMantenimiento de tarjetas", "click", driver);

		} catch (Exception e) {
			msg = "Error Menú Negocio Emisor Mantenimiento de Tarjetas";
		}

		return msg;
	}

	public String menuSeguridadPerfil(WebDriver driver) {

		String msg = "OK";

		try {

			msg = FunctionGeneric.clickObjectByXpath("Menú Seguridad", "table", "id",
					"BotonBarraMenuBarraMenuSeguridad", "click", driver);
			if (!msg.equals("OK"))
				return msg;

			msg = FunctionGeneric.clickObject("Sub Menú Perfil", "id",
					"MenuOptionBarraMenuSeguridadPerfiles de Usuario", "click", driver);

		} catch (Exception e) {
			msg = "Error Menú Seguridad Perfil de Usuario";
		}

		return msg;
	}

	public String menuSeguridadUsuario(WebDriver driver) {

		String msg = "OK";

		try {

			msg = FunctionGeneric.clickObjectByXpath("Menú Seguridad", "INPUT", "id",
					"BotonBarraMenuBarraMenuSeguridad", "click", driver);
			if (!msg.equals("OK"))
				return msg;

			msg = FunctionGeneric.clickObjectByXpath("Menú Usuario", "INPUT", "td",
					"MenuOptionBarraMenuSeguridadUsuarios", "click", driver);

		} catch (Exception e) {
			msg = "Error Menú Seguridad Usuario";
		}

		return msg;
	}

	public String menuVentaSeguros(WebDriver driver) {

		String msg = "OK";

		try {

			driver = FunctionGeneric.waitWindows(2, driver);

			msg = FunctionGeneric.clickObject("Menú Seguro de Ventas", "id", "MenuOptionBarraMenuVenta de Seguros",
					"click", driver);

		} catch (Exception e) {
			msg = "Error en Menú Seguro de Ventas";
		}

		return msg;
	}

	public String menuVencimiento(WebDriver driver) {

		String msg = "OK";

		try {

			if (FunctionGeneric.validaAlert(driver))
				driver.switchTo().alert().accept();

			driver = FunctionGeneric.waitWindows(2, driver);

			msg = FunctionGeneric.clickObject("Menú Vencimientos", "id", "MenuOptionBarraMenuVencimientos", "click",
					driver);

		} catch (Exception e) {
			msg = "Error en el Menú Vencimientos";
		}

		return msg;
	}

	public String menuModificacionContrato(WebDriver driver) {

		String msg = "OK";

		try {

			msg = FunctionGeneric.clickObjectByXpath("Menú Modificaciones ", "table", "id",
					"BotonBarraMenuBarraMenuModificaciones", "click", driver);
			if (!msg.equals("OK"))
				return msg;

			msg = FunctionGeneric.clickObjectByXpath("Menú Contrato", "td", "id",
					"MenuOptionBarraMenuModificacionesContratos", "click", driver);
			if (!msg.equals("OK"))
				return msg;

			driver.switchTo().frame((new WebDriverWait(driver, 30)).until(
					ExpectedConditions.presenceOfElementLocated(By.xpath("//IFRAME[@src='paginas/esperaAux.jsp']"))));

			if (!FunctionGeneric.findObject("name", "SISTEMA", driver))
				msg = "No se ha logrado acceder a pantalla Contrato";

		} catch (Exception e) {
			msg = "Error en el Menú Modificaciones de Contrato";
		}

		return msg;
	}

	public String menuModificacionCliente(WebDriver driver) {

		String msg = "OK";

		try {

			msg = FunctionGeneric.clickObjectByXpath("Menú Modificaciones ", "table", "id",
					"BotonBarraMenuBarraMenuModificaciones", "click", driver);
			if (!msg.equals("OK"))
				return msg;

			msg = FunctionGeneric.clickObjectByXpath("Menú Cliente", "td", "id",
					"MenuOptionBarraMenuModificacionesCliente", "click", driver);
			if (!msg.equals("OK"))
				return msg;

			driver.switchTo().frame((new WebDriverWait(driver, 30)).until(
					ExpectedConditions.presenceOfElementLocated(By.xpath("//IFRAME[@src='paginas/esperaAux.jsp']"))));

			if (!FunctionGeneric.findObject("name", "SISTEMA", driver))
				msg = "No se ha logrado acceder a pantalla contrato";

		} catch (Exception e) {
			msg = "Error en el Menú Modificaciones Cliente";
		}

		return msg;
	}
}
