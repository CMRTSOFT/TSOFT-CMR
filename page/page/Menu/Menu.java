package page.Menu;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import util.FunctionGeneric;

public class Menu {

	public static String menuAperturaInmediata(WebDriver driver) {

		String msg = "OK";

		try {

			msg = FunctionGeneric.clickObjectByXpath("Men� Admisi�n", "table", "id", "BotonBarraMenuBarraMenuAdmisi�n",
					"click", driver);
			if (!msg.equals("OK"))
				return msg;

			msg = FunctionGeneric.clickObjectByXpath("Men� Apertura Inmediata", "td", "id",
					"MenuOptionBarraMenuAdmisi�nApertura Inmediata", "click", driver);
			if (!msg.equals("OK"))
				return msg;

			msg = FunctionGeneric.validarTexto("Monto Solicitud", "Acceder al Men� Apertura Inmediata", driver);

		} catch (Exception e) {
			msg = "Error en el Men� Apertura Inmediata";
		}

		return msg;
	}

	public static String menuBusquedaContrato(WebDriver driver) {

		String msg = "OK";

		try {

			msg = FunctionGeneric.clickObjectByXpath("Men� Atenci�n al Clientes", "table", "id",
					"BotonBarraMenuBarraMenuAtenci�n_al_cliente", "click", driver);
			if (!msg.equals("OK"))
				return msg;

			msg = FunctionGeneric.clickObjectByXpath("Men� B�squeda de Contrato", "td", "id",
					"MenuOptionBarraMenuAtenci�n_al_clienteB�squeda de contrato", "click", driver);
			if (!msg.equals("OK"))
				return msg;

			driver.switchTo().frame((new WebDriverWait(driver, 30)).until(
					ExpectedConditions.presenceOfElementLocated(By.xpath("//IFRAME[@src='paginas/index.jsp']"))));

			if (!FunctionGeneric.findObject("name", "NUMDOC", driver))
				msg = "No se logr� acceder a Busqueda Contrato";

		} catch (Exception e) {
			msg = "Error en Men� Busqueda Contrato";
		}

		return msg;
	}

	public static String subMenuModificacionCupo(WebDriver driver) {

		String msg = "OK";

		try {

			driver = FunctionGeneric.waitWindows(2, driver);

			msg = FunctionGeneric.clickObject("Men� Modificaciones", "id", "BotonBarraMenuBarraMenuModificaciones",
					"click", driver);
			if (!msg.equals("OK"))
				return msg;

			msg = FunctionGeneric.clickObject("Men� Modificar Cupo", "id",
					"MenuOptionBarraMenuModificacionesModificar Cupo", "click", driver);

		} catch (Exception e) {
			msg = "Error al acceder al Men� Modificaci�n de Cupo";
		}

		return msg;
	}

	public static String menuAdmisionEvaluador(WebDriver driver) {

		String msg = "OK";

		try {

			driver = FunctionGeneric.waitWindows(0, driver);

			msg = FunctionGeneric.clickObject("Menu Admisi�n", "id", "BotonBarraMenuBarraMenuAdmisi�n", "click",
					driver);
			if (!msg.equals("OK"))
				return msg;

			msg = FunctionGeneric.clickObject("Menu Evaluador", "id",
					"BotonBarraMenuAdmisi�nBarraMenuAdmisi�nEvaluador", "click", driver);
			if (!msg.equals("OK"))
				return msg;

			msg = FunctionGeneric.clickObject("Menu Evaluador de Solicitudes", "id",
					"MenuOptionBarraMenuAdmisi�nEvaluadorEvaluador de Solicitudes", "click", driver);

		} catch (Exception e) {
			msg = "Error en Men� Admisi�n Evaluador de Solicitudes";
		}

		return msg;
	}

