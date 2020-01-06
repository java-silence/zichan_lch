package com.itycu.server.service;

import com.itycu.server.dto.TodoDto;
import com.itycu.server.model.FlowDoc;

/**
 * Created by fanlinglong on 2019-02-02.
 */
public interface FlowDocService {
    FlowDoc save(FlowDoc flowDoc);
    TodoDto todo(TodoDto todo);
}
