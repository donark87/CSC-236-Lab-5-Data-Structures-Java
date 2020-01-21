//Donark Patel
//CSC 236-01
//Lab 5

public class QueueException extends RuntimeException
{
	/**
		Default Constructor
		Display the QueueException's default message.
	**/
	public QueueException()
	{
	}

	/**
		Overloaded Constructor
		Display the QueueException's message passed by the user.
	**/
	public QueueException(String message)
	{
		super(message);
	}
}