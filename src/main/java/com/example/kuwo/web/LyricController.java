package com.example.kuwo.web;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.example.kuwo.entity.Lyric;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.IOException;
import java.util.List;

//加载歌曲的歌词
@Controller
public class LyricController {
    @RequestMapping("/getLyric")
    public @ResponseBody List<Lyric> getLyrics(String rid) throws IOException {
        //定义出获取歌词的API
        String LyricUrl = "http://m.kuwo.cn/newh5/singles/songinfoandlrc?musicId="+rid+"&httpsStatus=1&reqId=10ed22e0-f2cc-11ed-b97b-b75dc8346334";
        //使用Jsoup去连接歌词的API
        Document doc = Jsoup.connect(LyricUrl).ignoreContentType(true).get();
        //解析获取data的数据
        String data = JSONObject.parseObject(doc.text()).getString("data");
        String lrclist = JSONObject.parseObject(data).getString("lrclist");

        List<Lyric> lyrics = JSONArray.parseArray(lrclist,Lyric.class);
        return lyrics;
    }
}
