package no.mesan.mobil.androidworkshop.task;

public interface ResponseListener<T> {

    void success(T t);
    void error();
}
