package cn.edu.seu.cose;

import java.io.IOException;
import java.io.OutputStream;
import java.net.Socket;
import java.net.UnknownHostException;
import java.sql.SQLException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import cn.edu.seu.cose.R;
import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnFocusChangeListener;
import android.view.View.OnKeyListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class RegisterActivity extends Activity{
	
	private EditText account, pwd1, pwd2, realName;
	private TextView pwd1_label, pwd2_label, account_label, realName_label;
	private Button button;
	String pwd1_content = "", pwd2_content = "", account_content = "";
	boolean account_correct = false;
	boolean pwd_correct = false;
	
    private static final String USERNAME_PATTERN = "^[a-zA-Z0-9_]{6,15}$";
    
    public boolean checkForm(String name){
        Pattern pattern = Pattern.compile(USERNAME_PATTERN);
    	
    	Matcher matcher = pattern.matcher(name);
    	return matcher.matches();
    }
	
	public void onCreate(Bundle savedInstanceState){
		super.onCreate(savedInstanceState);
		setContentView(R.layout.register);
		account = (EditText) findViewById(R.id.account);
		pwd1 = (EditText) findViewById(R.id.password1);
		pwd2 = (EditText) findViewById(R.id.password2);
		realName = (EditText) findViewById(R.id.realName);
		button = (Button) findViewById(R.id.submit);
		
		pwd1_label = (TextView) findViewById(R.id.pwd1_label);
		pwd2_label = (TextView) findViewById(R.id.pwd2_label);
		account_label = (TextView) findViewById(R.id.account_label);
		realName_label = (TextView) findViewById(R.id.realName_label);
		

		
		
		
		//按键事件
		account.setOnKeyListener(new OnKeyListener() {
			public boolean onKey(View v, int keyCode, KeyEvent event){
				pwd1.setText("");
//				Toast.makeText(getApplicationContext(), "account1111", 1000).show();
				return false;
			}
		});
		
		pwd1.setOnKeyListener(new OnKeyListener() {
			public boolean onKey(View v, int keyCode, KeyEvent event){
				pwd1.setText("");
//				Toast.makeText(getApplicationContext(), "pw11111", 1000).show();
				return false;
			}
		});
		
		pwd2.setOnKeyListener(new OnKeyListener() {
			public boolean onKey(View v, int keyCode, KeyEvent event){
				pwd1.setText("");
//				Toast.makeText(getApplicationContext(), "pwd21111", 1000).show();
				return false;
			}
		});
		
		realName.setOnKeyListener(new OnKeyListener() {
			public boolean onKey(View v, int keyCode, KeyEvent event){
				pwd1.setText("");
//				Toast.makeText(getApplicationContext(), "pwd21111", 1000).show();
				return false;
			}
		});
		
		account.setOnFocusChangeListener(new OnFocusChangeListener(){
			public void onFocusChange(View v, boolean hasFocus) {
				if(hasFocus){
					account_label.setText("");
				}else{
					if(account.getText().toString().equals("")){
						account_label.setText("用户名不能为空");
					}else{
						account_content = account.getText().toString();
						if(checkForm(account_content)){
							String event = "checkAccount";
							XML_Person xmlp = new XML_Person();
							xmlp.addPersonAccount(account_content);
							String resultXML = xmlp.producePersonXML(event);
							pwd2_label.setText("ddd" + resultXML);
							try {
								pwd1_label.setText("fdsf");
								Socket Cli_Soc = new Socket("honka.xicp.net",30145);
								OutputStream out = Cli_Soc.getOutputStream();
								out.write(resultXML.getBytes());
								Cli_Soc.close();
							} catch (UnknownHostException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							} catch (IOException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
							
							account_correct  =true;
							
						}else
							account_label.setText("用户名只能包含大小写字母、数字和下划线");
					}
				}
			}
			
		});
		
		realName.setOnFocusChangeListener(new OnFocusChangeListener(){
			public void onFocusChange(View v, boolean hasFocus) {
				if(hasFocus){
					realName_label.setText("");
				}else{
					if(realName.getText().toString().equals("")){
						realName_label.setText("真实姓名不能为空");
					}
				}
			}
			
		});
		
		pwd1.setOnFocusChangeListener(new OnFocusChangeListener(){
			public void onFocusChange(View v, boolean hasFocus) {
				if(hasFocus){
					pwd1_label.setText("");
				}else{
					if(pwd1.getText().toString().equals("")){
						pwd1_label.setText("密码不能为空");
					}else{
						pwd1_content = pwd1.getText().toString();
						
						if(checkForm(pwd1_content))
							pwd_correct  =true;
						else
							pwd1_label.setText("密码只能包含大小写字母、数字和下划线");
						
						if(pwd1_content.equals(pwd2_content)){
							pwd_correct = true;
							pwd1_label.setText("");
							pwd2_label.setText("");
						}
							
					}
				}
			}
			
		});
		
		pwd2.setOnFocusChangeListener(new OnFocusChangeListener(){
			public void onFocusChange(View v, boolean hasFocus) {
				if(hasFocus){
					pwd2_label.setText("");
				}else{
					if(pwd2.getText().toString().equals("")){
						pwd2_label.setText("密码不能为空");		
					}else{
						pwd2_content = pwd2.getText().toString();
						if(!pwd1_content.equals(pwd2_content)){
							pwd2_label.setText("两次密码不一致，请重新输入");
						}else{
							if(checkForm(pwd2_content))
								pwd_correct  =true; //gaigaigiagaigaigaigaigaigai
							else
								pwd2_label.setText("密码只能包含大小写字母、数字和下划线");
						}
					}
				}
			}
			
		});
		
		
		//button
		button.setOnClickListener(new OnClickListener(){
			
			public void onClick(View arg0){
	
					
				}
				
			
			
		});
	}
	

}
