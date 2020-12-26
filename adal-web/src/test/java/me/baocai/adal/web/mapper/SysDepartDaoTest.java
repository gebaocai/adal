package me.baocai.adal.web.mapper;

import lombok.extern.slf4j.Slf4j;
import me.baocai.adal.web.AdalWebApplicationTests;
import me.baocai.adal.web.entity.SysDepart;
import me.baocai.adal.web.model.SysDepartTreeModel;
import me.baocai.adal.web.service.SysDepartService;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * @author gebaocai
 * @date 2020/12/26 10:41
 */
@Slf4j
public class SysDepartDaoTest extends AdalWebApplicationTests {

    @Autowired
    private SysDepartService sysDepartService;

    @Test
    public void testSelect() {
        List<SysDepartTreeModel> treeModelList = sysDepartService.queryTreeList();
//        Assert.assertEquals(true, treeModelList.size() > 0);
        log.info("【userList】= {}", treeModelList);
    }

    @Test
    public void testAdd() {
        SysDepart sysDepart = new SysDepart();
        sysDepart.setDepartName("公司1");
        sysDepart.setParentId("1342850005937033216");
//        sysDepartService.saveDepartData(sysDepart, "1234");

        for (int i = 0; i < 1000; i++) {
            sysDepart = new SysDepart();
            sysDepart.setDepartName("公司" + i);
            sysDepart.setParentId("1342850656117067776");
            sysDepartService.saveDepartData(sysDepart, "1234");
        }
    }

    @Test
    public void testEdit() {
        SysDepart sysDepart = new SysDepart();
        sysDepart.setDepartName("总公司");
        sysDepart.setId("1342850005937033216");
        boolean result = sysDepartService.updateDepartDataById(sysDepart, "4321");
        Assert.assertTrue(result);
    }

    @Test
    public void testDelete() {
        boolean result = sysDepartService.delete("1342850006310326272");
        Assert.assertTrue(result);
    }
}
