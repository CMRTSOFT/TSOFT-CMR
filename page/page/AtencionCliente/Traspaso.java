package page.AtencionCliente;

import java.awt.Robot;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import javax.swing.JOptionPane;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import util.FunctionGeneric;
import util.KeyboardClass;

public class Traspaso {
	KeyboardClass keyBoa;
	FunctionGeneric func = new FunctionGeneric();
	public static String ventana3;

	public String presionaIMGTraspaso(WebDriver driver) {
		String msg = "OK";
		try {
			Set<String> winSet = driver.getWindowHandles();
			List<String> winList = new ArrayList<String>(winSet);
			if (!func.waitWindows(2, winList, driver)) {
				msg = msg + "No se encontró el ventana \n";
				return msg;
			} else {
				System.out.println("Encontró ventana 2");
			}
			winSet = driver.getWindowHandles();
			winList = new ArrayList<String>(winSet);
			driver.switchTo().window(winList.get(winList.size() - 1));

			WebElement FrameInterContrato = (new WebDriverWait(driver, 60)).until(
					ExpectedConditions.presenceOfElementLocated(By.xpath("//IFRAME[@src='paginas/esperaAux.jsp']")));
			driver.switchTo().frame(FrameInterContrato);

			WebElement btnTraspaso = (new WebDriverWait(driver, 60))
					.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//INPUT[@id='productoTraspaso']")));

			if (btnTraspaso.isDisplayed()) {
				btnTraspaso.click();
				// action.doubleClick(rdoUsuario).perform();
			} else {
				if (msg.equals("OK"))
					msg = "";

				msg = msg + "No se encontró el Botón Traspaso \n";
				return msg;
			}

		} catch (Exception e) {
			System.out.println("ERROR TRASPASO PRODUCTO  " + e.toString());
			msg = "ERROR  TRASPASO PRODUCTO  " + e.toString();
		}
		return msg;
	}

	public String seleccionaProducto(WebDriver driver) {
		String msg = "OK";
		try {
			Thread.sleep(6000);
			Set<String> winSet = driver.getWindowHandles();
			List<String> winList = new ArrayList<String>(winSet);
			if (!func.waitWindows(3, winList, driver)) {
				msg = msg + "No se encontró el ventana \n";
				return msg;
			} else {
				System.out.println("encontro ventana 3 Seleccionar producto");
			}
			winSet = driver.getWindowHandles();
			winList = new ArrayList<String>(winSet);
			driver.switchTo().window(winList.get(winList.size() - 1));

			ventana3 = driver.getWindowHandle();
			Thread.sleep(2000);
			// System.out.println(driver.getPageSource());
			List<WebElement> listRadio = (new WebDriverWait(driver, 60)).until(ExpectedConditions
					.presenceOfAllElementsLocatedBy((By.xpath("//INPUT[@name='RadioButtonProductosTraspasables']"))));
			WebElement btnAceptar = (new WebDriverWait(driver, 60))
					.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//INPUT[@name='ACEPTAR']")));

			if (listRadio.size() != 0) {
				listRadio.get(0).click();
			} else {
				if (msg.equals("OK"))
					msg = "";

				msg = msg + "No se encontraron lista de Radio Button \n";
				return msg;
			}

			if (btnAceptar.isDisplayed()) {
				btnAceptar.click();
			} else {
				if (msg.equals("OK"))
					msg = "";

				msg = msg + "No se encontraron el Botón Aceptar \n";
				return msg;
			}

		} catch (Exception e) {
			System.out.println("ERROR AL SELECCIONAR EL PRODUCTO " + e.getMessage());
			msg = "ERROR AL SELECCIONAR EL PRODUCTO " + e.getMessage();
		}
		return msg;
	}

