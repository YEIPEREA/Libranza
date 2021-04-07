/* 6Cobrconv=		2:07		minutos
 * Bancolombia=	11 		Segundos
 * 3Catradia= 			34:24 	minutos---2:26 	minutos
 * 3Cagmaest=		11:18 	minutos
 * Parametrizados=14 		segundos
 * -------------------48:10 	minutos---16:08 	minutos
 * Ejecucion=			11:45 	minutos---2:34 	minutos
 * -------------------59:55 	minutos---18:42  minutos*/
package graficos;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.SystemColor;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.DefaultListModel;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.*;

import java.awt.Font;
import java.io.File;
import java.io.IOException;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.JList;

import CargaDatos.conexionBD;
import CapaNegocio.cargaTXR;
import CapaNegocio.ProcesoPagos;

public class Pagos extends JFrame {

	private java.util.List ARCH;
	private JPanel contentPane;
	private String validar;
	private String validar2="PAGLIBRANZ";
	private JTextField textField;
	private JPanel panelPocentaje;
	private JLabel labelPorcentaje;
	private JList list;
	private JList Listas;
	private JList listCargados1;
	private  int k=1;
	private  int G=0;
	private  int H=0;
	private int h=0;
	private String RutaVen="";
	private String NombreArchivo;
	private JButton btnQuitar;
	private JButton Btn_Ejecutar;

	public void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Pagos frame = new Pagos();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	cargaTXR function = new cargaTXR();
	conexionBD base = new conexionBD();
	DefaultListModel ModeloLista = new DefaultListModel();
	DefaultListModel ModeloLista2 = new DefaultListModel();
	
public void CerrarV(){

		this.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		addWindowListener(new WindowAdapter(){
			public void windowClosing(WindowEvent we){
				CerrarVentana();
			}
		});
		this.setVisible(true);
	}
	
	public void CerrarVentana(){
		String Botones[]={"Aceptar","Cancelar"};
		int eleccion=JOptionPane.showOptionDialog(this, "¿ Está seguro de querer cerrar la aplicación? ","Advertencia"
				,0,0,null,Botones,this);
		if (eleccion==JOptionPane.YES_OPTION){
			JOptionPane.showMessageDialog(null, "Gracias por su visita, hasta pronto","Gracias",JOptionPane.INFORMATION_MESSAGE);
			System.exit(0);
		}
	}
	
