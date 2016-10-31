import java.util.*;

public class Heap<E extends Comparable<E>> {

	private E[] heap;
	private int size;
	
	public Heap(){
		this.size = 0;
		heap = (E[]) new Comparable[2];
	}
	
	public Heap(E[] arr) {
		this.size = arr.length;
		heap = (E[]) new Comparable[this.size];
		System.arraycopy(arr, 0, heap, 1, arr.length);
		buildHeap();
	}
	
	private void buildHeap() {
		for (int k = size/2; 0 < k; k--) {
			percolatingDown(k);
		}
	}

	private void percolatingDown(int k) {
		E temp = heap[k];
		int child;
		
		for(; 2*k < size; k = child) {
			child = 2*k;
			
			if (child != size && heap[child].compareTo(heap[child+1]) > 0)
				child++;
			
			if (temp.compareTo(heap[child]) > 0)
				heap[k] = heap[child];
			
			else 
				break;
		}
		heap[k] = temp;
	}

	public int getSize() {
		return this.size;
	}
	
	public void insert(E item) {
		if(size == heap.length - 1) doubleSize();
		
		int pos = ++size;
		
		for (; pos > 1 && item.compareTo(heap[pos/2]) < 0; pos = pos/2)
			heap[pos] = heap[pos/2];
		
		heap[pos] = item;
		
	}

	private void doubleSize() {
		E[] old = heap;
		heap = (E[]) new Comparable[heap.length*2];
		System.arraycopy(old,  1,  heap, 1, size);
		
	}
	
	public E removeMin() {
		E min = heap[1];
		heap[1] = heap[size--];
	    percolatingDown(1);
		return min;
	}
	
	
}
