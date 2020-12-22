package hw3.model;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Reply {
    private int id;
    private String author;
    private String content;
    private final Date createdOn;
    private final Timestamp timeStamp;
    private String postedOn;

    public Reply(int id, String author, String content) {
        this.id = id;
        this.author = author;
        this.content = content;
        this.createdOn = new Date();
        timeStamp = new Timestamp(createdOn.getTime());
        postedOn = timestampToString(timeStamp);
    }

    public Reply(int id, String author, String content, Timestamp timeStamp) {
        this.id = id;
        this.author = author;
        this.content = content;
        this.createdOn = timeStamp;
        this.timeStamp = timeStamp;
        this.postedOn = timestampToString(timeStamp);
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

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getPostedOn() {
        return postedOn;
    }

    public void setPostedOn(String postedOn) {
        this.postedOn = postedOn;
    }

    public Date getCreatedOn() { return createdOn; }

    public Timestamp getTimeStamp() { return timeStamp; }

}
