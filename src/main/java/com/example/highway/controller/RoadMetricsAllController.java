package com.example.highway.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.highway.common.Result;
import com.example.highway.pojo.RoadMetricsAll;
import com.example.highway.service.RoadMetricsAllService;
import com.example.highway.service.serviceImpl.RoadMetricsAllServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@Slf4j
@RestController
@RequestMapping("/road-metrics")
@CrossOrigin
public class RoadMetricsAllController {

    @Autowired
    private RoadMetricsAllServiceImpl roadService;

    @PostMapping("/upload-file")
    public Result<String> uploadExcel(@RequestParam("file") MultipartFile file) {
        if (file.isEmpty()) {
            return Result.error("空文件");
        }
        return Result.success(roadService.importExcel(file));
    }

    @GetMapping("/page")
    public Result<Page> page(int page, int pageSize, String name){
        log.info("page = {}, pageSize = {}", page, pageSize);
        Page pageInfo = new Page(page, pageSize);

        LambdaQueryWrapper<RoadMetricsAll> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.like(StringUtils.isNotEmpty(name), RoadMetricsAll::getRoadSectionId, name);
        queryWrapper.orderByDesc(RoadMetricsAll::getUpdateTime);

        roadService.page(pageInfo, queryWrapper);
        return Result.success(pageInfo);
    }

    @PostMapping("/upload-manual")
    public Result<String> uploadManual(@RequestBody RoadMetricsAll roadMetricsAll){
        log.info(roadMetricsAll.toString());
        roadService.save(roadMetricsAll);
        return Result.success("数据上传成功");
    }

    @PostMapping("/update")
    public Result<String> update(@RequestBody RoadMetricsAll roadMetricsAll){
        roadService.updateById(roadMetricsAll);
        return Result.success("更新成功");
    }

    @PostMapping("/delete/{id}")
    public Result<String> delete(@PathVariable Long id){
        roadService.removeById(id);
        return Result.success("删除成功");
    }
}
