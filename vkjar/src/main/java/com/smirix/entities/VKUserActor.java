package com.smirix.entities;

/**
 * Created by Smirnov-VA on 07.09.2017.
 */
public class VKUserActor extends Network
{
    private String vkAccessCode;
    private Integer vkUserId;

    public String getVkAccessCode() {
        return vkAccessCode;
    }

    public void setVkAccessCode(String vkAccessToken) {
        this.vkAccessCode = vkAccessToken;
    }

    public Integer getVkUserId() {
        return vkUserId;
    }

    public void setVkUserId(Integer vkUserId) {
        this.vkUserId = vkUserId;
    }
}
