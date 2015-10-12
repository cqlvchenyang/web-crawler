package net.lvcy.net;

import org.mozilla.universalchardet.UniversalDetector;

public class GuessEncode {

	public static String getEncode(byte[] content){
		UniversalDetector detector=new UniversalDetector(null);
		detector.handleData(content, 0, content.length);
		detector.dataEnd();
		String encoding=detector.getDetectedCharset();
		detector.reset();
		if(encoding==null){
			return "UTF-8";
		}else{
			return encoding;
		}
	}
}
