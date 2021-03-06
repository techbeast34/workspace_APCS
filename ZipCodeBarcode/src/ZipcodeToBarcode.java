/* POSTNET APCS PROJECT
 * @Satyendra Emani
 * 
 * This program will encode zipcodes into barcodes,
 * and decorde barcodes into zipcodes. In both cases
 * it will find the corresponding city to the zipcode/barcode.
 * 
 * 
 */


import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class ZipcodeToBarcode {
	static final String[] encodedBarcode = { 
			"||:::", // 0
			":::||", // 1
			"::|:|", // 2
			"::||:", // 3
			":|::|", // 4
			":|:|:", // 5
			":||::", // 6
			"|:::|", // 7
			"|::|:", // 8
			"|:|::" }; // 9

	public static void main(String[] args) throws FileNotFoundException {
		File zipCodeFile = new File("ZipCodes.txt");
		File cityFile = new File("ZipCodesCity.txt");
		File barcodeFile = new File("ZipBarCodes.txt");
		Scanner zipcodeFileScanner = new Scanner(zipCodeFile);
		Scanner cityFileScanner = new Scanner(cityFile);
		Scanner barcodeScanner = new Scanner(barcodeFile);
		String[] barcodeArray = new String[6];
		String[] zipCode = new String[6];
		String barcode = "";
		
		
		System.out.println("Options 1 & 2");
		System.out.println();
		while (zipcodeFileScanner.hasNext()) {
			zipCode = readZipCode(zipcodeFileScanner);
			barcodeArray = encodeZipCode(zipcodeFileScanner, zipCode);
			printBarCode(barcodeArray, zipCode, cityFileScanner, cityFile);
			cityFileScanner = new Scanner(cityFile);
		}
		//Parts 1 and 2
		System.out.println("-----------------------------------");
		System.out.println();
		//Break in between parts
		
		zipCode = new String[6];
		cityFileScanner.close();
		cityFileScanner = new Scanner(cityFile);
		//Reinitialize scanner and zipcode array for part 3
		
		System.out.println("Option 3");
		System.out.println();
		while (barcodeScanner.hasNext()) {
			barcode = barcodeScanner.nextLine();
			zipCode = decodeBarcode(barcode);
			printZipCode(zipCode, barcode, cityFileScanner);
			cityFileScanner = new Scanner(cityFile);
		}
		//Part 3
		
		zipcodeFileScanner.close();
		cityFileScanner.close();
		barcodeScanner.close();
		//Close scanners, clean up
	}
	
	/**
	 * Prints out the barcode and corresponding zipcode when reading the barcode file
	 * Pre: Needs a zipcode, barcode, and scanner to be passed
	 * Post: none.
	 * @param zipCode The zipcode read in
	 * @param barcode the barcode also read in
	 * @param scanner Scannef for scanning through city file.
	 */
	private static void printZipCode(String[] zipCode, String barcode, Scanner scanner) {
		int checkTotal = 0;
		
		int checkDigit = Integer.parseInt(zipCode[zipCode.length - 1]);
		//Get the check digit. The zipCode should  
		//have the check digit as the last element.
		
		boolean isInvalid = false;
		
		for (int i = 0; i < 5; i++) {
			try {
				checkTotal += Integer.parseInt(zipCode[i]);
				//Total up the zipCode digits
			}
			catch(Exception e){
				isInvalid = true;
				//If a zipCode digit is null, mark as invalid.
			}
		}
		
		String zipcode = "";
		String currentLine = "";
		String city = "";
		for (int i = 0; i < zipCode.length - 1; i++) {
			if(!isInvalid){
				zipcode += zipCode[i];
				//Turn zipcode array into a string
			}
		}
		
		while (scanner.hasNext()) {
			currentLine = scanner.nextLine();
			if (currentLine.contains(zipcode) && !isInvalid) {
				city += currentLine.replace(",", " ").replace(zipcode, "").trim() + "\n";
			}
		}
		//Search through the city file for lines with same zipcode,
		//if a line with a matching zipcode is found, then add to
		//string named "city" and add a new line after every
		//added line.
		

		
		if (checkDigit == 10 - (checkTotal % 10) || (checkTotal % 10 == 0 && checkDigit == 0)) {
			System.out.print(barcode + "\t -------> \t");
				if(!isInvalid)
					System.out.println(zipcode);
				else if(isInvalid){
					System.out.println("ERROR - Invalid Barcode!");
				}
				if(isInvalid || city.equals("")){
					city = "No Location Found. \n";
				}
			System.out.println(city);
		}
		//Print the barcode and the corresponding zipcode, then print
		//any corresponding locations. If the barcode is invalid,
		//then output "No Location Found." Also tell user barcode
		//is invalid if it is determined to be invalid beforehand.
		else if(!isInvalid){
			System.out.print(barcode + " \t -------> \t");
			System.out.println("ERROR - Invalid Check Digit!");
			if(isInvalid || city.equals("")){
				city = "No Location Found. \n";
			}
			System.out.println(city);
		}
		//Tell the user if the check digit is invalid,
		//and output "No Location found."
		
		
		
	}
	
	/**
	 * Decodes the barcode and translates it into a zipcode
	 * Pre: needs a barcode
	 * Post: none
	 * @param barcode The barcode to be read in
	 * @return The zip code, with each number as an element in an array of strings. The check digit is the last element in the array.
	 */
	private static String[] decodeBarcode(String barcode) {
		String barcodeBit = "";
		char[] barcodeArray = new char[32];
		String[] zipCode = new String[6];
		int count = 0;
	
		for(int i = 1; i <= barcode.toCharArray().length - 2; i++){
				barcodeArray[i] = barcode.toCharArray()[i];
		}
		//Strip the barcode of the frame bars.

		for (int i = 0; i < barcodeArray.length; i++) {
				barcodeBit += barcodeArray[i];
				if(i % 5 == 0 && i != 0){
					zipCode[count] = convertBarcodeBitToDigit(barcodeBit.trim());
					if(count < 5)
						count++;
					else if(count > 5)
						count = 0;
					barcodeBit = "";
				}
			}
		//Build up the xipcode array. Every 5 characters in
		//the stripped barcode array, make a string of them,
		//then convert the barcode "bit" into a zipcode digit.
		
		
		return zipCode;
	}
	/**
	 * 
	 * @param barcodeBit 5 character string that represents part of a barcode
	 * @return the zipcode digit corresponding to barcodeBit. returns -1 if invalid.
	 */
	private static String convertBarcodeBitToDigit(String barcodeBit) {
		switch(barcodeBit){
		case "||:::": // 0
			return "0";
		case ":::||": // 1
			return "1";
		case "::|:|": // 2
			return "2";
		case "::||:": // 3
			return "3";
		case ":|::|": // 4
			return "4";
		case ":|:|:": // 5
			return "5";
		case ":||::": // 6
			return "6";
		case "|:::|": // 7
			return "7";
		case "|::|:": // 8
			return "8";
		case "|:|::": // 9
			return "9";
		} 
		return "-1";
	}

	/**
	 * prints readable and postal barcodes based on zipcode
	 * Pre: needs a barcode, zipcode, scanner, and the city file
	 * Post: none
	 * @param barcode The barcode array
	 * @param zipCode The zipcode 
	 * @param scanner The scanner
	 * @param cityFile The file of zipcodes and corresponding cities
	 * @throws FileNotFoundException just in case the city file is not found.
	 */
	private static void printBarCode(String[] barcode, String[] zipCode, Scanner scanner, File cityFile)
			throws FileNotFoundException {
		String zipcode = "";
		String currentLine = "";
		String city = "";
		for (int i = 0; i < zipCode.length; i++) {
			zipcode += zipCode[i];
		}
		//Convert zipcode array into a string
		
		while (scanner.hasNext()) {
			currentLine = scanner.nextLine();
			if (currentLine.contains(zipcode)) {
				city += currentLine.replace(",", " ").trim() + "\n";
			}
		}
		//Check city file for locations with same zipcodes
		//then append to "city" string with new lines at
		//the end of each new city.
		
		System.out.println(city);
		System.out.print("\t Readable Barcode:");
		System.out.print(" | ");
		for (int i = 0; i < barcode.length; i++) {
			System.out.print(" " + barcode[i]);
		}
		System.out.print(" |");
		System.out.println();
		System.out.print("     \t Postal Barcode:   ");
		System.out.print("|");
		for (int i = 0; i < barcode.length; i++) {
			System.out.print(barcode[i]);
		}
		System.out.print("|");
		System.out.println();
		System.out.println();
		//Print out the barcode in a readable
		//and postal version.
		
	}
	
	/**
	 * Encodes the zip codes into barcodes.
	 * Pre: needs scanner and zipcode, along with encoded barcode array
	 * Post: none
	 * @param scanner The scanner for the zipcode file
	 * @param zipCode The array for the zipcode
	 * @return A barcode.
	 */
	private static String[] encodeZipCode(Scanner scanner, String[] zipCode) {

		String[] barCode = new String[6];
		int total = 0;
		int checkDigit = 0;
		for (int i = 0; i < 5; i++) {
			total += Integer.parseInt(zipCode[i]);
		}
		//Total up the zipcode digits
		
		checkDigit = 10 - (total % 10);
		if (checkDigit == 10) {
			checkDigit = 0;
		}
		//Find the check digit. Sometimes when
		//the check digit is 10, the check digit
		//will manually need to be set to 0.
		
		for (int i = 0; i < zipCode.length; i++) {
			barCode[i] = encodedBarcode[Integer.parseInt(zipCode[i])];
		}
		//Encode the barcode using the global array
		//"encodedBarcode" to encode the zipcode into
		//a barcode
		
		barCode[5] = encodedBarcode[checkDigit];
		//Set last element of barcode array 
		//to be the check digit
		
		return barCode;
		
	}
	
	/**
	 * Reads in zipcodes
	 * Pre: needs scanner
	 * Post: none
	 * @param scanner The scanner for zipcodes
	 * @return An array where the zipcode is stored.
	 */
	private static String[] readZipCode(Scanner scanner) {
		
		String zipCode = scanner.next();
		String[] zipCodeArray = new String[5];
		
		for (int i = 0; i < zipCodeArray.length; i++) {
			zipCodeArray[i] = zipCode.charAt(i) + "";
		}
		//Read in zipcode, convertstring to an array.
		
		return zipCodeArray;
	}

}
