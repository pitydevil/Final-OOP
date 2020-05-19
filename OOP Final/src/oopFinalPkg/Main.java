package oopFinalPkg;

import java.io.Console;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

import oopFinalPkg.Model.HelperItem;
import oopFinalPkg.Model.Item;
import oopFinalPkg.Model.Transaction;
import oopFinalPkg.Model.Humans.Admin;
import oopFinalPkg.Model.Humans.Porter;

public class Main extends HelperItem {
	
	// Defining Variables and Constants
	
	
	// For accessing object method from each class
	protected final Admin admin = new Admin("", 0,"", false, 0, "", "");
	protected final Porter porter = new Porter("", 0,"", false, 0, "", "");
	
	// crrnUserName untuk nama user akun yang login sekarang, begitupula dengan ID. 
	// Untuk menentukan admin atau tidak, kita gunakan isCurrAdmin jika is currAdmin == true maka dia admin, dan sebaliknya
	// Unutk crrnUserIndex kita gunakan untuk mempermudah pencarian user di array admin/porter.

	private int crrnTransactionID = 1;
	private Console console = System.console();
	
	//Admin and Porter Constructor
	//=====================================================
	//String nama, int id, String password, boolean accountType, int numberOfTransaction, String joinDate,String lastAccessDate
	//Jika accType = true, maka dia admin. else untuk porter
	public Main() {
	
		// Defaults admin and porter, and inventoryItem
		String lokasi = admin.determineLocation("Vegetable");
		itemArray.add(new Item("23-05-2020", random.nextInt(999), random.nextInt(9999),random.nextInt(9999), lokasi ,"Carrot","Vegetable"));
		lokasi = admin.determineLocation("Fruit");
		itemArray.add(new Item("18-01-2020", random.nextInt(999), random.nextInt(9999),random.nextInt(99999), lokasi ,"Buah Naga","Fruit"));
		lokasi = admin.determineLocation("Canned");
		itemArray.add(new Item("29-07-2020", random.nextInt(999), random.nextInt(9999),random.nextInt(9999), lokasi ,"Sardine","Canned"));
		lokasi = admin.determineLocation("Frozen");
		itemArray.add(new Item("01-02-2020", random.nextInt(999), random.nextInt(9999),random.nextInt(39999), lokasi ,"Nugget","Frozen"));
		lokasi = admin.determineLocation("Fruit");
		itemArray.add(new Item("09-08-2020", random.nextInt(999), random.nextInt(9999),random.nextInt(14999), lokasi ,"Pisang","Fruit"));
		lokasi = admin.determineLocation("Vegetable");
		itemArray.add(new Item("13-04-2020", random.nextInt(999), random.nextInt(9999),random.nextInt(4999), lokasi ,"Kembang Kol","Vegetable"));
		
		adminArray.add(new Admin("Master", 001,"1234", true, 0,crrnDate, crrnDate ));
		porterArray.add(new Porter("Mikhael", 0001, "1234", false, 0, crrnDate, crrnDate));
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
		crrnUserIndex = 0;
		
		String roleUser = null;
		boolean typeUser;
		String namaUser;
	//	char[] consolePass;
		String passwordUser;
		int indexTemp;
		System.out.println("\n\n"+ZonedDateTime.now().format(DateTimeFormatter.RFC_1123_DATE_TIME));
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
			} while(!(roleUser.equals("Admin")|| roleUser.equals("Porter")));
			
