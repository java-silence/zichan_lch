package com.itycu.server.service;

import com.itycu.server.dto.TodoDto;
import com.itycu.server.model.Equipment;
import org.springframework.web.multipart.MultipartFile;

import java.util.Map;

/**
 * Created by fanlinglong on 2018/12/27.
 */
public interface EquipmentService {
    Equipment save(Equipment equipment);

    TodoDto todo(TodoDto todo);

    Map eqpImport(MultipartFile file);

    Map beipinImport(MultipartFile file);
}
