package com.itycu.server.service;

import com.itycu.server.dto.CgdingdanVO;
import com.itycu.server.model.CgDingdan;

/**
 * Created by fanlinglong on 2019/4/10.
 */
public interface CgDingdanService {
    CgdingdanVO save(CgdingdanVO cgdingdanVO);

    CgdingdanVO update(CgdingdanVO cgdingdanVO);
}
