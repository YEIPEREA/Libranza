/*Facturacion*/
package graficos;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.SystemColor;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.io.File;
import java.sql.SQLException;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.border.EmptyBorder;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.table.DefaultTableModel;

import CapaEjecucion.Ejecucion;
import CapaEmail.Email;
import CapaNegocio.cargaTXR;
import CargaDatos.conexionBD;
import GenerarExcel.Generar;

import GenerarExcel.EcriptadoExcel;

import javax.swing.JLayeredPane;
import javax.swing.ImageIcon;

import java.awt.Panel;
import java.awt.Label;

import com.jgoodies.forms.factories.DefaultComponentFactory;

import java.awt.Dialog.ModalExclusionType;
import java.awt.Window.Type;

/**
 * @author yeiperea
 * @version 2.6.1
 */

public class MENÚ extends JFrame {

	private String valida;
	private String Email;
	public JPanel contentPane;
	private JTextField txtCusersingYeisonPdesktopquincena;
	private JTextField textField_1;
	private JTextField txtProcesamiento;
	private JLabel labelPorcentaje;
	private JPanel panelPorcentaje;
	private JCheckBox checkbxDesvinculado;
	private JCheckBox chckbxCagmaest;
	private JCheckBox chckbxCobrconv;
	private JCheckBox chckbxParameizacion;
	private JTextField textFieldDireccion;
	private JPanel JpDesv;
	private JPanel JpCag;
	private JPanel JpCobr;
	private JPanel JpPara;
	private JButton btnEmail;
	private JCheckBox ChkBxPrueba;
	private JLabel labeProcesamiento;
	private JPanel panelProcesamiento;
	private JPanel panelDocumento;
	private JLabel labelDocumento;
	private JTextField txtEmail;
	private JPanel panelFecha;
	private JLabel labelFecha;
	private JLabel lblIndicadores;
	private JTextField textField_2;
	private JButton button;
	conexionBD base = new conexionBD();

	public void main(String[] args) {
		// public void MenuFacturacion() {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MENÚ frame = new MENÚ();
					frame.setVisible(true);

				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

//cerrar ventana
	public void CerrarV() {

		this.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent we) {
				CerrarVentana();
			}
		});
		this.setVisible(true);
	}

	public void CerrarVentana() {
		String Botones[] = { "Aceptar", "Cancelar" };
		int eleccion = JOptionPane.showOptionDialog(this, "¿ Está seguro de querer cerrar la aplicación? ",
				"Advertencia", 0, 0, null, Botones, this);
		if (eleccion == JOptionPane.YES_OPTION) {
			JOptionPane.showMessageDialog(null, "Gracias por su visita, hasta pronto", "Gracias",
					JOptionPane.INFORMATION_MESSAGE);
			System.exit(0);
		}
	}

