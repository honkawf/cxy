package cn.edu.seu.cose;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import cn.edu.seu.cose.encrypt.MD5T;
import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnFocusChangeListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;

public class LinkBankCardActivity extends Activity{
	private EditText cardNum, cardPwd, phoneNum, idCardNum;
	private TextView cardNum_label, cardPwd_label, phoneNum_label, idCardNum_label, btn_link_label;
	private Button btn_link_submit;
	private Socket Cli_Soc = null;
	private ProgressDialog pd;
	private String cardNum_content = "", cardPwd_content = "", phoneNum_content = "", idCardNum_content= "";
	private boolean cardNum_correct = false, cardPwd_correct = false, phoneNum_correct = false, idCardNum_correct = false;

	
	private Handler handler = new Handler(){
		@Override
		public void handleMessage(Message msg) {
			switch (msg.what) {
			case 0:
				pd.dismiss();
				Intent intent = new Intent();
				intent.setClass(LinkBankCardActivity.this, VarifyPatternPasswordActivity.class);
				startActivity(intent);
				LinkBankCardActivity.this.finish();
				break;
			case 1:
				pd.dismiss();
				btn_link_label.setText("绑定失败，请重新绑定");
			}
			super.handleMessage(msg);
		}
	};
	
	private static final String CARDNUM_PATTERN = "^[0-9]{19,19}$";
	private static final String CARDPWD_PATTERN = "^[0-9]{6,6}$";
	private static final String PHONENUM_PATTERN = "^[0-9]{11,11}$";
	private static final String IDCARDNUM_PATTERN = "^[0-9]{18,18}$";

	public boolean checkForm(String name, String re) {
		Pattern pattern = Pattern.compile(re);

		Matcher matcher = pattern.matcher(name);
		return matcher.matches();
	}

	private static byte[] plusHead(int length) {
		String head = Integer.toString(length);
		byte[] temp = head.getBytes();
		byte[] send = new byte[16];
		for (int i = 16 - temp.length, j = 0; j < temp.length; i++, j++) {
			send[i] = temp[j];
		}
		return send;
	}

	private static int readHead(byte[] buffer) {
		int total = 0;
		int counter = 0;
		for (counter = 0; counter < 16; counter++) {
			if (buffer[counter] != '\0')
				break;
		}
		byte[] tmp = new byte[16 - counter];
		System.arraycopy(buffer, counter, tmp, 0, 16 - counter);
		total = Integer.parseInt(new String(tmp));
		return total;

	}
	
	
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.linkbankcard);
		cardNum = (EditText) findViewById(R.id.cardNum);
		cardPwd = (EditText) findViewById(R.id.cardPwd);
		phoneNum = (EditText) findViewById(R.id.phoneNum);
		idCardNum = (EditText) findViewById(R.id.idCardNum);
		btn_link_submit = (Button) findViewById(R.id.btn_link_submit);
