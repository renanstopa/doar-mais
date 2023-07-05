package com.api.doarmais.notifications;

public interface Notificador<T> {

  public void enviar(T entidade);
}
