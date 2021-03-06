package Negocio_Emisor;

import org.junit.AfterClass;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import atu.alm.wrapper.ALMServiceWrapper;
import atu.alm.wrapper.ITestCase;
import atu.alm.wrapper.ITestCaseRun;
import page.Login.LoginSatif;
import page.Menu.Menu;
import page.NegocioEmisor.NegocioEmisor;
import util.ALM;
import util.Evidencia;
import util.FunctionGeneric;
import util.LeerExcel;

public class TC001_Mantenimiento_Contrato_Inclusion_Mov_Extractos {

	private WebDriver driver;
	private LoginSatif login;
	private LeerExcel excel;
	private String[][] matriz;
	private ALM alm;
	private Evidencia evi;
	private ALMServiceWrapper wrapper;
	private String nameClass, lab, idLab, rutaAlm, pathResultados;
	private ITestCase ITestCase;
	private ITestCaseRun ITestCaseRun;
	private boolean flagState = true;
	private String estado = "";

	@BeforeClass
	public void beforeClass() {

		try {

			excel = new LeerExcel();
			alm = new ALM();
			evi = new Evidencia();
			wrapper = alm.conectALM();
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
			LeerExcel.setTextRow("ID_RUN", Integer.toString(ALM.returnIDRun(ITestCase) - 1), nameClass);

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

			estado = Menu.menuNegocioEmisorContrato(driver);
			if (!FunctionGeneric.stateStep("Login", estado, ITestCaseRun, wrapper)) {
				flagState = false;
				afterClass();
			}

			estado = NegocioEmisor.formBusquedaPANContrato(excel.valorCol("Pan", matriz), driver);
			if (!FunctionGeneric.stateStep("Formulario Busqueda PAN", estado, ITestCaseRun, wrapper)) {
				flagState = false;
				afterClass();
			}

			estado = NegocioEmisor.selectOptionContract(driver);
			if (!FunctionGeneric.stateStep("Opciones de CONTRATO", estado, ITestCaseRun, wrapper)) {
				flagState = false;
				afterClass();
			}

			estado = NegocioEmisor.selectAccountPending(driver);
			if (!FunctionGeneric.stateStep("Seleccionar Cuenta Pendiente", estado, ITestCaseRun, wrapper)) {
				flagState = false;
				afterClass();
			}

			estado = NegocioEmisor.insertarDatosPAN(excel.valorCol("Pan", matriz),
					excel.valorCol("Transaccion", matriz), excel.valorCol("Monto", matriz),
					excel.valorCol("Comercio", matriz), excel.valorCol("Pais", matriz),
					excel.valorCol("Factura", matriz), excel.valorCol("Moneda", matriz),
					excel.valorCol("Factura", matriz), excel.valorCol("Nombre", matriz), driver);
			if (!FunctionGeneric.stateStep("Datos PAN", estado, ITestCaseRun, wrapper)) {
				flagState = false;
				afterClass();
			}

			estado = NegocioEmisor.formVentana(driver);
			if (!FunctionGeneric.stateStep("Formulario Ventana", estado, ITestCaseRun, wrapper)) {
				flagState = false;
				afterClass();
			}

			estado = NegocioEmisor.confirmMoviExtractos(driver);
			if (!FunctionGeneric.stateStep("Confirmar Movimientos Extractos", estado, ITestCaseRun, wrapper)) {
				flagState = false;
				afterClass();
			}

			estado = NegocioEmisor.validarMovimiento(driver);
			if (!FunctionGeneric.stateStep("Validar Movimientos Extractos", estado, ITestCaseRun, wrapper)) {
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

			FunctionGeneric.closeWindows(driver, 0);
			evi.createPDF(FunctionGeneric.arrEvidencia, nameClass, pathResultados, flagState);
			FunctionGeneric.updateStateTestCase(flagState, nameClass);
			FunctionGeneric.moveFileXLSX(pathResultados, nameClass);
			System.exit(0);

		} catch (Exception e) {
			System.out.println("Error AfterClass: " + e.getMessage());
		}
	}
}
