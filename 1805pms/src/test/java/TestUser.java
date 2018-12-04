import java.util.Date;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.zs.pms.exception.AppException;
import com.zs.pms.po.TDep;
import com.zs.pms.po.TPermission;
import com.zs.pms.po.TUser;
import com.zs.pms.service.UserService;
import com.zs.pms.vo.QueryUser;

@RunWith(SpringJUnit4ClassRunner.class) // 使用spring测试框架
@ContextConfiguration("classpath:applicationcontext.xml") // 引入配置文件
public class TestUser {
	@Autowired
	UserService us;

	public void testlogin() {
		QueryUser query = new QueryUser();
		query.setLoginname("test1");
		query.setPassword("1234"); // 明码
		try {
			TUser user = us.chkLogin(query);
			System.out.println(user.getRealname() + "、" + user.getDept().getName());
			for (TPermission per : user.getPermissions()) {
				System.out.println(per.getPname());
			}
			System.out.println("-----------------------整理后--------------------------");

			for (TPermission per1 : user.getMenu()) {
				System.out.println(per1.getPname());

				for (TPermission per2 : per1.getChildren()) {
					System.out.println("\t\t" + per2.getPname());
				}

			}
		} catch (AppException e) {
			// TODO Auto-generated catch block
			System.out.println(e.getErrMsg());
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	}
	
	
	public void testQuery() {
		QueryUser query = new QueryUser();
		//query.setLoginname("test1");
		//query.setPassword("81DC9BDB52D04DC20036DBD8313ED055"); // 明码
		// query.setIsenabled(1);
		// query.setPage(2); //当前页
		us.queryByCon(query);

	}
	
	@Test
	public void testPage() {
		QueryUser query = new QueryUser();
		query.setSex("男");
		System.out.println("当前总页数"+us.queryPageCount(query));
		for(TUser user:us.queryByPage(query, 2)) {
			System.out.println(user.getRealname());
		}
	}

	public void testdels() {
		int[] ids = { 3106, 3107, 3108 };
		us.deleteByIds(ids);
	}

	public void testUpdate() {
		TUser user = new TUser();
		user.setId(3094);
		// user.setBirthday(new Date());

		// 部门
		TDep dep = new TDep();
		dep.setId(6);
		// user.setDept(dep);

		// user.setEmail("qwe@123.com");
		user.setIsenabled(1);
		// user.setPassword("4321");
		// user.setPicurl("aaa.jpg");
		// user.setRealname("测试员66");
		user.setSex("男");
		user.setUpdator(1001);

		try {
			us.update(user);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	
	public void testAdd() {
		TUser user = new TUser();
		user.setBirthday(new Date());

		// 部门
		TDep dep = new TDep();
		dep.setId(6);
		user.setDept(dep);
		user.setLoginname("trans002");
		user.setEmail("qwe@123.com");
		user.setPassword("4321");
		user.setPicurl("aaa.jpg");
		user.setRealname("测试员66");
		user.setSex("男");
		user.setCreator(1000);
		
		try {
			us.insert(user);
		} catch (AppException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
