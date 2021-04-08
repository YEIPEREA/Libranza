/**
 * Permite enviar los correo de forma masiva o por regional
 */
package CapaEmail;

import java.io.File;
import java.io.IOException;
import java.sql.SQLException;
import java.util.Properties;

import javax.activation.DataHandler;
import javax.activation.FileDataSource;
import javax.mail.BodyPart;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.table.DefaultTableModel;








import CargaDatos.conexionBD;

public class Email {
/**
 * Permite enviar loss correo por numero de convenio que se encuentran separados por regional 
 * 
 * @param direccion ruta donde se encuentra los archivos encriptados por el numero de nit
 * @param PanelPorcentaje Actualizacion de porcentaje
 * @param LabelPorcentaje  Actualizacion de porcentaje
 * @param region es la regional que se ha escogido para enviar los correos
 * @param Quincena indica el mes de la quincena y si son los primero 15 dias del mes o los ultimos 15 dias
 */
	public void Correo(String direccion,JPanel PanelPorcentaje,JLabel LabelPorcentaje,String region, JLabel Quincena) throws IOException{
		conexionBD base = new conexionBD();
		String Convenio;
		boolean Existe;
		String RT3="";
		int cantiidades =0;
		String RT1;
		int n=0;
		String Blue="Blue";
		String Yellow="yellow";
		String White="white";
		String TipoLeta="Arial";
		int cont=-1;
		
		
		String Validar[]=Quincena.getText().split("-");
		String Corter= Validar[0].toString().trim();
		String numero=Corter.replace("Corte ", "").replace("_", "");//QUINCENA 1 O QUINCENA 2
		String fecha[]=Validar[1].trim().split(" ");
		String Mes=fecha[0].toString().trim();//MES
		String Anio=fecha[1].toString().trim();//AÑO
		System.out.println(Mes+" "+Anio);
		
		
	try {	
		
		LabelPorcentaje.setText("Conectando...");
		PanelPorcentaje.update(PanelPorcentaje.getGraphics());
		
		String DatosEmail="SELECT * FROM [Libranza].[dbo].[Correo_Remitente]";
		DefaultTableModel resultado;
		resultado = base.ConsultarQuery(DatosEmail);
		String Nombre=resultado.getValueAt(0, 0).toString().trim();
		String correoRemitente = resultado.getValueAt(0, 1).toString().trim();
		String claveCorreoRemitente = resultado.getValueAt(0, 2).toString().trim();
		String CorreoMascara= resultado.getValueAt(0, 3).toString().trim();
		System.out.println(Nombre+" "+correoRemitente+" "+" ********  Mascara "+CorreoMascara);
	
		
		//configutacion coneccion al banco agrario
		Properties props = new Properties();//@bancoagrario.gov.co Dominio del Correo 
		props.setProperty("mail.smtp.host", "10.0.8.8");
		//props.setProperty("mail.smtp.starttls", "true");
		props.setProperty("mail.smtp.port", "25");
		props.setProperty("mail.smtp.user", Nombre);
		props.setProperty("mail.smtp.auth", "true");
		
		/*
		//configuracion a hotmail
		Properties props = new Properties();
		props.setProperty("mail.smtp.host", "smtp.outlook.com");
		props.put("mail.smtp.ssl.trust", "smtp.outlook.com");
		props.setProperty("mail.smtp.starttls.enable", "true");
		props.setProperty("mail.smtp.port", "587");
		props.setProperty("mail.smtp.auth", "true");
		*/
		switch (region) {
		case "TODOS"://en caso de querer enviar los correos de forma masiva
			RT1 = "SELECT [CONVENIO] FROM [Libranza].[dbo].[BASE_SALDOS_ACTUALIZADOS_FACTURACION_CORTE] GROUP BY CONVENIO ORDER BY CAST(CONVENIO AS int)";
		 break;
		 default://envio de correo por regional
			RT1 = "SELECT BASE_SALDOS_ACTUALIZADOS_FACTURACION_CORTE.CONVENIO from "
				+" [Libranza].[dbo].[BASE_SALDOS_ACTUALIZADOS_FACTURACION_CORTE] inner join "
				+" [Libranza].[dbo].[Parametrizados] on BASE_SALDOS_ACTUALIZADOS_FACTURACION_CORTE.CONVENIO= "
				+" Parametrizados.Convenio WHERE Parametrizados.Regional='"+region+"' AND Parametrizados.Contacto<>'' "
				+" group by BASE_SALDOS_ACTUALIZADOS_FACTURACION_CORTE.CONVENIO";
			}
	
	DefaultTableModel result;
	DefaultTableModel result2;
	DefaultTableModel result3;
	result = base.ConsultarQuery(RT1);//CONSULTA EL QUERY DEPENDIENDO SI ES POR ENVIOS MASIVO O POR REGIONAL
			int cantiidad = result.getRowCount();//CANTIDAD DE REGISTROS QUE TRAJO EL QUERY
		
		//cuerpo del mensaje que se va a enviar por correo
		String mensaje ="<body>"
				+"<td height=430 class=xl6311626 width=279 >"
				+"<font face="+TipoLeta+">"
				+"<font size=3>"
				+"Buen día, <br> <br> "
				+"Reciba un cordial saludo de parte de El Banco Agrario de Colombia y en especial de la Operativa de Convenios de Crédito, Libranza y Seguros.<br> <br> "
				+"Nos permitimos remitir los archivos en Excel con la facturación emitida por el Banco Agrario para el mes de "+Mes+" de "+ Anio+". <br> <br> <br> "
				+""
				+"<u><b><mark>Para acceder al archivo por favor digitar el NIT de la entidad completo sin guiones ni puntos.</mark></u></b><br><br> "
				+"<u><font bgcolor="+Blue+" >Así mismo agradecemos su colaboración con la captura de la información en las columnas <b>O,P,Q (Valor Pago Empresa,Novedad,Observaciones )e "
				+"informarnos la fecha de pago de la empresa y el medio utilizado. </font></u></b><br><br>"
				+"Gracias por la atención prestada, quedamos atentos a sus respuestas o inquietudes en caso de presentarse. <br><br>"
				+"Con el ánimo de brindarles un mejor servicio y mayor atención informamos que estos archivos serán remitidos mensualmente. <br><br>"
				+"<u><b><mark>Favor enviar el soporte cuando haya efectuado el pago junto con la relación de los pagos de los clientes al "
				+"correo <font color="+Blue+"> convenios.libranzas@bancoagrario.gov.co</mark></u></b><br><br></font>"
				+"Es importante realizar el pago de la factura en la fecha de corte, teniendo en cuenta que el atraso genera intereses de mora. <br><br>"
				+"<font color="+Blue+"> "
				+"<b>Cordial saludo,<br><br>"
				+"Operativa de Convenios de Crédito, Libranza y Seguros</b> <br>"
				+"Gerencia Operativa de Convenios<br> "
				+"BANCO AGRARIO DE COLOMBIA S. A. <br>"
				+"<small><u>convenios.libranzas@bancoagrario.gov.co <br>"
				+"www.bancoagrario.gov.co</u></small><br> "
				+"PBX: (1) 5945555<br>"
				+"Calle 16 No. 6 - 66 Edificio avianca Piso 33 Bogotá, Colombia <br></font> "
				+"</body> ";
		
		for (int L=0;L<cantiidad;L++){
			Convenio = result.getValueAt(L, 0).toString();
			n++;
			
			RT3="SELECT Documento,Regional,Contacto  FROM [Libranza].[dbo].[Parametrizados] where Convenio="+Convenio;//CONSULTA UN CONVENIO ES ESPECIFICO
					
				result3 = base.ConsultarQuery(RT3);
				String Regional = result3.getValueAt(0, 1).toString();//REGIONAL
				String Nit= result3.getValueAt(0, 0).toString();//NIT
				String Correo = result3.getValueAt(0, 2).toString();//Email A QUIEN SE LE VA A ENVIAR EL MENSAJE
				String asunto = "BANCO AGRARIO DE COLOMBIA REGIONAL " + Regional + " CONVENIO LIBRANZA - "+Convenio;//ASUNTO DEL CORREO
				
				if (Correo.equals("")){//Solo va a enviar Email a  quellos convenios que contengan un correo
					System.out.println("no tiene correo");
				}
				else{//convenios que contienen un Correo
					
				Session session = Session.getDefaultInstance(props);// se envian las porpiedades smtp construir mensaje de texto
				
				String correoDestinatario=Correo;//correo destinatario
				String correoDes[]=correoDestinatario.split(";");//en caso de existir mas de un correo destinatario
					
					BodyPart Texto = new MimeBodyPart();//Bodypart Texto
					Texto.setContent(mensaje, "text/html");//indicamos que el mensaje esta en formato HTML
					// ADJUNTA ARCHIVO
					BodyPart adjunto = new MimeBodyPart();//Bodypart ARchivos adjuntos
					adjunto.setDataHandler(new DataHandler(new FileDataSource(direccion+"\\"+Regional+"\\Encriptado\\"+Convenio+".xls")));//direccion del archivo a adjuntar
					double numEntero = Double.parseDouble(Convenio);
					long numeroConvenio=Math.round(numEntero);
					//adjunto.setFileName(numeroConvenio+".xls");//nombre con el cual se envia el archivo
					adjunto.setFileName(Convenio+".xls");//nombre con el cual se envia el archivo
					
					File file = new File(direccion+"\\"+Regional+"\\Encriptado\\"+Convenio+".xls");
					
			if (file.exists()){
						
					MimeMultipart Multipart = new MimeMultipart();
					//creamos un multipart para el texto y el archivo adjunto
					Multipart.addBodyPart(Texto);
					Multipart.addBodyPart(adjunto);
					
					MimeMessage message = new MimeMessage(session);// envia la sesion
					message.setFrom(new InternetAddress(CorreoMascara));// cuenta que va a enviar el correo
						for(int z=0;z<correoDes.length;z++){			
							//message.setFrom(new InternetAddress(CorreoMascara));// cuenta que va a enviar el correo
							message.addRecipients(Message.RecipientType.TO, InternetAddress.parse(correoDes[z]));// receptor principal sin copia
						}
					message.setContent(Multipart);// envia mensaje, CON CODIFICACION A ESPAÑOL
					message.setSubject(asunto);// envia asunto
					// Enviar el mensaje
					Transport t = session.getTransport("smtp");// conexion al correo
					t.connect(correoRemitente, claveCorreoRemitente);//conectando con el  correo remitente y clave
					t.sendMessage(message, message.getAllRecipients());// envia mensaje
			
					int Porcentaje = ((n*100)/cantiidad);
						if (Porcentaje>=(cont+1)){//muestra el porcentaje solo una vez
							LabelPorcentaje.setText("Porcentaje : "+Porcentaje + "% " + " Van "	+ n + " Mensaje(s) Enviado(s)"+ " de " + cantiidad);
							PanelPorcentaje.update(PanelPorcentaje.getGraphics());
							cont++;
							if(cont>=99){cont=99;}
						}
					if (n==cantiidad){//indica cuando los mensajes fueron enviados
						t.close();// cierra el correo
						JOptionPane.showMessageDialog(null,cantiidad+" "+"Mensajes Enviados");// indica si  el mensaje a sido enviado
					}
			}
			else{LabelPorcentaje.setText("Archivo no existe");
				PanelPorcentaje.update(PanelPorcentaje.getGraphics());
						if (n==cantiidad){
							LabelPorcentaje.setText("Proceso Finalizado");
							PanelPorcentaje.update(PanelPorcentaje.getGraphics());
							//JOptionPane.showMessageDialog(null," Mensajes YA Enviados");// indica si  el mensaje a sido enviado
						}
				}
			}
		}  //fin del for
				
				}catch (AddressException e) {
						e.printStackTrace();
				 } 
			catch (MessagingException e) {
			    	e.printStackTrace();
			  }
		catch (SQLException e) {
			e.printStackTrace();
		}
	
	}

}
