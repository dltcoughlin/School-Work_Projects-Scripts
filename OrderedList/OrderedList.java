//Name: Dalton Coughlin
//Project Name: Project 2
//File Name: OrderedList.java
//Date: 12/5/2020
//Description: This script is used for checking if the input file is in sorted order
import java.util.List;
public class OrderedList {
	//implments Compareable method  and checks if list is sorted
	public static <T extends Comparable<? super T>> boolean checkSorted(List<T> list){
	boolean isSorted = true;
	for(int i = list.size()-1; i > 0 ; i--){
		T current = list.get(i);
		//throws list and current into checksorted method and returns if isSorted
		if(!checkSorted(list, current)){
			isSorted = false;
		}
	}
		return isSorted;
	}
	//Method to grab list and current value to check if isSorted
	private static <T extends Comparable<? super T>> boolean checkSorted(List<T> list, T current) {
		T currentValue = list.get(list.indexOf(current));
		T nextValue = list.get(list.indexOf(current) - 1);
		//is the next value isn't empty it checks it uses compareTo method to check the sort
		if (nextValue != null) {
			return currentValue.compareTo(nextValue) >= 0; 
		}
		return true;
	}
}