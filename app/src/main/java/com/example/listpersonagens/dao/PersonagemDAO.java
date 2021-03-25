package com.example.listpersonagens.dao;

import com.example.listpersonagens.model.Personagem;

import java.util.ArrayList;
import java.util.List;

public class PersonagemDAO {

    private final static List<Personagem> personagens = new ArrayList<>();

    //adiciona o personagem do formulário
    public void salva(Personagem personagemSalvo){
        personagens.add(personagemSalvo);
    }

    //busca e retorna a lista de todos personagens
    public List<Personagem> todos() {
        return new ArrayList<>(personagens);
    }
}
