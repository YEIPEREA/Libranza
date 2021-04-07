package CapaNegocio;

import java.sql.SQLException;
import java.text.DecimalFormatSymbols;

import CargaDatos.conexionBD;

public class Proceso {

	conexionBD close = new conexionBD();

	// Proceso DE DATOS
	/*
	 * FUNCION ENCARGADA DE DIVIDIR DATOS DEL ARCHIVO DE lIBRANZA POR "|" ARMAR
	 * DE NUEVO EL QUERY BAJO LA FUNCION STRINGFORMAT AGREGA ESPACIO AL FINAL DE
	 * CADA LINEA DE TEXTO SI NO LA HAY.
	 */
/**
 * añade a la BD los resgistro del archivo txt 
 * 
 * @param cadena recibe la fila del archivo txt
 * @param base conexion a la base de datos
 * @param n numero de la fila
 * @param Tem 1 Empresa 2 Facturacion, 3 clientes
 * @throws SQLException
 */
	public void ProcesoCobrconv(String cadena, conexionBD base, long n,String Tem) throws SQLException {// cadena recibe la primera línea del archivo txt

		String BD1 = "insert into EMPRESAS(ITEM,CONVENIO,NIT,NOMBRE_EMPRESA,DIRECCION,TELEFONO,CIUDAD,DEPARTAMENTO,FECHA_DE_PAGO,Orden)values('%s','%s','%s','%s','%s','%s','%s','%s','%s','%s')";
		String BD2 = "insert into FACTURACION(ITEM,Vr_Cuota_Mensual,Vr_en_Mora,Vr_Total_a_Pagar,Vr_Intereses_en_Mora,CONVENIO,Orden)values('%s','%s','%s','%s','%s','%s','%s')";
		String BD3 = "insert into CLIENTES(ITEM,Tipo_de_Documento,Numero_de_Documento,Nombre,No_Obligación,Código_Oficina,Nombre_Oficina,Vr_Cuota_Mensual,Vr_en_Mora,Vr_Total_a_Pagar,Vr_Intereses_en_Mora,Cuotas_Pactadas,Cuotas_a_Pagar,CONVENIO,Orden)values('%s','%s','%s','%s','%s','%s','%s','%s','%s','%s','%s','%s','%s','%s','%s')";
		String x;
		String[] a;

		cadena = cadena.replace(",", ".");// se cambie el | por el ; debido a error de JAVA al ejecutar
		if (cadena.endsWith("\\|")) {// pregunta si en el ultimo dato hay ; y agrega un espacio
			cadena = cadena + " ";
		}
		a = cadena.split("\\|");// separa los datos por ; y lo agrgega a un vector
		// System.out.println(a[1].toString().trim());
		switch (a[0]) {
		case "1":
			x = String.format(BD1, a[0].toString().trim(), a[1].toString().trim(), a[2].toString().trim(), a[3].toString().trim(),
					a[4].toString().trim(), a[5].toString().trim(), a[6].toString().trim(), a[7].toString().trim(), a[8].toString().trim(), n);
			base.EjecutarQuery(x);
			break;

		case "2":

			x = String.format(BD2, a[0].toString().trim(), a[1].toString().trim().trim(), a[2].toString().trim(), a[3].toString().trim(), a[4].toString().trim(), Tem, n);
			base.EjecutarQuery(x);
			break;

		case "3":
			x = String.format(BD3, a[0].toString().trim(), a[1].toString().trim(), a[2].toString().trim(), a[3].toString().trim(),a[4].toString().trim(), a[5].toString().trim(), a[6].toString().trim(), a[7].toString().trim(), a[8]
							.toString().trim(), a[9].toString().trim(), a[10].toString().trim(), a[11].toString().trim(), a[12].toString().trim(), Tem, n);
			base.EjecutarQuery(x);
			break;

		default:
			System.out.println("Dato Invalido");
			break;
		}
	}

	// =======================================================================================================
/**
 * añade a la BD los resgistro del archivo txt 
 * 
 * @param cadena  recibe la fila del archivo txt
 * @param base conexion a la base de datos
 * @param n numero de la fila
 * @throws SQLException
 */
	public void ProcesoBaseDesvinculados(String cadena, conexionBD base, long n)throws SQLException {// cadena recibe la primera línea del archivo txt

		String BD1 = "insert into BASE_DESVINCULADO(CONVENIO,ENTE_CONVENIO,mo_numero_de_banco,mo_nombre_cliente,Número_de_documento,mo_monto_desembolso,Saldo_Capital,FECHA_DESEMBOLSO,Días_de_mora,Estado_obligacion,id)values('%s','%s','%s','%s','%s','%s','%s','%s','%s','%s','%s')";
		String x;
		String[] a;

		boolean Vacios =cadena.contains("||");
		
		if (Vacios==true) {// pregunta si existe ||  y agrega un espacio entre ellos
			cadena = cadena.replace("||", "|"+"  "+"|");
		}
		a = cadena.split("\\|");// separa los datos por ; y lo agrgega a un vector
		x = String.format(BD1, a[0].toString().trim(), a[1].toString().trim(),a[2].toString().trim(), a[3].toString().trim(), a[4].toString()
						.trim(), a[5].toString().trim(),a[6].toString().trim(), a[7].toString().trim(), a[8].toString().trim(), a[9].toString().trim(), n);
		base.EjecutarQuery(x);

	}

