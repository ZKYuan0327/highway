package com.example.highway.utils;

import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.read.listener.ReadListener;
import com.baomidou.mybatisplus.extension.service.IService;
import com.example.highway.pojo.RoadMetricsAll;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Slf4j
public class RoadMetricsDataListener  implements ReadListener<RoadMetricsAll> {

    private static final int BATCH_COUNT = 1000;
    private final List<RoadMetricsAll> cachedList = new ArrayList<>(BATCH_COUNT);
    private final IService<RoadMetricsAll> service;  // 使用 IService 或直接注入 Mapper

    public RoadMetricsDataListener(IService<RoadMetricsAll> service) {
        this.service = service;
    }

    @Override
    public void invoke(RoadMetricsAll data, AnalysisContext analysisContext) {
        cachedList.add(data);
        if (cachedList.size() >= BATCH_COUNT){
            saveData();
            cachedList.clear();
        }
    }

    @Override
    public void doAfterAllAnalysed(AnalysisContext analysisContext) {
        if (!cachedList.isEmpty()) {
            saveData();
            cachedList.clear();
        }
        log.info("所有数据解析完成并保存");
    }

    private void saveData() {
        service.saveBatch(cachedList);
        log.info("批量保存 {} 条数据", cachedList.size());
    }
}
