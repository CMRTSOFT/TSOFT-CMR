package Atencion_Cliente_Busqueda_Contrato;

import org.junit.AfterClass;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import atu.alm.wrapper.ALMServiceWrapper;
import atu.alm.wrapper.ITestCase;
import atu.alm.wrapper.ITestCaseRun;
import page.AtencionCliente.BusquedaContrato;
import page.Login.LoginSatif;
import page.Menu.Menu;
import util.ALM;
import util.Evidencia;
import util.FunctionGeneric;
import util.LeerExcel;

public class TC001_Busqueda_Contrato_PAN {

	private WebDriver driver;
	private LoginSatif login;
	private Menu menu;
	private BusquedaContrato busContrato;
	private ALMServiceWrapper wrapper;
	private ALM alm;
	private String nameClass, lab, idLab, rutaAlm, pathResultados;
	private Boolean flagState = true;
	private static Evidencia evi;
	private String[][] matriz;
	private static LeerExcel excel;
	private ITestCase ITestCase;
	private ITestCaseRun ITestCaseRun;
	private FunctionGeneric funge;
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

			ITestCase = alm.createItestCase(wrapper, lab, idLab, nameClass, rutaAlm);
			ITestCaseRun = alm.createITestCaseRun(wrapper, ITestCase);

		} catch (Exception e) {
			System.out.println("Error BeforeClass: " + e.getMessage());
		}
	}

	@Test
	public void Test() {
		
		try {

			driver = login.openUrlSatif(excel.valorCol("AMBIENTE", matriz));

			estado = login.ingresoLogin(excel.valorCol("Usuario", matriz), excel.valorCol("Password", matriz), driver);
			if (!FunctionGeneric.stateStep("Login", estado, ITestCaseRun, wrapper)) {
				flagState = false;
				afterClass();
			}

			estado = menu.menuBusquedaContrato(driver);
			if (!FunctionGeneric.stateStep("Menú Busqueda Contrato ", estado, ITestCaseRun, wrapper)) {
				flagState = false;
				afterClass();
			}

			estado = busContrato.formContratoPAN(excel.valorCol("Tipo_Tarjeta", matriz), excel.valorCol("PAN", matriz),
					driver);
			if (!FunctionGeneric.stateStep("Buscar Contrato ", estado, ITestCaseRun, wrapper)) {
				flagState = false;
				afterClass();
			}

			estado = FunctionGeneric.validarTexto("Documento", "Buscar Contrato", driver);
			if (!FunctionGeneric.stateStep("Buscar Contrato ", estado, ITestCaseRun, wrapper)) {
				flagState = false;
				afterClass();
			}

		} catch (Exception e) {
			System.out.println("Error Atencion Cliente Busqueda Contrato: " + e.getMessage());
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
