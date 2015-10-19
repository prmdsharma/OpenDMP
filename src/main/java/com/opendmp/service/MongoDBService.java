package com.opendmp.service;

import org.jsoup.nodes.Document;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.opendmp.db.MongoDBManager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.HashMap;


/**
 * Created by pramod.sharma on 15/10/15.
 */

@Service
public class MongoDBService {

    private static final Logger logger = LoggerFactory.getLogger(MongoDBService.class.getCanonicalName());

    @Resource
    private MongoDBManager     mongoDBManager;

    public boolean putDocument( String collectionName,String jsonData)
    {
        Boolean status = false;


        return status;
    }

}
