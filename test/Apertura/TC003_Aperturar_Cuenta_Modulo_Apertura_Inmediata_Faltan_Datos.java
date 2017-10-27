package Apertura;

import org.testng.annotations.AfterMethod;
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

public class TC003_Aperturar_Cuenta_Modulo_Apertura_Inmediata_Faltan_Datos {

	private WebDriver driver;
	private static Menu menu;
	private String[][] matriz;
	private static LeerExcel excel;
	private static LoginSatif login;
	private static Evidencia evi;
	private static FunctionGeneric funge;
	private static AperturaCuenta apertura;
	private ALMServiceWrapper wrapper;
	private ALM alm;
	private Evaluador evaluador;
	private String nameClass, lab, idLab, rutaAlm, estado, pathResultados;
	private Boolean flagState = true;
	private ITestCase ITestCase;
	private ITestCaseRun ITestCaseRun;

	@BeforeClass
	public void beforeClass() {
		
		try {
			
			menu = new Menu();
			excel = new LeerExcel();
			login = new LoginSatif();
			funge = new FunctionGeneric();
			apertura = new AperturaCuenta();
			alm = new ALM();
			evi = new Evidencia();
			wrapper = alm.conectALM();
			evaluador = new Evaluador();

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

			estado = menu.menuAperturaInmediata(driver);
			if (!FunctionGeneric.stateStep("Menú Apertura Inmediata", estado, ITestCaseRun, wrapper)) {
				flagState = false;
				afterClass();
			}

			estado = apertura.solicitudApertura(driver, excel.valorCol("Rut", matriz),
					excel.valorCol("Num_Serie", matriz));
			if (!FunctionGeneric.stateStep("Solicitud de Apertura", estado, ITestCaseRun, wrapper)) {
				flagState = false;
				afterClass();
			}

			funge.cerraALTF4();

			estado = funge.validaMensajeAlert("1012 - Proceso de solicitud incompleta", driver);
			if (!FunctionGeneric.stateStep("Validar Mensaje Proceso de Solicitud Incompleta", estado, ITestCaseRun,
					wrapper)) {
				flagState = false;
				afterClass();
			}

			estado = apertura.ingresoFormularioCliente(driver);
			if (!FunctionGeneric.stateStep("Completar Datos del Cliente", estado, ITestCaseRun, wrapper)) {
				flagState = false;
				afterClass();
			}

			estado = evaluador.crearCuenta(driver);
			if (!FunctionGeneric.stateStep("Crear Cuenta", estado, ITestCaseRun, wrapper)) {
				flagState = false;
				afterClass();
			}

			funge.cerraALTF4();

			estado = funge.validaMensajeAlert("¿Cliente Firmó Contrato?", driver);
			if (!FunctionGeneric.stateStep("Validar Mensaje Cliente Firmó Contrato", estado, ITestCaseRun, wrapper)) {
				flagState = false;
				afterClass();
			}

		} catch (Exception e) {
			System.out.println("Error Test: " + e.getMessage());
			flagState = false;
			afterClass();
		}
	}

	@AfterMethod
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
