package com.coweii.tensquare_base.polo;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

@Entity
@Table(name="tb_label")
public class Label implements Serializable {
    @Id
    private String id;
    private String labelname;
    private String state;
    private int count;
    private int fans;
    private String recommend;

    public Label() {
    }

    public Label(String id, String labelname, String state, int count, int fans, String recommend) {
        this.id = id;
        this.labelname = labelname;
        this.state = state;
        this.count = count;
        this.fans = fans;
        this.recommend = recommend;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getLabelname() {
        return labelname;
    }

    public void setLabelname(String labelname) {
        this.labelname = labelname;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public int getFans() {
        return fans;
    }

    public void setFans(int fans) {
        this.fans = fans;
    }

    public String getRecommend() {
        return recommend;
    }

    public void setRecommend(String recommend) {
        this.recommend = recommend;
    }
}