	public static String subMenuDetalle(WebDriver driver) {

		String msg = "OK";

		try {

			driver = FunctionGeneric.waitWindows(2, driver);

			msg = FunctionGeneric.clickObjectByXpath("Men� Detalle", "td", "id", "MenuOptionBarraMenuDetalle", "enter",
					driver);
			if (!msg.equals("OK"))
				return msg;

			FunctionGeneric.clickObjectByXpath("Men� Detalle", "td", "id", "MenuOptionBarraMenuDetalle", "click",
					driver);

			Thread.sleep(4000);

		} catch (Exception e) {
			msg = "Error en Submen� Detalle";
		}

		return msg;
	}

	public static String subMenuSimulacionCC(WebDriver driver) {

		String msg = "OK";

		try {

			driver = FunctionGeneric.waitWindows(2, driver);
			msg = FunctionGeneric.clickObject("Men� Simulaci�n CC", "id", "MenuOptionBarraMenuSimulaci�n CC", "click",
					driver);

		} catch (Exception e) {
			System.out.println("Error Submen� Simulaci�n CC" + e.toString());
			msg = "Error Submen� Simulaci�n CC";
		}

		return msg;
	}

	public static String menuSolicitud(WebDriver driver) {

		String msg = "OK";

		try {

			driver = FunctionGeneric.waitWindows(2, driver);
			msg = FunctionGeneric.clickObject("Men� Solicitud", "id", "MenuOptionBarraMenuSolicitud", "click", driver);

		} catch (Exception e) {
			msg = "Error en Men� Solicitud";
		}

		return msg;
	}

	public static String menuComprasDeuda(WebDriver driver) {

		String msg = "OK";

		try {

			driver = FunctionGeneric.waitWindows(2, driver);
			msg = FunctionGeneric.clickObject("Men� Compras Deudas", "id", "MenuOptionBarraMenuCompras Deudas", "click",
					driver);

		} catch (Exception e) {
			msg = "Error en Men� Compras Deudas";
		}

		return msg;
	}

	public static String menuFidelizacion(WebDriver driver) {

		String msg = "OK";

		try {

			msg = FunctionGeneric.clickObject("Men� Fidelizaci�n", "id", "MenuOptionBarraMenuFidelizaci�n", "click",
					driver);

		} catch (Exception e) {
			msg = "Error en Men� Fidelizaci�n";
		}

		return msg;
	}

	public static String subMenuFideEstadoCuenta(WebDriver driver) {

		String msg = "OK";

		try {

			msg = FunctionGeneric.clickObject("Men� Titulares", "id", "MenuOptionBarraMenuFidelizaci�n", "click",
					driver);
			if (!msg.equals("OK"))
				return msg;

			msg = FunctionGeneric.clickObject("Men� Estado de Cuenta", "id", "MenuOptionBarraMenuFidelizaci�n", "click",
					driver);

		} catch (Exception e) {
			msg = "Error en Men� Fidelizaci�n Estado de Cuenta";
		}

		return msg;
	}

	public static String subMenuFideCuentasGlobales(WebDriver driver) {

		String msg = "OK";

		try {

			msg = FunctionGeneric.clickObject("Men� Titulares", "id", "BotonBarraMenuBarraMenuTitulares_Cuentas",
					"click", driver);
			if (!msg.equals("OK"))
				return msg;

			msg = FunctionGeneric.clickObject("Men� Global Cuenta", "id",
					"MenuOptionBarraMenuTitulares_CuentasConsulta Global Cuenta", "click", driver);

		} catch (Exception e) {
			msg = "Error en el Men� Cuentas Globales";
		}

		return msg;
	}

	public static String menuTarjetasClaves(WebDriver driver) {

		String msg = "OK";

		try {

			driver = FunctionGeneric.waitWindows(2, driver);

			msg = FunctionGeneric.clickObject("Men� Tarjetas Claves", "id", "MenuOptionBarraMenuTarjetas Clave",
					"click", driver);

		} catch (Exception e) {
			msg = "Error en Men� Tarjetas Claves";
		}

		return msg;
	}

