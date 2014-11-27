package cn.lcf.service;

import cn.cartman.annotation.Service;
import cn.lcf.doc.view.DocApi;
import cn.lcf.doc.view.DocMapping;

import java.util.List;

/**
 * Created by lcf on 2014/11/26.
 */
@Service
public class CartmanService {
    public DocApi getApiDoc(String path){
        return  new DocApi();
    }
    public DocApi getApiDoc(List<Integer> a,DocMapping docMapping){
        return  new DocApi();
    }
}
