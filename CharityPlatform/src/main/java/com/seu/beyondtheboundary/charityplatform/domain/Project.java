package com.seu.beyondtheboundary.charityplatform.domain;

import org.hibernate.validator.constraints.NotEmpty;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.sql.Timestamp;
import java.util.HashSet;
import java.util.Set;

@Entity  //实体
public class Project implements Serializable {

    @ManyToMany(mappedBy = "projectList")
    private Set<User> userSet = new HashSet<User>();

    @Id // 主键
    @GeneratedValue(strategy = GenerationType.IDENTITY) // 自增长策略
    private Long id; // 用户的唯一标识

    @Column(name = "category" ,length = 100)
    private Long category = 1L;  //类别
    @Column(name = "status")
    private Long status = 1L;  //状态,状态1表示待审核，2表示已审核通过未发布，3，表示审核通过已发布，0表示已审核未通过

    @Column(nullable = false)
    private Long aim_donation = 0L;  //目标金额

    @Column(nullable = false)
    private Long already_donation = 0L;  //目前已筹

    @Column(name = "reading")
    private Long reading = 0L;  //阅读量

    @Column(name ="donate_people_counter")
    private Long donate_people_counter = 0L;  //捐款人数


    @NotEmpty(message = "标题不能为空")
    @Size(min=2, max=50)
    @Column(nullable = false, length = 50) //映射字段不能为空
    private String title;  //项目名称

    @NotEmpty(message = "摘要不能为空")
    @Size(min=2, max = 300)
    @Column(nullable = false) //映射字段不能为空
    private String summary;  //摘要

    @Lob //大对象，映射MySQL的Long Text类型
    @Basic(fetch = FetchType.LAZY) //懒加载
    @NotEmpty(message = "内容不能为空")
    @Size(min=2)
    @Column(nullable = false) //映射字段不能为空
    private String content;  //项目正文

    @Lob
    @Basic(fetch = FetchType.LAZY)
//    @NotEmpty(message = "内容不能为空")
    @Size(min = 2)
//    @Column(nullable = false)
    private String html_content;  //项目html正文,将md 转换为html

    //1个求助者可对应多个活动
    @ManyToOne(cascade = CascadeType.DETACH, fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

//    @Column(nullable = false)  //映射字段不能为空
    @org.hibernate.annotations.CreationTimestamp
    private Timestamp created_time;  //项目时间开始

//    @Column(nullable = false)  //映射字段不能为空
    @org.hibernate.annotations.CreationTimestamp
    private Timestamp end_time;  //项目结束开始

//    @Column(nullable = false)  //映射字段不能为空
    private String initiator;  //发起人
    private String propaganda_map;  //首页大图

    private String pro_confirmation_link;

    public Project(String title, String summary,String content) {
        this.title = title;
        this.summary = summary;
        this.content = content;
    }

    public Project(){

    }


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

    public Long getAim_donation() {
        return aim_donation;
    }

    public void setAim_donation(Long aim_donation) {
        this.aim_donation = aim_donation;
    }

    public Long getAlready_donation() {
        return already_donation;
    }

    public void setAlready_donation(Long already_donation) {
        this.already_donation = already_donation;
    }

    public Long getReading() {
        return reading;
    }

    public void setReading(Long reading) {
        this.reading = reading;
    }

    public Long getDonate_people_counter() {
        return donate_people_counter;
    }

    public void setDonate_people_counter(Long donate_people_counter) {
        this.donate_people_counter = donate_people_counter;
    }

    public Timestamp getCreated_time() {
        return created_time;
    }

    public void setCreated_time(Timestamp created_time) {
        this.created_time = created_time;
    }

    public Timestamp getEnd_time() {
        return end_time;
    }

    public void setEnd_time(Timestamp end_time) {
        this.end_time = end_time;
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

    public String getHtml_content() {
        return html_content;
    }

    public void setHtml_content(String html_content) {
        this.html_content = html_content;
    }

    public String getSummary() {
        return summary;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }

    public String getInitiator() {
        return initiator;
    }

    public void setInitiator(String initiator) {
        this.initiator = initiator;
    }

    public String getPropaganda_map() {
        return propaganda_map;
    }

    public void setPropaganda_map(String propaganda_map) {
        this.propaganda_map = propaganda_map;
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

    public Set<User> getUserSet() {
        return userSet;
    }

    public void setUserSet(Set<User> userSet) {
        this.userSet = userSet;
    }
}