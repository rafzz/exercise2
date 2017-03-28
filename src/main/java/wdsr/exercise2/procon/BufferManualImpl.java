package wdsr.exercise2.procon;

import java.util.Queue;
import java.util.LinkedList;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Task: implement Buffer interface without using any *Queue classes from java.util.concurrent package.
 * Any combination of "synchronized", *Lock, *Semaphore, *Condition, *Barrier, *Latch is allowed.
 */
public class BufferManualImpl implements Buffer {
	
	private Queue<Order> orderList = new LinkedList<Order>();
	
	private Lock lock = new ReentrantLock();
	private final Condition notFull  = lock.newCondition(); 
	private final Condition notEmpty = lock.newCondition(); 
	
	
	
	public void submitOrder(Order order) throws InterruptedException {
		lock.lock();
	     try {
	    	 ifFull();
	    	 orderList.add(order);
			 notEmpty.signal();
	       
	       
	     } finally {
			 lock.unlock();
	     }
		
	}
	
	public Order consumeNextOrder() throws InterruptedException {
		lock.lock();
	     try {
	    	 ifEmpty();
	    	 Order order = orderList.remove();
			 notFull.signal();
			 return order;
	       
	       
	     } finally {
			 lock.unlock();
	     }
	}
	
	private void ifFull() throws InterruptedException {
 		while (orderList.size() == 1200)
 			notFull.await();
  	}
	
	
	private void ifEmpty() throws InterruptedException {
 		while (orderList.isEmpty())
 			notEmpty.await();
  	}
}
