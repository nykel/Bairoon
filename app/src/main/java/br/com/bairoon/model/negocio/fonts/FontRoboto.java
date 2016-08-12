package br.com.bairoon.model.negocio.fonts;

/**
 * Created by Katherine on 14/09/2015.
 */
public enum FontRoboto {

    ROBOTO_REGULAR("fonts/Roboto/Roboto-Regular.ttf"),
    ROBOTO_MEDIUM("fonts/Roboto/Roboto-Medium.ttf"),
    ROBOTO_BOLD("fonts/Roboto/Roboto-Bold.ttf");

    private final String variacao;

    FontRoboto(String variacao) {
        this.variacao = variacao;
    }

    public String getVariacao() {
        return variacao;
    }

}
