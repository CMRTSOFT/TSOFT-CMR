package page.Menu;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
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

			WebElement menuModificaciones = (new WebDriverWait(driver, 25))
					.until(ExpectedConditions.presenceOfElementLocated(By.id("BotonBarraMenuBarraMenuModificaciones")));

			menuModificaciones.click();

			Thread.sleep(1000);

			WebElement menuModificarCupo = driver
					.findElement(By.xpath("//td[@id='MenuOptionBarraMenuModificacionesModificar Cupo']"));
			menuModificarCupo.click();

			Thread.sleep(4000);

			WebElement FrameInterContrato = (new WebDriverWait(driver, 25))
					.until(ExpectedConditions.presenceOfElementLocated(By.name("InterfaceContrato")));

			driver.switchTo().frame(FrameInterContrato);

			WebElement txtCupo = driver.findElement(By.xpath("//input[@name='CUPOSOL']"));
			txtCupo.sendKeys("300000");

			WebElement btnAceptar = driver.findElement(By.name("ACEPTAR"));
			btnAceptar.click();

			WebDriverWait wait = new WebDriverWait(driver, 15);
			Alert myAlert = wait.until(ExpectedConditions.alertIsPresent());
			myAlert.accept();

			driver = FunctionGeneric.waitWindows(3, driver);

			WebElement btnCrear = driver.findElement(By.xpath("//img[@name='Enviar']"));
			btnCrear.click();

		} catch (Exception e) {
			System.out.println("Error MenÃº Busqueda Contrato  " + e.toString());
			msg = "Error MenÃº Busqueda Contrato " + e.toString();
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

			Set<String> winSet = driver.getWindowHandles();
			List<String> winList = new ArrayList<String>(winSet);
			if (!FunctionGeneric.waitWindows(2, winList, driver)) {
				msg = msg + "No se encontró el ventana de menú \n";
				return msg;
			}

			driver.switchTo().window(winList.get(winList.size() - 1));

			Thread.sleep(2000);
			WebElement subMenuSimulacionCC = (new WebDriverWait(driver, 30)).until(ExpectedConditions
					.presenceOfElementLocated(By.xpath("//td[@id='MenuOptionBarraMenuSimulación CC']")));
			subMenuSimulacionCC.click();

		} catch (Exception e) {
			System.out.println("Error Submenú Simulación CC" + e.toString());
			msg = "Error Submenú Simulación CC" + e.toString();
		}

		return msg;
	}

	public String menuSolicitud(WebDriver driver) {
		String msg = "OK";
		try {
			Set<String> winSet = driver.getWindowHandles();
			List<String> winList = new ArrayList<String>(winSet);
			if (!FunctionGeneric.waitWindows(2, winList, driver)) {
				msg = "No se encontró Ventana 2";
				return msg;
			}
			winSet = driver.getWindowHandles();
			winList = new ArrayList<String>(winSet);
			driver.switchTo().window(winList.get(winList.size() - 1));

			WebElement menuSolicitud = (new WebDriverWait(driver, 60)).until(
					ExpectedConditions.presenceOfElementLocated(By.xpath("//td[@id='MenuOptionBarraMenuSolicitud']")));
			menuSolicitud.click();

		} catch (Exception e) {
			System.out.println("ERROR EN MENÚ SOLICITUD " + e.getMessage());
			msg = "ERROR EN MENÚ SOLICITUD " + e.getMessage();
		}
		return msg;
	}

	public String menuComprasDeuda(WebDriver driver) {
		String msg = "OK";
		try {
			// MenuOptionBarraMenuCompras Deudas

			Thread.sleep(3000);
			Set<String> winSet = driver.getWindowHandles();
			List<String> winList = new ArrayList<String>(winSet);
			if (!FunctionGeneric.waitWindows(2, winList, driver)) {
				msg = msg + "No se encontró el ventana de menú \n";
				return msg;
			}
			winSet = driver.getWindowHandles();
			winList = new ArrayList<String>(winSet);
			driver.switchTo().window(winList.get(winList.size() - 1));
			WebElement menuCompras = (new WebDriverWait(driver, 60)).until(ExpectedConditions
					.presenceOfElementLocated(By.xpath("//TD[@id='MenuOptionBarraMenuCompras Deudas']")));
			menuCompras.click();

		} catch (Exception e) {
			System.out.println("ERROR AL ENCONTRAR MENU COMPRAS DEUDAS " + e.getMessage());
			msg = "ERROR AL ENCONTRAR MENU COMPRAS DEUDAS " + e.getMessage();
		}
		return msg;
	}

	public String menuFidelizacion(WebDriver driver) {
		String msg = "OK";
		try {
			// TD ID MenuOptionBarraMenuFidelización
			WebElement menuFidelizacion = (new WebDriverWait(driver, 60)).until(ExpectedConditions
					.presenceOfElementLocated(By.xpath("//TD[@id='MenuOptionBarraMenuFidelización']")));
			menuFidelizacion.click();

		} catch (Exception e) {
			System.out.println("ERROR EN EL MENÚ FIDELIZACIÓN " + e.getMessage());
			msg = "ERROR EN EL MENÚ FIDELIZACIÓN " + e.getMessage();
		}
		return msg;
	}

	public String subMenuFideEstadoCuenta(WebDriver driver) {
		String msg = "OK";
		try {
			// MenuOptionBarraMenuTitulares_CuentasEE CC
			WebElement menuTitulares = (new WebDriverWait(driver, 60)).until(ExpectedConditions
					.presenceOfElementLocated(By.xpath("//TABLE[@id='BotonBarraMenuBarraMenuTitulares_Cuentas']")));
			menuTitulares.click();
			WebElement subMenuEstadoCuenta = (new WebDriverWait(driver, 60)).until(ExpectedConditions
					.presenceOfElementLocated(By.xpath("//TD[@id='MenuOptionBarraMenuTitulares_CuentasEE CC']")));
			subMenuEstadoCuenta.click();
		} catch (Exception e) {
			System.out.println("ERROR EN EL MENÚ FIDELIZACIÓN ESTADO CUENTA " + e.getMessage());
			msg = "ERROR EN EL MENÚ FIDELIZACIÓN ESTADO CUENTA " + e.getMessage();
		}
		return msg;
	}

	public String subMenuFideCuentasGlobales(WebDriver driver) {
		String msg = "OK";
		try {
			// Table id BotonBarraMenuBarraMenuTitulares_Cuentas
			// TD ID MenuOptionBarraMenuTitulares_CuentasConsulta Global Cuentas
			WebElement menuGlobalCuenta = (new WebDriverWait(driver, 60)).until(ExpectedConditions
					.presenceOfElementLocated(By.xpath("//TABLE[@id='BotonBarraMenuBarraMenuTitulares_Cuentas']")));
			menuGlobalCuenta.click();
			WebElement subMenuGlobalCuenta = (new WebDriverWait(driver, 60))
					.until(ExpectedConditions.presenceOfElementLocated(
							By.xpath("//TD[@id='MenuOptionBarraMenuTitulares_CuentasConsulta Global Cuentas']")));
			subMenuGlobalCuenta.click();

		} catch (Exception e) {
			System.out.println("ERROR EN EL MENU CUENTAS GLOBALES " + e.getMessage());
			msg = "ERROR EN EL MENU CUENTAS GLOBALES " + e.getMessage();
		}
		return msg;
	}

	public String menuTarjetasClaves(WebDriver driver) {
		String msg = "OK";
		try {
			Thread.sleep(4000);
			Set<String> winSet = driver.getWindowHandles();
			List<String> winList = new ArrayList<String>(winSet);
			if (!FunctionGeneric.waitWindows(2, winList, driver)) {
				msg = msg + "No se encontró el ventana de menú \n";
				return msg;
			}
			winSet = driver.getWindowHandles();
			winList = new ArrayList<String>(winSet);
			driver.switchTo().window(winList.get(winList.size() - 1));

			WebElement menuTarjetaClave = (new WebDriverWait(driver, 60)).until(ExpectedConditions
					.presenceOfElementLocated(By.xpath("//TD[@id='MenuOptionBarraMenuTarjetas Clave']")));
			menuTarjetaClave.click();
		} catch (Exception e) {
			System.out.println("ERROR AL SELECCIONAR MENU TARJETAS CLAVES " + e.getMessage());
			msg = "ERROR AL SELECCIONAR MENU TARJETAS CLAVES " + e.getMessage();
		}
		return msg;
	}

	public String menuRechazosMerchant(WebDriver driver) {
		String msg = "OK";
		try {
			WebElement Adquirencia = driver.findElement(By.xpath("//table[@id='BotonBarraMenuBarraMenuAdquirencia']"));
			WebElement subRechazoMerchant = driver
					.findElement(By.xpath("//td[@id='MenuOptionBarraMenuAdquirenciaRechazos Merchant']"));
			Adquirencia.click();
			subRechazoMerchant.click();
		} catch (Exception e) {
			System.out.println("ERROR EN EL MENU RECHAZOS MERCHANT " + e.getMessage());
			msg = "ERROR EN EL MENU RECHAZOS MERCHANT " + e.getMessage();
		}
		return msg;
	}
	
	public String menuMantenimientoMerchant(WebDriver driver) {
		String msg = "OK";
		try {
			WebElement menuAdquirencia = (new WebDriverWait(driver, 60)).until(ExpectedConditions
					.presenceOfElementLocated(By.xpath("//TABLE[@id='BotonBarraMenuBarraMenuAdquirencia']")));
			menuAdquirencia.click();
			WebElement menuMerchant = (new WebDriverWait(driver, 60)).until(ExpectedConditions.presenceOfElementLocated(
					By.xpath("//TABLE[@id='BotonBarraMenuAdquirenciaBarraMenuAdquirenciaMerchant']")));
			menuMerchant.click();
			WebElement menuMantMerchant = (new WebDriverWait(driver, 60))
					.until(ExpectedConditions.presenceOfElementLocated(
							By.xpath("//TD[@id='MenuOptionBarraMenuAdquirenciaMerchantMantenimiento Merchant']")));
			menuMantMerchant.click();

		} catch (Exception e) {
			System.out.println("ERROR EN EL MENÚ MANTENIMIENTO MERCHANT " + e.getMessage());
			msg = "ERROR EN EL MENÚ MANTENIMIENTO MERCHANT " + e.getMessage();
		}
		return msg;
	}
	
	public String menuNegocioEmisorContrato(WebDriver driver) {
		String msg = "OK";
		try {
			WebElement menuNegocioEmisor = (new WebDriverWait(driver, 60)).until(ExpectedConditions
					.presenceOfElementLocated(By.xpath("//TABLE[@id='BotonBarraMenuBarraMenuNegocio_Emisor']")));
			menuNegocioEmisor.click();
			WebElement subMenuContrato = (new WebDriverWait(driver, 60))
					.until(ExpectedConditions.presenceOfElementLocated(
							By.xpath("//TABLE[@id='BotonBarraMenuNegocio_EmisorBarraMenuNegocio_EmisorContrato']")));
			subMenuContrato.click();
			WebElement menuMantContra = (new WebDriverWait(driver, 60))
					.until(ExpectedConditions.presenceOfElementLocated(By
							.xpath("//TD[@id='MenuOptionBarraMenuNegocio_EmisorContratoMantenimiento de contratos']")));
			menuMantContra.click();
		} catch (Exception e) {
			System.out.println("ERROR EN EL MENÚ NEGOCIO EMISOR CONTRATO " + e.getMessage());
			msg = "ERROR EN EL MENÚ NEGOCIO EMISOR CONTRATO " + e.getMessage();
		}
		return msg;
	}
	
	public String menuNegocioEmisor(WebDriver driver) {
		String msg = "OK";
		try {
			WebElement menuNegocioEmisor = (new WebDriverWait(driver, 60)).until(ExpectedConditions
					.presenceOfElementLocated(By.xpath("//TABLE[@id='BotonBarraMenuBarraMenuNegocio_Emisor']")));
			menuNegocioEmisor.click();
			WebElement subMenuTarje = (new WebDriverWait(driver, 60)).until(ExpectedConditions.presenceOfElementLocated(
					By.xpath("//TABLE[@id='BotonBarraMenuNegocio_EmisorBarraMenuNegocio_EmisorTarjeta']")));
			subMenuTarje.click();
			WebElement menuMantTarje = (new WebDriverWait(driver, 60))
					.until(ExpectedConditions.presenceOfElementLocated(
							By.xpath("//TD[@id='MenuOptionBarraMenuNegocio_EmisorTarjetaMantenimiento de tarjetas']")));
			menuMantTarje.click();
		} catch (Exception e) {
			System.out.println("Error Menú Negocio Emisor " + e.getMessage());
			msg = "Error Menú Negocio Emisor " + e.getMessage();
		}
		return msg;
	}
	
	public String menuSeguridadPerfil(WebDriver driver) {
		String msg = "OK";
		try {
			/*if (driver.findElements(By.xpath("//table[@id='BotonBarraMenuBarraMenuSeguridad']")).size() == 0) {
				msg = "No se ha encontrado el Menú";
				return msg;
			}
			if (driver.findElements(By.xpath("//td[@id='MenuOptionBarraMenuSeguridadPerfiles de Usuario']"))
					.size() == 0) {
				msg = "No se ha encontrado el Menú";
				return msg;
			}
			WebElement menuSeguridad = driver.findElement(By.xpath("//table[@id='BotonBarraMenuBarraMenuSeguridad']"));
			menuSeguridad.click();
			WebElement subMenuPerfil = driver
					.findElement(By.xpath("//td[@id='MenuOptionBarraMenuSeguridadPerfiles de Usuario']"));

			subMenuPerfil.click();
			*/
			
			msg = FunctionGeneric.clickObjectByXpath("Menú Seguridad", "table", "id", "BotonBarraMenuBarraMenuSeguridad", "click", driver);
			if (!msg.equals("OK"))
				return msg;
			
			msg = FunctionGeneric.clickObject("Sub Menú Perfil", "id", "MenuOptionBarraMenuSeguridadPerfiles de Usuario", "click", driver);
			if (!msg.equals("OK"))
				return msg;

		} catch (Exception e) {
			System.out.println("Error Menú Seguridad " + e.toString());
			msg = "Error Menú Busqueda Contrato ";
		}
		return msg;
	}
	
	public String menuSeguridadUsuario(WebDriver driver) {
		String msg = "OK";
		try {
		
			msg = FunctionGeneric.clickObjectByXpath("Menú Seguridad", "INPUT", "id", "BotonBarraMenuBarraMenuSeguridad", "click", driver);
			if (!msg.equals("OK"))
				return msg;
			//WebElement menuSeguridad = driver.findElement(By.xpath("//table[@id='BotonBarraMenuBarraMenuSeguridad']"));
			//menuSeguridad.click();
			msg = FunctionGeneric.clickObjectByXpath("Sub Menú Usuario", "INPUT", "td", "MenuOptionBarraMenuSeguridadUsuarios", "click", driver);
			if (!msg.equals("OK"))
				return msg;
			//WebElement subMenuUsuario = driver.findElement(By.xpath("//td[@id='MenuOptionBarraMenuSeguridadUsuarios']"));
			//subMenuUsuario.click();
			 
		} catch (Exception e) {
			System.out.println("Error Menú Seguridad " + e.toString());
			msg = "Error Menú Seguridad " ;
		}
		return msg;
	}
}
