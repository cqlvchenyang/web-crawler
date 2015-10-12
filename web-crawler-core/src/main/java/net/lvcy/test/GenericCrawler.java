package net.lvcy.test;

import java.net.URISyntaxException;

import net.lvcy.core.Crawler;
import net.lvcy.fetcher.Fetcher;
import net.lvcy.pool.PooledUrl;
import net.lvcy.pool.impl.GeneraicUrl;
import net.lvcy.pool.impl.GenericPool;
import net.lvcy.utils.UrlRegrex;

public class GenericCrawler extends Crawler{

	public GenericCrawler() {
		urlPool=new GenericPool();
		fetcher=new Fetcher();
		UrlRegrex regrex=UrlRegrex.getInstance();
		regrex.addPositive(".*1919.*");
		visitor=new PageVisitor(regrex);
		fetcher.setUrlPool(urlPool);
		fetcher.setVisitor(visitor);
	}
	@Override
	public void addSeed(String url) {
		try {
			PooledUrl pooledUrl=new GeneraicUrl(url);
			urlPool.addUrl(pooledUrl);
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		} catch (URISyntaxException e) {
			e.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public static void main(String[] args) {
		Crawler crawler=new GenericCrawler();
		crawler.addSeed("http://www.1919.cn");
		crawler.start();
	}

}
