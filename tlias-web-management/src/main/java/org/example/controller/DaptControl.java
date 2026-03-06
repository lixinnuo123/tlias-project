package org.example.controller;

import lombok.extern.slf4j.Slf4j;
import org.example.pojo.Dept;
import org.example.pojo.Result;
import org.example.service.DeptService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@Slf4j
@RestController
public class DaptControl {

    /*private static final Logger log= LoggerFactory.getLogger(DaptControl.class);
*/
    @Autowired
    private DeptService deptService;

   // @RequestMapping(value = "/depts",method = RequestMethod.GET)//method 指定请求方式
    @GetMapping("/depts")//优化后的
    public Result list(){
       /* System.out.println("查询全部的部门数据");*/
        log.info("查询全部的部门数据");
        List<Dept> deptList= deptService.findAll();
        return Result.success(deptList);
    }


    /*
    * 删除部门
    * */
    //方式一：通过原始的HttpSercletRequest对象获取请求参数
    /*@DeleteMapping("/depts")
    public Result delete(HttpServletRequest request) {
        String idSt = request.getParameter("id");
        Integer id=Integer.parseInt(idSt);
        System.out.println("根据ID删除部门："+id);
        return Result.success();//不需要额外给前端返回数据
    }*/

    //方式二：通过Spring提供的@RequestParam注解，将请求参数绑定给方法形参

    //注意事项：一旦声明了@RequestParam注解，该参数在请求时必须传递，如果不传递将会报错。（默认）（默认：required为true）
    /*@DeleteMapping("/depts")
    public Result delete(@RequestParam("id") Integer deptId){
        System.out.println("根据ID删除部门："+deptId);
        return Result.success();
    }*/

    //方式三：如果请求参数的名称和形参名称一致，可以省略@RequestParam注解
    @DeleteMapping("/depts")
    public Result delete(Integer id){
        /*System.out.println("根据ID删除部门："+id);*/
        log.info("根据ID删除部门：{}",id);
        deptService.deleteById(id);
        return  Result.success();
    }

    /*
    * 新增部门
    * */
    //@RequestBody注解：将请求体中的JSON数据，映射为Java对象
    @PostMapping("/depts")
    public Result post(@RequestBody Dept dept){
        /*System.out.println("新增部门："+dept);*/
        log.info("新增部门：{}",dept);
        deptService.add(dept);
        return Result.success();
    }

    /*
     * 根据ID查询部门,返回数据
     * */
    //传输路径参数，使用@PathVariable注解
    @GetMapping("/depts/{id}")
    public Result getInfo(@PathVariable Integer id) {
        /*System.out.println("根据ID查询部门数据：" + id);*/
        log.info("根据ID查询部门数据：{}" ,id);
        Dept dept = deptService.getInfo(id);
        return Result.success(dept);
    }

    /*
    * 修改部门
    * */
    @PutMapping("/depts")
    public Result update(@RequestBody Dept dept){
        /*System.out.println("修改部门："+dept);*/
        log.info("修改部门：{}",dept);
        deptService.update(dept);
        return  Result.success();
    }
}