	//public Pagos() {
	public void MenuPagos(){
		
		setAutoRequestFocus(false);
		setResizable(false);
		setType(Type.POPUP);
		setBackground(Color.BLACK);
		
		setTitle("Novedades");
		setIconImage(Toolkit.getDefaultToolkit().getImage(Seleccion.class.getResource("/Imagenes/BAC.png")));
		
		Toolkit miPantalla = Toolkit.getDefaultToolkit();
		Dimension tamanoPantalla = miPantalla.getScreenSize();// devuelve el tamaño de la pantalla
		int altura = tamanoPantalla.height;// altude pantalla
		int ancho = tamanoPantalla.width;// ancho de pantalla
		setBounds(ancho/4 , altura / 8, 786, 613);
		setTitle("LIBRANZA");// TITULO DE LA VENTANA
		
		contentPane = new JPanel();
		contentPane.setBackground(Color.white);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JComboBox comboBox = new JComboBox();//TIPOS de dtr_concepto a seleccionar por defecto es PAGLIBRANZ
		comboBox.setModel(new DefaultComboBoxModel(new String[] {"SELECCIONE UN TIPO DE CONCEPTO", "CAP", "CARTERA", "CENACH",
				"CHEPRO", "CHGDE", "CHLOCAL", "CMIPYME2", "COMCHG", "COMISIOFAG", "CONDCASPNC", "CXCCOMIFAG", "CXCCOMIFNG", 
				"CXCINTES", "DEPGTIA", "DESSOB", "DRECGAROT", "ECCCBPCS", "EDCCBPCS", "EFEMN", "FAG", "FAGANUAL", "FAGMEDANUA", 
				"FAGMEDPROD", "FDBSP221", "FDBSP224", "FNGANUALAA", "FPBSP224", "GASTOSJUDI", "HONAUXJUST", "HONETAPROC", 
				"HONOPREJUR", "IMO", "INT", "INTCUND", "IOCCASTIGO", "IOCSEGDES", "IOCSEGUROS", "IVACOMCHG", "IVACOMGFAG", 
				"IVAFAGANU", "IVAFAGMED", "IVAFNGANTI", "IVAMEDPROD", "IVAPYME2", "IVASEGTRVI", "MONEXTRAN", "NCAHINTER", "NCAHO", 
				"NCCC", "NCCCINTER", "NCCCNB", "NDACBPCS", "NDAHINTERN", "NDAHO", "NDCC", "NDCCBPCS", "NDCCINTERN", "NDCCNB", 
				"OPAGXCCLI", "PAGAJUST", "PAGCOMPRA", "PAGEXTCAST", "PAGLIBRANZ", "PAGOSEBRA", "PAGSERBAN", "PDGTIA", "PGBANANERA", 
				"PGLIBFOPEP", "PGLIBRABAC", "PGRETANQ", "REAPLICPAG", "RECFOGCAFE", "RECGAROT", "REINCOMFAG", "REINTHONOR", 
				"SEGEXTRAPR", "SEGITP", "SEGTORVIVI", "SEGVIDA", "SEGVIDAF", "SGDESEMPLE", "SOBRANTE", "TASAPUT", "VAC0", "VAC1", "VAC2"}));
		comboBox.setBounds(221, 457, 205, 25);
		contentPane.add(comboBox);
		
		panelPocentaje = new JPanel();
		panelPocentaje.setLayout(null);
		panelPocentaje.setForeground(SystemColor.windowBorder);
		panelPocentaje.setBackground(Color.WHITE);
		panelPocentaje.setBounds(20, 500, 408, 23);
		contentPane.add(panelPocentaje);
	
		labelPorcentaje = new JLabel();
		labelPorcentaje.setForeground(SystemColor.windowBorder);
		labelPorcentaje.setBackground(Color.WHITE);
		labelPorcentaje.setBounds(0, 0, 408, 23);
		panelPocentaje.add(labelPorcentaje);
//==========================================================================================================================		
		Listas = new JList();
		Listas.setForeground(Color.gray);
		Listas.setFont(new Font("Tahoma", Font.ITALIC, 14));
		String[] values = new String[] {};
		Listas=new JList(values);
		Listas.setLocation(30, 0);
		Listas.setSize(500,500);

		Listas.setSelectionBackground(new Color(136,182,11));
		Listas.setVisibleRowCount(4);
		
		JScrollPane scrollbar = new JScrollPane(Listas);
		scrollbar.setBackground(UIManager.getColor("window"));
		scrollbar.setBounds(12, 284, 306, 153);
		contentPane.add(scrollbar);
		setVisible(true);
//===========================================================================================================================	
		listCargados1 = new JList();
		listCargados1.setForeground(Color.GRAY);
		listCargados1.setBackground(Color.WHITE);
		listCargados1.setFont(new Font("Tahoma", Font.BOLD, 8));
		String[] values2 = new String[] {};
		listCargados1=new JList(values2);
		
		listCargados1.setSize(500,500);
		//listCargados1.setSelectionBackground(new Color(136,182,11));
		listCargados1.setVisibleRowCount(4);
		
		JScrollPane scrollbar2 = new JScrollPane(listCargados1);
		scrollbar2.setBackground(UIManager.getColor("window"));
		scrollbar2.setBounds(450, 284, 306, 153);
		contentPane.add(scrollbar2);
		setVisible(true);
		Listas2();
		/*listCargados1.setBounds(330, 281, 234, 152);
		contentPane.add(listCargados1);
		Listas2();*/
//===========================================================================================================================		
		
		JButton btnAgregar = new JButton("Agregar >>");
		btnAgregar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				CARGAR();
				int eleccion;
				String NomArchivo;	
				String borrar;
				
				textField.setText("");
				textField.setText(RutaVen);
				String RUTA =RutaVen+"\\"+validar;
//*****************************************************************************************************************************************************
//==========================================================================================
//*****************************************************************************************************************************************************
				if (validar.contains("cobrconv")==true){//insumos de cobrconv
					textField.setText(textField.getText()+"\\"+validar);
					
					eleccion=JOptionPane.showConfirmDialog(null, "Realmente desea Cargar el  Archivo "+ validar  +" ?", "Confirmar", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
					if (eleccion==JOptionPane.YES_OPTION){
						
						String Select = "SELECT * FROM [Libranza].[dbo].[DataCobrconv]";
						
						DefaultTableModel result;
						try {
							result  = base.ConsultarQuery(Select);
							int cantiidad = result.getRowCount();
							String Obtener= validar.replace(".txt", "");
							System.out.println(Obtener+" Seleccionado");
							
							NomArchivo=result.getValueAt(0, 0).toString();//pregunta si existe algun archivo en la posicion 1 de sql es decir el mes actual
								
								if ( NomArchivo.isEmpty()==true){
									
									eleccion=JOptionPane.showConfirmDialog(null, "El  Archivo "+ validar  +" se subirá como mes actual. Desea continuar ?", "Confirmar", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
									if (eleccion==JOptionPane.YES_OPTION){
											borrar="truncate table Cobrconv";
											base.EjecutarQuery(borrar);
											function.leerTxtCobrconv0(RUTA,panelPocentaje, labelPorcentaje,Obtener);
									}
											
											}
											else{
												if(k<1){k=1;}
												k++;
												if(k>=6){k=6;}
													borrar="truncate table P_Cobrconv"+k;
													base.EjecutarQuery(borrar);
													function.leerTxtCobrconv2(RUTA,panelPocentaje, labelPorcentaje,k,Obtener);
												}
										ModeloLista2.clear();//Actualiza la tabla de los archivos
										Listas2();//Actualiza la tabla de los archivo
										activar();// activa el boton ejecutar
									}
						catch (SQLException e) {
							e.printStackTrace();
						}
					}
				}
//*****************************************************************************************************************************************************
//==========================================================================================
//*****************************************************************************************************************************************************

				else if(validar.contains("BANCOLOMBIA")==true){
						textField.setText(textField.getText()+"\\"+validar);
						String Obtener =validar.replace(".txt", "");
						eleccion=JOptionPane.showConfirmDialog(null, "Realmente desea Cargar el  Archivo "+ validar  +" ?", "Confirmar", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
							if (eleccion==JOptionPane.YES_OPTION){
								
								System.out.println(Obtener + " Seleccionado");
								function.leerTxtBancolombia(textField.getText(),panelPocentaje, labelPorcentaje,Obtener);
								ModeloLista2.clear();
								Listas2();
								activar();// activa el boton ejecutar
							}
				}
//*****************************************************************************************************************************************************
//==========================================================================================
//*****************************************************************************************************************************************************

				else if(validar.contains("CONVENIOS PARAMETRIZADOS")==true){
					textField.setText(textField.getText()+"\\"+validar);
					String Obtener =validar.replace(".txt", "");
					eleccion=JOptionPane.showConfirmDialog(null, "Realmente desea Cargar el  Archivo "+ validar  +" ?", "Confirmar", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
						if (eleccion==JOptionPane.YES_OPTION){
							System.out.println(Obtener + " Seleccionado");
							function.leerTxtParametrizadosP(textField.getText(), panelPocentaje, labelPorcentaje, Obtener);
							ModeloLista2.clear();
							Listas2();
							activar();// activa el boton ejecutar
						}
			}
				else if(validar.contains("catradia")==true){
					textField.setText(textField.getText()+"\\"+validar);
					String Obtener =validar.replace(".txt", "");
					eleccion=JOptionPane.showConfirmDialog(null, "Realmente desea Cargar el  Archivo "+ validar  +" ?", "Confirmar", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
						if (eleccion==JOptionPane.YES_OPTION){
							G++;
							if(G>=3){G=3;}
								if(G==1){
									eleccion=JOptionPane.showConfirmDialog(null, "El  Archivo "+ validar  +" se subirá como día actual. Desea continuar ?", "Confirmar", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
									if (eleccion==JOptionPane.YES_OPTION){
										System.out.println(Obtener +" Seleccionado");
										function.leerTxtCatradia(RUTA,panelPocentaje, labelPorcentaje,G,Obtener,validar2);
									}
									else {G=0;}
								}
								else{
									System.out.println(Obtener +" Seleccionado");
									function.leerTxtCatradia(RUTA,panelPocentaje, labelPorcentaje,G,Obtener,validar2);
								}
								ModeloLista2.clear();
								Listas2();
								activar();// activa el boton ejecutar
						}
				}
				
				else if(validar.contains("cagmaest")==true){
					textField.setText(textField.getText()+"\\"+validar);
					String Obtener =validar.replace(".txt", "");
					eleccion=JOptionPane.showConfirmDialog(null, "Realmente desea Cargar el  Archivo "+ validar  +" ?", "Confirmar", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
						if (eleccion==JOptionPane.YES_OPTION){
							H++;
							if(H>=3){H=3;}
							System.out.println(Obtener +" Seleccionado");
							function.leerTxtCagmaestP(RUTA,panelPocentaje, labelPorcentaje,H,Obtener);
							ModeloLista2.clear();
							Listas2();
							activar();// activa el boton ejecutar
						}
				}
				else{
					JOptionPane.showMessageDialog(null,"ARCHIVO "+validar+" INVALIDO");//indica que el archivo que se va a cargar no es Cobrconv o Bancolombia o Catradia o Cagmaest
				}
			}
		});
		btnAgregar.setEnabled(false);
		btnAgregar.setBounds(330, 284, 108, 30);
		contentPane.add(btnAgregar);
//===============================================examinar============================================================================
		
		JButton btnExaminar = new JButton("Examinar");
		btnExaminar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				JFileChooser jf = new JFileChooser();
				jf.setCurrentDirectory(new java.io.File(""));//donce quiere que se abra la ventana en este caso se dejo por defecto
				jf.setDialogTitle("Carpetas");
				jf.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
				jf.showOpenDialog(jf);
				
				if(jf.showOpenDialog(jf)==JFileChooser.APPROVE_OPTION){
					
					textField.setText(jf.getSelectedFile().getAbsolutePath());
					ModeloLista.clear();
					Liistas();
					btnAgregar.setEnabled(true);
					RutaVen = textField.getText();
					
				}
				else {textField.setText(""); btnAgregar.setEnabled(false);}
			}
		});
		btnExaminar.setBounds(560, 198, 108, 30);
		contentPane.add(btnExaminar);
		
		textField = new JTextField();
		textField.setFont(new Font("Arial", Font.PLAIN, 17));
		textField.setBounds(100, 198, 360, 30);
		contentPane.add(textField);
		textField.setColumns(10);
//==================================boton quitar===========================================================================		
		btnQuitar = new JButton("Quitar");
		btnQuitar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				quitarvalores();
			}
		});
		btnQuitar.setEnabled(true);
		btnQuitar.setBounds(330, 328, 108, 30);
		contentPane.add(btnQuitar);