	public String seleccionarSeguro(WebDriver driver) {
		String msg = "OK";
		try {
			Set<String> winSet = driver.getWindowHandles();
			List<String> winList = new ArrayList<String>(winSet);
			if (!func.waitWindows(4, winList, driver)) {
				msg = msg + "No se encontró el ventana \n";
				return msg;
			}
			winSet = driver.getWindowHandles();
			winList = new ArrayList<String>(winSet);
			driver.switchTo().window(winList.get(winList.size() - 1));
			Thread.sleep(2000);

			WebElement btnResumenSeg = (new WebDriverWait(driver, 60))
					.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//INPUT[@name='ResumenSegPat']")));
			if (btnResumenSeg.isDisplayed()) {
				btnResumenSeg.click();
			} else {
				if (msg.equals("OK"))
					msg = "";

				msg = msg + "No se encontraron el Botón Resumen seguro \n";
				return msg;
			}

		} catch (Exception e) {
			System.out.println("ERROR AL SELECCIONAR EL SEGURO " + e.getMessage());
			msg = "ERROR AL SELECCIONAR EL SEGURO " + e.getMessage();
			JOptionPane.showMessageDialog(null, "ERROR AL SELECCIONAR EL SEGURO " + e.getMessage());
		}
		return msg;
	}

	public String ventanaResumenSeguro(WebDriver driver) {
		String msg = "OK";
		try {
			keyBoa = new util.KeyboardClass();
			Set<String> winSet = driver.getWindowHandles();
			List<String> winList = new ArrayList<String>(winSet);
			if (!func.waitWindows(5, winList, driver)) {
				msg = msg + "No se encontró el ventana \n";
				return msg;
			} else {
				System.out.println("Encontró ventana 5 Ventana Resumen Seguro");
			}
			winSet = driver.getWindowHandles();
			winList = new ArrayList<String>(winSet);
			driver.switchTo().window(winList.get(winList.size() - 1));
			// System.out.println("HTML ventana 5 " + driver.getPageSource());
			if (!driver.getPageSource().contains("SEGUROS Y PAT")) {
				msg = "No se encontró Ventana Resumen Seguro";
				return msg;
			}
			driver.close();
			Thread.sleep(3000);

			/*
			 * if (!validaResumenTraspaso(driver)) { msg =
			 * "NO se encontró ventana resumen traspaso"; return msg; }
			 * 
			 * driver.switchTo().window(ventana3);
			 * 
			 * Thread.sleep(2000); if (func.validaAlert(driver)) { //
			 * System.out.println(driver.switchTo().alert().getText());
			 * driver.switchTo().alert().accept(); System.out.println("cantidad ventana " +
			 * driver.getWindowHandles().size()); Thread.sleep(4000);
			 * keyBoa.KeyPressTecl("ALT"); keyBoa.KeyPressTecl("F4");
			 * robot.keyRelease(KeyEvent.VK_ALT); robot.keyRelease(KeyEvent.VK_F4); //
			 * System.out.println("cantidad ventana 2 "+driver.getWindowHandles().size());
			 * if (driver.getWindowHandles().size() == 4) { winSet =
			 * driver.getWindowHandles(); winList = new ArrayList<String>(winSet); if
			 * (!func.waitWindows(4, winList, driver)) { msg = msg +
			 * "No se encontró el ventana \n"; return msg; } else {
			 * System.out.println("Encontró ventana 4 ++++ 2"); }
			 * 
			 * winSet = driver.getWindowHandles(); winList = new ArrayList<String>(winSet);
			 * driver.switchTo().window(winList.get(winList.size() - 1)); driver.close();
			 * Thread.sleep(3000); System.out.println(
			 * "Cantidad ventanas despues de cerrar ventana 4 " +
			 * driver.getWindowHandles().size()); } driver.switchTo().window(ventana3); if
			 * (func.validaAlert(driver)) {
			 * 
			 * driver.switchTo().alert().accept(); Thread.sleep(5000);
			 * keyBoa.KeyPressTecl("ESC"); Thread.sleep(3000);
			 * System.out.println("Ventanas despues de imprimir " +
			 * driver.getWindowHandles().size()); if (driver.getWindowHandles().size() == 4)
			 * { winSet = driver.getWindowHandles(); winList = new
			 * ArrayList<String>(winSet); if (!func.waitWindows(4, winList, driver)) { msg =
			 * msg + "No se encontró el ventana \n"; return msg; } else {
			 * System.out.println("Encontró ventana 4 *****"); }
			 * 
			 * winSet = driver.getWindowHandles(); winList = new ArrayList<String>(winSet);
			 * driver.switchTo().window(winList.get(winList.size() - 1)); driver.close();
			 * 
			 * Thread.sleep(3000);
			 * 
			 * if (driver.getWindowHandles().size() == 4) { winSet =
			 * driver.getWindowHandles(); winList = new ArrayList<String>(winSet); if
			 * (!func.waitWindows(4, winList, driver)) { msg = msg +
			 * "No se encontró el ventana \n"; return msg; } else {
			 * System.out.println("Encontró ventana 4 ******22222 "); }
			 * 
			 * winSet = driver.getWindowHandles(); winList = new ArrayList<String>(winSet);
			 * driver.switchTo().window(winList.get(winList.size() - 1)); driver.close();
			 * 
			 * }
			 * 
			 * System.out.println( "Cantidad de ventanas para firmar contrato " +
			 * driver.getWindowHandles().size()); driver.switchTo().window(ventana3);
			 * 
			 * if (func.validaAlert(driver)) { driver.switchTo().alert().accept(); }
			 * 
			 * } }
			 * 
			 * } else { System.out.println("No existe ventana"); }
			 */

		} catch (Exception e) {
			System.out.println("ERROR AL MOMENTO DE VALIDAR VENTANA DE RESUMEN DE SEGURO " + e.getMessage());
			msg = "ERROR AL MOMENTO DE VALIDAR VENTANA DE RESUMEN DE SEGURO " + e.getMessage();
			JOptionPane.showMessageDialog(null, "ERROR AL MOMENTO DE VALIDAR VENTANA DE RESUMEN DE SEGURO");
		}
		return msg;

	}

