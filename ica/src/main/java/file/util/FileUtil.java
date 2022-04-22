package file.util;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

import org.apache.commons.codec.binary.Base64;




public class FileUtil implements Serializable{


	private static final long serialVersionUID = 8395506215049588912L;

	public static byte[] serialize(Object obj) throws IOException {
	    ByteArrayOutputStream out = new ByteArrayOutputStream();
	    ObjectOutputStream os = new ObjectOutputStream(out);
	    os.writeObject(obj);
	    return out.toByteArray();
	}
	
	public static String fileToBase64(File originalFile) {

	    String encodedBase64 = null;
	    try {
	        FileInputStream fileInputStreamReader = new FileInputStream(originalFile);
	        byte[] bytes = new byte[(int)originalFile.length()];
	        fileInputStreamReader.read(bytes);
	        encodedBase64 = new String(Base64.encodeBase64(bytes));
	    } catch (FileNotFoundException e) {
	        e.printStackTrace();
	    } catch (IOException e) {
	        e.printStackTrace();
	    }
	    
	    return encodedBase64;
	}
}
