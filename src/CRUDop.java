import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

//*****************************************************************************************************************************************************************

//*      STEPS FOR JDBC CONNECTION                                                                                                                                *

//1      Load and Register the Driver using Class.forName("Driver class name e.g com.mysql.cj.jdbc.Driver");                                                      *
//2      Connect the Database and store it in a Connection type variable. e.g Connection conn = DriverManager.getConnection(Database Url, UserName, Password);    *
//3      Prepare a statement e.g PreparedStatement ps = conn.prepareStatement(SQL QUERY);                                                                         *
//4      Execute the Query e.g ps.executeUpdate() or ps.executeQuery() etc.                                                                                       *
//5      Store the result in a ResultSet and process the results.                                                                                                 *
//6      Close the connectionS.                                                                                                                                   *

//*****************************************************************************************************************************************************************

public class CRUDop {
	static String jdbcUrl = "jdbc:mysql://localhost:3306/employee";
	static String databaseUserName = "root";
	static String databasePassword = "subham465";

	static Statement st = null;
	static Connection conn = null;
	static PreparedStatement ps = null;

	// Connecting Database Logic
	public static void connectDatabase() {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver"); // -------------------------------------------------------------Driver
														// loaded and registered
			conn = DriverManager.getConnection(jdbcUrl, databaseUserName, databasePassword); // --------------------------Database
																								// Connected
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	// Closing Connection Logic
	public static void closeConnection() {
		// Closing connection
		try {
			ps.close();
		} catch (SQLException e) {

			e.printStackTrace();
		}
		try {
			conn.close();
		} catch (SQLException e) {

			e.printStackTrace();
		}
	}

	// Add Employee Logic
	public static void addEmployee(employee emp) {
		CRUDop.connectDatabase();// -------------------------------------------------------------------Connection Established
		try {
			String INSERT_USER_QUERY = "INSERT INTO employee_details(fullname,email,phone,salary,dob,password) VALUES(?, ?, ?, ?, ?, ?)";
			ps = conn.prepareStatement(INSERT_USER_QUERY);// ------------------------------------------------------------------------------------Statement created
			ps.setString(1, emp.name);
			ps.setString(2, emp.mail);
			ps.setString(3, emp.phone);
			ps.setLong(4, emp.salary);
			ps.setString(5, emp.dob);
			ps.setString(6, emp.password);
			ps.executeUpdate();// --------------------------------------------------Query Executed
			System.out.println("One User added.");
		} catch (SQLException e) {
			System.out.println("Syntax error.");
			e.printStackTrace();
		}
		CRUDop.closeConnection(); // -----------------------------------------------Connection Closed
		System.out.println("=================================");

	}

	//Get all Employee Logic
	public static void getAllEmployee() {
		ArrayList<employee> employeeList = new ArrayList<>();
		CRUDop.connectDatabase();// -------------------------------------------------Connection Established
		try {
			String GET_ALL_USER = "SELECT * FROM employee_details";
			ps = conn.prepareStatement(GET_ALL_USER);// -----------------------Statement created
			ResultSet rs = ps.executeQuery();//------------------------------------Query Excuted
			while (rs.next()) {
				int id = rs.getInt("id");
				String fullname = rs.getString("fullname");
				String email = rs.getString("email");
				String phone = rs.getString("phone");
				int salary = rs.getInt("salary");
				String dob = rs.getString("dob");
				String password = rs.getString("password");
				employee employeeObj = new employee(id, fullname, email, phone, salary, dob, password);
				employeeList.add(employeeObj);
			}
		} catch (SQLException e) {
			System.out.println("Syntax error.");
			e.printStackTrace();
		}

		for (employee p : employeeList) {
			System.out.println("ID : " + p.id);
			System.out.println("Full Name : " + p.name);
			System.out.println("Email id : " + p.mail);
			System.out.println("Phone Number : " + p.phone);
			System.out.println("Salary : " + p.salary);
			System.out.println("Date Of Birth : " + p.salary);
			System.out.println("Password : " + p.password);
			System.out.println("=================================");
		}
		CRUDop.closeConnection(); // ----------------------------------------------------Connection Closed
		System.out.println("=================================");
	}

	//Get Employee By ID Logic
	public static void getEmployeeDetails(int id_number) {
		CRUDop.connectDatabase();// --------------------------------------------------Connection Established
		try {
			String fetchById = "SELECT * FROM employee_details WHERE ID = ?";
			ps = conn.prepareStatement(fetchById);// ----------------------------------------Statement created
			ps.setLong(1, id_number);
			ResultSet rs = ps.executeQuery();// ----------------------------------------------Query Executed
			while (rs.next()) {
				System.out.println("ID : " + rs.getString("id"));
				System.out.println("Full Name : " + rs.getString("fullname"));
				System.out.println("Mail Id : " + rs.getString("email"));
				System.out.println("Phone : " + rs.getString("phone"));
				System.out.println("Salary : " + rs.getInt("salary"));
				System.out.println("Date Of Birth : " + rs.getString("dob"));
				System.out.println("Password : " + rs.getString("password"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		CRUDop.closeConnection(); // ---------------------------------------------------------Connection Closed
		System.out.println("=================================");
	}

	//Get Employee By DOB Logic
	public static void getEmployeesDOBwise(String fromDate, String toDate) {
		//String getEmployeesDOBwiseQuery = "SELECT * FROM employee_details WHERE dob BETWEEN 1996-01-01 AND 1998-01-01";
		CRUDop.connectDatabase();// ------------------------------------------------------------------------------------------------Connection Established
		try {
			String fetchDataDOBWise = "SELECT * FROM employee_details WHERE dob BETWEEN " + fromDate + " AND " + toDate;
			ps = conn.prepareStatement(fetchDataDOBWise);// ------------------------------------------------------------------------Statement created
			ResultSet rs = ps.executeQuery();// ------------------------------------------------------------------------------------Query Executed
			while (rs.next()) {
				System.out.println("ID : " + rs.getString("id"));
				System.out.println("Full Name : " + rs.getString("fullname"));
				System.out.println("Mail Id : " + rs.getString("email"));
				System.out.println("Phone : " + rs.getString("phone"));
				System.out.println("Salary : " + rs.getInt("salary"));
				System.out.println("Date Of Birth : " + rs.getString("dob"));
				System.out.println("Password : " + rs.getString("password"));
				System.out.println("=================================");
			}
		}
		catch (SQLException e) {
			System.out.println("Syntax Error");
			e.printStackTrace();
		}
		CRUDop.closeConnection(); // --------------------------------------------Connection Closed
		System.out.println("=================================");
	}

	//Get Employee Details according to the condition Logic
	public static void getEmployeesSalaryWise(String ch, int amount) {
		CRUDop.connectDatabase();// ----------------------------------------------Connection Established
		try {
			String fetchDataSalaryWise = "SELECT * FROM employee_details WHERE SALARY " + ch + "" + amount;
			ps = conn.prepareStatement(fetchDataSalaryWise);// ----------------------------------------------------Statement created
			ResultSet rs = ps.executeQuery();// -------------------------------------------------------------------Query Executed
			while (rs.next()) {
				System.out.println("ID : " + rs.getString("id"));
				System.out.println("Full Name : " + rs.getString("fullname"));
				System.out.println("Mail Id : " + rs.getString("email"));
				System.out.println("Phone : " + rs.getString("phone"));
				System.out.println("Salary : " + rs.getInt("salary"));
				System.out.println("Date Of Birth : " + rs.getString("dob"));
				System.out.println("Password : " + rs.getString("password"));
				System.out.println("=================================");
			}
		}
		catch (SQLException e) {
			System.out.println("Syntax Error");
			e.printStackTrace();
		}
		CRUDop.closeConnection(); // ----------------------------------------------Connection Closed
		System.out.println("=================================");
	}

	//Update Password Logic
	public static void ForgetPassword(String newPassword, int id) {
		System.out.println("The old Info is : ");
		CRUDop.getEmployeeDetails(id);
		CRUDop.connectDatabase();// ------------------------------------------Connection Established
		try {
			String updatePasswordQuery = "UPDATE employee_details SET password = ? WHERE ID = ? ";
			ps = conn.prepareStatement(updatePasswordQuery);// ---------------------------------------------------Statement created
			ps.setString(1, newPassword);
			ps.setLong(2, id);
			ps.executeUpdate();// --------------------------------------------Query Executed
			System.out.println("=================================");
			System.out.println("Password Updated Successfully.");
		} catch (SQLException e) {
			System.out.println("Syntax Error");
			e.printStackTrace();
		}
		CRUDop.closeConnection(); // --------------------------------------Connection Closed
		System.out.println("=================================");
		System.out.println("The Updated Info is : ");
		CRUDop.getEmployeeDetails(id);

	}

}
