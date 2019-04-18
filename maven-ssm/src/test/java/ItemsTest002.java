import com.xf.maven_ssm.dao.mapper.ItemsMapper;
import com.xf.maven_ssm.dao.pojo.Items;
import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class ItemsTest002 {

    @Test
    public void daoTest001(){
        //得到spring容器
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("applicationContext.xml");
        //从spring容器中得到所需dao接口的代理对象
        ItemsMapper itemsMapper = applicationContext.getBean(ItemsMapper.class);
        //调用方法
        Items items = itemsMapper.selectByPrimaryKey(2);
        System.out.println(items.getName());
    }
}
