package Atencion_Cliente;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import atu.alm.wrapper.ALMServiceWrapper;
import atu.alm.wrapper.ITestCase;
import atu.alm.wrapper.ITestCaseRun;
import page.AtencionCliente.AumentoCupo;
import page.AtencionCliente.BusquedaContrato;
import page.Login.LoginSatif;
import page.Menu.Menu;
import util.ALM;
import util.Evidencia;
import util.FunctionGeneric;
import util.LeerExcel;

public class TC011_Aumento_Cupo_Super_Avance_Online_Rechazo {

	private LeerExcel excel;
	private WebDriver driver;
	private LoginSatif login;
	private String[][] matriz;
	private ALM alm;
	private Evidencia evi;
	private ALMServiceWrapper wrapper;
	private String nameClass, lab, idLab, rutaAlm, pathResultados;
	private ITestCase ITestCase;
	private ITestCaseRun ITestCaseRun;
	private boolean flagState = true;
	private String estado;

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

			estado = Menu.menuBusquedaContrato(driver);
			if (!FunctionGeneric.stateStep("Menú Busqueda Contrato", estado, ITestCaseRun, wrapper)) {
				flagState = false;
				afterClass();
			}

			estado = BusquedaContrato.formularioContrato(excel.valorCol("Rut", matriz), driver);
			if (!FunctionGeneric.stateStep("Formulario Busqueda Contrato", estado, ITestCaseRun, wrapper)) {
				flagState = false;
				afterClass();
			}

			estado = BusquedaContrato.seleccionarProducto(excel.valorCol("Producto", matriz), driver);
			if (!FunctionGeneric.stateStep("Seleccionar Producto", estado, ITestCaseRun, wrapper)) {
				flagState = false;
				afterClass();
			}

			estado = Menu.subMenuModificacionCupoSA(driver);
			if (!FunctionGeneric.stateStep("Menú Modificación Cupo", estado, ITestCaseRun, wrapper)) {
				flagState = false;
				afterClass();
			}

			estado = AumentoCupo.formAumentoCupoSARechazo(excel.valorCol("Cupo", matriz), driver);
			if (!FunctionGeneric.stateStep("Formulario Modificación de Cupo", estado, ITestCaseRun, wrapper)) {
				flagState = false;
				afterClass();
			}

			estado = FunctionGeneric.validarTexto("Fecha apertura", "", driver);
			if (!FunctionGeneric.stateStep("Modificación de Cupo", estado, ITestCaseRun, wrapper)) {
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
