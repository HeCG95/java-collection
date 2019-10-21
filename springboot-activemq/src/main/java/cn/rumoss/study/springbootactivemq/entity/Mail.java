package cn.rumoss.study.springbootactivemq.entity;

import lombok.Data;

import java.io.Serializable;

@Data
public class Mail implements Serializable {

    private static final long serialVersionUID = 1L;

    private String from;
    private String to;
    private String subject;
    private String content;

}
