import java.io.*;

public class coursera_2 {
	public static void main(String args[]){
		
//		String fileName = "test1_25_29_21.txt";
		String fileName = "BigInts.txt";
//		String fileName = "test2_615_587_518.txt";
//		String fileName = "test3.txt";
		String line = null;
		
		int[] myArray = new int[10000]; //BigInts
//		int[] myArray = new int[10]; //test1
//		int[] myArray = new int[100]; //test2
//		int[] myArray = new int[1000]; //test3
		try {
			FileReader fileReader = new FileReader(fileName);
			BufferedReader bufferedReader = new BufferedReader(fileReader);
			
			int i = 0;
			
			while ((line = bufferedReader.readLine()) != null) {
				myArray[i] = Integer.parseInt(line);
				i++;
			}
			bufferedReader.close();
		}
		catch(FileNotFoundException ex) {
			System.out.println("Unable to open file "+fileName);
		}
		catch (IOException ex) {
			System.out.println("Error reading file");
		}

		System.out.println("comparisons: " + quickSortComparisons_second(myArray, 0, myArray.length - 1));
		
	}

	private static int quickSortComparisons_first(int[] arr, int l, int r) {
		int len = (r-l) + 1;
		if (len <= 1)	return 0;

		int countComp = len - 1;
		int p = l;
		
		int i = l + 1,j;
		for (j = (l+1); j <= r; j++) {
			if (arr[p] > arr[j]) {
				int temp = arr[i];
				arr[i] = arr[j];
				arr[j] = temp;
				i++;
			}
		}
		int temp = arr[p];
		arr[p] = arr[i-1];
		arr[i-1] = temp;
//		printMyArr(arr);
		countComp += quickSortComparisons_first(arr,l,(i-2));
		countComp += quickSortComparisons_first(arr,i,r);
		return countComp;
	}
	
	private static int quickSortComparisons_second(int[] arr, int l, int r) {
		int len = (r-l) + 1;
		if (len <= 1)	return 0;

		int countComp = len - 1;
		int p = r;
		int temp = arr[p];
		arr[p] = arr[l];
		arr[l] = temp;
		p = l;
		
		int i = l + 1,j;
		for (j = (l+1); j <= r; j++) {
			if (arr[p] > arr[j]) {
				temp = arr[i];
				arr[i] = arr[j];
				arr[j] = temp;
				i++;
			}
		}
		temp = arr[p];
		arr[p] = arr[i-1];
		arr[i-1] = temp;
//		printMyArr(arr);
		countComp += quickSortComparisons_second(arr,l,(i-2));
		countComp += quickSortComparisons_second(arr,i,r);
		return countComp;
	}
	
//	private static int quickSortComparisons_second(int[] arr, int l, int r) {
//		int len = (r-l) + 1;
//		if (len <= 1){
////			System.out.println("base case!");
//			return 0;
//		}
//
//		int countComp = len - 1;
////		System.out.println("Current countComp: " + countComp);
//		int p = r;
//		
//		int i = l,j;
//		for (j = l; j < r; j++) {
//			if (arr[p] > arr[j]) {
//				int temp = arr[i];
//				arr[i] = arr[j];
//				arr[j] = temp;
//				i++;
//			}
//		}
//		int temp = arr[p];
//		arr[p] = arr[i];
//		arr[i] = temp;
////		printMyArr(arr);
//		countComp += quickSortComparisons_second(arr,l,(i-1));
//		countComp += quickSortComparisons_second(arr,(i+1),r);
//		return countComp;
//	}
	
	private static int quickSortComparisons_third(int[] arr, int l, int r) {
		int len = (r-l) + 1;
		if (len <= 1)	return 0;

		int countComp = len - 1;
		int p;
		int first = arr[l];
		int last = arr[r];
		int med, m;
		
		if (len%2 == 0) {
			m = l + len/2 - 1;
//			System.out.println("even, med index: " + m);
			med = arr[m];
		}
		else {
			m = l + len/2;
//			System.out.println("odd, med index: " + m);
			med = arr[m];
		}
	
//		System.out.println(first+" or "+med+" or "+last);
		
		if (first > med) {
			  if (med > last) {
			    p = m;
			  } else if (first > last) {
			    p = r;
			  } else {
			    p = l;
			  }
			} else {
			  if (first > last) {
			    p = l;
			  } else if (med > last) {
			    p = r;
			  } else {
			    p = m;
			  }
			}

//		System.out.println("p is: " + arr[p]);
		
		int temp = arr[p];
		arr[p] = arr[l];
		arr[l] = temp;
		p = l;
		
		int i = l + 1,j;
		for (j = (l+1); j <= r; j++) {
			if (arr[p] > arr[j]) {
				temp = arr[i];
				arr[i] = arr[j];
				arr[j] = temp;
				i++;
			}
		}
		temp = arr[p];
		arr[p] = arr[i-1];
		arr[i-1] = temp;
//		printMyArr(arr);
		countComp += quickSortComparisons_third(arr,l,(i-2));
		countComp += quickSortComparisons_third(arr,i,r);
		return countComp;
	}

	private static void printMyArr(int[] arr) {
		System.out.println("Current array: ");
		for (int i = 0; i < arr.length; i++){
			System.out.println("|"+ arr[i] +"|");
		}
		
	}
}
