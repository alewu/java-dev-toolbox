package com.ale.demo;

import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.event.AnalysisEventListener;

import java.util.ArrayList;
import java.util.List;

public class ExcelListener extends AnalysisEventListener {
    /**
     * 可以通过实例获取该值
     */
    private List<Object> dataList = new ArrayList<>();

    @Override
    public void invoke(Object object, AnalysisContext context) {
        //数据存储到list，供批量处理，或后续自己业务逻辑处理。
        dataList.add(object);
        handleBusinessLogic();
          /*
        如数据过大，可以进行定量分批处理
        if(dataList.size()>=200){
            handleBusinessLogic();
            dataList.clear();
        }
         */
    }

    @Override
    public void doAfterAllAnalysed(AnalysisContext context) {
        //非必要语句，查看导入的数据
        System.out.println("导入的数据条数为: " + dataList.size());
    }

    //根据业务自行实现该方法，例如将解析好的dataList存储到数据库中
    private void handleBusinessLogic() {

    }

    public List<Object> getDataList() {
        return dataList;
    }

    public void setDataList(List<Object> dataList) {
        this.dataList = dataList;
    }
}