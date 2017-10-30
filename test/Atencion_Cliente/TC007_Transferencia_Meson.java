package Atencion_Cliente;

import org.junit.AfterClass;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import atu.alm.wrapper.ALMServiceWrapper;
import atu.alm.wrapper.ITestCase;
import atu.alm.wrapper.ITestCaseRun;
import page.Login.LoginSatif;
import page.Menu.Menu;
import page.AtencionCliente.BusquedaContrato;
import page.AtencionCliente.SimulacionCC;
import util.ALM;
import util.Evidencia;
import util.FunctionGeneric;
import util.LeerExcel;

public class TC007_Transferencia_Meson {
	private WebDriver driver;
	private LoginSatif login;
	private Menu menu;
	private LeerExcel excel;
	private String[][] matriz;
	private FunctionGeneric funge;
	private ALM alm;
	private Evidencia evi;
	private ALMServiceWrapper wrapper;
	private String nameClass, lab, idLab, rutaAlm;
	private ITestCase ITestCase;
	private ITestCaseRun ITestCaseRun;
	private boolean flagState = true;
	private String estado = "";
	private SimulacionCC simulaCC;
	private BusquedaContrato busContrato;

	@BeforeClass
	public void beforeClass() {
		try {
			excel = new LeerExcel();
			alm = new ALM();
			evi = new Evidencia();
			wrapper = alm.conectALM();
			funge = new FunctionGeneric();
			login = new LoginSatif();
			menu = new Menu();
			busContrato = new BusquedaContrato();
			simulaCC = new SimulacionCC();
			nameClass = this.getClass().getName().substring(this.getClass().getPackage().getName().length() + 1,
					this.getClass().getName().length());
			matriz = LeerExcel.retornaDatosExcel(this.getClass().getPackage().getName(), nameClass);

			lab = excel.valorCol("LABORATORIO", matriz);
			idLab = excel.valorCol("ID_LABORATORIO", matriz);
			rutaAlm = excel.valorCol("RUTA_ALM", matriz);

			ITestCase = alm.createItestCase(wrapper, lab, idLab, nameClass, rutaAlm);
			ITestCaseRun = alm.createITestCaseRun(wrapper, ITestCase);

		} catch (Exception e) {
			System.out.println("Error BeforeClass: " + e.getMessage());
		}
	}

	@Test
	public void test() {
		try {
			driver = login.openUrlSatif();

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
			estado = busContrato.formularioContrato(excel.valorCol("Rut", matriz), driver);
			if (!FunctionGeneric.stateStep("Formulario Contrato", estado, ITestCaseRun, wrapper)) {
				flagState = false;
				afterClass();
			}

			estado = busContrato.seleccionarProducto(excel.valorCol("Producto", matriz), driver);
			if (!FunctionGeneric.stateStep("Seleccionar Contrato", estado, ITestCaseRun, wrapper)) {
				flagState = false;
				afterClass();
			}

			estado = menu.subMenuSimulacionCC(driver);
			if (!FunctionGeneric.stateStep("Menú Simulación CC", estado, ITestCaseRun, wrapper)) {
				flagState = false;
				afterClass();
			}

			estado = simulaCC.formularioSimulacionCC(excel.valorCol("Monto", matriz),
					excel.valorCol("NumeroCuotas", matriz),
					excel.valorCol("MesDiferido", matriz),
					excel.valorCol("TipoCuota", matriz), driver);
			if (!FunctionGeneric.stateStep("Login", estado, ITestCaseRun, wrapper)) {
				flagState = false;
				afterClass();
			}

			estado = simulaCC.transferirCuenta(excel.valorCol("Banco", matriz),
					excel.valorCol("TipoCuenta", matriz),
					excel.valorCol("NumeroCuenta", matriz),
					excel.valorCol("Email", matriz), driver);
			if (!FunctionGeneric.stateStep("Transferir Cuenta", estado, ITestCaseRun, wrapper)) {
				flagState = false;
				afterClass();
			}
			
			estado = simulaCC.validaPantallaImprimir(driver);
			if (!FunctionGeneric.stateStep("Validar Pantalla Comprobar Transferencia", estado, ITestCaseRun, wrapper)) {
				flagState = false;
				afterClass();
			}

		} catch (Exception e) {
			System.out.println("Error Test Simulación " + e.toString());
			flagState = false;
			afterClass();
		}

	}

	@AfterClass
	public void afterClass() {
		try {
			evi.createPDF(FunctionGeneric.arrEvidencia, nameClass, flagState);
			alm.AttachmentEvi(wrapper, ITestCaseRun, nameClass);
			funge.closeWindows(driver, 0);
			funge.deleteFile(nameClass);
			System.exit(0);
		} catch (Exception e) {
			System.out.println("Error AfterClass: " + e.getMessage());
		}
	}

}