//==================================boton ejecutar============================================================================		
		 Btn_Ejecutar = new JButton("Ejecutar");
		
		Btn_Ejecutar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				textField.setText("");
				textField.setText(RutaVen);
				if(RutaVen.isEmpty()==false){
					ProcesoPagos Proces =new ProcesoPagos();
					Proces.Comparar(RutaVen,panelPocentaje, labelPorcentaje,validar2);
					JOptionPane.showMessageDialog(null, "Fin del programa");
				}
				else{JOptionPane.showMessageDialog(null,"Por Favor Asigne una Ruta");}
			}
		});
		Btn_Ejecutar.setBounds(446, 457, 301, 30);
		contentPane.add(Btn_Ejecutar);
				
				JButton btnQuitarTodo = new JButton("Quitar Todo");
				btnQuitarTodo.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent arg0) {
						try {
							int respuesta=JOptionPane.showConfirmDialog(null,"Realmente desea Eliminar el contenido de la Tabla? ");
								if (respuesta==JOptionPane.YES_OPTION){
									int respuesta2=JOptionPane.showConfirmDialog(null,"Esta acción eliminará la información contenida en el proceso de Pagos incluyendo el histórico de Catradia. ¿Desea continuar.?");
										if (respuesta2==JOptionPane.YES_OPTION){	
											String Seleccion=" Use Libranza 	truncate table [Cobrconv] truncate table [P_Cobrconv2] truncate table [P_Cobrconv3]"
																		+" truncate table [P_Cobrconv3] truncate table [P_Cobrconv4]  truncate table [P_Cobrconv5] truncate table [P_Cobrconv6]"
																		+" truncate table BANCOLOMBIA truncate table CagmaestP1 truncate table CagmaestP2 truncate table CagmaestP3"
																		+" truncate table Catradia1 truncate table Catradia2 truncate table Catradia3  truncate table ParametrizadosP"
																		+" truncate table CatradiaHistorial update DataCobrconv set Nombre =''";
												base.EjecutarQuery(Seleccion);
												ModeloLista2.clear();
												Listas2();
												activar();// activa el boton ejecutar
												G=0;H=0;k=0;
										}
								}
						} catch (SQLException e) {
							e.printStackTrace();
						}
					}
				});
				btnQuitarTodo.setEnabled(true);
				btnQuitarTodo.setBounds(330, 368, 108, 30);
				contentPane.add(btnQuitarTodo);
				
				JButton btnExportar = new JButton("Exportar");
				btnExportar.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent arg0) {
						try {
							textField.setText("");
							textField.setText(RutaVen);
							if(RutaVen.isEmpty()==false){
							cargaTXR function = new cargaTXR();	
							function.escribirTxtCatradia(RutaVen, panelPocentaje, labelPorcentaje);
							}
							else{JOptionPane.showMessageDialog(null,"Por Favor Asigne una Ruta");}
						} catch (SQLException | IOException e) {
							e.printStackTrace();
						}
					}
				});
				btnExportar.setEnabled(true);
				btnExportar.setBounds(330, 407, 108, 30);
				contentPane.add(btnExportar);
				
				JLabel lblV = new JLabel("V.2.2.3.");
				lblV.setHorizontalAlignment(SwingConstants.RIGHT);
				lblV.setForeground(Color.WHITE);
				lblV.setFont(new Font("Comic Sans MS", Font.BOLD, 9));
				lblV.setBounds(729, 548, 43, 23);
				contentPane.add(lblV);
				
				JLabel labelFecha = new JLabel(FechaActual());
				labelFecha.setForeground(new Color(255, 255, 255));
				labelFecha.setHorizontalAlignment(SwingConstants.CENTER);
				labelFecha.setFont(new Font("Trebuchet MS", Font.BOLD, 14));
				labelFecha.setBounds(650, 0, 156, 23);
				contentPane.add(labelFecha);
				
				JLabel lblNewLabel = new JLabel("Tipo de  Concepto : ");
				lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
				lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 16));
				lblNewLabel.setBounds(20, 457, 181, 23);
				contentPane.add(lblNewLabel);
				
				JLabel lblListaSinAsignar = new JLabel("Lista sin Asignar");
				lblListaSinAsignar.setForeground(new Color(102, 153, 0));
				lblListaSinAsignar.setHorizontalAlignment(SwingConstants.CENTER);
				lblListaSinAsignar.setFont(new Font("Trebuchet MS", Font.BOLD, 16));
				lblListaSinAsignar.setBounds(12, 250, 306, 23);
				contentPane.add(lblListaSinAsignar);
				
				JLabel lblAsignados = new JLabel("Lista Asignada");
				lblAsignados.setForeground(new Color(102, 153, 0));
				lblAsignados.setHorizontalAlignment(SwingConstants.CENTER);
				lblAsignados.setFont(new Font("Trebuchet MS", Font.BOLD, 16));
				lblAsignados.setBounds(450, 250, 306, 23);
				contentPane.add(lblAsignados);
