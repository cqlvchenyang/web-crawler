package net.lvcy.net;

import java.net.HttpURLConnection;
import java.net.ProtocolException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

public class RequestConfig {

	private String userAgent="Mozilla/5.0 (X11; Ubuntu; Linux x86_64; rv:36.0) Gecko/20100101 Firefox/36.0";
	private int maxRedirect=2;
	private int maxReceiveSize=1000*1000;
	private String method="GET";
	private boolean doinput=true;
	private boolean dooutput=true;
	private boolean followRedirects=false;
	private boolean useCache=false;
	private int connectTimeout=3000;
	private int readTimeout=10000;
	private Map<String, List<String>> header=null;
	/**
	 * Ĭ�Ϲ��캯��
	 */
	public RequestConfig() {
		initHeader();
	}
	/**
	 * ���캯��
	 * @param userAgent
	 */
	public RequestConfig(String userAgent){
		initHeader();
		setUserAgent(userAgent);
	}
	/**
	 * ���캯��
	 * @param userAgent
	 * @param cookie
	 */
	public RequestConfig(String userAgent,String cookie){
		initHeader();
		setUserAgent(userAgent);
		setCookie(cookie);
	}
	/**
	 * �����û�����
	 * @param userAgent
	 */
	public void setUserAgent(String userAgent) {
		this.userAgent = userAgent;
	}
	/**
	 * ����cookie
	 * @param cookie
	 */
	public void setCookie(String cookie){
		
	}
	/**
	 * ��url connection��������
	 * @param conn
	 * @throws ProtocolException 
	 */
	public void config(HttpURLConnection conn) throws ProtocolException{
		conn.setDoInput(doinput);
		conn.setDoOutput(dooutput);
		conn.setRequestMethod(method);
		conn.setInstanceFollowRedirects(followRedirects);
		conn.setConnectTimeout(connectTimeout);
		conn.setReadTimeout(readTimeout);
		Iterator<Entry<String, List<String>>> iterator=header.entrySet().iterator();
		while(iterator.hasNext()){
			Entry<String, List<String>> entry=iterator.next();
			String key=entry.getKey();
			List<String> values=entry.getValue();
			for (String value : values) {
				conn.addRequestProperty(key, value);
			}
		}
	}
	/**
	 * ��������ͷ��Ϣ
	 * @param key
	 * @param value
	 */
	public void setHeader(String key,String value){
		if(key==null){
			throw new NullPointerException("key is null");
		}
		if(value==null){
			throw new NullPointerException("value is null");
		}
		List<String> list=new ArrayList<String>();
		list.add(value);
		header.put(key, list);
	}
	/**
	 * ��ʼ�����ͷ��Ϣ������
	 */
	private void initHeader(){
		if(this.header==null){
			this.header=new HashMap<String, List<String>>();
		}
	}
	/**
	 * ��ȡ�����ת����
	 * @return
	 */
	public int getMaxRedirect() {
		return maxRedirect;
	}
	/**
	 * ���������ת����
	 * @param maxRedirect
	 */
	public void setMaxRedirect(int maxRedirect) {
		this.maxRedirect = maxRedirect;
	}
	/**
	 * ��ȡ�����ճ���
	 * @return
	 */
	public int getMaxReceiveSize() {
		return maxReceiveSize;
	}
	/**
	 * ���������ճ���
	 * @param maxReceiveSize
	 */
	public void setMaxReceiveSize(int maxReceiveSize) {
		this.maxReceiveSize = maxReceiveSize;
	}
	/**
	 * ��ȡ���󷽷�
	 * @return
	 */
	public String getMethod() {
		return method;
	}
	/**
	 * �������󷽷�
	 * @param method
	 */
	public void setMethod(String method) {
		this.method = method;
	}
	public boolean isDoinput() {
		return doinput;
	}
	/**
	 * �����Ƿ���conn������
	 * @param doinput
	 */
	public void setDoinput(boolean doinput) {
		this.doinput = doinput;
	}
	public boolean isDooutput() {
		return dooutput;
	}
	/**
	 * �����Ƿ���conn�����
	 * @param dooutput
	 */
	public void setDooutput(boolean dooutput) {
		this.dooutput = dooutput;
	}
	public boolean isFollowRedirects() {
		return followRedirects;
	}
	public void setFollowRedirects(boolean followRedirects) {
		this.followRedirects = followRedirects;
	}
	public boolean isUseCache() {
		return useCache;
	}
	public void setUseCache(boolean useCache) {
		this.useCache = useCache;
	}
	public int getConnectTimeout() {
		return connectTimeout;
	}
	public void setConnectTimeout(int connectTimeout) {
		this.connectTimeout = connectTimeout;
	}
	public int getReadTimeout() {
		return readTimeout;
	}
	public void setReadTimeout(int readTimeout) {
		this.readTimeout = readTimeout;
	}
	public Map<String, List<String>> getHeader() {
		return header;
	}
	public void setHeader(Map<String, List<String>> header) {
		this.header = header;
	}
	public String getUserAgent() {
		return userAgent;
	}
	
	
}
