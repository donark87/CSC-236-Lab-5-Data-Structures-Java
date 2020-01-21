//Donark Patel
//CSC 236-01
//Lab 5

public class QueueArray<T> implements QueueArrayADT<T>
{
	private int count;
	private int maxQueueSize;
	private int queueFront;
	private int queueRear;
	private T[] list;

	/**
	Default Constructor
	Postcondition: Create an array with deafult values
				   maxQueueSize = 100
				   count = 0
				   queueFront = 0
				   queueRear = maxQueueSize-1
	**/
	public QueueArray()
	{
		maxQueueSize = 100;
		queueFront = 0;
		queueRear = maxQueueSize -1;
		count = 0;
		list = (T[])new Object[maxQueueSize];
	}

	/**
	Overloaded constructor
	Postcondition: Create an array with user input
		   	       maxQueueSize = queueMaxSize
		   	       count = 0
		   	       queueFront = 0
		   	       queueRear = maxQueueSize-1
		   	       if queueMaxSize <= 0 then maxQueueSize = 100
	**/
	public QueueArray(int queueMaxSize)
	{
		if(queueMaxSize <= 0)
		{
			System.out.println("The array size much be greater than zero.");
			System.out.println("Creating an array of 100 elements");
			maxQueueSize = 100;
		}
		else
		{
			maxQueueSize = queueMaxSize;
			queueFront = 0;
			queueRear = maxQueueSize -1;
			count = 0;
			list = (T[])new Object[maxQueueSize];
		}
	}

	/**
	Method to initialize the Queue to empty
	Postcondition: count = 0
				   queueFront = 0
				   queueRear = maxQueueSize-1

	**/
	public void initializeQueue()
	{
		for(int i = queueFront; i < queueRear; i = (i+1)%maxQueueSize)
		{
			list[i] = null;
		}

		queueFront = 0;
		queueRear = maxQueueSize -1;
		count = 0;
	}

	/**
	Method to check if the Queue is empty
	Postcondition: Returns true if the Queue is empty
				   Returns false if the Queue is not empty
	**/
	public boolean isEmptyQueue()
	{
		return(count == 0);
	}

	/**
	Method to check if the Queue if full
	Postcondition: Returns true if the Queue if full
				   Returns false if the Queue is not full
	**/
	public boolean isFullQueue()
	{
		return(count == maxQueueSize);
	}

	/**
	Method to access count
	Postcondition: Returns the value of count
	**/
	public int count()
	{
		return count;
	}

	/**
	Method to return the first element in the Queue
	Precondition: A queue must exist and not empty
	Postcondition: If the Queue is empty a
				   QueueUnderflowException is thrown
				   otherwise, a reference to a copy
				   of the first element is returned
	**/
	public T peekFront() throws QueueUnderflowException
	{
		if(isEmptyQueue())
		{
			throw new QueueUnderflowException();
		}
		return(T)list[queueFront];
	}

	/**
	Method to return the last element in the Queue
	Precondition: A queue must exist and not empty
	Postcondition: If the Queue is empty a
				   QueueUnderflowException is thrown
				   otherwise, a reference to a copy
				   of the last element is returned
	**/
	public T peekBack() throws QueueUnderflowException
	{
		if(isEmptyQueue())
		{
			throw new QueueUnderflowException();
		}
		return(T) list[queueRear];
	}

	/**
	Method to add an element to the Queue
	Precondition: A queue must exist and not empty
	PostCondition: element is added to the Queue,
				   if the Queue is full a
				   QueueOverflowException is thrown
	**/
	public void enqueue(T element)throws QueueOverflowException
	{
		if(isFullQueue())
		{
			throw new QueueOverflowException();
		}
		else
		{
			queueRear = (queueRear + 1)% maxQueueSize;
			count++;
			list[queueRear] = element;
		}
	}

	/**
		Method to delete the first element of the Queue
		Precondition: A queue must exist and not empty
		PostCondition: element is deleted from the front
					   of the  Queue, if the Queue is full a
					   QueueUnderflowException is thrown
	**/
	public void dequeue()throws QueueUnderflowException
	{
		if(isEmptyQueue())
		{
			throw new QueueUnderflowException();
		}
		else
		{
			count--;
			list[queueFront] = null;
			queueFront = (queueFront + 1)% maxQueueSize;
		}
	}

	/**
	Method to output an object of the QueueArray class
	**/
	public String toString()
	{
		String str = "";
		T temp;
		for(int i = 0; i < count(); i++)
		{
			str += peekFront() + " ";
			temp = peekFront();
			dequeue();
			enqueue(temp);
		}
		return str;
	}
}