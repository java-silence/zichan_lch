package com.itycu.server.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import com.itycu.server.model.BudgetBuyItem;

@Mapper
public interface BudgetBuyItemDao {

    //@Select("select * from budget_buy_item t where t.id = #{id}")
    BudgetBuyItem getById(Long id);

    @Delete("delete from budget_buy_item where id = #{id}")
    int delete(Long id);

    int update(BudgetBuyItem budgetBuyItem);
    
    @Options(useGeneratedKeys = true, keyProperty = "id")
    @Insert("insert into budget_buy_item(zcName, zcCategoryId, brand, specification, model, num, price, totalprice, useDes, fileName, fileUrl, step1, step2, step3, step4, status, type, del, createTime, updateTime) values(#{zcName}, #{zcCategoryId}, #{brand}, #{specification}, #{model}, #{num}, #{price}, #{totalprice}, #{useDes}, #{fileName}, #{fileUrl}, #{step1}, #{step2}, #{step3}, #{step4}, #{status}, #{type}, #{del}, #{createTime}, #{updateTime})")
    int save(BudgetBuyItem budgetBuyItem);
    
    int count(@Param("params") Map<String, Object> params);

    List<BudgetBuyItem> list(@Param("params") Map<String, Object> params, @Param("offset") Integer offset, @Param("limit") Integer limit);

    @Select("select * from budget_buy_item t order by id")
    List<BudgetBuyItem> listAll();

    @Select("select * from budget_buy_item t where t.Ccode = #{Ccode}")
    BudgetBuyItem getByCcode(String Ccode);

    @Select("select max(ccode) from budget_buy_item t")
    String getMaxCcode();
}
