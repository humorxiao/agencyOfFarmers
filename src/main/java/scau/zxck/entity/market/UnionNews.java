package scau.zxck.entity.market;

import scau.zxck.base.dao.annotation.Column;
import scau.zxck.base.dao.annotation.Table;
import scau.zxck.base.dao.entity.Unique;

import java.sql.Timestamp;

@Table(name = "union_news")
public class UnionNews extends Unique {
  @Column(name = "news_title")
  private String news_title;
  @Column(name = "news_text")
  private String news_text;
  @Column(name = "news_time")
  private String news_time;
  @Column(name = "news_mark")
  private int news_mark;
  @Column(name = "news_picture")
  private String news_picture;
  public String getNews_picture() {
    return news_picture;
  }

  public void setNews_picture(String news_picture) {
    this.news_picture = news_picture;
  }

  @Column(name = "remark")
  private String remark;

  public String getNews_title() {
    return news_title;
  }

  public void setNews_title(String news_title) {
    this.news_title = news_title;
  }

  public String getNews_text() {
    return news_text;
  }

  public void setNews_text(String news_text) {
    this.news_text = news_text;
  }

  public String getNews_time() {
    return news_time;
  }

  public void setNews_time(String news_time) {
    this.news_time = news_time;
  }

  public String getRemark() {
    return remark;
  }

  public void setRemark(String remark) {
    this.remark = remark;
  }

  public int getNews_mark() {
    return news_mark;
  }

  public void setNews_mark(int news_mark) {
    this.news_mark = news_mark;
  }

}
