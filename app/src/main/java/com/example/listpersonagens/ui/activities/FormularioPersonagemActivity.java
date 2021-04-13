package com.example.listpersonagens.ui.activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

import com.example.listpersonagens.R;
import com.example.listpersonagens.dao.PersonagemDAO;
import com.example.listpersonagens.model.Personagem;

import static com.example.listpersonagens.ui.activities.ConstantesActivities.CHAVE_PERSONAGEM;

public class FormularioPersonagemActivity extends AppCompatActivity {

    private static final String TITULO_APPBAR_EDITA_PERSONAGEM = "Edita Personagem";
    private static final String TITULO_APPBAR_NOVO_PERSONAGEM = "Novo Personagem";
    private EditText campoNome;
    private EditText campoAltura;
    private EditText campoNascimento;
    private final PersonagemDAO dao = new PersonagemDAO(); //nova classe
    private Personagem personagem;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_formulario_personagem);

        inicializacaoCampos();
        configBotaoAdd();
        carregaPersonagem();
    }

    private void carregaPersonagem() {
        Intent dados = getIntent(); //instancia os dados
        if(dados.hasExtra(CHAVE_PERSONAGEM)) {
            setTitle(TITULO_APPBAR_EDITA_PERSONAGEM);
            //"dados" busca as informações do personagem
            personagem = (Personagem) dados.getSerializableExtra(CHAVE_PERSONAGEM);
            preencheCampos();
        } else {
            setTitle(TITULO_APPBAR_NOVO_PERSONAGEM);
            personagem = new Personagem(); //caso user add personagem sem dados
        }
    }

    private void preencheCampos() {
        campoNome.setText(personagem.getNome());
        campoAltura.setText(personagem.getAltura());
        campoNascimento.setText(personagem.getNascimento());
    }

    private void configBotaoAdd() {
        Button botaoSalvar = findViewById(R.id.button_salvar);

        /*Instanciando uma View*/
        botaoSalvar.setOnClickListener(new View.OnClickListener() {

            /*Sobrescrevendo Método de Click*/
            @Override
            public void onClick(View v) {
                finalizarFomulario();
            }
        });
    }

    private void finalizarFomulario() {
        ArmazenaValoresClick();
        if(personagem.IdValido()){
            dao.editar(personagem);
            finish();
        } else{
            dao.salva(personagem);
        }
        finish();

    }

    private void inicializacaoCampos() {
        /*Vinculando os objetos do xml com as variáveis*/
        campoNome = findViewById(R.id.edittext_nome);
        campoAltura = findViewById(R.id.edittext_altura);
        campoNascimento = findViewById(R.id.edittext_nascimento);
    }

    private void ArmazenaValoresClick(){
        /*Pega informação das caixas de texto e armazena os valores nas variáveis*/
        String nome = campoNome.getText().toString();
        String altura = campoAltura.getText().toString();
        String nascimento = campoNascimento.getText().toString();

        //possibilita edição dos personagens salvos
        personagem.setNome(nome);
        personagem.setAltura(altura);
        personagem.setNascimento(nascimento);
    }
}