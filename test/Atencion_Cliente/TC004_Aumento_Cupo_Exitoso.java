package Atencion_Cliente;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import atu.alm.wrapper.ALMServiceWrapper;
import atu.alm.wrapper.ITestCase;
import atu.alm.wrapper.ITestCaseRun;
import page.Admision.AperturaCuenta;
import page.Admision.Evaluador;
import page.Login.LoginSatif;
import page.Menu.Menu;
import page.AtencionCliente.AumentoCupo;
import page.AtencionCliente.BusquedaContrato;
import page.AtencionCliente.Detalle;
import util.ALM;
import util.Evidencia;
import util.FunctionGeneric;
import util.KeyboardClass;
import util.LeerExcel;

public class TC004_Aumento_Cupo_Exitoso {
	
	private Menu menu;
	private LeerExcel excel;
	private WebDriver driver;
	private LoginSatif login;
	private String[][] matriz;
	private FunctionGeneric funge;
	private ALM alm;
	private Evidencia evi;
	private ALMServiceWrapper wrapper;
	private String nameClass, lab, idLab, rutaAlm, pathResultados;
	private ITestCase ITestCase;
	private ITestCaseRun ITestCaseRun;
	private boolean flagState = true;
	private BusquedaContrato busContrato;
	private AumentoCupo aumen;
	private String estado;

	@BeforeClass
	public void beforeClass() {

		try {

			menu = new Menu();
			busContrato = new BusquedaContrato();
			excel = new LeerExcel();
			alm = new ALM();
			evi = new Evidencia();
			wrapper = alm.conectALM();
			funge = new FunctionGeneric();
			login = new LoginSatif();

			nameClass = this.getClass().getName().substring(this.getClass().getPackage().getName().length() + 1,
					this.getClass().getName().length());
			matriz = LeerExcel.retornaDatosExcel(this.getClass().getPackage().getName(), nameClass);

			lab = excel.valorCol("LABORATORIO", matriz);
			idLab = excel.valorCol("ID_LABORATORIO", matriz);
			rutaAlm = excel.valorCol("RUTA_ALM", matriz);
		
			pathResultados = rutaAlm + "\\" + lab + "\\";

			ITestCase = alm.createItestCase(wrapper, lab, idLab, nameClass, rutaAlm);
			ITestCaseRun = alm.createITestCaseRun(wrapper, ITestCase);
			LeerExcel.setTextRow("ID_RUN",Integer.toString(ALM.returnIDRun(ITestCase)-1), nameClass);
			
		} catch (Exception e) {
			System.out.println("Error BeforeClass: " + e.getMessage());
		}
	}
	
  @Test
  public void f() {
	  
	  try {
			driver = login.openUrlSatif(excel.valorCol("AMBIENTE", matriz));
			
			estado = login.ingresoLogin(excel.valorCol("Usuario", matriz), excel.valorCol("Password", matriz), driver);
			if (!FunctionGeneric.stateStep("Login", estado, ITestCaseRun, wrapper)) {
				flagState = false;
				afterClass();
			}
			
			estado = menu.menuBusquedaContrato(driver);
			if (!FunctionGeneric.stateStep("Menú Busqueda Contrato", estado, ITestCaseRun, wrapper)) {
				flagState = false;
				afterClass();
			}
			
			estado = busContrato.formularioContrato(excel.valorCol("Rut", matriz),driver);
			if (!FunctionGeneric.stateStep("Formulario Busqueda Contrato", estado, ITestCaseRun, wrapper)) {
				flagState = false;
				afterClass();
			}
			
			estado = busContrato.seleccionarProducto(excel.valorCol("Producto", matriz),driver);
			if (!FunctionGeneric.stateStep("Seleccionar Producto", estado, ITestCaseRun, wrapper)) {
				flagState = false;
				afterClass();
			}
			
			estado = menu.subMenuModificacionCupo(driver);
			if (!FunctionGeneric.stateStep("Menú Modificación Cupo", estado, ITestCaseRun, wrapper)) {
				flagState = false;
				afterClass();
			}
			
			
			
			//menu.subMenuModificacionCupo(driver);
			/*
			login.ingresoLogin("LBIANCHI", "QA123456", driver);
			Thread.sleep(3000);
			resultado = menu.menuBusquedaContrato(driver);
			if (!resultado.equalsIgnoreCase("OK"))
				afterClass();
			Thread.sleep(3000);
			resultado = busContrato.formularioContrato("9.552.288-2", driver);
			if (!resultado.equalsIgnoreCase("OK"))
				afterClass();
			Thread.sleep(3000);
			if (busContrato.validarCuentas(driver)) {
				Thread.sleep(3000);
				resultado = busContrato.seleccionarProducto("CMR VISA", driver);
				if (!resultado.equalsIgnoreCase("OK"))
					afterClass();

			}

			Thread.sleep(3000);
			resultado = menu.subMenuModificacionCupo(driver);
			if (!resultado.equalsIgnoreCase("OK"))
				afterClass();
			
			resultado = aumen.formAumentoCupo(driver);
			if (!resultado.equalsIgnoreCase("OK"))
				afterClass();
			
			*/
			
			
			
		} catch (Exception e) {
			System.out.println("ERROR AL EJECUTAR CASO AUMENTO CUPO "+e.getMessage());
			flagState = false;
			afterClass();
		}
	  
  }
  
  @AfterClass
	public void afterClass() {
		try {
			
			funge.closeWindows(driver, 0);
			evi.createPDF(FunctionGeneric.arrEvidencia, nameClass, pathResultados, flagState);
			FunctionGeneric.updateStateTestCase(flagState, nameClass);
			FunctionGeneric.moveFileXLSX(pathResultados, nameClass);
			System.exit(0);
			
		} catch (Exception e) {
			System.out.println("Error AfterClass: " + e.getMessage());
		}
	}
}
