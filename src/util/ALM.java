package util;

import atu.alm.wrapper.ALMServiceWrapper;
import atu.alm.wrapper.ITestCase;
import atu.alm.wrapper.ITestCaseRun;
import atu.alm.wrapper.classes.Run;
import atu.alm.wrapper.classes.RunFactory;
import atu.alm.wrapper.enums.StatusAs;

public class ALM {

	public ALMServiceWrapper conectALM() {

		ALMServiceWrapper wrapper = null;

		try {

			wrapper = new ALMServiceWrapper("https://almchile.tsoftglobal.com:8443/qcbin/");
			if(wrapper.connect("g.munoz", "G99385421m", "CMR", "CMR"));

		} catch (Exception e) {
			System.out.println("Error al conectarse hacia ALM: " + e.getMessage());
			System.exit(0);
		}
		return wrapper;
	}

	public void addExecutionSteps(ALMServiceWrapper wrapper, String estado, String paso, ITestCaseRun ITestCaseRun) {
		try {
			
			if (estado.equals("Pass")) {
				wrapper.addStep(ITestCaseRun, paso, StatusAs.PASSED, paso, "", "");
			} else {
				wrapper.addStep(ITestCaseRun, paso, StatusAs.FAILED, paso, "", "");
			}

		} catch (Exception e) {
			System.out.println("Error al crear secuencia de pasos ALM: " + e.getMessage());
		}
	}

	public ITestCase createItestCase(ALMServiceWrapper wrapper, String labEjecucion, String idLab, String caso,
			String ruta) {
		
		ITestCase ITestCase = null;
		try {
			
			int id = Integer.parseInt(idLab);
			ruta = ruta.substring(5, ruta.length());
			ITestCase = wrapper.updateResult(ruta, labEjecucion, id, caso, StatusAs.NOT_COMPLETED);

		} catch (Exception e) {

		}
		return ITestCase;
	}
	
	public void updateItestCase(ALMServiceWrapper wrapper, String labEjecucion, String idLab, String caso,
			String ruta, boolean estado) {

		try {

			int id = Integer.parseInt(idLab);
			ruta = ruta.substring(5, ruta.length());
			
			if(estado) {
				wrapper.updateResult(ruta, labEjecucion, id, caso, StatusAs.PASSED);
			}else {
				wrapper.updateResult(ruta, labEjecucion, id, caso, StatusAs.FAILED);
			}
		} catch (Exception e) {

		}
	}

	public ITestCaseRun createITestCaseRun(ALMServiceWrapper wrapper, ITestCase ITestCase) {
		ITestCaseRun run = null;

		try {
			run = wrapper.createNewRun(ITestCase, "Run Automatizado", StatusAs.PASSED);		
		} catch (Exception e) {

		}
		return run;
	}


	public static int returnIDRun(ITestCase ITestCase) {
		int idRun;
		RunFactory runFac = ITestCase.getRunFactory();
		Run rn = runFac.addItem();
		idRun = rn.getID();
		return idRun;
	}

}
