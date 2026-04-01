package org.example.aop;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.example.mapper.OperateLogMapper;
import org.example.pojo.OperateLog;
import org.example.utils.CurrentHolder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;
import java.time.LocalDateTime;
import java.util.Arrays;

@Slf4j
@Aspect
@Component
public class OperationLogAspect {

    @Autowired
    private OperateLogMapper operateLogMapper;

    @Around("@annotation(org.example.anno.Log)")
    public Object recordLog(ProceedingJoinPoint joinPoint) throws Throwable {
        long begin = System.currentTimeMillis();
        //执行目标方法
        Object result = joinPoint.proceed();
        //计算耗时
        long endTime = System.currentTimeMillis();
        long costTime = endTime- begin;
        //构建日志实体
        OperateLog olog = new OperateLog();
        olog.setOperateTime(LocalDateTime.now());
        //记录操作人的ID
        olog.setOperateEmpId(getCurrentUserId());

        olog.setClassName(joinPoint.getTarget().getClass().getName());
        olog.setMethodName(((MethodSignature)joinPoint.getSignature()).getName());
        olog.setMethodParams(Arrays.toString(joinPoint.getArgs()));
        olog.setReturnValue(result!=null ? result.toString():"void");
        olog.setCostTime(costTime);
        //保存日志
        log.info("保存日志：{}",olog);
        operateLogMapper.insert(olog);
        return result;
    }

    public Integer getCurrentUserId() {

        return CurrentHolder.getCurrentId();
    }

}
