/**
 * Genera archivos en formato XLS
 */
package GenerarExcel;

import java.awt.Color;
import java.awt.image.IndexColorModel;
import java.io.File;
import java.io.IOException;
import java.sql.SQLException;
import java.util.regex.Pattern;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.table.DefaultTableModel;

import com.jgoodies.forms.layout.ColumnSpec;

import jxl.CellView;
import jxl.Workbook;
import jxl.WorkbookSettings;
import jxl.biff.DisplayFormat;
import jxl.write.Colour;
import jxl.write.Label;
import jxl.write.WritableCellFormat;
import jxl.write.WritableFont;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;
import jxl.write.WriteException;
import jxl.write.NumberFormat;
import jxl.write.Formula;
import CargaDatos.conexionBD;
import GenerarExcel.Encriptar;

public class Generar {

	String[][] entrada;
	private int cantidad_linea=0;
	private long suma1=0;
	private long suma2=0;
	/**
	 * Genera archivos excel por numero de convenio
	 * 
	 * @param direccion ruta donde se guardara el archivo
	 * @param PanelPorcentaje Actualizacion de porcentaje
	 * @param LabelPorcentaje Actualizacion de porcentaje
	 * @param Quincena fecha de la quincena a generar
	 */
	public void GenerarEx(String direccion, JPanel PanelPorcentaje,JLabel LabelPorcentaje, JLabel Quincena){
		String valor;
		String valor2;
		String valor3;
		String valor4;
		int kk=0;
		double numEntero;
		long numeroLong;
		long numeroLong10=0;
		long numeroLong11=0;
		long numeroLong12=0;
		long numeroLong13=0;
		String Correo;
		int cantiidad =0;
		int cont=-1;
		
		int n=0;
		
		String Va[]=Quincena.getText().split("-");
		String Or= Va[0].toString().trim();
		//String numero=Or.replace("Corte ", "").replace("_", "");
		
	try{
		
		conexionBD base = new conexionBD();
		
		String RT1 = "SELECT [CONVENIO] FROM [Libranza].[dbo].[BASE_SALDOS_ACTUALIZADOS_FACTURACION_CORTE] GROUP BY CONVENIO"
					+ " update [Libranza].[dbo].[BASE_SALDOS_ACTUALIZADOS_FACTURACION_CORTE] set novedad='' where novedad is null"
					+ " update [Libranza].[dbo].[BASE_SALDOS_ACTUALIZADOS_FACTURACION_CORTE] set Observaciones='' where Observaciones is null"
					+ " update [Libranza].[dbo].[BASE_SALDOS_ACTUALIZADOS_FACTURACION_CORTE] set Valor_Pago_Empresa='' where Valor_Pago_Empresa is null"
					+ " update [Libranza].[dbo].[Parametrizados] set Contacto=REPLACE(Contacto,char(34),'')";
		
		
					DefaultTableModel result;
					DefaultTableModel result2;
					DefaultTableModel result3;
						result = base.ConsultarQuery(RT1);
						cantiidad = result.getRowCount();
	for (int L=0;L<cantiidad;L++){
		valor = result.getValueAt(L, 0).toString();
		
		n++;
		
		String RT2 ="SELECT [CONVENIO],[NIT],[NOMBRE_EMPRESA],[FECHA_DE_PAGO],[Numero_de_Documento],[Nombre],[No_Obligación],[Nombre_Oficina],"
				+" [Cuotas_Pactadas] ,[Cuotas_a_Pagar],[Vr_Cuota_Mensual],[Vr_en_Mora],[Vr_Total_a_Pagar],[Vr_Intereses_en_Mora],"
  				+" [Valor_Pago_Empresa],[novedad],[Observaciones]"
  				+" FROM [Libranza].[dbo].[BASE_SALDOS_ACTUALIZADOS_FACTURACION_CORTE] WHERE  CONVENIO='"+ valor +"'  ORDER BY CAST(CONVENIO AS INT)";
		result2 = base.ConsultarQuery(RT2);
		int cantiidad2 = result2.getRowCount();
		valor2 = result2.getValueAt(0, 0).toString();
		
		String RT3="SELECT Documento,Regional,Contacto  FROM [Libranza].[dbo].[Parametrizados] where Convenio='"+ valor+"'";
		
		result3 = base.ConsultarQuery(RT3);
		int conteo= result3.getRowCount();
		if (conteo>0){
		valor3 = result3.getValueAt(0, 1).toString();//regional
		valor4 = result3.getValueAt(0, 0).toString();//documento
		Correo = result3.getValueAt(0, 2).toString();//Email
	//========================================================================================================	
		
			WorkbookSettings conf=new WorkbookSettings();//INSTANCIA DE CONFIGURACION
			//WritableCellFormat headerFormat = new WritableCellFormat();
			
			conf.setEncoding("ISO-8859-1");
			 WritableWorkbook LibroEX;
				if (Correo.equals("")){
					LibroEX= Workbook.createWorkbook(new File(direccion+"\\OTROS\\"+valor+".xls"),conf);//ubicacion de los archivos,nombree de la carpeta,nombre del archivo	
				}
				else{
					LibroEX= Workbook.createWorkbook(new File(direccion+"\\"+valor3+"\\"+valor+".xls"),conf);//ubicacion de los archivos,nombree de la carpeta,nombre del archivo
				}
					
			WritableSheet Sheet=LibroEX.createSheet("Tabla",0);//nombre de la hoja del excel y la posicion
			WritableFont h=new WritableFont(WritableFont.TAHOMA,9,WritableFont.NO_BOLD);//tipo de letra y tamaño
			WritableFont h2=new WritableFont(WritableFont.TAHOMA,9,WritableFont.BOLD);//tipo de letra y tamaño
			WritableFont h3=new WritableFont(WritableFont.TAHOMA,9,WritableFont.NO_BOLD);//tipo de letra y tamaño
			
			WritableCellFormat hFormat =new WritableCellFormat(h);//para mandar el formato
			WritableCellFormat hFormat2 =new WritableCellFormat(h2);//para mandar el formato
			WritableCellFormat hFormat3 =new WritableCellFormat(h3, new NumberFormat("$_#,##0.00"));
			
			String[] Encabezado = new String[]{//estructura del archivo excel "Títulos"
	                "Convenio",
	                "Nit",
	                "Nombre Empresa",
	                "Fecha de Pago",
	                "Número de Documento",
	                "Nombre",
	                "No Obligación",
	                "Nombre Oficina",
	                "Cuotas Pactadas",
	                "Cuotas a Pagar",
	                "Vr Cuota Mensual",
	                "Vr en Mora",
	                "Vr Total a Pagar",
	                "Vr Intereses en Mora",
	                "Vr Pago Empresa",
	                "Novedad",
	                "Observaciones"
	      	};
			WritableCellFormat headerFormat = new WritableCellFormat(hFormat2);
			headerFormat.setBackground(Colour.GREY_25_PERCENT); //color de fondo de las celdas Titulo
			
				for(int j=0;j<Encabezado.length;j++){//recoore los titulos
					Sheet.addCell(new jxl.write.Label(j,0,Encabezado[j],headerFormat));
				}	
			
			for(int i=0;i<cantiidad2;i++){//filas
				for(int j=0;j<Encabezado.length;j++){//columnas
					valor2=result2.getValueAt(i, j).toString();
					//valor2=valor2.replace(".", ",");
					valor2.replace("\\¥", "Ñ").trim();
					
						if (j==0 || j==1 || j==4 || j==8 ||j==9 ||j==10 || j==11 || j==12 || j==13 || j==14){
							numEntero = Double.parseDouble(valor2);
							numeroLong=Math.round(numEntero);
							
							switch (j) {
							case 10:
								numeroLong10=numeroLong10+numeroLong;
								break;
							case 11:
								numeroLong11=numeroLong11+numeroLong;
								break;
							case 12:
								numeroLong12=numeroLong12+numeroLong;
								break;
							case 13:
								numeroLong13=numeroLong13+numeroLong;
								break;
								
							default:
								break;
							}
							
							if (j==14 && numeroLong==0 ){// en caso de encontrar un valor de pago empresa igual acero
								Sheet.addCell(new jxl.write.Label(j,(i+1),"",hFormat));
							}
							else if(j==10 || j==11 || j==12 || j==13){
								Sheet.addCell(new jxl.write.Number(j,(i+1),numeroLong,hFormat3));
							}
							else{
							Sheet.addCell(new jxl.write.Number(j,(i+1),numeroLong,hFormat));
							}
						}
						else{
							Sheet.addCell(new jxl.write.Label(j,(i+1),valor2,hFormat));
							
						}
				}			
			}
			
			Sheet.addCell(new jxl.write.Number(10,(cantiidad2+2),numeroLong10,hFormat3));//total Vr cuota mensual
			Sheet.addCell(new jxl.write.Number(11,(cantiidad2+2),numeroLong11,hFormat3));//total Vr en mora
			Sheet.addCell(new jxl.write.Number(12,(cantiidad2+2),numeroLong12,hFormat3));//total Vr total a pagar
			Sheet.addCell(new jxl.write.Number(13,(cantiidad2+2),numeroLong13,hFormat3));//total Vr intereses en mora
			Sheet.addCell(new jxl.write.Number(0,(cantiidad2+2),cantiidad2,hFormat));//cantidad de registros
			
			Sheet.setColumnView(0, 10);//A ajustar tamaño de celda
			Sheet.setColumnView(1, 15);//B ajustar tamaño de celda 
			Sheet.setColumnView(2, 30);//C ajustar tamaño de celda
			Sheet.setColumnView(3, 15);//D ajustar tamaño de celda
			Sheet.setColumnView(4, 21);//E ajustar tamaño de celda
			Sheet.setColumnView(5, 40);//F ajustar tamaño de celda
			Sheet.setColumnView(6, 18);//G ajustar tamaño de celda
			Sheet.setColumnView(7, 15);//H ajustar tamaño de celda
			Sheet.setColumnView(8, 15);//I ajustar tamaño de celda
			Sheet.setColumnView(9, 15);//J ajustar tamaño de celda
			Sheet.setColumnView(10, 20);//K ajustar tamaño de celda
			Sheet.setColumnView(11, 20);//L ajustar tamaño de celda
			Sheet.setColumnView(12, 20);//M ajustar tamaño de celda
			Sheet.setColumnView(13, 20);//N ajustar tamaño de celda
			Sheet.setColumnView(14, 20);//O ajustar tamaño de celda
			Sheet.setColumnView(15, 15);//P ajustar tamaño de celda
			Sheet.setColumnView(16, 15);//Q ajustar tamaño de celda
			
			int Porcentaje = ((n*100)/cantiidad);

			LibroEX.write();
			LibroEX.close();
			numeroLong10=0;
			numeroLong11=0;
			numeroLong12=0;
			numeroLong13=0;
			
			//Encriptar funcion = new Encriptar(); // prueba si se han subido todos los archivos
			EcriptadoExcel funcion =new EcriptadoExcel();
			
				if (Correo.equals("")){
					//funcion.EcriptarZip(direccion,valor,"OTROS",valor4);
					funcion.ClaveExcel(direccion,valor,"OTROS",valor4);
					LabelPorcentaje.setText(Porcentaje + "% " + " Van "	+ n + " de " + cantiidad +" Generando Archivo " + valor + " "+ "OTRO");
					PanelPorcentaje.update(PanelPorcentaje.getGraphics());
				}
				else{
					//funcion.EcriptarZip(direccion,valor,valor3,valor4);
					funcion.ClaveExcel(direccion,valor,valor3,valor4);
					if(Porcentaje>=(cont+1)){
						LabelPorcentaje.setText(Porcentaje + "% " + " Van "	+ n + " de " + cantiidad +" Generando Archivo " + valor +" "+ valor3);
						PanelPorcentaje.update(PanelPorcentaje.getGraphics());
						cont++;
						if(cont>99){cont=99;}
					}
				}
				}
			else{kk++;
			System.out.println("El convenio "+valor+" no tiene registros ");}
			}
		}
		catch(IOException ex){
			System.out.println(ex);
		}
			catch(WriteException ex){
				System.out.println(ex);
			}
				 catch (SQLException e) {
					 System.out.println(e);
				}
	}
//}

//==========================================================================================================
//**********************************************************************************************************
	/**
	 * genera archivos excel
	 * 
	 * @param direccion ruta donde se guardara el archivo
	 * @param Panel Actualizacion de porcentaje
	 * @param Label Actualizacion de porcentaje
	 */
	public void BaseConsolidado(String direccion, JPanel Panel,JLabel Label){
		

		String valor;
		String valor2;
		String valor3;
		String valor4;
		int cont=-1;
		double numEntero;
		long numeroLong;
		long numeroLong10=0;
		long numeroLong11=0;
		long numeroLong12=0;
		long numeroLong13=0;
		String Correo;
		int cantiidad =0;
		
		int n=0;

		try{
		
		conexionBD base = new conexionBD();
		
		Label.setText("Generando BASE CONSOLIDADA CLIENTES LIBRANZA");
		Panel.update(Panel.getGraphics());
		
			WorkbookSettings conf=new WorkbookSettings();
			conf.setEncoding("ISO-8859-1");
			 WritableWorkbook LibroEX;
					LibroEX= Workbook.createWorkbook(new File(direccion+"\\BASE_CONSOLIDADA_CLIENTES_LIBRANZA.xls"),conf);//ubicacion de los archivos,nombree de la carpeta,nombre del archivo	
					
			WritableSheet Sheet=LibroEX.createSheet("Tabla",0);//nombre de la hoja del excel y la posicion
			WritableFont h=new WritableFont(WritableFont.TAHOMA,9,WritableFont.NO_BOLD);//tipo de letra y tamaño
			WritableFont h2=new WritableFont(WritableFont.TAHOMA,9,WritableFont.BOLD);//tipo de letra y tamaño
			
			WritableCellFormat hFormat =new WritableCellFormat(h);//para mandar el formato
			WritableCellFormat hFormat2 =new WritableCellFormat(h2);//para mandar el formato
			
			String[] Encabezado = new String[]{
					"CONVENIO",
					"NIT",
					"NOMBRE_EMPRESA",
					"FECHA_DE_PAGO",
					"correo_electronico_Empresa",
					"ITEM",
					"Tipo_de_Documento",
					"Numero_de_Documento",
					"Nombre",
					"No_Obligación",
					"Código_Oficina",
					"Nombre_Oficina",
					"Vr_Cuota_Mensual",
					"Vr_en_Mora",
					"Vr_Total_a_Pagar",
					"Vr_Intereses_en_Mora",
					"Cuotas_Pactadas",
					"Cuotas_a_Pagar",
					"Valor_Pago_Empresa",
					"novedad",
					"Observaciones"
	      	};
			
			for(int j=0;j<Encabezado.length;j++){
				 
				Sheet.addCell(new jxl.write.Label(j,0,Encabezado[j],hFormat2));
			}	
			
			String RT1 = "SELECT * FROM [Libranza].[dbo].BASE_CONSOLIDADA_CLIENTES_LIBRANZA order by cast(Orden as int)";
				
		
		DefaultTableModel result;
		result = base.ConsultarQuery(RT1);
		cantiidad = result.getRowCount();
		for (int L=0;L<cantiidad;L++){
			
			n++;
			//for(int i=0;i<cantiidad;i++){//filas
				for(int j=0;j<Encabezado.length;j++){//columnas
					valor2=result.getValueAt(L, j).toString();
					valor2.trim();
					
						if (j==0 || j==1 || j==7 ||j==9 ||j==10 || j==12 || j==13 || j==14 || j==15){
							numEntero = Double.parseDouble(valor2);
							numeroLong=Math.round(numEntero);
							if (j==9){
								String valor9 =Long.toString(numeroLong);
								Sheet.addCell(new jxl.write.Label(j,(L+1),valor9,hFormat));
							}else{Sheet.addCell(new jxl.write.Number(j,(L+1),numeroLong,hFormat));}
						}
						else{
							Sheet.addCell(new jxl.write.Label(j,(L+1),valor2.replace(".0", ""),hFormat));
						}
				//}	
				}
			
			int Porcentaje = ((n*100)/cantiidad);
				if (Porcentaje >= (cont + 1)) {// permite mostrar el porcentaje una sola vez
					Label.setText("Base Consolidado "+Porcentaje + "% " + " Van "	+ n + " de " + cantiidad );
					Panel.update(Panel.getGraphics());
					cont++;
					if(cont>=100){cont=99;}
				}
				if (n==cantiidad){
					LibroEX.write();
					LibroEX.close();
				}
			
			}

		}
		catch(IOException ex){
			System.out.println(ex);
		}
			catch(WriteException ex){
				System.out.println(ex);
			}
				 catch (SQLException e) {
					 System.out.println(e);
				}
	
	}
//==========================================================================================================
//**********************************************************************************************************
	/**
	 * genera archivos excel
	 * 
	 * @param direccion ruta donde se guardara el archivo
	 * @param Panel Actualizacion de porcentaje
	 * @param Label Actualizacion de porcentaje
	 */
	public void FacturacionCorte(String direccion, JPanel Panel,JLabel Label){
		

		String valor;
		String valor2;
		String valor3;
		String valor4;
		double numEntero;
		long numeroLong;
		long numeroLong10=0;
		long numeroLong11=0;
		long numeroLong12=0;
		long numeroLong13=0;
		String Correo;
		int cantiidad =0;
		int cont=-1;
		
		int n=0;

		try{
		
		conexionBD base = new conexionBD();
		
		Label.setText("Generando BASE SALDOS ACTUALIZADOS FACTURACION CORTE");
		Panel.update(Panel.getGraphics());
		
			WorkbookSettings conf=new WorkbookSettings();
			conf.setEncoding("ISO-8859-1");
			 WritableWorkbook LibroEX;
					LibroEX= Workbook.createWorkbook(new File(direccion+"\\BASE_SALDOS_ACTUALIZADOS_FACTURACION_CORTE.xls"),conf);//ubicacion de los archivos,nombree de la carpeta,nombre del archivo	
					
			WritableSheet Sheet=LibroEX.createSheet("Tabla",0);//nombre de la hoja del excel y la posicion
			WritableFont h=new WritableFont(WritableFont.TAHOMA,9,WritableFont.NO_BOLD);//tipo de letra y tamaño
			WritableFont h2=new WritableFont(WritableFont.TAHOMA,9,WritableFont.BOLD);//tipo de letra y tamaño
			
			WritableCellFormat hFormat =new WritableCellFormat(h);//para mandar el formato
			WritableCellFormat hFormat2 =new WritableCellFormat(h2);//para mandar el formato
			
			String[] Encabezado = new String[]{
					  "CONVENIO",
				      "NIT",
				      "NOMBRE_EMPRESA",
				      "FECHA_DE_PAGO",
				      "Correo_electronico_Empresa",
				      "ITEM",
				      "Tipo_de_Documento",
				      "Numero_de_Documento",
				      "Nombre",
				      "Obligación",
				      "LLAVE",
				      "No_Obligación",
				      "Código_Oficina",
				      "Nombre_Oficina",
				      "Vr_Cuota_Mensual",
				      "Vr_en_Mora",
				      "Vr_Total_a_Pagar",
				      "Vr_Intereses_en_Mora",
				      "Cuotas_Pactadas",
				      "Cuotas_a_Pagar",
				      "Valor_Pago_Empresa",
				      "novedad",
				      "Observaciones",
				      "mo_dias_vencido_op",
				      "mo_fecha_prox_vencimiento",
				      "mo_saldo_cuota_prox_venc",
				      "mo_saldo_capital_vigente",
				      "mo_saldo_capital_vencido",
				      "mo_saldo_interes_vigente",
				      "mo_saldo_interes_vencido",
				      "mo_saldo_interes_contingente",
				      "mo_saldo_mora_contingente",
				      "mo_saldo_seguro_vida_vigente",
				      "mo_saldo_seguro_vida_vencido",
				      "mo_saldo_otros_vigente",
				      "mo_saldo_otros_vencidos",
				      "mo_estado_obligacion",
				      "mo_fecha_ult_pago",
				      "mo_valor_ult_pago",
				      "mo_numero_cuotas_vencidas"
	      	};
			
			for(int j=0;j<Encabezado.length;j++){
				 
				Sheet.addCell(new jxl.write.Label(j,0,Encabezado[j],hFormat2));
			}	
			DefaultTableModel result;
			
			String BorrarNull= " update .BASE_SALDOS_ACTUALIZADOS_FACTURACION_CORTE set mo_dias_vencido_op='0',mo_fecha_prox_vencimiento='', "
								+" mo_saldo_cuota_prox_venc='0',mo_saldo_capital_vigente='0',mo_saldo_capital_vencido='0',mo_saldo_interes_vigente='0', "
								+" mo_saldo_interes_vencido='0',mo_saldo_interes_contingente='0',mo_saldo_mora_contingente='0',mo_saldo_seguro_vida_vencido='0', "
								+" mo_saldo_seguro_vida_vigente='0',mo_saldo_otros_vigente='0',mo_saldo_otros_vencidos='0',mo_estado_obligacion='', "
								+" mo_fecha_ult_pago='',mo_valor_ult_pago='0',mo_numero_cuotas_vencidas='0' "
								+" where mo_dias_vencido_op is NULL";
			
			base.EjecutarQuery(BorrarNull);
			String RT1 =" SELECT * FROM [Libranza].[dbo].[BASE_SALDOS_ACTUALIZADOS_FACTURACION_CORTE] order by cast(Orden as int)";
		
		result = base.ConsultarQuery(RT1);
		cantiidad = result.getRowCount();
		for (int L=0;L<cantiidad;L++){
			
			n++;
			//for(int i=0;i<cantiidad;i++){//filas
				for(int j=0;j<Encabezado.length;j++){//columnas
					valor2=result.getValueAt(L, j).toString().trim();
					
						if (j==0 || j==1 || j==7 ||j==11 ||j==15 || j==16 || j==17 || j==25 || j==26 || j==27 || j==28 || j==29 || j==30 || j==31 || j==32 || j==33 || j==34 || j==35 || j==38 || j==39){
							numEntero = Double.parseDouble(valor2);
							numeroLong=Math.round(numEntero);
							if (j==11){
								String valor9 =Long.toString(numeroLong);
								Sheet.addCell(new jxl.write.Label(j,(L+1),valor9,hFormat));
							}else{Sheet.addCell(new jxl.write.Number(j,(L+1),numeroLong,hFormat));}
						}
						else{
							Sheet.addCell(new jxl.write.Label(j,(L+1),valor2.replace(".0", ""),hFormat));
						}
				//}	
				}
			
			int Porcentaje = ((n*100)/cantiidad);
			if (Porcentaje >= (cont + 1)) {// permite mostrar el porcentaje una sola vez
				Label.setText("Base saldos Actualizados "+Porcentaje + "% " + " Van "	+ n + " de " + cantiidad );
				Panel.update(Panel.getGraphics());
				cont++;
				if(cont>=100){cont=99;}
			}
				if (n==cantiidad){
					LibroEX.write();
					LibroEX.close();
				}
			
			}

		}
		catch(IOException ex){
			System.out.println(ex);
		}
			catch(WriteException ex){
				System.out.println(ex);
			}
				 catch (SQLException e) {
					 System.out.println(e);
				}
	
	}
//==========================================================================================================
//**********************************************************************************************************
	/**
	 * genera archivos excel no coincidentes
	 * 
	 * @param direccion ruta donde se guardara el archivo
	 * @param PanelPorcentaje Actualizacion de porcentaje
	 * @param LabelPorcentaje Actualizacion de porcentaje
	 */
	public void ExNoPagos(String direccion, JPanel PanelPorcentaje,JLabel LabelPorcentaje){

		String valor;
		String valor2;
		String valor3;
		String valor4;
		double numEntero;
		long numeroLong;
		int cantiidad =0;
		int cantiidad2 =0;
		int n=0;
		
	try{
		
		conexionBD base = new conexionBD();
		
		String RT1 = "select DISTINCT Regional,Cod from NO_COINCIDENTE2 group by Regional,Cod";
					DefaultTableModel result;
						result = base.ConsultarQuery(RT1);
						cantiidad = result.getRowCount();

	//========================================================================================================
		for(int A=0;A<cantiidad;A++){
			
			String Regional=result.getValueAt(A, 0).toString().trim();
			if (Regional.contains("DIRECCION GENERAL")==true){Regional="BOGOTA";}
			String Convenio=result.getValueAt(A, 1).toString().trim();
			String Convenio2=result.getValueAt(A, 1).toString().trim();
			n++;
				/*if (n==65){
					System.out.println(n);
				}*/
			String RT2 = "UPDATE Libranza.dbo.NO_COINCIDENTE2 set DirenciaCvsB=0 where DirenciaCvsB is null "
								+" select DISTINCT Cod,Nit,Convenio,Fecha,CC,nombre,obligacion,nomOficina,CPactadas,CPagar,CMensual,VrMora,VrTPagar,VrIMora,"
								+" VrPEmpresa,Novedad,Observaciones,CMensual,DirenciaCvsB from Libranza.dbo.NO_COINCIDENTE2 where cod="+Convenio;
			DefaultTableModel result2;
				result2= base.ConsultarQuery(RT2);
				cantiidad2 = result2.getRowCount();
				int cont=-1;
				
			if(cantiidad2>0){
			WorkbookSettings conf=new WorkbookSettings();
			
			conf.setEncoding("ISO-8859-1");
			 WritableWorkbook LibroEX;
					LibroEX= Workbook.createWorkbook(new File(direccion+"\\"+Regional+"\\"+Convenio2+".xls"),conf);//ubicacion del archivo nombre del archivo	
					
			WritableSheet Sheet=LibroEX.createSheet("Tabla",0);//nombre de la hoja del excel y la posicion
			WritableFont h=new WritableFont(WritableFont.TAHOMA,9,WritableFont.NO_BOLD);//tipo de letra y tamaño
			WritableFont h2=new WritableFont(WritableFont.TAHOMA,9,WritableFont.BOLD);//tipo de letra y tamaño
			WritableFont h3=new WritableFont(WritableFont.TAHOMA,9,WritableFont.NO_BOLD);//tipo de letra y tamaño
			
			WritableCellFormat hFormat =new WritableCellFormat(h);//para mandar el formato
			WritableCellFormat hFormat2 =new WritableCellFormat(h2);//para mandar el formato
			WritableCellFormat hFormat3 =new WritableCellFormat(h3, new NumberFormat("$_#,##0"));
			WritableCellFormat hFormat4 =new WritableCellFormat(h3, new NumberFormat("0"));
			
			String[] Encabezado = new String[]{
					"CONVENIO ",
					"NIT ",
					"NOMBRE EMPRESA ",
					"FECHA DE PAGO ",
					"Número de documento ",
					"Nombre",
					"Obligación ",
					"Nombre Oficina ",
					"Cuotas pactadas ",
					"Cuotas a pagar ",
					"Vr cuota mensual ",
					"Vr en mora ",
					"Vr total a pagar ",
					"Vr intereses en mora ",
					"Valor Pago Empresa ",
					"Novedad ",
					"Observaciones ",
					"FACTURACION",
					"CATRADIA"
					/*"VALOR DIFERENCIA PAGO BANCOLOMBIA Y DEP GTIA VS FACTURACION",
					"VALOR DIFERENCIA PAGO BANCOLOMBIA Y DEP GTIA VS CATRADIA"*/
	      	};
			WritableCellFormat ColorFormato = new WritableCellFormat(hFormat2);
			WritableCellFormat ColorFormato2 = new WritableCellFormat(hFormat2);
			ColorFormato.setBackground(Colour.GREY_40_PERCENT); //color de fondo de las celdas
			ColorFormato2.setBackground(Colour.GREY_25_PERCENT); //color de fondo de las celdas
			
				for(int j=0;j<Encabezado.length;j++){
					Sheet.addCell(new jxl.write.Label(j,0,Encabezado[j],ColorFormato));
				}	
				cantidad_linea=0;
				suma1=0;
				suma2=0;
			for (int L=0;L<cantiidad2;L++){
				numeroLong=0;
				cantidad_linea++;
				for(int j=0;j<Encabezado.length;j++){//columnas
					//System.out.println(j);
					valor2=result2.getValueAt(L, j).toString();
					valor2.replace("¥", "Ñ").trim();
					//System.out.println("TABLA : "+valor2);
					
						if ( j==1 || j==4 || j==6 || j==10 || j==11 || j==12 || j==13 || j==17 || j==18  || j==8 || j==9){
							numEntero = Double.parseDouble(valor2);
							numeroLong=Math.round(numEntero);
							if(  j==10 || j==11 ||j==12 || j==13 || j==17 || j==18){
							Sheet.addCell(new jxl.write.Number(j,(L+1),numeroLong,hFormat3));
								if( j==17 ){suma1=	numeroLong+suma1;} //suma de Facturacion vs bancolomba
									else if( j==18){suma2=	numeroLong+suma2;}//suma de catradia vs bancolombia
							}
							else{
								Sheet.addCell(new jxl.write.Number(j,(L+1),numeroLong,hFormat4));
							}
						}
						else{
							Sheet.addCell(new jxl.write.Label(j,(L+1),valor2,hFormat));
							
						}
				}			
			
			int Porcentaje = ((n*100)/cantiidad);
			if( Porcentaje>=(cont+1)){
					LabelPorcentaje.setText("Generando Archivo "+Porcentaje + "% " + " Van "	+ n + " de " + cantiidad );
					PanelPorcentaje.update(PanelPorcentaje.getGraphics());
					if(Porcentaje>cont){cont=Porcentaje;}
					cont++;
					if(cont>=99){cont=99;}
			}
			
			}// fin de L
			
			String subTitulo="VALOR DIFERENCIA PAGO BANCOLOMBIA Y DEP GTIA VS FACTURACION";
			String subTitulo2="VALOR DIFERENCIA PAGO BANCOLOMBIA Y DEP GTIA VS CATRADIA";
			String subTitulo3="VALOR BANCOLOMBIA";
			
			Sheet.addCell(new jxl.write.Label(16,(cantidad_linea+3),subTitulo3,ColorFormato2));
			Sheet.addCell(new jxl.write.Label(17,(cantidad_linea+3),subTitulo,ColorFormato2));
			Sheet.addCell(new jxl.write.Label(18,(cantidad_linea+3),subTitulo2,ColorFormato2));
			
			RT2="select SUM(CAST(Valor AS FLOAT)) AS VALOR from BANCOLOMBIA  where COD='"+Convenio+"'";
					result2= base.ConsultarQuery(RT2);
					String VALOR=result2.getValueAt(0, 0).toString();
					numEntero = Double.parseDouble(VALOR);
					numeroLong=Math.round(numEntero);
					
			Sheet.addCell(new jxl.write.Number(16,(cantidad_linea+4),numeroLong,hFormat3));
			Sheet.addCell(new jxl.write.Number(17,(cantidad_linea+4),(numeroLong-suma1),hFormat3));
			Sheet.addCell(new jxl.write.Number(18,(cantidad_linea+4),(numeroLong-suma2),hFormat3));
				
			Sheet.setColumnView(0, 10);//A ajustar tamaño de celda
			Sheet.setColumnView(1, 15);//B ajustar tamaño de celda 
			Sheet.setColumnView(2, 60);//C ajustar tamaño de celda
			Sheet.setColumnView(3, 15);//D ajustar tamaño de celda
			Sheet.setColumnView(4, 22);//E ajustar tamaño de celda
			Sheet.setColumnView(5, 60);//F ajustar tamaño de celda
			Sheet.setColumnView(6, 20);//G ajustar tamaño de celda
			Sheet.setColumnView(7, 35);//H ajustar tamaño de celda
			Sheet.setColumnView(8, 15);//I ajustar tamaño de celda
			Sheet.setColumnView(9, 20);//J ajustar tamaño de celda
			Sheet.setColumnView(10, 20);//K ajustar tamaño de celda
			Sheet.setColumnView(11, 20);//L ajustar tamaño de celda
			Sheet.setColumnView(12, 20);//M ajustar tamaño de celda
			Sheet.setColumnView(13, 20);//N ajustar tamaño de celda
			Sheet.setColumnView(14, 20);//O ajustar tamaño de celda
			Sheet.setColumnView(15, 20);//P ajustar tamaño de celda
			Sheet.setColumnView(16, 20);//Q ajustar tamaño de celda
			Sheet.setColumnView(17, 64);//R ajustar tamaño de celda
			Sheet.setColumnView(18, 61);//S ajustar tamaño de celda
			
			LibroEX.write();
					try {
						Thread.sleep(10);//pausa por 10 milisegundos ==> 4000 4 segundos
					} catch (InterruptedException e) {
						e.printStackTrace(); 
					}
			LibroEX.close();
		}
		}}
		catch(IOException ex){
			System.out.println(ex);
		}
			catch(WriteException ex){
				System.out.println(ex);
			}
				 catch (SQLException e) {
					 System.out.println(e);
				}
	
	}
//==========================================================================================================
//**********************************************************************************************************
	/**
	 * genera archivos excel Coincidentes
	 * 
	 * @param direccion ruta donde se guardara el archivo
	 * @param PanelPorcentaje Actualizacion de porcentaje
	 * @param LabelPorcentaje Actualizacion de porcentaje
	 */
	public void ExPagos(String direccion, JPanel PanelPorcentaje,JLabel LabelPorcentaje){
		String valor;
		String valor2;
		String valor3;
		String valor4;
		double numEntero;
		long numeroLong;
		int cantiidad =0;
		int n=0;
		int cont=-1;
		
	try{
		
		conexionBD base = new conexionBD();
		
		String RT1 = "SELECT * FROM [Libranza].[dbo].COINCIDENTE";
					DefaultTableModel result;
						result = base.ConsultarQuery(RT1);
						cantiidad = result.getRowCount();
		
	//========================================================================================================
		
			WorkbookSettings conf=new WorkbookSettings();
			
			conf.setEncoding("ISO-8859-1");
			 WritableWorkbook LibroEX;
					LibroEX= Workbook.createWorkbook(new File(direccion+ "\\COINCIDENTE.xls"),conf);//ubicacion del archivo nombre del archivo	
					
			WritableSheet Sheet=LibroEX.createSheet("Tabla",0);//nombre de la hoja del excel y la posicion
			WritableFont h=new WritableFont(WritableFont.TAHOMA,9,WritableFont.NO_BOLD);//tipo de letra y tamaño
			WritableFont h2=new WritableFont(WritableFont.TAHOMA,9,WritableFont.BOLD);//tipo de letra y tamaño
			WritableFont h3=new WritableFont(WritableFont.TAHOMA,9,WritableFont.NO_BOLD);//tipo de letra y tamaño
			
			WritableCellFormat hFormat =new WritableCellFormat(h);//para mandar el formato
			WritableCellFormat hFormat2 =new WritableCellFormat(h2);//para mandar el formato
			WritableCellFormat hFormat3 =new WritableCellFormat(h3, new NumberFormat("$_#,##0.00"));//formato tipo moneda
			WritableCellFormat hFormat4 =new WritableCellFormat(h3, new NumberFormat("###0"));//formato numerico
			
			String[] Encabezado = new String[]{
					"REGIONAL",
					"NUMERO CONVENIO",
					"FORMA DE PAGO (Nit Base Bancolombia - Consecutivo ",
					"FECHA DE PAGO",
					"IDENTIFICACION CLIENTE",
					"NOMBRE CLIENTE",
					"OBLIGACION ACTIVA",
					"VALOR PAGO EMPRESA",
					"OBSERVACION",
	      	};
			WritableCellFormat ColorFormato = new WritableCellFormat(hFormat2);
			ColorFormato.setBackground(Colour.GREY_25_PERCENT); //color de fondo de las celdas
			
				for(int j=0;j<Encabezado.length;j++){
					Sheet.addCell(new jxl.write.Label(j,0,Encabezado[j],ColorFormato));
					
				}	
			
			for (int L=0;L<cantiidad;L++){
				numeroLong=0;
					n++;
				
				for(int j=0;j<Encabezado.length;j++){//columnas
					valor2=result.getValueAt(L, j).toString();
					//valor2=valor2.replace(".", ",");
					valor2.replace("¥", "Ñ").trim();
					
						if (j==2 || j==4 || j==6 || j==7 ){
							numEntero = Double.parseDouble(valor2);
							numeroLong=Math.round(numEntero);
							if(j==7){
								Sheet.addCell(new jxl.write.Number(j,(L+1),numeroLong,hFormat3));
							}
							else{
								Sheet.addCell(new jxl.write.Number(j,(L+1),numeroLong,hFormat4));
							}
						}
						else{
							Sheet.addCell(new jxl.write.Label(j,(L+1),valor2,hFormat));
							
						}
				}			
			
			int Porcentaje = ((n*100)/cantiidad);
				if(Porcentaje>=(cont+1)){
						LabelPorcentaje.setText("Generando archivo coincidente "+Porcentaje + "% " + " Van "	+ n + " de " + cantiidad  );
						PanelPorcentaje.update(PanelPorcentaje.getGraphics());
						if(Porcentaje>cont){cont=Porcentaje;}
						cont++;
						if(cont>99){cont=99;}
				}
					
			
			}// fin de L
			
			Sheet.setColumnView(0, 15);//A ajustar tamaño de celda
			Sheet.setColumnView(1, 18);//B ajustar tamaño de celda 
			Sheet.setColumnView(2, 49);//C ajustar tamaño de celda
			Sheet.setColumnView(3, 15);//D ajustar tamaño de celda
			Sheet.setColumnView(4, 23);//E ajustar tamaño de celda
			Sheet.setColumnView(5, 55);//F ajustar tamaño de celda
			Sheet.setColumnView(6, 20);//G ajustar tamaño de celda
			Sheet.setColumnView(7, 21);//G ajustar tamaño de celda
			Sheet.setColumnView(8, 15);//G ajustar tamaño de celda

			
			LibroEX.write();
			LibroEX.close();
			
		}
		catch(IOException ex){
			System.out.println(ex);
		}
			catch(WriteException ex){
				System.out.println(ex);
			}
				 catch (SQLException e) {
					 System.out.println(e);
				}
	
	}
	
}