			if (roleUser.equals("Admin"))
				typeUser = true;
			else 
				typeUser = false;
			System.out.printf("Username (N to Cancel): ");
			namaUser = scanner.next();
			if (namaUser.equals("N")) {
				displayWelcome();
			}
			System.out.printf("Password (N to Cancel): ");
			passwordUser = scanner.next();
			if (passwordUser.equals("N")) {
				displayWelcome();
			}
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
			porterArray.set(crrnUserIndex, new Porter(crrnUsername, crrnUserID, passwordUser, isCurrAdmin,porterArray.get(crrnUserIndex).getNumberOfTransaction(), porterArray.get(crrnUserIndex).getJoinDate(), 
					crrnDate));
		}
		System.out.println("Successfully logged in!\n");
		mainMenu();
	}
		
	// Main Menu Function
	// ================= 
	// Internal Params: pilihan digunakan sebagai variable untuk menenutkan index mana yang user sentuh.
	private void mainMenu() {
		int pilihan = 0;
		System.out.println(crrnUserIndex);
		do {
			System.out.println(ZonedDateTime.now().format(DateTimeFormatter.RFC_1123_DATE_TIME));
			System.out.println("\n===========================================================================================================");
			System.out.println("     				  ***Inventory Gudang Sembako***\n");
			System.out.printf("Welcome, Mr. /Mrs. %s\n", crrnUsername);
			System.out.println("===========================================================================================================");
			printInventory();
			System.out.println("1. Add Item");
			System.out.println("2. Remove Item");
			System.out.println("3. Restock Item");
			System.out.println("4. Search Item");
			System.out.println("5. Scan Barcode for taking item");
			System.out.println("6. Print List of Transtaction");
			System.out.println("7. Relocate Item");
			System.out.println("8. Account Settings");
			System.out.println("9. Switch Account");
			System.out.println("10. Exit");
			System.out.printf("Choice >> ");
			
			//Rubah scan pilihan jadi pakai fungsi, buat mencegah invalid input
			pilihan = scanInt();
			switch (pilihan) {
			//Add item
			case 1: 
				if (isCurrAdmin == true) {
					int idBarang;
					int quantity;
					int price;
					int nameLength;
					boolean expiredLength = false;
					String namaBarang;
					String typeBarang;
					String expired;
					String lokasi;
					
					do {
						System.out.printf("Enter Item name (N to Cancel) : ");
						namaBarang = scanner.nextLine();
						nameLength = namaBarang.length();
						if (namaBarang.equals("N")) {
							mainMenu();
						}
					}while((nameLength==0));
				
					do {
						System.out.printf("Enter Item category (N to Cancel) [Vegetable | Canned | Frozen | Fruit]: ");
						typeBarang = scanner.nextLine();
						
						if (!(typeBarang.equals("Vegetable") ||typeBarang.equals("Canned") ||typeBarang.equals("Frozen") ||typeBarang.equals("Fruit"))) {
							if (typeBarang.equals("N")) {
								mainMenu();
							}else {
								System.out.println("Invalid category!");
							}
						}
					}while(!(typeBarang.equals("Vegetable") ||typeBarang.equals("Canned") ||typeBarang.equals("Frozen") ||typeBarang.equals("Fruit")));
					
					do {
						System.out.printf("Enter item price [1000 - 500000]: ");
						price = scanInt();
						if (!(price >= 1000 && price <= 500000)) {
							System.out.println("Invalid price!");
						}
					}while(!(price >= 1000 && price <= 500000));
					
					do {
						System.out.printf("Enter item quantity [1 -  10000]: ");
						quantity = scanInt();
						if(!(quantity >= 1 && quantity <= 10000)) {
							System.out.println("Invalid quantity!");
						}
					}while(!(quantity >= 1 && quantity <= 10000));
					
					//Reminder utk tambah date validation
					do {
						System.out.printf("Enter item expire date [DD-MM-YYYY]: ");
						expired = scanner.nextLine();
						
						// \\d expect number 0-9
						// {2} expect numbernya sebanyak 2/4
						if (expired.matches("^\\d{2}-\\d{2}-\\d{4}")) {
							expiredLength = true;
						}

						if (!(expiredLength == true)) {
							System.out.println("Invalid date format!");
						}
					}while(!(expiredLength == true));
					
					//Buat ngecek dulu, apakah itemID tersebut udah ada di listnya
					boolean itemAlreadyExist = false;
					do {
						idBarang = random.nextInt(999);
						Item cekItemIDSudahAda = searchItemByID(idBarang);
						if (cekItemIDSudahAda != null)
							itemAlreadyExist = true;
					} while (itemAlreadyExist);
					
					lokasi  =  admin.determineLocation(typeBarang);

					//String expired, int idItem, int quantity, int harga, String lokasi, String nama, String type
					itemArray.add(new Item(expired, idBarang, quantity, price, lokasi, namaBarang, typeBarang));
					
					//int transactionID, String transUsername, boolean accountType, String transDescription, String transDate
					//Tambahkan transaksi ini ke array dan increment crrnTransactionID
					transactionArray.add(new Transaction(crrnTransactionID, crrnUsername, 
							isCurrAdmin, ("Add itemID = " + idBarang + " with quantity " + quantity),
							crrnDateWM));
					crrnTransactionID++;
					addTransaction();
					System.out.println("Item added succesfully!");
				}else {
					System.out.println("Access Prohibited!");
				}
				System.out.println("Press Any Key to Continue...");
				scanner.nextLine();
				break;
				
			//Remove item
			case 2:
				if (isCurrAdmin == true) {
					
					//Cek dulu listnya kosong gak
					if (!(itemArray.isEmpty())) {
						int idBarang;
						Item itemToRemove;

						do {
							System.out.print("Enter valid Item ID: ");
							idBarang = scanInt();
							itemToRemove = searchItemByID(idBarang);
							if (itemToRemove == null)
								System.out.println("Item doesn't exist!");
						} while (itemToRemove == null);

						itemArray.remove(itemToRemove);
						transactionArray.add(new Transaction(crrnTransactionID, crrnUsername, 
								isCurrAdmin, ("Remove itemID = " + idBarang),
								crrnDateWM));
						crrnTransactionID++;
						addTransaction();
				
						System.out.println("Successfully removed item!");
					}else {
						System.out.println("There's no item in inventory!");
					}

				}else {
					System.out.println("Access Prohibited!");
				}
				
				//Biar sebelum ke-print looping selanjutnya, keliatan messagenya
				System.out.println("Press Any Key to Continue...");
				scanner.nextLine();
				break;
				
			//Restock Item
			case 3:
				if (isCurrAdmin == true) {
					
					//Cek dulu listnya kosong gak
					if (!(itemArray.isEmpty())) {

						int idBarang;
						int quantityRestock;
						Item itemToRestock;
						
						do {
							System.out.print("Enter valid Item ID: ");
							idBarang = scanInt();
							itemToRestock = searchItemByID(idBarang);
							if (itemToRestock == null)
								System.out.println("Item doesn't exist!");
						} while (itemToRestock == null);
						
						//Dibuat begini, karena inventory kita limitnya cuman sampai 10000 
						//quantity per ItemID, jadi user hanya bisa restock sampai maximal 10000
						int limitRestock = (10000) - itemToRestock.getQuantity();
						
						if (limitRestock == 0) 
							System.out.println("This item has reached max quantity!");
						else {
							do {
								System.out.printf("Enter quantity to restock [1 - %d]: ", limitRestock);
								quantityRestock = scanInt();
								if (!(quantityRestock >= 1 && quantityRestock <= limitRestock))
									System.out.println("Invalid restock value!");
							} while (!(quantityRestock >= 1 && quantityRestock <= limitRestock));
							
							//Tambahkan quantity item yang mau di restock,
							//kemudian masukkan juga transaksi ini ke transactionArray
							itemToRestock.setQuantity(itemToRestock.getQuantity() + quantityRestock);
							transactionArray.add(new Transaction(crrnTransactionID, crrnUsername, 
									isCurrAdmin, ("Restock itemID = " + idBarang + " with quantity " + quantityRestock),
									crrnDateWM));
							crrnTransactionID++;
							addTransaction();
							System.out.println("Successfully restock item!");
						}
					}

					else {
						System.out.println("There's no item in inventory!");
					}
					
				}else {
					System.out.println("Access Prohibited!");
				}
				System.out.println("Press Any Key to Continue...");
				scanner.nextLine();
				break;
			case 4:
				
				if (itemArray.isEmpty() == false) {
					Item itemToSearch;
					itemToSearch = determineSearch();
					System.out.println("\n+====================================+");
					System.out.printf("|Name: %-30s|\n", itemToSearch.getNama());
					System.out.printf("|ID: %-32s|\n", itemToSearch.getIdItem());
					System.out.printf("|Category: %-26s|\n", itemToSearch.getType());
					System.out.printf("|Price: Rp. %-25d|\n", itemToSearch.getHarga());
					System.out.printf("|Quantity: %-26d|\n", itemToSearch.getQuantity());
					System.out.printf("|Location: %-26s|\n", itemToSearch.getLokasi());
					System.out.println("+====================================+");
				}else {
					System.out.println("There's no item in inventory!");
				}
				System.out.println("Press Any Key to Continue...");
				scanner.nextLine();	
				break;
			case 5:
				if (!itemArray.isEmpty()) {
					int idBarang;
					int quantityAmbil;
					Item itemToTake;
					
					do {
						System.out.print("Enter valid Item ID: ");
						idBarang = scanInt();
						itemToTake = searchItemByID(idBarang);
						if (itemToTake == null)
							System.out.println("Item doesn't exist!");
					} while (itemToTake == null);
					
					//Maksimal quantity yang bisa diambil
					int limitTake = itemToTake.getQuantity();
					
					if (limitTake == 0) 
						System.out.println("This item is out of stock!");
					else {
						do {
							System.out.printf("Enter quantity to take [1 - %d]: ", limitTake);
							quantityAmbil = scanInt();
							if (!(quantityAmbil >= 1 && quantityAmbil <= limitTake))
								System.out.println("Invalid take value!");
						} while (!(quantityAmbil >= 1 && quantityAmbil <= limitTake));
						
						//Rubah quantity item yang diambil,
						//kemudian masukkan juga transaksi ini ke transactionArray
						itemToTake.setQuantity(itemToTake.getQuantity() - quantityAmbil);
						transactionArray.add(new Transaction(crrnTransactionID, crrnUsername, 
								isCurrAdmin, ("Take itemID = " + idBarang + " with quantity " + quantityAmbil),
								crrnDateWM));
						crrnTransactionID++;
						//Tambahkan jumlah transaction per user
						addTransaction();
						System.out.println("Successfully take item!");
					}
				}else {
					System.out.println("There's no item in inventory!");
				}
				System.out.println("Press Any Key to Continue...");
				scanner.nextLine();
				break;
			case 6:
				if (!transactionArray.isEmpty()) {
					
					//Cek dulu apakah akun ini admin/bukan
					if (isCurrAdmin) {
						System.out.println("");
						admin.printTransactionList(crrnUsername, transactionArray);
					}else 
						porter.printTransactionList(crrnUsername, transactionArray);
				}
				
				else {
					System.out.println("There's no transaction to show!");
				}
				System.out.println("\nPress Any Key to Continue...");
				scanner.nextLine();
				break;
			case 7:
				if (itemArray.isEmpty()==false){
					Item itemToSearch;
					itemToSearch = determineSearch();
					String category;
					category = itemToSearch.getType();
					String lokasiInput = null;
					String namaUser;
					String passwordUser;
					int nomorLokasi    = 0;
					int indexTemp;
				//	char[] consolePass;
					
				
					do {
						System.out.printf("Enter Shelf/Chiller Position [1-100]: ");
						nomorLokasi = scanner.nextInt();
						
					}while(!(nomorLokasi>=1 && nomorLokasi <= 100));
					
					if (category.equals("Vegetable")) {
						do {
							System.out.printf("Enter Which Self (N to Cancel) [Chiller A | Chiller B]: ");
							lokasiInput = scanner.nextLine();
							if(!(lokasiInput.equals("Chiller A") || lokasiInput.equals("Chiller B"))) {
								if (lokasiInput.equals("N")) {
									mainMenu();
								}else {
									System.out.println("Invalid Shelf!");
								}
							}
							
						}while(!(lokasiInput.equals("Chiller A") || lokasiInput.equals("Chiller B")));
					}else if (category.equals("Fruit")) {
						do {
							System.out.println("Enter Which Self (N to Cancel) [Shelf A | Shelf B | Shelf X | Shelf D | Shelf I]: ");
							lokasiInput = scanner.nextLine();
							if(!(lokasiInput.equals("Shelf A") || lokasiInput.equals("Shelf B") || lokasiInput.equals("Shelf X") 
									|| lokasiInput.equals("Shelf D") || lokasiInput.equals("Shelf I"))) {
								if (lokasiInput.equals("N")) {
									mainMenu();
								}else {
									System.out.println("Invalid Shelf!");
								}
							}
						}while(!(lokasiInput.equals("Shelf A") || lokasiInput.equals("Shelf B") || lokasiInput.equals("Shelf X") 
								|| lokasiInput.equals("Shelf D") || lokasiInput.equals("Shelf I")));
						
					}else if (category.equals("Canned")) {
						do {
							System.out.print("Enter Which Self (N to Cancel) [Shelf C | Shelf K | Shelf E | Shelf F | Shelf L]: ");
							lokasiInput = scanner.nextLine();
							if(!(lokasiInput.equals("Shelf C") || lokasiInput.equals("Shelf K") || lokasiInput.equals("Shelf E") 
									|| lokasiInput.equals("Shelf F") || lokasiInput.equals("Shelf L"))) {
								if (lokasiInput.equals("N")) {
									mainMenu();
								}else {
									System.out.println("Invalid Shelf!");
								}
							}
						}while(!(lokasiInput.equals("Shelf A") || lokasiInput.equals("Shelf B") || lokasiInput.equals("Shelf X") 
								|| lokasiInput.equals("Shelf D") || lokasiInput.equals("Shelf I")));
					}else if (category.equals("Frozen")) {
						do {
							System.out.print("Enter Which Self (N to Cancel) [Chiller C | Chiller D]: ");
							lokasiInput = scanner.nextLine();
							if(!(lokasiInput.equals("Chiller C") || lokasiInput.equals("Chiller D"))) {
								if (lokasiInput.equals("N")) {
									mainMenu();
								}else {
									System.out.println("Invalid Shelf!");
								}
							}
						}while(!(lokasiInput.equals("Chiller C") || lokasiInput.equals("Chiller D")));
					}
					
					lokasiInput = lokasiInput + nomorLokasi;
					if (!(isCurrAdmin == true)) {
						do {
							System.out.println("Enter Admin's Credential!");
							System.out.printf("Username (N to Cancel):  ");
							namaUser = scanner.next();
							System.out.printf("Password (N to Cancel): ");
							passwordUser = scanner.next();
					//      namaUser = console.readLine("Username: ");
					//		consolePass = console.readPassword("Password: ");
					//		passwordUser = String.valueOf(consolePass);
							if (namaUser.equals("N") || passwordUser.equals("N")) {
								mainMenu();
							}
							indexTemp = checkAccount(namaUser, passwordUser, 1);
							if(indexTemp == -1) {
								System.out.println("Account doesn't exist or Invalid Credential!\n");
							}
						}while(indexTemp == -1);
						System.out.println("Account is verified!");
						itemToSearch.setLokasi(lokasiInput);
					}
					itemToSearch.setLokasi(lokasiInput);
					transactionArray.add(new Transaction(crrnTransactionID, crrnUsername, 
							isCurrAdmin, ("Relocate itemID = " + itemToSearch.getIdItem()),
							crrnDateWM));
					crrnTransactionID++;
					addTransaction();
					System.out.println("Location Updated!");
					System.out.println("\nPress Any Key to Continue...");
					scanner.nextLine();
				}
				break;
			case 8:
				accountMenu();
				break;
			case 9:
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
			case 10:
				System.out.println("Thank you for using our companys app");
				return;
			default:
				break;
			}
		}while(pilihan != 10);
		
	}
	private void accountMenu() {
		int pilihan = 0;
		do {
			System.out.println("=========================================================================================================");
			System.out.println("     				  ***Inventory Gudang Sembako***\n");
			System.out.printf("Welcome, Mr. /Mrs. %s\n", crrnUsername);
			if (isCurrAdmin == true) {
				System.out.println("=========================================================================================================");
				System.out.printf("|No. |Account Id |%10sName%11s|  Role   |  Transaction  |  Join Date  |  Last Access Date  |\n","", "","","","", "");
				System.out.println("=========================================================================================================");
				for(int x = 0; x<adminArray.size(); x++) {
						System.out.printf("|%-3d |%-10d | %-23s |  Admin  | %-14d| %-11s | %-18s |\n", 
								x+1,adminArray.get(x).getId(), adminArray.get(x).getNama(), adminArray.get(x).getNumberOfTransaction()
								,adminArray.get(x).getJoinDate(), adminArray.get(x).getLastAccessDate());
				}
				int adminSize = adminArray.size()+1;
				for(int x = 0; x<porterArray.size(); x++, adminSize++) {
					System.out.printf("|%-3d |%-10d | %-23s |  Porter | %-14d| %-11s | %-18s |\n", 
							adminSize, porterArray.get(x).getId(), porterArray.get(x).getNama(), porterArray.get(x).getNumberOfTransaction()
							,porterArray.get(x).getJoinDate(), porterArray.get(x).getLastAccessDate());
				}
			}
			System.out.println("=========================================================================================================");
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
							System.out.printf("What's the role (N to Cancel) [Admin | Porter]? ");
							type = scanner.next();
							if(!(type.equals("Admin")==true || type.equals("Porter") == true)) {
								if (type.equals("N")) {
									accountMenu();
								}else {
									System.out.println("Invalid role selection!");	
								}
							}
						}while(!(type.equals("Admin")==true || type.equals("Porter") == true));
						if(type.equals("Admin") == true) {
							adminType = true;
						}else {
							adminType = false;
						}
						do {
							System.out.printf("Enter new username (N to Cancel) [5-15 Characters]: ");
							nama = scanner.next();
							if (!(nama.length()>= 5 && nama.length()<= 15 )) {
								if (nama.equals("N")) {
									accountMenu();
								}else {
									System.out.println("Username is not long enough!");
								}
							}
						}while(!(nama.length()>= 5 && nama.length()<= 15 ));
						
						System.out.printf("Enter new password (N to Cancel) [>=4 Lengths]: ");
						pass = scanner.next();
						
						if (!(pass.length()>=4)) {
							if (pass.equals("N")) {
								accountMenu();
							}else {
								System.out.println("Password is not strong enough!");
							}
						}
					}while(!(pass.length()>=4));
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
					int totalUser =  adminArray.size()+porterArray.size();
					String condition;
					
					do {
						System.out.println("=========================================================================================================");
						System.out.printf("|No. |Account Id |%10sName%11s|  Role   |  Transaction  |  Join Date  |  Last Access Date  |\n","", "","","","", "");
						System.out.println("=========================================================================================================");
						for(int x = 0; x<adminArray.size(); x++) {
								System.out.printf("|%-3d |%-10d | %-23s |  Admin  | %-14d| %-11s | %-18s |\n", 
										x+1,adminArray.get(x).getId(), adminArray.get(x).getNama(), adminArray.get(x).getNumberOfTransaction()
										,adminArray.get(x).getJoinDate(), adminArray.get(x).getLastAccessDate());
						}
						int adminSiz = adminArray.size()+1;
						for(int x = 0; x<porterArray.size(); x++, adminSiz++) {
							System.out.printf("|%-3d |%-10d | %-23s |  Porter | %-14d| %-11s | %-18s |\n", 
									adminSiz, porterArray.get(x).getId(), porterArray.get(x).getNama(), porterArray.get(x).getNumberOfTransaction()
									,porterArray.get(x).getJoinDate(), porterArray.get(x).getLastAccessDate());
						}
						System.out.println("=========================================================================================================");
				
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
					if (isCurrAdmin == true) {
						state = admin.changePassword(currPass, newPass);
					}else {
						state = porter.changePassword(currPass, newPass);
					}
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
	
	private void printInventory() {
		System.out.printf("| Item ID  |%10sName%10s|  Category  |  Quantity  |   Price    |  Expired   |%5sLocation%5s|\n", "", "", "", "", "");
		System.out.println("===========================================================================================================");
		for(int x = 0; x<itemArray.size(); x++) {
			System.out.printf("|%-10d| %-23s| %-11s| %-10d | Rp. %-7d| %-11s| %-17s|\n", 
					itemArray.get(x).getIdItem(), itemArray.get(x).getNama(), itemArray.get(x).getType()
					,itemArray.get(x).getQuantity(), itemArray.get(x).getHarga(), itemArray.get(x).getExpired(), itemArray.get(x).getLokasi());
		}
		System.out.println("...");
		System.out.println("===========================================================================================================");
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
	
	//Search item function
	//=================================
	//Params: idBarang
	//idBarang: idBarang yang diberikan user
	//=================================
	//internal Params
	//itemFound : item yang akan di return, jika item tidak ketemu, return item tanpa isi (null) 
	private Item searchItemByID(int idBarang) {
		Item itemFound = null;
		for (Item currentItem : itemArray) {
			if (currentItem.getIdItem() == idBarang) {
				itemFound = currentItem;
				break;
			}
		}
		return itemFound;
	}
	
	//Search item function
	//=================================
	//Params: nama
	//nama: nama yang diberikan user
	//=================================
	//internal Params
	//itemFound : item yang akan di return, jika item tidak ketemu, return item tanpa isi (null) 
	private Item searchItemByName(String nama) {
		Item itemFound = null;
		for (Item currentItem: itemArray) {
			if (currentItem.getNama().equals(nama)) {
				itemFound = currentItem;
				break;
			}
		}
		return itemFound;
	}
	
	private Item determineSearch() {
		String pencarian;
		boolean typePencarian = false;
		int ID;
		String nama;
		Item itemToSearch = null;
		
		do {
			System.out.printf("Search item by (N to Cancel) [ID | Name]? ");
			
			pencarian = scanner.nextLine();
			if (!(pencarian.equals("ID") || pencarian.equals("Name"))) {
				if (pencarian.equals("N")) {
					mainMenu();
				}else {
					System.out.println("Invalid Selection");
				}
			}else if (pencarian.equals("ID")) {
				typePencarian = true;
			}else if (pencarian.equals("Name")) {
				typePencarian = false;
			}
		}while(!(pencarian.equals("ID") || pencarian.equals("Name")));
		
		do {
			if (typePencarian == true) {
				System.out.printf("Enter Product's ID: ");
				ID = scanInt();
				itemToSearch = searchItemByID(ID);
			}else {
				System.out.printf("Enter Product's name (Press N to cancel): ");
				nama = scanner.nextLine();
				if (nama.equals("N")) {
					mainMenu();
				}
				itemToSearch = searchItemByName(nama);
			}
			
			if (itemToSearch == null) {
				System.out.println("Item doesn't exist!\nInvalid ID or Name..");
			}
		}while(itemToSearch == null);
		
		return itemToSearch;
	}
	private void addTransaction() {
		if (isCurrAdmin == true) {
			adminArray.set(crrnUserIndex, new Admin(crrnUsername, crrnUserID, adminArray.get(crrnUserIndex).getPassword(), isCurrAdmin, (adminArray.get(crrnUserIndex).getNumberOfTransaction()+1), adminArray.get(crrnUserIndex).getJoinDate(), 
					adminArray.get(crrnUserIndex).getLastAccessDate()));
		}else {
			porterArray.set(crrnUserIndex, new Porter(crrnUsername, crrnUserID, porterArray.get(crrnUserIndex).getPassword(), isCurrAdmin, (porterArray.get(crrnUserIndex).getNumberOfTransaction()+1), porterArray.get(crrnUserIndex).getJoinDate(), 
					porterArray.get(crrnUserIndex).getLastAccessDate()));
		}
	}
	
	//Scan integer function
	//=================================
	//Purpose : memastikan input dari user berupa integer, jika input adalah karakter invalid,
	//			return angka -1
	private int scanInt() {
		int scanNumber = -1;
		try {
			scanNumber = scanner.nextInt();
		}
		catch (Exception TypeMismatch) {
			//System.out.println("Invalid input!");
		}
		scanner.nextLine();
		return scanNumber;
	}
}
