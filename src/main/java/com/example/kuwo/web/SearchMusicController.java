package com.example.kuwo.web;
import java.io.IOException;
import java.util.List;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.example.kuwo.entity.Music;
import org.jsoup.Connection;
import org.jsoup.nodes.Document;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.jsoup.Jsoup;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.IOException;

@Controller
public class SearchMusicController {
        @RequestMapping("/searchMusic") //@RequestsBody 将数据按照JSON格式进行响应到页面
        public @ResponseBody List<Music> searchMusic(String key) throws IOException {
            String searchUrl =  "http://kuwo.cn/api/www/search/searchMusicBykeyWord?key="+key+"&pn=1&rn=30&httpsStatus=1&reqId=2691a5d1-f2c2-11ed-a29f-95624f2b41af";
            Connection.Response execute = Jsoup.connect("http://kuwo.cn").execute();
            String kw_token = execute.cookie("kw_token");
            Document doc = Jsoup.connect(searchUrl)
                    .cookie("kw_token",kw_token)
                    .header("csrf",kw_token)
                    .header("Referer","http://kuwo.cn/search/list")
                    .ignoreContentType(true)
                    .get();

            String data = JSONObject.parseObject(doc.text()).getString("data");
            String list = JSONObject.parseObject(data).getString("list");

            List<Music> musics = JSONArray.parseArray(list, Music.class);

            return musics;
//            System.out.println(musics);
        }


}
