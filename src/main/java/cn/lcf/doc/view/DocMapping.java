package cn.lcf.doc.view;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by lcf on 2014/11/26.
 */
public class DocMapping {
   String path;
   List<Operation> operationses = new ArrayList<Operation>();
   String description;

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<Operation> getOperationses() {
        return operationses;
    }

    public void setOperationses(List<Operation> operationses) {
        this.operationses = operationses;
    }
}
