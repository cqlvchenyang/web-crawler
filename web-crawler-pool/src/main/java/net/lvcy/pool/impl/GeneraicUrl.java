package net.lvcy.pool.impl;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.concurrent.atomic.AtomicReference;
import java.util.regex.Pattern;

import net.lvcy.pool.BasePooledUrl;
import net.lvcy.pool.PoolType;
import net.lvcy.pool.PooledUrl;

public class GeneraicUrl extends BasePooledUrl{

	private final URI uri;
	private AtomicReference<PoolType> type=new AtomicReference<PoolType>();
	private volatile int weight=0;
	public GeneraicUrl(String url) throws URISyntaxException, IllegalArgumentException {
		this(url,PoolType.UNKNOWN);
	}
	public GeneraicUrl(String url,PoolType type) throws URISyntaxException,IllegalArgumentException{
		url=format(url);
		if(!validate(url)){
			throw new IllegalArgumentException("url format error");
		}
		this.uri=new URI(url);
		this.type.set(type);
	}
	/**
	 * 获取Schema
	 * @return
	 */
	public String getScheme(){
		return uri.getScheme();
	}
	/**
	 * 获取UserInfo
	 * @return
	 */
	public String getUserInfo(){
		return uri.getUserInfo();
	}
	/**
	 * 获取HostName
	 * @return
	 */
	public String getHostName(){
		return uri.getHost();
	}
	/**
	 * 获取Port
	 * @return
	 */
	public Integer getPort(){
		return uri.getPort();
	}
	/**
	 * 获取Path
	 * @return
	 */
	public String getPath(){
		return uri.getPath();
	}
	/**
	 * 获取QueryString
	 * @return
	 */
	public String getQuery(){
		return uri.getQuery();
	}
	/**
	 * 获取Fragment
	 * @return
	 */
	public String getFragment(){
		return uri.getFragment();
	}
	/**
	 * 获取Type
	 * @return
	 */
	public PoolType getType() {
		return type.get();
	}
	/**
	 * 设置type
	 * @param type
	 */
	public void setType(PoolType type) {
		this.type.set(type);
	}
	/**
	 * 获取权重
	 * @return
	 */
	public int getWeight() {
		return weight;
	}
	/**
	 * 设置权重
	 * @param weight
	 */
	public void setWeight(int weight) {
		this.weight = weight;
	}
	
	/**
	 * 格式化url
	 * @param url
	 * @return
	 */
	private String format(String url){
		url=removeLast(url);
		if(!Pattern.matches("^(http\\:\\/\\/.+$)||((https\\:\\/\\/.+$))", url)){
			return "http://"+url;
		}else {
			return url;
		}
	}
	private String removeLast(String url){
		if(url.charAt(url.length()-1)=='\\'||url.charAt(url.length()-1)=='/'){
			url=url.substring(0,url.length()-1);
			return removeLast(url);
		}else{
			return url;
		}
	}
	@Override
	public String toString() {
		return uri.toString();
	}
	@Override
	public boolean equals(Object obj) {
		PooledUrl pooledUrl=(PooledUrl)obj;
		return this.toString().equals(pooledUrl.toString());
	}
}
