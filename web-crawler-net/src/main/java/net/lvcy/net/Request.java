package net.lvcy.net;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class Request {

	private URL url=null;
	private RequestConfig requestConfig=null;
	public Request(String url) throws MalformedURLException {
		requestConfig=new RequestConfig();
		this.url=new URL(url);
		
	}
	public Request(String url,RequestConfig requestConfig) throws MalformedURLException{
		if(requestConfig==null){
			throw new NullPointerException("requestConfig is null");
		}
		this.requestConfig=requestConfig;
		this.url=new URL(url);
	}
	/**
	 * ��ȡ�������˵���Ӧ
	 * @return
	 * @throws Exception
	 */
	public Response getResponse() throws Exception{
		
		// TODO �˴��Ƿ���Ҫ����30X ��ת�������
		
		HttpURLConnection conn=(HttpURLConnection)url.openConnection();
		requestConfig.config(conn);
		InputStream in=null;
		try {
			in=conn.getInputStream();
		} catch (Exception e) {
			System.err.println(url.toString()+"\t connect time out");
			return null;
		}
		Response response=new Response();
		response.setCode(conn.getResponseCode()); //������Ӧ��
		ByteArrayOutputStream bos=new ByteArrayOutputStream();
		int read=-1;
		try {
			while((read=in.read())!=-1){
				bos.write(read);
			}
		} catch (Exception e) {
			System.err.println(url.toString()+"\t read time out");
		}
		
		response.setContent(bos.toByteArray());
		response.setUrl(url.toString());
		response.setHeaders(conn.getHeaderFields());
		response.setContentType(conn.getContentType());
		in.close();
		bos.close();
		return response;
	}
}