	public String validaResumenTraspaso(WebDriver driver) {
		String msg = "OK";
		try {

			Thread.sleep(3000);
			Set<String> winSet;
			List<String> winList;
			// System.out.println("HTML ventana Resumen Traspaso" +
			// driver.getPageSource().substring(0, 20000));

			if (driver.getWindowHandles().size() == 4) {
				winSet = driver.getWindowHandles();
				winList = new ArrayList<String>(winSet);
				if (!func.waitWindows(4, winList, driver)) {
					msg = "No sé Encontró ventana 4";
					return msg;
				}
				winSet = driver.getWindowHandles();
				winList = new ArrayList<String>(winSet);
				driver.switchTo().window(winList.get(winList.size() - 1));
				driver.close();
			}

		} catch (Exception e) {
			System.out.println("ERROR EN LA VENTANA RESUMEN TRASPASO" + e.getMessage());
			msg = "ERROR EN LA VENTANA RESUMEN TRASPASO" + e.getMessage();
		}
		return msg;
	}

	public String validaVentanaProductos(WebDriver driver) {
		String msg = "OK";
		try {
			Robot robot = new Robot();
			Thread.sleep(3000);
			Set<String> winSet;
			List<String> winList;

			winSet = driver.getWindowHandles();
			winList = new ArrayList<String>(winSet);
			if (!func.waitWindows(3, winList, driver)) {
				msg = "No sé Encontró ventana 3 Ventana Productos";
				return msg;
			}
			winSet = driver.getWindowHandles();
			winList = new ArrayList<String>(winSet);
			driver.switchTo().window(winList.get(winList.size() - 1));

			// System.out.println("HTML ventana productos
			// "+driver.getPageSource().toString());
			System.out.println("VALIDA VENTANA PRODUCTOS ");
			if (func.validaAlert(driver)) {
				driver.switchTo().alert().accept();
				Thread.sleep(6000);
				keyBoa.KeyPressTecl("ALT");
				keyBoa.KeyPressTecl("F4");
				robot.keyRelease(KeyEvent.VK_ALT);
				robot.keyRelease(KeyEvent.VK_F4);
				Thread.sleep(4000);
				//System.out.println("Cantidad Ventana ANTES DE VALIDAR " + driver.getWindowHandles().size());
				winSet = driver.getWindowHandles();
				winList = new ArrayList<String>(winSet);
				if (!func.waitWindows(4, winList, driver)) {
					msg = "NO ENCONTRÓ VENTANA 4";
					System.out.println("NO ENCONTRÓ VENTANA 4");
					return msg;
				}
				//System.out.println("Cantidad Ventana ANTES DE CERRAR" + driver.getWindowHandles().size());
				func.closeWindows(driver, 3);
				Thread.sleep(3000);
				//System.out.println("Cantidad Ventana DESPUES DE CERRAR" + driver.getWindowHandles().size());
				
			}

		} catch (Exception e) {
			System.out.println("ERROR AL VALIDAR VENTANA PRODUCTO " + e.getMessage());
			msg = "ERROR AL VALIDAR VENTANA PRODUCTO " + e.getMessage();
		}
		return msg;
	}

