package com.ale;

import com.thoughtworks.xstream.annotations.XStreamAlias;
import lombok.Data;

import java.io.Serializable;

@XStreamAlias("xml")
@Data
public abstract class WxMpXmlOutMessage implements Serializable {
    private static final long serialVersionUID = -381382011286216263L;

    @XStreamAlias("ToUserName")
    protected String toUserName;

    @XStreamAlias("FromUserName")
    protected String fromUserName;

    @XStreamAlias("CreateTime")
    protected Long createTime;

    @XStreamAlias("MsgType")
    protected String msgType;

}
