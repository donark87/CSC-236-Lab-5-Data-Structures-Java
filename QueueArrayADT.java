//Donark Patel
//CSC 236-01
//Lab 5

public interface QueueArrayADT<T>
{
	public void initializeQueue();
	public boolean isEmptyQueue();
	public boolean isFullQueue();
	public int count();
	public T peekFront() throws QueueUnderflowException;
	public T peekBack() throws QueueUnderflowException;
	public void enqueue(T element)throws QueueOverflowException;
	public void dequeue()throws QueueUnderflowException;
	public String toString();
}