package friendtest;

import com.coweii.friend.FriendApplication;
import com.coweii.friend.dao.FriendDao;
import com.coweii.friend.dao.NoFriendDao;
import com.coweii.friend.pojo.Friend;
import com.coweii.friend.pojo.NoFriend;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@SpringBootTest(classes = FriendApplication.class)
@RunWith(SpringRunner.class)
public class DaoTest {

    @Autowired
    NoFriendDao noFriendDao;

    @Autowired
    FriendDao friendDao;

    @Test
    public void test1(){
        NoFriend noFriend = noFriendDao.findByUseridAndFriendid("1222422591847927808","123");
    }

    @Test
    public void test2(){
        Friend friend = friendDao.findFriendByUseridAndFriendid("1","100");
    }
}
