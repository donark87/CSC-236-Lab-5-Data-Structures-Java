//Donark Patel
//CSC 236-01
//Lab 5

public class QueueOverflowException extends QueueException
{
	/**
		Default Constructor
		Display the QueueOverflowException's default message.
	**/
	public QueueOverflowException()
	{
		super("Queue Overflow");
	}

	/**
		Overloaded Constructor
		Display the QueueOverflowException's message passed by the user.
	**/
	public QueueOverflowException(String message)
	{
		super(message);
	}
}