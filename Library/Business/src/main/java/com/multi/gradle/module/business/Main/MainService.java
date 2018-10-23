package com.multi.gradle.module.business.Main;

import com.multi.gradle.module.data.mybatis.repository.ListMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by dhokim on  2018-10-19
 */
@Service
public class MainService {

    @Autowired
    ListMapper listMapper;

    public Integer getListCnt(){
        return listMapper.totalCount();
    }
}
