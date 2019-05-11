package com.smirix.requests;

import java.io.Serializable;

/**
 * Class description
 *
 * @author Smirnov-VA
 * @created on 2019-05-11
 */
public class AttachmentDto implements Serializable {
    private Long id;
    private String externalUrl;
    private String privateUrl;
    private String name;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getExternalUrl() {
        return externalUrl;
    }

    public void setExternalUrl(String externalUrl) {
        this.externalUrl = externalUrl;
    }

    public String getPrivateUrl() {
        return privateUrl;
    }

    public void setPrivateUrl(String privateUrl) {
        this.privateUrl = privateUrl;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
