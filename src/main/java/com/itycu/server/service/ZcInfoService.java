package com.itycu.server.service;

import com.itycu.server.app.dto.ZcInfoListDTO;
import com.itycu.server.app.model.AppIndexZcValueAndNumber;
import com.itycu.server.app.vo.zonghang.ZongHangMonthNumber;
import com.itycu.server.dto.ZcInfoDto;
import com.itycu.server.model.SysUser;
import com.itycu.server.model.ZcInfo;
import com.itycu.server.page.table.PageTableRequest;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.Map;

public interface ZcInfoService {

    /**
     * 资产新增
     * @param zcInfo
     * @return
     */
    Map save(ZcInfo zcInfo);

    ZcInfo update(ZcInfo zcInfo);
    void delete(Long id);
    void export(PageTableRequest request, HttpServletResponse response);
    Map Import(MultipartFile file) throws IOException;
    Map gudingImport(MultipartFile file) throws IOException;
    Map dizhiImport(MultipartFile file) throws IOException;


    AppIndexZcValueAndNumber getZcValueAndZcNumber(SysUser user);


    ZcInfo  queryZnInfoByEpcId(String epcid);

    List<ZcInfoDto> getAllZcInfoListByUser(SysUser sysUser, ZcInfoListDTO zcInfoListDTO);


    ZcInfoDto queryZnInfoDtoByEpcId(String epcid);

    /**
     * 账号664000获取数据
     * @param sysUser
     * @return
     */
    ZongHangMonthNumber getZongHangZcNumber(SysUser sysUser);
}
