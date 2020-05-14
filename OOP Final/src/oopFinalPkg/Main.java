package oopFinalPkg;

import java.io.Console;
import java.util.ArrayList;

import oopFinalPkg.Model.HelperItem;
import oopFinalPkg.Model.Item;
import oopFinalPkg.Model.Humans.Admin;
import oopFinalPkg.Model.Humans.Porter;

public class Main extends HelperItem {
	
	// Defining Variables and Constants
	private ArrayList<Admin> adminArray = new ArrayList<Admin>();
	private ArrayList<Porter> porterArray = new ArrayList<Porter>();
	
	// For accessing object method from each class
	protected final Admin admin = new Admin("", 0,"", false, 0, "", "");
	protected final Porter porter = new Porter("", 0,"", false, 0, "", "");
	
	// crrnUserName untuk nama user akun yang login sekarang, begitupula dengan ID. 
	// Untuk menentukan admin atau tidak, kita gunakan isCurrAdmin jika is currAdmin == true maka dia admin, dan sebaliknya
	// Unutk crrnUserIndex kita gunakan untuk mempermudah pencarian user di array admin/porter.
	private String crrnUsername;
	private int crrnUserID;
	private boolean isCurrAdmin;
	private int crrnUserIndex;
	private Console console = System.console();
	
//	protected Admin admin = new Admin("",0,"",false);
//	protected Porter porter = new Porter("", 0,"",false);
	
	//Admin and Porter Constructor
	//=====================================================
	//String nama, int id, String password, boolean accountType, int numberOfTransaction, String joinDate,String lastAccessDate
	//Jika accType = true, maka dia admin. else untuk porter
	public Main() {
		// Defaults admin and porter
		adminArray.add(new Admin("Master", 0,"1234", true, 0,crrnDate, crrnDate ));
		porterArray.add(new Porter("Mikhael", 0, "1234", false, 0, crrnDate, crrnDate));
		// Defaults Item
		displayWelcome();
	}
	
	public static void main(String[] args) {
		new Main();
	}
	
	
	//Mark: - func for welcoming user
	//displayWelcome internal parameter
	//========================================
	//roleUser: Dipergunakan untuk mendapatkan informasi apakah user yang mau sign in admin atau tidak
	//typeUser: jika roleUser.equals("admin") == true, maka typeUser akan bersifat true, dan sebaliknya. Dipergunakan untuk menentukan array mana yang mau dicheck.
	//passwprdUser: Menampung password sementara
	//namaUser = dipergunakan untuk menampung nama user sementara;
	//indexTemp = indextemp akan memperoleh nilai saat func accountCheck telah dipanggil, dipergunakan untuk mendapatkan index posisi akun tersebut, dan mengakses nama, id, dan type akunnya.
	//ConsolePass = digunakan saat sudah di compile(export application).
	private void displayWelcome() {
		
		// Reset global variable setiap kali user mau ganti account
		crrnUsername = null;
		crrnUserID = 0;
		isCurrAdmin = false;
		
		String roleUser = "";
		boolean typeUser;
		String namaUser;
	//	char[] consolePass;
		String passwordUser;
		int indexTemp;
		System.out.println("==============================================================================================");
		System.out.println("     				  ***Inventory Gudang Sembako***\n");
		System.out.println("Welcome, Mr. /Mrs. ");
		System.out.println("Please enter your credential!\n");
		System.out.println("==============================================================================================");
		do {
			do {
				System.out.printf("What's your role [Admin | Porter]? ");
				roleUser = scanner.next();
				if(!(roleUser.equals("Admin")|| roleUser.equals("Porter"))) {
					System.out.println("Invalid Role!");
				}
			}while(!(roleUser.equals("Admin")|| roleUser.equals("Porter")));
			
			if (roleUser.equals("Admin"))
				typeUser = true;
			else 
				typeUser = false;
			System.out.printf("Username: ");
			namaUser = scanner.next();
			System.out.printf("Password: ");
			passwordUser = scanner.next();
	//      namaUser = console.readLine("Username: ");
	//		consolePass = console.readPassword("Password: ");
	//		passwordUser = String.valueOf(consolePass);
			if (typeUser == true ) {
				indexTemp = checkAccount(namaUser, passwordUser, 1);
			}else {
				indexTemp = checkAccount(namaUser, passwordUser, 0);
			}
			if(indexTemp == -1) {
				System.out.println("Account doesn't exist or Invalid Credential!\n");
			}
		}while(indexTemp == -1);
		
		isCurrAdmin = typeUser;
		crrnUserIndex = indexTemp;
		if (isCurrAdmin == true) {
		
			crrnUserID = adminArray.get(crrnUserIndex).getId();
			crrnUsername = 	adminArray.get(crrnUserIndex).getNama();
			//String nama, int id, String password, boolean accountType, int numberOfTransaction, String joinDate,String lastAccessDate
			adminArray.set(crrnUserIndex, new Admin(crrnUsername, crrnUserID, passwordUser, isCurrAdmin,adminArray.get(crrnUserIndex).getNumberOfTransaction(), adminArray.get(crrnUserIndex).getJoinDate(), 
					crrnDate));
		}else {
			crrnUserID = porterArray.get(crrnUserIndex).getId();
			crrnUsername = 	porterArray.get(crrnUserIndex).getNama();
			adminArray.set(crrnUserIndex, new Admin(crrnUsername, crrnUserID, passwordUser, isCurrAdmin,porterArray.get(crrnUserIndex).getNumberOfTransaction(), porterArray.get(crrnUserIndex).getJoinDate(), 
					crrnDate));
		}
		System.out.println("Successfully logged in!");
		mainMenu();
	}
	
	
	//Mark: - Func for checking user account
	//checkAccount parameters
	//========================================
	//nama: namaUser yang sudah diinput dari menuWelcome
	//passwprd: kodePassword user yang diinput dari menuWelcome
	//condition: menentukan array mana yang harus dicari, jika condition == 0 maka dia bkn admin, dan sebaliknya jika condition == 1
	private int checkAccount(String nama, String password, int condition) {
		int index = -1;
		if (condition == 1) {
			for (int x = 0; x<adminArray.size(); x++) {
				if(nama.equals(adminArray.get(x).getNama())== true && password.equals(adminArray.get(x).getPassword()) == true ) {
					index = x;
					break;
				}
			}
		}else {
			for (int x = 0; x<porterArray.size(); x++) {
				if(nama.equals(porterArray.get(x).getNama())== true && password.equals(porterArray.get(x).getPassword()) == true) {
					index = x;
					break;
				}
			}
		}
		return index;
	}
	

