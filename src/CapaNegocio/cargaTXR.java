package CapaNegocio;

import java.awt.Color;
import java.awt.SystemColor;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Iterator;

import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.table.DefaultTableModel;

import org.apache.poi.ss.usermodel.*;

import CargaDatos.conexionBD;

public class cargaTXR {
	private JPanel contentPane;
	conexionBD base = new conexionBD();
	public int n = 0;
	/* FUNCIONES ENCARGADA DE LEER ARCHIVO TXT */
	/**
	 * Esta funcion recibe el archivo Cobrconv en formato txt validando la separacion de columnas con |
	 * 
	 * @param direccion Ruta donde se encuentra el archivo
	 * @param PanelPorcentaje Permite actualizar el porcentaje de avance
	 * @param LabelPorcentaje Permite actualizar el porcentaje de avance
	 * @param CheckCobrconv Valida si el archivo fue cargado de una forma correcta
	 * @param JpCobr Para actualizar el panel y poder mostrar el proceso
	 */
	public void leerTxtCobrconv(String direccion, JPanel PanelPorcentaje,JLabel LabelPorcentaje, JCheckBox CheckCobrconv, JPanel JpCobr) {
		int contador = 0;
		String linea;
		String Tem = "";
		String ITEM[];
		int cantidadLineas;
		int porcentaje;
		int cont = -1;

		boolean resutado = direccion.contains("cobrconv");
		boolean resutado2 = direccion.contains(".txt");
		if (resutado == true && resutado2 == true) {

			try {
				base.EjecutarQuery("update LibrosSubidos set Estado=0 where Libros='Cobrconv'");
				Proceso lectxtpag = new Proceso();
				JpCobr.update(JpCobr.getGraphics());// para actualizar el panel y poder mostrar el proceso
				CheckCobrconv.setSelected(false);
				
				LabelPorcentaje.setForeground(Color.BLACK);
				LabelPorcentaje.setText("CALCULANDO...");
				LabelPorcentaje.setForeground(SystemColor.windowBorder);
				PanelPorcentaje.update(PanelPorcentaje.getGraphics());

				BufferedReader bf = new BufferedReader(new FileReader(direccion));
				BufferedReader bf2 = new BufferedReader(new FileReader(	direccion));
				cantidadLineas = (int) bf2.lines().count();
				base.EjecutarQuery("truncate table Libranza.dbo.CLIENTES  	truncate table Libranza.dbo.EMPRESAS	truncate table Libranza.dbo.FACTURACION");// elimina los datos de la tabla antes de ejecutar el proceso

				while ((linea = bf.readLine()) != null) {
					ITEM = linea.split("\\|");
					
					contador = contador + 1;
					porcentaje = (contador * 100) / cantidadLineas;
					
					if (porcentaje == (cont + 1)) {// permite mostrar el porcentaje una sola vez
						LabelPorcentaje.setText("de Cobrconv " + porcentaje+ "% " + " Van " + contador + " de "	+ cantidadLineas);
						PanelPorcentaje.update(PanelPorcentaje.getGraphics());// para actualizar el panel y poder mostrar el proceso
						cont = cont + 1;
						if(cont>=99) {cont=99;}
					}
					
					if (ITEM[0].equals("1")) {
						Tem = ITEM[1].toString().trim();
						// System.out.println(Tem);
					}
					
					if (contador == (cantidadLineas - 1)) {// para actualizar el panel y poder mostrar el proceso
						CheckCobrconv.setSelected(true);
						JpCobr.update(JpCobr.getGraphics());
						base.EjecutarQuery("update LibrosSubidos set Estado=1 where Libros='Cobrconv'");
					}
					lectxtpag.ProcesoCobrconv(linea, base, contador, Tem);
					
				}

				base.CerrarconexionBD();
				base = null;
			} catch (Exception ex) {
				System.out.println(ex);
				CheckCobrconv.setSelected(false);
				JpCobr.update(JpCobr.getGraphics());// para actualizar el panel y poder mostrar el proceso
				LabelPorcentaje.setForeground(Color.RED);
				LabelPorcentaje.setText("Hubo un error leyendo el archivo de Cobrconv " +contador);
				PanelPorcentaje.update(PanelPorcentaje.getGraphics());// para actualizar el panel y poder mostrar el proceso
			}
		} else {
			// System.out.println("Error subiendo el archivo de Cobrconv");
			CheckCobrconv.setSelected(false);
			JpCobr.update(JpCobr.getGraphics());// para actualizar el panel y poder mostrar el proceso

			LabelPorcentaje.setForeground(Color.RED);
			LabelPorcentaje.setText("Error subiendo el archivo de Cobrconv");
			PanelPorcentaje.update(PanelPorcentaje.getGraphics());// para actualizar el panel y poder mostrar el proceso
		}
	}

	// ====================================================================================
	/**
	 * Esta funcion recibe el archivo Desvinculados en formato txt validando la separacion de columnas con |
	 * 
	 * @param direccion  Ruta donde se encuentra el archivo
	 * @param CheckDesvinculados Valida si el archivo fue cargado de una forma correcta
	 * @param PanelPorcentaje  Permite actualizar el porcentaje de avance
	 * @param LabelPorcentajePermite actualizar el porcentaje de avance
	 * @param JpDesv Para actualizar el panel y poder mostrar el proceso
	 */
	public void leerTxtBaseDesvinculados(String direccion,JCheckBox CheckDesvinculados, JPanel PanelPorcentaje,	JLabel LabelPorcentaje, JPanel JpDesv) {
		int contador = 0;
		String linea;
		String ITEM[];
		int cantidadLineas;
		int porcentaje;
		int cont = -1;

		conexionBD base = new conexionBD();

		boolean resultado = direccion.contains("BASE DESVINCULADOS");
		boolean resultado2 = direccion.contains(".txt");

		if (resultado == true && resultado2 == true) {
			try {
				base.EjecutarQuery("update LibrosSubidos set Estado=0 where Libros='Desvinculado'");
				Proceso lectxtpag = new Proceso();
				CheckDesvinculados.setSelected(false);
				JpDesv.update(JpDesv.getGraphics());// para actualizar el panel y poder mostrar el proceso

				LabelPorcentaje.setText("CALCULANDO...");
				LabelPorcentaje.setForeground(SystemColor.windowBorder);
				PanelPorcentaje.update(LabelPorcentaje.getGraphics());

				BufferedReader bf = new BufferedReader(new FileReader(direccion));
				BufferedReader bf2 = new BufferedReader(new FileReader(	direccion));
				cantidadLineas = (int) bf2.lines().count();
				base.EjecutarQuery("truncate table Libranza.dbo.BASE_DESVINCULADO");// elimina los datos de latabla antes deejetutar el proceso

				while ((linea = bf.readLine()) != null) {
					ITEM = linea.split("\\|");// separación popr tabulacion
					if (ITEM[0].equals("CONVENIO")) {
						// System.out.println(ITEM[0]);
					} else {
						contador = contador + 1;
						porcentaje = (contador * 100) / (cantidadLineas - 1);
							if (porcentaje == (cont + 1)) {// permite mostrar el porcentaje una sola vez
								LabelPorcentaje.setText("Desvinculado "	+ porcentaje + "% " + " Van " + contador	+ " de " + (cantidadLineas - 1));
								PanelPorcentaje.update(PanelPorcentaje.getGraphics());// para actualizar el panel y poder mostrar elproceso
								cont = cont + 1;
							}
								if (contador == (cantidadLineas - 1)) {
									CheckDesvinculados.setSelected(true);
									JpDesv.update(JpDesv.getGraphics());// para actualizar el panel y poder mostrar el proceso
									base.EjecutarQuery("update LibrosSubidos set Estado=1 where Libros='Desvinculado'");
								}
						// System.out.println(porcentaje+"%");
						lectxtpag.ProcesoBaseDesvinculados(linea, base,contador);
					}

				}

				base.CerrarconexionBD();
				base = null;
			} catch (Exception ex) {
				// System.out.println("Hubo un error leyendo el archivo de BASE DESVINCULADOS");
				// System.out.println(ex);
				LabelPorcentaje.setForeground(Color.RED);
				LabelPorcentaje
						.setText("Hubo un error leyendo el archivo de BASE DESVINCULADOS");
				PanelPorcentaje.update(PanelPorcentaje.getGraphics());// para actualizar el panel y poder mostrar el proceso

				CheckDesvinculados.setSelected(false);
				JpDesv.update(JpDesv.getGraphics());// para actualizar el panel y poder mostrar el proceso

			}// fin del catch

		}// fin del if resultado
		else {// System.out.println("Error subiendo el archivo de BASE DESVINCULADOS");
			CheckDesvinculados.setSelected(false);
			JpDesv.update(JpDesv.getGraphics());// para actualizar el panel y
												// poder mostrar el proceso

			LabelPorcentaje.setForeground(Color.RED);
			LabelPorcentaje
					.setText("Error subiendo el archivo de BASE DESVINCULADOS");
			PanelPorcentaje.update(LabelPorcentaje.getGraphics());// para actualizar el panel y poder mostrar el proceso
		}// fin del else
	}// fin de la funcion desvinculado

