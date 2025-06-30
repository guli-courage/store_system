package mapper.order;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import system.DoApp;
import system.mapper.OrderMapper;
import system.pojo.Order;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = DoApp.class)
public class insert {

    @Autowired
    @Qualifier("OrderMapper")
    private OrderMapper mapper;
    @Test
    public void insert() {
        Order order = new Order();
        order.setOrdersUserId(2);
        order.setOrdersAmount(0.0);
        order.setOrdersStatus("pending");
        int i = mapper.insertOrder(order);
        System.out.println("新订单ID"+order.getOrdersId());
    }
}
