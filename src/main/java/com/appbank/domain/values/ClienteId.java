package com.appbank.domain.values;

import com.appbank.business.generic.Identity;


public class ClienteId extends Identity {
    public ClienteId(String clienteId){super(clienteId);}

    public ClienteId(){ }

    public static ClienteId of(String clienteId) { return new ClienteId(clienteId);}
}
