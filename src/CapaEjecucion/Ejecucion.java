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
				base.EjecutarQuery("exec EliminaBaseConsolidadoCliente");				
				/*sube el query para cruzar los archivos Clientes, Desvinculados  y Empresa y generar la tabla BASE_CONSOLIDADA_CLIENTES_LIBRANZA */
				base.EjecutarQuery("exec Generar_BASE_CONSOLIDADA_CLIENTES_LIBRANZA");
//======================================================================================================================
				
				/*se comprueba si la tabla BASE_SALDOS_ACTUALIZADOS_FACTURACION_CORTE existe o no,
				 * en caso de existir se borra*/	
				base.EjecutarQuery("exec Elimina_BASE_SALDOS_ACTUALIZADOS_FACTURACION_CORTE");
				/*sube el query para cruzar los archivos Cagmaest y BASE_CONSOLIDADA_CLIENTES_LIBRANZA y genera la tabla BASE_SALDOS_ACTUALIZADOS_FACTURACION_CORTE*/
				base.EjecutarQuery("Generar_BASE_SALDOS_ACTUALIZADOS_FACTURACION_CORTE");
//======================================================================================================================
				LabelProcesamiento.setText("Preparando Archivos");
				PanelProcesamiento.update(PanelProcesamiento.getGraphics());//para actualizar el panel y poder mostrar el proceso
				/*se hace un filtro en la tabla BASE_SALDOS_ACTUALIZADOS_FACTURACION_CORTE y remplaza lo que esta en Vr_Total_a_Pagar por Vr_Cuota_Mensual*/
				base.EjecutarQuery("exec Actualizacion_BASE_SALDOS_ACTUALIZADOS_FACTURACION_CORTE");
//======================================================================================================================
				/*se hace un filtro en mo_estado_obligacion por aquellas que sean CANCELADO y se eliminan*/				
				String miCancelado ="exec EstadoCancelado";
				base.EjecutarQuery(miCancelado);
//======================================================================================================================
				/*se crea una tabla con respecto a la tabla BASE_SALDOS_ACTUALIZADOS_FACTURACION_CORTE*/	
				base.EjecutarQuery("exec Elimina_BASE_DE_DATOS_CLIENTES_FINAL");
				base.EjecutarQuery("exec Generar_BASE_DE_DATOS_CLIENTES_FINAL");
//======================================================================================================================				
				LabelProcesamiento.setText("Generando Cruces");
				PanelProcesamiento.update(PanelProcesamiento.getGraphics());//para actualizar el panel y poder mostrar el proceso
				/*se actualiza la tabla facturacion con respecto a la tabla BASE _DE_DATOS_CLIENTES_FINAL*/
				base.EjecutarQuery("exec Limpia_Tablas");
				base.EjecutarQuery("exec Actualiza_Facturacion");		
//======================================================================================================================
				/*se crea una tabla cobrconv con respecto a las tablas Empresa,Facturacion,Clientes*/					
				base.EjecutarQuery("exec Depura_Cobrconv");
				base.EjecutarQuery("exec Actualiza_Cobrconv");
//======================================================================================================================				
				LabelProcesamiento.setText("Actualizando Cobrconv");
				PanelProcesamiento.update(PanelProcesamiento.getGraphics());//para actualizar el panel y poder mostrar el proceso
				/*ACTUALIZA LA TABLA COBRCONV CON EL LARGO CORRECTO PARA CADA CAMPO*/					
				base.EjecutarQuery("exec Actualiza_Campos_Cobrconv");
//======================================================================================================================	
				/* quita los campos en null */
				base.EjecutarQuery("exec Elimina_Nulls");
//======================================================================================================================			
				//ACTUALIZA EL LARGO DE CONVENIO CON CERO A LA IZQUIERDA PARA EL PROCESO DE ENVIO DE CORREOS
				base.EjecutarQuery("exec Largo_Convenio");
//======================================================================================================================					
				function.escribirTxtCobrconv(direccion,PanelProcesamiento, LabelProcesamiento);	//funcion para generar el archivo en txt actualizado de COBRCONV
				
		}	 
	
		catch(Exception ex){
				System.out.println("ERROR ACTUALIZANDO LOS ARCHIVOS");
				ex.printStackTrace();
		}
	}
}


