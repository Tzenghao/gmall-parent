package com.dubbo.gmall.pms;

import com.dubbo.gmall.pms.entity.Brand;
import com.dubbo.gmall.pms.entity.Product;
import com.dubbo.gmall.pms.service.BrandService;
import com.dubbo.gmall.pms.service.ProductService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;

@SpringBootTest
class GmallPmsApplicationTests {

    @Autowired
    private ProductService productService;
    @Autowired
    private BrandService brandService;
    @Autowired
    private StringRedisTemplate stringRedisTemplate;
    @Autowired
    private RedisTemplate redisTemplate;
    @Test
    void contextLoads() {
        /*Product byId = productService.getById(1);
        System.out.println(byId.getBrandName());*/
        //测试增删改在主库，查在从库
        /*Brand brand = new Brand();
        brand.setName("test");
        brandService.save(brand);
        System.out.println("保存成功。。。");*/
    }
    @Test
    void redisTemplateTest() {
        redisTemplate.opsForValue().set("hello","word");
        System.out.println("保存了数据");
        String hello = stringRedisTemplate.opsForValue().get("hello");
        System.out.println("保存的值是："+hello);
    }

    /**
     * redis中存数据默认是使用jdk的序列化方式存放数据(这种序列化方式不能跨语言)。
     *  json、xml是跨语言的
     */
    @Test
    void redisObjectTemplateTest() {
        Brand brand = new Brand();
        brand.setName("TML");
        redisTemplate.opsForValue().set("brand",brand);
        System.out.println("保存了一个对象");
        Brand tml = (Brand) redisTemplate.opsForValue().get("brand");
        System.out.println("刚才保存的对象是:"+tml);
    }
}
