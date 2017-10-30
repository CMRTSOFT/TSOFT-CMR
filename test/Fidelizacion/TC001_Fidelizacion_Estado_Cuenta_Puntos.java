package Fidelizacion;

import org.junit.AfterClass;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import atu.alm.wrapper.ALMServiceWrapper;
import atu.alm.wrapper.ITestCase;
import atu.alm.wrapper.ITestCaseRun;
import page.AtencionCliente.BusquedaContrato;
import page.AtencionCliente.Detalle;
import page.Fidelizacion.Fidelizacion;
import page.Login.LoginSatif;
import page.Menu.Menu;
import util.ALM;
import util.Evidencia;
import util.FunctionGeneric;
import util.LeerExcel;

public class TC001_Fidelizacion_Estado_Cuenta_Puntos {
	
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
	private String estado = "";
	private Fidelizacion fide;

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
  public void test() {
	  try {
			driver = login.openUrlSatif(excel.valorCol("AMBIENTE", matriz));
			
			estado = login.ingresoLogin(excel.valorCol("Usuario", matriz), excel.valorCol("Password", matriz), driver);
			if (!FunctionGeneric.stateStep("Login", estado, ITestCaseRun, wrapper)) {
				flagState = false;
				afterClass();
			}
			Thread.sleep(3000);
			
			estado = menu.menuFidelizacion(driver);
			if (!FunctionGeneric.stateStep("Menú Fidelización", estado, ITestCaseRun, wrapper)) {
				flagState = false;
				afterClass();
			}
			
			estado =  menu.subMenuFideEstadoCuenta(driver);
			if (!FunctionGeneric.stateStep("Menú Fidelización Estado Cuenta", estado, ITestCaseRun, wrapper)) {
				flagState = false;
				afterClass();
			}
			
			estado = fide.formEstadoCuenta(driver);
			if (!FunctionGeneric.stateStep("Formulario Estado Cuenta", estado, ITestCaseRun, wrapper)) {
				flagState = false;
				afterClass();
			}
			
			estado = fide.formWindowUser(excel.valorCol("Rut", matriz), driver);
			if (!FunctionGeneric.stateStep("Formulario Ventana Usuario", estado, ITestCaseRun, wrapper)) {
				flagState = false;
				afterClass();
			}
			
			estado = fide.formEstateAccount(driver);
			if (!FunctionGeneric.stateStep("Formulario Estado Cuenta", estado, ITestCaseRun, wrapper)) {
				flagState = false;
				afterClass();
			}
					

		} catch (Exception e) {
			System.out.println("ERROR EN LA EJECUCIÓN DEL CASO NEGOCIO EMISOR MANTENEDOR CONTRATO " + e.getMessage());
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
