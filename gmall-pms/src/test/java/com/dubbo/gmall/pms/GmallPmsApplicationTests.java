package com.dubbo.gmall.pms;

import com.dubbo.gmall.pms.entity.Brand;
import com.dubbo.gmall.pms.entity.Product;
import com.dubbo.gmall.pms.service.BrandService;
import com.dubbo.gmall.pms.service.ProductService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class GmallPmsApplicationTests {

    @Autowired
    private ProductService productService;
    @Autowired
    private BrandService brandService;

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

}
