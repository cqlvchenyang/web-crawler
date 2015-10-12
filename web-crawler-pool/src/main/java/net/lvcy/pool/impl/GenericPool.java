package net.lvcy.pool.impl;

import java.net.URISyntaxException;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.LinkedBlockingQueue;

import net.lvcy.pool.BaseUrlPool;
import net.lvcy.pool.PoolType;
import net.lvcy.pool.PooledUrl;
import net.lvcy.pool.UrlPool;


public class GenericPool extends BaseUrlPool{

	private final Queue<PooledUrl> urlNew;
	private final Map<String, PooledUrl> unfetched;
	private final Map<String, PooledUrl> fetched;
	private final Map<String, PooledUrl> invalidate;
	public GenericPool() {
		urlNew=new LinkedBlockingQueue<PooledUrl>();
		unfetched=new ConcurrentHashMap<String, PooledUrl>();
		fetched=new ConcurrentHashMap<String, PooledUrl>();
		invalidate=new ConcurrentHashMap<String, PooledUrl>();
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
		System.out.println("urlNew: "+urlNew.size());
		System.out.println("fetched: "+fetched.size());
		System.out.println("unfetched: "+unfetched.size());
		System.out.println("invadiate: "+invalidate.size());
		while(pooledUrl==null){
			pooledUrl=urlNew.poll();
		}
		return pooledUrl;
	}

	public void addUrl(PooledUrl url) throws Exception {
		assertClosed();
		if(url.getType()==PoolType.FETCHED){
			if(!fetched.containsKey(url.toString())){
				fetched.put(url.toString(), url);
			}
		}else if (url.getType()==PoolType.INVALIDATE) {
			if(!invalidate.containsKey(url.toString())){
				invalidate.put(url.toString(), url);
			}
		}else if (url.getType()==PoolType.NEW) {
			if(!urlNew.contains(url)){
				urlNew.add(url);
			}
		}else if (url.getType()==PoolType.UNFETCH) {
			if(!unfetched.containsKey(url.toString())){
				unfetched.put(url.toString(), url);
			}
		}else if (url.getType()==PoolType.UNKNOWN) {
			//判断当前url是否被抓取过
			if(fetched.containsKey(url.toString())){
				return;
			}
			if(unfetched.containsKey(url.toString())){
				return;
			}
			if(invalidate.containsKey(url.toString())){
				return;
			}
			if(urlNew.contains(url)){
				return;
			}
			urlNew.add(url);
		}
		else{
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
	public Queue<PooledUrl> getUrlNew() {
		return urlNew;
	}
	public static void main(String[] args) throws Exception {
		UrlPool urlPool=new GenericPool();
		PooledUrl url1=new GeneraicUrl("http://www.baidu.com");
		urlPool.addUrl(url1);
		System.out.println(((GenericPool)urlPool).getUrlNew().size());
		PooledUrl url2=new GeneraicUrl("http://www.baidu.com");
		urlPool.addUrl(url2);
		System.out.println(((GenericPool)urlPool).getUrlNew().size());
	}
}
