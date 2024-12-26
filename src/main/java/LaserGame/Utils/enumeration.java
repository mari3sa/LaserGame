package LaserGame.Utils;

public class enumeration {
    public enum StatoPrenotazione {
        CONFERMATA,
        ANNULLATA,
        ATTESA
    }

    public enum TipoUtente{
        CLIENTE,
        ADMIN
    }

    public enum TipoAbbonamento{
        SETTIMANALE,
        MENSILE,
        ANNUALE
    }


    public enum StatoPagamento {
        SUCCESSO,
        FALLITO,
        IN_ATTESA
    }

    public final static String usernameAdmin= "pippo@gmail.com";
    public final static String passwordAdmin = "pippo";

}