//===================================================================================================================		
				JLabel lblImagen = new JLabel("");
				lblImagen.setVerticalAlignment(SwingConstants.TOP);
				lblImagen.setHorizontalAlignment(SwingConstants.RIGHT);
				lblImagen.setIcon(new ImageIcon(Pagos.class.getResource("/Imagenes/FondoMarco.png")));
				lblImagen.setBounds(0, 0, 772, 576);
				contentPane.add(lblImagen);
				
				comboBox.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent arg0) {
						switch (comboBox.getSelectedItem().toString()) {
						case  "CAP": System.out.println( "CAP");validar2= "CAP";break; 
						case  "CARTERA": System.out.println( "CARTERA");validar2= "CARTERA";break; 
						case  "CENACH":System.out.println( "CENACH");validar2= "CENACH";break; 
						case  "CHEPRO": System.out.println( "CHEPRO");validar2= "CHEPRO";break; 
						case  "CHGDE": System.out.println( "CHGDE");validar2= "CHGDE";break; 
						case  "CHLOCAL": System.out.println( "CHLOCAL");validar2= "CHLOCAL";break; 
						case  "CMIPYME2": System.out.println( "CMIPYME2");validar2= "CMIPYME2";break; 
						case  "COMCHG": System.out.println( "COMCHG");validar2= "COMCHG";break; 
						case  "COMISIOFAG": System.out.println( "COMISIOFAG");validar2= "COMISIOFAG";break; 
						case  "CONDCASPNC": System.out.println( "CONDCASPNC");validar2= "CONDCASPNC";break; 
						case  "CXCCOMIFAG": System.out.println( "CXCCOMIFAG");validar2= "CXCCOMIFAG";break; 
						case  "CXCCOMIFNG": System.out.println( "CXCCOMIFNG");validar2= "CXCCOMIFNG";break; 
						case  "CXCINTES": System.out.println( "CXCINTES");validar2= "CXCINTES";break; 
						case  "DEPGTIA": System.out.println( "DEPGTIA");validar2= "DEPGTIA";break; 
						case  "DESSOB": System.out.println( "DESSOB");validar2= "DESSOB";break; 
						case  "DRECGAROT": System.out.println( "DRECGAROT");validar2= "DRECGAROT";break; 
						case  "ECCCBPCS": System.out.println( "ECCCBPCS");validar2= "ECCCBPCS";break; 
						case  "EDCCBPCS": System.out.println( "EDCCBPCS");validar2= "EDCCBPCS";break; 
						case  "EFEMN": System.out.println( "EFEMN");validar2= "EFEMN";break; 
						case  "FAG": System.out.println( "FAG");validar2= "FAG";break; 
						case  "FAGANUAL": System.out.println( "FAGANUAL");validar2= "FAGANUAL";break; 
						case  "FAGMEDANUA": System.out.println( "FAGMEDANUA");validar2= "FAGMEDANUA";break; 
						case  "FAGMEDPROD": System.out.println( "FAGMEDPROD");validar2= "FAGMEDPROD";break; 
						case  "FDBSP221": System.out.println( "FDBSP221");validar2= "FDBSP221";break; 
						case  "FDBSP224": System.out.println( "FDBSP224");validar2= "FDBSP224";break; 
						case  "FNGANUALAA": System.out.println( "FNGANUALAA");validar2= "FNGANUALAA";break; 
						case  "FPBSP224": System.out.println( "FPBSP224");validar2= "FPBSP224";break; 
						case  "GASTOSJUDI": System.out.println( "GASTOSJUDI");validar2= "GASTOSJUDI";break; 
						case  "HONAUXJUST": System.out.println( "HONAUXJUST");validar2= "HONAUXJUST";break; 
						case  "HONETAPROC": System.out.println( "HONETAPROC");validar2= "HONETAPROC";break; 
						case  "HONOPREJUR": System.out.println( "HONOPREJUR");validar2= "HONOPREJUR";break; 
						case  "IMO": System.out.println( "IMO");validar2= "IMO";break; 
						case  "INT": System.out.println( "INT");validar2= "INT";break; 
						case  "INTCUND": System.out.println( "INTCUND");validar2= "INTCUND";break; 
						case  "IOCCASTIGO": System.out.println( "IOCCASTIGO");validar2= "IOCCASTIGO";break; 
						case  "IOCSEGDES": System.out.println( "IOCSEGDES");validar2= "IOCSEGDES";break; 
						case  "IOCSEGUROS": System.out.println( "IOCSEGUROS");validar2= "IOCSEGUROS";break; 
						case  "IVACOMCHG": System.out.println( "IVACOMCHG");validar2= "IVACOMCHG";break; 
						case  "IVACOMGFAG":System.out.println( "IVACOMGFAG");validar2= "IVACOMGFAG";break; 
						case  "IVAFAGANU": System.out.println( "IVAFAGANU");validar2= "IVAFAGANU";break; 
						case  "IVAFAGMED": System.out.println( "IVAFAGMED");validar2= "IVAFAGMED";break; 
						case  "IVAFNGANTI": System.out.println( "IVAFNGANTI");validar2= "IVAFNGANTI";break; 
						case  "IVAMEDPROD": System.out.println( "IVAMEDPROD");validar2= "IVAMEDPROD";break; 
						case  "IVAPYME2": System.out.println( "IVAPYME2");validar2= "IVAPYME2";break; 
						case  "IVASEGTRVI": System.out.println( "IVASEGTRVI");validar2= "IVASEGTRVI";break; 
						case  "MONEXTRAN": System.out.println( "MONEXTRAN");validar2= "MONEXTRAN";break; 
						case  "NCAHINTER": System.out.println( "NCAHINTER");validar2= "NCAHINTER";break; 
						case  "NCAHO":System.out.println( "NCAHO");validar2= "NCAHO";break; 
						case  "NCCC": System.out.println( "NCCC");validar2= "NCCC";break; 
						case  "NCCCINTER": System.out.println( "NCCCINTER");validar2= "NCCCINTER";break; 
						case  "NCCCNB": System.out.println( "NCCCNB");validar2= "NCCCNB";break; 
						case  "NDACBPCS": System.out.println( "NDACBPCS");validar2= "NDACBPCS";break; 
						case  "NDAHINTERN": System.out.println( "NDAHINTERN");validar2= "NDAHINTERN";break; 
						case  "NDAHO": System.out.println( "NDAHO");validar2= "NDAHO";break; 
						case  "NDCC": System.out.println( "NDCC");validar2= "NDCC";break; 
						case  "NDCCBPCS": System.out.println( "NDCCBPCS");validar2= "NDCCBPCS";break; 
						case  "NDCCINTERN": System.out.println( "NDCCINTERN");validar2= "NDCCINTERN";break; 
						case  "NDCCNB":System.out.println( "NDCCNB");validar2= "NDCCNB";break; 
						case  "OPAGXCCLI": System.out.println( "OPAGXCCLI");validar2= "OPAGXCCLI";break; 
						case  "PAGAJUST": System.out.println( "PAGAJUST");validar2= "PAGAJUST";break; 
						case  "PAGCOMPRA": System.out.println( "PAGCOMPRA");validar2= "PAGCOMPRA";break; 
						case  "PAGEXTCAST": System.out.println( "PAGEXTCAST");validar2= "PAGEXTCAST";break; 
						case  "PAGOSEBRA": System.out.println( "PAGOSEBRA");validar2= "PAGOSEBRA";break; 
						case  "PAGSERBAN": System.out.println( "PAGSERBAN");validar2= "PAGSERBAN";break; 
						case  "PDGTIA": System.out.println( "PDGTIA");validar2= "PDGTIA";break; 
						case  "PGBANANERA": System.out.println( "PGBANANERA");validar2= "PGBANANERA";break; 
						case  "PGLIBFOPEP": System.out.println( "PGLIBFOPEP");validar2= "PGLIBFOPEP";break; 
						case  "PGLIBRABAC": System.out.println( "PGLIBRABAC");validar2= "PGLIBRABAC";break; 
						case  "PGRETANQ": System.out.println( "PGRETANQ");validar2= "PGRETANQ";break; 
						case  "REAPLICPAG": System.out.println( "REAPLICPAG");validar2= "REAPLICPAG";break; 
						case  "RECFOGCAFE": System.out.println( "RECFOGCAFE");validar2= "RECFOGCAFE";break; 
						case  "RECGAROT": System.out.println( "RECGAROT");validar2= "RECGAROT";break; 
						case  "REINCOMFAG": System.out.println( "REINCOMFAG");validar2= "REINCOMFAG";break; 
						case  "REINTHONOR": System.out.println( "REINTHONOR");validar2= "REINTHONOR";break; 
						case  "SEGEXTRAPR": System.out.println( "SEGEXTRAPR");validar2= "SEGEXTRAPR";break; 
						case  "SEGITP": System.out.println( "SEGITP");validar2= "SEGITP";break; 
						case  "SEGTORVIVI": System.out.println( "SEGTORVIVI");validar2= "SEGTORVIVI";break; 
						case  "SEGVIDA": System.out.println( "SEGVIDA");validar2= "SEGVIDA";break; 
						case  "SEGVIDAF": System.out.println( "SEGVIDAF");validar2= "SEGVIDAF";break; 
						case  "SGDESEMPLE": System.out.println( "SGDESEMPLE");validar2= "SGDESEMPLE";break; 
						case  "SOBRANTE": System.out.println( "SOBRANTE");validar2= "SOBRANTE";break; 
						case  "TASAPUT": System.out.println( "TASAPUT");validar2= "TASAPUT";break; 
						case  "VAC0": System.out.println( "VAC0");validar2= "VAC0";break; 
						case  "VAC1": System.out.println( "VAC1");validar2= "VAC1";break; 
						case  "VAC2":System.out.println( "VAC2");validar2= "VAC2";break; 
						default:
							System.out.println( "PAGLIBRANZ");validar2= "PAGLIBRANZ";
							break;
						}
					}
				});
				
