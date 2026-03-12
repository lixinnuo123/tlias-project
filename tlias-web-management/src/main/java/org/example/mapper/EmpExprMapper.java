package org.example.mapper;


import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Mapper;
import org.example.pojo.EmpExpr;

import java.util.List;


/*
* 员工工作经历
* */
@Mapper
public interface EmpExprMapper {

    /*
    * 批量保存员工工作经历信息
    * */
    void insertBatch(List<EmpExpr> exprList);

    /*
     * 批量删除员工工作经历信息
     * */
    void deleteempExpr(List<Integer> ids);
}
