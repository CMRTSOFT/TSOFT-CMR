package Autorizaciones;

import org.testng.annotations.Test;
import atu.alm.wrapper.ALMServiceWrapper;
import atu.alm.wrapper.ITestCase;
import atu.alm.wrapper.ITestCaseRun;
import page.AtencionCliente.BusquedaContrato;
import page.AtencionCliente.Detalle;
import page.Login.LoginSatif;
import page.Menu.Menu;
import util.ALM;
import util.Evidencia;
import util.FunctionGeneric;
import util.LeerExcel;
import org.testng.annotations.BeforeClass;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterClass;

public class TC032_Liberacion_Autorizacion {

	private WebDriver driver;
	private LoginSatif login;
	private Menu menu;
	private BusquedaContrato busContrato;
	private ALMServiceWrapper wrapper;
	private ALM alm;
	private String nameClass, lab, idLab, rutaAlm, estado, pathResultados;
	private Boolean flagState = true;
	private static Evidencia evi;
	private String[][] matriz;
	private static LeerExcel excel;
	private ITestCase ITestCase;
	private ITestCaseRun ITestCaseRun;
	private FunctionGeneric funge;
	private Detalle detalle;

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
			detalle = new Detalle();
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
			
			LeerExcel.setTextRow(Integer.toString(ALM.returnIDRun(ITestCase)-1));

		} catch (Exception e) {
			System.out.println("Error BeforeClass: " + e.getMessage());
			System.exit(0);
		}
	}

	@Test
	public void Test() {

		try {

			driver = login.openUrlSatif(excel.valorCol("AMBIENTE", matriz));

			estado = login.validarURL("SATIF v.13.00.");
			if (!FunctionGeneric.stateStep("Abrir Url SATIF", estado, ITestCaseRun, wrapper)) {
				flagState = false;
				afterClass();
			}

			estado = login.ingresoLogin(excel.valorCol("Usuario", matriz), excel.valorCol("Password", matriz), driver);
			if (!FunctionGeneric.stateStep("Login", estado, ITestCaseRun, wrapper)) {
				flagState = false;
				afterClass();
			}

			estado = menu.menuBusquedaContrato(driver);
			if (!FunctionGeneric.stateStep("Men� Busqueda de Contrato", estado, ITestCaseRun, wrapper)) {
				flagState = false;
				afterClass();
			}

			estado = busContrato.formularioContrato(excel.valorCol("Rut", matriz), driver);
			if (!FunctionGeneric.stateStep("Buscar Contrato", estado, ITestCaseRun, wrapper)) {
				flagState = false;
				afterClass();
			}

			estado = busContrato.seleccionarProducto(excel.valorCol("Producto", matriz), driver);
			if (!FunctionGeneric.stateStep("Seleccionar Producto", estado, ITestCaseRun, wrapper)) {
				flagState = false;
				afterClass();
			}

			estado = menu.subMenuDetalle(driver);
			if (!FunctionGeneric.stateStep("Sub Men� Detalles", estado, ITestCaseRun, wrapper)) {
				flagState = false;
				afterClass();
			}

			estado = detalle.lnkAutorizaciones(driver);
			if (!FunctionGeneric.stateStep("Link Autorizaciones", estado, ITestCaseRun, wrapper)) {
				flagState = false;
				afterClass();
			}

			estado = detalle.liberarAutorizacion(driver);
			if (!FunctionGeneric.stateStep("Liberaci�n de Autorizaci�n", estado, ITestCaseRun, wrapper)) {
				flagState = false;
				afterClass();
			}

		} catch (Exception e) {
			System.out.println("Error Test: " + e.getMessage());
			flagState = false;
			afterClass();
		}
	}

	@AfterClass
	public void afterClass() {

		try {

			funge.closeWindows(driver, 0);
			evi.createPDF(FunctionGeneric.arrEvidencia, nameClass, pathResultados, flagState);
			FunctionGeneric.moveFileXLSX(pathResultados, nameClass);
			System.exit(0);

		} catch (Exception e) {
			System.out.println("Error AfterClass: " + e.getMessage());
		}
	}

}