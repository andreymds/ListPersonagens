package com.example.listpersonagens.dao;

import com.example.listpersonagens.model.Personagem;

import java.util.ArrayList;
import java.util.List;

public class PersonagemDAO {

    private final static List<Personagem> personagens = new ArrayList<>();
    private static int contadordeId = 1;

    //adiciona o personagem do formulário
    public void salva(Personagem personagemSalvo){
        personagemSalvo.setId(contadordeId);
        personagens.add(personagemSalvo);
        contadordeId++;
    }
    public void editar (Personagem personagem){
        Personagem personagemEscolhido = null;

        //verifica a lista de personagens
        for (Personagem p:
             personagens) {
            if(p.getId() == personagem.getId()){
                personagemEscolhido = p;
            }
        }
        if (personagemEscolhido != null){
            //verifica qual numerção que a numeração está indicando
            int posicaoDoPersonagem = personagens.indexOf(personagemEscolhido);
            personagens.set(posicaoDoPersonagem, personagem);
        }
    }
    //busca e retorna a lista de todos personagens
    public List<Personagem> todos() {
        return new ArrayList<>(personagens);
    }
}
