package com.seu.beyondtheboundary.charityplatform.domain;

import org.hibernate.validator.constraints.NotEmpty;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Timestamp;

@Entity  //实体
public class OrderItem {

    @Id // 主键
    @GeneratedValue(strategy = GenerationType.IDENTITY) // 自增长策略
    private Long id; // 用户的唯一标识

    @Column(name = "status")
    private Long status = 0L;  //状态,状态0表示待付款，1表示已付款

    @Column(name = "price")
    private float price;

    @Column(name = "orderId")
    private String orderId;

    @ManyToOne(cascade=CascadeType.DETACH)
    @JoinColumn(name="project_id")
    private Project project;

    @ManyToOne(cascade=CascadeType.DETACH)
    @JoinColumn(name="user_id")
    private User user;

    @org.hibernate.annotations.CreationTimestamp  // 由数据库自动创建时间
    private Timestamp createTime;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getStatus() {
        return status;
    }

    public void setStatus(Long status) {
        this.status = status;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public Project getProject() {
        return project;
    }

    public void setProject(Project project) {
        this.project = project;
    }

    public Timestamp getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Timestamp createTime) {
        this.createTime = createTime;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public OrderItem( float price, String orderId, Project project, User user) {
        this.price = price;
        this.orderId = orderId;
        this.project = project;
        this.user = user;
    }

    public OrderItem() {

    }
}