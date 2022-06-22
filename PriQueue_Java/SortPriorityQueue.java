import java.util.*;
public class SortPriorityQueue<T>
{
    private ArrayList<T> array;
    public SortPriorityQueue()
    {
        array = new ArrayList<T>();
    }
    public void add(T addNum)
    {
        array.add(addNum);  
        System.out.println(addNum + " added to queue");
    }
    public T returnPriority()
    {
        T returnOut = array.get(0);
        for(int i = 1; i < array.size(); i++)
        {
            Comparable<T> length = (Comparable<T>)
            array.get(i);
            if(length.compareTo(returnOut) < 0)
            returnOut = (T) length;
        }
        return returnOut;
    }
    public T remove()
    {
        T returnOut = array.get(0);
        int removeInt = 0;  
        for(int i = 1; i < array.size(); i++)
        {
            Comparable<T> length = (Comparable<T>)
            array.get(i);
            if(length.compareTo(returnOut) < 0)
            {
                returnOut = (T) length;
                removeInt = i;
            }
        }
        array.remove(removeInt);
        System.out.println(returnOut + " removed from queue");
        return returnOut;
    }
    public static void main(String array[])
        {
        SortPriorityQueue<Integer> queue = new SortPriorityQueue<Integer>();
        queue.add(5);
        queue.add(10);
        queue.add(3);
        queue.add(4);
        queue.remove();
        System.out.println("Priority Next: " + queue.returnPriority());
        }
}