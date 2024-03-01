package br.alura.aluraviajens.util;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;

import br.alura.aluraviajens.model.Pacote;

public class ResourcesUtil {

    public static Drawable getDrawable(Context context, String drawableInText) {
        Resources resources = context.getResources();
        int idOfDrawable = resources.getIdentifier(drawableInText, "drawable", context.getPackageName());
        return resources.getDrawable(idOfDrawable);
    }
}
