package net.lvcy.fetcher;

import java.net.MalformedURLException;
import java.net.URISyntaxException;

import net.lvcy.model.Links;
import net.lvcy.model.Page;
import net.lvcy.net.Request;
import net.lvcy.net.Response;
import net.lvcy.pool.PoolType;
import net.lvcy.pool.PooledUrl;
import net.lvcy.pool.UrlPool;
import net.lvcy.pool.impl.GeneraicUrl;

public class Fetcher {

	private UrlPool urlPool;
	private Visitor visitor;
	private int threadNum=50;
	private boolean running=true;
	/**
	 * 停止页面的抓取
	 */
	public void stop(){
		running=false;
	}
	/**
	 * 设置url资源池
	 * @param urlPool
	 */
	public void setUrlPool(UrlPool urlPool) {
		this.urlPool = urlPool;
	}
	/**
	 * 判断抓取器是否在运行
	 * @return
	 */
	public boolean isRunning() {
		return running;
	}
	/**
	 * 获取url资源池
	 * @return
	 */
	public UrlPool getUrlPool() {
		return urlPool;
	}
	public int getThreadNum() {
		return threadNum;
	}
	public void setThreadNum(int threadNum) {
		this.threadNum = threadNum;
	}
	public Visitor getVisitor() {
		return visitor;
	}
	public void setVisitor(Visitor visitor) {
		this.visitor = visitor;
	}
	public void fetchAll(){
		Thread[] threads=new Thread[threadNum];
		for(int i=0;i<threadNum;i++){
			threads[i]=new Thread(new FetchThread());
			threads[i].start();
		}
	}
	private class FetchThread extends Thread{
		@Override
		public void run() {
			while(running){
				PooledUrl pooledUrl=getFetchItem();
				if(pooledUrl==null){
					System.err.println("url is null");
					continue;
				}
				String url=pooledUrl.toString();
				Request request=null;
				Response response=null;
				try {
					request=new Request(url);
				} catch (MalformedURLException e) {
					System.err.println("initial request faild");
					continue;
				}
				try {
					response=request.getResponse();
				} catch (Exception e) {
					e.printStackTrace();
				}
				if(response==null){
					System.err.println("response is null");
					continue;
				}
				pooledUrl.setType(PoolType.FETCHED);
				try {
					urlPool.addUrl(pooledUrl);
				} catch (Exception e) {
					System.err.println("add url error");
				}
				//生成page页面
				Page page=new Page(response);
				//访问页面元素
				Links links=visitor.vistPageAndGetNextLinks(page);
				//添加最新的url到资源池
				for (String link : links) {
					try {
						PooledUrl purl=new GeneraicUrl(link);
						urlPool.addUrl(purl);
					} catch (IllegalArgumentException e) {
						System.out.println("IllegalArgumentException");
					} catch (URISyntaxException e) {
						System.err.println("URISyntaxException");
					} catch (Exception e) {
						e.printStackTrace();
						//System.err.println("Exception");
					}
				}
			}
			// TODO Auto-generated method stub
			super.run();
		}
		/**
		 * 从资源池中获取一个url
		 * @return
		 */
		private PooledUrl getFetchItem(){
			try {
				return urlPool.getNewUrl();
			} catch (Exception e) {
				System.err.println("get item faild");
				return null;
			}
		}
	}
}
