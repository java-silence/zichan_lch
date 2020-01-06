package com.itycu.server.service;

import com.itycu.server.dto.StockoutVO;
import com.itycu.server.dto.StockoutsVO;
import com.itycu.server.dto.TodoDto;
import com.itycu.server.model.Stockout;
import com.itycu.server.model.Stockouts;
import com.itycu.server.page.table.PageTableRequest;

import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.Map;

/**
 * Created by fanlinglong on 2019/1/2.
 */
public interface StockoutService {
    Stockout save(StockoutVO stockoutVO);
    TodoDto todo(TodoDto todo);
    Stockout audit(Long id);
    Stockout unaudit(Long id);


}
