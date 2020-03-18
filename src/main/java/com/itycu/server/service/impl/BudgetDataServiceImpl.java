package com.itycu.server.service.impl;

import com.itycu.server.dao.BudgetDataDao;
import com.itycu.server.model.BudgetData;
import com.itycu.server.model.BudgetDataItem;
import com.itycu.server.model.SysUser;
import com.itycu.server.service.BudgetDataService;
import com.itycu.server.utils.UserUtil;
import lombok.ToString;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

/**
 * 预算数据表(年度预算和月度预算)(BudgetDataItem)表服务实现类
 *
 * @author makejava
 * @since 2020-03-18 09:05:57
 */
@Service("budgetDataService")
public class BudgetDataServiceImpl implements BudgetDataService {
    @Resource
    private BudgetDataDao budgetDataDao;

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    @Override
    public BudgetDataItem queryById(Integer id) {
        return this.budgetDataDao.queryById(id);
    }

    /**
     * 查询多条数据
     *
     * @param offset 查询起始位置
     * @param limit  查询条数
     * @return 对象列表
     */
    @Override
    public List<BudgetDataItem> queryAllByLimit(int offset, int limit) {
        return this.budgetDataDao.queryAllByLimit(offset, limit);
    }

    /**
     * 新增数据
     *
     * @param budgetDataItem 实例对象
     * @return 实例对象
     */
    @Override
    public BudgetDataItem insert(BudgetDataItem budgetDataItem) {
        this.budgetDataDao.insert(budgetDataItem);
        return budgetDataItem;
    }

    /**
     * 修改数据
     *
     * @param budgetDataItem 实例对象
     * @return 实例对象
     */
    @Override
    public BudgetDataItem update(BudgetDataItem budgetDataItem) {
        this.budgetDataDao.update(budgetDataItem);
        return this.queryById(budgetDataItem.getId());
    }

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    @Override
    public boolean deleteById(Integer id) {
        return this.budgetDataDao.deleteById(id) > 0;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public int saveBudgetDataInfo(BudgetData budgetData) {
        if (null != budgetData) {
            List<BudgetDataItem> list = budgetData.getBudgetDataList();
            if (CollectionUtils.isEmpty(list)) {
                return 0;
            }
            String budgetDataId = createNum();
            budgetData.setUserId(UserUtil.getLoginUser().getId().intValue());
            budgetData.setApplyDeptId(list.get(0).getBudgetDeptId());
            budgetData.setBudgetDataId(budgetDataId);
            int result = budgetDataDao.saveBudgetDataInfo(budgetData);
            if (result>0) {
                for (BudgetDataItem budgetDataItem : list) {
                    budgetDataItem.setBudgetDataId(budgetDataId);
                    budgetDataItem.setBudgetKind(String.valueOf(budgetData.getBudgetKind()));
                }
            }
            return budgetDataDao.saveBudgetDataItemInfo(list);
        }
        return 0;
    }


    /**
     * 创建预算编号
     * @return
     */
    private synchronized  String  createNum(){
        return  String.valueOf(new Date().getTime());
    }

}