package dev.manyroads.projects.simplebankingsystem.stage4.example3;

public class CardSystem {
    private static CardSystem instance;


    public static synchronized CardSystem getInstance() {
        if (instance == null) {
            instance = new CardSystem();
        }
        return instance;
    }

    public Card addCard() {
        Card newCard = new Card();
        BankDB.createCardTableIfNotExist();
        int statusCode = BankDB.addNewCard(newCard);
        if (statusCode != 0) {
            return newCard;
        }
        return addCard();
    }

    /**
     * Returns the requested Card Object if login is successful, otherwise return null
     */
    public Card loginWithCredentials(String number, String PIN) {
        Card requestedCard = BankDB.getCardByNumber(number);
        if (requestedCard != null) {
            return PIN.equals(requestedCard.getPIN()) ? requestedCard : null;
        }
        return null;
    }

    public void addBalance(Card card, int amount) {
        int newBalance = card.getBalance() + amount;
        card.setBalance(newBalance);
        BankDB.updateBalance(card, newBalance);
    }

    public Card getCardByNumber(String number) {
        return BankDB.getCardByNumber(number);
    }

    public boolean transferMoney(Card sourceCard, Card cardToTransfer, int amount) {
        boolean success = BankDB.transferMoney(sourceCard, cardToTransfer, amount);
        if (success) {
            sourceCard.setBalance(sourceCard.getBalance() - amount);
            cardToTransfer.setBalance(cardToTransfer.getBalance() + amount);
            return true;
        } else {
            return false;
        }
    }

    public boolean closeAccount(Card loggedInCard) {
        return BankDB.deleteCard(loggedInCard);
    }


    /**
     * Check whether user provided number to transfer is valid.
     * <p>
     * if a number doesn't pass the luhn algorithm,
     * return a ValidityInfo with boolean false and
     * String("Probably you made a mistake in the card number. Please try again!")
     * </p>
     * <br/>
     * <p>
     * if a number to transfer is the same as the logged in card,
     * return a ValidityInfo with boolean false and
     * String("You can't transfer money to the same account!")
     * </p>
     * <br/>
     * <p>
     * if a number is valid,
     * return a ValidityInfo with boolean true and String("Card number is valid")
     * </p>
     *
     * @return a ValidityInfo that has boolean variable valid and a String variable message.
     */
    public ValidityInfo checkTransferValidity(String numberToTransfer) {
        // check with luhn algorithm
        String numberNoChecksum = numberToTransfer.substring(0, numberToTransfer.length() - 1);
        String luhnChecksum = Luhn.generateValidChecksum(numberNoChecksum);
        char checkSum = luhnChecksum.charAt(0);

        if (numberToTransfer.charAt(numberToTransfer.length() - 1) != checkSum) {
            return new ValidityInfo(false,
                    "Probably you made a mistake in the card number. Please try again!");
        }

        String loggedInCardNumber = Main.getLoggedInCard().getNumber();
        if (numberToTransfer.equals(loggedInCardNumber)) {
            return new ValidityInfo(false, "You can't transfer money to the same account!");
        }

        return new ValidityInfo(true, "Card number is valid");

    }


}

