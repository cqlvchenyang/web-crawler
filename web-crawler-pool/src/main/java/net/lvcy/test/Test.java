package net.lvcy.test;

import java.net.URISyntaxException;

import net.lvcy.pool.impl.GenericPool;

public class Test {

	public static void main(String[] args) throws Exception {
		GenericPool pool=new GenericPool();
		pool.addSeed("www.1919.com");
		System.out.println(pool.getNewUrl());
	}
}
