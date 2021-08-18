package me.baocai.adal.web.mapper;

import lombok.extern.slf4j.Slf4j;
import me.baocai.adal.web.AdalWebApplicationTests;
import me.baocai.adal.web.entity.SysDepart;
import me.baocai.adal.web.model.SysDepartTreeModel;
import me.baocai.adal.web.playload.Depart;
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

        for (int i = 0; i < 10; i++) {
            Depart depart = new Depart();
            depart.setDepartName("公司" + i);
            depart.setParentId("c6d7cb4deeac411cb3384b1b31278596");
            sysDepartService.saveDepartData(depart, "1234");
        }
    }

    @Test
    public void testEdit() {
        Depart depart = new Depart();
        depart.setDepartName("总公司");
        depart.setId("1342850005937033216");
        SysDepart sysDepart = sysDepartService.updateDepartDataById(depart, "4321");
        Assert.assertEquals("总公司", sysDepart.getDepartName());
    }

    @Test
    public void testDelete() {
        boolean result = sysDepartService.delete("1342850006310326272");
        Assert.assertTrue(result);
    }
}
