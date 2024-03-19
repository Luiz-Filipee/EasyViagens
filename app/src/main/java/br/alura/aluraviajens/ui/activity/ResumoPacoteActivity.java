package br.alura.aluraviajens.ui.activity;

import static br.alura.aluraviajens.ui.activity.ConstantesActivity.CHAVE_PACOTE;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import br.alura.aluraviajens.R;
import br.alura.aluraviajens.model.Pacote;
import br.alura.aluraviajens.util.CurrencyUtil;
import br.alura.aluraviajens.util.DataUtil;
import br.alura.aluraviajens.util.DaysUtil;
import br.alura.aluraviajens.util.ResourcesUtil;

public class ResumoPacoteActivity extends AppCompatActivity {

    private static final String RESUMO_PACOTE = "Resumo do Pacote";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_resumo_pacote);
        setTitle(RESUMO_PACOTE);
        carregaPacoteRecebido();
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
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

    private void carregaPacoteRecebido() {
        Intent dados = getIntent();
        if (dados.hasExtra(CHAVE_PACOTE)) {
            final Pacote pacote = (Pacote) dados.getSerializableExtra(CHAVE_PACOTE);

            inicializaCampos(pacote);
            configuraBotaoo(pacote);
        }
    }

    private void inicializaCampos(Pacote pacote) {
        mostraImagem(pacote);
        mostraDias(pacote);
        mostraData(pacote);
        mostraLocal(pacote);
        mostraPreco(pacote);
    }

    private void mostraLocal(Pacote pacote) {
        TextView local = findViewById(R.id.resumo_pacote_local);
        local.setText(pacote.getLocal());
    }

    private void mostraDias(Pacote pacote) {
        TextView dias = findViewById(R.id.resumo_pacote_dias);
        String daysInText = DaysUtil.formatDaysInText(pacote.getDias());
        dias.setText(daysInText);
    }

    private void mostraImagem(Pacote pacote) {
        ImageView imagemBanner = findViewById(R.id.resumo_pacote_banner);
        Drawable drawablePackageImage = ResourcesUtil.getDrawable(this, pacote.getImage());
        imagemBanner.setImageDrawable(drawablePackageImage);
    }

    private void mostraPreco(Pacote pacote) {
        TextView preco = findViewById(R.id.resumo_pacote_preco);
        String currencyBrazilian = CurrencyUtil.formatCurrencyBrazilian(pacote.getPreco());
        preco.setText(currencyBrazilian);
    }

    private void mostraData(Pacote pacote) {
        TextView data = findViewById(R.id.resumo_pacote_data);
        String dataFormatadaDaViajem = DataUtil.periodoEmTexto(pacote.getDias());
        data.setText(dataFormatadaDaViajem);
    }

    private void configuraBotaoo(final Pacote pacote) {
        Button botaoRealizarPagamento = findViewById(R.id.resumo_pacote_button);
        botaoRealizarPagamento.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                vaiParaPagamento(pacote);
            }
        });
    }

    private void vaiParaPagamento(Pacote pacote) {
        Intent intent = new Intent(ResumoPacoteActivity.this, PagamentoActivity.class);
        intent.putExtra(CHAVE_PACOTE, pacote);
        startActivity(intent);

    }

}