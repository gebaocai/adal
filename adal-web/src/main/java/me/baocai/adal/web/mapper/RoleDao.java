package me.baocai.adal.web.mapper;

import me.baocai.adal.web.model.Role;
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
public interface RoleDao {
    /**
     * 根据用户id 查询角色列表
     *
     * @param userId 用户id
     * @return 角色列表
     */
    List<Role> getRolesByUserId(@Param("userId") Long userId);
}
