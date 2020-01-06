package com.itycu.server.service;

import com.itycu.server.dto.TodoDto;
import com.itycu.server.dto.ZxBorrowVO;
import com.itycu.server.model.ZxBorrow;

/**
 * Created by fanlinglong on 2018/12/23.
 */
public interface ZxBorrowService {
    ZxBorrow save(ZxBorrowVO zxBorrow);

    TodoDto todo(TodoDto todoDto);
}