	public String validarFirmaContrato(WebDriver driver) {
		String msg = "OK";
		try {
			Robot robot = new Robot();
			Set<String> winSet;
			List<String> winList;
			winSet = driver.getWindowHandles();
			winList = new ArrayList<String>(winSet);
			if (!func.waitWindows(3, winList, driver)) {
				msg = "No sé Encontró ventana 3 Ventana Productos";
				return msg;
			}
			winSet = driver.getWindowHandles();
			winList = new ArrayList<String>(winSet);
			driver.switchTo().window(winList.get(winList.size() - 1));
			
			if (func.validaAlert(driver)) {
				driver.switchTo().alert().accept();
			}
			Thread.sleep(6000);
			keyBoa.KeyPressTecl("ESC");
			robot.keyRelease(KeyEvent.VK_ESCAPE);
			Thread.sleep(6000);
			System.out.println("2.0 Cantidad Ventana ANTES DE CERRAR" + driver.getWindowHandles().size());
			winSet = driver.getWindowHandles();
			winList = new ArrayList<String>(winSet);
			if (!func.waitWindows(4, winList, driver)) {
				msg = "NO ENCONTRÓ VENTANA 4";
				System.out.println("NO ENCONTRÓ VENTANA 4");
			}
			func.closeWindows(driver, 3);
			System.out.println("2.0 Cantidad Ventana DESPUES DE CERRAR" + driver.getWindowHandles().size());
			Thread.sleep(3000);
			winSet = driver.getWindowHandles();
			winList = new ArrayList<String>(winSet);
			if (!func.waitWindows(4, winList, driver)) {
				msg = "NO ENCONTRÓ VENTANA 4";
				System.out.println("NO ENCONTRÓ VENTANA 4");
			}
			func.closeWindows(driver, 3);
			
			Thread.sleep(3000);
			func.closeWindows(driver, 3);
			
			driver = func.waitWindows(3, driver);
			Thread.sleep(3000);
			if (func.validaAlert(driver)) {
				//keyBoa.KeyPressTecl("ENTER");
				//driver.switchTo().alert().accept();
				msg = func.validaMensajeAlert("¿Cliente firmó Hoja de Resumen?",driver);
			}
			
			

			
		} catch (Exception e) {
			System.out.println("ERROR AL MOMENTO VALIDAR LA FIRMA DE CONTRATO " + e.getMessage());
			msg = "ERROR AL MOMENTO VALIDAR LA FIRMA DE CONTRATO " + e.getMessage();
		}
		return msg;
	}

}
