package controller;
import java.io.*;
import java.util.*;

/**
 * @author Esteban
 * Modified by Aaron Kemmer for CEN5035
 *
 *	@usage 
 *	Compiled via "javac <pathtothisfile>"
 *	Run via "java <srcpath> <inputpath>"
 */
public class HardwoodSeller {

	/**
	 * @param args
	 * 
	 * Main sends the order file to be parsed.
	 * Relevant dated is stored in corresponding HashMaps
	 * within an inner class where it is used for calculations.
	 * An ArrayList stores the resulting deliverable items
	 * and their necessary data.
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String deliveryFile = args[0];
		Double totalPrice = 0.0;
		Double totalDel = 0.0;
		String info, order;
		String name, date, address;
		String[] customer, materials, fileResult;
		List<Deliverable> fullOrder = new ArrayList<Deliverable>();
		
		fileResult = new String[2];
		fileResult = readInputFile(deliveryFile);
		
		info = fileResult[0];
		order = fileResult[1];
		
		customer = info.split(";");
		name = customer[0];
		address = customer[1];
		date = customer[2];
		
		materials = order.split(";");
		
		for(int counter = 0; counter < materials.length; ++counter){
			String[] itemSplit = materials[counter].split(":");
			
			String first = itemSplit[0];
			int second = Integer.parseInt(itemSplit[1]);
			
			//fullOrder.put(first, second);
			Deliverable item = new Deliverable(first, second);
			fullOrder.add(item);
		}
		
		//Set set = fullOrder.entrySet();
		//Iterator i = set.iterator();
		System.out.printf("Customer: %s \nAddress: %s \nOrder Date: %s\n\n", name, address, date);
		System.out.printf("%15s %13s %13s\n", "Wood", "Amount(BF)", "Price");
		System.out.println("----------------------------------------------");
		for (Deliverable del : fullOrder){
			del.printItem();
			if (del.delTime > totalDel){
				totalDel = del.delTime;
				totalPrice += del.price;
			}
		}
		
		System.out.println();
		System.out.printf("Estimated Delivery: %s hours\n", totalDel);
		System.out.printf("Total Price: $%.2f\n", totalPrice);
	}
	
	public static String[] readInputFile(String inputFilePath){
		String[] result = new String[2];

		try{
			FileReader inFile = new FileReader(inputFilePath);
			BufferedReader buff = new BufferedReader(inFile);
			String firstLine, materials = "", myLine;
			
			firstLine = buff.readLine();
			
			while((myLine = buff.readLine()) != null){
				materials += myLine;
			}
			
			result[0] = firstLine;
			result[1] = materials;
			
			inFile.close();
		}
		catch(FileNotFoundException e){
			System.out.println("Error, file not found!");
			result = null;
		}
		catch(IOException ioe){
			System.out.println("Error, Could not read file!");
			result = null;
		}
		return result;

	}
	
	/*
	 * The following inner class replaces the
	 * original WoodItem class defined in a file. That class
	 * is to be removed as it is unused.
	 */
	private static class Deliverable{
		private int amount;
		private String type;
		private double price;
		private double delTime;
		private double multiplier;
		
		HashMap prices = new HashMap();
		HashMap baseDels = new HashMap();
		
		private void fillMaps(){
			prices.put("Cherry", new Double(5.95));
			prices.put("Curly Maple", new Double(6.00));
			prices.put("Genuine Mahogany", new Double(9.60));
			prices.put("Wenge", new Double(22.35));
			prices.put("White Oak", new Double(6.70));
			prices.put("Sawdust", new Double(1.50));
			
			baseDels.put("Cherry", new Double(2.5));
			baseDels.put("Curly Maple", new Double(1.5));
			baseDels.put("Genuine Mahogany", new Double(3.0));
			baseDels.put("Wenge", new Double(5.0));
			baseDels.put("White Oak", new Double(1.3));
			baseDels.put("Sawdust", new Double(1.0));
		}

		public Deliverable(String woodType, int bf){
			this.type = woodType;
			this.amount = bf;
			
			fillMaps();
			
			this.price = this.amount * (double)prices.get(this.type);
			
			int check = this.amount;
			if (check < 101)
				multiplier = 1;
			else if (check < 201)
				multiplier = 2;
			else if (check < 301)
				multiplier = 3;
			else if (check < 401)
				multiplier = 4;
			else if (check < 501)
				multiplier = 5;
			else
				multiplier = 5.5;
			
			this.delTime = multiplier * (double)baseDels.get(this.type);
		}
		
		public void printItem(){
			System.out.printf("%15s %13d %13s\n", this.type, this.amount, "$" + String.format("%.2f", this.price));
		}
	}
}
