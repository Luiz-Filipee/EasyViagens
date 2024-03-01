package br.alura.aluraviajens.ui.activity;

import static br.alura.aluraviajens.ui.activity.ConstantesActivity.CHAVE_PACOTE;
import static br.alura.aluraviajens.ui.activity.ConstantesActivity.CHAVE_RESUMO;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;

import java.math.BigDecimal;

import br.alura.aluraviajens.R;
import br.alura.aluraviajens.model.Pacote;
import br.alura.aluraviajens.util.CurrencyUtil;

public class PagamentoActivity extends AppCompatActivity {
    private static final String TITULO_APPBAR = "Pagamento";
    private TextView precoCompra;
    private Button botaoFinalizarCompra;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pagamento);
        setTitle(TITULO_APPBAR);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        carregaInfo();
        configuraSpinner();
    }

    private void configuraSpinner(){
        Spinner spinnerParcelas = findViewById(R.id.pagamento_parcelado);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.parcelas, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerParcelas.setSelection(0, false);
        spinnerParcelas.setAdapter(adapter);
    }

    private void carregaInfo(){
        precoCompra = findViewById(R.id.pagamento_preco);
        Intent dados = getIntent();
        if (dados.hasExtra(CHAVE_RESUMO)) {
            String precoPacoteString = dados.getStringExtra(CHAVE_RESUMO);
            BigDecimal precoPacote = new BigDecimal(precoPacoteString);
            String currencyBrazilian = CurrencyUtil.formatCurrencyBrazilian(precoPacote);
            precoCompra.setText(currencyBrazilian);
        }
    }

    private void configuraBotaoFinalizaCompra(){

    }


    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case android.R.id.home:
                onBackPressed();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}