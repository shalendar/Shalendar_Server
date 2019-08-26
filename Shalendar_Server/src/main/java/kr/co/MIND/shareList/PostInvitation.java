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

import org.json.simple.JSONObject;

public class PostInvitation {

    private static HttpURLConnection con;

    
//    -----------카톡 공지에 있는 서버키 입력란 --------------
    private static final String serverKey="AAAAhPVesjc:APA91bEDuSeZgWdX8-ATS97pYEWSZH68oJ4F2FHlUCfJVmp7lNXMIrwhdaAJwtSqaspkLgrmADQdyv_PF3V1Oty_pRnO6e5pL4aeff70ve7JuB6tp7QVCTgUS_3bJ_5JqUvOpTVd9n4Y";
//    -----------------------------------------------



    public static void push(ArrayList<String> deviceToken,String userName,String calName) throws MalformedURLException, ProtocolException, IOException {

        String url = "https://fcm.googleapis.com/fcm/send";
        
      
        JSONObject json = new JSONObject();
        JSONObject json2 = new JSONObject();
        json2.put("title", userName+"초대장");
        json2.put("text",calName+" 공유 달력에 초대하셨습니다.");
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