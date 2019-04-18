import com.xf.maven_ssm.annotation_dao.ItemsDao;
import com.xf.maven_ssm.domain.Items;
import com.xf.maven_ssm.service.ItemsService;
import com.xf.maven_ssm.service.SpringThreadPollTestService;
import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class ItemsTest {
    /**
     * dao代码测试
     */
    @Test
    public void daoTest(){
        //得到spring容器
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("applicationContext.xml");
        //从spring容器中得到所需dao接口的代理对象
        ItemsDao itemsDao = applicationContext.getBean(ItemsDao.class);
        //调用方法
        Items items = itemsDao.findById(2);
        System.out.println(items.getName());
    }

    /**
     * service代码测试
     */
    @Test
    public void serviceTest(){
        //得到spring容器
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("applicationContext.xml");
        //从spring容器中得到所需dao接口的代理对象
        ItemsService itemsService = applicationContext.getBean(ItemsService.class);
        //调用方法
        Items items = itemsService.findById(3);
        System.out.println(items.getName());
    }

    @Test
    public void testSpringThreadPool(){
        ClassPathXmlApplicationContext applicationContext = new ClassPathXmlApplicationContext("classpath:applicationContext.xml");
        SpringThreadPollTestService springThreadPollTestService = applicationContext.getBean(SpringThreadPollTestService.class);
        springThreadPollTestService.testTaskThread();
    }
}
