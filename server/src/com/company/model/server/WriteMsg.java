//package com.company.model.server;
//
//import com.company.model.Message;
//
//import java.io.ObjectOutputStream;
//import java.net.Socket;
//
//public class WriteMsg extends Thread{
//    Connection connectionClient;
//    Socket socket;
//    private ObjectOutputStream writeMsg;
//
//    public WriteMsg(Connection connectionClient, Socket socket){
//        try {
//            this.connectionClient = connectionClient;
//            this.socket = socket;
//            writeMsg = new ObjectOutputStream(socket.getOutputStream());
//        }
//        catch (Exception e) {
//            System.out.println("Ошибка при создании WriteMsgServer: " + e.getMessage());
//        }
//    }
//    @Override
//    public void run(){
//        try {
//            while (true){
//                Thread.currentThread().sleep(1);
//                write(new Message("game", connectionClient.server.getGame().copy()));
//            }
//        }
//        catch (Exception e) {
//            try {
//                socket.close();
//            }
//            catch (Exception ee){
//                System.out.println("Ошибка при закрытии сокета: " + e.getMessage());
//            }
//            System.out.println("Ошибка при отправке сообщения клиенту: " + e.getMessage());
//        }
//    }
//
//    synchronized public void write(Message message) throws Exception{
//        writeMsg.writeObject(message);
//    }
//}