	public static String menuRechazosMerchant(WebDriver driver) {

		String msg = "OK";

		try {

			msg = FunctionGeneric.clickObject("Men� Adquirencia", "id", "BotonBarraMenuBarraMenuAdquirencia", "click",
					driver);
			if (!msg.equals("OK"))
				return msg;

			msg = FunctionGeneric.clickObject("Men� Rechazos Merchant", "id",
					"MenuOptionBarraMenuAdquirenciaRechazos Merchant", "click", driver);

		} catch (Exception e) {

			msg = "Error en Men� Adquirencia Rechazos Merchant";
		}

		return msg;
	}

	public static String menuMantenimientoMerchant(WebDriver driver) {

		String msg = "OK";

		try {

			msg = FunctionGeneric.clickObject("Men� Adquirencia", "id", "BotonBarraMenuBarraMenuAdquirencia", "click",
					driver);
			if (!msg.equals("OK"))
				return msg;

			msg = FunctionGeneric.clickObject("Men� Merchant", "id",
					"BotonBarraMenuAdquirenciaBarraMenuAdquirenciaMerchant", "click", driver);
			if (!msg.equals("OK"))
				return msg;

			msg = FunctionGeneric.clickObject("Men� Mantenimiento Merchant", "id",
					"MenuOptionBarraMenuAdquirenciaMerchantMantenimiento Merchant", "click", driver);

		} catch (Exception e) {
			msg = "Error en el Men� Mantenimiento Merchant";
		}

		return msg;
	}

	public static String menuNegocioEmisorContrato(WebDriver driver) {

		String msg = "OK";

		try {

			msg = FunctionGeneric.clickObject("Men� Negocio Emisor", "id", "BotonBarraMenuBarraMenuNegocio_Emisor",
					"click", driver);
			if (!msg.equals("OK"))
				return msg;

			msg = FunctionGeneric.clickObject("Men� Contrato", "id",
					"BotonBarraMenuNegocio_EmisorBarraMenuNegocio_EmisorContrato", "click", driver);
			if (!msg.equals("OK"))
				return msg;

			msg = FunctionGeneric.clickObject("Men� Mantenimiento de Contratos", "id",
					"MenuOptionBarraMenuNegocio_EmisorContratoMantenimiento de contratos", "click", driver);

		} catch (Exception e) {
			msg = "Error en el Men� Negocio Emisor Contrato";
		}

		return msg;
	}

	public static String menuNegocioEmisor(WebDriver driver) {

		String msg = "OK";

		try {

			msg = FunctionGeneric.clickObject("Men� Negocio Emisor", "id", "BotonBarraMenuBarraMenuNegocio_Emisor",
					"click", driver);
			if (!msg.equals("OK"))
				return msg;

			msg = FunctionGeneric.clickObject("Men� Tarjeta", "id",
					"BotonBarraMenuNegocio_EmisorBarraMenuNegocio_EmisorTarjeta", "click", driver);
			if (!msg.equals("OK"))
				return msg;

			msg = FunctionGeneric.clickObject("Men� Mantenimiento de Tarjetas", "id",
					"MenuOptionBarraMenuNegocio_EmisorTarjetaMantenimiento de tarjetas", "click", driver);

		} catch (Exception e) {
			msg = "Error Men� Negocio Emisor Mantenimiento de Tarjetas";
		}

		return msg;
	}

	public static String menuSeguridadPerfil(WebDriver driver) {

		String msg = "OK";

		try {

			msg = FunctionGeneric.clickObjectByXpath("Men� Seguridad", "table", "id",
					"BotonBarraMenuBarraMenuSeguridad", "click", driver);
			if (!msg.equals("OK"))
				return msg;

			msg = FunctionGeneric.clickObject("Sub Men� Perfil", "id",
					"MenuOptionBarraMenuSeguridadPerfiles de Usuario", "click", driver);

		} catch (Exception e) {
			msg = "Error Men� Seguridad Perfil de Usuario";
		}

		return msg;
	}

	public static String menuSeguridadUsuario(WebDriver driver) {

		String msg = "OK";

		try {

			msg = FunctionGeneric.clickObjectByXpath("Men� Seguridad", "INPUT", "id",
					"BotonBarraMenuBarraMenuSeguridad", "click", driver);
			if (!msg.equals("OK"))
				return msg;

			msg = FunctionGeneric.clickObjectByXpath("Men� Usuario", "INPUT", "td",
					"MenuOptionBarraMenuSeguridadUsuarios", "click", driver);

		} catch (Exception e) {
			msg = "Error Men� Seguridad Usuario";
		}

		return msg;
	}

