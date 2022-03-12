package com.company;

import java.io.UnsupportedEncodingException;
import java.util.Arrays;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Main {
    public static final String AUSTIN_POWERS = "Austin Powers";
    public static final String WEAPONS = "weapons";
    public static final String BANNED_SUBSTANCE = "banned substance";

    public static void main(String[] args)	throws UnsupportedEncodingException {

            Logger logger = Logger.getLogger(Main.class.getName());
            logger.setLevel(Level.INFO);
            Inspector inspector = new Inspector();
            Spy spy = new Spy(logger);
            Thief thief = new Thief(10000);
            MailService variousWorkers[] = new MailService[]{spy, thief, inspector};
            UntrustworthyMailWorker worker = new UntrustworthyMailWorker(variousWorkers);
            Package pack1 = new Package("ВВЖ", 32);
            Package pack2 = new Package("рпакетный двигатель ", 2500000);
            Package pack3 = new Package("stones", 1000);
            Package pack4 = new Package("banned substance", 99);
            Package pack5 = new Package("tiny bomb", 9000);
            AbstractSendable correspondence[] = {
                    new MailMessage("Oxxxymiron", "Гнойный", "Я здесь чисто по фану, поглумиться над слабым\n" +
                            "Ты же вылез из мамы под мой дисс на Бабана...."),
                    new MailMessage("Гнойный", "Oxxxymiron", "....Что? Так болел за Россию, что на нервах терял ганглии.\n" +
                            "Но когда тут проходили митинги, где ты сидел? В Англии!...."),
                    new MailMessage("Жриновский", AUSTIN_POWERS, "Бери пацанов, и несите меня к воде."),
                    new MailMessage(AUSTIN_POWERS, "Пацаны", "Го, потаскаем Вольфовича как Клеопатру"),
                    new MailPackage("берег", "море", pack1),
                    new MailMessage("NASA", AUSTIN_POWERS, "Найди в России ракетные двигатели и лунные stones"),
                    new MailPackage(AUSTIN_POWERS, "NASA", pack2),
                    new MailPackage(AUSTIN_POWERS, "NASA", pack3),
                    new MailPackage("Китай", "КНДР", pack4),
                    new MailPackage(AUSTIN_POWERS, "ИГИЛ (запрещенная группировка", pack5),
                    new MailMessage(AUSTIN_POWERS, "Психиатр", "Помогите"),
            };
            Arrays.stream(correspondence).forEach(parcell -> {
                try {
                    worker.processMail(parcell);
                } catch (StolenPackageException e) {
                    logger.log(Level.WARNING, "Inspector found stolen package: " + e);
                } catch (IllegalPackageException e) {
                    logger.log(Level.WARNING, "Inspector found illegal package: " + e);
                }
            });

            System.out.println(thief.getStolenValue());
        }


    public static class UntrustworthyMailWorker implements MailService {
        private RealMailService realMailService;
        private MailService[] mailServices;

        public UntrustworthyMailWorker(MailService[] mailServices) {
            this.mailServices = mailServices;
            this.realMailService = new RealMailService();
        }

        public Sendable processMail(Sendable mail) {
            for (int i = 0; i < mailServices.length; i++) {
                mail = mailServices[i].processMail(mail);
            }
            return realMailService.processMail(mail);
        }

        public RealMailService getRealMailService() {
            return this.realMailService;
        }
    }

    public static class Spy implements MailService {
        private final Logger LOG;

        public Spy(final Logger logger) {
            this.LOG = logger;
        }


        public Sendable processMail(Sendable mail) {
            if (mail instanceof MailMessage) {
                MailMessage mail2 = (MailMessage) mail;
                if (mail2.getFrom().equals(AUSTIN_POWERS) || mail2.getTo().equals(AUSTIN_POWERS)) {
                    LOG.warning("Detected target mail correspondence: from " + mail2.getFrom() + " to "
                            + mail2.getTo() + " " + "\"" + mail2.getMessage() + "\"");
                } else {
                    LOG.info("Usual correspondence: from " + mail2.getFrom() + " to " + " \" " + mail2.getTo());
                }
            }
            return mail;
        }
    }

    public static class Thief implements MailService {
        private int minPrice;
        private int stolenPrice;

        public Thief(int minPrice) {
            this.minPrice = minPrice;
            this.stolenPrice = 0;
        }

        public int getStolenValue() {
            return stolenPrice;
        }

        public Sendable processMail(Sendable mail) {
            if (mail instanceof MailPackage) {
                MailPackage pac = (MailPackage) mail;
                if (pac.getContent().getPrice() >= this.minPrice) {
                    this.stolenPrice += pac.getContent().getPrice();
                    return new MailPackage(mail.getFrom(), mail.getTo(),
                            new Package("stones instead of " + pac.getContent().getContent(), 0));
                }
            }
            return mail;
        }
    }

    public static class Inspector implements MailService {
        public Sendable processMail(Sendable mail) {
            if (mail.getClass() == MailPackage.class) {
                Package pac = ((MailPackage) mail).getContent();
                String content = pac.getContent();
                if (content.contains(WEAPONS) || content.contains(BANNED_SUBSTANCE)) {
                    throw new IllegalPackageException();
                } else if (content.indexOf("stones instead of ") == 0) {
                    throw new StolenPackageException();
                }
            }
            return mail;
        }
    }

    public static class IllegalPackageException extends RuntimeException {
        public IllegalPackageException() {
            super("ПУШКИ");
        }
    }

    public static class StolenPackageException extends RuntimeException {
        public StolenPackageException() {
            super("ВОРИШКИ");
        }
    }

    public static interface Sendable {
        String getFrom();
        String getTo();
    }

    public static abstract class AbstractSendable implements Sendable {

        protected final String from;
        protected final String to;

        public AbstractSendable(String from, String to) {
            this.from = from;
            this.to = to;
        }

        @Override
        public String getFrom() {
            return from;
        }

        @Override
        public String getTo() {
            return to;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;

            AbstractSendable that = (AbstractSendable) o;

            if (!from.equals(that.from)) return false;
            if (!to.equals(that.to)) return false;

            return true;
        }
    }

    public static class MailMessage extends AbstractSendable {

        private final String message;

        public MailMessage(String from, String to, String message) {
            super(from, to);
            this.message = message;
        }

        public String getMessage() {
            return message;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            if (!super.equals(o)) return false;

            MailMessage that = (MailMessage) o;

            if (message != null ? !message.equals(that.message) : that.message != null) return false;

            return true;
        }
    }

    public static class MailPackage extends AbstractSendable {
        private final Package content;

        public MailPackage(String from, String to, Package content) {
            super(from, to);
            this.content = content;
        }

        public Package getContent() {
            return content;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            if (!super.equals(o)) return false;

            MailPackage that = (MailPackage) o;

            if (!content.equals(that.content)) return false;

            return true;
        }
    }

    public static class Package {
        private final String content;
        private final int price;

        public Package(String content, int price) {
            this.content = content;
            this.price = price;
        }

        public String getContent() {
            return content;
        }

        public int getPrice() {
            return price;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;

            Package aPackage = (Package) o;

            if (price != aPackage.price) return false;
            if (!content.equals(aPackage.content)) return false;

            return true;
        }
    }

    public static interface MailService {
        Sendable processMail(Sendable mail);
    }

    public static class RealMailService implements MailService {

        @Override
        public Sendable processMail(Sendable mail) {
            // Здесь описан код настоящей системы отправки почты.
            return mail;
        }
    }


}
