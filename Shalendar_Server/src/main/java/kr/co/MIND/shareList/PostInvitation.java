package kr.co.MIND.shareList;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;

import javax.inject.Inject;

import org.json.simple.JSONObject;
import kr.co.MIND.config.*;
public class PostInvitation {

	static keyProperties key = new keyProperties();
	
	
	private static HttpURLConnection con;
    
//    -----------移댄넚 怨듭��뿉 �엳�뒗 �꽌踰꾪궎 �엯�젰�� --------------

    private static final String serverKey= key.getINVITEKEY();
    		//    -----------------------------------------------



    public static void push(ArrayList<String> deviceToken,String userName,String calName) throws MalformedURLException, ProtocolException, IOException {

        String url = "https://fcm.googleapis.com/fcm/send";
        
      
        JSONObject json = new JSONObject();
        JSONObject json2 = new JSONObject();
        json2.put("title", userName+"珥덈��옣");
        json2.put("text",calName+" 怨듭쑀 �떖�젰�뿉 珥덈��븯�뀲�뒿�땲�떎.");
        json2.put("sound", "default");
        
//        json.put("to",deviceToken);

        json.put("registration_ids",deviceToken);
        json.put("notification", json2);
       
        String temp = json.toString();
        byte[] postData = temp.getBytes(StandardCharsets.UTF_8);
        
        try {

            URL myurl = new URL(url);
            con = (HttpURLConnection) myurl.openConnection();

            
            con.setRequestMethod("POST");
            con.setDoInput(true);
            con.setRequestProperty("Authorization","key="+serverKey);
            con.setRequestProperty("Content-Type", "application/json; UTF-8");
            con.setDoOutput(true);
            try (DataOutputStream wr = new DataOutputStream(con.getOutputStream())) {
                wr.write(postData);
              
            }
        
            StringBuilder content;

            try (BufferedReader in = new BufferedReader(
                    new InputStreamReader(con.getInputStream()))) {

                String line;
                content = new StringBuilder();

                while ((line = in.readLine()) != null) {
                    content.append(line);
                    content.append(System.lineSeparator());
                }
            }

            System.out.println(content.toString());

        } finally {
            
            con.disconnect();
        }
    }
}