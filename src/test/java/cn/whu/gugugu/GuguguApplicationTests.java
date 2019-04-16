package cn.whu.gugugu;

import cn.whu.gugugu.generated.mapper.UserMapper;
import cn.whu.gugugu.generated.model.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class GuguguApplicationTests {
    @Autowired
    UserMapper userMapper;

    @Test
    public void contextLoads() {
    }

    @Test
    public void testDB() {

        User a = new User();
        a.setOpenId("12");
        userMapper.insert(a);
    }

}