//public MENÚ() {

	public void MenuFacturacion() {

		setAutoRequestFocus(false);
		setResizable(false);
		setType(Type.POPUP);
		conexionBD close = new conexionBD();
		setBackground(Color.BLACK);

		Toolkit miPantalla = Toolkit.getDefaultToolkit();
		Dimension tamanoPantalla = miPantalla.getScreenSize();// devuelve el tamaño de la pantalla
		int altura = tamanoPantalla.height;// altO de pantalla
		int ancho = tamanoPantalla.width;// ancho de pantalla
		setBounds(ancho / 3, altura / 6, 865, 545);

		CerrarV();// pregunta si quieres cerrar la ventana

		setTitle("Facturación");
		setIconImage(Toolkit.getDefaultToolkit().getImage(Seleccion.class.getResource("/Imagenes/BAC.png")));

		contentPane = new JPanel();
		contentPane.setBackground(new Color(255, 255, 255));// fondo de la ventana
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

//menu desplegable archivos
		JComboBox comboBox = new JComboBox();
		comboBox.setBackground(SystemColor.textHighlightText);// color del menu desplegable
		comboBox.setForeground(Color.BLACK);// color de letras
		comboBox.setFont(new Font("Arial", Font.PLAIN, 17));// tipo de letra del menu deplegable
		comboBox.setMaximumRowCount(10);
		comboBox.setBounds(11, 195, 444, 25);
// comboBox.getColorModel(Color.cyan);
		comboBox.setModel(new DefaultComboBoxModel(new String[] { "Seleccione un archivo ...", "Cobrconv",
				"Desvinculado", "Parametrizaci\u00F3n", "Cagmaest" }));
		comboBox.setSelectedIndex(0);
		contentPane.add(comboBox);

//menu desplegable regional
		JComboBox comboBoxEmail = new JComboBox();
		comboBoxEmail.setEnabled(false);
		comboBoxEmail.setModel(new DefaultComboBoxModel(new String[] { "Seleccionar Regional...", "ANTIOQUIA",
				"BOGOT\u00C1", "CAFETERA", "COSTA", "OCCIDENTE", "ORIENTE", "SANTANDERES", "SUR", "TODOS" }));
		comboBoxEmail.setMaximumRowCount(10);
		comboBoxEmail.setForeground(Color.BLACK);
		comboBoxEmail.setFont(new Font("Arial", Font.PLAIN, 17));
		comboBoxEmail.setBackground(Color.WHITE);
		comboBoxEmail.setBounds(11, 438, 444, 25);
		contentPane.add(comboBoxEmail);

		JLabel lblProceso = new JLabel("Porcentaje de Carga");
		lblProceso.setForeground(SystemColor.windowBorder);
		lblProceso.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblProceso.setBounds(11, 268, 129, 25);
		contentPane.add(lblProceso);

//muestra el porcentaje de carga
		panelPorcentaje = new JPanel();
		panelPorcentaje.setForeground(SystemColor.windowBorder);
		panelPorcentaje.setBackground(Color.WHITE);
		panelPorcentaje.setBounds(146, 270, 408, 23);
		contentPane.add(panelPorcentaje);
		panelPorcentaje.setLayout(null);

		labelPorcentaje = new JLabel();
		labelPorcentaje.setBounds(0, 0, 408, 23);
		panelPorcentaje.add(labelPorcentaje);
		labelPorcentaje.setForeground(SystemColor.windowBorder);
		labelPorcentaje.setBackground(Color.WHITE);

		JButton btnCargar = new JButton("Cargar");// boton cargar
		btnCargar.setForeground(SystemColor.windowText);
		btnCargar.setBackground(UIManager.getColor("Button.background"));
		btnCargar.setEnabled(false);

		JLabel lblFacturacon = new JLabel("FACTURACI\u00D3N");// titulo
		lblFacturacon.setFont(new Font("Tahoma", Font.BOLD, 20));
		lblFacturacon.setHorizontalAlignment(SwingConstants.CENTER);
		lblFacturacon.setBounds(11, 125, 824, 25);
		contentPane.add(lblFacturacon);

//proceso de ejecucion en porcentaje
		panelProcesamiento = new JPanel();
		panelProcesamiento.setLayout(null);
		panelProcesamiento.setForeground(SystemColor.windowBorder);
		panelProcesamiento.setBackground(Color.WHITE);
		panelProcesamiento.setBounds(11, 369, 804, 23);
		contentPane.add(panelProcesamiento);

		labeProcesamiento = new JLabel();
		labeProcesamiento.setForeground(SystemColor.windowBorder);
		labeProcesamiento.setBackground(Color.WHITE);
		labeProcesamiento.setBounds(0, 0, 804, 23);
		panelProcesamiento.add(labeProcesamiento);

		panelDocumento = new JPanel();
		panelDocumento.setLayout(null);
		panelDocumento.setForeground(SystemColor.windowBorder);
		panelDocumento.setBackground(Color.WHITE);
		panelDocumento.setBounds(11, 482, 408, 23);
		contentPane.add(panelDocumento);

		labelDocumento = new JLabel();
		labelDocumento.setForeground(SystemColor.windowBorder);
		labelDocumento.setBackground(Color.WHITE);
		labelDocumento.setBounds(0, 0, 408, 23);
		panelDocumento.add(labelDocumento);

//muestra la fecha
		panelFecha = new JPanel();
		panelFecha.setLayout(null);
		panelFecha.setForeground(SystemColor.windowBorder);
		panelFecha.setBackground(Color.WHITE);
		panelFecha.setBounds(545, 125, 290, 23);
		contentPane.add(panelFecha);

		labelFecha = new JLabel();
		labelFecha.setToolTipText("");
		labelFecha.setHorizontalAlignment(SwingConstants.RIGHT);
		labelFecha.setForeground(SystemColor.windowBorder);
		labelFecha.setBackground(Color.WHITE);
		labelFecha.setBounds(0, 0, 280, 23);
		panelFecha.add(labelFecha);

// ========================================CheckBox======================================================
		JpDesv = new JPanel();
		FlowLayout flowLayout_2 = (FlowLayout) JpDesv.getLayout();
		flowLayout_2.setAlignment(FlowLayout.LEFT);
		JpDesv.setBackground(Color.WHITE);
		JpDesv.setBounds(705, 189, 119, 36);
		contentPane.add(JpDesv);

		checkbxDesvinculado = new JCheckBox("Desvinculado");
		checkbxDesvinculado.setLocation(5, 58);
		JpDesv.add(checkbxDesvinculado);
		checkbxDesvinculado.setForeground(SystemColor.desktop);
		checkbxDesvinculado.setEnabled(false);
		checkbxDesvinculado.setBackground(Color.WHITE);

		JpCag = new JPanel();
		FlowLayout flowLayout = (FlowLayout) JpCag.getLayout();
		flowLayout.setAlignment(FlowLayout.LEFT);
		JpCag.setBackground(Color.WHITE);
		JpCag.setBounds(572, 189, 119, 36);
		contentPane.add(JpCag);

		chckbxCagmaest = new JCheckBox("Cagmaest");
		chckbxCagmaest.setLocation(5, 58);
		JpCag.add(chckbxCagmaest);
		chckbxCagmaest.setForeground(SystemColor.desktop);
		chckbxCagmaest.setEnabled(false);
		chckbxCagmaest.setBackground(Color.WHITE);

		JpCobr = new JPanel();
		FlowLayout flowLayout_1 = (FlowLayout) JpCobr.getLayout();
		flowLayout_1.setAlignment(FlowLayout.LEFT);
		JpCobr.setBackground(Color.WHITE);
		JpCobr.setBounds(572, 225, 119, 36);
		contentPane.add(JpCobr);

		chckbxCobrconv = new JCheckBox("Cobrconv");
		JpCobr.add(chckbxCobrconv);
		chckbxCobrconv.setBackground(Color.WHITE);
		chckbxCobrconv.setForeground(SystemColor.desktop);
		chckbxCobrconv.setEnabled(false);

		JpPara = new JPanel();
		FlowLayout flowLayout_3 = (FlowLayout) JpPara.getLayout();
		flowLayout_3.setAlignment(FlowLayout.LEFT);
		JpPara.setBackground(Color.WHITE);
		JpPara.setBounds(705, 225, 119, 36);
		contentPane.add(JpPara);

		chckbxParameizacion = new JCheckBox("Parametrizaci\u00F3n");
		JpPara.add(chckbxParameizacion);
		chckbxParameizacion.setEnabled(false);
		chckbxParameizacion.setForeground(SystemColor.desktop);
		chckbxParameizacion.setBackground(Color.WHITE);

		try {
			DefaultTableModel result;
			result = base.ConsultarQuery("select * from LibrosSubidos");
			int cantiidad = result.getRowCount();// cantidad de registros en la BD

			String Cobrconv = result.getValueAt(0, 1).toString();
			String Cagmaest = result.getValueAt(1, 1).toString();
			String Desvinculado = result.getValueAt(2, 1).toString();
			String Parametrizacion = result.getValueAt(3, 1).toString();
			if (Cobrconv.contains("true") == true) {
				chckbxCobrconv.setSelected(true);
			} else {
				chckbxCobrconv.setSelected(false);
			}
			if (Cagmaest.contains("true") == true) {
				chckbxCagmaest.setSelected(true);
			} else {
				chckbxCagmaest.setSelected(false);
			}
			if (Desvinculado.contains("true") == true) {
				checkbxDesvinculado.setSelected(true);
			} else {
				checkbxDesvinculado.setSelected(false);
			}
			if (Parametrizacion.contains("true") == true) {
				chckbxParameizacion.setSelected(true);
			} else {
				chckbxParameizacion.setSelected(false);
			}
		} catch (SQLException e2) {
			e2.printStackTrace();
		}
// ===================================BOTON CARGAR======================================================

		btnCargar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// conexionBD base = new conexionBD();
				cargaTXR function = new cargaTXR();
				boolean resutado;
				boolean resutado2;
				boolean resutado3;

				switch (valida) {

				case "Cobrconv":
					System.out.println("Arhivo Cobrconv Seleccionado");
					chckbxCobrconv.setSelected(false);
					resutado = txtCusersingYeisonPdesktopquincena.getText().contains("cobrconv");
					resutado2 = txtCusersingYeisonPdesktopquincena.getText().contains(".txt");
					resutado3 = txtCusersingYeisonPdesktopquincena.getText().contains(".csv");
					if (resutado == true && (resutado2 == true || resutado3 == true)) {
						String ValidarFecha[] = txtCusersingYeisonPdesktopquincena.getText().split("\\\\");
						int contar = ValidarFecha.length;
						String Obtener = ValidarFecha[(contar - 1)].toString().trim().replace(".txt", "");
						String ObtenerFecha = Obtener.replace("cobrconv", "").replace("_", "");
						int cantidad = ObtenerFecha.length();
						String Mes = ObtenerFecha.substring(0, 2);
						String Dia = ObtenerFecha.substring(2, 4);
						String Año = ObtenerFecha.substring(4, 8);
						int numDia = Integer.parseInt(Dia);
						String Meses = "";
						String Quince = "";

						if (numDia <= 15) {
							Quince = "Quincena 1 - ";
						} else {
							Quince = "Quincena 2 - ";
						}

						String fecha = Quince + Dia + " " + Meses + " " + Año;
						String query = "update DataCobrconv set Nombre='', Dia=0, mes=0, anio='' where Nombre is null	"
								+ "update Datacobrconv set Nombre='" + "cobrconv_" + Mes + Dia + Año + "',Dia=" + Dia
								+ ",mes='" + Mes + "',anio=" + Año + "  where posicion= 1";
						try {
							base.EjecutarQuery(query);
						} catch (SQLException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
//funcion que permite validar filtrar y cargar el archivo de cobrconv

						function.leerTxtCobrconv(txtCusersingYeisonPdesktopquincena.getText(), panelPorcentaje,
								labelPorcentaje, chckbxCobrconv, JpCobr);
					} else {
						chckbxCobrconv.setSelected(false);
						JpCobr.update(JpCobr.getGraphics());// para actualizar el panel y poder mostrar el proceso
						labelPorcentaje.setForeground(Color.RED);
						labelPorcentaje.setText("Hubo un error leyendo el archivo de Cobrconv");
						panelPorcentaje.update(panelPorcentaje.getGraphics());// para actualizar el panel y poder
						// mostrar el proceso
					}

					break;
				case "Desvinculado":
					System.out.println("Arhivo BASE DESVINCULADOS Seleccionado");
					checkbxDesvinculado.setSelected(false);
					function.leerTxtBaseDesvinculados(txtCusersingYeisonPdesktopquincena.getText(), checkbxDesvinculado,
							panelPorcentaje, labelPorcentaje, JpDesv);
					break;

				case "Cagmaest":
					System.out.println("Arhivo Cagmaest Seleccionado");
					chckbxCagmaest.setSelected(false);
					resutado = txtCusersingYeisonPdesktopquincena.getText().contains("cagmaest");
					resutado2 = txtCusersingYeisonPdesktopquincena.getText().contains(".txt");
					resutado3 = txtCusersingYeisonPdesktopquincena.getText().contains(".csv");
					if (resutado == true && (resutado2 == true || resutado3 == true)) {
						String ValidarFecha[] = txtCusersingYeisonPdesktopquincena.getText().split("\\\\");
						int contar = ValidarFecha.length;
						String Obtener = ValidarFecha[(contar - 1)].toString().trim();
						String ObtenerFecha = Obtener.replace("cagmaest", "").replace("_", "");
						int cantidad = ObtenerFecha.length();
						int Mes = Integer.parseInt(ObtenerFecha.substring(0, 2));
						String Dia = ObtenerFecha.substring(2, 4);
						String Año = ObtenerFecha.substring(4, 8);
						int numDia = Integer.parseInt(Dia);
						String Meses = "";
						String Quince = "";

						if (numDia <= 15) {
							Quince = "Corte 2 - ";
						} else {
							Quince = "Corte 1 - ";
							Mes = Mes + 1;
							if (Mes > 12) {
								Mes = 1;
							}
						}

						switch (Mes) {
						case 1:
							Meses = "Enero";
							break;
						case 2:
							Meses = "Febrero";
							break;
						case 3:
							Meses = "Marzo";
							break;
						case 4:
							Meses = "Abril";
							break;
						case 5:
							Meses = "Mayo";
							break;
						case 6:
							Meses = "Junio";
							break;
						case 7:
							Meses = "Julio";
							break;
						case 8:
							Meses = "Agosto";
							break;
						case 9:
							Meses = "Septiembre";
							break;
						case 10:
							Meses = "Octubre";
							break;
						case 11:
							Meses = "Noviembre";
							break;
						case 12:
							Meses = "Diciembre";
							break;
						default:
							break;
						}

//String fecha=Quince+Dia+" "+Meses+" "+Año;
						String fecha = Quince + Meses + " " + Año;
						labelFecha.setText(fecha);
						panelFecha.update(panelFecha.getGraphics());

						function.leerTxtCagmaest(txtCusersingYeisonPdesktopquincena.getText(),panelPorcentaje, labelPorcentaje, chckbxCagmaest,JpCag);
					} else {
						chckbxCagmaest.setSelected(false);
						JpCobr.update(JpCobr.getGraphics());// para actualizar el panel y poder mostrar el proceso

						labelPorcentaje.setForeground(Color.RED);
						labelPorcentaje.setText("Hubo un error leyendo el archivo de Cagmaest");
						panelPorcentaje.update(panelPorcentaje.getGraphics());// para actualizar el panel y poder
						// mostrar el proceso
					}
					break;
				case "Parametrización":
					System.out.println("Arhivo Parametrizado Seleccionado");
					chckbxParameizacion.setSelected(false);
					function.leerTxtParametrizados(txtCusersingYeisonPdesktopquincena.getText(), panelPorcentaje,
							labelPorcentaje, chckbxParameizacion, JpPara);
					break;

				default:
					break;
				}
				close.CerrarconexionBD();
			}
		});
