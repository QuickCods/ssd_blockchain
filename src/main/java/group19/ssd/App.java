package group19.ssd;

import group19.ssd.blockchain.transactions.Transaction;
import group19.ssd.miscellaneous.Configuration;
import group19.ssd.p2p.Kademlia;
import group19.ssd.p2p.KademliaClient;
import group19.ssd.p2p.KademliaServer;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;

import java.util.Objects;
import java.util.Scanner;

public class App {
    public static void CreateMenu(){
        int k = 0;
        StringBuilder menu = new StringBuilder("Menu\n");
        while(k >= 0){
            ++k;
         switch(k){
             case 1:
                 menu.append(k).append(" -> Personal Information\n");
                 break;
             //case 2:
             //    menu.append(k).append(" -> Balance\n");
             //    break;
             //case 3:
             //    menu.append(k).append(" -> Send Coins\n");
             //    break;
             case 2:
                 menu.append(k).append(" -> Start Mining\n");
                 break;
             case 3:
                 menu.append(k).append(" -> See Blockchain\n");
                 break;
             case 4:
                 menu.append(k).append(" -> See KBucket\n");
                 break;
             case 5:
                 menu.append(k).append(" -> Exit\n");
                 break;
             default:
                 k = -1;
                 break;
         }
        }
        menu.append("Introduce a value between 1 and 6: \n");
        System.out.println(menu);
    }

    public static void main(String[] args) throws InvalidKeySpecException, NoSuchAlgorithmException, IOException {

        int port = 8888;

        if (Objects.equals(Configuration.knownNode, "")) {
            port = 8080;
        }

        KademliaClient client = new KademliaClient();
        client.setup(port, "localhost");
        KademliaClient.startPinging();

        KademliaServer server = new KademliaServer("localhost", port);
        server.start();

        Kademlia.findNode(KademliaClient.id);

        Scanner scanner = new Scanner(System.in);

        while(true){
            CreateMenu();

            int input = scanner.nextInt();
            scanner.nextLine();

            switch(input){
                case 1:     //Personal Information
                    System.out.println("ID: " + KademliaClient.id);
                    System.out.println(KademliaClient.ip + ":" + KademliaClient.port);
                    System.out.println("Public Key: " + KademliaClient.publicKey);
                    break;
                //case 2:     //Balance
                //    KademliaClient.wallet.printWalletBalance();
                //    break;
                //case 3:     //Send Coins
                //    System.out.println("Give receiver Public Key");
                //    String pubKey = scanner.nextLine();
                //    if(KademliaClient.publicKey.equals(pubKey)){
                //        System.out.println("Can't send to yourself");
                //    } else {
                //        System.out.println("Insert amount to send");
                //        int amount = scanner.nextInt();

                //        if(KademliaClient.wallet.getBalance() < amount){
                //            System.out.println("Error: Not enough balance");
                //        } else{
                //            System.out.println("Receiver Public Key: " + KademliaClient.publicKey);
                //            Transaction transaction = new Transaction(KademliaClient.publicKey, pubKey, amount);
                //            KademliaClient.shareTransaction(transaction, KademliaClient.id);
                //        }
                //    }
                //    break;
                case 2:     //Start Mining
                    KademliaClient.startMining();
                    break;
                case 3:     //See Blockchain
                    KademliaClient.blockchain.printBlockChain();
                    break;
                case 4:     //See KBucket
                    KademliaClient.kbucket.print();
                    break;
                case 5:     //Exit
                    System.exit(0);
                    break;
                default:
                    System.out.println("Invalid input");
                    break;
            }
        }
    }
}
