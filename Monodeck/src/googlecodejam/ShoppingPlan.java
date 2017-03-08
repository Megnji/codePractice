package googlecodejam;

import java.io.FileWriter;
import java.io.IOException;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;

import javax.swing.event.ListSelectionEvent;

public class ShoppingPlan {
	private static int NUM_OF_THREAD = 1000;
	static Thread[] threads = new Thread[NUM_OF_THREAD];
	private static Map<List<Item>,Double> map  = new HashMap<List<Item>,Double>();
	private static class Item {
		String name;
		boolean isPerishable;
	}

	private static class Shop {
		int x;
		int y;
		Map<Item, Integer> map = new HashMap<Item, Integer>();
	}

	public static void main(String[] paras) {
		
		
		Scanner scanner = new Scanner(System.in);
		int numOfCases = scanner.nextInt();
		StringBuffer sb = new StringBuffer("");
		for (int i = 0; i < numOfCases; i++) {
			int numItems = scanner.nextInt();
			int numShops = scanner.nextInt();
			int priceOfGas = scanner.nextInt();
			List<Item> items = new ArrayList<Item>();
			for (int j = 0; j < numItems; j++) {
				String item = scanner.next();
				Item it = new Item();
				if (item.endsWith("!")) {
					it.name = item.substring(0, item.lastIndexOf('!'));
					it.isPerishable = true;
				} else {
					it.name = item;
					it.isPerishable = false;
				}
				items.add(it);
			}
			List<Shop> shops = new ArrayList<Shop>();
			for (int j = 0; j < numShops; j++) {
				String str = scanner.nextLine();
				String[] strs = str.split(" ");
				Shop shop = new Shop();
				shop.x = Integer.parseInt(strs[0]);
				shop.y = Integer.parseInt(strs[1]);
				for (int k = 2; k < strs.length; k++) {
					String[] temp = strs[k].split(":");
					Item it = null;
					for (Item item : items) {
						if (item.name.equals(temp[0])) {
							it = item;
						}
					}
					int price = Integer.parseInt(temp[1]);
					shop.map.put(it, price);
				}
				shops.add(shop);
			}

			sb.append(calculate(priceOfGas, items, shops));
		}

		scanner.close();

		try (FileWriter writer = new FileWriter("result.out")) {
			writer.write(sb.toString());
			System.out.println(sb.toString());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private static Double calculate(int gas, List<Item> items, List<Shop> shops) {
		DecimalFormat df = new DecimalFormat("#.#######");
		double min = -1;
		for (int i = 0; i< items.size() ; i++){
			
		}
		return 0.0;
	}
	
	private static Double getMin(List<Item> list,List<Shop> shops){
		if (map.containsKey(list)){
			return map.get(list);
		}
		
		if (list.size() == 1){
			return ()
		}
		
	}
	

}
