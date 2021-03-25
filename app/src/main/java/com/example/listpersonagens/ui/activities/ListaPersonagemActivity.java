package com.example.listpersonagens.ui.activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;
import com.example.listpersonagens.dao.PersonagemDAO;
import com.example.listpersonagens.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ListaPersonagemActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_personagem); //determina uma tela para o contexto
        setTitle("Lista de Personagens"); //título superior da tela
        PersonagemDAO dao = new PersonagemDAO(); //criação da classe que vai receber as informações
        //List<String> personagem = new ArrayList<>(Arrays.asList("Alex","Ken","Ryu"));

        //determina a acção do botão de adicionar personagem, direcionando o usuário à tela do formulário
        FloatingActionButton botaoNovoPersonagem = findViewById(R.id.fab_add);
        botaoNovoPersonagem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(ListaPersonagemActivity.this, FormularioPersonagemActivity.class));
            }
        });
        //determina que a tela vai exibir a lista com todos os personagens criados
        ListView listaDePersonagens = findViewById(R.id.activity_main_lista_personagem);
        listaDePersonagens.setAdapter(new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, dao.todos()));

        /*TextView primeiroPersonagem = findViewById(R.id.textView);
        TextView segundoPersonagem = findViewById(R.id.textView2);
        TextView terceiroPersonagem = findViewById(R.id.textView3);
        primeiroPersonagem.setText(personagem.get(0));
        segundoPersonagem.setText(personagem.get(1));
        terceiroPersonagem.setText(personagem.get(2));*/
    }
    }

