package ru.job4j.ood.srp;

/**
 * Принцип единственной ответственности.
 * Задание:
 * Придумать примеры на нарушение принципа SRP
 * Один из принцыпов SOLID - Single Responsibility Principle
 * Каждый класс должен отвечать только за представления своего функционала,
 *
 * Здесь так же происходит нарушение приецыпа  SRP - класс должен предоставлять только один функционал,
 * - либо считывать из файла,
 * - либо записывать в файл,
 *   а тут и то и другое.
 * По принципу SRP надо этот класс разделить на два класса - Reader и Writer.
 */
public class WriteReader {

    /*
    Метод readFile считывает данные из файла
     */
    public void readFile() {
    }

    /*
    Метод writeToFile записывает данные в файл
     */
    public void writeToFile() {
    }
}
