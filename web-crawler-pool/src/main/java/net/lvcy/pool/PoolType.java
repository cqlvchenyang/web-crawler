package net.lvcy.pool;

/**
 * url����
 * @author ����
 *
 */
public enum PoolType {

	/**
	 * ��ǰurl�Ѿ���ץȡ
	 */
	FETCHED,
	/**
	 * ��ǰurlץȡʧ��
	 */
	UNFETCH,
	/**
	 * ��ʾ��ǰurl��û�б�ץȡ��
	 */
	NEW,
	/**
	 * ��Ч��url
	 */
	INVALIDATE,
	/**
	 * ״̬δ֪
	 */
	UNKNOWN
}
