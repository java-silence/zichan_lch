package com.itycu.server.service;

import com.itycu.server.dto.TodoDto;
import com.itycu.server.model.Todo;

/**
 * Created by Hezhilin on 2018/3/11 0011.
 */
public interface TodoService {

    Todo update(TodoDto todoDto);

    Todo sendTodo(TodoDto todo);
}
