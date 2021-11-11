<#list tableMetas as item>
    /**
    * ${item.filedComment}
    */
    @ApiModelProperty(value="${item.filedComment}")
    private String ${item.filedName};
</#list>