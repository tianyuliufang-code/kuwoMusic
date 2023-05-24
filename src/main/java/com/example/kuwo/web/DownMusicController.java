package com.example.kuwo.web;

import com.alibaba.fastjson.JSONObject;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;

//
@Controller
public class DownMusicController {
    @RequestMapping("/downMusic")
    public @ResponseBody String downMusic(String rid, String artist, String name) throws IOException {
        String mp3Url = "http://www.kuwo.cn/api/v1/www/music/playUrl?mid="+rid+"&type=convert_url&httpsStatus=1";
        //使用jsoup访问
        Document doc = Jsoup.connect(mp3Url).ignoreContentType(true).get();
        String data = JSONObject.parseObject(doc.text()).getString("data");
        String playUrl = JSONObject.parseObject(data).getString("url");
        //IO流实现歌曲下载
        URL url = new URL(playUrl);
        InputStream is = url.openStream();
        //创建输出流
//        FileOutputStream fos = new FileOutputStream(new File("./Desktop"+artist+"_"+name+".mp3"));
        FileOutputStream fos = new FileOutputStream(new File("/Users/marx/Desktop/test/"+artist+"_"+name+".mp3"));
        int len;
        byte[] bys = new byte[1024];
        while((len = is.read(bys)) != -1){
            fos.write(bys,0,len);
        }
        //直到循环结束说明文件下载成功
        return "download successfully!";
    }
}
