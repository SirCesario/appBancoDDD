package com.appbank.domain.values;


import com.appbank.business.generic.Identity;

public class TransaccionId extends Identity {

    private TransaccionId(String transaccionId){super(transaccionId); }

    public TransaccionId(){

    }

    public static TransaccionId of (String transaccionId){ return new TransaccionId(transaccionId); }
}
