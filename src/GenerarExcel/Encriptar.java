/**
 * Encripta archivos RAR por numero de nit
 */
package GenerarExcel;

import java.io.File;
import java.util.ArrayList;

import net.lingala.zip4j.core.ZipFile;
import net.lingala.zip4j.exception.ZipException;
import net.lingala.zip4j.model.ZipParameters;
import net.lingala.zip4j.util.Zip4jConstants;

public class Encriptar {
	
	public void EcriptarZip (String direccion,String Convenio,String Regional,String Clave) {
		String nombre="prueba";
		boolean Existe;
  try {
	  
		   ZipFile zipFile = new ZipFile(direccion+"\\"+Regional+"\\C1_"+ Convenio +".zip");//ubicacion donde se guarda el archivo y el nombre del zip
		   ArrayList filesToAdd = new ArrayList();
		   filesToAdd.add(new File(direccion+"\\"+Regional+"\\C1_"+ Convenio +".xls"));//ubicacion del archivo a comprimir
		   
		   ZipParameters parameters = new ZipParameters();
		   parameters.setCompressionMethod(Zip4jConstants.COMP_DEFLATE); // habilita el metodo de compresion
		   parameters.setCompressionLevel(Zip4jConstants.DEFLATE_LEVEL_NORMAL); //metodo de compresion normal
		   
		   parameters.setEncryptFiles(true);//habilita el metodo de encriptado
		   parameters.setEncryptionMethod(Zip4jConstants.ENC_METHOD_AES);
		   parameters.setAesKeyStrength(Zip4jConstants.AES_STRENGTH_256);
		   parameters.setPassword(Clave);//contraseña
		 
		   zipFile.addFiles(filesToAdd, parameters);//crea el archivo zip
		   
  } catch (ZipException e) {
   e.printStackTrace();
  }
 }

}