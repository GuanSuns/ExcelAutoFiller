package excel.filler.model;

/**
 * Created by guanl on 6/23/2017.
 */
public class Sheet429CorePDM extends Sheet429PersonalPDM{
    private Sheet429DataPDM integratedCore;
    private Sheet429DataPDM queryCore;

    public Sheet429CorePDM() {
        this.integratedCore = null;
        this.queryCore = null;
    }

    public Sheet429CorePDM(Long order, String batch, String province
            , Sheet429DataPDM collectionCore, Sheet429DataPDM integratedCore
            , Sheet429DataPDM queryCore) {
        super(order, batch, province, collectionCore);
        this.integratedCore = integratedCore;
        this.queryCore = queryCore;
    }

    public void setIntegratedCore(Sheet429DataPDM integratedCore) {
        this.integratedCore = integratedCore;
    }

    public void setQueryCore(Sheet429DataPDM queryCore) {
        this.queryCore = queryCore;
    }

    public Sheet429DataPDM getIntegratedCore() {
        return integratedCore;
    }

    public Sheet429DataPDM getQueryCore() {
        return queryCore;
    }
}
