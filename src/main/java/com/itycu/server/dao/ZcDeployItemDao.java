package com.itycu.server.dao;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.itycu.server.model.ZcBfItem;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import com.itycu.server.model.ZcDeployItem;

@Mapper
public interface ZcDeployItemDao {

    //@Select("select * from zc_deploy_item t where t.id = #{id}")
    ZcDeployItem getById(Long id);

    @Delete("delete from zc_deploy_item where id = #{id}")
    int delete(Long id);

    int update(ZcDeployItem zcDeployItem);

    @Options(useGeneratedKeys = true, keyProperty = "id")
    @Insert("insert into zc_deploy_item(zc_deploy, zc_id, sy_dept_id, gl_dept_id, front_dept_id, frontUser, frontUsername,back_dept_id, backUser, backUsername,front_dept_status, back_dept_status, cwb_status, status, bz, del, create_time, update_time) values(#{zcDeploy}, #{zcId}, #{syDeptId}, #{glDeptId}, #{frontDeptId}, #{frontUser}, #{frontUsername},#{backDeptId}, #{backUser}, #{backUsername},#{frontDeptStatus}, #{backDeptStatus}, #{cwbStatus}, #{status}, #{bz}, #{del}, #{createTime}, #{updateTime})")
    int save(ZcDeployItem zcDeployItem);

    int count(@Param("params") Map<String, Object> params);

    List<ZcDeployItem> list(@Param("params") Map<String, Object> params, @Param("offset") Integer offset, @Param("limit") Integer limit);

    @Select("select * from zc_deploy_item t order by id")
    List<ZcDeployItem> listAll();

    @Select("select * from zc_deploy_item t where t.Ccode = #{Ccode}")
    ZcDeployItem getByCcode(String Ccode);

    @Select("select max(ccode) from zc_deploy_item t")
    String getMaxCcode();

    void saves(@Param("zcDeployItemList") List<ZcDeployItem> zcDeployItemList, @Param("deployId") Long deployId,
               @Param("applyUserId")Long applyUserId, @Param("backDeptId")Long backDeptId);

    @Select("select * from zc_deploy_item t where t.zc_deploy = #{zcDeployId} and t.del = 0")
    List<ZcDeployItem> listByZcDeployId(@Param("zcDeployId") Long zcDeployId);

    void updateStatus(@Param("deployId") Long deployId, @Param("status") Integer status);

    List<Map<String,Object>> listDetailByZcDeployId(@Param("zcDeployId") Long zcDeployId,@Param("cw") String cw);

    List<Map<String,Object>> listDetailByFlowTodoIdNew(@Param("todoId") Long todoId);

    // 批量更新状态
    void updateListStatus(@Param("type") String type, @Param("status")Integer status, @Param("deployItemIds")List<Long> deployItemIds,
                          @Param("userId")Long userId,@Param("nickname")String nickname);

    int countCheck(@Param("type") String type,@Param("zcDeployId") Long zcDeployId);

    List<Map<String,Object>> listByZcDeployIdNew(@Param("deployId") Long deployId);

    @Delete("delete from zc_deploy_item where zc_deploy = #{zcDeployId}")
    void deleteByZcDeployId(@Param("zcDeployId") Long zcDeployId);
}
