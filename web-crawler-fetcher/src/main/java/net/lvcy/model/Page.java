package net.lvcy.model;

import java.net.MalformedURLException;
import java.net.URL;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import net.lvcy.net.Response;

public class Page {

	private final Response response;
	private String html;
	private Document document;
	private String url;
	private String host;
	public Page(Response response) {
		this.response=response;
	}
	public Response getResponse() {
		return response;
	}
	public String getHtml() {
		if(html==null){
			html=response.getHtml();
		}
		return html;
	}
	public String getUrl() {
		if(url==null){
			url=response.getUrl();
		}
		return url;
	}
	public String getHost() {
		if(host==null){
			URL url=null;
			try {
				url=new URL(getUrl());
			} catch (MalformedURLException e) {
				System.err.println(e.getMessage());
			}
			host=url.getHost();
		}
		return host;
	}
	public Document getDocument() {
		if(document==null){
			document=Jsoup.parse(getHtml());
		}
		return document;
	}
}