	// =======================================================================================================
/**
 *  añade a la BD los resgistro del archivo txt 
 * 
 * @param cadena recibe la fila del archivo txt
 * @param base conexion a la base de datos
 * @throws SQLException
 */
	public void ProcesoBancolombia(String cadena, conexionBD base)throws SQLException {// cadena recibe la línea del archivo txt

		String BD1 = "insert into BANCOLOMBIA values('%s','%s','%s','%s','%s','%s','%s','%s','%s','%s')";
		String x;
		String[] a;
		boolean Vacios =cadena.contains("||");
		
		DecimalFormatSymbols simb =new DecimalFormatSymbols();
		//simb.setDecimalSeparator(',');//Separador de Decimal
		//simb.setGroupingSeparator('.');//Separador de Millis
		//DecimalFormat def= new DecimalFormat("#,###.##",simb);
		
		cadena = cadena.replace(",", "").replace(",",".");
				if (cadena.endsWith("|")) {// pregunta si en el ultimo dato hay | y agrega un espacio
					cadena = cadena + " ";
				}
				if (Vacios==true) {// pregunta si existe ||  y agrega un espacio entre ellos
					cadena = cadena.replace("||", "|"+"  "+"|");
				}
	
		a = cadena.split("\\|");// separa los datos por ; y lo agrgega a un vector
		//System.out.println(def.format(a[6].toString().trim()));
		x = String.format(BD1, a[0].toString().trim(), a[1].toString().trim(),a[2].toString().trim(), a[3].toString().trim(), a[4].toString().trim(), a[5].toString().trim(),a[6].toString().trim(), a[7].toString().trim(), a[8].toString().trim(),a[9].toString().trim());
		base.EjecutarQuery(x);

	}

