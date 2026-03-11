package com.example.highway.pojo;

import com.alibaba.excel.annotation.ExcelProperty;
import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
public class RoadMetricsAll {
    private Long id;

    @ExcelProperty("路段编号")   // 假设 Excel 中有一列名为“路段编号”
    private String roadSectionId;

    @ExcelProperty("月均车流量")
    private BigDecimal monthlyTrafficVolume;

    @ExcelProperty("平均车速（km/h）")
    private BigDecimal avgSpeedKmh;

    @ExcelProperty("车道数")
    private Integer laneCount;

    @ExcelProperty("路面平整度（IRI）")
    private BigDecimal pavementRoughnessIri;

    @ExcelProperty("百公里事故率")
    private BigDecimal accidentRatePer100km;

    @ExcelProperty("应急响应时间（分钟）")
    private BigDecimal emergencyResponseTimeMin;

    @ExcelProperty("智能监测覆盖率（%）")
    private BigDecimal smartMonitorCoveragePercent;

    @ExcelProperty("路段长度（公里）")
    private BigDecimal sectionLengthKm;

    @ExcelProperty("路段设计通行能力（辆/日）")
    private Integer designCapacity;

    @ExcelProperty("路段所在区域类型")
    private String areaType;

    @ExcelProperty("货车比例（%）")
    private BigDecimal truckRatioPercent;

    @ExcelProperty("年通行费收入（万元）")
    private BigDecimal tollRevenueTenThousandYuan;

    @ExcelProperty("沿线GDP增长率（%）")
    private BigDecimal gdpGrowthRatePercent;

    @ExcelProperty("服务区年收入（万元）")
    private BigDecimal serviceAreaRevenueTenThousandYuan;

    @ExcelProperty("单位里程碳排放（吨/公里）")
    private BigDecimal co2EmissionsPerKmTons;

    @ExcelProperty("噪声降低值（dB）")
    private BigDecimal noiseReductionDb;

    @ExcelProperty("旅游人次增长率（%）")
    private BigDecimal touristGrowthRatePercent;

    @ExcelProperty("沿线景区数量")
    private Integer scenicSpotCount;

    // 审计字段不加 @ExcelProperty，因为不需要从 Excel 读取
    @TableField(fill = FieldFill.INSERT)
    @JsonDeserialize(using = LocalDateTimeDeserializer.class)
    @JsonSerialize(using = LocalDateTimeSerializer.class)
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern="yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    private LocalDateTime createTime;

    @TableField(fill = FieldFill.INSERT_UPDATE)
    @JsonDeserialize(using = LocalDateTimeDeserializer.class)
    @JsonSerialize(using = LocalDateTimeSerializer.class)
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern="yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    private LocalDateTime updateTime;
}