// =====================================BOTON EXAMINAR====================================================
		btnCargar.setBounds(469, 231, 89, 25);
		contentPane.add(btnCargar);

		JButton btnNewButton = new JButton("Examinar");
		btnNewButton.setEnabled(false);
		btnNewButton.setForeground(SystemColor.windowText);
		btnNewButton.setBackground(UIManager.getColor("Button.background"));
// =============================Examinar documento============================================================
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JFileChooser jf = new JFileChooser();
				FileNameExtensionFilter filtro = new FileNameExtensionFilter("*.TXT", "txt", "*CSV", "csv");

				jf.setFileFilter(filtro);
				jf.showOpenDialog(jf);

				File archivos = jf.getSelectedFile();
				if (archivos != null) {
					txtCusersingYeisonPdesktopquincena.setText(archivos.getAbsolutePath());
					btnCargar.setEnabled(true);
				} else {
					btnCargar.setEnabled(false);
					txtCusersingYeisonPdesktopquincena.setText("");
				}
			}
		});// cierre de btnNewButton
// ==============================================================================================
		btnNewButton.setBounds(469, 195, 89, 25);
		contentPane.add(btnNewButton);

		txtCusersingYeisonPdesktopquincena = new JTextField();
		txtCusersingYeisonPdesktopquincena.setFont(new Font("Arial", Font.PLAIN, 17));
		txtCusersingYeisonPdesktopquincena.setBackground(SystemColor.textHighlightText);
