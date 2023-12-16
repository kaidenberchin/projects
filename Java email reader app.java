import javax.mail.*;
import java.util.Properties;

public class EmailClient {

    private Session emailSession;
    private Store store;
    private String username;
    private String password;

    public EmailClient(String host, String storeType, String user, String password) {
        this.username = user;
        this.password = password;
        Properties properties = new Properties();

        properties.put("mail.smtp.host", host);
        properties.put("mail.smtp.port", "587");
        properties.put("mail.smtp.auth", "true");
        properties.put("mail.smtp.starttls.enable", "true");
        properties.put("mail.store.protocol", storeType);

        // Create a session
        emailSession = Session.getDefaultInstance(properties);
    }

    public boolean connect() {
        try {
            // Connect to the email server
            store = emailSession.getStore("imaps");
            store.connect(username, password);
            return true;
        } catch (NoSuchProviderException e) {
            e.printStackTrace();
            return false;
        } catch (MessagingException e) {
            e.printStackTrace();
            return false;
        }
    }

    public void checkInbox() throws MessagingException {
        // Open the inbox folder
        Folder inbox = store.getFolder("INBOX");
        inbox.open(Folder.READ_ONLY);

        // Retrieve messages
        Message[] messages = inbox.getMessages();
        for (int i = 0; i < messages.length; i++) {
            Message message = messages[i];
            System.out.println("---------------------------------");
            System.out.println("Email Number " + (i + 1));
            System.out.println("Subject: " + message.getSubject());
            System.out.println("From: " + message.getFrom()[0]);
        }

        // Close the inbox folder
        inbox.close(false);
    }

    public static void main(String[] args) {
        String host = "imap.gmail.com"; // or your IMAP host
        String mailStoreType = "imaps";
        String username = "your_email@gmail.com";
        String password = "your_password";

        EmailClient client = new EmailClient(host, mailStoreType, username, password);

        if (client.connect()) {
            try {
                client.checkInbox();
            } catch (MessagingException e) {
                e.printStackTrace();
            }
        } else {
            System.out.println("Could not connect to the email server.");
        }
    }
}