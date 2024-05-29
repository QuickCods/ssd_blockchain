package group19.ssd;

import group19.ssd.blockchain.Blockchain;
import group19.ssd.blockchain.transactions.Transaction;
import group19.ssd.blockchain.utils.Pair;
import group19.ssd.blockchain.utils.StringUtil;
import group19.ssd.miscellaneous.Configuration;
import group19.ssd.p2p.*;
import group19.ssd.blockchain.auctions.AuctionManager;
import group19.ssd.blockchain.auctions.Auction;
import group19.ssd.blockchain.auctions.Bid;

import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.*;
import java.io.IOException;
import java.util.Objects;
import java.util.Scanner;

public class App {

    public static Blockchain b = new Blockchain();
    public static AuctionManager auctionManager = new AuctionManager();


    public static ArrayList<Transaction> transactions = new ArrayList<>();
    public static ArrayList<Auction> myAuctions = new ArrayList<>();

    public static void CreateMenu() {
        StringBuilder menu = new StringBuilder("Menu\n");
        menu.append("1 -> Personal Information\n")
                .append("2 -> Start Mining\n")
                .append("3 -> See Blockchain\n")
                .append("4 -> See KBucket\n")
                .append("5 -> Create Auction\n")
                .append("6 -> See Auctions and which Auction to bid on\n")
                .append("7 -> Close your Auctions\n")
                .append("8 -> Exit\n")
                .append("Introduce a value between 1 and 8: ");
        System.out.println(menu);
    }
    public static void CreateAuctionMenu(KademliaClient client, Scanner scanner){
        try {
            System.out.println("Create Auction:");
            System.out.println("Enter item name:");
            String itemName = scanner.nextLine();
            // Prompt for additional information as needed
            System.out.println("Enter auction timeout (in milliseconds):");
            long timeout = scanner.nextLong();
            scanner.nextLine(); // Consume newline
            byte[] itemId = Base64.getEncoder().encode(itemName.getBytes());
            String sellerPublicKeyBase64 = Base64.getEncoder().encodeToString(KademliaClient.wallet.getPublicKey().getEncoded());
            String auctionData = itemName + timeout + sellerPublicKeyBase64;
            byte[] auctionHash = StringUtil.applySha256(auctionData).getBytes();
            byte[] auctionSignature = StringUtil.applyECDSASig(KademliaClient.wallet.getPrivateKey(), auctionData).getBytes();
            // Create Auction object
            Auction auction = new Auction(itemId, timeout, sellerPublicKeyBase64.getBytes(), auctionHash, auctionSignature);
            System.out.println("Auction valid and created successfully!!!!");
            auctionManager.startAuction(auction, KademliaClient.wallet);
            myAuctions.add(auction);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void SeeAuctions_n_Bid(KademliaClient client, Scanner scanner){
        try {
            int i = 1;
            System.out.println("Auctions available for bidding:\n");
            List<Pair<Auction, List<Bid>>> auctions = auctionManager.getAuctions();
            Blockchain bc = KademliaClient.blockchain;
            if (auctions.isEmpty()) {
                System.out.println("No auctions found!\n");
            } else {
                for (Pair<Auction, List<Bid>> pair : auctions) {
                    Auction auction1 = pair.getFirst();
                    long remainingTime = auction1.getTimeout() - System.currentTimeMillis();
                    if (remainingTime > 0) {
                        System.out.println("Item Name: " + new String(auction1.getItemId()));
                        System.out.println("Remaining Time: " + remainingTime + " milliseconds <- " + i + "\n");
                    }
                    i++;
                }
                System.out.println("Select the number corresponding to the auction you want to bid:\n");
                int Auction_to_bid = scanner.nextInt();
                scanner.nextLine(); // Consume newline
                if (Auction_to_bid >= 1 && Auction_to_bid < i) {
                    long bid_Time = System.currentTimeMillis();
                    Auction a = auctions.get(Auction_to_bid - 1).getFirst();
                    System.out.println("Place a bid on: " + new String(a.getItemId()));
                    System.out.println("Enter the bid amount:");
                    double bidAmount = scanner.nextDouble();
                    scanner.nextLine();

                    String transaction = scanner.next();
                    scanner.nextLine();

                    Bid n_bid = new Bid(Base64.getEncoder().encode(transaction.getBytes()), a.getHash(), bid_Time);
                    boolean is_BidValid = n_bid.isValidBid();
                    if (is_BidValid) {
                        auctionManager.placeBid(n_bid, client.wallet);
                        System.out.println("Bid valid and created successfully!\n");
                    } else {
                        System.out.println("Error on creation of Bid!\n");
                    }
                } else if(Auction_to_bid == 0) {
                    System.out.println("Back to main menu");
                } else{
                    System.out.println("Invalid Auction\n");
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void CloseYAuctions(KademliaClient client, Scanner scanner){
        System.out.println("Your Auctions: ");
        //List<Auction> myAuctions = auctionManager.getAuctionsBySeller(client.wallet.getPublicKey().getEncoded());
        if (myAuctions.isEmpty()) {
            System.out.println("No auctions found.");
        } else {
            for (int j = 0; j < myAuctions.size(); j++) {
                Auction a = myAuctions.get(j);
                System.out.println((j + 1) + ": " + new String(a.getItemId()) + ", Timeout: " + a.getTimeout());
            }
            System.out.println("Type 0 to go back to main menu");
            System.out.println("Enter the index of the auction to close:");
            int auctionIndex = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            if (auctionIndex == 0) {
                System.out.println("Returning to main menu...");
                return; // Go back to main menu
            } else if (auctionIndex > 0 && auctionIndex <= myAuctions.size()) {
                Auction auctionToClose = myAuctions.get(auctionIndex - 1);
                auctionManager.endAuction(auctionToClose.getHash(), KademliaClient.wallet);
                myAuctions.remove(auctionIndex-1);
                System.out.println("Auction closed successfully!");
                return; // Go back to main menu after closing the auction
            }else {
                System.out.println("Invalid index.");
            }
        }
    }

    public static void main(String[] args) throws IOException, InterruptedException {
        auctionManager.blockchain = b;
        if (args.length > 0 && args[0].equals("--server")) {
            startServer();
        } else if (args.length > 0 && args[0].equals("--client")) {
            KademliaClient client = new KademliaClient();
            startClient(client);
        } else {
            System.out.println("Usage: java -jar your-jar-file-name.jar --server|--client");
        }
    }

    public static void startServer() throws IOException, InterruptedException {
        int port = 8888;
        if (Objects.equals(Configuration.knownNode, "")) {
            port = 8080;
        }

        KademliaServer server = new KademliaServer("192.168.10.0", port);
        // Initialize the interceptor
        server.start();

        System.out.println("Server started on port " + port);

        // Keep the server running
        Scanner scanner = new Scanner(System.in);
        System.out.println("Press Enter to stop the server...");
        scanner.nextLine();  // This will block the main thread until Enter is pressed

        // Cleanup code if needed
        server.stop();
        System.out.println("Server stopped.");
    }

    public static void startClient(KademliaClient client) throws UnknownHostException {
        int port;
        if (Objects.equals(Configuration.knownNode, "")) {
            port = 8080;
        } else {
            port = 8888;
        }
        Random rand = new Random();
        String firstThreeOctets = "192.60.10"; // Modify this as needed
        // Generate a random number for the last octet
        int lastOctet = rand.nextInt(256);
        String Fip = firstThreeOctets + "." + lastOctet;
        if(Fip.endsWith("0")){
            lastOctet = rand.nextInt(256);
            Fip = firstThreeOctets + "." + lastOctet;
        }
        client.setup(port, Fip);
        KademliaClient.startPinging();
        System.out.println("Client started on port " + port);
        Kademlia.findNode(KademliaClient.id);
        // Start a thread to monitor the server status
        Thread serverMonitor = new Thread(() -> {
            while (true) {
                try {
                    Thread.sleep(5000); // Check every 5 seconds
                    new Socket("localhost", port).close();
                } catch (IOException e) {
                    System.out.println("Server is down. Exiting client...");
                    System.exit(0);
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                    break;
                }
            }
        });
        serverMonitor.start();
        Scanner scanner = new Scanner(System.in);
        //
        while (true) {
            CreateMenu();

            int input = scanner.nextInt();
            scanner.nextLine();

            switch (input) {
                case 1:     // Personal Information
                    System.out.println("ID: " + KademliaClient.id);
                    System.out.println("IP: " + KademliaClient.ip + "; Port :" + KademliaClient.port);
                    System.out.println("Public Key: " + KademliaClient.publicKey);
                    break;
                case 2:     // Start Mining
                    KademliaClient.startMining();
                    break;
                case 3:     // See Blockchain
                    KademliaClient.blockchain.printBlockChain();
                    break;
                case 4:     // See KBucket
                    KademliaClient.kbucket.print();
                    break;
                case 5: // Create Auction
                    CreateAuctionMenu(client, scanner);
                    break;
                case 6: //See available Auctions and choose auction to play bid
                    SeeAuctions_n_Bid(client, scanner);
                    break;
                case 7: //Close your Auctions
                    CloseYAuctions(client, scanner);
                    break;
                case 8:     // Exit
                    System.exit(0);
                    break;
                default:
                    System.out.println("Invalid input");
                    break;
            }
        }
    }
}
