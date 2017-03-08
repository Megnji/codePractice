package googlecodejam;

import java.awt.Point;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

/**
 * Generate maze according to the paths:
 * https://code.google.com/codejam/contest/32003/dashboard
 * 
 * 
 * Completed and correct
 * @author megnji
 *
 */
public class AlwaysTurnLeft {
	
	private enum Direction{North,South,West,East};
	private static class Block {
		Point pos = new Point();  
		boolean canNorth;
		boolean canSouth;
		boolean canWest;
		boolean canEast;
		
		public Block(int x, int y){
			pos.x = x;
			pos.y = y;
			canNorth = false;
			canSouth = false;
			canWest = false;
			canEast = false;
		}
		
	}
	
	public static void main(String[] paras){
		
		Scanner scanner = new Scanner(System.in);
		int numOfLines = scanner.nextInt();
		StringBuffer sb = new StringBuffer("");
		for(int i=0; i < numOfLines; i++){
			String str1 = scanner.next();
			String str2 = scanner.next();
			sb.append("Case #" + (i+1) +":\n"); 
			sb.append(calculateMaze(str1,str2));
		}
		
		scanner.close();
		
		try (FileWriter writer = new FileWriter("result.out")){
			writer.write(sb.toString());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		System.out.println(sb.toString());
	}
	
	private static int x;
	private static int y;
	private static int minX;
	private static int maxX;
	private static int maxY;
	@SuppressWarnings("incomplete-switch")
	private static String calculateMaze(String path,String reversedPath){
		StringBuilder sb = new StringBuilder();
		Direction face = Direction.South;
		x = 0;
		y = -1;
		minX = 0;
		maxX = 0;
		maxY = 0;
		Map<Point,Block> map = new HashMap<Point,Block>();
		Block b = new Block(x,y);
		Point begin = b.pos;
		map.put(b.pos, b);
		char[] array = path.toCharArray();
		for (char c:array){
			switch (c){
			case 'W':
				can(face,b,true);
				move(face);
				b = map.get(new Point(x,y));
				if (b == null){
					b = new Block(x,y);
					can(getOppo(face),b,true);
					map.put(b.pos, b);
				}
				break;
			case 'L':
				can(getLeft(face),b,true);
				face = getLeft(face);
				break;
			case 'R':
				can(face,b,false);
				can(getLeft(face),b,false);
				face = getRight(face);
				break;
			}
		}
		
		Point end = b.pos;
		switch (face){
		case South:
			maxY --;
			break;
		case West:
			minX ++;
			break;
		case East:
			maxX --;
			break;
		}
		face = getOppo(face);
		
		array = reversedPath.toCharArray();
		for (char c:array){
			switch (c){
			case 'W':
				can(face,b,true);
				move(face);
				b = map.get(new Point(x,y));
				if (b == null){
					b = new Block(x,y);
					can(getOppo(face),b,true);
					map.put(b.pos, b);
				}
				break;
			case 'L':
				can(getLeft(face),b,true);
				face = getLeft(face);
				break;
			case 'R':
				can(face,b,false);
				can(getLeft(face),b,false);
				face = getRight(face);
				break;
			}
		}
		
		map.remove(end);
		map.remove(begin);
		
		for (int i = 0; i < maxY +1; i++){
			for (int j = minX; j<maxX+1; j ++){
				sb.append(getString(map.get(new Point(j,i))));
			}
			sb.append("\n");
		}
		return sb.toString();

	}
	
	private static String getString(Block b){
		int dec = 0;
		if (b.canNorth){
			dec ++;
		}
		if (b.canSouth){
			dec = dec +2 ;
		}
		if (b.canWest){
			dec = dec + 4;
		}
		if (b.canEast){
			dec = dec + 8;
		}
		String hex = Integer.toHexString(dec);
		return hex;
	}
	
	private static Direction getLeft(Direction d){
		switch (d){
		case North:
			return Direction.West;
		case South:
			return Direction.East;
		case East:
			return Direction.North;
		case West:
			return Direction.South;
		}
		return null;
	}
	private static Direction getOppo(Direction d){
		switch (d){
		case North:
			return Direction.South;
		case South:
			return Direction.North;
		case East:
			return Direction.West;
		case West:
			return Direction.East;
		}
		return null;
	}
	
	private static void can(Direction d,Block b,boolean can){
		switch (d){
		case North:
			b.canNorth = can;
			break;
		case South:
			b.canSouth = can;
			break;
		case East:
			b.canEast = can;
			break;
		case West:
			b.canWest = can;
			break;
		}
	}
	
	private static void move(Direction d){
		switch (d){
		case North:
			y--;
			break;
		case South:
			y++;
			if (y > maxY){
				maxY = y;
			}
			break;
		case East:
			x++;
			if (x > maxX){
				maxX = x;
			}
			break;
		case West:
			x--;
			if (x < minX) {
				minX = x;
			}
			break;
		}
	}
	
	private static Direction getRight(Direction d){
		switch (d){
		case North:
			return Direction.East;
		case South:
			return Direction.West;
		case East:
			return Direction.South;
		case West:
			return Direction.North;
		}
		return null;
	}
}