	// =======================================================================================================
/**
 *  añade a la BD los resgistro del archivo txt 
 *  
 * @param cadena recibe la fila del archivo txt
 * @param base  conexion a la base de datos
 * @param n numero de la fila
 * @throws SQLException
 */
	public void ProcesoCagmaest(String cadena, conexionBD base, int n)	throws SQLException {// cadena recibe la línea del archivo txt

		// String BD1
		// ="insert into Cagmaest(mo_fecha_de_proceso,mo_producto,mo_tipo_de_producto,mo_moneda,mo_numero_de_operacion,mo_numero_de_banco,mo_numero_migrada,mo_cliente,mo_nombre_cliente,mo_cupo_credito,mo_oficina,mo_nombre_oficina,mo_dias_causados,mo_monto,mo_monto_desembolso,mo_tasa,mo_tasa_efectiva,mo_plazo_total,mo_modalidad_cobro_int,mo_fecha_inicio_op,mo_fecha_ven_op,mo_dias_vencido_op,mo_fecha_fin_min_div_ven,mo_reestructurada_plano,mo_fecha_ult_reest,mo_num_reestructuraciones,mo_num_cuotas_pagadas,mo_num_cuotas_pagadas_reest,mo_destino_credito,mo_clase_cartera,mo_ciudad,mo_fecha_prox_vencimiento,mo_saldo_cuota_prox_venc,mo_saldo_capital_vigente,mo_saldo_capital_vencido,mo_saldo_interes_vigente,mo_saldo_interes_vencido,mo_saldo_interes_contingente,mo_saldo_mora_vigente,mo_saldo_mora_contingente,mo_saldo_seguro_vida_vigente,mo_saldo_seguro_vida_vencido,mo_saldo_otros_vigente,mo_saldo_otros_vencidos,mo_estado_obligacion,mo_calificacion_obligacion,mo_frecuencia_pago_int,mo_frecuencia_pago_cap,mo_edad_vencimiento_cartera,mo_fecha_ult_pago,mo_valor_ult_pago,mo_fecha_ult_pago_cap,mo_valor_ult_pago_cap,mo_valor_cuota_tabla,mo_numero_cuotas_pactadas,mo_numero_cuotas_vencidas,mo_clase_garantia,mo_tipo_garantia,mo_descripcion_tipo_garantia,mo_fecha_castigo,mo_fecha_pago_pasiva,mo_estado_pasiva,mo_fecha_embarque,mo_fecha_ini_caus,mo_tipo_tasa,mo_tasa_referencial,mo_signo,mo_factor,mo_tipo_identificacion,mo_numero_identificacion,mo_provision_cap,mo_provision_int,mo_provision_cxc,mo_cuenta_asociada,mo_forma_pago,mo_tipo_tabla,mo_periodo_gracia_cap,mo_periodo_gracia_int,mo_estado_cobranza,mo_descripcion_estado_cobranza,mo_tasa_representativa_mercado,mo_reajustable,mo_descripcion_reajustable,mo_periodo_reajuste,mo_fecha_prox_reajuste,mo_fecha_ult_proceso,mo_tipo_soc,mo_tipo_puntos,mo_cobertura_garantia,mo_des_tipo_bien,mo_tramite,mo_defecto_garantia,mo_modalidad,mo_clausula,mo_naturaleza_linea,mo_tiene_seg_vida,mo_tiene_seg_vehiculo,mo_tiene_seg_todor_maq,mo_tiene_seg_rotura_maq,mo_tiene_seg_vivienda,mo_tiene_seg_extraprima,mo_tiene_incentivo,mo_tipo_banca,mo_nombre_codeudor,mo_ced_ruc_codeudor,mo_zona,mo_regional,mo_capitaliza,mo_tipo_productor,mo_mercado_obj,mo_aprobador,mo_llave_redescuento,mo_condonacion_intereses,mo_condonacion_capital,mo_provision_defecto,mo_margen_redescuento,mo_codigo_sector,mo_dias_base,mo_provisiona_cap,mo_provisiona_int,mo_provisiona_cxc,mo_norma_legal,mo_ind_aprob,mo_cap_diferido,mo_int_imo_diferido,mo_tramite_solicitud,mo_banco_pasiva,mo_truta,mo_fabrica,mo_callcenter,mo_apr_fabrica,mo_monto_solicitado,mo_tipo_programa,mo_asociativo,mo_administrador_cca,mo_nombre_adm_cca,mo_tipo_incentivo,mo_saldo_int_contin_vigente,mo_saldo_int_contin_vencido,mo_saldo_otros_gastos_judic,mo_saldo_otros_comisiones,mo_saldo_otros_iva,mo_saldo_otros_todo_riesgo,mo_saldo_otros_segromaqui,mo_valor_quedar_al_dia,mo_valor_cancelacion_total,mo_valor_corr_mon_capital,mo_valor_corr_mon_interes,mo_ciudad_inversion,mo_aprobo_desembolso,mo_tiene_seg_cosecha,mo_marca_cobro_jur,mo_categoria_asesor,mo_conv_garc,mo_entidad_convenio,mo_tipo_alivio,mo_Tipo_Arreglo,mo_tipo_operacion_co,mo_tipo_micro,mo_tipo_seg,mo_valor_tipo_seg,Mo_Tipo_cartera_Svia,Mo_Programa_FAG_Svia,NUEVO_CAMPO,ids)values('%s','%s','%s','%s','%s','%s','%s','%s','%s','%s','%s','%s','%s','%s','%s','%s','%s','%s','%s','%s','%s','%s','%s','%s','%s','%s','%s','%s','%s','%s','%s','%s','%s','%s','%s','%s','%s','%s','%s','%s','%s','%s','%s','%s','%s','%s','%s','%s','%s','%s','%s','%s','%s','%s','%s','%s','%s','%s','%s','%s','%s','%s','%s','%s','%s','%s','%s','%s','%s','%s','%s','%s','%s','%s','%s','%s','%s','%s','%s','%s','%s','%s','%s','%s','%s','%s','%s','%s','%s','%s','%s','%s','%s','%s','%s','%s','%s','%s','%s','%s','%s','%s','%s','%s','%s','%s','%s','%s','%s','%s','%s','%s','%s','%s','%s','%s','%s','%s','%s','%s','%s','%s','%s','%s','%s','%s','%s','%s','%s','%s','%s','%s','%s','%s','%s','%s','%s','%s','%s','%s','%s','%s','%s','%s','%s','%s','%s','%s','%s','%s','%s','%s','%s','%s','%s','%s','%s','%s','%s','%s','%s','%s','%s','%s','%s')";
		//String BD1 = "insert into Cagmaest values('%s','%s','%s','%s','%s','%s','%s','%s','%s','%s','%s','%s','%s','%s','%s','%s','%s','%s','%s','%s','%s','%s','%s','%s','%s','%s','%s','%s','%s','%s','%s','%s','%s','%s','%s','%s','%s','%s','%s','%s','%s','%s','%s','%s','%s','%s','%s','%s','%s','%s','%s','%s','%s','%s','%s','%s','%s','%s','%s','%s','%s','%s','%s','%s','%s','%s','%s','%s','%s','%s','%s','%s','%s','%s','%s','%s','%s','%s','%s','%s','%s','%s','%s','%s','%s','%s','%s','%s','%s','%s','%s','%s','%s','%s','%s','%s','%s','%s','%s','%s','%s','%s','%s','%s','%s','%s','%s','%s','%s','%s','%s','%s','%s','%s','%s','%s','%s','%s','%s','%s','%s','%s','%s','%s','%s','%s','%s','%s','%s','%s','%s','%s','%s','%s','%s','%s','%s','%s','%s','%s','%s','%s','%s','%s','%s','%s','%s','%s','%s','%s','%s','%s','%s','%s','%s','%s','%s','%s','%s','%s','%s','%s','%s','%s','%s')";
		String BD1 = "insert into Cagmaest values('%s','%s','%s','%s','%s','%s','%s','%s','%s','%s','%s','%s','%s','%s','%s','%s','%s','%s','%s')";

		String x;
		String[] b;
		int contador;

		cadena = cadena.replace("'", " ");
		b = cadena.split("\\|");// separa los datos por ; y lo agrgega a un vector
		contador = b.length;// cuenta cuantos registros tiene la cadna
			
		boolean Vacios =cadena.contains("||");
		if (Vacios==true) {// pregunta si existe ||  y agrega un espacio entre ellos
			cadena = cadena.replace("||", "|"+"  "+"|");
		}
		
		if (contador<164){//pregunta si la fila tiene menos de 164 columnas de ser cierto añande un "| " al final de la fila
			for (int j=contador;j<=164;j++){
				cadena=cadena+"| ";
			}
		}
		b = cadena.split("\\|");// separa los datos por ; y lo agrgega a un vector
			//x=String.format(BD1,b[0].toString().trim(),b[1].toString().trim(),b[2].toString().trim(),b[3].toString().trim(),b[4].toString().trim(),b[5].toString().trim(),b[6].toString().trim(),b[7].toString().trim(),b[8].toString().trim(),b[9].toString().trim(),b[10].toString().trim(),b[11].toString().trim(),b[12].toString().trim(),b[13].toString().trim(),b[14].toString().trim(),b[15].toString().trim(),b[16].toString().trim(),b[17].toString().trim(),b[18].toString().trim(),b[19].toString().trim(),b[20].toString().trim(),b[21].toString().trim(),b[22].toString().trim(),b[23].toString().trim(),b[24].toString().trim(),b[25].toString().trim(),b[26].toString().trim(),b[27].toString().trim(),b[28].toString().trim(),b[29].toString().trim(),b[30].toString().trim(),b[31].toString().trim(),b[32].toString().trim(),b[33].toString().trim(),b[34].toString().trim(),b[35].toString().trim(),b[36].toString().trim(),b[37].toString().trim(),b[38].toString().trim(),b[39].toString().trim(),b[40].toString().trim(),b[41].toString().trim(),b[42].toString().trim(),b[43].toString().trim(),b[44].toString().trim(),b[45].toString().trim(),b[46].toString().trim(),b[47].toString().trim(),b[48].toString().trim(),b[49].toString().trim(),b[50].toString().trim(),b[51].toString().trim(),b[52].toString().trim(),b[53].toString().trim(),b[54].toString().trim(),b[55].toString().trim(),b[56].toString().trim(),b[57].toString().trim(),b[58].toString().trim(),b[59].toString().trim(),b[60].toString().trim(),b[61].toString().trim(),b[62].toString().trim(),b[63].toString().trim(),b[64].toString().trim(),b[65].toString().trim(),b[66].toString().trim(),b[67].toString().trim(),b[68].toString().trim(),b[69].toString().trim(),b[70].toString().trim(),b[71].toString().trim(),b[72].toString().trim(),b[73].toString().trim(),b[74].toString().trim(),b[75].toString().trim(),b[76].toString().trim(),b[77].toString().trim(),b[78].toString().trim(),b[79].toString().trim(),b[80].toString().trim(),b[81].toString().trim(),b[82].toString().trim(),b[83].toString().trim(),b[84].toString().trim(),b[85].toString().trim(),b[86].toString().trim(),b[87].toString().trim(),b[88].toString().trim(),b[89].toString().trim(),b[90].toString().trim(),b[91].toString().trim(),b[92].toString().trim(),b[93].toString().trim(),b[94].toString().trim(),b[95].toString().trim(),b[96].toString().trim(),b[97].toString().trim(),b[98].toString().trim(),b[99].toString().trim(),b[100].toString().trim(),b[101].toString().trim(),b[102].toString().trim(),b[103].toString().trim(),b[104].toString().trim(),b[105].toString().trim(),b[106].toString().trim(),b[107].toString().trim(),b[108].toString().trim(),b[109].toString().trim(),b[110].toString().trim(),b[111].toString().trim(),b[112].toString().trim(),b[113].toString().trim(),b[114].toString().trim(),b[115].toString().trim(),b[116].toString().trim(),b[117].toString().trim(),b[118].toString().trim(),b[119].toString().trim(),b[120].toString().trim(),b[121].toString().trim(),b[122].toString().trim(),b[123].toString().trim(),b[124].toString().trim(),b[125].toString().trim(),b[126].toString().trim(),b[127].toString().trim(),b[128].toString().trim(),b[129].toString().trim(),b[130].toString().trim(),b[131].toString().trim(),b[132].toString().trim(),b[133].toString().trim(),b[134].toString().trim(),b[135].toString().trim(),b[136].toString().trim(),b[137].toString().trim(),b[138].toString().trim(),b[139].toString().trim(),b[140].toString().trim(),b[141].toString().trim(),b[142].toString().trim(),b[143].toString().trim(),b[144].toString().trim(),b[145].toString().trim(),b[146].toString().trim(),b[147].toString().trim(),b[148].toString().trim(),b[149].toString().trim(),b[150].toString().trim(),b[151].toString().trim(),b[152].toString().trim(),b[153].toString().trim(),b[154].toString().trim(),b[155].toString().trim(),b[156].toString().trim(),b[157].toString().trim(),b[158].toString().trim(),b[159].toString().trim(),b[160].toString().trim(),b[161].toString().trim(),b[162].toString().trim(),b[163].toString().trim(),n);
			x=String.format(BD1,b[5].toString().trim(),b[21].toString().trim(),b[31].toString().trim(),b[32].toString().trim(),b[33].toString().trim(),b[34].toString().trim(),b[35].toString().trim(),b[36].toString().trim(),b[37].toString().trim(),b[39].toString().trim(),b[40].toString().trim(),b[41].toString().trim(),b[42].toString().trim(),b[43].toString().trim(),b[44].toString().trim(),b[49].toString().trim(),b[50].toString().trim(),b[55].toString().trim(),n);
			base.EjecutarQuery(x);
	}
	