// textField.setEnabled(false);
		txtCusersingYeisonPdesktopquincena.setEditable(false);
		txtCusersingYeisonPdesktopquincena.setBounds(11, 231, 444, 25);
		contentPane.add(txtCusersingYeisonPdesktopquincena);
		txtCusersingYeisonPdesktopquincena.setColumns(10);
// ========================================boton documento======================================================	
		JButton Documentos = new JButton("Documentos");
		Documentos.setEnabled(false);
		Documentos.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Generar funcion = new Generar();
				funcion.GenerarEx(textFieldDireccion.getText(), panelDocumento, labelDocumento, labelFecha);
				comboBoxEmail.setEnabled(true);
			}
		});
		Documentos.setBackground(UIManager.getColor("Button.background"));
		Documentos.setBounds(717, 331, 118, 25);
		contentPane.add(Documentos);

		JButton btnEjecutar = new JButton("Ejecutar");
		btnEjecutar.setEnabled(false);
		btnEjecutar.setBackground(UIManager.getColor("Button.background"));
		btnEjecutar.addActionListener(new ActionListener() {
// ========================================boton Ejecuta el Proyecto======================================================
			public void actionPerformed(ActionEvent e) {
				Ejecucion funcion = new Ejecucion();
				Generar funcion2 = new Generar();
				String[] CARPETAS = new String[] { "ANTIOQUIA", "BOGOTA", "CAFETERA", "COSTA", "OCCIDENTE", "ORIENTE",
						"SANTANDERES", "SUR", "OTROS" };

				for (int k = 0; k < CARPETAS.length; k++) {
					String Carp2 = textFieldDireccion.getText() + "\\" + CARPETAS[k];// SELECCIONA CARPETAS
					File crea_carpeta2 = new File(Carp2);
					crea_carpeta2.mkdir();// CREA CARPETA
					String Carp3 = Carp2 + "\\Encriptado";
					File crea_carpeta3 = new File(Carp3);
					crea_carpeta3.mkdir();// CREA CARPETA
				}

				Documentos.setEnabled(true);
				funcion.conteo(textFieldDireccion.getText(), panelProcesamiento, labeProcesamiento);
				funcion2.BaseConsolidado(textFieldDireccion.getText(), panelDocumento, labelDocumento);
				funcion2.FacturacionCorte(textFieldDireccion.getText(), panelDocumento, labelDocumento);
			}

		});
		btnEjecutar.setBounds(590, 331, 118, 25);
		contentPane.add(btnEjecutar);

