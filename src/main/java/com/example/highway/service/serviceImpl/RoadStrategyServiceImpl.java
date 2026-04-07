package com.example.highway.service.serviceImpl;

import com.alibaba.excel.EasyExcel;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.highway.mapper.RoadStrategyMapper;
import com.example.highway.pojo.RoadStrategy;
import com.example.highway.service.RoadStrategyService;
import com.example.highway.utils.RoadStrategyDataListener;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Service
public class RoadStrategyServiceImpl extends ServiceImpl<RoadStrategyMapper, RoadStrategy> implements RoadStrategyService {
    @Transactional(rollbackFor = Exception.class)
    public String importExcel(MultipartFile file) {
        try {
            RoadStrategyDataListener listener = new RoadStrategyDataListener(this);
            EasyExcel.read(file.getInputStream(), RoadStrategy.class, listener)
                    .sheet()          // 指定读取名为“策略”的sheet
                    .headRowNumber(1)       // 表头占2行（第一行合并标题，第二行实际列名？根据观察，数据从第3行开始）
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