	//====================================================================================================================
	/**
 *  añade a la BD los resgistro del archivo txt 
 *  
 * @param cadena recibe la fila del archivo txt
 * @param base  conexion a la base de datos
 * @param n numero de la fila
 * @throws SQLException
	 */
	public void ProcesoParametrizado(String cadena, conexionBD base, long n)throws SQLException {// cadena recibe la línea del archivo txt

		String BD1 = "insert into Parametrizados values('%s','%s','%s','%s','%s','%s','%s','%s','%s','%s','%s','%s','%s','%s','%s','%s','%s','%s','%s','%s','%s','%s','%s','%s','%s','%s','%s','%s','%s','%s','%s','%s','%s','%s','%s','%s','%s','%s','%s','%s','%s','%s','%s','%s')";
		String x;
		String[] b;
	
		/*char characterValue = '"';
		int asciiValue = (int) characterValue;*/ //convierte a ascii
		boolean Vacios =cadena.contains("||");
		
		if (Vacios==true) {// pregunta si existe ||  y agrega un espacio entre ellos
			cadena = cadena.replace("||", "|"+"  "+"|");
		}
		
		cadena = cadena.replace("'", " ");

		b = cadena.split("\\|");// separa los datos por | y lo agrgega a un vector

		x = String.format(BD1,  b[0].toString().trim(), b[1].toString().trim(), b[2].toString().trim(), b[3].toString().trim(),b[4].toString().trim(), b[5].toString().trim(), b[6].toString().trim(), b[7].toString().trim(),
									b[8].toString().trim(), b[9].toString().trim(), b[10].toString().trim(), b[11].toString().trim(), b[12].toString().trim(), b[13].toString().trim(), b[14].toString().trim(), b[15].toString().trim(), 
									b[16].toString().trim(), b[17].toString().trim(), b[18].toString().trim(), b[19].toString().trim(), b[20].toString().trim(), b[21].toString().trim(), b[22].toString().trim(), b[23].toString().trim(),
									b[24].toString().trim(), b[25].toString().trim(), b[26].toString().trim(), b[27].toString().trim(), b[28].toString().trim(), b[29].toString().trim(), b[30].toString().trim(), b[31].toString().trim(), 
									b[32].toString().trim(), b[33].toString().trim(), b[34].toString().trim(), b[35].toString().trim(), b[36].toString().trim(), b[37].toString().trim(), b[38].toString().trim(), b[39].toString().trim(), 
									b[40].toString().trim(), b[41].toString().trim(), b[42].toString().trim(),  n);
			base.EjecutarQuery(x);
	}

//****************************************************************************************************************************
//														PAGOS
//****************************************************************************************************************************
	/**
	 *   añade a la BD los resgistro del archivo txt 
	 * 
	 * @param cadena  recibe la fila del archivo txt
	 * @param base conexion a la base de datos
	 * @param L posicion en la tabla de la BD
	 * @param ITEM  1 Empresa 2 Facturacion, 3 clientes
	 * @throws SQLException
	 */
	public void ProcesoCobrconv2(String cadena, conexionBD base, int L, String ITEM) throws SQLException {// cadena recibe la primera línea
												// del archivo txt
		String BD1 = "insert into P_Cobrconv"+L+" values('%s','%s','%s','%s','%s','%s','%s','%s','%s','%s','%s','%s','%s','%s')";
		String x;
		String[] a;
		int contador;
		
		cadena = cadena.replace(",", ".");
			if (cadena.endsWith("|")) {// pregunta si en el ultimo dato hay | y agrega un espacio
				cadena = cadena + " ";
			}
		a = cadena.split("\\|");// separa los datos por ; y lo agrgega a un vector
		contador = a.length;// cuenta cuantos registros tiene la cadena
		
		switch(contador){
			case 13:
				x = String.format(BD1, a[0].toString().trim(), a[1].toString().trim(), a[2].toString().trim(), a[3].toString().trim(),a[4].toString().trim(), a[5].toString().trim(), a[6].toString().trim(), a[7].toString().trim(), a[8].toString().trim(), a[9].toString().trim(), a[10].toString().trim(), a[11].toString().trim(), a[12].toString().trim(),ITEM);
				base.EjecutarQuery(x);
				break;
			case 5:
				long valor2= Math.round(Double.parseDouble(a[1].toString().trim()));
				x = String.format(BD1, a[0].toString().trim(), valor2,a[2].toString().trim(), a[3].toString().trim(),a[4].toString().trim(),"''","''","''","''","''","''","''","''",ITEM);
				base.EjecutarQuery(x);
				break;
			case 9:
				x = String.format(BD1, a[0].toString().trim(), a[1].toString().trim(), a[2].toString().trim(), a[3].toString().trim(),a[4].toString().trim(), a[5].toString().trim(), a[6].toString().trim(), a[7].toString().trim(), a[8].toString().trim(),"''","''","''","''",ITEM);
				base.EjecutarQuery(x);
				break;
				
		}
	}
	
