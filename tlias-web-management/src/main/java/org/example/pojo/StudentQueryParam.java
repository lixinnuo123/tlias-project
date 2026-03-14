package org.example.pojo;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class StudentQueryParam {
    private Integer page=1;//设置默认值  页码
    private Integer pageSize=10;//每页展示的记录数
    private String name;
    private Integer clazzId;
    private Integer degree;
}
