package com.itycu.server.dao;

import com.itycu.server.model.BudgetBuyItem;
import com.itycu.server.model.BudgetData;
import com.itycu.server.model.BudgetDataItem;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 预算数据表(年度预算和月度预算)(BudgetDataItem)表数据库访问层
 *
 * @author makejava
 * @since 2020-03-18 09:05:48
 */
@Mapper
public interface BudgetDataDao {

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    BudgetDataItem queryById(Integer id);

    /**
     * 查询指定行数据
     *
     * @param offset 查询起始位置
     * @param limit 查询条数
     * @return 对象列表
     */
    List<BudgetDataItem> queryAllByLimit(@Param("offset") int offset, @Param("limit") int limit);


    /**
     * 通过实体作为筛选条件查询
     *
     * @param budgetDataItem 实例对象
     * @return 对象列表
     */
    List<BudgetDataItem> queryAll(BudgetDataItem budgetDataItem);

    /**
     * 新增数据
     *
     * @param budgetDataItem 实例对象
     * @return 影响行数
     */
    int insert(BudgetDataItem budgetDataItem);

    /**
     * 修改数据
     *
     * @param budgetDataItem 实例对象
     * @return 影响行数
     */
    int update(BudgetDataItem budgetDataItem);

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 影响行数
     */
    int deleteById(Integer id);




    int  saveBudgetDataInfo(@Param("budgetData") BudgetData budgetData);


    int  saveBudgetDataItemInfo(@Param("list") List<BudgetDataItem> list);

}