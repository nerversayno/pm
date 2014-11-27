package cn.lcf.service;

import cn.lcf.doc.view.DocApi;

/**
 * Created by lcf on 2014/11/26.
 */
public class CartmanNoService {

    public DocApi getApiDoc(String path){
        return  new DocApi();
    }
}
