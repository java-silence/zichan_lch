package com.itycu.server.dao;

import java.util.List;
import java.util.Map;

import com.itycu.server.dto.RepairVO;
import org.apache.ibatis.annotations.*;

import com.itycu.server.model.Repair;

@Mapper
public interface RepairDao {

    @Select("select * from v_zx_repair t where t.id = #{id}")
    RepairVO getById(Long id);

    @Select("select * from v_zx_repair_todo t where t.todoid = #{id}")
    RepairVO gettodoById(Long id);

    @Select("select * from v_zx_repair_todo t where t.id = #{id} limit 0,1")
    RepairVO gettodoByBizid(Long id);

    @Select("select * from v_zx_repair where deptid = #{deptid} and DATE_FORMAT(createTime,'%Y-%m')  = #{cmonth} ")
    List<RepairVO> listByMonthAndDept(@Param("deptid") Long deptid, @Param("cmonth") String cmonth);

    @Delete("delete from zx_repair where id = #{id}")
    int delete(Long id);

    int update(Repair repair);
    
    @Options(useGeneratedKeys = true, keyProperty = "id")
    @Insert("insert into zx_repair(xtid, lbid, deptid, xjqyid, whqyid, bizdate, eqpid, eqpcode, eqpname, description, descpic, repair, repairman, repaircost, material, status, userid, memo, del, biztype, createby, createTime, updateby, updateTime, auditby, auditTime, c01, c02, c03, inspid, flowid, stepid, gzid, gzmc) values(#{xtid}, #{lbid}, #{deptid}, #{xjqyid}, #{whqyid}, #{bizdate}, #{eqpid}, #{eqpcode}, #{eqpname}, #{description}, #{descpic}, #{repair}, #{repairman}, #{repaircost}, #{material}, #{status}, #{userid}, #{memo}, #{del}, #{biztype}, #{createby}, #{createTime}, #{updateby}, #{updateTime}, #{auditby}, #{auditTime}, #{c01}, #{c02}, #{c03}, #{inspid}, #{flowid}, #{stepid}, #{gzid}, #{gzmc})")
    int save(Repair repair);

    int saveFiles(@Param("repairId") Long repairId, @Param("biztype") String biztype, @Param("fileIds") List<String> fileIds);

    int saves(@Param("repairList") List<Repair> repairList, @Param("inspid") Long inspid, @Param("createby") Long createby);
    
    int count(@Param("params") Map<String, Object> params);

    List<RepairVO> list(@Param("params") Map<String, Object> params, @Param("offset") Integer offset, @Param("limit") Integer limit);

    int todocount(@Param("params") Map<String, Object> params);

    List<RepairVO> todolist(@Param("params") Map<String, Object> params, @Param("offset") Integer offset, @Param("limit") Integer limit);

    @Select("select * from zx_repair t order by id")
    List<Repair> listAll();

    @Update("update zx_repair set stepid = stepid +1 where id = #{id}")
    int toNextStep(Long id);

}
