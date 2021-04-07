/*permite seleccionar el tipo de aplicativo a ejecutar Facturacion, Novedades, Pagos*/


package graficos;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

import java.awt.Color;
import java.awt.GridBagLayout;

import javax.swing.JComboBox;
import javax.swing.JButton;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.DefaultComboBoxModel;

import com.jgoodies.forms.factories.DefaultComponentFactory;

import java.awt.Font;

import javax.swing.JTextField;

import graficos.MENÚ;
import graficos.Novedades;
import graficos.Pagos;
import java.awt.Toolkit;

/**
 * 
 * @author yeiperea
 * @version 1.3.1
 *
 */

public class Seleccion extends JFrame {//PRINCIPAL

	private JPanel contentPane;
	private String validar;
	private JTextField textField;

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Seleccion frame = new Seleccion();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}


public void CerrarV(){//Cerrar ventana
		
		this.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		addWindowListener(new WindowAdapter(){
			public void windowClosing(WindowEvent we){
				CerrarVentana();
			}
		});
		this.setVisible(true);
	}

	public void CerrarVentana(){//PERMITE CERRAR LA VENTANA
		String Botones[]={"Aceptar","Cancelar"};
		int eleccion=JOptionPane.showOptionDialog(this, "¿ Está seguro de querer cerrar la aplicación? ","Advertencia",0,0,null,Botones,this);
		if (eleccion==JOptionPane.YES_OPTION){
			JOptionPane.showMessageDialog(null, "Gracias por su visita, hasta pronto","Gracias",JOptionPane.INFORMATION_MESSAGE);
			System.exit(0);
		}
	}
	
	 private void btnMinimizarActionPerformed() {
		this.setExtendedState(ICONIFIED);
	}
	 
/**
 * ESTA FUNCION PERMITE SELECCIONAR EL TIPO DE APLICACION
 */
	public Seleccion() {
	
		//tamaño de la ventana de seleccion
		setResizable(false);
		setAutoRequestFocus(false);
		setBounds(100, 100, 808, 301);
		contentPane = new JPanel();
		contentPane.setBackground(Color.WHITE);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		CerrarV();//pregunta si quieres cerrar la ventana
		
		setTitle("Libranza");
		setIconImage(Toolkit.getDefaultToolkit().getImage(Seleccion.class.getResource("/Imagenes/BAC.png")));
//=============================================================================================================================================
		//boton de seleccion
		JButton btnSeleccion = new JButton("Seleccionar");
		btnSeleccion.setEnabled(false);
		btnSeleccion.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				MENÚ funcion =new MENÚ();
				Pagos funcion2 =new Pagos();
				Novedades funcion3 =new Novedades();
				switch(validar){
					case "Facturación":
						/**
						 * LLAMA LA APLICACION FACTURACION
						 */
						btnMinimizarActionPerformed();//minimiza la pantalla
						funcion.MenuFacturacion();
						break;
					case "Pagos":
						/**
						 * LLAMA LA APLICACION PAGOS
						 */
						btnMinimizarActionPerformed();//minimiza la pantalla
						funcion2.MenuPagos();
						break;
					case "Novedades":
						/**
						 * LLAMA LA APLICACION NOVEDADES
						 */
						btnMinimizarActionPerformed();//minimiza la pantalla
						funcion3.MenuNovedades();
						break;
					}
			}
		});
		/*imagen de fondo*/
		JLabel label = DefaultComponentFactory.getInstance().createLabel("");
		label.setHorizontalAlignment(SwingConstants.CENTER);
		label.setIcon(new ImageIcon(MENÚ.class.getResource("/Imagenes/images.png")));
		label.setBounds(1, 1, 792, 190);
		contentPane.add(label);
		
		btnSeleccion.setBounds(648, 203, 109, 28);
		contentPane.add(btnSeleccion);
		
//=============================================================================================================================================
		// Menu desplegable
		JComboBox comboBox = new JComboBox();
		comboBox.setFont(new Font("Tahoma", Font.PLAIN, 15));
		comboBox.setModel(new DefaultComboBoxModel(new String[] {"Seleccione una Aplicaci\u00F3n", "Facturaci\u00F3n", "Novedades", "Pagos"}));//CONTENIDO DEL MENU
		comboBox.setMaximumRowCount(10);
		comboBox.setToolTipText("");
		comboBox.setBackground(Color.WHITE);
		comboBox.setBounds(38, 202, 574, 26);
		contentPane.add(comboBox);
		
		textField = new JTextField();
		textField.setBackground(new Color(107, 142, 35));
		textField.setBounds(1, 1, 790, 1);
		contentPane.add(textField);
		textField.setColumns(10);
		
		/*seleccion de aplicacion*/
		comboBox.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				switch (comboBox.getSelectedItem().toString()) {
				case "Facturación":
					System.out.println("Facturación");
					validar="Facturación";
					btnSeleccion.setEnabled(true);
					break;
				case "Pagos":
					System.out.println("Pagos");
					btnSeleccion.setEnabled(true);
					validar ="Pagos";
					break;
				case "Novedades":
					System.out.println("Novedades");
					btnSeleccion.setEnabled(true);
					validar="Novedades";
					break;
				default:
					btnSeleccion.setEnabled(false);
					break;
				}
			}
		});
		
	}
}