	// ====================================================================================
/**
 *  Esta funcion recibe el archivo Cagmaest en formato txt validando la separacion de columnas con |
 * 
 * @param direccion  Ruta donde se encuentra el archivo
 * @param PanelPorcentaje  Permite actualizar el porcentaje de avance
 * @param LabelPorcentaje  Permite actualizar el porcentaje de avance
 * @param CheckCagmaest  Valida si el archivo fue cargado de una forma correcta
 * @param JpCag para actualizar el panel y poder mostrar el proceso
 */
	public void leerTxtCagmaest(String direccion, JPanel PanelPorcentaje,JLabel LabelPorcentaje, JCheckBox CheckCagmaest, JPanel JpCag,String fecha) {
		int contador = 0;
		String linea;
		String ITEM[];
		int cantidadLineas;
		int porcentaje;
		int cont = -1;
		conexionBD base = new conexionBD();
		boolean resutado = direccion.contains("cagmaest");
		boolean resutado2 = direccion.contains(".txt");
		if (resutado == true && resutado2 == true) {
			try {
				Proceso lectxtcag = new Proceso();
				base.EjecutarQuery("update LibrosSubidos set Estado=0,Corte='' where Libros='Cagmaest'");
				CheckCagmaest.setSelected(false);
				JpCag.update(JpCag.getGraphics());// para actualizar el panel y poder mostrar el proceso

				LabelPorcentaje.setText("CALCULANDO...");
				LabelPorcentaje.setForeground(SystemColor.windowBorder);
				PanelPorcentaje.update(PanelPorcentaje.getGraphics());

				BufferedReader bf = new BufferedReader(new FileReader(direccion));
				BufferedReader bf2 = new BufferedReader(new FileReader(	direccion));
				cantidadLineas = (int) bf2.lines().count();
				base.EjecutarQuery("truncate table Libranza.dbo.Cagmaest");// elimina los datos de la tabla antes ejecutar el proceso
																			
				while ((linea = bf.readLine()) != null) {

					contador++;
					porcentaje = (contador * 100) / cantidadLineas;

					if (porcentaje == (cont + 1)) {// permite mostrar el porcentaje una sola vez
						LabelPorcentaje.setText(porcentaje + "% " + " Van "+ contador + " de " + cantidadLineas);
						PanelPorcentaje.update(PanelPorcentaje.getGraphics());// para actualizar el panel y poder mostrar el proceso
						cont = cont + 1;
						if (cont >= 99) {
							cont = 99;
						}

						if (contador == cantidadLineas) {
							CheckCagmaest.setSelected(true);
							JpCag.update(JpCag.getGraphics());// para actualizar el panel y poder mostrar el proceso
							base.EjecutarQuery("update LibrosSubidos set Estado=1,Corte='"+fecha+"' where Libros='Cagmaest'");
						}
					}
					lectxtcag.ProcesoCagmaest(linea, base, contador);
				}

				base.CerrarconexionBD();
				base = null;
			}

			catch (Exception ex) {

				CheckCagmaest.setSelected(false);
				JpCag.update(JpCag.getGraphics());// para actualizar el panel y poder mostrar el proceso
				LabelPorcentaje.setForeground(Color.RED);
				LabelPorcentaje.setText("Hubo un error leyendo el archivo de Cagmaest" +contador);
				PanelPorcentaje.update(PanelPorcentaje.getGraphics());// para actualizar el panel y poder mostrar el proceso
			}
		} else {// System.out.println("Error subiedo el archivo de Cagmaest");

			CheckCagmaest.setSelected(false);
			JpCag.update(JpCag.getGraphics());// para actualizar el panel y poder mostrar el proceso
			LabelPorcentaje.setForeground(Color.RED);
			LabelPorcentaje.setText("Error subiedo el archivo de Cagmaest Linea"+ contador);
			PanelPorcentaje.update(PanelPorcentaje.getGraphics());// para actualizar el panel y poder mostrar el proceso

		}
	}

