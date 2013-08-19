package cn.edu.seu.cose.encrypt;

import java.io.Serializable;

public class Local implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * @param args
	 */
	private String userName;
	private String password;
	private String gesturePwd;
	private String dynamicPasswordNum;
	private String cardnumber;
	private String balance;
	private String availablebalance;
	public Local(){
	}
	public Local(String u , String p , String g , String d , String c , String b , String a ){
		setu(u);
		setp(p);
		setg(g);
		setd(d);
		setc(c);
		setb(b);
		seta(a);
	}
	public void setu(String u){
		userName = u;
	}
	public String getu(){
		return userName;
	}
	public void setp(String p){
		password = p;
	}
	public String getp(){
		return password;
	}
	public void setg(String g){
		gesturePwd = g;
	}
	public String getg(){
		return gesturePwd;
	}
	public void setd(String d){
		dynamicPasswordNum = d;
	}
	public String getd(){
		return dynamicPasswordNum;
	}
	public void setc(String c){
		cardnumber = c;
	}
	public String getc(){
		return cardnumber;
	}
	public void setb(String b){
		balance = b;
	}
	public String getb(){
		return balance;
	}
	public void seta(String a){
		availablebalance = a;
	}
	public String geta(){
		return availablebalance;
	}
}
