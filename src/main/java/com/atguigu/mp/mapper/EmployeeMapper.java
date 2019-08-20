package com.atguigu.mp.mapper;

import com.atguigu.mp.beans.Employee;
import com.baomidou.mybatisplus.mapper.BaseMapper;

/**
 * Mapper 接口
 * 基于Mybatis：编写CRUD相关方法，提供mapper接口所对应的SQL映射文件以及方法对应的SQL语句
 * 
 * 基于MP：让Mapper接口继承BaseMapper接口即可
 * BaseMapper<T>:泛型指定的就是当前Mapper接口所操作的实体类类型
 *
 */
public interface EmployeeMapper extends BaseMapper<Employee> {

}
