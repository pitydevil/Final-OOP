package oopFinalPkg.Model;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Random;
import java.util.Scanner;

import oopFinalPkg.Model.Humans.Admin;
import oopFinalPkg.Model.Humans.Porter;

public abstract class HelperItem {
	
	protected String crrnUsername;
	protected int crrnUserID;
	protected boolean isCurrAdmin;
	protected int crrnUserIndex;
	protected ArrayList<Item> itemArray = new ArrayList<Item>();
	protected final Random random = new Random();
	protected ArrayList<Transaction> transactionArray = new ArrayList<Transaction>();
	protected final Scanner scanner = new Scanner(System.in);
	protected final String crrnDate = new SimpleDateFormat("dd-MM-yyyy").format(new Date());
	protected final String crrnDateWM = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss").format(new Date());
	protected ArrayList<Admin> adminArray = new ArrayList<Admin>();
	protected ArrayList<Porter> porterArray = new ArrayList<Porter>();

}