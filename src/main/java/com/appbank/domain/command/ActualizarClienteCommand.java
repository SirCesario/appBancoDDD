package com.appbank.domain.command;


import com.appbank.business.generic.Command;
import com.appbank.domain.entitys.Cuenta;
import com.appbank.domain.values.ClienteId;

import java.util.HashSet;
import java.util.Set;

public class ActualizarClienteCommand extends Command {

    private ClienteId clienteId;
    private String nombre;
    private String apellido;
    private String correo;
    private String telefono;



    public ActualizarClienteCommand(){

    }

    public ActualizarClienteCommand(ClienteId clienteId, String nombre, String apellido, String correo, String telefono) {
        this.clienteId = clienteId;
        this.nombre = nombre;
        this.apellido = apellido;
        this.correo = correo;
        this.telefono = telefono;

    }

    public ClienteId getClienteId() {
        return clienteId;
    }

    public void setClienteId(ClienteId clienteId) {
        this.clienteId = clienteId;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }


}
