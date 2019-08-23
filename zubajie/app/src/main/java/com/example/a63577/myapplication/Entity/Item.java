package com.example.a63577.myapplication.Entity;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

public class Item implements Serializable {
    private int  goodId;//正数表示lendGood，负数表示borrowGood

    private String title;

    private String description;

    private int userId;

    private Date createTime;

    private int validity;

    private List<String> imageList;

    private String userImage;

    private String tag;

    private int viewCount;

    private boolean isBorrow;

    private String price;
    private static final long serialVersionUID = 1L;

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", goodId=").append(goodId);
        sb.append(", title=").append(title);
        sb.append(", description=").append(description);
        sb.append(", userId=").append(userId);
        sb.append(", createTime=").append(createTime);
        sb.append(", validity=").append(validity);
        sb.append(", imageList=").append(imageList);
        sb.append(", createTime=").append(createTime);
        sb.append(", validity=").append(validity);
        sb.append(", imageList=").append(imageList);
        sb.append(", userImage=").append(userImage);
        sb.append(", tag=").append(tag);
        sb.append(",viewCount=").append(viewCount);
        sb.append(", isBorrow=").append(isBorrow);
        sb.append(", price=").append(price);

        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }








    public int getGoodId() {
        return goodId;
    }

    public void setGoodId(int goodId) {
        this.goodId = goodId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }



    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public int getValidity() {
        return validity;
    }

    public void setValidity(int validity) {
        this.validity = validity;
    }

    public List<String> getImageList() {
        return imageList;
    }

    public void setImageList(List<String> imageList) {
        this.imageList = imageList;
    }

    public String getUserImage() {
        return userImage;
    }

    public void setUserImage(String userImage) {
        this.userImage = userImage;
    }

    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }

    public int getViewCount() {
        return viewCount;
    }

    public void setViewCount(int viewCount) {
        this.viewCount = viewCount;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public boolean isBorrow() {
        return isBorrow;
    }

    public void setBorrow(boolean borrow) {
        isBorrow = borrow;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }
}
