package com.xf.maven_ssm.annotation_dao;

import com.xf.maven_ssm.domain.Items;
import org.apache.ibatis.annotations.Select;

public interface ItemsDao {

    @Select("select * from items where id = #{id}")
    public Items findById(Integer id);
}
