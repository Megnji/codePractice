package googlecodejam;

import java.awt.Point;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

/**
 * Another binary search:
 * Problem C. Egg Drop
 * https://code.google.com/codejam/contest/32003/dashboard
 * 
 * Completed and correct (partially)
 * @author megnji
 *
 */
public class EggDrop {
	private static Map<Point,Double> map = new HashMap<Point,Double>();
	public static void main(String[] paras){
		
		map.clear();
		map.put(new Point(0,0), 0.0);
		Double temp = 0.0;
		for (int j=1; j<33; j++){
			temp = 0.0;
			int i = 0;
			while (temp < Math.pow(2, 32) && i < 100000){
				temp = getF(i,j);
				map.put(new Point(i,j), temp);
				i++;
				temp = getF(i,j);
			}
		}
		System.out.println(map.get(new Point(915,3)));
		Scanner scanner = new Scanner(System.in);
		int numOfLines = scanner.nextInt();
		StringBuffer sb = new StringBuffer("");
		for(int i=0; i < numOfLines; i++){
			String str1 = scanner.next();
			String str2 = scanner.next();
			String str3 = scanner.next();
			sb.append("Case #" + (i+1) + ": "+calculate(str1, str2 ,str3)) ;
		}
		
		scanner.close();
		System.out.println(sb.toString());
		try (FileWriter writer = new FileWriter("result.out")){
			writer.write(sb.toString());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
	}
	
	private static String calculate(String str1,String str2,String str3){

		StringBuilder sb = new StringBuilder();
		int f = Integer.parseInt(str1);
		int d = Integer.parseInt(str2);
		int b = Integer.parseInt(str3);
		double rf,rd = 0.0,rb= 0.0;
		rf = map.getOrDefault(new Point(d,b),-1.0);
		
		int temp = getPow2((int)f);
		double temp2 = 0.0;
		while (temp2 < f){
			temp2 = map.getOrDefault(new Point(temp,b), Math.pow(2, 35));
			temp++;
		}
		rd = (double)temp -1;
		temp = 1;
		temp2 = 0.0;
		while (temp2 <f){
			temp2 = map.getOrDefault(new Point(d,temp), Math.pow(2, 35));
			temp ++;
		}
		rb = temp -1;
		if (d >= 100000){
			rb ++;
		}
		if (d >= f){
			rb = 1;
		}
		
		sb = sb.append((long)rf + " "+(int)rd+" "+(int)rb +"\n");
		
		
		return sb.toString();
	}
	
	
	private static Double getF(int d,int b){
		if (d <= 0 || b<=0){
			return 0.0;
		}
		if (b == 1 ){
			return (double)d;
		}

		if (d >= 33 && b >=33){
			return Math.pow(2, 35);
		}
		
		if (b >= d){
			return Math.pow(2, d)-1;
		}
		
		Point p = new Point(d,b);
		if (map.containsKey(p)){
			return map.get(p);
		}


		return getF(d-1,b) + getF(d-1,b-1) + 1;
		
	}
	
	private static int getPow(int f,int b){
		int d = 1;
		while (Math.pow(b, d)< f){
			d++;
		}
		return d;
	}
	
	private static int getPow2(int f){
		int d = 1;
		while (Math.pow(2, d) < f){
			d++;
		}
		return d;
	}
	
	private static int getThat(int f,int b){
		return (int) (f - Math.pow(2, b));
	}
	
}
