package com.nd.me.entity;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "userXml")
public class UserXml {
    private String name;
    private String addr;

    public String getName() {
        return name;
    }

    @XmlElement
    public void setName(String name) {
        this.name = name;
    }

    public String getAddr() {
        return addr;
    }

    @XmlElement
    public void setAddr(String addr) {
        this.addr = addr;
    }
}
