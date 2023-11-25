package br.com.aab.forkjoin.recursiveaction.exercise;

import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveTask;

public class FibonacciTask extends RecursiveTask<Integer> {
 
    private int n;
	
    public FibonacciTask(int n){
        this.n = n;
    }
 
    @Override
    protected Integer compute() {
        
    	// F(0) = F(1) = 0
    	if(n <= 1)
            return n;
 
    	FibonacciTask fib1 = new FibonacciTask(n-1);
        FibonacciTask fib2 = new FibonacciTask(n-2);
        
        fib1.fork();
        fib2.fork();
 
        return fib1.join()+ fib2.join();
    }

    public static void main(String[] args) {
        ForkJoinPool pool = new ForkJoinPool();
        System.out.println(pool.invoke(new FibonacciTask(25)));
    }
}
