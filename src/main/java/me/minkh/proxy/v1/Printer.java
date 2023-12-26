package me.minkh.proxy.v1;

public class Printer implements Printable {

    private String name;

    public Printer() {
        heavyJob("Printer의 인스턴스를 생성중");
    }

    public Printer(String name) {
        this.name = name;
        heavyJob("Printer의 인스턴스 (" + name + ")을 생성 중");
    }

    @Override
    public void setPrinterName(String name) {
        this.name = name;
    }

    @Override
    public String getPrinterName() {
        return name;
    }

    @Override
    public void print(String string) {
        System.out.println("===" + name + "===" );
        System.out.println(string);
    }

    // 무엇인가 5초동안 함
    private void heavyJob(String msg) {
        System.out.println(msg);
        for (int i = 0; i < 5; i++) {
            try {
                Thread.sleep(1_000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            System.out.print(".");
        }
        System.out.println("완료.");
    }

}
