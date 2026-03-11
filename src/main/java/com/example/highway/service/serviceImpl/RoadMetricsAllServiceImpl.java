package com.example.highway.service.serviceImpl;

import com.alibaba.excel.EasyExcel;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.highway.mapper.RoadMetricsAllMapper;
import com.example.highway.pojo.RoadMetricsAll;
import com.example.highway.service.RoadMetricsAllService;
import com.example.highway.utils.RoadMetricsDataListener;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Service
public class RoadMetricsAllServiceImpl extends ServiceImpl<RoadMetricsAllMapper, RoadMetricsAll> implements RoadMetricsAllService {
    @Transactional(rollbackFor = Exception.class)
    public String importExcel(MultipartFile file) {
        try {
            // 使用自定义监听器，传入当前 service（因为继承了 IService）
            RoadMetricsDataListener listener = new RoadMetricsDataListener(this);
            EasyExcel.read(file.getInputStream(), RoadMetricsAll.class, listener)
                    .sheet()          // 读取第一个 sheet
                    .headRowNumber(1) // 表头行数，默认为1
                    .doRead();
            return "导入成功";
        } catch (IOException e) {
            log.error("文件读取失败", e);
            throw new RuntimeException("文件读取失败: " + e.getMessage());
        } catch (Exception e) {
            log.error("导入失败", e);
            throw new RuntimeException("导入失败: " + e.getMessage());
        }
    }
}