//		pb = (ProgressBar) findViewById(R.id.progressBar_Account);

		cardNum_label = (TextView) findViewById(R.id.cardNum_label);
		cardPwd_label = (TextView) findViewById(R.id.cardPwd_label);
		phoneNum_label = (TextView) findViewById(R.id.phoneNum_label);
		idCardNum_label = (TextView) findViewById(R.id.idCardNum_label);
		btn_link_label = (TextView) findViewById(R.id.btn_link_label);
		
		cardNum.setOnFocusChangeListener(new OnFocusChangeListener() {
			public void onFocusChange(View v, boolean hasFocus) {
				if(hasFocus){
					cardNum_label.setText("");
					cardNum_correct = false;
				}else{
					cardNum_content = cardNum.getText().toString();
					if(cardNum_content.equals(""))
						cardNum_label.setText("银行卡卡号不能为空");
					else{
						if(checkForm(cardNum_content,CARDNUM_PATTERN)){
							cardNum_correct = true;
							cardNum_label.setText(cardNum_content);
							}
						else
							cardNum_label.setText("银行卡卡号格式不正确");
//							cardNum_label.setText("ddd" + PersonInfo.localPersonInfo.getUserName());
					}
				}
			}
		});
		
		cardPwd.setOnFocusChangeListener(new OnFocusChangeListener() {
			public void onFocusChange(View v, boolean hasFocus) {
				if(hasFocus){
					cardPwd_label.setText("");
					cardPwd_correct = false;
				}else{
					cardPwd_content = cardPwd.getText().toString();
					if(cardPwd_content.equals(""))
						cardPwd_label.setText("银行卡密码不能为空");
					else{
						if(checkForm(cardPwd_content,CARDPWD_PATTERN)){
							cardPwd_correct = true;
							cardPwd_label.setText(cardPwd_content);
						}
						else
							cardPwd_label.setText("银行卡密码格式不正确");
					}
				}
			}
		});
		
		phoneNum.setOnFocusChangeListener(new OnFocusChangeListener() {
			public void onFocusChange(View v, boolean hasFocus) {
				if(hasFocus){
					phoneNum_label.setText("");
					phoneNum_correct = false;
				}else{
					phoneNum_content = phoneNum.getText().toString();
					if(phoneNum_content.equals(""))
						phoneNum_label.setText("手机号码不能为空");
					else{
						if(checkForm(phoneNum_content,PHONENUM_PATTERN)){
							phoneNum_correct = true;
							phoneNum_label.setText(phoneNum_content);
						}
						else
							phoneNum_label.setText("手机号码格式不正确");
					}
				}
			}
		});
		
		idCardNum.setOnFocusChangeListener(new OnFocusChangeListener() {
			public void onFocusChange(View v, boolean hasFocus) {
				if(hasFocus){
					idCardNum_label.setText("");
					idCardNum_correct = false;
				}else{
					idCardNum_content = idCardNum.getText().toString();
					if(idCardNum_content.equals(""))
						idCardNum_label.setText("身份证号码不能为空");
					else{
						if(checkForm(idCardNum_content,IDCARDNUM_PATTERN)){
							idCardNum_correct = true;
							idCardNum_label.setText(idCardNum_content);
						}
						else
							idCardNum_label.setText("身份证号码格式不正确");
					}
				}
			}
		});
		
		btn_link_submit.setOnClickListener(new OnClickListener() {

			public void onClick(View arg0) {
				idCardNum_content = idCardNum.getText().toString();
				cardNum_content = cardNum.getText().toString();
				cardPwd_content = cardPwd.getText().toString();
				phoneNum_content = phoneNum.getText().toString();
				
				if(checkForm(idCardNum_content,IDCARDNUM_PATTERN)){
					if(checkForm(phoneNum_content,PHONENUM_PATTERN)){
						if(checkForm(cardPwd_content,CARDPWD_PATTERN)){
							if(checkForm(cardNum_content,CARDNUM_PATTERN)){
								
								new Thread() {
									public void run() {
										try {
											String localUserName  =PersonInfo.localPersonInfo.getUserName();
											String event = "linkBankCard";
											Log.i("link","1");
											XML_Person xmlp = new XML_Person();
											
											xmlp.addPersonLinkBankCard(localUserName, cardNum_content,  phoneNum_content, idCardNum_content);
											
											String resultXML = xmlp.produceLinkBankCardXML(event);
											
											Cli_Soc = new Socket("honka.xicp.net", 30145);
											
											OutputStream out = Cli_Soc.getOutputStream();
											
											out.write(plusHead(resultXML.length()));
											
											out.write(resultXML.getBytes());
											
											
											
											
											byte[] buffer = new byte[16];
											
											InputStream in = Cli_Soc.getInputStream();
											
											in.read(buffer);
											
											int XML_length = readHead(buffer);
											
											byte[] info = new byte[XML_length];
											in.read(info);
											String checkResult = new String(info);
											checkResult = XML_Person.parseSentenceXML(new ByteArrayInputStream(info));
											
											
											if (checkResult.equals("绑定成功")) {
											
												Log.i("chris", "注册成功");
												handler.sendEmptyMessage(0);
											} else {
												Log.i("chris", "绑定失败");
												handler.sendEmptyMessage(1);
											}
											Cli_Soc.close();
										} catch (UnknownHostException e) {
											// TODO Auto-generated catch block
											e.printStackTrace();
										} catch (IOException e) {
											// TODO Auto-generated catch block
											e.printStackTrace();
										}
									}
								}.start();
								
								pd = ProgressDialog.show(LinkBankCardActivity.this,"注册", "注册中，请稍后……");
								pd.setCancelable(true);// 设置进度条是否可以按退回键取消
								pd.setCanceledOnTouchOutside(false);
								
							}else{
								cardNum_label.setText("银行卡密码格式不正确");
							}
						}else{
							cardPwd_label.setText("银行卡密码格式不正确");
						}
					}else{
						phoneNum_label.setText("手机号码格式不正确");
					}
				}else{
					idCardNum_label.setText("身份证号码格式不正确");
				}
				
								
			}
		});
		
	}	
	
}
