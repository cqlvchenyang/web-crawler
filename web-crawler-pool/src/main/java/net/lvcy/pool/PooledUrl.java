package net.lvcy.pool;

public interface PooledUrl {

	/**
	 * ��ȡSchema
	 * @return
	 */
	public String getScheme();
	/**
	 * ��ȡUserInfo
	 * @return
	 */
	public String getUserInfo();
	/**
	 * ��ȡHostName
	 * @return
	 */
	public String getHostName();
	/**
	 * ��ȡPort
	 * @return
	 */
	public Integer getPort();
	/**
	 * ��ȡPath
	 * @return
	 */
	public String getPath();
	/**
	 * ��ȡQueryString
	 * @return
	 */
	public String getQuery();
	/**
	 * ��ȡFragment
	 * @return
	 */
	public String getFragment();
	/**
	 * ��ȡType
	 * @return
	 */
	public PoolType getType();
	/**
	 * ����type
	 * @param type
	 */
	public void setType(PoolType type);
	public String toString();
	public boolean equals(Object obj);
	
}