	//====================================================================================================================
	/**
	 *   añade a la BD los resgistro del archivo txt 
	 * 
	 * @param cadena adena  recibe la fila del archivo txt
	 * @param base conexion a la base de datos
	 * @param L  posicion en la tabla de la BD
	 * @param ITEM  1 Empresa 2 Facturacion, 3 clientes
	 * @throws SQLException
	 */
	public void ProcesoCobrconv0(String cadena, conexionBD base, int L, String ITEM) throws SQLException {// cadena recibe la primera línea
												// del archivo txt
		String BD1 = "insert into Cobrconv values('%s','%s','%s','%s','%s','%s','%s','%s','%s','%s','%s','%s','%s','%s','%s')";
		String x;
		String[] a;
		int contador;
		
		cadena = cadena.replace(",", ".");
			if (cadena.endsWith("|")) {// pregunta si en el ultimo dato hay | y agrega un espacio
				cadena = cadena + " ";
			}
		a = cadena.split("\\|");// separa los datos por ; y lo agrgega a un vector
		contador = a.length;// cuenta cuantos registros tiene la cadena
		
		switch(contador){
			case 13:
				x = String.format(BD1, a[0].toString().trim(), a[1].toString().trim(), a[2].toString().trim(), a[3].toString().trim(),a[4].toString().trim(), a[5].toString().trim(), a[6].toString().trim(), a[7].toString().trim(), a[8].toString().trim(), a[9].toString().trim(), a[10].toString().trim(), a[11].toString().trim(), a[12].toString().trim(),ITEM,L);
				base.EjecutarQuery(x);
				break;
			case 5:
				long valor2= Math.round(Double.parseDouble(a[1].toString().trim()));
				x = String.format(BD1, a[0].toString().trim(),valor2,a[2].toString().trim(), a[3].toString().trim(),a[4].toString().trim(),"","","","","","","","",ITEM,L);
				base.EjecutarQuery(x);
				break;
			case 9:
				x = String.format(BD1, a[0].toString().trim(), a[1].toString().trim(), a[2].toString().trim(), a[3].toString().trim(),a[4].toString().trim(), a[5].toString().trim(), a[6].toString().trim(), a[7].toString().trim(), a[8].toString().trim(),"","","","",ITEM,L);
				base.EjecutarQuery(x);
				break;
				
		}
	}
	
