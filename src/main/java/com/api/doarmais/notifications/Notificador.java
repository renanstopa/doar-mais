package com.api.doarmais.notifications;

import jakarta.mail.MessagingException;

public interface Notificador<T> {

  public void enviar(T entidade) throws MessagingException;
}
