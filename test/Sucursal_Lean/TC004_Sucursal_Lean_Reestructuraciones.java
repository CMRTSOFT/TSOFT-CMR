package Sucursal_Lean;

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
import page.Reestructuracion.Reestructuracion;
import util.ALM;
import util.Evidencia;
import util.FunctionGeneric;
import util.LeerExcel;

public class TC004_Sucursal_Lean_Reestructuraciones {
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
	private BusquedaContrato busContrato;
	private Reestructuracion reest;

	@BeforeClass
	public void beforeClass() {

		try {

			menu = new Menu();
			excel = new LeerExcel();
			alm = new ALM();
			evi = new Evidencia();
			wrapper = alm.conectALM();
			funge = new FunctionGeneric();
			login = new LoginSatif();
			menu = new Menu();
			reest = new Reestructuracion();
			busContrato = new BusquedaContrato();
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
			Thread.sleep(3000);
			estado = menu.menuBusquedaContrato(driver);
			if (!FunctionGeneric.stateStep("Login", estado, ITestCaseRun, wrapper)) {
				flagState = false;
				afterClass();
			}
			estado = busContrato.formularioContrato(excel.valorCol("Rut", matriz), driver);
			if (!FunctionGeneric.stateStep("Formulario Contrato", estado, ITestCaseRun, wrapper)) {
				flagState = false;
				afterClass();
			}

	
			estado = busContrato.seleccionarProducto(excel.valorCol("Producto", matriz), driver);
			if (!FunctionGeneric.stateStep("Seleccionar Producto", estado, ITestCaseRun, wrapper)) {
				flagState = false;
				afterClass();
			}

			
			estado = menu.menuVencimiento(driver);
			if (!FunctionGeneric.stateStep("Menú Vencimiento", estado, ITestCaseRun, wrapper)) {
				flagState = false;
				afterClass();
			}
			
			estado = reest.formVencimientos("RESA", excel.valorCol("NumeroCuota", matriz), driver);
			if (!FunctionGeneric.stateStep("Formulario Vencimiento", estado, ITestCaseRun, wrapper)) {
				flagState = false;
				afterClass();
			}
			
			estado = reest.formVencimientoImprimir(driver);
			if (!FunctionGeneric.stateStep("Formulario Vencimiento Imprimir", estado, ITestCaseRun, wrapper)) {
				flagState = false;
				afterClass();
			}
			estado = funge.validarTexto("Re-estructuración súper avance","Validar Flujo Re-estructuración deuda" ,driver);
			if (!FunctionGeneric.stateStep("Validar Re-estructuración Deuda", estado, ITestCaseRun, wrapper)) {
				flagState = false;
				afterClass();
			}

		} catch (Exception e) {
			System.out.println("ERROR AL EJECUTAR CASO RE-ESTRUCTRACIÓN " + e.getMessage());
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
