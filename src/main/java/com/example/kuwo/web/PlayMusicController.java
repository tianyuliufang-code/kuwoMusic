package com.example.kuwo.web;

import com.alibaba.fastjson.JSONObject;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.IOException;

//歌曲URL
@Controller
public class PlayMusicController {
    @RequestMapping("/playMusic")
    public @ResponseBody String playMusic(String rid) throws IOException {
        String mp3Url = "http://www.kuwo.cn/api/v1/www/music/playUrl?mid="+rid+"&type=convert_url&httpsStatus=1";

        //使用jsoup访问
        Document doc = Jsoup.connect(mp3Url).ignoreContentType(true).get();
        String data = JSONObject.parseObject(doc.text()).getString("data");
        String url = JSONObject.parseObject(data).getString("url");
        System.out.println(url);
        return url;
    }
}
