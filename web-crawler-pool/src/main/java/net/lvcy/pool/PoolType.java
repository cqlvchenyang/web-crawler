package net.lvcy.pool;

/**
 * url类型
 * @author 晨阳
 *
 */
public enum PoolType {

	/**
	 * 当前url已经被抓取
	 */
	FETCHED,
	/**
	 * 当前url抓取失败
	 */
	UNFETCH,
	/**
	 * 表示当前url还没有被抓取过
	 */
	NEW,
	/**
	 * 无效的url
	 */
	INVALIDATE,
	/**
	 * 状态未知
	 */
	UNKNOWN
}
