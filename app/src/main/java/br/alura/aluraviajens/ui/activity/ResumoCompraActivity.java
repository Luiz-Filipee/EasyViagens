package br.alura.aluraviajens.ui.activity;

import static br.alura.aluraviajens.ui.activity.ConstantesActivity.CHAVE_PACOTE;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import br.alura.aluraviajens.R;
import br.alura.aluraviajens.model.Pacote;
import br.alura.aluraviajens.util.CurrencyUtil;
import br.alura.aluraviajens.util.DataUtil;
import br.alura.aluraviajens.util.ResourcesUtil;

public class ResumoCompraActivity extends AppCompatActivity {
    private static final String TITULO_APPBAR = "Resumo da compra";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_resumo_compra);
        setTitle(TITULO_APPBAR);
        carregaPacoteRecebido();
        configuraBotaoHome();
    }

    private void carregaPacoteRecebido() {
        Intent dados = getIntent();
        if (dados.hasExtra(CHAVE_PACOTE)) {
            Pacote pacote = (Pacote) dados.getSerializableExtra(CHAVE_PACOTE);
            inicializaCampos(pacote);
        }
    }

    public void inicializaCampos(Pacote pacote){
        mostraLocal(pacote);
        mostraImagem(pacote);
        mostraData(pacote);
        mostraPreco(pacote);
    }

    public void configuraBotaoHome(){
        FloatingActionButton botaoHome = findViewById(R.id.resumo_compra_floating_button);
        botaoHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                abreTelaHome();
            }
        });
    }

    private void abreTelaHome(){
        startActivity(new Intent(ResumoCompraActivity.this, ListaPacotesActivity.class));
    }


    private void mostraLocal(Pacote pacote) {
        TextView localImage = findViewById(R.id.resumo_compra_local);
        localImage.setText(pacote.getLocal());
    }

    private void mostraImagem(Pacote pacote) {
        ImageView imagem = findViewById(R.id.resumo_compra_imagem_local);
        Drawable drawablePackageImage = ResourcesUtil.getDrawable(this, pacote.getImage());
        imagem.setImageDrawable(drawablePackageImage);
    }

    private void mostraPreco(Pacote pacote) {
        TextView preco = findViewById(R.id.resumo_compra_preco);
        String currencyBrazilian = CurrencyUtil.formatCurrencyBrazilian(pacote.getPreco());
        preco.setText(currencyBrazilian);
    }

    private void mostraData(Pacote pacote) {
        TextView data = findViewById(R.id.resumo_compra_data);
        String dataFormatadaDaViajem = DataUtil.periodoEmTexto(pacote.getDias());
        data.setText(dataFormatadaDaViajem);
    }

    
}