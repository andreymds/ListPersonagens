package com.example.listpersonagens.ui.activities;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.example.listpersonagens.R;
import com.example.listpersonagens.dao.PersonagemDAO;
import com.example.listpersonagens.model.Personagem;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.List;

import static com.example.listpersonagens.ui.activities.ConstantesActivities.CHAVE_PERSONAGEM;

public class ListaPersonagemActivity extends AppCompatActivity {

    public static final String TITULO_APPBAR = "Lista de Personagens"; //string q define o título

    //cria a classe para já tê-la no início do programa e receber as infos do formulário
    private final PersonagemDAO dao = new PersonagemDAO();
    private ArrayAdapter<Personagem> adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_personagem); //determina uma tela para o contexto
        setTitle(TITULO_APPBAR); //título superior da tela
        configuraFabNovoPersonagem();
        configuraLista();

    }

    private void configuraFabNovoPersonagem() {
        //determina a acção do botão de adicionar personagem, direcionando o usuário à tela do formulário
        FloatingActionButton botaoNovoPersonagem = findViewById(R.id.fab_add);
        botaoNovoPersonagem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {  abreFormulario(); }
        });
    }

    private void abreFormulario() {
        startActivity(new Intent(this, FormularioPersonagemActivity.class));
    }

    @Override
    protected void onResume() { //persiste as informações adicionadas
        super.onResume();
        atualizaPersonagem();
    }

    private void atualizaPersonagem(){
        adapter.clear(); //limpa a lista
        adapter.addAll(dao.todos()); //adiciona todos valores
    }

    private void remove(Personagem personagem){
        dao.remove(personagem);
        adapter.remove(personagem);
    }

    //abre uma janela quando aperta o botão
    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        getMenuInflater().inflate(R.menu.activity_lista_personagem_menu, menu); //popula menu com infos de fora
    }

    //dá um retorno pro adapter sobre o clique
    @Override
    public boolean onContextItemSelected(@NonNull MenuItem item) {
        return configuraMenu(item);
    }

    private boolean configuraMenu(@NonNull MenuItem item) {
        int itemId = item.getItemId();
        if(itemId == R.id.activity_lista_meu_personagem_remover){
            new AlertDialog.Builder(this) //alerta de confimação de remoção de personagem
                .setTitle("Removendo Personagem")
                .setMessage("Tem certeza que deseja remover Personagem")
                .setPositiveButton("Sim", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        AdapterView.AdapterContextMenuInfo menuInfo = (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();
                        Personagem personagemEscolhido = adapter.getItem(menuInfo.position); //personagem selecionado pelo user
                        remove(personagemEscolhido); //remove o personagem selecionado
                    }
                })
                .setNegativeButton("Não", null)
                .show();
        }
        return super.onContextItemSelected(item);
    }

    private void configuraLista() {
        //determina que a tela vai exibir a lista com todos os personagens criados
        ListView listaDePersonagens = findViewById(R.id.activity_main_lista_personagem);
        //final List<Personagem> personagens = dao.todos();
        listaDePersonagens(listaDePersonagens);
        configuraItemPorClick(listaDePersonagens);
        registerForContextMenu(listaDePersonagens);
    }

    private void configuraItemPorClick(ListView listaDePersonagens) {
        listaDePersonagens.setOnItemClickListener(new AdapterView.OnItemClickListener(){ //retorna o clique do usuário
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int posicao, long id) {
                Personagem personagemEscolhido = (Personagem) adapterView.getItemAtPosition(posicao); //pega o conteúdo do personagem selecionado
                abreFormularioEditar(personagemEscolhido);
            }
        });
    }

    private void abreFormularioEditar(Personagem personagemEscolhido) {
        //abre infos do formulário do personagem selecionado
        Intent vaiParaFormulario = new Intent(ListaPersonagemActivity.this, FormularioPersonagemActivity.class);

        //leva a informação para outra parte do programa
            vaiParaFormulario.putExtra(CHAVE_PERSONAGEM, personagemEscolhido);

        startActivity(vaiParaFormulario);
    }

    private void listaDePersonagens(ListView listaDePersonagens) {
        adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1);
        listaDePersonagens.setAdapter(adapter);
    }
}

