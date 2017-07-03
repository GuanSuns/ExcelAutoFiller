package excel.filler.model;

/**
 * Created by guanl on 6/23/2017.
 */
public class Sheet429PersonalPDM {
    protected Long order;
    protected String batch;
    protected String province;
    protected Sheet429DataPDM collectionCore;

    public Sheet429PersonalPDM() {
        this.order = 0L;
        this.batch = "";
        this.province = "";
        this.collectionCore = null;
    }

    public Sheet429PersonalPDM(Long order, String batch
            , String province, Sheet429DataPDM collectionCore) {
        this.order = order;
        this.batch = batch;
        this.province = province;
        this.collectionCore = collectionCore;
    }

    public Long getOrder() {
        return order;
    }

    public String getBatch() {
        return batch;
    }

    public String getProvince() {
        return province;
    }

    public Sheet429DataPDM getCollectionCore() {
        return collectionCore;
    }

    public void setCollectionCore(Sheet429DataPDM collectionCore) {
        this.collectionCore = collectionCore;
    }

    public void setOrder(Long order) {
        this.order = order;
    }

    public void setBatch(String batch) {
        this.batch = batch;
    }

    public void setProvince(String province) {
        this.province = province;
    }
}
