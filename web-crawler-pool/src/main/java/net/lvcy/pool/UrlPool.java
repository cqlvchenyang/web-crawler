package net.lvcy.pool;

public interface UrlPool {

	/**
	 * 获取一个还未被抓取的url
	 * @return
	 */
	PooledUrl getNewUrl() throws Exception;
	/**
	 * 将一个url放入到资源池中
	 * @param url
	 */
	void addUrl(PooledUrl url) throws Exception;
	/**
	 * 获取还未抓取的url的数量
	 * @return
	 */
	int getNumNew();
	/**
	 * 清理资源池，将申请的资源回收
	 * @throws Exception
	 */
	void clear() throws Exception,UnsupportedOperationException;
	/**
	 * 关闭资源池
	 */
	void close();

}
