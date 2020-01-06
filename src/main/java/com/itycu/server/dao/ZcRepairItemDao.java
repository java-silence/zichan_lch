package com.itycu.server.dao;

import java.util.List;
import java.util.Map;

import com.itycu.server.dto.ZcRepairItemDto;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import com.itycu.server.model.ZcRepairItem;

@Mapper
public interface ZcRepairItemDao {

    //@Select("select * from zc_repair_item t where t.id = #{id}")
    ZcRepairItem getById(Long id);

    @Delete("delete from zc_repair_item where id = #{id}")
    int delete(Long id);

    int update(ZcRepairItem zcRepairItem);
    
    @Options(useGeneratedKeys = true, keyProperty = "id")
    @Insert("insert into zc_repair_item(zc_repair_id, zc_id, repair_des, apply_id, apply_dept_id, repair_mode, deliver_mode, out_company, out_address, out_username, out_phone, out_fee, repair_time, front_description, front_pics, back_description, back_pics, status, del, bz, shb_status, qr_status, create_by, update_by, create_time, update_time, repair_address,gl_dept_id,front_pics_url,back_pics_url) values(#{zcRepairId}, #{zcId}, #{repairDes}, #{applyId}, #{applyDeptId}, #{repairMode}, #{deliverMode}, #{outCompany}, #{outAddress}, #{outUsername}, #{outPhone}, #{outFee}, #{repairTime}, #{frontDescription}, #{frontPics}, #{backDescription}, #{backPics}, #{status}, #{del}, #{bz}, #{shbStatus}, #{qrStatus}, #{createBy}, #{updateBy}, #{createTime}, #{updateTime}, #{repairAddress},#{glDeptId},#{frontPicsUrl},#{backPicsUrl})")
    int save(ZcRepairItem zcRepairItem);

    int saves(@Param("zcRepairItems")List<ZcRepairItem> zcRepairItems, @Param("zcReId") Long zcReId);
    
    int count(@Param("params") Map<String, Object> params);

    List<ZcRepairItemDto> list(@Param("params") Map<String, Object> params, @Param("offset") Integer offset, @Param("limit") Integer limit);

    @Select("select * from zc_repair_item t order by id")
    List<ZcRepairItem> listAll();

    List<ZcRepairItemDto> listByCondition(@Param("params") Map<String, Object> params);

    @Select("select * from zc_repair_item t where t.Ccode = #{Ccode}")
    ZcRepairItem getByCcode(String Ccode);

    @Select("select max(ccode) from zc_repair_item t")
    String getMaxCcode();

    @Select("select * from v_zc_repair_item t where t.zc_repair_id = #{zcReId} and t.del = 0")
    List<ZcRepairItemDto> listByZcReId(@Param("zcReId") Long zcReId);

    @Select("select * from v_zc_repair_item t where t.zc_repair_id = #{zcReId} and t.gl_dept_id = #{glDeptId} and t.del = 0")
    List<ZcRepairItemDto> listByZcReIdAndGldept(@Param("zcReId") Long zcReId,@Param("glDeptId") Long glDeptId);

    void updateStatus(@Param("params") Map<String, Object> params);

    List<ZcRepairItemDto> listDetailByFlowTodoIdNew(@Param("todoId") Long todoId);
}