	// Main Menu Function
	// ================= 
	// Internal Params: pilihan digunakan sebagai variable untuk menenutkan index mana yang user sentuh.
	private void mainMenu() {
		int pilihan = 0;
		do {
			System.out.println("\n==============================================================================================");
			System.out.println("     				  ***Inventory Gudang Sembako***\n");
			System.out.printf("Welcome, Mr. /Mrs. %s\n", crrnUsername);
			System.out.println("==============================================================================================");
			printInventory();
			System.out.println("1. Add Item");
			System.out.println("2. Remove Item");
			System.out.println("3. Restock Item");
			System.out.println("4. Search Item");
			System.out.println("5. Scan Barcode for taking item");
			System.out.println("6. Print List of Transtaction");
			System.out.println("7. Account Settings");
			System.out.println("8. Switch Account");
			System.out.println("9. Exit");
			System.out.printf("Choice >> ");
			pilihan = scanner.nextInt();
			switch (pilihan) {
			case 1:
				if (isCurrAdmin == true) {
					String namaBarang;
					int idBarang;
					String typeBarang;
					int quantity;
					int price;
					int expiredLength;
					String expired;
					String lokasi;
					
					System.out.printf("Enter Item name: ");
					namaBarang = scanner.next();
					
					do {
						System.out.printf("Enter Item category [Vegetable | Canned | Frozen | Fruit]: ");
						typeBarang = scanner.next();
						
						if (!(typeBarang.equals("Vegetable") ||typeBarang.equals("Canned") ||typeBarang.equals("Frozen") ||typeBarang.equals("Fruit"))) {
							System.out.println("Invalid category!");
						}
					}while(!(typeBarang.equals("Vegetable") ||typeBarang.equals("Canned") ||typeBarang.equals("Frozen") ||typeBarang.equals("Fruit")));
					
					do {
						System.out.printf("Enter item price [1000 - 500000]: ");
						price = scanner.nextInt();
						if (!(price >= 1000 && price <= 500000)) {
							System.out.println("Invalid price!");
						}
					}while(!(price >= 1000 && price <= 500000));
					
					do {
						System.out.printf("Enter item quantity [1 -  10000]: ");
						quantity = scanner.nextInt();
						if(!(quantity >= 1 && quantity <= 10000)) {
							System.out.println("Invalid quantity!");
						}
					}while(!(quantity >= 1 && quantity <= 10000));
					
					do {
						System.out.printf("Enter item expire date [DD-MM-YYYY]: ");
						expired = scanner.next();
						expiredLength = expired.length();

						if (!(expiredLength == 10)) {
							System.out.println("Invalid date format!");
						}
					}while(!(expiredLength == 10));
					idBarang = random.nextInt(999);
					lokasi  =  admin.determineLocation(typeBarang);

					//String expired, int idItem, int quantity, int harga, String lokasi, String nama, String type
					itemArray.add(new Item(expired, idBarang, quantity, price, lokasi, namaBarang, typeBarang));
					System.out.println("Item added succesfully!");
				}else {
					System.out.println("Access Prohibited!");
				}
				break;
			case 2:
				if (isCurrAdmin == true) {
					
				}else {
					System.out.println("Access Prohibited!");
				}
				break;
			case 3:
				if (isCurrAdmin == true) {
					
				}else {
					System.out.println("Access Prohibited!");
				}
				break;
			case 4:
				break;
			case 5:
				break;
			case 6:
				break;
			case 7:
				accountMenu();
				break;
			case 8:
				String state;
				do {
					System.out.printf("Are you sure [Y | N]? ");
					state = scanner.next();
				}while(!(state.equals("Y") || state.equals("N")));
				if(state.equals("Y")) {
					System.out.println("Logged off!\n");
					displayWelcome();	
				}else {
					pilihan = 9;
				}							
				break;		
			case 9:
				System.out.println("Thank you for using our companys app");
				return;
			default:
				break;
			}
		}while(pilihan != 8);
		
	}
	
