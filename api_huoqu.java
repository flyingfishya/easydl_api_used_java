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
* easydl图像分类
*/
public class api_huoqu {

    /**
    * 重要提示代码中所需工具类
    * FileUtil,Base64Util,HttpUtil,GsonUtils请从
    * https://ai.baidu.com/file/658A35ABAB2D404FBF903F64D47C1F72
    * https://ai.baidu.com/file/C8D81F3301E24D2892968F09AE1AD6E2
    * https://ai.baidu.com/file/544D677F5D4E4F17B4122FBD60DB82B3
    * https://ai.baidu.com/file/470B3ACCA3FE43788B5A963BF0B625F3
    * 下载
    */
	
	private static String ImageToBase64(String imgPath) {
	    byte[] data = null;
	    // 读取图片字节数组
	    try {
	        InputStream in = new FileInputStream(imgPath);
	        data = new byte[in.available()];
	        in.read(data);
	        in.close();
	    } catch (IOException e) {
	        e.printStackTrace();
	    }
	    // 对字节数组Base64编码
	    BASE64Encoder encoder = new BASE64Encoder();
	    // 返回Base64编码过的字节数组字符串
	    return encoder.encode(Objects.requireNonNull(data));
	   // System.out.println("本地图片转换Base64:" + encoder.encode(Objects.requireNonNull(data)));
	}

	
	
	
    public static String easydlImageClassify(String tupian_address) {
        // 请求url
    	char ch = 0;
    	int n = 0;
        String url = "  ";  //api jie kou di zhi
        try {
            Map<String, Object> map = new HashMap<>();
            map.put("image", ImageToBase64(tupian_address));//map shuju
            map.put("top_num", "5");//qian ji ge jie guo

            String param = GsonUtils.toJson(map);

            // 注意这里仅为了简化编码每一次请求都去获取access_token，线上环境access_token有过期时间， 客户端可自行缓存，过期后重新获取。
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
            			 System.out.println("照片内容是	"+readString);
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