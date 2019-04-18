package com.xf.maven_ssm.service.impl;

import com.xf.maven_ssm.annotation_dao.ItemsDao;
import com.xf.maven_ssm.domain.Items;
import com.xf.maven_ssm.service.ItemsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(rollbackFor=Exception.class)
public class ItemsServiceImpl implements ItemsService{
    @Autowired
    private ItemsDao itemsDao;

    public Items findById(Integer id) {
        return itemsDao.findById(id);
    }
}
