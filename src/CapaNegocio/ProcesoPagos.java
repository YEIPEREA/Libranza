package CapaNegocio;

import java.io.File;
import java.sql.SQLException;
import java.util.Iterator;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.table.DefaultTableModel;

import org.apache.poi.util.SystemOutLogger;

import CargaDatos.conexionBD;
import GenerarExcel.Generar;

public class ProcesoPagos {
	
private	String RT;
private	String RT0;
private	String RT01;
private	String RT1;
private	String RT2;
private	String RT3;
private	String RT4;
private	String RT5;
private	String RT6;
//private	String RT7;
private	String RT8;
private	String RT9;
private	String RT10;
private	String RT10_3;
private	String RT11;
private	String RT12;
private	String RT13;

private	String Convenio="";
private	String Convenio1="";
private	String Convenio2="";
private	String Convenio3="";
private	String Convenio4="";
private	String Convenio5="";
private	String Convenio6="";
private	String Carp4;
private	String Carp5;
private	String Grado;
private	String Obligacion="";
private	String validar="";

private	long suma=0;
private	long valor=0;
private	long suma2=0;
private	long suma3=0;
private	long suma4=0;
private	long suma5=0;
private	long suma6=0;
private	long ValorBanco=0;
private	double Diferencia=0;

private	int porcentaje;
private	int cont = -1;
private	int cantidad1;
private	int z=0;

private	DefaultTableModel result0;
private	DefaultTableModel result1;
private	DefaultTableModel result2;
private	DefaultTableModel result3;
private	DefaultTableModel result4;
private	DefaultTableModel result5;
private	DefaultTableModel result6;
private	DefaultTableModel result7;
private	DefaultTableModel result8;
private	DefaultTableModel result9;
private	DefaultTableModel result10;
private	DefaultTableModel result10_3;
private	DefaultTableModel result11;
private	DefaultTableModel result12;

conexionBD base = new conexionBD();
/**
 * realiza la actualizacion a la BD por medio de Querys para generear los archivos de pagos
 * 
 * @param direccion ruta  donde se va a generar el archivo
 * @param PanelPorcentaje actualizacion de porcentaje
 * @param LabelPorcentaje  actualizacion de porcentaje
 * @param validar2 añade filtros por defecto es PAGLIBRANZ
 */
	public void Comparar(String direccion,JPanel PanelPorcentaje,JLabel LabelPorcentaje, String validar2) {
		
		LabelPorcentaje.setText("Conectando con la BD. Por favor espere.");
		PanelPorcentaje.update(PanelPorcentaje.getGraphics());// para actualizar el panel y poder mostrar el proceso
		
		validar=validar2;
		
		RT="truncate table [Libranza].[dbo].[NO_COINCIDENTE] 	truncate table [Libranza].[dbo].[NO_COINCIDENTE2]	truncate table [Libranza].[dbo].[COINCIDENTE] 	truncate table [Libranza].[dbo].[COINCIDENTE2]";
		
		RT0="SELECT COD,Valor FROM BANCOLOMBIA";
		
		RT01=" UPDATE Libranza.dbo.BANCOLOMBIA set COD=TRIM(COD) "
			+" UPDATE Libranza.dbo.BANCOLOMBIA set COD=replicate('0',4-Len(COD))+COD where  Len(COD)<=3 ";
		
		/*RT10="Delete COINCIDENTE2 where Convenio in("//ELIMINA LOS CONVENIOS QUE YA SE ENCUENTRA EN LA TABLA COINCIDENTE
			+" Select BANCOLOMBIA.COD from BANCOLOMBIA inner join COINCIDENTE on BANCOLOMBIA.COD=COINCIDENTE.Convenio )";*/
		
		RT10="SELECT Cod,Bancolombia,Orden FROM [Libranza].[dbo].NO_COINCIDENTE";
				
		//TRAE LOS DATOS QUE QUEDARON SIN COINCIDIR LOS AGRUPA EN UN SOLO GRUPO CON SUMA de bancolombia
		RT10_3="SELECT Cod,SUM(Bancolombia) AS MONTO FROM [Libranza].[dbo].NO_COINCIDENTE GROUP BY Cod HAVING COUNT(*)>1"; 
						
		try {
			result0 = base.ConsultarQuery(RT0);
			base.EjecutarQuery(RT);
			base.EjecutarQuery(RT01);
//elimina los.00 de bancolombia y ademas trae aquellos convenios que se repiten mas de una vez en los no coincidentes
			base.EjecutarQuery("exec ActuaizacionBancolombia");
//actualiza los decimales de catradia
			base.EjecutarQuery("exec ActualizacionCatradia '"+validar+"'");
//Actualiza el catradia vs cagmaest
			base.EjecutarQuery("exec LLenadoCatradia '"+validar+"'");
			int cantiidad = result0.getRowCount();
			int K=0;
				for (int L=0;L<cantiidad;L++){
					Diferencia=0;
					Convenio="";
					suma=0;
					K++;
					//bancolombia vs Facturacion
					Convenio=result0.getValueAt(L, 0).toString();
					ValorBanco=Math.round(Double.parseDouble(result0.getValueAt(L, 1).toString().trim()));
						Comparador();//funcion que compara los resultados de bancolombia con los de facturacion
					porcentaje = (K * 100) / cantiidad;
						if (porcentaje >= (cont + 1)) {// permite mostrar el porcentaje una sola vez
							LabelPorcentaje.setText("Facturaciòn Vs Bancolombia " + porcentaje+ "% " + " Van " + K + " de "+ cantiidad);
							PanelPorcentaje.update(PanelPorcentaje.getGraphics());// para actualizar el panel y poder mostrar el proceso
							cont = cont + 1;
							if (cont>=100){cont=99;}
						}
				}
//====================================================================================
				//base.EjecutarQuery(RT10);
				base.EjecutarQuery("exec ACtuaizacionBancolombia");
				RT9="Select Codigo,VALOR from COINCIDENTE2";//TRAE AQUELLOS CONVENIOS Q SE REPITEN DE NO COINCIDENTE
				result9 = base.ConsultarQuery(RT9);
				z=1;
				int cantiidad2 = result9.getRowCount();
				
				for(int T=0;T<cantiidad2;T++){//compara los convenios repetidos de Bancolombia y los compara con los libros cobrconv
					Convenio=result9.getValueAt(T, 0).toString();
					ValorBanco=Math.round(Double.parseDouble(result9.getValueAt(T, 1).toString().trim()));
					ComparadorRepetido();
				}
				
//====================================================================================

//===============================================================================================				
				LabelPorcentaje.setText(" Iniciando proceso de Catradia Vs Cagmaest. Por favor Espere.");
				PanelPorcentaje.update(PanelPorcentaje.getGraphics());// para actualizar el panel y poder mostrar el proceso
				//base.EjecutarQuery(RT10_2);
				cont=-1;
				result10=base.ConsultarQuery(RT10);//TRAE LA CANTIDAD DE REGISTROS QUE SE VAN A CRUZAR CON CATRADIA
				int cantiidad3 = result10.getRowCount();
					for(int F=0;F<cantiidad3;F++){
						Convenio=result10.getValueAt(F, 0).toString();
						ValorBanco=Math.round(Double.parseDouble(result10.getValueAt(F, 1).toString().trim()));
						CatradiaVsNoCoincidente();
						porcentaje = (F * 100) /( cantiidad3-1);
						if (porcentaje >= (cont + 1)) {// permite mostrar el porcentaje una sola vez
							LabelPorcentaje.setText("Catradia Vs Bancolombia " + porcentaje+ "% " + " Van " + F + " de "+ ( cantiidad3-1));
							PanelPorcentaje.update(PanelPorcentaje.getGraphics());// para actualizar el panel y poder mostrar el proceso
							cont = cont + 1;
							if (cont>=100){cont=99;}
						}
					}
//===============================================================================================				
				//TRAE LOS DATOS QUE QUEDARON SIN COINCIDIR LOS AGRUPA EN UN SOLO GRUPO CON SUMA DE BANCOLOMBIA
					result10_3=base.ConsultarQuery(RT10_3);
					int cantiidad4 = result10_3.getRowCount();
					cont=-1;
					for(int B=0;B<cantiidad4;B++){
						Convenio=result10_3.getValueAt(B, 0).toString();
						ValorBanco=Math.round(Double.parseDouble(result10_3.getValueAt(B, 1).toString().trim()));
						CatradiaVsNoCoincidente();
						porcentaje = (B * 100) /( cantiidad4-1);
						if (porcentaje >= (cont + 1)) {// permite mostrar el porcentaje una sola vez
							LabelPorcentaje.setText("Catradia Vs No Coincidente " + porcentaje+ "% " + " Van " + B + " de "+ ( cantiidad4-1));
							PanelPorcentaje.update(PanelPorcentaje.getGraphics());// para actualizar el panel y poder mostrar el proceso
							cont = cont + 1;
							if (cont>=100){cont=99;}
						}
					}
//==========================================NOCOINCIDENTE=====================================================				
							//trae la suma de los valores que no coincidieron Y los actualiza en la tabla no_coincidente
					result10_3=base.ConsultarQuery("select Cod,Gado,SUM(Bancolombia),Vr_Cuota_Mensual from NO_COINCIDENTE GROUP BY Cod,Gado,Vr_Cuota_Mensual ORDER BY Cod");
							int cantiidad5= result10_3.getRowCount();
							cont=-1;
							for(int J=0;J<cantiidad5;J++){
								Convenio=result10_3.getValueAt(J, 0).toString();
								Grado=result10_3.getValueAt(J, 1).toString();
								ValorBanco=Math.round(Double.parseDouble(result10_3.getValueAt(J, 2).toString().trim()));
								suma=Math.round(Double.parseDouble(result10_3.getValueAt(J, 3).toString().trim()));
								NoComparador2 ();
							}
//===============================================================================================				
							//ELIMINA LOS DATOS REPETIDOS DE NO COINCIDENTE ANTES DE ACTUALIZAR
							base.EjecutarQuery("exec EliminaRepetidos ");
//===============================================================================================				
							//Actualiza la tabla de no coincidente en la diferencia de entre el valor en bancolombia y catradia
							//teniendo en cuenta si es mes actual o los meses anteriores
							result10_3=base.ConsultarQuery("exec ActualizaNoCoincidente 'Catradia1','"+validar+"'");// consulta el query
							int cantiidad6= result10_3.getRowCount();// trae la cantidad de registros de ese query
							int tipo =1;
							recorrer(cantiidad6, tipo, PanelPorcentaje, LabelPorcentaje);
							
							result10_3=base.ConsultarQuery("exec ActualizaNoCoincidente 'Catradia2','"+validar+"'");// consulta el query
							 cantiidad6= result10_3.getRowCount();// trae la cantidad de registros de ese query
							 tipo =2;
							recorrer(cantiidad6, tipo, PanelPorcentaje, LabelPorcentaje);
							
							result10_3=base.ConsultarQuery("exec ActualizaNoCoincidente 'Catradia1','"+validar+"'");// consulta el query
							 cantiidad6= result10_3.getRowCount();// trae la cantidad de registros de ese query
							 tipo =3;
							recorrer(cantiidad6, tipo, PanelPorcentaje, LabelPorcentaje);
							
//==============================================================================================							
							//funcionalidad de comparación entre Vr total a Pagar + Vr intereses en mora Vs Bancolombia
							result10_3=base.ConsultarQuery("SELECT Distinct a.Cod,b.Valor FROM NO_COINCIDENTE2 a inner join BANCOLOMBIA b"
										+" on a.Cod=b.COD and b.Valor=a.Bancolombia order by a.Cod");
							cantiidad6= result10_3.getRowCount();
							for (int iterator = 0; iterator<cantiidad6;iterator++) {
								double SumaVr=0,SumaVrP=0;
								String convenio=result10_3.getValueAt(iterator, 0).toString();
								long BANCO=Math.round(Double.parseDouble(result10_3.getValueAt(iterator, 1).toString().trim()));
								result10=base.ConsultarQuery("exec SumaVlrInteresVSBancolombia 'Cobrconv','"+convenio+"'");
								result11=base.ConsultarQuery("exec SumaVlrInteresVSBancolombia 'P_Cobrconv2','"+convenio+"'");
								int cant1=result10.getRowCount();
								int cant2=result11.getRowCount();
								if(cant1>0) {SumaVr =Math.round(Double.parseDouble(result10.getValueAt(0, 0).toString().trim()));}
								if(cant2>0) {SumaVrP=Math.round(Double.parseDouble(result11.getValueAt(0, 0).toString().trim()));}
								Bancolombi_Vs_VrFacturacion(convenio,BANCO,SumaVr,SumaVrP);
								
										//RT11="Delete NO_COINCIDENTE2 where Convenio in('"+convenio+"')";
										//base.EjecutarQuery(RT11);
								
							}
							result12=base.ConsultarQuery("exec BancolombiaVsVlrInteres");
								int cant1=result12.getRowCount();
								for (int iterator = 0; iterator<cant1;iterator++) {
									
									double SumaVr=0,SumaVrP=0;
									String convenio=result12.getValueAt(iterator, 0).toString();
									long BANCO=Math.round(Double.parseDouble(result12.getValueAt(iterator, 1).toString().trim()));
									SumaVr  =Math.round(Double.parseDouble(result12.getValueAt(iterator, 2).toString().trim()));
									SumaVrP =Math.round(Double.parseDouble(result12.getValueAt(iterator, 3).toString().trim()));
									 
									 Bancolombi_Vs_VrFacturacion(convenio,BANCO,SumaVr,SumaVrP);
							}
							
//========================================crea carpetas=======================================================				
				Carpetas(direccion);
//===============================================================================================				
				Generar Pag =new Generar();
				Pag.ExNoPagos(Carp5, PanelPorcentaje, LabelPorcentaje);
				Pag.ExPagos(Carp4, PanelPorcentaje, LabelPorcentaje);
				//Pag.ExNoPagos(Carp5, PanelPorcentaje, LabelPorcentaje);//REDUNDANTE EVITA FALLOS POR LA GENERACION DE ARCHIVOS DESMASIADO RAPIDO
				//Pag.ExPagos(Carp4, PanelPorcentaje, LabelPorcentaje);;//REDUNDANTE EVITA FALLOS POR LA GENERACION DE ARCHIVOS DESMASIADO RAPIDO
		} 
		catch (SQLException e) {
			e.printStackTrace();
		}
	}
//----------------------------------------------------------------------------------------------------------------------
//**********************************************************************************************************************
	/**
	 * compara los registros de Bancolombia Vs la Facturacion
	 */
	public void Comparador(){
		try {
			//compara los registros de Bancolobia vs Cobrconv
			suma=0;suma2=0;suma3=0;suma4=0;suma5=0;suma6=0;
			Convenio1="";Convenio2="";Convenio3="";Convenio4="";Convenio5="";Convenio6="";
			
			RT1="SELECT CONVENIO,convenio2 FROM Cobrconv WHERE convenio2="+Convenio+" and ITEM=2";
			result1 = base.ConsultarQuery(RT1);
			cantidad1 = result1.getRowCount();
				if (cantidad1>0){
					suma = Math.round(Double.parseDouble(result1.getValueAt(0, 0).toString().trim()));
					Convenio1 = result1.getValueAt(0, 1).toString().trim();
				}
			
			if (ValorBanco==suma){
				base.EjecutarQuery("exec comparador '" + ValorBanco + "','" + Convenio + "','Cobrconv'");
				if(z==1){
					RT11="Delete NO_COINCIDENTE where Convenio in(Select BANCOLOMBIA.COD from BANCOLOMBIA inner join COINCIDENTE on BANCOLOMBIA.COD=COINCIDENTE.Convenio where COINCIDENTE.Convenio='"+Convenio+"')";
					base.EjecutarQuery(RT11);
				}
			}
		else if(ValorBanco!=suma){
			RT2="SELECT tipo_de_documento,convenio2 FROM P_Cobrconv2 WHERE convenio2="+Convenio+" and ITEM=2";
			result2 = base.ConsultarQuery(RT2);
			int cantidad2 = result2.getRowCount();
				if (cantidad2>0){
					suma2= Math.round(Double.parseDouble(result2.getValueAt(0, 0).toString().trim()));
					Convenio2= result2.getValueAt(0, 1).toString().trim();
				}
			if(ValorBanco==suma2){
				base.EjecutarQuery("exec comparador '" + ValorBanco + "','" + Convenio + "','P_Cobrconv2'");
				if(z==1){
					RT11="Delete NO_COINCIDENTE where Convenio in(Select BANCOLOMBIA.COD from BANCOLOMBIA inner join COINCIDENTE on BANCOLOMBIA.COD=COINCIDENTE.Convenio where COINCIDENTE.Convenio='"+Convenio+"')";
					base.EjecutarQuery(RT11);
				}
			}
			
		else if(ValorBanco!=suma && ValorBanco!=suma2){
			RT3="SELECT tipo_de_documento,convenio2 FROM P_Cobrconv3 WHERE convenio2="+Convenio+" and ITEM=2";
			result3 = base.ConsultarQuery(RT3);
			int cantidad3 = result3.getRowCount();
				if (cantidad3>0){
					suma3 = Math.round(Double.parseDouble(result3.getValueAt(0, 0).toString().trim()));
					Convenio3 = result3.getValueAt(0, 1).toString().trim();
				}
			if(ValorBanco==suma3){
				base.EjecutarQuery("exec comparador '" + ValorBanco + "','" + Convenio + "','P_Cobrconv3'");
				if(z==1){
					RT11="Delete NO_COINCIDENTE where Convenio in(Select BANCOLOMBIA.COD from BANCOLOMBIA inner join COINCIDENTE on BANCOLOMBIA.COD=COINCIDENTE.Convenio where COINCIDENTE.Convenio='"+Convenio+"')";
					base.EjecutarQuery(RT11);
				}
			}
			
		else if(ValorBanco!=suma && ValorBanco!=suma2 && ValorBanco!=suma3){
			RT4="SELECT tipo_de_documento,convenio2 FROM P_Cobrconv4 WHERE convenio2="+Convenio+" and ITEM=2";
			result4 = base.ConsultarQuery(RT4);
			int cantidad4 = result4.getRowCount();
				if (cantidad4>0){
					suma4= Math.round(Double.parseDouble(result4.getValueAt(0, 0).toString().trim()));
					Convenio4 = result4.getValueAt(0, 1).toString().trim();
				}
			if(ValorBanco==suma4){
				base.EjecutarQuery("exec comparador '" + ValorBanco + "','" + Convenio + "','P_Cobrconv4'");
				if(z==1){
					RT11="Delete NO_COINCIDENTE where Convenio in(Select BANCOLOMBIA.COD from BANCOLOMBIA inner join COINCIDENTE on BANCOLOMBIA.COD=COINCIDENTE.Convenio where COINCIDENTE.Convenio='"+Convenio+"')";
					base.EjecutarQuery(RT11);
				}
			}
			
		else if(ValorBanco!=suma && ValorBanco!=suma2 && ValorBanco!=suma3 && ValorBanco!=suma4){
			RT5="SELECT tipo_de_documento,convenio2 FROM P_Cobrconv5 WHERE convenio2="+Convenio+" and ITEM=2";
			result5 = base.ConsultarQuery(RT5);
			int cantidad5 = result5.getRowCount();
				if (cantidad5>0){
					suma5=Math.round(Double.parseDouble(result5.getValueAt(0, 0).toString().trim()));
					Convenio5 = result5.getValueAt(0, 1).toString().trim();
				}
			if(ValorBanco==suma5){
				base.EjecutarQuery("exec comparador '" + ValorBanco + "','" + Convenio + "','P_Cobrconv5'");
			if(z==1){
				RT11="Delete NO_COINCIDENTE where Convenio in(Select BANCOLOMBIA.COD from BANCOLOMBIA inner join COINCIDENTE on BANCOLOMBIA.COD=COINCIDENTE.Convenio where COINCIDENTE.Convenio='"+Convenio+"')";
				base.EjecutarQuery(RT11);
			}
		}
			
		else if(ValorBanco!=suma && ValorBanco!=suma2 && ValorBanco!=suma3 && ValorBanco!=suma4 && ValorBanco!=suma5){
			RT6="SELECT tipo_de_documento,convenio2 FROM P_Cobrconv6 WHERE convenio2="+Convenio+" and ITEM=2";
			result6 = base.ConsultarQuery(RT6);
			int cantidad6 = result6.getRowCount();
				if (cantidad6>0){
					suma6= Math.round(Double.parseDouble(result6.getValueAt(0, 0).toString().trim()));
					Convenio6 = result6.getValueAt(0, 1).toString().trim();
				}
			if(ValorBanco==suma6){
				base.EjecutarQuery("exec comparador '" + ValorBanco + "','" + Convenio + "','P_Cobrconv6'");
				if(z==1){
					RT11="Delete NO_COINCIDENTE where Convenio in(Select BANCOLOMBIA.COD from BANCOLOMBIA inner join COINCIDENTE on BANCOLOMBIA.COD=COINCIDENTE.Convenio where COINCIDENTE.Convenio='"+Convenio+"')";
					base.EjecutarQuery(RT11);
				}
			}
//************************************************************************************************************************
			else {//No coincidente 
				if(z==0){//solo se utiliza para cuando la comparacion no son repetidos
					NoComparador();
				}
			}
		}
	 }
   }
  }
}
		} catch (SQLException e) {
			e.printStackTrace();
		}
}
//=======================================================================================================================
	/**
	 * Añade los registros que no fueron coincidente en la tabla NO_COINCIDENTE
	 */
	public void NoComparador (){
		try{
			//envia los registros de no coincidente a la tabla nocoincidente
			
			if(Convenio.equals(Convenio1)){
					Diferencia=ValorBanco-suma;
					base.EjecutarQuery("exec LLenadoNoCoincidente Cobrconv,'"+Convenio1+"',"+Diferencia+",'"+ValorBanco+"'");
			}
			else if(Convenio.equals(Convenio2)){
				Diferencia=ValorBanco-suma2;
				base.EjecutarQuery("exec LLenadoNoCoincidente P_Cobrconv2,'"+Convenio2+"',"+Diferencia+",'"+ValorBanco+"'");	
			}
			else if(Convenio.equals(Convenio3)){
				Diferencia=ValorBanco-suma3;
				base.EjecutarQuery("exec LLenadoNoCoincidente P_Cobrconv3,'"+Convenio3+"',"+Diferencia+",'"+ValorBanco+"'");
			}
			else if(Convenio.equals(Convenio4)){
				Diferencia=ValorBanco-suma4;
				base.EjecutarQuery("exec LLenadoNoCoincidente P_Cobrconv4,'"+Convenio4+"',"+Diferencia+",'"+ValorBanco+"'");
			}
			else if(Convenio.equals(Convenio5)){
				Diferencia=ValorBanco-suma5;
				base.EjecutarQuery("exec LLenadoNoCoincidente P_Cobrconv5,'"+Convenio5+"',"+Diferencia+",'"+ValorBanco+"'");
			}
			else if(Convenio.equals(Convenio6)){
				Diferencia=ValorBanco-suma6;
				base.EjecutarQuery("exec LLenadoNoCoincidente P_Cobrconv6,'"+Convenio6+"',"+Diferencia+",'"+ValorBanco+"'");
			}
			else {
				base.EjecutarQuery("exec LLenadoNoCoincidente '','"+Convenio+"',0,'"+ValorBanco+"'");
			}
		
		} catch (SQLException e) {
				e.printStackTrace();
		}
	}
//=======================================================================================================================
	/**
	 * compara los registros repetidos de Bancolombia Vs Facturacion
	 */
	public void ComparadorRepetido (){
		try{
			//compara los registros de Bancolombia que se encuentran repetido en el numero de convenio
			suma=0;suma2=0;suma3=0;suma4=0;suma5=0;suma6=0;
			Convenio1="";Convenio2="";Convenio3="";Convenio4="";Convenio5="";Convenio6="";
			
			RT1="SELECT CONVENIO,convenio2 FROM Cobrconv WHERE convenio2="+Convenio+" and ITEM=2";
			result1 = base.ConsultarQuery(RT1);
			cantidad1 = result1.getRowCount();
				if (cantidad1>0){
					suma = Math.round(Double.parseDouble(result1.getValueAt(0, 0).toString().trim()));
					Convenio1 = result1.getValueAt(0, 1).toString().trim();
				}
			
			if (ValorBanco==suma){
				base.EjecutarQuery("exec ComparadorRepetido 'Cobrconv','"+Convenio+"'");
				if(z==1){
					RT11="Delete NO_COINCIDENTE where Cod in(Select BANCOLOMBIA.COD from BANCOLOMBIA inner join COINCIDENTE on BANCOLOMBIA.COD=COINCIDENTE.Convenio where COINCIDENTE.Convenio='"+Convenio+"')";
					base.EjecutarQuery(RT11);
				}
			}
		else if(ValorBanco!=suma){
			RT2="SELECT tipo_de_documento,convenio2 FROM P_Cobrconv2 WHERE convenio2="+Convenio+" and ITEM=2";
			result2 = base.ConsultarQuery(RT2);
			int cantidad2 = result2.getRowCount();
				if (cantidad2>0){
					suma2= Math.round(Double.parseDouble(result2.getValueAt(0, 0).toString().trim()));
					Convenio2= result2.getValueAt(0, 1).toString().trim();
				}
			if(ValorBanco==suma2){
				base.EjecutarQuery("exec ComparadorRepetido 'P_Cobrconv2','"+Convenio+"'");
				if(z==1){
					RT11="Delete NO_COINCIDENTE where Cod in(Select BANCOLOMBIA.COD from BANCOLOMBIA inner join COINCIDENTE on BANCOLOMBIA.COD=COINCIDENTE.Convenio where COINCIDENTE.Convenio='"+Convenio+"')";
					base.EjecutarQuery(RT11);
				}
			}
			
		else if(ValorBanco!=suma && ValorBanco!=suma2){
			RT3="SELECT tipo_de_documento,convenio2 FROM P_Cobrconv3 WHERE convenio2="+Convenio+" and ITEM=2";
			result3 = base.ConsultarQuery(RT3);
			int cantidad3 = result3.getRowCount();
				if (cantidad3>0){
					suma3 = Math.round(Double.parseDouble(result3.getValueAt(0, 0).toString().trim()));
					Convenio3 = result3.getValueAt(0, 1).toString().trim();
				}
			if(ValorBanco==suma3){
				base.EjecutarQuery("exec ComparadorRepetido 'P_Cobrconv3','"+Convenio+"'");
				if(z==1){
					RT11="Delete NO_COINCIDENTE where Cod in(Select BANCOLOMBIA.COD from BANCOLOMBIA inner join COINCIDENTE on BANCOLOMBIA.COD=COINCIDENTE.Convenio where COINCIDENTE.Convenio='"+Convenio+"')";
					base.EjecutarQuery(RT11);
				}
			}
			
		else if(ValorBanco!=suma && ValorBanco!=suma2 && ValorBanco!=suma3){
			RT4="SELECT tipo_de_documento,convenio2 FROM P_Cobrconv4 WHERE convenio2="+Convenio+" and ITEM=2";
			result4 = base.ConsultarQuery(RT4);
			int cantidad4 = result4.getRowCount();
				if (cantidad4>0){
					suma4= Math.round(Double.parseDouble(result4.getValueAt(0, 0).toString().trim()));
					Convenio4 = result4.getValueAt(0, 1).toString().trim();
				}
			if(ValorBanco==suma4){
				base.EjecutarQuery("exec ComparadorRepetido 'P_Cobrconv4','"+Convenio+"'");
				if(z==1){
					RT11="Delete NO_COINCIDENTE where Cod in(Select BANCOLOMBIA.COD from BANCOLOMBIA inner join COINCIDENTE on BANCOLOMBIA.COD=COINCIDENTE.Convenio where COINCIDENTE.Convenio='"+Convenio+"')";
					base.EjecutarQuery(RT11);
				}
			}
			
		else if(ValorBanco!=suma && ValorBanco!=suma2 && ValorBanco!=suma3 && ValorBanco!=suma4){
			RT5="SELECT tipo_de_documento,convenio2 FROM P_Cobrconv5 WHERE convenio2="+Convenio+" and ITEM=2";
			result5 = base.ConsultarQuery(RT5);
			int cantidad5 = result5.getRowCount();
				if (cantidad5>0){
					suma5=Math.round(Double.parseDouble(result5.getValueAt(0, 0).toString().trim()));
					Convenio5 = result5.getValueAt(0, 1).toString().trim();
				}
			if(ValorBanco==suma5){
				base.EjecutarQuery("exec ComparadorRepetido 'P_Cobrconv5','"+Convenio+"'");
			if(z==1){
				RT11="Delete NO_COINCIDENTE where Cod in(Select BANCOLOMBIA.COD from BANCOLOMBIA inner join COINCIDENTE on BANCOLOMBIA.COD=COINCIDENTE.Convenio where COINCIDENTE.Convenio='"+Convenio+"')";
				base.EjecutarQuery(RT11);
			}
		}
			
		else if(ValorBanco!=suma && ValorBanco!=suma2 && ValorBanco!=suma3 && ValorBanco!=suma4 && ValorBanco!=suma5){
			RT6="SELECT tipo_de_documento,convenio2 FROM P_Cobrconv6 WHERE convenio2="+Convenio+" and ITEM=2";
			result6 = base.ConsultarQuery(RT6);
			int cantidad6 = result6.getRowCount();
				if (cantidad6>0){
					suma6= Math.round(Double.parseDouble(result6.getValueAt(0, 0).toString().trim()));
					Convenio6 = result6.getValueAt(0, 1).toString().trim();
				}
			if(ValorBanco==suma6){
				base.EjecutarQuery("exec ComparadorRepetido 'P_Cobrconv6','"+Convenio+"'");
				if(z==1){
					RT11="Delete NO_COINCIDENTE where Cod in(Select BANCOLOMBIA.COD from BANCOLOMBIA inner join COINCIDENTE on BANCOLOMBIA.COD=COINCIDENTE.Convenio where COINCIDENTE.Convenio='"+Convenio+"')";
					base.EjecutarQuery(RT11);
				}
			}
		}
	 }
   }
  }
}
		}
	 catch (SQLException e) {
		e.printStackTrace();
	 }
	}
//=======================================================================================================================
	/**
	 * compara los registros de los 3 catradia Vs los registros de NoCoincidente para encontrar coincidencia entre los valores
	 */
	public void CatradiaVsNoCoincidente(){
	//compara los registros de catradia vs los registros que se encuentran en la tabala de no coincidente
		try{
		suma=0;suma2=0;suma3=0;
		Convenio1="";Convenio2="";Convenio3="";
		
		RT11=" SELECT convenio,sum(dtr_monto_mn) as Monto  FROM [Libranza].[dbo].Catradia1 "
				+" where (dtr_concepto='PAGLIBRANZ'  or dtr_concepto='"+validar+"') AND  convenio='"+Convenio+"' group by convenio" ;//trae la data de catradia 1 vs cagmaest 1
		
		result1 = base.ConsultarQuery(RT11);
		cantidad1 = result1.getRowCount();
			
			if (cantidad1>0){
				suma = Math.round(Double.parseDouble(result1.getValueAt(0, 1).toString().trim()));
				//Convenio1 = result1.getValueAt(0, 1).toString().trim();
			}
				if (ValorBanco==suma){
					base.EjecutarQuery("exec CatradiaVsNoCoincidente 'Catradia1','"+Convenio+"'");
					RT11="Delete NO_COINCIDENTE where Cod in(Select BANCOLOMBIA.COD from BANCOLOMBIA inner join COINCIDENTE on BANCOLOMBIA.COD=COINCIDENTE.Convenio where COINCIDENTE.Convenio='"+Convenio+"')";
					base.EjecutarQuery(RT11);
				}
			
			else if(ValorBanco!=suma){
				RT12=" SELECT convenio,sum(dtr_monto_mn) as Monto  FROM [Libranza].[dbo].Catradia2 "
						+" where (dtr_concepto='PAGLIBRANZ'  or dtr_concepto='"+validar+"') AND  convenio='"+Convenio+"' group by convenio" ;//trae la data de catradia 1 vs cagmaest 1
						
				result2 = base.ConsultarQuery(RT12);
				int cantidad2 = result2.getRowCount();
				if (cantidad2>0){
					suma2 = Math.round(Double.parseDouble(result2.getValueAt(0, 1).toString().trim()));
				}
					if (ValorBanco==suma2){
						base.EjecutarQuery("exec CatradiaVsNoCoincidente 'Catradia2','"+Convenio+"'");
						RT11="Delete NO_COINCIDENTE where Cod in(Select BANCOLOMBIA.COD from BANCOLOMBIA inner join COINCIDENTE on BANCOLOMBIA.COD=COINCIDENTE.Convenio where COINCIDENTE.Convenio='"+Convenio+"')";
						base.EjecutarQuery(RT11);
					}
			
			else {
				RT13=" SELECT convenio,sum(dtr_monto_mn) as Monto  FROM [Libranza].[dbo].Catradia3 "
						+" where (dtr_concepto='PAGLIBRANZ'  or dtr_concepto='"+validar+"')  AND  convenio='"+Convenio+"' group by convenio" ;//trae la data de catradia 1 vs cagmaest 1
					
				result3 = base.ConsultarQuery(RT13);
				int cantidad3 = result3.getRowCount();
				if (cantidad3>0){
					suma3 = Math.round(Double.parseDouble(result3.getValueAt(0, 1).toString().trim()));
				}
					if (ValorBanco==suma3){
						base.EjecutarQuery("exec CatradiaVsNoCoincidente 'Catradia3','"+Convenio+"'");
						RT11="Delete NO_COINCIDENTE where Cod in(Select BANCOLOMBIA.COD from BANCOLOMBIA inner join COINCIDENTE on BANCOLOMBIA.COD=COINCIDENTE.Convenio where COINCIDENTE.Convenio='"+Convenio+"')";
						base.EjecutarQuery(RT11);
				}
			}
		}
		} catch (SQLException e) {
			e.printStackTrace();
		}
}
//=======================================================================================================================
	/**
	 * Genera las carpetas por regional
	 * 
	 * @param direccion  ruta donde desea generar las carpetas
	 */
	public void Carpetas(String direccion){
		try {
			//crea las carpetas por regional
		String[] CARPETAS = new String[]{
                "ANTIOQUIA",
                "BOGOTA",
                "CAFETERA",
                "COSTA",
                "OCCIDENTE",
                "ORIENTE",
                "SANTANDERES",
                "SUR",
                "OTROS",
                "OTROS"
      	};
			result10_3=base.ConsultarQuery("SELECT Nombre FROM DataCobrconv WHERE posicion=8");

		//String Resultado=	result10_3.getValueAt(0, 0).toString().replace("catradia_", "").replace("_of00000", "");
		String Resultado=	result10_3.getValueAt(0, 0).toString().substring(9,15);
		
		String Carp3=direccion+"\\Pagos_"+Resultado;//nombre de la carpeta principal
		File crea_carpeta3=new File(Carp3);
		crea_carpeta3.mkdir();//CREA CARPETA
		//carp4 y cap5 son carpetas secundarias donde se van almacenar la informacion
		Carp4=direccion+"\\Pagos_"+Resultado+"\\Conciliados";
		File crea_carpeta4=new File(Carp4);
		crea_carpeta4.mkdir();//CREA CARPETA
		
		Carp5=direccion+"\\Pagos_"+Resultado+"\\No Conciliados";
		File crea_carpeta5=new File(Carp5);
		crea_carpeta5.mkdir();//CREA CARPETA
		
		for (int k=0;k<CARPETAS.length;k++){//crea carpeta por carpeta de regional
				String Carp2=direccion+"\\Pagos_"+Resultado+"\\No Conciliados\\"+CARPETAS[k];//SELECCIONA CARPETAS
				File crea_carpeta2=new File(Carp2);
				crea_carpeta2.mkdir();//CREA CARPETA
			}
				} catch (SQLException e) {
					e.printStackTrace();
				} //CONSULTA EL LIBRO DE CATRADIA ACTUAL
	}
//=======================================================================================================================
	/**
	 * actualiza la BD de NO_COINCIDENTE2 a partir de los registro que no existen
	 */
	public void NoComparador2 (){
		try{
			//System.out.println("NADA");
			
			if(Grado.equals("1")){
				Diferencia=ValorBanco-suma;
				base.EjecutarQuery("exec LLenadoNoCoincidente2 '"+Convenio+"','"+Diferencia+"',"+1);
			}
			else if(Grado.equals("2")){
				Diferencia=ValorBanco-suma;
				base.EjecutarQuery("exec LLenadoNoCoincidente2 '"+Convenio+"','"+Diferencia+"',"+2);	
			}
			else if(Grado.equals("3")){
				Diferencia=ValorBanco-suma;
				base.EjecutarQuery("exec LLenadoNoCoincidente2 '"+Convenio+"','"+Diferencia+"',"+3);
			}
			else if(Grado.equals("4")){
				Diferencia=ValorBanco-suma;
				base.EjecutarQuery("exec LLenadoNoCoincidente2 '"+Convenio+"','"+Diferencia+"',"+4);
			}
			else if(Grado.equals("5")){
				Diferencia=ValorBanco-suma;
				base.EjecutarQuery("exec LLenadoNoCoincidente2 '"+Convenio+"','"+Diferencia+"',"+5);
			}
			else if(Grado.equals("6")){
				Diferencia=ValorBanco-suma6;
				base.EjecutarQuery("exec LLenadoNoCoincidente2 '"+Convenio+"','"+Diferencia+"',"+6);
			}
			else {
				Diferencia=ValorBanco;
				base.EjecutarQuery("exec LLenadoNoCoincidente2 '"+Convenio+"','"+Diferencia+"',"+7);
			}
		
		} catch (SQLException e) {
				e.printStackTrace();
		}
	}
//=======================================================================================================================
	/**
	 * recorre cada una de las filas de la BD por # de convenio 
	 * @param cantiidad6 cantidad de registros con un mismo convenio
	 * @param tipo categoria 1, 2, o 3
	 */
	public void recorrer(int cantiidad6,int tipo,JPanel PanelPorcentaje,JLabel LabelPorcentaje){//recorre conveio por conveio para poder crear un archivo consolidado  por convenio
		int R=0;
		cont=0;
		for( R=0;R<cantiidad6;R++){//recorre el query
		try {	
				Convenio=result10_3.getValueAt(R, 0).toString();//captura el convenio del query
				ActualizarNoComparador(tipo);
				RT8="update [Libranza].[dbo].[NO_COINCIDENTE2] set USADO='SI' where Cod='"+Convenio+"' ";
				base.EjecutarQuery(RT8);
					porcentaje = (R * 100) /cantiidad6;
						if (porcentaje >= (cont + 1)) {// permite mostrar el porcentaje una sola vez
							LabelPorcentaje.setText("Actualizando BD " + porcentaje+ "% " + " Van " + R + " de "+ cantiidad6);
							PanelPorcentaje.update(PanelPorcentaje.getGraphics());// para actualizar el panel y poder mostrar el proceso
							cont = cont + 1;
							if (cont>=100){cont=99;}
						}
				
				} catch (SQLException e) {
					e.printStackTrace();
				}
		}
	}
//=======================================================================================================================
	/**
	 * Actualiza la BD de  NO_COINCIDENTE2 generando cruces para encontrar registros faltantes
	 * 
	 * @param tipo Categoria 1,2 o 3
	 */
	public void ActualizarNoComparador(int tipo){// actualiza la tabala de nocioncidente  con los registros faltantesde la tabla.
		try {
				if (tipo==1){
					base.EjecutarQuery("exec ActualizarNoComparador '"+Convenio+"','"+validar+"',"+tipo);
				}
					else if (tipo==2){
						base.EjecutarQuery("exec ActualizarNoComparador '"+Convenio+"','"+validar+"',"+tipo);
					}
						else if (tipo==3){
							base.EjecutarQuery("exec ActualizarNoComparador '"+Convenio+"','"+validar+"',"+tipo);
						}
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
		}

//=======================================================================================================================
/**
 * Compara el resultado de la suma de ?? con Bancolombia y verifica si son coincidentes o no
 */
	public void Bancolombi_Vs_VrFacturacion(String Convenio,long ValorBanco,double suma,double suma2) {
	    //compara los registros de Bancolobia vs Cobrconv
	    try {
	        if (ValorBanco == suma) {
	           base.EjecutarQuery("exec comparador '" + ValorBanco + "','" + Convenio + "','Cobrconv'");
	           base.EjecutarQuery("Delete NO_COINCIDENTE2 where Cod in('"+Convenio+"')");
	        }
	        else{
	            if (ValorBanco == suma2) {
	               base.EjecutarQuery("exec comparador '" + ValorBanco + "','" + Convenio + "','P_Cobrconv2'");
	               base.EjecutarQuery("Delete NO_COINCIDENTE2 where Cod in('"+Convenio+"')");
	            }
	        }
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
	}
	
}//fin de la clase procesoPagos