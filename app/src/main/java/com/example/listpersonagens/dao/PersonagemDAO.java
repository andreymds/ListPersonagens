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
        atualizaId();
    }

    private void atualizaId() {
        contadordeId++;
    }

    public void editar (Personagem personagem){
        Personagem personagemEscolhido = buscarPersonagemId(personagem);
        if (personagemEscolhido != null){
            //verifica qual numerção que a numeração está indicando
            int posicaoDoPersonagem = personagens.indexOf(personagemEscolhido);
            personagens.set(posicaoDoPersonagem, personagem);
        }
    }
    private Personagem buscarPersonagemId(Personagem personagem) {
        //verifica a lista de personagens
        for (Personagem p:
             personagens) {
            if(p.getId() == personagem.getId()){
                return p;
            }
        }
        return null;
    }
    //busca e retorna a lista de todos personagens
    public List<Personagem> todos() {
        return new ArrayList<>(personagens);
    }

    public void remove(Personagem personagem){
        Personagem personagemDevolvido = buscarPersonagemId(personagem);
        if(personagemDevolvido != null){
            personagens.remove(personagemDevolvido);
        }
    }
}
