package net.lvcy.model;

import java.util.ArrayList;

import net.lvcy.utils.UrlRegrex;

import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class Links extends ArrayList<String>{

	public void addFromDocument(Document document){
		Elements elements=document.select("a[href]");
		for (Element element : elements) {
			String link=element.attr("abs:href");
			if(!link.trim().equals("")){
				this.add(link);
			}
		}
	}
	public void addFromDocument(Document document,UrlRegrex regrex){
		if(regrex==null){
			addFromDocument(document);
			return;
		}
		Elements elements=document.select("a[href]");
		for (Element element : elements) {
			String link=element.attr("abs:href");
			if(regrex.verify(link)&&!link.equals("")){
				this.add(link);
			}
		}
	}
	
}
