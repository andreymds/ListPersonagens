package com.example.listpersonagens.ui.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.listpersonagens.R;
import com.example.listpersonagens.dao.PersonagemDAO;
import com.example.listpersonagens.model.Personagem;

public class FormularioPersonagemActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_formulario_personagem);
        setTitle("Formulário de Personagem"); //Título superior da tela

        PersonagemDAO dao = new PersonagemDAO(); //nova classe

        /*Vinculando os objetos do xml com as variáveis*/
        EditText campoNome = findViewById(R.id.edittext_nome);
        EditText campoAltura = findViewById(R.id.edittext_altura);
        EditText campoNascimento = findViewById(R.id.edittext_nascimento);
        Button botaoSalvar = findViewById(R.id.button_salvar);

        /*Instanciando uma View*/
        botaoSalvar.setOnClickListener(new View.OnClickListener() {

            /*Sobrescrevendo Método de Click*/
            @Override
            public void onClick(View v) {

                /*Pega informação das caixas de texto e armazena os valores nas variáveis*/
                String nome = campoNome.getText().toString();
                String altura = campoAltura.getText().toString();
                String nascimento = campoNascimento.getText().toString();

                Personagem personagemSalvo = new Personagem(nome,altura,nascimento);
                dao.salva(personagemSalvo); //leva o personagem para ser salvo

                //muda as interfaces
                startActivity(new Intent(FormularioPersonagemActivity.this, ListaPersonagemActivity.class));

                /*
                Toast.makeText(FormularioPersonagemActivity.this,
                        personagemSalvo.getNome() + " - " + personagemSalvo.getAltura() + " - "
                        + personagemSalvo.getNascimento(),
                        Toast.LENGTH_SHORT).show();*/

                /*Criação da Classe Personagem com os parâmetros nome, altura e nascimento*/
                new Personagem(nome, altura, nascimento);


            }
        });
    }
}