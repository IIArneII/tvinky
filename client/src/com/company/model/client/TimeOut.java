package com.company.model.client;

public class TimeOut extends Thread{
    public static int timeout = 0;
    public static final int MAX_TIME = 5;
    public boolean launched;
    private Client client;

    public TimeOut(Client client){
        this.client = client;
    }

    @Override
    public void run() {
        launched = true;
        while (launched){
            try {
                timeout++;
                if(timeout > MAX_TIME){
                    System.out.println("Превышено время ожидания сервера");
                    client.stop();
                }
                sleep(1000);
            }
            catch (Exception e){}
        }
    }
}
