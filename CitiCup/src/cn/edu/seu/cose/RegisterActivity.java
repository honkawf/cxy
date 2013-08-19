package cn.edu.seu.cose;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.net.UnknownHostException;
import java.sql.SQLException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import cn.edu.seu.cose.R;
import cn.edu.seu.cose.bluetooth.BluetoothOperation;
import cn.edu.seu.cose.encrypt.MD5T;
import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnFocusChangeListener;
import android.view.View.OnKeyListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

public class RegisterActivity extends Activity {

	private EditText account, pwd1, pwd2, realName;
	private TextView pwd1_label, pwd2_label, account_label, realName_label,
			button_label;
	private Button button;
	String pwd1_content = "", pwd2_content = "", account_content = "",
			realName_content = "";
	boolean account_correct = false;
	boolean pwd_correct = false;
	private Socket Cli_Soc = null;
	private ProgressDialog pd;
	private String bluetoothMac;
	private ProgressBar pb;
	private int XML_length1,XML_length2;

	private Handler handler = new Handler() {
		@Override
		public void handleMessage(Message msg) {
			switch (msg.what) {
			case 0:
				account_label.setText("用户名可用");
				pb.setVisibility(View.INVISIBLE);
				break;
			case 1:
				account_label.setText("用户名不可用");
				pb.setVisibility(View.INVISIBLE);
				break;
			case 2:
				pd.dismiss();
				Intent intent = new Intent();
				intent.setClass(RegisterActivity.this, SetFirstActivity.class);
				startActivity(intent);
				RegisterActivity.this.finish();
				break;
			case 3:
				account_label.setText("注册失败，请重试");
				break;
			}
			super.handleMessage(msg);
		}
	};

	private static final String USERNAME_PATTERN = "^[a-zA-Z0-9_]{6,15}$";
	private static final String REALNAME_PATTERN = "^[a-zA-Z]{6,15}$";

