package SnakeThread.Login.src;


public class Buffer<E> extends Login {

	protected E[] buffer;
	protected int in = 0;
	protected int out = 0;
	protected int count = 0;
	protected int size;
	
	public Buffer(int size) {
		this.size = size;
		buffer = (E[])new Object[size];
	}

	public synchronized void put(E obj) throws InterruptedException{
		while (count == size){
			wait();
		}
		buffer[in] = obj;
		++count;
		in = (in+1) % size;
		notifyAll();
	}
	
	public synchronized E get() throws InterruptedException{
		while (count == 0){
			wait();
		}
		E obj = buffer[out];
		buffer[out] = null;
		--count;
		out = (out+1) % size;
		notifyAll();
		return(obj);
	}
}
