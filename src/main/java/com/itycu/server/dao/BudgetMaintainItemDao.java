package com.itycu.server.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import com.itycu.server.model.BudgetMaintainItem;

@Mapper
public interface BudgetMaintainItemDao {

    //@Select("select * from budget_maintain_item t where t.id = #{id}")
    BudgetMaintainItem getById(Long id);

    @Delete("delete from budget_maintain_item where id = #{id}")
    int delete(Long id);

    int update(BudgetMaintainItem budgetMaintainItem);
    
    @Options(useGeneratedKeys = true, keyProperty = "id")
    @Insert("insert into budget_maintain_item(budgetMaintainId, zcId, epcid, zcCodenum, zcName, zcCategoryId, specification, model, num, maintainPrice, maintainTotalPrice, maintainReason, fileName, fileUrl, step1, step2, step3, step4, status, type, del, createTime, updateTime) values(#{budgetMaintainId}, #{zcId}, #{epcid}, #{zcCodenum}, #{zcName}, #{zcCategoryId}, #{specification}, #{model}, #{num}, #{maintainPrice}, #{maintainTotalPrice}, #{maintainReason}, #{fileName}, #{fileUrl}, #{step1}, #{step2}, #{step3}, #{step4}, #{status}, #{type}, #{del}, #{createTime}, #{updateTime})")
    int save(BudgetMaintainItem budgetMaintainItem);
    
    int count(@Param("params") Map<String, Object> params);

    List<BudgetMaintainItem> list(@Param("params") Map<String, Object> params, @Param("offset") Integer offset, @Param("limit") Integer limit);

    @Select("select * from budget_maintain_item t order by id")
    List<BudgetMaintainItem> listAll();

    @Select("select * from budget_maintain_item t where t.Ccode = #{Ccode}")
    BudgetMaintainItem getByCcode(String Ccode);

    @Select("select max(ccode) from budget_maintain_item t")
    String getMaxCcode();
}
