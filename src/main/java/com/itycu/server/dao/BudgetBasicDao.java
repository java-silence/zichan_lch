package com.itycu.server.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import com.itycu.server.model.BudgetBasic;

@Mapper
public interface BudgetBasicDao {

    BudgetBasic getById(Long id);

    @Delete("delete from budget_basic where id = #{id}")
    int delete(Long id);

    int update(BudgetBasic budgetBasic);
    
    @Options(useGeneratedKeys = true, keyProperty = "id")
    @Insert("insert into budget_basic(buggetBasicNum, basicWage, allowance, reward, socialSecurity, retirementPay, eldAllowance, retirementCosts, pension, livingAllowance, userId, applyDeptId, applyTime, flowid, stepid, flowStatus, step1, step2, step3, step4, status, type, del, createTime, updateTime) values(#{buggetBasicNum}, #{basicWage}, #{allowance}, #{reward}, #{socialSecurity}, #{retirementPay}, #{eldAllowance}, #{retirementCosts}, #{pension}, #{livingAllowance}, #{userId}, #{applyDeptId}, #{applyTime}, #{flowid}, #{stepid}, #{flowStatus}, #{step1}, #{step2}, #{step3}, #{step4}, #{status}, #{type}, #{del}, #{createTime}, #{updateTime})")
    int save(BudgetBasic budgetBasic);
    
    int count(@Param("params") Map<String, Object> params);

    List<BudgetBasic> list(@Param("params") Map<String, Object> params, @Param("offset") Integer offset, @Param("limit") Integer limit);

    @Select("select * from budget_basic t order by id")
    List<BudgetBasic> listAll();

    @Select("select * from budget_basic t where t.Ccode = #{Ccode}")
    BudgetBasic getByCcode(String Ccode);

    @Select("select max(ccode) from budget_basic t")
    String getMaxCcode();
}
