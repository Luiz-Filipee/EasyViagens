package br.alura.aluraviajens.ui.activity;

import static br.alura.aluraviajens.ui.activity.ConstantesActivity.CHAVE_PACOTE;
import static br.alura.aluraviajens.ui.activity.ConstantesActivity.CHAVE_RESUMO;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import br.alura.aluraviajens.R;
import br.alura.aluraviajens.model.Pacote;
import br.alura.aluraviajens.util.CurrencyUtil;
import br.alura.aluraviajens.util.DataUtil;
import br.alura.aluraviajens.util.DaysUtil;
import br.alura.aluraviajens.util.ResourcesUtil;

public class ResumoPacoteActivity extends AppCompatActivity {

    private static final String RESUMO_PACOTE = "Resumo do Pacote";
    private Pacote pacote;
    private ImageView imagemBanner;
    private TextView dias;
    private TextView preco;
    private TextView data;
    private TextView local;
    private EditText textoBusca;
    private Button botaoRealizarPagamento;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_resumo_pacote);
        inicializaCampos();
        carregaInfo();
        configuraBotaoRealizarPagamento();
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

    private void inicializaCampos() {
        imagemBanner = findViewById(R.id.resumo_pacote_banner);
        dias = findViewById(R.id.resumo_pacote_dias);
        data = findViewById(R.id.resumo_pacote_data);
        local = findViewById(R.id.resumo_pacote_local);
        preco = findViewById(R.id.resumo_pacote_preco);
        botaoRealizarPagamento = findViewById(R.id.resumo_pacote_button);
    }

    private void carregaInfo() {
        Intent dados = getIntent();
        if (dados.hasExtra(CHAVE_PACOTE)) {
            setTitle(RESUMO_PACOTE);
            pacote = (Pacote) dados.getSerializableExtra(CHAVE_PACOTE);
            preencheCampos();
        }
    }

    private void preencheCampos() {
        mostraImagem();
        mostraDias();
        mostraData();
        mostraLocal();
        mostraPreco();
    }

    private void mostraLocal() {
        local.setText(pacote.getLocal());
    }

    private void mostraDias() {
        String daysInText = DaysUtil.formatDaysInText(pacote.getDias());
        dias.setText(daysInText);
    }

    private void mostraImagem() {
        Drawable drawablePackageImage = ResourcesUtil.getDrawable(this, pacote.getImage());
        imagemBanner.setImageDrawable(drawablePackageImage);
    }

    private void mostraPreco() {
        String currencyBrazilian = CurrencyUtil.formatCurrencyBrazilian(pacote.getPreco());
        preco.setText(currencyBrazilian);
    }

    private void mostraData() {
        String dataFormatadaDaViajem = DataUtil.periodoEmTexto(pacote.getDias());
        data.setText(dataFormatadaDaViajem);
    }

    private void configuraBotaoRealizarPagamento(){
        botaoRealizarPagamento.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               abreTelaPagamento();
            }
        });
    }

    private void abreTelaPagamento() {
        if(pacote != null && pacote.getPreco() != null){
            Intent abrePagamentoActvity = new Intent(ResumoPacoteActivity.this, PagamentoActivity.class);
            String precoPacoteString = pacote.getPreco().toString();
            abrePagamentoActvity.putExtra(CHAVE_RESUMO, precoPacoteString);
            startActivity(abrePagamentoActvity);
        }else{
            Toast.makeText(ResumoPacoteActivity.this, "Pacote inválido", Toast.LENGTH_SHORT).show();
        }
    }


}