// ==============================================================================================
		txtProcesamiento = new JTextField();
		txtProcesamiento.setBackground(new Color(127, 255, 0));
		txtProcesamiento.setForeground(Color.BLACK);
		txtProcesamiento.setFont(new Font("Tahoma", Font.BOLD, 12));
		txtProcesamiento.setEditable(false);
		txtProcesamiento.setText("PROCESAMIENTO");
		txtProcesamiento.setBounds(11, 300, 824, 20);
		contentPane.add(txtProcesamiento);
		txtProcesamiento.setColumns(10);

		textFieldDireccion = new JTextField();
		textFieldDireccion.setFont(new Font("Arial", Font.PLAIN, 17));
		textFieldDireccion.setColumns(10);
		textFieldDireccion.setBackground(Color.WHITE);
		textFieldDireccion.setBounds(10, 331, 444, 25);
		contentPane.add(textFieldDireccion);

		JButton buttonCarpeta = new JButton("Direccion");
		buttonCarpeta.setEnabled(true);
		buttonCarpeta.addActionListener(new ActionListener() {
// ========================================boton seleccion carpeta======================================================
			public void actionPerformed(ActionEvent e) {

//if (checkbxDesvinculado.isSelected()==false && chckbxCobrconv.isSelected()==false && chckbxCagmaest.isSelected()==false &&  checkbxDesvinculado.isSelected()==false){
				if (checkbxDesvinculado.isSelected() == true && chckbxCobrconv.isSelected() == true
						&& chckbxCagmaest.isSelected() == true && checkbxDesvinculado.isSelected() == true) {
					JFileChooser jf = new JFileChooser();
					jf.setCurrentDirectory(new java.io.File(""));// donce quiere que se abra la ventana en este caso se
					// dejo por defecto
					jf.setDialogTitle("Carpetas");
					jf.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
					jf.showOpenDialog(jf);

					if (jf.showOpenDialog(jf) == JFileChooser.APPROVE_OPTION) {
						textFieldDireccion
								.setText(jf.getSelectedFile().getAbsolutePath() + "\\" + labelFecha.getText());
						String Carp = textFieldDireccion.getText();// SELECCIONA CARPETAS
						File crea_carpeta = new File(Carp);
						crea_carpeta.mkdir();// CREA CARPETA

						labeProcesamiento.setText("");
						labeProcesamiento.setForeground(SystemColor.windowBorder);
						panelProcesamiento.update(panelProcesamiento.getGraphics()); // para actualizar el panel y poder
						// mostrar el proceso

						btnEjecutar.setEnabled(true);
					} else {
						textFieldDireccion.setText("");
						btnEjecutar.setEnabled(false);

					}
				} else {
					labeProcesamiento.setText("ERROR FALTAN ARCHIVOS POR SUBIR, VERIFIQUE LOS INDICADORES.");
					labeProcesamiento.setForeground(Color.RED);
					panelProcesamiento.update(panelProcesamiento.getGraphics());// para actualizar el panel y poder
					// mostrar el proceso

				}
			}
		});
		buttonCarpeta.setForeground(Color.BLACK);
		buttonCarpeta.setBackground(UIManager.getColor("Button.background"));
		buttonCarpeta.setBounds(463, 331, 118, 25);
		contentPane.add(buttonCarpeta);