	// ====================================================================================
	/**
	 * Esta funcion recibe el archivo Parametrizados en formato txt validando la separacion de columnas con |
	 * 
	 * @param direccion Ruta donde se encuentra el archivo
	 * @param PanelPorcentaje Permite actualizar el porcentaje de avance
	 * @param LabelPorcentaje Permite actualizar el porcentaje de avance
	 * @param CheckParametrizado Valida si el archivo fue cargado de una forma correcta
	 * @param JpPar para actualizar el panel y poder mostrar el proceso
	 */
	public void leerTxtParametrizados(String direccion, JPanel PanelPorcentaje,JLabel LabelPorcentaje, JCheckBox CheckParametrizado, JPanel JpPar) {
		int contador = 0;
		String linea;
		String ITEM[];
		int cantidadLineas;
		int porcentaje;
		int cont = -1;
		conexionBD base = new conexionBD();
		boolean resutado = direccion.contains("PARAMETRIZADOS");
		boolean resutado2 = direccion.contains(".txt");
		if (resutado == true && resutado2 == true) {
			try {
				base.EjecutarQuery("update LibrosSubidos set Estado=0 where Libros='Parametrización'");
				Proceso lectxtpag = new Proceso();
				CheckParametrizado.setSelected(false);
				JpPar.update(JpPar.getGraphics());// para actualizar el panel y poder mostrar el proceso
				
				LabelPorcentaje.setText("CALCULANDO...");
				LabelPorcentaje.setForeground(SystemColor.windowBorder);
				PanelPorcentaje.update(PanelPorcentaje.getGraphics());
				
				BufferedReader bf = new BufferedReader(new FileReader(direccion));
				BufferedReader bf2 = new BufferedReader(new FileReader(	direccion));
				cantidadLineas = (int) bf2.lines().count();
				base.EjecutarQuery("truncate table Libranza.dbo.Parametrizados");// elimina los datos de la tabla antes de ejecutar el proceso
				
				while ((linea = bf.readLine()) != null) {
					
					contador = contador + 1;
					porcentaje = (contador * 100) / cantidadLineas;
					
					if (porcentaje == (cont + 1)) {// permite mostrar el porcentaje una sola vez
						LabelPorcentaje.setText(porcentaje + "% " + " Van "+ contador + " de " + cantidadLineas);
						PanelPorcentaje.update(PanelPorcentaje.getGraphics());// para actualizar el panel y poder mostrar el proceso
						cont = cont + 1;
						if(cont>99){cont=99;}
					}
					
					if (contador == cantidadLineas) {
						CheckParametrizado.setSelected(true);
						JpPar.update(JpPar.getGraphics());// para actualizar el panel y poder mostrar el proceso
						base.EjecutarQuery("update LibrosSubidos set Estado=1 where Libros='Parametrización'");
					}
					lectxtpag.ProcesoParametrizado(linea, base, contador);
				}
				
				base.CerrarconexionBD();
				base = null;
			}

			catch (Exception ex) {
				// System.out.println("Hubo un error leyendo el archivo de Cagmaest");
				CheckParametrizado.setSelected(false);
				JpPar.update(JpPar.getGraphics());// para actualizar el panel y poder mostrar el proceso

				LabelPorcentaje.setForeground(Color.RED);
				LabelPorcentaje.setText("Hubo un error leyendo el archivo de Parametrizados "	+ contador);
				PanelPorcentaje.update(PanelPorcentaje.getGraphics());// para actualizar el panel y poder mostrar el proceso
			}
		} else {// System.out.println("Error subiedo el archivo de Cagmaest");
			CheckParametrizado.setSelected(false);
			JpPar.update(JpPar.getGraphics());// para actualizar el panel y poder mostrar el proceso
			LabelPorcentaje.setForeground(Color.RED);
			LabelPorcentaje.setText("Error subiedo el archivo de Parametrizados");
			PanelPorcentaje.update(PanelPorcentaje.getGraphics());// para actualizar el panel y poder mostrar el proceso
		}
	}

