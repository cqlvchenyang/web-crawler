package net.lvcy.pool;

public interface UrlPool {

	/**
	 * ��ȡһ����δ��ץȡ��url
	 * @return
	 */
	PooledUrl getNewUrl() throws Exception;
	/**
	 * ��һ��url���뵽��Դ����
	 * @param url
	 */
	void addUrl(PooledUrl url) throws Exception;
	/**
	 * ��ȡ��δץȡ��url������
	 * @return
	 */
	int getNumNew();
	/**
	 * ������Դ�أ����������Դ����
	 * @throws Exception
	 */
	void clear() throws Exception,UnsupportedOperationException;
	/**
	 * �ر���Դ��
	 */
	void close();

}
