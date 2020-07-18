package com.ale;

import cn.hutool.core.util.ReUtil;

import java.util.List;
import java.util.Queue;
import java.util.regex.Pattern;

/**
  *  该工具类暂时用来处理微信xml消息，后续需替换
  * @author alewu
  * @date 2020/6/13
  */
public final class WxMsgUtil {
    private WxMsgUtil() {}
    public static final String MSG_TYPE = "<MsgType>%s</MsgType>";
    public static final String CDATA = "<![CDATA[%s]]>";
    public static final String CDATA_PATTERN = "<!\\[CDATA\\[(.*?)\\]\\]>";
    public static final String  CONTENT_FORMAT = "<Content>%s</Content>";
    public static final String MSG_TYPE_FORMAT = "<MsgType>%s</MsgType>";
    public static final String  URL_FORMAT = "<Url>%s</Url>";
    public static final String  TO_USER_NAME_FORMAT = "<ToUserName>%s</ToUserName>";

    public static final Pattern MSG_TYPE_COMPILE = Pattern.compile(String.format(MSG_TYPE_FORMAT, CDATA_PATTERN));

    public static final Pattern CONTENT_COMPILE = Pattern.compile(String.format(CONTENT_FORMAT, CDATA_PATTERN));

    public static final Pattern URL_COMPILE = Pattern.compile(String.format(URL_FORMAT, CDATA_PATTERN));


    public static final Pattern TO_USER_NAME_COMPILE = Pattern.compile(String.format(TO_USER_NAME_FORMAT, CDATA_PATTERN));



    public static String getToUserName(String wxXmlMsg) {
        List<String> all = ReUtil.findAllGroup1(TO_USER_NAME_COMPILE, wxXmlMsg);
        return all.stream().findFirst().orElse("");
    }



    public static String getContent(String wxXmlMsg) {
        List<String> all = ReUtil.findAllGroup1(CONTENT_COMPILE, wxXmlMsg);
//         return all.iterator().next();
        return all.stream().findFirst().orElse("");
    }

    public static String getMsgType(String wxXmlMsg) {
        List<String> all = ReUtil.findAllGroup1(MSG_TYPE_COMPILE, wxXmlMsg);
        return all.stream().findFirst().orElse("");
    }

    public static List<String> getUrl(String wxXmlMsg) {
        return ReUtil.findAllGroup1(URL_COMPILE, wxXmlMsg);
    }

    /**
     * 递归替换匹配的字符串
     * @param src 原字符串
     * @param replacementTemplate 替换模板
     * @param queue 要替换的字符串
     * @return 替换后的字符串
     */
    public static String recursionJoinMatchStr(String src, String replacementTemplate, Queue<String> queue) {
        if (queue.isEmpty()) {
            return src;
        } else {
            String value = ReUtil.replaceAll(src, queue.poll(), replacementTemplate);
            return recursionJoinMatchStr(value, replacementTemplate, queue);
        }
    }
}
