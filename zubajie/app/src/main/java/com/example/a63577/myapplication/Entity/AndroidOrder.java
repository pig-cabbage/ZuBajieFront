package com.example.a63577.myapplication.Entity;



import java.io.Serializable;
import java.util.Date;



public class AndroidOrder implements Serializable {
    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column order.order_id
     *
     * @mbg.generated
     */

    private Integer orderId;
    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column order.buyer_id
     *
     * @mbg.generated
     */
    private String title;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column order.saler_id
     *
     * @mbg.generated
     */
    private String price;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column order.time
     *
     * @mbg.generated
     */
    private Date time;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column order.order_state
     *
     * @mbg.generated
     */
    private boolean orderState;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column order.goods_id
     *
     * @mbg.generated
     */
    private Integer goodsId;

    private boolean isBorrow;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column order.buyer_score
     *
     * @mbg.generated
     */


    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column order.saler_score
     *
     * @mbg.generated
     */


    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table order
     *
     * @mbg.generated
     */
    private static final long serialVersionUID = 1L;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column order.order_id
     *
     * @return the value of order.order_id
     *
     * @mbg.generated
     */


    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column order.order_id
     *
     * @param orderId the value for order.order_id
     *
     * @mbg.generated
     */


    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column order.buyer_id
     *
     * @return the value of order.buyer_id
     *
     * @mbg.generated
     */


    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column order.buyer_id
     *
     * @param buyerId the value for order.buyer_id
     *
     * @mbg.generated
     */


    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column order.saler_id
     *
     * @return the value of order.saler_id
     *
     * @mbg.generated
     */


    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column order.saler_id
     *
     * @param salerId the value for order.saler_id
     *
     * @mbg.generated
     */


    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column order.time
     *
     * @return the value of order.time
     *
     * @mbg.generated
     */
    public Date getTime() {
        return time;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column order.time
     *
     * @param time the value for order.time
     *
     * @mbg.generated
     */
    public void setTime(Date time) {
        this.time = time;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column order.order_state
     *
     * @return the value of order.order_state
     *
     * @mbg.generated
     */
    public boolean getOrderState() {
        return orderState;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column order.order_state
     *
     * @param orderState the value for order.order_state
     *
     * @mbg.generated
     */
    public void setOrderState(boolean orderState) {
        this.orderState = orderState;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column order.goods_id
     *
     * @return the value of order.goods_id
     *
     * @mbg.generated
     */
    public Integer getGoodsId() {
        return goodsId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column order.goods_id
     *
     * @param goodsId the value for order.goods_id
     *
     * @mbg.generated
     */
    public void setGoodsId(Integer goodsId) {
        this.goodsId = goodsId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column order.buyer_score
     *
     * @return the value of order.buyer_score
     *
     * @mbg.generated
     */


    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column order.buyer_score
     *
     * @param buyerScore the value for order.buyer_score
     *
     * @mbg.generated
     */


    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column order.saler_score
     *
     * @return the value of order.saler_score
     *
     * @mbg.generated
     */


    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column order.saler_score
     *
     * @param salerScore the value for order.saler_score
     *
     * @mbg.generated
     */


    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table order
     *
     * @mbg.generated
     */
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", orderId=").append(orderId);
        sb.append(", isBorrow=").append(isBorrow);
        sb.append(", title=").append(title);
        sb.append(", price=").append(price);
        sb.append(", time=").append(time);
        sb.append(", orderState=").append(orderState);
        sb.append(", goodsId=").append(goodsId);

        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public Integer getOrderId() {
        return orderId;
    }

    public void setOrderId(Integer orderId) {
        this.orderId = orderId;
    }

    public boolean isBorrow() {
        return isBorrow;
    }

    public void setBorrow(boolean borrow) {
        isBorrow = borrow;
    }
}

