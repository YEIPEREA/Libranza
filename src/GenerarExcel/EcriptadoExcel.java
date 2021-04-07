/**
 * encripta los archivos excel por numero de nit
 */
package GenerarExcel;
import java.io.BufferedInputStream; 
import java.io.FileInputStream; 
import java.io.FileOutputStream; 
import java.io.IOException; 
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

import org.apache.poi.hssf.record.crypto.Biff8EncryptionKey; 
import org.apache.poi.hssf.usermodel.HSSFRow; 
import org.apache.poi.hssf.usermodel.HSSFSheet; 
import org.apache.poi.hssf.usermodel.HSSFWorkbook; 
import org.apache.poi.poifs.filesystem.POIFSFileSystem; 
import org.apache.poi.ss.usermodel.Cell; 

public class EcriptadoExcel {

	public void ClaveExcel(String direccion,String Convenio,String Regional,String Clave) {

		String ruta = direccion+"\\"+Regional+"\\"+Convenio+".xls"; //ruta excel a encriptar
		String destino = direccion+"\\"+Regional+"\\Encriptado\\"+Convenio+".xls";//ruta donde va a guardar el archivo
		
		FileInputStream fileInput = null;  
		BufferedInputStream bufferInput = null;  
		POIFSFileSystem poiFileSystem = null;  
		FileOutputStream fileOut = null; 
		
		  try {   
			
			fileInput = new FileInputStream(ruta);
			bufferInput = new BufferedInputStream(fileInput);    
			poiFileSystem = new POIFSFileSystem(bufferInput);    
			
			Biff8EncryptionKey.setCurrentUserPassword(Clave);    
			HSSFWorkbook workbook = new HSSFWorkbook(poiFileSystem, true);    
			
			fileOut = new FileOutputStream(destino); 
			workbook.writeProtectWorkbook(Biff8EncryptionKey.getCurrentUserPassword(), ""); 
			workbook.write(fileOut); 
			
		  } catch (Exception ex) { 

		   System.out.println(ex.getMessage());  

		  } finally {   

		    try {    

		     bufferInput.close();  

		    } catch (IOException ex) { 

		     System.out.println(ex.getMessage());  

		    }  

		    try {    

		     fileOut.close();  

		    } catch (IOException ex) { 

		     System.out.println(ex.getMessage());  

		    } 
		  }  
	}

}




