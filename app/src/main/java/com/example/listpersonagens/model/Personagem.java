package com.example.listpersonagens.model;

public class Personagem {

    /*Traz a variável de fora*/
    private final String nome;
    private final String altura;
    private final String nascimento;

    //determina as variáveis que receberá as informações de fora
    public Personagem(String nome, String altura, String nascimento) {

        this.nome = nome;
        this.altura = altura;
        this.nascimento = nascimento;
    }

    //transaciona as informações

    @Override
    public String toString() {
        return nome;
    }


    /*
    public String getNome() {
        return nome;
    }

    public String getAltura() {
        return altura;
    }

    public String getNascimento() {
        return nascimento;
    }*/
}
