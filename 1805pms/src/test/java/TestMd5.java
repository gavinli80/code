import org.junit.Test;

import com.zs.pms.utils.MD5;

public class TestMd5 {
	@Test
	public void testMd5() {
		MD5 md5=new MD5();
		System.out.println(md5.getMD5ofStr("张飞"));
	}

}
