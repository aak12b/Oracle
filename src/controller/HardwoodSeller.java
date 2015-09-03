/**
 * 
 */
package controller;
import java.io.*;
import java.util.*;

/**
 * @author Esteban
 * Modified by Aaron Kemmer for CEN5035
 *
 */
public class HardwoodSeller {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String deliveryFile = args[0];
		String info, order;
		String name, date, address;
		String[] customer, materials, fileResult;
		HashMap fullOrder = new HashMap();
		
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
			
			fullOrder.put(first, second);
		}
		
		Set set = fullOrder.entrySet();
		Iterator i = set.iterator();
		
		while(i.hasNext()){
			Map.Entry entry = (Map.Entry)i.next();
			System.out.print(entry.getKey() + ": ");
			System.out.println(entry.getValue());
		}
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
	
	public Double deliveryTime(){
		Double deliveryETA = 0.0;
		return deliveryETA;
	}
	
}
