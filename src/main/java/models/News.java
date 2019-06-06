package models;

import java.text.SimpleDateFormat;
import java.util.Date;

public class News {
    private int id;
    private String title;
    private String content;
    private long createdat;
    private String formattedCreatedAt;

    public News(String title, String content){
        this.title=title;
        this.content=content;
        this.createdat=System.currentTimeMillis();
        setFormattedCreatedAt();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public long getCreatedat() {
        return createdat;
    }

    public void setCreatedat(long createdat) {
        this.createdat = createdat;
    }

    public String getFormattedCreatedAt() {
        Date date=new Date(createdat);
        String datePatternToUse="MM/dd/yyyy @ K:mm a";
        SimpleDateFormat sdf=new SimpleDateFormat(datePatternToUse);
        return sdf.format(date);    }

    public void setFormattedCreatedAt() {
        Date date=new Date(this.createdat);
        String datePatternToUse="MM/dd/yyyy @ K:mm a";
        SimpleDateFormat sdf= new SimpleDateFormat(datePatternToUse);
        this.formattedCreatedAt = sdf.format(date);
    }

}
