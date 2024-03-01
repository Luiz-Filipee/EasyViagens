package br.alura.aluraviajens.ui.activity;

import static br.alura.aluraviajens.ui.activity.ConstantesActivity.CHAVE_PACOTE;

import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.List;

import br.alura.aluraviajens.R;
import br.alura.aluraviajens.dao.PacoteDAO;
import br.alura.aluraviajens.model.Pacote;
import br.alura.aluraviajens.ui.adapter.ListaPacotesAdapter;

public class ListaPacotesActivity extends AppCompatActivity {
    private static final String TITULO_APPBAR = "Pacotes";
    private EditText textoBusca;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_pacotes);
        setTitle(TITULO_APPBAR);
        configuraLista();
        configuraBotaoPesquisar();
    }


    private void configuraLista() {
        textoBusca = findViewById(R.id.lista_pacotes_pesquisa);
        ListView listaDePacotes = findViewById(R.id.lista_pacotes_listview);
        List<Pacote> pacotes = new PacoteDAO().lista();
        configuraListetnerDeCliquePorItem(listaDePacotes);
        listaDePacotes.setAdapter(new ListaPacotesAdapter(pacotes, this));
    }

    private void configuraBotaoPesquisar(){
        textoBusca.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if(actionId == EditorInfo.IME_ACTION_SEARCH ||
                        event.getAction() == KeyEvent.ACTION_DOWN &&
                            event.getKeyCode() == KeyEvent.KEYCODE_ENTER){
                    realizarPesquisa();
                    return true;
                }
                return false;
            }
        });
    }

    private void realizarPesquisa(){
        String termoPesquisa = textoBusca.getText().toString();
        List<Pacote> pacotesEncontrados = new ArrayList<>();
        List<Pacote> listaPacotes = new PacoteDAO().lista();
        ListView listaDePacotes = findViewById(R.id.lista_pacotes_listview);
        for(Pacote p : listaPacotes){
            if(p.getLocal().toLowerCase().equals(termoPesquisa.toLowerCase())){
                pacotesEncontrados.add(p);
            }
        }
        if (!pacotesEncontrados.isEmpty()){
            listaDePacotes.setAdapter(new ListaPacotesAdapter(pacotesEncontrados, this));
        }else if(termoPesquisa.isEmpty()){
            listaDePacotes.setAdapter(new ListaPacotesAdapter(listaPacotes, this));
        }
        else{
            Toast.makeText(this, "Pacote não encontrado", Toast.LENGTH_SHORT).show();
        }
    }

    private void configuraListetnerDeCliquePorItem(ListView listaDePacotes){
        listaDePacotes.setOnItemClickListener((parent, view, position, id) -> {
            Pacote pacote = (Pacote) parent.getItemAtPosition(position);
            abreResumoPacoteActivity(pacote);
        });
    }

    private void abreResumoPacoteActivity(Pacote pacote){
        Intent abreFormularioAcitivity = new Intent(ListaPacotesActivity.this, ResumoPacoteActivity.class);
        abreFormularioAcitivity.putExtra(CHAVE_PACOTE, pacote);
        startActivity(abreFormularioAcitivity);
    }
}