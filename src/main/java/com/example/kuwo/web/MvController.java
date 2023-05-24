package com.example.kuwo.web;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.example.kuwo.entity.Comment;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.IOException;
import java.util.List;

@Controller
public class MvController {
    @RequestMapping("/mv")
    public String toMv(){
        return "mv";
    }
    @RequestMapping("/getMv")
    public @ResponseBody String getMvUrl(String rid) throws IOException {
        String mvurl =  "http://kuwo.cn/api/v1/www/music/playUrl?mid="+rid+"&type=mv&httpsStatus=1&reqId=be0a9f80-f2d0-11ed-b97b-b75dc8346334";
        Document doc = Jsoup.connect(mvurl).ignoreContentType(true).get();
        String data = JSONObject.parseObject(doc.text()).getString("data");
        String mvUrl =  JSONObject.parseObject(data).getString("url");
        return mvUrl;
    }
    @RequestMapping("/gethotComment")
    public @ResponseBody List<Comment> gethotComment(String rid) throws IOException{
        //最新评论的API地址
        String mvhotCurl = "http://kuwo.cn/comment?type=get_rec_comment&f=web&page=1&rows=5&digest=15&sid="+rid+"&uid=0&prod=newWeb&httpsStatus=1&reqId=7a60ff90-f2d0-11ed-843e-8160c46182da";
        //使用jsoup访问url地址
        Document doc =  Jsoup.connect(mvhotCurl).ignoreContentType(true).get();
        //解析的时候需要解析rows
        String rows = JSONObject.parseObject(doc.text()).getString("rows");
        //将数据转换为JSON
        List<Comment> comments = JSONArray.parseArray(rows,Comment.class);
        return comments;
    }
}