	//====================================================================================================================
	/**
	 *   añade a la BD los resgistro del archivo txt 
	 * 
	 * @param cadena   recibe la fila del archivo txt
	 * @param base conexion a la base de datos
	 * @param L  posicion en la tabla de la BD
	 * @param  validar2 pòr defecto el dtr concepto es PAGLIBRANZ
	 */
	public void ProcesoCatradia(String cadena, conexionBD base, int L,String validar2) throws SQLException {// cadena recibe la primera línea del archivo txt

			String BD1 = "insert into Catradia"+L+" values('%s','%s','%s','%s','%s','%s','%s','%s','%s','%s','%s','%s','%s','%s')";
			String x;
			String[] a;
			
			cadena = cadena.replace(",", ".");
			a = cadena.split("\\|");// separa los datos por ; y lo agrgega a un vector
		if(L>1){//catradias consolidadoes meses anteriores
			if(a[5].contains("PAGLIBRANZ")==true || a[5].contains(validar2)==true ){//filtrado poe dtr_concepto por PAGLIBRANZ y el valor de Validar2 
				//System.out.println(a[5]);
				x = String.format(BD1, "","","","",a[0].toString().trim(), a[1].toString().trim(), a[2].toString().trim(), a[3].toString().trim(),a[4].toString().trim(), a[5].toString().trim(),  Double.parseDouble(  a[6].toString().trim())  , a[7].toString().trim(), a[8].toString().trim(),"");
				base.EjecutarQuery(x);
			}
		}
			else{// diario sin filtrar
				x = String.format(BD1, "","","","",a[0].toString().trim(), a[1].toString().trim(), a[2].toString().trim(), a[3].toString().trim(),a[4].toString().trim(), a[5].toString().trim(),  Double.parseDouble(  a[6].toString().trim())  , a[7].toString().trim(), a[8].toString().trim(),"");
				base.EjecutarQuery(x);
	}
}

