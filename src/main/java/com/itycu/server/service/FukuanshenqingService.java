package com.itycu.server.service;

import com.itycu.server.dto.TodoDto;
import com.itycu.server.model.Fukuanshenqing;

/**
 * Created by fanlinglong on 2019-04-09.
 */
public interface FukuanshenqingService {

    Fukuanshenqing save(Fukuanshenqing fukuanshenqing);
    TodoDto todo(TodoDto todo);
}
