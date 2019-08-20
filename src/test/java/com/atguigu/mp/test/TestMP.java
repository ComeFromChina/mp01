package com.atguigu.mp.test;

import java.sql.Connection;
import java.util.Arrays;
import java.util.List;

import javax.sql.DataSource;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.atguigu.mp.beans.Employee;
import com.atguigu.mp.mapper.EmployeeMapper;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;

public class TestMP {
	private ApplicationContext ioc = new ClassPathXmlApplicationContext("applicationContext.xml");
	
	private EmployeeMapper employeeMapper = ioc.getBean("employeeMapper",EmployeeMapper.class);
	
	/**
	 * insert
	 * @throws Exception
	 */
	@Test
	public void testCommonInsert() {
		Employee employee = new Employee();
		employee.setLastName("MP");
		employee.setAge(19);
		employee.setEmail("mp@attuigu.com");
		employee.setGender(1);
		Integer result = employeeMapper.insert(employee);
		System.out.println(result);
		System.out.println(employee.getId());//直接获取id
	}
	
	@Test 
	public void testCommonUpdate() {
		Employee employee = new Employee();
		employee.setId(7);
		employee.setAge(33);
		employee.setEmail("mp11@attuigu.com");
		employee.setGender(1);
		employee.setLastName("lllslss");
		Integer result = employeeMapper.updateById(employee);
		System.out.println(result);
	}
	
	@Test
	public void testCommonSelect() {
//		List<Employee> employeeList = employeeMapper.selectList(null);
//		List<Map<String, Object>> selectMaps = employeeMapper.selectMaps(null);
//		for (Map<String, Object> map : selectMaps) {
//			System.out.println(map);
//		}
//		Employee emp = employeeMapper.selectById(7);
//		System.out.println(emp.getLastName());
//		System.out.println(employeeList.size());
		
//		Map<String,Object> map = new HashMap<String, Object>();
//		map.put("last_name", "MP");
//		List<Employee> list = employeeMapper.selectByMap(map);
//		System.out.println(list);
		
		List<Employee> list = employeeMapper.selectPage(new Page<Employee>(2,2), null);
		System.out.println(list);
		
		List<Employee> list2 = employeeMapper.selectList(new EntityWrapper<Employee>()
				.eq("last_name", "Tom")
				.orderDesc(Arrays.asList(new String[]{"age"})));
		System.out.println(list2);
	}
	
	@Test
	public void testCommonDelete() {
		Integer result = employeeMapper.deleteById(7);//根据id删除
		System.out.println("result:"+result);
	}
	
	@Test
	public void testEntityWrapperSelect() {
		//年龄在18~50之间性别为男且姓名为xxx的所有用户
		List<Employee> list = employeeMapper.selectPage(new Page<Employee>(1,3),
				new EntityWrapper<Employee>().between("age",18,50)
				.eq("last_name", "Tom")
				.eq("gender", 1));
		System.out.println(list);
	}
	
	@Test
	public void testEntityWrapperUpdate() {
		Employee employee = new Employee();
		employee.setAge(22);
		employee.setEmail("www.@sina.com");
		employee.setGender(1);
		employee.setLastName("testName");
		Integer result = employeeMapper.update(employee, 
				new EntityWrapper<Employee>()
				.eq("last_name", "Tom")
				.eq("age", 55));
		System.out.println("result:"+result);
	}
	
	@Test
	public void testDataSource() throws Exception {
		DataSource ds  = ioc.getBean("dataSource",DataSource.class);
		System.out.println(ds);
		Connection conn = ds.getConnection();
		System.out.println(conn);
	}

	
	
}