//=========================================================================================================================		
		activar();// activa el boton ejecutar
		CerrarV();//pregunta si quieres cerrar la ventana
		//CARGAR();
	}
//=======================================================================================
//***************************************************************************************
	public void Liistas(){
		
		String NomArchivo;	
		int eleccion;
		String COB="";
		String borrar;
		int MesAnt2;
		int MesAnt;
		
		Listas.setModel(ModeloLista);
		
		File ruta =new File(textField.getText(), "");
		String[] nombre_Archvios=ruta.list();
			
		for (int i=0;i<nombre_Archvios.length;i++){
			boolean txtt =nombre_Archvios[i].contains(".txt");
			//boolean txtc =nombre_Archvios[i].contains("cobrconv");
				if (txtt==true /*&& txtc==true*/ ){
					ModeloLista.addElement(nombre_Archvios[i]);
				}
		}
	}
//=======================================================================================
//***************************************************************************************
	public void Listas2(){
		
		String Seleccion="Select Nombre from [Libranza].[dbo].DataCobrconv";
		DefaultTableModel resultado;
			try {
				resultado  = base.ConsultarQuery(Seleccion);
				int cantidadCobr = resultado.getRowCount();
					for(int f=0; f<cantidadCobr; f++){
						String Listasubida=resultado.getValueAt(f, 0).toString().trim();
						ModeloLista2.addElement(Listasubida);
					}
				} 
				catch (SQLException e1) {
					e1.printStackTrace();
				}
		listCargados1.setModel(ModeloLista2);
	}