	public static String menuVentaSeguros(WebDriver driver) {

		String msg = "OK";

		try {

			driver = FunctionGeneric.waitWindows(2, driver);

			msg = FunctionGeneric.clickObject("Men� Seguro de Ventas", "id", "MenuOptionBarraMenuVenta de Seguros",
					"click", driver);

		} catch (Exception e) {
			msg = "Error en Men� Seguro de Ventas";
		}

		return msg;
	}

	public static String menuVencimiento(WebDriver driver) {

		String msg = "OK";

		try {

			if (FunctionGeneric.validaAlert(driver))
				driver.switchTo().alert().accept();

			driver = FunctionGeneric.waitWindows(2, driver);

			msg = FunctionGeneric.clickObject("Men� Vencimientos", "id", "MenuOptionBarraMenuVencimientos", "click",
					driver);

		} catch (Exception e) {
			msg = "Error en el Men� Vencimientos";
		}

		return msg;
	}

	public static String menuModificacionContrato(WebDriver driver) {

		String msg = "OK";

		try {

			msg = FunctionGeneric.clickObjectByXpath("Men� Modificaciones ", "table", "id",
					"BotonBarraMenuBarraMenuModificaciones", "click", driver);
			if (!msg.equals("OK"))
				return msg;

			msg = FunctionGeneric.clickObjectByXpath("Men� Contrato", "td", "id",
					"MenuOptionBarraMenuModificacionesContratos", "click", driver);
			if (!msg.equals("OK"))
				return msg;

			driver.switchTo().frame((new WebDriverWait(driver, 30)).until(
					ExpectedConditions.presenceOfElementLocated(By.xpath("//IFRAME[@src='paginas/esperaAux.jsp']"))));

			if (!FunctionGeneric.findObject("name", "SISTEMA", driver))
				msg = "No se ha logrado acceder a pantalla Contrato";

		} catch (Exception e) {
			msg = "Error en el Men� Modificaciones de Contrato";
		}

		return msg;
	}

	public static String menuModificacionCliente(WebDriver driver) {

		String msg = "OK";

		try {

			msg = FunctionGeneric.clickObjectByXpath("Men� Modificaciones ", "table", "id",
					"BotonBarraMenuBarraMenuModificaciones", "click", driver);
			if (!msg.equals("OK"))
				return msg;

			msg = FunctionGeneric.clickObjectByXpath("Men� Cliente", "td", "id",
					"MenuOptionBarraMenuModificacionesCliente", "click", driver);
			if (!msg.equals("OK"))
				return msg;

			driver.switchTo().frame((new WebDriverWait(driver, 30)).until(
					ExpectedConditions.presenceOfElementLocated(By.xpath("//IFRAME[@src='paginas/esperaAux.jsp']"))));

			if (!FunctionGeneric.findObject("name", "SISTEMA", driver))
				msg = "No se ha logrado acceder a pantalla contrato";

		} catch (Exception e) {
			msg = "Error en el Men� Modificaciones Cliente";
		}

		return msg;
	}

	public static String subMenuModificacionCupoSA(WebDriver driver) {

		String msg = "OK";

		try {

			driver = FunctionGeneric.waitWindows(2, driver);

			msg = FunctionGeneric.clickObject("Men� Modificaciones", "id", "BotonBarraMenuBarraMenuModificaciones",
					"click", driver);
			if (!msg.equals("OK"))
				return msg;

			msg = FunctionGeneric.clickObjectByXpath("Men� Modificar Cupo SA", "TD", "id",
					"MenuOptionBarraMenuModificacionesModificar Cupo SA", "click", driver);

		} catch (Exception e) {
			msg = "Error al acceder al Men� Modificaci�n de Cupo SA";
		}

		return msg;
	}
}
