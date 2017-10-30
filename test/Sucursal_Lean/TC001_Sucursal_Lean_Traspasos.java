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
import page.AtencionCliente.Traspaso;
import util.ALM;
import util.Evidencia;
import util.FunctionGeneric;
import util.LeerExcel;

public class TC001_Sucursal_Lean_Traspasos {
	
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
	private BusquedaContrato busContrato;
	private Traspaso tras;

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
			LeerExcel.setTextRow("ID_RUN", Integer.toString(ALM.returnIDRun(ITestCase) - 1), nameClass);

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

			estado = menu.menuBusquedaContrato(driver);
			if (!FunctionGeneric.stateStep("Men� Busqueda Contrato", estado, ITestCaseRun, wrapper)) {
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

			estado = tras.presionaIMGTraspaso(driver);
			if (!FunctionGeneric.stateStep("Presionar Imagen Traspaso", estado, ITestCaseRun, wrapper)) {
				flagState = false;
				afterClass();
			}
			estado = tras.seleccionaProducto(driver);
			if (!FunctionGeneric.stateStep("Seleccionar Producto a traspasar", estado, ITestCaseRun, wrapper)) {
				flagState = false;
				afterClass();
			}

			estado = tras.seleccionarSeguro(driver);
			if (!FunctionGeneric.stateStep("Seleccionar Seguro", estado, ITestCaseRun, wrapper)) {
				flagState = false;
				afterClass();
			}
			estado = tras.ventanaResumenSeguro(driver);
			if (!FunctionGeneric.stateStep("Ventana Resumen ", estado, ITestCaseRun, wrapper)) {
				flagState = false;
				afterClass();
			}
			
			estado = tras.validaResumenTraspaso(driver);
			if (!FunctionGeneric.stateStep("Validar Resumen Traspaso", estado, ITestCaseRun, wrapper)) {
				flagState = false;
				afterClass();
			}
			estado = tras.validaVentanaProductos(driver);
			if (!FunctionGeneric.stateStep("Validar Ventana Productos", estado, ITestCaseRun, wrapper)) {
				flagState = false;
				afterClass();
			}
			estado = tras.validarFirmaContrato(driver);
			if (!FunctionGeneric.stateStep("Validar Firma Contrato" , estado, ITestCaseRun, wrapper)) {
				flagState = false;
				afterClass();
			}
		} catch (Exception e) {
			System.out.println("ERROR EN LA EJECUCI�N DEL CASO TRASPASO PRODUCTO " + e.getMessage());
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