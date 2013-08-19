package cn.edu.seu.cose.encrypt;

import java.io.Serializable;

public class LocalDES implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private byte [] userName;
	private byte [] password;
	private byte [] gesturePwd;
	private byte [] dynamicPasswordNum;
	private byte [] cardnumber;
	private byte [] balance;
	private byte [] availablebalance;
	public LocalDES(){
	}
	public LocalDES(byte [] u , byte [] p , byte [] g , byte [] d , byte [] c , byte [] b , byte [] a){
		setu(u);
		setp(p);
		setg(g);
		setd(d);
		setc(c);
		setb(b);
		seta(a);
	}
	public void setu(byte [] u){
		userName = u;
	}
	public byte [] getu(){
		return userName;
	}
	public void setp(byte [] p){
		password = p;
	}
	public byte [] getp(){
		return password;
	}
	public void setg(byte [] g){
		gesturePwd = g;
	}
	public byte [] getg(){
		return gesturePwd;
	}
	public void setd(byte [] d){
		dynamicPasswordNum = d;
	}
	public byte [] getd(){
		return dynamicPasswordNum;
	}
	public void setc(byte [] c){
		cardnumber = c;
	}
	public byte [] getc(){
		return cardnumber;
	}
	public void setb(byte [] b){
		balance = b;
	}
	public byte [] getb(){
		return balance;
	}public void seta(byte [] a){
		availablebalance = a;
	}
	public byte [] geta(){
		return availablebalance;
	}
}
