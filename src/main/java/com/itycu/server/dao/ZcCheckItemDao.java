package com.itycu.server.dao;

import java.util.List;
import java.util.Map;

import com.itycu.server.app.vo.pandian.CheckItemReportVO;
import com.itycu.server.app.vo.pandian.CheckItemVO;
import com.itycu.server.model.ZcEpcCheckItem;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import com.itycu.server.model.ZcCheckItem;

@Mapper
public interface ZcCheckItemDao {

    //@Select("select * from zc_check_item t where t.id = #{id}")
    ZcCheckItem getById(Long id);


    @Delete("delete from zc_check_item where id = #{id}")
    int delete(Long id);

    @Delete("delete from zc_check_item where zc_check_id = #{zc_check_id}")
    int deleteParent(Long zc_check_id);

    int update(@Param("zcCheckItem")  ZcCheckItem zcCheckItem);

    /**
     * 批量更新
     * @param zcCheckItem
     * @return
     */
    int updates(List<ZcCheckItem> zcCheckItem);
    
    @Options(useGeneratedKeys = true, keyProperty = "id")
    @Insert("insert into zc_check_item(zc_check_id, zc_id, result, del, bz, finish_time) values(#{zcCheckId}, #{zcId}, #{result}, #{del}, #{bz}, #{finishTime})")
    int save(ZcCheckItem zcCheckItem);

    int saves(List<ZcCheckItem> zcCheckItem);
    
    int count(@Param("params") Map<String, Object> params);

    List<ZcCheckItem> list(@Param("params") Map<String, Object> params, @Param("offset") Integer offset, @Param("limit") Integer limit);


    List<ZcCheckItem> list2(@Param("checkId") long  checkId, @Param("offset") Integer offset, @Param("limit") Integer limit);



    @Select("select * from zc_check_item t order by id")
    List<ZcCheckItem> listAll();

    @Select("select * from zc_check_item t where t.Ccode = #{Ccode}")
    ZcCheckItem getByCcode(String Ccode);

    @Select("select max(ccode) from zc_check_item t")
    String getMaxCcode();


    int  saveZcItem(@Param("model") ZcCheckItem  model);



    List<ZcCheckItem> queryAllZcCheckItem(@Param("checkId") long  checkId);


    /**
     * 查询第一次盘点异常的数据
     * @param checkId 盘点id
     * @return 返回的再次盘点的数据
     */
    List<ZcCheckItem> queryCheckFailItem(@Param("checkId") long  checkId);


    List<ZcEpcCheckItem> queryNotFullCheckItem(@Param("checkId") long  checkId);



   int queryProfitCount(@Param("checkId") long checkId);


    int  updateItemStatusByZid(@Param("checkId") long  checkId,@Param("zcId") long  zcId,@Param("status") long  status);

    List<ZcCheckItem>  queryRecheckId(@Param("checkId") long  checkId);


    int  deleteZcItem(@Param("checkId") long  checkId);

    List<Map<String,Object>> listDetailByZcCheckId(@Param("zcCheckId") Long zcCheckId);


    /**
     * 同一个人或者部门是否已经创建了
     * @return
     */
    int checkHasCreatedByCreateByAndDept(@Param("creteBy") long   creteBy,@Param("deptId") long  deptId);

    int checkHasCreatedByCreateByAndDept2(@Param("creteBy") long   creteBy,@Param("deptId") long  deptId,@Param("profit") int  profit);


    List<ZcEpcCheckItem>  queryPanYingCheckItem(@Param("zcCheckId") long  zcCheckId);


    ZcCheckItem  findZcItemInZcCheckSubList(@Param("zcId") long  zcId,@Param("zcCheckId") Long  zc_check_id);


    List<CheckItemVO> queryCheckItemListById(@Param("id") long  id, @Param("offset") long  offset, @Param("limit") long  limit);



    List<CheckItemReportVO> getCheckReportInfoProfitLossList(@Param("params") Map<String, Object> params, @Param("offset") long  offset, @Param("limit") long  limit);

}
