package com.itycu.server.dao;

import java.util.List;
import java.util.Map;

import com.itycu.server.app.vo.pandian.ZcCheckDetailReportVO;
import com.itycu.server.dto.ZcCheckDto;
import com.itycu.server.dto.ZcInfoDto;
import com.itycu.server.model.*;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import org.springframework.security.core.parameters.P;

@Mapper
public interface ZcCheckDao {

    //@Select("select * from zc_check t where t.id = #{id}")
    ZcCheck getById(Long id);

    @Delete("delete from zc_check where id = #{id}")
    int delete(Long id);

    int update(@Param("zcCheck") ZcCheck zcCheck);

    @Options(useGeneratedKeys = true, keyProperty = "id")
    @Insert("insert into zc_check(check_dept_id, check_user_id, check_time, status,total, del, bz, create_by, update_by, create_time, update_time,re_check,profit) values(#{checkDeptId}, #{checkUserId}, #{checkTime}, #{status},#{total}, #{del}, #{bz}, #{createBy}, #{updateBy}, #{createTime}, #{updateTime},#{reCheck},#{profit})")
    int save(ZcCheck zcCheck);

    int count(@Param("params") Map<String, Object> params);

    List<ZcCheck> list(@Param("params") Map<String, Object> params, @Param("offset") Integer offset, @Param("limit") Integer limit);

    @Select("select * from zc_check t order by id")
    List<ZcCheck> listAll();

    @Select("select * from zc_check t where t.Ccode = #{Ccode}")
    ZcCheck getByCcode(String Ccode);

    @Select("select max(ccode) from zc_check t")
    String getMaxCcode();


    int queryCountManagerDeptIds(@Param("params") Map<String, Object> map);


    int updateItemStatus(@Param("list") List<Long> errorItemList);

    int updateZcStatus(@Param("zcCheckId") long zcCheckId, @Param("status") int status);


    int queryOldCheckId(@Param("zcCheckId") long zcCheckId);

    int insertReal(@Param("newId") long newId, @Param("oldId") long oldId);


    String queryZcCheckOperator(@Param("zcCheckId") long zcCheckId);


    int getNormalItem(@Param("id") long id);


    int getTotalZcItem(@Param("id") long id);


    int updateZcCheckBh(@Param("zcCheckId") long zcCheckId, @Param("bh") long bh);

    int queryZcCheckCount(@Param("jx") String zcCheckId);

    int deleteZcCheck(@Param("zcCheckId") long zcCheckId);


    int batchZcItem(@Param("list") List<ZcCheckItem> list);



    ZcInfoDto getByEpcId(@Param("epcId") String epcId);





    String  queryPandianUserName(@Param("id") long  id,@Param("checkUserId") long  checkUserId);





    List<ZcItemDeptCountInfo>  queryPanYinZcList(@Param("id") long  id);

    /**
     * 更新父盘点表为盘盈状态
     *
     * @param zcCheckId
     * @param profit
     * @return
     */
    int updateZcCheck(@Param("zcCheckId") long zcCheckId, @Param("profit") long profit);

    String getAccountName(@Param("id") String id);

    /**
     * 查询一个部门下面管理的所有的部门id
     *
     * @return 返回管理的部门列表
     */
    List<ZcCheck> queryManagerDeptIds(@Param("params") Map<String, Object> params, @Param("offset") Integer offset, @Param("limit") Integer limit);


   int   queryZcCheckCountById(@Param("id") long id);


   int queryDeptZcCheckCountById(@Param("id") long id);


    ZcCheckDetailReportVO getCheckReportInfoById(@Param("id") long id);


    ZcCheck getZcInfoDownLoadById(@Param("id") long id);
}