	//====================================================================================================================
	/**
	 *  añade a la BD los resgistro del archivo txt 
	 * 
	 * @param cadena recibe la fila del archivo txt
	 * @param base conexion a la base de datos
	 * @param n posicion de la fila
	 * @param L posicion en la tabla de la BD
	 * @throws SQLException
	 */
	public void ProcesoCagmaestP(String cadena, conexionBD base, long n,int L)	throws SQLException {// cadena recibe la línea del archivo txt

		// String BD1
		// ="insert into Cagmaest(mo_fecha_de_proceso,mo_producto,mo_tipo_de_producto,mo_moneda,mo_numero_de_operacion,mo_numero_de_banco,mo_numero_migrada,mo_cliente,mo_nombre_cliente,mo_cupo_credito,mo_oficina,mo_nombre_oficina,mo_dias_causados,mo_monto,mo_monto_desembolso,mo_tasa,mo_tasa_efectiva,mo_plazo_total,mo_modalidad_cobro_int,mo_fecha_inicio_op,mo_fecha_ven_op,mo_dias_vencido_op,mo_fecha_fin_min_div_ven,mo_reestructurada_plano,mo_fecha_ult_reest,mo_num_reestructuraciones,mo_num_cuotas_pagadas,mo_num_cuotas_pagadas_reest,mo_destino_credito,mo_clase_cartera,mo_ciudad,mo_fecha_prox_vencimiento,mo_saldo_cuota_prox_venc,mo_saldo_capital_vigente,mo_saldo_capital_vencido,mo_saldo_interes_vigente,mo_saldo_interes_vencido,mo_saldo_interes_contingente,mo_saldo_mora_vigente,mo_saldo_mora_contingente,mo_saldo_seguro_vida_vigente,mo_saldo_seguro_vida_vencido,mo_saldo_otros_vigente,mo_saldo_otros_vencidos,mo_estado_obligacion,mo_calificacion_obligacion,mo_frecuencia_pago_int,mo_frecuencia_pago_cap,mo_edad_vencimiento_cartera,mo_fecha_ult_pago,mo_valor_ult_pago,mo_fecha_ult_pago_cap,mo_valor_ult_pago_cap,mo_valor_cuota_tabla,mo_numero_cuotas_pactadas,mo_numero_cuotas_vencidas,mo_clase_garantia,mo_tipo_garantia,mo_descripcion_tipo_garantia,mo_fecha_castigo,mo_fecha_pago_pasiva,mo_estado_pasiva,mo_fecha_embarque,mo_fecha_ini_caus,mo_tipo_tasa,mo_tasa_referencial,mo_signo,mo_factor,mo_tipo_identificacion,mo_numero_identificacion,mo_provision_cap,mo_provision_int,mo_provision_cxc,mo_cuenta_asociada,mo_forma_pago,mo_tipo_tabla,mo_periodo_gracia_cap,mo_periodo_gracia_int,mo_estado_cobranza,mo_descripcion_estado_cobranza,mo_tasa_representativa_mercado,mo_reajustable,mo_descripcion_reajustable,mo_periodo_reajuste,mo_fecha_prox_reajuste,mo_fecha_ult_proceso,mo_tipo_soc,mo_tipo_puntos,mo_cobertura_garantia,mo_des_tipo_bien,mo_tramite,mo_defecto_garantia,mo_modalidad,mo_clausula,mo_naturaleza_linea,mo_tiene_seg_vida,mo_tiene_seg_vehiculo,mo_tiene_seg_todor_maq,mo_tiene_seg_rotura_maq,mo_tiene_seg_vivienda,mo_tiene_seg_extraprima,mo_tiene_incentivo,mo_tipo_banca,mo_nombre_codeudor,mo_ced_ruc_codeudor,mo_zona,mo_regional,mo_capitaliza,mo_tipo_productor,mo_mercado_obj,mo_aprobador,mo_llave_redescuento,mo_condonacion_intereses,mo_condonacion_capital,mo_provision_defecto,mo_margen_redescuento,mo_codigo_sector,mo_dias_base,mo_provisiona_cap,mo_provisiona_int,mo_provisiona_cxc,mo_norma_legal,mo_ind_aprob,mo_cap_diferido,mo_int_imo_diferido,mo_tramite_solicitud,mo_banco_pasiva,mo_truta,mo_fabrica,mo_callcenter,mo_apr_fabrica,mo_monto_solicitado,mo_tipo_programa,mo_asociativo,mo_administrador_cca,mo_nombre_adm_cca,mo_tipo_incentivo,mo_saldo_int_contin_vigente,mo_saldo_int_contin_vencido,mo_saldo_otros_gastos_judic,mo_saldo_otros_comisiones,mo_saldo_otros_iva,mo_saldo_otros_todo_riesgo,mo_saldo_otros_segromaqui,mo_valor_quedar_al_dia,mo_valor_cancelacion_total,mo_valor_corr_mon_capital,mo_valor_corr_mon_interes,mo_ciudad_inversion,mo_aprobo_desembolso,mo_tiene_seg_cosecha,mo_marca_cobro_jur,mo_categoria_asesor,mo_conv_garc,mo_entidad_convenio,mo_tipo_alivio,mo_Tipo_Arreglo,mo_tipo_operacion_co,mo_tipo_micro,mo_tipo_seg,mo_valor_tipo_seg,Mo_Tipo_cartera_Svia,Mo_Programa_FAG_Svia,NUEVO_CAMPO,ids)values('%s','%s','%s','%s','%s','%s','%s','%s','%s','%s','%s','%s','%s','%s','%s','%s','%s','%s','%s','%s','%s','%s','%s','%s','%s','%s','%s','%s','%s','%s','%s','%s','%s','%s','%s','%s','%s','%s','%s','%s','%s','%s','%s','%s','%s','%s','%s','%s','%s','%s','%s','%s','%s','%s','%s','%s','%s','%s','%s','%s','%s','%s','%s','%s','%s','%s','%s','%s','%s','%s','%s','%s','%s','%s','%s','%s','%s','%s','%s','%s','%s','%s','%s','%s','%s','%s','%s','%s','%s','%s','%s','%s','%s','%s','%s','%s','%s','%s','%s','%s','%s','%s','%s','%s','%s','%s','%s','%s','%s','%s','%s','%s','%s','%s','%s','%s','%s','%s','%s','%s','%s','%s','%s','%s','%s','%s','%s','%s','%s','%s','%s','%s','%s','%s','%s','%s','%s','%s','%s','%s','%s','%s','%s','%s','%s','%s','%s','%s','%s','%s','%s','%s','%s','%s','%s','%s','%s','%s','%s','%s','%s','%s','%s','%s','%s')";
		String BD1 = "insert into CagmaestP"+L+" values('%s','%s','%s','%s')";
		String x;
		String[] b;
		String[] a;
		int contador=0;
	
		boolean Vacios =cadena.contains("||");
			if (Vacios==true) {// pregunta si existe ||  y agrega un espacio entre ellos
				cadena = cadena.replace("||", "|"+"  "+"|");
			}
		
		cadena = cadena.replace("'", " ");
		a = cadena.split("\\|");// separa los datos por ; y lo agrgega a un vector
		contador = a.length;// cuenta cuantos registros tiene la cadna
		if (contador<164){
			for (int j=contador;j<=164;j++){
				cadena=cadena+"| ";
			}
		}
		b = cadena.split("\\|");// separa los datos por ; y lo agrgega a un vector
		if(b[154].toString().trim().isEmpty()==false && b[154].toString().contains("D")==false){//si el convenio se encuentra vacio o cntiene una D no lo carga en sql
			x=String.format(	BD1,b[5].toString().trim(),	 b[154].toString() , b[8].toString().trim(),b[69].toString().trim());// obligacion,convenio,nombre,cc
			base.EjecutarQuery(x);		
		}
	}
	
