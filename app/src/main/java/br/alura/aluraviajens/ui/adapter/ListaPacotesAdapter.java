package br.alura.aluraviajens.ui.adapter;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import br.alura.aluraviajens.util.DaysUtil;
import br.alura.aluraviajens.util.CurrencyUtil;
import br.alura.aluraviajens.R;
import br.alura.aluraviajens.util.ResourcesUtil;
import br.alura.aluraviajens.model.Pacote;

public class ListaPacotesAdapter extends BaseAdapter {
    private final List<Pacote> packages;
    private final Context context;

    public ListaPacotesAdapter(List<Pacote> packages, Context context) {
        this.packages = packages;
        this.context = context;
    }

    @Override
    public int getCount() {
        return packages.size();
    }

    @Override
    public Pacote getItem(int position) {
        return packages.get(position);
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int position, View view, ViewGroup viewGroup) {
        View viewCreated = createView(viewGroup);
        Pacote pacoteDevolvido = packages.get(position);
        preenchePacote(viewCreated, pacoteDevolvido);
        return viewCreated;
    }

    private void preenchePacote(View view, Pacote returnedPackage) {
        findViewByLocal(view, returnedPackage);
        findViewByImage(view, returnedPackage);
        findViewByDays(view, returnedPackage);
        findViewByPrice(view, returnedPackage);
    }

    private static void findViewByPrice(View view, Pacote returnedPackage) {
        TextView price = view.findViewById(R.id.item_pacote_preco);
        String currencyBrazilian = CurrencyUtil.formatCurrencyBrazilian(returnedPackage.getPreco());
        price.setText(currencyBrazilian);
    }
    private static void findViewByDays(View view, Pacote returnedPackage) {
        TextView days = view.findViewById(R.id.item_pacote_dias);
        String daysInText = DaysUtil.formatDaysInText(returnedPackage.getDias());
        days.setText(daysInText);
    }

    private void findViewByImage(View view, Pacote returnedPackage) {
        ImageView image = view.findViewById(R.id.item_pacote_imagem);
        Drawable drawablePackageImage = ResourcesUtil.getDrawable(context, returnedPackage.getImage());
        image.setImageDrawable(drawablePackageImage);
    }

    private static void findViewByLocal(View view, Pacote returnedPackage) {
        TextView locale = view.findViewById(R.id.item_pacote_local);
        locale.setText(returnedPackage.getLocal());
    }

    private View createView(ViewGroup viewGroup) {
        return LayoutInflater.from(context).inflate(R.layout.item_pacote, viewGroup, false);
    }

}
