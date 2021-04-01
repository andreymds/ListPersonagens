package com.example.listpersonagens.ui.activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;
import com.example.listpersonagens.dao.PersonagemDAO;
import com.example.listpersonagens.R;
import com.example.listpersonagens.model.Personagem;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ListaPersonagemActivity extends AppCompatActivity {

    //cria a classe para já tê-la no início do programa e receber as infos do formulário
    private final PersonagemDAO dao = new PersonagemDAO();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_personagem); //determina uma tela para o contexto
        setTitle("Lista de Personagens"); //título superior da tela

        //inicia o programa com personagens salvos
        dao.salva(new Personagem("Ken", "1.80", "05061989"));
        dao.salva(new Personagem("Ryu", "1.96", "28061995"));

        //List<String> personagem = new ArrayList<>(Arrays.asList("Alex","Ken","Ryu"));

        //determina a acção do botão de adicionar personagem, direcionando o usuário à tela do formulário
        FloatingActionButton botaoNovoPersonagem = findViewById(R.id.fab_add);
        botaoNovoPersonagem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(ListaPersonagemActivity.this, FormularioPersonagemActivity.class));
            }
        });


        /*TextView primeiroPersonagem = findViewById(R.id.textView);
        TextView segundoPersonagem = findViewById(R.id.textView2);
        TextView terceiroPersonagem = findViewById(R.id.textView3);
        primeiroPersonagem.setText(personagem.get(0));
        segundoPersonagem.setText(personagem.get(1));
        terceiroPersonagem.setText(personagem.get(2));*/
    }
    @Override
    protected void onResume() { //persiste as informações adicionadas
        super.onResume();

        //determina que a tela vai exibir a lista com todos os personagens criados
        ListView listaDePersonagens = findViewById(R.id.activity_main_lista_personagem);
        List<Personagem> personagens = dao.todos();
        listaDePersonagens.setAdapter(new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, personagens));

        listaDePersonagens.setOnItemClickListener(new AdapterView.OnItemClickListener(){ //retorna o clique do usuário
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int posicao, long id) {
                Personagem personagemEscolhido = personagens.get(posicao); //pega o conteúdo do personagem selecionado

                //abre infos do formulário do personagem selecionado
                Intent vaiParaFormulario = new Intent(ListaPersonagemActivity.this, FormularioPersonagemActivity.class);

                //leva a informação para outra parte do programa
                vaiParaFormulario.putExtra("personagem", personagemEscolhido);

                startActivity(vaiParaFormulario);

            }
        });
    }
}

