package net.lvcy.pool;

import java.util.concurrent.atomic.AtomicBoolean;

public abstract class BaseUrlPool implements UrlPool{

	private final AtomicBoolean closed=new AtomicBoolean(false);
	public int getNumNew() {
		return -1;
	}
	public void close() {
		closed.set(true);
	}
	public boolean isClosed(){
		return closed.get();
	}
	public void assertClosed() throws IllegalStateException{
		if(isClosed())
			throw new IllegalStateException("pool is closed");
	}
}