	// =====================================================================================
//FUNCION QUE PERMITE GENERAR EL ARCHIVO EN FORMATO TXT
	/**
	 * esta funcion permite generar una archivo en formato txt del libro Cobrconv actualizado. 
	 * con separacion de columnas con | y una longitud especifica por cada columna
	 * 
	 * @param direccion Ruta donde se se va a generar el archivo
	 * @param PanelPorcentaje Permite actualizar el porcentaje de avance
	 * @param LabelPorcentaje Permite actualizar el porcentaje de avance
	 * @throws SQLException Conexión  a la base de datos
	 * @throws IOException Conexión  a la base de datos
	 */
	public void escribirTxtCobrconv(String direccion, JPanel PanelPorcentaje,JLabel LabelPorcentaje) throws SQLException, IOException {
		int porcentaje;
		int cont = -1;
		int n = 0;
		boolean Existe;
		int Nit;
		int Nombre_Empresa;
		int Direccion;
		int Telefono;
		int Ciudad;
		int Departamento;
		int CC;
		int Identificacion;
		int Nombre;
		int Obligacion;
		int Oficina;
		int VrCM;
		int VriMora;
		int VtPagar;
		int Cod_Oficina;

		String val1_3;
		String val2_3;
		String val3_3;
		String val4_3;
		String val5_3;
		String val6_3;
		String val9_3;
		String val10_3;
		String val7;
		String val6;
		String val5;
		String val4;
		String val3;
		String val2;
		String valor;

		String Espacio = "";
		String NE_Espacios = "";
		String Dir_Espacios = "";
		String Tel_Espacios = "";
		String Ci_Espacios = "";
		String Dep_Espacios = "";

		String Espacio3 = "";
		String Idn_Espacios3 = "";
		String Nom_Espacios3 = "";
		String Obl_Espacios3 = "";
		String Ofc_Espacios3 = "";
		String VrM_Espacios3 = "";
		String[] Cientifico;
		String Ceros = "";
		String Ceros10 = "";
		String PCero = "";
		String val;
		String Esp_Cod_Oficina = "";

		conexionBD base = new conexionBD();

		File file = new File(direccion + "\\Cobrconv.txt");
		Existe = file.exists();
		if (Existe == true) {// borra el archivo Cobrconv siempre y cuando
								// exista
			file.delete();
		}

		FileWriter filewrite = new FileWriter(file.getAbsoluteFile(), true);
		BufferedWriter bufer = new BufferedWriter(filewrite);
		DefaultTableModel result;
		LabelPorcentaje.setText("GENERANDO COBRCONV...");
		PanelPorcentaje.update(PanelPorcentaje.getGraphics());
		String RT1 = "SELECT * FROM [Libranza].[dbo].[Cobrconv] ORDER BY CAST(Orden AS int)";//trae la informacion contenida en la base de datos
		result = base.ConsultarQuery(RT1);
		int cantiidad = result.getRowCount();

		for (int x = 0; x < result.getRowCount(); x++) {

			Espacio = "";
			NE_Espacios = "";
			Dir_Espacios = "";
			Tel_Espacios = "";
			Ci_Espacios = "";
			Dep_Espacios = "";
			Espacio3 = "";
			Idn_Espacios3 = "";
			Nom_Espacios3 = "";
			Obl_Espacios3 = "";
			Ofc_Espacios3 = "";
			VrM_Espacios3 = "";
			Ceros = "";
			Ceros10 = "";
			PCero = "";
			Esp_Cod_Oficina = "";

			valor = result.getValueAt(x, 0).toString();

			switch (valor) {
			case "1":
				val2 = result.getValueAt(x, 2).toString();
				Nit = val2.length();
				if(Nit<10){
					for (int a =0;a<(10 - Nit);a++){
						Espacio = " "+Espacio;
					}
				}
				val3 = result.getValueAt(x, 3).toString();
				Nombre_Empresa = val3.length();
				if(Nombre_Empresa<30){
					for (int a2=0;a2<(30 - Nombre_Empresa);a2++){
						NE_Espacios = " "+NE_Espacios;
					}
				}
				val4 = result.getValueAt(x, 4).toString();
				Direccion = val4.length();
				if(Direccion<30){
					for (int a3=0;a3<(30 - Direccion);a3++){
						Dir_Espacios = " "+Dir_Espacios;
					}
				}
				val5 = result.getValueAt(x, 5).toString();
				Telefono = val5.length();
				if(Telefono<10){
					for (int a4=0;a4<(10 - Telefono);a4++){
						Tel_Espacios = " "+Tel_Espacios;
					}
				}
				val6 = result.getValueAt(x, 6).toString();
				Ciudad = val6.length();
				if(Ciudad<20){
					for (int a5=0;a5<(20 - Ciudad);a5++){
						Ci_Espacios = " "+Ci_Espacios;
					}
				}
				val7 = result.getValueAt(x, 7).toString();
				Departamento = val7.length();
				if(Departamento<20){
					for (int a6=0;a6<(20 - Departamento);a6++){
						Dep_Espacios = " "+Dep_Espacios;
					}
				}
				break;// fin del item 1 Empresa
			// ====================================================================================================
			case "2":

				break;//  fin del item 2 Facturacion
			// ====================================================================================================
			case "3":
				val1_3 = result.getValueAt(x, 1).toString();
				CC = val1_3.length();
				if(CC<3){
					for (int c=0;c<(3 - CC);c++){
						Espacio3 = " "+Espacio3;
					}
				}
				val2_3 = result.getValueAt(x, 2).toString();
				Identificacion = val2_3.length();
				if(Identificacion<20){
					for (int c2=0;c2<(20 - Identificacion);c2++){
						Idn_Espacios3 = " "+Idn_Espacios3;
					}
				}
				val3_3 = result.getValueAt(x, 3).toString();
				Nombre = val3_3.length();
				if(Nombre<64){
					for (int c3=0;c3<(64 - Nombre);c3++){
						Nom_Espacios3 = " "+Nom_Espacios3;
					}
				}
				val4_3 = result.getValueAt(x, 4).toString();
				Obligacion = val4_3.length();
				if(Obligacion<20){
					for (int c4=0;c4<(20 - Obligacion);c4++){
						Obl_Espacios3 = " "+Obl_Espacios3;
					}
				}
				val5_3 = result.getValueAt(x, 5).toString();
				Cod_Oficina = val5_3.length();
				if (Cod_Oficina < 10) {
					for (int i = 0; i < (10 - Cod_Oficina); i++) {
						Esp_Cod_Oficina = " " + Esp_Cod_Oficina;
					}
				}
				val6_3 = result.getValueAt(x, 6).toString();
				Oficina = val6_3.length();
				if(Oficina<64){
					for (int c5=0;c5<(64 - Oficina);c5++){
						Ofc_Espacios3 = " "+Ofc_Espacios3;
					}
				}
				val9_3 = result.getValueAt(x, 9).toString();
				boolean resutado9 = val9_3.contains("E");//convierte un numero de metodo cientifico a long
				if (resutado9 == true) {
					double numEntero9 = Double.parseDouble(val9_3);
					long numeroMultiplicador9 = Math.round(numEntero9);
					val9_3 = Long.toString(numeroMultiplicador9);
				}
				VtPagar = val9_3.replace(".00", "").replace(".0", "").length();
				// PCero=".00";
				if (VtPagar < 12) {
					for (int i = 0; i < (12 - VtPagar); i++) {
						Ceros = "0" + Ceros;
					}
				}
				val10_3 = result.getValueAt(x, 10).toString();
				boolean resutado = val10_3.contains("E");
				if (resutado == true) {//convierte un numero de metodo cientifico a long
					double numEntero = Double.parseDouble(val10_3);
					long numeroMultiplicador = Math.round(numEntero);
					val10_3 = Long.toString(numeroMultiplicador);
				}
				VriMora = val10_3.replace(".00", "").replace(".0", "").length();
				if (VriMora < 12) {
					for (int i = 0; i < (12 - VriMora); i++) {
						Ceros10 = "0" + Ceros10;
					}
				}

				break;// fin 3
			// ====================================================================================================

			default:
				System.out.println("Dato Invalido");
				break;
			}

			switch (valor) {
			case "2":
				val =	result.getValueAt(x, 0).toString().trim() + "|"
						+ result.getValueAt(x, 1).toString().trim() + "|"
						+ result.getValueAt(x, 2).toString().trim() + "|"
						+ result.getValueAt(x, 3).toString().trim() + "|"
						+ result.getValueAt(x, 4).toString().trim();
				break;
			case "1":
				val =	result.getValueAt(x, 0).toString().trim() + 
					"|"	+ result.getValueAt(x, 1).toString().trim() + Espacio3+
					"|"	+ result.getValueAt(x, 2).toString().trim() + Espacio + Idn_Espacios3 +
					"|"	+ result.getValueAt(x, 3).toString().trim()+ NE_Espacios + Nom_Espacios3 +
					"|"	+ result.getValueAt(x, 4).toString().trim()	+ Dir_Espacios + Obl_Espacios3 + 
					"|"	+ result.getValueAt(x, 5).toString().trim()	+ Tel_Espacios + 
					"|"	+ result.getValueAt(x, 6).toString().trim()	+ Ci_Espacios + Ofc_Espacios3 + 
					"|"	+ result.getValueAt(x, 7).toString().trim()	+ Dep_Espacios + 
					"|"	+ result.getValueAt(x, 8).toString().trim();
				break;
			default:
				val10_3 = result.getValueAt(x, 10).toString();
				boolean resutado = val10_3.contains("E");
				if (resutado == true) {
					double numEntero = Double.parseDouble(val10_3);
					long numeroMultiplicador = Math.round(numEntero);
					val10_3 = Long.toString(numeroMultiplicador) + ".0";
				}
				val9_3 = result.getValueAt(x, 9).toString();
				boolean resutado9 = val9_3.contains("E");
				if (resutado9 == true) {
					double numEntero9 = Double.parseDouble(val9_3);
					long numeroMultiplicador9 = Math.round(numEntero9);
					val9_3 = Long.toString(numeroMultiplicador9) + ".0";
				}

				val = 		result.getValueAt(x, 0).toString().trim() + 
						"|"+ result.getValueAt(x, 1).toString().trim() + Espacio3+ 
						"|"+ result.getValueAt(x, 2).toString().trim()	+ Espacio + Idn_Espacios3 + 
						"|"+ result.getValueAt(x, 3).toString().trim()	+ NE_Espacios + Nom_Espacios3 + 
						"|"+ result.getValueAt(x, 4).toString().trim()	+ Dir_Espacios + Obl_Espacios3 + 
						"|"+ Esp_Cod_Oficina	+ result.getValueAt(x, 5).toString().trim()	+ Tel_Espacios + 
						"|"+ result.getValueAt(x, 6).toString().trim()	+ Ci_Espacios + Ofc_Espacios3 + 
						"|"+ result.getValueAt(x, 7).toString().trim()	+ Dep_Espacios + 
						"|"+ result.getValueAt(x, 8).toString().trim() +
						"|"+ Ceros + val9_3 + "0" + 
						"|" + Ceros10 + val10_3 + "0"+ 
						"|" + result.getValueAt(x, 11).toString().trim()+ 
						"|" + result.getValueAt(x, 12).toString().trim();
				break;
			}

			n = n + 1;
			porcentaje = (n * 100) / cantiidad;

			if (porcentaje == (cont + 1)) {// permite mostrar el porcentaje una sola vez
				LabelPorcentaje.setText(porcentaje + "% " + " Van " + n	+ " de " + cantiidad);
				PanelPorcentaje.update(PanelPorcentaje.getGraphics());// para actualizar el panel y poder mostrar el proceso
				cont = cont + 1;
				if(cont>99){cont=99;}
			}
			bufer.write(val);
			bufer.newLine();
		}
		bufer.close();
	}

