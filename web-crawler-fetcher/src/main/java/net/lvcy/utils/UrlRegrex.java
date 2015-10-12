package net.lvcy.utils;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;


public class UrlRegrex {

	private static UrlRegrex regrex=null;
	private List<String> positives;
	private List<String> negatives;
	private UrlRegrex(){
		this.positives=new ArrayList<String>();
		this.negatives=new ArrayList<String>();
	}
	public static UrlRegrex getInstance(){
		if(regrex==null){
			regrex=new UrlRegrex();
		}
		return regrex;
	}
	public void addPositive(String regString){
		if(positives==null){
			positives=new ArrayList<String>();
		}
		if(positives.contains(regString)){
			return;
		}
		positives.add(regString);
	}
	public void addNagetive(String regString){
		if(negatives==null){
			negatives=new ArrayList<String>();
		}
		if(negatives.contains(regString)){
			return;
		}
		negatives.add(regString);
	}
	public boolean verify(String url){
		for (String negative : negatives) {
			if(Pattern.matches(negative, url)){
				return false;
			}
		}
		if(positives==null || positives.size()==0){
			return true;
		}
		for (String positive : positives) {
			if(Pattern.matches(positive, url)){
				return true;
			}
		}
		return false;
	}
}