//==============================================================================================================
//**************************************************************************************************************
		btnEmail = new JButton("Email");
		btnEmail.setEnabled(false);
		btnEmail.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					Email funcion = new Email(); // prueba si se han subido todos los archivos
					funcion.Correo(textFieldDireccion.getText(), panelDocumento, labelDocumento, Email, labelFecha);
				} catch (Exception ex) {
					System.out.println(ex);
				}
			}
		});
		btnEmail.setBackground(UIManager.getColor("Button.background"));
		btnEmail.setBounds(465, 438, 89, 25);
		contentPane.add(btnEmail);
//==============================================================================================================
//**************************************************************************************************************
		txtEmail = new JTextField();
		txtEmail.setText("CORREO");
		txtEmail.setForeground(Color.BLACK);
		txtEmail.setFont(new Font("Tahoma", Font.BOLD, 12));
		txtEmail.setEditable(false);
		txtEmail.setColumns(10);
		txtEmail.setBackground(new Color(124, 252, 0));
		txtEmail.setBounds(11, 403, 824, 20);
		contentPane.add(txtEmail);

		lblIndicadores = new JLabel("INDICADORES");
		lblIndicadores.setEnabled(false);
		lblIndicadores.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblIndicadores.setHorizontalAlignment(SwingConstants.CENTER);
		lblIndicadores.setBounds(572, 149, 245, 20);
		contentPane.add(lblIndicadores);

		JLabel label = DefaultComponentFactory.getInstance().createLabel("");
		label.setHorizontalAlignment(SwingConstants.CENTER);
		label.setIcon(new ImageIcon(MENÚ.class.getResource("/Imagenes/Banco-Agrario.jpg")));
		label.setBounds(0, 11, 844, 127);
		contentPane.add(label);

		textField_2 = new JTextField();
		textField_2.setFont(new Font("Arial", Font.PLAIN, 17));
		textField_2.setEditable(false);
		textField_2.setColumns(10);
		textField_2.setBackground(Color.WHITE);
		textField_2.setBounds(0, 1, 844, 1);
		contentPane.add(textField_2);

		button = new JButton(".");
		button.addActionListener(new ActionListener() {
			int muestra = 0;

			public void actionPerformed(ActionEvent arg0) {

				muestra = muestra + 1;
				if (muestra >= 20) {
					/*
					 * 080 114 111 103 114 097 109 097 032 068 105 115 101 195 177 097 100 111 032
					 * 112 111 114 032 032 073 110 103 032 089 101 105 115 111 110 032 080 101 114
					 * 101 097 032 067
					 *//* 048 056 047 048 052 047 050 048 050 048 */
					JOptionPane.showMessageDialog(null, "Programa Diseñado por  Ing Yeison Perea C", "08/04/2020",
							JOptionPane.INFORMATION_MESSAGE);
					muestra = 0;
				}
			}
		});
		button.setBackground(Color.WHITE);
		button.setBounds(839, 510, 2, 2);
		contentPane.add(button);