	// *****************************************************************************************
	//									PAGOS
	// *****************************************************************************************
/**
 * Esta funcion recibe el archivo Bancolombia en formato txt validando la separacion de columnas con |
 * 
 * @param direccion Ruta donde se encuentra el archivo
 * @param PanelPorcentaje Permite actualizar el porcentaje de avance
 * @param LabelPorcentaje Permite actualizar el porcentaje de avance
 * @param NomArchivo captura el nombre del archivo para almacenarlo en la BD
 */
	public void leerTxtBancolombia(String direccion, JPanel PanelPorcentaje,	JLabel LabelPorcentaje, String NomArchivo) {
		int contador = 0;
		String linea;
		String ITEM[];
		int cantidadLineas;
		int porcentaje;
		int cont = 0;

		conexionBD base = new conexionBD();

		boolean resutado = direccion	.contains("BANCOLOMBIA");// para actualizar el panel y poder mostrar el proceso
		boolean resutado2 = direccion.contains(".txt");
		if (resutado == true && resutado2 == true) {

			try {
				Proceso lectxtpag = new Proceso();
				LabelPorcentaje.setForeground(Color.BLACK);
				LabelPorcentaje.setText("CALCULANDO...");
				PanelPorcentaje.update(PanelPorcentaje.getGraphics());
				BufferedReader bf = new BufferedReader(new FileReader(direccion));
				BufferedReader bf2 = new BufferedReader(new FileReader(direccion));
				cantidadLineas = (int) bf2.lines().count();
				base.EjecutarQuery("truncate table Libranza.dbo.BANCOLOMBIA");// elimina los datos de la tabla antes de ejecutar el proceso
				while ((linea = bf.readLine()) != null) {
					contador ++;
					porcentaje = ((contador-1) * 100) / (cantidadLineas-1);//el -1 es para no agregart el titulo
			if ( linea.contains("Fecha|Nit|") ){
				System.out.println(linea);
			}
				else{
					if (porcentaje >= (cont + 1)) {// permite mostrar el porcentaje una sola vez
						LabelPorcentaje.setForeground(Color.BLACK);
						LabelPorcentaje.setText("Porcentaje de carga de Bancolombia "+ porcentaje + "% " + " Van "+ contador + " de " + cantidadLineas);
						PanelPorcentaje.update(PanelPorcentaje.getGraphics());// para actualizar el panel y poder mostrar el proceso
						if(porcentaje>cont){cont=porcentaje;}
						cont ++;
						if(cont>=99){cont=99;}
					}
					lectxtpag.ProcesoBancolombia(linea, base);
				}
			}
					if ((contador-1) == (cantidadLineas-1) ) {//sube el registro  a la DataCobrconv siempre y cuando se suban la totalidad de lo s registros
						base.EjecutarQuery("update [Libranza].[dbo].[DataCobrconv] set Nombre ='"+ NomArchivo + "' where posicion=7");
					}
				base.CerrarconexionBD();
				base = null;
			} catch (Exception ex) {
				LabelPorcentaje.setForeground(Color.RED);
				LabelPorcentaje.setText("Hubo un error leyendo el archivo de BANCOLOMBIA en la Lìnea " + (contador-1));
				PanelPorcentaje.update(PanelPorcentaje.getGraphics());// para actualizar el panel y poder mostrar el proceso
			}
		} else {
			LabelPorcentaje.setForeground(Color.RED);
			LabelPorcentaje.setText("Error subiendo el archivo de BANCOLOMBIA");
			PanelPorcentaje.update(LabelPorcentaje.getGraphics());// para actualizar el panel y poder mostrar el proceso
		}
	}

	// ====================================================================================
/**
 * Lee los 6 archivos de cobrconv  en formato txt validando la separacion de columnas con |
 * 
 * @param direccion Ruta donde se encuentra el archivo
 * @param PanelPorcentaje Permite actualizar el porcentaje de avance
 * @param LabelPorcentaje Permite actualizar el porcentaje de avance
 * @param L contiene la posicion del archivo ejemlplo en la BD de SQL existen 5 tablas P_Cobrconv2,  P_Cobrconv3 ... P_Cobrconv6. L indica en que tabla se tiene que almacenar los datos
 * @param NomArchivo  captura el nombre del archivo para almacenarlo en la BD
 */
	public void leerTxtCobrconv2(String direccion, JPanel PanelPorcentaje,JLabel LabelPorcentaje, int L, String NomArchivo) {
		int contador = 0;
		String linea;
		String Tem = "";
		String ITEM[];
		int cantidadLineas;
		int porcentaje;
		int cont = -1;
		String BD1 = "insert into P_Cobrconv"+ L+ " values('%s','%s','%s','%s','%s','%s','%s','%s','%s','%s','%s','%s','%s','%s')";

		conexionBD base = new conexionBD();

		boolean resutado = direccion.contains("cobrconv");
		boolean resutado2 = direccion.contains(".txt");
		if (resutado == true && resutado2 == true) {

			try {

				Proceso lectxtpag = new Proceso();

				LabelPorcentaje.setText("CALCULANDO...");
				LabelPorcentaje.setForeground(SystemColor.windowBorder);
				PanelPorcentaje.update(PanelPorcentaje.getGraphics());

				BufferedReader bf = new BufferedReader(	new FileReader(direccion));
				BufferedReader bf2 = new BufferedReader(new FileReader(	direccion));
				cantidadLineas = (int) bf2.lines().count();
				base.EjecutarQuery("truncate table Libranza.dbo.P_Cobrconv" + L);// elimina los datos de la tabla antes de ejecutar el proceso

				while ((linea = bf.readLine()) != null) {
					ITEM = linea.split("\\|");
					contador++;
					porcentaje = (contador * 100) / cantidadLineas;

					if (porcentaje >= (cont + 1)) {// permite mostrar el porcentaje una sola vez
						LabelPorcentaje.setForeground(Color.BLACK);
						LabelPorcentaje.setText("Porcentaje de carga de Cobrconv "	+ porcentaje + "% " + " Van "+ contador + " de " + cantidadLineas);
						PanelPorcentaje.update(PanelPorcentaje.getGraphics());// para actualizar el panel y poder mostrar el proceso
						if(porcentaje>cont){cont=porcentaje;}
						cont = cont + 1;
						if (cont >99){cont=99;}
					}

					if (ITEM[0].equals("1")) {
						Tem = ITEM[1].toString().trim();
					}
					if (contador == cantidadLineas) {
						base.EjecutarQuery("update [Libranza].[dbo].[DataCobrconv] set Nombre ='"+ NomArchivo + "' where posicion=" + L);
					}

					lectxtpag.ProcesoCobrconv2(linea, base, L, Tem);

				}

				base.CerrarconexionBD();
				base = null;
			} catch (Exception ex) {
				System.out.println(ex);
				LabelPorcentaje.setForeground(Color.RED);
				LabelPorcentaje.setText("Hubo un error leyendo el archivo de Cobrconv" + contador);
				PanelPorcentaje.update(PanelPorcentaje.getGraphics());// para actualizar el panel y poder mostrar el proceso
			}
		} else {
			// System.out.println("Error subiendo el archivo de Cobrconv");

			LabelPorcentaje.setForeground(Color.RED);
			LabelPorcentaje.setText("Error subiendo el archivo de Cobrconv");
			PanelPorcentaje.update(PanelPorcentaje.getGraphics());// para actualizar el panel y poder mostrar el proceso

		}
	}

