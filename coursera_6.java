import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Scanner;
import java.util.Set;

public class coursera_6 {

	public static void main(String[] args) {
		
		//Construct hash table A
		//Exclude duplicates
		Set<Long> A = createHashTable("2sum.txt");
		
		//track the number of 2sums that are found
		int num2sums = 0;
		
		//for t = -10000 to 10000,
		//for each x in A, lookup t-x
		//ensure distinctness, i.e. t=x+x is not valid
		
		for (int t = -10000; t <= 10000; t++) {
			boolean found = false;
			if (t%100 == 0)
			   	System.out.println(t);
			for (Iterator<Long> iterator = A.iterator(); iterator.hasNext() && !found;) {
				   Long x = (Long) iterator.next();
				   if (A.contains(t-x) && (t-x) != x) {
					   found = true;
					   num2sums++;
				   
				   }
			}
		}
		System.out.println(num2sums);

	}

	private static Set<Long> createHashTable(String fileName) {
		Set<Long> A = new HashSet<Long>();
		System.out.println("Creating hash table");
		String line = null;

		try {
			FileReader fileReader = new FileReader(fileName);
			BufferedReader bufferedReader = new BufferedReader(fileReader);
			Scanner s;
			while ((line = bufferedReader.readLine()) != null) {
				s = new Scanner(line);
				while(s.hasNext())
				{
					Long num = s.nextLong();
					if (!A.contains(num)) {
						A.add(num);
					}
				}
			}
			bufferedReader.close();
		}
		catch(FileNotFoundException ex) {
			System.out.println("Unable to open file "+fileName);
		}
		catch (IOException ex) {
			System.out.println("Error reading file");
		}
		
		return A;
	}

}
