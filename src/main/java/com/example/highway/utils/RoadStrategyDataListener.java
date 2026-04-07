package com.example.highway.utils;



import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.metadata.data.ReadCellData;
import com.alibaba.excel.read.listener.ReadListener;
import com.baomidou.mybatisplus.extension.service.IService;
import com.example.highway.pojo.RoadStrategy;
import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Slf4j
public class RoadStrategyDataListener implements ReadListener<RoadStrategy> {

    private static final int BATCH_COUNT = 1000;
    private final List<RoadStrategy> cachedList = new ArrayList<>(BATCH_COUNT);
    private final IService<RoadStrategy> service;
    private String lastCategoryLevel1 = null; // 缓存上一行的一级分类
    private String lastCategoryLevel2 = null;

    public RoadStrategyDataListener(IService<RoadStrategy> service) {
        this.service = service;
    }

    @Override
    public void invoke(RoadStrategy data, AnalysisContext context) {
        log.info(data.toString());
        if (data.getCategoryLevel1() == null || data.getCategoryLevel1().trim().isEmpty()) {
            data.setCategoryLevel1(lastCategoryLevel1);
        } else {
            lastCategoryLevel1 = data.getCategoryLevel1();
        }

        if (data.getCategoryLevel2() == null || data.getCategoryLevel2().trim().isEmpty()){
            data.setCategoryLevel2(lastCategoryLevel2);
        }else {
            lastCategoryLevel2 = data.getCategoryLevel2();
        }


        cachedList.add(data);
        if (cachedList.size() >= BATCH_COUNT) {
            saveData();
            cachedList.clear();
        }
    }



    @Override
    public void doAfterAllAnalysed(AnalysisContext context) {
        if (!cachedList.isEmpty()) {
            saveData();
            cachedList.clear();
        }
        log.info("所有策略数据解析完成并保存");
    }

    private void saveData() {
        service.saveBatch(cachedList);
        log.info("批量保存 {} 条策略数据", cachedList.size());
    }

//    @Override
//    public void invokeHead(Map<Integer, ReadCellData<?>> headMap, AnalysisContext context) {
//        System.out.println("表头信息：");
//        headMap.forEach((index, cellData) -> {
//            String headName = cellData.getStringValue(); // 获取表头字符串
//            System.out.println("列索引 " + index + " -> [" + headName + "]");
//        });
//    }
}