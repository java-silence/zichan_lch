package com.itycu.server.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import com.itycu.server.model.BudgetBuy;

@Mapper
public interface BudgetBuyDao {

    BudgetBuy getById(Long id);

    @Delete("delete from budget_buy where id = #{id}")
    int delete(Long id);

    int update(BudgetBuy budgetBuy);
    
    @Options(useGeneratedKeys = true, keyProperty = "id")
    @Insert("insert into budget_buy(budgetBuyNum, userId, applyDeptId, applyTime, flowid, stepid, status, type, del, createTime, updateTime) values(#{budgetBuyNum}, #{userId}, #{applyDeptId}, #{applyTime}, #{flowid}, #{stepid}, #{status}, #{type}, #{del}, #{createTime}, #{updateTime})")
    int save(BudgetBuy budgetBuy);
    
    int count(@Param("params") Map<String, Object> params);

    List<BudgetBuy> list(@Param("params") Map<String, Object> params, @Param("offset") Integer offset, @Param("limit") Integer limit);

    @Select("select * from budget_buy t order by id")
    List<BudgetBuy> listAll();

    @Select("select * from budget_buy t where t.Ccode = #{Ccode}")
    BudgetBuy getByCcode(String Ccode);

    @Select("select max(ccode) from budget_buy t")
    String getMaxCcode();
}
