package com.itycu.server.dao;

import com.itycu.server.dto.EquipmentVO;
import com.itycu.server.model.Equipment;
import org.apache.ibatis.annotations.*;

import java.util.List;
import java.util.Map;

@Mapper
public interface EquipmentDao {

    @Select("select * from v_zx_equipment t where t.id = #{id}")
    EquipmentVO getById(Long id);

    @Delete("delete from zx_equipment where id = #{id}")
    int delete(Long id);

    int update(Equipment equipment);
    
    @Options(useGeneratedKeys = true, keyProperty = "id")
    @Insert("insert into zx_equipment(xtid, lbid, deptid, xjqyid, whqyid, cname, pid, isort, serialno, etype, factoryid, buildtime, techphone, techname, qualityperiod, maintainperiod, maintainbiao1, maintainbiao2, maintainbiao3, useperiod, equipmentcontent, equipmentstatus, adminid, addtime, isxun, iswei, tdesc, createby, createTime, updateby, updateTime, auditby, auditTime, status, del, memo, ctype, c01, c02, c03, whid,price,pinpai,anzhuang,zhuangfx,zhuangfw,zhuangwz) values(#{xtid}, #{lbid}, #{deptid}, #{xjqyid}, #{whqyid}, #{cname}, #{pid}, #{isort}, #{serialno}, #{etype}, #{factoryid}, #{buildtime}, #{techphone}, #{techname}, #{qualityperiod}, #{maintainperiod}, #{maintainbiao1}, #{maintainbiao2}, #{maintainbiao3}, #{useperiod}, #{equipmentcontent}, #{equipmentstatus}, #{adminid}, #{addtime}, #{isxun}, #{iswei}, #{tdesc}, #{createby}, #{createTime}, #{updateby}, #{updateTime}, #{auditby}, #{auditTime}, #{status}, #{del}, #{memo}, #{ctype}, #{c01}, #{c02}, #{c03},#{whid},#{price},#{pinpai},#{anzhuang},#{zhuangfx},#{zhuangfw},#{zhuangwz})")
    int save(Equipment equipment);

    int saves(List<EquipmentVO> equipmentVOS);
    
    int count(@Param("params") Map<String, Object> params);

    List<EquipmentVO> list(@Param("params") Map<String, Object> params, @Param("offset") Integer offset, @Param("limit") Integer limit);

    List<EquipmentVO> listByCondition(@Param("params") Map<String, Object> params);

    @Select("select * from zx_equipment t order by id")
    List<Equipment> listAll();

    @Select("select * from zx_equipment where deptid is null order by id")
    List<Equipment> listAdd();

    @Select("select * from zx_equipment where deptid is not null order by id")
    List<Equipment> listOldadd();

//    新设备安装更新收费站，出库，设备状态
    @Update("update zx_equipment set deptid = #{deptid}, xjqyid = #{xjqyid}, whqyid = #{whqyid}, whid= null, status = '2' where id= #{id}")
    Long updatenew(@Param("deptid") Long deptid, @Param("xjqyid") Long xjqyid, @Param("whqyid") Long whqyid, @Param("id") Long id);

//    旧设备更换去掉收费站，入库，设备状态
    @Update("update zx_equipment set deptid = null, xjqyid = null, whqyid = null, whid = #{whid}, status = '1' where id= #{id}")
    Long updateold(@Param("whid") int whid, @Param("id") Long id);
//    报废设备修改设备状态为9
    @Update("update zx_equipment set status = '9' where id= #{id}")
    Long updatebaofei(@Param("id") Long id);

    //更新设备状态
    @Update("update zx_equipment set status = #{status} where id= #{id}")
    Long updateStatus(@Param("id") Long id, @Param("status") String status);

    @Select("SELECT id,CONCAT(ifnull(serialno,''),'-', cname) as cname from zx_equipment where xjqyid=#{id} and isxun=1 ")
    List<Equipment> listxjsb(Long id);

    @Select("SELECT id,CONCAT(ifnull(serialno,''),'-', cname) as cname from zx_equipment where whid=#{whid} ")
    List<Equipment> listbywhid(Long whid);

    @Select("SELECT id,CONCAT(ifnull(serialno,''),'-',ifnull(etype,''),'-', cname) as cname from zx_equipment where lbid=#{lbid} and deptid=#{deptid}")
    List<Equipment> listbywlbid(@Param("lbid") Long lbid, @Param("deptid") Long deptid);

    @Select("SELECT id,CONCAT(ifnull(serialno,''),'-', cname) as cname from zx_equipment where status=#{status} and deptid=#{deptid}")
    List<Equipment> listbystatus(@Param("status") String status, @Param("deptid") Long deptid);

    @Select("SELECT id,CONCAT(ifnull(serialno,''),'-', cname) as cname from zx_equipment where xjqyid=#{id} and (isxun=0 or isxun is null) ")
    List<Equipment> listnoxjsb(Long id);

    @Update("update zx_equipment set isxun=0 where xjqyid=#{xjqyid}")
    int clearisxun(@Param("xjqyid") Long xjqyid);

    int isxunsaves(@Param("xjqyid") Long xjqyid, @Param("sbids") List<Long> sbids);

    @Select("SELECT id,CONCAT(ifnull(serialno,''),'-', cname,ifnull(etype,''),'-',status) as cname from zx_equipment where deptid=#{deptid} ")
    List<Equipment> listbydept(Long deptid);
}
