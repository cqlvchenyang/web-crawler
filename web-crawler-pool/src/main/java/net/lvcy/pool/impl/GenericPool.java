package net.lvcy.pool.impl;

import java.net.URISyntaxException;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.concurrent.LinkedBlockingQueue;

import net.lvcy.pool.BaseUrlPool;
import net.lvcy.pool.PoolType;
import net.lvcy.pool.PooledUrl;


public class GenericPool extends BaseUrlPool{

	private final LinkedBlockingQueue<PooledUrl> urlNew;
	private final LinkedHashMap<String, PooledUrl> unfetched;
	private final LinkedHashMap<String, PooledUrl> fetched;
	private final LinkedHashMap<String, PooledUrl> invalidate;
	public GenericPool() {
		urlNew=new LinkedBlockingQueue<PooledUrl>();
		unfetched=new LinkedHashMap<String, PooledUrl>();
		fetched=new LinkedHashMap<String, PooledUrl>();
		invalidate=new LinkedHashMap<String, PooledUrl>();
	}
	/**
	 * 添加种子
	 * @param url
	 * @throws URISyntaxException 
	 * @throws IllegalArgumentException 
	 */
	public void addSeed(String url) throws IllegalArgumentException, URISyntaxException{
		PooledUrl pooledUrl=new GeneraicUrl(url);
		urlNew.add(pooledUrl);
	}
	/**
	 * 添加种子
	 * @param url
	 * @throws URISyntaxException 
	 * @throws IllegalArgumentException 
	 */
	public void addSeed(List<String> urls) throws IllegalArgumentException, URISyntaxException{
		assertClosed();
		for (String url : urls) {
			addSeed(url);
		}
	}
	public PooledUrl getNewUrl() throws Exception {
		assertClosed();
		PooledUrl pooledUrl=null;
		while(pooledUrl==null){
			pooledUrl=urlNew.poll();
		}
		return pooledUrl;
	}

	public void addUrl(PooledUrl url) throws Exception {
		assertClosed();
		if(url.getType()==PoolType.FETCHED){
			fetched.put(url.toString(), url);
		}else if (url.getType()==PoolType.INVALIDATE) {
			invalidate.put(url.toString(), url);
		}else if (url.getType()==PoolType.NEW) {
			urlNew.add(url);
		}else if (url.getType()==PoolType.UNFETCH) {
			unfetched.put(url.toString(), url);
		}else{
			System.err.println("url type error");
		}
	}

	public void clear() throws Exception, UnsupportedOperationException {
		assertClosed();
		this.urlNew.clear();
		this.fetched.clear();
		this.unfetched.clear();
		this.invalidate.clear();
	}
}
