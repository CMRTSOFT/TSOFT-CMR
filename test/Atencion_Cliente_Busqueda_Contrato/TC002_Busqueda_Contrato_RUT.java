package Atencion_Cliente_Busqueda_Contrato;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterClass;
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

public class TC002_Busqueda_Contrato_RUT {
	private Menu menu;
	private LeerExcel excel;
	private WebDriver driver;
	private LoginSatif login;
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
	private String estado;

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

			ITestCase = alm.createItestCase(wrapper, lab, idLab, nameClass, rutaAlm);
			ITestCaseRun = alm.createITestCaseRun(wrapper, ITestCase);

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
			if (!FunctionGeneric.stateStep("Menú Busqueda Contrato", estado, ITestCaseRun, wrapper)) {
				flagState = false;
				afterClass();
			}

			estado = busContrato.formularioContrato(excel.valorCol("Rut", matriz),driver);
			if (!FunctionGeneric.stateStep("Formulario Busqueda Contrato", estado, ITestCaseRun, wrapper)) {
				flagState = false;
				afterClass();
			}
							
			
						
			/*resultado = login.ingresoLogin("HAMONTECINO", "13131313", driver);
			Thread.sleep(3000);
			if (!resultado.equalsIgnoreCase("OK"))
				afterClass();

			resultado = menu.menuBusquedaContrato(driver);
			if (!resultado.equalsIgnoreCase("OK"))
				afterClass();

			Thread.sleep(3000);
			resultado = busContrato.formularioContrato("20322877-5", driver);
			if (!resultado.equalsIgnoreCase("OK"))
				afterClass();

			Thread.sleep(3000);
			if (busContrato.validarCuentas(driver)) {
				Thread.sleep(3000);
				resultado = busContrato.seleccionarProducto("CMR MASTERCARD", driver);
				if (!resultado.equalsIgnoreCase("OK"))
					afterClass();

				Thread.sleep(3000);
				resultado = busContrato.validaContrato("Contrato", driver);
				if (!resultado.equalsIgnoreCase("OK"))
					afterClass();
			} else {
				Thread.sleep(3000);
				busContrato.validaContrato("Contrato", driver);
			}*/

		} catch (Exception e) {
			System.out.println("ERROR EN LA EJECUCIÓN DEL CASO BUSQUEDA CONTRATO " + e.getMessage());
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
