package com.xf.netty.pojo;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Setter
@Getter
public class UserParam implements Serializable{
    private String id;
    private String name;
    private String responseMessage;
    private String requestMessage;
}
