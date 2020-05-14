package oopFinalPkg.Model;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Random;
import java.util.Scanner;

import oopFinalPkg.Model.Humans.Admin;
import oopFinalPkg.Model.Humans.Porter;

public abstract class HelperItem {
	
	protected ArrayList<Item> itemArray = new ArrayList<Item>();
	protected Random random = new Random();
	protected ArrayList<Transaction> transactionArray = new ArrayList<Transaction>();
	protected Scanner scanner = new Scanner(System.in);
	protected String crrnDate = new SimpleDateFormat("dd-MM-yyyy").format(new Date());
	//String nama, int id, int password, boolean accountType
	
}