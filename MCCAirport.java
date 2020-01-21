//Donark Patel
//CSC 236-01
//Lab 5

import java.util.Random;
import java.text.DecimalFormat;

public class MCCAirport
{
	public static void main (String[]args)
	{
		int waitTime = 0;

		final double LANDING_TIME = 3,
					 TAKE_OFF_TIME = 2,
					 LANDING_RATE = 10,
					 TAKE_OFF_RATE = 10;

		final int ITERATIONS = 1440;

		Random generator = new Random(System.currentTimeMillis());

		QueueArray<Integer> LandingPlanes = new QueueArray<Integer>(ITERATIONS);
		QueueArray<Integer> TakeOffPlanes = new QueueArray<Integer>(ITERATIONS);
		QueueArray<Integer> Runway = new QueueArray<Integer>(1);

		QueueArray<Integer> LandingLength = new QueueArray<Integer>(ITERATIONS);
		QueueArray<Integer> TakeOffLength = new QueueArray<Integer>(ITERATIONS);

		QueueArray<Integer> LandingPlaneArrivalTime = new QueueArray<Integer>(ITERATIONS);
		QueueArray<Integer> TakeOffPlaneArrivalTime = new QueueArray<Integer>(ITERATIONS);
		QueueArray<Integer> LandingPlaneQueueTime = new QueueArray<Integer>(ITERATIONS);
		QueueArray<Integer> TakeOffPlaneQueueTime = new QueueArray<Integer>(ITERATIONS);

		for(int i = 0; i < ITERATIONS; i++)
		{
			if(generator.nextDouble() < LANDING_RATE / 60)
			{
				LandingPlanes.enqueue(typeCast(LANDING_TIME));
				LandingPlaneArrivalTime.enqueue(i);
			}
			if(generator.nextDouble() < TAKE_OFF_RATE / 60)
			{
				TakeOffPlanes.enqueue(typeCast(TAKE_OFF_TIME));
				TakeOffPlaneArrivalTime.enqueue(i);
			}

			if(Runway.isEmptyQueue())
			{
				if(!LandingPlanes.isEmptyQueue())
				{
					Runway.enqueue(LandingPlanes.peekFront());
					LandingPlanes.dequeue();

					LandingPlaneQueueTime.enqueue(i - LandingPlaneArrivalTime.peekFront());
					LandingPlaneArrivalTime.dequeue();

					waitTime = Runway.peekFront();
				}
				else
				{
					if(!TakeOffPlanes.isEmptyQueue())
					{
						Runway.enqueue(TakeOffPlanes.peekFront());
						TakeOffPlanes.dequeue();

						TakeOffPlaneQueueTime.enqueue(i-TakeOffPlaneArrivalTime.peekFront());
						TakeOffPlaneArrivalTime.dequeue();

						waitTime = Runway.peekFront();
					}
				}
			}
			else
			{
				waitTime--;
				if(waitTime == 0)
				{
					Runway.dequeue();
				}

			}

			LandingLength.enqueue(LandingPlanes.count());
			TakeOffLength.enqueue(TakeOffPlanes.count());
		}
		System.out.println("Average Landing Queue Length: " + formatOutput(LandingLength));
		System.out.println("Average Take Off Queue Length: "+ formatOutput(TakeOffLength));
		System.out.println("Average Landing Queue Time: "+ formatOutput(LandingPlaneQueueTime));
		System.out.println("Average Take Off Queue Time: "+ formatOutput(TakeOffPlaneQueueTime));
		
	}

	public static String formatOutput(QueueArray<Integer> Queue)
	{
		DecimalFormat output = new DecimalFormat("###,##0.000000");
		double sum = 0;
		int temp;

		if(Queue.isEmptyQueue())
		{
			return "Queue is empty.";
		}

		for(int i = 0; i < Queue.count(); i++)
		{
			sum += Queue.peekFront();
			temp = Queue.peekFront();
			Queue.dequeue();
			Queue.enqueue(temp);
		}
		Double Avg = new Double(sum / Queue.count());
		return output.format(Avg);
	}

	public static int typeCast(double dbl)
	{
		return (int)dbl;
	}
}