	private void printInventory() {
		System.out.println("|Item Id | Name	|   Category   |    Quantity	| Price  | Expired  | Lokasi  |");
		System.out.println("==============================================================================================");
		for(int x = 0; x<itemArray.size(); x++) {
			System.out.printf("|%d      | %s   	| %s   | %d     | %d   | %s   | %s   |\n", 
					itemArray.get(x).getIdItem(), itemArray.get(x).getNama(), itemArray.get(x).getType()
					,itemArray.get(x).getQuantity(), itemArray.get(x).getHarga(), itemArray.get(x).getExpired(), itemArray.get(x).getLokasi());
		}
		System.out.println("...");
		System.out.println("==============================================================================================");
	}
	
	
	private void accountMenu() {
		int pilihan = 0;
		do {
			System.out.println("\n==============================================================================================");
			System.out.println("     				  ***Inventory Gudang Sembako***\n");
			System.out.printf("Welcome, Mr. /Mrs. %s\n", crrnUsername);
			System.out.println("==============================================================================================");
			System.out.println("1. Add User");
			System.out.println("2. Remove User");
			System.out.println("3. Change Password");
			System.out.println("4. Go back");
			System.out.printf("Choice >> ");
			pilihan = scanner.nextInt();
			
			switch (pilihan) {
			case 1:
				// nama = variable sementara buat nama akun yang akan dibuat.
				String nama;
				String pass;
				String type;
				boolean adminType;
				int userID;
				if (isCurrAdmin == true) {
					do {
						do {
							System.out.printf("What's the role [Admin | Porter]? ");
							type = scanner.next();
							if(!(type.equals("Admin")==true || type.equals("Porter") == true)) {
								System.out.println("Invalid role selection!");
							}
						}while(!(type.equals("Admin")==true || type.equals("Porter") == true));
						if(type.equals("Admin") == true) {
							adminType = true;
						}else {
							adminType = false;
						}
						System.out.printf("Enter new username [5-15 Characters]: ");
						nama = scanner.next();
						System.out.printf("Enter new password [>=4 Lengths]: ");
						pass = scanner.next();
						
						if (!((nama.length()>= 5 && nama.length()<= 15 ) && pass.length()>=4)) {
							System.out.println("Username is way too short! or Password is not strong enough!");
						}
					}while(!((nama.length()>= 5 && nama.length()<= 15 ) && pass.length()>=4));
					//String nama, int id, String password, boolean accountType
					userID = random.nextInt(999);
					if (adminType == true) {
						adminArray.add(new Admin(nama, userID, pass, adminType, 0, crrnDate, crrnDate));
					}else {
						porterArray.add(new Porter(nama, userID, pass, adminType, 0, crrnDate, crrnDate));
					}
					System.out.println("Account Succesfully Created!\n");
					pilihan = 5;
				}else {
					System.out.println("Access Prohibited!");
					pilihan = 5;
				}
				break;
			case 2:
				if (isCurrAdmin == true) {
					// Choose dipergunakan untuk menentukan mana index yang mau dihapus User
					// adminSize digunakan untuk melanjutkan nomor dari looping admin sebelumnya ke porter array
					// totalUser = jumlah pertambahan admin.size + porter.size digunakan sebagai batasan pilihan user
					// Condition = digunkana untuk menentukan apakah user yakin atau tdk saat menghapus item
					int choose;
					int adminSize = adminArray.size()+1;
					int totalUser =  adminArray.size()+porterArray.size();
					String condition;
					do {
						System.out.println("==============================================================================================");
						System.out.println("No. |Item Id | Name	  | Role   |   Transactions   |    Join Date	| Last Access Date   |");
						System.out.println("==============================================================================================");
						for(int x = 0; x<adminArray.size(); x++) {
								System.out.printf("|%d  |%d      | %s   | Admin | %d              | %s     | %s       |\n", 
										x+1,adminArray.get(x).getId(), adminArray.get(x).getNama(), adminArray.get(x).getNumberOfTransaction()
										,adminArray.get(x).getJoinDate(), adminArray.get(x).getLastAccessDate());
						}
		
						for(int x = 0; x<porterArray.size(); x++, adminSize++) {
							System.out.printf("|%d  |%d      | %s    | Porter | %d              | %s     | %s       |\n", 
									adminSize, porterArray.get(x).getId(), porterArray.get(x).getNama(), porterArray.get(x).getNumberOfTransaction()
									,porterArray.get(x).getJoinDate(), porterArray.get(x).getLastAccessDate());
						}
						System.out.println("==============================================================================================");
				
						System.out.printf("Choose which user wanted to be remove [1 - %d]: ", totalUser);
						choose = scanner.nextInt();
						if (!(choose >= 1 && choose <= totalUser)) {
							System.out.println("Invalid Selection!");
						}
					}while(!(choose >= 1 && choose <= totalUser));
					
				
					do {
						System.out.printf("Are you sure [Y | N]? ");
						condition = scanner.next();
					}while(!(condition.equals("Y") || condition.equals("N")));
					if(condition.equals("Y")) {
						choose = choose -1;
						if(choose <= adminArray.size()-1) {
							System.out.println(choose);
							adminArray.remove(choose);
						}else {
							choose = choose - adminArray.size();
							porterArray.remove(choose);
						}
						System.out.println("User is removed!");
						pilihan = 5;
					}else {
						pilihan = 5;
					}
					
				}else {
					System.out.println("Access Prohibited!");
					pilihan = 5;
				}
				break;
			case 3:
				// CurrPass = pass skrng yang akan dicek
				// newPass  = pass yang baru
				// state    = untuk menerima return function dari changePassword func, jika nilai = false, maka tdk diizinkan mengganti password. 
				String currPass;
				String newPass;
				boolean state;
				//char[] consolePass;
				do {
					System.out.printf("Enter your current password: ");
					currPass = scanner.next();
					System.out.printf("Enter new password: ");
					newPass = scanner.next();
					state = changePassword(currPass, newPass);
					//		namaUser = console.readLine("Username: ");
					//		consolePass = console.readPassword("Password: ");
					//		passwordUser = String.valueOf(consolePass);
					if(state == false) {
						System.out.println("Wrong password!");
					}
				}while((state == false));
				System.out.println("Passwords is changed!\n");
				pilihan = 5;
				break;
			case 4:
				String condition;
				do {
					System.out.printf("Are you sure [Y | N]? ");
					condition = scanner.next();
				}while(!(condition.equals("Y") || condition.equals("N")));
				if(condition.equals("Y")) {
					return;
				}else {
					pilihan = 5;
				}
			default:
				break;
			}
		}while(!(pilihan>= 1 && pilihan<= 4));
	}
	