	public boolean checkForm(String name) {
		Pattern pattern = Pattern.compile(USERNAME_PATTERN);

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
		setContentView(R.layout.register);
		account = (EditText) findViewById(R.id.account);
		pwd1 = (EditText) findViewById(R.id.password1);
		pwd2 = (EditText) findViewById(R.id.password2);
		realName = (EditText) findViewById(R.id.realName);
		button = (Button) findViewById(R.id.submit);
		pb = (ProgressBar) findViewById(R.id.progressBar_Account);

		pwd1_label = (TextView) findViewById(R.id.pwd1_label);
		pwd2_label = (TextView) findViewById(R.id.pwd2_label);
		account_label = (TextView) findViewById(R.id.account_label);
		realName_label = (TextView) findViewById(R.id.realName_label);
		button_label = (TextView) findViewById(R.id.button_label);

		// �����¼�
		account.setOnKeyListener(new OnKeyListener() {
			public boolean onKey(View v, int keyCode, KeyEvent event) {
//				account.setText("");
				return false;
			}
		});

		pwd1.setOnKeyListener(new OnKeyListener() {
			public boolean onKey(View v, int keyCode, KeyEvent event) {
//				pwd1.setText("");
				return false;
			}
		});

		pwd2.setOnKeyListener(new OnKeyListener() {
			public boolean onKey(View v, int keyCode, KeyEvent event) {
//				pwd2.setText("");
				return false;
			}
		});

		realName.setOnKeyListener(new OnKeyListener() {
			public boolean onKey(View v, int keyCode, KeyEvent event) {
//				realName.setText("");
				return false;
			}
		});

		account.setOnFocusChangeListener(new OnFocusChangeListener() {
			public void onFocusChange(View v, boolean hasFocus) {
				if (hasFocus) {
					account_label.setText("");
					
					
				} else {
					if (account.getText().toString().equals("")) {
						account_label.setText("用户名不能为空");
						account_correct = false;
					} else {
						account_content = account.getText().toString();
						if (checkForm(account_content)) {
							Log.i("chris", "111");
							
							pb.setVisibility(View.VISIBLE);

							new Thread() {
								public void run() {
									Log.i("chris","1");
									String event = "checkAccount";
									Log.i("chris","12");
									XML_Person xmlp = new XML_Person();
									Log.i("chris","13");
									xmlp.addPersonUserName(account_content);
									Log.i("chris","14");
									String resultXML = xmlp.produceUserNameXML(event);
									Log.i("chris","15");
									try {
										Log.i("chris","16");
										Cli_Soc = new Socket("honka.xicp.net",30145);
										Log.i("chris","17");
										OutputStream out = Cli_Soc.getOutputStream();
										Log.i("chris","18");
										out.write(plusHead(resultXML.length()));
										Log.i("chris","19");
										out.write(resultXML.getBytes());
										Log.i("chris","110");
										
										byte[] buffer = new byte[16];
										Log.i("chris","111");
										InputStream in = Cli_Soc.getInputStream();
										Log.i("chris","112");
										in.read(buffer);
										Log.i("chris","113");
										XML_length1 = readHead(buffer);
										Log.i("chris","114");
										byte[] info = new byte[XML_length1];
										Log.i("chris","115");
										in.read(info);
										Log.i("chris","116");
										String checkResult = new String(info);
										Log.i("chris","117");
										checkResult = XML_Person.parseSentenceXML(new ByteArrayInputStream(info));
										Log.i("chris","118");
										
										if (checkResult.equals("可以使用")) {
											Log.i("chris", "keyi");
											handler.sendEmptyMessage(0);
										} else {
											Log.i("chris", "bukeyi");
											handler.sendEmptyMessage(1);
										}
										Log.i("chris", checkResult);

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

							account_correct = true;

						} else
							account_label.setText("用户名只能包含大小写字母、数字和下划线");
					}
				}
			}

		});

		pwd1.setOnFocusChangeListener(new OnFocusChangeListener() {
			public void onFocusChange(View v, boolean hasFocus) {
				if (hasFocus) {
					pwd1_label.setText("");
				} else {
					if (pwd1.getText().toString().equals("")) {
						pwd1_label.setText("密码不能为空");
					} else {
						pwd1_content = pwd1.getText().toString();

						if (checkForm(pwd1_content)) {
						} else {
							pwd1_label.setText("密码只能包含大小写字母、数字和下划线");
							pwd_correct = false;
						}

						if (pwd1_content.equals(pwd2_content)) {
							pwd_correct = true;
							pwd1_label.setText("");
							pwd2_label.setText("");
						} else {
							pwd_correct = false;
						}

					}
				}
			}

		});

		pwd2.setOnFocusChangeListener(new OnFocusChangeListener() {
			public void onFocusChange(View v, boolean hasFocus) {
				if (hasFocus) {
					pwd2_label.setText("");
				} else {
					if (pwd2.getText().toString().equals("")) {
						pwd2_label.setText("密码不能为空");
					} else {
						pwd2_content = pwd2.getText().toString();
						if (!pwd1_content.equals(pwd2_content)) {
							pwd2_label.setText("两次密码不一致，请重新输入");
							pwd_correct = false;
						} else {
							if (checkForm(pwd2_content)) {
								pwd_correct = true;
							} else {
								pwd2_label.setText("密码只能包含大小写字母、数字和下划线");
								pwd_correct = false;
							}

						}
					}
				}
			}

		});

		realName.setOnFocusChangeListener(new OnFocusChangeListener() {
			public void onFocusChange(View v, boolean hasFocus) {
				if (hasFocus) {
					realName_label.setText("");
				}

			}

		});

		// button
		button.setOnClickListener(new OnClickListener() {

			public void onClick(View arg0) {
				button.setFocusable(true);
				button.setFocusableInTouchMode(true);
				
				realName_content = realName.getText().toString();
				if (realName_content.equals("")) {
					realName_label.setText("真实姓名不能为空");
				} else {
					
					if (checkForm(realName_content)) {
						realName_label.setText("");
						
						if (account_correct && pwd_correct) {
							Log.i("point","1");
							bluetoothMac = BluetoothOperation.getLocalMac().replaceAll(":","");
							Log.i("point",bluetoothMac);
//							replaceAll(":","");
							PersonInfo.localPersonInfo.setUserName(account_content);
							
							new Thread() {
								public void run() {
									try {
										Log.i("point","12");
										String event = "register";
										Log.i("point","13");
										XML_Person xmlp = new XML_Person();
										Log.i("point","14");
										pwd1_content = MD5T.encodeStr(pwd1_content);
										Log.i("point","15");
										xmlp.addPersonRegister(account_content,pwd1_content, realName_content,bluetoothMac);
										Log.i("point","16");
										String resultXML = xmlp.produceRegisterXML(event);
										Log.i("point","17");
	//									button_label.setText(resultXML);
										Log.i("point","18");
										Socket Cli_Soc = new Socket("honka.xicp.net", 30145);
										Log.i("point","19");
										OutputStream out = Cli_Soc.getOutputStream();
										Log.i("point","111");
										out.write(plusHead(resultXML.length()));
										Log.i("point","112");
										out.write(resultXML.getBytes());
										Log.i("point","113");
										
										
										byte[] buffer = new byte[16];
										Log.i("point","114");
										InputStream in = Cli_Soc.getInputStream();
										Log.i("point","115");
										in.read(buffer);
										Log.i("point","116");
										XML_length2 = readHead(buffer);
										Log.i("point","117");
										byte[] info = new byte[XML_length2];
										Log.i("point","118");
										in.read(info);
										Log.i("point","119");
										String checkResult = new String(info);
										Log.i("point","120");
										checkResult = XML_Person.parseSentenceXML(new ByteArrayInputStream(info));
										Log.i("point","121");
										
										
										if (checkResult.equals("注册成功")) {
										
											Log.i("chris", "注册成功");
											handler.sendEmptyMessage(2);
										} else {
											Log.i("chris", "注册失败");
											handler.sendEmptyMessage(3);
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

							pd = ProgressDialog.show(RegisterActivity.this,
									"注册", "注册中，请稍后……");
							pd.setCancelable(true);// 设置进度条是否可以按退回键取消
							pd.setCanceledOnTouchOutside(false);

						} else {
							button_label.setText("请确认输入是否正确");
						}
					} else {
						realName_label.setText("真实姓名格式不正确");
					}

				}

			}

		});
	}

}
