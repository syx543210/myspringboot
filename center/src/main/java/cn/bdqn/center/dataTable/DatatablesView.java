package cn.bdqn.center.dataTable;

import java.util.List;

/**
 * @author wj
 * @date 14:02 2018/3/14
 * @description
 */
public class DatatablesView<T> {

    private List<T> data; //data 与datatales 加载的“dataSrc"对应

    private Long recordsTotal;

    private Long recordsFiltered;

    private int draw;

    public DatatablesView() {

    }

    public int getDraw() {
        return draw;
    }

    public void setDraw(int draw) {
        this.draw = draw;
    }

    public List<T> getData() {
        return data;
    }

    public void setData(List<T> data) {
        this.data = data;
    }

    public Long getRecordsTotal() {
        return recordsTotal;
    }

    public void setRecordsTotal(Long recordsTotal) {
        this.recordsTotal = recordsTotal;
        this.recordsFiltered = recordsTotal;
    }

    public Long getRecordsFiltered() {
        return recordsFiltered;
    }

    public void setRecordsFiltered(Long recordsFiltered) {
        this.recordsFiltered = recordsFiltered;
    }
}
