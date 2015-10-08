package net.lvcy.pool;

import java.util.regex.Pattern;

public abstract class BasePooledUrl implements PooledUrl{
	public abstract String toString();
	/**
	 * 验证url的正确性
	 * @param url
	 * @return
	 */
	public boolean validate(String url){
		return Pattern.matches("^(([^:/?#]+):)?(//([^/?#]*))?([^?#]*)(\\?([^#]*))?(#(.*))?", url);
	}
	public abstract boolean equals(Object obj);
}