	// ====================================================================================
/**
 * lee el archivo cobrconv del mes actual en formato txt validando la separacion de columnas con |
 * 
 * @param direccion Ruta donde se encuentra el archivo
 * @param PanelPorcentaje Permite actualizar el porcentaje de avance
 * @param LabelPorcentaje Permite actualizar el porcentaje de avance
 * @param NomArchivo captura el nombre del archivo para almacenarlo en la BD
 */
	public void leerTxtCobrconv0(String direccion, JPanel PanelPorcentaje,	JLabel LabelPorcentaje, String NomArchivo) {
		int contador = 0;
		String linea;
		String Tem = "";
		String ITEM[];
		int cantidadLineas;
		int porcentaje;
		int cont = -1;
		conexionBD base = new conexionBD();

		boolean resutado = direccion.contains("cobrconv");
		boolean resutado2 = direccion.contains(".txt");
		if (resutado == true && resutado2 == true) {

			try {

				Proceso lectxtpag = new Proceso();

				LabelPorcentaje.setText("CALCULANDO...");
				LabelPorcentaje.setForeground(SystemColor.windowBorder);
				PanelPorcentaje.update(PanelPorcentaje.getGraphics());

				BufferedReader bf = new BufferedReader(
						new FileReader(direccion));
				BufferedReader bf2 = new BufferedReader(new FileReader(	direccion));
				cantidadLineas = (int) bf2.lines().count();
				base.EjecutarQuery("truncate table Libranza.dbo.Cobrconv");// elimina los datos de la tabla antes de ejecutar el proceso

				while ((linea = bf.readLine()) != null) {
					ITEM = linea.split("\\|");
					contador = contador + 1;
					porcentaje = (contador * 100) / cantidadLineas;

					if (porcentaje >= (cont + 1)) {// permite mostrar el porcentaje una sola vez
						LabelPorcentaje.setForeground(Color.BLACK);
						LabelPorcentaje.setText("Porcentaje de carga de Cobrconv " + porcentaje	+ "% " + " Van " + contador + " de "+ cantidadLineas);
						PanelPorcentaje.update(PanelPorcentaje.getGraphics());// para actualizar el panel y poder mostrar el proceso
						if(porcentaje>cont){cont=porcentaje;}
						cont = cont + 1;
						if (cont>99){cont=99;}
					}

					if (ITEM[0].equals("1")) {
						Tem = ITEM[1].toString().trim();
						// System.out.println(Tem);
					}
					lectxtpag.ProcesoCobrconv0(linea, base, contador, Tem);

					if (contador == cantidadLineas) {
						base.EjecutarQuery("update [Libranza].[dbo].[DataCobrconv] set Nombre ='"+ NomArchivo + "' where posicion=1");
					}

				}

				base.CerrarconexionBD();
				base = null;
			} catch (Exception ex) {
				System.out.println(ex);
				LabelPorcentaje.setForeground(Color.RED);
				LabelPorcentaje	.setText("Hubo un error leyendo el archivo de Cobrconv" +contador);
				PanelPorcentaje.update(PanelPorcentaje.getGraphics());// para actualizar el panel y poder mostrar el proceso
			}
		} else {
			// System.out.println("Error subiendo el archivo de Cobrconv");
			LabelPorcentaje.setForeground(Color.RED);
			LabelPorcentaje.setText("Error subiendo el archivo de Cobrconv");
			PanelPorcentaje.update(PanelPorcentaje.getGraphics());// para actualizar el panel y poder mostrar el proceso

		}
	}

	// ====================================================================================
/**
 *  Lee los 3 archivos de Catradia  en formato txt validando la separacion de columnas con |
 * 
 * @param direccion Ruta donde se encuentra el archivo
 * @param PanelPorcentaje Permite actualizar el porcentaje de avance
 * @param LabelPorcentaje Permite actualizar el porcentaje de avance
 * @param L contiene la posicion del archivo ejemlplo en la BD de SQL existen 3 tablas catradia1,catradia2,  catradia3 . L indica en que tabla se tiene que almacenar los datos
 * @param NomArchivo captura el nombre del archivo para almacenarlo en la BD
 * @param validar2 indica el filtro que se tiene que hacer en la tabla, por defecto es PAGLIBRANZ
 */
	public void leerTxtCatradia(String direccion, JPanel PanelPorcentaje,JLabel LabelPorcentaje, int L, String NomArchivo,String validar2) {
		int contador = 0;
		String linea;
		String Tem = "";
		String ITEM[];
		int cantidadLineas;
		int porcentaje;
		int cont = -1;
		int titulo=0;
		// String
		// BD1="insert into Catradia"+L+" values('%s','%s','%s','%s','%s','%s','%s','%s','%s')";

		conexionBD base = new conexionBD();

		boolean resutado = direccion.contains("catradia");
		boolean resutado2 = direccion.contains(".txt");
		if (resutado == true && resutado2 == true) {

			try {

				Proceso lectxtpag = new Proceso();

				LabelPorcentaje.setText("CALCULANDO...");
				LabelPorcentaje.setForeground(SystemColor.windowBorder);
				PanelPorcentaje.update(PanelPorcentaje.getGraphics());

				BufferedReader bf = new BufferedReader(new FileReader(direccion));
				BufferedReader bf2 = new BufferedReader(new FileReader(direccion));
				cantidadLineas = (int) bf2.lines().count();
				base.EjecutarQuery("truncate table Libranza.dbo.Catradia" + L);// elimina los datos de la tabla antes de ejecutar el proceso

				while ((linea = bf.readLine()) != null) {
					
					contador++;
					
							if(linea.contains("BANCO AGRARIO DE COLOMBIA") || linea.contains("ARCHIVO TRANSACCIONES") || linea.contains("Fecha Desde:") || linea.isEmpty()==true || linea.contains("---------------")
									|| linea.contains("FechaMov")){//se salta los titulos del archivo
								System.out.println(linea);
								 titulo++;
								}
				else{
					//ITEM = linea.split("\\|");
					
				//	if (contador > 1) { // arranca a partir de la linea 2 del archivo txt
						porcentaje = (contador * 100) / cantidadLineas;
						
						if (porcentaje >= (cont + 1)) {// permite mostrar el porcentaje una sola vez
							LabelPorcentaje.setForeground(Color.BLACK);
							LabelPorcentaje.setText("Porcentaje de carga de Catradia "+ porcentaje + "% " + " Van "+ contador + " de "+ cantidadLineas);
							PanelPorcentaje.update(PanelPorcentaje.getGraphics());// para actualizar el panel y poder mostrar el proceso
							if(porcentaje>cont){cont=porcentaje;}
							cont = cont + 1;
							if (cont >= 100) {
								cont = 99;
							}
						}
						lectxtpag.ProcesoCatradia(linea, base, L,validar2);

					//}
				}
				}
				if (contador == cantidadLineas) {
					base.EjecutarQuery("update [Libranza].[dbo].[DataCobrconv] set Nombre ='"+ NomArchivo + "' where posicion=" + (L + 7));
					if (L == 1) {
						Date Fecha=new Date();
						SimpleDateFormat FormatoFecha=new SimpleDateFormat("dd/MM/YYYY HH:mm");//pasa la fecha a formato String
						String FechaActual =FormatoFecha.format(Fecha);
						base.EjecutarQuery("Insert into [Libranza].[dbo].[CatradiaHistorial] SELECT [FechaMov],[tr_tran],[tr_banco],[tr_ofi_oper],[tr_ofi_usu],[dtr_concepto],[dtr_monto_mn],[dtr_cuenta],[tr_estado],'' FROM [Libranza].[dbo].[Catradia1]"
														+" update [Libranza].[dbo].CatradiaHistorial set Fecha='"+FechaActual+"' where Fecha is null or fecha =''");
					}
				}

				base.CerrarconexionBD();
				base = null;
			} catch (Exception ex) {
				System.out.println(ex);
				LabelPorcentaje.setForeground(Color.RED);
				LabelPorcentaje	.setText("Hubo un error leyendo el archivo de Catradia en la linea "+contador);
				PanelPorcentaje.update(PanelPorcentaje.getGraphics());// para actualizar el panel y poder mostrar el proceso
			}
		} else {
			// System.out.println("Error subiendo el archivo de Cobrconv");
			LabelPorcentaje.setForeground(Color.RED);
			LabelPorcentaje.setText("Error subiendo el archivo de Catradia "+contador);
			PanelPorcentaje.update(PanelPorcentaje.getGraphics());// para actualizar el panel y poder mostrar el proceso
		}
	}

