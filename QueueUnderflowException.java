//Donark Patel
//CSC 236-01
//Lab 5

public class QueueUnderflowException extends QueueException
{
	/**
		Default Constructor
		Display the QueueUnderflowException's default message.
	**/
	public QueueUnderflowException()
	{
		super("Queue Underflow");
	}

	/**
		Overloaded Constructor
		Display the QueueUnderflowException's message passed by the user.
	**/
	public QueueUnderflowException(String message)
	{
		super(message);
	}
}