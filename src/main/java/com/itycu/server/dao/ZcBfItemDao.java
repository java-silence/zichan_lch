package com.itycu.server.dao;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.*;

import com.itycu.server.model.ZcBfItem;

@Mapper
public interface ZcBfItemDao {

    //@Select("select * from zc_bf_item t where t.id = #{id}")
    ZcBfItem getById(Long id);

    @Delete("delete from zc_bf_item where id = #{id}")
    int delete(Long id);

    int update(ZcBfItem zcBfItem);
    
    @Options(useGeneratedKeys = true, keyProperty = "id")
    @Insert("insert into zc_bf_item(zcbfCwItemid,zc_bf_id, zc_id,sy_dept_id,gl_dept_id, cwb_status, shb_status, bz,del, create_time, update_time) values(#{zcbfCwItemid},#{zcBfId}, #{zcId}, #{sy_dept_id},#{gl_dept_id},#{cwbStatus}, #{shbStatus}, #{bz},#{del}, #{createTime}, #{updateTime})")
    int save(ZcBfItem zcBfItem);

    int saves(@Param("zcBfItemList") List<ZcBfItem> zcBfItemList, @Param("zcBfid") Long zcBfid,@Param("applyUserId") Long applyUserId);
    
    int count(@Param("params") Map<String, Object> params);

    List<ZcBfItem> list(@Param("params") Map<String, Object> params, @Param("offset") Integer offset, @Param("limit") Integer limit);

    @Select("select * from zc_bf_item t order by id")
    List<ZcBfItem> listAll();

    @Select("select * from zc_bf_item t where t.Ccode = #{Ccode}")
    ZcBfItem getByCcode(String Ccode);

    @Select("select max(ccode) from zc_bf_item t")
    String getMaxCcode();

    @Select("select * from zc_bf_item t where t.zc_bf_id = #{zcBfId} and t.del = 0")
    List<ZcBfItem> listByZcBfId(@Param("zcBfId") Long zcBfId);

    @Select("select * from zc_bf_item t where t.cwb_status is not null or t.del = 1")
    List<ZcBfItem> listFinishedByZcBfId(@Param("zcBfId") Long zcBfId);

    void updateStatus(@Param("bfid") Long bfid, @Param("status") Integer status);

    List<Map<String,Object>> listDetailByZcBfId(@Param("zcBfId") Long zcBfId);

    List<Map<String,Object>> listByItemIds(@Param("zcbfItemIds") List zcbfItemIds);


    /**
     * 根据待办查询资产报废子项
     * @param todoId
     * @return
     */
    List<Map<String,Object>> listDetailByFlowTodoIdNew(@Param("todoId") Long todoId);

    /**
     * 根据待办查询资产报废子项
     * @param todoId
     * @return
     */
    List<Map<String,Object>> listDetailByFlowTodoIdNewByType(@Param("todoId") Long todoId,@Param("type") String type);

    // 批量更新状态
    void updateListStatus(@Param("type") String type, @Param("status")Integer status, @Param("bfItemIds")List<Long> bfItemIds,
                          @Param("username")String username,@Param("deptname")String deptname);

    int countCheck(@Param("type") String type,@Param("zcbfid") Long zcbfid);

    void updateShStatusNull(@Param("bfItemId") Long bfItemId);

    @Delete("delete from zc_bf_item where zc_bf_id = #{zcBfId}")
    void deleteByZcBfId(@Param("zcBfId") Long zcBfId);

    int countYearSubmit(@Param("deptid") Long deptid);

    int countByIds(@Param("itemList") ArrayList<Long> itemList);

    // void updateStatusByIds(@Param("list") ArrayList<Long> list, @Param("status") Integer status);

    List<ZcBfItem> listByIds(@Param("zcbfItemIds") List<Long> zcbfItemIds);

    // 查询本年度已经提交的管使用部门
    List<ZcBfItem> listSyDeptThisYear(@Param("deptids") List<Long> deptids, @Param("updateTime")Date updateTime);

    void updateZcBfCwitem(@Param("zcBfCwitemId") Long zcBfCwitemId, @Param("bfitemList") ArrayList<Long> bfitemList);

    List<Map<String,Object>> listShenqing(@Param("zcbfCwItemId") Long zcbfCwItemId);

    List<Map<String,Object>> listShenqingByItemId(@Param("bfitemIds") List<Long> bfitemIds);

    @Update("update zc_bf_item set identifyFileName = #{name},identifyFileUrl = #{url} where id = #{id}")
    void updateById(@Param("id") Long id, @Param("name") String name,@Param("url") String url);

    @Update("update zc_bf_item set identifyContent = #{identifyContent} where id = #{flowItemId}")
    void updateItem(@Param("flowItemId") Long flowItemId, @Param("identifyContent") String identifyContent);

    void updateZcbfItemList(@Param("name") String name, @Param("url") String url, @Param("ids") List<String> ids);
}
