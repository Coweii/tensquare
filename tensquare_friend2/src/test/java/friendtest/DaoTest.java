package friendtest;

import com.coweii.friend.Friend2Application;
import com.coweii.friend.dao.FriendDao;
import com.coweii.friend.dao.NoFriendDao;
import com.coweii.friend.pojo.Friend;
import com.coweii.friend.pojo.NoFriend;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@SpringBootTest(classes = Friend2Application.class)
@RunWith(SpringRunner.class)
public class DaoTest {

    @Autowired
    NoFriendDao noFriendDao;

    @Autowired
    FriendDao friendDao;

    @Test
    public void test1(){
        NoFriend noFriend = noFriendDao.findByUseridAndFriendid("1222422591847927808","123");
        System.out.println(noFriend);
        noFriendDao.deleteNoFriendByUseridAndFriendid("1222422591847927808","123");
    }

    @Test
    public void test2(){
        Friend friend = friendDao.findFriendByUseridAndFriendid("1","100");
        Friend friend1 = new Friend();
        friend1.setUserid("12345");
        friend1.setFriendid("54321");
        friendDao.save(friend1);
        System.out.println(friend);
    }

    @Test
    public void test3(){
        friendDao.updateIslike("1","1222422591847927808","123");
    }
}
