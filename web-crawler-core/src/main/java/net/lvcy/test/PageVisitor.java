package net.lvcy.test;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;

import org.jsoup.nodes.Document;

import net.lvcy.fetcher.Visitor;
import net.lvcy.model.Links;
import net.lvcy.model.Page;
import net.lvcy.utils.UrlRegrex;

public class PageVisitor implements Visitor{

	private static int num=0;
	public PageVisitor(UrlRegrex regrex) {
		this.regrex=regrex;
	}
	public PageVisitor() {
		this.regrex=null;
	}
	private UrlRegrex regrex;
	public Links vistPageAndGetNextLinks(Page page) {
		System.out.println(++num+": "+page.getUrl());
		Links nextLinks=new Links();
		String contentType=page.getResponse().getContentType();
		if(contentType!=null && contentType.contains("text/html")){
			Document document=page.getDocument();
			if(document != null){
				nextLinks.addFromDocument(document,regrex);
			}
		}
		return nextLinks;
	}

}
