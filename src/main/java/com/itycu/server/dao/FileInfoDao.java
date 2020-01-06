package com.itycu.server.dao;

import java.util.List;
import java.util.Map;

import com.itycu.server.model.FileInfo;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

@Mapper
public interface FileInfoDao {

	@Select("select * from file_info t where t.id = #{id}")
    FileInfo getById(String id);

	@Insert("insert into file_info(id, contentType, filename, size, path, url, type, createTime, updateTime, biztype, bizid) values(#{id}, #{contentType},#{filename}, #{size}, #{path}, #{url}, #{type}, now(), now(), #{biztype}, #{bizid})")
	int save(FileInfo fileInfo);

	@Update("update file_info t set t.updateTime = now() where t.id = #{id}")
	int update(FileInfo fileInfo);

	@Delete("delete from file_info where id = #{id}")
	int delete(String id);

	int count(@Param("params") Map<String, Object> params);

	List<FileInfo> list(@Param("params") Map<String, Object> params, @Param("offset") Integer offset,
                        @Param("limit") Integer limit);
	@Select("select * from file_info t where t.biztype=#{biztype} and t.bizid = #{bizid} order by createTime desc")
	List<FileInfo> listBybizid(@Param("bizid") Long bizid, @Param("biztype") String biztype);

	int saveFiles(@Param("bizid") Long bizid, @Param("biztype") String biztype, @Param("fileIds") List<String> fileIds);
}
