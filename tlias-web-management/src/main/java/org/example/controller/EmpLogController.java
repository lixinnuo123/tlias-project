package org.example.controller;

import lombok.extern.slf4j.Slf4j;
import org.example.pojo.EmpLog;
import org.example.pojo.PageResult;
import org.example.pojo.Result;
import org.example.service.EmpLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/log")
public class EmpLogController {

    @Autowired
    private EmpLogService empLogService;

    /**
     * 分页查询操作日志
     */
    @GetMapping("/page")
    public Result page(Integer page, Integer pageSize) {
        log.info("分页查询操作日志：page={}, pageSize={}", page, pageSize);
        PageResult<EmpLog> pageResult = empLogService.page(page, pageSize);
        return Result.success(pageResult);
    }
}
