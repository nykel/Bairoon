package br.com.bairoon.model.negocio.fonts;


public enum FontDosis {
    DOSIS_REGULAR("fonts/Dosis/Dosis-Regular.ttf"),
    DOSIS_MEDIUM("fonts/Dosis/Dosis-Bold.ttf"),
    DOSIS_BOLD("fonts/Dosis/Dosis-Bold.ttf");

    private final String variacao;

    FontDosis(String variacao) {
        this.variacao = variacao;
    }

    public String getVariacao() {
        return variacao;
    }
}
