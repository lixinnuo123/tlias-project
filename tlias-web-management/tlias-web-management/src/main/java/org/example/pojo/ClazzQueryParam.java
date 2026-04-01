package org.example.pojo;


import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;

@Data
public class ClazzQueryParam {

    private Integer page=1;//设置默认值  页码
    private Integer pageSize=10;//每页展示的记录数
    private String name;
    @DateTimeFormat(pattern = "yyyy-MM-dd")//指定日期时间格式
    private LocalDate begin;//入职时间的开始时间
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate end;//入职时间的结束时间
}