	//Mark: - Change Password Function
	//==================================
	//Params: oldPass, newPass
	//oldPass: password lama yang diberikan user
	//newPass: password baru yang diberikan user 
	//==================================
	//internal Params
	//State : nilai yang akan di return, jika nilai pass yang lama benar dengan database maka baru diizinkan merubah pass.
	private boolean changePassword(String oldPass, String newPass) {
		boolean state = false;
		if (isCurrAdmin == true) {
			//String nama, int id, String password, boolean accountType
			//Memperbaharui data password user.
			if(adminArray.get(crrnUserIndex).getPassword().equals(oldPass)==true) {
				adminArray.set(crrnUserIndex, new Admin(crrnUsername, crrnUserID, newPass, isCurrAdmin, adminArray.get(crrnUserIndex).getNumberOfTransaction(), adminArray.get(crrnUserIndex).getJoinDate(), 
						adminArray.get(crrnUserIndex).getLastAccessDate()));
				state = true;
			}else {
				state = false;
			}
		}else {
			if(porterArray.get(crrnUserIndex).getPassword().equals(oldPass)==true) {
				porterArray.set(crrnUserIndex, new Porter(crrnUsername, crrnUserID, newPass, isCurrAdmin, porterArray.get(crrnUserIndex).getNumberOfTransaction(), porterArray.get(crrnUserIndex).getJoinDate(), 
						porterArray.get(crrnUserIndex).getLastAccessDate()));
				state = true;
			}else {
				state = false;
			}
		}
		return state;
	}
	
	
}
