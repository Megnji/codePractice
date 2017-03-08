package googlecodejam;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Convert any number/string base X to base Y on decimal, google Codejam question A: AlienNumbers:
 * https://code.google.com/codejam/contest/32003/dashboard
 * 
 * 
 * Completed and correct
 * @author megnji
 *
 */
public class AlienNumbers {

	public static void main(String[] paras){
		
		Scanner scanner = new Scanner(System.in);
		int numOfLines = scanner.nextInt();
		StringBuffer sb = new StringBuffer("");
		for(int i=0; i < numOfLines; i++){
			String str1 = scanner.next();
			String str2 = scanner.next();
			String str3 = scanner.next();
			sb.append("Case #" + (i+1) + ": "+convert(str1, str2 ,str3) + "\n") ;
		}
		
		scanner.close();
		
		try (FileWriter writer = new FileWriter("result.out")){
			writer.write(sb.toString());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
	}
	
	private static String convert(String alienNumber, String sourceLanguage,String targetLanguage){
		int decimalValue = convertToDecimal(alienNumber,sourceLanguage);
		String targetValue = convertToTarget(decimalValue,targetLanguage);

		return targetValue;
		
	}
	
	private static int convertToDecimal(String source, String sourceLan){
		int result = 0;
		char[] a = source.toCharArray();
		
		ArrayList<Character> lanList = new ArrayList<Character>();
		
		char[] lan = sourceLan.toCharArray();
		for (char c : lan){
			lanList.add(c);
		}
		int digit = 1;
		for (int i = a.length-1; i>-1; i--){
			result += lanList.indexOf(Character.valueOf(a[i]))*digit;
			digit = digit * lan.length;
		}
		
		
		return result;
	}
	
	private static String convertToTarget(int value,String targetLan){
		StringBuffer sb = new StringBuffer("");
		char[] lan = targetLan.toCharArray();
		while (value > 0){
			int mod = value % lan.length;
			sb.insert(0, lan[mod]);
			value = value / lan.length;
		}
		return sb.toString();
	}
}
