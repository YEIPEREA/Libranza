package graficos;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

public class Novedades extends JFrame {

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
//public static void main(String[] args) {
	public void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Novedades frame = new Novedades();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}


	
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
	
	
	public void MenuNovedades() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		CerrarV();//pregunta si quieres cerrar la ventana
	}

}
