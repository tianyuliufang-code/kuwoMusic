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
public class CommentController {
    @RequestMapping("/comment")
    public String getComment(){
        return "comment";
    }

    //加载获取歌曲最新评论
    @RequestMapping("/newComment")
    public @ResponseBody List<Comment> getNewComment(String rid) throws IOException {
        //API
        String newCommentUrl = "http://kuwo.cn/comment?type=get_comment&f=web&page=1&rows=10&digest=15&sid="+rid+"&uid=0&prod=newWeb&httpsStatus=1&reqId=1109d2a0-f2cc-11ed-b97b-b75dc8346334";
        //使用jsoup访问URL地址
        Document doc = Jsoup.connect(newCommentUrl).ignoreContentType(true).get();
        //rows
        String rows = JSONObject.parseObject(doc.text()).getString("rows");
        //数据转换为JSON
        List<Comment> comments = JSONArray.parseArray(rows,Comment.class);
        return comments;
    }


//    //最热
    @RequestMapping("/hotComment")
    public @ResponseBody List<Comment> getHotComment(String rid) throws IOException{
        String newHotCommentUrl = "http://kuwo.cn/comment?type=get_rec_comment&f=web&page=1&rows=10&digest=15&sid="+rid+"&uid=0&prod=newWeb&httpsStatus=1&reqId=10ef45c0-f2cc-11ed-b97b-b75dc8346334";
        Document doc = Jsoup.connect(newHotCommentUrl).ignoreContentType(true).get();
        String rows = JSONObject.parseObject(doc.text()).getString("rows");
        List<Comment> comments = JSONArray.parseArray(rows,Comment.class);
        return comments;
    }
}