//=======================================================================================
//***************************************************************************************
	public void CARGAR(){
		validar="";
		ARCH=Listas.getSelectedValuesList();
		validar=ARCH.toString().replace("[", "").replace("]", "");
	}
//=======================================================================================
//***************************************************************************************	
	public void quitarvalores(){
		String Seleccion;
		int pos =listCargados1.getSelectedIndex();
		if(pos>=0){
			int respuesta=JOptionPane.showConfirmDialog(null,"Realmente desea quitar este registro?");
			if(respuesta==0){
				ModeloLista2.remove(pos);
				if(pos==0){
				Seleccion="truncate table [Libranza].[dbo].[Cobrconv]"+" update [Libranza].[dbo].DataCobrconv set Nombre ='' where posicion=1";
					try {
						base.EjecutarQuery(Seleccion);
						ModeloLista2.clear();
						Listas2();
						activar();// activa el boton ejecutar
					} 
					catch (SQLException e1) {
						e1.printStackTrace();
					}
				}
				else if(pos==6){
					Seleccion="truncate table [Libranza].[dbo].BANCOLOMBIA "+" update [Libranza].[dbo].DataCobrconv set Nombre ='' where posicion=7";
					try {
						base.EjecutarQuery(Seleccion);
						ModeloLista2.clear();
						Listas2();
						activar();// activa el boton ejecutar
					} 
					catch (SQLException e1) {
						e1.printStackTrace();
					}
				}
				else if(pos>=7 && pos<=9){
					Seleccion="truncate table [Libranza].[dbo].[Catradia"+(pos-6)+"] "+" update [Libranza].[dbo].DataCobrconv set Nombre ='' where posicion="+(pos+1);
					G=(pos-6);
					G--;//disminuye el contador de catradia, para mantener la posicion de las casillas donde se va almacenar los archivos
					if(G<=0){G=0;}
					try {
						base.EjecutarQuery(Seleccion);
						ModeloLista2.clear();
						Listas2();
						activar();// activa el boton ejecutar
					} 
					catch (SQLException e1) {
						e1.printStackTrace();
					}
				}
				else if(pos>=10 && pos<=12){
					Seleccion="truncate table [Libranza].[dbo].[CagmaestP"+(pos-9)+"] "+" update [Libranza].[dbo].DataCobrconv set Nombre ='' where posicion="+(pos+1);
					H=(pos-9);
					H--;//disminuye el contador de catradia, para mantener la posicion de las casillas donde se va almacenar los archivos
					if(H<=0){H=0;}
					try {
						base.EjecutarQuery(Seleccion);
						ModeloLista2.clear();
						Listas2();
						activar();// activa el boton ejecutar
					} 
					catch (SQLException e1) {
						e1.printStackTrace();
					}
				}
				else if(pos==13){
					Seleccion="truncate table [Libranza].[dbo].ParametrizadosP "+" update [Libranza].[dbo].DataCobrconv set Nombre ='' where posicion=14";
					try {
						base.EjecutarQuery(Seleccion);
						ModeloLista2.clear();
						Listas2();
						activar();// activa el boton ejecutar
					} 
					catch (SQLException e1) {
						e1.printStackTrace();
					}
				}
					else {	
						Seleccion="truncate table [Libranza].[dbo].[P_Cobrconv"+(pos+1)+"]" +" update  [Libranza].[dbo].DataCobrconv set Nombre ='' where posicion="+(pos+1);
						k=(pos+1);
						k--;
						if(k<=0){k=0;}
						try {
							base.EjecutarQuery(Seleccion);
							ModeloLista2.clear();
							Listas2();
							activar();// activa el boton ejecutar
						} 
						catch (SQLException e1) {
							e1.printStackTrace();
					}
				}
			}
		}
	}
