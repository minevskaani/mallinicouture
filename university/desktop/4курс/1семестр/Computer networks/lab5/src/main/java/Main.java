import org.apache.commons.cli.*;
import org.apache.commons.cli.ParseException;

import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.mail.*;
import javax.mail.internet.*;
import java.io.IOException;
import java.util.Properties;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws MessagingException, IOException, InterruptedException, ParseException {

        final Options options = new Options();
        final CommandLineParser parser = new DefaultParser();

        options.addOption(Option.builder()
                .longOpt("username")
                .optionalArg(false)
                .hasArg()
                .optionalArg(false)
                .required()
                .desc("Username needed for authentication")
                .build()
        );
        options.addOption(Option.builder()
                .longOpt("to")
                .optionalArg(false)
                .hasArg()
                .optionalArg(false)
                .required()
                .desc("Email destination")
                .build()
        );
        options.addOption(Option.builder()
                .longOpt("password")
                .optionalArg(false)
                .hasArg()
                .optionalArg(false)
                .required()
                .desc("Password needed for authentication")
                .build()
        );
        HelpFormatter help = new HelpFormatter();
        CommandLine line = null;

        try {
            line = parser.parse(options, args);
        } catch (ParseException e) {
            help.printHelp("app", options,  true);
            System.exit(1);
        }
        final String username = line.getOptionValue("username");
        final String password = line.getOptionValue("password");
        final String to = line.getOptionValue("to");


        final String from = username;
        System.out.println("subject: ");
        Scanner sc = new Scanner(System.in);

        final String subject = sc.nextLine();

        System.out.println("text: ");
        final String text = sc.nextLine();


        System.out.println("fileName(optional): ");
        final String fileName = sc.nextLine();
        System.out.println("delay: ");
        final long delay = sc.nextLong();
        sc.close();

        Properties prop = new Properties();
        prop.setProperty("mail.smtp.host", "smtp.gmail.com");
        prop.setProperty("mail.smtp.port", "465");
        prop.setProperty("mail.smtp.auth", "true");
        prop.setProperty("mail.smtp.socketFactory.port", "465");
        prop.setProperty("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");

        Session session = Session.getInstance(prop,
                new javax.mail.Authenticator() {
                    protected PasswordAuthentication getPasswordAuthentication() {
                        return new PasswordAuthentication(username, password);
                    }
                });

        Message message = new MimeMessage(session);
        message.setFrom(new InternetAddress(from));
        message.setRecipients(
                Message.RecipientType.TO,
                InternetAddress.parse(to)
        );
        message.setSubject(subject);
        message.setText(text);

        // Add attachment
        if (!fileName.isBlank()) {
            Multipart multipart = new MimeMultipart();
            // text
            MimeBodyPart textPart = new MimeBodyPart();
            textPart.setContent(text, "text/plain");
            // attachment
            MimeBodyPart attachmentPart = new MimeBodyPart();
            DataSource source = new FileDataSource(fileName);
            attachmentPart.setDataHandler(new DataHandler(source));
            attachmentPart.setFileName(fileName);

            // add parts
            multipart.addBodyPart(textPart);
            multipart.addBodyPart(attachmentPart);

            message.setContent(multipart);
        }


        // Send message
        while (true) {
            Transport.send(message);
            System.out.println("Sent.");

            Thread.sleep(delay);
        }
    }
}
