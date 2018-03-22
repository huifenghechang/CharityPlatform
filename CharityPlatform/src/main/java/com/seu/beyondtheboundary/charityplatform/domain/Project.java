package com.seu.beyondtheboundary.charityplatform.domain;

import org.hibernate.validator.constraints.NotEmpty;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.sql.Timestamp;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity  //实体
public class Project implements Serializable {

    @Id // 主键
    @GeneratedValue(strategy = GenerationType.IDENTITY) // 自增长策略
    private Long id; // 用户的唯一标识

    @Column(name = "category" ,length = 100)
    private Long category = 1L;  //类别 1 教育助学，2 医疗救助， 3 扶贫救灾 4 其他

    @Column(name = "status")
    private Long status = 1L;  //状态,状态1表示待审核，2表示已审核通过未发布，3，表示审核通过已发布，0表示已审核未通过

    @Column(name ="aimDonation")
    private float aimDonation = 1L;  //目标金额

    @Column(name ="alreadyDonation")
    private float alreadyDonation = 1L;  //目前已筹

    @Column(name = "reading")
    private Long reading = 0L;  //阅读量

    @Column(name ="donatePeopleCounter")
    private Long donatePeopleCounter = 0L;  //捐款人数


    @NotEmpty(message = "标题不能为空")


    private String title;  //项目名称

    @NotEmpty(message = "摘要不能为空")

    private String summary;  //摘要

    @Lob //大对象，映射MySQL的Long Text类型
    @Basic(fetch = FetchType.LAZY) //懒加载

    private String content;  //项目正文

    @Lob
    @Basic(fetch = FetchType.LAZY)

    private String htmlContent;  //项目html正文,将md 转换为html

    //1个求助者可对应多个活动
    @ManyToOne(cascade = CascadeType.DETACH, fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

//    @Column(nullable = false)  //映射字段不能为空
//    @org.hibernate.annotations.CreationTimestamp
    private String createdTime;  //项目时间开始

//    @Column(nullable = false)  //映射字段不能为空
//    @org.hibernate.annotations.CreationTimestamp
    private String endTime;  //项目结束开始

    private String initiator;  //发起人
    private String propagandaMap;  //首页大图

    private String pro_confirmation_link;

    public Project(String title, String summary,String content) {
        this.title = title;
        this.summary = summary;
        this.content = content;
    }

    public Project(String title, String summary,String content,String htmlContent) {
        this.title = title;
        this.summary = summary;
        this.content = content;
        this.htmlContent = htmlContent;
    }

    public Project(){

    }

//    @OneToMany(mappedBy="project",cascade=CascadeType.ALL,fetch=FetchType.LAZY)
//    private Set<OrderItem> orders = new HashSet<OrderItem>();


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getCategory() {
        return category;
    }

    public void setCategory(Long category) {
        this.category = category;
    }

    public Long getStatus() {
        return status;
    }

    public void setStatus(Long status) {
        this.status = status;
    }

    public float getAimDonation() {
        return aimDonation;
    }

    public void setAimDonation(float aimDonation) {
        this.aimDonation = aimDonation;
    }

    public float getAlreadyDonation() {
        return alreadyDonation;
    }

    public void setAlreadyDonation(float alreadyDonation) {
        this.alreadyDonation = alreadyDonation;
    }

    public Long getReading() {
        return reading;
    }

    public void setReading(Long reading) {
        this.reading = reading;
    }

    public Long getDonatePeopleCounter() {
        return donatePeopleCounter;
    }

    public void setDonatePeopleCounter(Long donatePeopleCounter) {
        this.donatePeopleCounter = donatePeopleCounter;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getSummary() {
        return summary;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getHtmlContent() {
        return htmlContent;
    }

    public void setHtmlContent(String htmlContent) {
        this.htmlContent = htmlContent;
    }

    public String getCreatedTime() {
        return createdTime;
    }

    public void setCreatedTime(String createdTime) {
        this.createdTime = createdTime;
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }

    public String getInitiator() {
        return initiator;
    }

    public void setInitiator(String initiator) {
        this.initiator = initiator;
    }

    public String getPropagandaMap() {
        return propagandaMap;
    }

    public String getPro_confirmation_link() {
        return pro_confirmation_link;
    }

    public void setPro_confirmation_link(String pro_confirmation_link) {
        this.pro_confirmation_link = pro_confirmation_link;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public void setPropagandaMap(String propagandaMap) {
        this.propagandaMap = propagandaMap;
    }
}