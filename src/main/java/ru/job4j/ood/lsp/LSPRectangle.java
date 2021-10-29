package ru.job4j.ood.lsp;

public class LSPRectangle {
    private int width;
    private int height;

    public void setWidth(int width) {
        this.width = width;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public int perimeter() {
        return 2 * width + 2 * height;
    }
}

class Square extends LSPRectangle {
    public void setSide(int side) {
        super.setHeight(side);
        super.setWidth(side);
    }
}

/**
 * Пример 3 (нарушение принципа LSP):
 * Прямоугольник и квадрат.
 * Нарушение LSP - не правильное наследование, при подстановке
 * базового типа (Rectangle) метод setSide() для квадрата не
 * доступен, тем самым можно ошибочно присвоить не правильные
 * значения сторон.
 */
class Perimeter {
    public static void main(String[] args) {
        Square square = new Square();
        square.setSide(5);
        System.out.println(square.perimeter());

        LSPRectangle figure = new Square();
        figure.setHeight(5);
        figure.setWidth(6);
        System.out.println(figure.perimeter());
    }
}
