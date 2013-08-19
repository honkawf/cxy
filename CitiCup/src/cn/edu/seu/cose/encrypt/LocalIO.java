package cn.edu.seu.cose.encrypt;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;

import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;

public class LocalIO {

	/**
	 * @param args
	 */
	private String dirPath;
	private String filename;
	private EncryptDecryptData ed;
	
	public LocalIO(String d , String f){
		dirPath = d;
		filename = f;
		ed = new EncryptDecryptData();
		if(createDir()){
			createFile();
		}
	}
	private boolean createDir(){
		if(dirPath != null){
			File path = new File(dirPath);
			if(path.exists()){
				return true;
			}
			if(path.mkdirs() == true){
				return true;
			}
		}
		return false;
	}
	private void createFile(){
		if(filename != null){
			File file = new File(dirPath , filename);
			if(file.exists() == false){
				try{
					file.createNewFile();
				}catch(IOException e){
					e.printStackTrace();
				}
			}
		}
	}
	
	public void writefile(Local l){
		File file = new File(dirPath, filename);  
        if (false == file.isFile()) {  
            return ;  
        }  
        try {
        	LocalDES ld = new LocalDES(ed.encrypt(l.getu()) , ed.encrypt(l.getp()) , ed.encrypt(l.getg()) , ed.encrypt(l.getd()) , ed.encrypt(l.getc()) , ed.encrypt(l.getb()) , ed.encrypt(l.geta()));
            ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(file,false));  
            oos.writeObject(ld);  
            oos.flush();
            oos.close();  
        } catch (FileNotFoundException e) {  
            e.printStackTrace();  
        } catch (IOException e) {  
            e.printStackTrace();  
        } catch (InvalidKeyException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (NoSuchAlgorithmException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalBlockSizeException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (BadPaddingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (NoSuchPaddingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InvalidKeySpecException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public Local readfile(){
		File file = new File(dirPath, filename);  
        if (false == file.isFile()) {  
            return null;  
        }
        LocalDES ld = new LocalDES();
        Local l = new Local();
        try {
            ObjectInputStream ois = new ObjectInputStream(new FileInputStream(file));  
            ld = (LocalDES)ois.readObject();  
			l.setu(ed.decrypt(ld.getu()));
            l.setp(ed.decrypt(ld.getp()));
            l.setg(ed.decrypt(ld.getg()));
            l.setd(ed.decrypt(ld.getd()));
            l.setc(ed.decrypt(ld.getc()));
            l.setb(ed.decrypt(ld.getb()));
            l.seta(ed.decrypt(ld.geta()));
            ois.close();
        } catch (FileNotFoundException e) {  
            e.printStackTrace();  
        } catch (IOException e) {  
            e.printStackTrace();  
        } catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InvalidKeyException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalBlockSizeException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (BadPaddingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (NoSuchAlgorithmException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (NoSuchPaddingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InvalidKeySpecException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        return l;
	}
	
	public void revisep(String newp){
		Local l = readfile();
		l.setp(newp);
		writefile(l);
	}
	
	public void reviseg(String newg){
		Local l = readfile();
		l.setg(newg);
		writefile(l);
	}
	
	public static void main(String[] args) {
		Local l = new Local("kk" , MD5T.encodeStr("9999997777999999999") , "55545455454545" , "353468436" , "9999999" , "100" , "100");
		LocalIO lio = new LocalIO("f:\\shixun" , "local.data");
		//lio.writefile(l);
		Local x = lio.readfile();
		System.out.println(x.getu() + "\n" + x.getp() + "\n" + x.getg() + "\n" + x.getd());
		lio.revisep(MD5T.encodeStr("11111111111111111"));
		lio.reviseg("11111111111");
		x = lio.readfile();
		System.out.println(x.getu() + "\n" + x.getp() + "\n" + x.getg() + "\n" + x.getd());
	}

}
