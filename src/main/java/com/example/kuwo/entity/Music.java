package com.example.kuwo.entity;
//Music实体类，封装实体对象
public class Music {
    public String artist;
    public String pic;
    public String rid;
    public String releaseDate;
    private Integer hasmv;
    private String album;
    private String name;

    private String songTimeMinutes;


    public String getSongTimeMinutes() {
        return songTimeMinutes;
    }

    public void setSongTimeMinutes(String songTimeMinutes) {
        this.songTimeMinutes = songTimeMinutes;
    }


    //给属性提供对应的get set 方法

    public String getArtist() {
        return artist;
    }

    public void setArtist(String artist) {
        this.artist = artist;
    }

    public String getPic() {
        return pic;
    }

    public void setPic(String pic) {
        this.pic = pic;
    }

    public String getRid() {
        return rid;
    }

    public void setRid(String rid) {
        this.rid = rid;
    }

    public String getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(String releaseDate) {
        this.releaseDate = releaseDate;
    }

    public Integer getHasmv() {
        return hasmv;
    }

    public void setHasmv(Integer hasmv) {
        this.hasmv = hasmv;
    }

    public String getAlbum() {
        return album;
    }

    public void setAlbum(String album) {
        this.album = album;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    //重写toString方法

    @Override
    public String toString() {
        return "Music{" +
                "artist='" + artist + '\'' +
                ", pic='" + pic + '\'' +
                ", rid='" + rid + '\'' +
                ", releaseDate='" + releaseDate + '\'' +
                ", hasmv=" + hasmv +
                ", album='" + album + '\'' +
                ", name='" + name + '\'' +
                '}';
    }
}
