import com.coweii.qa.service.ProblemService;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
@ContextConfiguration
public class Test {
    @Autowired
    ProblemService problemService;

    @org.junit.Test
    public void test(){
        System.out.println(problemService.findLatest("1",1,1).getContent());
    }
}
