package hw3.model;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Topic {
    private int id;
    private String title;
    private String author;
    private Date createdOn;
    private Timestamp lastPost;
    private int replyCount;
    private String lastPostString;

    public Topic(int id, String title, String author) {
        this.id = id;
        this.title = title;
        this.author = author;
        this.createdOn = new Date();
        this.lastPost = new Timestamp(createdOn.getTime());
        this.replyCount = 0;
        lastPostString = timestampToString(lastPost);
    }

    public Topic(int id, String title, String author, int replyCount, Timestamp lastPost) {
        this.id = id;
        this.title = title;
        this.author = author;
        this.createdOn = new Date();
        this.lastPost = lastPost;
        this.replyCount = replyCount;
        lastPostString = timestampToString(lastPost);
    }

    public String timestampToString(Date timestamp) {
        SimpleDateFormat formatter = new SimpleDateFormat("MM/dd/yyyy hh:mmaa");
        return formatter.format(timestamp);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Date getCreatedOn() { return createdOn; }

    public void setCreatedOn(Date createdOn) { this.createdOn = createdOn; }

    public Date getLastPost() { return lastPost; }

    public void setLastPost(Timestamp lastPost) { this.lastPost = lastPost; }

    public int getReplyCount() { return replyCount; }

    public void setReplyCount(int replyCount) { this.replyCount = replyCount; }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getLastPostString() { return lastPostString; }

    public void setLastPostString(String lastPostString) { this.lastPostString = lastPostString; }

}