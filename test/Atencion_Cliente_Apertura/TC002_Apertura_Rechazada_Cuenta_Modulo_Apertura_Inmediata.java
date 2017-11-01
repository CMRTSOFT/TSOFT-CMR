package Atencion_Cliente_Apertura;

import org.testng.annotations.AfterClass;
import org.testng.annotations.Test;
import org.testng.annotations.BeforeClass;
import util.LeerExcel;
import page.Menu.Menu;
import util.ALM;
import util.Evidencia;
import util.FunctionGeneric;
import page.Login.LoginSatif;
import org.openqa.selenium.WebDriver;
import atu.alm.wrapper.ALMServiceWrapper;
import atu.alm.wrapper.ITestCase;
import atu.alm.wrapper.ITestCaseRun;
import page.Admision.AperturaCuenta;
import page.Admision.Evaluador;

public class TC002_Apertura_Rechazada_Cuenta_Modulo_Apertura_Inmediata {

	private WebDriver driver;
	private String[][] matriz;
	private static LeerExcel excel;
	private static LoginSatif login;
	private static Evidencia evi;
	private ALMServiceWrapper wrapper;
	private ALM alm;
	private String nameClass, lab, idLab, rutaAlm, estado, pathResultados;
	private Boolean flagState = true;
	private ITestCase ITestCase;
	private ITestCaseRun ITestCaseRun;

	@BeforeClass
	public void beforeClass() {

		try {

			excel = new LeerExcel();
			login = new LoginSatif();
			alm = new ALM();
			evi = new Evidencia();
			wrapper = alm.conectALM();

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

			estado = Menu.menuAperturaInmediata(driver);
			if (!FunctionGeneric.stateStep("Menú Apertura Inmediata", estado, ITestCaseRun, wrapper)) {
				flagState = false;
				afterClass();
			}

			estado = AperturaCuenta.solicitudApertura(driver, excel.valorCol("Rut", matriz),
					excel.valorCol("Num_Serie", matriz));
			if (!FunctionGeneric.stateStep("Solicitud de Apertura", estado, ITestCaseRun, wrapper)) {
				flagState = false;
				afterClass();
			}

			FunctionGeneric.cerraALTF4();

			estado = FunctionGeneric.validaMensajeAlert("1001 - Proceso de solicitud rechazada", driver);
			if (!FunctionGeneric.stateStep("Solicitud de Apertura", estado, ITestCaseRun, wrapper)) {
				flagState = false;
				afterClass();
			}

			estado = Menu.menuAdmisionEvaluador(driver);
			if (!FunctionGeneric.stateStep("Menú Admisión Evaluador", estado, ITestCaseRun, wrapper)) {
				flagState = false;
				afterClass();
			}

			estado = Evaluador.evaluarCliente(excel.valorCol("Rut", matriz), driver);
			if (!FunctionGeneric.stateStep("Evaluar Cliente", estado, ITestCaseRun, wrapper)) {
				flagState = false;
				afterClass();
			}

			estado = Evaluador.validaEstado("5 - RECHAZADA", "Mora / Protesto mayor", driver);
			if (!FunctionGeneric.stateStep("Estado del Rechazo", estado, ITestCaseRun, wrapper)) {
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
