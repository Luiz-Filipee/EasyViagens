package br.alura.aluraviajens.ui.activity;

import static br.alura.aluraviajens.ui.activity.ConstantesActivity.CHAVE_PACOTE;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import br.alura.aluraviajens.R;
import br.alura.aluraviajens.model.Pacote;
import br.alura.aluraviajens.util.CurrencyUtil;

public class PagamentoActivity extends AppCompatActivity {
    private static final String TITULO_APPBAR = "Pagamento";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pagamento);
        setTitle(TITULO_APPBAR);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        carregaPacoteRecebido();
        configuraSpinner();
    }

    private void configuraSpinner() {
        Spinner spinnerParcelas = findViewById(R.id.pagamento_parcelado);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.parcelas, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerParcelas.setSelection(0, false);
        spinnerParcelas.setAdapter(adapter);
    }

    private void carregaPacoteRecebido() {
        Intent intent = getIntent();
        if(intent.hasExtra(CHAVE_PACOTE)){
            final Pacote pacote = (Pacote) intent.getSerializableExtra(CHAVE_PACOTE);
            mostraPreco(pacote);
            configutaBotao(pacote);
        }
    }

    private void mostraPreco(Pacote pacote) {
        TextView precoCompra = findViewById(R.id.pagamento_preco_compra);
        String currencyBrazilian = CurrencyUtil.formatCurrencyBrazilian(pacote.getPreco());
        precoCompra.setText(currencyBrazilian);
    }


    private void vaiParaResumoCompra(Pacote pacote) {
       Intent intent = new Intent(PagamentoActivity.this, ResumoCompraActivity.class);
       intent.putExtra(CHAVE_PACOTE, pacote);
       startActivity(intent);
    }

    private void configutaBotao(final Pacote pacote) {
        Button botaoFinalizaCompra = findViewById(R.id.pagamento_finalizar_compra);
        botaoFinalizaCompra.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                vaiParaResumoCompra(pacote);
            }
        });
    }


    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                onBackPressed();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}