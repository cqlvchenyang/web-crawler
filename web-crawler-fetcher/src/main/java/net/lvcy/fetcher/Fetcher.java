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
	 * ֹͣҳ���ץȡ
	 */
	public void stop(){
		running=false;
	}
	/**
	 * ����url��Դ��
	 * @param urlPool
	 */
	public void setUrlPool(UrlPool urlPool) {
		this.urlPool = urlPool;
	}
	/**
	 * �ж�ץȡ���Ƿ�������
	 * @return
	 */
	public boolean isRunning() {
		return running;
	}
	/**
	 * ��ȡurl��Դ��
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
				//����pageҳ��
				Page page=new Page(response);
				//����ҳ��Ԫ��
				Links links=visitor.vistPageAndGetNextLinks(page);
				//������µ�url����Դ��
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
		 * ����Դ���л�ȡһ��url
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
