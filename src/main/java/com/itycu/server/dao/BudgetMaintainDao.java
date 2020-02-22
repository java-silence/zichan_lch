package com.itycu.server.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import com.itycu.server.model.BudgetMaintain;

@Mapper
public interface BudgetMaintainDao {

    //@Select("select * from budget_maintain t where t.id = #{id}")
    BudgetMaintain getById(Long id);

    @Delete("delete from budget_maintain where id = #{id}")
    int delete(Long id);

    int update(BudgetMaintain budgetMaintain);
    
    @Options(useGeneratedKeys = true, keyProperty = "id")
    @Insert("insert into budget_maintain(budgetMaintainNum, userId, applyDeptId, applyTime, flowid, stepid, status, type, del, createTime, updateTime) values(#{budgetMaintainNum}, #{userId}, #{applyDeptId}, #{applyTime}, #{flowid}, #{stepid}, #{status}, #{type}, #{del}, #{createTime}, #{updateTime})")
    int save(BudgetMaintain budgetMaintain);
    
    int count(@Param("params") Map<String, Object> params);

    List<BudgetMaintain> list(@Param("params") Map<String, Object> params, @Param("offset") Integer offset, @Param("limit") Integer limit);

    @Select("select * from budget_maintain t order by id")
    List<BudgetMaintain> listAll();

    @Select("select * from budget_maintain t where t.Ccode = #{Ccode}")
    BudgetMaintain getByCcode(String Ccode);

    @Select("select max(ccode) from budget_maintain t")
    String getMaxCcode();
}