// =====================================captura los datos del menu desplegable==========================================================
		comboBoxEmail.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				switch (comboBoxEmail.getSelectedItem().toString()) {
				case "ANTIOQUIA":
					System.out.println("ANTIOQUIA");
					btnEmail.setEnabled(true);
					Email = "ANTIOQUIA";
					break;
				case "BOGOTÁ":
					System.out.println("BOGOTÁ");
					btnEmail.setEnabled(true);
					Email = "BOGOTÁ";
					break;
				case "CAFETERA":
					System.out.println("CAFETERA");
					btnEmail.setEnabled(true);
					Email = "CAFETERA";
					break;
				case "COSTA":
					System.out.println("COSTA");
					btnEmail.setEnabled(true);
					Email = "COSTA";
					break;
				case "OCCIDENTE":
					System.out.println("OCCIDENTE");
					btnEmail.setEnabled(true);
					Email = "OCCIDENTE";
					break;
				case "ORIENTE":
					System.out.println("ORIENTE");
					btnEmail.setEnabled(true);
					Email = "ORIENTE";
					break;
				case "SANTANDERES":
					System.out.println("SANTANDERES");
					btnEmail.setEnabled(true);
					Email = "SANTANDERES";
					break;
				case "SUR":
					System.out.println("SUR");
					btnEmail.setEnabled(true);
					Email = "SUR";
					break;
				case "TODOS":
					System.out.println("TODOS");
					btnEmail.setEnabled(true);
					Email = "TODOS";
					break;
				default:
					System.out.println("NADA");
					btnEmail.setEnabled(false);
				}
			}
		}); // cierre del combobox
		comboBox.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				switch (comboBox.getSelectedItem().toString()) {
				case "Cagmaest":
					System.out.println("Cagmaest");
					btnNewButton.setEnabled(true);
					txtCusersingYeisonPdesktopquincena.setEnabled(true);
					btnCargar.setEnabled(false);
					txtCusersingYeisonPdesktopquincena.setText("");// limpia el text box
					valida = "Cagmaest";
					break;
				case "Cobrconv":
					System.out.println("Cobrconv");
					btnNewButton.setEnabled(true);
					txtCusersingYeisonPdesktopquincena.setEnabled(true);
					btnCargar.setEnabled(false);
					txtCusersingYeisonPdesktopquincena.setText("");// limpia el text box
					valida = "Cobrconv";
					break;
				case "Desvinculado":
					System.out.println("Desvinculado");
					btnNewButton.setEnabled(true);
					txtCusersingYeisonPdesktopquincena.setEnabled(true);
					btnCargar.setEnabled(false);
					txtCusersingYeisonPdesktopquincena.setText("");// limpia el text box
					valida = "Desvinculado";
					break;
				case "BanColombia":
					System.out.println("BanColombia");
					btnNewButton.setEnabled(true);
					txtCusersingYeisonPdesktopquincena.setEnabled(true);
					btnCargar.setEnabled(false);
					txtCusersingYeisonPdesktopquincena.setText("");// limpia el text box
					valida = "BanColombia";
					break;
				case "Parametrización":
					System.out.println("Parametrización");
					btnNewButton.setEnabled(true);
					txtCusersingYeisonPdesktopquincena.setEnabled(true);
					btnCargar.setEnabled(false);
					txtCusersingYeisonPdesktopquincena.setText("");// limpia el text box
					valida = "Parametrización";
					break;

				case "Seleccione un archivo ...":
					System.out.println("nada");
					txtCusersingYeisonPdesktopquincena.setEnabled(false);
					txtCusersingYeisonPdesktopquincena.setText("");// limpia el text box
					btnCargar.setEnabled(false);
					btnNewButton.setEnabled(false);
					break;
				default:
				}
			}
		}); // cierre del combobox
// ===============================================================================================
	}
}
