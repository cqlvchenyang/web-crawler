package net.lvcy.core;

import net.lvcy.fetcher.Fetcher;
import net.lvcy.fetcher.Visitor;
import net.lvcy.pool.UrlPool;

public abstract class Crawler{
	protected Fetcher fetcher;
	protected UrlPool urlPool;
	protected Visitor visitor;
	public abstract void addSeed(String url);
	public void start(){
		if(urlPool==null){
			throw new NullPointerException("urlPool is null");
		}
		if(fetcher==null){
			throw new NullPointerException("fetcher is null");
		}
		fetcher.fetchAll();
	}
}
