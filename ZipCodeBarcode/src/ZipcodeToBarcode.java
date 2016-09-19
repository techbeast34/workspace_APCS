import java.io.File;
import java.io.FileNotFoundException;
import java.lang.reflect.Array;
import java.util.Scanner;

public class ZipcodeToBarcode {
	static final String[] encodedBarcode = { "||:::", // 0
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
		while (zipcodeFileScanner.hasNext()) {
			zipCode = readZipCode(zipcodeFileScanner);
			barcodeArray = encodeZipCode(zipcodeFileScanner, zipCode);
			printBarCode(barcodeArray, zipCode, cityFileScanner, cityFile);
			cityFileScanner = new Scanner(cityFile);
		}
		zipCode = new String[6];
		cityFileScanner = new Scanner(cityFile);
		while (barcodeScanner.hasNext()) {
			barcode = barcodeScanner.nextLine();
			zipCode = decodeBarcode(barcode);
			printZipCode(zipCode, barcode, cityFileScanner);

		}
	}

	private static void printZipCode(String[] zipCode, String barcode, Scanner cityFileScanner) {
		int checkTotal = 0;
		int checkDigit = Integer.parseInt(zipCode[zipCode.length - 1]);

		for (int i = 0; i < 5; i++) {
			try {
				checkTotal += Integer.parseInt(zipCode[i]);
			}
			catch(Exception e){
				System.out.println(i);
			}
		}

		if (checkDigit == 10 - (checkTotal % 10) || (checkTotal % 10 == 0 && checkDigit == 0)) {
			System.out.print(barcode + "------->");
			for (int i = 0; i < zipCode.length - 1; i++) {
				System.out.print(zipCode[i]);
			}
			System.out.println();
		} else {
			System.out.print(barcode + "------->");
			System.err.println("ERROR - Invalid Check Digit!");
		}

	}

	private static String[] decodeBarcode(String barcode) {
		String barcodeBit = "";
		char[] barcodeArray = new char[32];
		String[] zipCode = new String[6];
		int count = 0;
		barcodeArray = barcode.toCharArray();

		for (int i = 0; i < barcodeArray.length; i++) {
			if ((i != 0 || i != barcodeArray.length - 1)) {
				barcodeBit += barcodeArray[i];
				if (i % 5 == 0) {
					if (count >= 6) {
						count = 0;
					}
					for (int c = 0; c < encodedBarcode.length; c++) {
						if (encodedBarcode[c].equals(barcodeBit)) {
							zipCode[count] = c + "";
						}
						else if(c == 10){
							zipCode[count] = "-1";
						}
					}
					barcodeBit = "";
					count++;
				}
			}

		}
		return zipCode;
	}

	private static void printBarCode(String[] barcode, String[] zipCode, Scanner scanner, File cityFile)
			throws FileNotFoundException {
		String zipcode = "";
		String currentLine = "";
		String city = "";
		for (int i = 0; i < zipCode.length; i++) {
			zipcode += zipCode[i];
		}

		while (scanner.hasNext()) {
			currentLine = scanner.nextLine();
			if (currentLine.contains(zipcode)) {
				city += currentLine + "\n";
			}
		}
		System.out.println(city);
		System.out.print("\t Readable Barcode");
		System.out.print(" | ");
		for (int i = 0; i < barcode.length; i++) {
			System.out.print(" " + barcode[i]);
		}
		System.out.print(" |");
		System.out.println();
		System.out.print("     \t Postal Barcode ");
		System.out.print("|");
		for (int i = 0; i < barcode.length; i++) {
			System.out.print(barcode[i]);
		}
		System.out.print("|");
		System.out.println();
		System.out.println();
	}

	private static String[] encodeZipCode(Scanner scanner, String[] zipCode) {

		String[] barCode = new String[6];
		int total = 0;
		int checkDigit = 0;
		for (int i = 0; i < 5; i++) {
			total += Integer.parseInt(zipCode[i]);
		}
		checkDigit = 10 - (total % 10);
		if (checkDigit == 10) {
			checkDigit = 0;
		}
		for (int i = 0; i < zipCode.length; i++) {
			barCode[i] = encodedBarcode[Integer.parseInt(zipCode[i])];
		}
		barCode[5] = encodedBarcode[checkDigit];
		return barCode;
	}

	private static String[] readZipCode(Scanner scanner) {
		int total = 0;
		int checkDigit = 0;
		String zipCode = scanner.next();
		String[] zipCodeArray = new String[5];
		for (int i = 0; i < zipCodeArray.length; i++) {
			zipCodeArray[i] = zipCode.charAt(i) + "";
		}
		return zipCodeArray;
	}

}
