package br.com.fiap.escuta_afetiva_api.Models;

import java.util.Random;

public class Paciente {
    private Long Id;
    private String Nome;
    private String Plano;
    private String Telefone;
    private String Email;

    public Paciente(Long id, String nome, String plano, String telefone, String email) {
        this.Id = Math.abs(new Random().nextLong());
        this.Nome = nome;
        this.Plano = plano;
        this.Telefone = telefone;
        this.Email = email;
    }

    public Long getId() {
        return Id;
    }

    public void setId(Long id) {
        Id = id;
    }

    public String getNome() {
        return Nome;
    }

    public void setNome(String nome) {
        Nome = nome;
    }

    public String getPlano() {
        return Plano;
    }

    public void setPlano(String plano) {
        Plano = plano;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String email) {
        Email = email;
    }

    public String getTelefone() {
        return Telefone;
    }

    public void setTelefone(String telefone) {
        Telefone = telefone;
    }

}
