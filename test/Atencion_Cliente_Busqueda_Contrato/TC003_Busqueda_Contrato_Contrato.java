package Atencion_Cliente_Busqueda_Contrato;

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

import util.ALM;
import util.Evidencia;
import util.FunctionGeneric;
import util.LeerExcel;

public class TC003_Busqueda_Contrato_Contrato {
	
	private WebDriver driver;
	private LoginSatif login;
	private Menu menu;
	private LeerExcel excel;
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
	private String estado = "";

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
			login = new LoginSatif();
			menu = new Menu();

			driver = login.openUrlSatif(excel.valorCol("AMBIENTE", matriz));
			
			estado = login.ingresoLogin(excel.valorCol("Usuario", matriz), excel.valorCol("Password", matriz), driver);
			if (!FunctionGeneric.stateStep("Login", estado, ITestCaseRun, wrapper)) {
				flagState = false;
				afterClass();
			}

			estado = menu.menuBusquedaContrato(driver);
			if (!FunctionGeneric.stateStep("Men� Busqueda Contrato", estado, ITestCaseRun, wrapper)) {
				flagState = false;
				afterClass();
			}
			
			Thread.sleep(3000);
			estado = busContrato.formContratoContrato( excel.valorCol("NumeroContrato", matriz), driver);
			if (!FunctionGeneric.stateStep("Formulario Busqueda Contrato", estado, ITestCaseRun, wrapper)) {
				flagState = false;
				afterClass();
			}
			
			estado = FunctionGeneric.validarTexto("Documento", "Despliegue de Contrato", driver);
			if (!FunctionGeneric.stateStep("Validar Contrato", estado, ITestCaseRun, wrapper)) {
				flagState = false;
				afterClass();
			}
			

		} catch (Exception e) {
			System.out.println("Error Atencion Cliente Busqueda Contrato" + e.toString());
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
