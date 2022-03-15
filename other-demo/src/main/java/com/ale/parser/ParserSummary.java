package com.ale.parser;


import lombok.Data;

@Data
public class ParserSummary {
    private boolean containsConditional;
    private String containsConditionalClassName;
    private String addedConditionalClassName;
    private String todoAddAnnotationClassName;
    private boolean addAnnotationSucceed;

}
