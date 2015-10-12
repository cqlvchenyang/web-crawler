package net.lvcy.net;

import java.io.UnsupportedEncodingException;
import java.util.List;
import java.util.Map;

public class Response {
	private String url;
	private byte[] content;
	private String html;
	private int code;
	private String contentType;
	private Map<String, List<String>> headers;
	public int getCode() {
		return code;
	}
	public byte[] getContent() {
		if(content==null){
			content=new byte[0];
		}
		return content;
	}
	public String getHtml() {
		if(html==null){
			try {
				html=new String(content,GuessEncode.getEncode(content));
			} catch (UnsupportedEncodingException e) {
				System.err.println("UnsupportedEncodingException");
			}
		}
		return html;
	}
	public void setCode(int code) {
		this.code = code;
	}
	public void setContent(byte[] content) {
		this.content = content;
	}
	public void setHtml(String html) {
		this.html = html;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public Map<String, List<String>> getHeaders() {
		return headers;
	}
	public void setHeaders(Map<String, List<String>> headers) {
		this.headers = headers;
	}
	public String getHeader(String key){
		List<String> values=this.headers.get(key);
		if(values==null){
			return "";
		}else {
			StringBuilder builder=new StringBuilder();
			for (String value : values) {
				builder.append(value).append(" ");
			}
			return builder.toString();
		}
	}
	public String getContentType() {
		return contentType;
	}
	public void setContentType(String contentType) {
		this.contentType = contentType;
	}
}
