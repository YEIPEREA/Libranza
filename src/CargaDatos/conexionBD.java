/**
 * Conexion a la BD
 */
package CargaDatos;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

//import CargaDatos.conexionBD;

public class conexionBD {
	private static Connection cn;
/**
 * Conexion a la base de datos
 * @return
 */
	public static Connection conectar() {

		try {
			Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
			//cn = DriverManager.getConnection("jdbc:sqlserver://192.168.0.23;DatabaseName=Libranza","sa","HDI@123*");// con direccion ip 
			cn = DriverManager.getConnection("jdbc:sqlserver://localhost;DatabaseName=Libranza", "sa","HDI@123*");//de forma local

		} catch (Exception ex) {
			System.out.println(ex.getMessage());
		}
		return cn;
	}
//cierra la conexion de la base de datos.
	/**
	 * cierra la conexion a la BD
	 */
	public void CerrarconexionBD() {
		try {
			conectar().close();
			cn = null;
		} catch (Exception sqle) {
			JOptionPane.showMessageDialog(null, "Error al cerrar la conexion","Error", JOptionPane.ERROR_MESSAGE);
			Logger.getLogger(conexionBD.class.getName()).log(Level.SEVERE,null, sqle);
		}

	}
//ejecuta los querys
	/**
	 *  Ejecuta los querys que se le mandan
	 *  
	 * @param q Querys de SQL
	 * @throws SQLException
	 */
	public void EjecutarQuery(String q) throws SQLException {

		try {
			if (cn == null) {
				conectar();

			}
			Statement st;
			st = cn.createStatement();
			st.executeUpdate(q);

		} catch (SQLException ex) {
			cn = null;
			System.out.println(q);
			System.out.println("Error al ingresar datos");

		}
	}

// =================================consulta la base de datos de SQL===========================================================================
/**
 * consulta la BD a partir de un  Query
 * 
 * @param q Query a  consultar
 * @return
 * @throws SQLException
 */
	public DefaultTableModel ConsultarQuery(String q) throws SQLException {

		try {
			if (cn == null) {
				conectar();
			}
			Statement st;
			st = cn.createStatement();
			ResultSet result = st.executeQuery(q);
			DefaultTableModel TableModel = this.ConvertToTableModel(result);
			return TableModel;

		} catch (SQLException ex) {
			System.out.println(q);
			System.out.println("Error al leer los datos");
			cn.close();
			return null;
		}
	}

// *************************************************************************************************************
	private static DefaultTableModel ConvertToTableModel(ResultSet rs) {
		try {
			DefaultTableModel table = new DefaultTableModel();
			ResultSetMetaData metaData = rs.getMetaData();
			int totalColumn = metaData.getColumnCount();
			Object[] dataRow = new Object[totalColumn];
			if (rs != null) {
				for (int i = 1; i <= totalColumn; i++) {
					table.addColumn(metaData.getColumnName(i));
				}
				while (rs.next()) {
					for (int i = 1; i <= totalColumn; i++) {
						dataRow[i - 1] = rs.getObject(i);
					}
					table.addRow(dataRow);
				}
			}
			table.getColumnCount();
			return table;

		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}

}// fin del programa...