	//====================================================================================================================
 /**
 *  añade a la BD los resgistro del archivo txt 
 * 
 * @param cadena recibe la fila del archivo txt
 * @param base conexion a la base de datos
 * @throws SQLException
 */
	public void ProcesoParametrizadoP(String cadena, conexionBD base, long n)throws SQLException {// cadena recibe la línea del archivo txt

		String BD1 = "insert into ParametrizadosP values('%s','%s','%s','%s','%s','%s','%s','%s','%s','%s','%s','%s','%s','%s','%s','%s','%s','%s','%s','%s','%s','%s','%s','%s','%s','%s','%s','%s','%s','%s','%s','%s','%s','%s','%s','%s','%s','%s','%s','%s','%s','%s','%s','%s')";
		String x;
		String[] b;
	
		/*char characterValue = '"';
		int asciiValue = (int) characterValue;*/ //convierte a ascii
		boolean Vacios =cadena.contains("||");
		
		if (Vacios==true) {// pregunta si existe ||  y agrega un espacio entre ellos
			cadena = cadena.replace("||", "|"+"  "+"|");
		}
		
		cadena = cadena.replace("'", " ");

		b = cadena.split("\\|");// separa los datos por | y lo agrgega a un vector

		x = String.format(BD1,  b[0].toString().trim(), b[1].toString().trim(), b[2].toString().trim(), b[3].toString().trim(),b[4].toString().trim(), b[5].toString().trim(), b[6].toString().trim(), b[7].toString().trim(),
									b[8].toString().trim(), b[9].toString().trim(), b[10].toString().trim(), b[11].toString().trim(), b[12].toString().trim(), b[13].toString().trim(), b[14].toString().trim(), b[15].toString().trim(), 
									b[16].toString().trim(), b[17].toString().trim(), b[18].toString().trim(), b[19].toString().trim(), b[20].toString().trim(), b[21].toString().trim(), b[22].toString().trim(), b[23].toString().trim(),
									b[24].toString().trim(), b[25].toString().trim(), b[26].toString().trim(), b[27].toString().trim(), b[28].toString().trim(), b[29].toString().trim(), b[30].toString().trim(), b[31].toString().trim(), 
									b[32].toString().trim(), b[33].toString().trim(), b[34].toString().trim(), b[35].toString().trim(), b[36].toString().trim(), b[37].toString().trim(), b[38].toString().trim(), b[39].toString().trim(), 
									b[40].toString().trim(), b[41].toString().trim(), b[42].toString().trim(),n);
			base.EjecutarQuery(x);
	}

}// fin de la clase proceso

