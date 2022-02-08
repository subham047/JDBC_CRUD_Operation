import java.util.Scanner;

public class employee {

	int id, salary;
	String name, mail, phone, dob, password;

	public employee(String name, String mail, String phone, int salary, String dob, String password) {
		super();
		this.name = name;
		this.mail = mail;
		this.phone = phone;
		this.salary = salary;
		this.dob = dob;
		this.password = password;
	}

	public employee(int id, String name, String mail, String phone, int salary, String dob, String password) {
		super();
		this.id = id;
		this.name = name;
		this.mail = mail;
		this.phone = phone;
		this.salary = salary;
		this.dob = dob;
		this.password = password;
	}

	public static void main(String[] args) {

		String name = null, mail = null, phone = null, dob = null, password = null;
		int salary = 0;

		Scanner sc = new Scanner(System.in);

		int choice = 0;
		do {

			System.out.println("Please choose any operation : ");
			System.out.println("1. Add User");
			System.out.println("2. Show all Users details");
			System.out.println("3. Show Employee Details by their ID");
			System.out.println("4. Show Employee Details Date-Of-Birth wise");
			System.out.println("5. Show Employee Details Salary Wise");
			System.out.println("6. Forget Password");
			System.out.println("7. Exit");
			System.out.println("========================================");

			System.out.print("Operation Number : ");
			choice = sc.nextInt();

			switch (choice) {

			case 1:
				System.out.println("========================================");
				System.out.println("How many users details you want to add ???");
				int numOfUsers = sc.nextInt();

				for (int i = 1; i <= numOfUsers; i++) {
					System.out.println("Enter the details of User...");
					System.out.println("--------------------------------------");
					System.out.print("name : ");
					name = sc.next();
					System.out.print("email : ");
					mail = sc.next();
					System.out.print("Phone : ");
					phone = sc.next();
					System.out.print("salary: ");
					salary = sc.nextInt();
					System.out.print("DOB : ");
					dob = sc.next();
					System.out.print("Password : ");
					password = sc.next();

					System.out.println("========================================");

					employee emp = new employee(name, mail, phone, salary, dob, password);
					CRUDop.addEmployee(emp);
				}
				break;

			case 2:
				System.out.println("========================================");
				CRUDop.getAllEmployee();
				break;

			case 3:
				System.out.println("========================================");
				System.out.print("Enter the ID you want to search : ");
				int id = sc.nextInt();

				CRUDop.getEmployeeDetails(id);
				break;

			case 4:
				System.out.println("========================================");
				System.out.print("Enter the First Date : ");
				String startDate = sc.next();

				System.out.print("Enter the Last Date : ");
				String endDate = sc.next();

				CRUDop.getEmployeesDOBwise(startDate, endDate);
				break;

			case 5:
				System.out.println("Enter a sign : ");
				String ch = sc.next();
				System.out.println("Enter the amount : ");
				int amount = sc.nextInt();
				CRUDop.getEmployeesSalaryWise(ch, amount);
				System.out.println("========================================");
				break;

			case 6:
				System.out.println("========================================");
				System.out.println("Enter new password : ");
				String newPassWord = sc.next();

				System.out.println("Enter the ID of which you want to reset the password : ");
				id = sc.nextInt();

				CRUDop.ForgetPassword(newPassWord, id);
				break;

			case 7:
				System.out.println("========================================");
				System.out.println("Program terminated. You can run the program again.");
				break;

			default:
				System.out.println("=================================");
				System.out.println("Wrong Choice. Choose Again");
				System.out.println("=================================");
				break;
			}

		} while (choice != 7);

	}
	

}
