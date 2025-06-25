package com.atguigu.gulimail.member.entity;

import lombok.Data;


@Data
public class GiteeUser {
    private Long id;
    private String login;
    private String name;
    private String avatarUrl;
    private String url;
    private String htmlUrl;
    private String remark;
    private String followersUrl;
    private String followingUrl;
    private String gistsUrl;
    private String starredUrl;
    private String subscriptionsUrl;
    private String organizationsUrl;
    private String reposUrl;
    private String eventsUrl;
    private String receivedEventsUrl;
    private String type;
    private String blog;
    private String weibo;
    private String bio;
    private Integer publicRepos;
    private Integer publicGists;
    private Integer followers;
    private Integer following;
    private Integer stared;
    private Integer watched;
    private String createdAt;
    private String updatedAt;
    private String email;
}

