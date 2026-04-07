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
import lombok.ToString;

import java.math.BigDecimal;
import java.text.DecimalFormat;
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

    @Override
    public String toString() {
        // 用于格式化BigDecimal，保留两位小数
        DecimalFormat df = new DecimalFormat("#.##");

        StringBuilder sb = new StringBuilder();

        // 路段编号
        sb.append("路段").append(roadSectionId != null ? roadSectionId : "未知").append("为");

        // 区域类型
        sb.append(areaType != null ? areaType : "未知区域");

        // 车道数
        if (laneCount != null) {
            sb.append(laneCount).append("车道");
        } else {
            sb.append("未知车道数");
        }

        // 路段长度
        sb.append("，全长");
        if (sectionLengthKm != null) {
            sb.append(df.format(sectionLengthKm)).append("公里");
        } else {
            sb.append("未知长度");
        }

        // 设计通行能力
        sb.append("，设计通行能力");
        if (designCapacity != null) {
            sb.append(designCapacity).append("辆/日");
        } else {
            sb.append("未知");
        }

        // 月均车流量
        sb.append("，月均车流量");
        if (monthlyTrafficVolume != null) {
            sb.append(df.format(monthlyTrafficVolume)).append("辆");
        } else {
            sb.append("未知");
        }

        // 平均车速
        sb.append("，平均车速");
        if (avgSpeedKmh != null) {
            sb.append(df.format(avgSpeedKmh)).append("km/h");
        } else {
            sb.append("未知");
        }

        // 货车比例
        sb.append("，货车比例");
        if (truckRatioPercent != null) {
            sb.append(df.format(truckRatioPercent)).append("%");
        } else {
            sb.append("未知");
        }

        // 路面平整度
        sb.append("，路面平整度IRI");
        if (pavementRoughnessIri != null) {
            sb.append(df.format(pavementRoughnessIri));
        } else {
            sb.append("未知");
        }

        // 百公里事故率
        sb.append("，百公里事故率");
        if (accidentRatePer100km != null) {
            sb.append(df.format(accidentRatePer100km)).append("起");
        } else {
            sb.append("未知");
        }

        // 应急响应时间
        sb.append("，应急响应时间");
        if (emergencyResponseTimeMin != null) {
            sb.append(df.format(emergencyResponseTimeMin)).append("分钟");
        } else {
            sb.append("未知");
        }

        // 智能监测覆盖率
        sb.append("，智能监测覆盖率");
        if (smartMonitorCoveragePercent != null) {
            sb.append(df.format(smartMonitorCoveragePercent)).append("%");
        } else {
            sb.append("未知");
        }

        // 沿线景区数量
        sb.append("，沿线景区数量");
        if (scenicSpotCount != null) {
            sb.append(scenicSpotCount).append("个");
        } else {
            sb.append("未知");
        }

        // 旅游人次增长率
        sb.append("，旅游人次增长率");
        if (touristGrowthRatePercent != null) {
            sb.append(df.format(touristGrowthRatePercent)).append("%");
        } else {
            sb.append("未知");
        }

        // 单位里程碳排放
        sb.append("，单位里程碳排放");
        if (co2EmissionsPerKmTons != null) {
            sb.append(df.format(co2EmissionsPerKmTons)).append("吨/公里");
        } else {
            sb.append("未知");
        }

        // 噪声降低值
        sb.append("，噪声降低值");
        if (noiseReductionDb != null) {
            sb.append(df.format(noiseReductionDb)).append("dB");
        } else {
            sb.append("未知");
        }

        // 年通行费收入
        sb.append("，年通行费收入");
        if (tollRevenueTenThousandYuan != null) {
            sb.append(df.format(tollRevenueTenThousandYuan)).append("万元");
        } else {
            sb.append("未知");
        }

        // 服务区年收入
        sb.append("，服务区年收入");
        if (serviceAreaRevenueTenThousandYuan != null) {
            sb.append(df.format(serviceAreaRevenueTenThousandYuan)).append("万元");
        } else {
            sb.append("未知");
        }

        // 沿线GDP增长率
        sb.append("，沿线GDP增长率");
        if (gdpGrowthRatePercent != null) {
            sb.append(df.format(gdpGrowthRatePercent)).append("%");
        } else {
            sb.append("未知");
        }

        sb.append("。");
        return sb.toString();
    }
}
