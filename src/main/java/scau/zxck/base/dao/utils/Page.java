package scau.zxck.base.dao.utils;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * 分页响应列表。
 */
public class Page<T> implements Serializable {

    private static final long serialVersionUID = 1238749498860238250L;

    /**
     * 所有记录数
     */
    private long total;

    /**
     * 解释后的具体对象
     */
    private List<T> results;

    /**
     * pagesize
     **/
    private int pageSize = 10;

    public Page() {

    }

    public Page(long total, List<T> results) {
        this.total = total;
        this.results = results;
    }

    public Page(long total, List<T> results, int pageSize) {
        this.total = total;
        this.results = results;
        this.pageSize = pageSize;
    }

    public long getTotal() {
        return this.total;
    }

    public void setTotal(long total) {
        this.total = total;
    }

    public List<T> getResults() {
        return this.results;
    }

    public void setResults(List<T> results) {
        this.results = results;
    }

    public void addResult(T result) {
        if (this.results == null) {
            this.results = new ArrayList<T>();
        }
        this.results.add(result);
    }

    /**
     * 取得响应列表中的第一个对象。
     *
     * @return 第一个对象或者null
     */
    public T getFirst() {
        if (results != null && !results.isEmpty()) {
            return results.get(0);
        } else {
            return null;
        }
    }

    /**
     * 判断响应列表是否为空。
     *
     * @return true/false
     */
    public boolean isEmpty() {
        return results == null || results.isEmpty();
    }

    /**
     * 获取实际返回的记录数。
     */
    public int getReturnSize() {
        if (isEmpty()) {
            return 0;
        } else {
            return results.size();
        }
    }

    public int getPageSize() {
        return this.pageSize;
    }

    /**
     * 获取返回记录的总页数。
     */
    public int getPageCount() {
        if (pageSize == 0) {
            return 0;
        }
        if (this.total % pageSize == 0) {
            return (int) (this.total / pageSize);
        } else {
            return (int) (this.total / pageSize + 1);
        }
    }
}
