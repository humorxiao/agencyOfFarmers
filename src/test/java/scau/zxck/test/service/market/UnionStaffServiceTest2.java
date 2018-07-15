package scau.zxck.test.service.market;

import java.io.FileNotFoundException;
import java.util.List;

import org.apache.log4j.Logger;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.util.Log4jConfigurer;

import com.alibaba.fastjson.JSON;

import scau.zxck.base.exception.BaseException;
import scau.zxck.entity.market.UnionStaff;
import scau.zxck.service.market.IUnionStaffService;

/**
 * Created by lishunpeng on 2015/11/13.
 */

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:config/spring/spring.xml"})
public class UnionStaffServiceTest2 {
    private static Logger logger = Logger.getLogger(UnionStaffServiceTest2.class);

    @Before
    public void before() {
        try {
            Log4jConfigurer.initLogging("classpath:config/log/log4j.properties");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    @Autowired
    private IUnionStaffService unionStaffService;

//    @Autowired
//    private RoleDao roleDao;

    @Test
    public void navServiceTest() throws BaseException {
        List<UnionStaff> unionStaffList = unionStaffService.listUnionStaff();

        logger.info("===========================================");
        logger.info(JSON.toJSONString(unionStaffList));
        logger.info("===========================================");

    }



}
