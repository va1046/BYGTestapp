package com.example.vamshi.bygtestapp.Model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * Created by vamshi on 06-07-2016.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class BlogPost {

    String title;
    String url;
    String by;
//    @JsonProperty Integer descendants;
//    @JsonProperty Long id;
//    @JsonProperty String type;
//    @JsonProperty Long time;
//    @JsonProperty List<Object> kids;
//    @JsonProperty Integer score;

    public BlogPost() {
    }

    //    public BlogPost(String url, String title) {
//        this.url = url;
//        Title = title;
//    }


    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getBy() {
        return by;
    }

    public void setBy(String by) {
        this.by = by;
    }

//    public Integer getDescendants() {
//        return descendants;
//    }
//
//    public void setDescendants(Integer descendants) {
//        this.descendants = descendants;
//    }
//
//    public Long getId() {
//        return id;
//    }
//
//    public void setId(Long id) {
//        this.id = id;
//    }
//
//    public String getType() {
//        return type;
//    }
//
//    public void setType(String type) {
//        this.type = type;
//    }
//
//    public Long getTime() {
//        return time;
//    }
//
//    public void setTime(Long time) {
//        this.time = time;
//    }
//
//    public List<Object> getKids() {
//        return kids;
//    }
//
//    public void setKids(List<Object> kids) {
//        this.kids = kids;
//    }
//
//    public Integer getScore() {
//        return score;
//    }
//
//    public void setScore(Integer score) {
//        this.score = score;
//    }
}
