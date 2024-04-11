package com.ale;

import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author : alewu
 * @since : 2024/4/10 23:07
 **/
@NoArgsConstructor
@Data
public class DNSRecord {
    /**
     * data
     */
    private String data;
    /**
     * name
     */
    private String name;
    /**
     * port
     */
    private Integer port;
    /**
     * priority
     */
    private Integer priority;
    /**
     * protocol
     */
    private String protocol;
    /**
     * service
     */
    private String service;
    /**
     * ttl
     */
    private Integer ttl;
    /**
     * type
     */
    private String type;
    /**
     * weight
     */
    private Integer weight;
}
