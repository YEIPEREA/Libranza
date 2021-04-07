package graficos;

import java.awt.*;

import javax.swing.*;
import javax.swing.border.EmptyBorder;

import java.io.BufferedReader;
import java.io.FileReader;

import javax.swing.JTextField;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.sql.DriverManager;

import CapaNegocio.Proceso;
import CapaNegocio.cargaTXR;
import CargaDatos.conexionBD;
import CapaEjecucion.Ejecucion;
import CargaDatos.conexionBD;

public class MENÚ extends JFrame {
	
	
	private String valida;
	private JPanel contentPane;
	private JTextField textField;
	private JTextField textField_1;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		
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


	public MENÚ() {
		conexionBD close = new conexionBD();
		
		setBackground(new Color(25, 25, 112));
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		Toolkit miPantalla=Toolkit.getDefaultToolkit();
		Dimension tamanoPantalla=miPantalla.getScreenSize();//devuelve el tamaño de la pantalla 
		int altura=tamanoPantalla.height;//altude pantalla
		int ancho=tamanoPantalla.width;// ancho de pantalla	
		setBounds(ancho/4,altura/4,642,368);//Posicion de la ventana centrada	y tamaño de la ventana
		setResizable(false);//Impide que se cambie el tamaño de la ventana		
		setTitle("FACTURACIÓN");//TITULO DE LA VENTANA	
		contentPane = new JPanel();
		contentPane.setBackground(SystemColor.control);//fondo de la ventana
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		
		JComboBox comboBox = new JComboBox();
		comboBox.setBackground(Color.WHITE);//color del menu desplegable
		comboBox.setForeground(Color.BLACK);//color de letras
		comboBox.setFont(new Font("Arial", Font.PLAIN, 15));//tipo de letra del menu deplegable
		comboBox.setMaximumRowCount(10);
		comboBox.setBounds(10, 100, 357, 25);
		//comboBox.getColorModel(Color.cyan);
		comboBox.setModel(new DefaultComboBoxModel(new String[] {"Seleccione un archivo ...", "Cagmaest", "Cobrconv", "Desvinculado", "BanColombia"}));
		contentPane.add(comboBox);
		
		JButton btnCargar = new JButton("Cargar");//boton cargar
		btnCargar.setForeground(SystemColor.windowText);
		btnCargar.setBackground(SystemColor.scrollbar);
		btnCargar.setEnabled(false);
//===================================BOTON CARGAR======================================================				
		btnCargar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				cargaTXR function = new cargaTXR();	

				/*SWITCH DE VALIDACION PARA LLAMAR LA FUNCION SEGUN LA SELECCION DEL USUARIO 
				 EN LA LISTA DESPLEGABLE*/
				
				switch (valida) {
				case "Cobrconv":
					System.out.println("Arhivo Cobrconv Seleccionado");
					function.leerTxtCobrconv(textField.getText());						
					break;
				case "Desvinculado":
					System.out.println("Arhivo BASE DESVINCULADOS Seleccionado");
					function.leerTxtBaseDesvinculados(textField.getText());						
					break;
				case "BanColombia":
					System.out.println("Arhivo CUENTA BANCOLOMBIA Seleccionado");
					function.leerTxtBancolombia(textField.getText());						
					break;
				case "Cagmaest":
					System.out.println("Arhivo Cagmaest Seleccionado");
					function.leerTxtCagmaest(textField.getText());						
					break;
					
				
				default:
					break;
				}

				close.CerrarconexionBD();
			}
		});
//=========================================================================================		
		btnCargar.setBounds(377, 137, 89, 25);
		contentPane.add(btnCargar);		
		
		JButton btnNewButton = new JButton("Examinar");
		btnNewButton.setForeground(SystemColor.windowText);
		btnNewButton.setBackground(SystemColor.text);
//=============================Examinar documento============================================================			
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JFileChooser jf = new JFileChooser();
				jf.showOpenDialog(jf);
				File archivos = jf.getSelectedFile();
				if(archivos != null){
					textField.setText(archivos.getAbsolutePath());
				}
				//BTNImportar.setEnabled(true)
				btnCargar.setEnabled(true);
			}
			}
		);//cierre de btnNewButton
//==============================================================================================		
		btnNewButton.setBounds(377, 100, 89, 25);
		contentPane.add(btnNewButton);
		
		textField = new JTextField();
		//textField.setEnabled(false);
		textField.setEditable(false);
		textField.setBounds(10, 136, 357, 25);
		contentPane.add(textField);
		textField.setColumns(10);
		
		JButton btnEjecutar = new JButton("Ejecutar");
		btnEjecutar.setBackground(UIManager.getColor("Button.light"));
		btnEjecutar.addActionListener(new ActionListener() {
//========================================Ejecuta el Proyecto======================================================				
			public void actionPerformed(ActionEvent e) {
				Ejecucion funcion = new Ejecucion();	//prueba si se han subido todos los archivos 
				funcion.conteo();	
			}
		});
		btnEjecutar.setBounds(10, 172, 89, 25);
		contentPane.add(btnEjecutar);
		
		
//=====================================captura los datos del menu desplegable==========================================================
		comboBox.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				//String Archivo;
				//Archivo=comboBox.getSelectedItem().toString();//captura el valor del menu desplegable
			switch (comboBox.getSelectedItem().toString()) {
				case "Cagmaest":
					System.out.println("Cagmaest");
					btnNewButton.setEnabled(true);
					textField.setEnabled(true);
					valida="Cagmaest";
				break;
				case "Cobrconv":
					System.out.println("Cobrconv");
					btnNewButton.setEnabled(true);
					textField.setEnabled(true);
					valida="Cobrconv";
				break;
				case "Desvinculado":
					System.out.println("Desvinculado");
					btnNewButton.setEnabled(true);
					textField.setEnabled(true);
					valida="Desvinculado";
				break;
				case "BanColombia":
					System.out.println("BanColombia");
					btnNewButton.setEnabled(true);
					textField.setEnabled(true);
					valida="BanColombia";
				break;
				case "Seleccione un archivo ...":
					System.out.println("nada");
					textField.setEnabled(false);
					textField.setText("");//limpia el text box
					btnCargar.setEnabled(false);
					btnNewButton.setEnabled(false);
				break;
			default:

							}
						}
					}
				);	//cierre del combobox
//===============================================================================================
	}
}

