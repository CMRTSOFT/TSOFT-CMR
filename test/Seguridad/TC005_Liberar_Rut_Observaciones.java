/*package Seguridad;

import static org.junit.Assert.*;

import org.junit.AfterClass;
import org.junit.Test;
import org.openqa.selenium.WebDriver;

import pages.AtencionCliente.BusquedaContrato;
import pages.AtencionCliente.RutObservacion;
import pages.Login.LoginSatif;
import pages.Menu.Menu;
import pages.Seguridad.Usuario;


public class TC005_Liberar_Rut_Observaciones {
	
	private WebDriver driver;
	private LoginSatif login;
	private Menu menu;
	private BusquedaContrato busContrato;
	private RutObservacion rutObs;
	@Test
	public void test() {
		try {
			login = new LoginSatif();
			menu = new Menu();
			busContrato = new BusquedaContrato();
			rutObs = new RutObservacion();
			String resultado = "";
			driver = login.openUrlSatif();
			resultado = login.ingresoLogin("CESPARZA", "13131313", driver);
			Thread.sleep(3000);
			if (!resultado.equalsIgnoreCase("OK"))
				afterClass();
			resultado = menu.menuBusquedaContrato(driver);
			if (!resultado.equalsIgnoreCase("OK"))
				afterClass();

			Thread.sleep(3000);
			resultado = busContrato.formularioContrato("097460469", driver);
			if (!resultado.equalsIgnoreCase("OK"))
				afterClass();

			Thread.sleep(3000);
			if (busContrato.validarCuentas(driver)) {
				Thread.sleep(3000);
				resultado = busContrato.seleccionarProducto("CMR CUOTA PACTADA", driver);
				if (!resultado.equalsIgnoreCase("OK"))
					afterClass();
			}

			resultado = menu.subMenuEliminarContrato(driver);
			if (!resultado.equalsIgnoreCase("OK"))
				afterClass();

			Thread.sleep(3000);
			resultado = rutObs.aceptarPopup(driver);
			if (!resultado.equalsIgnoreCase("OK"))
				afterClass();

			Thread.sleep(3000);
			resultado = menu.subMenuRutObservaciones(driver);
			if (!resultado.equalsIgnoreCase("OK"))
				afterClass();
			
			resultado = rutObs.formularioObservacionRUT("097460469", driver);
			if (!resultado.equalsIgnoreCase("OK"))
				afterClass();
			
			//menu.subMenuRutObservaciones(driver);

		} catch (Exception e) {
			System.out.println("ERROR EJECUCIÓN CASO DE PRUEBA "+e.getMessage());
		}
	}
	
	@AfterClass
	public static void afterClass() {
		System.exit(0);
	}

}*/
