package wdsr.exercise2.startthread;

import java.util.ArrayList;
import java.util.Collection;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Future;

public class BusinessServiceWithCallable {
	private final ExecutorService executorService;
	private final NumericHelper helper;

	public BusinessServiceWithCallable(ExecutorService executorService, NumericHelper helper) {
		this.executorService = executorService;
		this.helper = helper;
	}

	/**
	 * Calculates a sum of 100 random numbers. Random numbers are returned by
	 * helper.nextRandom method. Each random number is calculated
	 * asynchronously.
	 * 
	 * @return sum of 100 random numbers.
	 */
	public long sumOfRandomInts() throws InterruptedException, ExecutionException {
		long result = 0;

		ArrayList<Future<Integer>> futureColection = new ArrayList<>();
		

		for (int i = 0; i < 100; i++) {
			
			Future<Integer> future = executorService.submit(() -> {

				return helper.nextRandom();

			});
			
			futureColection.add(future);
			
			
			
		}

		for (Future<Integer> future : futureColection) {

			result += future.get();

		}


		return result;
		
	}
}
