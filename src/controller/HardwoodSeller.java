/**
 * 
 */
package controller;
import java.io.*;

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
		String[] customer, materials, fileResult;
		
		fileResult = new String[2];
		fileResult = readInputFile(deliveryFile);
		
		info = fileResult[0];
		order = fileResult[1];
		
		System.out.println(info);
		System.out.println(order);
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
