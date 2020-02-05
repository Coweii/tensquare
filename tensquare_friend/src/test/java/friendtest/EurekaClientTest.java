package friendtest;

import com.coweii.friend.FriendApplication;
import com.coweii.friend.client.UserClient;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@SpringBootTest(classes = FriendApplication.class)
@RunWith(SpringRunner.class)
public class EurekaClientTest {

    @Autowired
    UserClient userClient;

    @Test
    public void test1(){
        userClient.updateFansCount("123",1);
    }
}
