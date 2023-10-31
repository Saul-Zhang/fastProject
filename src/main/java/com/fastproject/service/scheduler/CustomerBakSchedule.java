package com.fastproject.service.scheduler;


import cn.dev33.satoken.stp.StpUtil;
import cn.hutool.poi.excel.ExcelUtil;
import cn.hutool.poi.excel.ExcelWriter;
import com.fastproject.service.CustomerService;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import javax.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
@Slf4j
public class CustomerBakSchedule {

    private final CustomerService customerService;


    @Scheduled(cron = "0 0 23 ? * 6") // 每周5的23点执行
//    @PostConstruct //启动项目先执行
    public void execute() {
        log.info("执行定时任务");
        String filePath = "E:\\backup\\客户备份数据"+LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH-mm-ss"))+".xlsx";
        ExcelWriter writer = ExcelUtil.getWriter(filePath);
        writer.write(customerService.exportList(null, null), true);
        writer.close();
        log.info("执行定时任务成功");
    }
}
