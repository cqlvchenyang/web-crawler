package net.lvcy.pool;

public interface PooledUrl {

	/**
	 * 获取Schema
	 * @return
	 */
	public String getScheme();
	/**
	 * 获取UserInfo
	 * @return
	 */
	public String getUserInfo();
	/**
	 * 获取HostName
	 * @return
	 */
	public String getHostName();
	/**
	 * 获取Port
	 * @return
	 */
	public Integer getPort();
	/**
	 * 获取Path
	 * @return
	 */
	public String getPath();
	/**
	 * 获取QueryString
	 * @return
	 */
	public String getQuery();
	/**
	 * 获取Fragment
	 * @return
	 */
	public String getFragment();
	/**
	 * 获取Type
	 * @return
	 */
	public PoolType getType();
	/**
	 * 设置type
	 * @param type
	 */
	public void setType(PoolType type);
	public String toString();
	public boolean equals(Object obj);
	
}
