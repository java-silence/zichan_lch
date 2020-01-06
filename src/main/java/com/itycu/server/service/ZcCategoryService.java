package com.itycu.server.service;

import com.itycu.server.model.ZcCategory;

public interface ZcCategoryService {
    ZcCategory save(ZcCategory zcCategory);
    ZcCategory update(ZcCategory zcCategory);
    void delete(Long id);
}
