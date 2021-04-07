/**
 * actualiza la BD por medio de Querys
 */
package CapaEjecucion;

import javax.swing.JLabel;
import javax.swing.JPanel;

import CapaNegocio.cargaTXR;
import CargaDatos.conexionBD;
public class Ejecucion {

	/**
	 * Actualiza todas las tablas de la BD de SQL por medio de Querys
	 * 
	 * @param direccion Ruta donde desea guardar los archivos generados
	 * @param PanelProcesamiento Actualizacion del porcentaje
	 * @param LabelProcesamiento Actualizacion del porcentaje
	 */
	public void conteo(String direccion,JPanel PanelProcesamiento, JLabel LabelProcesamiento) {

		try{
			cargaTXR function = new cargaTXR();	
			conexionBD base = new conexionBD();
			
			LabelProcesamiento.setText("Espere...");
			PanelProcesamiento.update(PanelProcesamiento.getGraphics());//para actualizar el panel y poder mostrar el proceso

			
//======================================================================================================================
			/*se comprueba si la tabla BASE_CONSOLIDADA_CLIENTES_LIBRANZA existe o no,
			 * en caso de existir se elimina*/
				String miTabla ="  IF OBJECT_ID (N'[BASE_CONSOLIDADA_CLIENTES_LIBRANZA]', N'U') IS NOT NULL "
						+ "  DROP TABLE [Libranza].[dbo].[BASE_CONSOLIDADA_CLIENTES_LIBRANZA]; ";

				base.EjecutarQuery(miTabla);				
				/*sube el query para cruzar los archivos Clientes, Desvinculados  y Empresa y generar la tabla BASE_CONSOLIDADA_CLIENTES_LIBRANZA */
				String miClientes ="SELECT [Libranza].[dbo].[EMPRESAS].[CONVENIO], [Libranza].[dbo].[EMPRESAS].[NIT], [Libranza].[dbo].[EMPRESAS].[NOMBRE_EMPRESA], [Libranza].[dbo].[EMPRESAS].[FECHA_DE_PAGO], [Libranza].[dbo].[EMPRESAS].[correo_electronico_Empresa], [Libranza].[dbo].[CLIENTES].[ITEM], [Libranza].[dbo].[CLIENTES].[Tipo_de_Documento], "
						+ "	[Libranza].[dbo].[CLIENTES].[Numero_de_Documento], [Libranza].[dbo].[CLIENTES].[Nombre], [Libranza].[dbo].[CLIENTES].[No_Obligación], [Libranza].[dbo].[CLIENTES].[Código_Oficina], [Libranza].[dbo].[CLIENTES].[Nombre_Oficina], [Libranza].[dbo].[CLIENTES].[Vr_Cuota_Mensual], [Libranza].[dbo].[CLIENTES].[Vr_en_Mora], [Libranza].[dbo].[CLIENTES].[Vr_Total_a_Pagar],"
						+ " [Libranza].[dbo].[CLIENTES].[Vr_Intereses_en_Mora], [Libranza].[dbo].[CLIENTES].[Cuotas_Pactadas], [Libranza].[dbo].[CLIENTES].[Cuotas_a_Pagar], [Libranza].[dbo].[EMPRESAS].[Valor_Pago_Empresa], [Libranza].[dbo].[EMPRESAS].[novedad], [Libranza].[dbo].[EMPRESAS].[Observaciones], [Libranza].[dbo].[CLIENTES].[Orden]  "
						+ "  into [Libranza].[dbo].[BASE_CONSOLIDADA_CLIENTES_LIBRANZA]"
						+ "  FROM ([Libranza].[dbo].[CLIENTES] LEFT JOIN [Libranza].[dbo].[BASE_DESVINCULADO] ON [Libranza].[dbo].[CLIENTES].[No_Obligación] = [Libranza].[dbo].[BASE_DESVINCULADO].[mo_numero_de_banco]) LEFT JOIN [Libranza].[dbo].[EMPRESAS] ON [Libranza].[dbo].[CLIENTES].[CONVENIO] = [Libranza].[dbo].[EMPRESAS].[CONVENIO] "
						+ "  where ((([Libranza].[dbo].[BASE_DESVINCULADO].[mo_numero_de_banco]) Is Null))";		 
				base.EjecutarQuery(miClientes);
//======================================================================================================================
				
				/*se comprueba si la tabla BASE_SALDOS_ACTUALIZADOS_FACTURACION_CORTE existe o no,
				 * en caso de existir se borra*/	
				String miTabla2 ="  IF OBJECT_ID (N'[BASE_SALDOS_ACTUALIZADOS_FACTURACION_CORTE]', N'U') IS NOT NULL "
						+ "  DROP TABLE [Libranza].[dbo].[BASE_SALDOS_ACTUALIZADOS_FACTURACION_CORTE]; ";
				
				base.EjecutarQuery(miTabla2);
				/*sube el query para cruzar los archivos Cagmaest y BASE_CONSOLIDADA_CLIENTES_LIBRANZA y generar la tabla BASE_SALDOS_ACTUALIZADOS_FACTURACION_CORTE*/
				String miFacturacionCorte ="SELECT [BASE_CONSOLIDADA_CLIENTES_LIBRANZA].[CONVENIO], [BASE_CONSOLIDADA_CLIENTES_LIBRANZA].[NIT], [BASE_CONSOLIDADA_CLIENTES_LIBRANZA].[NOMBRE_EMPRESA], [BASE_CONSOLIDADA_CLIENTES_LIBRANZA].[FECHA_DE_PAGO], [BASE_CONSOLIDADA_CLIENTES_LIBRANZA].[Correo_electronico_Empresa],"
						+"	[BASE_CONSOLIDADA_CLIENTES_LIBRANZA].[ITEM], [BASE_CONSOLIDADA_CLIENTES_LIBRANZA].[Tipo_de_Documento], [BASE_CONSOLIDADA_CLIENTES_LIBRANZA].[Numero_de_Documento], [BASE_CONSOLIDADA_CLIENTES_LIBRANZA].[Nombre],"
						+"	('********' + substring ([BASE_CONSOLIDADA_CLIENTES_LIBRANZA].[No_Obligación],9,7)) AS [Obligación], "
						+"	([CONVENIO] + trim([Numero_de_Documento]) + ('********' + substring ([BASE_CONSOLIDADA_CLIENTES_LIBRANZA].[No_Obligación],9,7))) AS [LLAVE],"
						+"	[BASE_CONSOLIDADA_CLIENTES_LIBRANZA].[No_Obligación], [BASE_CONSOLIDADA_CLIENTES_LIBRANZA].[Código_Oficina], [BASE_CONSOLIDADA_CLIENTES_LIBRANZA].[Nombre_Oficina], [BASE_CONSOLIDADA_CLIENTES_LIBRANZA].[Vr_Cuota_Mensual],"
						+"	[BASE_CONSOLIDADA_CLIENTES_LIBRANZA].[Vr_en_Mora], [BASE_CONSOLIDADA_CLIENTES_LIBRANZA].[Vr_Total_a_Pagar], [BASE_CONSOLIDADA_CLIENTES_LIBRANZA].[Vr_Intereses_en_Mora], [BASE_CONSOLIDADA_CLIENTES_LIBRANZA].[Cuotas_Pactadas], [BASE_CONSOLIDADA_CLIENTES_LIBRANZA].[Cuotas_a_Pagar],"
						+"	[BASE_CONSOLIDADA_CLIENTES_LIBRANZA].[Valor_Pago_Empresa], [BASE_CONSOLIDADA_CLIENTES_LIBRANZA].[novedad], [BASE_CONSOLIDADA_CLIENTES_LIBRANZA].[Observaciones], [Cagmaest].[mo_dias_vencido_op],"
						+"	[Cagmaest].[mo_fecha_prox_vencimiento],"
						+"	[Cagmaest].[mo_saldo_cuota_prox_venc], [Cagmaest].[mo_saldo_capital_vigente],"
						+"	[Cagmaest].[mo_saldo_capital_vencido], [Cagmaest].[mo_saldo_interes_vigente],"
						+"	[Cagmaest].[mo_saldo_interes_vencido], [Cagmaest].[mo_saldo_interes_contingente],"
						+"	[Cagmaest].[mo_saldo_mora_contingente], [Cagmaest].[mo_saldo_seguro_vida_vigente],"
						+"	[Cagmaest].[mo_saldo_seguro_vida_vencido], [Cagmaest].[mo_saldo_otros_vigente],"
						+"	[Cagmaest].[mo_saldo_otros_vencidos], [Cagmaest].[mo_estado_obligacion],"
						+"	[Cagmaest].[mo_fecha_ult_pago], [Cagmaest].[mo_valor_ult_pago], "
						+"	[Cagmaest].[mo_numero_cuotas_vencidas], [BASE_CONSOLIDADA_CLIENTES_LIBRANZA].[Orden]"
						+"	INTO [BASE_SALDOS_ACTUALIZADOS_FACTURACION_CORTE]"
						+"	FROM [BASE_CONSOLIDADA_CLIENTES_LIBRANZA] LEFT JOIN [Cagmaest] ON [BASE_CONSOLIDADA_CLIENTES_LIBRANZA].[No_Obligación] = [Cagmaest].[mo_numero_de_banco]";

				base.EjecutarQuery(miFacturacionCorte);
//======================================================================================================================
				LabelProcesamiento.setText("Preparando Archivos");
				PanelProcesamiento.update(PanelProcesamiento.getGraphics());//para actualizar el panel y poder mostrar el proceso

				/*se hace un filtro en la tabla BASE_SALDOS_ACTUALIZADOS_FACTURACION_CORTE y remplaza lo que esta en Vr_Total_a_Pagar por Vr_Cuota_Mensual*/
				
				String mifiltro ="UPDATE [Libranza].[dbo].[BASE_SALDOS_ACTUALIZADOS_FACTURACION_CORTE] SET  Vr_Total_a_Pagar=Vr_Cuota_Mensual  where Vr_en_Mora <> '0' AND mo_dias_vencido_op='0' AND mo_saldo_capital_vencido='0.00'  AND mo_saldo_interes_vencido='0.00'  AND mo_saldo_mora_contingente='0.00'"
								+"	UPDATE [Libranza].[dbo].[BASE_SALDOS_ACTUALIZADOS_FACTURACION_CORTE] SET Vr_Intereses_en_Mora=0 where Vr_en_Mora <> '0' AND mo_dias_vencido_op='0' AND mo_saldo_capital_vencido='0.00'  AND mo_saldo_interes_vencido='0.00'  AND mo_saldo_mora_contingente='0.00'"
								+"	UPDATE [Libranza].[dbo].[BASE_SALDOS_ACTUALIZADOS_FACTURACION_CORTE] SET Vr_en_Mora=0 where Vr_en_Mora <> '0' AND mo_dias_vencido_op='0' AND mo_saldo_capital_vencido='0.00'  AND mo_saldo_interes_vencido='0.00'  AND mo_saldo_mora_contingente='0.00'";
				base.EjecutarQuery(mifiltro);
				
//======================================================================================================================
				/*se hace un filtro en mo_estado_obligacion por aquellas que sean CANCELADO y se eliminan*/				
				String miCancelado ="delete from [Libranza].[dbo].[BASE_SALDOS_ACTUALIZADOS_FACTURACION_CORTE] where  mo_estado_obligacion='CANCELADO'";
				base.EjecutarQuery(miCancelado);
//======================================================================================================================

				/*se crea una tabla con respecto a la tabla BASE_SALDOS_ACTUALIZADOS_FACTURACION_CORTE*/	
				
				String miTabla3 ="  IF OBJECT_ID (N'[BASE _DE_DATOS_CLIENTES_FINAL]', N'U') IS NOT NULL "
						+ "  DROP TABLE [Libranza].[dbo].[BASE _DE_DATOS_CLIENTES_FINAL]; ";
				base.EjecutarQuery(miTabla3);
				
				String miClientesFinal="SELECT [BASE_SALDOS_ACTUALIZADOS_FACTURACION_CORTE].[ITEM], [BASE_SALDOS_ACTUALIZADOS_FACTURACION_CORTE].[Tipo_de_Documento],[BASE_SALDOS_ACTUALIZADOS_FACTURACION_CORTE].[Numero_de_Documento],[BASE_SALDOS_ACTUALIZADOS_FACTURACION_CORTE].[Nombre],"
										+"	[BASE_SALDOS_ACTUALIZADOS_FACTURACION_CORTE].[No_Obligación],[BASE_SALDOS_ACTUALIZADOS_FACTURACION_CORTE].[Código_Oficina],[BASE_SALDOS_ACTUALIZADOS_FACTURACION_CORTE].[Nombre_Oficina],[BASE_SALDOS_ACTUALIZADOS_FACTURACION_CORTE].[Vr_Cuota_Mensual],"
										+"	[BASE_SALDOS_ACTUALIZADOS_FACTURACION_CORTE].[Vr_en_Mora],[BASE_SALDOS_ACTUALIZADOS_FACTURACION_CORTE].[Vr_Total_a_Pagar],[BASE_SALDOS_ACTUALIZADOS_FACTURACION_CORTE].[Vr_Intereses_en_Mora],[BASE_SALDOS_ACTUALIZADOS_FACTURACION_CORTE].[Cuotas_Pactadas],"
										+"	[BASE_SALDOS_ACTUALIZADOS_FACTURACION_CORTE].[Cuotas_a_Pagar],[BASE_SALDOS_ACTUALIZADOS_FACTURACION_CORTE].[Valor_Pago_Empresa],[BASE_SALDOS_ACTUALIZADOS_FACTURACION_CORTE].[novedad],[BASE_SALDOS_ACTUALIZADOS_FACTURACION_CORTE].[Observaciones],"
										+"	[BASE_SALDOS_ACTUALIZADOS_FACTURACION_CORTE].[CONVENIO],[BASE_SALDOS_ACTUALIZADOS_FACTURACION_CORTE].[Orden]"
										+"	INTO [Libranza].[dbo].[BASE _DE_DATOS_CLIENTES_FINAL]"
										+"	FROM [Libranza].[dbo].[BASE_SALDOS_ACTUALIZADOS_FACTURACION_CORTE]";
				base.EjecutarQuery(miClientesFinal);
//======================================================================================================================				
				//LabelProcesamiento.setText("Generando Cruces");
				//PanelProcesamiento.update(PanelProcesamiento.getGraphics());//para actualizar el panel y poder mostrar el proceso
				
				/*se actualiza la tabla facturacion con respecto a la tabla BASE _DE_DATOS_CLIENTES_FINAL*/
				String miTabla4 ="  IF OBJECT_ID (N'[FACTURACION2]', N'U') IS NOT NULL "
								+ "   truncate table [FACTURACION2]; "
								+ "   IF OBJECT_ID (N'[Tb_Dinamica]', N'U') IS NOT NULL"
								+ "   truncate table [Tb_Dinamica];";
				base.EjecutarQuery(miTabla4);
				String miFacturacion2="insert into [Libranza].[dbo].[Tb_Dinamica]"
									+ "   select CONVENIO, sum(Vr_Cuota_Mensual) as Vr_Cuota_Mensual,SUM(Vr_en_Mora) as Vr_en_Mora,SUM(Vr_Total_a_Pagar) as Vr_Total_a_Pagar,SUM(Vr_Intereses_en_Mora) as Vr_Intereses_en_Mora"
									+ "   from [BASE _DE_DATOS_CLIENTES_FINAL]"
									+ "   group by CONVENIO "
									+ "   order by cast(CONVENIO as int)"
									+ "   														"
									+ "   insert into [Libranza].[dbo].[FACTURACION2]"
									+ "   SELECT ITEM,Vr_Cuota_Mensual_TD,Vr_en_Mora_TD,Vr_Total_a_Pagar_TD,Vr_Intereses_en_Mora_TD,CONVENIO,Orden FROM FACTURACION left join Tb_Dinamica on FACTURACION.CONVENIO =Tb_Dinamica.CONVENIO_TD order by cast(CONVENIO as int)"
									+ "   update [Libranza].[dbo].[FACTURACION2] set Vr_Cuota_Mensual='0',Vr_en_Mora='0',Vr_Total_a_Pagar='0',Vr_Intereses_en_Mora='0' where Vr_Cuota_Mensual is null or Vr_en_Mora is null or Vr_Total_a_Pagar is null or Vr_Intereses_en_Mora is null";
				base.EjecutarQuery(miFacturacion2);		
//======================================================================================================================

				/*se crea una tabla cobrconv con respecto a las tablas Empresa,Facturacion,Clientes*/					
				String miTabla5 ="  IF OBJECT_ID (N'[Cobrconv]', N'U') IS NOT NULL "
								+ "   truncate table [Cobrconv]; ";

				base.EjecutarQuery(miTabla5);
				String miCobrconv="insert into Libranza.dbo.Cobrconv "
								+" SELECT [ITEM],[CONVENIO],[NIT],[NOMBRE_EMPRESA],[DIRECCION],[TELEFONO],[CIUDAD],[DEPARTAMENTO],[FECHA_DE_PAGO],[correo_electronico_Empresa],[Valor_Pago_Empresa],[novedad],[Observaciones],[CONVENIO] AS convenio2,[Orden] "
								+" FROM [Libranza].[dbo].[EMPRESAS] "
								+" UNION all	"
								+" SELECT [ITEM],str([Vr_Cuota_Mensual],20,2) AS [Vr_Cuota_Mensual] ,str([Vr_en_Mora] ,20,2) AS [Vr_en_Mora],str([Vr_Total_a_Pagar] ,20,2)AS [Vr_Total_a_Pagar],str([Vr_Intereses_en_Mora],20,2)AS [Vr_Intereses_en_Mora],'','','','','','','','',[CONVENIO],[Orden] "
								+" FROM [Libranza].[dbo].[FACTURACION2] "
								+" UNION all "
								+" SELECT [ITEM],[Tipo_de_Documento],[Numero_de_Documento],[Nombre],[No_Obligación],[Código_Oficina],[Nombre_Oficina],str([Vr_Cuota_Mensual],14,0) AS Vr_Cuota_Mensual,str([Vr_en_Mora],14,0)AS Vr_en_Mora,[Vr_Total_a_Pagar],str([Vr_Intereses_en_Mora],14,0)AS Vr_Intereses_en_Mora,str([Cuotas_Pactadas],14,0)AS Cuotas_Pactadas,[Cuotas_a_Pagar],[CONVENIO],[Orden] "
								+" FROM [Libranza].[dbo].[BASE _DE_DATOS_CLIENTES_FINAL]; "
								+" update Libranza.dbo.Cobrconv set correo_electronico_Empresa='',Valor_Pago_Empresa='',novedad='',Observaciones='' where correo_electronico_Empresa is null or Valor_Pago_Empresa is null or novedad is null or Observaciones is null ";

				base.EjecutarQuery(miCobrconv);
//======================================================================================================================				
				LabelProcesamiento.setText("Actualizando Cobrconv");
				PanelProcesamiento.update(PanelProcesamiento.getGraphics());//para actualizar el panel y poder mostrar el proceso

				/*ACTUALIZA LA TABLA COBRCONV CON EL LARGO CORRECTO PARA CADA CAMPO*/					
				String miTabla6 =" UPDATE Libranza.dbo.Cobrconv set CONVENIO='0'+CONVENIO where Len(CONVENIO)=3 and ITEM=1 "
								+" UPDATE Libranza.dbo.Cobrconv set CONVENIO='00'+CONVENIO where Len(CONVENIO)=2 and ITEM=1 "
						
								+" UPDATE Libranza.dbo.Cobrconv set DEPARTAMENTO=TRIM(DEPARTAMENTO) where ITEM=3" 
								+" UPDATE Libranza.dbo.Cobrconv set DEPARTAMENTO=replicate('0',12-Len(DEPARTAMENTO))+DEPARTAMENTO+'.00' where  Len(DEPARTAMENTO)<=12 and ITEM=3"
								+" UPDATE Libranza.dbo.Cobrconv set FECHA_DE_PAGO=TRIM(FECHA_DE_PAGO) where ITEM=3 "
								+" UPDATE Libranza.dbo.Cobrconv set FECHA_DE_PAGO=replicate('0',12-Len(FECHA_DE_PAGO))+FECHA_DE_PAGO+'.00' where  Len(FECHA_DE_PAGO)<=12 and ITEM=3"
								+" UPDATE Libranza.dbo.Cobrconv set novedad=trim(novedad)  where ITEM=3 "
								+" UPDATE Libranza.dbo.Cobrconv set novedad=replicate(0,6-Len(novedad))+novedad where  Len(novedad)<6 and ITEM=3 "
								+" UPDATE Libranza.dbo.Cobrconv set Observaciones=TRIM(Observaciones) where ITEM=3 "
								+" UPDATE Libranza.dbo.Cobrconv set Observaciones=replicate(0,6-Len(Observaciones))+Observaciones where  Len(Observaciones)<6 and ITEM=3 "
								
								+" UPDATE Libranza.dbo.Cobrconv set CONVENIO=TRIM(CONVENIO) where ITEM=2"
								+" UPDATE Libranza.dbo.Cobrconv set CONVENIO=replicate(0,15-Len(CONVENIO))+CONVENIO where  Len(CONVENIO)<15 and ITEM=2"
								+" UPDATE Libranza.dbo.Cobrconv set NIT=TRIM(NIT) where ITEM=2"
								+" UPDATE Libranza.dbo.Cobrconv set NIT=replicate(0,15-Len(NIT))+NIT  where  Len(NIT)<15 and ITEM=2"
								+" UPDATE Libranza.dbo.Cobrconv set NOMBRE_EMPRESA=TRIM(NOMBRE_EMPRESA) where ITEM=2"
								+" UPDATE Libranza.dbo.Cobrconv set NOMBRE_EMPRESA=replicate(0,15-Len(NOMBRE_EMPRESA))+NOMBRE_EMPRESA  where  Len(NOMBRE_EMPRESA)<15 and ITEM=2"
								+" UPDATE Libranza.dbo.Cobrconv set DIRECCION=TRIM(DIRECCION) where ITEM=2"
								+" UPDATE Libranza.dbo.Cobrconv set DIRECCION=replicate(0,15-Len(DIRECCION))+DIRECCION  where  Len(DIRECCION)<15 and ITEM=2";

				base.EjecutarQuery(miTabla6);
				
//======================================================================================================================	
				/* quita los campos en null */
				String mitabla7= " update BASE_CONSOLIDADA_CLIENTES_LIBRANZA set correo_electronico_Empresa='',Valor_Pago_Empresa='',novedad='',"
								+" Observaciones='' where correo_electronico_Empresa is null and  Valor_Pago_Empresa is null and novedad is null and Observaciones is null"
								
								+" update BASE_SALDOS_ACTUALIZADOS_FACTURACION_CORTE set Correo_electronico_Empresa='',"
								+" Valor_Pago_Empresa='',novedad='',Observaciones='' where Correo_electronico_Empresa is null and Valor_Pago_Empresa is null"
								+" and novedad is null and Observaciones is null";
				
				base.EjecutarQuery(mitabla7);

//======================================================================================================================			
				//ACTUALIZA EL LARGO DE CONVENIO CON CERO A LA IZQUIERDA PARA EL PROCESO DE ENVIO DE CORREOS
				String mitabla8="UPDATE Libranza.dbo.Parametrizados set Convenio=TRIM(Convenio)  "
								+" UPDATE Libranza.dbo.Parametrizados set Convenio=replicate('0',4-Len(Convenio))+Convenio where  Len(Convenio)<4 "
								+" UPDATE Libranza.dbo.BASE_SALDOS_ACTUALIZADOS_FACTURACION_CORTE set CONVENIO=TRIM(CONVENIO)  "
								+" UPDATE Libranza.dbo.BASE_SALDOS_ACTUALIZADOS_FACTURACION_CORTE set CONVENIO=replicate('0',4-Len(CONVENIO))+CONVENIO where  Len(CONVENIO)<4 ";
				
				base.EjecutarQuery(mitabla8);
//======================================================================================================================					
				function.escribirTxtCobrconv(direccion,PanelProcesamiento, LabelProcesamiento);	//funcion para generar el archivo en txt actualizado de COBRCONV
				
		}	 
	
		catch(Exception ex){
				System.out.println("ERROR ACTUALIZANDO LOS ARCHIVOS");
				ex.printStackTrace();
		}
	}
}


