package group19.ssd;

import group19.ssd.blockchain.transactions.Transaction;
import group19.ssd.blockchain.utils.Pair;
import group19.ssd.blockchain.utils.StringUtil;
import group19.ssd.miscellaneous.Configuration;
import group19.ssd.p2p.Kademlia;
import group19.ssd.p2p.KademliaClient;
import group19.ssd.p2p.KademliaServer;
import group19.ssd.blockchain.auctions.AuctionManager;
import group19.ssd.blockchain.auctions.Auction;
import group19.ssd.blockchain.auctions.Bid;

import java.net.Socket;
import java.util.*;
import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.util.Objects;
import java.util.Scanner;

public class App {
    public static AuctionManager auctionManager = new AuctionManager();
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

    public static void CreateAuctionMenu(Scanner scanner){
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
            byte[] auctionSignature = StringUtil.applyECDSASig(KademliaClient.wallet.getPrivateKey(), auctionData);
            // Create Auction object
            Auction auction = new Auction(itemId, timeout, sellerPublicKeyBase64.getBytes(), auctionHash, auctionSignature);
            boolean isAuctionValid = auction.isValidAuction();
            if(isAuctionValid) {
                // Call AuctionManager to start the auction
                auctionManager.addAuction(auction);
                auctionManager.startAuction(auction,KademliaClient.wallet);
                System.out.println("Auction valid and created successfully!");
            } else{
                System.out.println("Auction not valid!");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void SeeAuctions_n_Bid(Scanner scanner){
        try {
            int i = 1;
            System.out.println("Auctions available for bidding:\n");
            List<Pair<Auction, List<Bid>>> auctions = auctionManager.getAuctions();
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
                        auctionManager.placeBid(n_bid, KademliaClient.wallet);
                        System.out.println("Bid valid and created successfully!\n");
                    } else {
                        System.out.println("Error on creation of Bid!\n");
                    }
                } else {
                    System.out.println("Invalid auction selection.");
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void CloseYAuctions(Scanner scanner){
        System.out.println("Your Auctions: ");
        List<Auction> myAuctions = auctionManager.getAuctionsBySeller(KademliaClient.wallet.getPublicKey().getEncoded());
        if (myAuctions.isEmpty()) {
            System.out.println("No auctions found.");
        } else {
            for (int j = 0; j < myAuctions.size(); j++) {
                Auction a = myAuctions.get(j);
                System.out.println(j + ": " + new String(a.getItemId()) + ", Timeout: " + a.getTimeout());
            }
            System.out.println("Enter the index of the auction to close:");
            int auctionIndex = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            if (auctionIndex >= 0 && auctionIndex < myAuctions.size()) {
                Auction auctionToClose = myAuctions.get(auctionIndex);
                auctionManager.endAuction(auctionToClose.getHash(), KademliaClient.wallet);
                System.out.println("Auction closed successfully!");
            } else {
                System.out.println("Invalid index.");
            }
        }
    }

    public static void main(String[] args) throws InvalidKeySpecException, NoSuchAlgorithmException, IOException, InterruptedException {
        if (args.length > 0 && args[0].equals("--server")) {
            startServer();
        } else if (args.length > 0 && args[0].equals("--client")) {
            startClient();
        } else {
            System.out.println("Usage: java -jar your-jar-file-name.jar --server|--client");
        }
    }

    public static void startServer() throws IOException, InterruptedException {
        int port = 8888;
        if (Objects.equals(Configuration.knownNode, "")) {
            port = 8080;
        }

        KademliaServer server = new KademliaServer("localhost", port);
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

    public static void startClient() throws NoSuchAlgorithmException, InvalidKeySpecException {
        int port;
        if (Objects.equals(Configuration.knownNode, "")) {
            port = 8080;
        } else {
            port = 8888;
        }

        KademliaClient client = new KademliaClient();
        client.setup(port, "localhost");
        KademliaClient.startPinging();

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
        System.out.println("Private Key Type: " + KademliaClient.wallet.getPrivateKey().getAlgorithm());
        System.out.println("Public Key Type: " + KademliaClient.wallet.getPublicKey().getAlgorithm());

        Scanner scanner = new Scanner(System.in);

        while (true) {
            CreateMenu();

            int input = scanner.nextInt();
            scanner.nextLine();

            switch (input) {
                case 1:     // Personal Information
                    System.out.println("ID: " + KademliaClient.id);
                    System.out.println(KademliaClient.ip + ":" + KademliaClient.port);
                    System.out.println("Public Key: " + KademliaClient.publicKey);
                    break;
                case 2:     // Start Mining
                    KademliaClient.startMining();
                    System.out.println("Started Mining");
                    break;
                case 3:     // See Blockchain
                    KademliaClient.blockchain.printBlockChain();
                    break;
                case 4:     // See KBucket
                    KademliaClient.kbucket.print();
                    break;
                case 5: // Create Auction
                    CreateAuctionMenu(scanner);
                    break;
                case 6: //See available Auctions and choose auction to play bid on
                    SeeAuctions_n_Bid(scanner);
                    break;
                case 7: //Close your Auctions
                    CloseYAuctions(scanner);
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
