USE [Libranza]
GO
/****** Object:  Table [dbo].[BANCOLOMBIA]    Script Date: 8/04/2021 9:58:23 a. m. ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[BANCOLOMBIA](
	[Fecha] [varchar](max) NOT NULL,
	[Nit] [varchar](max) NOT NULL,
	[Convenio] [varchar](max) NOT NULL,
	[COD] [varchar](max) NOT NULL,
	[F_Abono] [varchar](max) NOT NULL,
	[Observaciones] [varchar](max) NULL,
	[Valor] [varchar](max) NOT NULL,
	[Regional] [varchar](max) NOT NULL,
	[FORMA_DE_PAGO] [varchar](max) NULL,
	[OBSERVACION] [varchar](max) NULL,
	[id] [int] IDENTITY(1,1) NOT NULL
) ON [PRIMARY] TEXTIMAGE_ON [PRIMARY]
GO
/****** Object:  Table [dbo].[BASE _DE_DATOS_CLIENTES_FINAL]    Script Date: 8/04/2021 9:58:23 a. m. ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[BASE _DE_DATOS_CLIENTES_FINAL](
	[ITEM] [varchar](max) NULL,
	[Tipo_de_Documento] [varchar](max) NULL,
	[Numero_de_Documento] [varchar](max) NULL,
	[Nombre] [varchar](max) NULL,
	[No_Obligación] [varchar](max) NULL,
	[Código_Oficina] [varchar](max) NULL,
	[Nombre_Oficina] [varchar](max) NULL,
	[Vr_Cuota_Mensual] [float] NULL,
	[Vr_en_Mora] [float] NULL,
	[Vr_Total_a_Pagar] [float] NULL,
	[Vr_Intereses_en_Mora] [float] NULL,
	[Cuotas_Pactadas] [float] NULL,
	[Cuotas_a_Pagar] [float] NULL,
	[Valor_Pago_Empresa] [float] NULL,
	[novedad] [varchar](max) NULL,
	[Observaciones] [varchar](max) NULL,
	[CONVENIO] [varchar](max) NULL,
	[Orden] [float] NULL
) ON [PRIMARY] TEXTIMAGE_ON [PRIMARY]
GO
/****** Object:  Table [dbo].[BASE_CONSOLIDADA_CLIENTES_LIBRANZA]    Script Date: 8/04/2021 9:58:23 a. m. ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[BASE_CONSOLIDADA_CLIENTES_LIBRANZA](
	[CONVENIO] [varchar](max) NULL,
	[NIT] [varchar](max) NULL,
	[NOMBRE_EMPRESA] [varchar](max) NULL,
	[FECHA_DE_PAGO] [varchar](max) NULL,
	[correo_electronico_Empresa] [varchar](max) NULL,
	[ITEM] [varchar](max) NULL,
	[Tipo_de_Documento] [varchar](max) NULL,
	[Numero_de_Documento] [varchar](max) NULL,
	[Nombre] [varchar](max) NULL,
	[No_Obligación] [varchar](max) NULL,
	[Código_Oficina] [varchar](max) NULL,
	[Nombre_Oficina] [varchar](max) NULL,
	[Vr_Cuota_Mensual] [float] NULL,
	[Vr_en_Mora] [float] NULL,
	[Vr_Total_a_Pagar] [float] NULL,
	[Vr_Intereses_en_Mora] [float] NULL,
	[Cuotas_Pactadas] [float] NULL,
	[Cuotas_a_Pagar] [float] NULL,
	[Valor_Pago_Empresa] [float] NULL,
	[novedad] [varchar](max) NULL,
	[Observaciones] [varchar](max) NULL,
	[Orden] [float] NULL
) ON [PRIMARY] TEXTIMAGE_ON [PRIMARY]
GO
/****** Object:  Table [dbo].[BASE_DESVINCULADO]    Script Date: 8/04/2021 9:58:23 a. m. ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[BASE_DESVINCULADO](
	[CONVENIO] [varchar](max) NULL,
	[ENTE_CONVENIO] [varchar](max) NULL,
	[mo_numero_de_banco] [varchar](max) NULL,
	[mo_nombre_cliente] [varchar](max) NULL,
	[Número_de_documento] [varchar](max) NULL,
	[mo_monto_desembolso] [varchar](max) NULL,
	[Saldo_Capital] [varchar](max) NULL,
	[FECHA_DESEMBOLSO] [varchar](max) NULL,
	[Días_de_mora] [varchar](max) NULL,
	[Estado_obligacion] [varchar](max) NULL,
	[id] [float] NULL
) ON [PRIMARY] TEXTIMAGE_ON [PRIMARY]
GO
/****** Object:  Table [dbo].[BASE_SALDOS_ACTUALIZADOS_FACTURACION_CORTE]    Script Date: 8/04/2021 9:58:23 a. m. ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[BASE_SALDOS_ACTUALIZADOS_FACTURACION_CORTE](
	[CONVENIO] [varchar](max) NULL,
	[NIT] [varchar](max) NULL,
	[NOMBRE_EMPRESA] [varchar](max) NULL,
	[FECHA_DE_PAGO] [varchar](max) NULL,
	[Correo_electronico_Empresa] [varchar](max) NULL,
	[ITEM] [varchar](max) NULL,
	[Tipo_de_Documento] [varchar](max) NULL,
	[Numero_de_Documento] [varchar](max) NULL,
	[Nombre] [varchar](max) NULL,
	[Obligación] [varchar](max) NULL,
	[LLAVE] [varchar](max) NULL,
	[No_Obligación] [varchar](max) NULL,
	[Código_Oficina] [varchar](max) NULL,
	[Nombre_Oficina] [varchar](max) NULL,
	[Vr_Cuota_Mensual] [float] NULL,
	[Vr_en_Mora] [float] NULL,
	[Vr_Total_a_Pagar] [float] NULL,
	[Vr_Intereses_en_Mora] [float] NULL,
	[Cuotas_Pactadas] [float] NULL,
	[Cuotas_a_Pagar] [float] NULL,
	[Valor_Pago_Empresa] [float] NULL,
	[novedad] [varchar](max) NULL,
	[Observaciones] [varchar](max) NULL,
	[mo_dias_vencido_op] [varchar](max) NULL,
	[mo_fecha_prox_vencimiento] [varchar](max) NULL,
	[mo_saldo_cuota_prox_venc] [varchar](max) NULL,
	[mo_saldo_capital_vigente] [varchar](max) NULL,
	[mo_saldo_capital_vencido] [varchar](max) NULL,
	[mo_saldo_interes_vigente] [varchar](max) NULL,
	[mo_saldo_interes_vencido] [varchar](max) NULL,
	[mo_saldo_interes_contingente] [varchar](max) NULL,
	[mo_saldo_mora_contingente] [varchar](max) NULL,
	[mo_saldo_seguro_vida_vigente] [varchar](max) NULL,
	[mo_saldo_seguro_vida_vencido] [varchar](max) NULL,
	[mo_saldo_otros_vigente] [varchar](max) NULL,
	[mo_saldo_otros_vencidos] [varchar](max) NULL,
	[mo_estado_obligacion] [varchar](max) NULL,
	[mo_fecha_ult_pago] [varchar](max) NULL,
	[mo_valor_ult_pago] [varchar](max) NULL,
	[mo_numero_cuotas_vencidas] [varchar](max) NULL,
	[Orden] [float] NULL
) ON [PRIMARY] TEXTIMAGE_ON [PRIMARY]
GO
/****** Object:  Table [dbo].[Cagmaest]    Script Date: 8/04/2021 9:58:23 a. m. ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[Cagmaest](
	[mo_numero_de_banco] [varchar](max) NULL,
	[mo_dias_vencido_op] [varchar](max) NULL,
	[mo_fecha_prox_vencimiento] [varchar](max) NULL,
	[mo_saldo_cuota_prox_venc] [varchar](max) NULL,
	[mo_saldo_capital_vigente] [varchar](max) NULL,
	[mo_saldo_capital_vencido] [varchar](max) NULL,
	[mo_saldo_interes_vigente] [varchar](max) NULL,
	[mo_saldo_interes_vencido] [varchar](max) NULL,
	[mo_saldo_interes_contingente] [varchar](max) NULL,
	[mo_saldo_mora_contingente] [varchar](max) NULL,
	[mo_saldo_seguro_vida_vigente] [varchar](max) NULL,
	[mo_saldo_seguro_vida_vencido] [varchar](max) NULL,
	[mo_saldo_otros_vigente] [varchar](max) NULL,
	[mo_saldo_otros_vencidos] [varchar](max) NULL,
	[mo_estado_obligacion] [varchar](max) NULL,
	[mo_fecha_ult_pago] [varchar](max) NULL,
	[mo_valor_ult_pago] [varchar](max) NULL,
	[mo_numero_cuotas_vencidas] [varchar](max) NULL,
	[id] [float] NULL
) ON [PRIMARY] TEXTIMAGE_ON [PRIMARY]
GO
/****** Object:  Table [dbo].[Cagmaest-1]    Script Date: 8/04/2021 9:58:23 a. m. ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[Cagmaest-1](
	[mo_fecha_de_proceso] [varchar](max) NULL,
	[mo_producto] [varchar](max) NULL,
	[mo_tipo_de_producto] [varchar](max) NULL,
	[mo_moneda] [varchar](max) NULL,
	[mo_numero_de_operacion] [varchar](max) NULL,
	[mo_numero_de_banco] [varchar](max) NULL,
	[mo_numero_migrada] [varchar](max) NULL,
	[mo_cliente] [varchar](max) NULL,
	[mo_nombre_cliente] [varchar](max) NULL,
	[mo_cupo_credito] [varchar](max) NULL,
	[mo_oficina] [varchar](max) NULL,
	[mo_nombre_oficina] [varchar](max) NULL,
	[mo_dias_causados] [varchar](max) NULL,
	[mo_monto] [varchar](max) NULL,
	[mo_monto_desembolso] [varchar](max) NULL,
	[mo_tasa] [varchar](max) NULL,
	[mo_tasa_efectiva] [varchar](max) NULL,
	[mo_plazo_total] [varchar](max) NULL,
	[mo_modalidad_cobro_int] [varchar](max) NULL,
	[mo_fecha_inicio_op] [varchar](max) NULL,
	[mo_fecha_ven_op] [varchar](max) NULL,
	[mo_dias_vencido_op] [varchar](max) NULL,
	[mo_fecha_fin_min_div_ven] [varchar](max) NULL,
	[mo_reestructurada_plano] [varchar](max) NULL,
	[mo_fecha_ult_reest] [varchar](max) NULL,
	[mo_num_reestructuraciones] [varchar](max) NULL,
	[mo_num_cuotas_pagadas] [varchar](max) NULL,
	[mo_num_cuotas_pagadas_reest] [varchar](max) NULL,
	[mo_destino_credito] [varchar](max) NULL,
	[mo_clase_cartera] [varchar](max) NULL,
	[mo_ciudad] [varchar](max) NULL,
	[mo_fecha_prox_vencimiento] [varchar](max) NULL,
	[mo_saldo_cuota_prox_venc] [varchar](max) NULL,
	[mo_saldo_capital_vigente] [varchar](max) NULL,
	[mo_saldo_capital_vencido] [varchar](max) NULL,
	[mo_saldo_interes_vigente] [varchar](max) NULL,
	[mo_saldo_interes_vencido] [varchar](max) NULL,
	[mo_saldo_interes_contingente] [varchar](max) NULL,
	[mo_saldo_mora_vigente] [varchar](max) NULL,
	[mo_saldo_mora_contingente] [varchar](max) NULL,
	[mo_saldo_seguro_vida_vigente] [varchar](max) NULL,
	[mo_saldo_seguro_vida_vencido] [varchar](max) NULL,
	[mo_saldo_otros_vigente] [varchar](max) NULL,
	[mo_saldo_otros_vencidos] [varchar](max) NULL,
	[mo_estado_obligacion] [varchar](max) NULL,
	[mo_calificacion_obligacion] [varchar](max) NULL,
	[mo_frecuencia_pago_int] [varchar](max) NULL,
	[mo_frecuencia_pago_cap] [varchar](max) NULL,
	[mo_edad_vencimiento_cartera] [varchar](max) NULL,
	[mo_fecha_ult_pago] [varchar](max) NULL,
	[mo_valor_ult_pago] [varchar](max) NULL,
	[mo_fecha_ult_pago_cap] [varchar](max) NULL,
	[mo_valor_ult_pago_cap] [varchar](max) NULL,
	[mo_valor_cuota_tabla] [varchar](max) NULL,
	[mo_numero_cuotas_pactadas] [varchar](max) NULL,
	[mo_numero_cuotas_vencidas] [varchar](max) NULL,
	[mo_clase_garantia] [varchar](max) NULL,
	[mo_tipo_garantia] [varchar](max) NULL,
	[mo_descripcion_tipo_garantia] [varchar](max) NULL,
	[mo_fecha_castigo] [varchar](max) NULL,
	[mo_fecha_pago_pasiva] [varchar](max) NULL,
	[mo_estado_pasiva] [varchar](max) NULL,
	[mo_fecha_embarque] [varchar](max) NULL,
	[mo_fecha_ini_caus] [varchar](max) NULL,
	[mo_tipo_tasa] [varchar](max) NULL,
	[mo_tasa_referencial] [varchar](max) NULL,
	[mo_signo] [varchar](max) NULL,
	[mo_factor] [varchar](max) NULL,
	[mo_tipo_identificacion] [varchar](max) NULL,
	[mo_numero_identificacion] [varchar](max) NULL,
	[mo_provision_cap] [varchar](max) NULL,
	[mo_provision_int] [varchar](max) NULL,
	[mo_provision_cxc] [varchar](max) NULL,
	[mo_cuenta_asociada] [varchar](max) NULL,
	[mo_forma_pago] [varchar](max) NULL,
	[mo_tipo_tabla] [varchar](max) NULL,
	[mo_periodo_gracia_cap] [varchar](max) NULL,
	[mo_periodo_gracia_int] [varchar](max) NULL,
	[mo_estado_cobranza] [varchar](max) NULL,
	[mo_descripcion_estado_cobranza] [varchar](max) NULL,
	[mo_tasa_representativa_mercado] [varchar](max) NULL,
	[mo_reajustable] [varchar](max) NULL,
	[mo_descripcion_reajustable] [varchar](max) NULL,
	[mo_periodo_reajuste] [varchar](max) NULL,
	[mo_fecha_prox_reajuste] [varchar](max) NULL,
	[mo_fecha_ult_proceso] [varchar](max) NULL,
	[mo_tipo_soc] [varchar](max) NULL,
	[mo_tipo_puntos] [varchar](max) NULL,
	[mo_cobertura_garantia] [varchar](max) NULL,
	[mo_des_tipo_bien] [varchar](max) NULL,
	[mo_tramite] [varchar](max) NULL,
	[mo_defecto_garantia] [varchar](max) NULL,
	[mo_modalidad] [varchar](max) NULL,
	[mo_clausula] [varchar](max) NULL,
	[mo_naturaleza_linea] [varchar](max) NULL,
	[mo_tiene_seg_vida] [varchar](max) NULL,
	[mo_tiene_seg_vehiculo] [varchar](max) NULL,
	[mo_tiene_seg_todor_maq] [varchar](max) NULL,
	[mo_tiene_seg_rotura_maq] [varchar](max) NULL,
	[mo_tiene_seg_vivienda] [varchar](max) NULL,
	[mo_tiene_seg_extraprima] [varchar](max) NULL,
	[mo_tiene_incentivo] [varchar](max) NULL,
	[mo_tipo_banca] [varchar](max) NULL,
	[mo_nombre_codeudor] [varchar](max) NULL,
	[mo_ced_ruc_codeudor] [varchar](max) NULL,
	[mo_zona] [varchar](max) NULL,
	[mo_regional] [varchar](max) NULL,
	[mo_capitaliza] [varchar](max) NULL,
	[mo_tipo_productor] [varchar](max) NULL,
	[mo_mercado_obj] [varchar](max) NULL,
	[mo_aprobador] [varchar](max) NULL,
	[mo_llave_redescuento] [varchar](max) NULL,
	[mo_condonacion_intereses] [varchar](max) NULL,
	[mo_condonacion_capital] [varchar](max) NULL,
	[mo_provision_defecto] [varchar](max) NULL,
	[mo_margen_redescuento] [varchar](max) NULL,
	[mo_codigo_sector] [varchar](max) NULL,
	[mo_dias_base] [varchar](max) NULL,
	[mo_provisiona_cap] [varchar](max) NULL,
	[mo_provisiona_int] [varchar](max) NULL,
	[mo_provisiona_cxc] [varchar](max) NULL,
	[mo_norma_legal] [varchar](max) NULL,
	[mo_ind_aprob] [varchar](max) NULL,
	[mo_cap_diferido] [varchar](max) NULL,
	[mo_int_imo_diferido] [varchar](max) NULL,
	[mo_tramite_solicitud] [varchar](max) NULL,
	[mo_banco_pasiva] [varchar](max) NULL,
	[mo_truta] [varchar](max) NULL,
	[mo_fabrica] [varchar](max) NULL,
	[mo_callcenter] [varchar](max) NULL,
	[mo_apr_fabrica] [varchar](max) NULL,
	[mo_monto_solicitado] [varchar](max) NULL,
	[mo_tipo_programa] [varchar](max) NULL,
	[mo_asociativo] [varchar](max) NULL,
	[mo_administrador_cca] [varchar](max) NULL,
	[mo_nombre_adm_cca] [varchar](max) NULL,
	[mo_tipo_incentivo] [varchar](max) NULL,
	[mo_saldo_int_contin_vigente] [varchar](max) NULL,
	[mo_saldo_int_contin_vencido] [varchar](max) NULL,
	[mo_saldo_otros_gastos_judic] [varchar](max) NULL,
	[mo_saldo_otros_comisiones] [varchar](max) NULL,
	[mo_saldo_otros_iva] [varchar](max) NULL,
	[mo_saldo_otros_todo_riesgo] [varchar](max) NULL,
	[mo_saldo_otros_segromaqui] [varchar](max) NULL,
	[mo_valor_quedar_al_dia] [varchar](max) NULL,
	[mo_valor_cancelacion_total] [varchar](max) NULL,
	[mo_valor_corr_mon_capital] [varchar](max) NULL,
	[mo_valor_corr_mon_interes] [varchar](max) NULL,
	[mo_ciudad_inversion] [varchar](max) NULL,
	[mo_aprobo_desembolso] [varchar](max) NULL,
	[mo_tiene_seg_cosecha] [varchar](max) NULL,
	[mo_marca_cobro_jur] [varchar](max) NULL,
	[mo_categoria_asesor] [varchar](max) NULL,
	[mo_conv_garc] [varchar](max) NULL,
	[mo_entidad_convenio] [varchar](max) NULL,
	[mo_tipo_alivio] [varchar](max) NULL,
	[mo_Tipo_Arreglo] [varchar](max) NULL,
	[mo_tipo_operacion_co] [varchar](max) NULL,
	[mo_tipo_micro] [varchar](max) NULL,
	[mo_tipo_seg] [varchar](max) NULL,
	[mo_valor_tipo_seg] [varchar](max) NULL,
	[Mo_Tipo_cartera_Svia] [varchar](max) NULL,
	[Mo_Programa_FAG_Svia] [varchar](max) NULL,
	[NUEVO_CAMPO] [varchar](max) NULL,
	[id] [float] NULL
) ON [PRIMARY] TEXTIMAGE_ON [PRIMARY]
GO
/****** Object:  Table [dbo].[CagmaestP1]    Script Date: 8/04/2021 9:58:23 a. m. ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[CagmaestP1](
	[Obligacion] [nvarchar](max) NOT NULL,
	[Convenio] [varchar](max) NOT NULL,
	[NombreC] [nvarchar](max) NOT NULL,
	[CedulaC] [nvarchar](max) NOT NULL,
	[id] [int] IDENTITY(1,1) NOT NULL
) ON [PRIMARY] TEXTIMAGE_ON [PRIMARY]
GO
/****** Object:  Table [dbo].[CagmaestP2]    Script Date: 8/04/2021 9:58:23 a. m. ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[CagmaestP2](
	[Obligacion] [nvarchar](max) NOT NULL,
	[Convenio] [nvarchar](max) NOT NULL,
	[NombreC] [nvarchar](max) NOT NULL,
	[CedulaC] [nvarchar](max) NOT NULL,
	[id] [int] IDENTITY(1,1) NOT NULL
) ON [PRIMARY] TEXTIMAGE_ON [PRIMARY]
GO
/****** Object:  Table [dbo].[CagmaestP3]    Script Date: 8/04/2021 9:58:23 a. m. ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[CagmaestP3](
	[Obligacion] [nvarchar](max) NOT NULL,
	[Convenio] [nvarchar](max) NOT NULL,
	[NombreC] [nvarchar](max) NOT NULL,
	[CedulaC] [nvarchar](max) NOT NULL,
	[id] [int] IDENTITY(1,1) NOT NULL
) ON [PRIMARY] TEXTIMAGE_ON [PRIMARY]
GO
/****** Object:  Table [dbo].[Catradia1]    Script Date: 8/04/2021 9:58:23 a. m. ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[Catradia1](
	[Regional] [varchar](50) NULL,
	[convenio] [varchar](50) NULL,
	[CC] [varchar](50) NULL,
	[NombreC] [varchar](max) NULL,
	[FechaMov] [varchar](max) NULL,
	[tr_tran] [varchar](max) NULL,
	[tr_banco] [varchar](max) NULL,
	[tr_ofi_oper] [varchar](max) NULL,
	[tr_ofi_usu] [varchar](max) NULL,
	[dtr_concepto] [varchar](max) NULL,
	[dtr_monto_mn] [float] NULL,
	[dtr_cuenta] [varchar](max) NULL,
	[tr_estado] [varchar](max) NULL,
	[Grado] [int] NULL,
	[id] [int] IDENTITY(1,1) NOT NULL
) ON [PRIMARY] TEXTIMAGE_ON [PRIMARY]
GO
/****** Object:  Table [dbo].[Catradia2]    Script Date: 8/04/2021 9:58:23 a. m. ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[Catradia2](
	[Regional] [varchar](max) NULL,
	[convenio] [varchar](max) NULL,
	[CC] [varchar](max) NULL,
	[NombreC] [varchar](max) NULL,
	[FechaMov] [varchar](max) NULL,
	[tr_tran] [varchar](max) NULL,
	[tr_banco] [varchar](max) NULL,
	[tr_ofi_oper] [varchar](max) NULL,
	[tr_ofi_usu] [varchar](max) NULL,
	[dtr_concepto] [varchar](max) NULL,
	[dtr_monto_mn] [float] NULL,
	[dtr_cuenta] [varchar](max) NULL,
	[tr_estado] [varchar](max) NULL,
	[Grado] [int] NULL,
	[id] [int] IDENTITY(1,1) NOT NULL
) ON [PRIMARY] TEXTIMAGE_ON [PRIMARY]
GO
/****** Object:  Table [dbo].[Catradia3]    Script Date: 8/04/2021 9:58:23 a. m. ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[Catradia3](
	[Regional] [varchar](max) NULL,
	[convenio] [varchar](max) NULL,
	[CC] [varchar](max) NULL,
	[NombreC] [varchar](max) NULL,
	[FechaMov] [varchar](max) NULL,
	[tr_tran] [varchar](max) NULL,
	[tr_banco] [varchar](max) NULL,
	[tr_ofi_oper] [varchar](max) NULL,
	[tr_ofi_usu] [varchar](max) NULL,
	[dtr_concepto] [varchar](max) NULL,
	[dtr_monto_mn] [float] NULL,
	[dtr_cuenta] [varchar](max) NULL,
	[tr_estado] [varchar](max) NULL,
	[Grado] [int] NULL,
	[id] [int] IDENTITY(1,1) NOT NULL
) ON [PRIMARY] TEXTIMAGE_ON [PRIMARY]
GO
/****** Object:  Table [dbo].[CatradiaHistorial]    Script Date: 8/04/2021 9:58:23 a. m. ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[CatradiaHistorial](
	[FechaMov] [varchar](max) NOT NULL,
	[tr_tran] [varchar](max) NOT NULL,
	[tr_banco] [varchar](max) NOT NULL,
	[tr_ofi_oper] [varchar](max) NOT NULL,
	[tr_ofi_usu] [varchar](max) NOT NULL,
	[dtr_concepto] [varchar](max) NOT NULL,
	[dtr_monto_mn] [float] NOT NULL,
	[dtr_cuenta] [varchar](max) NOT NULL,
	[tr_estado] [varchar](max) NOT NULL,
	[id] [int] IDENTITY(1,1) NOT NULL,
	[Fecha] [varchar](max) NULL
) ON [PRIMARY] TEXTIMAGE_ON [PRIMARY]
GO
/****** Object:  Table [dbo].[CLIENTES]    Script Date: 8/04/2021 9:58:23 a. m. ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[CLIENTES](
	[ITEM] [varchar](max) NULL,
	[Tipo_de_Documento] [varchar](max) NULL,
	[Numero_de_Documento] [varchar](max) NULL,
	[Nombre] [varchar](max) NULL,
	[No_Obligación] [varchar](max) NULL,
	[Código_Oficina] [varchar](max) NULL,
	[Nombre_Oficina] [varchar](max) NULL,
	[Vr_Cuota_Mensual] [float] NULL,
	[Vr_en_Mora] [float] NULL,
	[Vr_Total_a_Pagar] [float] NULL,
	[Vr_Intereses_en_Mora] [float] NULL,
	[Cuotas_Pactadas] [float] NULL,
	[Cuotas_a_Pagar] [float] NULL,
	[CONVENIO] [varchar](max) NULL,
	[Orden] [float] NULL
) ON [PRIMARY] TEXTIMAGE_ON [PRIMARY]
GO
/****** Object:  Table [dbo].[Cobrconv]    Script Date: 8/04/2021 9:58:23 a. m. ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[Cobrconv](
	[ITEM] [varchar](max) NULL,
	[CONVENIO] [varchar](max) NULL,
	[NIT] [varchar](max) NULL,
	[NOMBRE_EMPRESA] [varchar](max) NULL,
	[DIRECCION] [varchar](max) NULL,
	[TELEFONO] [varchar](max) NULL,
	[CIUDAD] [varchar](max) NULL,
	[DEPARTAMENTO] [varchar](max) NULL,
	[FECHA_DE_PAGO] [varchar](max) NULL,
	[correo_electronico_Empresa] [float] NULL,
	[Valor_Pago_Empresa] [float] NULL,
	[novedad] [varchar](max) NULL,
	[Observaciones] [varchar](max) NULL,
	[convenio2] [varchar](max) NULL,
	[Orden] [varchar](max) NULL
) ON [PRIMARY] TEXTIMAGE_ON [PRIMARY]
GO
/****** Object:  Table [dbo].[COINCIDENTE]    Script Date: 8/04/2021 9:58:23 a. m. ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[COINCIDENTE](
	[Regional] [varchar](max) NULL,
	[Convenio] [varchar](max) NULL,
	[Formato_de_Pago] [varchar](50) NULL,
	[Fecha_de_Pago] [varchar](50) NULL,
	[Identificacion] [varchar](max) NULL,
	[Nombre] [varchar](50) NULL,
	[Obligacion] [varchar](max) NULL,
	[Bancolombia] [varchar](max) NULL,
	[Observacion] [varchar](max) NULL,
	[Orden] [int] IDENTITY(1,1) NOT NULL
) ON [PRIMARY] TEXTIMAGE_ON [PRIMARY]
GO
/****** Object:  Table [dbo].[COINCIDENTE2]    Script Date: 8/04/2021 9:58:23 a. m. ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[COINCIDENTE2](
	[REGIONAL] [varchar](max) NULL,
	[Codigo] [varchar](max) NULL,
	[NIT] [varchar](max) NULL,
	[FECHA] [varchar](max) NULL,
	[OBSERVACION] [varchar](max) NULL,
	[VALOR] [varchar](max) NULL,
	[id] [int] IDENTITY(1,1) NOT NULL
) ON [PRIMARY] TEXTIMAGE_ON [PRIMARY]
GO
/****** Object:  Table [dbo].[Correo_Remitente]    Script Date: 8/04/2021 9:58:23 a. m. ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[Correo_Remitente](
	[Usuario] [varchar](max) NOT NULL,
	[Email] [varchar](max) NOT NULL,
	[Password] [varchar](max) NULL,
	[Mascara] [varchar](max) NOT NULL,
	[id] [int] IDENTITY(1,1) NOT NULL
) ON [PRIMARY] TEXTIMAGE_ON [PRIMARY]
GO
/****** Object:  Table [dbo].[DataCobrconv]    Script Date: 8/04/2021 9:58:23 a. m. ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[DataCobrconv](
	[Nombre] [varchar](max) NULL,
	[Dia] [int] NULL,
	[mes] [varchar](max) NULL,
	[anio] [int] NULL,
	[posicion] [int] NULL
) ON [PRIMARY] TEXTIMAGE_ON [PRIMARY]
GO
/****** Object:  Table [dbo].[EMPRESAS]    Script Date: 8/04/2021 9:58:23 a. m. ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[EMPRESAS](
	[ITEM] [varchar](max) NULL,
	[CONVENIO] [varchar](max) NULL,
	[NIT] [varchar](max) NULL,
	[NOMBRE_EMPRESA] [varchar](max) NULL,
	[DIRECCION] [varchar](max) NULL,
	[TELEFONO] [varchar](max) NULL,
	[CIUDAD] [varchar](max) NULL,
	[DEPARTAMENTO] [varchar](max) NULL,
	[FECHA_DE_PAGO] [varchar](max) NULL,
	[correo_electronico_Empresa] [varchar](max) NULL,
	[Valor_Pago_Empresa] [float] NULL,
	[novedad] [varchar](max) NULL,
	[Observaciones] [varchar](max) NULL,
	[Orden] [float] NULL
) ON [PRIMARY] TEXTIMAGE_ON [PRIMARY]
GO
/****** Object:  Table [dbo].[FACTURACION]    Script Date: 8/04/2021 9:58:23 a. m. ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[FACTURACION](
	[ITEM] [varchar](max) NULL,
	[Vr_Cuota_Mensual] [float] NULL,
	[Vr_en_Mora] [float] NULL,
	[Vr_Total_a_Pagar] [float] NULL,
	[Vr_Intereses_en_Mora] [float] NULL,
	[CONVENIO] [varchar](max) NULL,
	[Orden] [float] NULL
) ON [PRIMARY] TEXTIMAGE_ON [PRIMARY]
GO
/****** Object:  Table [dbo].[FACTURACION2]    Script Date: 8/04/2021 9:58:23 a. m. ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[FACTURACION2](
	[ITEM] [varchar](max) NULL,
	[Vr_Cuota_Mensual] [float] NULL,
	[Vr_en_Mora] [float] NULL,
	[Vr_Total_a_Pagar] [float] NULL,
	[Vr_Intereses_en_Mora] [float] NULL,
	[CONVENIO] [varchar](max) NULL,
	[Orden] [float] NULL
) ON [PRIMARY] TEXTIMAGE_ON [PRIMARY]
GO
/****** Object:  Table [dbo].[LibrosSubidos]    Script Date: 8/04/2021 9:58:23 a. m. ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[LibrosSubidos](
	[Libros] [varchar](50) NULL,
	[Estado] [bit] NULL
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[NO_COINCIDENTE]    Script Date: 8/04/2021 9:58:23 a. m. ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[NO_COINCIDENTE](
	[Fecha] [varchar](50) NULL,
	[Nit] [varchar](50) NULL,
	[Convenio] [varchar](max) NULL,
	[Cod] [varchar](50) NULL,
	[F_Abono] [varchar](max) NULL,
	[Bancolombia] [float] NULL,
	[Vr_Cuota_Mensual] [float] NULL,
	[Diferencia] [float] NULL,
	[Regional] [varchar](50) NULL,
	[CC] [varchar](max) NULL,
	[nombre] [varchar](max) NULL,
	[obligacion] [varchar](max) NULL,
	[nomOficina] [varchar](max) NULL,
	[CPactadas] [varchar](max) NULL,
	[CPagar] [varchar](max) NULL,
	[CMensual] [varchar](max) NULL,
	[VrMora] [varchar](max) NULL,
	[VrTPagar] [varchar](max) NULL,
	[VrIMora] [varchar](max) NULL,
	[VrPEmpresa] [varchar](max) NULL,
	[Novedad] [varchar](max) NULL,
	[Observaciones] [varchar](max) NULL,
	[DirenciaCvsB] [varchar](max) NULL,
	[Gado] [int] NOT NULL,
	[Orden] [int] IDENTITY(1,1) NOT NULL
) ON [PRIMARY] TEXTIMAGE_ON [PRIMARY]
GO
/****** Object:  Table [dbo].[NO_COINCIDENTE2]    Script Date: 8/04/2021 9:58:23 a. m. ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[NO_COINCIDENTE2](
	[Fecha] [varchar](50) NULL,
	[Nit] [varchar](50) NULL,
	[Convenio] [varchar](max) NULL,
	[Cod] [varchar](50) NULL,
	[F_Abono] [varchar](max) NULL,
	[Bancolombia] [float] NULL,
	[Vr_Cuota_Mensual] [float] NULL,
	[Diferencia] [float] NULL,
	[Regional] [varchar](50) NULL,
	[CC] [varchar](max) NULL,
	[nombre] [varchar](max) NULL,
	[obligacion] [varchar](max) NULL,
	[nomOficina] [varchar](max) NULL,
	[CPactadas] [float] NULL,
	[CPagar] [float] NULL,
	[CMensual] [float] NULL,
	[VrMora] [float] NULL,
	[VrTPagar] [float] NULL,
	[VrIMora] [float] NULL,
	[VrPEmpresa] [varchar](max) NULL,
	[Novedad] [varchar](max) NULL,
	[Observaciones] [varchar](max) NULL,
	[DirenciaCvsB] [float] NULL,
	[USADO] [varchar](50) NULL,
	[Orden] [int] IDENTITY(1,1) NOT NULL
) ON [PRIMARY] TEXTIMAGE_ON [PRIMARY]
GO
/****** Object:  Table [dbo].[P_Cobrconv2]    Script Date: 8/04/2021 9:58:23 a. m. ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[P_Cobrconv2](
	[ITEM] [varchar](max) NULL,
	[tipo_de_documento] [varchar](max) NULL,
	[cedula] [varchar](max) NULL,
	[nombre_cliente] [varchar](max) NULL,
	[obligacion] [varchar](max) NULL,
	[codigo_de_oficina] [varchar](max) NULL,
	[oficina] [varchar](max) NULL,
	[Vr_cuota_vigente] [varchar](max) NULL,
	[Vr_cuota_mora] [varchar](max) NULL,
	[Vr_total] [varchar](max) NULL,
	[intereses_mora] [varchar](max) NULL,
	[plazo] [varchar](max) NULL,
	[no_cuenta] [varchar](max) NULL,
	[convenio2] [varchar](50) NULL,
	[Orden] [int] IDENTITY(1,1) NOT NULL
) ON [PRIMARY] TEXTIMAGE_ON [PRIMARY]
GO
/****** Object:  Table [dbo].[P_Cobrconv3]    Script Date: 8/04/2021 9:58:23 a. m. ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[P_Cobrconv3](
	[ITEM] [varchar](max) NULL,
	[tipo_de_documento] [varchar](max) NULL,
	[cedula] [varchar](max) NULL,
	[nombre_cliente] [varchar](max) NULL,
	[obligacion] [varchar](max) NULL,
	[codigo_de_oficina] [varchar](max) NULL,
	[oficina] [varchar](max) NULL,
	[Vr_cuota_vigente] [varchar](max) NULL,
	[Vr_cuota_mora] [varchar](max) NULL,
	[Vr_total] [varchar](max) NULL,
	[intereses_mora] [varchar](max) NULL,
	[plazo] [varchar](max) NULL,
	[no_cuenta] [varchar](max) NULL,
	[convenio2] [varchar](50) NULL,
	[Orden] [int] IDENTITY(1,1) NOT NULL
) ON [PRIMARY] TEXTIMAGE_ON [PRIMARY]
GO
/****** Object:  Table [dbo].[P_Cobrconv4]    Script Date: 8/04/2021 9:58:23 a. m. ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[P_Cobrconv4](
	[ITEM] [varchar](max) NULL,
	[tipo_de_documento] [varchar](max) NULL,
	[cedula] [varchar](max) NULL,
	[nombre_cliente] [varchar](max) NULL,
	[obligacion] [varchar](max) NULL,
	[codigo_de_oficina] [varchar](max) NULL,
	[oficina] [varchar](max) NULL,
	[Vr_cuota_vigente] [varchar](max) NULL,
	[Vr_cuota_mora] [varchar](max) NULL,
	[Vr_total] [varchar](max) NULL,
	[intereses_mora] [varchar](max) NULL,
	[plazo] [varchar](max) NULL,
	[no_cuenta] [varchar](max) NULL,
	[convenio2] [varchar](max) NULL,
	[Orden] [int] IDENTITY(1,1) NOT NULL
) ON [PRIMARY] TEXTIMAGE_ON [PRIMARY]
GO
/****** Object:  Table [dbo].[P_Cobrconv5]    Script Date: 8/04/2021 9:58:23 a. m. ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[P_Cobrconv5](
	[ITEM] [varchar](max) NULL,
	[tipo_de_documento] [varchar](max) NULL,
	[cedula] [varchar](max) NULL,
	[nombre_cliente] [varchar](max) NULL,
	[obligacion] [varchar](max) NULL,
	[codigo_de_oficina] [varchar](max) NULL,
	[oficina] [varchar](max) NULL,
	[Vr_cuota_vigente] [varchar](max) NULL,
	[Vr_cuota_mora] [varchar](max) NULL,
	[Vr_total] [varchar](max) NULL,
	[intereses_mora] [varchar](max) NULL,
	[plazo] [varchar](max) NULL,
	[no_cuenta] [varchar](max) NULL,
	[convenio2] [varchar](max) NULL,
	[Orden] [int] IDENTITY(1,1) NOT NULL
) ON [PRIMARY] TEXTIMAGE_ON [PRIMARY]
GO
/****** Object:  Table [dbo].[P_Cobrconv6]    Script Date: 8/04/2021 9:58:23 a. m. ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[P_Cobrconv6](
	[ITEM] [varchar](max) NULL,
	[tipo_de_documento] [varchar](max) NULL,
	[cedula] [varchar](max) NULL,
	[nombre_cliente] [varchar](max) NULL,
	[obligacion] [varchar](max) NULL,
	[codigo_de_oficina] [varchar](max) NULL,
	[oficina] [varchar](max) NULL,
	[Vr_cuota_vigente] [varchar](max) NULL,
	[Vr_cuota_mora] [varchar](max) NULL,
	[Vr_total] [varchar](max) NULL,
	[intereses_mora] [varchar](max) NULL,
	[plazo] [varchar](max) NULL,
	[no_cuenta] [varchar](max) NULL,
	[convenio2] [varchar](max) NULL,
	[Orden] [int] IDENTITY(1,1) NOT NULL
) ON [PRIMARY] TEXTIMAGE_ON [PRIMARY]
GO
/****** Object:  Table [dbo].[Parametrizados]    Script Date: 8/04/2021 9:58:23 a. m. ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[Parametrizados](
	[Convenio] [varchar](max) NULL,
	[EstadodelConvenio] [varchar](max) NULL,
	[Ente] [varchar](max) NULL,
	[TipodeDocumento] [varchar](max) NULL,
	[Documento] [varchar](max) NULL,
	[Tipodepersona] [varchar](max) NULL,
	[Nombre] [varchar](max) NULL,
	[Dirección] [varchar](max) NULL,
	[Teléfono] [varchar](max) NULL,
	[NodeEmpleados] [varchar](max) NULL,
	[FechaDeAbonoalBanco] [varchar](max) NULL,
	[Formadepago] [varchar](max) NULL,
	[Fechacreacióndelconvenio] [varchar](max) NULL,
	[FechaFinaldelconvenio] [varchar](max) NULL,
	[FechaSolicituddeRenovación] [varchar](max) NULL,
	[Cuentadecobro] [varchar](max) NULL,
	[CódigodeOficina] [varchar](max) NULL,
	[Oficina] [varchar](max) NULL,
	[Ciudad] [varchar](max) NULL,
	[Depto] [varchar](max) NULL,
	[TipoConvenio] [varchar](max) NULL,
	[CódigoRegional] [varchar](max) NULL,
	[Regional] [varchar](max) NULL,
	[EstadodelaPagaduría] [varchar](max) NULL,
	[Sector] [varchar](max) NULL,
	[Carácter] [varchar](max) NULL,
	[TipodeBanca] [varchar](max) NULL,
	[ConvenioEspecial] [varchar](max) NULL,
	[FormadeAprobación] [varchar](max) NULL,
	[AutorizaciónODS] [varchar](max) NULL,
	[Contacto] [varchar](max) NULL,
	[CortedeNomina] [varchar](max) NULL,
	[FechadeNovedades] [varchar](max) NULL,
	[Requierepazysalvo] [varchar](max) NULL,
	[DirecciónPazysalvo] [varchar](max) NULL,
	[PromotorResponsable] [varchar](max) NULL,
	[Observaciones] [varchar](max) NULL,
	[CartadeAprobación] [varchar](max) NULL,
	[InformedeVisita] [varchar](max) NULL,
	[AplicaparaCompradeCartera] [varchar](max) NULL,
	[OBSERVACIONDECOMPRADECARTERA] [varchar](max) NULL,
	[CIUDADSEDEDELAEMPRESA] [varchar](max) NULL,
	[FECHADEBLOQUEODELCONVENIO] [varchar](max) NULL,
	[Orden] [decimal](18, 0) NOT NULL
) ON [PRIMARY] TEXTIMAGE_ON [PRIMARY]
GO
/****** Object:  Table [dbo].[ParametrizadosP]    Script Date: 8/04/2021 9:58:23 a. m. ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[ParametrizadosP](
	[Convenio] [varchar](max) NULL,
	[EstadodelConvenio] [varchar](max) NULL,
	[Ente] [varchar](max) NULL,
	[TipodeDocumento] [varchar](max) NULL,
	[Documento] [varchar](max) NULL,
	[Tipodepersona] [varchar](max) NULL,
	[Nombre] [varchar](max) NULL,
	[Dirección] [varchar](max) NULL,
	[Teléfono] [varchar](max) NULL,
	[NodeEmpleados] [varchar](max) NULL,
	[FechaDeAbonoalBanco] [varchar](max) NULL,
	[Formadepago] [varchar](max) NULL,
	[Fechacreacióndelconvenio] [varchar](max) NULL,
	[FechaFinaldelconvenio] [varchar](max) NULL,
	[FechaSolicituddeRenovación] [varchar](max) NULL,
	[Cuentadecobro] [varchar](max) NULL,
	[CódigodeOficina] [varchar](max) NULL,
	[Oficina] [varchar](max) NULL,
	[Ciudad] [varchar](max) NULL,
	[Depto] [varchar](max) NULL,
	[TipoConvenio] [varchar](max) NULL,
	[CódigoRegional] [varchar](max) NULL,
	[Regional] [varchar](max) NULL,
	[EstadodelaPagaduría] [varchar](max) NULL,
	[Sector] [varchar](max) NULL,
	[Carácter] [varchar](max) NULL,
	[TipodeBanca] [varchar](max) NULL,
	[ConvenioEspecial] [varchar](max) NULL,
	[FormadeAprobación] [varchar](max) NULL,
	[AutorizaciónODS] [varchar](max) NULL,
	[Contacto] [varchar](max) NULL,
	[CortedeNomina] [varchar](max) NULL,
	[FechadeNovedades] [varchar](max) NULL,
	[Requierepazysalvo] [varchar](max) NULL,
	[DirecciónPazysalvo] [varchar](max) NULL,
	[PromotorResponsable] [varchar](max) NULL,
	[Observaciones] [varchar](max) NULL,
	[CartadeAprobación] [varchar](max) NULL,
	[InformedeVisita] [varchar](max) NULL,
	[AplicaparaCompradeCartera] [varchar](max) NULL,
	[OBSERVACIONDECOMPRADECARTERA] [varchar](max) NULL,
	[CIUDADSEDEDELAEMPRESA] [varchar](max) NULL,
	[FECHADEBLOQUEODELCONVENIO] [varchar](max) NULL,
	[Orden] [decimal](18, 0) NOT NULL
) ON [PRIMARY] TEXTIMAGE_ON [PRIMARY]
GO
/****** Object:  Table [dbo].[Tb_Dinamica]    Script Date: 8/04/2021 9:58:23 a. m. ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[Tb_Dinamica](
	[CONVENIO_TD] [float] NULL,
	[Vr_Cuota_Mensual_TD] [float] NULL,
	[Vr_en_Mora_TD] [float] NULL,
	[Vr_Total_a_Pagar_TD] [float] NULL,
	[Vr_Intereses_en_Mora_TD] [float] NULL
) ON [PRIMARY]
GO
/****** Object:  StoredProcedure [dbo].[ActuaizacionBancolombia]    Script Date: 8/04/2021 9:58:23 a. m. ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE procedure [dbo].[ActuaizacionBancolombia]

as
update BANCOLOMBIA set Valor= REPLACE(Valor,'.00','') 
insert into  Libranza.dbo.COINCIDENTE2
SELECT Regional,Cod,Nit,'' as Fecha,'',sum(cast(Bancolombia as float)) from NO_COINCIDENTE  GROUP BY Regional,Cod,Nit HAVING COUNT(*)>1
update  [Libranza].[dbo].COINCIDENTE2 set FECHA=BANCOLOMBIA.Fecha from BANCOLOMBIA left join COINCIDENTE2 on COINCIDENTE2.Codigo=BANCOLOMBIA.COD
GO
/****** Object:  StoredProcedure [dbo].[Actualiza_Campos_Cobrconv]    Script Date: 8/04/2021 9:58:23 a. m. ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
Create procedure [dbo].[Actualiza_Campos_Cobrconv]
as
UPDATE Libranza.dbo.Cobrconv set CONVENIO='0'+CONVENIO where Len(CONVENIO)=3 and ITEM=1 
UPDATE Libranza.dbo.Cobrconv set CONVENIO='00'+CONVENIO where Len(CONVENIO)=2 and ITEM=1 

UPDATE Libranza.dbo.Cobrconv set DEPARTAMENTO=TRIM(DEPARTAMENTO) where ITEM=3
UPDATE Libranza.dbo.Cobrconv set DEPARTAMENTO=replicate('0',12-Len(DEPARTAMENTO))+DEPARTAMENTO+'.00' where  Len(DEPARTAMENTO)<=12 and ITEM=3
UPDATE Libranza.dbo.Cobrconv set FECHA_DE_PAGO=TRIM(FECHA_DE_PAGO) where ITEM=3 
UPDATE Libranza.dbo.Cobrconv set FECHA_DE_PAGO=replicate('0',12-Len(FECHA_DE_PAGO))+FECHA_DE_PAGO+'.00' where  Len(FECHA_DE_PAGO)<=12 and ITEM=3
UPDATE Libranza.dbo.Cobrconv set novedad=trim(novedad)  where ITEM=3 
UPDATE Libranza.dbo.Cobrconv set novedad=replicate(0,6-Len(novedad))+novedad where  Len(novedad)<6 and ITEM=3 
UPDATE Libranza.dbo.Cobrconv set Observaciones=TRIM(Observaciones) where ITEM=3 
UPDATE Libranza.dbo.Cobrconv set Observaciones=replicate(0,6-Len(Observaciones))+Observaciones where  Len(Observaciones)<6 and ITEM=3 

UPDATE Libranza.dbo.Cobrconv set CONVENIO=TRIM(CONVENIO) where ITEM=2
UPDATE Libranza.dbo.Cobrconv set CONVENIO=replicate(0,15-Len(CONVENIO))+CONVENIO where  Len(CONVENIO)<15 and ITEM=2
UPDATE Libranza.dbo.Cobrconv set NIT=TRIM(NIT) where ITEM=2
UPDATE Libranza.dbo.Cobrconv set NIT=replicate(0,15-Len(NIT))+NIT  where  Len(NIT)<15 and ITEM=2
UPDATE Libranza.dbo.Cobrconv set NOMBRE_EMPRESA=TRIM(NOMBRE_EMPRESA) where ITEM=2
UPDATE Libranza.dbo.Cobrconv set NOMBRE_EMPRESA=replicate(0,15-Len(NOMBRE_EMPRESA))+NOMBRE_EMPRESA  where  Len(NOMBRE_EMPRESA)<15 and ITEM=2
UPDATE Libranza.dbo.Cobrconv set DIRECCION=TRIM(DIRECCION) where ITEM=2
UPDATE Libranza.dbo.Cobrconv set DIRECCION=replicate(0,15-Len(DIRECCION))+DIRECCION  where  Len(DIRECCION)<15 and ITEM=2
GO
/****** Object:  StoredProcedure [dbo].[Actualiza_Cobrconv]    Script Date: 8/04/2021 9:58:23 a. m. ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
Create procedure [dbo].[Actualiza_Cobrconv]
as
insert into Libranza.dbo.Cobrconv 
SELECT [ITEM],[CONVENIO],[NIT],[NOMBRE_EMPRESA],[DIRECCION],[TELEFONO],[CIUDAD],[DEPARTAMENTO],[FECHA_DE_PAGO],[correo_electronico_Empresa],[Valor_Pago_Empresa],[novedad],
[Observaciones],[CONVENIO] AS convenio2,[Orden] FROM [Libranza].[dbo].[EMPRESAS] 

UNION all	

SELECT [ITEM],str([Vr_Cuota_Mensual],20,2) AS [Vr_Cuota_Mensual] ,str([Vr_en_Mora] ,20,2) AS [Vr_en_Mora],str([Vr_Total_a_Pagar] ,20,2)AS [Vr_Total_a_Pagar],
str([Vr_Intereses_en_Mora],20,2)AS [Vr_Intereses_en_Mora],'','','','','','','','',[CONVENIO],[Orden] FROM [Libranza].[dbo].[FACTURACION2] 

UNION all 

SELECT [ITEM],[Tipo_de_Documento],[Numero_de_Documento],[Nombre],[No_Obligación],[Código_Oficina],[Nombre_Oficina],str([Vr_Cuota_Mensual],14,0) AS Vr_Cuota_Mensual,
str([Vr_en_Mora],14,0)AS Vr_en_Mora,[Vr_Total_a_Pagar],str([Vr_Intereses_en_Mora],14,0)AS Vr_Intereses_en_Mora,str([Cuotas_Pactadas],14,0)AS Cuotas_Pactadas,[Cuotas_a_Pagar],
[CONVENIO],[Orden] FROM [Libranza].[dbo].[BASE _DE_DATOS_CLIENTES_FINAL]; 

update Libranza.dbo.Cobrconv set correo_electronico_Empresa='',Valor_Pago_Empresa='',novedad='',Observaciones='' where correo_electronico_Empresa is null or 
Valor_Pago_Empresa is null or novedad is null or Observaciones is null 
GO
/****** Object:  StoredProcedure [dbo].[Actualiza_Facturacion]    Script Date: 8/04/2021 9:58:23 a. m. ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
Create procedure [dbo].[Actualiza_Facturacion]
as
insert into [Libranza].[dbo].[Tb_Dinamica]
select CONVENIO, sum(Vr_Cuota_Mensual) as Vr_Cuota_Mensual,SUM(Vr_en_Mora) as Vr_en_Mora,SUM(Vr_Total_a_Pagar) as Vr_Total_a_Pagar,
SUM(Vr_Intereses_en_Mora) as Vr_Intereses_en_Mora from [BASE _DE_DATOS_CLIENTES_FINAL]
group by CONVENIO order by cast(CONVENIO as int)

insert into [Libranza].[dbo].[FACTURACION2]
SELECT ITEM,Vr_Cuota_Mensual_TD,Vr_en_Mora_TD,Vr_Total_a_Pagar_TD,Vr_Intereses_en_Mora_TD,CONVENIO,Orden FROM FACTURACION left join Tb_Dinamica 
on FACTURACION.CONVENIO =Tb_Dinamica.CONVENIO_TD order by cast(CONVENIO as int)

update [Libranza].[dbo].[FACTURACION2] set Vr_Cuota_Mensual='0',Vr_en_Mora='0',Vr_Total_a_Pagar='0',Vr_Intereses_en_Mora='0' 
where Vr_Cuota_Mensual is null or Vr_en_Mora is null or Vr_Total_a_Pagar is null or Vr_Intereses_en_Mora is null
GO
/****** Object:  StoredProcedure [dbo].[Actualizacion_BASE_SALDOS_ACTUALIZADOS_FACTURACION_CORTE]    Script Date: 8/04/2021 9:58:23 a. m. ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
create procedure [dbo].[Actualizacion_BASE_SALDOS_ACTUALIZADOS_FACTURACION_CORTE]
as
UPDATE [Libranza].[dbo].[BASE_SALDOS_ACTUALIZADOS_FACTURACION_CORTE] SET  Vr_Total_a_Pagar=Vr_Cuota_Mensual  
where Vr_en_Mora <> '0' AND mo_dias_vencido_op='0' AND mo_saldo_capital_vencido='0.00'  AND mo_saldo_interes_vencido='0.00'  AND mo_saldo_mora_contingente='0.00'

UPDATE [Libranza].[dbo].[BASE_SALDOS_ACTUALIZADOS_FACTURACION_CORTE] SET Vr_Intereses_en_Mora=0 
where Vr_en_Mora <> '0' AND mo_dias_vencido_op='0' AND mo_saldo_capital_vencido='0.00'  AND mo_saldo_interes_vencido='0.00'  AND mo_saldo_mora_contingente='0.00'

UPDATE [Libranza].[dbo].[BASE_SALDOS_ACTUALIZADOS_FACTURACION_CORTE] SET Vr_en_Mora=0 
where Vr_en_Mora <> '0' AND mo_dias_vencido_op='0' AND mo_saldo_capital_vencido='0.00'  AND mo_saldo_interes_vencido='0.00'  AND mo_saldo_mora_contingente='0.00';
GO
/****** Object:  StoredProcedure [dbo].[ActualizacionCatradia]    Script Date: 8/04/2021 9:58:23 a. m. ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
create procedure [dbo].[ActualizacionCatradia] 
@dtr_concepto as varchar(max)
as
update [Libranza].[dbo].Catradia1 set dtr_monto_mn= REPLACE(dtr_monto_mn,'.00','')  where  dtr_concepto='PAGLIBRANZ' or dtr_concepto=@dtr_concepto
update [Libranza].[dbo].Catradia2 set dtr_monto_mn= REPLACE(dtr_monto_mn,'.00','')  where  dtr_concepto='PAGLIBRANZ' or dtr_concepto=@dtr_concepto
update [Libranza].[dbo].Catradia3 set dtr_monto_mn= REPLACE(dtr_monto_mn,'.00','')  where  dtr_concepto='PAGLIBRANZ' or dtr_concepto=@dtr_concepto
update [Libranza].[dbo].ParametrizadosP set Convenio=REPLICATE('0',4-Len(Convenio))+Convenio
update [Libranza].[dbo].CagmaestP1 set Convenio=REPLICATE('0',4-Len(Convenio))+Convenio where Len(Convenio)<4
update [Libranza].[dbo].CagmaestP2 set Convenio=REPLICATE('0',4-Len(Convenio))+Convenio where Len(Convenio)<4
update [Libranza].[dbo].CagmaestP3 set Convenio=REPLICATE('0',4-Len(Convenio))+Convenio where Len(Convenio)<4
GO
/****** Object:  StoredProcedure [dbo].[ActualizaNoCoincidente]    Script Date: 8/04/2021 9:58:23 a. m. ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
create procedure [dbo].[ActualizaNoCoincidente]
@catradia as varchar (max),
@validar as varchar (max)
as
	if @catradia='Catradia1'
		select NO_COINCIDENTE2.Cod from NO_COINCIDENTE2 inner join Catradia1 on Catradia1.convenio=NO_COINCIDENTE2.Cod
		and NO_COINCIDENTE2.DirenciaCvsB=0 and (Catradia1.dtr_concepto='PAGLIBRANZ'  or Catradia1.dtr_concepto=@validar) AND
		Catradia1.Grado=1 AND NO_COINCIDENTE2.USADO<>'SI' and NO_COINCIDENTE2.DirenciaCvsB=0 group by NO_COINCIDENTE2.Cod order by NO_COINCIDENTE2.Cod 
	else if @catradia='Catradia2'
		select NO_COINCIDENTE2.Cod from NO_COINCIDENTE2 inner join Catradia2 on Catradia2.convenio=NO_COINCIDENTE2.Cod
		and NO_COINCIDENTE2.DirenciaCvsB=0 and (Catradia2.dtr_concepto='PAGLIBRANZ'  or Catradia2.dtr_concepto=@validar) AND
		Catradia2.Grado=2 AND NO_COINCIDENTE2.USADO<>'SI' and NO_COINCIDENTE2.DirenciaCvsB=0 group by NO_COINCIDENTE2.Cod order by NO_COINCIDENTE2.Cod
	else if @catradia='Catradia2'
		select NO_COINCIDENTE2.Cod from NO_COINCIDENTE2 inner join Catradia3 on Catradia3.convenio=NO_COINCIDENTE2.Cod
		and NO_COINCIDENTE2.DirenciaCvsB=0 and (Catradia3.dtr_concepto='PAGLIBRANZ'  or Catradia3.dtr_concepto=@validar) AND
		Catradia3.Grado=3 AND NO_COINCIDENTE2.USADO<>'SI' and NO_COINCIDENTE2.DirenciaCvsB=0 group by NO_COINCIDENTE2.Cod order by NO_COINCIDENTE2.Cod
GO
/****** Object:  StoredProcedure [dbo].[ActualizarNoComparador]    Script Date: 8/04/2021 9:58:23 a. m. ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
create procedure [dbo].[ActualizarNoComparador]
@Convenio as varchar(max),
@validar as varchar(max),
@Grado AS int
as

IF (@Grado=1)
	UPDATE  [Libranza].[dbo].[NO_COINCIDENTE2] SET DirenciaCvsB= C.dtr_monto_mn , USADO='' 
	FROM [Libranza].[dbo].Catradia1 C INNER JOIN [Libranza].[dbo].NO_COINCIDENTE2 N ON 
	C.tr_banco=N.obligacion and N.Cod=@Convenio AND  N.DirenciaCvsB=0
	AND  (C.dtr_concepto='PAGLIBRANZ'  or C.dtr_concepto=@validar)

ELSE IF (@Grado=2)
	UPDATE  [Libranza].[dbo].[NO_COINCIDENTE2] SET DirenciaCvsB= C.dtr_monto_mn , USADO=''  
	FROM [Libranza].[dbo].Catradia2 C INNER JOIN [Libranza].[dbo].NO_COINCIDENTE2 N ON  
	C.tr_banco=N.obligacion and N.Cod=@Convenio  AND  N.DirenciaCvsB=0 
	AND  (C.dtr_concepto='PAGLIBRANZ'  or C.dtr_concepto=@validar)

ELSE IF (@Grado=3)
	UPDATE  [Libranza].[dbo].[NO_COINCIDENTE2] SET DirenciaCvsB= C.dtr_monto_mn , USADO='' 
	FROM [Libranza].[dbo].Catradia3 C INNER JOIN [Libranza].[dbo].NO_COINCIDENTE2 N ON 
	C.tr_banco=N.obligacion and N.Cod=@Convenio  AND  N.DirenciaCvsB=0
	AND  (C.dtr_concepto='PAGLIBRANZ'  or C.dtr_concepto=@validar)
GO
/****** Object:  StoredProcedure [dbo].[BancolombiaVsVlrInteres]    Script Date: 8/04/2021 9:58:23 a. m. ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
create procedure [dbo].[BancolombiaVsVlrInteres]

as
	SELECT a.Cod,(select sum(cast(Valor as float)) from BANCOLOMBIA where COD=a.cod) AS VlrBancolombia,
	 isnull((SELECT SUM([correo_electronico_Empresa]) + SUM([Valor_Pago_Empresa])
	 FROM [Libranza].[dbo].[Cobrconv] where ITEM=3 AND convenio2=a.Cod),0) as VlrCobrconv,
	 isnull((SELECT sum(cast(Vr_total as float))+sum(cast(intereses_mora as float))
	 FROM [Libranza].[dbo].[P_Cobrconv2] where ITEM=3 and convenio2=a.Cod),0)as VlrPCobrconv
	 FROM NO_COINCIDENTE2 a
	 group by a.Cod order by Cod
GO
/****** Object:  StoredProcedure [dbo].[CatradiaVsNoCoincidente]    Script Date: 8/04/2021 9:58:23 a. m. ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
create procedure [dbo].[CatradiaVsNoCoincidente]
@Catradia as varchar(max),
@Convenio as varchar(max)
as

IF (@Catradia='Catradia1')
	insert into [Libranza].dbo.COINCIDENTE SELECT DISTINCT N.[Regional],N.Cod,N.[Nit],N.[Fecha],C.CC,
	C.[NombreC],C.tr_banco,C.dtr_monto_mn,B.OBSERVACION from Catradia1 C inner join NO_COINCIDENTE N 
	on N.Cod=C.convenio and C.[Convenio]=@Convenio inner join BANCOLOMBIA B on B.COD=N.Cod 		

ELSE IF (@Catradia='Catradia2')
	insert into [Libranza].dbo.COINCIDENTE SELECT DISTINCT N.[Regional],N.Cod,N.[Nit],N.[Fecha],C.CC, 
	C.[NombreC],C.tr_banco,C.dtr_monto_mn,B.OBSERVACION from catradia2 C inner join NO_COINCIDENTE N 
	on N.Cod=C.convenio and C.[Convenio]=@Convenio inner join BANCOLOMBIA B on B.COD=N.Cod 

ELSE IF (@Catradia='Catradia3')
	insert into [Libranza].dbo.COINCIDENTE SELECT DISTINCT N.[Regional],N.Cod,N.[Nit],N.[Fecha],C.CC, 
	C.[NombreC],C.tr_banco,C.dtr_monto_mn,B.OBSERVACION from catradia3 C inner join NO_COINCIDENTE N 
	on N.Cod=C.convenio and C.[Convenio]=@Convenio inner join BANCOLOMBIA B on B.COD=N.Cod 
GO
/****** Object:  StoredProcedure [dbo].[comparador]    Script Date: 8/04/2021 9:58:23 a. m. ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
create procedure [dbo].[comparador]
@valor as varchar (max),
@convenio as varchar (max),
@cobrconv as varchar (max)
as
if @cobrconv='Cobrconv'
	insert into  dbo.COINCIDENTE SELECT DISTINCT B.[Regional],B.[COD],B.[Nit],B.[Fecha],C.[NIT],
	C.[NOMBRE_EMPRESA],C.[DIRECCION],C.[DEPARTAMENTO],B.[OBSERVACION] from Cobrconv C inner join BANCOLOMBIA B
	on B.COD=@convenio and B.Valor=@valor and C.convenio2=@convenio and C.ITEM='3'
else if @cobrconv='P_Cobrconv2'
	insert into  dbo.COINCIDENTE SELECT DISTINCT B.[Regional],B.[COD],B.[Nit],B.[Fecha],C.[cedula],
	C.[nombre_cliente],C.[obligacion],C.[Vr_cuota_vigente],B.[OBSERVACION] from P_Cobrconv2 C inner join 
	BANCOLOMBIA B on B.COD=@convenio  and B.Valor=@valor and C.convenio2=@convenio and C.ITEM='3'
else if @cobrconv='P_Cobrconv3'
	insert into  dbo.COINCIDENTE SELECT DISTINCT B.[Regional],B.[COD],B.[Nit],B.[Fecha],C.[cedula],
	C.[nombre_cliente],C.[obligacion],C.[Vr_cuota_vigente],B.[OBSERVACION] from P_Cobrconv3 C inner join 
	BANCOLOMBIA B on B.COD=@convenio and B.Valor=@valor  and C.convenio2=@convenio and C.ITEM='3'
else if @cobrconv='P_Cobrconv4'
	insert into  dbo.COINCIDENTE SELECT DISTINCT B.[Regional],B.[COD],B.[Nit],B.[Fecha],C.[cedula],
	C.[nombre_cliente],C.[obligacion],C.[Vr_cuota_vigente],B.[OBSERVACION] from P_Cobrconv4 C inner join 
	BANCOLOMBIA B on B.COD=@convenio and B.Valor=@valor  and C.convenio2=@convenio and C.ITEM='3'
else if @cobrconv='P_Cobrconv5'
	insert into  dbo.COINCIDENTE SELECT DISTINCT B.[Regional],B.[COD],B.[Nit],B.[Fecha],C.[cedula],
	C.[nombre_cliente],C.[obligacion],C.[Vr_cuota_vigente],B.[OBSERVACION] from P_Cobrconv5 C inner join 
	BANCOLOMBIA B on B.COD=@convenio and B.Valor=@valor  and C.convenio2=@convenio and C.ITEM='3'
else if @cobrconv='P_Cobrconv6'
	insert into  dbo.COINCIDENTE SELECT DISTINCT B.[Regional],B.[COD],B.[Nit],B.[Fecha],C.[cedula],
	C.[nombre_cliente],C.[obligacion],C.[Vr_cuota_vigente],B.[OBSERVACION] from P_Cobrconv6 C inner join 
	BANCOLOMBIA B on B.COD=@convenio and B.Valor=@valor  and C.convenio2=@convenio and C.ITEM='3'
GO
/****** Object:  StoredProcedure [dbo].[ComparadorRepetido]    Script Date: 8/04/2021 9:58:23 a. m. ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
create procedure [dbo].[ComparadorRepetido]
@Cobrconv as varchar(max),
@Convenio as varchar(max)
as

IF (@Cobrconv='Cobrconv')
	insert into  dbo.COINCIDENTE 
	SELECT B.REGIONAL,B.Codigo,B.[Nit],B.[Fecha],C.[NIT],C.[NOMBRE_EMPRESA],C.[DIRECCION],
	C.[DEPARTAMENTO],B.OBSERVACION from Cobrconv C inner join COINCIDENTE2 B on B.Codigo=C.convenio2  and 
	B.Codigo=@Convenio and C.ITEM='3' 

ELSE IF (@Cobrconv='P_Cobrconv2')
	insert into  dbo.COINCIDENTE 
	SELECT B.REGIONAL,B.Codigo,B.[Nit],B.[Fecha],C.[cedula],C.[nombre_cliente],C.[obligacion],
	C.[Vr_cuota_vigente],B.OBSERVACION from P_Cobrconv2 C inner join COINCIDENTE2 B on B.Codigo=C.convenio2  and 
	B.Codigo=@Convenio and C.ITEM='3' 

ELSE IF (@Cobrconv='P_Cobrconv3')
	insert into  dbo.COINCIDENTE 
	SELECT B.REGIONAL,B.Codigo,B.[Nit],B.[Fecha],C.[cedula],C.[nombre_cliente],C.[obligacion],
	C.[Vr_cuota_vigente],B.OBSERVACION from P_Cobrconv3 C inner join COINCIDENTE2 B on B.Codigo=C.convenio2  and 
	B.Codigo=@Convenio and C.ITEM='3' 

ELSE IF (@Cobrconv='P_Cobrconv4')
	insert into  dbo.COINCIDENTE 
	SELECT B.REGIONAL,B.Codigo,B.[Nit],B.[Fecha],C.[cedula],C.[nombre_cliente],C.[obligacion],
	C.[Vr_cuota_vigente],B.OBSERVACION from P_Cobrconv4 C inner join COINCIDENTE2 B on B.Codigo=C.convenio2  and 
	B.Codigo=@Convenio and C.ITEM='3' 

ELSE IF (@Cobrconv='P_Cobrconv5')
	insert into  dbo.COINCIDENTE 
	SELECT B.REGIONAL,B.Codigo,B.[Nit],B.[Fecha],C.[cedula],C.[nombre_cliente],C.[obligacion],
	C.[Vr_cuota_vigente],B.OBSERVACION from P_Cobrconv5 C inner join COINCIDENTE2 B on B.Codigo=C.convenio2  and 
	B.Codigo=@Convenio and C.ITEM='3' 

ELSE IF (@Cobrconv='P_Cobrconv6')
	insert into  dbo.COINCIDENTE 
	SELECT B.REGIONAL,B.Codigo,B.[Nit],B.[Fecha],C.[cedula],C.[nombre_cliente],C.[obligacion],
	C.[Vr_cuota_vigente],B.OBSERVACION from P_Cobrconv6 C inner join COINCIDENTE2 B on B.Codigo=C.convenio2  and 
	B.Codigo=@Convenio and C.ITEM='3' 
GO
/****** Object:  StoredProcedure [dbo].[Depura_Cobrconv]    Script Date: 8/04/2021 9:58:23 a. m. ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
Create procedure [dbo].[Depura_Cobrconv]
as
 IF OBJECT_ID (N'[Cobrconv]', N'U') IS NOT NULL 
 truncate table [Cobrconv];
GO
/****** Object:  StoredProcedure [dbo].[Elimina_BASE_DE_DATOS_CLIENTES_FINAL]    Script Date: 8/04/2021 9:58:23 a. m. ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
create procedure [dbo].[Elimina_BASE_DE_DATOS_CLIENTES_FINAL]
as
IF OBJECT_ID (N'[BASE _DE_DATOS_CLIENTES_FINAL]', N'U') IS NOT NULL 
 DROP TABLE [Libranza].[dbo].[BASE _DE_DATOS_CLIENTES_FINAL]
GO
/****** Object:  StoredProcedure [dbo].[Elimina_BASE_SALDOS_ACTUALIZADOS_FACTURACION_CORTE]    Script Date: 8/04/2021 9:58:23 a. m. ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
Create procedure [dbo].[Elimina_BASE_SALDOS_ACTUALIZADOS_FACTURACION_CORTE]
as
IF OBJECT_ID (N'[BASE_SALDOS_ACTUALIZADOS_FACTURACION_CORTE]', N'U') IS NOT NULL 
DROP TABLE [Libranza].[dbo].[BASE_SALDOS_ACTUALIZADOS_FACTURACION_CORTE]
GO
/****** Object:  StoredProcedure [dbo].[Elimina_Nulls]    Script Date: 8/04/2021 9:58:23 a. m. ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
Create procedure [dbo].[Elimina_Nulls]
as
 update BASE_CONSOLIDADA_CLIENTES_LIBRANZA set correo_electronico_Empresa='',Valor_Pago_Empresa='',novedad='',
Observaciones='' where correo_electronico_Empresa is null and  Valor_Pago_Empresa is null and novedad is null and Observaciones is null

update BASE_SALDOS_ACTUALIZADOS_FACTURACION_CORTE set Correo_electronico_Empresa='',
Valor_Pago_Empresa='',novedad='',Observaciones='' where Correo_electronico_Empresa is null and Valor_Pago_Empresa is null
and novedad is null and Observaciones is null
GO
/****** Object:  StoredProcedure [dbo].[EliminaBaseConsolidadoCliente]    Script Date: 8/04/2021 9:58:23 a. m. ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
Create procedure [dbo].[EliminaBaseConsolidadoCliente]
as
IF OBJECT_ID (N'[BASE_CONSOLIDADA_CLIENTES_LIBRANZA]', N'U') IS NOT NULL 
DROP TABLE [Libranza].[dbo].[BASE_CONSOLIDADA_CLIENTES_LIBRANZA]
GO
/****** Object:  StoredProcedure [dbo].[EliminaRepetidos]    Script Date: 8/04/2021 9:58:23 a. m. ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
create procedure [dbo].[EliminaRepetidos]

as
DELETE [Libranza].[dbo].[NO_COINCIDENTE2] WHERE Orden IN(
SELECT A.Orden  FROM NO_COINCIDENTE2 A INNER JOIN NO_COINCIDENTE2 B ON A.Cod =B.Cod AND A.Diferencia=B.Diferencia
 AND A.CC=B.CC AND A.nombre=B.nombre AND A.obligacion=B.obligacion AND A.ORDEN>B.ORDEN) 
GO
/****** Object:  StoredProcedure [dbo].[EstadoCancelado]    Script Date: 8/04/2021 9:58:23 a. m. ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
create procedure [dbo].[EstadoCancelado]
as
delete from [Libranza].[dbo].[BASE_SALDOS_ACTUALIZADOS_FACTURACION_CORTE] where  mo_estado_obligacion='CANCELADO'
GO
/****** Object:  StoredProcedure [dbo].[Generar_BASE_CONSOLIDADA_CLIENTES_LIBRANZA]    Script Date: 8/04/2021 9:58:23 a. m. ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
Create procedure [dbo].[Generar_BASE_CONSOLIDADA_CLIENTES_LIBRANZA]
as
	SELECT E.[CONVENIO], E.[NIT], E.[NOMBRE_EMPRESA], E.[FECHA_DE_PAGO], E.[correo_electronico_Empresa], C.[ITEM], C.[Tipo_de_Documento], 
	C.[Numero_de_Documento], C.[Nombre], C.[No_Obligación], C.[Código_Oficina], C.[Nombre_Oficina], C.[Vr_Cuota_Mensual], C.[Vr_en_Mora], C.[Vr_Total_a_Pagar],
	C.[Vr_Intereses_en_Mora], C.[Cuotas_Pactadas], C.[Cuotas_a_Pagar], E.[Valor_Pago_Empresa], E.[novedad], E.[Observaciones], C.[Orden]  
	into [Libranza].[dbo].[BASE_CONSOLIDADA_CLIENTES_LIBRANZA]
	FROM ([CLIENTES] C LEFT JOIN [BASE_DESVINCULADO] B ON C.[No_Obligación] = B.[mo_numero_de_banco]) LEFT JOIN [EMPRESAS] E ON C.[CONVENIO] = E.[CONVENIO] 
	where (((B.[mo_numero_de_banco]) Is Null))
GO
/****** Object:  StoredProcedure [dbo].[Generar_BASE_DE_DATOS_CLIENTES_FINAL]    Script Date: 8/04/2021 9:58:23 a. m. ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
Create procedure [dbo].[Generar_BASE_DE_DATOS_CLIENTES_FINAL]
as
	SELECT B.[ITEM], B.[Tipo_de_Documento],B.[Numero_de_Documento],B.[Nombre],
	B.[No_Obligación],B.[Código_Oficina],B.[Nombre_Oficina],B.[Vr_Cuota_Mensual],
	B.[Vr_en_Mora],B.[Vr_Total_a_Pagar],B.[Vr_Intereses_en_Mora],B.[Cuotas_Pactadas],
	B.[Cuotas_a_Pagar],B.[Valor_Pago_Empresa],B.[novedad],B.[Observaciones],
	B.[CONVENIO],B.[Orden]
	INTO [Libranza].[dbo].[BASE _DE_DATOS_CLIENTES_FINAL]
	FROM [Libranza].[dbo].[BASE_SALDOS_ACTUALIZADOS_FACTURACION_CORTE] B
GO
/****** Object:  StoredProcedure [dbo].[Generar_BASE_SALDOS_ACTUALIZADOS_FACTURACION_CORTE]    Script Date: 8/04/2021 9:58:23 a. m. ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
Create procedure [dbo].[Generar_BASE_SALDOS_ACTUALIZADOS_FACTURACION_CORTE]
as
SELECT BC.[CONVENIO], BC.[NIT], BC.[NOMBRE_EMPRESA], BC.[FECHA_DE_PAGO], BC.[Correo_electronico_Empresa],
BC.[ITEM], BC.[Tipo_de_Documento], BC.[Numero_de_Documento], BC.[Nombre],
('********' + substring (BC.[No_Obligación],9,7)) AS [Obligación], 
([CONVENIO] + trim([Numero_de_Documento]) + ('********' + substring (BC.[No_Obligación],9,7))) AS [LLAVE],
BC.[No_Obligación], BC.[Código_Oficina], BC.[Nombre_Oficina], BC.[Vr_Cuota_Mensual],
BC.[Vr_en_Mora], BC.[Vr_Total_a_Pagar], BC.[Vr_Intereses_en_Mora], BC.[Cuotas_Pactadas], BC.[Cuotas_a_Pagar],
BC.[Valor_Pago_Empresa], BC.[novedad], BC.[Observaciones], C.[mo_dias_vencido_op],
C.[mo_fecha_prox_vencimiento],
C.[mo_saldo_cuota_prox_venc], C.[mo_saldo_capital_vigente],
C.[mo_saldo_capital_vencido], C.[mo_saldo_interes_vigente],
C.[mo_saldo_interes_vencido], C.[mo_saldo_interes_contingente],
C.[mo_saldo_mora_contingente], C.[mo_saldo_seguro_vida_vigente],
C.[mo_saldo_seguro_vida_vencido], C.[mo_saldo_otros_vigente],
C.[mo_saldo_otros_vencidos], C.[mo_estado_obligacion],
C.[mo_fecha_ult_pago], C.[mo_valor_ult_pago], 
C.[mo_numero_cuotas_vencidas], BC.[Orden]
INTO [BASE_SALDOS_ACTUALIZADOS_FACTURACION_CORTE]
FROM [BASE_CONSOLIDADA_CLIENTES_LIBRANZA]  BC LEFT JOIN [Cagmaest] C ON BC.[No_Obligación] = C.[mo_numero_de_banco]
GO
/****** Object:  StoredProcedure [dbo].[Largo_Convenio]    Script Date: 8/04/2021 9:58:23 a. m. ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
Create procedure [dbo].[Largo_Convenio]
as
UPDATE Libranza.dbo.Parametrizados set Convenio=TRIM(Convenio)  
UPDATE Libranza.dbo.Parametrizados set Convenio=replicate('0',4-Len(Convenio))+Convenio where  Len(Convenio)<4 
UPDATE Libranza.dbo.BASE_SALDOS_ACTUALIZADOS_FACTURACION_CORTE set CONVENIO=TRIM(CONVENIO)  
UPDATE Libranza.dbo.BASE_SALDOS_ACTUALIZADOS_FACTURACION_CORTE set CONVENIO=replicate('0',4-Len(CONVENIO))+CONVENIO where  Len(CONVENIO)<4 
GO
/****** Object:  StoredProcedure [dbo].[Limpia_Tablas]    Script Date: 8/04/2021 9:58:23 a. m. ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
Create procedure [dbo].[Limpia_Tablas]
as
 IF OBJECT_ID (N'[FACTURACION2]', N'U') IS NOT NULL 
truncate table [FACTURACION2]; 
 IF OBJECT_ID (N'[Tb_Dinamica]', N'U') IS NOT NULL
truncate table [Tb_Dinamica];
GO
/****** Object:  StoredProcedure [dbo].[LLenadoCatradia]    Script Date: 8/04/2021 9:58:23 a. m. ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
create procedure [dbo].[LLenadoCatradia] 
@dtr_concepto as varchar(max)
as
update  [Libranza].[dbo].[Catradia1]  set [Catradia1].Regional=ParametrizadosP.Regional,[Catradia1].Convenio=CagmaestP1.Convenio,
 CC=CagmaestP1.[CedulaC],NombreC=CagmaestP1.[NombreC],Grado=1 from   [Libranza].[dbo].[Catradia1] inner join [Libranza].[dbo].CagmaestP1
 on [Catradia1].tr_banco=CagmaestP1.Obligacion inner  join [Libranza].[dbo].ParametrizadosP on ParametrizadosP.Convenio= CagmaestP1.Convenio 
 and ([Catradia1].dtr_concepto='PAGLIBRANZ'  or [catradia1].dtr_concepto=@dtr_concepto)--//Actualiza el catradia1 vs cagmaest 1 

 update  [Libranza].[dbo].[catradia2]  set [catradia2].Regional=ParametrizadosP.Regional,[catradia2].Convenio=CagmaestP2.Convenio, 
 CC=CagmaestP2.[CedulaC],NombreC=CagmaestP2.[NombreC],Grado=2 from   [Libranza].[dbo].[catradia2] inner join [Libranza].[dbo].CagmaestP2 
 on  [catradia2].tr_banco=CagmaestP2.Obligacion inner  join [Libranza].[dbo].ParametrizadosP on ParametrizadosP.Convenio= CagmaestP2.Convenio 
 and ([catradia2].dtr_concepto='PAGLIBRANZ'  or [catradia2].dtr_concepto=@dtr_concepto)--"//Actualiza el catradia 2 vs cagmaest 2

 update  [Libranza].[dbo].[Catradia3]  set [Catradia3].Regional=ParametrizadosP.Regional,[Catradia3].Convenio=CagmaestP3.Convenio, 
 CC=CagmaestP3.[CedulaC],NombreC=CagmaestP3.[NombreC],Grado=3 from   [Libranza].[dbo].[Catradia3] inner join [Libranza].[dbo].CagmaestP3
 on  [Catradia3].tr_banco=CagmaestP3.Obligacion inner  join [Libranza].[dbo].ParametrizadosP on ParametrizadosP.Convenio= CagmaestP3.Convenio 
and ([Catradia3].dtr_concepto='PAGLIBRANZ' or [catradia3].dtr_concepto=@dtr_concepto)--"//Actualiza el catradia 3 vs cagmaest 3

update  [Libranza].[dbo].[Catradia1]  set [Catradia1].Regional=ParametrizadosP.Regional,[Catradia1].Convenio=CagmaestP2.Convenio, 
 CC=CagmaestP2.[CedulaC],NombreC=CagmaestP2.[NombreC],Grado=1 from   [Libranza].[dbo].[Catradia1] inner join [Libranza].[dbo].CagmaestP2  
 on [Catradia1].tr_banco=CagmaestP2.Obligacion inner  join [Libranza].[dbo].ParametrizadosP on ParametrizadosP.Convenio= CagmaestP2.Convenio  
 and ([Catradia1].dtr_concepto='PAGLIBRANZ'  or [catradia1].dtr_concepto=@dtr_concepto) AND [catradia1].Regional='' --"//Actualiza el catradia 1 vs cagmaest 2

 update  [Libranza].[dbo].[Catradia1]  set [Catradia1].Regional=ParametrizadosP.Regional,[Catradia1].Convenio=CagmaestP3.Convenio,  
 CC=CagmaestP3.[CedulaC],NombreC=CagmaestP3.[NombreC],Grado=1 from   [Libranza].[dbo].[Catradia1] inner join [Libranza].[dbo].CagmaestP3  
 on [Catradia1].tr_banco=CagmaestP3.Obligacion inner  join [Libranza].[dbo].ParametrizadosP on ParametrizadosP.Convenio= CagmaestP3.Convenio  
 and ([Catradia1].dtr_concepto='PAGLIBRANZ'  or [catradia1].dtr_concepto=@dtr_concepto) AND [catradia1].Regional=''-- " //Actualiza el catradia 1 vs cagmaest 3

 update  [Libranza].[dbo].[Catradia2]  set [Catradia2].Regional=ParametrizadosP.Regional,[Catradia2].Convenio=CagmaestP3.Convenio,  
 CC=CagmaestP3.[CedulaC],NombreC=CagmaestP3.[NombreC],Grado=2 from   [Libranza].[dbo].[Catradia2] inner join [Libranza].[dbo].CagmaestP3  
 on [Catradia2].tr_banco=CagmaestP3.Obligacion inner  join [Libranza].[dbo].ParametrizadosP on ParametrizadosP.Convenio= CagmaestP3.Convenio  
 and ([Catradia2].dtr_concepto='PAGLIBRANZ'  or [catradia2].dtr_concepto=@dtr_concepto) AND [catradia2].Regional='' --";//Actualiza el catradia 2 vs cagmaest 3
GO
/****** Object:  StoredProcedure [dbo].[LLenadoNoCoincidente]    Script Date: 8/04/2021 9:58:23 a. m. ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE procedure [dbo].[LLenadoNoCoincidente]
@Cobrconv as varchar(max),
@Convenio as varchar(max),
@Diferencia as float,
@ValorBanco as varchar(max)
as


if (@Cobrconv='Cobrconv')
	insert into  dbo.NO_COINCIDENTE
	SELECT B.[Fecha],B.[Nit],B.[Convenio],B.[COD],B.[F_Abono],B.[Valor],cast(C.CONVENIO as float) as Vr_Cuota_Mensual,@Diferencia,B.[Regional]
	,'','','','','','','','','','','','','','',1 from BANCOLOMBIA B inner join Cobrconv C on B.COD=@Convenio
	and B.Valor=@ValorBanco and C.convenio2=@Convenio and C.ITEM=2

else if(@Cobrconv='P_Cobrconv2')
	insert into  dbo.NO_COINCIDENTE 
	SELECT B.[Fecha],B.[Nit],B.[Convenio],B.[COD],B.[F_Abono],B.[Valor],cast(C.tipo_de_documento as float)as Vr_Cuota_Mensual,@Diferencia,[Regional]
	,'','','','','','','','','','','','','','',2 from BANCOLOMBIA B inner join P_Cobrconv2 C on B.COD=@Convenio
	and B.Valor=@ValorBanco and C.convenio2=@Convenio and C.ITEM=2

else if(@Cobrconv='P_Cobrconv3')
	insert into  dbo.NO_COINCIDENTE 
	SELECT B.[Fecha],B.[Nit],B.[Convenio],B.[COD],B.[F_Abono],B.[Valor],cast(C.tipo_de_documento as float)as Vr_Cuota_Mensual,@Diferencia,[Regional]
	,'','','','','','','','','','','','','','',3 from BANCOLOMBIA B	inner join P_Cobrconv3 C on B.COD=@Convenio
	and B.Valor=@ValorBanco and C.convenio2=@Convenio and C.ITEM=2

else if(@Cobrconv='P_Cobrconv4')
	insert into  dbo.NO_COINCIDENTE 
	SELECT B.[Fecha],B.[Nit],B.[Convenio],B.[COD],B.[F_Abono],B.[Valor],cast(C.tipo_de_documento as float)as Vr_Cuota_Mensual,@Diferencia,[Regional]
	,'','','','','','','','','','','','','','',4 from BANCOLOMBIA B	inner join P_Cobrconv4 C on B.COD=@Convenio
	and B.Valor=@ValorBanco and C.convenio2=@Convenio and C.ITEM=2

else if(@Cobrconv='P_Cobrconv5')
	insert into  dbo.NO_COINCIDENTE 
	SELECT B.[Fecha],B.[Nit],B.[Convenio],B.[COD],B.[F_Abono],B.[Valor],cast(C.tipo_de_documento as float)as Vr_Cuota_Mensual,@Diferencia,[Regional]
	,'','','','','','','','','','','','','','',5 from BANCOLOMBIA B	inner join P_Cobrconv5 C on B.COD=@Convenio
	and B.Valor=@ValorBanco and C.convenio2=@Convenio and C.ITEM=2

else if(@Cobrconv='P_Cobrconv6')
	insert into  dbo.NO_COINCIDENTE 
	SELECT B.[Fecha],B.[Nit],B.[Convenio],B.[COD],B.[F_Abono],B.[Valor],cast(C.tipo_de_documento as float)as Vr_Cuota_Mensual,@Diferencia,[Regional]
	,'','','','','','','','','','','','','','',6 from BANCOLOMBIA B	inner join P_Cobrconv6 C on B.COD=@Convenio
	and B.Valor=@ValorBanco and C.convenio2=@Convenio and C.ITEM=2

else
	insert into  dbo.NO_COINCIDENTE 
	SELECT B.[Fecha],B.[Nit],B.[Convenio],B.[COD],B.[F_Abono],B.[Valor],0,@ValorBanco,[Regional],'','','','','','','','','','','','','','',7
	from BANCOLOMBIA B where  B.Valor=@ValorBanco and Cod=@Convenio	
GO
/****** Object:  StoredProcedure [dbo].[LLenadoNoCoincidente2]    Script Date: 8/04/2021 9:58:23 a. m. ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE procedure [dbo].[LLenadoNoCoincidente2]
@Convenio as varchar(max),
@Diferencia as float,
@Grado AS int
as

IF (@Grado=1)
	insert into  dbo.NO_COINCIDENTE2  
	SELECT distinct N.[Fecha],N.[Nit],N.[Convenio],N.[COD],N.[F_Abono],
	N.Bancolombia as valor,N.Vr_Cuota_Mensual,@Diferencia AS DIFERENCIA,N.[Regional],
	C.NIT AS CC,C.NOMBRE_EMPRESA AS NOMBRE,C.DIRECCION AS OBLIGACION,C.CIUDAD AS nomOficina,
	C.novedad AS CPACTADAS,C.Observaciones AS CPAGAR,C.DEPARTAMENTO AS CMENSUAL,
	C.FECHA_DE_PAGO AS VR_MORA,C.correo_electronico_Empresa AS VRTPAGAR,C.Valor_Pago_Empresa AS VrIMORA,
	''AS VrPEMPRESA,''AS NOVEDAD,''AS OBSERVACION,0,'' from NO_COINCIDENTE N inner join Cobrconv C on 
	N.cod=C.convenio2 and N.Cod=@Convenio and C.ITEM=3 

ELSE IF (@Grado=2)
	insert into  dbo.NO_COINCIDENTE2  
	SELECT distinct N.[Fecha],N.[Nit],N.[Convenio],N.[COD],N.[F_Abono], 
	N.Bancolombia as valor,N.Vr_Cuota_Mensual,@Diferencia AS DIFERENCIA,N.[Regional], 
	C.cedula,C.nombre_cliente,C.obligacion,C.oficina, 
	C.plazo AS CPACTADAS,C.no_cuenta AS CPAGAR,C.Vr_cuota_vigente AS CMENSUAL, 
	C.Vr_cuota_mora AS VR_MORA,C.Vr_total AS VRTPAGAR,C.intereses_mora AS VrIMORA, 
	''AS VrPEMPRESA,''AS NOVEDAD,''AS OBSERVACION,0,'' from NO_COINCIDENTE N inner join P_Cobrconv2 C on 
	N.cod=C.convenio2 and N.Cod=@Convenio and C.ITEM=3 

ELSE IF (@Grado=3)
	insert into  dbo.NO_COINCIDENTE2  
	SELECT distinct N.[Fecha],N.[Nit],N.[Convenio],N.[COD],N.[F_Abono], 
	N.Bancolombia as valor,N.Vr_Cuota_Mensual,@Diferencia AS DIFERENCIA,N.[Regional], 
	C.cedula,C.nombre_cliente,C.obligacion,C.oficina, 
	C.plazo AS CPACTADAS,C.no_cuenta AS CPAGAR,C.Vr_cuota_vigente AS CMENSUAL, 
	C.Vr_cuota_mora AS VR_MORA,C.Vr_total AS VRTPAGAR,C.intereses_mora AS VrIMORA, 
	''AS VrPEMPRESA,''AS NOVEDAD,''AS OBSERVACION,0,'' from NO_COINCIDENTE N inner join P_Cobrconv3 C on 
	N.cod=C.convenio2 and N.Cod=@Convenio and C.ITEM=3 

ELSE IF (@Grado=4)
	insert into  dbo.NO_COINCIDENTE2  
	SELECT distinct N.[Fecha],N.[Nit],N.[Convenio],N.[COD],N.[F_Abono], 
	N.Bancolombia as valor,N.Vr_Cuota_Mensual,@Diferencia AS DIFERENCIA,N.[Regional], 
	C.cedula,C.nombre_cliente,C.obligacion,C.oficina, 
	C.plazo AS CPACTADAS,C.no_cuenta AS CPAGAR,C.Vr_cuota_vigente AS CMENSUAL, 
	C.Vr_cuota_mora AS VR_MORA,C.Vr_total AS VRTPAGAR,C.intereses_mora AS VrIMORA, 
	''AS VrPEMPRESA,''AS NOVEDAD,''AS OBSERVACION,0,'' from NO_COINCIDENTE N inner join P_Cobrconv4 C on 
	N.cod=C.convenio2 and N.Cod=@Convenio and C.ITEM=3 

ELSE IF (@Grado=5)
	insert into  dbo.NO_COINCIDENTE2  
	SELECT distinct N.[Fecha],N.[Nit],N.[Convenio],N.[COD],N.[F_Abono], 
	N.Bancolombia as valor,N.Vr_Cuota_Mensual,@Diferencia AS DIFERENCIA,N.[Regional], 
	C.cedula,C.nombre_cliente,C.obligacion,C.oficina, 
	C.plazo AS CPACTADAS,C.no_cuenta AS CPAGAR,C.Vr_cuota_vigente AS CMENSUAL, 
	C.Vr_cuota_mora AS VR_MORA,C.Vr_total AS VRTPAGAR,C.intereses_mora AS VrIMORA, 
	''AS VrPEMPRESA,''AS NOVEDAD,''AS OBSERVACION,0,'' from NO_COINCIDENTE N inner join P_Cobrconv5 C on 
	N.cod=C.convenio2 and N.Cod=@Convenio and C.ITEM=3 

ELSE IF (@Grado=6)
	insert into  dbo.NO_COINCIDENTE2  
	SELECT distinct N.[Fecha],N.[Nit],N.[Convenio],N.[COD],N.[F_Abono], 
	N.Bancolombia as valor,N.Vr_Cuota_Mensual,@Diferencia AS DIFERENCIA,N.[Regional], 
	C.cedula,C.nombre_cliente,C.obligacion,C.oficina, 
	C.plazo AS CPACTADAS,C.no_cuenta AS CPAGAR,C.Vr_cuota_vigente AS CMENSUAL, 
	C.Vr_cuota_mora AS VR_MORA,C.Vr_total AS VRTPAGAR,C.intereses_mora AS VrIMORA, 
	''AS VrPEMPRESA,''AS NOVEDAD,''AS OBSERVACION,0,'' from NO_COINCIDENTE N inner join P_Cobrconv6 C on 
	N.cod=C.convenio2 and N.Cod=@Convenio and C.ITEM=3 

ELSE 
	insert into  dbo.NO_COINCIDENTE2
	SELECT B.[Fecha],B.[Nit],B.[Convenio],B.[COD],B.[F_Abono],
	B.[Valor],0,@Diferencia,[Regional],0,'-',0,'-','','','','','','','','','',0,'' from BANCOLOMBIA B where  B.Valor=@Diferencia and Cod=@Convenio
GO
/****** Object:  StoredProcedure [dbo].[SumaVlrInteresVSBancolombia]    Script Date: 8/04/2021 9:58:23 a. m. ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
create procedure [dbo].[SumaVlrInteresVSBancolombia]
@Cobrconv as varchar (max),
@convenio as varchar (max)
as
if @Cobrconv='Cobrconv'
	SELECT isnull(SUM([correo_electronico_Empresa]) + SUM([Valor_Pago_Empresa]),0)
	FROM [Libranza].[dbo].[Cobrconv] where ITEM=3 AND convenio2=@convenio
else if @Cobrconv='P_Cobrconv2'
	SELECT isnull(sum(cast(Vr_total as float))+sum(cast(intereses_mora as float)),0)
	FROM [Libranza].[dbo].[P_Cobrconv2] where ITEM=3 and convenio2=@convenio
GO