	// ====================================================================================
/**
 *  Lee los 3 archivos de Cagmaest  en formato txt validando la separacion de columnas con |
 * 
 * @param direccion  c
 * @param PanelPorcentaje Permite actualizar el porcentaje de avance
 * @param LabelPorcentaje Permite actualizar el porcentaje de avance
 * @param L contiene la posicion del archivo ejemlplo en la BD de SQL existen 3 tablas CagmaestP1,CagmaestP2,  CagmaestP3 . L indica en que tabla se tiene que almacenar los datos
 * @param NomArchivo captura el nombre del archivo para almacenarlo en la BD
 */
	public void leerTxtCagmaestP(String direccion, JPanel PanelPorcentaje,JLabel LabelPorcentaje, int L, String NomArchivo) {
		int contador = 0;
		String linea;
		String ITEM[];
		int cantidadLineas;
		int porcentaje;
		int cont = -1;
		conexionBD base = new conexionBD();
		boolean resutado = direccion.contains("cagmaest");
		boolean resutado2 = direccion.contains(".txt");

		if (resutado == true && resutado2 == true) {
			try {
				Proceso lectxtcag = new Proceso();

				LabelPorcentaje.setText("CALCULANDO...");
				LabelPorcentaje.setForeground(SystemColor.windowBorder);
				PanelPorcentaje.update(PanelPorcentaje.getGraphics());

				BufferedReader bf = new BufferedReader(new FileReader(direccion));
				BufferedReader bf2 = new BufferedReader(new FileReader(direccion));
				cantidadLineas = (int) bf2.lines().count();
				base.EjecutarQuery("truncate table Libranza.dbo.CagmaestP"+L);// elimina los datos de la tabla antes de ejecutar el proceso
			
				while ((linea = bf.readLine()) != null) {

					contador = contador + 1;
					porcentaje = (contador * 100) / cantidadLineas;

					if (porcentaje >= (cont + 1)) {// permite mostrar el porcentaje una sola vez
						LabelPorcentaje.setText("Porcentaje de Cagmaest "+ porcentaje + "% " + " Van "	+ contador + " de " + cantidadLineas);
						PanelPorcentaje.update(PanelPorcentaje.getGraphics());// para actualizar el panel y poder mostrar el proceso
						if(porcentaje>cont){cont=porcentaje;}
						cont = cont + 1;
						if (cont >= 99) {
							cont = 99;
						}
					}
					lectxtcag.ProcesoCagmaestP(linea, base, contador,L);
				}
					if (contador == cantidadLineas) {
						base.EjecutarQuery("update [Libranza].[dbo].[DataCobrconv] set Nombre ='"+ NomArchivo + "' where posicion=" + (L + 10));
					}
				base.CerrarconexionBD();
				base = null;
			}
			catch (Exception ex) {
				LabelPorcentaje.setForeground(Color.RED);
				LabelPorcentaje.setText("Hubo un error leyendo el archivo de Cagmaest en la lìnea "+contador);
				PanelPorcentaje.update(PanelPorcentaje.getGraphics());// para actualizar el panel y poder mostrar el proceso
			}
		} else {// System.out.println("Error subiedo el archivo de Cagmaest");
			LabelPorcentaje.setForeground(Color.RED);
			LabelPorcentaje.setText("Error subiedo el archivo de Cagmaest");
			PanelPorcentaje.update(PanelPorcentaje.getGraphics());// para actualizar el panel y poder mostrar el proceso

		}
	}

// ====================================================================================
/**
 *  lee el archivo Parametrizados del mes actual en formato txt validando la separacion de columnas con |
 * 
 * @param direccion Ruta donde se encuentra el archivo
 * @param PanelPorcentaje Permite actualizar el porcentaje de avance
 * @param LabelPorcentaje Permite actualizar el porcentaje de avance
 * @param NomArchivo captura el nombre del archivo para almacenarlo en la BD
 */
	public void leerTxtParametrizadosP(String direccion, JPanel PanelPorcentaje,	JLabel LabelPorcentaje,String NomArchivo) {
		int contador = 0;
		 String linea;
		String ITEM[];
		int cantidadLineas;
		int porcentaje;
		int cont = -1;
		conexionBD base = new conexionBD();
		boolean resutado = direccion.contains("PARAMETRIZADOS");
		boolean resutado2 = direccion.contains(".txt");
		if (resutado == true && resutado2 == true) {
			try {
				Proceso lectxtpag = new Proceso();
				LabelPorcentaje.setText("CALCULANDO...");
				LabelPorcentaje.setForeground(SystemColor.windowBorder);
				PanelPorcentaje.update(PanelPorcentaje.getGraphics());

				BufferedReader bf = new BufferedReader(new FileReader(direccion));
				BufferedReader bf2 = new BufferedReader(new FileReader(	direccion));
				cantidadLineas = (int) bf2.lines().count();
				base.EjecutarQuery("truncate table Libranza.dbo.ParametrizadosP");// elimina los datos de la tabla antes de ejecutar el proceso

				while ((linea = bf.readLine()) != null) {
				if (linea.contains("Convenio|Estado")==true){System.out.println(linea);} //quita los titulos
				else{
					contador = contador + 1;
					porcentaje = (contador * 100) / (cantidadLineas-1);

					if (porcentaje >= (cont + 1)) {// permite mostrar el porcentaje una sola vez
						LabelPorcentaje.setForeground(Color.BLACK);
						LabelPorcentaje.setText(porcentaje + "% " + " Van "+ contador + " de " +  (cantidadLineas-1));
						PanelPorcentaje.update(PanelPorcentaje.getGraphics());// para actualizar el panel y poder mostrar el proceso
						if(porcentaje>cont){cont=porcentaje;}
						cont = cont + 1;
						if(cont>99){cont=99;}
					}
						lectxtpag.ProcesoParametrizadoP(linea, base, contador);
					}
				}
				if(contador== (cantidadLineas-1)){
					base.EjecutarQuery("update [Libranza].[dbo].[DataCobrconv] set Nombre ='"+ NomArchivo + "' where posicion=14");
				}
				base.CerrarconexionBD();
				base = null;
			}

			catch (Exception ex) {
				LabelPorcentaje.setForeground(Color.RED);
				LabelPorcentaje.setText("Hubo un error leyendo el archivo de Parametrizados en la lìnea"+ contador);
				PanelPorcentaje.update(PanelPorcentaje.getGraphics());// para actualizar el panel y poder mostrar el proceso
			}
		} else {// System.out.println("Error subiedo el archivo de Cagmaest");
			LabelPorcentaje.setForeground(Color.RED);
			LabelPorcentaje.setText("Error subiedo el archivo de Parametrizados");
			PanelPorcentaje.update(PanelPorcentaje.getGraphics());// para actualizar el panel y poder mostrar el proceso
		}
	}

// ====================================================================================
	/**
	 * Permite generar un archivo historico de catradia a partir de los insumos ingresados
	 * 
	 * @param direccion Ruta donde se encuentra el archivo
	 * @param PanelPorcentaje Permite actualizar el porcentaje de avance
	 * @param LabelPorcentaje Permite actualizar el porcentaje de avance
	 * @throws SQLException Conexión  a la base de datos
	 * @throws IOException Conexión  a la base de datos
	 */
	public void escribirTxtCatradia(String direccion, JPanel PanelPorcentaje,	JLabel LabelPorcentaje) throws SQLException, IOException {

		int porcentaje;//porcentaje de avance
		int cont = -1;
		int n = 0;//contador de líneas
		boolean Existe;//variable boolena para indicar si el archivo existe o no
		String val;//encabezado del archivo txt
		String Mes="";//mes con el que se va a generarl el archivo
		
		DefaultTableModel result;
		conexionBD base = new conexionBD();
		
		String RT_1 = "SELECT Nombre  FROM [Libranza].[dbo].[DataCobrconv] WHERE posicion=8";//consulta el nombre del archivo
		result = base.ConsultarQuery(RT_1);
		String Fecha=result.getValueAt(0, 0).toString().replace("catradia_", "").substring(0, 2);//trae el mes en numero y lo cambia a fecha 
		switch (Fecha){
			case "01": 	Mes="Enero";				break;
			case "02": 	Mes="Febrero";			break;
			case "03": 	Mes="Marzo";				break;
			case "04": 	Mes="Abril";				break;
			case "05": 	Mes="Mayo";				break;
			case "06": 	Mes="Junio";				break;
			case "07": 	Mes="Julio";				break;
			case "08": 	Mes="Agosto";			break;
			case "09": 	Mes="Septiembre";	break;
			case "10": 	Mes="Octubre";			break;
			case "11": 	Mes="Noviembre";		break;
			case "12": 	Mes="Diciembre";		break;
		}
		File file = new File(direccion + "\\Catradia "+Mes+".txt");//direccion donde se quiere guardar el archivo + nombre del archivo + extension del archivo
		
		Existe = file.exists();
		if (Existe == true) {// borra el archivo catradia siempre y cuando exista
			file.delete();
		}

		FileWriter filewrite = new FileWriter(file.getAbsoluteFile(), true);
		BufferedWriter bufer = new BufferedWriter(filewrite);
		LabelPorcentaje.setText("GENERANDO HISTORICO DE CATRADIA...");//muestra por pantalla que se va a generar el archivo
		PanelPorcentaje.update(PanelPorcentaje.getGraphics());
		String RT1 = "SELECT * FROM CatradiaHistorial ORDER BY id";// consulta en sql la tabla 
		result = base.ConsultarQuery(RT1);
		int cantiidad = result.getRowCount();//cantidad de lineas que contiene la tabla
			if (cantiidad>0){//solo genera el documento de catradia si hay datos en la tabla
			val =	"FechaMov|tr_tran|tr_banco|tr_ofi_oper|tr_ofi_usu|dtr_concepto|dtr_monto_mn|dtr_cuenta|tr_estado \n";//encabezado del archivo plano
			bufer.write(val);//añade al archivo plano el encabezado
			for (int x = 0; x < result.getRowCount(); x++) {//para recorrer cada linea de la tabla
			
				double	redondeo=Double.parseDouble(result.getValueAt(x, 6).toString().trim());
				long Redondeo=Math.round(redondeo);//convierte el numerode notacion cientifica a numero entero largo
				
				//Extructura de como se va agenerar el archivo plano separado por pleca |
							val =		result.getValueAt(x, 0).toString().trim() + 
								"|"	+ result.getValueAt(x, 1).toString().trim()	+
								"|"	+ result.getValueAt(x, 2).toString().trim()	+ 
								"|"	+ result.getValueAt(x, 3).toString().trim()	+
								"|"	+ result.getValueAt(x, 4).toString().trim()	+ 
								"|"	+ result.getValueAt(x, 5).toString().trim()	+ 
								"|"	+ Redondeo+".00"	+
								"|"	+ result.getValueAt(x, 7).toString().trim()	+
								"|"	+ result.getValueAt(x, 8).toString().trim();
							
					n = n + 1;
				porcentaje = (n * 100) / cantiidad;//porcentaje de avance
							
				if (porcentaje >= (cont + 1)) {// permite mostrar el porcentaje una sola vez
					LabelPorcentaje.setText(porcentaje + "% " + " Van " + n	+ " de " + cantiidad);
					PanelPorcentaje.update(PanelPorcentaje.getGraphics());// para actualizar el panel y poder mostrar el proceso
					if(porcentaje>cont){cont=porcentaje;}
					cont = cont + 1;
					if(cont>99){cont=99;}
				}
				bufer.write(val);//añade el contenido al archivo plano
				bufer.newLine();//asigna un salto de linea "\n"
			}
			bufer.close();//cierra y guarda el archivo en la ruta especificada
		}
		else{		//en caso de no tener registro arroja el siguiente ERROR
			LabelPorcentaje.setText("NO SE PUEDE GENERAR EL  HISTORICO DE CATRADIA, NO EXISTE DATA...");
			PanelPorcentaje.update(PanelPorcentaje.getGraphics());}
	}
// ====================================================================================
}// fin de CARGAR TXR

