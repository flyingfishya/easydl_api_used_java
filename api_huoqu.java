package javakeshe_121;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.*;

import javax.imageio.ImageIO;

import Decoder.BASE64Encoder;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

/**
* easydlͼ�����
*/
public class api_huoqu {

    /**
    * ��Ҫ��ʾ���������蹤����
    * FileUtil,Base64Util,HttpUtil,GsonUtils���
    * https://ai.baidu.com/file/658A35ABAB2D404FBF903F64D47C1F72
    * https://ai.baidu.com/file/C8D81F3301E24D2892968F09AE1AD6E2
    * https://ai.baidu.com/file/544D677F5D4E4F17B4122FBD60DB82B3
    * https://ai.baidu.com/file/470B3ACCA3FE43788B5A963BF0B625F3
    * ����
    */
	
	private static String ImageToBase64(String imgPath) {
	    byte[] data = null;
	    // ��ȡͼƬ�ֽ�����
	    try {
	        InputStream in = new FileInputStream(imgPath);
	        data = new byte[in.available()];
	        in.read(data);
	        in.close();
	    } catch (IOException e) {
	        e.printStackTrace();
	    }
	    // ���ֽ�����Base64����
	    BASE64Encoder encoder = new BASE64Encoder();
	    // ����Base64��������ֽ������ַ���
	    return encoder.encode(Objects.requireNonNull(data));
	   // System.out.println("����ͼƬת��Base64:" + encoder.encode(Objects.requireNonNull(data)));
	}

	
	
	
    public static String easydlImageClassify(String tupian_address) {
        // ����url
    	char ch = 0;
    	int n = 0;
        String url = "  ";  //api jie kou di zhi
        try {
            Map<String, Object> map = new HashMap<>();
            map.put("image", ImageToBase64(tupian_address));//map shuju
            map.put("top_num", "5");//qian ji ge jie guo

            String param = GsonUtils.toJson(map);

            // ע�������Ϊ�˼򻯱���ÿһ������ȥ��ȡaccess_token�����ϻ���access_token�й���ʱ�䣬 �ͻ��˿����л��棬���ں����»�ȡ��
            String accessToken = "";//huoqu jian quan ma

            String result = HttpUtil.post(url, accessToken, "application/json", param);
            System.out.println(result);
               
            //zifu chazaho
            while(ch!='}')
            {
            	ch = result.charAt(n);       
            	if(ch=='n')
            	{
            		 if(result.charAt(n+3)=='e')
            		 {
            			 String readString = "";
            			 int i = n+7;
            			 while(result.charAt(i)!='"')
            			 {
            				 readString+=result.charAt(i);
            				 i++;
            			 } 
            			 System.out.println("��Ƭ������	"+readString);
            		 }
            	}
            	n++;
            }
                   
            //jie shu
        	
            return result;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public static void main(String[] args) {
    	api_huoqu.easydlImageClassify("C:/Users/ChuanZhou/Desktop/yi.jpg");
    	//ImageToBase64("C:/Users/ChuanZhou/Desktop/xiaojiu.png");
    }
}