//=======================================================================================
//***************************************************************************************	
	public void activar(){
		boolean CantidadRegistros =ModeloLista2.contains("");//indica si se han subido todos los archivos y activa el boton de ejecutar
		String Select = "SELECT * FROM [Libranza].[dbo].[DataCobrconv]";//trae los registros de la BD
				DefaultTableModel result;
			try {
				result  = base.ConsultarQuery(Select);
				String registro1=result.getValueAt(0, 0).toString();//pregunta si existe algun archivo en la posicion 1 de sql
				String registro2=result.getValueAt(1, 0).toString();//pregunta si existe algun archivo en la posicion 2 de sql
				String registro3=result.getValueAt(2, 0).toString();//pregunta si existe algun archivo en la posicion 3 de sql
				String registro4=result.getValueAt(3, 0).toString();//pregunta si existe algun archivo en la posicion 4 de sql
				String registro5=result.getValueAt(4, 0).toString();//pregunta si existe algun archivo en la posicion 5 de sql
				String registro6=result.getValueAt(5, 0).toString();//pregunta si existe algun archivo en la posicion 6 de sql
				String registro7=result.getValueAt(6, 0).toString();//pregunta si existe algun archivo en la posicion 7 de sql
				String registro8=result.getValueAt(7, 0).toString();//pregunta si existe algun archivo en la posicion 8 de sql
				String registro9=result.getValueAt(8, 0).toString();//pregunta si existe algun archivo en la posicion 9 de sql
				String registro10=result.getValueAt(9, 0).toString();//pregunta si existe algun archivo en la posicion 10 de sql
				String registro11=result.getValueAt(10, 0).toString();//pregunta si existe algun archivo en la posicion 11 de sql
				String registro12=result.getValueAt(11, 0).toString();//pregunta si existe algun archivo en la posicion 12 de sql
				String registro13=result.getValueAt(12, 0).toString();//pregunta si existe algun archivo en la posicion 13 de sql
				String registro14=result.getValueAt(13, 0).toString();//pregunta si existe algun archivo en la posicion 14 de sql
					if(CantidadRegistros==false || (registro1.isEmpty()==false && registro2.isEmpty()==false && registro3.isEmpty()==false && registro4.isEmpty()==false && registro5.isEmpty()==false
							 && registro6.isEmpty()==true && registro7.isEmpty()==false && registro8.isEmpty()==false && registro9.isEmpty()==false && registro10.isEmpty()==false && registro11.isEmpty()==false
							 && registro12.isEmpty()==false && registro13.isEmpty()==false && registro14.isEmpty()==false) ){
						Btn_Ejecutar.setEnabled(true);
					}
					else{Btn_Ejecutar.setEnabled(false);	}	
			
			} catch (SQLException e) {
				e.printStackTrace();
			}
	}
//=======================================================================================
//***************************************************************************************	
	public static String FechaActual(){
		Date Fecha=new Date();
		SimpleDateFormat FormatoFecha=new SimpleDateFormat("dd/MM/YYYY");//pasa la fecha a formato String
		return FormatoFecha.format(Fecha);
	}
}//fin de public class Pagos