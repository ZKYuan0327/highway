package com.example.highway.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.highway.common.Result;
import com.example.highway.pojo.RoadMetricsAll;
import com.example.highway.pojo.RoadStrategy;
import com.example.highway.service.serviceImpl.RoadMetricsAllServiceImpl;
import com.example.highway.service.serviceImpl.RoadStrategyServiceImpl;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/road-strategy")
@RequiredArgsConstructor
@CrossOrigin
@Slf4j
public class RoadStrategyController {

    @Autowired
    private RoadStrategyServiceImpl roadStrategyService;

    @PostMapping("/upload-file")
    public Result<String> uploadExcel(@RequestParam("file") MultipartFile file) {
        if (file.isEmpty()) {
            return Result.error("空文件");
        }
        return Result.success(roadStrategyService.importExcel(file));
    }

    @PostMapping("/upload-manual")
    public Result<String> upload(@RequestBody RoadStrategy roadStrategy){
        roadStrategyService.save(roadStrategy);
        return Result.success("上传成功");
    }

    @PostMapping("/update")
    public Result<String> update(@RequestBody RoadStrategy roadStrategy){
        roadStrategyService.updateById(roadStrategy);
        return Result.success("修改成功");
    }

    @GetMapping("/page")
    public Result<Page> page(int page, int pageSize, String name){
        log.info("page = {}, pageSize = {}", page, pageSize);
        Page pageInfo = new Page(page, pageSize);

        QueryWrapper<RoadStrategy> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq(name, true);
//        LambdaQueryWrapper<RoadStrategy> queryWrapper = new LambdaQueryWrapper<>();
//        queryWrapper.eq(name, true);
//        queryWrapper.orderByDesc(RoadStrategy::getUpdateTime);

        roadStrategyService.page(pageInfo, queryWrapper);
        return Result.success(pageInfo);
    }

    @PostMapping("/delete/{id}")
    public Result<String> delete(@PathVariable Long id){
        roadStrategyService.removeById(id);
        return Result.success("删除成功");
    }





}
