package ar.edu.unq.desapp.grupoF.desappgrupoFbackend.model.economy;

import ar.edu.unq.desapp.grupoF.desappgrupoFbackend.model.economy.feeStatus.PaidFee;
import ar.edu.unq.desapp.grupoF.desappgrupoFbackend.model.economy.feeStatus.PaidStatus;
import ar.edu.unq.desapp.grupoF.desappgrupoFbackend.model.economy.feeStatus.PendingFee;
import ar.edu.unq.desapp.grupoF.desappgrupoFbackend.model.economy.feeStatus.UnpaidFee;

import javax.persistence.AttributeConverter;

public class PaidStatusConverter implements AttributeConverter<PaidStatus,String> {
    @Override
    public String convertToDatabaseColumn(PaidStatus paidStatus) {
        return paidStatus.getClass().getSimpleName().toLowerCase();
    }

    @Override
    public PaidStatus convertToEntityAttribute(String dbData) {
        //works as a factory
        if (dbData.equals("paidfee")) {
            return new PaidFee();
        } else if (dbData.equals("unpaidfee")) {
            return new UnpaidFee();
        }
        if(dbData.equals("pendingfee")){
            return new PendingFee();
        }
        return null;
    }
}