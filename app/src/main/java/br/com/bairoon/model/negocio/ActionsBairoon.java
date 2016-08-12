package br.com.bairoon.model.negocio;

public enum ActionsBairoon {
    HOME(1l, true),
    PESQUISAR(2l, true),
    EVENTOS(3l, false),
    COMO_ANUNCIAR(4l, false),
    MENSAGENS(5l, false),
    CONFIGURACOES(6l, false),
    NOTIFICACOES(7l, false),
    SAIR(8l, true),
    SOBRE_O_APP(9l, false),;

    private long identificador;
    private boolean implementado;

    ActionsBairoon(long identificador, boolean implementado) {
        this.identificador = identificador;
        this.implementado = implementado;
    }

    public boolean isImplementado() {
        return implementado;
    }

    public long getIdentificador() {
        return identificador;
    }

    public final static ActionsBairoon recuperarAction(final long identificador) {

        ActionsBairoon actionRecuperada = null;

        for (ActionsBairoon actionCongresso : ActionsBairoon.values()) {
            if (actionCongresso.getIdentificador() == identificador)
                actionRecuperada = actionCongresso;
        }

        if (actionRecuperada == null)
            throw new IllegalArgumentException("Nenhuma activity foi encontrada com o identificador informado!");

        return actionRecuperada;
    }
}
