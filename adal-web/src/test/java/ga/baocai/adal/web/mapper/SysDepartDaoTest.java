package ga.baocai.adal.web.mapper;

import cn.hutool.core.collection.CollectionUtil;
import ga.baocai.adal.web.playload.Depart;
import lombok.extern.slf4j.Slf4j;
import ga.baocai.adal.web.AdalWebApplicationTests;
import ga.baocai.adal.web.entity.SysDepart;
import ga.baocai.adal.web.model.SysDepartTreeModel;
import ga.baocai.adal.web.service.SysDepartService;
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

    @Test
    public void getDataScopeListByDataScopeType() {
        List<String> departIds = CollectionUtil.newArrayList();
        departIds.add("1427582230926069760");
        List<String> departList = sysDepartService.getDataScopeListByDataScopeType(2, departIds);
        log.info("【departList】= {}", departList);
    }
}
