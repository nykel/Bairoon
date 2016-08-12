package br.com.bairoon.model.negocio.fonts;

import android.app.Activity;
import android.graphics.Typeface;

public class ModificadorFonte {
    private static ModificadorFonte instance;

    public static ModificadorFonte getInstance() {
        if (instance == null) {
            instance = new ModificadorFonte();
        }
        return instance;
    }

    public Typeface configurarFont(Object tipoFont, Activity activity) throws IllegalArgumentException {

        String variacao = "";
        if (tipoFont instanceof FontDosis) {
            FontDosis fontDosis = (FontDosis) tipoFont;
            variacao = fontDosis.getVariacao();
        }

        if (tipoFont instanceof FontRoboto) {
            FontRoboto fontDosis = (FontRoboto) tipoFont;
            variacao = fontDosis.getVariacao();
        }

        if (variacao.isEmpty()) {
            throw new IllegalArgumentException("A variação de fonte informada não existe");
        }

        Typeface typeface = Typeface.createFromAsset(activity.getApplicationContext().getAssets(), variacao);

        return typeface;
    }

}
