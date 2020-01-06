package com.itycu.server.service.impl;

import com.itycu.server.dao.ZcCategoryDao;
import com.itycu.server.model.ZcCategory;
import com.itycu.server.service.ZcCategoryService;
import com.itycu.server.utils.UserUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ZcCategoryServiceImpl implements ZcCategoryService {
    private static final Logger log = LoggerFactory.getLogger("adminLogger");
    @Autowired
    private ZcCategoryDao zcCategoryDao;

    @Override
    public ZcCategory save(ZcCategory zcCategory) {
        zcCategory.setCreateBy(UserUtil.getLoginUser().getId());
        zcCategory.setDel(0);
        zcCategoryDao.save(zcCategory);
        log.debug("新增资产分类{}", zcCategory.getCreateBy() + zcCategory.getName());
        return zcCategory;
    }

    @Override
    public ZcCategory update(ZcCategory zcCategory) {
        zcCategory.setUpdateBy(UserUtil.getLoginUser().getId());
        zcCategoryDao.update(zcCategory);
        log.debug("编辑资产分类{}", zcCategory.getUpdateBy() + zcCategory.getName());
        return zcCategory;
    }

    @Override
    public void delete(Long id) {
        int count = zcCategoryDao.listByPid(id);  //查看要删除的资产分类是否存在子级分类
        if (count != 0){
            throw new NullPointerException("无法删除，该资产分类存在子级分类");
        }
        ZcCategory zcCategory = new ZcCategory();
        zcCategory.setId(id);
        zcCategory.setUpdateBy(UserUtil.getLoginUser().getId());
        zcCategory.setDel(1);
        zcCategoryDao.update(zcCategory);
        log.debug("删除资产分类{}", zcCategory.getUpdateBy() + zcCategory.getName());
    }
}
