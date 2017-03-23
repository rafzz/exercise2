package wdsr.exercise2.procon;

import java.util.ArrayList;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Task: implement Buffer interface without using any *Queue classes from java.util.concurrent package.
 * Any combination of "synchronized", *Lock, *Semaphore, *Condition, *Barrier, *Latch is allowed.
 */
public class BufferManualImpl implements Buffer {
	
	private ArrayList<Order> orderList = new ArrayList<Order>();
	
	private Lock lock = new ReentrantLock();
	final Condition notFull  = lock.newCondition(); 
	final Condition notEmpty = lock.newCondition(); 
	
	
	
	public void submitOrder(Order order) throws InterruptedException {
		// TODO
		lock.lock();
	     try {
	    	 
	    	 orderList.add(order);
	       
	       
	     } finally {
	       lock.unlock();
	     }
		
	}
	
	public Order consumeNextOrder() throws InterruptedException {
		// TODO
		return null;
	}
}
