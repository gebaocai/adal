package ga.baocai.adal.web.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import ga.baocai.adal.web.entity.SysRole;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * <p>
 * 角色 DAO
 * </p>
 */
@Mapper
@Component
public interface SysRoleDao extends BaseMapper<SysRole> {
    /**
     * 根据用户id 查询角色列表
     *
     * @param userId 用户id
     * @return 角色列表
     */
    List<SysRole> getRolesByUserId(@Param("userId") String userId);

    SysRole getRolesByName(@Param("name") String name);
}
