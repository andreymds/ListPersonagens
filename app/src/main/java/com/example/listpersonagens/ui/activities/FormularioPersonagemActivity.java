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

    private EditText campoNome;
    private EditText campoAltura;
    private EditText campoNascimento;
    private final PersonagemDAO dao = new PersonagemDAO(); //nova classe

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_formulario_personagem);
        setTitle("Formulário de Personagem"); //Título superior da tela

        /*Vinculando os objetos do xml com as variáveis*/
        campoNome = findViewById(R.id.edittext_nome);
        campoAltura = findViewById(R.id.edittext_altura);
        campoNascimento = findViewById(R.id.edittext_nascimento);

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
                finish(); //leva o usuário devolta para a lista

                //muda as interfaces
                //startActivity(new Intent(FormularioPersonagemActivity.this, ListaPersonagemActivity.class));

                /*
                Toast.makeText(FormularioPersonagemActivity.this,
                        personagemSalvo.getNome() + " - " + personagemSalvo.getAltura() + " - "
                        + personagemSalvo.getNascimento(),
                        Toast.LENGTH_SHORT).show();*/

                /*Criação da Classe Personagem com os parâmetros nome, altura e nascimento*/
                //new Personagem(nome, altura, nascimento);

                //possibilita edição dos personagens salvos
                personagemSalvo.setNome(nome);
                personagemSalvo.setAltura(altura);
                personagemSalvo.setNascimento(nascimento);
                dao.editar(personagemSalvo);


                Intent dados = getIntent(); //instancia os dados

                //"dados" busca as informações do personagem
                Personagem personagem = (Personagem) dados.getSerializableExtra("personagem");
                campoNome.setText(personagem.getNome());
                campoAltura.setText(personagem.getAltura());
                campoNascimento.setText(personagem.getNascimento());

            }
        });
    }
}