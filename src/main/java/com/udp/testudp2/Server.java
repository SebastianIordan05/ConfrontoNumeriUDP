/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */
package com.udp.testudp2;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.SocketException;
import java.util.Scanner;

/**
 *
 * @author seba2
 */
public class Server {

    public static void main(String[] args) {
        int port = 9980;
        final Scanner sc = new Scanner(System.in);
        try (final DatagramSocket socket = new DatagramSocket(port)) {
            System.out.println("Listening...");

            byte[] buffer = new byte[2048];
            final DatagramPacket packet = new DatagramPacket(buffer, buffer.length);
            socket.receive(packet);

            final Numeri read = (Numeri) new ObjectInputStream(new ByteArrayInputStream(packet.getData())).readObject();
            
            if (read.getLhs() == read.getRhs()) 
                System.out.println(read.getLhs() + " = " + read.getRhs());
            else if (read.getLhs() > read.getRhs())
                System.out.println(read.getLhs() + " > " + read.getRhs());
            else if (read.getLhs() < read.getRhs())
                System.out.println(read.getLhs() + " < " + read.getRhs());
            
        } catch (final SocketException e) {
        } catch (final IOException | ClassNotFoundException e) {
        }
    }
}
